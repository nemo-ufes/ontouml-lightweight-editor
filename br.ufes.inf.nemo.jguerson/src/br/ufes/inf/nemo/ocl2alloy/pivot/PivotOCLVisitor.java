package br.ufes.inf.nemo.ocl2alloy.pivot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.AnyType;
import org.eclipse.ocl.examples.pivot.AssociationClassCallExp;
import org.eclipse.ocl.examples.pivot.BooleanLiteralExp;
import org.eclipse.ocl.examples.pivot.CollectionItem;
import org.eclipse.ocl.examples.pivot.CollectionLiteralExp;
import org.eclipse.ocl.examples.pivot.CollectionRange;
import org.eclipse.ocl.examples.pivot.CollectionType;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.ConstructorExp;
import org.eclipse.ocl.examples.pivot.ConstructorPart;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.ElementExtension;
import org.eclipse.ocl.examples.pivot.EnumLiteralExp;
import org.eclipse.ocl.examples.pivot.EnumerationLiteral;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.IfExp;
import org.eclipse.ocl.examples.pivot.Import;
import org.eclipse.ocl.examples.pivot.IntegerLiteralExp;
import org.eclipse.ocl.examples.pivot.InvalidLiteralExp;
import org.eclipse.ocl.examples.pivot.InvalidType;
import org.eclipse.ocl.examples.pivot.IterateExp;
import org.eclipse.ocl.examples.pivot.Iteration;
import org.eclipse.ocl.examples.pivot.IteratorExp;
import org.eclipse.ocl.examples.pivot.LambdaType;
import org.eclipse.ocl.examples.pivot.LetExp;
import org.eclipse.ocl.examples.pivot.MessageExp;
import org.eclipse.ocl.examples.pivot.Metaclass;
import org.eclipse.ocl.examples.pivot.NamedElement;
import org.eclipse.ocl.examples.pivot.NullLiteralExp;
import org.eclipse.ocl.examples.pivot.OCLExpression;
import org.eclipse.ocl.examples.pivot.OpaqueExpression;
import org.eclipse.ocl.examples.pivot.Operation;
import org.eclipse.ocl.examples.pivot.OperationCallExp;
import org.eclipse.ocl.examples.pivot.Parameter;
import org.eclipse.ocl.examples.pivot.Precedence;
import org.eclipse.ocl.examples.pivot.PrimitiveType;
import org.eclipse.ocl.examples.pivot.Property;
import org.eclipse.ocl.examples.pivot.PropertyCallExp;
import org.eclipse.ocl.examples.pivot.RealLiteralExp;
import org.eclipse.ocl.examples.pivot.Root;
import org.eclipse.ocl.examples.pivot.StateExp;
import org.eclipse.ocl.examples.pivot.StringLiteralExp;
import org.eclipse.ocl.examples.pivot.TemplateBinding;
import org.eclipse.ocl.examples.pivot.TemplateParameter;
import org.eclipse.ocl.examples.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.examples.pivot.TemplateSignature;
import org.eclipse.ocl.examples.pivot.TupleLiteralExp;
import org.eclipse.ocl.examples.pivot.TupleLiteralPart;
import org.eclipse.ocl.examples.pivot.TupleType;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.TypeExp;
import org.eclipse.ocl.examples.pivot.UMLReflection;
import org.eclipse.ocl.examples.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.examples.pivot.UnspecifiedType;
import org.eclipse.ocl.examples.pivot.UnspecifiedValueExp;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.VariableExp;
import org.eclipse.ocl.examples.pivot.VoidType;
import org.eclipse.ocl.examples.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.examples.pivot.util.Visitable;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;

public class PivotOCLVisitor extends AbstractExtendingVisitor<String, Object> {
		
	private PivotOCLParser oclparser;
	private PrettyPrintAlloyOption opt;		 
	private int constraint_count = 0;	
	private static String NULL_PLACEHOLDER = "<null>";
	
	public PivotOCLVisitor() 
	{ 
		super(Object.class); 
	}
	
	public PivotOCLVisitor(PivotOCLParser parser)
	{
		super(Object.class);
		oclparser = parser;	
		opt =  new PrettyPrintAlloyOption(PrettyPrintAlloyOption.ConstraintType.FACT,10,1);
	}
	
	public PivotOCLVisitor(PivotOCLParser parser, PrettyPrintAlloyOption option)
	{
		super(Object.class);
		oclparser = parser;	
		opt =  option;
	}
	
	public String prettyPrintAlloy (Constraint element) 
	{		 
		opt =  new PrettyPrintAlloyOption(PrettyPrintAlloyOption.ConstraintType.FACT,10,1);
		return prettyPrintAlloy(element,opt);
	}
	
	public String prettyPrintAlloy (Constraint element, PrettyPrintAlloyOption option) 
	{
		this.opt = option;		 
        try {
        	String result = safeVisit(element);
            return result;
        } catch (Exception e) {
        	e.printStackTrace();
        	return toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
        }
	}	
	
	public String prettyPrintAlloy (ArrayList<Constraint> constraints, ArrayList<PrettyPrintAlloyOption> options) throws Exception
	{
		if(constraints.size() > options.size()) throw new Exception("prettyPrintAlloy() : the number of constraints is greater than the number of options.");
		try {			
			int i=0; String result = new String();
			for(Constraint ct: constraints) { this.opt = options.get(i); result += safeVisit(ct); i++; }			
			return result;			
		} catch (Exception e) {
        	e.printStackTrace();
        	return toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
        }		
	}
	
	public EModelElement getEcoreOfPivot (org.eclipse.ocl.examples.pivot.Element pivotElem)
	{
		return oclparser.getMetamodelManager().getEcoreOfPivot(EModelElement.class, pivotElem);
	}
	
	public RefOntoUML.Element getOntoUMLOfEcore (EModelElement eElem)
	{
		for (Entry<RefOntoUML.Element,EModelElement> entry : oclparser.getOntoUML2EcoreMap().entrySet()) 
	    {
            if (eElem.equals(entry.getValue())) return entry.getKey();                
            else {            	
        		if (eElem.eClass().getName().equals(entry.getValue().eClass().getName()))  
        		{
        			String ecoreName = ((ENamedElement)eElem).getName().trim().toLowerCase();
	    			String entryName = ((ENamedElement)entry.getValue()).getName().trim().toLowerCase();	    			
        			if (eElem instanceof EReference){
        				String ecoreContainerName = ((EClass)eElem.eContainer()).getName().trim().toLowerCase();
        				String entryContainerName = ((EClass)entry.getValue().eContainer()).getName().trim().toLowerCase();  
        				String ecoreTypeName = ((EReference)eElem).getEType().getName().trim().toLowerCase();
        				String entryTypeName = ((EReference)entry.getValue()).getEType().getName().trim().toLowerCase();        				
        				if (ecoreName.equals(entryName) && ecoreContainerName.equals(entryContainerName) && ecoreTypeName.equals(entryTypeName)) return entry.getKey();
        			}else{ 		    			      			
		    			if (ecoreName.equals(entryName)) return entry.getKey();
        			}		    		
        		}
            }
	    }
		return null;
	}
	
	/** Get sub types of Classifier from its alias */
	private ArrayList<String> getSubTypeList(String aliasClassifier)
    {    	
    	EObject type = oclparser.getOntoUMLParser().getElement(aliasClassifier);    	
    	Set<RefOntoUML.Classifier> setChildren = oclparser.getOntoUMLParser().getChildren((RefOntoUML.Classifier)type);    	
    	ArrayList<RefOntoUML.Classifier> listChildren = new ArrayList<RefOntoUML.Classifier>();
    	listChildren.addAll(setChildren);    	
    	ArrayList<String> subtypes = new ArrayList<String>();    	
    	for(RefOntoUML.Classifier child: listChildren)
    	{
    		subtypes.add(oclparser.getOntoUMLParser().getAlias(child));
    	}   	    	
    	return subtypes;
    }
	
	protected String append(Number number)
	{
		StringBuilder localResult = new StringBuilder();
		if (number != null) localResult.append(number.toString());
		else localResult.append(NULL_PLACEHOLDER);		
		return localResult.toString();
	}
	
	protected String append(String string) 
	{
		StringBuilder localResult = new StringBuilder();
		if (string != null) localResult.append(string);
		else localResult.append(NULL_PLACEHOLDER);		
		return localResult.toString();
	}
	
	protected String visitName(NamedElement object) 
	{
		StringBuilder localResult = new StringBuilder();
		if (object == null) localResult.append(NULL_PLACEHOLDER);
		else localResult.append(object.getName());		
		return localResult.toString();
	}	
	
	public String visitConstraintName (Constraint constraint)
	{
		StringBuilder localResult = new StringBuilder();
		if (constraint.getName()==null || constraint.getName().isEmpty())
		{			
			constraint.setName("constraint"+constraint_count);
			localResult.append("constraint"+constraint_count);			
		}else 
			localResult.append(constraint.getName());
		return localResult.toString();
	}
	
	public String visitContext (Element context, String stereotype)
	{
		StringBuilder localResult = new StringBuilder();
		EModelElement eContext = getEcoreOfPivot(context);
		RefOntoUML.Element ontoContext = getOntoUMLOfEcore(eContext);
		String aliasContext = oclparser.getOntoUMLParser().getAlias(ontoContext);
		
		if (UMLReflection.INVARIANT.equals(stereotype)) 
		{						
			if (context instanceof Type)
			{
				localResult.append("all self: ");				
				if (ontoContext instanceof RefOntoUML.DataType) localResult.append(aliasContext+" | "); 
				else localResult.append("w."+aliasContext+" | ");
			}
		} 
		
		else if (UMLReflection.DERIVATION.equals(stereotype)) 
		{
			if (context instanceof Property)
			{
				RefOntoUML.Type ontoContextType = ((RefOntoUML.Property)ontoContext).getType();
				String aliasType = oclparser.getOntoUMLParser().getAlias(ontoContextType);
				
				// the aliasType is a string in the form "Set(TypeName)"
				// we only need the "Typename"					
		    	String typename = new String();
		    	if(aliasType.contains("Set")) typename = aliasType.substring(4,aliasType.length()-1);
		    	else typename = aliasType;					
			
				RefOntoUML.Property ontoProperty = (RefOntoUML.Property)ontoContext;			
		    	RefOntoUML.Type src_type;
		    	RefOntoUML.Type tgt_type;
		    	
		    	 // derive an assoc end or and attribute
		    	if (ontoProperty.getAssociation()!=null){ 
		    		src_type = ontoProperty.getAssociation().getMemberEnd().get(0).getType();
		    		tgt_type = ontoProperty.getAssociation().getMemberEnd().get(1).getType();
		    	}else {
		    		src_type = (RefOntoUML.Type)ontoProperty.getOwner();
		    		tgt_type = ontoProperty.getType();
		    	}
		    	String src_name = oclparser.getOntoUMLParser().getAlias(src_type);                	
		    	String tgt_name = oclparser.getOntoUMLParser().getAlias(tgt_type);
		    	                	                	                	
		   	    // this piece of code considers the direction...
		    	if(typename.equals(src_name)) localResult.append("w."+tgt_name+" | ");                	
		    	if(typename.equals(tgt_name)) localResult.append("w."+src_name+" | ");
				                	
		    	if(ontoProperty.getAssociation()!=null) localResult.append("self."+aliasContext+"[w] = ");
		    	else localResult.append("self.(w."+aliasContext+") = ");
			}
		}		
		return localResult.toString();
	}
	
	@Override
	public String visitConstraint (Constraint constraint)
	{
		StringBuilder localResult = new StringBuilder();
		Element context = constraint.getContext();
		OCLExpression oclExpression = ((ExpressionInOCL)constraint.getSpecification()).getBodyExpression();		
		String stereotype = PivotUtil.getStereotype(constraint); 				
		constraint_count++;
		
		if (UMLReflection.INVARIANT.equals(stereotype) || UMLReflection.DERIVATION.equals(stereotype))
		{
			if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.CHECK)) localResult.append("assert ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.RUN)) localResult.append("pred ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.FACT)) localResult.append("fact ");			
			localResult.append(visitConstraintName(constraint));
			localResult.append(" {\n").append("\tall w: World | ");						
			if (oclExpression.toString().contains("self")) 
			{
				if (context instanceof Type) localResult.append(visitContext(context,stereotype));
				else if (context instanceof Operation) localResult.append(visitContext(context,stereotype));
				else if (context instanceof Property) localResult.append(visitContext(context,stereotype));
			}								
			localResult.append("\n\t");			
			localResult.append(safeVisit(constraint.getSpecification()));			
			localResult.append("\n}\n\n");
		    if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.CHECK)) localResult.append("check ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.RUN)) localResult.append("run ");		    
		    if (!opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.FACT))
		    {
		    	localResult.append(visitConstraintName(constraint));
		    	localResult.append(" for "+opt.global_scope+" but "+opt.world_scope+" World, 7 Int\n\n");
		    }
		}
		if (UMLReflection.DEFINITION.equals(stereotype));		
		if (UMLReflection.INITIAL.equals(stereotype));		
		if (UMLReflection.BODY.equals(stereotype));
		if (UMLReflection.PRECONDITION.equals(stereotype)); 
		if (UMLReflection.POSTCONDITION.equals(stereotype)); 
		
		return localResult.toString(); 
	}	
		
   	@Override
	public String visitAnyType(@NonNull AnyType object) 
   	{   		
   		return NULL_PLACEHOLDER; 
   	}
   	
	@Override
	public String visitAssociationClassCallExp(@NonNull AssociationClassCallExp ac) 
	{		
		return NULL_PLACEHOLDER;
	}
	
	@Override
	public String visitBooleanLiteralExp(@NonNull BooleanLiteralExp bl) 
	{
		StringBuilder localResult = new StringBuilder();
		if (Boolean.toString(bl.isBooleanSymbol()).equals("true")) localResult.append("no none");
		if (Boolean.toString(bl.isBooleanSymbol()).equals("false")) localResult.append("some none");
		return localResult.toString(); 
	}
	
	@Override
	public String visitClass(@NonNull org.eclipse.ocl.examples.pivot.Class cls) 
	{		
		StringBuilder localResult = new StringBuilder();
		EClass eClass = (EClass)getEcoreOfPivot(cls);
		RefOntoUML.Classifier ontoClassifier = (RefOntoUML.Classifier)getOntoUMLOfEcore(eClass);
		String aliasOntoClassifier = oclparser.getOntoUMLParser().getAlias(ontoClassifier);		
		if (ontoClassifier instanceof RefOntoUML.DataType) localResult.append(aliasOntoClassifier);
		else localResult.append("w."+aliasOntoClassifier);			
		return localResult.toString(); 
	}
	
	@Override
	public String visitCollectionItem(@NonNull CollectionItem item) 
	{
		return safeVisit(item.getItem());		
	}
	
	@Override
	public String visitCollectionLiteralExp(@NonNull CollectionLiteralExp cl) 
	{		
		return ""; 
	}	
	
	@Override
	public String visitCollectionRange(@NonNull CollectionRange range) 
	{		
		return NULL_PLACEHOLDER;
	}
		
	@Override
	public String visitCollectionType(@NonNull CollectionType object) 
	{		
		return NULL_PLACEHOLDER; 
	}
	
	@Override
	public String visitConstructorExp(@NonNull ConstructorExp constructorExp) 
	{		
		return NULL_PLACEHOLDER; 
	}
	
	@Override
	public String visitConstructorPart(@NonNull ConstructorPart part) 
	{		
		return NULL_PLACEHOLDER;
	}
	
	@Override
	public String visitElementExtension(@NonNull ElementExtension as) 
	{		
		return NULL_PLACEHOLDER;
	}
	
	@Override
	public String visitEnumLiteralExp(@NonNull EnumLiteralExp el) 
	{
		return NULL_PLACEHOLDER;
	}	
	
	@Override
	public String visitEnumerationLiteral(@NonNull EnumerationLiteral el) 
	{
		return NULL_PLACEHOLDER; 
	}	
	
	@Override
	public String visitExpressionInOCL(@NonNull ExpressionInOCL expression) 
	{
		StringBuilder localResult = new StringBuilder();	
		OCLExpression bodyExpression = expression.getBodyExpression();
		if (bodyExpression != null) { localResult.append(safeVisit(bodyExpression)); }
		return localResult.toString(); 		
	}
	
	@Override 
	public String visitIfExp(@NonNull IfExp ifExp) 
	{
		StringBuilder localResult = new StringBuilder();	
		OCLExpression condExpression = ifExp.getCondition();
		OCLExpression thenExpression = ifExp.getThenExpression();
		OCLExpression elseExpression = ifExp.getElseExpression();				
		localResult.append(safeVisit(condExpression)); 
		localResult.append(" implies "); 
		localResult.append(safeVisit(thenExpression)); 
		localResult.append(" else "); 
		localResult.append(safeVisit(elseExpression));		
		return localResult.toString(); 	
	}
	
	@Override 
	public @Nullable String visitImport(@NonNull Import object) 
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitIntegerLiteralExp(@NonNull IntegerLiteralExp il) 
	{
		return il.getIntegerSymbol().toString();		 
	}
	
	@Override
	public String visitInvalidLiteralExp(@NonNull InvalidLiteralExp il) 
	{		
		return NULL_PLACEHOLDER;   
	}
	
	@Override
	public String visitInvalidType(@NonNull InvalidType object)  
	{		
		return NULL_PLACEHOLDER;   
	}
	
	@Override
	public String visitIterateExp(@NonNull IterateExp callExp)  
	{		
		return NULL_PLACEHOLDER;   
	}
	
	@Override
	public String visitIteration(@NonNull Iteration iteration)  
	{		
		return ""; 
	}
	
	@Override
	public String visitIteratorExp(@NonNull IteratorExp callExp)  
	{
		OCLExpression srcExpression = callExp.getSource();
		Iteration iteration = callExp.getReferredIteration();
		String iterExpName = iteration.getName();
		OCLExpression bodyExpression = callExp.getBody();
				
		append("(");
		if(iterExpName.equals("forAll")) append("all ");        
        if(iterExpName.equals("exists")) append("some ");        
        if(iterExpName.equals("one")) append("#{ ");        
        if(iterExpName.equals("select")) append("{ ");        
        if(iterExpName.equals("reject")) append("{ ");              
        if(iterExpName.equals("isUnique")) append("all disj ");  
        
		boolean isFirst = true;
		for (Variable variable : callExp.getIterator()) 
		{
			if (!isFirst) {	append(", "); }                        
            if(iterExpName.equals("forAll")) safeVisit(variable);        	
        	if(iterExpName.equals("exists")) safeVisit(variable);        	
        	if(iterExpName.equals("one")) safeVisit(variable);        	
        	if(iterExpName.equals("select")) safeVisit(variable);        	
            if(iterExpName.equals("reject")) safeVisit(variable);               
			isFirst = false;
		}
		
		if(iterExpName.equals("forAll")) { append(": "); safeVisit(srcExpression); append(" | "); safeVisit(bodyExpression);  }        
        if(iterExpName.equals("exists")) { append(": "); safeVisit(srcExpression); append(" | "); safeVisit(bodyExpression); }        
        if(iterExpName.equals("one")) { append(": "); safeVisit(srcExpression); append(" | "); safeVisit(bodyExpression); append(" } = 1"); }        
        if(iterExpName.equals("reject")) { append(": "); safeVisit(srcExpression); append(" | "); append("not "); safeVisit(bodyExpression); append(" }"); }        
        if(iterExpName.equals("select")) { append(": "); safeVisit(srcExpression); append(" | "); safeVisit(bodyExpression); append(" }"); }
                        
		append(" | ");
		
		safeVisit(bodyExpression);
		append(")");
		
		/*
		               
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
     	return result.toString(); */
		return null; 
	}
	
	@Override
	public String visitLambdaType(@NonNull LambdaType lambda)  
	{		
		return NULL_PLACEHOLDER;   
	}
	
	@Override
	public String visitLetExp(@NonNull LetExp letExp)  
	{
		StringBuilder localResult = new StringBuilder();
		Variable var = letExp.getVariable();
		OCLExpression inExpression = letExp.getIn();		
		localResult.append("let ");  
		localResult.append(safeVisit(var)); 
		localResult.append(" | "); 
		localResult.append(safeVisit(inExpression));
		return localResult.toString(); 	 
	}
	
	@Override
	public String visitMessageExp(@NonNull MessageExp messageExp)  
	{		
		return NULL_PLACEHOLDER;   
	}
	
	@Override
	public String visitMetaclass(@NonNull Metaclass object)  
	{		
		return NULL_PLACEHOLDER;   
	}
	
	@Override
	public String visitNullLiteralExp(@NonNull NullLiteralExp il)  
	{
		return "none";
	}
	
	@Override
	public String visitOpaqueExpression(@NonNull OpaqueExpression object)  
	{ 			
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitOperation(@NonNull Operation operation)  
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitOperationCallExp(@NonNull OperationCallExp oc)  
	{		
		Operation oper = oc.getReferredOperation(); 
		String operTypeResult = oper.getType().getName();
		String operName = oper.getName();
		OCLExpression srcExpression = oc.getSource();		
		List<OCLExpression> arguments = oc.getArgument();
		String sourceResult = safeVisit(srcExpression);
		
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
			java.util.Iterator<OCLExpression> iter = arguments.iterator();				
			if(!iter.hasNext()) { return "negate[" + sourceResult +"]"; }			
			else if( iter.hasNext() && operTypeResult.equals("Set(T)")) { return "("+sourceResult + " - " + safeVisit(iter.next())+ ")"; }			
			else if( iter.hasNext() && operTypeResult.equals("Real")){ return "("+sourceResult+").minus["+safeVisit(iter.next())+"]"; }
		}
		
        for (java.util.Iterator<OCLExpression> iter = arguments.iterator(); iter.hasNext();) 
        {
        	OCLExpression argumentExpression = iter.next();        	
        	String argument = safeVisit(argumentExpression);
        	
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
//			if(operName.equals("oclIsTypeOf"))	{  return generateOclIsTypeOfMapping(sourceResult,argument); }
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
        
		return NULL_PLACEHOLDER;  
	}	
	
	@Override
	public String visitPackage(@NonNull org.eclipse.ocl.examples.pivot.Package pkg)  
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitParameter(@NonNull Parameter parameter)  
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitPrecedence(@NonNull Precedence precedence)  
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitPrimitiveType(@NonNull PrimitiveType object)  
	{	
		return NULL_PLACEHOLDER; 
	}
	
	@Override
	public String visitProperty(@NonNull Property property)  
	{ 		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitPropertyCallExp(@NonNull PropertyCallExp pc)  
	{
		StringBuilder localResult = new StringBuilder();
		OCLExpression scrExpression = pc.getSource();
		Property property = pc.getReferredProperty();		
		EStructuralFeature eStructFeature = (EStructuralFeature) getEcoreOfPivot(property);
		RefOntoUML.Property ontoProperty = (RefOntoUML.Property)getOntoUMLOfEcore(eStructFeature);
		String aliasOntoProperty = oclparser.getOntoUMLParser().getAlias(ontoProperty);		
		if (eStructFeature instanceof EAttribute)
		{
			if (eStructFeature.getEType().isInstance(EcorePackage.eINSTANCE.getEBoolean())) 
			{ 
				localResult.append(safeVisit(scrExpression)); 
				localResult.append("in w." + aliasOntoProperty); 
				return localResult.toString(); 
			} 
			else { 
				localResult.append(safeVisit(scrExpression));
				localResult.append(".(w."+aliasOntoProperty+")"); 
				return localResult.toString();
			}
		} 
		else if (eStructFeature instanceof EReference) 
		{ 
			localResult.append(safeVisit(scrExpression)); 
			localResult.append("."+aliasOntoProperty+"[w]"); 
			return localResult.toString();
		}
		
		List<OCLExpression> qualifiers = pc.getQualifier();
		if (!qualifiers.isEmpty()) for (@SuppressWarnings("unused") OCLExpression qualifier : qualifiers) { }		
		return localResult.toString();		 
	}
	
	@Override
	public String visitRealLiteralExp(@NonNull RealLiteralExp rl)  
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitRoot(@NonNull Root root)  
	{		
		return NULL_PLACEHOLDER; 
	}
	
	@Override
	public String visitStateExp(@NonNull StateExp s)  
	{
		return NULL_PLACEHOLDER; 
	}
	
	@Override
	public String visitStringLiteralExp(@NonNull StringLiteralExp sl)  
	{
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitTemplateBinding(@NonNull TemplateBinding object)  
	{		
		return NULL_PLACEHOLDER; 
	}
	
	@Override
	public String visitTemplateParameter(@NonNull TemplateParameter object)  
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitTemplateParameterSubstitution(@NonNull TemplateParameterSubstitution object)  
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitTemplateSignature(@NonNull TemplateSignature object)  
	{		
		return NULL_PLACEHOLDER; 
	}
	
	@Override
	public String visitTupleLiteralExp(@NonNull TupleLiteralExp literalExp)  
	{		
		return NULL_PLACEHOLDER; 
	}
	
	@Override
	public String visitTupleLiteralPart(@NonNull TupleLiteralPart part)  
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitTupleType(@NonNull TupleType object)  
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitTypeExp(@NonNull TypeExp t) 
	{
		StringBuilder localResult = new StringBuilder();
		Type type = t.getReferredType();
		EClass eType = (EClass)getEcoreOfPivot(type);
		RefOntoUML.Type ontoType = (RefOntoUML.Type)getOntoUMLOfEcore(eType);
		String aliasOntoType = oclparser.getOntoUMLParser().getAlias(ontoType);		
		if (ontoType instanceof RefOntoUML.DataType) localResult.append(aliasOntoType);
		else localResult.append("w."+aliasOntoType);
		return localResult.toString(); 
	}
	
	@Override
	public String visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp unl) 
	{
		return unl.getUnlimitedNaturalSymbol().toString();		
	}
	
	@Override
	public String visitUnspecifiedType(@NonNull UnspecifiedType object) 
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitUnspecifiedValueExp(@NonNull UnspecifiedValueExp uv) 
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitVariable(@NonNull Variable variable) 
	{
		StringBuilder localResult = new StringBuilder();
		localResult.append(visitName(variable));
		Type type = variable.getType();
		if (type != null) {}
		OCLExpression initExpression = variable.getInitExpression();
		if (initExpression != null) {
			localResult.append(" = ");
			localResult.append(safeVisit(initExpression));
		}		
		return localResult.toString(); 
	}
	
	@Override
	public String visitVariableExp(@NonNull VariableExp v) 
	{
		return visitName(v.getReferredVariable());		
	}
	
	@Override
	public String visitVoidType(@NonNull VoidType object) 
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visiting(@NonNull Visitable visitable) 
	{		
		return NULL_PLACEHOLDER; 
	}
}
