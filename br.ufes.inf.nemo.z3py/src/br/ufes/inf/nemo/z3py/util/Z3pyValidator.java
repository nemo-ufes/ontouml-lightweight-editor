/**
 */
package br.ufes.inf.nemo.z3py.util;

import br.ufes.inf.nemo.z3py.*;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see br.ufes.inf.nemo.z3py.Z3pyPackage
 * @generated
 */
public class Z3pyValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final Z3pyValidator INSTANCE = new Z3pyValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "br.ufes.inf.nemo.z3py";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Z3pyValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return Z3pyPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case Z3pyPackage.EXPRESSION:
				return validateExpression((Expression)value, diagnostics, context);
			case Z3pyPackage.FUNCTION_CALL:
				return validateFunctionCall((FunctionCall)value, diagnostics, context);
			case Z3pyPackage.QUANTIFICATION:
				return validateQuantification((Quantification)value, diagnostics, context);
			case Z3pyPackage.UNIVERSAL_QUANTIFICATION:
				return validateUniversalQuantification((UniversalQuantification)value, diagnostics, context);
			case Z3pyPackage.EXISTENTIAL_QUANTIFICATION:
				return validateExistentialQuantification((ExistentialQuantification)value, diagnostics, context);
			case Z3pyPackage.CONJUNCTION:
				return validateConjunction((Conjunction)value, diagnostics, context);
			case Z3pyPackage.DISJUNCTION:
				return validateDisjunction((Disjunction)value, diagnostics, context);
			case Z3pyPackage.EXCLUSIVE_DISJUNCTION:
				return validateExclusiveDisjunction((ExclusiveDisjunction)value, diagnostics, context);
			case Z3pyPackage.LOGICAL_NEGATION:
				return validateLogicalNegation((LogicalNegation)value, diagnostics, context);
			case Z3pyPackage.IMPLICATION:
				return validateImplication((Implication)value, diagnostics, context);
			case Z3pyPackage.BI_IMPLICATION:
				return validateBiImplication((BiImplication)value, diagnostics, context);
			case Z3pyPackage.LOGICAL_BINARY_EXPRESSION:
				return validateLogicalBinaryExpression((LogicalBinaryExpression)value, diagnostics, context);
			case Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION:
				return validateBooleanFunctionDefinition((BooleanFunctionDefinition)value, diagnostics, context);
			case Z3pyPackage.INT_CONSTANT:
				return validateIntConstant((IntConstant)value, diagnostics, context);
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM:
				return validateOntoUMLZ3System((OntoUMLZ3System)value, diagnostics, context);
			case Z3pyPackage.EQUALITY:
				return validateEquality((Equality)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExpression(Expression expression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(expression, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFunctionCall(FunctionCall functionCall, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(functionCall, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(functionCall, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(functionCall, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(functionCall, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(functionCall, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(functionCall, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(functionCall, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(functionCall, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(functionCall, diagnostics, context);
		if (result || diagnostics != null) result &= validateFunctionCall_FunctionCallNumberOfArguments(functionCall, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the FunctionCallNumberOfArguments constraint of '<em>Function Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String FUNCTION_CALL__FUNCTION_CALL_NUMBER_OF_ARGUMENTS__EEXPRESSION = "self.calledFunction.numberOfArguments = self.arguments->size()";

	/**
	 * Validates the FunctionCallNumberOfArguments constraint of '<em>Function Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFunctionCall_FunctionCallNumberOfArguments(FunctionCall functionCall, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(Z3pyPackage.Literals.FUNCTION_CALL,
				 functionCall,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "FunctionCallNumberOfArguments",
				 FUNCTION_CALL__FUNCTION_CALL_NUMBER_OF_ARGUMENTS__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateQuantification(Quantification quantification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(quantification, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(quantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(quantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(quantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(quantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(quantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(quantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(quantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(quantification, diagnostics, context);
		if (result || diagnostics != null) result &= validateQuantification_IrreflexiveQuant(quantification, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the IrreflexiveQuant constraint of '<em>Quantification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String QUANTIFICATION__IRREFLEXIVE_QUANT__EEXPRESSION = "self.expression <> self";

	/**
	 * Validates the IrreflexiveQuant constraint of '<em>Quantification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateQuantification_IrreflexiveQuant(Quantification quantification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(Z3pyPackage.Literals.QUANTIFICATION,
				 quantification,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "IrreflexiveQuant",
				 QUANTIFICATION__IRREFLEXIVE_QUANT__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUniversalQuantification(UniversalQuantification universalQuantification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(universalQuantification, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(universalQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(universalQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(universalQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(universalQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(universalQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(universalQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(universalQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(universalQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validateQuantification_IrreflexiveQuant(universalQuantification, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExistentialQuantification(ExistentialQuantification existentialQuantification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(existentialQuantification, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(existentialQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(existentialQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(existentialQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(existentialQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(existentialQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(existentialQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(existentialQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(existentialQuantification, diagnostics, context);
		if (result || diagnostics != null) result &= validateQuantification_IrreflexiveQuant(existentialQuantification, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConjunction(Conjunction conjunction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(conjunction, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDisjunction(Disjunction disjunction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(disjunction, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExclusiveDisjunction(ExclusiveDisjunction exclusiveDisjunction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(exclusiveDisjunction, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLogicalNegation(LogicalNegation logicalNegation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(logicalNegation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateImplication(Implication implication, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(implication, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBiImplication(BiImplication biImplication, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(biImplication, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLogicalBinaryExpression(LogicalBinaryExpression logicalBinaryExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(logicalBinaryExpression, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBooleanFunctionDefinition(BooleanFunctionDefinition booleanFunctionDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(booleanFunctionDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIntConstant(IntConstant intConstant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(intConstant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOntoUMLZ3System(OntoUMLZ3System ontoUMLZ3System, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(ontoUMLZ3System, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEquality(Equality equality, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(equality, diagnostics, context);
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //Z3pyValidator
