/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.util;

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
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see RefOntoUML.RefOntoUMLPackage
 * @generated
 */
public class RefOntoUMLAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static RefOntoUMLPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUMLAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = RefOntoUMLPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RefOntoUMLSwitch<Adapter> modelSwitch =
		new RefOntoUMLSwitch<Adapter>() {
			@Override
			public Adapter caseComment(Comment object) {
				return createCommentAdapter();
			}
			@Override
			public Adapter caseElement(Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter casePackage(RefOntoUML.Package object) {
				return createPackageAdapter();
			}
			@Override
			public Adapter casePackageableElement(PackageableElement object) {
				return createPackageableElementAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseDependency(Dependency object) {
				return createDependencyAdapter();
			}
			@Override
			public Adapter caseDirectedRelationship(DirectedRelationship object) {
				return createDirectedRelationshipAdapter();
			}
			@Override
			public Adapter caseRelationship(Relationship object) {
				return createRelationshipAdapter();
			}
			@Override
			public Adapter caseNamespace(Namespace object) {
				return createNamespaceAdapter();
			}
			@Override
			public Adapter caseElementImport(ElementImport object) {
				return createElementImportAdapter();
			}
			@Override
			public Adapter casePackageImport(PackageImport object) {
				return createPackageImportAdapter();
			}
			@Override
			public Adapter caseConstraintx(Constraintx object) {
				return createConstraintxAdapter();
			}
			@Override
			public Adapter caseValueSpecification(ValueSpecification object) {
				return createValueSpecificationAdapter();
			}
			@Override
			public Adapter caseTypedElement(TypedElement object) {
				return createTypedElementAdapter();
			}
			@Override
			public Adapter caseType(Type object) {
				return createTypeAdapter();
			}
			@Override
			public Adapter caseAssociation(Association object) {
				return createAssociationAdapter();
			}
			@Override
			public Adapter caseClassifier(Classifier object) {
				return createClassifierAdapter();
			}
			@Override
			public Adapter caseRedefinableElement(RedefinableElement object) {
				return createRedefinableElementAdapter();
			}
			@Override
			public Adapter caseGeneralization(Generalization object) {
				return createGeneralizationAdapter();
			}
			@Override
			public Adapter caseGeneralizationSet(GeneralizationSet object) {
				return createGeneralizationSetAdapter();
			}
			@Override
			public Adapter caseFeature(Feature object) {
				return createFeatureAdapter();
			}
			@Override
			public Adapter caseOpaqueExpression(OpaqueExpression object) {
				return createOpaqueExpressionAdapter();
			}
			@Override
			public Adapter caseMultiplicityElement(MultiplicityElement object) {
				return createMultiplicityElementAdapter();
			}
			@Override
			public Adapter caseProperty(Property object) {
				return createPropertyAdapter();
			}
			@Override
			public Adapter caseClass(RefOntoUML.Class object) {
				return createClassAdapter();
			}
			@Override
			public Adapter caseModel(Model object) {
				return createModelAdapter();
			}
			@Override
			public Adapter caseDataType(DataType object) {
				return createDataTypeAdapter();
			}
			@Override
			public Adapter caseStructuralFeature(StructuralFeature object) {
				return createStructuralFeatureAdapter();
			}
			@Override
			public Adapter caseStringExpression(StringExpression object) {
				return createStringExpressionAdapter();
			}
			@Override
			public Adapter caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			@Override
			public Adapter casePackageMerge(PackageMerge object) {
				return createPackageMergeAdapter();
			}
			@Override
			public Adapter caseInstanceSpecification(InstanceSpecification object) {
				return createInstanceSpecificationAdapter();
			}
			@Override
			public Adapter caseSlot(Slot object) {
				return createSlotAdapter();
			}
			@Override
			public Adapter caseLiteralSpecification(LiteralSpecification object) {
				return createLiteralSpecificationAdapter();
			}
			@Override
			public Adapter caseEnumeration(Enumeration object) {
				return createEnumerationAdapter();
			}
			@Override
			public Adapter caseEnumerationLiteral(EnumerationLiteral object) {
				return createEnumerationLiteralAdapter();
			}
			@Override
			public Adapter caseOrdinalEnumeration(OrdinalEnumeration object) {
				return createOrdinalEnumerationAdapter();
			}
			@Override
			public Adapter caseOrdinalLiteral(OrdinalLiteral object) {
				return createOrdinalLiteralAdapter();
			}
			@Override
			public Adapter casePrimitiveType(PrimitiveType object) {
				return createPrimitiveTypeAdapter();
			}
			@Override
			public Adapter caseReferenceStructure(ReferenceStructure object) {
				return createReferenceStructureAdapter();
			}
			@Override
			public Adapter caseNominalStructure(NominalStructure object) {
				return createNominalStructureAdapter();
			}
			@Override
			public Adapter caseStringNominalStructure(StringNominalStructure object) {
				return createStringNominalStructureAdapter();
			}
			@Override
			public Adapter caseMeasurementStructure(MeasurementStructure object) {
				return createMeasurementStructureAdapter();
			}
			@Override
			public Adapter caseMeasurementDimension(MeasurementDimension object) {
				return createMeasurementDimensionAdapter();
			}
			@Override
			public Adapter caseOrdinalDimension(OrdinalDimension object) {
				return createOrdinalDimensionAdapter();
			}
			@Override
			public Adapter caseIntegerOrdinalDimension(IntegerOrdinalDimension object) {
				return createIntegerOrdinalDimensionAdapter();
			}
			@Override
			public Adapter caseDecimalOrdinalDimension(DecimalOrdinalDimension object) {
				return createDecimalOrdinalDimensionAdapter();
			}
			@Override
			public Adapter caseIntervalDimension(IntervalDimension object) {
				return createIntervalDimensionAdapter();
			}
			@Override
			public Adapter caseIntegerIntervalDimension(IntegerIntervalDimension object) {
				return createIntegerIntervalDimensionAdapter();
			}
			@Override
			public Adapter caseDecimalIntervalDimension(DecimalIntervalDimension object) {
				return createDecimalIntervalDimensionAdapter();
			}
			@Override
			public Adapter caseRationalDimension(RationalDimension object) {
				return createRationalDimensionAdapter();
			}
			@Override
			public Adapter caseIntegerRationalDimension(IntegerRationalDimension object) {
				return createIntegerRationalDimensionAdapter();
			}
			@Override
			public Adapter caseDecimalRationalDimension(DecimalRationalDimension object) {
				return createDecimalRationalDimensionAdapter();
			}
			@Override
			public Adapter caseMeasurementDomain(MeasurementDomain object) {
				return createMeasurementDomainAdapter();
			}
			@Override
			public Adapter caseReferenceRegion(ReferenceRegion object) {
				return createReferenceRegionAdapter();
			}
			@Override
			public Adapter caseMeasurementRegion(MeasurementRegion object) {
				return createMeasurementRegionAdapter();
			}
			@Override
			public Adapter caseBasicMeasurementRegion(BasicMeasurementRegion object) {
				return createBasicMeasurementRegionAdapter();
			}
			@Override
			public Adapter caseDecimalMeasurementRegion(DecimalMeasurementRegion object) {
				return createDecimalMeasurementRegionAdapter();
			}
			@Override
			public Adapter caseIntegerMeasurementRegion(IntegerMeasurementRegion object) {
				return createIntegerMeasurementRegionAdapter();
			}
			@Override
			public Adapter caseComposedMeasurementRegion(ComposedMeasurementRegion object) {
				return createComposedMeasurementRegionAdapter();
			}
			@Override
			public Adapter caseNominalRegion(NominalRegion object) {
				return createNominalRegionAdapter();
			}
			@Override
			public Adapter caseStringNominalRegion(StringNominalRegion object) {
				return createStringNominalRegionAdapter();
			}
			@Override
			public Adapter caseLiteralInteger(LiteralInteger object) {
				return createLiteralIntegerAdapter();
			}
			@Override
			public Adapter caseLiteralDecimal(LiteralDecimal object) {
				return createLiteralDecimalAdapter();
			}
			@Override
			public Adapter caseLiteralString(LiteralString object) {
				return createLiteralStringAdapter();
			}
			@Override
			public Adapter caseLiteralBoolean(LiteralBoolean object) {
				return createLiteralBooleanAdapter();
			}
			@Override
			public Adapter caseLiteralNull(LiteralNull object) {
				return createLiteralNullAdapter();
			}
			@Override
			public Adapter caseInstanceValue(InstanceValue object) {
				return createInstanceValueAdapter();
			}
			@Override
			public Adapter caseLiteralUnlimitedNatural(LiteralUnlimitedNatural object) {
				return createLiteralUnlimitedNaturalAdapter();
			}
			@Override
			public Adapter caseObjectClass(ObjectClass object) {
				return createObjectClassAdapter();
			}
			@Override
			public Adapter caseMomentClass(MomentClass object) {
				return createMomentClassAdapter();
			}
			@Override
			public Adapter caseSortalClass(SortalClass object) {
				return createSortalClassAdapter();
			}
			@Override
			public Adapter caseMixinClass(MixinClass object) {
				return createMixinClassAdapter();
			}
			@Override
			public Adapter caseRigidSortalClass(RigidSortalClass object) {
				return createRigidSortalClassAdapter();
			}
			@Override
			public Adapter caseAntiRigidSortalClass(AntiRigidSortalClass object) {
				return createAntiRigidSortalClassAdapter();
			}
			@Override
			public Adapter caseSubstanceSortal(SubstanceSortal object) {
				return createSubstanceSortalAdapter();
			}
			@Override
			public Adapter caseSubKind(SubKind object) {
				return createSubKindAdapter();
			}
			@Override
			public Adapter caseKind(Kind object) {
				return createKindAdapter();
			}
			@Override
			public Adapter caseQuantity(Quantity object) {
				return createQuantityAdapter();
			}
			@Override
			public Adapter caseCollective(Collective object) {
				return createCollectiveAdapter();
			}
			@Override
			public Adapter casePhase(Phase object) {
				return createPhaseAdapter();
			}
			@Override
			public Adapter caseRole(Role object) {
				return createRoleAdapter();
			}
			@Override
			public Adapter caseRigidMixinClass(RigidMixinClass object) {
				return createRigidMixinClassAdapter();
			}
			@Override
			public Adapter caseNonRigidMixinClass(NonRigidMixinClass object) {
				return createNonRigidMixinClassAdapter();
			}
			@Override
			public Adapter caseCategory(Category object) {
				return createCategoryAdapter();
			}
			@Override
			public Adapter caseAntiRigidMixinClass(AntiRigidMixinClass object) {
				return createAntiRigidMixinClassAdapter();
			}
			@Override
			public Adapter caseSemiRigidMixinClass(SemiRigidMixinClass object) {
				return createSemiRigidMixinClassAdapter();
			}
			@Override
			public Adapter caseRoleMixin(RoleMixin object) {
				return createRoleMixinAdapter();
			}
			@Override
			public Adapter caseMixin(Mixin object) {
				return createMixinAdapter();
			}
			@Override
			public Adapter caseIntrinsicMomentClass(IntrinsicMomentClass object) {
				return createIntrinsicMomentClassAdapter();
			}
			@Override
			public Adapter caseMode(Mode object) {
				return createModeAdapter();
			}
			@Override
			public Adapter caseQuality(Quality object) {
				return createQualityAdapter();
			}
			@Override
			public Adapter caseMeasurableQuality(MeasurableQuality object) {
				return createMeasurableQualityAdapter();
			}
			@Override
			public Adapter caseNominalQuality(NominalQuality object) {
				return createNominalQualityAdapter();
			}
			@Override
			public Adapter caseNonPerceivableQuality(NonPerceivableQuality object) {
				return createNonPerceivableQualityAdapter();
			}
			@Override
			public Adapter casePerceivableQuality(PerceivableQuality object) {
				return createPerceivableQualityAdapter();
			}
			@Override
			public Adapter caseRelator(Relator object) {
				return createRelatorAdapter();
			}
			@Override
			public Adapter caseDirectedBinaryAssociation(DirectedBinaryAssociation object) {
				return createDirectedBinaryAssociationAdapter();
			}
			@Override
			public Adapter caseMeronymic(Meronymic object) {
				return createMeronymicAdapter();
			}
			@Override
			public Adapter casesubQuantityOf(subQuantityOf object) {
				return createsubQuantityOfAdapter();
			}
			@Override
			public Adapter casesubCollectionOf(subCollectionOf object) {
				return createsubCollectionOfAdapter();
			}
			@Override
			public Adapter casememberOf(memberOf object) {
				return creatememberOfAdapter();
			}
			@Override
			public Adapter casecomponentOf(componentOf object) {
				return createcomponentOfAdapter();
			}
			@Override
			public Adapter caseDependencyRelationship(DependencyRelationship object) {
				return createDependencyRelationshipAdapter();
			}
			@Override
			public Adapter caseCharacterization(Characterization object) {
				return createCharacterizationAdapter();
			}
			@Override
			public Adapter caseMediation(Mediation object) {
				return createMediationAdapter();
			}
			@Override
			public Adapter caseDerivation(Derivation object) {
				return createDerivationAdapter();
			}
			@Override
			public Adapter caseStructuration(Structuration object) {
				return createStructurationAdapter();
			}
			@Override
			public Adapter caseFormalAssociation(FormalAssociation object) {
				return createFormalAssociationAdapter();
			}
			@Override
			public Adapter caseMaterialAssociation(MaterialAssociation object) {
				return createMaterialAssociationAdapter();
			}
			@Override
			public Adapter caseEModelElement(EModelElement object) {
				return createEModelElementAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Comment
	 * @generated
	 */
	public Adapter createCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Package
	 * @generated
	 */
	public Adapter createPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.PackageableElement <em>Packageable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.PackageableElement
	 * @generated
	 */
	public Adapter createPackageableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Dependency <em>Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Dependency
	 * @generated
	 */
	public Adapter createDependencyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.DirectedRelationship <em>Directed Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.DirectedRelationship
	 * @generated
	 */
	public Adapter createDirectedRelationshipAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Relationship <em>Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Relationship
	 * @generated
	 */
	public Adapter createRelationshipAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Namespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Namespace
	 * @generated
	 */
	public Adapter createNamespaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.ElementImport <em>Element Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.ElementImport
	 * @generated
	 */
	public Adapter createElementImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.PackageImport <em>Package Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.PackageImport
	 * @generated
	 */
	public Adapter createPackageImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Constraintx <em>Constraintx</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Constraintx
	 * @generated
	 */
	public Adapter createConstraintxAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.ValueSpecification <em>Value Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.ValueSpecification
	 * @generated
	 */
	public Adapter createValueSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.TypedElement
	 * @generated
	 */
	public Adapter createTypedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Type
	 * @generated
	 */
	public Adapter createTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Association <em>Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Association
	 * @generated
	 */
	public Adapter createAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Classifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Classifier
	 * @generated
	 */
	public Adapter createClassifierAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.RedefinableElement <em>Redefinable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.RedefinableElement
	 * @generated
	 */
	public Adapter createRedefinableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Generalization <em>Generalization</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Generalization
	 * @generated
	 */
	public Adapter createGeneralizationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.GeneralizationSet <em>Generalization Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.GeneralizationSet
	 * @generated
	 */
	public Adapter createGeneralizationSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Feature
	 * @generated
	 */
	public Adapter createFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.OpaqueExpression <em>Opaque Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.OpaqueExpression
	 * @generated
	 */
	public Adapter createOpaqueExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.MultiplicityElement <em>Multiplicity Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.MultiplicityElement
	 * @generated
	 */
	public Adapter createMultiplicityElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Property
	 * @generated
	 */
	public Adapter createPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Class <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Class
	 * @generated
	 */
	public Adapter createClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Model
	 * @generated
	 */
	public Adapter createModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.DataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.DataType
	 * @generated
	 */
	public Adapter createDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.StructuralFeature <em>Structural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.StructuralFeature
	 * @generated
	 */
	public Adapter createStructuralFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.StringExpression <em>String Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.StringExpression
	 * @generated
	 */
	public Adapter createStringExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.PackageMerge <em>Package Merge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.PackageMerge
	 * @generated
	 */
	public Adapter createPackageMergeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.InstanceSpecification <em>Instance Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.InstanceSpecification
	 * @generated
	 */
	public Adapter createInstanceSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Slot <em>Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Slot
	 * @generated
	 */
	public Adapter createSlotAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.LiteralSpecification <em>Literal Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.LiteralSpecification
	 * @generated
	 */
	public Adapter createLiteralSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Enumeration <em>Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Enumeration
	 * @generated
	 */
	public Adapter createEnumerationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.EnumerationLiteral <em>Enumeration Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.EnumerationLiteral
	 * @generated
	 */
	public Adapter createEnumerationLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.OrdinalEnumeration <em>Ordinal Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.OrdinalEnumeration
	 * @generated
	 */
	public Adapter createOrdinalEnumerationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.OrdinalLiteral <em>Ordinal Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.OrdinalLiteral
	 * @generated
	 */
	public Adapter createOrdinalLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.PrimitiveType
	 * @generated
	 */
	public Adapter createPrimitiveTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.ReferenceStructure <em>Reference Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.ReferenceStructure
	 * @generated
	 */
	public Adapter createReferenceStructureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.NominalStructure <em>Nominal Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.NominalStructure
	 * @generated
	 */
	public Adapter createNominalStructureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.StringNominalStructure <em>String Nominal Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.StringNominalStructure
	 * @generated
	 */
	public Adapter createStringNominalStructureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.MeasurementStructure <em>Measurement Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.MeasurementStructure
	 * @generated
	 */
	public Adapter createMeasurementStructureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.MeasurementDimension <em>Measurement Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.MeasurementDimension
	 * @generated
	 */
	public Adapter createMeasurementDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.OrdinalDimension <em>Ordinal Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.OrdinalDimension
	 * @generated
	 */
	public Adapter createOrdinalDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.IntegerOrdinalDimension <em>Integer Ordinal Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.IntegerOrdinalDimension
	 * @generated
	 */
	public Adapter createIntegerOrdinalDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.DecimalOrdinalDimension <em>Decimal Ordinal Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.DecimalOrdinalDimension
	 * @generated
	 */
	public Adapter createDecimalOrdinalDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.IntervalDimension <em>Interval Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.IntervalDimension
	 * @generated
	 */
	public Adapter createIntervalDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.IntegerIntervalDimension <em>Integer Interval Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.IntegerIntervalDimension
	 * @generated
	 */
	public Adapter createIntegerIntervalDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.DecimalIntervalDimension <em>Decimal Interval Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.DecimalIntervalDimension
	 * @generated
	 */
	public Adapter createDecimalIntervalDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.RationalDimension <em>Rational Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.RationalDimension
	 * @generated
	 */
	public Adapter createRationalDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.IntegerRationalDimension <em>Integer Rational Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.IntegerRationalDimension
	 * @generated
	 */
	public Adapter createIntegerRationalDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.DecimalRationalDimension <em>Decimal Rational Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.DecimalRationalDimension
	 * @generated
	 */
	public Adapter createDecimalRationalDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.MeasurementDomain <em>Measurement Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.MeasurementDomain
	 * @generated
	 */
	public Adapter createMeasurementDomainAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.ReferenceRegion <em>Reference Region</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.ReferenceRegion
	 * @generated
	 */
	public Adapter createReferenceRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.MeasurementRegion <em>Measurement Region</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.MeasurementRegion
	 * @generated
	 */
	public Adapter createMeasurementRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.BasicMeasurementRegion <em>Basic Measurement Region</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.BasicMeasurementRegion
	 * @generated
	 */
	public Adapter createBasicMeasurementRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.DecimalMeasurementRegion <em>Decimal Measurement Region</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.DecimalMeasurementRegion
	 * @generated
	 */
	public Adapter createDecimalMeasurementRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.IntegerMeasurementRegion <em>Integer Measurement Region</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.IntegerMeasurementRegion
	 * @generated
	 */
	public Adapter createIntegerMeasurementRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.ComposedMeasurementRegion <em>Composed Measurement Region</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.ComposedMeasurementRegion
	 * @generated
	 */
	public Adapter createComposedMeasurementRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.NominalRegion <em>Nominal Region</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.NominalRegion
	 * @generated
	 */
	public Adapter createNominalRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.StringNominalRegion <em>String Nominal Region</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.StringNominalRegion
	 * @generated
	 */
	public Adapter createStringNominalRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.LiteralInteger <em>Literal Integer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.LiteralInteger
	 * @generated
	 */
	public Adapter createLiteralIntegerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.LiteralDecimal <em>Literal Decimal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.LiteralDecimal
	 * @generated
	 */
	public Adapter createLiteralDecimalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.LiteralString <em>Literal String</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.LiteralString
	 * @generated
	 */
	public Adapter createLiteralStringAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.LiteralBoolean <em>Literal Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.LiteralBoolean
	 * @generated
	 */
	public Adapter createLiteralBooleanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.LiteralNull <em>Literal Null</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.LiteralNull
	 * @generated
	 */
	public Adapter createLiteralNullAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.InstanceValue <em>Instance Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.InstanceValue
	 * @generated
	 */
	public Adapter createInstanceValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.LiteralUnlimitedNatural <em>Literal Unlimited Natural</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.LiteralUnlimitedNatural
	 * @generated
	 */
	public Adapter createLiteralUnlimitedNaturalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.ObjectClass <em>Object Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.ObjectClass
	 * @generated
	 */
	public Adapter createObjectClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.MomentClass <em>Moment Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.MomentClass
	 * @generated
	 */
	public Adapter createMomentClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.SortalClass <em>Sortal Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.SortalClass
	 * @generated
	 */
	public Adapter createSortalClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.MixinClass <em>Mixin Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.MixinClass
	 * @generated
	 */
	public Adapter createMixinClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.RigidSortalClass <em>Rigid Sortal Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.RigidSortalClass
	 * @generated
	 */
	public Adapter createRigidSortalClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.AntiRigidSortalClass <em>Anti Rigid Sortal Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.AntiRigidSortalClass
	 * @generated
	 */
	public Adapter createAntiRigidSortalClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.SubstanceSortal <em>Substance Sortal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.SubstanceSortal
	 * @generated
	 */
	public Adapter createSubstanceSortalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.SubKind <em>Sub Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.SubKind
	 * @generated
	 */
	public Adapter createSubKindAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Kind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Kind
	 * @generated
	 */
	public Adapter createKindAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Quantity <em>Quantity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Quantity
	 * @generated
	 */
	public Adapter createQuantityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Collective <em>Collective</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Collective
	 * @generated
	 */
	public Adapter createCollectiveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Phase <em>Phase</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Phase
	 * @generated
	 */
	public Adapter createPhaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Role
	 * @generated
	 */
	public Adapter createRoleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.RigidMixinClass <em>Rigid Mixin Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.RigidMixinClass
	 * @generated
	 */
	public Adapter createRigidMixinClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.NonRigidMixinClass <em>Non Rigid Mixin Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.NonRigidMixinClass
	 * @generated
	 */
	public Adapter createNonRigidMixinClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Category <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Category
	 * @generated
	 */
	public Adapter createCategoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.AntiRigidMixinClass <em>Anti Rigid Mixin Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.AntiRigidMixinClass
	 * @generated
	 */
	public Adapter createAntiRigidMixinClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.SemiRigidMixinClass <em>Semi Rigid Mixin Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.SemiRigidMixinClass
	 * @generated
	 */
	public Adapter createSemiRigidMixinClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.RoleMixin <em>Role Mixin</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.RoleMixin
	 * @generated
	 */
	public Adapter createRoleMixinAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Mixin <em>Mixin</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Mixin
	 * @generated
	 */
	public Adapter createMixinAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.IntrinsicMomentClass <em>Intrinsic Moment Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.IntrinsicMomentClass
	 * @generated
	 */
	public Adapter createIntrinsicMomentClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Mode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Mode
	 * @generated
	 */
	public Adapter createModeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Quality <em>Quality</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Quality
	 * @generated
	 */
	public Adapter createQualityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.MeasurableQuality <em>Measurable Quality</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.MeasurableQuality
	 * @generated
	 */
	public Adapter createMeasurableQualityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.NominalQuality <em>Nominal Quality</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.NominalQuality
	 * @generated
	 */
	public Adapter createNominalQualityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.NonPerceivableQuality <em>Non Perceivable Quality</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.NonPerceivableQuality
	 * @generated
	 */
	public Adapter createNonPerceivableQualityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.PerceivableQuality <em>Perceivable Quality</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.PerceivableQuality
	 * @generated
	 */
	public Adapter createPerceivableQualityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Relator <em>Relator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Relator
	 * @generated
	 */
	public Adapter createRelatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.DirectedBinaryAssociation <em>Directed Binary Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.DirectedBinaryAssociation
	 * @generated
	 */
	public Adapter createDirectedBinaryAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Meronymic <em>Meronymic</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Meronymic
	 * @generated
	 */
	public Adapter createMeronymicAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.subQuantityOf <em>sub Quantity Of</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.subQuantityOf
	 * @generated
	 */
	public Adapter createsubQuantityOfAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.subCollectionOf <em>sub Collection Of</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.subCollectionOf
	 * @generated
	 */
	public Adapter createsubCollectionOfAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.memberOf <em>member Of</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.memberOf
	 * @generated
	 */
	public Adapter creatememberOfAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.componentOf <em>component Of</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.componentOf
	 * @generated
	 */
	public Adapter createcomponentOfAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.DependencyRelationship <em>Dependency Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.DependencyRelationship
	 * @generated
	 */
	public Adapter createDependencyRelationshipAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Characterization <em>Characterization</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Characterization
	 * @generated
	 */
	public Adapter createCharacterizationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Mediation <em>Mediation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Mediation
	 * @generated
	 */
	public Adapter createMediationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Derivation <em>Derivation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Derivation
	 * @generated
	 */
	public Adapter createDerivationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.Structuration <em>Structuration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.Structuration
	 * @generated
	 */
	public Adapter createStructurationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.FormalAssociation <em>Formal Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.FormalAssociation
	 * @generated
	 */
	public Adapter createFormalAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link RefOntoUML.MaterialAssociation <em>Material Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see RefOntoUML.MaterialAssociation
	 * @generated
	 */
	public Adapter createMaterialAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EModelElement <em>EModel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.EModelElement
	 * @generated
	 */
	public Adapter createEModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //RefOntoUMLAdapterFactory
