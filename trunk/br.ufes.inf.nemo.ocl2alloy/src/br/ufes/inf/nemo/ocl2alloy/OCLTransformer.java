package br.ufes.inf.nemo.ocl2alloy;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.expressions.IteratorExp;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2alloy.exception.IteratorException;
import br.ufes.inf.nemo.ocl2alloy.exception.OperationException;


public class OCLTransformer {
			
	public static String generateOperationMapping (Operation oper, String sourceResult, java.util.List<String> argumentsResult, OntoUMLParser refparser)
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
			
			if(operName.equals("oclIsTypeOf"))	{  return generateOclIsTypeOfMapping(sourceResult,argument,refparser); }
							
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
	
	public static String generateIteratorMapping(IteratorExp<Classifier,Parameter> icallExp, String sourceResult,java.util.List<String> variableResults,String bodyResult, OntoUMLParser refparser)
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
	
	public static String generateIfThenElseMapping(String conditionResult, String thenResult, String elseResult)
	{
		return conditionResult + " implies " + thenResult + " else " + elseResult;
	}
	
	public static String generateLetMapping(String variableResult, String inResult)
	{
		return "let " + variableResult + " | " + inResult;
	}
	
	public static String generatePropertyMapping(Property property,  String sourceResult, OCLParser oclparser, OntoUMLParser refparser)
	{
		StringBuffer result = new StringBuffer();
    	RefOntoUML.Property ontoProperty = (RefOntoUML.Property)oclparser.getOntoUMLElement(property);    	
    	String nameProperty = refparser.getAlias(ontoProperty);
    	
    	if (ontoProperty.getAssociation()!=null) result.append(sourceResult + "." + nameProperty+ "[w]");
    	
    	else if (ontoProperty.getType().getName().compareToIgnoreCase("Boolean")==0) { result.append(sourceResult + " in w." + nameProperty+ "");}
    	
    	else result.append(sourceResult + ".(w." + nameProperty+ ")");
    	
		return result.toString();
	}
	
	public static String generateTypeMapping(Classifier classifier, OCLParser oclparser, OntoUMLParser refparser)
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
	
	//====================================================================
	
	/**
	 * Generate the mapping regarding the OCL oclIsTypeOf Operation.
	 */
	private static String generateOclIsTypeOfMapping(String sourceResult, String argument, OntoUMLParser refparser)
	{
		String firstPart = "("+sourceResult + " in " + argument+")";
		String secondPart = new String();
		if (argument.contains("w.")) argument = argument.replace("w.", "");				
		ArrayList<String> subtypes = getSubTypes(refparser, argument);
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
    public static String substitute (String var, String bodyResult, String sourceResult)
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
    public static ArrayList<String> getSubTypes(OntoUMLParser refparser, String alias)
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
