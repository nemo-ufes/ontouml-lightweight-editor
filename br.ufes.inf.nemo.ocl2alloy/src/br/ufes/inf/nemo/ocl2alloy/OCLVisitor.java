package br.ufes.inf.nemo.ocl2alloy;

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

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2alloy.exception.IteratorException;
import br.ufes.inf.nemo.ocl2alloy.exception.LiteralException;
import br.ufes.inf.nemo.ocl2alloy.exception.OperationException;
import br.ufes.inf.nemo.ocl2alloy.exception.StereotypeException;

/**
 * This class is used for visit the OCL AST and transform an OCL Constraint into Alloy. 
 *
 * @author John Guerson
 */

public class OCLVisitor extends org.eclipse.ocl.utilities.AbstractVisitor <String,Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint> 
{	
	public OCLParser oclparser;
	public OntoUMLParser refparser;
	
    public int derive_counter = 0;
    public int inv_counter = 0;
    
    public String stereotype = "FACT";  
	
    public OCLVisitor (OCLParser oclparser,OntoUMLParser refparser) 
    {	
		this.oclparser = oclparser;
		this.refparser = refparser;
	}    
            
    /** Visits the OperationCallExp.  */
	@Override
    public String handleOperationCallExp (OperationCallExp<Classifier,Operation> operCallExp, String sourceResult, java.util.List<String> argumentsResult) 
    {    	
    	Operation oper = operCallExp.getReferredOperation();    	
    	return generateOperationMapping(oper, sourceResult, argumentsResult);
	}
        
    /** Visits IteratorExp. */        
	@Override
	public String handleIteratorExp (IteratorExp<Classifier,Parameter> icallExp,String sourceResult,java.util.List<String> variableResults,String bodyResult) 
    {     
    	return generateIteratorMapping(icallExp, sourceResult, variableResults, bodyResult);   	       
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
		
		return generatePropertyMapping(property, sourceResult);
	}        			
    
    /** Visits TypeExp. */ 
	@Override 
	public String visitTypeExp (TypeExp<Classifier> t) 
	{		
		Classifier classifier = t.getReferredType();		
		return generateTypeMapping(classifier);
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
        return generateIfThenElseMapping(conditionResult, thenResult, elseResult);
	}
	
    /** Visits LetExp. */    
    @Override
    public String handleLetExp(LetExp<Classifier,Parameter> letExp, String variableResult, String inResult) 
    {        
        return generateLetMapping(variableResult, inResult);
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
		switch (kind) 
		{
			case SET_LITERAL: 
			{
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
	    
    /** Visits Constraint. */	
	@SuppressWarnings("unchecked")
	@Override
    public String visitConstraint(Constraint constraint) 
    {
        StringBuffer result = new StringBuffer();
        java.util.List<? extends EObject> constrained = oclparser.getUMLReflection().getConstrainedElements(constraint);
        String stereo = oclparser.getUMLReflection().getStereotype(constraint);       

        if (org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(stereo)) 
        {			
            if (!constrained.isEmpty()) 
            {
    			EObject elem = constrained.get(0);
                if (oclparser.getUMLReflection().isClassifier(elem)) 
                {
                	Classifier classifier = (Classifier)elem;
                	
                	result.append(generateInvariantMapping(classifier,constraint));                	
                } 
                else if (oclparser.getUMLReflection().isOperation(elem)); 
                else if (oclparser.getUMLReflection().isProperty(elem));                          
            }
        }

        else if (org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(stereo))
        {
        	if (!constrained.isEmpty()) 
            {
                EObject elem = constrained.get(0);
                if (oclparser.getUMLReflection().isProperty(elem)) 
                {                	
                	Property property = (Property)elem;
                	
                	result.append(generateDeriveMapping(property, constraint));                	
                }                
                else if (oclparser.getUMLReflection().isClassifier(elem));
                else if (oclparser.getUMLReflection().isOperation(elem));
            }
        }
        
        else if(org.eclipse.ocl.utilities.UMLReflection.DEFINITION.equals(stereo)) {        	
        	throw new StereotypeException("def");
        }
        
        else if(org.eclipse.ocl.utilities.UMLReflection.INITIAL.equals(stereo)) {
        	throw new StereotypeException("init");        	
        }
        
        else if(org.eclipse.ocl.utilities.UMLReflection.POSTCONDITION.equals(stereo)) {        			    
        	throw new StereotypeException("post");        	
        }
        
        else if(org.eclipse.ocl.utilities.UMLReflection.PRECONDITION.equals(stereo)) {
        	throw new StereotypeException("pre");        	
        }
        
        else if(org.eclipse.ocl.utilities.UMLReflection.BODY.equals(stereo)) {
        	throw new StereotypeException("body");
        }
        
        return result.toString();
    }
    
   	public String visit(org.eclipse.ocl.utilities.Visitable visitable)
   	{
   		return (visitable == null)? null : (String) visitable.accept(this);
   	}	
   	
   	private String generateOperationMapping (Operation oper, String sourceResult, java.util.List<String> argumentsResult)
	{			
		String operTypeResult = oper.getType().getName();
		String operName = oper.getName();
		
		if(operName.equals("allInstances")) { return sourceResult; }
		
		if(operName.equals("size")) { return "("+"#" + sourceResult+ ")"; }
		
		if(operName.equals("isEmpty")) { return "("+"no " + sourceResult + ")"; }
		
		if(operName.equals("notEmpty")) { return "("+"some " + sourceResult+ ")"; }	
		
		if(operName.equals("not")) { return "("+"not " + sourceResult+ ")"; }	
		
		if(operName.equals("oclIsUndefined")) { return "("+"#" + sourceResult + " = 0"+ ")"; }	
		
		if(operName.equals("abs")) { return "abs[" + sourceResult + "]"; }
		
        if(operName.equals("sum")) { return "(sum " + sourceResult + ")"; }
        
        if(operName.equals("asSet")) { return sourceResult; }
        
		if(operName.equals("oclAsType")) { return sourceResult; }
		
		if(operName.equals("-")) 
		{
			java.util.Iterator<String> iter = argumentsResult.iterator();	
			
			if(!iter.hasNext()) { return "negate[" + sourceResult +"]"; }
			
			else if( iter.hasNext() && operTypeResult.equals("Set(T)")) { return "("+sourceResult + " - " + iter.next()+ ")"; }
			
			else if( iter.hasNext() && operTypeResult.equals("Real")){ return "("+sourceResult+").minus["+iter.next()+"]"; }
		}
				
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
			
			if(operName.equals("excludesAll")) { return "("+"#"+argument + " & " + sourceResult+" = 0)"; }
			
			if(operName.equals("product")) { return "("+sourceResult + " -> " + argument+")"; }
			
			if(operName.equals("=")) { return "("+sourceResult + " = " + argument+")"; }	
			
			if(operName.equals("<>")) { return "("+sourceResult + " != " + argument+")"; }	
			
			if(operName.equals("oclIsKindOf"))	{ return "("+sourceResult + " in " + argument+")"; }
			
			if(operName.equals("oclAsType")) { return ""+sourceResult+""; }
			
			if(operName.equals("oclIsTypeOf"))	{  return generateOclIsTypeOfMapping(sourceResult,argument); }
							
			if(operName.equals("<")) { return "("+sourceResult + " < " + argument + ")"; }	
			
			if(operName.equals(">")) { return "("+sourceResult + " > " + argument + ")"; }	
			
			if(operName.equals("<=")) { return "("+sourceResult + " <= " + argument + ")"; }
			
			if(operName.equals(">=")) { return "("+sourceResult + " >= " + argument + ")"; }
			
			if(operName.equals("and")) { return "("+sourceResult + " and " + argument+")"; }
			
			if(operName.equals("or")) { return "("+sourceResult + " or " + argument+")"; }	
			
			if(operName.equals("implies")) { return "("+sourceResult + " implies " + argument+")"; }
			
			if(operName.equals("xor")) { return "("+"("+sourceResult +" or "+argument+ ")"+" and not "+"("+sourceResult+" and "+ argument+")"+")"; }
			
			if(operName.equals("max")) { return "max["+sourceResult +","+ argument + "]"; }
			
			if(operName.equals("min")) { return "min["+sourceResult +"," + argument + "]"; }
			
			if(operName.equals("+")) { return "(" + sourceResult +").plus["+argument+"]"; }
			
			if(operName.equals("*")) { return "(" + sourceResult +").mul["+argument+"]"; }
			
			if(operName.equals("symmetricDifference")) { return "("+"("+sourceResult + " + " + argument+") - ("+sourceResult + " & " + argument+")"+")"; }	        	
						
			if (iter.hasNext()) ; // no more arguments 
        }
       			
        if(operName.equals("/")) throw new OperationException("/","We only support the integer operations: +, -, *, max(), min(), abs().");
        
        if(operName.equals("div")) throw new OperationException("div","We only support the integer operations: +, -, *, max(), min(), abs().");
        
        if(operName.equals("mod")) throw new OperationException("mod","We only support the integer operations: +, -, *, max(), min(), abs().");
        
        if(operName.equals("toString")) throw new OperationException("toString()","The type String is not supported.");
        
        if(operName.equals("asOrderedSet")) throw new OperationException("asOrderedSet()","The collection type OrderedSet is not supported.");
        
        if(operName.equals("asSequence")) throw new OperationException("asSequence()","The collection type Sequence is not supported.");
        
        if(operName.equals("asBag")) throw new OperationException("asBag()","The collection type Bag is not supported.");
        
        if(operName.equals("oclIsInState")) throw new OperationException("oclIsInState()","There is not a state machine.");
        
        if(operName.equals("oclIsNew")) throw new OperationException("oclIsNew()","Post conditions are not supported.");
        
        if(operName.equals("flatten")) throw new OperationException("flatten()","We do not support nested collections.");
        
        if(operName.equals("oclIsInvalid"))	throw new OperationException("oclIsInvalid()","The OclInvalid is not supported.");
        
        if(operName.equals("count")) throw new OperationException("count()","We do not support this operation. It is not possible to iterate over collections.");    
        
		return "";
	}
   	
   	private String generateIteratorMapping(IteratorExp<Classifier,Parameter> icallExp, String sourceResult,java.util.List<String> variableResults,String bodyResult)
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
        
        if(iterName.equals("isUnique")) result.append("all disj ");  
               
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
            
            if (iter.hasNext()) result.append(",");
        }
        
        if(iterName.equals("forAll")) result.append(": ").append(sourceResult).append(" | ").append(bodyResult);  
        
        if(iterName.equals("exists")) result.append(": ").append(sourceResult).append(" | ").append(bodyResult); 
        
        if(iterName.equals("one")) result.append(": ").append(sourceResult).append(" | ").append(bodyResult).append(" } = 1");
        
        if(iterName.equals("reject")) result.append(": ").append(sourceResult).append(" | ").append("not ").append(bodyResult).append(" }"); 
        
        if(iterName.equals("select")) result.append(": ").append(sourceResult).append(" | ").append(bodyResult).append(" }");
        
        if(iterName.equals("collect")) 
        {        	
        	String sb = substitute(var,bodyResult,sourceResult); //substitute variable "x" in expression "bodyResult" for "sourceResult"         	
        	result.append(sb);        	
        }
        
        if(iterName.equals("isUnique")) 
        {
        	result.append(": ").append(sourceResult).append(" | ").append(bodyResult).append(" != ");
        	String sb = substitute(var,bodyResult,var+"'");  //substitute variable "x" in expression "bodyResult" for "x'"        	
        	result.append(sb);        	
        }
        
        if(iterName.equals("closure")) 
        {        	
        	String assocAlias = new String();
        	String propertyAlias = new String();
        	
        	String aux1 = new String(bodyResult);
        	propertyAlias = aux1.replace(var+".", "");
        	propertyAlias = propertyAlias.replace("[w]", "");
        	        	
        	EObject obj = refparser.getElement(propertyAlias);
        	
        	if (obj instanceof RefOntoUML.Property) 
        	{
        		EObject container = obj.eContainer();
            	assocAlias = refparser.getAlias(container);	
        	}        	
        	
        	String strFinal = sourceResult+".^(w."+assocAlias+")";        	
        	result.append(strFinal);
        }
                
        if(iterName.equals("any")) throw new IteratorException("any()","We do not support this iterator. Problems arise when trying to return the type of an element of the collection");
        
        if(iterName.equals("sortedBy")) throw new IteratorException("sortedBy()","The type OrderedSet is not supported.");
        
        if(iterName.equals("collectNested")) throw new IteratorException("collectNested()","We do not support nested collections and the type Bag.");
       
        result.append(")");
     	return result.toString(); 
	}
   	
   	private String generateIfThenElseMapping(String conditionResult, String thenResult, String elseResult)
	{
		return conditionResult + " implies " + thenResult + " else " + elseResult;
	}
	
	private String generateLetMapping(String variableResult, String inResult)
	{
		return "let " + variableResult + " | " + inResult;
	}
	
	private String generatePropertyMapping(Property property,  String sourceResult)
	{
		StringBuffer result = new StringBuffer();
    	RefOntoUML.Property ontoProperty = (RefOntoUML.Property)oclparser.getOntoUMLElement(property);    	
    	String nameProperty = refparser.getAlias(ontoProperty);
    	
    	if (ontoProperty.getAssociation()!=null) result.append(sourceResult + "." + nameProperty+ "[w]");
    	
    	else if (ontoProperty.getType().getName().compareToIgnoreCase("Boolean")==0) { result.append(sourceResult + " in w." + nameProperty+ "");}
    	
    	else result.append(sourceResult + ".(w." + nameProperty+ ")");
    	
		return result.toString();
	}
	
	private String generateTypeMapping(Classifier classifier)
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
    	
    	else return "w." + nameClassifier;
	}
	
   	private String generateInvariantMapping(Classifier classifier, Constraint constraint)
	{
		StringBuffer result = new StringBuffer();
		inv_counter++;
		
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
	    
	    if(stereotype.equals("SIMULATE")) result.append("run "+""+constraint.getName()+" for 10 but 3 World, 7 Int\n\n");
	    else if(stereotype.equals("CHECK")) result.append("check "+""+constraint.getName()+" for 10 but 3 World, 7 Int\n\n");
	    
	    return result.toString();
	}
		
   	private String generateDeriveMapping(Property property, Constraint constraint)
	{
   		StringBuffer result = new StringBuffer();
   		derive_counter++;
    	
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
    	
    	if(stereotype.equals("SIMULATE")) result.append("run "+"derive"+""+derive_counter+" for 10 but 3 World, 7 Int\n\n");
        else if(stereotype.equals("CHECK")) result.append("check "+"derive"+""+derive_counter+" for 10 but 3 World, 7 Int\n\n");
    	
    	return result.toString(); 
	}
   	
	/**
	 * Generate the mapping regarding the OCL oclIsTypeOf Operation.
	 */
	private String generateOclIsTypeOfMapping(String sourceResult, String argument)
	{
		String firstPart = "("+sourceResult + " in " + argument+")";
		String secondPart = new String();
		if (argument.contains("w.")) argument = argument.replace("w.", "");				
		ArrayList<String> subtypes = getSubTypes(argument);
		if (subtypes.size()>0) 
		{
			secondPart = " and # "+ sourceResult + " & (";				
			int i = 1;
			for(String subtype: subtypes) { if (i<subtypes.size()) secondPart += "w."+subtype+" + "; else if (i==subtypes.size()) secondPart += "w."+subtype; i++;}
			secondPart += ") = 0";
		}
		String code = "("+firstPart + secondPart+")";
		return code;
	}
	
	/** 
     *  Substitute "var" for "sourceResult" in expression "bodyResult" 
     *  This method is used for Iterators: isUnique() and collect()
     */    
    public String substitute (String var, String bodyResult, String sourceResult)
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
    
    /** 
     *  Specifically for the operation oclIsTypeOf(type: Classifier).
     *  Get all the sub types of the type Classifier. 
     */    
    private ArrayList<String> getSubTypes(String alias)
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
}

