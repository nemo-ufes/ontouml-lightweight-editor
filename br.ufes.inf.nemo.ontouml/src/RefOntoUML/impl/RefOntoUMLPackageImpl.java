/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.RefOntoUMLPackage;

import RefOntoUML.util.RefOntoUMLValidator;

import java.io.IOException;

import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RefOntoUMLPackageImpl extends EPackageImpl implements RefOntoUMLPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected String packageFilename = "RefOntoUML.ecore";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dependencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass directedRelationshipEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationshipEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namespaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementImportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageImportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintxEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classifierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass redefinableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass generalizationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass generalizationSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass opaqueExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiplicityElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structuralFeatureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringExpressionEClass = null;

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
	private EClass packageMergeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instanceSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass slotEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumerationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumerationLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ordinalEnumerationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ordinalLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceStructureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nominalStructureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringNominalStructureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measurementStructureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measurementDimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ordinalDimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerOrdinalDimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decimalOrdinalDimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intervalDimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerIntervalDimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decimalIntervalDimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rationalDimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerRationalDimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decimalRationalDimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measurementDomainEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceRegionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measurementRegionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass basicMeasurementRegionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decimalMeasurementRegionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerMeasurementRegionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass composedMeasurementRegionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nominalRegionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringNominalRegionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalIntegerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalDecimalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalStringEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalBooleanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalNullEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instanceValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalUnlimitedNaturalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objectClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass momentClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sortalClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mixinClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rigidSortalClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass antiRigidSortalClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass substanceSortalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subKindEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kindEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass quantityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectiveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass phaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rigidMixinClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nonRigidMixinClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass categoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass antiRigidMixinClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass semiRigidMixinClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleMixinEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mixinEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intrinsicMomentClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qualityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measurableQualityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nominalQualityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nonPerceivableQualityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass perceivableQualityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass directedBinaryAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass meronymicEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subQuantityOfEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subCollectionOfEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass memberOfEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentOfEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dependencyRelationshipEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass characterizationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mediationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass derivationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formalAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass materialAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum visibilityKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum aggregationKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType integerEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType booleanEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType stringEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType unlimitedNaturalEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType decimalEDataType = null;

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
	 * @see RefOntoUML.RefOntoUMLPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RefOntoUMLPackageImpl() {
		super(eNS_URI, RefOntoUMLFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link RefOntoUMLPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @generated
	 */
	public static RefOntoUMLPackage init() {
		if (isInited) return (RefOntoUMLPackage)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI);

		// Obtain or create and register package
		RefOntoUMLPackageImpl theRefOntoUMLPackage = (RefOntoUMLPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RefOntoUMLPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RefOntoUMLPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Load packages
		theRefOntoUMLPackage.loadPackage();

		// Fix loaded packages
		theRefOntoUMLPackage.fixPackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theRefOntoUMLPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return RefOntoUMLValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theRefOntoUMLPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RefOntoUMLPackage.eNS_URI, theRefOntoUMLPackage);
		return theRefOntoUMLPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComment() {
		if (commentEClass == null) {
			commentEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(7);
		}
		return commentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Body() {
        return (EAttribute)getComment().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComment_AnnotatedElement() {
        return (EReference)getComment().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElement() {
		if (elementEClass == null) {
			elementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(8);
		}
		return elementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElement_OwnedElement() {
        return (EReference)getElement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElement_Owner() {
        return (EReference)getElement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElement_OwnedComment() {
        return (EReference)getElement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackage() {
		if (packageEClass == null) {
			packageEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(9);
		}
		return packageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_OwnedType() {
        return (EReference)getPackage().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_PackageMerge() {
        return (EReference)getPackage().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_PackagedElement() {
        return (EReference)getPackage().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_NestedPackage() {
        return (EReference)getPackage().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_NestingPackage() {
        return (EReference)getPackage().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageableElement() {
		if (packageableElementEClass == null) {
			packageableElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(10);
		}
		return packageableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		if (namedElementEClass == null) {
			namedElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(11);
		}
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
        return (EAttribute)getNamedElement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Visibility() {
        return (EAttribute)getNamedElement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_QualifiedName() {
        return (EAttribute)getNamedElement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamedElement_ClientDependency() {
        return (EReference)getNamedElement().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamedElement_Namespace() {
        return (EReference)getNamedElement().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamedElement_NameExpression() {
        return (EReference)getNamedElement().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDependency() {
		if (dependencyEClass == null) {
			dependencyEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(12);
		}
		return dependencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDependency_Supplier() {
        return (EReference)getDependency().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDependency_Client() {
        return (EReference)getDependency().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDirectedRelationship() {
		if (directedRelationshipEClass == null) {
			directedRelationshipEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(13);
		}
		return directedRelationshipEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDirectedRelationship_Source() {
        return (EReference)getDirectedRelationship().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDirectedRelationship_Target() {
        return (EReference)getDirectedRelationship().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelationship() {
		if (relationshipEClass == null) {
			relationshipEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(14);
		}
		return relationshipEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelationship_RelatedElement() {
        return (EReference)getRelationship().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamespace() {
		if (namespaceEClass == null) {
			namespaceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(15);
		}
		return namespaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamespace_ElementImport() {
        return (EReference)getNamespace().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamespace_PackageImport() {
        return (EReference)getNamespace().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamespace_OwnedRule() {
        return (EReference)getNamespace().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamespace_Member() {
        return (EReference)getNamespace().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamespace_ImportedMember() {
        return (EReference)getNamespace().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamespace_OwnedMember() {
        return (EReference)getNamespace().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementImport() {
		if (elementImportEClass == null) {
			elementImportEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(16);
		}
		return elementImportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElementImport_Visibility() {
        return (EAttribute)getElementImport().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElementImport_Alias() {
        return (EAttribute)getElementImport().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementImport_ImportedElement() {
        return (EReference)getElementImport().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementImport_ImportingNamespace() {
        return (EReference)getElementImport().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageImport() {
		if (packageImportEClass == null) {
			packageImportEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(17);
		}
		return packageImportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageImport_Visibility() {
        return (EAttribute)getPackageImport().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageImport_ImportedPackage() {
        return (EReference)getPackageImport().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageImport_ImportingNamespace() {
        return (EReference)getPackageImport().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintx() {
		if (constraintxEClass == null) {
			constraintxEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(18);
		}
		return constraintxEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraintx_ConstrainedElement() {
        return (EReference)getConstraintx().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraintx_Specification() {
        return (EReference)getConstraintx().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraintx_Context() {
        return (EReference)getConstraintx().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValueSpecification() {
		if (valueSpecificationEClass == null) {
			valueSpecificationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(19);
		}
		return valueSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypedElement() {
		if (typedElementEClass == null) {
			typedElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(20);
		}
		return typedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypedElement_Type() {
        return (EReference)getTypedElement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getType() {
		if (typeEClass == null) {
			typeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(21);
		}
		return typeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getType_Package() {
        return (EReference)getType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssociation() {
		if (associationEClass == null) {
			associationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(22);
		}
		return associationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociation_OwnedEnd() {
        return (EReference)getAssociation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociation_MemberEnd() {
        return (EReference)getAssociation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_IsDerived() {
        return (EAttribute)getAssociation().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociation_EndType() {
        return (EReference)getAssociation().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociation_NavigableOwnedEnd() {
        return (EReference)getAssociation().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassifier() {
		if (classifierEClass == null) {
			classifierEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(23);
		}
		return classifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassifier_IsAbstract() {
        return (EAttribute)getClassifier().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassifier_Generalization() {
        return (EReference)getClassifier().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassifier_PowertypeExtent() {
        return (EReference)getClassifier().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassifier_Feature() {
        return (EReference)getClassifier().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassifier_InheritedMember() {
        return (EReference)getClassifier().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassifier_RedefinedClassifier() {
        return (EReference)getClassifier().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassifier_General() {
        return (EReference)getClassifier().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassifier_Attribute() {
        return (EReference)getClassifier().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRedefinableElement() {
		if (redefinableElementEClass == null) {
			redefinableElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(24);
		}
		return redefinableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRedefinableElement_IsLeaf() {
        return (EAttribute)getRedefinableElement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRedefinableElement_RedefinedElement() {
        return (EReference)getRedefinableElement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRedefinableElement_RedefinitionContext() {
        return (EReference)getRedefinableElement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeneralization() {
		if (generalizationEClass == null) {
			generalizationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(25);
		}
		return generalizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneralization_IsSubstitutable() {
        return (EAttribute)getGeneralization().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeneralization_General() {
        return (EReference)getGeneralization().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeneralization_GeneralizationSet() {
        return (EReference)getGeneralization().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeneralization_Specific() {
        return (EReference)getGeneralization().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeneralizationSet() {
		if (generalizationSetEClass == null) {
			generalizationSetEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(26);
		}
		return generalizationSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneralizationSet_IsCovering() {
        return (EAttribute)getGeneralizationSet().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneralizationSet_IsDisjoint() {
        return (EAttribute)getGeneralizationSet().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeneralizationSet_Powertype() {
        return (EReference)getGeneralizationSet().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeneralizationSet_Generalization() {
        return (EReference)getGeneralizationSet().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFeature() {
		if (featureEClass == null) {
			featureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(27);
		}
		return featureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeature_IsStatic() {
        return (EAttribute)getFeature().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFeature_FeaturingClassifier() {
        return (EReference)getFeature().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOpaqueExpression() {
		if (opaqueExpressionEClass == null) {
			opaqueExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(28);
		}
		return opaqueExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOpaqueExpression_Body() {
        return (EAttribute)getOpaqueExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOpaqueExpression_Language() {
        return (EAttribute)getOpaqueExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultiplicityElement() {
		if (multiplicityElementEClass == null) {
			multiplicityElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(29);
		}
		return multiplicityElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMultiplicityElement_IsOrdered() {
        return (EAttribute)getMultiplicityElement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMultiplicityElement_IsUnique() {
        return (EAttribute)getMultiplicityElement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMultiplicityElement_Upper() {
        return (EAttribute)getMultiplicityElement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMultiplicityElement_Lower() {
        return (EAttribute)getMultiplicityElement().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMultiplicityElement_UpperValue() {
        return (EReference)getMultiplicityElement().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMultiplicityElement_LowerValue() {
        return (EReference)getMultiplicityElement().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperty() {
		if (propertyEClass == null) {
			propertyEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(30);
		}
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperty_Class() {
        return (EReference)getProperty().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperty_Datatype() {
        return (EReference)getProperty().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_IsDerived() {
        return (EAttribute)getProperty().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_IsDerivedUnion() {
        return (EAttribute)getProperty().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Default() {
        return (EAttribute)getProperty().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Aggregation() {
        return (EAttribute)getProperty().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_IsComposite() {
        return (EAttribute)getProperty().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperty_RedefinedProperty() {
        return (EReference)getProperty().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperty_OwningAssociation() {
        return (EReference)getProperty().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperty_DefaultValue() {
        return (EReference)getProperty().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperty_Opposite() {
        return (EReference)getProperty().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperty_SubsettedProperty() {
        return (EReference)getProperty().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperty_Association() {
        return (EReference)getProperty().getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClass_() {
		if (classEClass == null) {
			classEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(31);
		}
		return classEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClass_NestedClassifier() {
        return (EReference)getClass_().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClass_SuperClass() {
        return (EReference)getClass_().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass_IsActive() {
        return (EAttribute)getClass_().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClass_OwnedAttribute() {
        return (EReference)getClass_().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModel() {
		if (modelEClass == null) {
			modelEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(32);
		}
		return modelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModel_Viewpoint() {
        return (EAttribute)getModel().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataType() {
		if (dataTypeEClass == null) {
			dataTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(33);
		}
		return dataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataType_OwnedAttribute() {
        return (EReference)getDataType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStructuralFeature() {
		if (structuralFeatureEClass == null) {
			structuralFeatureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(34);
		}
		return structuralFeatureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStructuralFeature_IsReadOnly() {
        return (EAttribute)getStructuralFeature().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringExpression() {
		if (stringExpressionEClass == null) {
			stringExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(35);
		}
		return stringExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringExpression_SubExpression() {
        return (EReference)getStringExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringExpression_OwningExpression() {
        return (EReference)getStringExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpression() {
		if (expressionEClass == null) {
			expressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(36);
		}
		return expressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExpression_Symbol() {
        return (EAttribute)getExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExpression_Operand() {
        return (EReference)getExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageMerge() {
		if (packageMergeEClass == null) {
			packageMergeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(37);
		}
		return packageMergeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageMerge_MergedPackage() {
        return (EReference)getPackageMerge().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageMerge_ReceivingPackage() {
        return (EReference)getPackageMerge().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstanceSpecification() {
		if (instanceSpecificationEClass == null) {
			instanceSpecificationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(38);
		}
		return instanceSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstanceSpecification_Classifier() {
        return (EReference)getInstanceSpecification().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstanceSpecification_Slot() {
        return (EReference)getInstanceSpecification().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstanceSpecification_Specification() {
        return (EReference)getInstanceSpecification().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSlot() {
		if (slotEClass == null) {
			slotEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(39);
		}
		return slotEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSlot_DefiningFeature() {
        return (EReference)getSlot().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSlot_Value() {
        return (EReference)getSlot().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSlot_OwningInstance() {
        return (EReference)getSlot().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLiteralSpecification() {
		if (literalSpecificationEClass == null) {
			literalSpecificationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(40);
		}
		return literalSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumeration() {
		if (enumerationEClass == null) {
			enumerationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(41);
		}
		return enumerationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumeration_OwnedLiteral() {
        return (EReference)getEnumeration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumerationLiteral() {
		if (enumerationLiteralEClass == null) {
			enumerationLiteralEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(42);
		}
		return enumerationLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumerationLiteral_Enumeration() {
        return (EReference)getEnumerationLiteral().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOrdinalEnumeration() {
		if (ordinalEnumerationEClass == null) {
			ordinalEnumerationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(43);
		}
		return ordinalEnumerationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOrdinalLiteral() {
		if (ordinalLiteralEClass == null) {
			ordinalLiteralEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(44);
		}
		return ordinalLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveType() {
		if (primitiveTypeEClass == null) {
			primitiveTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(45);
		}
		return primitiveTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceStructure() {
		if (referenceStructureEClass == null) {
			referenceStructureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(46);
		}
		return referenceStructureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceStructure_OwnedRegions() {
        return (EReference)getReferenceStructure().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNominalStructure() {
		if (nominalStructureEClass == null) {
			nominalStructureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(47);
		}
		return nominalStructureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringNominalStructure() {
		if (stringNominalStructureEClass == null) {
			stringNominalStructureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(48);
		}
		return stringNominalStructureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasurementStructure() {
		if (measurementStructureEClass == null) {
			measurementStructureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(49);
		}
		return measurementStructureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasurementDimension() {
		if (measurementDimensionEClass == null) {
			measurementDimensionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(50);
		}
		return measurementDimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurementDimension_Domain() {
        return (EReference)getMeasurementDimension().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeasurementDimension_UnitOfMeasure() {
        return (EAttribute)getMeasurementDimension().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurementDimension_LowerBound() {
        return (EReference)getMeasurementDimension().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurementDimension_UpperBound() {
        return (EReference)getMeasurementDimension().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOrdinalDimension() {
		if (ordinalDimensionEClass == null) {
			ordinalDimensionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(51);
		}
		return ordinalDimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerOrdinalDimension() {
		if (integerOrdinalDimensionEClass == null) {
			integerOrdinalDimensionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(52);
		}
		return integerOrdinalDimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecimalOrdinalDimension() {
		if (decimalOrdinalDimensionEClass == null) {
			decimalOrdinalDimensionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(53);
		}
		return decimalOrdinalDimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntervalDimension() {
		if (intervalDimensionEClass == null) {
			intervalDimensionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(54);
		}
		return intervalDimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerIntervalDimension() {
		if (integerIntervalDimensionEClass == null) {
			integerIntervalDimensionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(55);
		}
		return integerIntervalDimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecimalIntervalDimension() {
		if (decimalIntervalDimensionEClass == null) {
			decimalIntervalDimensionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(56);
		}
		return decimalIntervalDimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRationalDimension() {
		if (rationalDimensionEClass == null) {
			rationalDimensionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(57);
		}
		return rationalDimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerRationalDimension() {
		if (integerRationalDimensionEClass == null) {
			integerRationalDimensionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(58);
		}
		return integerRationalDimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecimalRationalDimension() {
		if (decimalRationalDimensionEClass == null) {
			decimalRationalDimensionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(59);
		}
		return decimalRationalDimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasurementDomain() {
		if (measurementDomainEClass == null) {
			measurementDomainEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(60);
		}
		return measurementDomainEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurementDomain_OwnedDimensions() {
        return (EReference)getMeasurementDomain().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurementDomain_CompositionRule() {
        return (EReference)getMeasurementDomain().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceRegion() {
		if (referenceRegionEClass == null) {
			referenceRegionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(61);
		}
		return referenceRegionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceRegion_Structure() {
        return (EReference)getReferenceRegion().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasurementRegion() {
		if (measurementRegionEClass == null) {
			measurementRegionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(62);
		}
		return measurementRegionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBasicMeasurementRegion() {
		if (basicMeasurementRegionEClass == null) {
			basicMeasurementRegionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(63);
		}
		return basicMeasurementRegionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecimalMeasurementRegion() {
		if (decimalMeasurementRegionEClass == null) {
			decimalMeasurementRegionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(64);
		}
		return decimalMeasurementRegionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerMeasurementRegion() {
		if (integerMeasurementRegionEClass == null) {
			integerMeasurementRegionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(65);
		}
		return integerMeasurementRegionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComposedMeasurementRegion() {
		if (composedMeasurementRegionEClass == null) {
			composedMeasurementRegionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(66);
		}
		return composedMeasurementRegionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComposedMeasurementRegion_OwnedRegions() {
        return (EReference)getComposedMeasurementRegion().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNominalRegion() {
		if (nominalRegionEClass == null) {
			nominalRegionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(67);
		}
		return nominalRegionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringNominalRegion() {
		if (stringNominalRegionEClass == null) {
			stringNominalRegionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(68);
		}
		return stringNominalRegionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLiteralInteger() {
		if (literalIntegerEClass == null) {
			literalIntegerEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(69);
		}
		return literalIntegerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLiteralInteger_Value() {
        return (EAttribute)getLiteralInteger().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLiteralDecimal() {
		if (literalDecimalEClass == null) {
			literalDecimalEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(70);
		}
		return literalDecimalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLiteralDecimal_Value() {
        return (EAttribute)getLiteralDecimal().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLiteralString() {
		if (literalStringEClass == null) {
			literalStringEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(71);
		}
		return literalStringEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLiteralString_Value() {
        return (EAttribute)getLiteralString().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLiteralBoolean() {
		if (literalBooleanEClass == null) {
			literalBooleanEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(72);
		}
		return literalBooleanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLiteralBoolean_Value() {
        return (EAttribute)getLiteralBoolean().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLiteralNull() {
		if (literalNullEClass == null) {
			literalNullEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(73);
		}
		return literalNullEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstanceValue() {
		if (instanceValueEClass == null) {
			instanceValueEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(74);
		}
		return instanceValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstanceValue_Instance() {
        return (EReference)getInstanceValue().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLiteralUnlimitedNatural() {
		if (literalUnlimitedNaturalEClass == null) {
			literalUnlimitedNaturalEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(75);
		}
		return literalUnlimitedNaturalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLiteralUnlimitedNatural_Value() {
        return (EAttribute)getLiteralUnlimitedNatural().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObjectClass() {
		if (objectClassEClass == null) {
			objectClassEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(76);
		}
		return objectClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMomentClass() {
		if (momentClassEClass == null) {
			momentClassEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(77);
		}
		return momentClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSortalClass() {
		if (sortalClassEClass == null) {
			sortalClassEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(78);
		}
		return sortalClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMixinClass() {
		if (mixinClassEClass == null) {
			mixinClassEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(79);
		}
		return mixinClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRigidSortalClass() {
		if (rigidSortalClassEClass == null) {
			rigidSortalClassEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(80);
		}
		return rigidSortalClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAntiRigidSortalClass() {
		if (antiRigidSortalClassEClass == null) {
			antiRigidSortalClassEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(81);
		}
		return antiRigidSortalClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubstanceSortal() {
		if (substanceSortalEClass == null) {
			substanceSortalEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(82);
		}
		return substanceSortalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubKind() {
		if (subKindEClass == null) {
			subKindEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(83);
		}
		return subKindEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKind() {
		if (kindEClass == null) {
			kindEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(84);
		}
		return kindEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuantity() {
		if (quantityEClass == null) {
			quantityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(85);
		}
		return quantityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCollective() {
		if (collectiveEClass == null) {
			collectiveEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(86);
		}
		return collectiveEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCollective_IsExtensional() {
        return (EAttribute)getCollective().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPhase() {
		if (phaseEClass == null) {
			phaseEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(87);
		}
		return phaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRole() {
		if (roleEClass == null) {
			roleEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(88);
		}
		return roleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRigidMixinClass() {
		if (rigidMixinClassEClass == null) {
			rigidMixinClassEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(89);
		}
		return rigidMixinClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNonRigidMixinClass() {
		if (nonRigidMixinClassEClass == null) {
			nonRigidMixinClassEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(90);
		}
		return nonRigidMixinClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCategory() {
		if (categoryEClass == null) {
			categoryEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(91);
		}
		return categoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAntiRigidMixinClass() {
		if (antiRigidMixinClassEClass == null) {
			antiRigidMixinClassEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(92);
		}
		return antiRigidMixinClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSemiRigidMixinClass() {
		if (semiRigidMixinClassEClass == null) {
			semiRigidMixinClassEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(93);
		}
		return semiRigidMixinClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoleMixin() {
		if (roleMixinEClass == null) {
			roleMixinEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(94);
		}
		return roleMixinEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMixin() {
		if (mixinEClass == null) {
			mixinEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(95);
		}
		return mixinEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntrinsicMomentClass() {
		if (intrinsicMomentClassEClass == null) {
			intrinsicMomentClassEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(96);
		}
		return intrinsicMomentClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMode() {
		if (modeEClass == null) {
			modeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(97);
		}
		return modeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuality() {
		if (qualityEClass == null) {
			qualityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(98);
		}
		return qualityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasurableQuality() {
		if (measurableQualityEClass == null) {
			measurableQualityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(99);
		}
		return measurableQualityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNominalQuality() {
		if (nominalQualityEClass == null) {
			nominalQualityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(100);
		}
		return nominalQualityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNonPerceivableQuality() {
		if (nonPerceivableQualityEClass == null) {
			nonPerceivableQualityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(101);
		}
		return nonPerceivableQualityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPerceivableQuality() {
		if (perceivableQualityEClass == null) {
			perceivableQualityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(102);
		}
		return perceivableQualityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelator() {
		if (relatorEClass == null) {
			relatorEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(103);
		}
		return relatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDirectedBinaryAssociation() {
		if (directedBinaryAssociationEClass == null) {
			directedBinaryAssociationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(104);
		}
		return directedBinaryAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeronymic() {
		if (meronymicEClass == null) {
			meronymicEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(105);
		}
		return meronymicEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeronymic_IsShareable() {
        return (EAttribute)getMeronymic().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeronymic_IsEssential() {
        return (EAttribute)getMeronymic().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeronymic_IsInseparable() {
        return (EAttribute)getMeronymic().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeronymic_IsImmutablePart() {
        return (EAttribute)getMeronymic().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeronymic_IsImmutableWhole() {
        return (EAttribute)getMeronymic().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getsubQuantityOf() {
		if (subQuantityOfEClass == null) {
			subQuantityOfEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(106);
		}
		return subQuantityOfEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getsubCollectionOf() {
		if (subCollectionOfEClass == null) {
			subCollectionOfEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(107);
		}
		return subCollectionOfEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getmemberOf() {
		if (memberOfEClass == null) {
			memberOfEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(108);
		}
		return memberOfEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getcomponentOf() {
		if (componentOfEClass == null) {
			componentOfEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(109);
		}
		return componentOfEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDependencyRelationship() {
		if (dependencyRelationshipEClass == null) {
			dependencyRelationshipEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(110);
		}
		return dependencyRelationshipEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCharacterization() {
		if (characterizationEClass == null) {
			characterizationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(111);
		}
		return characterizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMediation() {
		if (mediationEClass == null) {
			mediationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(112);
		}
		return mediationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDerivation() {
		if (derivationEClass == null) {
			derivationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(113);
		}
		return derivationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStructuration() {
		if (structurationEClass == null) {
			structurationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(114);
		}
		return structurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormalAssociation() {
		if (formalAssociationEClass == null) {
			formalAssociationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(115);
		}
		return formalAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMaterialAssociation() {
		if (materialAssociationEClass == null) {
			materialAssociationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(116);
		}
		return materialAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getVisibilityKind() {
		if (visibilityKindEEnum == null) {
			visibilityKindEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(5);
		}
		return visibilityKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAggregationKind() {
		if (aggregationKindEEnum == null) {
			aggregationKindEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(6);
		}
		return aggregationKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getInteger() {
		if (integerEDataType == null) {
			integerEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(0);
		}
		return integerEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBoolean() {
		if (booleanEDataType == null) {
			booleanEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(1);
		}
		return booleanEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getString() {
		if (stringEDataType == null) {
			stringEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(2);
		}
		return stringEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getUnlimitedNatural() {
		if (unlimitedNaturalEDataType == null) {
			unlimitedNaturalEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(3);
		}
		return unlimitedNaturalEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDecimal() {
		if (decimalEDataType == null) {
			decimalEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI).getEClassifiers().get(4);
		}
		return decimalEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUMLFactory getRefOntoUMLFactory() {
		return (RefOntoUMLFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isLoaded = false;

	/**
	 * Laods the package and any sub-packages from their serialized form.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void loadPackage() {
		if (isLoaded) return;
		isLoaded = true;

		URL url = getClass().getResource(packageFilename);
		if (url == null) {
			throw new RuntimeException("Missing serialized package: " + packageFilename);
		}
		URI uri = URI.createURI(url.toString());
		Resource resource = new EcoreResourceFactoryImpl().createResource(uri);
		try {
			resource.load(null);
		}
		catch (IOException exception) {
			throw new WrappedException(exception);
		}
		initializeFromLoadedEPackage(this, (EPackage)resource.getContents().get(0));
		createResource(eNS_URI);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isFixed = false;

	/**
	 * Fixes up the loaded package, to make it appear as if it had been programmatically built.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fixPackageContents() {
		if (isFixed) return;
		isFixed = true;
		fixEClassifiers();
	}

	/**
	 * Sets the instance class on the given classifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void fixInstanceClass(EClassifier eClassifier) {
		if (eClassifier.getInstanceClassName() == null) {
			eClassifier.setInstanceClassName("RefOntoUML." + eClassifier.getName());
			setGeneratedClassName(eClassifier);
		}
	}

} //RefOntoUMLPackageImpl
