/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.util;

import RefOntoUML.AggregationKind;
import RefOntoUML.AntiRigidMixinClass;
import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Association;
import RefOntoUML.BasicMeasurementRegion;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Comment;
import RefOntoUML.ComposedMeasurementRegion;
import RefOntoUML.Constraintx;
import RefOntoUML.DataType;
import RefOntoUML.DecimalIntervalDimension;
import RefOntoUML.DecimalMeasurementRegion;
import RefOntoUML.DecimalOrdinalDimension;
import RefOntoUML.DecimalRationalDimension;
import RefOntoUML.Dependency;
import RefOntoUML.DependencyRelationship;
import RefOntoUML.Derivation;
import RefOntoUML.DirectedBinaryAssociation;
import RefOntoUML.DirectedRelationship;
import RefOntoUML.Element;
import RefOntoUML.ElementImport;
import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.Expression;
import RefOntoUML.Feature;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.InstanceSpecification;
import RefOntoUML.InstanceValue;
import RefOntoUML.IntegerIntervalDimension;
import RefOntoUML.IntegerMeasurementRegion;
import RefOntoUML.IntegerOrdinalDimension;
import RefOntoUML.IntegerRationalDimension;
import RefOntoUML.IntervalDimension;
import RefOntoUML.IntrinsicMomentClass;
import RefOntoUML.Kind;
import RefOntoUML.LiteralBoolean;
import RefOntoUML.LiteralDecimal;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralNull;
import RefOntoUML.LiteralSpecification;
import RefOntoUML.LiteralString;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.MeasurableQuality;
import RefOntoUML.MeasurementDimension;
import RefOntoUML.MeasurementDomain;
import RefOntoUML.MeasurementRegion;
import RefOntoUML.MeasurementStructure;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mixin;
import RefOntoUML.MixinClass;
import RefOntoUML.Mode;
import RefOntoUML.Model;
import RefOntoUML.MomentClass;
import RefOntoUML.MultiplicityElement;
import RefOntoUML.NamedElement;
import RefOntoUML.Namespace;
import RefOntoUML.NominalQuality;
import RefOntoUML.NominalRegion;
import RefOntoUML.NominalStructure;
import RefOntoUML.NonPerceivableQuality;
import RefOntoUML.NonRigidMixinClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.OpaqueExpression;
import RefOntoUML.OrdinalDimension;
import RefOntoUML.OrdinalEnumeration;
import RefOntoUML.OrdinalLiteral;
import RefOntoUML.PackageImport;
import RefOntoUML.PackageMerge;
import RefOntoUML.PackageableElement;
import RefOntoUML.PerceivableQuality;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Quality;
import RefOntoUML.Quantity;
import RefOntoUML.RationalDimension;
import RefOntoUML.RedefinableElement;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.ReferenceRegion;
import RefOntoUML.ReferenceStructure;
import RefOntoUML.Relationship;
import RefOntoUML.Relator;
import RefOntoUML.RigidMixinClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SemiRigidMixinClass;
import RefOntoUML.Slot;
import RefOntoUML.SortalClass;
import RefOntoUML.StringExpression;
import RefOntoUML.StringNominalRegion;
import RefOntoUML.StringNominalStructure;
import RefOntoUML.StructuralFeature;
import RefOntoUML.Structuration;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.Type;
import RefOntoUML.TypedElement;
import RefOntoUML.ValueSpecification;
import RefOntoUML.VisibilityKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;

import java.math.BigDecimal;

import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;

import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see RefOntoUML.RefOntoUMLPackage
 * @generated
 */
public class RefOntoUMLValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final RefOntoUMLValidator INSTANCE = new RefOntoUMLValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "RefOntoUML";

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Not own self' of 'Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int ELEMENT__NOT_OWN_SELF = 1;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Has owner' of 'Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int ELEMENT__HAS_OWNER = 2;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Elements public or private' of 'Package'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PACKAGE__ELEMENTS_PUBLIC_OR_PRIVATE = 3;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Has no qualified name' of 'Named Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int NAMED_ELEMENT__HAS_NO_QUALIFIED_NAME = 4;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Has qualified name' of 'Named Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int NAMED_ELEMENT__HAS_QUALIFIED_NAME = 5;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Visibility needs ownership' of 'Named Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int NAMED_ELEMENT__VISIBILITY_NEEDS_OWNERSHIP = 6;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Members distinguishable' of 'Namespace'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int NAMESPACE__MEMBERS_DISTINGUISHABLE = 7;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Visibility public or private' of 'Element Import'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int ELEMENT_IMPORT__VISIBILITY_PUBLIC_OR_PRIVATE = 8;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Imported element is public' of 'Element Import'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int ELEMENT_IMPORT__IMPORTED_ELEMENT_IS_PUBLIC = 9;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Public or private' of 'Package Import'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PACKAGE_IMPORT__PUBLIC_OR_PRIVATE = 10;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Not apply to self' of 'Constraintx'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CONSTRAINTX__NOT_APPLY_TO_SELF = 11;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Value specification boolean' of 'Constraintx'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CONSTRAINTX__VALUE_SPECIFICATION_BOOLEAN = 12;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Boolean value' of 'Constraintx'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CONSTRAINTX__BOOLEAN_VALUE = 13;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'No side effects' of 'Constraintx'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CONSTRAINTX__NO_SIDE_EFFECTS = 14;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Not applied to self' of 'Constraintx'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CONSTRAINTX__NOT_APPLIED_TO_SELF = 15;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Specialized end number' of 'Association'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int ASSOCIATION__SPECIALIZED_END_NUMBER = 16;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Specialized end types' of 'Association'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int ASSOCIATION__SPECIALIZED_END_TYPES = 17;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Binary associations' of 'Association'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int ASSOCIATION__BINARY_ASSOCIATIONS = 18;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Association ends' of 'Association'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int ASSOCIATION__ASSOCIATION_ENDS = 19;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'No cycles in generalization' of 'Classifier'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CLASSIFIER__NO_CYCLES_IN_GENERALIZATION = 20;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Generalization hierarchies' of 'Classifier'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CLASSIFIER__GENERALIZATION_HIERARCHIES = 21;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Specialize type' of 'Classifier'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CLASSIFIER__SPECIALIZE_TYPE = 22;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Maps to generalization set' of 'Classifier'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CLASSIFIER__MAPS_TO_GENERALIZATION_SET = 23;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Redefinition context valid' of 'Redefinable Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int REDEFINABLE_ELEMENT__REDEFINITION_CONTEXT_VALID = 24;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Redefinition consistent' of 'Redefinable Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int REDEFINABLE_ELEMENT__REDEFINITION_CONSISTENT = 25;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Generalization same classifier' of 'Generalization'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int GENERALIZATION__GENERALIZATION_SAME_CLASSIFIER = 26;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Generalization same classifier' of 'Generalization Set'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int GENERALIZATION_SET__GENERALIZATION_SAME_CLASSIFIER = 27;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Maps to generalization set' of 'Generalization Set'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int GENERALIZATION_SET__MAPS_TO_GENERALIZATION_SET = 28;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Language body size' of 'Opaque Expression'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int OPAQUE_EXPRESSION__LANGUAGE_BODY_SIZE = 29;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Only return result parameters' of 'Opaque Expression'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int OPAQUE_EXPRESSION__ONLY_RETURN_RESULT_PARAMETERS = 30;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'One return result parameter' of 'Opaque Expression'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int OPAQUE_EXPRESSION__ONE_RETURN_RESULT_PARAMETER = 31;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Lower ge 0' of 'Multiplicity Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int MULTIPLICITY_ELEMENT__LOWER_GE_0 = 32;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Upper ge lower' of 'Multiplicity Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int MULTIPLICITY_ELEMENT__UPPER_GE_LOWER = 33;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Value specification no side effects' of 'Multiplicity Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int MULTIPLICITY_ELEMENT__VALUE_SPECIFICATION_NO_SIDE_EFFECTS = 34;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Value specification constant' of 'Multiplicity Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int MULTIPLICITY_ELEMENT__VALUE_SPECIFICATION_CONSTANT = 35;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Multiplicity of composite' of 'Property'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PROPERTY__MULTIPLICITY_OF_COMPOSITE = 36;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Subsetting context conforms' of 'Property'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PROPERTY__SUBSETTING_CONTEXT_CONFORMS = 37;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Redefined property inherited' of 'Property'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PROPERTY__REDEFINED_PROPERTY_INHERITED = 38;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Subsetting rules' of 'Property'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PROPERTY__SUBSETTING_RULES = 39;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Navigable readonly' of 'Property'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PROPERTY__NAVIGABLE_READONLY = 40;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Derived union is derived' of 'Property'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PROPERTY__DERIVED_UNION_IS_DERIVED = 41;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Derived union is read only' of 'Property'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PROPERTY__DERIVED_UNION_IS_READ_ONLY = 42;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Subsetted property names' of 'Property'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PROPERTY__SUBSETTED_PROPERTY_NAMES = 43;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Deployment target' of 'Property'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PROPERTY__DEPLOYMENT_TARGET = 44;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Binding to attribute' of 'Property'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PROPERTY__BINDING_TO_ATTRIBUTE = 45;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Passive class' of 'Class'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CLASS__PASSIVE_CLASS = 46;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Operands' of 'String Expression'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int STRING_EXPRESSION__OPERANDS = 47;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Subexpressions' of 'String Expression'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int STRING_EXPRESSION__SUBEXPRESSIONS = 48;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Defining feature' of 'Instance Specification'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int INSTANCE_SPECIFICATION__DEFINING_FEATURE = 49;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Structural feature' of 'Instance Specification'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int INSTANCE_SPECIFICATION__STRUCTURAL_FEATURE = 50;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Deployment target' of 'Instance Specification'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int INSTANCE_SPECIFICATION__DEPLOYMENT_TARGET = 51;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Deployment artifact' of 'Instance Specification'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int INSTANCE_SPECIFICATION__DEPLOYMENT_ARTIFACT = 52;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 52;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The parsed OCL expression for the definition of the '<em>GeneralizationConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint generalization_GeneralizationConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>LowerAndUpperBound</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint multiplicityElement_LowerAndUpperBoundInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>ClassAttributeConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint class__ClassAttributeConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>DataTypeAttributeConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint dataType_DataTypeAttributeConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>OrdinalEnumerationConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint ordinalEnumeration_OrdinalEnumerationConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>NominalStructureConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint nominalStructure_NominalStructureConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MeasurementDimensionConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint measurementDimension_MeasurementDimensionConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MeasurementDomainConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint measurementDomain_MeasurementDomainConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MeasurementDomainConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint measurementDomain_MeasurementDomainConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>ComposedMeasurementRegionConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint composedMeasurementRegion_ComposedMeasurementRegionConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>SubstanceSortalConstraint2a</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint objectClass_SubstanceSortalConstraint2aInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>SortalClassConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint sortalClass_SortalClassConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MixinClassConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint mixinClass_MixinClassConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MixinClassConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint mixinClass_MixinClassConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>RigidSortalClassConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint rigidSortalClass_RigidSortalClassConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>SubstanceSortalConstraint2b</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint substanceSortal_SubstanceSortalConstraint2bInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>CollectiveConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint collective_CollectiveConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>PhaseConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint phase_PhaseConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>RoleConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint role_RoleConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>CategoryConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint category_CategoryConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>RoleMixinConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint roleMixin_RoleMixinConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MixinConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint mixin_MixinConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>IntrinsicMomentConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint intrinsicMomentClass_IntrinsicMomentConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>QualityConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint quality_QualityConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MeasurableQualityConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint measurableQuality_MeasurableQualityConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>NominalQualityConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint nominalQuality_NominalQualityConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>RelatorConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint relator_RelatorConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>RelatorConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint relator_RelatorConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>DirectedBinaryAssociationConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint directedBinaryAssociation_DirectedBinaryAssociationConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MeronymicConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint meronymic_MeronymicConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MeronymicConstraint2a</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint meronymic_MeronymicConstraint2aInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MeronymicConstraint2b</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint meronymic_MeronymicConstraint2bInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>subQuantityOfConstraint1a</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint subQuantityOf_subQuantityOfConstraint1aInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>subQuantityOfConstraint1b</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint subQuantityOf_subQuantityOfConstraint1bInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>subQuantityOfConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint subQuantityOf_subQuantityOfConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>subQuantityOfConstraint3</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint subQuantityOf_subQuantityOfConstraint3InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>subQuantityOfConstraint4</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint subQuantityOf_subQuantityOfConstraint4InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>subCollectionOfConstraint1a</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint subCollectionOf_subCollectionOfConstraint1aInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>subCollectionOfConstraint1b</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint subCollectionOf_subCollectionOfConstraint1bInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>subCollectionOfConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint subCollectionOf_subCollectionOfConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>memberOfConstraint1a</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint memberOf_memberOfConstraint1aInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>memberOfConstraint1b</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint memberOf_memberOfConstraint1bInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>memberOfConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint memberOf_memberOfConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>componentOfConstraint1a</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint componentOf_componentOfConstraint1aInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>componentOfConstraint1b</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint componentOf_componentOfConstraint1bInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>DependencyRelationshipConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint dependencyRelationship_DependencyRelationshipConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>DependencyRelationshipConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint dependencyRelationship_DependencyRelationshipConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>CharacterizationConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint characterization_CharacterizationConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>CharacterizationConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint characterization_CharacterizationConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MediationConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint mediation_MediationConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MediationConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint mediation_MediationConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>DerivationConstraint1a</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint derivation_DerivationConstraint1aInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>DerivationConstraint1b</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint derivation_DerivationConstraint1bInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>DerivationConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint derivation_DerivationConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>StructurationConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint structuration_StructurationConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>StructurationConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint structuration_StructurationConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>StructurationConstraint3</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint structuration_StructurationConstraint3InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MaterialAssociationConstraint1</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint materialAssociation_MaterialAssociationConstraint1InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MaterialAssociationConstraint2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint materialAssociation_MaterialAssociationConstraint2InvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MaterialAssociationConstraint3</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint materialAssociation_MaterialAssociationConstraint3InvOCL;
	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUMLValidator() {
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
	  return RefOntoUMLPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresonding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case RefOntoUMLPackage.COMMENT:
				return validateComment((Comment)value, diagnostics, context);
			case RefOntoUMLPackage.ELEMENT:
				return validateElement((Element)value, diagnostics, context);
			case RefOntoUMLPackage.PACKAGE:
				return validatePackage((RefOntoUML.Package)value, diagnostics, context);
			case RefOntoUMLPackage.PACKAGEABLE_ELEMENT:
				return validatePackageableElement((PackageableElement)value, diagnostics, context);
			case RefOntoUMLPackage.NAMED_ELEMENT:
				return validateNamedElement((NamedElement)value, diagnostics, context);
			case RefOntoUMLPackage.DEPENDENCY:
				return validateDependency((Dependency)value, diagnostics, context);
			case RefOntoUMLPackage.DIRECTED_RELATIONSHIP:
				return validateDirectedRelationship((DirectedRelationship)value, diagnostics, context);
			case RefOntoUMLPackage.RELATIONSHIP:
				return validateRelationship((Relationship)value, diagnostics, context);
			case RefOntoUMLPackage.NAMESPACE:
				return validateNamespace((Namespace)value, diagnostics, context);
			case RefOntoUMLPackage.ELEMENT_IMPORT:
				return validateElementImport((ElementImport)value, diagnostics, context);
			case RefOntoUMLPackage.PACKAGE_IMPORT:
				return validatePackageImport((PackageImport)value, diagnostics, context);
			case RefOntoUMLPackage.CONSTRAINTX:
				return validateConstraintx((Constraintx)value, diagnostics, context);
			case RefOntoUMLPackage.VALUE_SPECIFICATION:
				return validateValueSpecification((ValueSpecification)value, diagnostics, context);
			case RefOntoUMLPackage.TYPED_ELEMENT:
				return validateTypedElement((TypedElement)value, diagnostics, context);
			case RefOntoUMLPackage.TYPE:
				return validateType((Type)value, diagnostics, context);
			case RefOntoUMLPackage.ASSOCIATION:
				return validateAssociation((Association)value, diagnostics, context);
			case RefOntoUMLPackage.CLASSIFIER:
				return validateClassifier((Classifier)value, diagnostics, context);
			case RefOntoUMLPackage.REDEFINABLE_ELEMENT:
				return validateRedefinableElement((RedefinableElement)value, diagnostics, context);
			case RefOntoUMLPackage.GENERALIZATION:
				return validateGeneralization((Generalization)value, diagnostics, context);
			case RefOntoUMLPackage.GENERALIZATION_SET:
				return validateGeneralizationSet((GeneralizationSet)value, diagnostics, context);
			case RefOntoUMLPackage.FEATURE:
				return validateFeature((Feature)value, diagnostics, context);
			case RefOntoUMLPackage.OPAQUE_EXPRESSION:
				return validateOpaqueExpression((OpaqueExpression)value, diagnostics, context);
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT:
				return validateMultiplicityElement((MultiplicityElement)value, diagnostics, context);
			case RefOntoUMLPackage.PROPERTY:
				return validateProperty((Property)value, diagnostics, context);
			case RefOntoUMLPackage.CLASS:
				return validateClass((RefOntoUML.Class)value, diagnostics, context);
			case RefOntoUMLPackage.MODEL:
				return validateModel((Model)value, diagnostics, context);
			case RefOntoUMLPackage.DATA_TYPE:
				return validateDataType((DataType)value, diagnostics, context);
			case RefOntoUMLPackage.STRUCTURAL_FEATURE:
				return validateStructuralFeature((StructuralFeature)value, diagnostics, context);
			case RefOntoUMLPackage.STRING_EXPRESSION:
				return validateStringExpression((StringExpression)value, diagnostics, context);
			case RefOntoUMLPackage.EXPRESSION:
				return validateExpression((Expression)value, diagnostics, context);
			case RefOntoUMLPackage.PACKAGE_MERGE:
				return validatePackageMerge((PackageMerge)value, diagnostics, context);
			case RefOntoUMLPackage.INSTANCE_SPECIFICATION:
				return validateInstanceSpecification((InstanceSpecification)value, diagnostics, context);
			case RefOntoUMLPackage.SLOT:
				return validateSlot((Slot)value, diagnostics, context);
			case RefOntoUMLPackage.LITERAL_SPECIFICATION:
				return validateLiteralSpecification((LiteralSpecification)value, diagnostics, context);
			case RefOntoUMLPackage.ENUMERATION:
				return validateEnumeration((Enumeration)value, diagnostics, context);
			case RefOntoUMLPackage.ENUMERATION_LITERAL:
				return validateEnumerationLiteral((EnumerationLiteral)value, diagnostics, context);
			case RefOntoUMLPackage.ORDINAL_ENUMERATION:
				return validateOrdinalEnumeration((OrdinalEnumeration)value, diagnostics, context);
			case RefOntoUMLPackage.ORDINAL_LITERAL:
				return validateOrdinalLiteral((OrdinalLiteral)value, diagnostics, context);
			case RefOntoUMLPackage.PRIMITIVE_TYPE:
				return validatePrimitiveType((PrimitiveType)value, diagnostics, context);
			case RefOntoUMLPackage.REFERENCE_STRUCTURE:
				return validateReferenceStructure((ReferenceStructure)value, diagnostics, context);
			case RefOntoUMLPackage.NOMINAL_STRUCTURE:
				return validateNominalStructure((NominalStructure)value, diagnostics, context);
			case RefOntoUMLPackage.STRING_NOMINAL_STRUCTURE:
				return validateStringNominalStructure((StringNominalStructure)value, diagnostics, context);
			case RefOntoUMLPackage.MEASUREMENT_STRUCTURE:
				return validateMeasurementStructure((MeasurementStructure)value, diagnostics, context);
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION:
				return validateMeasurementDimension((MeasurementDimension)value, diagnostics, context);
			case RefOntoUMLPackage.ORDINAL_DIMENSION:
				return validateOrdinalDimension((OrdinalDimension)value, diagnostics, context);
			case RefOntoUMLPackage.INTEGER_ORDINAL_DIMENSION:
				return validateIntegerOrdinalDimension((IntegerOrdinalDimension)value, diagnostics, context);
			case RefOntoUMLPackage.DECIMAL_ORDINAL_DIMENSION:
				return validateDecimalOrdinalDimension((DecimalOrdinalDimension)value, diagnostics, context);
			case RefOntoUMLPackage.INTERVAL_DIMENSION:
				return validateIntervalDimension((IntervalDimension)value, diagnostics, context);
			case RefOntoUMLPackage.INTEGER_INTERVAL_DIMENSION:
				return validateIntegerIntervalDimension((IntegerIntervalDimension)value, diagnostics, context);
			case RefOntoUMLPackage.DECIMAL_INTERVAL_DIMENSION:
				return validateDecimalIntervalDimension((DecimalIntervalDimension)value, diagnostics, context);
			case RefOntoUMLPackage.RATIONAL_DIMENSION:
				return validateRationalDimension((RationalDimension)value, diagnostics, context);
			case RefOntoUMLPackage.INTEGER_RATIONAL_DIMENSION:
				return validateIntegerRationalDimension((IntegerRationalDimension)value, diagnostics, context);
			case RefOntoUMLPackage.DECIMAL_RATIONAL_DIMENSION:
				return validateDecimalRationalDimension((DecimalRationalDimension)value, diagnostics, context);
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN:
				return validateMeasurementDomain((MeasurementDomain)value, diagnostics, context);
			case RefOntoUMLPackage.REFERENCE_REGION:
				return validateReferenceRegion((ReferenceRegion)value, diagnostics, context);
			case RefOntoUMLPackage.MEASUREMENT_REGION:
				return validateMeasurementRegion((MeasurementRegion)value, diagnostics, context);
			case RefOntoUMLPackage.BASIC_MEASUREMENT_REGION:
				return validateBasicMeasurementRegion((BasicMeasurementRegion)value, diagnostics, context);
			case RefOntoUMLPackage.DECIMAL_MEASUREMENT_REGION:
				return validateDecimalMeasurementRegion((DecimalMeasurementRegion)value, diagnostics, context);
			case RefOntoUMLPackage.INTEGER_MEASUREMENT_REGION:
				return validateIntegerMeasurementRegion((IntegerMeasurementRegion)value, diagnostics, context);
			case RefOntoUMLPackage.COMPOSED_MEASUREMENT_REGION:
				return validateComposedMeasurementRegion((ComposedMeasurementRegion)value, diagnostics, context);
			case RefOntoUMLPackage.NOMINAL_REGION:
				return validateNominalRegion((NominalRegion)value, diagnostics, context);
			case RefOntoUMLPackage.STRING_NOMINAL_REGION:
				return validateStringNominalRegion((StringNominalRegion)value, diagnostics, context);
			case RefOntoUMLPackage.LITERAL_INTEGER:
				return validateLiteralInteger((LiteralInteger)value, diagnostics, context);
			case RefOntoUMLPackage.LITERAL_DECIMAL:
				return validateLiteralDecimal((LiteralDecimal)value, diagnostics, context);
			case RefOntoUMLPackage.LITERAL_STRING:
				return validateLiteralString((LiteralString)value, diagnostics, context);
			case RefOntoUMLPackage.LITERAL_BOOLEAN:
				return validateLiteralBoolean((LiteralBoolean)value, diagnostics, context);
			case RefOntoUMLPackage.LITERAL_NULL:
				return validateLiteralNull((LiteralNull)value, diagnostics, context);
			case RefOntoUMLPackage.INSTANCE_VALUE:
				return validateInstanceValue((InstanceValue)value, diagnostics, context);
			case RefOntoUMLPackage.LITERAL_UNLIMITED_NATURAL:
				return validateLiteralUnlimitedNatural((LiteralUnlimitedNatural)value, diagnostics, context);
			case RefOntoUMLPackage.OBJECT_CLASS:
				return validateObjectClass((ObjectClass)value, diagnostics, context);
			case RefOntoUMLPackage.MOMENT_CLASS:
				return validateMomentClass((MomentClass)value, diagnostics, context);
			case RefOntoUMLPackage.SORTAL_CLASS:
				return validateSortalClass((SortalClass)value, diagnostics, context);
			case RefOntoUMLPackage.MIXIN_CLASS:
				return validateMixinClass((MixinClass)value, diagnostics, context);
			case RefOntoUMLPackage.RIGID_SORTAL_CLASS:
				return validateRigidSortalClass((RigidSortalClass)value, diagnostics, context);
			case RefOntoUMLPackage.ANTI_RIGID_SORTAL_CLASS:
				return validateAntiRigidSortalClass((AntiRigidSortalClass)value, diagnostics, context);
			case RefOntoUMLPackage.SUBSTANCE_SORTAL:
				return validateSubstanceSortal((SubstanceSortal)value, diagnostics, context);
			case RefOntoUMLPackage.SUB_KIND:
				return validateSubKind((SubKind)value, diagnostics, context);
			case RefOntoUMLPackage.KIND:
				return validateKind((Kind)value, diagnostics, context);
			case RefOntoUMLPackage.QUANTITY:
				return validateQuantity((Quantity)value, diagnostics, context);
			case RefOntoUMLPackage.COLLECTIVE:
				return validateCollective((Collective)value, diagnostics, context);
			case RefOntoUMLPackage.PHASE:
				return validatePhase((Phase)value, diagnostics, context);
			case RefOntoUMLPackage.ROLE:
				return validateRole((Role)value, diagnostics, context);
			case RefOntoUMLPackage.RIGID_MIXIN_CLASS:
				return validateRigidMixinClass((RigidMixinClass)value, diagnostics, context);
			case RefOntoUMLPackage.NON_RIGID_MIXIN_CLASS:
				return validateNonRigidMixinClass((NonRigidMixinClass)value, diagnostics, context);
			case RefOntoUMLPackage.CATEGORY:
				return validateCategory((Category)value, diagnostics, context);
			case RefOntoUMLPackage.ANTI_RIGID_MIXIN_CLASS:
				return validateAntiRigidMixinClass((AntiRigidMixinClass)value, diagnostics, context);
			case RefOntoUMLPackage.SEMI_RIGID_MIXIN_CLASS:
				return validateSemiRigidMixinClass((SemiRigidMixinClass)value, diagnostics, context);
			case RefOntoUMLPackage.ROLE_MIXIN:
				return validateRoleMixin((RoleMixin)value, diagnostics, context);
			case RefOntoUMLPackage.MIXIN:
				return validateMixin((Mixin)value, diagnostics, context);
			case RefOntoUMLPackage.INTRINSIC_MOMENT_CLASS:
				return validateIntrinsicMomentClass((IntrinsicMomentClass)value, diagnostics, context);
			case RefOntoUMLPackage.MODE:
				return validateMode((Mode)value, diagnostics, context);
			case RefOntoUMLPackage.QUALITY:
				return validateQuality((Quality)value, diagnostics, context);
			case RefOntoUMLPackage.MEASURABLE_QUALITY:
				return validateMeasurableQuality((MeasurableQuality)value, diagnostics, context);
			case RefOntoUMLPackage.NOMINAL_QUALITY:
				return validateNominalQuality((NominalQuality)value, diagnostics, context);
			case RefOntoUMLPackage.NON_PERCEIVABLE_QUALITY:
				return validateNonPerceivableQuality((NonPerceivableQuality)value, diagnostics, context);
			case RefOntoUMLPackage.PERCEIVABLE_QUALITY:
				return validatePerceivableQuality((PerceivableQuality)value, diagnostics, context);
			case RefOntoUMLPackage.RELATOR:
				return validateRelator((Relator)value, diagnostics, context);
			case RefOntoUMLPackage.DIRECTED_BINARY_ASSOCIATION:
				return validateDirectedBinaryAssociation((DirectedBinaryAssociation)value, diagnostics, context);
			case RefOntoUMLPackage.MERONYMIC:
				return validateMeronymic((Meronymic)value, diagnostics, context);
			case RefOntoUMLPackage.SUB_QUANTITY_OF:
				return validatesubQuantityOf((subQuantityOf)value, diagnostics, context);
			case RefOntoUMLPackage.SUB_COLLECTION_OF:
				return validatesubCollectionOf((subCollectionOf)value, diagnostics, context);
			case RefOntoUMLPackage.MEMBER_OF:
				return validatememberOf((memberOf)value, diagnostics, context);
			case RefOntoUMLPackage.COMPONENT_OF:
				return validatecomponentOf((componentOf)value, diagnostics, context);
			case RefOntoUMLPackage.DEPENDENCY_RELATIONSHIP:
				return validateDependencyRelationship((DependencyRelationship)value, diagnostics, context);
			case RefOntoUMLPackage.CHARACTERIZATION:
				return validateCharacterization((Characterization)value, diagnostics, context);
			case RefOntoUMLPackage.MEDIATION:
				return validateMediation((Mediation)value, diagnostics, context);
			case RefOntoUMLPackage.DERIVATION:
				return validateDerivation((Derivation)value, diagnostics, context);
			case RefOntoUMLPackage.STRUCTURATION:
				return validateStructuration((Structuration)value, diagnostics, context);
			case RefOntoUMLPackage.FORMAL_ASSOCIATION:
				return validateFormalAssociation((FormalAssociation)value, diagnostics, context);
			case RefOntoUMLPackage.MATERIAL_ASSOCIATION:
				return validateMaterialAssociation((MaterialAssociation)value, diagnostics, context);
			case RefOntoUMLPackage.VISIBILITY_KIND:
				return validateVisibilityKind((VisibilityKind)value, diagnostics, context);
			case RefOntoUMLPackage.AGGREGATION_KIND:
				return validateAggregationKind((AggregationKind)value, diagnostics, context);
			case RefOntoUMLPackage.INTEGER:
				return validateInteger(((Integer)value).intValue(), diagnostics, context);
			case RefOntoUMLPackage.BOOLEAN:
				return validateBoolean(((Boolean)value).booleanValue(), diagnostics, context);
			case RefOntoUMLPackage.STRING:
				return validateString((String)value, diagnostics, context);
			case RefOntoUMLPackage.UNLIMITED_NATURAL:
				return validateUnlimitedNatural(((Integer)value).intValue(), diagnostics, context);
			case RefOntoUMLPackage.DECIMAL:
				return validateDecimal((BigDecimal)value, diagnostics, context);
			default: 
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComment(Comment comment, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(comment, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(comment, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(comment, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(comment, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(comment, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(comment, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(comment, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(comment, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(comment, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateElement(Element element, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(element, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(element, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(element, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(element, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(element, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(element, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(element, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(element, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(element, diagnostics, context);
		return result;
	}

	/**
	 * Validates the not_own_self constraint of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateElement_not_own_self(Element element, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return element.not_own_self(diagnostics, context);
	}

	/**
	 * Validates the has_owner constraint of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateElement_has_owner(Element element, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return element.has_owner(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackage(RefOntoUML.Package package_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackage_elements_public_or_private(package_, diagnostics, context);
		return result;
	}

	/**
	 * Validates the elements_public_or_private constraint of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackage_elements_public_or_private(RefOntoUML.Package package_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return package_.elements_public_or_private(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackageableElement(PackageableElement packageableElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(packageableElement, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNamedElement(NamedElement namedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(namedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(namedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(namedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(namedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(namedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(namedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(namedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(namedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(namedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(namedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(namedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(namedElement, diagnostics, context);
		return result;
	}

	/**
	 * Validates the has_no_qualified_name constraint of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNamedElement_has_no_qualified_name(NamedElement namedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return namedElement.has_no_qualified_name(diagnostics, context);
	}

	/**
	 * Validates the has_qualified_name constraint of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNamedElement_has_qualified_name(NamedElement namedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return namedElement.has_qualified_name(diagnostics, context);
	}

	/**
	 * Validates the visibility_needs_ownership constraint of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNamedElement_visibility_needs_ownership(NamedElement namedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return namedElement.visibility_needs_ownership(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDependency(Dependency dependency, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(dependency, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dependency, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dependency, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dependency, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dependency, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dependency, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dependency, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(dependency, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(dependency, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(dependency, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(dependency, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(dependency, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDirectedRelationship(DirectedRelationship directedRelationship, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(directedRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(directedRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(directedRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(directedRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(directedRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(directedRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(directedRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(directedRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(directedRelationship, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRelationship(Relationship relationship, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(relationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(relationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(relationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(relationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(relationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(relationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(relationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(relationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(relationship, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNamespace(Namespace namespace, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(namespace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(namespace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(namespace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(namespace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(namespace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(namespace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(namespace, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(namespace, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(namespace, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(namespace, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(namespace, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(namespace, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(namespace, diagnostics, context);
		return result;
	}

	/**
	 * Validates the members_distinguishable constraint of '<em>Namespace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNamespace_members_distinguishable(Namespace namespace, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return namespace.members_distinguishable(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateElementImport(ElementImport elementImport, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(elementImport, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(elementImport, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(elementImport, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(elementImport, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(elementImport, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(elementImport, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(elementImport, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(elementImport, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(elementImport, diagnostics, context);
		if (result || diagnostics != null) result &= validateElementImport_visibility_public_or_private(elementImport, diagnostics, context);
		if (result || diagnostics != null) result &= validateElementImport_imported_element_is_public(elementImport, diagnostics, context);
		return result;
	}

	/**
	 * Validates the visibility_public_or_private constraint of '<em>Element Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateElementImport_visibility_public_or_private(ElementImport elementImport, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return elementImport.visibility_public_or_private(diagnostics, context);
	}

	/**
	 * Validates the imported_element_is_public constraint of '<em>Element Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateElementImport_imported_element_is_public(ElementImport elementImport, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return elementImport.imported_element_is_public(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackageImport(PackageImport packageImport, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(packageImport, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(packageImport, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(packageImport, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(packageImport, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(packageImport, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(packageImport, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(packageImport, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(packageImport, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(packageImport, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageImport_public_or_private(packageImport, diagnostics, context);
		return result;
	}

	/**
	 * Validates the public_or_private constraint of '<em>Package Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackageImport_public_or_private(PackageImport packageImport, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return packageImport.public_or_private(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConstraintx(Constraintx constraintx, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validateConstraintx_not_apply_to_self(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validateConstraintx_value_specification_boolean(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validateConstraintx_boolean_value(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validateConstraintx_no_side_effects(constraintx, diagnostics, context);
		if (result || diagnostics != null) result &= validateConstraintx_not_applied_to_self(constraintx, diagnostics, context);
		return result;
	}

	/**
	 * Validates the not_apply_to_self constraint of '<em>Constraintx</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConstraintx_not_apply_to_self(Constraintx constraintx, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return constraintx.not_apply_to_self(diagnostics, context);
	}

	/**
	 * Validates the value_specification_boolean constraint of '<em>Constraintx</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConstraintx_value_specification_boolean(Constraintx constraintx, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return constraintx.value_specification_boolean(diagnostics, context);
	}

	/**
	 * Validates the boolean_value constraint of '<em>Constraintx</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConstraintx_boolean_value(Constraintx constraintx, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return constraintx.boolean_value(diagnostics, context);
	}

	/**
	 * Validates the no_side_effects constraint of '<em>Constraintx</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConstraintx_no_side_effects(Constraintx constraintx, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return constraintx.no_side_effects(diagnostics, context);
	}

	/**
	 * Validates the not_applied_to_self constraint of '<em>Constraintx</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConstraintx_not_applied_to_self(Constraintx constraintx, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return constraintx.not_applied_to_self(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateValueSpecification(ValueSpecification valueSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(valueSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(valueSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(valueSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(valueSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(valueSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(valueSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(valueSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(valueSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(valueSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(valueSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(valueSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(valueSpecification, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypedElement(TypedElement typedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(typedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(typedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(typedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(typedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(typedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(typedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(typedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(typedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(typedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(typedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(typedElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(typedElement, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateType(Type type, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(type, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(type, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(type, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(type, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(type, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(type, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(type, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(type, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(type, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(type, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(type, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(type, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(association, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(association, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(association, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(association, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(association, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(association, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(association, diagnostics, context);
		return result;
	}

	/**
	 * Validates the specialized_end_number constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_specialized_end_number(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return association.specialized_end_number(diagnostics, context);
	}

	/**
	 * Validates the specialized_end_types constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_specialized_end_types(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return association.specialized_end_types(diagnostics, context);
	}

	/**
	 * Validates the binary_associations constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_binary_associations(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return association.binary_associations(diagnostics, context);
	}

	/**
	 * Validates the association_ends constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_association_ends(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return association.association_ends(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassifier(Classifier classifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(classifier, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(classifier, diagnostics, context);
		return result;
	}

	/**
	 * Validates the no_cycles_in_generalization constraint of '<em>Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassifier_no_cycles_in_generalization(Classifier classifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return classifier.no_cycles_in_generalization(diagnostics, context);
	}

	/**
	 * Validates the generalization_hierarchies constraint of '<em>Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassifier_generalization_hierarchies(Classifier classifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return classifier.generalization_hierarchies(diagnostics, context);
	}

	/**
	 * Validates the specialize_type constraint of '<em>Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassifier_specialize_type(Classifier classifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return classifier.specialize_type(diagnostics, context);
	}

	/**
	 * Validates the maps_to_generalization_set constraint of '<em>Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassifier_maps_to_generalization_set(Classifier classifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return classifier.maps_to_generalization_set(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRedefinableElement(RedefinableElement redefinableElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(redefinableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(redefinableElement, diagnostics, context);
		return result;
	}

	/**
	 * Validates the redefinition_context_valid constraint of '<em>Redefinable Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRedefinableElement_redefinition_context_valid(RedefinableElement redefinableElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return redefinableElement.redefinition_context_valid(diagnostics, context);
	}

	/**
	 * Validates the redefinition_consistent constraint of '<em>Redefinable Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRedefinableElement_redefinition_consistent(RedefinableElement redefinableElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return redefinableElement.redefinition_consistent(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeneralization(Generalization generalization, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(generalization, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(generalization, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(generalization, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(generalization, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(generalization, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(generalization, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(generalization, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(generalization, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(generalization, diagnostics, context);
		if (result || diagnostics != null) result &= validateGeneralization_GeneralizationConstraint1(generalization, diagnostics, context);
		if (result || diagnostics != null) result &= validateGeneralization_generalization_same_classifier(generalization, diagnostics, context);
		return result;
	}

	/**
	 * Validates the GeneralizationConstraint1 constraint of '<em>Generalization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeneralization_GeneralizationConstraint1(Generalization generalization, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (generalization_GeneralizationConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getGeneralization());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getGeneralization().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("GeneralizationConstraint1");
			
			try {
				generalization_GeneralizationConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(generalization_GeneralizationConstraint1InvOCL);
		
		if (!query.check(generalization)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "GeneralizationConstraint1", getObjectLabel(generalization, context) }),
						 new Object[] { generalization }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the generalization_same_classifier constraint of '<em>Generalization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeneralization_generalization_same_classifier(Generalization generalization, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return generalization.generalization_same_classifier(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeneralizationSet(GeneralizationSet generalizationSet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validateGeneralizationSet_generalization_same_classifier(generalizationSet, diagnostics, context);
		if (result || diagnostics != null) result &= validateGeneralizationSet_maps_to_generalization_set(generalizationSet, diagnostics, context);
		return result;
	}

	/**
	 * Validates the generalization_same_classifier constraint of '<em>Generalization Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeneralizationSet_generalization_same_classifier(GeneralizationSet generalizationSet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return generalizationSet.generalization_same_classifier(diagnostics, context);
	}

	/**
	 * Validates the maps_to_generalization_set constraint of '<em>Generalization Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeneralizationSet_maps_to_generalization_set(GeneralizationSet generalizationSet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return generalizationSet.maps_to_generalization_set(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFeature(Feature feature, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(feature, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(feature, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOpaqueExpression(OpaqueExpression opaqueExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateOpaqueExpression_language_body_size(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateOpaqueExpression_only_return_result_parameters(opaqueExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateOpaqueExpression_one_return_result_parameter(opaqueExpression, diagnostics, context);
		return result;
	}

	/**
	 * Validates the language_body_size constraint of '<em>Opaque Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOpaqueExpression_language_body_size(OpaqueExpression opaqueExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return opaqueExpression.language_body_size(diagnostics, context);
	}

	/**
	 * Validates the only_return_result_parameters constraint of '<em>Opaque Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOpaqueExpression_only_return_result_parameters(OpaqueExpression opaqueExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return opaqueExpression.only_return_result_parameters(diagnostics, context);
	}

	/**
	 * Validates the one_return_result_parameter constraint of '<em>Opaque Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOpaqueExpression_one_return_result_parameter(OpaqueExpression opaqueExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return opaqueExpression.one_return_result_parameter(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMultiplicityElement(MultiplicityElement multiplicityElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_LowerAndUpperBound(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_lower_ge_0(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_upper_ge_lower(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_value_specification_no_side_effects(multiplicityElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_value_specification_constant(multiplicityElement, diagnostics, context);
		return result;
	}

	/**
	 * Validates the LowerAndUpperBound constraint of '<em>Multiplicity Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMultiplicityElement_LowerAndUpperBound(MultiplicityElement multiplicityElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (multiplicityElement_LowerAndUpperBoundInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMultiplicityElement().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("LowerAndUpperBound");
			
			try {
				multiplicityElement_LowerAndUpperBoundInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(multiplicityElement_LowerAndUpperBoundInvOCL);
		
		if (!query.check(multiplicityElement)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "LowerAndUpperBound", getObjectLabel(multiplicityElement, context) }),
						 new Object[] { multiplicityElement }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the lower_ge_0 constraint of '<em>Multiplicity Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMultiplicityElement_lower_ge_0(MultiplicityElement multiplicityElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return multiplicityElement.lower_ge_0(diagnostics, context);
	}

	/**
	 * Validates the upper_ge_lower constraint of '<em>Multiplicity Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMultiplicityElement_upper_ge_lower(MultiplicityElement multiplicityElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return multiplicityElement.upper_ge_lower(diagnostics, context);
	}

	/**
	 * Validates the value_specification_no_side_effects constraint of '<em>Multiplicity Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMultiplicityElement_value_specification_no_side_effects(MultiplicityElement multiplicityElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return multiplicityElement.value_specification_no_side_effects(diagnostics, context);
	}

	/**
	 * Validates the value_specification_constant constraint of '<em>Multiplicity Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMultiplicityElement_value_specification_constant(MultiplicityElement multiplicityElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return multiplicityElement.value_specification_constant(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_LowerAndUpperBound(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_lower_ge_0(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_upper_ge_lower(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_value_specification_no_side_effects(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_value_specification_constant(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateProperty_multiplicity_of_composite(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateProperty_subsetting_context_conforms(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateProperty_redefined_property_inherited(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateProperty_subsetting_rules(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateProperty_navigable_readonly(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateProperty_derived_union_is_derived(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateProperty_derived_union_is_read_only(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateProperty_subsetted_property_names(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateProperty_deployment_target(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateProperty_binding_to_attribute(property, diagnostics, context);
		return result;
	}

	/**
	 * Validates the multiplicity_of_composite constraint of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty_multiplicity_of_composite(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return property.multiplicity_of_composite(diagnostics, context);
	}

	/**
	 * Validates the subsetting_context_conforms constraint of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty_subsetting_context_conforms(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return property.subsetting_context_conforms(diagnostics, context);
	}

	/**
	 * Validates the redefined_property_inherited constraint of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty_redefined_property_inherited(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return property.redefined_property_inherited(diagnostics, context);
	}

	/**
	 * Validates the subsetting_rules constraint of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty_subsetting_rules(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return property.subsetting_rules(diagnostics, context);
	}

	/**
	 * Validates the navigable_readonly constraint of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty_navigable_readonly(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return property.navigable_readonly(diagnostics, context);
	}

	/**
	 * Validates the derived_union_is_derived constraint of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty_derived_union_is_derived(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return property.derived_union_is_derived(diagnostics, context);
	}

	/**
	 * Validates the derived_union_is_read_only constraint of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty_derived_union_is_read_only(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return property.derived_union_is_read_only(diagnostics, context);
	}

	/**
	 * Validates the subsetted_property_names constraint of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty_subsetted_property_names(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return property.subsetted_property_names(diagnostics, context);
	}

	/**
	 * Validates the deployment_target constraint of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty_deployment_target(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return property.deployment_target(diagnostics, context);
	}

	/**
	 * Validates the binding_to_attribute constraint of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty_binding_to_attribute(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return property.binding_to_attribute(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClass(RefOntoUML.Class class_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(class_, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(class_, diagnostics, context);
		return result;
	}

	/**
	 * Validates the ClassAttributeConstraint1 constraint of '<em>Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClass_ClassAttributeConstraint1(RefOntoUML.Class class_, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (class__ClassAttributeConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getClass_());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getClass_().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("ClassAttributeConstraint1");
			
			try {
				class__ClassAttributeConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(class__ClassAttributeConstraint1InvOCL);
		
		if (!query.check(class_)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "ClassAttributeConstraint1", getObjectLabel(class_, context) }),
						 new Object[] { class_ }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the passive_class constraint of '<em>Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClass_passive_class(RefOntoUML.Class class_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return class_.passive_class(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModel(Model model, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(model, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(model, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(model, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(model, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(model, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(model, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(model, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(model, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(model, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(model, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(model, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(model, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(model, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackage_elements_public_or_private(model, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataType(DataType dataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(dataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(dataType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the DataTypeAttributeConstraint1 constraint of '<em>Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataType_DataTypeAttributeConstraint1(DataType dataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (dataType_DataTypeAttributeConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getDataType());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getDataType().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("DataTypeAttributeConstraint1");
			
			try {
				dataType_DataTypeAttributeConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(dataType_DataTypeAttributeConstraint1InvOCL);
		
		if (!query.check(dataType)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "DataTypeAttributeConstraint1", getObjectLabel(dataType, context) }),
						 new Object[] { dataType }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStructuralFeature(StructuralFeature structuralFeature, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_LowerAndUpperBound(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_lower_ge_0(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_upper_ge_lower(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_value_specification_no_side_effects(structuralFeature, diagnostics, context);
		if (result || diagnostics != null) result &= validateMultiplicityElement_value_specification_constant(structuralFeature, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringExpression(StringExpression stringExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateStringExpression_operands(stringExpression, diagnostics, context);
		if (result || diagnostics != null) result &= validateStringExpression_subexpressions(stringExpression, diagnostics, context);
		return result;
	}

	/**
	 * Validates the operands constraint of '<em>String Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringExpression_operands(StringExpression stringExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return stringExpression.operands(diagnostics, context);
	}

	/**
	 * Validates the subexpressions constraint of '<em>String Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringExpression_subexpressions(StringExpression stringExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return stringExpression.subexpressions(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExpression(Expression expression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(expression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(expression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(expression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(expression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(expression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(expression, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(expression, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(expression, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(expression, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(expression, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(expression, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(expression, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackageMerge(PackageMerge packageMerge, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(packageMerge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(packageMerge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(packageMerge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(packageMerge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(packageMerge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(packageMerge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(packageMerge, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(packageMerge, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(packageMerge, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstanceSpecification(InstanceSpecification instanceSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanceSpecification_defining_feature(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanceSpecification_structural_feature(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanceSpecification_deployment_target(instanceSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanceSpecification_deployment_artifact(instanceSpecification, diagnostics, context);
		return result;
	}

	/**
	 * Validates the defining_feature constraint of '<em>Instance Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstanceSpecification_defining_feature(InstanceSpecification instanceSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return instanceSpecification.defining_feature(diagnostics, context);
	}

	/**
	 * Validates the structural_feature constraint of '<em>Instance Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstanceSpecification_structural_feature(InstanceSpecification instanceSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return instanceSpecification.structural_feature(diagnostics, context);
	}

	/**
	 * Validates the deployment_target constraint of '<em>Instance Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstanceSpecification_deployment_target(InstanceSpecification instanceSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return instanceSpecification.deployment_target(diagnostics, context);
	}

	/**
	 * Validates the deployment_artifact constraint of '<em>Instance Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstanceSpecification_deployment_artifact(InstanceSpecification instanceSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return instanceSpecification.deployment_artifact(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSlot(Slot slot, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(slot, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(slot, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(slot, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(slot, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(slot, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(slot, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(slot, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(slot, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(slot, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLiteralSpecification(LiteralSpecification literalSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(literalSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(literalSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(literalSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(literalSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(literalSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(literalSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(literalSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(literalSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(literalSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(literalSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(literalSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(literalSpecification, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEnumeration(Enumeration enumeration, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(enumeration, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEnumerationLiteral(EnumerationLiteral enumerationLiteral, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanceSpecification_defining_feature(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanceSpecification_structural_feature(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanceSpecification_deployment_target(enumerationLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanceSpecification_deployment_artifact(enumerationLiteral, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrdinalEnumeration(OrdinalEnumeration ordinalEnumeration, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDimension_MeasurementDimensionConstraint1(ordinalEnumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateOrdinalEnumeration_OrdinalEnumerationConstraint1(ordinalEnumeration, diagnostics, context);
		return result;
	}

	/**
	 * Validates the OrdinalEnumerationConstraint1 constraint of '<em>Ordinal Enumeration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrdinalEnumeration_OrdinalEnumerationConstraint1(OrdinalEnumeration ordinalEnumeration, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (ordinalEnumeration_OrdinalEnumerationConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getOrdinalEnumeration());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getOrdinalEnumeration().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("OrdinalEnumerationConstraint1");
			
			try {
				ordinalEnumeration_OrdinalEnumerationConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(ordinalEnumeration_OrdinalEnumerationConstraint1InvOCL);
		
		if (!query.check(ordinalEnumeration)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "OrdinalEnumerationConstraint1", getObjectLabel(ordinalEnumeration, context) }),
						 new Object[] { ordinalEnumeration }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrdinalLiteral(OrdinalLiteral ordinalLiteral, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanceSpecification_defining_feature(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanceSpecification_structural_feature(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanceSpecification_deployment_target(ordinalLiteral, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanceSpecification_deployment_artifact(ordinalLiteral, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePrimitiveType(PrimitiveType primitiveType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(primitiveType, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(primitiveType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateReferenceStructure(ReferenceStructure referenceStructure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(referenceStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(referenceStructure, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNominalStructure(NominalStructure nominalStructure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(nominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNominalStructure_NominalStructureConstraint1(nominalStructure, diagnostics, context);
		return result;
	}

	/**
	 * Validates the NominalStructureConstraint1 constraint of '<em>Nominal Structure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNominalStructure_NominalStructureConstraint1(NominalStructure nominalStructure, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (nominalStructure_NominalStructureConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getNominalStructure());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getNominalStructure().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("NominalStructureConstraint1");
			
			try {
				nominalStructure_NominalStructureConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(nominalStructure_NominalStructureConstraint1InvOCL);
		
		if (!query.check(nominalStructure)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "NominalStructureConstraint1", getObjectLabel(nominalStructure, context) }),
						 new Object[] { nominalStructure }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringNominalStructure(StringNominalStructure stringNominalStructure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(stringNominalStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNominalStructure_NominalStructureConstraint1(stringNominalStructure, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeasurementStructure(MeasurementStructure measurementStructure, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(measurementStructure, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(measurementStructure, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeasurementDimension(MeasurementDimension measurementDimension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(measurementDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDimension_MeasurementDimensionConstraint1(measurementDimension, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MeasurementDimensionConstraint1 constraint of '<em>Measurement Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeasurementDimension_MeasurementDimensionConstraint1(MeasurementDimension measurementDimension, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (measurementDimension_MeasurementDimensionConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMeasurementDimension());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMeasurementDimension().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MeasurementDimensionConstraint1");
			
			try {
				measurementDimension_MeasurementDimensionConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(measurementDimension_MeasurementDimensionConstraint1InvOCL);
		
		if (!query.check(measurementDimension)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MeasurementDimensionConstraint1", getObjectLabel(measurementDimension, context) }),
						 new Object[] { measurementDimension }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrdinalDimension(OrdinalDimension ordinalDimension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(ordinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDimension_MeasurementDimensionConstraint1(ordinalDimension, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIntegerOrdinalDimension(IntegerOrdinalDimension integerOrdinalDimension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(integerOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDimension_MeasurementDimensionConstraint1(integerOrdinalDimension, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDecimalOrdinalDimension(DecimalOrdinalDimension decimalOrdinalDimension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(decimalOrdinalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDimension_MeasurementDimensionConstraint1(decimalOrdinalDimension, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIntervalDimension(IntervalDimension intervalDimension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(intervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDimension_MeasurementDimensionConstraint1(intervalDimension, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIntegerIntervalDimension(IntegerIntervalDimension integerIntervalDimension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(integerIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDimension_MeasurementDimensionConstraint1(integerIntervalDimension, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDecimalIntervalDimension(DecimalIntervalDimension decimalIntervalDimension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(decimalIntervalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDimension_MeasurementDimensionConstraint1(decimalIntervalDimension, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRationalDimension(RationalDimension rationalDimension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(rationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDimension_MeasurementDimensionConstraint1(rationalDimension, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIntegerRationalDimension(IntegerRationalDimension integerRationalDimension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(integerRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDimension_MeasurementDimensionConstraint1(integerRationalDimension, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDecimalRationalDimension(DecimalRationalDimension decimalRationalDimension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(decimalRationalDimension, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDimension_MeasurementDimensionConstraint1(decimalRationalDimension, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeasurementDomain(MeasurementDomain measurementDomain, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateDataType_DataTypeAttributeConstraint1(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDomain_MeasurementDomainConstraint1(measurementDomain, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurementDomain_MeasurementDomainConstraint2(measurementDomain, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MeasurementDomainConstraint1 constraint of '<em>Measurement Domain</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeasurementDomain_MeasurementDomainConstraint1(MeasurementDomain measurementDomain, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (measurementDomain_MeasurementDomainConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMeasurementDomain());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMeasurementDomain().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MeasurementDomainConstraint1");
			
			try {
				measurementDomain_MeasurementDomainConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(measurementDomain_MeasurementDomainConstraint1InvOCL);
		
		if (!query.check(measurementDomain)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MeasurementDomainConstraint1", getObjectLabel(measurementDomain, context) }),
						 new Object[] { measurementDomain }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the MeasurementDomainConstraint2 constraint of '<em>Measurement Domain</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeasurementDomain_MeasurementDomainConstraint2(MeasurementDomain measurementDomain, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (measurementDomain_MeasurementDomainConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMeasurementDomain());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMeasurementDomain().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MeasurementDomainConstraint2");
			
			try {
				measurementDomain_MeasurementDomainConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(measurementDomain_MeasurementDomainConstraint2InvOCL);
		
		if (!query.check(measurementDomain)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MeasurementDomainConstraint2", getObjectLabel(measurementDomain, context) }),
						 new Object[] { measurementDomain }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateReferenceRegion(ReferenceRegion referenceRegion, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(referenceRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(referenceRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(referenceRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(referenceRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(referenceRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(referenceRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(referenceRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(referenceRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(referenceRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(referenceRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(referenceRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(referenceRegion, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeasurementRegion(MeasurementRegion measurementRegion, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(measurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(measurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(measurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(measurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(measurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(measurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(measurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(measurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(measurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(measurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(measurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(measurementRegion, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBasicMeasurementRegion(BasicMeasurementRegion basicMeasurementRegion, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(basicMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(basicMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(basicMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(basicMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(basicMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(basicMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(basicMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(basicMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(basicMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(basicMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(basicMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(basicMeasurementRegion, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDecimalMeasurementRegion(DecimalMeasurementRegion decimalMeasurementRegion, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(decimalMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(decimalMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(decimalMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(decimalMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(decimalMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(decimalMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(decimalMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(decimalMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(decimalMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(decimalMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(decimalMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(decimalMeasurementRegion, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIntegerMeasurementRegion(IntegerMeasurementRegion integerMeasurementRegion, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(integerMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(integerMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(integerMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(integerMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(integerMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(integerMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(integerMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(integerMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(integerMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(integerMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(integerMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(integerMeasurementRegion, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComposedMeasurementRegion(ComposedMeasurementRegion composedMeasurementRegion, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(composedMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(composedMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(composedMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(composedMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(composedMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(composedMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(composedMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(composedMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(composedMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(composedMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(composedMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(composedMeasurementRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateComposedMeasurementRegion_ComposedMeasurementRegionConstraint1(composedMeasurementRegion, diagnostics, context);
		return result;
	}

	/**
	 * Validates the ComposedMeasurementRegionConstraint1 constraint of '<em>Composed Measurement Region</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComposedMeasurementRegion_ComposedMeasurementRegionConstraint1(ComposedMeasurementRegion composedMeasurementRegion, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (composedMeasurementRegion_ComposedMeasurementRegionConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getComposedMeasurementRegion());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getComposedMeasurementRegion().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("ComposedMeasurementRegionConstraint1");
			
			try {
				composedMeasurementRegion_ComposedMeasurementRegionConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(composedMeasurementRegion_ComposedMeasurementRegionConstraint1InvOCL);
		
		if (!query.check(composedMeasurementRegion)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "ComposedMeasurementRegionConstraint1", getObjectLabel(composedMeasurementRegion, context) }),
						 new Object[] { composedMeasurementRegion }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNominalRegion(NominalRegion nominalRegion, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(nominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(nominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(nominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(nominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(nominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(nominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(nominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(nominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(nominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(nominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(nominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(nominalRegion, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringNominalRegion(StringNominalRegion stringNominalRegion, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(stringNominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(stringNominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(stringNominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(stringNominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(stringNominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(stringNominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(stringNominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(stringNominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(stringNominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(stringNominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(stringNominalRegion, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(stringNominalRegion, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLiteralInteger(LiteralInteger literalInteger, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(literalInteger, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(literalInteger, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(literalInteger, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(literalInteger, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(literalInteger, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(literalInteger, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(literalInteger, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(literalInteger, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(literalInteger, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(literalInteger, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(literalInteger, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(literalInteger, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLiteralDecimal(LiteralDecimal literalDecimal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(literalDecimal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(literalDecimal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(literalDecimal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(literalDecimal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(literalDecimal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(literalDecimal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(literalDecimal, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(literalDecimal, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(literalDecimal, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(literalDecimal, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(literalDecimal, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(literalDecimal, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLiteralString(LiteralString literalString, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(literalString, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(literalString, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(literalString, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(literalString, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(literalString, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(literalString, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(literalString, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(literalString, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(literalString, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(literalString, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(literalString, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(literalString, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLiteralBoolean(LiteralBoolean literalBoolean, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(literalBoolean, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(literalBoolean, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(literalBoolean, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(literalBoolean, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(literalBoolean, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(literalBoolean, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(literalBoolean, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(literalBoolean, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(literalBoolean, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(literalBoolean, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(literalBoolean, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(literalBoolean, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLiteralNull(LiteralNull literalNull, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(literalNull, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(literalNull, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(literalNull, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(literalNull, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(literalNull, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(literalNull, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(literalNull, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(literalNull, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(literalNull, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(literalNull, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(literalNull, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(literalNull, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstanceValue(InstanceValue instanceValue, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(instanceValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(instanceValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(instanceValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(instanceValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(instanceValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(instanceValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(instanceValue, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(instanceValue, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(instanceValue, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(instanceValue, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(instanceValue, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(instanceValue, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLiteralUnlimitedNatural(LiteralUnlimitedNatural literalUnlimitedNatural, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(literalUnlimitedNatural, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(literalUnlimitedNatural, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(literalUnlimitedNatural, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(literalUnlimitedNatural, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(literalUnlimitedNatural, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(literalUnlimitedNatural, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(literalUnlimitedNatural, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(literalUnlimitedNatural, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(literalUnlimitedNatural, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(literalUnlimitedNatural, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(literalUnlimitedNatural, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(literalUnlimitedNatural, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateObjectClass(ObjectClass objectClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(objectClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(objectClass, diagnostics, context);
		return result;
	}

	/**
	 * Validates the SubstanceSortalConstraint2a constraint of '<em>Object Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateObjectClass_SubstanceSortalConstraint2a(ObjectClass objectClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (objectClass_SubstanceSortalConstraint2aInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getObjectClass());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getObjectClass().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("SubstanceSortalConstraint2a");
			
			try {
				objectClass_SubstanceSortalConstraint2aInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(objectClass_SubstanceSortalConstraint2aInvOCL);
		
		if (!query.check(objectClass)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "SubstanceSortalConstraint2a", getObjectLabel(objectClass, context) }),
						 new Object[] { objectClass }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMomentClass(MomentClass momentClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(momentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(momentClass, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSortalClass(SortalClass sortalClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(sortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateSortalClass_SortalClassConstraint1(sortalClass, diagnostics, context);
		return result;
	}

	/**
	 * Validates the SortalClassConstraint1 constraint of '<em>Sortal Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSortalClass_SortalClassConstraint1(SortalClass sortalClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (sortalClass_SortalClassConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getSortalClass());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getSortalClass().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("SortalClassConstraint1");
			
			try {
				sortalClass_SortalClassConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(sortalClass_SortalClassConstraint1InvOCL);
		
		if (!query.check(sortalClass)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "SortalClassConstraint1", getObjectLabel(sortalClass, context) }),
						 new Object[] { sortalClass }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMixinClass(MixinClass mixinClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint1(mixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint2(mixinClass, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MixinClassConstraint1 constraint of '<em>Mixin Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMixinClass_MixinClassConstraint1(MixinClass mixinClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (mixinClass_MixinClassConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMixinClass());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMixinClass().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MixinClassConstraint1");
			
			try {
				mixinClass_MixinClassConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(mixinClass_MixinClassConstraint1InvOCL);
		
		if (!query.check(mixinClass)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MixinClassConstraint1", getObjectLabel(mixinClass, context) }),
						 new Object[] { mixinClass }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the MixinClassConstraint2 constraint of '<em>Mixin Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMixinClass_MixinClassConstraint2(MixinClass mixinClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (mixinClass_MixinClassConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMixinClass());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMixinClass().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MixinClassConstraint2");
			
			try {
				mixinClass_MixinClassConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(mixinClass_MixinClassConstraint2InvOCL);
		
		if (!query.check(mixinClass)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MixinClassConstraint2", getObjectLabel(mixinClass, context) }),
						 new Object[] { mixinClass }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRigidSortalClass(RigidSortalClass rigidSortalClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateSortalClass_SortalClassConstraint1(rigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRigidSortalClass_RigidSortalClassConstraint1(rigidSortalClass, diagnostics, context);
		return result;
	}

	/**
	 * Validates the RigidSortalClassConstraint1 constraint of '<em>Rigid Sortal Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRigidSortalClass_RigidSortalClassConstraint1(RigidSortalClass rigidSortalClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (rigidSortalClass_RigidSortalClassConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getRigidSortalClass());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getRigidSortalClass().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("RigidSortalClassConstraint1");
			
			try {
				rigidSortalClass_RigidSortalClassConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(rigidSortalClass_RigidSortalClassConstraint1InvOCL);
		
		if (!query.check(rigidSortalClass)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "RigidSortalClassConstraint1", getObjectLabel(rigidSortalClass, context) }),
						 new Object[] { rigidSortalClass }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAntiRigidSortalClass(AntiRigidSortalClass antiRigidSortalClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(antiRigidSortalClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateSortalClass_SortalClassConstraint1(antiRigidSortalClass, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSubstanceSortal(SubstanceSortal substanceSortal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateSortalClass_SortalClassConstraint1(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateRigidSortalClass_RigidSortalClassConstraint1(substanceSortal, diagnostics, context);
		if (result || diagnostics != null) result &= validateSubstanceSortal_SubstanceSortalConstraint2b(substanceSortal, diagnostics, context);
		return result;
	}

	/**
	 * Validates the SubstanceSortalConstraint2b constraint of '<em>Substance Sortal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSubstanceSortal_SubstanceSortalConstraint2b(SubstanceSortal substanceSortal, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (substanceSortal_SubstanceSortalConstraint2bInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getSubstanceSortal());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getSubstanceSortal().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("SubstanceSortalConstraint2b");
			
			try {
				substanceSortal_SubstanceSortalConstraint2bInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(substanceSortal_SubstanceSortalConstraint2bInvOCL);
		
		if (!query.check(substanceSortal)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "SubstanceSortalConstraint2b", getObjectLabel(substanceSortal, context) }),
						 new Object[] { substanceSortal }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSubKind(SubKind subKind, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateSortalClass_SortalClassConstraint1(subKind, diagnostics, context);
		if (result || diagnostics != null) result &= validateRigidSortalClass_RigidSortalClassConstraint1(subKind, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateKind(Kind kind, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateSortalClass_SortalClassConstraint1(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateRigidSortalClass_RigidSortalClassConstraint1(kind, diagnostics, context);
		if (result || diagnostics != null) result &= validateSubstanceSortal_SubstanceSortalConstraint2b(kind, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateQuantity(Quantity quantity, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateSortalClass_SortalClassConstraint1(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateRigidSortalClass_RigidSortalClassConstraint1(quantity, diagnostics, context);
		if (result || diagnostics != null) result &= validateSubstanceSortal_SubstanceSortalConstraint2b(quantity, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCollective(Collective collective, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateSortalClass_SortalClassConstraint1(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateRigidSortalClass_RigidSortalClassConstraint1(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateSubstanceSortal_SubstanceSortalConstraint2b(collective, diagnostics, context);
		if (result || diagnostics != null) result &= validateCollective_CollectiveConstraint1(collective, diagnostics, context);
		return result;
	}

	/**
	 * Validates the CollectiveConstraint1 constraint of '<em>Collective</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCollective_CollectiveConstraint1(Collective collective, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (collective_CollectiveConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getCollective());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getCollective().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("CollectiveConstraint1");
			
			try {
				collective_CollectiveConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(collective_CollectiveConstraint1InvOCL);
		
		if (!query.check(collective)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "CollectiveConstraint1", getObjectLabel(collective, context) }),
						 new Object[] { collective }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePhase(Phase phase, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validateSortalClass_SortalClassConstraint1(phase, diagnostics, context);
		if (result || diagnostics != null) result &= validatePhase_PhaseConstraint2(phase, diagnostics, context);
		return result;
	}

	/**
	 * Validates the PhaseConstraint2 constraint of '<em>Phase</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePhase_PhaseConstraint2(Phase phase, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (phase_PhaseConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getPhase());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getPhase().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("PhaseConstraint2");
			
			try {
				phase_PhaseConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(phase_PhaseConstraint2InvOCL);
		
		if (!query.check(phase)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "PhaseConstraint2", getObjectLabel(phase, context) }),
						 new Object[] { phase }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRole(Role role, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(role, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(role, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(role, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(role, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(role, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(role, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateSortalClass_SortalClassConstraint1(role, diagnostics, context);
		if (result || diagnostics != null) result &= validateRole_RoleConstraint2(role, diagnostics, context);
		return result;
	}

	/**
	 * Validates the RoleConstraint2 constraint of '<em>Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRole_RoleConstraint2(Role role, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (role_RoleConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getRole());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getRole().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("RoleConstraint2");
			
			try {
				role_RoleConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(role_RoleConstraint2InvOCL);
		
		if (!query.check(role)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "RoleConstraint2", getObjectLabel(role, context) }),
						 new Object[] { role }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRigidMixinClass(RigidMixinClass rigidMixinClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint1(rigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint2(rigidMixinClass, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNonRigidMixinClass(NonRigidMixinClass nonRigidMixinClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint1(nonRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint2(nonRigidMixinClass, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCategory(Category category, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(category, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(category, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(category, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(category, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(category, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(category, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint1(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint2(category, diagnostics, context);
		if (result || diagnostics != null) result &= validateCategory_CategoryConstraint1(category, diagnostics, context);
		return result;
	}

	/**
	 * Validates the CategoryConstraint1 constraint of '<em>Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCategory_CategoryConstraint1(Category category, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (category_CategoryConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getCategory());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getCategory().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("CategoryConstraint1");
			
			try {
				category_CategoryConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(category_CategoryConstraint1InvOCL);
		
		if (!query.check(category)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "CategoryConstraint1", getObjectLabel(category, context) }),
						 new Object[] { category }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAntiRigidMixinClass(AntiRigidMixinClass antiRigidMixinClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint1(antiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint2(antiRigidMixinClass, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSemiRigidMixinClass(SemiRigidMixinClass semiRigidMixinClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint1(semiRigidMixinClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint2(semiRigidMixinClass, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRoleMixin(RoleMixin roleMixin, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint1(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint2(roleMixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateRoleMixin_RoleMixinConstraint1(roleMixin, diagnostics, context);
		return result;
	}

	/**
	 * Validates the RoleMixinConstraint1 constraint of '<em>Role Mixin</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRoleMixin_RoleMixinConstraint1(RoleMixin roleMixin, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (roleMixin_RoleMixinConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getRoleMixin());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getRoleMixin().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("RoleMixinConstraint1");
			
			try {
				roleMixin_RoleMixinConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(roleMixin_RoleMixinConstraint1InvOCL);
		
		if (!query.check(roleMixin)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "RoleMixinConstraint1", getObjectLabel(roleMixin, context) }),
						 new Object[] { roleMixin }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMixin(Mixin mixin, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateObjectClass_SubstanceSortalConstraint2a(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint1(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixinClass_MixinClassConstraint2(mixin, diagnostics, context);
		if (result || diagnostics != null) result &= validateMixin_MixinConstraint1(mixin, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MixinConstraint1 constraint of '<em>Mixin</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMixin_MixinConstraint1(Mixin mixin, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (mixin_MixinConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMixin());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMixin().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MixinConstraint1");
			
			try {
				mixin_MixinConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(mixin_MixinConstraint1InvOCL);
		
		if (!query.check(mixin)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MixinConstraint1", getObjectLabel(mixin, context) }),
						 new Object[] { mixin }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIntrinsicMomentClass(IntrinsicMomentClass intrinsicMomentClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(intrinsicMomentClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateIntrinsicMomentClass_IntrinsicMomentConstraint1(intrinsicMomentClass, diagnostics, context);
		return result;
	}

	/**
	 * Validates the IntrinsicMomentConstraint1 constraint of '<em>Intrinsic Moment Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIntrinsicMomentClass_IntrinsicMomentConstraint1(IntrinsicMomentClass intrinsicMomentClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (intrinsicMomentClass_IntrinsicMomentConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getIntrinsicMomentClass());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getIntrinsicMomentClass().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("IntrinsicMomentConstraint1");
			
			try {
				intrinsicMomentClass_IntrinsicMomentConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(intrinsicMomentClass_IntrinsicMomentConstraint1InvOCL);
		
		if (!query.check(intrinsicMomentClass)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "IntrinsicMomentConstraint1", getObjectLabel(intrinsicMomentClass, context) }),
						 new Object[] { intrinsicMomentClass }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMode(Mode mode, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(mode, diagnostics, context);
		if (result || diagnostics != null) result &= validateIntrinsicMomentClass_IntrinsicMomentConstraint1(mode, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateQuality(Quality quality, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateIntrinsicMomentClass_IntrinsicMomentConstraint1(quality, diagnostics, context);
		if (result || diagnostics != null) result &= validateQuality_QualityConstraint1(quality, diagnostics, context);
		return result;
	}

	/**
	 * Validates the QualityConstraint1 constraint of '<em>Quality</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateQuality_QualityConstraint1(Quality quality, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (quality_QualityConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getQuality());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getQuality().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("QualityConstraint1");
			
			try {
				quality_QualityConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(quality_QualityConstraint1InvOCL);
		
		if (!query.check(quality)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "QualityConstraint1", getObjectLabel(quality, context) }),
						 new Object[] { quality }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeasurableQuality(MeasurableQuality measurableQuality, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateIntrinsicMomentClass_IntrinsicMomentConstraint1(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateQuality_QualityConstraint1(measurableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurableQuality_MeasurableQualityConstraint1(measurableQuality, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MeasurableQualityConstraint1 constraint of '<em>Measurable Quality</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeasurableQuality_MeasurableQualityConstraint1(MeasurableQuality measurableQuality, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (measurableQuality_MeasurableQualityConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMeasurableQuality());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMeasurableQuality().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MeasurableQualityConstraint1");
			
			try {
				measurableQuality_MeasurableQualityConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(measurableQuality_MeasurableQualityConstraint1InvOCL);
		
		if (!query.check(measurableQuality)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MeasurableQualityConstraint1", getObjectLabel(measurableQuality, context) }),
						 new Object[] { measurableQuality }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNominalQuality(NominalQuality nominalQuality, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateIntrinsicMomentClass_IntrinsicMomentConstraint1(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateQuality_QualityConstraint1(nominalQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNominalQuality_NominalQualityConstraint1(nominalQuality, diagnostics, context);
		return result;
	}

	/**
	 * Validates the NominalQualityConstraint1 constraint of '<em>Nominal Quality</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNominalQuality_NominalQualityConstraint1(NominalQuality nominalQuality, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (nominalQuality_NominalQualityConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getNominalQuality());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getNominalQuality().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("NominalQualityConstraint1");
			
			try {
				nominalQuality_NominalQualityConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(nominalQuality_NominalQualityConstraint1InvOCL);
		
		if (!query.check(nominalQuality)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "NominalQualityConstraint1", getObjectLabel(nominalQuality, context) }),
						 new Object[] { nominalQuality }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNonPerceivableQuality(NonPerceivableQuality nonPerceivableQuality, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateIntrinsicMomentClass_IntrinsicMomentConstraint1(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateQuality_QualityConstraint1(nonPerceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurableQuality_MeasurableQualityConstraint1(nonPerceivableQuality, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePerceivableQuality(PerceivableQuality perceivableQuality, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateIntrinsicMomentClass_IntrinsicMomentConstraint1(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateQuality_QualityConstraint1(perceivableQuality, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeasurableQuality_MeasurableQualityConstraint1(perceivableQuality, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRelator(Relator relator, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_ClassAttributeConstraint1(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateClass_passive_class(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateRelator_RelatorConstraint1(relator, diagnostics, context);
		if (result || diagnostics != null) result &= validateRelator_RelatorConstraint2(relator, diagnostics, context);
		return result;
	}

	/**
	 * Validates the RelatorConstraint1 constraint of '<em>Relator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRelator_RelatorConstraint1(Relator relator, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (relator_RelatorConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getRelator());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getRelator().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("RelatorConstraint1");
			
			try {
				relator_RelatorConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(relator_RelatorConstraint1InvOCL);
		
		if (!query.check(relator)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "RelatorConstraint1", getObjectLabel(relator, context) }),
						 new Object[] { relator }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the RelatorConstraint2 constraint of '<em>Relator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRelator_RelatorConstraint2(Relator relator, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (relator_RelatorConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getRelator());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getRelator().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("RelatorConstraint2");
			
			try {
				relator_RelatorConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(relator_RelatorConstraint2InvOCL);
		
		if (!query.check(relator)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "RelatorConstraint2", getObjectLabel(relator, context) }),
						 new Object[] { relator }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDirectedBinaryAssociation(DirectedBinaryAssociation directedBinaryAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(directedBinaryAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateDirectedBinaryAssociation_DirectedBinaryAssociationConstraint1(directedBinaryAssociation, diagnostics, context);
		return result;
	}

	/**
	 * Validates the DirectedBinaryAssociationConstraint1 constraint of '<em>Directed Binary Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDirectedBinaryAssociation_DirectedBinaryAssociationConstraint1(DirectedBinaryAssociation directedBinaryAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (directedBinaryAssociation_DirectedBinaryAssociationConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getDirectedBinaryAssociation());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getDirectedBinaryAssociation().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("DirectedBinaryAssociationConstraint1");
			
			try {
				directedBinaryAssociation_DirectedBinaryAssociationConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(directedBinaryAssociation_DirectedBinaryAssociationConstraint1InvOCL);
		
		if (!query.check(directedBinaryAssociation)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "DirectedBinaryAssociationConstraint1", getObjectLabel(directedBinaryAssociation, context) }),
						 new Object[] { directedBinaryAssociation }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeronymic(Meronymic meronymic, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateDirectedBinaryAssociation_DirectedBinaryAssociationConstraint1(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint1(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint2a(meronymic, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint2b(meronymic, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MeronymicConstraint1 constraint of '<em>Meronymic</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeronymic_MeronymicConstraint1(Meronymic meronymic, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (meronymic_MeronymicConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMeronymic());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMeronymic().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MeronymicConstraint1");
			
			try {
				meronymic_MeronymicConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(meronymic_MeronymicConstraint1InvOCL);
		
		if (!query.check(meronymic)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MeronymicConstraint1", getObjectLabel(meronymic, context) }),
						 new Object[] { meronymic }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the MeronymicConstraint2a constraint of '<em>Meronymic</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeronymic_MeronymicConstraint2a(Meronymic meronymic, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (meronymic_MeronymicConstraint2aInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMeronymic());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMeronymic().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MeronymicConstraint2a");
			
			try {
				meronymic_MeronymicConstraint2aInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(meronymic_MeronymicConstraint2aInvOCL);
		
		if (!query.check(meronymic)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MeronymicConstraint2a", getObjectLabel(meronymic, context) }),
						 new Object[] { meronymic }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the MeronymicConstraint2b constraint of '<em>Meronymic</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMeronymic_MeronymicConstraint2b(Meronymic meronymic, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (meronymic_MeronymicConstraint2bInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMeronymic());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMeronymic().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MeronymicConstraint2b");
			
			try {
				meronymic_MeronymicConstraint2bInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(meronymic_MeronymicConstraint2bInvOCL);
		
		if (!query.check(meronymic)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MeronymicConstraint2b", getObjectLabel(meronymic, context) }),
						 new Object[] { meronymic }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatesubQuantityOf(subQuantityOf subQuantityOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateDirectedBinaryAssociation_DirectedBinaryAssociationConstraint1(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint1(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint2a(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint2b(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatesubQuantityOf_subQuantityOfConstraint1a(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatesubQuantityOf_subQuantityOfConstraint1b(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatesubQuantityOf_subQuantityOfConstraint2(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatesubQuantityOf_subQuantityOfConstraint3(subQuantityOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatesubQuantityOf_subQuantityOfConstraint4(subQuantityOf, diagnostics, context);
		return result;
	}

	/**
	 * Validates the subQuantityOfConstraint1a constraint of '<em>sub Quantity Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatesubQuantityOf_subQuantityOfConstraint1a(subQuantityOf subQuantityOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (subQuantityOf_subQuantityOfConstraint1aInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getsubQuantityOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getsubQuantityOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("subQuantityOfConstraint1a");
			
			try {
				subQuantityOf_subQuantityOfConstraint1aInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(subQuantityOf_subQuantityOfConstraint1aInvOCL);
		
		if (!query.check(subQuantityOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "subQuantityOfConstraint1a", getObjectLabel(subQuantityOf, context) }),
						 new Object[] { subQuantityOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the subQuantityOfConstraint1b constraint of '<em>sub Quantity Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatesubQuantityOf_subQuantityOfConstraint1b(subQuantityOf subQuantityOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (subQuantityOf_subQuantityOfConstraint1bInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getsubQuantityOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getsubQuantityOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("subQuantityOfConstraint1b");
			
			try {
				subQuantityOf_subQuantityOfConstraint1bInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(subQuantityOf_subQuantityOfConstraint1bInvOCL);
		
		if (!query.check(subQuantityOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "subQuantityOfConstraint1b", getObjectLabel(subQuantityOf, context) }),
						 new Object[] { subQuantityOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the subQuantityOfConstraint2 constraint of '<em>sub Quantity Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatesubQuantityOf_subQuantityOfConstraint2(subQuantityOf subQuantityOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (subQuantityOf_subQuantityOfConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getsubQuantityOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getsubQuantityOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("subQuantityOfConstraint2");
			
			try {
				subQuantityOf_subQuantityOfConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(subQuantityOf_subQuantityOfConstraint2InvOCL);
		
		if (!query.check(subQuantityOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "subQuantityOfConstraint2", getObjectLabel(subQuantityOf, context) }),
						 new Object[] { subQuantityOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the subQuantityOfConstraint3 constraint of '<em>sub Quantity Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatesubQuantityOf_subQuantityOfConstraint3(subQuantityOf subQuantityOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (subQuantityOf_subQuantityOfConstraint3InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getsubQuantityOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getsubQuantityOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("subQuantityOfConstraint3");
			
			try {
				subQuantityOf_subQuantityOfConstraint3InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(subQuantityOf_subQuantityOfConstraint3InvOCL);
		
		if (!query.check(subQuantityOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "subQuantityOfConstraint3", getObjectLabel(subQuantityOf, context) }),
						 new Object[] { subQuantityOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the subQuantityOfConstraint4 constraint of '<em>sub Quantity Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatesubQuantityOf_subQuantityOfConstraint4(subQuantityOf subQuantityOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (subQuantityOf_subQuantityOfConstraint4InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getsubQuantityOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getsubQuantityOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("subQuantityOfConstraint4");
			
			try {
				subQuantityOf_subQuantityOfConstraint4InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(subQuantityOf_subQuantityOfConstraint4InvOCL);
		
		if (!query.check(subQuantityOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "subQuantityOfConstraint4", getObjectLabel(subQuantityOf, context) }),
						 new Object[] { subQuantityOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatesubCollectionOf(subCollectionOf subCollectionOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateDirectedBinaryAssociation_DirectedBinaryAssociationConstraint1(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint1(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint2a(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint2b(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatesubCollectionOf_subCollectionOfConstraint1a(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatesubCollectionOf_subCollectionOfConstraint1b(subCollectionOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatesubCollectionOf_subCollectionOfConstraint2(subCollectionOf, diagnostics, context);
		return result;
	}

	/**
	 * Validates the subCollectionOfConstraint1a constraint of '<em>sub Collection Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatesubCollectionOf_subCollectionOfConstraint1a(subCollectionOf subCollectionOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (subCollectionOf_subCollectionOfConstraint1aInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getsubCollectionOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getsubCollectionOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("subCollectionOfConstraint1a");
			
			try {
				subCollectionOf_subCollectionOfConstraint1aInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(subCollectionOf_subCollectionOfConstraint1aInvOCL);
		
		if (!query.check(subCollectionOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "subCollectionOfConstraint1a", getObjectLabel(subCollectionOf, context) }),
						 new Object[] { subCollectionOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the subCollectionOfConstraint1b constraint of '<em>sub Collection Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatesubCollectionOf_subCollectionOfConstraint1b(subCollectionOf subCollectionOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (subCollectionOf_subCollectionOfConstraint1bInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getsubCollectionOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getsubCollectionOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("subCollectionOfConstraint1b");
			
			try {
				subCollectionOf_subCollectionOfConstraint1bInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(subCollectionOf_subCollectionOfConstraint1bInvOCL);
		
		if (!query.check(subCollectionOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "subCollectionOfConstraint1b", getObjectLabel(subCollectionOf, context) }),
						 new Object[] { subCollectionOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the subCollectionOfConstraint2 constraint of '<em>sub Collection Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatesubCollectionOf_subCollectionOfConstraint2(subCollectionOf subCollectionOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (subCollectionOf_subCollectionOfConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getsubCollectionOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getsubCollectionOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("subCollectionOfConstraint2");
			
			try {
				subCollectionOf_subCollectionOfConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(subCollectionOf_subCollectionOfConstraint2InvOCL);
		
		if (!query.check(subCollectionOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "subCollectionOfConstraint2", getObjectLabel(subCollectionOf, context) }),
						 new Object[] { subCollectionOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatememberOf(memberOf memberOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateDirectedBinaryAssociation_DirectedBinaryAssociationConstraint1(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint1(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint2a(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint2b(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatememberOf_memberOfConstraint1a(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatememberOf_memberOfConstraint1b(memberOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatememberOf_memberOfConstraint2(memberOf, diagnostics, context);
		return result;
	}

	/**
	 * Validates the memberOfConstraint1a constraint of '<em>member Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatememberOf_memberOfConstraint1a(memberOf memberOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (memberOf_memberOfConstraint1aInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getmemberOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getmemberOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("memberOfConstraint1a");
			
			try {
				memberOf_memberOfConstraint1aInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(memberOf_memberOfConstraint1aInvOCL);
		
		if (!query.check(memberOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "memberOfConstraint1a", getObjectLabel(memberOf, context) }),
						 new Object[] { memberOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the memberOfConstraint1b constraint of '<em>member Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatememberOf_memberOfConstraint1b(memberOf memberOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (memberOf_memberOfConstraint1bInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getmemberOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getmemberOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("memberOfConstraint1b");
			
			try {
				memberOf_memberOfConstraint1bInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(memberOf_memberOfConstraint1bInvOCL);
		
		if (!query.check(memberOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "memberOfConstraint1b", getObjectLabel(memberOf, context) }),
						 new Object[] { memberOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the memberOfConstraint2 constraint of '<em>member Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatememberOf_memberOfConstraint2(memberOf memberOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (memberOf_memberOfConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getmemberOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getmemberOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("memberOfConstraint2");
			
			try {
				memberOf_memberOfConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(memberOf_memberOfConstraint2InvOCL);
		
		if (!query.check(memberOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "memberOfConstraint2", getObjectLabel(memberOf, context) }),
						 new Object[] { memberOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatecomponentOf(componentOf componentOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateDirectedBinaryAssociation_DirectedBinaryAssociationConstraint1(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint1(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint2a(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validateMeronymic_MeronymicConstraint2b(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatecomponentOf_componentOfConstraint1a(componentOf, diagnostics, context);
		if (result || diagnostics != null) result &= validatecomponentOf_componentOfConstraint1b(componentOf, diagnostics, context);
		return result;
	}

	/**
	 * Validates the componentOfConstraint1a constraint of '<em>component Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatecomponentOf_componentOfConstraint1a(componentOf componentOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (componentOf_componentOfConstraint1aInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getcomponentOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getcomponentOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("componentOfConstraint1a");
			
			try {
				componentOf_componentOfConstraint1aInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(componentOf_componentOfConstraint1aInvOCL);
		
		if (!query.check(componentOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "componentOfConstraint1a", getObjectLabel(componentOf, context) }),
						 new Object[] { componentOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the componentOfConstraint1b constraint of '<em>component Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatecomponentOf_componentOfConstraint1b(componentOf componentOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (componentOf_componentOfConstraint1bInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getcomponentOf());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getcomponentOf().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("componentOfConstraint1b");
			
			try {
				componentOf_componentOfConstraint1bInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(componentOf_componentOfConstraint1bInvOCL);
		
		if (!query.check(componentOf)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "componentOfConstraint1b", getObjectLabel(componentOf, context) }),
						 new Object[] { componentOf }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDependencyRelationship(DependencyRelationship dependencyRelationship, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateDirectedBinaryAssociation_DirectedBinaryAssociationConstraint1(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateDependencyRelationship_DependencyRelationshipConstraint1(dependencyRelationship, diagnostics, context);
		if (result || diagnostics != null) result &= validateDependencyRelationship_DependencyRelationshipConstraint2(dependencyRelationship, diagnostics, context);
		return result;
	}

	/**
	 * Validates the DependencyRelationshipConstraint1 constraint of '<em>Dependency Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDependencyRelationship_DependencyRelationshipConstraint1(DependencyRelationship dependencyRelationship, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (dependencyRelationship_DependencyRelationshipConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getDependencyRelationship());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getDependencyRelationship().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("DependencyRelationshipConstraint1");
			
			try {
				dependencyRelationship_DependencyRelationshipConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(dependencyRelationship_DependencyRelationshipConstraint1InvOCL);
		
		if (!query.check(dependencyRelationship)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "DependencyRelationshipConstraint1", getObjectLabel(dependencyRelationship, context) }),
						 new Object[] { dependencyRelationship }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the DependencyRelationshipConstraint2 constraint of '<em>Dependency Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDependencyRelationship_DependencyRelationshipConstraint2(DependencyRelationship dependencyRelationship, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (dependencyRelationship_DependencyRelationshipConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getDependencyRelationship());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getDependencyRelationship().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("DependencyRelationshipConstraint2");
			
			try {
				dependencyRelationship_DependencyRelationshipConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(dependencyRelationship_DependencyRelationshipConstraint2InvOCL);
		
		if (!query.check(dependencyRelationship)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "DependencyRelationshipConstraint2", getObjectLabel(dependencyRelationship, context) }),
						 new Object[] { dependencyRelationship }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCharacterization(Characterization characterization, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateDirectedBinaryAssociation_DirectedBinaryAssociationConstraint1(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateDependencyRelationship_DependencyRelationshipConstraint1(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateDependencyRelationship_DependencyRelationshipConstraint2(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateCharacterization_CharacterizationConstraint1(characterization, diagnostics, context);
		if (result || diagnostics != null) result &= validateCharacterization_CharacterizationConstraint2(characterization, diagnostics, context);
		return result;
	}

	/**
	 * Validates the CharacterizationConstraint1 constraint of '<em>Characterization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCharacterization_CharacterizationConstraint1(Characterization characterization, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (characterization_CharacterizationConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getCharacterization());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getCharacterization().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("CharacterizationConstraint1");
			
			try {
				characterization_CharacterizationConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(characterization_CharacterizationConstraint1InvOCL);
		
		if (!query.check(characterization)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "CharacterizationConstraint1", getObjectLabel(characterization, context) }),
						 new Object[] { characterization }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the CharacterizationConstraint2 constraint of '<em>Characterization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCharacterization_CharacterizationConstraint2(Characterization characterization, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (characterization_CharacterizationConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getCharacterization());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getCharacterization().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("CharacterizationConstraint2");
			
			try {
				characterization_CharacterizationConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(characterization_CharacterizationConstraint2InvOCL);
		
		if (!query.check(characterization)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "CharacterizationConstraint2", getObjectLabel(characterization, context) }),
						 new Object[] { characterization }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMediation(Mediation mediation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateDirectedBinaryAssociation_DirectedBinaryAssociationConstraint1(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateDependencyRelationship_DependencyRelationshipConstraint1(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateDependencyRelationship_DependencyRelationshipConstraint2(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateMediation_MediationConstraint1(mediation, diagnostics, context);
		if (result || diagnostics != null) result &= validateMediation_MediationConstraint2(mediation, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MediationConstraint1 constraint of '<em>Mediation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMediation_MediationConstraint1(Mediation mediation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (mediation_MediationConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMediation());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMediation().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MediationConstraint1");
			
			try {
				mediation_MediationConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(mediation_MediationConstraint1InvOCL);
		
		if (!query.check(mediation)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MediationConstraint1", getObjectLabel(mediation, context) }),
						 new Object[] { mediation }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the MediationConstraint2 constraint of '<em>Mediation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMediation_MediationConstraint2(Mediation mediation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (mediation_MediationConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMediation());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMediation().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MediationConstraint2");
			
			try {
				mediation_MediationConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(mediation_MediationConstraint2InvOCL);
		
		if (!query.check(mediation)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MediationConstraint2", getObjectLabel(mediation, context) }),
						 new Object[] { mediation }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDerivation(Derivation derivation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateDirectedBinaryAssociation_DirectedBinaryAssociationConstraint1(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateDependencyRelationship_DependencyRelationshipConstraint1(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateDependencyRelationship_DependencyRelationshipConstraint2(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateDerivation_DerivationConstraint1a(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateDerivation_DerivationConstraint1b(derivation, diagnostics, context);
		if (result || diagnostics != null) result &= validateDerivation_DerivationConstraint2(derivation, diagnostics, context);
		return result;
	}

	/**
	 * Validates the DerivationConstraint1a constraint of '<em>Derivation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDerivation_DerivationConstraint1a(Derivation derivation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (derivation_DerivationConstraint1aInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getDerivation());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getDerivation().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("DerivationConstraint1a");
			
			try {
				derivation_DerivationConstraint1aInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(derivation_DerivationConstraint1aInvOCL);
		
		if (!query.check(derivation)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "DerivationConstraint1a", getObjectLabel(derivation, context) }),
						 new Object[] { derivation }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the DerivationConstraint1b constraint of '<em>Derivation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDerivation_DerivationConstraint1b(Derivation derivation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (derivation_DerivationConstraint1bInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getDerivation());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getDerivation().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("DerivationConstraint1b");
			
			try {
				derivation_DerivationConstraint1bInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(derivation_DerivationConstraint1bInvOCL);
		
		if (!query.check(derivation)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "DerivationConstraint1b", getObjectLabel(derivation, context) }),
						 new Object[] { derivation }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the DerivationConstraint2 constraint of '<em>Derivation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDerivation_DerivationConstraint2(Derivation derivation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (derivation_DerivationConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getDerivation());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getDerivation().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("DerivationConstraint2");
			
			try {
				derivation_DerivationConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(derivation_DerivationConstraint2InvOCL);
		
		if (!query.check(derivation)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "DerivationConstraint2", getObjectLabel(derivation, context) }),
						 new Object[] { derivation }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStructuration(Structuration structuration, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateDirectedBinaryAssociation_DirectedBinaryAssociationConstraint1(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateDependencyRelationship_DependencyRelationshipConstraint1(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateDependencyRelationship_DependencyRelationshipConstraint2(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateStructuration_StructurationConstraint1(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateStructuration_StructurationConstraint2(structuration, diagnostics, context);
		if (result || diagnostics != null) result &= validateStructuration_StructurationConstraint3(structuration, diagnostics, context);
		return result;
	}

	/**
	 * Validates the StructurationConstraint1 constraint of '<em>Structuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStructuration_StructurationConstraint1(Structuration structuration, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (structuration_StructurationConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getStructuration());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getStructuration().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("StructurationConstraint1");
			
			try {
				structuration_StructurationConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(structuration_StructurationConstraint1InvOCL);
		
		if (!query.check(structuration)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "StructurationConstraint1", getObjectLabel(structuration, context) }),
						 new Object[] { structuration }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the StructurationConstraint2 constraint of '<em>Structuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStructuration_StructurationConstraint2(Structuration structuration, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (structuration_StructurationConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getStructuration());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getStructuration().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("StructurationConstraint2");
			
			try {
				structuration_StructurationConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(structuration_StructurationConstraint2InvOCL);
		
		if (!query.check(structuration)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "StructurationConstraint2", getObjectLabel(structuration, context) }),
						 new Object[] { structuration }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the StructurationConstraint3 constraint of '<em>Structuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStructuration_StructurationConstraint3(Structuration structuration, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (structuration_StructurationConstraint3InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getStructuration());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getStructuration().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("StructurationConstraint3");
			
			try {
				structuration_StructurationConstraint3InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(structuration_StructurationConstraint3InvOCL);
		
		if (!query.check(structuration)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "StructurationConstraint3", getObjectLabel(structuration, context) }),
						 new Object[] { structuration }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormalAssociation(FormalAssociation formalAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(formalAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(formalAssociation, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMaterialAssociation(MaterialAssociation materialAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_not_own_self(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateElement_has_owner(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_no_qualified_name(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_has_qualified_name(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamedElement_visibility_needs_ownership(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateNamespace_members_distinguishable(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_context_valid(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateRedefinableElement_redefinition_consistent(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_no_cycles_in_generalization(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_generalization_hierarchies(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_specialize_type(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassifier_maps_to_generalization_set(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_number(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_specialized_end_types(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_binary_associations(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_association_ends(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateMaterialAssociation_MaterialAssociationConstraint1(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateMaterialAssociation_MaterialAssociationConstraint2(materialAssociation, diagnostics, context);
		if (result || diagnostics != null) result &= validateMaterialAssociation_MaterialAssociationConstraint3(materialAssociation, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaterialAssociationConstraint1 constraint of '<em>Material Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMaterialAssociation_MaterialAssociationConstraint1(MaterialAssociation materialAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (materialAssociation_MaterialAssociationConstraint1InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMaterialAssociation());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMaterialAssociation().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MaterialAssociationConstraint1");
			
			try {
				materialAssociation_MaterialAssociationConstraint1InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(materialAssociation_MaterialAssociationConstraint1InvOCL);
		
		if (!query.check(materialAssociation)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MaterialAssociationConstraint1", getObjectLabel(materialAssociation, context) }),
						 new Object[] { materialAssociation }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the MaterialAssociationConstraint2 constraint of '<em>Material Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMaterialAssociation_MaterialAssociationConstraint2(MaterialAssociation materialAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (materialAssociation_MaterialAssociationConstraint2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMaterialAssociation());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMaterialAssociation().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MaterialAssociationConstraint2");
			
			try {
				materialAssociation_MaterialAssociationConstraint2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(materialAssociation_MaterialAssociationConstraint2InvOCL);
		
		if (!query.check(materialAssociation)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MaterialAssociationConstraint2", getObjectLabel(materialAssociation, context) }),
						 new Object[] { materialAssociation }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the MaterialAssociationConstraint3 constraint of '<em>Material Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMaterialAssociation_MaterialAssociationConstraint3(MaterialAssociation materialAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (materialAssociation_MaterialAssociationConstraint3InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RefOntoUMLPackage.eINSTANCE.getMaterialAssociation());
			
			EAnnotation ocl = RefOntoUMLPackage.eINSTANCE.getMaterialAssociation().getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MaterialAssociationConstraint3");
			
			try {
				materialAssociation_MaterialAssociationConstraint3InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(materialAssociation_MaterialAssociationConstraint3InvOCL);
		
		if (!query.check(materialAssociation)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MaterialAssociationConstraint3", getObjectLabel(materialAssociation, context) }),
						 new Object[] { materialAssociation }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVisibilityKind(VisibilityKind visibilityKind, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAggregationKind(AggregationKind aggregationKind, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInteger(int integer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBoolean(boolean boolean_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateString(String string, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUnlimitedNatural(int unlimitedNatural, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDecimal(BigDecimal decimal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

} //RefOntoUMLValidator
