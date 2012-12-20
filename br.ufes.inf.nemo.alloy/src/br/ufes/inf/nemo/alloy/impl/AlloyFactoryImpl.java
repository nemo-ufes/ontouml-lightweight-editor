/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AlloyFactoryImpl extends EFactoryImpl implements AlloyFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AlloyFactory init() {
		try {
			AlloyFactory theAlloyFactory = (AlloyFactory)EPackage.Registry.INSTANCE.getEFactory("http://alloy/1.0"); 
			if (theAlloyFactory != null) {
				return theAlloyFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AlloyFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlloyFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AlloyPackage.ALLOY_MODULE: return createAlloyModule();
			case AlloyPackage.PARAMETRIZED_MODULE: return createParametrizedModule();
			case AlloyPackage.SIGNATURE_PARAMETER: return createSignatureParameter();
			case AlloyPackage.IMPORTER_MODULE: return createImporterModule();
			case AlloyPackage.MODULE_IMPORTATION: return createModuleImportation();
			case AlloyPackage.PARAGRAPH: return createParagraph();
			case AlloyPackage.SIGNATURE_DECLARATION: return createSignatureDeclaration();
			case AlloyPackage.FACT_DECLARATION: return createFactDeclaration();
			case AlloyPackage.FUNCTION_DECLARATION: return createFunctionDeclaration();
			case AlloyPackage.PREDICATE_DECLARATION: return createPredicateDeclaration();
			case AlloyPackage.ASSERTION_DECLARATION: return createAssertionDeclaration();
			case AlloyPackage.COMMAND_DECLARATION: return createCommandDeclaration();
			case AlloyPackage.INHERITANCE: return createInheritance();
			case AlloyPackage.GENERIC_SCOPE: return createGenericScope();
			case AlloyPackage.DETAILED_SCOPE: return createDetailedScope();
			case AlloyPackage.SCOPEABLE: return createScopeable();
			case AlloyPackage.DECLARATION: return createDeclaration();
			case AlloyPackage.SIGNATURE_REFERENCE: return createSignatureReference();
			case AlloyPackage.BLOCK: return createBlock();
			case AlloyPackage.BINARY_OPERATION: return createBinaryOperation();
			case AlloyPackage.UNARY_OPERATION: return createUnaryOperation();
			case AlloyPackage.LET_DECLARATION: return createLetDeclaration();
			case AlloyPackage.COMPARE_OPERATION: return createCompareOperation();
			case AlloyPackage.VARIABLE_REFERENCE: return createVariableReference();
			case AlloyPackage.ARROW_OPERATION: return createArrowOperation();
			case AlloyPackage.CONSTANT_EXPRESSION: return createConstantExpression();
			case AlloyPackage.EXTERNAL_REFERENCE: return createExternalReference();
			case AlloyPackage.COMPREHENSION_EXPRESSION: return createComprehensionExpression();
			case AlloyPackage.FUNCTION_INVOCATION: return createFunctionInvocation();
			case AlloyPackage.PREDICATE_INVOCATION: return createPredicateInvocation();
			case AlloyPackage.DISJOINT_EXPRESSION: return createDisjointExpression();
			case AlloyPackage.IMPLIES_OPERATION: return createImpliesOperation();
			case AlloyPackage.QUANTIFICATION_EXPRESSION: return createQuantificationExpression();
			case AlloyPackage.VARIABLE: return createVariable();
			case AlloyPackage.ENUM_DECLARATION: return createEnumDeclaration();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case AlloyPackage.MULTIPLICITY:
				return createMultiplicityFromString(eDataType, initialValue);
			case AlloyPackage.BINARY_OPERATOR:
				return createBinaryOperatorFromString(eDataType, initialValue);
			case AlloyPackage.UNARY_OPERATOR:
				return createUnaryOperatorFromString(eDataType, initialValue);
			case AlloyPackage.COMPARE_OPERATOR:
				return createCompareOperatorFromString(eDataType, initialValue);
			case AlloyPackage.CONSTANT:
				return createConstantFromString(eDataType, initialValue);
			case AlloyPackage.QUANTIFICATOR:
				return createQuantificatorFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case AlloyPackage.MULTIPLICITY:
				return convertMultiplicityToString(eDataType, instanceValue);
			case AlloyPackage.BINARY_OPERATOR:
				return convertBinaryOperatorToString(eDataType, instanceValue);
			case AlloyPackage.UNARY_OPERATOR:
				return convertUnaryOperatorToString(eDataType, instanceValue);
			case AlloyPackage.COMPARE_OPERATOR:
				return convertCompareOperatorToString(eDataType, instanceValue);
			case AlloyPackage.CONSTANT:
				return convertConstantToString(eDataType, instanceValue);
			case AlloyPackage.QUANTIFICATOR:
				return convertQuantificatorToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlloyModule createAlloyModule() {
		AlloyModuleImpl alloyModule = new AlloyModuleImpl();
		return alloyModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParametrizedModule createParametrizedModule() {
		ParametrizedModuleImpl parametrizedModule = new ParametrizedModuleImpl();
		return parametrizedModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SignatureParameter createSignatureParameter() {
		SignatureParameterImpl signatureParameter = new SignatureParameterImpl();
		return signatureParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImporterModule createImporterModule() {
		ImporterModuleImpl importerModule = new ImporterModuleImpl();
		return importerModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModuleImportation createModuleImportation() {
		ModuleImportationImpl moduleImportation = new ModuleImportationImpl();
		return moduleImportation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Paragraph createParagraph() {
		ParagraphImpl paragraph = new ParagraphImpl();
		return paragraph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SignatureDeclaration createSignatureDeclaration() {
		SignatureDeclarationImpl signatureDeclaration = new SignatureDeclarationImpl();
		return signatureDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FactDeclaration createFactDeclaration() {
		FactDeclarationImpl factDeclaration = new FactDeclarationImpl();
		return factDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionDeclaration createFunctionDeclaration() {
		FunctionDeclarationImpl functionDeclaration = new FunctionDeclarationImpl();
		return functionDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredicateDeclaration createPredicateDeclaration() {
		PredicateDeclarationImpl predicateDeclaration = new PredicateDeclarationImpl();
		return predicateDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssertionDeclaration createAssertionDeclaration() {
		AssertionDeclarationImpl assertionDeclaration = new AssertionDeclarationImpl();
		return assertionDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommandDeclaration createCommandDeclaration() {
		CommandDeclarationImpl commandDeclaration = new CommandDeclarationImpl();
		return commandDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Inheritance createInheritance() {
		InheritanceImpl inheritance = new InheritanceImpl();
		return inheritance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericScope createGenericScope() {
		GenericScopeImpl genericScope = new GenericScopeImpl();
		return genericScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DetailedScope createDetailedScope() {
		DetailedScopeImpl detailedScope = new DetailedScopeImpl();
		return detailedScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Scopeable createScopeable() {
		ScopeableImpl scopeable = new ScopeableImpl();
		return scopeable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Declaration createDeclaration() {
		DeclarationImpl declaration = new DeclarationImpl();
		return declaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SignatureReference createSignatureReference() {
		SignatureReferenceImpl signatureReference = new SignatureReferenceImpl();
		return signatureReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block createBlock() {
		BlockImpl block = new BlockImpl();
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryOperation createBinaryOperation() {
		BinaryOperationImpl binaryOperation = new BinaryOperationImpl();
		return binaryOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryOperation createUnaryOperation() {
		UnaryOperationImpl unaryOperation = new UnaryOperationImpl();
		return unaryOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LetDeclaration createLetDeclaration() {
		LetDeclarationImpl letDeclaration = new LetDeclarationImpl();
		return letDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompareOperation createCompareOperation() {
		CompareOperationImpl compareOperation = new CompareOperationImpl();
		return compareOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableReference createVariableReference() {
		VariableReferenceImpl variableReference = new VariableReferenceImpl();
		return variableReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrowOperation createArrowOperation() {
		ArrowOperationImpl arrowOperation = new ArrowOperationImpl();
		return arrowOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstantExpression createConstantExpression() {
		ConstantExpressionImpl constantExpression = new ConstantExpressionImpl();
		return constantExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalReference createExternalReference() {
		ExternalReferenceImpl externalReference = new ExternalReferenceImpl();
		return externalReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComprehensionExpression createComprehensionExpression() {
		ComprehensionExpressionImpl comprehensionExpression = new ComprehensionExpressionImpl();
		return comprehensionExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionInvocation createFunctionInvocation() {
		FunctionInvocationImpl functionInvocation = new FunctionInvocationImpl();
		return functionInvocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredicateInvocation createPredicateInvocation() {
		PredicateInvocationImpl predicateInvocation = new PredicateInvocationImpl();
		return predicateInvocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisjointExpression createDisjointExpression() {
		DisjointExpressionImpl disjointExpression = new DisjointExpressionImpl();
		return disjointExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImpliesOperation createImpliesOperation() {
		ImpliesOperationImpl impliesOperation = new ImpliesOperationImpl();
		return impliesOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QuantificationExpression createQuantificationExpression() {
		QuantificationExpressionImpl quantificationExpression = new QuantificationExpressionImpl();
		return quantificationExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variable createVariable() {
		VariableImpl variable = new VariableImpl();
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumDeclaration createEnumDeclaration() {
		EnumDeclarationImpl enumDeclaration = new EnumDeclarationImpl();
		return enumDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Multiplicity createMultiplicityFromString(EDataType eDataType, String initialValue) {
		Multiplicity result = Multiplicity.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMultiplicityToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryOperator createBinaryOperatorFromString(EDataType eDataType, String initialValue) {
		BinaryOperator result = BinaryOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBinaryOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryOperator createUnaryOperatorFromString(EDataType eDataType, String initialValue) {
		UnaryOperator result = UnaryOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUnaryOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompareOperator createCompareOperatorFromString(EDataType eDataType, String initialValue) {
		CompareOperator result = CompareOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCompareOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constant createConstantFromString(EDataType eDataType, String initialValue) {
		Constant result = Constant.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConstantToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Quantificator createQuantificatorFromString(EDataType eDataType, String initialValue) {
		Quantificator result = Quantificator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertQuantificatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlloyPackage getAlloyPackage() {
		return (AlloyPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AlloyPackage getPackage() {
		return AlloyPackage.eINSTANCE;
	}

} //AlloyFactoryImpl
