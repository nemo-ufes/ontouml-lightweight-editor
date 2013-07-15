package br.ufes.inf.nemo.ocl2alloy;

import java.util.Iterator;
import java.util.List;

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
    
    public String stereotype = "RESTRICT";  
	
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
    	return OCLTransformer.generateOperationMapping(oper, sourceResult, argumentsResult, refparser);
	}
        
    /** Visits IteratorExp. */        
	@Override
	public String handleIteratorExp (IteratorExp<Classifier,Parameter> icallExp,String sourceResult,java.util.List<String> variableResults,String bodyResult) 
    {     
    	return OCLTransformer.generateIteratorMapping(icallExp, sourceResult, variableResults, bodyResult, refparser);   	       
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
		
		return OCLTransformer.generatePropertyMapping(property, sourceResult, oclparser, refparser);
	}        			
    
    /** Visits TypeExp. */ 
	@Override 
	public String visitTypeExp (TypeExp<Classifier> t) 
	{		
		Classifier classifier = t.getReferredType();		
		return OCLTransformer.generateTypeMapping(classifier, oclparser, refparser);
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
        return OCLTransformer.generateIfThenElseMapping(conditionResult, thenResult, elseResult);
	}
	
    /** Visits LetExp. */    
    @Override
    public String handleLetExp(LetExp<Classifier,Parameter> letExp, String variableResult, String inResult) 
    {        
        return OCLTransformer.generateLetMapping(variableResult, inResult);
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
		
        // INVARIANT
        if (org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(stereo)) 
        {			
            if (!constrained.isEmpty()) 
            {
    			EObject elem = constrained.get(0);
                if (oclparser.getUMLReflection().isClassifier(elem)) 
                {
                	Classifier classifier = (Classifier)elem;

                	if(stereotype.equals("CHECK")) result.append("assert ");
                	else if(stereotype.equals("SIMULATE")) result.append("pred ");
                	else result.append("fact ");
                	
                	result.append("inv_"+constraint.getName()).append(" {\n");            
                	
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
               			// nothing to do...
               		}
                	
                    if (expr.getBodyExpression().toString().equals("true")) result.append("isTrue["+exprResult+"]");
                    else if (expr.getBodyExpression().toString().equals("false")) result.append("isTrue["+exprResult+"]");
                    else result.append(exprResult); 
                    
                    result.append("\n}\n\n");                 
                    
                    if(stereotype.equals("SIMULATE")) result.append("run "+"inv_"+constraint.getName()+" for 10 but 3 World, 7 Int\n\n");
                    else if(stereotype.equals("CHECK")) result.append("check "+"inv_"+constraint.getName()+" for 10 but 3 World, 7 Int\n\n");
                	
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
                	derive_counter++;
                	
                	Property property = (Property)elem;
                	
                	// get Property Name
                	RefOntoUML.Property ontoProperty = (RefOntoUML.Property)oclparser.getOntoUMLElement(property);
                	String name_property = refparser.getAlias(ontoProperty);
                	
                	// get Type Name
                	RefOntoUML.PackageableElement ontoClassifier = (RefOntoUML.PackageableElement)oclparser.getOntoUMLElement(property.getType());
                	String type_property = refparser.getAlias(ontoClassifier);
                	                	
                	if(stereotype.equals("CHECK")) result.append("assert ");
                	else if(stereotype.equals("SIMULATE")) result.append("pred ");
                	else result.append("fact ");
                	
                	result.append("derive_"+derive_counter).append(" {\n");                	
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
                	
                	if(stereotype.equals("SIMULATE")) result.append("run "+"derive_"+""+derive_counter+" for 10 but 3 World, 7 Int\n\n");
                    else if(stereotype.equals("CHECK")) result.append("check "+"derive_"+""+derive_counter+" for 10 but 3 World, 7 Int\n\n");
                	
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

