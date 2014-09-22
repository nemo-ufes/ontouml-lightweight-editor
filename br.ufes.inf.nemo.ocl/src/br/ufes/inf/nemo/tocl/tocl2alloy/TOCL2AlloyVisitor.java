package br.ufes.inf.nemo.tocl.tocl2alloy;

import org.eclipse.ocl.expressions.OperationCallExp;
import org.eclipse.ocl.expressions.PropertyCallExp;
import org.eclipse.ocl.expressions.TypeExp;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ocl.ocl2alloy.OCL2AlloyVisitor;
import br.ufes.inf.nemo.tocl.parser.TOCLParser;

public class TOCL2AlloyVisitor extends OCL2AlloyVisitor {

	protected int temp_counter = 0;	
	protected int oclIsKindOf_counter=0;
	protected int oclIsTypeOf_counter=0;
	protected int oclIsNew_counter=0;
	
	public TOCL2AlloyVisitor(TOCLParser oclparser, OntoUMLParser refparser, TOCL2AlloyOption opt) 
	{
		super(oclparser, refparser, opt);	
	}
	 
	/** Visits TypeExp. */ 
	@Override 
	public String visitTypeExp (TypeExp<Classifier> t) 
	{		
		if(opt.getConstraintType(currentConstraint).equalsIgnoreCase("temporal"))
		{
			Classifier classifier = t.getReferredType();
			
			if (classifier.getName().equalsIgnoreCase("World")) return "World";			
			if (classifier.getName().equalsIgnoreCase("Path")) return "Path";
	    	if (classifier instanceof org.eclipse.ocl.uml.AnyType) return "univ";		
			if (classifier instanceof org.eclipse.ocl.uml.VoidType) return "none";		
			if (classifier instanceof org.eclipse.ocl.uml.PrimitiveType){ 
				if (classifier.getName().compareToIgnoreCase("Integer")==0) return "Int";			
			}    		
			
			RefOntoUML.PackageableElement ontoClassifier = (RefOntoUML.PackageableElement)((TOCLParser)oclparser).getOntoUMLElement(classifier);
	    	String nameClassifier = refparser.getAlias(ontoClassifier);    	
	    	return nameClassifier;	    
		}else{
			return super.visitTypeExp(t);
		}
	}
	
	/** Visits the OperationCallExp.  */
	@Override
    public String handleOperationCallExp (OperationCallExp<Classifier,Operation> operCallExp, String sourceResult, java.util.List<String> argumentsResult) 
    {  
		if(opt.getConstraintType(currentConstraint).equalsIgnoreCase("temporal"))
		{
			Operation oper = operCallExp.getReferredOperation();    	
			String operName = oper.getName();
			RefOntoUML.Element ontoElement = ((TOCLParser)oclparser).getOntoUMLElement(oper);
			if(operName.equals("hasPrevious")) { return "(some next."+sourceResult+")"; }
			if(operName.equals("hasNext")) { return "(some "+sourceResult+".next)"; }
			if(operName.equals("next")) { return "("+sourceResult+".next)"; }
			if(operName.equals("previous")) { return "(next."+sourceResult+")"; }			
			if(operName.equals("isOrigin")) { return "(no next."+sourceResult+")"; }
			if(operName.equals("isTerminal")) { return "(no "+sourceResult+".next)"; }			
			if(operName.equals("paths")) { return "Path["+sourceResult+"]"; }
			if(operName.equals("worlds")) { return "((^next."+sourceResult+")+"+sourceResult+")"; }
			if(operName.equals("oclIsNew")) { 
				String worldParam = ((TOCLParser)oclparser).getOclIsNewWorldParam(oclIsNew_counter);
				oclIsNew_counter++;					
				return sourceResult+" in "+worldParam+".exists and "+sourceResult+" !in (next."+worldParam+").exists)"; 
			}
			if(operName.equals("allIndividuals")) { return ""+sourceResult+".exists"; }
	        for (java.util.Iterator<String> iter = argumentsResult.iterator(); iter.hasNext();) 
	        {
				String argument = iter.next();
				if(operName.equals("allInstances")) { return argument+"."+sourceResult; }
				if(operName.equals("existsIn")) { return sourceResult+" in "+argument+"."+"exists"; }
				if(operName.equals("allPrevious")) { return "allPrevious["+sourceResult+","+argument+"]"; }
				if(operName.equals("allNext")) { return "allNext["+sourceResult+","+argument+"]"; }	
				if(ontoElement!=null){
					String alias = refparser.getAlias(ontoElement);
					if(ontoElement instanceof RefOntoUML.Property) { 
						if(((RefOntoUML.Property)ontoElement).getAssociation()!=null) return sourceResult+"."+alias+"["+argument+"]";
						else if (((RefOntoUML.Property)ontoElement).getType().getName().compareToIgnoreCase("Boolean")==0) { return "("+sourceResult + " in "+argument+"." + alias+ ")";}    	
				    	else { return sourceResult + ".("+argument+"." + alias+ ")"; }
					}
				}				
				if(operName.equals("oclIsKindOf")){
					String worldParam = ((TOCLParser)oclparser).getOclIsKindOfWorldParam(oclIsKindOf_counter);
					oclIsKindOf_counter++;
					return "("+sourceResult+" in "+worldParam+"."+argument+")";					
				}
				if(operName.equals("oclIsTypeOf")){
					String worldParam = ((TOCLParser)oclparser).getOclIsTypeOfWorldParam(oclIsTypeOf_counter);
					oclIsTypeOf_counter++;
					return super.visitOclIsTypeOf(sourceResult, argument, worldParam);		
				}				
	        }	        
			if(operName.equals("allPrevious")) { return "(^next."+sourceResult+")"; }
			if(operName.equals("allNext")) { return "("+sourceResult+".^next)"; }			
	        if(operName.equals("allInstances")) { 
	        	if (sourceResult.equals("World")) return sourceResult;	        	
	        	if (sourceResult.equals("Path")) return sourceResult;
	        	else return "World."+sourceResult;
	        }	        
	        if(ontoElement!=null){
				String alias = refparser.getAlias(ontoElement);
				if(ontoElement instanceof RefOntoUML.Property){ 
					if(((RefOntoUML.Property)ontoElement).getAssociation()!=null) return sourceResult+"."+alias;
					else if (((RefOntoUML.Property)ontoElement).getType().getName().compareToIgnoreCase("Boolean")==0) { return "("+sourceResult + " in World." + alias+ ")";}    	
			    	else { return sourceResult + ".(World." + alias+ ")"; }
				}
			}	

	        return super.handleOperationCallExp(operCallExp, sourceResult, argumentsResult);
		}else{
			return super.handleOperationCallExp(operCallExp, sourceResult, argumentsResult);	
		}
    }
	
	 /** Visits PropertyCallExp. */
	@Override
    public String handlePropertyCallExp (PropertyCallExp<Classifier,Property> propCallExp, String sourceResult, java.util.List<String> qualifierResults) 
    {    	
		if(opt.getConstraintType(currentConstraint).equalsIgnoreCase("temporal"))
		{
			StringBuffer result = new StringBuffer();
	    	Property property = propCallExp.getReferredProperty();    	

	    	RefOntoUML.Property ontoProperty = (RefOntoUML.Property)oclparser.getOntoUMLElement(property);
	    	String nameProperty = refparser.getAlias(ontoProperty);    	
	    	if (property.getAssociation()!=null) result.append(sourceResult + "." + nameProperty);    	
	    	else if (property.getType().getName().compareToIgnoreCase("Boolean")==0) { result.append("("+sourceResult + " in World." + nameProperty+ ")");}    	
	    	else { result.append(sourceResult + ".(World." + nameProperty+ ")"); }
	    	
			return result.toString();
		}else{
			return super.handlePropertyCallExp(propCallExp, sourceResult, qualifierResults);
		}
	}    
		
	@Override
	protected String visitInvariant(Classifier classifier, Constraint constraint) 
	{	
		StringBuffer result = new StringBuffer();
		if(opt.getConstraintType(constraint).equalsIgnoreCase("temporal"))
		{
			temp_counter++;			
			
			String stereotype = opt.getTransformationType(constraint);			
			if(stereotype.equals("CHECK")) result.append("assert ");		
			else if(stereotype.equals("SIMULATE")) result.append("pred ");		
			else result.append("fact ");
			
			if (constraint.getName()==null || constraint.getName().isEmpty()){
				result.append("temporal"+temp_counter).append(" {\n");
				constraint.setName("temporal"+temp_counter);
			}else
				result.append(constraint.getName()).append(" {\n");   
			
			org.eclipse.ocl.uml.ExpressionInOCL expr = (org.eclipse.ocl.uml.ExpressionInOCL) constraint.getSpecification();
			String exprResult = visit(expr);
			    
			RefOntoUML.PackageableElement ontoClassifier = (RefOntoUML.PackageableElement)oclparser.getOntoUMLElement(classifier);
			String nameClassifier = new String();
			if (ontoClassifier!=null) nameClassifier = refparser.getAlias(ontoClassifier);
			 	
			result.append("\t");
			if (expr.getBodyExpression().toString().contains("self")){
				if(ontoClassifier==null) result.append("all self: "+classifier.getName()+" | "); 
				else result.append("all self: World."+nameClassifier+" | ");
			}
			
			result.append(exprResult);
			result.append("\n}\n\n");                 
			    
			int scope = opt.getCommandScope(constraint);
		    int bitwidth = opt.getCommandBitwidth(constraint);
		    int world = opt.getWorldScope(constraint);
		    
		    if(stereotype.equals("SIMULATE")) result.append("run "+""+constraint.getName()+" for "+scope+" but "+world+" World, "+bitwidth+" Int\n\n");
		    else if(stereotype.equals("CHECK")) result.append("check "+""+constraint.getName()+" for "+scope+" but "+world+" World, "+bitwidth+" Int\n\n");
			    
			return result.toString();
		}else{
			return super.visitInvariant(classifier, constraint);
		}
	}
}
