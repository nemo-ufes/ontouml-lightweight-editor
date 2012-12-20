/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.AlloyModule;
import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.ArrowOperation;
import br.ufes.inf.nemo.alloy.AssertionDeclaration;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.Block;
import br.ufes.inf.nemo.alloy.CommandDeclaration;
import br.ufes.inf.nemo.alloy.CompareOperation;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.ComprehensionExpression;
import br.ufes.inf.nemo.alloy.Constant;
import br.ufes.inf.nemo.alloy.ConstantExpression;
import br.ufes.inf.nemo.alloy.Declaration;
import br.ufes.inf.nemo.alloy.DetailedScope;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.alloy.EnumDeclaration;
import br.ufes.inf.nemo.alloy.Expression;
import br.ufes.inf.nemo.alloy.ExternalReference;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.FunctionDeclaration;
import br.ufes.inf.nemo.alloy.FunctionInvocation;
import br.ufes.inf.nemo.alloy.GenericScope;
import br.ufes.inf.nemo.alloy.ImpliesOperation;
import br.ufes.inf.nemo.alloy.ImporterModule;
import br.ufes.inf.nemo.alloy.Inheritance;
import br.ufes.inf.nemo.alloy.LetDeclaration;
import br.ufes.inf.nemo.alloy.ModuleImportation;
import br.ufes.inf.nemo.alloy.Multiplicity;
import br.ufes.inf.nemo.alloy.Paragraph;
import br.ufes.inf.nemo.alloy.ParametrizedModule;
import br.ufes.inf.nemo.alloy.PredicateDeclaration;
import br.ufes.inf.nemo.alloy.PredicateInvocation;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.alloy.Quantificator;
import br.ufes.inf.nemo.alloy.ScopeSpecification;
import br.ufes.inf.nemo.alloy.Scopeable;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.SignatureParameter;
import br.ufes.inf.nemo.alloy.SignatureReference;
import br.ufes.inf.nemo.alloy.UnaryOperation;
import br.ufes.inf.nemo.alloy.UnaryOperator;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.alloy.VariableReference;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AlloyPackageImpl extends EPackageImpl implements AlloyPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass alloyModuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parametrizedModuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass signatureParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass importerModuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleImportationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass paragraphEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass signatureDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass factDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass predicateDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assertionDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commandDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inheritanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scopeSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericScopeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass detailedScopeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scopeableEClass = null;

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
	private EClass declarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass signatureReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass binaryOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unaryOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass letDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compareOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrowOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constantExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comprehensionExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionInvocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass predicateInvocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass disjointExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass impliesOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass quantificationExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum multiplicityEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum binaryOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum unaryOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum compareOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum constantEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum quantificatorEEnum = null;

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
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AlloyPackageImpl() {
		super(eNS_URI, AlloyFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AlloyPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AlloyPackage init() {
		if (isInited) return (AlloyPackage)EPackage.Registry.INSTANCE.getEPackage(AlloyPackage.eNS_URI);

		// Obtain or create and register package
		AlloyPackageImpl theAlloyPackage = (AlloyPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AlloyPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AlloyPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theAlloyPackage.createPackageContents();

		// Initialize created meta-data
		theAlloyPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAlloyPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AlloyPackage.eNS_URI, theAlloyPackage);
		return theAlloyPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAlloyModule() {
		return alloyModuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAlloyModule_Name() {
		return (EAttribute)alloyModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAlloyModule_Path() {
		return (EAttribute)alloyModuleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAlloyModule_Parameters() {
		return (EReference)alloyModuleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAlloyModule_Paragraph() {
		return (EReference)alloyModuleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAlloyModule_Imports() {
		return (EReference)alloyModuleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParametrizedModule() {
		return parametrizedModuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSignatureParameter() {
		return signatureParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSignatureParameter_Name() {
		return (EAttribute)signatureParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImporterModule() {
		return importerModuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModuleImportation() {
		return moduleImportationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModuleImportation_Name() {
		return (EAttribute)moduleImportationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModuleImportation_Alias() {
		return (EAttribute)moduleImportationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModuleImportation_Path() {
		return (EAttribute)moduleImportationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModuleImportation_Parameters() {
		return (EAttribute)moduleImportationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParagraph() {
		return paragraphEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParagraph_Name() {
		return (EAttribute)paragraphEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSignatureDeclaration() {
		return signatureDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSignatureDeclaration_IsAbstract() {
		return (EAttribute)signatureDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSignatureDeclaration_Multiplicity() {
		return (EAttribute)signatureDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSignatureDeclaration_Relation() {
		return (EReference)signatureDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSignatureDeclaration_Block() {
		return (EReference)signatureDeclarationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSignatureDeclaration_Exists() {
		return (EAttribute)signatureDeclarationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSignatureDeclaration_Inheritance() {
		return (EReference)signatureDeclarationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFactDeclaration() {
		return factDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFactDeclaration_Block() {
		return (EReference)factDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionDeclaration() {
		return functionDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunctionDeclaration_Path() {
		return (EAttribute)functionDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionDeclaration_Parameter() {
		return (EReference)functionDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionDeclaration_Type() {
		return (EReference)functionDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionDeclaration_Block() {
		return (EReference)functionDeclarationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPredicateDeclaration() {
		return predicateDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPredicateDeclaration_Block() {
		return (EReference)predicateDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredicateDeclaration_Path() {
		return (EAttribute)predicateDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPredicateDeclaration_Parameter() {
		return (EReference)predicateDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssertionDeclaration() {
		return assertionDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssertionDeclaration_Block() {
		return (EReference)assertionDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCommandDeclaration() {
		return commandDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCommandDeclaration_Scope() {
		return (EReference)commandDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCommandDeclaration_Block() {
		return (EReference)commandDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCommandDeclaration_Assertion() {
		return (EReference)commandDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCommandDeclaration_Predicate() {
		return (EReference)commandDeclarationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCommandDeclaration_IsRun() {
		return (EAttribute)commandDeclarationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCommandDeclaration_IsCheck() {
		return (EAttribute)commandDeclarationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInheritance() {
		return inheritanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInheritance_Supertype() {
		return (EAttribute)inheritanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInheritance_IsSubset() {
		return (EAttribute)inheritanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInheritance_IsExtension() {
		return (EAttribute)inheritanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScopeSpecification() {
		return scopeSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericScope() {
		return genericScopeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericScope_Scopeable() {
		return (EReference)genericScopeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGenericScope_ScopeSize() {
		return (EAttribute)genericScopeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDetailedScope() {
		return detailedScopeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDetailedScope_Scopeable() {
		return (EReference)detailedScopeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScopeable() {
		return scopeableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScopeable_ScopeSize() {
		return (EAttribute)scopeableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScopeable_IsExactly() {
		return (EAttribute)scopeableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScopeable_Signature() {
		return (EAttribute)scopeableEClass.getEStructuralFeatures().get(2);
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
	public EClass getDeclaration() {
		return declarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeclaration_IsDisjoint() {
		return (EAttribute)declarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeclaration_Expression() {
		return (EReference)declarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeclaration_Variable() {
		return (EReference)declarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeclaration_Multiplicity() {
		return (EAttribute)declarationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSignatureReference() {
		return signatureReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSignatureReference_Signature() {
		return (EAttribute)signatureReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlock() {
		return blockEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBlock_Expression() {
		return (EReference)blockEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBinaryOperation() {
		return binaryOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryOperation_LeftExpression() {
		return (EReference)binaryOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryOperation_RightExpression() {
		return (EReference)binaryOperationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBinaryOperation_Operator() {
		return (EAttribute)binaryOperationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnaryOperation() {
		return unaryOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnaryOperation_Expression() {
		return (EReference)unaryOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnaryOperation_Operator() {
		return (EAttribute)unaryOperationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLetDeclaration() {
		return letDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLetDeclaration_EqualExpression() {
		return (EReference)letDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLetDeclaration_Block() {
		return (EReference)letDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLetDeclaration_Name() {
		return (EAttribute)letDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLetDeclaration_BarExpression() {
		return (EReference)letDeclarationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLetDeclaration_HasBlock() {
		return (EAttribute)letDeclarationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLetDeclaration_HasExpression() {
		return (EAttribute)letDeclarationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompareOperation() {
		return compareOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompareOperation_LeftExpression() {
		return (EReference)compareOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompareOperation_RightExpression() {
		return (EReference)compareOperationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompareOperation_Operator() {
		return (EAttribute)compareOperationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompareOperation_Negation() {
		return (EAttribute)compareOperationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableReference() {
		return variableReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableReference_PrefixAt() {
		return (EAttribute)variableReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableReference_Variable() {
		return (EAttribute)variableReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrowOperation() {
		return arrowOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrowOperation_LeftMultiplicity() {
		return (EAttribute)arrowOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrowOperation_RightMultiplicity() {
		return (EAttribute)arrowOperationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrowOperation_LeftExpression() {
		return (EReference)arrowOperationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrowOperation_RightExpression() {
		return (EReference)arrowOperationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstantExpression() {
		return constantExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstantExpression_Constant() {
		return (EAttribute)constantExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalReference() {
		return externalReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExternalReference_Name() {
		return (EAttribute)externalReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExternalReference_Path() {
		return (EAttribute)externalReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComprehensionExpression() {
		return comprehensionExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComprehensionExpression_Declaration() {
		return (EReference)comprehensionExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComprehensionExpression_Block() {
		return (EReference)comprehensionExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComprehensionExpression_Expression() {
		return (EReference)comprehensionExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComprehensionExpression_HasBlock() {
		return (EAttribute)comprehensionExpressionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComprehensionExpression_HasExpression() {
		return (EAttribute)comprehensionExpressionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionInvocation() {
		return functionInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionInvocation_Parameter() {
		return (EReference)functionInvocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunctionInvocation_Function() {
		return (EAttribute)functionInvocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPredicateInvocation() {
		return predicateInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPredicateInvocation_Parameter() {
		return (EReference)predicateInvocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredicateInvocation_Predicate() {
		return (EAttribute)predicateInvocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDisjointExpression() {
		return disjointExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDisjointExpression_Set() {
		return (EReference)disjointExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImpliesOperation() {
		return impliesOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImpliesOperation_Else() {
		return (EReference)impliesOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImpliesOperation_Implication() {
		return (EReference)impliesOperationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImpliesOperation_Condition() {
		return (EReference)impliesOperationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuantificationExpression() {
		return quantificationExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuantificationExpression_Quantificator() {
		return (EAttribute)quantificationExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuantificationExpression_Declaration() {
		return (EReference)quantificationExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuantificationExpression_Expression() {
		return (EReference)quantificationExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuantificationExpression_Block() {
		return (EReference)quantificationExpressionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariable() {
		return variableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariable_Name() {
		return (EAttribute)variableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariable_Declaration() {
		return (EReference)variableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumDeclaration() {
		return enumDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnumDeclaration_Literals() {
		return (EAttribute)enumDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMultiplicity() {
		return multiplicityEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBinaryOperator() {
		return binaryOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getUnaryOperator() {
		return unaryOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCompareOperator() {
		return compareOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getConstant() {
		return constantEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getQuantificator() {
		return quantificatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlloyFactory getAlloyFactory() {
		return (AlloyFactory)getEFactoryInstance();
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
		alloyModuleEClass = createEClass(ALLOY_MODULE);
		createEAttribute(alloyModuleEClass, ALLOY_MODULE__NAME);
		createEAttribute(alloyModuleEClass, ALLOY_MODULE__PATH);
		createEReference(alloyModuleEClass, ALLOY_MODULE__PARAMETERS);
		createEReference(alloyModuleEClass, ALLOY_MODULE__PARAGRAPH);
		createEReference(alloyModuleEClass, ALLOY_MODULE__IMPORTS);

		parametrizedModuleEClass = createEClass(PARAMETRIZED_MODULE);

		signatureParameterEClass = createEClass(SIGNATURE_PARAMETER);
		createEAttribute(signatureParameterEClass, SIGNATURE_PARAMETER__NAME);

		importerModuleEClass = createEClass(IMPORTER_MODULE);

		moduleImportationEClass = createEClass(MODULE_IMPORTATION);
		createEAttribute(moduleImportationEClass, MODULE_IMPORTATION__NAME);
		createEAttribute(moduleImportationEClass, MODULE_IMPORTATION__ALIAS);
		createEAttribute(moduleImportationEClass, MODULE_IMPORTATION__PATH);
		createEAttribute(moduleImportationEClass, MODULE_IMPORTATION__PARAMETERS);

		paragraphEClass = createEClass(PARAGRAPH);
		createEAttribute(paragraphEClass, PARAGRAPH__NAME);

		signatureDeclarationEClass = createEClass(SIGNATURE_DECLARATION);
		createEAttribute(signatureDeclarationEClass, SIGNATURE_DECLARATION__IS_ABSTRACT);
		createEAttribute(signatureDeclarationEClass, SIGNATURE_DECLARATION__MULTIPLICITY);
		createEReference(signatureDeclarationEClass, SIGNATURE_DECLARATION__RELATION);
		createEReference(signatureDeclarationEClass, SIGNATURE_DECLARATION__BLOCK);
		createEAttribute(signatureDeclarationEClass, SIGNATURE_DECLARATION__EXISTS);
		createEReference(signatureDeclarationEClass, SIGNATURE_DECLARATION__INHERITANCE);

		factDeclarationEClass = createEClass(FACT_DECLARATION);
		createEReference(factDeclarationEClass, FACT_DECLARATION__BLOCK);

		functionDeclarationEClass = createEClass(FUNCTION_DECLARATION);
		createEAttribute(functionDeclarationEClass, FUNCTION_DECLARATION__PATH);
		createEReference(functionDeclarationEClass, FUNCTION_DECLARATION__PARAMETER);
		createEReference(functionDeclarationEClass, FUNCTION_DECLARATION__TYPE);
		createEReference(functionDeclarationEClass, FUNCTION_DECLARATION__BLOCK);

		predicateDeclarationEClass = createEClass(PREDICATE_DECLARATION);
		createEReference(predicateDeclarationEClass, PREDICATE_DECLARATION__BLOCK);
		createEAttribute(predicateDeclarationEClass, PREDICATE_DECLARATION__PATH);
		createEReference(predicateDeclarationEClass, PREDICATE_DECLARATION__PARAMETER);

		assertionDeclarationEClass = createEClass(ASSERTION_DECLARATION);
		createEReference(assertionDeclarationEClass, ASSERTION_DECLARATION__BLOCK);

		commandDeclarationEClass = createEClass(COMMAND_DECLARATION);
		createEReference(commandDeclarationEClass, COMMAND_DECLARATION__SCOPE);
		createEReference(commandDeclarationEClass, COMMAND_DECLARATION__BLOCK);
		createEReference(commandDeclarationEClass, COMMAND_DECLARATION__ASSERTION);
		createEReference(commandDeclarationEClass, COMMAND_DECLARATION__PREDICATE);
		createEAttribute(commandDeclarationEClass, COMMAND_DECLARATION__IS_RUN);
		createEAttribute(commandDeclarationEClass, COMMAND_DECLARATION__IS_CHECK);

		inheritanceEClass = createEClass(INHERITANCE);
		createEAttribute(inheritanceEClass, INHERITANCE__SUPERTYPE);
		createEAttribute(inheritanceEClass, INHERITANCE__IS_SUBSET);
		createEAttribute(inheritanceEClass, INHERITANCE__IS_EXTENSION);

		scopeSpecificationEClass = createEClass(SCOPE_SPECIFICATION);

		genericScopeEClass = createEClass(GENERIC_SCOPE);
		createEReference(genericScopeEClass, GENERIC_SCOPE__SCOPEABLE);
		createEAttribute(genericScopeEClass, GENERIC_SCOPE__SCOPE_SIZE);

		detailedScopeEClass = createEClass(DETAILED_SCOPE);
		createEReference(detailedScopeEClass, DETAILED_SCOPE__SCOPEABLE);

		scopeableEClass = createEClass(SCOPEABLE);
		createEAttribute(scopeableEClass, SCOPEABLE__SCOPE_SIZE);
		createEAttribute(scopeableEClass, SCOPEABLE__IS_EXACTLY);
		createEAttribute(scopeableEClass, SCOPEABLE__SIGNATURE);

		expressionEClass = createEClass(EXPRESSION);

		declarationEClass = createEClass(DECLARATION);
		createEAttribute(declarationEClass, DECLARATION__IS_DISJOINT);
		createEReference(declarationEClass, DECLARATION__EXPRESSION);
		createEReference(declarationEClass, DECLARATION__VARIABLE);
		createEAttribute(declarationEClass, DECLARATION__MULTIPLICITY);

		signatureReferenceEClass = createEClass(SIGNATURE_REFERENCE);
		createEAttribute(signatureReferenceEClass, SIGNATURE_REFERENCE__SIGNATURE);

		blockEClass = createEClass(BLOCK);
		createEReference(blockEClass, BLOCK__EXPRESSION);

		binaryOperationEClass = createEClass(BINARY_OPERATION);
		createEReference(binaryOperationEClass, BINARY_OPERATION__LEFT_EXPRESSION);
		createEReference(binaryOperationEClass, BINARY_OPERATION__RIGHT_EXPRESSION);
		createEAttribute(binaryOperationEClass, BINARY_OPERATION__OPERATOR);

		unaryOperationEClass = createEClass(UNARY_OPERATION);
		createEReference(unaryOperationEClass, UNARY_OPERATION__EXPRESSION);
		createEAttribute(unaryOperationEClass, UNARY_OPERATION__OPERATOR);

		letDeclarationEClass = createEClass(LET_DECLARATION);
		createEReference(letDeclarationEClass, LET_DECLARATION__EQUAL_EXPRESSION);
		createEReference(letDeclarationEClass, LET_DECLARATION__BLOCK);
		createEAttribute(letDeclarationEClass, LET_DECLARATION__NAME);
		createEReference(letDeclarationEClass, LET_DECLARATION__BAR_EXPRESSION);
		createEAttribute(letDeclarationEClass, LET_DECLARATION__HAS_BLOCK);
		createEAttribute(letDeclarationEClass, LET_DECLARATION__HAS_EXPRESSION);

		compareOperationEClass = createEClass(COMPARE_OPERATION);
		createEReference(compareOperationEClass, COMPARE_OPERATION__LEFT_EXPRESSION);
		createEReference(compareOperationEClass, COMPARE_OPERATION__RIGHT_EXPRESSION);
		createEAttribute(compareOperationEClass, COMPARE_OPERATION__OPERATOR);
		createEAttribute(compareOperationEClass, COMPARE_OPERATION__NEGATION);

		variableReferenceEClass = createEClass(VARIABLE_REFERENCE);
		createEAttribute(variableReferenceEClass, VARIABLE_REFERENCE__PREFIX_AT);
		createEAttribute(variableReferenceEClass, VARIABLE_REFERENCE__VARIABLE);

		arrowOperationEClass = createEClass(ARROW_OPERATION);
		createEAttribute(arrowOperationEClass, ARROW_OPERATION__LEFT_MULTIPLICITY);
		createEAttribute(arrowOperationEClass, ARROW_OPERATION__RIGHT_MULTIPLICITY);
		createEReference(arrowOperationEClass, ARROW_OPERATION__LEFT_EXPRESSION);
		createEReference(arrowOperationEClass, ARROW_OPERATION__RIGHT_EXPRESSION);

		constantExpressionEClass = createEClass(CONSTANT_EXPRESSION);
		createEAttribute(constantExpressionEClass, CONSTANT_EXPRESSION__CONSTANT);

		externalReferenceEClass = createEClass(EXTERNAL_REFERENCE);
		createEAttribute(externalReferenceEClass, EXTERNAL_REFERENCE__NAME);
		createEAttribute(externalReferenceEClass, EXTERNAL_REFERENCE__PATH);

		comprehensionExpressionEClass = createEClass(COMPREHENSION_EXPRESSION);
		createEReference(comprehensionExpressionEClass, COMPREHENSION_EXPRESSION__DECLARATION);
		createEReference(comprehensionExpressionEClass, COMPREHENSION_EXPRESSION__BLOCK);
		createEReference(comprehensionExpressionEClass, COMPREHENSION_EXPRESSION__EXPRESSION);
		createEAttribute(comprehensionExpressionEClass, COMPREHENSION_EXPRESSION__HAS_BLOCK);
		createEAttribute(comprehensionExpressionEClass, COMPREHENSION_EXPRESSION__HAS_EXPRESSION);

		functionInvocationEClass = createEClass(FUNCTION_INVOCATION);
		createEReference(functionInvocationEClass, FUNCTION_INVOCATION__PARAMETER);
		createEAttribute(functionInvocationEClass, FUNCTION_INVOCATION__FUNCTION);

		predicateInvocationEClass = createEClass(PREDICATE_INVOCATION);
		createEReference(predicateInvocationEClass, PREDICATE_INVOCATION__PARAMETER);
		createEAttribute(predicateInvocationEClass, PREDICATE_INVOCATION__PREDICATE);

		disjointExpressionEClass = createEClass(DISJOINT_EXPRESSION);
		createEReference(disjointExpressionEClass, DISJOINT_EXPRESSION__SET);

		impliesOperationEClass = createEClass(IMPLIES_OPERATION);
		createEReference(impliesOperationEClass, IMPLIES_OPERATION__ELSE);
		createEReference(impliesOperationEClass, IMPLIES_OPERATION__IMPLICATION);
		createEReference(impliesOperationEClass, IMPLIES_OPERATION__CONDITION);

		quantificationExpressionEClass = createEClass(QUANTIFICATION_EXPRESSION);
		createEAttribute(quantificationExpressionEClass, QUANTIFICATION_EXPRESSION__QUANTIFICATOR);
		createEReference(quantificationExpressionEClass, QUANTIFICATION_EXPRESSION__DECLARATION);
		createEReference(quantificationExpressionEClass, QUANTIFICATION_EXPRESSION__EXPRESSION);
		createEReference(quantificationExpressionEClass, QUANTIFICATION_EXPRESSION__BLOCK);

		variableEClass = createEClass(VARIABLE);
		createEAttribute(variableEClass, VARIABLE__NAME);
		createEReference(variableEClass, VARIABLE__DECLARATION);

		enumDeclarationEClass = createEClass(ENUM_DECLARATION);
		createEAttribute(enumDeclarationEClass, ENUM_DECLARATION__LITERALS);

		// Create enums
		multiplicityEEnum = createEEnum(MULTIPLICITY);
		binaryOperatorEEnum = createEEnum(BINARY_OPERATOR);
		unaryOperatorEEnum = createEEnum(UNARY_OPERATOR);
		compareOperatorEEnum = createEEnum(COMPARE_OPERATOR);
		constantEEnum = createEEnum(CONSTANT);
		quantificatorEEnum = createEEnum(QUANTIFICATOR);
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
		parametrizedModuleEClass.getESuperTypes().add(this.getAlloyModule());
		importerModuleEClass.getESuperTypes().add(this.getAlloyModule());
		signatureDeclarationEClass.getESuperTypes().add(this.getParagraph());
		factDeclarationEClass.getESuperTypes().add(this.getParagraph());
		functionDeclarationEClass.getESuperTypes().add(this.getParagraph());
		predicateDeclarationEClass.getESuperTypes().add(this.getParagraph());
		assertionDeclarationEClass.getESuperTypes().add(this.getParagraph());
		commandDeclarationEClass.getESuperTypes().add(this.getParagraph());
		genericScopeEClass.getESuperTypes().add(this.getScopeSpecification());
		detailedScopeEClass.getESuperTypes().add(this.getScopeSpecification());
		signatureReferenceEClass.getESuperTypes().add(this.getExpression());
		binaryOperationEClass.getESuperTypes().add(this.getExpression());
		unaryOperationEClass.getESuperTypes().add(this.getExpression());
		letDeclarationEClass.getESuperTypes().add(this.getExpression());
		compareOperationEClass.getESuperTypes().add(this.getExpression());
		variableReferenceEClass.getESuperTypes().add(this.getExpression());
		arrowOperationEClass.getESuperTypes().add(this.getExpression());
		constantExpressionEClass.getESuperTypes().add(this.getExpression());
		externalReferenceEClass.getESuperTypes().add(this.getExpression());
		comprehensionExpressionEClass.getESuperTypes().add(this.getExpression());
		functionInvocationEClass.getESuperTypes().add(this.getExpression());
		predicateInvocationEClass.getESuperTypes().add(this.getExpression());
		disjointExpressionEClass.getESuperTypes().add(this.getExpression());
		impliesOperationEClass.getESuperTypes().add(this.getExpression());
		quantificationExpressionEClass.getESuperTypes().add(this.getExpression());
		enumDeclarationEClass.getESuperTypes().add(this.getParagraph());

		// Initialize classes and features; add operations and parameters
		initEClass(alloyModuleEClass, AlloyModule.class, "AlloyModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlloyModule_Name(), ecorePackage.getEString(), "name", null, 0, 1, AlloyModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAlloyModule_Path(), ecorePackage.getEString(), "path", null, 0, 1, AlloyModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlloyModule_Parameters(), this.getSignatureParameter(), null, "parameters", null, 1, -1, AlloyModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlloyModule_Paragraph(), this.getParagraph(), null, "paragraph", null, 0, -1, AlloyModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlloyModule_Imports(), this.getModuleImportation(), null, "imports", null, 0, -1, AlloyModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parametrizedModuleEClass, ParametrizedModule.class, "ParametrizedModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(signatureParameterEClass, SignatureParameter.class, "SignatureParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSignatureParameter_Name(), ecorePackage.getEString(), "name", null, 1, 1, SignatureParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(importerModuleEClass, ImporterModule.class, "ImporterModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(moduleImportationEClass, ModuleImportation.class, "ModuleImportation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModuleImportation_Name(), ecorePackage.getEString(), "name", null, 0, 1, ModuleImportation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModuleImportation_Alias(), ecorePackage.getEString(), "alias", null, 0, 1, ModuleImportation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModuleImportation_Path(), ecorePackage.getEString(), "path", null, 0, 1, ModuleImportation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModuleImportation_Parameters(), ecorePackage.getEString(), "parameters", null, 1, -1, ModuleImportation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(paragraphEClass, Paragraph.class, "Paragraph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParagraph_Name(), ecorePackage.getEString(), "name", null, 0, 1, Paragraph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(signatureDeclarationEClass, SignatureDeclaration.class, "SignatureDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSignatureDeclaration_IsAbstract(), ecorePackage.getEBoolean(), "isAbstract", null, 0, 1, SignatureDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSignatureDeclaration_Multiplicity(), this.getMultiplicity(), "multiplicity", null, 0, 1, SignatureDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSignatureDeclaration_Relation(), this.getDeclaration(), null, "relation", null, 0, -1, SignatureDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSignatureDeclaration_Block(), this.getBlock(), null, "block", null, 0, 1, SignatureDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSignatureDeclaration_Exists(), ecorePackage.getEBoolean(), "exists", null, 1, 1, SignatureDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSignatureDeclaration_Inheritance(), this.getInheritance(), null, "inheritance", null, 0, 1, SignatureDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(factDeclarationEClass, FactDeclaration.class, "FactDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFactDeclaration_Block(), this.getBlock(), null, "block", null, 1, 1, FactDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(functionDeclarationEClass, FunctionDeclaration.class, "FunctionDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFunctionDeclaration_Path(), ecorePackage.getEString(), "path", null, 0, 1, FunctionDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionDeclaration_Parameter(), this.getDeclaration(), null, "parameter", null, 0, -1, FunctionDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionDeclaration_Type(), this.getExpression(), null, "type", null, 1, 1, FunctionDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionDeclaration_Block(), this.getBlock(), null, "block", null, 1, 1, FunctionDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(predicateDeclarationEClass, PredicateDeclaration.class, "PredicateDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPredicateDeclaration_Block(), this.getBlock(), null, "block", null, 1, 1, PredicateDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPredicateDeclaration_Path(), ecorePackage.getEString(), "path", null, 0, 1, PredicateDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPredicateDeclaration_Parameter(), this.getDeclaration(), null, "parameter", null, 0, -1, PredicateDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(assertionDeclarationEClass, AssertionDeclaration.class, "AssertionDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssertionDeclaration_Block(), this.getBlock(), null, "block", null, 1, 1, AssertionDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(commandDeclarationEClass, CommandDeclaration.class, "CommandDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCommandDeclaration_Scope(), this.getScopeSpecification(), null, "scope", null, 0, 1, CommandDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCommandDeclaration_Block(), this.getBlock(), null, "block", null, 0, 1, CommandDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCommandDeclaration_Assertion(), this.getAssertionDeclaration(), null, "assertion", null, 0, 1, CommandDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCommandDeclaration_Predicate(), this.getPredicateDeclaration(), null, "predicate", null, 0, 1, CommandDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCommandDeclaration_IsRun(), ecorePackage.getEBoolean(), "isRun", null, 1, 1, CommandDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCommandDeclaration_IsCheck(), ecorePackage.getEBoolean(), "isCheck", null, 1, 1, CommandDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inheritanceEClass, Inheritance.class, "Inheritance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInheritance_Supertype(), ecorePackage.getEString(), "supertype", null, 1, 1, Inheritance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInheritance_IsSubset(), ecorePackage.getEBoolean(), "isSubset", null, 1, 1, Inheritance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInheritance_IsExtension(), ecorePackage.getEBoolean(), "isExtension", null, 1, 1, Inheritance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scopeSpecificationEClass, ScopeSpecification.class, "ScopeSpecification", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(genericScopeEClass, GenericScope.class, "GenericScope", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGenericScope_Scopeable(), this.getScopeable(), null, "scopeable", null, 0, -1, GenericScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGenericScope_ScopeSize(), ecorePackage.getEInt(), "scopeSize", null, 1, 1, GenericScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(detailedScopeEClass, DetailedScope.class, "DetailedScope", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDetailedScope_Scopeable(), this.getScopeable(), null, "scopeable", null, 1, -1, DetailedScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scopeableEClass, Scopeable.class, "Scopeable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScopeable_ScopeSize(), ecorePackage.getEInt(), "scopeSize", null, 1, 1, Scopeable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScopeable_IsExactly(), ecorePackage.getEBoolean(), "isExactly", null, 0, 1, Scopeable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScopeable_Signature(), ecorePackage.getEString(), "signature", null, 1, 1, Scopeable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(expressionEClass, Expression.class, "Expression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(declarationEClass, Declaration.class, "Declaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeclaration_IsDisjoint(), ecorePackage.getEBoolean(), "isDisjoint", null, 1, 1, Declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeclaration_Expression(), this.getExpression(), null, "expression", null, 1, 1, Declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeclaration_Variable(), this.getVariable(), this.getVariable_Declaration(), "variable", null, 1, -1, Declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeclaration_Multiplicity(), this.getMultiplicity(), "multiplicity", null, 0, 1, Declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(signatureReferenceEClass, SignatureReference.class, "SignatureReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSignatureReference_Signature(), ecorePackage.getEString(), "signature", null, 1, 1, SignatureReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(blockEClass, Block.class, "Block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBlock_Expression(), this.getExpression(), null, "expression", null, 0, -1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(binaryOperationEClass, BinaryOperation.class, "BinaryOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBinaryOperation_LeftExpression(), this.getExpression(), null, "leftExpression", null, 1, 1, BinaryOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBinaryOperation_RightExpression(), this.getExpression(), null, "rightExpression", null, 1, 1, BinaryOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBinaryOperation_Operator(), this.getBinaryOperator(), "operator", null, 1, 1, BinaryOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unaryOperationEClass, UnaryOperation.class, "UnaryOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnaryOperation_Expression(), this.getExpression(), null, "expression", null, 1, 1, UnaryOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnaryOperation_Operator(), this.getUnaryOperator(), "operator", null, 1, 1, UnaryOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(letDeclarationEClass, LetDeclaration.class, "LetDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLetDeclaration_EqualExpression(), this.getExpression(), null, "equalExpression", null, 1, 1, LetDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLetDeclaration_Block(), this.getBlock(), null, "block", null, 0, 1, LetDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLetDeclaration_Name(), ecorePackage.getEString(), "name", null, 1, -1, LetDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLetDeclaration_BarExpression(), this.getExpression(), null, "barExpression", null, 0, 1, LetDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLetDeclaration_HasBlock(), ecorePackage.getEBoolean(), "hasBlock", null, 1, 1, LetDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLetDeclaration_HasExpression(), ecorePackage.getEBoolean(), "hasExpression", null, 1, 1, LetDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compareOperationEClass, CompareOperation.class, "CompareOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompareOperation_LeftExpression(), this.getExpression(), null, "leftExpression", null, 1, 1, CompareOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompareOperation_RightExpression(), this.getExpression(), null, "rightExpression", null, 1, 1, CompareOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompareOperation_Operator(), this.getCompareOperator(), "operator", null, 1, 1, CompareOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompareOperation_Negation(), ecorePackage.getEBoolean(), "negation", null, 1, 1, CompareOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variableReferenceEClass, VariableReference.class, "VariableReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariableReference_PrefixAt(), ecorePackage.getEBoolean(), "prefixAt", null, 1, 1, VariableReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableReference_Variable(), ecorePackage.getEString(), "variable", null, 1, 1, VariableReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arrowOperationEClass, ArrowOperation.class, "ArrowOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArrowOperation_LeftMultiplicity(), this.getMultiplicity(), "leftMultiplicity", null, 0, 1, ArrowOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArrowOperation_RightMultiplicity(), this.getMultiplicity(), "rightMultiplicity", null, 0, 1, ArrowOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrowOperation_LeftExpression(), this.getExpression(), null, "leftExpression", null, 1, 1, ArrowOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrowOperation_RightExpression(), this.getExpression(), null, "rightExpression", null, 1, 1, ArrowOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constantExpressionEClass, ConstantExpression.class, "ConstantExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstantExpression_Constant(), this.getConstant(), "constant", null, 1, 1, ConstantExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalReferenceEClass, ExternalReference.class, "ExternalReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExternalReference_Name(), ecorePackage.getEString(), "name", null, 1, 1, ExternalReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalReference_Path(), ecorePackage.getEString(), "path", null, 0, 1, ExternalReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(comprehensionExpressionEClass, ComprehensionExpression.class, "ComprehensionExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComprehensionExpression_Declaration(), this.getDeclaration(), null, "declaration", null, 1, -1, ComprehensionExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComprehensionExpression_Block(), this.getBlock(), null, "block", null, 0, 1, ComprehensionExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComprehensionExpression_Expression(), this.getExpression(), null, "expression", null, 0, 1, ComprehensionExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComprehensionExpression_HasBlock(), ecorePackage.getEBoolean(), "hasBlock", null, 1, 1, ComprehensionExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComprehensionExpression_HasExpression(), ecorePackage.getEBoolean(), "hasExpression", null, 1, 1, ComprehensionExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(functionInvocationEClass, FunctionInvocation.class, "FunctionInvocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFunctionInvocation_Parameter(), this.getExpression(), null, "parameter", null, 0, -1, FunctionInvocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFunctionInvocation_Function(), ecorePackage.getEString(), "function", null, 1, 1, FunctionInvocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(predicateInvocationEClass, PredicateInvocation.class, "PredicateInvocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPredicateInvocation_Parameter(), this.getExpression(), null, "parameter", null, 0, -1, PredicateInvocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPredicateInvocation_Predicate(), ecorePackage.getEString(), "predicate", null, 1, 1, PredicateInvocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(disjointExpressionEClass, DisjointExpression.class, "DisjointExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDisjointExpression_Set(), this.getExpression(), null, "set", null, 0, -1, DisjointExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(impliesOperationEClass, ImpliesOperation.class, "ImpliesOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getImpliesOperation_Else(), this.getExpression(), null, "else", null, 0, 1, ImpliesOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getImpliesOperation_Implication(), this.getExpression(), null, "implication", null, 1, 1, ImpliesOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getImpliesOperation_Condition(), this.getExpression(), null, "condition", null, 1, 1, ImpliesOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(quantificationExpressionEClass, QuantificationExpression.class, "QuantificationExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQuantificationExpression_Quantificator(), this.getQuantificator(), "quantificator", null, 1, 1, QuantificationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQuantificationExpression_Declaration(), this.getDeclaration(), null, "declaration", null, 1, -1, QuantificationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQuantificationExpression_Expression(), this.getExpression(), null, "expression", null, 0, 1, QuantificationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQuantificationExpression_Block(), this.getBlock(), null, "block", null, 0, 1, QuantificationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variableEClass, Variable.class, "Variable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariable_Name(), ecorePackage.getEString(), "name", null, 1, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVariable_Declaration(), this.getDeclaration(), this.getDeclaration_Variable(), "declaration", null, 1, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(enumDeclarationEClass, EnumDeclaration.class, "EnumDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnumDeclaration_Literals(), ecorePackage.getEString(), "literals", null, 1, -1, EnumDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(multiplicityEEnum, Multiplicity.class, "Multiplicity");
		addEEnumLiteral(multiplicityEEnum, Multiplicity.SOME);
		addEEnumLiteral(multiplicityEEnum, Multiplicity.ONE);
		addEEnumLiteral(multiplicityEEnum, Multiplicity.LONE);
		addEEnumLiteral(multiplicityEEnum, Multiplicity.SET);

		initEEnum(binaryOperatorEEnum, BinaryOperator.class, "BinaryOperator");
		addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.DIFFERENCE);
		addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.INTERSECTION);
		addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.JOIN);
		addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.UNION);
		addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.DOMAIN_RESTRICTION);
		addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.RANGE_RESTRICTION);
		addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.RELATIONAL_OVERRIDE);
		addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.IFF);
		addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.OR);
		addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.AND);
		addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.BOX_JOIN);

		initEEnum(unaryOperatorEEnum, UnaryOperator.class, "UnaryOperator");
		addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.TRANSPOSITION);
		addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.TRANSITIVITY_CLOSURE);
		addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.REFLEXIVE_TRANSITY_CLOSURE);
		addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.NOT);
		addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.NO);
		addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.SET);
		addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.SOME);
		addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.LONE);
		addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.ONE);
		addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.CARDINALITY);

		initEEnum(compareOperatorEEnum, CompareOperator.class, "CompareOperator");
		addEEnumLiteral(compareOperatorEEnum, CompareOperator.SUBSET);
		addEEnumLiteral(compareOperatorEEnum, CompareOperator.EQUAL);
		addEEnumLiteral(compareOperatorEEnum, CompareOperator.LESS_THAN);
		addEEnumLiteral(compareOperatorEEnum, CompareOperator.GREATER_THEN);
		addEEnumLiteral(compareOperatorEEnum, CompareOperator.LESS_EQUAL);
		addEEnumLiteral(compareOperatorEEnum, CompareOperator.GREATER_EQUAL);

		initEEnum(constantEEnum, Constant.class, "Constant");
		addEEnumLiteral(constantEEnum, Constant.NONE);
		addEEnumLiteral(constantEEnum, Constant.UNIV);
		addEEnumLiteral(constantEEnum, Constant.IDEN);

		initEEnum(quantificatorEEnum, Quantificator.class, "Quantificator");
		addEEnumLiteral(quantificatorEEnum, Quantificator.ALL);
		addEEnumLiteral(quantificatorEEnum, Quantificator.SUM);
		addEEnumLiteral(quantificatorEEnum, Quantificator.NO);
		addEEnumLiteral(quantificatorEEnum, Quantificator.SOME);
		addEEnumLiteral(quantificatorEEnum, Quantificator.LONE);
		addEEnumLiteral(quantificatorEEnum, Quantificator.ONE);

		// Create resource
		createResource(eNS_URI);
	}

} //AlloyPackageImpl
