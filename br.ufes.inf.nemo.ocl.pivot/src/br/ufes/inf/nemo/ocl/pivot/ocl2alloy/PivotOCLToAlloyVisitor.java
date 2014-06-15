package br.ufes.inf.nemo.ocl.pivot.ocl2alloy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import br.ufes.inf.nemo.ocl.pivot.ocl2alloy.exceptions.PivotConstraintException;
import br.ufes.inf.nemo.ocl.pivot.ocl2alloy.exceptions.PivotIteratorException;
import br.ufes.inf.nemo.ocl.pivot.ocl2alloy.exceptions.PivotLiteralException;
import br.ufes.inf.nemo.ocl.pivot.ocl2alloy.exceptions.PivotOperationException;
import br.ufes.inf.nemo.ocl.pivot.ocl2alloy.exceptions.PivotTypeException;
import br.ufes.inf.nemo.ocl.pivot.parser.PivotOCLParser;

public class PivotOCLToAlloyVisitor extends AbstractExtendingVisitor<String, Object> {
		
	private PivotOCLParser oclparser;
	private PivotPrettyPrintAlloyOption opt;		 
	private int constraint_count = 0;	
	private static String NULL_PLACEHOLDER = "<null>";
	
	public PivotOCLToAlloyVisitor() 
	{ 
		super(Object.class); 
	}
	
	public PivotOCLToAlloyVisitor(PivotOCLParser parser)
	{
		super(Object.class);
		oclparser = parser;	
		opt =  new PivotPrettyPrintAlloyOption(PivotPrettyPrintAlloyOption.ConstraintType.FACT,10,1);
	}
	
	public PivotOCLToAlloyVisitor(PivotOCLParser parser, PivotPrettyPrintAlloyOption option)
	{
		super(Object.class);
		oclparser = parser;	
		opt =  option;
	}
	
	public String prettyPrintAlloy (Constraint element) 
	{		 
		opt =  new PivotPrettyPrintAlloyOption(PivotPrettyPrintAlloyOption.ConstraintType.FACT,10,1);
		return prettyPrintAlloy(element,opt);
	}
	
	public String prettyPrintAlloy (Constraint element, PivotPrettyPrintAlloyOption option) 
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
	
	public String prettyPrintAlloy (ArrayList<Constraint> constraints, ArrayList<PivotPrettyPrintAlloyOption> options) throws Exception
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
	
	/** 
     *  Replace "var" for "sourceResult" in the expression "bodyResult" 
     *  This method is used for iterators such as isUnique() and collect()
     */    
    private String replaceVarInBodyResult (String var, String bodyResult, String sourceResult)
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
	
	protected String visitTypeName(Type type)
	{
		StringBuilder localResult = new StringBuilder();
		EClass eType = (EClass)getEcoreOfPivot(type);
		RefOntoUML.Type ontoType = (RefOntoUML.Type)getOntoUMLOfEcore(eType);
		String aliasOntoType = oclparser.getOntoUMLParser().getAlias(ontoType);		
		if (ontoType instanceof RefOntoUML.DataType) localResult.append(aliasOntoType);
		else localResult.append("w."+aliasOntoType);
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
	
	protected String visitOclIsTypeOf (OCLExpression srcExpression, OCLExpression argExpression)
	{
		StringBuilder localResult = new StringBuilder();
		String sourceResult = safeVisit(srcExpression);
		String argument = safeVisit(argExpression);
		String firstPart = "("+sourceResult + " in " + argument+")";
		String secondPart = new String();
		if (argument.contains("w.")) argument = argument.replace("w.", "");				
		ArrayList<String> subtypes = getSubTypeList(argument);
		if (subtypes.size()>0) 
		{
			secondPart = " and # "+ sourceResult + " & (";				
			int i = 1;
			for(String subtype: subtypes) { if (i<subtypes.size()) secondPart += "w."+subtype+" + "; else if (i==subtypes.size()) secondPart += "w."+subtype; i++;}
			secondPart += ") = 0";
		}
		localResult.append("("+firstPart + secondPart+")");
		return localResult.toString();
	}
	
	public String visitContext (EObject context, String stereotype)
	{
		StringBuilder localResult = new StringBuilder();
		EModelElement eContext = getEcoreOfPivot((Element)context);
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
		    		src_type = (RefOntoUML.Type)ontoProperty.eContainer();
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
		OCLExpression oclExpression = ((ExpressionInOCL)constraint.getSpecification()).getBodyExpression();				
		List<? extends EObject> constrained = constraint.getConstrainedElement();
		EObject context = constrained.get(0);				
		String stereotype = PivotOCLUtil.getCustomStereotype(constraint);		
		constraint_count++;
				
		if (UMLReflection.INVARIANT.equals(stereotype) || UMLReflection.DERIVATION.equals(stereotype))
		{
			if(opt.ctype.equals(PivotPrettyPrintAlloyOption.ConstraintType.CHECK)) localResult.append("assert ");		
			else if(opt.ctype.equals(PivotPrettyPrintAlloyOption.ConstraintType.RUN)) localResult.append("pred ");		
			else if(opt.ctype.equals(PivotPrettyPrintAlloyOption.ConstraintType.FACT)) localResult.append("fact ");			
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
		    if(opt.ctype.equals(PivotPrettyPrintAlloyOption.ConstraintType.CHECK)) localResult.append("check ");		
			else if(opt.ctype.equals(PivotPrettyPrintAlloyOption.ConstraintType.RUN)) localResult.append("run ");		    
		    if (!opt.ctype.equals(PivotPrettyPrintAlloyOption.ConstraintType.FACT))
		    {
		    	localResult.append(visitConstraintName(constraint));
		    	localResult.append(" for "+opt.global_scope+" but "+opt.world_scope+" World, 7 Int\n\n");
		    }
		}
		
		if (UMLReflection.DEFINITION.equals(stereotype)) throw new PivotConstraintException(stereotype,"No support for Definition Constraints in the mapping.");		
		if (UMLReflection.INITIAL.equals(stereotype)) throw new PivotConstraintException(stereotype,"There is no need of Initial Constraints in OntoUML");		
		if (UMLReflection.BODY.equals(stereotype)) throw new PivotConstraintException(stereotype,"Operations do not exist in OntoUML");
		if (UMLReflection.PRECONDITION.equals(stereotype)) throw new PivotConstraintException(stereotype,"Pre Conditions and Operations do not exist in OntoUML"); 
		if (UMLReflection.POSTCONDITION.equals(stereotype)) throw new PivotConstraintException(stereotype,"Post Conditions and Operations do not exist in OntoUML"); 
		
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
		return NULL_PLACEHOLDER;  
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
		throw new PivotLiteralException("Invalid","There is no invalid value in Alloy");	   
	}
	
	@Override
	public String visitInvalidType(@NonNull InvalidType object)  
	{		
		throw new PivotTypeException("Invalid","There is no Invalid Type in Alloy");
	}
	
	@Override
	public String visitIterateExp(@NonNull IterateExp callExp)  
	{		
		throw new PivotOperationException("iterate()","It is not possible to literally iterate over collections. Instead, use the OCL predefined iterators such as forAll(), exists(), etc.");	
	}
	
	@Override
	public String visitIteration(@NonNull Iteration iteration)  
	{		
		return NULL_PLACEHOLDER;  
	}
	
	@Override
	public String visitIteratorExp(@NonNull IteratorExp callExp)  
	{
		StringBuilder localResult = new StringBuilder(); 
		OCLExpression srcExpression = callExp.getSource();
		Iteration iteration = callExp.getReferredIteration();
		String iterExpName = iteration.getName();
		OCLExpression bodyExpression = callExp.getBody();
		String srcResult = safeVisit(srcExpression);
		String bodyResult = safeVisit(bodyExpression);
		
		localResult.append("(");
		if(iterExpName.equals("forAll")) localResult.append("all ");        
        if(iterExpName.equals("exists")) localResult.append("some ");        
        if(iterExpName.equals("one")) localResult.append("#{ ");        
        if(iterExpName.equals("select")) localResult.append("{ ");        
        if(iterExpName.equals("reject")) localResult.append("{ ");              
        if(iterExpName.equals("isUnique")) localResult.append("all disj ");  
        
		boolean isFirst = true;
		String varTemp = new String();
		for (Variable variable : callExp.getIterator()) 
		{
			if (!isFirst) {	localResult.append(", "); }                        
            if(iterExpName.equals("forAll")) localResult.append(safeVisit(variable));        	
        	if(iterExpName.equals("exists")) localResult.append(safeVisit(variable));        	
        	if(iterExpName.equals("one")) localResult.append(safeVisit(variable));        	
        	if(iterExpName.equals("select")) localResult.append(safeVisit(variable));        	
            if(iterExpName.equals("reject")) localResult.append(safeVisit(variable));
            if(iterExpName.equals("collect")) varTemp = safeVisit(variable);                        
            if(iterExpName.equals("isUnique"))
            { 
            	varTemp = safeVisit(variable); localResult.append(varTemp).append(",").append(varTemp).append("'"); 
            }
			isFirst = false;
		}
		
		if(iterExpName.equals("forAll")) 
		{ 
			localResult.append(": "); 
			localResult.append(srcResult); 
			localResult.append(" | "); 
			localResult.append(bodyResult);  
		}        
        if(iterExpName.equals("exists")) 
        { 
        	localResult.append(": "); 
        	localResult.append(srcResult); 
        	localResult.append(" | "); 
        	localResult.append(bodyResult); 
        }        
        if(iterExpName.equals("one")) 
        { 
        	localResult.append(": "); 
        	localResult.append(srcResult); 
        	localResult.append(" | "); 
        	localResult.append(bodyResult); 
        	localResult.append(" } = 1"); 
        }        
        if(iterExpName.equals("reject")) 
        { 
        	localResult.append(": "); 
        	localResult.append(srcResult); 
        	localResult.append(" | "); 
        	localResult.append("not "); 
        	localResult.append(bodyResult); 
        	localResult.append(" }"); 
        }        
        if(iterExpName.equals("select")) 
        { 
        	localResult.append(": "); 
        	localResult.append(srcResult); 
        	localResult.append(" | "); 
        	localResult.append(bodyResult); 
        	localResult.append(" }"); 
        }         
        if(iterExpName.equals("collect")) 
        {        	
        	//replace variable "x" in expression "bodyResult" for "sourceResult"
        	String subsExpression = replaceVarInBodyResult(varTemp,bodyResult,srcResult);          	
        	localResult.append(subsExpression);        	
        }
        
        if(iterExpName.equals("isUnique")) 
        {
        	localResult.append(": ").append(srcResult).append(" | ").append(bodyResult).append(" != ");
        	//replace variable "x" in expression "bodyResult" for "x'"
        	String sb = replaceVarInBodyResult(varTemp,bodyResult,varTemp+"'");          	
        	localResult.append(sb);        	
        }
        localResult.append(")");		
        
        if(iterExpName.equals("any")) throw new PivotIteratorException("any()","It's not possible to get an specific element in an Alloy Set and return it.");        
        if(iterExpName.equals("sortedBy")) throw new PivotIteratorException("sortedBy()","OrderedSet collections are not supported in Alloy.");        
        if(iterExpName.equals("collectNested")) throw new PivotIteratorException("collectNested()","No suuport for nested colletion and Bags in Alloy. Use iterator collect() instead.");
        if(iterExpName.equals("closure")) throw new PivotIteratorException("closure()","There is no mapping of this iterator to Alloy. Need to be done.");
        
        return localResult.toString();
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
			if(operName.equals("oclAsType")) { return sourceResult; }			
			if(operName.equals("oclIsTypeOf"))	{  return visitOclIsTypeOf(srcExpression, argumentExpression); }
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
        
        if(operName.equals("/")) throw new PivotOperationException("/","In Alloy, there is only support for integer operations such as +, -, *, max(), min() and abs().");        
        if(operName.equals("div")) throw new PivotOperationException("div","In Alloy, there is only support for integer operations such as +, -, *, max(), min(), abs().");        
        if(operName.equals("mod")) throw new PivotOperationException("mod","In Alloy, there is only support for integer operations such as +, -, *, max(), min(), abs().");        
        if(operName.equals("toString")) throw new PivotOperationException("toString()","There is no support for the type String in Alloy");        
        if(operName.equals("asOrderedSet")) throw new PivotOperationException("asOrderedSet()","OrderedSet collections are not supported in Alloy.");        
        if(operName.equals("asSequence")) throw new PivotOperationException("asSequence()","Sequence collections are not supported in Alloy.");        
        if(operName.equals("asBag")) throw new PivotOperationException("asBag()","Bag collections are not supported in Alloy.");        
        if(operName.equals("oclIsInState")) throw new PivotOperationException("oclIsInState()","There is no state machine in OntoUML.");        
        if(operName.equals("oclIsNew")) throw new PivotOperationException("oclIsNew()","Post conditions and Operations don't exist in OntoUML.");        
        if(operName.equals("flatten")) throw new PivotOperationException("flatten()","Alloy does not support nested collections (sets), or in other words, high order relations.");        
        if(operName.equals("oclIsInvalid"))	throw new PivotOperationException("oclIsInvalid()","No support in Alloy for the type Invalid.");        
        if(operName.equals("count")) throw new PivotOperationException("count()","It is not possible to iterate over collections. Since all collections are Set, this would always return 1.");  
        
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
		throw new PivotLiteralException("Real","There is no real numbers (double, float, etc.) in Alloy. Only integers.");
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
		throw new PivotLiteralException("String","There is no string value in Alloy");
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
		localResult.append(visitTypeName(type));
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
		if (type != null) {
			//localResult.append(" : ");
			//localResult.append(visitTypeName(type));
		}
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
