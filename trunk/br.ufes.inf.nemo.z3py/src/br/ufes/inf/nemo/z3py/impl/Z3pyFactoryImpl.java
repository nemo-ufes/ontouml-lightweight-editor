/**
 */
package br.ufes.inf.nemo.z3py.impl;

import br.ufes.inf.nemo.z3py.*;

import org.eclipse.emf.ecore.EClass;
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
public class Z3pyFactoryImpl extends EFactoryImpl implements Z3pyFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Z3pyFactory init() {
		try {
			Z3pyFactory theZ3pyFactory = (Z3pyFactory)EPackage.Registry.INSTANCE.getEFactory("http://z3py/1.0"); 
			if (theZ3pyFactory != null) {
				return theZ3pyFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Z3pyFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Z3pyFactoryImpl() {
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
			case Z3pyPackage.FUNCTION_CALL: return createFunctionCall();
			case Z3pyPackage.UNIVERSAL: return createUniversal();
			case Z3pyPackage.EXISTENTIAL: return createExistential();
			case Z3pyPackage.CONJUNCTION: return createConjunction();
			case Z3pyPackage.DISJUNCTION: return createDisjunction();
			case Z3pyPackage.EXCLUSIVE_DISJUNCTION: return createExclusiveDisjunction();
			case Z3pyPackage.LOGICAL_NEGATION: return createLogicalNegation();
			case Z3pyPackage.IMPLICATION: return createImplication();
			case Z3pyPackage.EQUIVALENCE: return createEquivalence();
			case Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION: return createBooleanFunctionDefinition();
			case Z3pyPackage.INT_CONSTANT: return createIntConstant();
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM: return createOntoUMLZ3System();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionCall createFunctionCall() {
		FunctionCallImpl functionCall = new FunctionCallImpl();
		return functionCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Universal createUniversal() {
		UniversalImpl universal = new UniversalImpl();
		return universal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Existential createExistential() {
		ExistentialImpl existential = new ExistentialImpl();
		return existential;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Conjunction createConjunction() {
		ConjunctionImpl conjunction = new ConjunctionImpl();
		return conjunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Disjunction createDisjunction() {
		DisjunctionImpl disjunction = new DisjunctionImpl();
		return disjunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExclusiveDisjunction createExclusiveDisjunction() {
		ExclusiveDisjunctionImpl exclusiveDisjunction = new ExclusiveDisjunctionImpl();
		return exclusiveDisjunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalNegation createLogicalNegation() {
		LogicalNegationImpl logicalNegation = new LogicalNegationImpl();
		return logicalNegation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Implication createImplication() {
		ImplicationImpl implication = new ImplicationImpl();
		return implication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Equivalence createEquivalence() {
		EquivalenceImpl equivalence = new EquivalenceImpl();
		return equivalence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanFunctionDefinition createBooleanFunctionDefinition() {
		BooleanFunctionDefinitionImpl booleanFunctionDefinition = new BooleanFunctionDefinitionImpl();
		return booleanFunctionDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntConstant createIntConstant() {
		IntConstantImpl intConstant = new IntConstantImpl();
		return intConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntoUMLZ3System createOntoUMLZ3System() {
		OntoUMLZ3SystemImpl ontoUMLZ3System = new OntoUMLZ3SystemImpl();
		return ontoUMLZ3System;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Z3pyPackage getZ3pyPackage() {
		return (Z3pyPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Z3pyPackage getPackage() {
		return Z3pyPackage.eINSTANCE;
	}

} //Z3pyFactoryImpl
