/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see br.ufes.inf.nemo.alloy.AlloyFactory
 * @model kind="package"
 * @generated
 */
public interface AlloyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "alloy";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://alloy/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "alloy";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AlloyPackage eINSTANCE = br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl.init();

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.AlloyModuleImpl <em>Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyModuleImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getAlloyModule()
	 * @generated
	 */
	int ALLOY_MODULE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOY_MODULE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOY_MODULE__PATH = 1;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOY_MODULE__PARAMETERS = 2;

	/**
	 * The feature id for the '<em><b>Paragraph</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOY_MODULE__PARAGRAPH = 3;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOY_MODULE__IMPORTS = 4;

	/**
	 * The number of structural features of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOY_MODULE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.ParametrizedModuleImpl <em>Parametrized Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.ParametrizedModuleImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getParametrizedModule()
	 * @generated
	 */
	int PARAMETRIZED_MODULE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETRIZED_MODULE__NAME = ALLOY_MODULE__NAME;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETRIZED_MODULE__PATH = ALLOY_MODULE__PATH;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETRIZED_MODULE__PARAMETERS = ALLOY_MODULE__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Paragraph</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETRIZED_MODULE__PARAGRAPH = ALLOY_MODULE__PARAGRAPH;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETRIZED_MODULE__IMPORTS = ALLOY_MODULE__IMPORTS;

	/**
	 * The number of structural features of the '<em>Parametrized Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETRIZED_MODULE_FEATURE_COUNT = ALLOY_MODULE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.SignatureParameterImpl <em>Signature Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.SignatureParameterImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getSignatureParameter()
	 * @generated
	 */
	int SIGNATURE_PARAMETER = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_PARAMETER__NAME = 0;

	/**
	 * The number of structural features of the '<em>Signature Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_PARAMETER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.ImporterModuleImpl <em>Importer Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.ImporterModuleImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getImporterModule()
	 * @generated
	 */
	int IMPORTER_MODULE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTER_MODULE__NAME = ALLOY_MODULE__NAME;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTER_MODULE__PATH = ALLOY_MODULE__PATH;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTER_MODULE__PARAMETERS = ALLOY_MODULE__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Paragraph</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTER_MODULE__PARAGRAPH = ALLOY_MODULE__PARAGRAPH;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTER_MODULE__IMPORTS = ALLOY_MODULE__IMPORTS;

	/**
	 * The number of structural features of the '<em>Importer Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTER_MODULE_FEATURE_COUNT = ALLOY_MODULE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.ModuleImportationImpl <em>Module Importation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.ModuleImportationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getModuleImportation()
	 * @generated
	 */
	int MODULE_IMPORTATION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_IMPORTATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_IMPORTATION__ALIAS = 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_IMPORTATION__PATH = 2;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_IMPORTATION__PARAMETERS = 3;

	/**
	 * The number of structural features of the '<em>Module Importation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_IMPORTATION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.ParagraphImpl <em>Paragraph</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.ParagraphImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getParagraph()
	 * @generated
	 */
	int PARAGRAPH = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAGRAPH__NAME = 0;

	/**
	 * The number of structural features of the '<em>Paragraph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAGRAPH_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.SignatureDeclarationImpl <em>Signature Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.SignatureDeclarationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getSignatureDeclaration()
	 * @generated
	 */
	int SIGNATURE_DECLARATION = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_DECLARATION__NAME = PARAGRAPH__NAME;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_DECLARATION__IS_ABSTRACT = PARAGRAPH_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_DECLARATION__MULTIPLICITY = PARAGRAPH_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Relation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_DECLARATION__RELATION = PARAGRAPH_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_DECLARATION__BLOCK = PARAGRAPH_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Exists</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_DECLARATION__EXISTS = PARAGRAPH_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Inheritance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_DECLARATION__INHERITANCE = PARAGRAPH_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Signature Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_DECLARATION_FEATURE_COUNT = PARAGRAPH_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.FactDeclarationImpl <em>Fact Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.FactDeclarationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getFactDeclaration()
	 * @generated
	 */
	int FACT_DECLARATION = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACT_DECLARATION__NAME = PARAGRAPH__NAME;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACT_DECLARATION__BLOCK = PARAGRAPH_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Fact Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACT_DECLARATION_FEATURE_COUNT = PARAGRAPH_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.FunctionDeclarationImpl <em>Function Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.FunctionDeclarationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getFunctionDeclaration()
	 * @generated
	 */
	int FUNCTION_DECLARATION = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_DECLARATION__NAME = PARAGRAPH__NAME;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_DECLARATION__PATH = PARAGRAPH_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_DECLARATION__PARAMETER = PARAGRAPH_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_DECLARATION__TYPE = PARAGRAPH_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_DECLARATION__BLOCK = PARAGRAPH_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Function Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_DECLARATION_FEATURE_COUNT = PARAGRAPH_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.PredicateDeclarationImpl <em>Predicate Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.PredicateDeclarationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getPredicateDeclaration()
	 * @generated
	 */
	int PREDICATE_DECLARATION = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDICATE_DECLARATION__NAME = PARAGRAPH__NAME;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDICATE_DECLARATION__BLOCK = PARAGRAPH_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDICATE_DECLARATION__PATH = PARAGRAPH_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDICATE_DECLARATION__PARAMETER = PARAGRAPH_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Predicate Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDICATE_DECLARATION_FEATURE_COUNT = PARAGRAPH_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.AssertionDeclarationImpl <em>Assertion Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.AssertionDeclarationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getAssertionDeclaration()
	 * @generated
	 */
	int ASSERTION_DECLARATION = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION_DECLARATION__NAME = PARAGRAPH__NAME;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION_DECLARATION__BLOCK = PARAGRAPH_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Assertion Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION_DECLARATION_FEATURE_COUNT = PARAGRAPH_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.CommandDeclarationImpl <em>Command Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.CommandDeclarationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getCommandDeclaration()
	 * @generated
	 */
	int COMMAND_DECLARATION = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_DECLARATION__NAME = PARAGRAPH__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_DECLARATION__SCOPE = PARAGRAPH_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Block</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_DECLARATION__BLOCK = PARAGRAPH_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Assertion</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_DECLARATION__ASSERTION = PARAGRAPH_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Predicate</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_DECLARATION__PREDICATE = PARAGRAPH_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Is Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_DECLARATION__IS_RUN = PARAGRAPH_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Is Check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_DECLARATION__IS_CHECK = PARAGRAPH_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Command Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_DECLARATION_FEATURE_COUNT = PARAGRAPH_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.InheritanceImpl <em>Inheritance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.InheritanceImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getInheritance()
	 * @generated
	 */
	int INHERITANCE = 12;

	/**
	 * The feature id for the '<em><b>Supertype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INHERITANCE__SUPERTYPE = 0;

	/**
	 * The feature id for the '<em><b>Is Subset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INHERITANCE__IS_SUBSET = 1;

	/**
	 * The feature id for the '<em><b>Is Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INHERITANCE__IS_EXTENSION = 2;

	/**
	 * The number of structural features of the '<em>Inheritance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INHERITANCE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.ScopeSpecificationImpl <em>Scope Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.ScopeSpecificationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getScopeSpecification()
	 * @generated
	 */
	int SCOPE_SPECIFICATION = 13;

	/**
	 * The number of structural features of the '<em>Scope Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_SPECIFICATION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.GenericScopeImpl <em>Generic Scope</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.GenericScopeImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getGenericScope()
	 * @generated
	 */
	int GENERIC_SCOPE = 14;

	/**
	 * The feature id for the '<em><b>Scopeable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SCOPE__SCOPEABLE = SCOPE_SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Scope Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SCOPE__SCOPE_SIZE = SCOPE_SPECIFICATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generic Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SCOPE_FEATURE_COUNT = SCOPE_SPECIFICATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.DetailedScopeImpl <em>Detailed Scope</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.DetailedScopeImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getDetailedScope()
	 * @generated
	 */
	int DETAILED_SCOPE = 15;

	/**
	 * The feature id for the '<em><b>Scopeable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAILED_SCOPE__SCOPEABLE = SCOPE_SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Detailed Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAILED_SCOPE_FEATURE_COUNT = SCOPE_SPECIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.ScopeableImpl <em>Scopeable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.ScopeableImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getScopeable()
	 * @generated
	 */
	int SCOPEABLE = 16;

	/**
	 * The feature id for the '<em><b>Scope Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPEABLE__SCOPE_SIZE = 0;

	/**
	 * The feature id for the '<em><b>Is Exactly</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPEABLE__IS_EXACTLY = 1;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPEABLE__SIGNATURE = 2;

	/**
	 * The number of structural features of the '<em>Scopeable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPEABLE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.ExpressionImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 17;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.DeclarationImpl <em>Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.DeclarationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getDeclaration()
	 * @generated
	 */
	int DECLARATION = 18;

	/**
	 * The feature id for the '<em><b>Is Disjoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__IS_DISJOINT = 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__EXPRESSION = 1;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__VARIABLE = 2;

	/**
	 * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__MULTIPLICITY = 3;

	/**
	 * The number of structural features of the '<em>Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.SignatureReferenceImpl <em>Signature Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.SignatureReferenceImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getSignatureReference()
	 * @generated
	 */
	int SIGNATURE_REFERENCE = 19;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_REFERENCE__SIGNATURE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Signature Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_REFERENCE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.BlockImpl <em>Block</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.BlockImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getBlock()
	 * @generated
	 */
	int BLOCK = 20;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.BinaryOperationImpl <em>Binary Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.BinaryOperationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getBinaryOperation()
	 * @generated
	 */
	int BINARY_OPERATION = 21;

	/**
	 * The feature id for the '<em><b>Left Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATION__LEFT_EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATION__RIGHT_EXPRESSION = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATION__OPERATOR = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Binary Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.UnaryOperationImpl <em>Unary Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.UnaryOperationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getUnaryOperation()
	 * @generated
	 */
	int UNARY_OPERATION = 22;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATION__OPERATOR = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unary Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.LetDeclarationImpl <em>Let Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.LetDeclarationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getLetDeclaration()
	 * @generated
	 */
	int LET_DECLARATION = 23;

	/**
	 * The feature id for the '<em><b>Equal Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_DECLARATION__EQUAL_EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_DECLARATION__BLOCK = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_DECLARATION__NAME = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Bar Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_DECLARATION__BAR_EXPRESSION = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Has Block</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_DECLARATION__HAS_BLOCK = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Has Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_DECLARATION__HAS_EXPRESSION = EXPRESSION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Let Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_DECLARATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.CompareOperationImpl <em>Compare Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.CompareOperationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getCompareOperation()
	 * @generated
	 */
	int COMPARE_OPERATION = 24;

	/**
	 * The feature id for the '<em><b>Left Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARE_OPERATION__LEFT_EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARE_OPERATION__RIGHT_EXPRESSION = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARE_OPERATION__OPERATOR = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Negation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARE_OPERATION__NEGATION = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Compare Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARE_OPERATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.VariableReferenceImpl <em>Variable Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.VariableReferenceImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getVariableReference()
	 * @generated
	 */
	int VARIABLE_REFERENCE = 25;

	/**
	 * The feature id for the '<em><b>Prefix At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE__PREFIX_AT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE__VARIABLE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Variable Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.ArrowOperationImpl <em>Arrow Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.ArrowOperationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getArrowOperation()
	 * @generated
	 */
	int ARROW_OPERATION = 26;

	/**
	 * The feature id for the '<em><b>Left Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_OPERATION__LEFT_MULTIPLICITY = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_OPERATION__RIGHT_MULTIPLICITY = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Left Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_OPERATION__LEFT_EXPRESSION = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Right Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_OPERATION__RIGHT_EXPRESSION = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Arrow Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_OPERATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.ConstantExpressionImpl <em>Constant Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.ConstantExpressionImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getConstantExpression()
	 * @generated
	 */
	int CONSTANT_EXPRESSION = 27;

	/**
	 * The feature id for the '<em><b>Constant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_EXPRESSION__CONSTANT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Constant Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.ExternalReferenceImpl <em>External Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.ExternalReferenceImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getExternalReference()
	 * @generated
	 */
	int EXTERNAL_REFERENCE = 28;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_REFERENCE__NAME = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_REFERENCE__PATH = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>External Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_REFERENCE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.ComprehensionExpressionImpl <em>Comprehension Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.ComprehensionExpressionImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getComprehensionExpression()
	 * @generated
	 */
	int COMPREHENSION_EXPRESSION = 29;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPREHENSION_EXPRESSION__DECLARATION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPREHENSION_EXPRESSION__BLOCK = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPREHENSION_EXPRESSION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Has Block</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPREHENSION_EXPRESSION__HAS_BLOCK = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Has Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPREHENSION_EXPRESSION__HAS_EXPRESSION = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Comprehension Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPREHENSION_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.FunctionInvocationImpl <em>Function Invocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.FunctionInvocationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getFunctionInvocation()
	 * @generated
	 */
	int FUNCTION_INVOCATION = 30;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_INVOCATION__PARAMETER = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Function</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_INVOCATION__FUNCTION = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Function Invocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_INVOCATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.PredicateInvocationImpl <em>Predicate Invocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.PredicateInvocationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getPredicateInvocation()
	 * @generated
	 */
	int PREDICATE_INVOCATION = 31;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDICATE_INVOCATION__PARAMETER = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Predicate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDICATE_INVOCATION__PREDICATE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Predicate Invocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDICATE_INVOCATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.DisjointExpressionImpl <em>Disjoint Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.DisjointExpressionImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getDisjointExpression()
	 * @generated
	 */
	int DISJOINT_EXPRESSION = 32;

	/**
	 * The feature id for the '<em><b>Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISJOINT_EXPRESSION__SET = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Disjoint Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISJOINT_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.ImpliesOperationImpl <em>Implies Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.ImpliesOperationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getImpliesOperation()
	 * @generated
	 */
	int IMPLIES_OPERATION = 33;

	/**
	 * The feature id for the '<em><b>Else</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES_OPERATION__ELSE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Implication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES_OPERATION__IMPLICATION = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES_OPERATION__CONDITION = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Implies Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES_OPERATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.QuantificationExpressionImpl <em>Quantification Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.QuantificationExpressionImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getQuantificationExpression()
	 * @generated
	 */
	int QUANTIFICATION_EXPRESSION = 34;

	/**
	 * The feature id for the '<em><b>Quantificator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFICATION_EXPRESSION__QUANTIFICATOR = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFICATION_EXPRESSION__DECLARATION = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFICATION_EXPRESSION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFICATION_EXPRESSION__BLOCK = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Quantification Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFICATION_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.VariableImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 35;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__DECLARATION = 1;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.impl.EnumDeclarationImpl <em>Enum Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.impl.EnumDeclarationImpl
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getEnumDeclaration()
	 * @generated
	 */
	int ENUM_DECLARATION = 36;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION__NAME = PARAGRAPH__NAME;

	/**
	 * The feature id for the '<em><b>Literals</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION__LITERALS = PARAGRAPH_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Enum Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION_FEATURE_COUNT = PARAGRAPH_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.Multiplicity <em>Multiplicity</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.Multiplicity
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getMultiplicity()
	 * @generated
	 */
	int MULTIPLICITY = 37;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.BinaryOperator <em>Binary Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.BinaryOperator
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getBinaryOperator()
	 * @generated
	 */
	int BINARY_OPERATOR = 38;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.UnaryOperator <em>Unary Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.UnaryOperator
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getUnaryOperator()
	 * @generated
	 */
	int UNARY_OPERATOR = 39;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.CompareOperator <em>Compare Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.CompareOperator
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getCompareOperator()
	 * @generated
	 */
	int COMPARE_OPERATOR = 40;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.Constant <em>Constant</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.Constant
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getConstant()
	 * @generated
	 */
	int CONSTANT = 41;

	/**
	 * The meta object id for the '{@link br.ufes.inf.nemo.alloy.Quantificator <em>Quantificator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see br.ufes.inf.nemo.alloy.Quantificator
	 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getQuantificator()
	 * @generated
	 */
	int QUANTIFICATOR = 42;


	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.AlloyModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module</em>'.
	 * @see br.ufes.inf.nemo.alloy.AlloyModule
	 * @generated
	 */
	EClass getAlloyModule();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.AlloyModule#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see br.ufes.inf.nemo.alloy.AlloyModule#getName()
	 * @see #getAlloyModule()
	 * @generated
	 */
	EAttribute getAlloyModule_Name();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.AlloyModule#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see br.ufes.inf.nemo.alloy.AlloyModule#getPath()
	 * @see #getAlloyModule()
	 * @generated
	 */
	EAttribute getAlloyModule_Path();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.AlloyModule#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see br.ufes.inf.nemo.alloy.AlloyModule#getParameters()
	 * @see #getAlloyModule()
	 * @generated
	 */
	EReference getAlloyModule_Parameters();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.AlloyModule#getParagraph <em>Paragraph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Paragraph</em>'.
	 * @see br.ufes.inf.nemo.alloy.AlloyModule#getParagraph()
	 * @see #getAlloyModule()
	 * @generated
	 */
	EReference getAlloyModule_Paragraph();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.AlloyModule#getImports <em>Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Imports</em>'.
	 * @see br.ufes.inf.nemo.alloy.AlloyModule#getImports()
	 * @see #getAlloyModule()
	 * @generated
	 */
	EReference getAlloyModule_Imports();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.ParametrizedModule <em>Parametrized Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parametrized Module</em>'.
	 * @see br.ufes.inf.nemo.alloy.ParametrizedModule
	 * @generated
	 */
	EClass getParametrizedModule();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.SignatureParameter <em>Signature Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signature Parameter</em>'.
	 * @see br.ufes.inf.nemo.alloy.SignatureParameter
	 * @generated
	 */
	EClass getSignatureParameter();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.SignatureParameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see br.ufes.inf.nemo.alloy.SignatureParameter#getName()
	 * @see #getSignatureParameter()
	 * @generated
	 */
	EAttribute getSignatureParameter_Name();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.ImporterModule <em>Importer Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Importer Module</em>'.
	 * @see br.ufes.inf.nemo.alloy.ImporterModule
	 * @generated
	 */
	EClass getImporterModule();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.ModuleImportation <em>Module Importation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Importation</em>'.
	 * @see br.ufes.inf.nemo.alloy.ModuleImportation
	 * @generated
	 */
	EClass getModuleImportation();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.ModuleImportation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see br.ufes.inf.nemo.alloy.ModuleImportation#getName()
	 * @see #getModuleImportation()
	 * @generated
	 */
	EAttribute getModuleImportation_Name();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.ModuleImportation#getAlias <em>Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alias</em>'.
	 * @see br.ufes.inf.nemo.alloy.ModuleImportation#getAlias()
	 * @see #getModuleImportation()
	 * @generated
	 */
	EAttribute getModuleImportation_Alias();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.ModuleImportation#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see br.ufes.inf.nemo.alloy.ModuleImportation#getPath()
	 * @see #getModuleImportation()
	 * @generated
	 */
	EAttribute getModuleImportation_Path();

	/**
	 * Returns the meta object for the attribute list '{@link br.ufes.inf.nemo.alloy.ModuleImportation#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Parameters</em>'.
	 * @see br.ufes.inf.nemo.alloy.ModuleImportation#getParameters()
	 * @see #getModuleImportation()
	 * @generated
	 */
	EAttribute getModuleImportation_Parameters();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.Paragraph <em>Paragraph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Paragraph</em>'.
	 * @see br.ufes.inf.nemo.alloy.Paragraph
	 * @generated
	 */
	EClass getParagraph();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.Paragraph#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see br.ufes.inf.nemo.alloy.Paragraph#getName()
	 * @see #getParagraph()
	 * @generated
	 */
	EAttribute getParagraph_Name();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.SignatureDeclaration <em>Signature Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signature Declaration</em>'.
	 * @see br.ufes.inf.nemo.alloy.SignatureDeclaration
	 * @generated
	 */
	EClass getSignatureDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.SignatureDeclaration#isIsAbstract <em>Is Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Abstract</em>'.
	 * @see br.ufes.inf.nemo.alloy.SignatureDeclaration#isIsAbstract()
	 * @see #getSignatureDeclaration()
	 * @generated
	 */
	EAttribute getSignatureDeclaration_IsAbstract();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.SignatureDeclaration#getMultiplicity <em>Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiplicity</em>'.
	 * @see br.ufes.inf.nemo.alloy.SignatureDeclaration#getMultiplicity()
	 * @see #getSignatureDeclaration()
	 * @generated
	 */
	EAttribute getSignatureDeclaration_Multiplicity();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.SignatureDeclaration#getRelation <em>Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relation</em>'.
	 * @see br.ufes.inf.nemo.alloy.SignatureDeclaration#getRelation()
	 * @see #getSignatureDeclaration()
	 * @generated
	 */
	EReference getSignatureDeclaration_Relation();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.SignatureDeclaration#getBlock <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block</em>'.
	 * @see br.ufes.inf.nemo.alloy.SignatureDeclaration#getBlock()
	 * @see #getSignatureDeclaration()
	 * @generated
	 */
	EReference getSignatureDeclaration_Block();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.SignatureDeclaration#isExists <em>Exists</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exists</em>'.
	 * @see br.ufes.inf.nemo.alloy.SignatureDeclaration#isExists()
	 * @see #getSignatureDeclaration()
	 * @generated
	 */
	EAttribute getSignatureDeclaration_Exists();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.SignatureDeclaration#getInheritance <em>Inheritance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Inheritance</em>'.
	 * @see br.ufes.inf.nemo.alloy.SignatureDeclaration#getInheritance()
	 * @see #getSignatureDeclaration()
	 * @generated
	 */
	EReference getSignatureDeclaration_Inheritance();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.FactDeclaration <em>Fact Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fact Declaration</em>'.
	 * @see br.ufes.inf.nemo.alloy.FactDeclaration
	 * @generated
	 */
	EClass getFactDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.FactDeclaration#getBlock <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block</em>'.
	 * @see br.ufes.inf.nemo.alloy.FactDeclaration#getBlock()
	 * @see #getFactDeclaration()
	 * @generated
	 */
	EReference getFactDeclaration_Block();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.FunctionDeclaration <em>Function Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Declaration</em>'.
	 * @see br.ufes.inf.nemo.alloy.FunctionDeclaration
	 * @generated
	 */
	EClass getFunctionDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.FunctionDeclaration#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see br.ufes.inf.nemo.alloy.FunctionDeclaration#getPath()
	 * @see #getFunctionDeclaration()
	 * @generated
	 */
	EAttribute getFunctionDeclaration_Path();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.FunctionDeclaration#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter</em>'.
	 * @see br.ufes.inf.nemo.alloy.FunctionDeclaration#getParameter()
	 * @see #getFunctionDeclaration()
	 * @generated
	 */
	EReference getFunctionDeclaration_Parameter();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.FunctionDeclaration#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see br.ufes.inf.nemo.alloy.FunctionDeclaration#getType()
	 * @see #getFunctionDeclaration()
	 * @generated
	 */
	EReference getFunctionDeclaration_Type();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.FunctionDeclaration#getBlock <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block</em>'.
	 * @see br.ufes.inf.nemo.alloy.FunctionDeclaration#getBlock()
	 * @see #getFunctionDeclaration()
	 * @generated
	 */
	EReference getFunctionDeclaration_Block();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.PredicateDeclaration <em>Predicate Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Predicate Declaration</em>'.
	 * @see br.ufes.inf.nemo.alloy.PredicateDeclaration
	 * @generated
	 */
	EClass getPredicateDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.PredicateDeclaration#getBlock <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block</em>'.
	 * @see br.ufes.inf.nemo.alloy.PredicateDeclaration#getBlock()
	 * @see #getPredicateDeclaration()
	 * @generated
	 */
	EReference getPredicateDeclaration_Block();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.PredicateDeclaration#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see br.ufes.inf.nemo.alloy.PredicateDeclaration#getPath()
	 * @see #getPredicateDeclaration()
	 * @generated
	 */
	EAttribute getPredicateDeclaration_Path();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.PredicateDeclaration#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter</em>'.
	 * @see br.ufes.inf.nemo.alloy.PredicateDeclaration#getParameter()
	 * @see #getPredicateDeclaration()
	 * @generated
	 */
	EReference getPredicateDeclaration_Parameter();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.AssertionDeclaration <em>Assertion Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assertion Declaration</em>'.
	 * @see br.ufes.inf.nemo.alloy.AssertionDeclaration
	 * @generated
	 */
	EClass getAssertionDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.AssertionDeclaration#getBlock <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block</em>'.
	 * @see br.ufes.inf.nemo.alloy.AssertionDeclaration#getBlock()
	 * @see #getAssertionDeclaration()
	 * @generated
	 */
	EReference getAssertionDeclaration_Block();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.CommandDeclaration <em>Command Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Command Declaration</em>'.
	 * @see br.ufes.inf.nemo.alloy.CommandDeclaration
	 * @generated
	 */
	EClass getCommandDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.CommandDeclaration#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Scope</em>'.
	 * @see br.ufes.inf.nemo.alloy.CommandDeclaration#getScope()
	 * @see #getCommandDeclaration()
	 * @generated
	 */
	EReference getCommandDeclaration_Scope();

	/**
	 * Returns the meta object for the reference '{@link br.ufes.inf.nemo.alloy.CommandDeclaration#getBlock <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Block</em>'.
	 * @see br.ufes.inf.nemo.alloy.CommandDeclaration#getBlock()
	 * @see #getCommandDeclaration()
	 * @generated
	 */
	EReference getCommandDeclaration_Block();

	/**
	 * Returns the meta object for the reference '{@link br.ufes.inf.nemo.alloy.CommandDeclaration#getAssertion <em>Assertion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Assertion</em>'.
	 * @see br.ufes.inf.nemo.alloy.CommandDeclaration#getAssertion()
	 * @see #getCommandDeclaration()
	 * @generated
	 */
	EReference getCommandDeclaration_Assertion();

	/**
	 * Returns the meta object for the reference '{@link br.ufes.inf.nemo.alloy.CommandDeclaration#getPredicate <em>Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Predicate</em>'.
	 * @see br.ufes.inf.nemo.alloy.CommandDeclaration#getPredicate()
	 * @see #getCommandDeclaration()
	 * @generated
	 */
	EReference getCommandDeclaration_Predicate();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.CommandDeclaration#isIsRun <em>Is Run</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Run</em>'.
	 * @see br.ufes.inf.nemo.alloy.CommandDeclaration#isIsRun()
	 * @see #getCommandDeclaration()
	 * @generated
	 */
	EAttribute getCommandDeclaration_IsRun();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.CommandDeclaration#isIsCheck <em>Is Check</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Check</em>'.
	 * @see br.ufes.inf.nemo.alloy.CommandDeclaration#isIsCheck()
	 * @see #getCommandDeclaration()
	 * @generated
	 */
	EAttribute getCommandDeclaration_IsCheck();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.Inheritance <em>Inheritance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inheritance</em>'.
	 * @see br.ufes.inf.nemo.alloy.Inheritance
	 * @generated
	 */
	EClass getInheritance();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.Inheritance#getSupertype <em>Supertype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supertype</em>'.
	 * @see br.ufes.inf.nemo.alloy.Inheritance#getSupertype()
	 * @see #getInheritance()
	 * @generated
	 */
	EAttribute getInheritance_Supertype();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.Inheritance#isIsSubset <em>Is Subset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Subset</em>'.
	 * @see br.ufes.inf.nemo.alloy.Inheritance#isIsSubset()
	 * @see #getInheritance()
	 * @generated
	 */
	EAttribute getInheritance_IsSubset();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.Inheritance#isIsExtension <em>Is Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Extension</em>'.
	 * @see br.ufes.inf.nemo.alloy.Inheritance#isIsExtension()
	 * @see #getInheritance()
	 * @generated
	 */
	EAttribute getInheritance_IsExtension();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.ScopeSpecification <em>Scope Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scope Specification</em>'.
	 * @see br.ufes.inf.nemo.alloy.ScopeSpecification
	 * @generated
	 */
	EClass getScopeSpecification();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.GenericScope <em>Generic Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Scope</em>'.
	 * @see br.ufes.inf.nemo.alloy.GenericScope
	 * @generated
	 */
	EClass getGenericScope();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.GenericScope#getScopeable <em>Scopeable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scopeable</em>'.
	 * @see br.ufes.inf.nemo.alloy.GenericScope#getScopeable()
	 * @see #getGenericScope()
	 * @generated
	 */
	EReference getGenericScope_Scopeable();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.GenericScope#getScopeSize <em>Scope Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope Size</em>'.
	 * @see br.ufes.inf.nemo.alloy.GenericScope#getScopeSize()
	 * @see #getGenericScope()
	 * @generated
	 */
	EAttribute getGenericScope_ScopeSize();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.DetailedScope <em>Detailed Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Detailed Scope</em>'.
	 * @see br.ufes.inf.nemo.alloy.DetailedScope
	 * @generated
	 */
	EClass getDetailedScope();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.DetailedScope#getScopeable <em>Scopeable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scopeable</em>'.
	 * @see br.ufes.inf.nemo.alloy.DetailedScope#getScopeable()
	 * @see #getDetailedScope()
	 * @generated
	 */
	EReference getDetailedScope_Scopeable();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.Scopeable <em>Scopeable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scopeable</em>'.
	 * @see br.ufes.inf.nemo.alloy.Scopeable
	 * @generated
	 */
	EClass getScopeable();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.Scopeable#getScopeSize <em>Scope Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope Size</em>'.
	 * @see br.ufes.inf.nemo.alloy.Scopeable#getScopeSize()
	 * @see #getScopeable()
	 * @generated
	 */
	EAttribute getScopeable_ScopeSize();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.Scopeable#isIsExactly <em>Is Exactly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Exactly</em>'.
	 * @see br.ufes.inf.nemo.alloy.Scopeable#isIsExactly()
	 * @see #getScopeable()
	 * @generated
	 */
	EAttribute getScopeable_IsExactly();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.Scopeable#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signature</em>'.
	 * @see br.ufes.inf.nemo.alloy.Scopeable#getSignature()
	 * @see #getScopeable()
	 * @generated
	 */
	EAttribute getScopeable_Signature();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.Declaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Declaration</em>'.
	 * @see br.ufes.inf.nemo.alloy.Declaration
	 * @generated
	 */
	EClass getDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.Declaration#isIsDisjoint <em>Is Disjoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Disjoint</em>'.
	 * @see br.ufes.inf.nemo.alloy.Declaration#isIsDisjoint()
	 * @see #getDeclaration()
	 * @generated
	 */
	EAttribute getDeclaration_IsDisjoint();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.Declaration#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.Declaration#getExpression()
	 * @see #getDeclaration()
	 * @generated
	 */
	EReference getDeclaration_Expression();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.Declaration#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variable</em>'.
	 * @see br.ufes.inf.nemo.alloy.Declaration#getVariable()
	 * @see #getDeclaration()
	 * @generated
	 */
	EReference getDeclaration_Variable();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.Declaration#getMultiplicity <em>Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiplicity</em>'.
	 * @see br.ufes.inf.nemo.alloy.Declaration#getMultiplicity()
	 * @see #getDeclaration()
	 * @generated
	 */
	EAttribute getDeclaration_Multiplicity();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.SignatureReference <em>Signature Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signature Reference</em>'.
	 * @see br.ufes.inf.nemo.alloy.SignatureReference
	 * @generated
	 */
	EClass getSignatureReference();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.SignatureReference#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signature</em>'.
	 * @see br.ufes.inf.nemo.alloy.SignatureReference#getSignature()
	 * @see #getSignatureReference()
	 * @generated
	 */
	EAttribute getSignatureReference_Signature();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block</em>'.
	 * @see br.ufes.inf.nemo.alloy.Block
	 * @generated
	 */
	EClass getBlock();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.Block#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.Block#getExpression()
	 * @see #getBlock()
	 * @generated
	 */
	EReference getBlock_Expression();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.BinaryOperation <em>Binary Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Operation</em>'.
	 * @see br.ufes.inf.nemo.alloy.BinaryOperation
	 * @generated
	 */
	EClass getBinaryOperation();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.BinaryOperation#getLeftExpression <em>Left Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.BinaryOperation#getLeftExpression()
	 * @see #getBinaryOperation()
	 * @generated
	 */
	EReference getBinaryOperation_LeftExpression();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.BinaryOperation#getRightExpression <em>Right Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.BinaryOperation#getRightExpression()
	 * @see #getBinaryOperation()
	 * @generated
	 */
	EReference getBinaryOperation_RightExpression();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.BinaryOperation#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see br.ufes.inf.nemo.alloy.BinaryOperation#getOperator()
	 * @see #getBinaryOperation()
	 * @generated
	 */
	EAttribute getBinaryOperation_Operator();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.UnaryOperation <em>Unary Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Operation</em>'.
	 * @see br.ufes.inf.nemo.alloy.UnaryOperation
	 * @generated
	 */
	EClass getUnaryOperation();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.UnaryOperation#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.UnaryOperation#getExpression()
	 * @see #getUnaryOperation()
	 * @generated
	 */
	EReference getUnaryOperation_Expression();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.UnaryOperation#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see br.ufes.inf.nemo.alloy.UnaryOperation#getOperator()
	 * @see #getUnaryOperation()
	 * @generated
	 */
	EAttribute getUnaryOperation_Operator();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.LetDeclaration <em>Let Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Let Declaration</em>'.
	 * @see br.ufes.inf.nemo.alloy.LetDeclaration
	 * @generated
	 */
	EClass getLetDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.LetDeclaration#getEqualExpression <em>Equal Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Equal Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.LetDeclaration#getEqualExpression()
	 * @see #getLetDeclaration()
	 * @generated
	 */
	EReference getLetDeclaration_EqualExpression();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.LetDeclaration#getBlock <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block</em>'.
	 * @see br.ufes.inf.nemo.alloy.LetDeclaration#getBlock()
	 * @see #getLetDeclaration()
	 * @generated
	 */
	EReference getLetDeclaration_Block();

	/**
	 * Returns the meta object for the attribute list '{@link br.ufes.inf.nemo.alloy.LetDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Name</em>'.
	 * @see br.ufes.inf.nemo.alloy.LetDeclaration#getName()
	 * @see #getLetDeclaration()
	 * @generated
	 */
	EAttribute getLetDeclaration_Name();

	/**
	 * Returns the meta object for the reference '{@link br.ufes.inf.nemo.alloy.LetDeclaration#getBarExpression <em>Bar Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Bar Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.LetDeclaration#getBarExpression()
	 * @see #getLetDeclaration()
	 * @generated
	 */
	EReference getLetDeclaration_BarExpression();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.LetDeclaration#isHasBlock <em>Has Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Block</em>'.
	 * @see br.ufes.inf.nemo.alloy.LetDeclaration#isHasBlock()
	 * @see #getLetDeclaration()
	 * @generated
	 */
	EAttribute getLetDeclaration_HasBlock();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.LetDeclaration#isHasExpression <em>Has Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.LetDeclaration#isHasExpression()
	 * @see #getLetDeclaration()
	 * @generated
	 */
	EAttribute getLetDeclaration_HasExpression();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.CompareOperation <em>Compare Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compare Operation</em>'.
	 * @see br.ufes.inf.nemo.alloy.CompareOperation
	 * @generated
	 */
	EClass getCompareOperation();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.CompareOperation#getLeftExpression <em>Left Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.CompareOperation#getLeftExpression()
	 * @see #getCompareOperation()
	 * @generated
	 */
	EReference getCompareOperation_LeftExpression();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.CompareOperation#getRightExpression <em>Right Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.CompareOperation#getRightExpression()
	 * @see #getCompareOperation()
	 * @generated
	 */
	EReference getCompareOperation_RightExpression();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.CompareOperation#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see br.ufes.inf.nemo.alloy.CompareOperation#getOperator()
	 * @see #getCompareOperation()
	 * @generated
	 */
	EAttribute getCompareOperation_Operator();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.CompareOperation#isNegation <em>Negation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Negation</em>'.
	 * @see br.ufes.inf.nemo.alloy.CompareOperation#isNegation()
	 * @see #getCompareOperation()
	 * @generated
	 */
	EAttribute getCompareOperation_Negation();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.VariableReference <em>Variable Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Reference</em>'.
	 * @see br.ufes.inf.nemo.alloy.VariableReference
	 * @generated
	 */
	EClass getVariableReference();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.VariableReference#isPrefixAt <em>Prefix At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix At</em>'.
	 * @see br.ufes.inf.nemo.alloy.VariableReference#isPrefixAt()
	 * @see #getVariableReference()
	 * @generated
	 */
	EAttribute getVariableReference_PrefixAt();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.VariableReference#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variable</em>'.
	 * @see br.ufes.inf.nemo.alloy.VariableReference#getVariable()
	 * @see #getVariableReference()
	 * @generated
	 */
	EAttribute getVariableReference_Variable();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.ArrowOperation <em>Arrow Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arrow Operation</em>'.
	 * @see br.ufes.inf.nemo.alloy.ArrowOperation
	 * @generated
	 */
	EClass getArrowOperation();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.ArrowOperation#getLeftMultiplicity <em>Left Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Left Multiplicity</em>'.
	 * @see br.ufes.inf.nemo.alloy.ArrowOperation#getLeftMultiplicity()
	 * @see #getArrowOperation()
	 * @generated
	 */
	EAttribute getArrowOperation_LeftMultiplicity();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.ArrowOperation#getRightMultiplicity <em>Right Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Right Multiplicity</em>'.
	 * @see br.ufes.inf.nemo.alloy.ArrowOperation#getRightMultiplicity()
	 * @see #getArrowOperation()
	 * @generated
	 */
	EAttribute getArrowOperation_RightMultiplicity();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.ArrowOperation#getLeftExpression <em>Left Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.ArrowOperation#getLeftExpression()
	 * @see #getArrowOperation()
	 * @generated
	 */
	EReference getArrowOperation_LeftExpression();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.ArrowOperation#getRightExpression <em>Right Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.ArrowOperation#getRightExpression()
	 * @see #getArrowOperation()
	 * @generated
	 */
	EReference getArrowOperation_RightExpression();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.ConstantExpression <em>Constant Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constant Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.ConstantExpression
	 * @generated
	 */
	EClass getConstantExpression();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.ConstantExpression#getConstant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constant</em>'.
	 * @see br.ufes.inf.nemo.alloy.ConstantExpression#getConstant()
	 * @see #getConstantExpression()
	 * @generated
	 */
	EAttribute getConstantExpression_Constant();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.ExternalReference <em>External Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Reference</em>'.
	 * @see br.ufes.inf.nemo.alloy.ExternalReference
	 * @generated
	 */
	EClass getExternalReference();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.ExternalReference#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see br.ufes.inf.nemo.alloy.ExternalReference#getName()
	 * @see #getExternalReference()
	 * @generated
	 */
	EAttribute getExternalReference_Name();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.ExternalReference#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see br.ufes.inf.nemo.alloy.ExternalReference#getPath()
	 * @see #getExternalReference()
	 * @generated
	 */
	EAttribute getExternalReference_Path();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.ComprehensionExpression <em>Comprehension Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comprehension Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.ComprehensionExpression
	 * @generated
	 */
	EClass getComprehensionExpression();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Declaration</em>'.
	 * @see br.ufes.inf.nemo.alloy.ComprehensionExpression#getDeclaration()
	 * @see #getComprehensionExpression()
	 * @generated
	 */
	EReference getComprehensionExpression_Declaration();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#getBlock <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block</em>'.
	 * @see br.ufes.inf.nemo.alloy.ComprehensionExpression#getBlock()
	 * @see #getComprehensionExpression()
	 * @generated
	 */
	EReference getComprehensionExpression_Block();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.ComprehensionExpression#getExpression()
	 * @see #getComprehensionExpression()
	 * @generated
	 */
	EReference getComprehensionExpression_Expression();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#isHasBlock <em>Has Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Block</em>'.
	 * @see br.ufes.inf.nemo.alloy.ComprehensionExpression#isHasBlock()
	 * @see #getComprehensionExpression()
	 * @generated
	 */
	EAttribute getComprehensionExpression_HasBlock();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#isHasExpression <em>Has Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.ComprehensionExpression#isHasExpression()
	 * @see #getComprehensionExpression()
	 * @generated
	 */
	EAttribute getComprehensionExpression_HasExpression();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.FunctionInvocation <em>Function Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Invocation</em>'.
	 * @see br.ufes.inf.nemo.alloy.FunctionInvocation
	 * @generated
	 */
	EClass getFunctionInvocation();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.FunctionInvocation#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter</em>'.
	 * @see br.ufes.inf.nemo.alloy.FunctionInvocation#getParameter()
	 * @see #getFunctionInvocation()
	 * @generated
	 */
	EReference getFunctionInvocation_Parameter();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.FunctionInvocation#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Function</em>'.
	 * @see br.ufes.inf.nemo.alloy.FunctionInvocation#getFunction()
	 * @see #getFunctionInvocation()
	 * @generated
	 */
	EAttribute getFunctionInvocation_Function();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.PredicateInvocation <em>Predicate Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Predicate Invocation</em>'.
	 * @see br.ufes.inf.nemo.alloy.PredicateInvocation
	 * @generated
	 */
	EClass getPredicateInvocation();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.PredicateInvocation#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter</em>'.
	 * @see br.ufes.inf.nemo.alloy.PredicateInvocation#getParameter()
	 * @see #getPredicateInvocation()
	 * @generated
	 */
	EReference getPredicateInvocation_Parameter();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.PredicateInvocation#getPredicate <em>Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicate</em>'.
	 * @see br.ufes.inf.nemo.alloy.PredicateInvocation#getPredicate()
	 * @see #getPredicateInvocation()
	 * @generated
	 */
	EAttribute getPredicateInvocation_Predicate();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.DisjointExpression <em>Disjoint Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Disjoint Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.DisjointExpression
	 * @generated
	 */
	EClass getDisjointExpression();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.DisjointExpression#getSet <em>Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Set</em>'.
	 * @see br.ufes.inf.nemo.alloy.DisjointExpression#getSet()
	 * @see #getDisjointExpression()
	 * @generated
	 */
	EReference getDisjointExpression_Set();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.ImpliesOperation <em>Implies Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implies Operation</em>'.
	 * @see br.ufes.inf.nemo.alloy.ImpliesOperation
	 * @generated
	 */
	EClass getImpliesOperation();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.ImpliesOperation#getElse <em>Else</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Else</em>'.
	 * @see br.ufes.inf.nemo.alloy.ImpliesOperation#getElse()
	 * @see #getImpliesOperation()
	 * @generated
	 */
	EReference getImpliesOperation_Else();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.ImpliesOperation#getImplication <em>Implication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Implication</em>'.
	 * @see br.ufes.inf.nemo.alloy.ImpliesOperation#getImplication()
	 * @see #getImpliesOperation()
	 * @generated
	 */
	EReference getImpliesOperation_Implication();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.ImpliesOperation#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see br.ufes.inf.nemo.alloy.ImpliesOperation#getCondition()
	 * @see #getImpliesOperation()
	 * @generated
	 */
	EReference getImpliesOperation_Condition();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.QuantificationExpression <em>Quantification Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quantification Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.QuantificationExpression
	 * @generated
	 */
	EClass getQuantificationExpression();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.QuantificationExpression#getQuantificator <em>Quantificator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantificator</em>'.
	 * @see br.ufes.inf.nemo.alloy.QuantificationExpression#getQuantificator()
	 * @see #getQuantificationExpression()
	 * @generated
	 */
	EAttribute getQuantificationExpression_Quantificator();

	/**
	 * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.alloy.QuantificationExpression#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Declaration</em>'.
	 * @see br.ufes.inf.nemo.alloy.QuantificationExpression#getDeclaration()
	 * @see #getQuantificationExpression()
	 * @generated
	 */
	EReference getQuantificationExpression_Declaration();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.QuantificationExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see br.ufes.inf.nemo.alloy.QuantificationExpression#getExpression()
	 * @see #getQuantificationExpression()
	 * @generated
	 */
	EReference getQuantificationExpression_Expression();

	/**
	 * Returns the meta object for the containment reference '{@link br.ufes.inf.nemo.alloy.QuantificationExpression#getBlock <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block</em>'.
	 * @see br.ufes.inf.nemo.alloy.QuantificationExpression#getBlock()
	 * @see #getQuantificationExpression()
	 * @generated
	 */
	EReference getQuantificationExpression_Block();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see br.ufes.inf.nemo.alloy.Variable
	 * @generated
	 */
	EClass getVariable();

	/**
	 * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.alloy.Variable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see br.ufes.inf.nemo.alloy.Variable#getName()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Name();

	/**
	 * Returns the meta object for the container reference '{@link br.ufes.inf.nemo.alloy.Variable#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Declaration</em>'.
	 * @see br.ufes.inf.nemo.alloy.Variable#getDeclaration()
	 * @see #getVariable()
	 * @generated
	 */
	EReference getVariable_Declaration();

	/**
	 * Returns the meta object for class '{@link br.ufes.inf.nemo.alloy.EnumDeclaration <em>Enum Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Declaration</em>'.
	 * @see br.ufes.inf.nemo.alloy.EnumDeclaration
	 * @generated
	 */
	EClass getEnumDeclaration();

	/**
	 * Returns the meta object for the attribute list '{@link br.ufes.inf.nemo.alloy.EnumDeclaration#getLiterals <em>Literals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Literals</em>'.
	 * @see br.ufes.inf.nemo.alloy.EnumDeclaration#getLiterals()
	 * @see #getEnumDeclaration()
	 * @generated
	 */
	EAttribute getEnumDeclaration_Literals();

	/**
	 * Returns the meta object for enum '{@link br.ufes.inf.nemo.alloy.Multiplicity <em>Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Multiplicity</em>'.
	 * @see br.ufes.inf.nemo.alloy.Multiplicity
	 * @generated
	 */
	EEnum getMultiplicity();

	/**
	 * Returns the meta object for enum '{@link br.ufes.inf.nemo.alloy.BinaryOperator <em>Binary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Binary Operator</em>'.
	 * @see br.ufes.inf.nemo.alloy.BinaryOperator
	 * @generated
	 */
	EEnum getBinaryOperator();

	/**
	 * Returns the meta object for enum '{@link br.ufes.inf.nemo.alloy.UnaryOperator <em>Unary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Unary Operator</em>'.
	 * @see br.ufes.inf.nemo.alloy.UnaryOperator
	 * @generated
	 */
	EEnum getUnaryOperator();

	/**
	 * Returns the meta object for enum '{@link br.ufes.inf.nemo.alloy.CompareOperator <em>Compare Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Compare Operator</em>'.
	 * @see br.ufes.inf.nemo.alloy.CompareOperator
	 * @generated
	 */
	EEnum getCompareOperator();

	/**
	 * Returns the meta object for enum '{@link br.ufes.inf.nemo.alloy.Constant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Constant</em>'.
	 * @see br.ufes.inf.nemo.alloy.Constant
	 * @generated
	 */
	EEnum getConstant();

	/**
	 * Returns the meta object for enum '{@link br.ufes.inf.nemo.alloy.Quantificator <em>Quantificator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Quantificator</em>'.
	 * @see br.ufes.inf.nemo.alloy.Quantificator
	 * @generated
	 */
	EEnum getQuantificator();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AlloyFactory getAlloyFactory();

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
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.AlloyModuleImpl <em>Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyModuleImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getAlloyModule()
		 * @generated
		 */
		EClass ALLOY_MODULE = eINSTANCE.getAlloyModule();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALLOY_MODULE__NAME = eINSTANCE.getAlloyModule_Name();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALLOY_MODULE__PATH = eINSTANCE.getAlloyModule_Path();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALLOY_MODULE__PARAMETERS = eINSTANCE.getAlloyModule_Parameters();

		/**
		 * The meta object literal for the '<em><b>Paragraph</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALLOY_MODULE__PARAGRAPH = eINSTANCE.getAlloyModule_Paragraph();

		/**
		 * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALLOY_MODULE__IMPORTS = eINSTANCE.getAlloyModule_Imports();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.ParametrizedModuleImpl <em>Parametrized Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.ParametrizedModuleImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getParametrizedModule()
		 * @generated
		 */
		EClass PARAMETRIZED_MODULE = eINSTANCE.getParametrizedModule();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.SignatureParameterImpl <em>Signature Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.SignatureParameterImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getSignatureParameter()
		 * @generated
		 */
		EClass SIGNATURE_PARAMETER = eINSTANCE.getSignatureParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIGNATURE_PARAMETER__NAME = eINSTANCE.getSignatureParameter_Name();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.ImporterModuleImpl <em>Importer Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.ImporterModuleImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getImporterModule()
		 * @generated
		 */
		EClass IMPORTER_MODULE = eINSTANCE.getImporterModule();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.ModuleImportationImpl <em>Module Importation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.ModuleImportationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getModuleImportation()
		 * @generated
		 */
		EClass MODULE_IMPORTATION = eINSTANCE.getModuleImportation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE_IMPORTATION__NAME = eINSTANCE.getModuleImportation_Name();

		/**
		 * The meta object literal for the '<em><b>Alias</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE_IMPORTATION__ALIAS = eINSTANCE.getModuleImportation_Alias();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE_IMPORTATION__PATH = eINSTANCE.getModuleImportation_Path();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE_IMPORTATION__PARAMETERS = eINSTANCE.getModuleImportation_Parameters();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.ParagraphImpl <em>Paragraph</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.ParagraphImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getParagraph()
		 * @generated
		 */
		EClass PARAGRAPH = eINSTANCE.getParagraph();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAGRAPH__NAME = eINSTANCE.getParagraph_Name();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.SignatureDeclarationImpl <em>Signature Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.SignatureDeclarationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getSignatureDeclaration()
		 * @generated
		 */
		EClass SIGNATURE_DECLARATION = eINSTANCE.getSignatureDeclaration();

		/**
		 * The meta object literal for the '<em><b>Is Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIGNATURE_DECLARATION__IS_ABSTRACT = eINSTANCE.getSignatureDeclaration_IsAbstract();

		/**
		 * The meta object literal for the '<em><b>Multiplicity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIGNATURE_DECLARATION__MULTIPLICITY = eINSTANCE.getSignatureDeclaration_Multiplicity();

		/**
		 * The meta object literal for the '<em><b>Relation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIGNATURE_DECLARATION__RELATION = eINSTANCE.getSignatureDeclaration_Relation();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIGNATURE_DECLARATION__BLOCK = eINSTANCE.getSignatureDeclaration_Block();

		/**
		 * The meta object literal for the '<em><b>Exists</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIGNATURE_DECLARATION__EXISTS = eINSTANCE.getSignatureDeclaration_Exists();

		/**
		 * The meta object literal for the '<em><b>Inheritance</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIGNATURE_DECLARATION__INHERITANCE = eINSTANCE.getSignatureDeclaration_Inheritance();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.FactDeclarationImpl <em>Fact Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.FactDeclarationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getFactDeclaration()
		 * @generated
		 */
		EClass FACT_DECLARATION = eINSTANCE.getFactDeclaration();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACT_DECLARATION__BLOCK = eINSTANCE.getFactDeclaration_Block();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.FunctionDeclarationImpl <em>Function Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.FunctionDeclarationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getFunctionDeclaration()
		 * @generated
		 */
		EClass FUNCTION_DECLARATION = eINSTANCE.getFunctionDeclaration();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION_DECLARATION__PATH = eINSTANCE.getFunctionDeclaration_Path();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_DECLARATION__PARAMETER = eINSTANCE.getFunctionDeclaration_Parameter();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_DECLARATION__TYPE = eINSTANCE.getFunctionDeclaration_Type();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_DECLARATION__BLOCK = eINSTANCE.getFunctionDeclaration_Block();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.PredicateDeclarationImpl <em>Predicate Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.PredicateDeclarationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getPredicateDeclaration()
		 * @generated
		 */
		EClass PREDICATE_DECLARATION = eINSTANCE.getPredicateDeclaration();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PREDICATE_DECLARATION__BLOCK = eINSTANCE.getPredicateDeclaration_Block();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDICATE_DECLARATION__PATH = eINSTANCE.getPredicateDeclaration_Path();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PREDICATE_DECLARATION__PARAMETER = eINSTANCE.getPredicateDeclaration_Parameter();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.AssertionDeclarationImpl <em>Assertion Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.AssertionDeclarationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getAssertionDeclaration()
		 * @generated
		 */
		EClass ASSERTION_DECLARATION = eINSTANCE.getAssertionDeclaration();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSERTION_DECLARATION__BLOCK = eINSTANCE.getAssertionDeclaration_Block();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.CommandDeclarationImpl <em>Command Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.CommandDeclarationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getCommandDeclaration()
		 * @generated
		 */
		EClass COMMAND_DECLARATION = eINSTANCE.getCommandDeclaration();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMAND_DECLARATION__SCOPE = eINSTANCE.getCommandDeclaration_Scope();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMAND_DECLARATION__BLOCK = eINSTANCE.getCommandDeclaration_Block();

		/**
		 * The meta object literal for the '<em><b>Assertion</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMAND_DECLARATION__ASSERTION = eINSTANCE.getCommandDeclaration_Assertion();

		/**
		 * The meta object literal for the '<em><b>Predicate</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMAND_DECLARATION__PREDICATE = eINSTANCE.getCommandDeclaration_Predicate();

		/**
		 * The meta object literal for the '<em><b>Is Run</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMAND_DECLARATION__IS_RUN = eINSTANCE.getCommandDeclaration_IsRun();

		/**
		 * The meta object literal for the '<em><b>Is Check</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMAND_DECLARATION__IS_CHECK = eINSTANCE.getCommandDeclaration_IsCheck();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.InheritanceImpl <em>Inheritance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.InheritanceImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getInheritance()
		 * @generated
		 */
		EClass INHERITANCE = eINSTANCE.getInheritance();

		/**
		 * The meta object literal for the '<em><b>Supertype</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INHERITANCE__SUPERTYPE = eINSTANCE.getInheritance_Supertype();

		/**
		 * The meta object literal for the '<em><b>Is Subset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INHERITANCE__IS_SUBSET = eINSTANCE.getInheritance_IsSubset();

		/**
		 * The meta object literal for the '<em><b>Is Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INHERITANCE__IS_EXTENSION = eINSTANCE.getInheritance_IsExtension();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.ScopeSpecificationImpl <em>Scope Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.ScopeSpecificationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getScopeSpecification()
		 * @generated
		 */
		EClass SCOPE_SPECIFICATION = eINSTANCE.getScopeSpecification();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.GenericScopeImpl <em>Generic Scope</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.GenericScopeImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getGenericScope()
		 * @generated
		 */
		EClass GENERIC_SCOPE = eINSTANCE.getGenericScope();

		/**
		 * The meta object literal for the '<em><b>Scopeable</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_SCOPE__SCOPEABLE = eINSTANCE.getGenericScope_Scopeable();

		/**
		 * The meta object literal for the '<em><b>Scope Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERIC_SCOPE__SCOPE_SIZE = eINSTANCE.getGenericScope_ScopeSize();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.DetailedScopeImpl <em>Detailed Scope</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.DetailedScopeImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getDetailedScope()
		 * @generated
		 */
		EClass DETAILED_SCOPE = eINSTANCE.getDetailedScope();

		/**
		 * The meta object literal for the '<em><b>Scopeable</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DETAILED_SCOPE__SCOPEABLE = eINSTANCE.getDetailedScope_Scopeable();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.ScopeableImpl <em>Scopeable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.ScopeableImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getScopeable()
		 * @generated
		 */
		EClass SCOPEABLE = eINSTANCE.getScopeable();

		/**
		 * The meta object literal for the '<em><b>Scope Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCOPEABLE__SCOPE_SIZE = eINSTANCE.getScopeable_ScopeSize();

		/**
		 * The meta object literal for the '<em><b>Is Exactly</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCOPEABLE__IS_EXACTLY = eINSTANCE.getScopeable_IsExactly();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCOPEABLE__SIGNATURE = eINSTANCE.getScopeable_Signature();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.ExpressionImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.DeclarationImpl <em>Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.DeclarationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getDeclaration()
		 * @generated
		 */
		EClass DECLARATION = eINSTANCE.getDeclaration();

		/**
		 * The meta object literal for the '<em><b>Is Disjoint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECLARATION__IS_DISJOINT = eINSTANCE.getDeclaration_IsDisjoint();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECLARATION__EXPRESSION = eINSTANCE.getDeclaration_Expression();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECLARATION__VARIABLE = eINSTANCE.getDeclaration_Variable();

		/**
		 * The meta object literal for the '<em><b>Multiplicity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECLARATION__MULTIPLICITY = eINSTANCE.getDeclaration_Multiplicity();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.SignatureReferenceImpl <em>Signature Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.SignatureReferenceImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getSignatureReference()
		 * @generated
		 */
		EClass SIGNATURE_REFERENCE = eINSTANCE.getSignatureReference();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIGNATURE_REFERENCE__SIGNATURE = eINSTANCE.getSignatureReference_Signature();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.BlockImpl <em>Block</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.BlockImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getBlock()
		 * @generated
		 */
		EClass BLOCK = eINSTANCE.getBlock();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BLOCK__EXPRESSION = eINSTANCE.getBlock_Expression();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.BinaryOperationImpl <em>Binary Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.BinaryOperationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getBinaryOperation()
		 * @generated
		 */
		EClass BINARY_OPERATION = eINSTANCE.getBinaryOperation();

		/**
		 * The meta object literal for the '<em><b>Left Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_OPERATION__LEFT_EXPRESSION = eINSTANCE.getBinaryOperation_LeftExpression();

		/**
		 * The meta object literal for the '<em><b>Right Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_OPERATION__RIGHT_EXPRESSION = eINSTANCE.getBinaryOperation_RightExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BINARY_OPERATION__OPERATOR = eINSTANCE.getBinaryOperation_Operator();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.UnaryOperationImpl <em>Unary Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.UnaryOperationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getUnaryOperation()
		 * @generated
		 */
		EClass UNARY_OPERATION = eINSTANCE.getUnaryOperation();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_OPERATION__EXPRESSION = eINSTANCE.getUnaryOperation_Expression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNARY_OPERATION__OPERATOR = eINSTANCE.getUnaryOperation_Operator();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.LetDeclarationImpl <em>Let Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.LetDeclarationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getLetDeclaration()
		 * @generated
		 */
		EClass LET_DECLARATION = eINSTANCE.getLetDeclaration();

		/**
		 * The meta object literal for the '<em><b>Equal Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LET_DECLARATION__EQUAL_EXPRESSION = eINSTANCE.getLetDeclaration_EqualExpression();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LET_DECLARATION__BLOCK = eINSTANCE.getLetDeclaration_Block();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LET_DECLARATION__NAME = eINSTANCE.getLetDeclaration_Name();

		/**
		 * The meta object literal for the '<em><b>Bar Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LET_DECLARATION__BAR_EXPRESSION = eINSTANCE.getLetDeclaration_BarExpression();

		/**
		 * The meta object literal for the '<em><b>Has Block</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LET_DECLARATION__HAS_BLOCK = eINSTANCE.getLetDeclaration_HasBlock();

		/**
		 * The meta object literal for the '<em><b>Has Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LET_DECLARATION__HAS_EXPRESSION = eINSTANCE.getLetDeclaration_HasExpression();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.CompareOperationImpl <em>Compare Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.CompareOperationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getCompareOperation()
		 * @generated
		 */
		EClass COMPARE_OPERATION = eINSTANCE.getCompareOperation();

		/**
		 * The meta object literal for the '<em><b>Left Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARE_OPERATION__LEFT_EXPRESSION = eINSTANCE.getCompareOperation_LeftExpression();

		/**
		 * The meta object literal for the '<em><b>Right Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARE_OPERATION__RIGHT_EXPRESSION = eINSTANCE.getCompareOperation_RightExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARE_OPERATION__OPERATOR = eINSTANCE.getCompareOperation_Operator();

		/**
		 * The meta object literal for the '<em><b>Negation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARE_OPERATION__NEGATION = eINSTANCE.getCompareOperation_Negation();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.VariableReferenceImpl <em>Variable Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.VariableReferenceImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getVariableReference()
		 * @generated
		 */
		EClass VARIABLE_REFERENCE = eINSTANCE.getVariableReference();

		/**
		 * The meta object literal for the '<em><b>Prefix At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_REFERENCE__PREFIX_AT = eINSTANCE.getVariableReference_PrefixAt();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_REFERENCE__VARIABLE = eINSTANCE.getVariableReference_Variable();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.ArrowOperationImpl <em>Arrow Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.ArrowOperationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getArrowOperation()
		 * @generated
		 */
		EClass ARROW_OPERATION = eINSTANCE.getArrowOperation();

		/**
		 * The meta object literal for the '<em><b>Left Multiplicity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARROW_OPERATION__LEFT_MULTIPLICITY = eINSTANCE.getArrowOperation_LeftMultiplicity();

		/**
		 * The meta object literal for the '<em><b>Right Multiplicity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARROW_OPERATION__RIGHT_MULTIPLICITY = eINSTANCE.getArrowOperation_RightMultiplicity();

		/**
		 * The meta object literal for the '<em><b>Left Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARROW_OPERATION__LEFT_EXPRESSION = eINSTANCE.getArrowOperation_LeftExpression();

		/**
		 * The meta object literal for the '<em><b>Right Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARROW_OPERATION__RIGHT_EXPRESSION = eINSTANCE.getArrowOperation_RightExpression();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.ConstantExpressionImpl <em>Constant Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.ConstantExpressionImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getConstantExpression()
		 * @generated
		 */
		EClass CONSTANT_EXPRESSION = eINSTANCE.getConstantExpression();

		/**
		 * The meta object literal for the '<em><b>Constant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTANT_EXPRESSION__CONSTANT = eINSTANCE.getConstantExpression_Constant();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.ExternalReferenceImpl <em>External Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.ExternalReferenceImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getExternalReference()
		 * @generated
		 */
		EClass EXTERNAL_REFERENCE = eINSTANCE.getExternalReference();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_REFERENCE__NAME = eINSTANCE.getExternalReference_Name();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_REFERENCE__PATH = eINSTANCE.getExternalReference_Path();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.ComprehensionExpressionImpl <em>Comprehension Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.ComprehensionExpressionImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getComprehensionExpression()
		 * @generated
		 */
		EClass COMPREHENSION_EXPRESSION = eINSTANCE.getComprehensionExpression();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPREHENSION_EXPRESSION__DECLARATION = eINSTANCE.getComprehensionExpression_Declaration();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPREHENSION_EXPRESSION__BLOCK = eINSTANCE.getComprehensionExpression_Block();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPREHENSION_EXPRESSION__EXPRESSION = eINSTANCE.getComprehensionExpression_Expression();

		/**
		 * The meta object literal for the '<em><b>Has Block</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPREHENSION_EXPRESSION__HAS_BLOCK = eINSTANCE.getComprehensionExpression_HasBlock();

		/**
		 * The meta object literal for the '<em><b>Has Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPREHENSION_EXPRESSION__HAS_EXPRESSION = eINSTANCE.getComprehensionExpression_HasExpression();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.FunctionInvocationImpl <em>Function Invocation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.FunctionInvocationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getFunctionInvocation()
		 * @generated
		 */
		EClass FUNCTION_INVOCATION = eINSTANCE.getFunctionInvocation();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_INVOCATION__PARAMETER = eINSTANCE.getFunctionInvocation_Parameter();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION_INVOCATION__FUNCTION = eINSTANCE.getFunctionInvocation_Function();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.PredicateInvocationImpl <em>Predicate Invocation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.PredicateInvocationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getPredicateInvocation()
		 * @generated
		 */
		EClass PREDICATE_INVOCATION = eINSTANCE.getPredicateInvocation();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PREDICATE_INVOCATION__PARAMETER = eINSTANCE.getPredicateInvocation_Parameter();

		/**
		 * The meta object literal for the '<em><b>Predicate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDICATE_INVOCATION__PREDICATE = eINSTANCE.getPredicateInvocation_Predicate();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.DisjointExpressionImpl <em>Disjoint Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.DisjointExpressionImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getDisjointExpression()
		 * @generated
		 */
		EClass DISJOINT_EXPRESSION = eINSTANCE.getDisjointExpression();

		/**
		 * The meta object literal for the '<em><b>Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DISJOINT_EXPRESSION__SET = eINSTANCE.getDisjointExpression_Set();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.ImpliesOperationImpl <em>Implies Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.ImpliesOperationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getImpliesOperation()
		 * @generated
		 */
		EClass IMPLIES_OPERATION = eINSTANCE.getImpliesOperation();

		/**
		 * The meta object literal for the '<em><b>Else</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLIES_OPERATION__ELSE = eINSTANCE.getImpliesOperation_Else();

		/**
		 * The meta object literal for the '<em><b>Implication</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLIES_OPERATION__IMPLICATION = eINSTANCE.getImpliesOperation_Implication();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLIES_OPERATION__CONDITION = eINSTANCE.getImpliesOperation_Condition();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.QuantificationExpressionImpl <em>Quantification Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.QuantificationExpressionImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getQuantificationExpression()
		 * @generated
		 */
		EClass QUANTIFICATION_EXPRESSION = eINSTANCE.getQuantificationExpression();

		/**
		 * The meta object literal for the '<em><b>Quantificator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUANTIFICATION_EXPRESSION__QUANTIFICATOR = eINSTANCE.getQuantificationExpression_Quantificator();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUANTIFICATION_EXPRESSION__DECLARATION = eINSTANCE.getQuantificationExpression_Declaration();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUANTIFICATION_EXPRESSION__EXPRESSION = eINSTANCE.getQuantificationExpression_Expression();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUANTIFICATION_EXPRESSION__BLOCK = eINSTANCE.getQuantificationExpression_Block();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.VariableImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__NAME = eINSTANCE.getVariable_Name();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE__DECLARATION = eINSTANCE.getVariable_Declaration();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.impl.EnumDeclarationImpl <em>Enum Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.impl.EnumDeclarationImpl
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getEnumDeclaration()
		 * @generated
		 */
		EClass ENUM_DECLARATION = eINSTANCE.getEnumDeclaration();

		/**
		 * The meta object literal for the '<em><b>Literals</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUM_DECLARATION__LITERALS = eINSTANCE.getEnumDeclaration_Literals();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.Multiplicity <em>Multiplicity</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.Multiplicity
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getMultiplicity()
		 * @generated
		 */
		EEnum MULTIPLICITY = eINSTANCE.getMultiplicity();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.BinaryOperator <em>Binary Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.BinaryOperator
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getBinaryOperator()
		 * @generated
		 */
		EEnum BINARY_OPERATOR = eINSTANCE.getBinaryOperator();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.UnaryOperator <em>Unary Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.UnaryOperator
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getUnaryOperator()
		 * @generated
		 */
		EEnum UNARY_OPERATOR = eINSTANCE.getUnaryOperator();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.CompareOperator <em>Compare Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.CompareOperator
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getCompareOperator()
		 * @generated
		 */
		EEnum COMPARE_OPERATOR = eINSTANCE.getCompareOperator();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.Constant <em>Constant</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.Constant
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getConstant()
		 * @generated
		 */
		EEnum CONSTANT = eINSTANCE.getConstant();

		/**
		 * The meta object literal for the '{@link br.ufes.inf.nemo.alloy.Quantificator <em>Quantificator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see br.ufes.inf.nemo.alloy.Quantificator
		 * @see br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl#getQuantificator()
		 * @generated
		 */
		EEnum QUANTIFICATOR = eINSTANCE.getQuantificator();

	}

} //AlloyPackage
