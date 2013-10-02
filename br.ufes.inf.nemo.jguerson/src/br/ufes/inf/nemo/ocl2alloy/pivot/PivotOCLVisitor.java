package br.ufes.inf.nemo.ocl2alloy.pivot;

import java.io.IOException;
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
import org.eclipse.ocl.examples.pivot.CollectionLiteralPart;
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
import org.eclipse.ocl.examples.pivot.FeatureCallExp;
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
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.PivotConstants;
import org.eclipse.ocl.examples.pivot.PivotPackage;
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
import org.eclipse.ocl.examples.pivot.TemplateableElement;
import org.eclipse.ocl.examples.pivot.TupleLiteralExp;
import org.eclipse.ocl.examples.pivot.TupleLiteralPart;
import org.eclipse.ocl.examples.pivot.TupleType;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.TypeExp;
import org.eclipse.ocl.examples.pivot.TypedElement;
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
	private StringBuilder result = new StringBuilder();	 
	private int constraint_count = 0;	
	private static String NULL_PLACEHOLDER = "<null>";
	
	//=====================================================================================================
	
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
	
	@Override
	public String toString() 
	{ 
		return result.toString(); 
	}
	
	//=====================================================================================================
	
	public String prettyPrintAlloy (Constraint element) 
	{		 
		opt =  new PrettyPrintAlloyOption(PrettyPrintAlloyOption.ConstraintType.FACT,10,1);
		return prettyPrintAlloy(element,opt);
	}
	
	public String prettyPrintAlloy (Constraint element, PrettyPrintAlloyOption option) 
	{
		this.opt = option;
		this.result = new StringBuilder(); 
        try {
        	safeVisit(element);
            return this.toString();
        } catch (Exception e) {
        	e.printStackTrace();
        	return toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
        }
	}	
	
	public String prettyPrintAlloy (ArrayList<Constraint> constraints, ArrayList<PrettyPrintAlloyOption> options) throws Exception
	{
		if(constraints.size() > options.size()) throw new Exception("prettyPrintAlloy() : the number of constraints is greater than the number of options.");
		try {			
			int i=0;
			for(Constraint ct: constraints) { this.opt = options.get(i); safeVisit(ct); i++; }			
			return this.toString();			
		} catch (Exception e) {
        	e.printStackTrace();
        	return toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
        }		
	}

	//=====================================================================================================
	
	/** Get Ecore element of Pivot */
	public EModelElement getEcoreOfPivot (org.eclipse.ocl.examples.pivot.Element pivotElem)
	{
		return oclparser.getMetamodelManager().getEcoreOfPivot(EModelElement.class, pivotElem);
	}
	
	/** Get OntoUML element of Ecore */
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
	
	//=====================================================================================================
	
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
	
	//=====================================================================================================
	
	protected void append(Number number)
	{
		if (number != null) result.append(number.toString());
		else result.append(NULL_PLACEHOLDER);		
	}
	
	protected void append(String string) 
	{
		if (string != null) result.append(string);
		else result.append(NULL_PLACEHOLDER);		
	}
	
	protected void appendName(NamedElement object) 
	{
		if (object == null) result.append(NULL_PLACEHOLDER);
		else result.append(object.getName());		
	}	
					 	
	protected void appendElementType(@Nullable TypedElement typedElement) 
	{
		if (typedElement == null) append(NULL_PLACEHOLDER);
		else safeVisit(typedElement.getType());		
	}
	
	protected void appendAtPre(FeatureCallExp mpc) {
		if (mpc.isPre()) {
			append("@pre"); //$NON-NLS-1$
		}
	}
	
	protected void appendType(Type type) {
		if ((type != null)
				 && (type.eClass() == PivotPackage.Literals.CLASS)	// i.e. by pass AnyType, PrimitiveType, ...
				 && (type.eContainer() instanceof NamedElement)) {
			appendQualifiedName((NamedElement) type.eContainer());
			append("::");
		}
		appendName(type);
	}

	protected void appendQualifiedName(NamedElement parent, String separator, NamedElement child) {
		if (parent != null) {
			appendQualifiedName(parent);
			append(separator);
		}
		appendName(child);
	}
	
	protected void appendQualifiedName(@Nullable NamedElement object) 
	{
		if (object == null) result.append(NULL_PLACEHOLDER);		
		else {
			EObject container = object.eContainer();
			if (!(container instanceof Root) && (container instanceof NamedElement) &&
					(!(container.eContainer() instanceof Root) || !PivotConstants.OCL_NAME.equals(((NamedElement)container).getName()))) {
				appendQualifiedName((NamedElement) container);
				append("::"); 
			}
			appendName(object);
			if (object instanceof TemplateableElement) {
				TemplateableElement templateableElement = (TemplateableElement) object;
				appendTemplateBindings(templateableElement.getTemplateBinding());
				appendTemplateSignature(templateableElement.getOwnedTemplateSignature());
			}
		}
	}
	
	protected void appendTemplateBindings(List<TemplateBinding> templateBindings) 
	{
		if (templateBindings.size() > 0) {
			append("(");
			String prefix = ""; 
			for (TemplateBinding templateBinding : templateBindings) {
				for (TemplateParameterSubstitution templateParameterSubstitution : templateBinding.getParameterSubstitution()) {
					append(prefix);
					safeVisit(templateParameterSubstitution.getActual());
					prefix = ",";
				}
			}
			append(")");
		}
	}
	
	protected void appendTemplateSignature(TemplateSignature templateSignature) 
	{
		if (templateSignature != null) {
			List<TemplateParameter> templateParameters = templateSignature.getOwnedParameter();
			if (!templateParameters.isEmpty()) {
				append("(");
				String prefix = ""; 
				for (TemplateParameter templateParameter : templateParameters) {
					append(prefix);
					safeVisit(templateParameter.getParameteredElement());
					prefix = ",";
				}
				append(")");
			}
		}
	}
	
	public void appendConstraintName (Constraint constraint)
	{
		if (constraint.getName()==null || constraint.getName().isEmpty())
		{			
			constraint.setName("constraint"+constraint_count);
			result.append("constraint"+constraint_count);			
		}else 
			result.append(constraint.getName());		
	}
	
	public void appendContext (Element context, String stereotype)
	{
		EModelElement eContext = getEcoreOfPivot(context);
		RefOntoUML.Element ontoContext = getOntoUMLOfEcore(eContext);
		String aliasContext = oclparser.getOntoUMLParser().getAlias(ontoContext);
		
		if (UMLReflection.INVARIANT.equals(stereotype)) 
		{						
			if (context instanceof Type)
			{
				result.append("all self: ");				
				if (ontoContext instanceof RefOntoUML.DataType) result.append(aliasContext+" | "); 
				else result.append("w."+aliasContext+" | ");
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
		    	if(typename.equals(src_name)) result.append("w."+tgt_name+" | ");                	
		    	if(typename.equals(tgt_name)) result.append("w."+src_name+" | ");
				                	
		    	if(ontoProperty.getAssociation()!=null) result.append("self."+aliasContext+"[w] = ");
		    	else result.append("self.(w."+aliasContext+") = ");
			}
		}		
	}
	
	//=====================================================================================================
	
	@Override
	public String visitConstraint (Constraint constraint)
	{
		Element context = constraint.getContext();
		OCLExpression oclExpression = ((ExpressionInOCL)constraint.getSpecification()).getBodyExpression();		
		String stereotype = PivotUtil.getStereotype(constraint); 				
		constraint_count++;
		
		if (UMLReflection.INVARIANT.equals(stereotype) || UMLReflection.DERIVATION.equals(stereotype))
		{
			if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.CHECK)) result.append("assert ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.RUN)) result.append("pred ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.FACT)) result.append("fact ");			
			appendConstraintName(constraint);
			result.append(" {\n").append("\tall w: World | ");						
			if (oclExpression.toString().contains("self")) 
			{
				if (context instanceof Type) appendContext(context,stereotype);
				else if (context instanceof Operation) appendContext(context,stereotype);
				else if (context instanceof Property) appendContext(context,stereotype);
			}								
			result.append("\n\t");			
			safeVisit(constraint.getSpecification());			
		    result.append("\n}\n\n");
		    if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.CHECK)) result.append("check ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.RUN)) result.append("run ");		    
		    if (!opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.FACT))
		    {
		    	appendConstraintName(constraint);
		    	result.append(" for "+opt.global_scope+" but "+opt.world_scope+" World, 7 Int\n\n");
		    }
		}
		if (UMLReflection.DEFINITION.equals(stereotype));		
		if (UMLReflection.INITIAL.equals(stereotype));		
		if (UMLReflection.BODY.equals(stereotype));
		if (UMLReflection.PRECONDITION.equals(stereotype)); 
		if (UMLReflection.POSTCONDITION.equals(stereotype)); 
		
		return null; 
	}	
		
   	@Override
	public String visitAnyType(@NonNull AnyType object) 
   	{   		
   		return null; 
   	}
   	
	@Override
	public String visitAssociationClassCallExp(@NonNull AssociationClassCallExp ac) 
	{		
		return null;
	}
	
	@Override
	public String visitBooleanLiteralExp(@NonNull BooleanLiteralExp bl) 
	{
		if (Boolean.toString(bl.isBooleanSymbol()).equals("true")) append("no none");
		if (Boolean.toString(bl.isBooleanSymbol()).equals("false")) append("some none");
		return null; 
	}
	
	@Override
	public String visitClass(@NonNull org.eclipse.ocl.examples.pivot.Class cls) 
	{		
		EClass eClass = (EClass)getEcoreOfPivot(cls);
		RefOntoUML.Class ontoClass = (RefOntoUML.Class)getOntoUMLOfEcore(eClass);
		String aliasOntoClass = oclparser.getOntoUMLParser().getAlias(ontoClass);		
		append(aliasOntoClass);			
		return null; 
	}
	
	@Override
	public String visitCollectionItem(@NonNull CollectionItem item) 
	{
		safeVisit(item.getItem());  
		return null; 
	}
	
	//=====================================================================================================
	
	@Override
	public String visitCollectionLiteralExp(@NonNull CollectionLiteralExp cl) 
	{
		switch (cl.getKind()) {
			case SET :
				append("Set {");//$NON-NLS-1$
				break;
			case ORDERED_SET :
				append("OrderedSet {");//$NON-NLS-1$
				break;
			case BAG :
				append("Bag {");//$NON-NLS-1$
				break;
			case SEQUENCE :
				append("Sequence {");//$NON-NLS-1$
				break;
			default :
				append("Collection {");//$NON-NLS-1$
				break;
		}
        boolean isFirst = true;
		for (CollectionLiteralPart part : cl.getPart()) {
			if (!isFirst) append(", ");			
            safeVisit(part);
			isFirst = false;
		}
		append("}");
		return null; 
	}	
	
	@Override
	public String visitCollectionRange(@NonNull CollectionRange range) 
	{		
		return null; 
	}
		
	@Override
	public String visitCollectionType(@NonNull CollectionType object) 
	{		
		return null; 
	}
	
	@Override
	public String visitConstructorExp(@NonNull ConstructorExp constructorExp) 
	{		
		return null; 
	}
	
	@Override
	public String visitConstructorPart(@NonNull ConstructorPart part) 
	{		
		return null; 
	}
	
	@Override
	public String visitElementExtension(@NonNull ElementExtension as) 
	{		
		return null; 
	}
	
	@Override
	public String visitEnumLiteralExp(@NonNull EnumLiteralExp el) 
	{
		appendQualifiedName(el.getReferredEnumLiteral());
		return null; 
	}	
	
	@Override
	public String visitEnumerationLiteral(@NonNull EnumerationLiteral el) 
	{
		appendQualifiedName(el.getEnumeration(), "::", el);
		return null; 
	}	
	
	@Override
	public String visitExpressionInOCL(@NonNull ExpressionInOCL expression) 
	{
		OCLExpression bodyExpression = expression.getBodyExpression();
		if (bodyExpression != null) return safeVisit(bodyExpression);
		else return PivotUtil.getBody(expression);		
	}
	
	@Override 
	public String visitIfExp(@NonNull IfExp ifExp) 
	{
		OCLExpression condExpression = ifExp.getCondition();
		OCLExpression thenExpression = ifExp.getThenExpression();
		OCLExpression elseExpression = ifExp.getElseExpression();				
		safeVisit(condExpression); append(" implies "); safeVisit(thenExpression); append(" else "); safeVisit(elseExpression);		
		return null; 
	}
	
	@Override 
	public @Nullable String visitImport(@NonNull Import object) 
	{		
		return null; 
	}
	
	@Override
	public String visitIntegerLiteralExp(@NonNull IntegerLiteralExp il) 
	{
		append(il.getIntegerSymbol());
		return null; 
	}
	
	@Override
	public String visitInvalidLiteralExp(@NonNull InvalidLiteralExp il) 
	{		
		return null; 
	}
	
	@Override
	public String visitInvalidType(@NonNull InvalidType object)  
	{		
		return null; 
	}
	
	@Override
	public String visitIterateExp(@NonNull IterateExp callExp)  
	{		
		return null; 
	}
	
	@Override
	public String visitIteration(@NonNull Iteration iteration)  
	{
		appendQualifiedName(iteration.getOwningType(), ".", iteration);
		appendTemplateBindings(iteration.getTemplateBinding());
		appendTemplateSignature(iteration.getOwnedTemplateSignature());
		append("(");
		boolean isFirst = true;
		for (Parameter parameter : iteration.getOwnedIterator()) {
			if (!isFirst) {
				append(", ");
			}
			appendElementType(parameter);
			isFirst = false;
		}
		isFirst = true;
		for (Parameter accumulator : iteration.getOwnedAccumulator()) {
			if (!isFirst) {
				append(", ");
			}
			else {
				append("; ");
			}
			appendElementType(accumulator);
			isFirst = false;
		}
		isFirst = true;
		for (Parameter parameter : iteration.getOwnedParameter()) {
			if (!isFirst) {
				append(", ");
			}
			else {
				append(" | ");
			}
			appendElementType(parameter);
			isFirst = false;
		}
		append(") : ");
		appendElementType(iteration);
		return null; 
	}
	
	@Override
	public String visitIteratorExp(@NonNull IteratorExp callExp)  
	{
		OCLExpression srcExpression = callExp.getSource();
		Iteration iteration = callExp.getReferredIteration();
		OCLExpression bodyExpression = callExp.getBody();
		safeVisit(srcExpression);
		
		
		
		
		append("->");
		appendName(iteration);
		append("("); //$NON-NLS-1$
		boolean isFirst = true;
		for (Variable variable : callExp.getIterator()) {
			if (!isFirst) {
				append(", ");
			}
            safeVisit(variable);
			isFirst = false;
		}
		append(" | ");
		safeVisit(bodyExpression);
		append(")");
		return null; 
	}
	@Override
	public String visitLambdaType(@NonNull LambdaType lambda)  
	{		
		return null; 
	}
	
	@Override
	public String visitLetExp(@NonNull LetExp letExp)  
	{
		Variable var = letExp.getVariable();
		OCLExpression inExpression = letExp.getIn();		
		append("let ");  safeVisit(var); append(" | "); safeVisit(inExpression);
		return null; 
	}
	
	@Override
	public String visitMessageExp(@NonNull MessageExp messageExp)  
	{		
		return null; 
	}
	
	@Override
	public String visitMetaclass(@NonNull Metaclass object)  
	{		
		return null; 
	}
	
	@Override
	public String visitNullLiteralExp(@NonNull NullLiteralExp il)  
	{
		append("none");
		return null; 
	}
	
	@Override
	public String visitOpaqueExpression(@NonNull OpaqueExpression object)  
	{ 
		String body = PivotUtil.getBody(object);
		if (body != null) append(body);		
		return null; 
	}
	
	@Override
	public String visitOperation(@NonNull Operation operation)  
	{		
		return null; 
	}
	
	@Override
	public String visitOperationCallExp(@NonNull OperationCallExp oc)  
	{ 		
		Operation oper = oc.getReferredOperation(); 
		String operTypeResult = oper.getType().getName();
		String operName = oper.getName();
		OCLExpression srcExpression = oc.getSource();		
		List<OCLExpression> arguments = oc.getArgument();
		    	
		if(operName.equals("allInstances")) { safeVisit(srcExpression); return null; }		
		if(operName.equals("size")) {  append("(#"); safeVisit(srcExpression); append(")"); return null; }		
		if(operName.equals("isEmpty")) {  append("(no "); safeVisit(srcExpression); append(")"); return null; }		
		if(operName.equals("notEmpty")) {  append("(some "); safeVisit(srcExpression); append(")"); return null; }		
		if(operName.equals("not")) {  append("(not "); safeVisit(srcExpression); append(")"); return null; }			
		if(operName.equals("oclIsUndefined")) { append("(#"); safeVisit(srcExpression); append(" = 0)"); return null; }		
		if(operName.equals("abs")) {  append("abs["); safeVisit(srcExpression); append("]"); return null; }		
        if(operName.equals("sum")) {  append("(sum "); safeVisit(srcExpression); append(")"); return null; }        
        if(operName.equals("asSet")) {  safeVisit(srcExpression); return null; }        
		if(operName.equals("oclAsType")) { safeVisit(srcExpression); return null; }
		
		if(operName.equals("-")) 
		{
			java.util.Iterator<OCLExpression> iter = arguments.iterator();			
			if(!iter.hasNext()) { append("negate["); safeVisit(srcExpression); append("]"); return null; }			
			else if( iter.hasNext() && operTypeResult.equals("Set(T)")) { append("("); safeVisit(srcExpression); append(" - "); safeVisit(iter.next()); append(")"); return null; }			
			else if( iter.hasNext() && operTypeResult.equals("Real")){ append("("); safeVisit(srcExpression); append(").minus["); safeVisit(iter.next()); append("]"); return null; }
		}
		
        for (java.util.Iterator<OCLExpression> iter = arguments.iterator(); iter.hasNext();) 
        {
        	OCLExpression argumentExpression = iter.next();        	
        	
			if(operName.equals("intersection")) { append("("); safeVisit(srcExpression); append(" & "); safeVisit(argumentExpression); append(")"); return null; }				
			if(operName.equals("union")) { append("("); safeVisit(srcExpression); append(" + "); safeVisit(argumentExpression); append(")"); return null; }				
			if(operName.equals("including")) { append("("); safeVisit(srcExpression); append(" + "); safeVisit(argumentExpression); append(")"); return null; }			
			if(operName.equals("excluding")) { append("("); safeVisit(srcExpression); append(" - "); safeVisit(argumentExpression); append(")"); return null; }			
			if(operName.equals("includes")) { append("("); safeVisit(argumentExpression);  append(" in "); safeVisit(srcExpression); append(")"); return null; }			
			if(operName.equals("excludes")) { append("("); safeVisit(argumentExpression); append(" !in "); safeVisit(srcExpression); append(")"); return null; }				
			if(operName.equals("includesAll")) { append("("); safeVisit(argumentExpression); append(" in "); safeVisit(srcExpression); append(")"); return null; }			
			if(operName.equals("excludesAll")) { append("(#"); safeVisit(argumentExpression); append(" & "); safeVisit(srcExpression); append(" = 0)"); return null; }			
			if(operName.equals("product")) { append("("); safeVisit(srcExpression); append(" -> "); safeVisit(argumentExpression); append(")"); return null; }			
			if(operName.equals("=")) { append("("); safeVisit(srcExpression); append(" = "); safeVisit(argumentExpression); append(")"); return null; }				
			if(operName.equals("<>")) { append("("); safeVisit(srcExpression); append(" != "); safeVisit(argumentExpression); append(")"); return null; }				
			if(operName.equals("oclIsKindOf"))	{ append("("); safeVisit(srcExpression); append(" in "); safeVisit(argumentExpression); append(")"); return null; }			
			if(operName.equals("oclAsType")) { safeVisit(srcExpression); return null; }			
			
			//if(operName.equals("oclIsTypeOf"))	{  result.append(generateOclIsTypeOfMapping(srcResult,argument)); }
							
			if(operName.equals("<")) { append("("); safeVisit(srcExpression); append(" < "); safeVisit(argumentExpression); append(")"); return null; }				
			if(operName.equals(">")) { append("("); safeVisit(srcExpression); append(" > "); safeVisit(argumentExpression); append(")"); return null; }				
			if(operName.equals("<=")) { append("("); safeVisit(srcExpression); append(" <= "); safeVisit(argumentExpression); append(")"); return null; }			
			if(operName.equals(">=")) { append("("); safeVisit(srcExpression); append(" >= "); safeVisit(argumentExpression); append(")"); return null; }			
			if(operName.equals("and")) { append("("); safeVisit(srcExpression); append(" and "); safeVisit(argumentExpression); append(")"); return null; }			
			if(operName.equals("or")) { append("("); safeVisit(srcExpression); append(" or "); safeVisit(argumentExpression); append(")"); return null; }				
			if(operName.equals("implies")) { append("("); safeVisit(srcExpression); append(" implies "); safeVisit(argumentExpression); append(")"); return null; }			
			if(operName.equals("xor")) { append("(("); safeVisit(srcExpression); append(" or "); safeVisit(argumentExpression); append(") and not ("); safeVisit(srcExpression); append(" and "); safeVisit(argumentExpression); append("))"); return null; }			
			if(operName.equals("max")) { append("max["); safeVisit(srcExpression); append(","); safeVisit(argumentExpression); append("]"); return null; }			
			if(operName.equals("min")) { append("min["); safeVisit(srcExpression); append(","); safeVisit(argumentExpression); append("]"); return null; }			
			if(operName.equals("+")) { append("("); safeVisit(srcExpression); append(").plus["); safeVisit(argumentExpression); append("]"); return null; }			
			if(operName.equals("*")) { append("("); safeVisit(srcExpression); append(").mul["); safeVisit(argumentExpression); append("]"); return null; }			
			if(operName.equals("symmetricDifference")) { append("(("); safeVisit(srcExpression); append(" + "); safeVisit(argumentExpression); append(") - ("); safeVisit(srcExpression); append(" & "); safeVisit(argumentExpression); append("))"); return null; }	        	
						
			if (iter.hasNext()) ; // no more arguments 
        }		
        
		return null; 
	}	
	
	@Override
	public String visitPackage(@NonNull org.eclipse.ocl.examples.pivot.Package pkg)  
	{		
		return null; 
	}
	
	@Override
	public String visitParameter(@NonNull Parameter parameter)  
	{		
		return null; 
	}
	
	@Override
	public String visitPrecedence(@NonNull Precedence precedence)  
	{		
		return null; 
	}
	
	@Override
	public String visitPrimitiveType(@NonNull PrimitiveType object)  
	{	
		return null; 
	}
	
	@Override
	public String visitProperty(@NonNull Property property)  
	{ 
		appendQualifiedName(property.getOwningType(), ".", property);
		return null; 
	}
	
	@Override
	public String visitPropertyCallExp(@NonNull PropertyCallExp pc)  
	{
		OCLExpression scrExpression = pc.getSource();
		Property property = pc.getReferredProperty();		
		EStructuralFeature eStructFeature = (EStructuralFeature) getEcoreOfPivot(property);
		RefOntoUML.Property ontoProperty = (RefOntoUML.Property)getOntoUMLOfEcore(eStructFeature);
		String aliasOntoProperty = oclparser.getOntoUMLParser().getAlias(ontoProperty);		
		if (eStructFeature instanceof EAttribute)
		{
			if (eStructFeature.getEType().isInstance(EcorePackage.eINSTANCE.getEBoolean())) { safeVisit(scrExpression); append("in w." + aliasOntoProperty); return null; } 
			else { safeVisit(scrExpression); append(".(w."+aliasOntoProperty+")"); return null; }
		} 
		else if (eStructFeature instanceof EReference) { safeVisit(scrExpression); append("."+aliasOntoProperty+"[w]"); return null; }
		
		List<OCLExpression> qualifiers = pc.getQualifier();
		if (!qualifiers.isEmpty()) for (@SuppressWarnings("unused") OCLExpression qualifier : qualifiers) { }		
		return null; 
	}
	
	@Override
	public String visitRealLiteralExp(@NonNull RealLiteralExp rl)  
	{		
		return null; 
	}
	
	@Override
	public String visitRoot(@NonNull Root root)  
	{		
		return null; 
	}
	
	@Override
	public String visitStateExp(@NonNull StateExp s)  
	{
		return null; 
	}
	
	@Override
	public String visitStringLiteralExp(@NonNull StringLiteralExp sl)  
	{
		return null; 
	}
	
	@Override
	public String visitTemplateBinding(@NonNull TemplateBinding object)  
	{		
		return null; 
	}
	
	@Override
	public String visitTemplateParameter(@NonNull TemplateParameter object)  
	{		
		return null; 
	}
	
	@Override
	public String visitTemplateParameterSubstitution(@NonNull TemplateParameterSubstitution object)  
	{		
		return null; 
	}
	
	@Override
	public String visitTemplateSignature(@NonNull TemplateSignature object)  
	{		
		return null; 
	}
	
	@Override
	public String visitTupleLiteralExp(@NonNull TupleLiteralExp literalExp)  
	{		
		return null; 
	}
	
	@Override
	public String visitTupleLiteralPart(@NonNull TupleLiteralPart part)  
	{		
		return null; 
	}
	
	@Override
	public String visitTupleType(@NonNull TupleType object)  
	{		
		return null; 
	}
	
	@Override
	public String visitTypeExp(@NonNull TypeExp t) 
	{
		Type type = t.getReferredType();
		EClass eType = (EClass)getEcoreOfPivot(type);
		RefOntoUML.Type ontoType = (RefOntoUML.Type)getOntoUMLOfEcore(eType);
		String aliasOntoType = oclparser.getOntoUMLParser().getAlias(ontoType);		
		append(aliasOntoType);		
		return null; 
	}
	
	@Override
	public String visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp unl) 
	{
		append(unl.getUnlimitedNaturalSymbol());
		return null; 
	}
	
	@Override
	public String visitUnspecifiedType(@NonNull UnspecifiedType object) 
	{		
		return null; 
	}
	
	@Override
	public String visitUnspecifiedValueExp(@NonNull UnspecifiedValueExp uv) 
	{		
		return null; 
	}
	
	@Override
	public String visitVariable(@NonNull Variable variable) 
	{
		appendName(variable);
		Type type = variable.getType();
		if (type != null) {
			append(" : ");
			appendElementType(variable);
		}
		OCLExpression initExpression = variable.getInitExpression();
		if (initExpression != null) {
			append(" = ");
			safeVisit(initExpression);
		}		
		return null; 
	}
	
	@Override
	public String visitVariableExp(@NonNull VariableExp v) 
	{
		appendName(v.getReferredVariable());
		return null; 
	}
	
	@Override
	public String visitVoidType(@NonNull VoidType object) 
	{		
		return null; 
	}
	
	@Override
	public String visiting(@NonNull Visitable visitable) 
	{		
		return null;
	}
		
    /** Used for a Test */	
	public static void main (String[]args)
	 {		 				
//		 String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.ocl";
//		 String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.refontouml";
		 
		 String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\project.ocl";
		 String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\project.refontouml";
		 
		 String tempPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\temp\\";
		 
//		 String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.ocl";
//		 String refPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.refontouml";
		 
//		 String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\project.ocl";
//		 String refPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\project.refontouml";
		 
//		 String tempPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\temp\\";
			
		 try {
			
			PivotOCLParser parser = new PivotOCLParser(refPath,tempPath);
			String oclContent = PivotOCL2AlloyUtil.readFile(oclPath);
			parser.parse(oclContent);
			
			System.out.println("OCL parsed succesfully.");

			PivotOCLVisitor visitor = new PivotOCLVisitor(parser);
			
			KeplerToStringVisitor keplerVisitor = new KeplerToStringVisitor();			
			keplerVisitor.visitConstraint(parser.getConstraints().get(0));
						
			System.out.println(
				visitor.prettyPrintAlloy(
					parser.getConstraints(),
					PrettyPrintAlloyOption.createListOfOptions(parser.getConstraints().size())
				)
			);
						
			System.out.println("OCL visited succesfully.");
			
		 } catch (IOException e) {
			e.printStackTrace();
			
		 } catch (ParserException e) {
			e.printStackTrace();
		 
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		
		
	}

	}
