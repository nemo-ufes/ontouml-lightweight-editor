package br.ufes.inf.nemo.ocl.ocl2alloy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.expressions.BooleanLiteralExp;
import org.eclipse.ocl.expressions.CollectionItem;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.expressions.CollectionLiteralExp;
import org.eclipse.ocl.expressions.EnumLiteralExp;
import org.eclipse.ocl.expressions.IfExp;
import org.eclipse.ocl.expressions.IntegerLiteralExp;
import org.eclipse.ocl.expressions.InvalidLiteralExp;
import org.eclipse.ocl.expressions.IterateExp;
import org.eclipse.ocl.expressions.IteratorExp;
import org.eclipse.ocl.expressions.LetExp;
import org.eclipse.ocl.expressions.NullLiteralExp;
import org.eclipse.ocl.expressions.OperationCallExp;
import org.eclipse.ocl.expressions.PropertyCallExp;
import org.eclipse.ocl.expressions.RealLiteralExp;
import org.eclipse.ocl.expressions.StringLiteralExp;
import org.eclipse.ocl.expressions.TupleLiteralExp;
import org.eclipse.ocl.expressions.TypeExp;
import org.eclipse.ocl.expressions.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.expressions.VariableExp;
import org.eclipse.ocl.utilities.ExpressionInOCL;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.State;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.IteratorException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.LiteralException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.OperationException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.StereotypeException;
import br.ufes.inf.nemo.ocl.parser.OCLParser;

/**
 * This class is used for visit the OCL AST and transform an OCL Constraint into Alloy. 
 *
 * @author John Guerson
 */

public class OCL2AlloyVisitor extends org.eclipse.ocl.utilities.AbstractVisitor <String,Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint> 
{	
	protected OCLParser oclparser;
	protected OntoUMLParser refparser;
	protected OCL2AlloyOption opt;
	
	protected int derive_counter = 0;
	protected int inv_counter = 0;	
	protected String library; // additional operations that alloy does not fully support
	protected Constraint currentConstraint; //save the current constraint being transformed
	protected List<Operation> definedOperationsList = new ArrayList<Operation>();
	
    /** Constructor */
    public OCL2AlloyVisitor (OCLParser oclparser,OntoUMLParser refparser, OCL2AlloyOption opt) 
    {	
		this.oclparser = oclparser;
		this.refparser = refparser;
		this.opt = opt;
	}    
       	
   	public String getLibrary() { return library; }
   	
    /** Visits the OperationCallExp.  */
	@Override
    public String handleOperationCallExp (OperationCallExp<Classifier,Operation> operCallExp, String sourceResult, java.util.List<String> argumentsResult) 
    {    	
		StringBuffer result = new StringBuffer();
    	Operation oper = operCallExp.getReferredOperation();    	
    	String operTypeResult = oper.getType().getName();
		String operName = oper.getName();	
		//user defined operations...
		boolean isUserDefined = false;
		for(Operation op: definedOperationsList) if(operName.equals(op.getName())) isUserDefined=true;				
		if(operName.equals("allInstances")) { return sourceResult; }		
		if(operName.equals("size")) { return "("+"#" + sourceResult+ ")"; }		
		if(operName.equals("isEmpty")) { return "("+"no " + sourceResult + ")"; }		
		if(operName.equals("notEmpty")) { return "("+"some " + sourceResult+ ")"; }		
		if(operName.equals("not")) { return "("+"! " + sourceResult+ ")"; }			
		if(operName.equals("oclIsUndefined")) { return "("+"#" + sourceResult + " = 0"+ ")"; }		
		if(operName.equals("abs")) 
		{ 
			library += "fun abs [self: Int] : Int"+"\n"+"{ self < 0 implies self.negate else self }"+"\n\n";
			return "abs[" + sourceResult + "]"; 
		}		
		if(operName.equals("floor")) { return sourceResult; }		
		if(operName.equals("round")) { return sourceResult; }		
        if(operName.equals("sum")) { return "(sum " + sourceResult + ")"; }        
        if(operName.equals("asSet")) { return sourceResult; }        
        if(operName.equals("flatten")) { return sourceResult; }        
		if(operName.equals("oclAsType")) { return sourceResult; }		
		if(operName.equals("-")) 
		{
			java.util.Iterator<String> iter = argumentsResult.iterator();			
			if(!iter.hasNext()) { return "negate[" + sourceResult +"]"; }			
			else if( iter.hasNext() && operTypeResult.equals("Set(T)")) { return "("+sourceResult + " - " + iter.next()+ ")"; }			
			else if( iter.hasNext() && operTypeResult.equals("Real")){ return "("+sourceResult+").minus["+iter.next()+"]"; }
		}		
		if(operName.equals("max"))
		{
			java.util.Iterator<String> iter = argumentsResult.iterator();			
			if(!iter.hasNext()) { return "{ i: "+sourceResult+" | all j: "+sourceResult+" | int[i] >= int[j] }"; }
		}		
		if(operName.equals("min"))
		{
			java.util.Iterator<String> iter = argumentsResult.iterator();			
			if(!iter.hasNext()) { return "{ i: "+sourceResult+" | all j: "+sourceResult+" | int[i] <= int[j] }"; }
		}		
		//user defined operations...
		if(isUserDefined) result.append("("+sourceResult+")."+operName+"[");		
		// Operation arguments
        for (java.util.Iterator<String> iter = argumentsResult.iterator(); iter.hasNext();) 
        {
			String argument = iter.next();			
			if(operName.equals("intersection")) { return "("+sourceResult + " & " + argument+")"; }			
			if(operName.equals("union")) { return "("+sourceResult + " + " + argument+")"; }				
			if(operName.equals("including")) { return "("+sourceResult + " + " + argument+")"; }			
			if(operName.equals("excluding")) { return "("+sourceResult + " - " + argument+")"; }			
			if(operName.equals("includes")) { return "("+argument + " in " + sourceResult+")"; }			
			if(operName.equals("excludes")) { return "("+argument + " !in " + sourceResult+")"; }			
			if(operName.equals("includesAll")) { return "("+argument + " in " + sourceResult+")"; }			
			if(operName.equals("excludesAll")) { return "(no"+"("+argument + " & " + sourceResult+"))"; }			
			if(operName.equals("product")) { return "("+sourceResult + " -> " + argument+")"; }			
			if(operName.equals("=")) { return "("+sourceResult + " = " + argument+")"; }				
			if(operName.equals("<>")) { return "("+sourceResult + " != " + argument+")"; }				
			if(operName.equals("count")) { return "("+argument+ " in "+sourceResult+ " => 1 else 0)"; }			
			if(operName.equals("oclIsKindOf"))	{ return "("+sourceResult + " in " + argument+")"; }			
			if(operName.equals("oclAsType")) { return ""+sourceResult+""; }			
			if(operName.equals("oclIsTypeOf"))	{  return visitOclIsTypeOf(sourceResult,argument,"w"); }							
			if(operName.equals("<")) { return "("+sourceResult + " < " + argument + ")"; }
			if(operName.equals(">")) { return "("+sourceResult + " > " + argument + ")"; }				
			if(operName.equals("<=")) { return "("+sourceResult + " <= " + argument + ")"; }			
			if(operName.equals(">=")) { return "("+sourceResult + " >= " + argument + ")"; }			
			if(operName.equals("and")) { return "("+sourceResult + " && " + argument+")"; }			
			if(operName.equals("or")) { return "("+sourceResult + " || " + argument+")"; }				
			if(operName.equals("implies")) { return "("+sourceResult + " => " + argument+")"; }			
			if(operName.equals("xor")) { return "("+"("+sourceResult +" || "+argument+ ")"+" && !"+"("+sourceResult+" && "+ argument+")"+")"; }			
			if(operName.equals("max")) 
			{ 
				library += "fun max [self, i: Int] : Int"+"\n"+"{ let a = int[self], b = int[i] | a <= b implies b else a }"+"\n\n"; 
				return "max["+sourceResult +","+ argument + "]"; 
			}			
	        if(operName.equals("div")) { return "("+sourceResult +").div["+ argument + "]"; }	        
			if(operName.equals("min")) 
			{
				library += "fun min [self, i: Int] : Int"+"\n"+"{ let a = int[self], b = int[i] | a <= b implies a else b }"+"\n\n";
				return "min["+sourceResult +"," + argument + "]"; 
			}			
			if(operName.equals("+")) { return "(" + sourceResult +").plus["+argument+"]"; }			
			if(operName.equals("*")) { return "(" + sourceResult +").mul["+argument+"]"; }			
			if(operName.equals("symmetricDifference")) { return "("+"("+sourceResult + " - " + argument+") + ("+argument + " - " + sourceResult+")"+")"; }	
			
			//user defined operations...
			if(isUserDefined) result.append(argument+",");			
			
			if (iter.hasNext()) ; // no more arguments 
        }
        if(isUserDefined) result.append("w]"); 
        if(operName.equals("/")) throw new OperationException("/","We only support the integer operations: +, -, *, max(), min(), abs(), div().");        
        if(operName.equals("mod")) throw new OperationException("mod","We only support the integer operations: +, -, *, max(), min(), abs(), div().");        
        if(operName.equals("toString")) throw new OperationException("toString()","The type String is not supported.");        
        if(operName.equals("asOrderedSet")) throw new OperationException("asOrderedSet()","The collection type OrderedSet is not supported.");        
        if(operName.equals("asSequence")) throw new OperationException("asSequence()","The collection type Sequence is not supported.");        
        if(operName.equals("asBag")) throw new OperationException("asBag()","The collection type Bag is not supported.");        
        if(operName.equals("oclIsInState")) throw new OperationException("oclIsInState()","There is not a state machine.");        
        //if(operName.equals("oclIsNew")) throw new OperationException("oclIsNew()","Post conditions are not supported.");        
        if(operName.equals("oclIsInvalid"))	throw new OperationException("oclIsInvalid()","The OclInvalid is not supported.");        
		return result.toString();
	}
        
    /** Visits IteratorExp. */        
	@Override
	public String handleIteratorExp (IteratorExp<Classifier,Parameter> icallExp,String sourceResult,java.util.List<String> variableResults,String bodyResult) 
    {     
		StringBuffer result = new StringBuffer();
		result.append("(");		
		String var = new String();		
		String iterName = icallExp.getName();    	
        if(iterName.equals("forAll")) result.append("all ");        
        if(iterName.equals("exists")) result.append("some ");        
        if(iterName.equals("one")) result.append("#{ ");        
        if(iterName.equals("select")) result.append("{ ");        
        if(iterName.equals("reject")) result.append("{ ");        
        if(iterName.equals("isUnique")) result.append("all ");               
        if(iterName.equals("any")) result.append("{ ");        
        if(iterName.equals("collect")) result.append("univ.{ ");        
        if(iterName.equals("closure")) result.append(sourceResult+".^{ ");        
        // Iterator Arguments
        for( java.util.Iterator<String> iter = variableResults.iterator(); iter.hasNext();) 
        {        	     
        	String string = iter.next();           	        	        	
        	if(iterName.equals("forAll")) result.append(string);        	
        	if(iterName.equals("exists")) result.append(string);        	
        	if(iterName.equals("one")) result.append(string);        	
        	if(iterName.equals("select")) result.append(string);        	
            if(iterName.equals("reject")) result.append(string);            
            if(iterName.equals("collect")) var = string;            
            if(iterName.equals("closure")) var = string;            
            if(iterName.equals("isUnique")){ var = string; result.append(var).append(",").append(var).append("'"); }            
            if(iterName.equals("any")) result.append(string);                        
            if (iter.hasNext()) result.append(",");
        }        
        if(iterName.equals("forAll")) result.append(": ").append(sourceResult).append(" | ").append(bodyResult);        
        if(iterName.equals("exists")) result.append(": ").append(sourceResult).append(" | ").append(bodyResult);        
        if(iterName.equals("one")) result.append(": ").append(sourceResult).append(" | ").append(bodyResult).append(" } = 1");        
        if(iterName.equals("reject")) result.append(": ").append(sourceResult).append(" | ").append("! ").append(bodyResult).append(" }");        
        if(iterName.equals("select")) result.append(": ").append(sourceResult).append(" | ").append(bodyResult).append(" }");        
        if(iterName.equals("collect")) result.append(var+": "+sourceResult+", res: "+bodyResult+" | no none }");        
        if(iterName.equals("isUnique")) 
        {
        	result.append(": ").append(sourceResult).append(" | ").append(bodyResult).append(" <=> ");
        	String sb = substitute(var,bodyResult,var+"'");  //substitute variable "x" in expression "bodyResult" for "x'"        	
        	result.append(sb);
        	result.append(" => "+var+"="+var+"'");
        }
        if(iterName.equals("any")) result.append(": "+sourceResult+" | "+bodyResult+" }");        
        if(iterName.equals("closure")) result.append(var+": univ"+", res: "+bodyResult+" | no none }");
        if(iterName.equals("sortedBy")) throw new IteratorException("sortedBy()","The type OrderedSet is not supported.");        
        if(iterName.equals("collectNested")) throw new IteratorException("collectNested()","We do not support nested collections and the type Bag.");       
        result.append(")");
     	return result.toString();    	       
    }
          
    /** Visits IterateExp. */
	@Override
    public String handleIterateExp(IterateExp<Classifier,Parameter> callExp, String sourceResult,    
    List<String> variableResults,String resultResult, String bodyResult) 
    {
    	throw new OperationException("iterate()","Operation not supported. Use OCL predefined iterators.");		
	}
    
    /** Visits PropertyCallExp. */
	@Override
    public String handlePropertyCallExp (PropertyCallExp<Classifier,Property> propCallExp, String sourceResult, java.util.List<String> qualifierResults) 
    {    	
    	Property property = propCallExp.getReferredProperty();    	
		// if we are the qualifier of an association class call then we just return our name, because our source is null (implied)    	
		if (sourceResult == null) return property.getName();;
		if (!qualifierResults.isEmpty()) { } // no qualifier	
		
		StringBuffer result = new StringBuffer();
    	RefOntoUML.Property ontoProperty = (RefOntoUML.Property)oclparser.getOntoUMLElement(property);
    	if(ontoProperty==null) {
    		System.err.println("Null OntoUML element -->>> Property "+property.getName()+" : "+property.getType().getName()+"");
    		return "<null-element>";
    	}
    	String nameProperty = refparser.getAlias(ontoProperty);    	
    	if (property.getAssociation()!=null) result.append(sourceResult + "." + nameProperty+ "[w]");    	
    	else if (property.getType().getName().compareToIgnoreCase("Boolean")==0) { result.append(sourceResult + " in w." + nameProperty+ "");}    	
    	else { result.append(sourceResult + ".(w." + nameProperty+ ")"); }
    	
		return result.toString();
	}        			
    
    /** Visits TypeExp. */ 
	@Override 
	public String visitTypeExp (TypeExp<Classifier> t) 
	{		
		Classifier classifier = t.getReferredType();		
		return visitType(classifier,"w");
	}
	
	private String visitType(Classifier classifier, String worldVar)
	{
		RefOntoUML.PackageableElement ontoClassifier = (RefOntoUML.PackageableElement)oclparser.getOntoUMLElement(classifier);
    	String nameClassifier = refparser.getAlias(ontoClassifier);
		if (classifier instanceof org.eclipse.ocl.uml.AnyType) return "univ";		
		if (classifier instanceof org.eclipse.ocl.uml.VoidType) return "none";		
		if (classifier instanceof org.eclipse.ocl.uml.PrimitiveType) 
		{ 
			if (classifier.getName().compareToIgnoreCase("Integer")==0) return "Int";			
		}    		
    	if (ontoClassifier instanceof RefOntoUML.DataType) return "" + nameClassifier;    	
    	else return worldVar+"." + nameClassifier;
	}
	
    /** Visits VariableExp. */	
	@Override
	public String visitVariableExp (VariableExp<Classifier, Parameter> v) 
	{
		Variable<Classifier, Parameter> vd = v.getReferredVariable();
		String name = vd.getName();		
		String result = (vd == null) ? null : name;		
		return result;
	}
   	
    /** Visits Variable. */
	@Override
	public String handleVariable (Variable<Classifier,Parameter> variable, String initResult) 
	{		
		String name = variable.getName();
		Classifier type = variable.getType();	
		String result = name;		
		if (type != null && initResult == null) {}
	    if (initResult != null) { result += " = " + initResult; }	    	    	    
		return result;
	}
	
	/** Visits IfExp. */	
    @Override
    public String handleIfExp (IfExp<Classifier> ifExp, String conditionResult, String thenResult, String elseResult) 
    {
    	return conditionResult + " => " + thenResult + " else " + elseResult;
	}
	
    /** Visits LetExp. */    
    @Override
    public String handleLetExp(LetExp<Classifier,Parameter> letExp, String variableResult, String inResult) 
    {        
    	return "let " + variableResult + " | " + inResult;
	}
             
    /** Visits CollectionItem. */        
    @Override
    public String handleCollectionItem(CollectionItem<Classifier> item, String itemResult) 
    {
        return itemResult;
    }
    
    /** Visits CollectionLiteralExp.  */    
    @Override
    public String handleCollectionLiteralExp(CollectionLiteralExp<Classifier> cl, java.util.List<String> partResults) 
    {        
        StringBuffer result = new StringBuffer();		
		CollectionKind kind = cl.getKind();		
		switch (kind) {
			case SET_LITERAL: {
				String str = new String();
				for (Iterator<String> iter = partResults.iterator(); iter.hasNext();) 
				{
					str = iter.next();
					if (!iter.hasNext())  result.append(str);
					else throw new LiteralException("Set{}");
				}
				break;
			}			
			case ORDERED_SET_LITERAL: throw new LiteralException("OrderedSet{}");				  
			case BAG_LITERAL: throw new LiteralException("Bag{}");				
			case SEQUENCE_LITERAL: throw new LiteralException("Sequence{}");				
			case COLLECTION_LITERAL: throw new LiteralException("OrderedSet{}");				
		}		       
        return result.toString();
	}       

    /** Visits TupleLiteralExp. */
    @Override
    public String handleTupleLiteralExp(TupleLiteralExp<Classifier,Property> literalExp, List<String> partResults) 
    {		        
		throw new LiteralException("Tuple{}");        
	}

    /** Visits EnumLiteralExp. */
	@Override
    public String visitEnumLiteralExp(EnumLiteralExp<Classifier,EnumerationLiteral> el) 
	{		
		return el.getReferredEnumLiteral().toString();		
	}
	
	/** Visits ItegerLiteralExp. */	
	@Override
	public String visitIntegerLiteralExp (IntegerLiteralExp<Classifier> intliteral) 
	{		
		return (intliteral.getIntegerSymbol() == null)? null : intliteral.getIntegerSymbol().toString();		 
	}
    
	/** Visits UnlimitedNaturalLiteralExp. */
	@Override
    public String visitUnlimitedNaturalLiteralExp(UnlimitedNaturalLiteralExp<Classifier> unl) 
	{
        if (unl.isUnlimited()) throw new LiteralException("UnlimitedNatural");
        return (unl.getIntegerSymbol() == null)? null : unl.getIntegerSymbol().toString();
    }

	/** Visits RealLiteralExp. */
	@Override
    public String visitRealLiteralExp(RealLiteralExp<Classifier> rl) 
	{
		throw new LiteralException("Real");
	}

	/** Visits StringLiteralExp. */
	@Override
    public String visitStringLiteralExp(StringLiteralExp<Classifier> sl) 
	{
		throw new LiteralException("String");
	}

	/** Visits BooleanLiteralExp. */
	@Override
    public String visitBooleanLiteralExp(BooleanLiteralExp<Classifier> bl) 
	{
		if (bl.getBooleanSymbol() == null) return null;		
		if (bl.getBooleanSymbol().toString().equals("true")) return "no none";		
		if (bl.getBooleanSymbol().toString().equals("false")) return "some none";		
		return null;
	}
	
	/** Visits NullLiteralExp (Since OCL 3.1). */	
	@Override
    public String visitNullLiteralExp(NullLiteralExp<Classifier> il) 
	{
		return "none";
	}

	/** Visits InvalidLiteralExp (Since OCL 3.1). */
	@Override
    public String visitInvalidLiteralExp(InvalidLiteralExp<Classifier> il) 
	{
		throw new LiteralException("Invalid");
	}		

	/** Visits ExpressionInOCL. */	
    @Override
    public String visitExpressionInOCL (ExpressionInOCL<Classifier,Parameter> expression) 
    {
		return expression.getBodyExpression().accept(this);
	}
	    
	public String visit(org.eclipse.ocl.utilities.Visitable visitable)
   	{
   		return (visitable == null)? null : (String) visitable.accept(this);
   	}	
	
    /** Visits Constraint. */	
	@SuppressWarnings("unchecked")
	@Override
    public String visitConstraint(Constraint constraint) 
    {
        StringBuffer result = new StringBuffer();
//        this.currentConstraint=constraint;
        java.util.List<? extends EObject> constrained = oclparser.getUMLReflection().getConstrainedElements(constraint);
        String stereo = oclparser.getUMLReflection().getStereotype(constraint);       
                
        if(org.eclipse.ocl.utilities.UMLReflection.DEFINITION.equals(stereo)) 
        {
            if (!constrained.isEmpty()) {
            	EObject elem = constrained.get(0);
                if (oclparser.getUMLReflection().isClassifier(elem)){
                	Classifier classifier = (Classifier)elem;
                	result.append(visitOperationDefinition(classifier, constraint));
                }
            }       	       
        }
        else if (org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(stereo)) 
        {        	
            if (!constrained.isEmpty()) {
    			EObject elem = constrained.get(0);
                if (oclparser.getUMLReflection().isClassifier(elem)) {
                	Classifier classifier = (Classifier)elem;                	
                	result.append(visitInvariant(classifier,constraint));                	
                } 
                else if (oclparser.getUMLReflection().isOperation(elem));
                else if (oclparser.getUMLReflection().isProperty(elem));
            }
        }
        else if (org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(stereo))
        {
        	if (!constrained.isEmpty()) {
                EObject elem = constrained.get(0);
                if (oclparser.getUMLReflection().isProperty(elem)) {                	
                	Property property = (Property)elem;                	
                	result.append(visitDerive(property, constraint));                	
                }                
                else if (oclparser.getUMLReflection().isClassifier(elem));
                else if (oclparser.getUMLReflection().isOperation(elem));
            }
        }       
        else if(org.eclipse.ocl.utilities.UMLReflection.INITIAL.equals(stereo)) throw new StereotypeException("init");        
        else if(org.eclipse.ocl.utilities.UMLReflection.POSTCONDITION.equals(stereo)) throw new StereotypeException("post");
        else if(org.eclipse.ocl.utilities.UMLReflection.PRECONDITION.equals(stereo)) throw new StereotypeException("pre");
        else if(org.eclipse.ocl.utilities.UMLReflection.BODY.equals(stereo)) throw new StereotypeException("body");
        return result.toString();
    }
   	
	/** Visits oclIsTypeOf */
	protected String visitOclIsTypeOf(String sourceResult, String argument, String worldVar)
	{
		String firstPart = "("+sourceResult + " in " + worldVar+"."+argument+")";
		String secondPart = new String();
		if (argument.contains(worldVar+".")) argument = argument.replace(worldVar+".", "");				
		ArrayList<String> subtypes = getSubTypes(argument);
		if (subtypes.size()>0) {
			secondPart = " and (# "+ sourceResult + " & (";				
			int i = 1;
			for(String subtype: subtypes) { if (i<subtypes.size()) secondPart += worldVar+"."+subtype+" + "; else if (i==subtypes.size()) secondPart += worldVar+"."+subtype; i++;}
			secondPart += ") = 0)";
		}
		String code = "("+firstPart + secondPart+")";
		return code;
	}
	
	/** 
     *  Substitute "var" for "sourceResult" in expression "bodyResult" 
     *  This method is used for Iterators: isUnique() and collect()
     */    
	protected String substitute (String var, String bodyResult, String sourceResult)
    {
    	Pattern p = Pattern.compile(var);
    	Matcher m = p.matcher(bodyResult);
    	StringBuffer sb = new StringBuffer();
    	while (m.find()) 
    	{ 
    		int indexBegin = m.start()-1;
    		int indexEnd = m.end()+1;
    		if(indexBegin < 0) indexBegin = 0;
    		if(indexEnd > bodyResult.length()) indexEnd = bodyResult.length();
    		String regex1 = "[.\t-()=<>:+]"+var+"[.\t-()=<>:+]";
    		String regex2 = var+"[.\t-()=<>:+]";
    		String regex3 = "[.\t-()=<>:+]"+var;
    		if(
    		   Pattern.matches(regex1,bodyResult.subSequence(indexBegin, indexEnd)) || 
    		   Pattern.matches(regex2,bodyResult.subSequence(indexBegin, indexEnd))	||
    		   Pattern.matches(regex3,bodyResult.subSequence(indexBegin, indexEnd))  
    		){
    		   m.appendReplacement(sb, sourceResult);    		   
    		}
    	}    	
    	m.appendTail(sb);
    	String result = sb.toString();
    	m.reset();
    	return result;
    }
    
    /** Get all the sub types of the type Classifier. */    
    protected ArrayList<String> getSubTypes(String alias)
    {    	
    	EObject type = refparser.getElement(alias);    	
    	Set<RefOntoUML.Classifier> setChildren = refparser.getChildren((RefOntoUML.Classifier)type);    	
    	ArrayList<RefOntoUML.Classifier> listChildren = new ArrayList<RefOntoUML.Classifier>();
    	listChildren.addAll(setChildren);    	
    	ArrayList<String> subtypes = new ArrayList<String>();    	
    	for(RefOntoUML.Classifier child: listChildren)
    	{
    		subtypes.add(refparser.getAlias(child));
    	}   	    	
    	return subtypes;
    }    
    
 	/** Visits Definition. */
    protected String visitOperationDefinition(Classifier classifier, Constraint constraint)
    {
    	StringBuffer result = new StringBuffer();
    	org.eclipse.ocl.uml.ExpressionInOCL expr = (org.eclipse.ocl.uml.ExpressionInOCL) constraint.getSpecification();
    	List<Operation> opList = oclparser.myOCL.getEnvironment().getAdditionalOperations(classifier);
    	for(Operation op: opList){
    		definedOperationsList.add(op);
    		if(!op.getReturnResult().getType().getName().equals("Boolean")) result.append("func ").append(op.getName());
    		else result.append("pred ").append(op.getName());
    		result.append(" [");
    		result.append("self: ");    			
			result.append(visitType(classifier,"World"));
    		for(Variable<Classifier,Parameter> v: expr.getParameterVariable())
    		{
    			result.append(", "+v.getName()+": ");    			
    			result.append(visitType(v.getType(),"World"));
    		}
    		result.append(", w: World] ");
    		if(!op.getReturnResult().getType().getName().equals("Boolean")){
    			result.append(": ");
    			if(op.getLower()==1 && op.getUpper()==1) result.append("one");
        		if(op.getLower()==0 && op.getUpper()==1) result.append("lone");
        		if(op.getUpper()==-1) result.append("set");
        		result.append(" "+visitType((Classifier)op.getReturnResult().getType(),"World"));
    		}
    		result.append(" {\n\t");
    	}
    	result.append(visit(expr));
    	result.append("\n}\n\n");
    	return result.toString();
    }
    
	/** Visits Invariant. */	
   	protected String visitInvariant(Classifier classifier, Constraint constraint)
	{
		StringBuffer result = new StringBuffer();
        this.currentConstraint=constraint;
		inv_counter++;
		String stereotype = opt.getTransformationType(constraint);
		
		if(stereotype.equals("CHECK")) result.append("assert ");		
		else if(stereotype.equals("SIMULATE")) result.append("pred ");		
		else result.append("fact ");
		
		if (constraint.getName()==null || constraint.getName().isEmpty()){
			result.append("invariant"+inv_counter).append(" {\n");
			constraint.setName("invariant"+inv_counter);
		}else
			result.append(""+constraint.getName()).append(" {\n");            
		
	    org.eclipse.ocl.uml.ExpressionInOCL expr = (org.eclipse.ocl.uml.ExpressionInOCL) constraint.getSpecification();
	    String exprResult = visit(expr);
	    
		if(stereotype.equals("SIMULATE")) result.append("\tall w: World | ");
	   	else if(stereotype.equals("CHECK")) result.append("\tall w: World | ");
	   	else result.append("\tall w: World | ");
			
		RefOntoUML.PackageableElement ontoClassifier = (RefOntoUML.PackageableElement)oclparser.getOntoUMLElement(classifier);
		
		String nameClassifier = refparser.getAlias(ontoClassifier);
		 	
		if (expr.getBodyExpression().toString().contains("self")){
			if (ontoClassifier instanceof RefOntoUML.DataType) result.append("all self: "+nameClassifier+" | "); 
			else result.append("all self: w."+nameClassifier+" | ");
		}else {
			//...
		}
		
	    result.append(exprResult); 
	    
	    result.append("\n}\n\n");                 
	    
	    int scope = opt.getCommandScope(constraint);
	    int bitwidth = opt.getCommandBitwidth(constraint);
	    int world = opt.getWorldScope(constraint);
	    
	    if(stereotype.equals("SIMULATE")) result.append("run "+""+constraint.getName()+" for "+scope+" but "+world+" World, "+bitwidth+" Int\n\n");
	    else if(stereotype.equals("CHECK")) result.append("check "+""+constraint.getName()+" for "+scope+" but "+world+" World, "+bitwidth+" Int\n\n");
	    
	    return result.toString();
	}
		
   	/** Visit Derive */
   	protected String visitDerive(Property property, Constraint constraint)
	{
   		StringBuffer result = new StringBuffer();
   		derive_counter++;
    	String stereotype = opt.getTransformationType(constraint);
    	
    	// get Property Name
    	RefOntoUML.Property ontoProperty = (RefOntoUML.Property)oclparser.getOntoUMLElement(property);
    	String name_property = refparser.getAlias(ontoProperty);
    	
    	// get Type Name
    	RefOntoUML.PackageableElement ontoClassifier = (RefOntoUML.PackageableElement)oclparser.getOntoUMLElement(property.getType());
    	String type_property = refparser.getAlias(ontoClassifier);
    	                	
    	if(stereotype.equals("CHECK")) result.append("assert ");
    	else if(stereotype.equals("SIMULATE")) result.append("pred ");
    	else result.append("fact ");
    	
    	result.append("derive"+derive_counter).append(" {\n");                	
    	result.append("\tall w: World | all self: ");                	         	
    	
    	// The type_property is equal to : "Set(typename)"
    	// We just need of the "typename"
    	String typename = new String();
    	if(type_property.contains("Set")){
    		typename = type_property.substring(4,type_property.length()-1);
    	}else{
    		typename = type_property;
    	}
    	
    	org.eclipse.uml2.uml.Type src_type;
    	org.eclipse.uml2.uml.Type tgt_type;
    	
    	if (property.getAssociation()!=null){ // derive an assoc end...
    		src_type = property.getAssociation().getMemberEnds().get(0).getType();
    		tgt_type = property.getAssociation().getMemberEnds().get(1).getType();
    	}else{ // derive an attribute...
    		src_type = (org.eclipse.uml2.uml.Type)property.getOwner();
    		tgt_type = property.getType();
    	}
    	
    	// get Source Type Name
    	RefOntoUML.PackageableElement sourceType = (RefOntoUML.PackageableElement)oclparser.getOntoUMLElement(src_type);
    	String src_name = refparser.getAlias(sourceType);                	
    	
    	// get Target Type Name
    	RefOntoUML.PackageableElement targetType = (RefOntoUML.PackageableElement)oclparser.getOntoUMLElement(tgt_type);
    	String tgt_name = refparser.getAlias(targetType);
    	                	                	                	
   	    // Considering the direction
    	if(typename.equals(src_name)) result.append("w."+tgt_name+" | ");                	
    	if(typename.equals(tgt_name)) result.append("w."+src_name+" | ");
    	                	
    	if(property.getAssociation()!=null){
    		result.append("self."+name_property+"[w] = ");
    	}else{
    		result.append("self.(w."+name_property+") = ");
    	}
    	
    	org.eclipse.ocl.uml.ExpressionInOCL expr = (org.eclipse.ocl.uml.ExpressionInOCL) constraint.getSpecification();
        result.append(visit(expr));
    	
    	result.append("\n}\n\n");
    	
    	int scope = opt.getCommandScope(constraint);
 	    int bitwidth = opt.getCommandBitwidth(constraint);
 	    int world = opt.getWorldScope(constraint);
 	    
    	if(stereotype.equals("SIMULATE")) result.append("run "+"derive"+""+derive_counter+" for "+scope+" but "+world+" World, "+bitwidth+" Int\n\n");
	    else if(stereotype.equals("CHECK")) result.append("check "+"derive"+""+derive_counter+" for "+scope+" but "+world+" World, "+bitwidth+" Int\n\n");
    	    	
    	return result.toString(); 
	}
}

