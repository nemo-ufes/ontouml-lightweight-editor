/**
 */
package br.ufes.inf.nemo.z3py;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see br.ufes.inf.nemo.z3py.Z3pyFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot'"
 * @generated
 */
public interface Z3pyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "z3py";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://z3py/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "z3py";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Z3pyPackage eINSTANCE = br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl.init();

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.ExpressionImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.FunctionCallImpl <em>Function Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.FunctionCallImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getFunctionCall()
	 * @generated
	 */
	int FUNCTION_CALL = 1;

	/**
	 * The feature id for the '<em><b>Called Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__CALLED_FUNCTION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__ARGUMENTS = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Function Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.QuantificationImpl <em>Quantification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.QuantificationImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getQuantification()
	 * @generated
	 */
	int QUANTIFICATION = 2;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFICATION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Quantifies Over</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFICATION__QUANTIFIES_OVER = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFICATION__COMMENTS = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Quantification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFICATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.UniversalQuantificationImpl <em>Universal Quantification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.UniversalQuantificationImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getUniversalQuantification()
	 * @generated
	 */
	int UNIVERSAL_QUANTIFICATION = 3;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIVERSAL_QUANTIFICATION__EXPRESSION = QUANTIFICATION__EXPRESSION;

	/**
	 * The feature id for the '<em><b>Quantifies Over</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIVERSAL_QUANTIFICATION__QUANTIFIES_OVER = QUANTIFICATION__QUANTIFIES_OVER;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIVERSAL_QUANTIFICATION__COMMENTS = QUANTIFICATION__COMMENTS;

	/**
	 * The number of structural features of the '<em>Universal Quantification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIVERSAL_QUANTIFICATION_FEATURE_COUNT = QUANTIFICATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.ExistentialQuantificationImpl <em>Existential Quantification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.ExistentialQuantificationImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getExistentialQuantification()
	 * @generated
	 */
	int EXISTENTIAL_QUANTIFICATION = 4;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTENTIAL_QUANTIFICATION__EXPRESSION = QUANTIFICATION__EXPRESSION;

	/**
	 * The feature id for the '<em><b>Quantifies Over</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTENTIAL_QUANTIFICATION__QUANTIFIES_OVER = QUANTIFICATION__QUANTIFIES_OVER;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTENTIAL_QUANTIFICATION__COMMENTS = QUANTIFICATION__COMMENTS;

	/**
	 * The number of structural features of the '<em>Existential Quantification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTENTIAL_QUANTIFICATION_FEATURE_COUNT = QUANTIFICATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.LogicalBinaryExpressionImpl <em>Logical Binary Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.LogicalBinaryExpressionImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getLogicalBinaryExpression()
	 * @generated
	 */
	int LOGICAL_BINARY_EXPRESSION = 11;

	/**
	 * The feature id for the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_BINARY_EXPRESSION__OPERAND1 = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_BINARY_EXPRESSION__OPERAND2 = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Logical Binary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_BINARY_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.ConjunctionImpl <em>Conjunction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.ConjunctionImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getConjunction()
	 * @generated
	 */
	int CONJUNCTION = 5;

	/**
	 * The feature id for the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONJUNCTION__OPERAND1 = LOGICAL_BINARY_EXPRESSION__OPERAND1;

	/**
	 * The feature id for the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONJUNCTION__OPERAND2 = LOGICAL_BINARY_EXPRESSION__OPERAND2;

	/**
	 * The number of structural features of the '<em>Conjunction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONJUNCTION_FEATURE_COUNT = LOGICAL_BINARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.DisjunctionImpl <em>Disjunction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.DisjunctionImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getDisjunction()
	 * @generated
	 */
	int DISJUNCTION = 6;

	/**
	 * The feature id for the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISJUNCTION__OPERAND1 = LOGICAL_BINARY_EXPRESSION__OPERAND1;

	/**
	 * The feature id for the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISJUNCTION__OPERAND2 = LOGICAL_BINARY_EXPRESSION__OPERAND2;

	/**
	 * The number of structural features of the '<em>Disjunction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISJUNCTION_FEATURE_COUNT = LOGICAL_BINARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.ExclusiveDisjunctionImpl <em>Exclusive Disjunction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.ExclusiveDisjunctionImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getExclusiveDisjunction()
	 * @generated
	 */
	int EXCLUSIVE_DISJUNCTION = 7;

	/**
	 * The feature id for the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUSIVE_DISJUNCTION__OPERAND1 = LOGICAL_BINARY_EXPRESSION__OPERAND1;

	/**
	 * The feature id for the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUSIVE_DISJUNCTION__OPERAND2 = LOGICAL_BINARY_EXPRESSION__OPERAND2;

	/**
	 * The number of structural features of the '<em>Exclusive Disjunction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUSIVE_DISJUNCTION_FEATURE_COUNT = LOGICAL_BINARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.LogicalNegationImpl <em>Logical Negation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.LogicalNegationImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getLogicalNegation()
	 * @generated
	 */
	int LOGICAL_NEGATION = 8;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_NEGATION__OPERAND = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Logical Negation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_NEGATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.ImplicationImpl <em>Implication</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.ImplicationImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getImplication()
	 * @generated
	 */
	int IMPLICATION = 9;

	/**
	 * The feature id for the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLICATION__OPERAND1 = LOGICAL_BINARY_EXPRESSION__OPERAND1;

	/**
	 * The feature id for the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLICATION__OPERAND2 = LOGICAL_BINARY_EXPRESSION__OPERAND2;

	/**
	 * The number of structural features of the '<em>Implication</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLICATION_FEATURE_COUNT = LOGICAL_BINARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.BiImplicationImpl <em>Bi Implication</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.BiImplicationImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getBiImplication()
	 * @generated
	 */
	int BI_IMPLICATION = 10;

	/**
	 * The feature id for the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BI_IMPLICATION__OPERAND1 = LOGICAL_BINARY_EXPRESSION__OPERAND1;

	/**
	 * The feature id for the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BI_IMPLICATION__OPERAND2 = LOGICAL_BINARY_EXPRESSION__OPERAND2;

	/**
	 * The number of structural features of the '<em>Bi Implication</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BI_IMPLICATION_FEATURE_COUNT = LOGICAL_BINARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.BooleanFunctionDefinitionImpl <em>Boolean Function Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.BooleanFunctionDefinitionImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getBooleanFunctionDefinition()
	 * @generated
	 */
	int BOOLEAN_FUNCTION_DEFINITION = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FUNCTION_DEFINITION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Number Of Arguments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FUNCTION_DEFINITION__NUMBER_OF_ARGUMENTS = 1;

	/**
	 * The number of structural features of the '<em>Boolean Function Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FUNCTION_DEFINITION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.IntConstantImpl <em>Int Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.IntConstantImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getIntConstant()
	 * @generated
	 */
	int INT_CONSTANT = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_CONSTANT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Int Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_CONSTANT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.OntoUMLZ3SystemImpl <em>Onto UMLZ3 System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.OntoUMLZ3SystemImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getOntoUMLZ3System()
	 * @generated
	 */
	int ONTO_UMLZ3_SYSTEM = 14;

	/**
	 * The feature id for the '<em><b>Functions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTO_UMLZ3_SYSTEM__FUNCTIONS = 0;

	/**
	 * The feature id for the '<em><b>Constants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTO_UMLZ3_SYSTEM__CONSTANTS = 1;

	/**
	 * The feature id for the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTO_UMLZ3_SYSTEM__FILE_NAME = 2;

	/**
	 * The feature id for the '<em><b>Formulas</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTO_UMLZ3_SYSTEM__FORMULAS = 3;

	/**
	 * The number of structural features of the '<em>Onto UMLZ3 System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTO_UMLZ3_SYSTEM_FEATURE_COUNT = 4;


	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.z3py.impl.EqualityImpl <em>Equality</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.z3py.impl.EqualityImpl
	 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getEquality()
	 * @generated
	 */
	int EQUALITY = 15;

	/**
	 * The feature id for the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY__OPERAND1 = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY__OPERAND2 = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Equality</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see br.ufes.inf.nemo.z3py.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.FunctionCall <em>Function Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Call</em>'.
	 * @see br.ufes.inf.nemo.z3py.FunctionCall
	 * @generated
	 */
	EClass getFunctionCall();

	/**
	 * Returns the meta object for the reference '{@link br.ufes.inf.nemo.z3py.FunctionCall#getCalledFunction <em>Called Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Called Function</em>'.
	 * @see br.ufes.inf.nemo.z3py.FunctionCall#getCalledFunction()
	 * @see #getFunctionCall()
	 * @generated
	 */
	EReference getFunctionCall_CalledFunction();

	/**
	 * Returns the meta object for the reference list '{@link br.ufes.inf.nemo.z3py.FunctionCall#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Arguments</em>'.
	 * @see br.ufes.inf.nemo.z3py.FunctionCall#getArguments()
	 * @see #getFunctionCall()
	 * @generated
	 */
	EReference getFunctionCall_Arguments();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.Quantification <em>Quantification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quantification</em>'.
	 * @see br.ufes.inf.nemo.z3py.Quantification
	 * @generated
	 */
	EClass getQuantification();

	/**
	 * Returns the meta object for the reference '{@link br.ufes.inf.nemo.z3py.Quantification#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Expression</em>'.
	 * @see br.ufes.inf.nemo.z3py.Quantification#getExpression()
	 * @see #getQuantification()
	 * @generated
	 */
	EReference getQuantification_Expression();

	/**
	 * Returns the meta object for the reference list '{@link br.ufes.inf.nemo.z3py.Quantification#getQuantifiesOver <em>Quantifies Over</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Quantifies Over</em>'.
	 * @see br.ufes.inf.nemo.z3py.Quantification#getQuantifiesOver()
	 * @see #getQuantification()
	 * @generated
	 */
	EReference getQuantification_QuantifiesOver();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.z3py.Quantification#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comments</em>'.
	 * @see br.ufes.inf.nemo.z3py.Quantification#getComments()
	 * @see #getQuantification()
	 * @generated
	 */
	EAttribute getQuantification_Comments();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.UniversalQuantification <em>Universal Quantification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Universal Quantification</em>'.
	 * @see br.ufes.inf.nemo.z3py.UniversalQuantification
	 * @generated
	 */
	EClass getUniversalQuantification();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.ExistentialQuantification <em>Existential Quantification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Existential Quantification</em>'.
	 * @see br.ufes.inf.nemo.z3py.ExistentialQuantification
	 * @generated
	 */
	EClass getExistentialQuantification();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.Conjunction <em>Conjunction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conjunction</em>'.
	 * @see br.ufes.inf.nemo.z3py.Conjunction
	 * @generated
	 */
	EClass getConjunction();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.Disjunction <em>Disjunction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Disjunction</em>'.
	 * @see br.ufes.inf.nemo.z3py.Disjunction
	 * @generated
	 */
	EClass getDisjunction();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.ExclusiveDisjunction <em>Exclusive Disjunction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exclusive Disjunction</em>'.
	 * @see br.ufes.inf.nemo.z3py.ExclusiveDisjunction
	 * @generated
	 */
	EClass getExclusiveDisjunction();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.LogicalNegation <em>Logical Negation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical Negation</em>'.
	 * @see br.ufes.inf.nemo.z3py.LogicalNegation
	 * @generated
	 */
	EClass getLogicalNegation();

	/**
	 * Returns the meta object for the reference '{@link br.ufes.inf.nemo.z3py.LogicalNegation#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand</em>'.
	 * @see br.ufes.inf.nemo.z3py.LogicalNegation#getOperand()
	 * @see #getLogicalNegation()
	 * @generated
	 */
	EReference getLogicalNegation_Operand();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.Implication <em>Implication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implication</em>'.
	 * @see br.ufes.inf.nemo.z3py.Implication
	 * @generated
	 */
	EClass getImplication();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.BiImplication <em>Bi Implication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bi Implication</em>'.
	 * @see br.ufes.inf.nemo.z3py.BiImplication
	 * @generated
	 */
	EClass getBiImplication();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.LogicalBinaryExpression <em>Logical Binary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical Binary Expression</em>'.
	 * @see br.ufes.inf.nemo.z3py.LogicalBinaryExpression
	 * @generated
	 */
	EClass getLogicalBinaryExpression();

	/**
	 * Returns the meta object for the reference '{@link br.ufes.inf.nemo.z3py.LogicalBinaryExpression#getOperand1 <em>Operand1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand1</em>'.
	 * @see br.ufes.inf.nemo.z3py.LogicalBinaryExpression#getOperand1()
	 * @see #getLogicalBinaryExpression()
	 * @generated
	 */
	EReference getLogicalBinaryExpression_Operand1();

	/**
	 * Returns the meta object for the reference '{@link br.ufes.inf.nemo.z3py.LogicalBinaryExpression#getOperand2 <em>Operand2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand2</em>'.
	 * @see br.ufes.inf.nemo.z3py.LogicalBinaryExpression#getOperand2()
	 * @see #getLogicalBinaryExpression()
	 * @generated
	 */
	EReference getLogicalBinaryExpression_Operand2();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.BooleanFunctionDefinition <em>Boolean Function Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Function Definition</em>'.
	 * @see br.ufes.inf.nemo.z3py.BooleanFunctionDefinition
	 * @generated
	 */
	EClass getBooleanFunctionDefinition();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.z3py.BooleanFunctionDefinition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see br.ufes.inf.nemo.z3py.BooleanFunctionDefinition#getName()
	 * @see #getBooleanFunctionDefinition()
	 * @generated
	 */
	EAttribute getBooleanFunctionDefinition_Name();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.z3py.BooleanFunctionDefinition#getNumberOfArguments <em>Number Of Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Arguments</em>'.
	 * @see br.ufes.inf.nemo.z3py.BooleanFunctionDefinition#getNumberOfArguments()
	 * @see #getBooleanFunctionDefinition()
	 * @generated
	 */
	EAttribute getBooleanFunctionDefinition_NumberOfArguments();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.IntConstant <em>Int Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Int Constant</em>'.
	 * @see br.ufes.inf.nemo.z3py.IntConstant
	 * @generated
	 */
	EClass getIntConstant();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.z3py.IntConstant#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see br.ufes.inf.nemo.z3py.IntConstant#getName()
	 * @see #getIntConstant()
	 * @generated
	 */
	EAttribute getIntConstant_Name();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.OntoUMLZ3System <em>Onto UMLZ3 System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Onto UMLZ3 System</em>'.
	 * @see br.ufes.inf.nemo.z3py.OntoUMLZ3System
	 * @generated
	 */
	EClass getOntoUMLZ3System();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.z3py.OntoUMLZ3System#getFunctions <em>Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Functions</em>'.
	 * @see br.ufes.inf.nemo.z3py.OntoUMLZ3System#getFunctions()
	 * @see #getOntoUMLZ3System()
	 * @generated
	 */
	EReference getOntoUMLZ3System_Functions();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.z3py.OntoUMLZ3System#getConstants <em>Constants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constants</em>'.
	 * @see br.ufes.inf.nemo.z3py.OntoUMLZ3System#getConstants()
	 * @see #getOntoUMLZ3System()
	 * @generated
	 */
	EReference getOntoUMLZ3System_Constants();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.z3py.OntoUMLZ3System#getFileName <em>File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Name</em>'.
	 * @see br.ufes.inf.nemo.z3py.OntoUMLZ3System#getFileName()
	 * @see #getOntoUMLZ3System()
	 * @generated
	 */
	EAttribute getOntoUMLZ3System_FileName();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.z3py.OntoUMLZ3System#getFormulas <em>Formulas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Formulas</em>'.
	 * @see br.ufes.inf.nemo.z3py.OntoUMLZ3System#getFormulas()
	 * @see #getOntoUMLZ3System()
	 * @generated
	 */
	EReference getOntoUMLZ3System_Formulas();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.z3py.Equality <em>Equality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Equality</em>'.
	 * @see br.ufes.inf.nemo.z3py.Equality
	 * @generated
	 */
	EClass getEquality();

	/**
	 * Returns the meta object for the reference '{@link br.ufes.inf.nemo.z3py.Equality#getOperand1 <em>Operand1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand1</em>'.
	 * @see br.ufes.inf.nemo.z3py.Equality#getOperand1()
	 * @see #getEquality()
	 * @generated
	 */
	EReference getEquality_Operand1();

	/**
	 * Returns the meta object for the reference '{@link br.ufes.inf.nemo.z3py.Equality#getOperand2 <em>Operand2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand2</em>'.
	 * @see br.ufes.inf.nemo.z3py.Equality#getOperand2()
	 * @see #getEquality()
	 * @generated
	 */
	EReference getEquality_Operand2();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Z3pyFactory getZ3pyFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.ExpressionImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.FunctionCallImpl <em>Function Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.FunctionCallImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getFunctionCall()
		 * @generated
		 */
		EClass FUNCTION_CALL = eINSTANCE.getFunctionCall();

		/**
		 * The meta object literal for the '<em><b>Called Function</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_CALL__CALLED_FUNCTION = eINSTANCE.getFunctionCall_CalledFunction();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_CALL__ARGUMENTS = eINSTANCE.getFunctionCall_Arguments();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.QuantificationImpl <em>Quantification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.QuantificationImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getQuantification()
		 * @generated
		 */
		EClass QUANTIFICATION = eINSTANCE.getQuantification();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUANTIFICATION__EXPRESSION = eINSTANCE.getQuantification_Expression();

		/**
		 * The meta object literal for the '<em><b>Quantifies Over</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUANTIFICATION__QUANTIFIES_OVER = eINSTANCE.getQuantification_QuantifiesOver();

		/**
		 * The meta object literal for the '<em><b>Comments</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUANTIFICATION__COMMENTS = eINSTANCE.getQuantification_Comments();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.UniversalQuantificationImpl <em>Universal Quantification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.UniversalQuantificationImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getUniversalQuantification()
		 * @generated
		 */
		EClass UNIVERSAL_QUANTIFICATION = eINSTANCE.getUniversalQuantification();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.ExistentialQuantificationImpl <em>Existential Quantification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.ExistentialQuantificationImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getExistentialQuantification()
		 * @generated
		 */
		EClass EXISTENTIAL_QUANTIFICATION = eINSTANCE.getExistentialQuantification();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.ConjunctionImpl <em>Conjunction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.ConjunctionImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getConjunction()
		 * @generated
		 */
		EClass CONJUNCTION = eINSTANCE.getConjunction();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.DisjunctionImpl <em>Disjunction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.DisjunctionImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getDisjunction()
		 * @generated
		 */
		EClass DISJUNCTION = eINSTANCE.getDisjunction();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.ExclusiveDisjunctionImpl <em>Exclusive Disjunction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.ExclusiveDisjunctionImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getExclusiveDisjunction()
		 * @generated
		 */
		EClass EXCLUSIVE_DISJUNCTION = eINSTANCE.getExclusiveDisjunction();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.LogicalNegationImpl <em>Logical Negation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.LogicalNegationImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getLogicalNegation()
		 * @generated
		 */
		EClass LOGICAL_NEGATION = eINSTANCE.getLogicalNegation();

		/**
		 * The meta object literal for the '<em><b>Operand</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGICAL_NEGATION__OPERAND = eINSTANCE.getLogicalNegation_Operand();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.ImplicationImpl <em>Implication</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.ImplicationImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getImplication()
		 * @generated
		 */
		EClass IMPLICATION = eINSTANCE.getImplication();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.BiImplicationImpl <em>Bi Implication</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.BiImplicationImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getBiImplication()
		 * @generated
		 */
		EClass BI_IMPLICATION = eINSTANCE.getBiImplication();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.LogicalBinaryExpressionImpl <em>Logical Binary Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.LogicalBinaryExpressionImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getLogicalBinaryExpression()
		 * @generated
		 */
		EClass LOGICAL_BINARY_EXPRESSION = eINSTANCE.getLogicalBinaryExpression();

		/**
		 * The meta object literal for the '<em><b>Operand1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGICAL_BINARY_EXPRESSION__OPERAND1 = eINSTANCE.getLogicalBinaryExpression_Operand1();

		/**
		 * The meta object literal for the '<em><b>Operand2</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGICAL_BINARY_EXPRESSION__OPERAND2 = eINSTANCE.getLogicalBinaryExpression_Operand2();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.BooleanFunctionDefinitionImpl <em>Boolean Function Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.BooleanFunctionDefinitionImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getBooleanFunctionDefinition()
		 * @generated
		 */
		EClass BOOLEAN_FUNCTION_DEFINITION = eINSTANCE.getBooleanFunctionDefinition();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_FUNCTION_DEFINITION__NAME = eINSTANCE.getBooleanFunctionDefinition_Name();

		/**
		 * The meta object literal for the '<em><b>Number Of Arguments</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_FUNCTION_DEFINITION__NUMBER_OF_ARGUMENTS = eINSTANCE.getBooleanFunctionDefinition_NumberOfArguments();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.IntConstantImpl <em>Int Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.IntConstantImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getIntConstant()
		 * @generated
		 */
		EClass INT_CONSTANT = eINSTANCE.getIntConstant();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INT_CONSTANT__NAME = eINSTANCE.getIntConstant_Name();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.OntoUMLZ3SystemImpl <em>Onto UMLZ3 System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.OntoUMLZ3SystemImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getOntoUMLZ3System()
		 * @generated
		 */
		EClass ONTO_UMLZ3_SYSTEM = eINSTANCE.getOntoUMLZ3System();

		/**
		 * The meta object literal for the '<em><b>Functions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ONTO_UMLZ3_SYSTEM__FUNCTIONS = eINSTANCE.getOntoUMLZ3System_Functions();

		/**
		 * The meta object literal for the '<em><b>Constants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ONTO_UMLZ3_SYSTEM__CONSTANTS = eINSTANCE.getOntoUMLZ3System_Constants();

		/**
		 * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONTO_UMLZ3_SYSTEM__FILE_NAME = eINSTANCE.getOntoUMLZ3System_FileName();

		/**
		 * The meta object literal for the '<em><b>Formulas</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ONTO_UMLZ3_SYSTEM__FORMULAS = eINSTANCE.getOntoUMLZ3System_Formulas();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.z3py.impl.EqualityImpl <em>Equality</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.z3py.impl.EqualityImpl
		 * @see br.ufes.inf.nemo.z3py.impl.Z3pyPackageImpl#getEquality()
		 * @generated
		 */
		EClass EQUALITY = eINSTANCE.getEquality();

		/**
		 * The meta object literal for the '<em><b>Operand1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EQUALITY__OPERAND1 = eINSTANCE.getEquality_Operand1();

		/**
		 * The meta object literal for the '<em><b>Operand2</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EQUALITY__OPERAND2 = eINSTANCE.getEquality_Operand2();

	}

} //Z3pyPackage
