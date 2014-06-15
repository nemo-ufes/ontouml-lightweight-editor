package br.ufes.inf.nemo.ocl.pivot.ocl2alloy;

/**
 * <copyright>
 *
 * Copyright (c) 2005, 2013 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   E.D. Willink - Robustness enhancements (null-proofing)
 *   Adolfo Sanchez- Barbudo Herrera - 228841 Fix NPE in VariableExp
 *
 * </copyright>
 *
 * $Id: ToStringVisitor.java,v 1.13 2011/05/02 09:31:29 ewillink Exp $
 */


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.domain.elements.Nameable;
import org.eclipse.ocl.examples.domain.ids.TypeId;
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
import org.eclipse.ocl.examples.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.examples.pivot.UnspecifiedType;
import org.eclipse.ocl.examples.pivot.UnspecifiedValueExp;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.VariableExp;
import org.eclipse.ocl.examples.pivot.VoidType;
import org.eclipse.ocl.examples.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.examples.pivot.util.Visitable;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;

/**
 * Converts an OCL expression to a string for debugging. This is not intended to
 * be used by client applications as an AST-to-text transformation.
 * 
 * @author Edith Schonberg (edith)
 * @author Christian W. Damus (cdamus)
 * @author Edward Willink (ewillink)
 */
public class PivotOCLToStringVisitor extends AbstractExtendingVisitor<String, Object>
{	
	private static final Logger logger = Logger.getLogger(PivotOCLToStringVisitor.class);

	public static interface Factory {
		@NonNull PivotOCLToStringVisitor createToStringVisitor();
		@NonNull EPackage getEPackage();
	}
	
	private static @NonNull Map<EPackage, Factory> factoryMap = new HashMap<EPackage, Factory>();
	
	public static synchronized void addFactory(@NonNull Factory factory) {
		factoryMap.put(factory.getEPackage(), factory);
	}

	public static @Nullable PivotOCLToStringVisitor create(@NonNull EObject eObject) {
		EPackage ePackage = eObject.eClass().getEPackage();
		Factory factory = factoryMap.get(ePackage);
		if (factory != null) {
			return factory.createToStringVisitor();
		}
		logger.error("No ToStringVisitor Factory registered for " + ePackage.getName());
		return null;
	}

	private static final class MyFactory implements PivotOCLToStringVisitor.Factory
	{
		private MyFactory() {
			PivotOCLToStringVisitor.addFactory(this);
		}

		public @NonNull PivotOCLToStringVisitor createToStringVisitor() {
			return new PivotOCLToStringVisitor();
		}

		public @NonNull EPackage getEPackage() {
			PivotPackage eInstance = PivotPackage.eINSTANCE;
			assert eInstance != null;
			return eInstance;
		}
	}

	public static @NonNull PivotOCLToStringVisitor.Factory FACTORY = new MyFactory();
	
	/**
	 * Indicates where a required element in the AST was <code>null</code>, so
	 * that it is evident in the debugger that something was missing. We don't
	 * want just <code>"null"</code> because that would look like the OclVoid
	 * literal.
	 */
	protected static @NonNull String NULL_PLACEHOLDER = "\"<null>\""; //$NON-NLS-1$

	protected @NonNull StringBuilder result = new StringBuilder();

	/**
	 * Initializes me.
	 */
	public PivotOCLToStringVisitor() {
        super(Object.class);						// Useless dummy object as context
	}

	/*
	 * protected List<? extends EObject> getConstrainedElements(Constraint
	 * constraint) { if (uml == null) { return Collections.emptyList(); } else {
	 * return uml.getConstrainedElements(constraint); } }
	 * 
	 * protected String getStereotype(Constraint constraint) { return (uml ==
	 * null)? null : uml.getStereotype(constraint); }
	 * 
	 * @Override protected ExpressionInOCL getSpecification(Constraint
	 * constraint) { return (uml == null)? null :
	 * uml.getSpecification(constraint); }
	 */

	protected void append(Number number) {
		if (number != null) {
			result.append(number.toString());
		}
		else {
			result.append(NULL_PLACEHOLDER);
		}
	}

	protected void append(String string) {
		if (string != null) {
			result.append(string);
		}
		else {
			result.append(NULL_PLACEHOLDER);
		}
	}

	protected void appendAtPre(FeatureCallExp mpc) {
		if (mpc.isPre()) {
			append("@pre"); //$NON-NLS-1$
		}
	}

	protected void appendElementType(@Nullable TypedElement typedElement) {
		if (typedElement == null) {
			append(NULL_PLACEHOLDER);
		}
		else {
			safeVisit(typedElement.getType());
			if (!typedElement.isRequired()) {
				append("[?]");
			}
		}
	}

	protected void appendName(Nameable object) {
		if (object == null) {
			result.append(NULL_PLACEHOLDER);
		}
		else {
			result.append(object.getName());
		}
	}

	protected void appendOperationSignature(Operation operation) {
		appendName(operation);
		append("(");
		boolean comma = false;
		for (java.util.Iterator<Parameter> iter = operation
			.getOwnedParameter().iterator(); iter.hasNext();) {
			Parameter parm = iter.next();

			if (comma) {
				append(", "); //$NON-NLS-1$
			} else {
				comma = true;
			}

			appendName(parm);
			append(" : "); //$NON-NLS-1$

			if (parm.getType() != null) {
				appendElementType(parm);
			} else {
				append(TypeId.OCL_VOID_NAME);
			}
		}

		append(") :"); //$NON-NLS-1$
		if (operation.getType() != null) {
			append(" ");
			appendElementType(operation);
		}
	}

	protected void appendPropertySignature(Property property) {
		appendName(property);
		if (property.getType() != null) {
			append(" : ");
			appendElementType(property);
		}
	}

	protected void appendQualifiedName(NamedElement parent, String separator, NamedElement child) {
		if (parent != null) {
			appendQualifiedName(parent);
			append(separator);
		}
		appendName(child);
	}

	protected void appendQualifiedName(@Nullable NamedElement object) {
		if (object == null) {
			result.append(NULL_PLACEHOLDER);
		}
		else {
			EObject container = object.eContainer();
			if (!(container instanceof Root) && (container instanceof NamedElement) &&
					(!(container.eContainer() instanceof Root) || !PivotConstants.OCL_NAME.equals(((NamedElement)container).getName()))) {
				appendQualifiedName((NamedElement) container);
				append("::"); //$NON-NLS-1$
			}
			appendName(object);
			if (object instanceof TemplateableElement) {
				TemplateableElement templateableElement = (TemplateableElement) object;
				appendTemplateBindings(templateableElement.getTemplateBinding());
				appendTemplateSignature(templateableElement.getOwnedTemplateSignature());
			}
		}
	}

	protected void appendTemplateBindings(List<TemplateBinding> templateBindings) {
		if (templateBindings.size() > 0) {
			append("(");
			String prefix = ""; //$NON-NLS-1$
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

	protected void appendTemplateSignature(TemplateSignature templateSignature) {
		if (templateSignature != null) {
			List<TemplateParameter> templateParameters = templateSignature.getOwnedParameter();
			if (!templateParameters.isEmpty()) {
				append("(");
				String prefix = ""; //$NON-NLS-1$
				for (TemplateParameter templateParameter : templateParameters) {
					append(prefix);
					safeVisit(templateParameter.getParameteredElement());
					prefix = ",";
				}
				append(")");
			}
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

	@Override
	public String toString() {
		return result.toString();
	}

	@Override
	public String visitAnyType(@NonNull AnyType object) {
		appendName(object);
		return null;
	}

	/**
	 * Callback for an AssociationClassCallExp visit.
	 * 
	 * @param ac
	 *            the association class expression
	 * @return string source.ref
	 */
	@Override
	public String visitAssociationClassCallExp(@NonNull AssociationClassCallExp ac) {
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

	/**
	 * Callback for a BooleanLiteralExp visit.
	 * 
	 * @param bl
	 *            -- boolean literal expression
	 * @return the value of the boolean literal as a java.lang.Boolean.
	 */
	@Override
	public String visitBooleanLiteralExp(@NonNull BooleanLiteralExp bl) {
		append(Boolean.toString(bl.isBooleanSymbol()));
		return null;
	}

	@Override
	public String visitClass(@NonNull org.eclipse.ocl.examples.pivot.Class cls) {
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
    
    /**
     * Visits the item's item expression.
     * 
     * Returns the result of {@link #handleCollectionItem(CollectionItem, Object)}
     */
    @Override
	public String visitCollectionItem(@NonNull CollectionItem item) {
    	safeVisit(item.getItem());  	
        return null;
    }

    /**
     * Visits the collection literal's parts.
     * 
     * Returns the result of {@link #handleCollectionLiteralExp(CollectionLiteralExp, List)}.
     */
	@Override
	public String visitCollectionLiteralExp(@NonNull CollectionLiteralExp cl) {
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
    
    /**
     * Visits the range's first and last expressions.
     * 
     * Returns the result of {@link #handleCollectionRange(CollectionRange, Object, Object)}.
     */
    @Override
	public String visitCollectionRange(@NonNull CollectionRange range) {
        safeVisit(range.getFirst());
        append(" .. ");
        safeVisit(range.getLast());
        return null;
    }

	@Override
	public String visitCollectionType(@NonNull CollectionType object) {
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

	/**
	 * Renders a constraint with its context and expression.
	 */
	@Override
	public String visitConstraint(@NonNull Constraint constraint) {
		List<? extends EObject> constrained = constraint.getConstrainedElement();
		if (!constrained.isEmpty()) {
			EObject elem = constrained.get(0);
			append("context "); //$NON-NLS-1$
			if (elem instanceof Type) {
				appendName((NamedElement) elem);
			} else if (elem instanceof Operation) {
				Operation oper = (Operation) elem;
				appendOperationSignature(oper);
			} else if (elem instanceof Property) {
				Property prop = (Property) elem;
				appendPropertySignature(prop);
			}
			append(" ");
		}

		String stereo = PivotUtil.getStereotype(constraint);
		append(stereo); //$NON-NLS-1$
		String name = constraint.getName();
		if (name != null) {
			append(" "); //$NON-NLS-1$
			append(name);
		}
		append(": "); //$NON-NLS-1$
/* FIXME def context
		EObject elem = constrained.get(1);
		if (elem instanceof Operation) {
			appendOperationSignature((Operation) elem);
		} else if (elem instanceof Property) {
			appendPropertySignature((Property) elem);
		}
		append(" = "); //$NON-NLS-1$
*/
		safeVisit(constraint.getSpecification());
		return null;
	}

	/**
	 * Callback for a ConstructorExp visit.
	 * 
	 * @param constructorExp
	 *            constructor expression
	 * @return the string representation
	 */
	@Override
	public String visitConstructorExp(@NonNull ConstructorExp constructorExp) {
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
	
    /**
     * Visits the tuple constructor part's value, if any.
     */
	@Override
	public String visitConstructorPart(@NonNull ConstructorPart part) {
		appendName(part.getReferredProperty());
		OCLExpression initExpression = part.getInitExpression();
		if (initExpression != null) {
			append(" = ");
			safeVisit(initExpression);
		}
		return null;
	}

	@Override
	public String visitElementExtension(@NonNull ElementExtension as) {
		appendQualifiedName(as.getPackage(), "::", as);
		return null;
	}

	/**
	 * Callback for an EnumLiteralExp visit.
	 * 
	 * @param el
	 *            the enumeration literal expresion
	 * @return the enumeration literal toString()
	 */
	@Override
	public String visitEnumLiteralExp(@NonNull EnumLiteralExp el) {
		appendQualifiedName(el.getReferredEnumLiteral());
		return null;
	}

	@Override
	public String visitEnumerationLiteral(@NonNull EnumerationLiteral el) {
		appendQualifiedName(el.getEnumeration(), "::", el);
		return null;
	}

	/**
	 * Renders an ExpressionInOCL with its context variables and body.
	 */
	@Override
	public String visitExpressionInOCL(@NonNull ExpressionInOCL expression) {
		OCLExpression bodyExpression = expression.getBodyExpression();
		if (bodyExpression != null) {
			return safeVisit(bodyExpression);
		}
		else {
			return PivotUtil.getBody(expression);
		}
	}

    /**
     * Visits the expressions context variable, its parameter variables (if any),
     * its result variable (if any), and finally its body expression.
     * 
     * Returns the result of
     * {@link #handleExpressionInOCL(ExpressionInOCL, Object, Object, List, Object)}.
     *
    @Override
	public T visitExpressionInOCL(ExpressionInOCL expression) {
        T contextResult = safeVisit(expression.getContextVariable());
        
        Variable resultVar = expression.getResultVariable();
        T resultResult = safeVisit(resultVar);
        
        List<T> parameterResults;
        List<Variable> parameters = expression.getParameterVariable();
        
        if (parameters.isEmpty()) {
            parameterResults = Collections.emptyList();
        } else {
            parameterResults = new java.util.ArrayList<T>(parameters.size());
            for (Variable iterVar : parameters) {
                parameterResults.add(safeVisit(iterVar));
            }
        }
        
        T bodyResult = safeVisit(expression.getBodyExpression());
        
        return handleExpressionInOCL(expression, contextResult, resultResult,
            parameterResults, bodyResult);
    } */

	/**
	 * Callback for an IfExp visit.
	 * 
	 * @param ifExp
	 *            an IfExp
	 * @return the string representation
	 */
	@Override
	public String visitIfExp(@NonNull IfExp ifExp) {
		append("if ");  //$NON-NLS-1$
		safeVisit(ifExp.getCondition());
		append(" then "); //$NON-NLS-1$
		safeVisit(ifExp.getThenExpression());
		append(" else "); //$NON-NLS-1$
		safeVisit(ifExp.getElseExpression());
		append(" endif"); //$NON-NLS-1$
		return null;
	}

	@Override public @Nullable String visitImport(@NonNull Import object) {
		appendName(object);
		append(" : ");
		appendQualifiedName(object.getImportedNamespace());
		return null;
	}

	/**
	 * Callback for an IntegerLiteralExp visit.
	 * 
	 * @param il
	 *            -- integer literal expression
	 * @return String
	 */
	@Override
	public String visitIntegerLiteralExp(@NonNull IntegerLiteralExp il) {
		append(il.getIntegerSymbol());
		return null;
	}

	@Override
	public String visitInvalidLiteralExp(@NonNull InvalidLiteralExp il) {
		append("invalid");
		return null;
	}

	@Override
	public String visitInvalidType(@NonNull InvalidType object) {
		appendName(object);
		return null;
	}

	/**
	 * Callback for an IterateExp visit.
	 * 
	 * @param callExp
	 *            an iterate expression
	 * @return the string representation
	 */
	@Override
	public String visitIterateExp(@NonNull IterateExp callExp) {
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
	public String visitIteration(@NonNull Iteration iteration) {
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

	/**
	 * Callback for an IteratorExp visit.
	 * 
	 * @param callExp
	 *            an iterator expression
	 * @return the string representation
	 */
	@Override
	public String visitIteratorExp(@NonNull IteratorExp callExp) {
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
		append(")");//$NON-NLS-1$
		return null;        
	}

	@Override
	public String visitLambdaType(@NonNull LambdaType lambda) {
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

	/**
	 * Callback for LetExp visit.
	 * 
	 * @param letExp
	 *            a let expression
	 * @return the string representation
	 */
	@Override
	public String visitLetExp(@NonNull LetExp letExp) {
		append("let "); //$NON-NLS-1$
		safeVisit(letExp.getVariable());
		append(" in "); //$NON-NLS-1$
		safeVisit(letExp.getIn());
		return null;
	}
	
    /**
     * Visits the message expression's target and then its arguments.
     * Returns the result of {@link #handleMessageExp(MessageExp, Object, List)}.
     */
	@Override
	public String visitMessageExp(@NonNull MessageExp messageExp) {
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
	public String visitMetaclass(@NonNull Metaclass object) {
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
	public String visitNullLiteralExp(@NonNull NullLiteralExp il) {
		append("null");
		return null;
	}

	@Override
	public String visitOpaqueExpression(@NonNull OpaqueExpression object) {
		String body = PivotUtil.getBody(object);
		if (body != null) {
			append(body);
		}
		return null;
	}

	@Override
	public String visitOperation(@NonNull Operation operation) {
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

	/**
	 * Callback for an OperationCallExp visit.
	 * 
	 * Look at the source to determine operator ( -> or . )
	 * 
	 * @param oc
	 *            the operation call expression
	 * @return string
	 */
	@Override
	public String visitOperationCallExp(@NonNull OperationCallExp oc) {
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
		return null;
	}

	@Override
	public String visitPackage(@NonNull org.eclipse.ocl.examples.pivot.Package pkg) {
		appendQualifiedName(pkg.getNestingPackage(), "::", pkg);
		return null;
	}

	@Override
	public String visitParameter(@NonNull Parameter parameter) {
		appendQualifiedName((NamedElement) parameter.eContainer(), ".", parameter);
		return null;
	}

	@Override
	public String visitPrecedence(@NonNull Precedence precedence) {
		appendName(precedence);
		return null;
	}

	@Override
	public String visitPrimitiveType(@NonNull PrimitiveType object) {
		appendName(object);
		return null;
	}

	@Override
	public String visitProperty(@NonNull Property property) {
		appendQualifiedName(property.getOwningType(), ".", property);
		return null;
	}

	/**
	 * Callback for an AssociationEndCallExp visit.
	 * 
	 * @param pc
	 *            the property call expression
	 * @return string source.ref
	 */
	@Override
	public String visitPropertyCallExp(@NonNull PropertyCallExp pc) {
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
		return null;
	}

	/**
	 * Callback for a RealLiteralExp visit.
	 * 
	 * @param rl
	 *            -- real literal expression
	 * @return the value of the real literal as a java.lang.Double.
	 */
	@Override
	public String visitRealLiteralExp(@NonNull RealLiteralExp rl) {
		append(rl.getRealSymbol());
		return null;
	}

	@Override
	public String visitRoot(@NonNull Root root) {
		appendName(root);
		return null;
	}

	@Override
	public String visitStateExp(@NonNull StateExp s) {
		appendName(s);
		return null;
	}

	/**
	 * Callback for a StringLiteralExp visit.
	 * 
	 * @param sl
	 *            -- string literal expression
	 * @return the value of the string literal as a java.lang.String.
	 */
	@Override
	public String visitStringLiteralExp(@NonNull StringLiteralExp sl) {
		append("'");
		append(sl.getStringSymbol());
		append("'");
		return null;
	}

	@Override
	public String visitTemplateBinding(@NonNull TemplateBinding object) {
		// s.append(getQualifiedName(object.getFormal(), "/", (NamedElement)
		// object.getActual()));
		appendTemplateBindings(Collections.singletonList(object));
		return null;
	}

	@Override
	public String visitTemplateParameter(@NonNull TemplateParameter object) {
		TemplateSignature signature = object.getSignature();
		appendName(signature != null ? (NamedElement) signature.getTemplate() : null);
		append(".");
		appendName((NamedElement) object.getParameteredElement());
		return null;
	}

	@Override
	public String visitTemplateParameterSubstitution(@NonNull TemplateParameterSubstitution object) {
		TemplateParameter formal = object.getFormal();
		appendName(formal != null ? (NamedElement) formal.getParameteredElement() : null);
		append("/");
		appendName((NamedElement) object.getActual());
		return null;
	}

	@Override
	public String visitTemplateSignature(@NonNull TemplateSignature object) {
		// s.append(getQualifiedName(object.getFormal(), "/", (NamedElement)
		// object.getActual()));
		appendTemplateSignature(object);
		return null;
	}

	/**
	 * Callback for a TupleLiteralExp visit.
	 * 
	 * @param literalExp
	 *            tuple literal expression
	 * @return the string representation
	 */
	@Override
	public String visitTupleLiteralExp(@NonNull TupleLiteralExp literalExp) {
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
	
    /**
     * Visits the tuple literal part's value, if any.
     */
	@Override
	public String visitTupleLiteralPart(@NonNull TupleLiteralPart part) {
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
	public String visitTupleType(@NonNull TupleType object) {
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
	public String visitTypeExp(@NonNull TypeExp t) {
		appendQualifiedName(t.getReferredType());
		return null;
	}

	/**
	 * Callback for an UnlimitedNaturalLiteralExp visit.
	 * 
	 * @param unl
	 *            -- unlimited natural literal expression
	 * @return String
	 */
	@Override
	public String visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp unl) {
		append(unl.getUnlimitedNaturalSymbol());
		return null;
	}

	@Override
	public String visitUnspecifiedType(@NonNull UnspecifiedType object) {
		appendName(object);
		return null;
	}

	/**
	 * Callback for an UnspecifiedValueExp visit.
	 * 
	 * @param uv
	 *            - UnspecifiedValueExp
	 * @return the string representation
	 */
	@Override
	public String visitUnspecifiedValueExp(@NonNull UnspecifiedValueExp uv) {
		append("?"); //$NON-NLS-1$
		if (uv.getType() != null && !(uv.getType() instanceof VoidType)) {
			append(" : "); //$NON-NLS-1$
			appendName(uv.getType());
		}
		return null;
	}

    /**
     * Visits the variable's initialization expression (if any).
     * Returns the result of {@link #handleVariable(Variable, Object)}.
     */
	@Override
	public String visitVariable(@NonNull Variable variable) {
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

	/**
	 * Callback for a VariableExp visit.
	 * 
	 * @param v
	 *            the variable expression
	 * @return the variable name
	 */
	@Override
	public String visitVariableExp(@NonNull VariableExp v) {
		appendName(v.getReferredVariable());
		return null;
	}

	@Override
	public String visitVoidType(@NonNull VoidType object) {
		appendName(object);
		return null;
	}

	public String visiting(@NonNull Visitable visitable) {
		append(visitable.getClass().getName());
		return null;
	}
} // ToStringVisitorImpl
