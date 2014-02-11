/**
 */
package br.ufes.inf.nemo.z3py.impl;

import br.ufes.inf.nemo.z3py.BiImplication;
import br.ufes.inf.nemo.z3py.BooleanFunctionDefinition;
import br.ufes.inf.nemo.z3py.Conjunction;
import br.ufes.inf.nemo.z3py.Disjunction;
import br.ufes.inf.nemo.z3py.Equality;
import br.ufes.inf.nemo.z3py.ExclusiveDisjunction;
import br.ufes.inf.nemo.z3py.ExistentialQuantification;
import br.ufes.inf.nemo.z3py.Expression;
import br.ufes.inf.nemo.z3py.FunctionCall;
import br.ufes.inf.nemo.z3py.Implication;
import br.ufes.inf.nemo.z3py.IntConstant;
import br.ufes.inf.nemo.z3py.LogicalBinaryExpression;
import br.ufes.inf.nemo.z3py.LogicalNegation;
import br.ufes.inf.nemo.z3py.OntoUMLZ3System;
import br.ufes.inf.nemo.z3py.Quantification;
import br.ufes.inf.nemo.z3py.UniversalQuantification;
import br.ufes.inf.nemo.z3py.Z3pyFactory;
import br.ufes.inf.nemo.z3py.Z3pyPackage;

import br.ufes.inf.nemo.z3py.util.Z3pyValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Z3pyPackageImpl extends EPackageImpl implements Z3pyPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionCallEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass quantificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass universalQuantificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass existentialQuantificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conjunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass disjunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exclusiveDisjunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicalNegationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass implicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass biImplicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicalBinaryExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanFunctionDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intConstantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ontoUMLZ3SystemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass equalityEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Z3pyPackageImpl() {
		super(eNS_URI, Z3pyFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link Z3pyPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Z3pyPackage init() {
		if (isInited) return (Z3pyPackage)EPackage.Registry.INSTANCE.getEPackage(Z3pyPackage.eNS_URI);

		// Obtain or create and register package
		Z3pyPackageImpl theZ3pyPackage = (Z3pyPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Z3pyPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Z3pyPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theZ3pyPackage.createPackageContents();

		// Initialize created meta-data
		theZ3pyPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theZ3pyPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return Z3pyValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theZ3pyPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Z3pyPackage.eNS_URI, theZ3pyPackage);
		return theZ3pyPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpression() {
		return expressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionCall() {
		return functionCallEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionCall_CalledFunction() {
		return (EReference)functionCallEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionCall_Arguments() {
		return (EReference)functionCallEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuantification() {
		return quantificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuantification_Expression() {
		return (EReference)quantificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuantification_QuantifiesOver() {
		return (EReference)quantificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuantification_Comments() {
		return (EAttribute)quantificationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUniversalQuantification() {
		return universalQuantificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExistentialQuantification() {
		return existentialQuantificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConjunction() {
		return conjunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDisjunction() {
		return disjunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExclusiveDisjunction() {
		return exclusiveDisjunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalNegation() {
		return logicalNegationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalNegation_Operand() {
		return (EReference)logicalNegationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImplication() {
		return implicationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBiImplication() {
		return biImplicationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalBinaryExpression() {
		return logicalBinaryExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalBinaryExpression_Operand1() {
		return (EReference)logicalBinaryExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalBinaryExpression_Operand2() {
		return (EReference)logicalBinaryExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanFunctionDefinition() {
		return booleanFunctionDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanFunctionDefinition_Name() {
		return (EAttribute)booleanFunctionDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanFunctionDefinition_NumberOfArguments() {
		return (EAttribute)booleanFunctionDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntConstant() {
		return intConstantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntConstant_Name() {
		return (EAttribute)intConstantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOntoUMLZ3System() {
		return ontoUMLZ3SystemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOntoUMLZ3System_Functions() {
		return (EReference)ontoUMLZ3SystemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOntoUMLZ3System_Constants() {
		return (EReference)ontoUMLZ3SystemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOntoUMLZ3System_FileName() {
		return (EAttribute)ontoUMLZ3SystemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOntoUMLZ3System_Formulas() {
		return (EReference)ontoUMLZ3SystemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEquality() {
		return equalityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEquality_Operand1() {
		return (EReference)equalityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEquality_Operand2() {
		return (EReference)equalityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Z3pyFactory getZ3pyFactory() {
		return (Z3pyFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		expressionEClass = createEClass(EXPRESSION);

		functionCallEClass = createEClass(FUNCTION_CALL);
		createEReference(functionCallEClass, FUNCTION_CALL__CALLED_FUNCTION);
		createEReference(functionCallEClass, FUNCTION_CALL__ARGUMENTS);

		quantificationEClass = createEClass(QUANTIFICATION);
		createEReference(quantificationEClass, QUANTIFICATION__EXPRESSION);
		createEReference(quantificationEClass, QUANTIFICATION__QUANTIFIES_OVER);
		createEAttribute(quantificationEClass, QUANTIFICATION__COMMENTS);

		universalQuantificationEClass = createEClass(UNIVERSAL_QUANTIFICATION);

		existentialQuantificationEClass = createEClass(EXISTENTIAL_QUANTIFICATION);

		conjunctionEClass = createEClass(CONJUNCTION);

		disjunctionEClass = createEClass(DISJUNCTION);

		exclusiveDisjunctionEClass = createEClass(EXCLUSIVE_DISJUNCTION);

		logicalNegationEClass = createEClass(LOGICAL_NEGATION);
		createEReference(logicalNegationEClass, LOGICAL_NEGATION__OPERAND);

		implicationEClass = createEClass(IMPLICATION);

		biImplicationEClass = createEClass(BI_IMPLICATION);

		logicalBinaryExpressionEClass = createEClass(LOGICAL_BINARY_EXPRESSION);
		createEReference(logicalBinaryExpressionEClass, LOGICAL_BINARY_EXPRESSION__OPERAND1);
		createEReference(logicalBinaryExpressionEClass, LOGICAL_BINARY_EXPRESSION__OPERAND2);

		booleanFunctionDefinitionEClass = createEClass(BOOLEAN_FUNCTION_DEFINITION);
		createEAttribute(booleanFunctionDefinitionEClass, BOOLEAN_FUNCTION_DEFINITION__NAME);
		createEAttribute(booleanFunctionDefinitionEClass, BOOLEAN_FUNCTION_DEFINITION__NUMBER_OF_ARGUMENTS);

		intConstantEClass = createEClass(INT_CONSTANT);
		createEAttribute(intConstantEClass, INT_CONSTANT__NAME);

		ontoUMLZ3SystemEClass = createEClass(ONTO_UMLZ3_SYSTEM);
		createEReference(ontoUMLZ3SystemEClass, ONTO_UMLZ3_SYSTEM__FUNCTIONS);
		createEReference(ontoUMLZ3SystemEClass, ONTO_UMLZ3_SYSTEM__CONSTANTS);
		createEAttribute(ontoUMLZ3SystemEClass, ONTO_UMLZ3_SYSTEM__FILE_NAME);
		createEReference(ontoUMLZ3SystemEClass, ONTO_UMLZ3_SYSTEM__FORMULAS);

		equalityEClass = createEClass(EQUALITY);
		createEReference(equalityEClass, EQUALITY__OPERAND1);
		createEReference(equalityEClass, EQUALITY__OPERAND2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		functionCallEClass.getESuperTypes().add(this.getExpression());
		quantificationEClass.getESuperTypes().add(this.getExpression());
		universalQuantificationEClass.getESuperTypes().add(this.getQuantification());
		existentialQuantificationEClass.getESuperTypes().add(this.getQuantification());
		conjunctionEClass.getESuperTypes().add(this.getLogicalBinaryExpression());
		disjunctionEClass.getESuperTypes().add(this.getLogicalBinaryExpression());
		exclusiveDisjunctionEClass.getESuperTypes().add(this.getLogicalBinaryExpression());
		logicalNegationEClass.getESuperTypes().add(this.getExpression());
		implicationEClass.getESuperTypes().add(this.getLogicalBinaryExpression());
		biImplicationEClass.getESuperTypes().add(this.getLogicalBinaryExpression());
		logicalBinaryExpressionEClass.getESuperTypes().add(this.getExpression());
		equalityEClass.getESuperTypes().add(this.getExpression());

		// Initialize classes and features; add operations and parameters
		initEClass(expressionEClass, Expression.class, "Expression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(functionCallEClass, FunctionCall.class, "FunctionCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFunctionCall_CalledFunction(), this.getBooleanFunctionDefinition(), null, "calledFunction", null, 1, 1, FunctionCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionCall_Arguments(), this.getIntConstant(), null, "arguments", null, 1, -1, FunctionCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(quantificationEClass, Quantification.class, "Quantification", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getQuantification_Expression(), this.getExpression(), null, "expression", null, 1, 1, Quantification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQuantification_QuantifiesOver(), this.getIntConstant(), null, "quantifiesOver", null, 1, -1, Quantification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getQuantification_Comments(), ecorePackage.getEString(), "comments", null, 0, 1, Quantification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(universalQuantificationEClass, UniversalQuantification.class, "UniversalQuantification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(existentialQuantificationEClass, ExistentialQuantification.class, "ExistentialQuantification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(conjunctionEClass, Conjunction.class, "Conjunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(disjunctionEClass, Disjunction.class, "Disjunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(exclusiveDisjunctionEClass, ExclusiveDisjunction.class, "ExclusiveDisjunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(logicalNegationEClass, LogicalNegation.class, "LogicalNegation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLogicalNegation_Operand(), this.getExpression(), null, "operand", null, 1, 1, LogicalNegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(implicationEClass, Implication.class, "Implication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(biImplicationEClass, BiImplication.class, "BiImplication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(logicalBinaryExpressionEClass, LogicalBinaryExpression.class, "LogicalBinaryExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLogicalBinaryExpression_Operand1(), this.getExpression(), null, "operand1", null, 1, 1, LogicalBinaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLogicalBinaryExpression_Operand2(), this.getExpression(), null, "operand2", null, 1, 1, LogicalBinaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanFunctionDefinitionEClass, BooleanFunctionDefinition.class, "BooleanFunctionDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanFunctionDefinition_Name(), ecorePackage.getEString(), "name", null, 1, 1, BooleanFunctionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBooleanFunctionDefinition_NumberOfArguments(), ecorePackage.getEInt(), "numberOfArguments", null, 1, 1, BooleanFunctionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intConstantEClass, IntConstant.class, "IntConstant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntConstant_Name(), ecorePackage.getEString(), "name", null, 1, 1, IntConstant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ontoUMLZ3SystemEClass, OntoUMLZ3System.class, "OntoUMLZ3System", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOntoUMLZ3System_Functions(), this.getBooleanFunctionDefinition(), null, "functions", null, 1, -1, OntoUMLZ3System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOntoUMLZ3System_Constants(), this.getIntConstant(), null, "constants", null, 1, -1, OntoUMLZ3System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOntoUMLZ3System_FileName(), ecorePackage.getEString(), "fileName", null, 1, 1, OntoUMLZ3System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOntoUMLZ3System_Formulas(), this.getQuantification(), null, "formulas", null, 1, -1, OntoUMLZ3System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(equalityEClass, Equality.class, "Equality", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEquality_Operand1(), this.getIntConstant(), null, "operand1", null, 1, 1, Equality.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEquality_Operand2(), this.getIntConstant(), null, "operand2", null, 1, 1, Equality.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot
		createPivotAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";		
		addAnnotation
		  (this, 
		   source, 
		   new String[] {
			 "invocationDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
			 "settingDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
			 "validationDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot"
		   });		
		addAnnotation
		  (functionCallEClass, 
		   source, 
		   new String[] {
			 "constraints", "FunctionCallNumberOfArguments"
		   });			
		addAnnotation
		  (quantificationEClass, 
		   source, 
		   new String[] {
			 "constraints", "IrreflexiveQuant"
		   });	
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createPivotAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot";				
		addAnnotation
		  (functionCallEClass, 
		   source, 
		   new String[] {
			 "FunctionCallNumberOfArguments", "self.calledFunction.numberOfArguments = self.arguments->size()"
		   });			
		addAnnotation
		  (quantificationEClass, 
		   source, 
		   new String[] {
			 "IrreflexiveQuant", "self.expression <> self"
		   });
	}

} //Z3pyPackageImpl
