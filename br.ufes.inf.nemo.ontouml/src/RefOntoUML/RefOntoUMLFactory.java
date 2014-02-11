/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see RefOntoUML.RefOntoUMLPackage
 * @generated
 */
public interface RefOntoUMLFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RefOntoUMLFactory eINSTANCE = RefOntoUML.impl.RefOntoUMLFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Comment</em>'.
	 * @generated
	 */
	Comment createComment();

	/**
	 * Returns a new object of class '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Package</em>'.
	 * @generated
	 */
	Package createPackage();

	/**
	 * Returns a new object of class '<em>Dependency</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dependency</em>'.
	 * @generated
	 */
	Dependency createDependency();

	/**
	 * Returns a new object of class '<em>Element Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Element Import</em>'.
	 * @generated
	 */
	ElementImport createElementImport();

	/**
	 * Returns a new object of class '<em>Package Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Package Import</em>'.
	 * @generated
	 */
	PackageImport createPackageImport();

	/**
	 * Returns a new object of class '<em>Constraintx</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constraintx</em>'.
	 * @generated
	 */
	Constraintx createConstraintx();

	/**
	 * Returns a new object of class '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Association</em>'.
	 * @generated
	 */
	Association createAssociation();

	/**
	 * Returns a new object of class '<em>Generalization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generalization</em>'.
	 * @generated
	 */
	Generalization createGeneralization();

	/**
	 * Returns a new object of class '<em>Generalization Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generalization Set</em>'.
	 * @generated
	 */
	GeneralizationSet createGeneralizationSet();

	/**
	 * Returns a new object of class '<em>Opaque Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Opaque Expression</em>'.
	 * @generated
	 */
	OpaqueExpression createOpaqueExpression();

	/**
	 * Returns a new object of class '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property</em>'.
	 * @generated
	 */
	Property createProperty();

	/**
	 * Returns a new object of class '<em>Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class</em>'.
	 * @generated
	 */
	Class createClass();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	Model createModel();

	/**
	 * Returns a new object of class '<em>Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Type</em>'.
	 * @generated
	 */
	DataType createDataType();

	/**
	 * Returns a new object of class '<em>String Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Expression</em>'.
	 * @generated
	 */
	StringExpression createStringExpression();

	/**
	 * Returns a new object of class '<em>Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expression</em>'.
	 * @generated
	 */
	Expression createExpression();

	/**
	 * Returns a new object of class '<em>Package Merge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Package Merge</em>'.
	 * @generated
	 */
	PackageMerge createPackageMerge();

	/**
	 * Returns a new object of class '<em>Instance Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instance Specification</em>'.
	 * @generated
	 */
	InstanceSpecification createInstanceSpecification();

	/**
	 * Returns a new object of class '<em>Slot</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Slot</em>'.
	 * @generated
	 */
	Slot createSlot();

	/**
	 * Returns a new object of class '<em>Enumeration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Enumeration</em>'.
	 * @generated
	 */
	Enumeration createEnumeration();

	/**
	 * Returns a new object of class '<em>Enumeration Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Enumeration Literal</em>'.
	 * @generated
	 */
	EnumerationLiteral createEnumerationLiteral();

	/**
	 * Returns a new object of class '<em>Ordinal Enumeration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ordinal Enumeration</em>'.
	 * @generated
	 */
	OrdinalEnumeration createOrdinalEnumeration();

	/**
	 * Returns a new object of class '<em>Ordinal Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ordinal Literal</em>'.
	 * @generated
	 */
	OrdinalLiteral createOrdinalLiteral();

	/**
	 * Returns a new object of class '<em>Primitive Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Primitive Type</em>'.
	 * @generated
	 */
	PrimitiveType createPrimitiveType();

	/**
	 * Returns a new object of class '<em>Nominal Structure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Nominal Structure</em>'.
	 * @generated
	 */
	NominalStructure createNominalStructure();

	/**
	 * Returns a new object of class '<em>String Nominal Structure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Nominal Structure</em>'.
	 * @generated
	 */
	StringNominalStructure createStringNominalStructure();

	/**
	 * Returns a new object of class '<em>Integer Ordinal Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Ordinal Dimension</em>'.
	 * @generated
	 */
	IntegerOrdinalDimension createIntegerOrdinalDimension();

	/**
	 * Returns a new object of class '<em>Decimal Ordinal Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decimal Ordinal Dimension</em>'.
	 * @generated
	 */
	DecimalOrdinalDimension createDecimalOrdinalDimension();

	/**
	 * Returns a new object of class '<em>Integer Interval Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Interval Dimension</em>'.
	 * @generated
	 */
	IntegerIntervalDimension createIntegerIntervalDimension();

	/**
	 * Returns a new object of class '<em>Decimal Interval Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decimal Interval Dimension</em>'.
	 * @generated
	 */
	DecimalIntervalDimension createDecimalIntervalDimension();

	/**
	 * Returns a new object of class '<em>Integer Rational Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Rational Dimension</em>'.
	 * @generated
	 */
	IntegerRationalDimension createIntegerRationalDimension();

	/**
	 * Returns a new object of class '<em>Decimal Rational Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decimal Rational Dimension</em>'.
	 * @generated
	 */
	DecimalRationalDimension createDecimalRationalDimension();

	/**
	 * Returns a new object of class '<em>Measurement Domain</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measurement Domain</em>'.
	 * @generated
	 */
	MeasurementDomain createMeasurementDomain();

	/**
	 * Returns a new object of class '<em>Decimal Measurement Region</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decimal Measurement Region</em>'.
	 * @generated
	 */
	DecimalMeasurementRegion createDecimalMeasurementRegion();

	/**
	 * Returns a new object of class '<em>Integer Measurement Region</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Measurement Region</em>'.
	 * @generated
	 */
	IntegerMeasurementRegion createIntegerMeasurementRegion();

	/**
	 * Returns a new object of class '<em>Composed Measurement Region</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composed Measurement Region</em>'.
	 * @generated
	 */
	ComposedMeasurementRegion createComposedMeasurementRegion();

	/**
	 * Returns a new object of class '<em>String Nominal Region</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Nominal Region</em>'.
	 * @generated
	 */
	StringNominalRegion createStringNominalRegion();

	/**
	 * Returns a new object of class '<em>Literal Integer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Literal Integer</em>'.
	 * @generated
	 */
	LiteralInteger createLiteralInteger();

	/**
	 * Returns a new object of class '<em>Literal Decimal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Literal Decimal</em>'.
	 * @generated
	 */
	LiteralDecimal createLiteralDecimal();

	/**
	 * Returns a new object of class '<em>Literal String</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Literal String</em>'.
	 * @generated
	 */
	LiteralString createLiteralString();

	/**
	 * Returns a new object of class '<em>Literal Boolean</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Literal Boolean</em>'.
	 * @generated
	 */
	LiteralBoolean createLiteralBoolean();

	/**
	 * Returns a new object of class '<em>Literal Null</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Literal Null</em>'.
	 * @generated
	 */
	LiteralNull createLiteralNull();

	/**
	 * Returns a new object of class '<em>Instance Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instance Value</em>'.
	 * @generated
	 */
	InstanceValue createInstanceValue();

	/**
	 * Returns a new object of class '<em>Literal Unlimited Natural</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Literal Unlimited Natural</em>'.
	 * @generated
	 */
	LiteralUnlimitedNatural createLiteralUnlimitedNatural();

	/**
	 * Returns a new object of class '<em>Sub Kind</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sub Kind</em>'.
	 * @generated
	 */
	SubKind createSubKind();

	/**
	 * Returns a new object of class '<em>Kind</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Kind</em>'.
	 * @generated
	 */
	Kind createKind();

	/**
	 * Returns a new object of class '<em>Quantity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quantity</em>'.
	 * @generated
	 */
	Quantity createQuantity();

	/**
	 * Returns a new object of class '<em>Collective</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collective</em>'.
	 * @generated
	 */
	Collective createCollective();

	/**
	 * Returns a new object of class '<em>Phase</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Phase</em>'.
	 * @generated
	 */
	Phase createPhase();

	/**
	 * Returns a new object of class '<em>Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role</em>'.
	 * @generated
	 */
	Role createRole();

	/**
	 * Returns a new object of class '<em>Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Category</em>'.
	 * @generated
	 */
	Category createCategory();

	/**
	 * Returns a new object of class '<em>Role Mixin</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Mixin</em>'.
	 * @generated
	 */
	RoleMixin createRoleMixin();

	/**
	 * Returns a new object of class '<em>Mixin</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mixin</em>'.
	 * @generated
	 */
	Mixin createMixin();

	/**
	 * Returns a new object of class '<em>Mode</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mode</em>'.
	 * @generated
	 */
	Mode createMode();

	/**
	 * Returns a new object of class '<em>Nominal Quality</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Nominal Quality</em>'.
	 * @generated
	 */
	NominalQuality createNominalQuality();

	/**
	 * Returns a new object of class '<em>Non Perceivable Quality</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Non Perceivable Quality</em>'.
	 * @generated
	 */
	NonPerceivableQuality createNonPerceivableQuality();

	/**
	 * Returns a new object of class '<em>Perceivable Quality</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Perceivable Quality</em>'.
	 * @generated
	 */
	PerceivableQuality createPerceivableQuality();

	/**
	 * Returns a new object of class '<em>Relator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Relator</em>'.
	 * @generated
	 */
	Relator createRelator();

	/**
	 * Returns a new object of class '<em>sub Quantity Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>sub Quantity Of</em>'.
	 * @generated
	 */
	subQuantityOf createsubQuantityOf();

	/**
	 * Returns a new object of class '<em>sub Collection Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>sub Collection Of</em>'.
	 * @generated
	 */
	subCollectionOf createsubCollectionOf();

	/**
	 * Returns a new object of class '<em>member Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>member Of</em>'.
	 * @generated
	 */
	memberOf creatememberOf();

	/**
	 * Returns a new object of class '<em>component Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>component Of</em>'.
	 * @generated
	 */
	componentOf createcomponentOf();

	/**
	 * Returns a new object of class '<em>Characterization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Characterization</em>'.
	 * @generated
	 */
	Characterization createCharacterization();

	/**
	 * Returns a new object of class '<em>Mediation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mediation</em>'.
	 * @generated
	 */
	Mediation createMediation();

	/**
	 * Returns a new object of class '<em>Derivation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Derivation</em>'.
	 * @generated
	 */
	Derivation createDerivation();

	/**
	 * Returns a new object of class '<em>Structuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Structuration</em>'.
	 * @generated
	 */
	Structuration createStructuration();

	/**
	 * Returns a new object of class '<em>Formal Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Formal Association</em>'.
	 * @generated
	 */
	FormalAssociation createFormalAssociation();

	/**
	 * Returns a new object of class '<em>Material Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Material Association</em>'.
	 * @generated
	 */
	MaterialAssociation createMaterialAssociation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RefOntoUMLPackage getRefOntoUMLPackage();

} //RefOntoUMLFactory
