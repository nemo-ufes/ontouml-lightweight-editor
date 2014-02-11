/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
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
import RefOntoUML.Derivation;
import RefOntoUML.ElementImport;
import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.Expression;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.InstanceSpecification;
import RefOntoUML.InstanceValue;
import RefOntoUML.IntegerIntervalDimension;
import RefOntoUML.IntegerMeasurementRegion;
import RefOntoUML.IntegerOrdinalDimension;
import RefOntoUML.IntegerRationalDimension;
import RefOntoUML.Kind;
import RefOntoUML.LiteralBoolean;
import RefOntoUML.LiteralDecimal;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralNull;
import RefOntoUML.LiteralString;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.MeasurementDomain;
import RefOntoUML.Mediation;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.Model;
import RefOntoUML.NominalQuality;
import RefOntoUML.NominalStructure;
import RefOntoUML.NonPerceivableQuality;
import RefOntoUML.OpaqueExpression;
import RefOntoUML.OrdinalEnumeration;
import RefOntoUML.OrdinalLiteral;
import RefOntoUML.PackageImport;
import RefOntoUML.PackageMerge;
import RefOntoUML.PerceivableQuality;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.Slot;
import RefOntoUML.StringExpression;
import RefOntoUML.StringNominalRegion;
import RefOntoUML.StringNominalStructure;
import RefOntoUML.Structuration;
import RefOntoUML.SubKind;
import RefOntoUML.VisibilityKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;

import java.math.BigDecimal;

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
public class RefOntoUMLFactoryImpl extends EFactoryImpl implements RefOntoUMLFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RefOntoUMLFactory init() {
		try {
			RefOntoUMLFactory theRefOntoUMLFactory = (RefOntoUMLFactory)EPackage.Registry.INSTANCE.getEFactory("http://nemo.inf.ufes.br/ontouml/refontouml"); 
			if (theRefOntoUMLFactory != null) {
				return theRefOntoUMLFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RefOntoUMLFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUMLFactoryImpl() {
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
			case RefOntoUMLPackage.COMMENT: return createComment();
			case RefOntoUMLPackage.PACKAGE: return createPackage();
			case RefOntoUMLPackage.DEPENDENCY: return createDependency();
			case RefOntoUMLPackage.ELEMENT_IMPORT: return createElementImport();
			case RefOntoUMLPackage.PACKAGE_IMPORT: return createPackageImport();
			case RefOntoUMLPackage.CONSTRAINTX: return createConstraintx();
			case RefOntoUMLPackage.ASSOCIATION: return createAssociation();
			case RefOntoUMLPackage.GENERALIZATION: return createGeneralization();
			case RefOntoUMLPackage.GENERALIZATION_SET: return createGeneralizationSet();
			case RefOntoUMLPackage.OPAQUE_EXPRESSION: return createOpaqueExpression();
			case RefOntoUMLPackage.PROPERTY: return createProperty();
			case RefOntoUMLPackage.CLASS: return createClass();
			case RefOntoUMLPackage.MODEL: return createModel();
			case RefOntoUMLPackage.DATA_TYPE: return createDataType();
			case RefOntoUMLPackage.STRING_EXPRESSION: return createStringExpression();
			case RefOntoUMLPackage.EXPRESSION: return createExpression();
			case RefOntoUMLPackage.PACKAGE_MERGE: return createPackageMerge();
			case RefOntoUMLPackage.INSTANCE_SPECIFICATION: return createInstanceSpecification();
			case RefOntoUMLPackage.SLOT: return createSlot();
			case RefOntoUMLPackage.ENUMERATION: return createEnumeration();
			case RefOntoUMLPackage.ENUMERATION_LITERAL: return createEnumerationLiteral();
			case RefOntoUMLPackage.ORDINAL_ENUMERATION: return createOrdinalEnumeration();
			case RefOntoUMLPackage.ORDINAL_LITERAL: return createOrdinalLiteral();
			case RefOntoUMLPackage.PRIMITIVE_TYPE: return createPrimitiveType();
			case RefOntoUMLPackage.NOMINAL_STRUCTURE: return createNominalStructure();
			case RefOntoUMLPackage.STRING_NOMINAL_STRUCTURE: return createStringNominalStructure();
			case RefOntoUMLPackage.INTEGER_ORDINAL_DIMENSION: return createIntegerOrdinalDimension();
			case RefOntoUMLPackage.DECIMAL_ORDINAL_DIMENSION: return createDecimalOrdinalDimension();
			case RefOntoUMLPackage.INTEGER_INTERVAL_DIMENSION: return createIntegerIntervalDimension();
			case RefOntoUMLPackage.DECIMAL_INTERVAL_DIMENSION: return createDecimalIntervalDimension();
			case RefOntoUMLPackage.INTEGER_RATIONAL_DIMENSION: return createIntegerRationalDimension();
			case RefOntoUMLPackage.DECIMAL_RATIONAL_DIMENSION: return createDecimalRationalDimension();
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN: return createMeasurementDomain();
			case RefOntoUMLPackage.DECIMAL_MEASUREMENT_REGION: return createDecimalMeasurementRegion();
			case RefOntoUMLPackage.INTEGER_MEASUREMENT_REGION: return createIntegerMeasurementRegion();
			case RefOntoUMLPackage.COMPOSED_MEASUREMENT_REGION: return createComposedMeasurementRegion();
			case RefOntoUMLPackage.STRING_NOMINAL_REGION: return createStringNominalRegion();
			case RefOntoUMLPackage.LITERAL_INTEGER: return createLiteralInteger();
			case RefOntoUMLPackage.LITERAL_DECIMAL: return createLiteralDecimal();
			case RefOntoUMLPackage.LITERAL_STRING: return createLiteralString();
			case RefOntoUMLPackage.LITERAL_BOOLEAN: return createLiteralBoolean();
			case RefOntoUMLPackage.LITERAL_NULL: return createLiteralNull();
			case RefOntoUMLPackage.INSTANCE_VALUE: return createInstanceValue();
			case RefOntoUMLPackage.LITERAL_UNLIMITED_NATURAL: return createLiteralUnlimitedNatural();
			case RefOntoUMLPackage.SUB_KIND: return createSubKind();
			case RefOntoUMLPackage.KIND: return createKind();
			case RefOntoUMLPackage.QUANTITY: return createQuantity();
			case RefOntoUMLPackage.COLLECTIVE: return createCollective();
			case RefOntoUMLPackage.PHASE: return createPhase();
			case RefOntoUMLPackage.ROLE: return createRole();
			case RefOntoUMLPackage.CATEGORY: return createCategory();
			case RefOntoUMLPackage.ROLE_MIXIN: return createRoleMixin();
			case RefOntoUMLPackage.MIXIN: return createMixin();
			case RefOntoUMLPackage.MODE: return createMode();
			case RefOntoUMLPackage.NOMINAL_QUALITY: return createNominalQuality();
			case RefOntoUMLPackage.NON_PERCEIVABLE_QUALITY: return createNonPerceivableQuality();
			case RefOntoUMLPackage.PERCEIVABLE_QUALITY: return createPerceivableQuality();
			case RefOntoUMLPackage.RELATOR: return createRelator();
			case RefOntoUMLPackage.SUB_QUANTITY_OF: return createsubQuantityOf();
			case RefOntoUMLPackage.SUB_COLLECTION_OF: return createsubCollectionOf();
			case RefOntoUMLPackage.MEMBER_OF: return creatememberOf();
			case RefOntoUMLPackage.COMPONENT_OF: return createcomponentOf();
			case RefOntoUMLPackage.CHARACTERIZATION: return createCharacterization();
			case RefOntoUMLPackage.MEDIATION: return createMediation();
			case RefOntoUMLPackage.DERIVATION: return createDerivation();
			case RefOntoUMLPackage.STRUCTURATION: return createStructuration();
			case RefOntoUMLPackage.FORMAL_ASSOCIATION: return createFormalAssociation();
			case RefOntoUMLPackage.MATERIAL_ASSOCIATION: return createMaterialAssociation();
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
			case RefOntoUMLPackage.VISIBILITY_KIND:
				return createVisibilityKindFromString(eDataType, initialValue);
			case RefOntoUMLPackage.AGGREGATION_KIND:
				return createAggregationKindFromString(eDataType, initialValue);
			case RefOntoUMLPackage.INTEGER:
				return createIntegerFromString(eDataType, initialValue);
			case RefOntoUMLPackage.BOOLEAN:
				return createBooleanFromString(eDataType, initialValue);
			case RefOntoUMLPackage.STRING:
				return createStringFromString(eDataType, initialValue);
			case RefOntoUMLPackage.UNLIMITED_NATURAL:
				return createUnlimitedNaturalFromString(eDataType, initialValue);
			case RefOntoUMLPackage.DECIMAL:
				return createDecimalFromString(eDataType, initialValue);
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
			case RefOntoUMLPackage.VISIBILITY_KIND:
				return convertVisibilityKindToString(eDataType, instanceValue);
			case RefOntoUMLPackage.AGGREGATION_KIND:
				return convertAggregationKindToString(eDataType, instanceValue);
			case RefOntoUMLPackage.INTEGER:
				return convertIntegerToString(eDataType, instanceValue);
			case RefOntoUMLPackage.BOOLEAN:
				return convertBooleanToString(eDataType, instanceValue);
			case RefOntoUMLPackage.STRING:
				return convertStringToString(eDataType, instanceValue);
			case RefOntoUMLPackage.UNLIMITED_NATURAL:
				return convertUnlimitedNaturalToString(eDataType, instanceValue);
			case RefOntoUMLPackage.DECIMAL:
				return convertDecimalToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Comment createComment() {
		CommentImpl comment = new CommentImpl();
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUML.Package createPackage() {
		PackageImpl package_ = new PackageImpl();
		return package_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dependency createDependency() {
		DependencyImpl dependency = new DependencyImpl();
		return dependency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementImport createElementImport() {
		ElementImportImpl elementImport = new ElementImportImpl();
		return elementImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageImport createPackageImport() {
		PackageImportImpl packageImport = new PackageImportImpl();
		return packageImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraintx createConstraintx() {
		ConstraintxImpl constraintx = new ConstraintxImpl();
		return constraintx;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Association createAssociation() {
		AssociationImpl association = new AssociationImpl();
		return association;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Generalization createGeneralization() {
		GeneralizationImpl generalization = new GeneralizationImpl();
		return generalization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralizationSet createGeneralizationSet() {
		GeneralizationSetImpl generalizationSet = new GeneralizationSetImpl();
		return generalizationSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OpaqueExpression createOpaqueExpression() {
		OpaqueExpressionImpl opaqueExpression = new OpaqueExpressionImpl();
		return opaqueExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property createProperty() {
		PropertyImpl property = new PropertyImpl();
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUML.Class createClass() {
		ClassImpl class_ = new ClassImpl();
		return class_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model createModel() {
		ModelImpl model = new ModelImpl();
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType createDataType() {
		DataTypeImpl dataType = new DataTypeImpl();
		return dataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringExpression createStringExpression() {
		StringExpressionImpl stringExpression = new StringExpressionImpl();
		return stringExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression createExpression() {
		ExpressionImpl expression = new ExpressionImpl();
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageMerge createPackageMerge() {
		PackageMergeImpl packageMerge = new PackageMergeImpl();
		return packageMerge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanceSpecification createInstanceSpecification() {
		InstanceSpecificationImpl instanceSpecification = new InstanceSpecificationImpl();
		return instanceSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Slot createSlot() {
		SlotImpl slot = new SlotImpl();
		return slot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration createEnumeration() {
		EnumerationImpl enumeration = new EnumerationImpl();
		return enumeration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationLiteral createEnumerationLiteral() {
		EnumerationLiteralImpl enumerationLiteral = new EnumerationLiteralImpl();
		return enumerationLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrdinalEnumeration createOrdinalEnumeration() {
		OrdinalEnumerationImpl ordinalEnumeration = new OrdinalEnumerationImpl();
		return ordinalEnumeration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrdinalLiteral createOrdinalLiteral() {
		OrdinalLiteralImpl ordinalLiteral = new OrdinalLiteralImpl();
		return ordinalLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType createPrimitiveType() {
		PrimitiveTypeImpl primitiveType = new PrimitiveTypeImpl();
		return primitiveType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NominalStructure createNominalStructure() {
		NominalStructureImpl nominalStructure = new NominalStructureImpl();
		return nominalStructure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringNominalStructure createStringNominalStructure() {
		StringNominalStructureImpl stringNominalStructure = new StringNominalStructureImpl();
		return stringNominalStructure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerOrdinalDimension createIntegerOrdinalDimension() {
		IntegerOrdinalDimensionImpl integerOrdinalDimension = new IntegerOrdinalDimensionImpl();
		return integerOrdinalDimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecimalOrdinalDimension createDecimalOrdinalDimension() {
		DecimalOrdinalDimensionImpl decimalOrdinalDimension = new DecimalOrdinalDimensionImpl();
		return decimalOrdinalDimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerIntervalDimension createIntegerIntervalDimension() {
		IntegerIntervalDimensionImpl integerIntervalDimension = new IntegerIntervalDimensionImpl();
		return integerIntervalDimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecimalIntervalDimension createDecimalIntervalDimension() {
		DecimalIntervalDimensionImpl decimalIntervalDimension = new DecimalIntervalDimensionImpl();
		return decimalIntervalDimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerRationalDimension createIntegerRationalDimension() {
		IntegerRationalDimensionImpl integerRationalDimension = new IntegerRationalDimensionImpl();
		return integerRationalDimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecimalRationalDimension createDecimalRationalDimension() {
		DecimalRationalDimensionImpl decimalRationalDimension = new DecimalRationalDimensionImpl();
		return decimalRationalDimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementDomain createMeasurementDomain() {
		MeasurementDomainImpl measurementDomain = new MeasurementDomainImpl();
		return measurementDomain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecimalMeasurementRegion createDecimalMeasurementRegion() {
		DecimalMeasurementRegionImpl decimalMeasurementRegion = new DecimalMeasurementRegionImpl();
		return decimalMeasurementRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerMeasurementRegion createIntegerMeasurementRegion() {
		IntegerMeasurementRegionImpl integerMeasurementRegion = new IntegerMeasurementRegionImpl();
		return integerMeasurementRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposedMeasurementRegion createComposedMeasurementRegion() {
		ComposedMeasurementRegionImpl composedMeasurementRegion = new ComposedMeasurementRegionImpl();
		return composedMeasurementRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringNominalRegion createStringNominalRegion() {
		StringNominalRegionImpl stringNominalRegion = new StringNominalRegionImpl();
		return stringNominalRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LiteralInteger createLiteralInteger() {
		LiteralIntegerImpl literalInteger = new LiteralIntegerImpl();
		return literalInteger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LiteralDecimal createLiteralDecimal() {
		LiteralDecimalImpl literalDecimal = new LiteralDecimalImpl();
		return literalDecimal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LiteralString createLiteralString() {
		LiteralStringImpl literalString = new LiteralStringImpl();
		return literalString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LiteralBoolean createLiteralBoolean() {
		LiteralBooleanImpl literalBoolean = new LiteralBooleanImpl();
		return literalBoolean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LiteralNull createLiteralNull() {
		LiteralNullImpl literalNull = new LiteralNullImpl();
		return literalNull;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanceValue createInstanceValue() {
		InstanceValueImpl instanceValue = new InstanceValueImpl();
		return instanceValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LiteralUnlimitedNatural createLiteralUnlimitedNatural() {
		LiteralUnlimitedNaturalImpl literalUnlimitedNatural = new LiteralUnlimitedNaturalImpl();
		return literalUnlimitedNatural;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubKind createSubKind() {
		SubKindImpl subKind = new SubKindImpl();
		return subKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Kind createKind() {
		KindImpl kind = new KindImpl();
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Quantity createQuantity() {
		QuantityImpl quantity = new QuantityImpl();
		return quantity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collective createCollective() {
		CollectiveImpl collective = new CollectiveImpl();
		return collective;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Phase createPhase() {
		PhaseImpl phase = new PhaseImpl();
		return phase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Role createRole() {
		RoleImpl role = new RoleImpl();
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Category createCategory() {
		CategoryImpl category = new CategoryImpl();
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleMixin createRoleMixin() {
		RoleMixinImpl roleMixin = new RoleMixinImpl();
		return roleMixin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mixin createMixin() {
		MixinImpl mixin = new MixinImpl();
		return mixin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mode createMode() {
		ModeImpl mode = new ModeImpl();
		return mode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NominalQuality createNominalQuality() {
		NominalQualityImpl nominalQuality = new NominalQualityImpl();
		return nominalQuality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NonPerceivableQuality createNonPerceivableQuality() {
		NonPerceivableQualityImpl nonPerceivableQuality = new NonPerceivableQualityImpl();
		return nonPerceivableQuality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerceivableQuality createPerceivableQuality() {
		PerceivableQualityImpl perceivableQuality = new PerceivableQualityImpl();
		return perceivableQuality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Relator createRelator() {
		RelatorImpl relator = new RelatorImpl();
		return relator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public subQuantityOf createsubQuantityOf() {
		subQuantityOfImpl subQuantityOf = new subQuantityOfImpl();
		return subQuantityOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public subCollectionOf createsubCollectionOf() {
		subCollectionOfImpl subCollectionOf = new subCollectionOfImpl();
		return subCollectionOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public memberOf creatememberOf() {
		memberOfImpl memberOf = new memberOfImpl();
		return memberOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public componentOf createcomponentOf() {
		componentOfImpl componentOf = new componentOfImpl();
		return componentOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Characterization createCharacterization() {
		CharacterizationImpl characterization = new CharacterizationImpl();
		return characterization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mediation createMediation() {
		MediationImpl mediation = new MediationImpl();
		return mediation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Derivation createDerivation() {
		DerivationImpl derivation = new DerivationImpl();
		return derivation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Structuration createStructuration() {
		StructurationImpl structuration = new StructurationImpl();
		return structuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalAssociation createFormalAssociation() {
		FormalAssociationImpl formalAssociation = new FormalAssociationImpl();
		return formalAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MaterialAssociation createMaterialAssociation() {
		MaterialAssociationImpl materialAssociation = new MaterialAssociationImpl();
		return materialAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisibilityKind createVisibilityKindFromString(EDataType eDataType, String initialValue) {
		VisibilityKind result = VisibilityKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVisibilityKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AggregationKind createAggregationKindFromString(EDataType eDataType, String initialValue) {
		AggregationKind result = AggregationKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAggregationKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer createIntegerFromString(EDataType eDataType, String initialValue) {
		return (Integer)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIntegerToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean createBooleanFromString(EDataType eDataType, String initialValue) {
		return (Boolean)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBooleanToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createStringFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStringToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer createUnlimitedNaturalFromString(EDataType eDataType, String initialValue) {
		return (Integer)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUnlimitedNaturalToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal createDecimalFromString(EDataType eDataType, String initialValue) {
		return (BigDecimal)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDecimalToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUMLPackage getRefOntoUMLPackage() {
		return (RefOntoUMLPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RefOntoUMLPackage getPackage() {
		return RefOntoUMLPackage.eINSTANCE;
	}

} //RefOntoUMLFactoryImpl
