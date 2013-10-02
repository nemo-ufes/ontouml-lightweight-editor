package br.ufes.inf.nemo.ocl2alloy.pivot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.domain.utilities.DomainUtil;
import org.eclipse.ocl.examples.domain.values.Unlimited;
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
		
	protected StringBuilder result = new StringBuilder();
	 
	private int constraint_count = 0;
	
	protected static String NULL_PLACEHOLDER = "<null>";
	
	//=====================================================================================================
	
	protected PivotOCLVisitor() { super(Object.class); } 
	
	protected PivotOCLVisitor(PivotOCLParser parser)
	{
		super(Object.class);
		oclparser = parser;	
		opt =  new PrettyPrintAlloyOption(PrettyPrintAlloyOption.ConstraintType.FACT,10,1);
	}	
	
	protected PivotOCLVisitor(PivotOCLParser parser, PrettyPrintAlloyOption option)
	{
		super(Object.class);
		oclparser = parser;	
		opt =  option;
	}
	
	@Override
	public String toString() { return result.toString(); }

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
			for(Constraint ct: constraints) 
			{
				this.opt = options.get(i); 
				safeVisit(ct); 
				i++;
			}			
			return this.toString();			
		} catch (Exception e) {
        	e.printStackTrace();
        	return toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
        }		
	}

	//=====================================================================================================
	
	protected EModelElement getEcoreOfPivot (org.eclipse.ocl.examples.pivot.Element pivotElem)
	{
		return oclparser.getMetamodelManager().getEcoreOfPivot(EModelElement.class, pivotElem);
	}
	
	protected RefOntoUML.Element getOntoUMLOfEcore (EModelElement eElem)
	{
		for (Entry<RefOntoUML.Element,EModelElement> entry : oclparser.getOntoUML2EcoreMap().entrySet()) 
	    {
            if (eElem.equals(entry.getValue())) return entry.getKey();                
            else {            	
        		if (eElem instanceof ENamedElement)
        		{
        			String ecoreName = ((ENamedElement)eElem).getName().trim().toLowerCase();
        			String entryName = ((ENamedElement)entry.getValue()).getName().trim().toLowerCase();
        			if (ecoreName.equals(entryName)) return entry.getKey();
        		}
            }
	    }
		return null;
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
		RefOntoUML.Element ontoContext = getOntoUMLOfPivot(context);
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

	//=====================================================================================================
	
   	@Override
	public String visitAnyType(@NonNull AnyType object) 
   	{
   		//appendName(object);
   		return null; 
   	}
	@Override
	public String visitAssociationClassCallExp(@NonNull AssociationClassCallExp ac) 
	{
		safeVisit(ac.getSource());
		append("."); //$NON-NLS-1$
		appendName(ac.getReferredAssociationClass()); //$NON-NLS-1$
		appendAtPre(ac);
        List<OCLExpression> qualifiers = ac.getQualifier();
		if (!qualifiers.isEmpty()) {
			append("[");
			safeVisit(qualifiers.get(0));
			append("]");
		}
		return null;
	}
	@Override
	public String visitBooleanLiteralExp(@NonNull BooleanLiteralExp bl) 
	{
		append(Boolean.toString(bl.isBooleanSymbol()));
		return null; 
	}
	@Override
	public String visitClass(@NonNull org.eclipse.ocl.examples.pivot.Class cls) 
	{ 
		TemplateParameter owningTemplateParameter = cls.getOwningTemplateParameter();
		if (owningTemplateParameter != null) {
			appendName(cls);
		}
		else {
			org.eclipse.ocl.examples.pivot.Package pkg = cls.getPackage();
			if (pkg == null) {
				append("null::");
				appendName(cls);
			}
			else if (!(pkg.eContainer() instanceof Root) || !PivotConstants.OCL_NAME.equals(pkg.getName())) {
				appendQualifiedName(pkg, "::", cls);
			}
			else {
				appendName(cls);
			}
			appendTemplateBindings(cls.getTemplateBinding());
			appendTemplateSignature(cls.getOwnedTemplateSignature());
		}
		return null; 
	}
	@Override
	public String visitCollectionItem(@NonNull CollectionItem item) 
	{
		safeVisit(item.getItem());  
		return null; 
	}
	@Override
	public String visitCollectionLiteralExp(@NonNull CollectionLiteralExp cl) 
	{
		// construct the appropriate collection from the parts
		// based on the collection kind.
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
			if (!isFirst) {
				append(", ");
			}
            safeVisit(part);
			isFirst = false;
		}
		append("}");
		return null; 
	}	
	@Override
	public String visitCollectionRange(@NonNull CollectionRange range) 
	{
		safeVisit(range.getFirst());
        append(" .. ");
        safeVisit(range.getLast());
		return null; 
	}
	@Override
	public String visitCollectionType(@NonNull CollectionType object) 
	{
		appendName(object);
		appendTemplateBindings(object.getTemplateBinding());
		appendTemplateSignature(object.getOwnedTemplateSignature());
		Number lower = object.getLower();
		Number upper = object.getUpper();
		long lowerValue = lower != null ? lower.longValue() : 0l;		// FIXME Handle BigInteger
		long upperValue = (upper != null) && !(upper instanceof Unlimited) ? upper.longValue() : -1l;
		if ((lowerValue != 0) || (upperValue != -1)) {
			DomainUtil.formatMultiplicity(result, lowerValue, upperValue);
		}
		return null; 
	}
	@Override
	public String visitConstructorExp(@NonNull ConstructorExp constructorExp) 
	{
		appendQualifiedName(constructorExp.getType());
		append("{");//$NON-NLS-1$
		String prefix = "";
		for (ConstructorPart part : constructorExp.getPart()) {
			append(prefix);
            safeVisit(part);
			prefix = ", ";//$NON-NLS-1$
		}
		append("}");
		return null; 
	}
	@Override
	public String visitConstructorPart(@NonNull ConstructorPart part) 
	{
		appendName(part.getReferredProperty());
		OCLExpression initExpression = part.getInitExpression();
		if (initExpression != null) {
			append(" = ");
			safeVisit(initExpression);
		}
		return null; 
	}
	@Override
	public String visitElementExtension(@NonNull ElementExtension as) 
	{
		appendQualifiedName(as.getPackage(), "::", as);
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
		if (bodyExpression != null) {
			return safeVisit(bodyExpression);
		}
		else {
			return PivotUtil.getBody(expression);
		}
	}
	@Override 
	public String visitIfExp(@NonNull IfExp ifExp) 
	{
		append("if ");  //$NON-NLS-1$
		safeVisit(ifExp.getCondition());
		append(" then "); //$NON-NLS-1$
		safeVisit(ifExp.getThenExpression());
		append(" else "); //$NON-NLS-1$
		safeVisit(ifExp.getElseExpression());
		append(" endif"); //$NON-NLS-1$
		return null; 
	}
	@Override 
	public @Nullable String visitImport(@NonNull Import object) 
	{
		appendName(object);
		append(" : ");
		appendQualifiedName(object.getImportedNamespace());
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
		append("invalid");
		return null; 
	}
	@Override
	public String visitInvalidType(@NonNull InvalidType object)  
	{
		appendName(object);
		return null; 
	}
	@Override
	public String visitIterateExp(@NonNull IterateExp callExp)  
	{
		safeVisit(callExp.getSource());
		append("->");
		appendName(callExp.getReferredIteration());
		append("("); //$NON-NLS-1$
		boolean isFirst = true;
		for (Variable variable : callExp.getIterator()) {
			if (!isFirst) {
				append(", ");
			}
            safeVisit(variable);
			isFirst = false;
		}
		append("; ");
		safeVisit(callExp.getResult());
		append(" | ");
		safeVisit(callExp.getBody());
		append(")");//$NON-NLS-1$
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
		safeVisit(callExp.getSource());
		append("->");
		appendName(callExp.getReferredIteration());
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
		safeVisit(callExp.getBody());
		append(")");
		return null; 
	}
	@Override
	public String visitLambdaType(@NonNull LambdaType lambda)  
	{
		appendName(lambda);
		Type contextType = lambda.getContextType();
		if (contextType != null) {
			append(" ");
			appendType(contextType);
			appendTemplateSignature(lambda.getOwnedTemplateSignature());
			append("(");
			boolean isFirst = true;
			for (Type parameterType : lambda.getParameterType()) {
				if (!isFirst) {
					append(",");
				}
				appendType(parameterType);
				isFirst = false;
			}
			append(") : ");
			appendType(lambda.getResultType());
		}
		return null; 
	}
	@Override
	public String visitLetExp(@NonNull LetExp letExp)  
	{
		append("let "); //$NON-NLS-1$
		safeVisit(letExp.getVariable());
		append(" in "); //$NON-NLS-1$
		safeVisit(letExp.getIn());
		return null; 
	}
	@Override
	public String visitMessageExp(@NonNull MessageExp messageExp)  
	{
		safeVisit(messageExp.getTarget());
		append((messageExp.getType() instanceof CollectionType) ? "^^" : "^"); //$NON-NLS-1$//$NON-NLS-2$
		if (messageExp.getCalledOperation() != null) {
			appendName(messageExp.getCalledOperation().getOperation());
		} else if (messageExp.getSentSignal() != null) {
			appendName(messageExp.getSentSignal().getSignal());
		}
		append("(");
		String prefix = "";
		for (OCLExpression argument : messageExp.getArgument()) {
			append(prefix);
            safeVisit(argument);
            prefix = ", "; //$NON-NLS-1$
		}
		append(")");
		return null; 
	}
	@Override
	public String visitMetaclass(@NonNull Metaclass object)  
	{
		appendName(object);
		if (object.getTemplateBinding().size() > 0) {
			appendTemplateBindings(object.getTemplateBinding());
		}
		else if (object.getInstanceType() != null) {
			append("<");
			appendQualifiedName(object.getInstanceType());
			append(">");
		}
		appendTemplateSignature(object.getOwnedTemplateSignature());
		return null; 
	}
	@Override
	public String visitNullLiteralExp(@NonNull NullLiteralExp il)  
	{
		append("null");
		return null; 
	}
	@Override
	public String visitOpaqueExpression(@NonNull OpaqueExpression object)  
	{ 
		String body = PivotUtil.getBody(object);
		if (body != null) {
			append(body);
		}
		return null; 
	}
	@Override
	public String visitOperation(@NonNull Operation operation)  
	{
		appendQualifiedName(operation.getOwningType(), ".", operation);
		appendTemplateBindings(operation.getTemplateBinding());
		appendTemplateSignature(operation.getOwnedTemplateSignature());
		append("(");
		boolean isFirst = true;
		for (Parameter parameter : operation.getOwnedParameter()) {
			if (!isFirst) {
				append(",");
			}
			appendElementType(parameter);
			isFirst = false;
		}
		append(") : ");
		appendElementType(operation);
		return null; 
	}
	@Override
	public String visitOperationCallExp(@NonNull OperationCallExp oc)  
	{ 
		OCLExpression source = oc.getSource();
		safeVisit(source);
		Operation oper = oc.getReferredOperation();
		if (oper != null) {
	        Type sourceType = source != null ? source.getType() : null;
			append(sourceType instanceof CollectionType
					? PivotConstants.COLLECTION_NAVIGATION_OPERATOR
					: PivotConstants.OBJECT_NAVIGATION_OPERATOR);
			appendName(oper);
		} else {
			append(PivotConstants.OBJECT_NAVIGATION_OPERATOR);
			appendName(oc);
		}
		append("(");
		String prefix = "";//$NON-NLS-1$
		for (OCLExpression argument : oc.getArgument()) {
			append(prefix);
			safeVisit(argument);
			prefix = ", ";//$NON-NLS-1$
		}
		append(")");
		appendAtPre(oc);
		/*
		Operation oper = operCallExp.getReferredOperation(); 
		String operTypeResult = oper.getType().getName();
		String operName = oper.getName();
		OCLExpression srcExpression = operCallExp.getSource();
		String srcResult = safeVisit(srcExpression);
		List<OCLExpression> arguments = operCallExp.getArgument();
		    	
		if(operName.equals("allInstances")) {append(srcResult); }		
		if(operName.equals("size")) {  append("("+"#" + srcResult+ ")"); }		
		if(operName.equals("isEmpty")) {  append("("+"no " + srcResult + ")"); }		
		if(operName.equals("notEmpty")) {  append("("+"some " + srcResult+ ")"); }		
		if(operName.equals("not")) {  append("("+"not " + srcResult+ ")"); }			
		if(operName.equals("oclIsUndefined")) { append("("+"#" + srcResult + " = 0"+ ")"); }		
		if(operName.equals("abs")) {  append("abs[" + srcResult + "]"); }		
        if(operName.equals("sum")) {  append("(sum " + srcResult + ")"); }        
        if(operName.equals("asSet")) {  append(srcResult); }        
		if(operName.equals("oclAsType")) {  append(srcResult); }
		
		if(operName.equals("-")) 
		{
			java.util.Iterator<OCLExpression> iter = arguments.iterator();			
			if(!iter.hasNext()) { append("negate[" + srcResult +"]"); }			
			else if( iter.hasNext() && operTypeResult.equals("Set(T)")) { append("("+srcResult + " - " + iter.next()+ ")"); }			
			else if( iter.hasNext() && operTypeResult.equals("Real")){ append("("+srcResult+").minus["+iter.next()+"]"); }
		}
		
        for (java.util.Iterator<OCLExpression> iter = arguments.iterator(); iter.hasNext();) 
        {
        	OCLExpression argumentExpression = iter.next();
        	String argument = safeVisit(argumentExpression);
        	
			if(operName.equals("intersection")) { append("("+srcResult + " & " + argument+")"); }				
			if(operName.equals("union")) { append("("+srcResult + " + " + argument+")"); }				
			if(operName.equals("including")) { append("("+srcResult + " + " + argument+")"); }			
			if(operName.equals("excluding")) { append("("+srcResult + " - " + argument+")"); }			
			if(operName.equals("includes")) { append("("+argument + " in " + srcResult+")"); }			
			if(operName.equals("excludes")) { append("("+argument + " !in " + srcResult+")"); }				
			if(operName.equals("includesAll")) { append("("+argument + " in " + srcResult+")"); }			
			if(operName.equals("excludesAll")) { append("("+"#"+argument + " & " + srcResult+" = 0)"); }			
			if(operName.equals("product")) { append("("+srcResult + " -> " + argument+")"); }			
			if(operName.equals("=")) { append("("+srcResult + " = " + argument+")"); }				
			if(operName.equals("<>")) { append("("+srcResult + " != " + argument+")"); }				
			if(operName.equals("oclIsKindOf"))	{ append("("+srcResult + " in " + argument+")"); }			
			if(operName.equals("oclAsType")) { append(""+srcResult+""); }			
			
			//if(operName.equals("oclIsTypeOf"))	{  result.append(generateOclIsTypeOfMapping(srcResult,argument)); }
							
			if(operName.equals("<")) { append("("+srcResult + " < " + argument + ")"); }				
			if(operName.equals(">")) { append("("+srcResult + " > " + argument + ")"); }				
			if(operName.equals("<=")) { append("("+srcResult + " <= " + argument + ")"); }			
			if(operName.equals(">=")) { append("("+srcResult + " >= " + argument + ")"); }			
			if(operName.equals("and")) { append("("+srcResult + " and " + argument+")"); }			
			if(operName.equals("or")) { append("("+srcResult + " or " + argument+")"); }				
			if(operName.equals("implies")) { append("("+srcResult + " implies " + argument+")"); }			
			if(operName.equals("xor")) { append("("+"("+srcResult +" or "+argument+ ")"+" and not "+"("+srcResult+" and "+ argument+")"+")"); }			
			if(operName.equals("max")) { append("max["+srcResult +","+ argument + "]"); }			
			if(operName.equals("min")) { append("min["+srcResult +"," + argument + "]"); }			
			if(operName.equals("+")) { append("(" + srcResult +").plus["+argument+"]"); }			
			if(operName.equals("*")) { append("(" + srcResult +").mul["+argument+"]"); }			
			if(operName.equals("symmetricDifference")) { append("("+"("+srcResult + " + " + argument+") - ("+srcResult + " & " + argument+")"+")"); }	        	
						
			if (iter.hasNext()) ; // no more arguments 
        }		
        */
		return null; 
	}
	@Override
	public String visitPackage(@NonNull org.eclipse.ocl.examples.pivot.Package pkg)  
	{
		appendQualifiedName(pkg.getNestingPackage(), "::", pkg);
		return null; 
	}
	@Override
	public String visitParameter(@NonNull Parameter parameter)  
	{
		appendQualifiedName((NamedElement) parameter.eContainer(), ".", parameter);
		return null; 
	}
	@Override
	public String visitPrecedence(@NonNull Precedence precedence)  
	{
		appendName(precedence);
		return null; 
	}
	@Override
	public String visitPrimitiveType(@NonNull PrimitiveType object)  
	{
		appendName(object);
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
		// source is null when the property call expression is an
        //    association class navigation qualifier
        OCLExpression source = pc.getSource();
		safeVisit(source);
		Property property = pc.getReferredProperty();
        Type sourceType = source != null ? source.getType() : null;
		result.append(sourceType instanceof CollectionType
				? PivotConstants.COLLECTION_NAVIGATION_OPERATOR
				: PivotConstants.OBJECT_NAVIGATION_OPERATOR);
		appendName(property);
		appendAtPre(pc);
        List<OCLExpression> qualifiers = pc.getQualifier();
		if (!qualifiers.isEmpty()) {
			append("["); //$NON-NLS-1$
			String prefix = ""; //$NON-NLS-1$
			for (OCLExpression qualifier : qualifiers) {
				append(prefix);
				safeVisit(qualifier);
				prefix = ", "; //$NON-NLS-1$
			}
			append("]");
		}
		/*OCLExpression scrExpression = pc.getSource();
		String srcResult = safeVisit(scrExpression);
		Property property = pc.getReferredProperty();
		EStructuralFeature eStructFeature = (EStructuralFeature) getEcoreOfPivot(property);
		
		if (eStructFeature instanceof EAttribute) 
		{
			if (eStructFeature.getEType().isInstance(EcorePackage.eINSTANCE.getEBoolean())) append(srcResult + "in w." + property.getName());
			else append(srcResult+".(w."+property.getName()+")");
		}
		else if (eStructFeature instanceof EReference) {
			append(srcResult + "."+property.getName()+"[w]");
		}*/
		return null; 
	}
	@Override
	public String visitRealLiteralExp(@NonNull RealLiteralExp rl)  
	{
		append(rl.getRealSymbol());
		return null; 
	}
	@Override
	public String visitRoot(@NonNull Root root)  
	{
		appendName(root);
		return null; 
	}
	@Override
	public String visitStateExp(@NonNull StateExp s)  
	{
		appendName(s);
		return null; 
	}
	@Override
	public String visitStringLiteralExp(@NonNull StringLiteralExp sl)  
	{
		append("'");
		append(sl.getStringSymbol());
		append("'");
		return null; 
	}
	@Override
	public String visitTemplateBinding(@NonNull TemplateBinding object)  
	{
		appendTemplateBindings(Collections.singletonList(object));
		return null; 
	}
	@Override
	public String visitTemplateParameter(@NonNull TemplateParameter object)  
	{
		TemplateSignature signature = object.getSignature();
		appendName(signature != null ? (NamedElement) signature.getTemplate() : null);
		append(".");
		appendName((NamedElement) object.getParameteredElement());
		return null; 
	}
	@Override
	public String visitTemplateParameterSubstitution(@NonNull TemplateParameterSubstitution object)  
	{
		TemplateParameter formal = object.getFormal();
		appendName(formal != null ? (NamedElement) formal.getParameteredElement() : null);
		append("/");
		appendName((NamedElement) object.getActual());
		return null; 
	}
	@Override
	public String visitTemplateSignature(@NonNull TemplateSignature object)  
	{
		appendTemplateSignature(object);
		return null; 
	}
	@Override
	public String visitTupleLiteralExp(@NonNull TupleLiteralExp literalExp)  
	{
		append("Tuple{");//$NON-NLS-1$
		String prefix = "";
		for (TupleLiteralPart part : literalExp.getPart()) {
			append(prefix);
            safeVisit(part);
			prefix = ", ";//$NON-NLS-1$
		}
		append("}");
		return null; 
	}
	@Override
	public String visitTupleLiteralPart(@NonNull TupleLiteralPart part)  
	{
		appendName(part);
		Type type = part.getType();
		if (type != null) {
			append(" : ");
			appendElementType(part);
		}
		OCLExpression initExpression = part.getInitExpression();
		if (initExpression != null) {
			append(" = ");
			safeVisit(initExpression);
		}
		return null; 
	}
	@Override
	public String visitTupleType(@NonNull TupleType object)  
	{
		appendName(object);
		append("(");
		String prefix = "";
		for (TypedElement part : object.getOwnedAttribute()) {
			append(prefix);
			appendName(part);
			append(":");
			appendElementType(part);
			prefix = ",";
		}
		append(")");
		return null; 
	}
	@Override
	public String visitTypeExp(@NonNull TypeExp t) 
	{ 
		appendQualifiedName(t.getReferredType());
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
		appendName(object);
		return null; 
	}
	@Override
	public String visitUnspecifiedValueExp(@NonNull UnspecifiedValueExp uv) 
	{
		append("?"); 
		if (uv.getType() != null && !(uv.getType() instanceof VoidType)) {
			append(" : "); 
			appendName(uv.getType());
		}
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
		appendName(object);
		return null; 
	}
	@Override
	public String visiting(@NonNull Visitable visitable) 
	{
		append(visitable.getClass().getName());
		return null;
	}
	
	/** 
	 * Typed Element.
	 * 
	 * This method returns the OntoUML element of the Pivot.
	 * The Match occurs through the attribute "name" due to the follow reasons:
	 * 
	 * 1. In Juno, we don't have getEcoreofPivot(). Also getETarget() doesn't work appropriately.
	 * 2. Even in Kepler (which uses getEcoreOfPivot()) the match can not be done since the ecore 
	 *    element returned isn't equal to the one in the OntoUML2Ecore Map.
	 *    
	 * Therewith, it's not possible to have two model elements with the same name in the model.
	 * This is obvious a current limitation.
	 */
	public RefOntoUML.Element getOntoUMLOfPivot (org.eclipse.ocl.examples.pivot.Element pivotElem)
	{
		// ecore model element of pivot
		EModelElement eElem = oclparser.getMetamodelManager().getEcoreOfPivot(EModelElement.class, pivotElem);

		for (Entry<RefOntoUML.Element,EModelElement> entry : oclparser.getOntoUML2EcoreMap().entrySet()) 
	    {
			// if the the ecore elements are equal..  
            if (eElem.equals(entry.getValue())) return entry.getKey();                
            else { 
            	// match through "name" attribute 
        		if (eElem instanceof ENamedElement)
        		{
        			String ecoreName = ((ENamedElement)eElem).getName().trim().toLowerCase();
        			String entryName = ((ENamedElement)entry.getValue()).getName().trim().toLowerCase();
        			if (ecoreName.equals(entryName)) return entry.getKey();
        		}
            }
	    }
		return null;
	} 	
	
    /** Used for a Test */	
	public static void main (String[]args)
	 {		 				
//		 String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.ocl";
//		 String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.refontouml";
		 
//		 String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\project.ocl";
//		 String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\project.refontouml";
		 
//		 String tempPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\temp\\";
		 
//		 String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.ocl";
//		 String refPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.refontouml";
		 
		 String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\project.ocl";
		 String refPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\project.refontouml";
		 
		 String tempPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\temp\\";
			
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
		 }
		
		
	}

	}
