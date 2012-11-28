package br.ufes.inf.nemo.ocl2alloy.visitor;

import java.util.Iterator;
import java.util.List;
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
import br.ufes.inf.nemo.ocl2alloy.exception.TypeException;

import br.ufes.inf.nemo.ocl2alloy.parser.OCLParser;

/**
 * This class is used for visit the OCL AST and transform an OCL Constraint into Alloy. 
 *
 * @author John Guerson
 */

public class ToAlloyVisitor extends org.eclipse.ocl.utilities.AbstractVisitor <String,Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint> 
{	  

	/**
	 * OCL Parser with constraints, UML environments, resources and etc...
	 */
	public OCLParser oclparser;
	
	/**
	 * OntoUML Parser with model elements, names mapping, resources and etc...
	 */
	public OntoUMLParser refparser;
	
    /** 
     * Counter to the labels of the derivation rules.  
     */
    public int count_derivation = 0;
    
    /**
     * Invariants can be transformed into facts, predicates(simulation) and assertions.
     * {"FACT","SIMULATION","ASSERTION"}.
     */
    public String stereo_invariant = "FACT";  

    /**
     * Used to verify if asSet() is being used after the collect() iterator.
     */
    public boolean collect_used = false;
    public boolean is_next_oper = false;
    
	
    /** 
     * Constructor.
     *      
     * @param oclparser
     * @param refparser
     */
    public ToAlloyVisitor (OCLParser oclparser,OntoUMLParser refparser) 
    {	
		this.oclparser = oclparser;
		this.refparser = refparser;
	}    
    
    
    /**
     * Visits the OperationCallExp. 
     * 
     */
	@Override
    public String handleOperationCallExp (OperationCallExp<Classifier,Operation> operCallExp, String sourceResult, java.util.List<String> argumentResults) 
    {    	
    	String result = new String();
    	Operation oper = operCallExp.getReferredOperation();		
		String typename = oper.getType().getName();
		String name = oper.getName();
		
		// correct use of collect. 
		if(name.equals("asSet")) { 
			if (collect_used==true && is_next_oper==true) { 
				collect_used=false; 
				is_next_oper=false; 
			} else if(collect_used==true && is_next_oper==false) {				 
				throw new TypeException("Bag","The \"asSet()\" operation should be used after collect iterator.");
			}
		} else { 
			if(is_next_oper==true)
				throw new TypeException("Bag","The \"asSet()\" operation should be used after collect iterator."); 
		}
		
		if(name.equals("allInstances")) { return sourceResult; }
		
		if(name.equals("size")) { return "("+"#" + sourceResult+ ")"; }		
		if(name.equals("isEmpty")) { return "("+"no " + sourceResult + ")"; }		
		if(name.equals("notEmpty")) { return "("+"some " + sourceResult+ ")"; }		
		if(name.equals("not")) { return "("+"not " + sourceResult+ ")"; }		
		if(name.equals("oclIsUndefined")) { return "("+"#" + sourceResult + " = 0"+ ")"; }				
		if(name.equals("abs")) { return "("+"abs[" + sourceResult + "]"; }
		
		if(name.equals("asSet")) { return sourceResult; }
		
		if(name.equals("-")) 
		{
			java.util.Iterator<String> iter = argumentResults.iterator();	
			
			if(!iter.hasNext()) { return "("+"negate[" + sourceResult +"]"+ ")"; }
			else if( iter.hasNext() && typename.equals("Set(T)")) { return "("+sourceResult + " - " + iter.next()+ ")"; }
			else if( iter.hasNext() && typename.equals("Real")){ return "("+sourceResult+").minus["+iter.next()+"]"+ ")"; }
		}
		
		// arguments
        for (java.util.Iterator<String> iter = argumentResults.iterator(); iter.hasNext();) 
        {
			String argument = iter.next();
			
			if(name.equals("intersection")) { return "("+sourceResult + " & " + argument+")"; }			
			if(name.equals("union")) { return "("+sourceResult + " + " + argument+")"; }						
			if(name.equals("including")) { return "("+sourceResult + " + " + argument+")"; }			
			if(name.equals("excluding")) { return "("+sourceResult + " - " + argument+")"; }			
			if(name.equals("includes")) { return "("+argument + " in " + sourceResult+")"; }			
			if(name.equals("excludes")) { return "("+argument + " !in " + sourceResult+")"; }			
			if(name.equals("includesAll")) { return "("+argument + " in " + sourceResult+")"; }
			if(name.equals("excludesAll")) { return "("+argument + " !in " + sourceResult+")"; }			
			if(name.equals("=")) { return "("+sourceResult + " = " + argument+")"; }			
			if(name.equals("<>")) { return "("+sourceResult + " != " + argument+")"; }			
			if(name.equals("oclIsKindOf"))	{ return "("+sourceResult + " in " + argument+")"; }			
			if(name.equals("<")) { return "("+sourceResult + " < " + argument + ")"; }			
			if(name.equals(">")) { return "("+sourceResult + " > " + argument + ")"; }			
			if(name.equals("<=")) { return "("+sourceResult + " <= " + argument + ")"; }			
			if(name.equals(">=")) { return "("+sourceResult + " >= " + argument + ")"; }						
			if(name.equals("and")) { return "("+sourceResult + " and " + argument+")"; }			
			if(name.equals("or")) { return "("+sourceResult + " or " + argument+")"; }			
			if(name.equals("implies")) { return "("+sourceResult + " implies " + argument+")"; }
			if(name.equals("xor")) { return "("+"!("+sourceResult + " == " + argument+")"+")"; }
			if(name.equals("max")) { return "(" + sourceResult + ").max[" + argument + "]"; }			
			if(name.equals("min")) { return "(" + sourceResult + ").min[" + argument + "]"; }
			if(name.equals("+")) { return "(" + sourceResult +").plus["+argument+"]"; }
			if(name.equals("symmetricDifference")) { return "("+"("+sourceResult + " + " + argument+") - ("+sourceResult + " & " + argument+")"+")"; }	        	
			
			if (iter.hasNext()) ;  // no more arguments 
        }
       	
		if(name.equals("xor")) 
			throw new OperationException("xor","We only support the boolean operations: and, or, not, implies.");
        if(name.equals("*")) 
        	throw new OperationException("*","We only support the integer operations: +, -, max(), min(), abs().");
        if(name.equals("/")) 
        	throw new OperationException("/","We only support the integer operations: +, -, max(), min(), abs().");
        if(name.equals("div")) 
        	throw new OperationException("div","We only support the integer operations: +, -, max(), min(), abs().");
        if(name.equals("mod")) 
        	throw new OperationException("mod","We only support the integer operations: +, -, max(), min(), abs().");
        if(name.equals("toString")) 
        	throw new OperationException("toString()","The type String is not supported.");
        if(name.equals("asOrderedSet"))	
        	throw new OperationException("asOrderedSet()","The collection type OrderedSet is not supported.");
        if(name.equals("asSequence")) 
        	throw new OperationException("asSequence()","The collection type Sequence is not supported.");
        if(name.equals("asBag")) 
        	throw new OperationException("asBag()","The collection type Bag is not supported.");         
        if(name.equals("oclIsInState"))	
        	throw new OperationException("oclIsInState()","There is not a state machine.");
        if(name.equals("oclIsNew"))	
        	throw new OperationException("oclIsNew()","Post conditions are not supported."); 
        if(name.equals("flatten"))
        	throw new OperationException("flatten()","We do not support nested collections.");       
        if(name.equals("oclIsInvalid")) 
        	throw new OperationException("oclIsInvalid()","The OclInvalid is not supported.");
        if(name.equals("product")) 
        	throw new OperationException("product()","The type Tuple is not supported");        
        if(name.equals("count"))
        	throw new OperationException("count()","We do not support this operation. Not possible of iterate over the collection.");
        if(name.equals("sum")) 
        	throw new OperationException("sum()","We do not support this operation because it only applies to numeric(Integer) types.");        
        if(name.equals("oclIsTypeOf"))	
        	throw new OperationException("oclIsTypeOf()","We do not support this object operation. ");        
        if(name.equals("oclType")) 
        	throw new OperationException("oclType()","We do not upport this object operation.");
               
        return result;
	}
        
    /** Visits IteratorExp. */        
	@Override
	public String handleIteratorExp (IteratorExp<Classifier,Parameter> icallExp,String sourceResult,java.util.List<String> variableResults,String bodyResult) 
    {     
    	StringBuffer result = new StringBuffer();
    	String name = icallExp.getName();    	
    	String var = new String();    	
    	    	
		// O iterador collect deverá sempre ser usado em conjunto com a operação asSet() porque collect retorna tipo Bag
    	if(name.equals("collect")) { 
    		collect_used=true; is_next_oper=true; 
    	} else { 
    		if(is_next_oper==true) 
    			throw new TypeException("Bag","The \"asSet()\" operation should be used after the \"collect()\" Iterator."); 
    	}
    	
        if(name.equals("forAll")) result.append("all ");
        if(name.equals("exists")) result.append("some ");
        if(name.equals("one")) result.append("#{ ");
        if(name.equals("select")) result.append("{ ");
        if(name.equals("reject")) result.append("{ ");        
        if(name.equals("isUnique")) result.append("all disj ");                
        
        // arguments
        for( java.util.Iterator<String> iter = variableResults.iterator(); iter.hasNext();) 
        {        	     
        	String string = iter.next();   
        	
        	if(name.equals("forAll")) result.append(string);
        	if(name.equals("exists")) result.append(string);
        	if(name.equals("one")) result.append(string);
        	if(name.equals("select")) result.append(string);
            if(name.equals("reject")) result.append(string);
            if(name.equals("collect")) var = string;
            
            if(name.equals("isUnique")){            	
            	var = string; 
            	result.append(var).append(",").append(var).append("'");
            }
                                               	            
            if (iter.hasNext()) result.append(",");
        }
        
        if(name.equals("forAll")) 
        	result.append(": ").append(sourceResult).append(" | ").append(bodyResult);
        if(name.equals("exists")) 
        	result.append(": ").append(sourceResult).append(" | ").append(bodyResult);
        if(name.equals("one")) 
        	result.append(": ").append(sourceResult).append(" | ").append(bodyResult).append(" } = 1");
        if(name.equals("reject")) 
        	result.append(": ").append(sourceResult).append(" | ").append("not ").append(bodyResult).append(" }");
        if(name.equals("select"))         	
        	result.append(": ").append(sourceResult).append(" | ").append(bodyResult).append(" }");
        
        if(name.equals("collect")) 
        {        	
        	// Substitue a variável x por sourceResult na expressão bodyResult
        	
        	String sb = substitutes(var,bodyResult,sourceResult);        	
        	result.append(sb);        	
        }
        if(name.equals("isUnique")) 
        {
        	result.append(": ").append(sourceResult).append(" | ").append(bodyResult).append(" != ");        	

        	// Substitue a variável x por x' na expressão bodyResult 

        	String sb = substitutes(var,bodyResult,var+"'");        	
        	result.append(sb);        	
        }        
                
        if(name.equals("sortedBy")) 
        	throw new IteratorException("sortedBy()","The type OrderedSet is not supported.");        
        if(name.equals("any")) 
        	throw new IteratorException("any()","We do not support this iterator. Problems arise when trying to return the type of an element of the collection"); 
        if(name.equals("collectNested")) 
        	throw new IteratorException("collectNested()","We do not support nested collections and the type Bag.");
       
       
     	return result.toString();    	       
    }    	    
    
    /** 
     *  Substitue var por sourceResult na expressão bodyResult 
     *  This method was used for Iterators: isUnique() and collect()
     */    
    public String substitutes (String var, String bodyResult, String sourceResult)
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
    		if(Pattern.matches(regex1,bodyResult.subSequence(indexBegin, indexEnd)) || 
    		   Pattern.matches(regex2,bodyResult.subSequence(indexBegin, indexEnd))	||
    		   Pattern.matches(regex3,bodyResult.subSequence(indexBegin, indexEnd))  )
    		   m.appendReplacement(sb, sourceResult);
    	}
    	m.appendTail(sb);
    	String result = sb.toString();
    	m.reset();
    	return result;
    }
    
    /** Visits IterateExp. */
	@Override
    public String handleIterateExp(IterateExp<Classifier,Parameter> callExp, String sourceResult,    
    List<String> variableResults,String resultResult, String bodyResult) 
    {
    	throw new OperationException("iterate()","Not possible of iterate over the collection.");		
	}
    
    /** Visits PropertyCallExp. */
	@Override
    public String handlePropertyCallExp (PropertyCallExp<Classifier,Property> propCallExp, String sourceResult, java.util.List<String> qualifierResults) 
    {    	
    	Property property = propCallExp.getReferredProperty();    	
    	StringBuffer result = new StringBuffer();
    	String name = property.getName();

		// if we are the qualifier of an association class call then we just return our name, 
    	// because our source is null (implied)    	
		if (sourceResult == null) return name;		

		// no qualifier		
		if (!qualifierResults.isEmpty()) { }
		
		// get Property Name
    	RefOntoUML.Property ontoProperty = (RefOntoUML.Property)oclparser.getKeyByValue(property);
    	String nameProperty = refparser.getName(ontoProperty);
    	
		result.append(sourceResult + "." + nameProperty+ "[w]");
				
		return result.toString();
	}        			
    
    /** Visits TypeExp. */ 
	@Override 
	public String visitTypeExp (TypeExp<Classifier> t) 
	{		
		Classifier classifier = t.getReferredType();
		
		// get Classifier Name
    	RefOntoUML.PackageableElement ontoClassifier = (RefOntoUML.PackageableElement)oclparser.getKeyByValue(classifier);
    	String nameClassifier = refparser.getName(ontoClassifier);
	
		return "w." + nameClassifier;
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
	
		// name
		String result = name;		
		
		// type (not need to transform)		
		if (type != null && initResult == null) {}

		// initial result
	    if (initResult != null) { result += " = " + initResult; }
	    	    	    
		return result;
	}
	
	/** Visits IfExp. */	
    @Override
    public String handleIfExp (IfExp<Classifier> ifExp, String conditionResult, String thenResult, String elseResult) 
    {
        return conditionResult + " implies " + thenResult + " else " + elseResult;
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
		switch (kind) 
		{
			case SET_LITERAL: 
			{
				String str = new String();
				for (Iterator<String> iter = partResults.iterator(); iter.hasNext();) 
				{
					str = iter.next();
					if (!iter.hasNext()) 
						result.append(str);
					else 
						throw new LiteralException("Set{}");
				}
				break;
			}
			
			case ORDERED_SET_LITERAL:	
				throw new LiteralException("OrderedSet{}");	
				  
			case BAG_LITERAL:
				throw new LiteralException("Bag{}");
				
			case SEQUENCE_LITERAL: 		
				throw new LiteralException("Sequence{}");
				
			case COLLECTION_LITERAL: 
				throw new LiteralException("OrderedSet{}");				
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
		throw new LiteralException("Enum");
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
        if (unl.isUnlimited()) 
        	throw new LiteralException("UnlimitedNatural");
        return 
        	(unl.getIntegerSymbol() == null)? null : unl.getIntegerSymbol().toString();
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
		throw new LiteralException("Boolean");
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
	@Override
    public String visitConstraint(Constraint constraint) 
    {
        StringBuffer result = new StringBuffer();
        java.util.List<? extends EObject> constrained = oclparser.getUMLReflection().getConstrainedElements(constraint);
        String stereo = oclparser.getUMLReflection().getStereotype(constraint);       
		
        // INVARIANT
        if (org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(stereo)) 
        {			
            if (!constrained.isEmpty()) 
            {
    			EObject elem = constrained.get(0);
                if (oclparser.getUMLReflection().isClassifier(elem)) 
                {
                	Classifier classifier = (Classifier)elem;

                	if(stereo_invariant.equals("ASSERTION")) result.append("assert ");
                	else if(stereo_invariant.equals("SIMULATION")) result.append("pred ");
                	else result.append("fact ");
                	
                	result.append("inv_"+constraint.getName()).append(" {\n");            
                	
                	if(stereo_invariant.equals("SIMULATION")) result.append("\tall w: World | all self: ");
                	else if(stereo_invariant.equals("ASSERTION")) result.append("\tall w: World | all self: ");
                	else result.append("\tall w: World | all self: ");
                	
                	// get name Classifier
                	RefOntoUML.PackageableElement ontoClassifier = (RefOntoUML.PackageableElement)oclparser.getKeyByValue(classifier);
                	String nameClassifier = refparser.getName(ontoClassifier);
                			
                	result.append("w."+nameClassifier+" | ");
                	
                    org.eclipse.ocl.uml.ExpressionInOCL expr = (org.eclipse.ocl.uml.ExpressionInOCL) constraint.getSpecification();
                            			
                    result.append(visit(expr)); 
                    result.append("\n}\n\n");                 
                    
                    if(stereo_invariant.equals("SIMULATION")) result.append("run "+"inv_"+constraint.getName()+" for 10 but 3 World, 7 Int\n\n");
                    else if(stereo_invariant.equals("ASSERTION")) result.append("check "+"inv_"+constraint.getName()+" for 10 but 3 World, 7 Int\n\n");
                	
                } 
                else if (oclparser.getUMLReflection().isOperation(elem)); 
                else if (oclparser.getUMLReflection().isProperty(elem));                          
            }
        }
        
        // DERIVATION 
        else if (org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(stereo))
        {
        	if (!constrained.isEmpty()) 
            {
                EObject elem = constrained.get(0);
                if (oclparser.getUMLReflection().isProperty(elem)) 
                {
                	count_derivation++;
                	
                	Property property = (Property)elem;
                	
                	// get Property Name
                	RefOntoUML.Property ontoProperty = (RefOntoUML.Property)oclparser.getKeyByValue(property);
                	String name_property = refparser.getName(ontoProperty);
                	
                	// get Type Name
                	RefOntoUML.PackageableElement ontoClassifier = (RefOntoUML.PackageableElement)oclparser.getKeyByValue(property.getType());
                	String type_property = refparser.getName(ontoClassifier);
                	                	
                	if(stereo_invariant.equals("ASSERTION")) result.append("assert ");
                	else if(stereo_invariant.equals("SIMULATION")) result.append("pred ");
                	else result.append("fact ");
                	
                	result.append("derive_"+count_derivation).append(" {\n");                	
                	result.append("\tall w: World | all self: ");                	         	
                	
                	// The type_property is equal to : "Set(typename)"
                	// We just need of the "typename"
                	String typename = new String();
                	if(type_property.contains("Set")){
                		typename = type_property.substring(4,type_property.length()-1);
                	}else{
                		typename = type_property;
                	}
                	
                	org.eclipse.uml2.uml.Type src_type = property.getAssociation().getMemberEnds().get(0).getType();
                	org.eclipse.uml2.uml.Type tgt_type = property.getAssociation().getMemberEnds().get(1).getType();
                	
                	// get Source Type Name
                	RefOntoUML.PackageableElement sourceType = (RefOntoUML.PackageableElement)oclparser.getKeyByValue(src_type);
                	String src_name = refparser.getName(sourceType);                	
                	
                	// get Target Type Name
                	RefOntoUML.PackageableElement targetType = (RefOntoUML.PackageableElement)oclparser.getKeyByValue(tgt_type);
                	String tgt_name = refparser.getName(targetType);
                	                	                	                	
               	    // Considering the direction
                	if(typename.equals(src_name)) result.append("w."+tgt_name+" | ");                	
                	if(typename.equals(tgt_name)) result.append("w."+src_name+" | ");
                	                	
                	result.append("self."+name_property+"[w] = ");
                	
                	org.eclipse.ocl.uml.ExpressionInOCL expr = (org.eclipse.ocl.uml.ExpressionInOCL) constraint.getSpecification();
                    result.append(visit(expr));
                	
                	result.append("\n}\n\n");
                	
                	if(stereo_invariant.equals("SIMULATION")) result.append("run "+"derive_"+""+count_derivation+" for 10 but 3 World, 7 Int\n\n");
                    else if(stereo_invariant.equals("ASSERTION")) result.append("check "+"derive_"+""+count_derivation+" for 10 but 3 World, 7 Int\n\n");
                	
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
   	
}

