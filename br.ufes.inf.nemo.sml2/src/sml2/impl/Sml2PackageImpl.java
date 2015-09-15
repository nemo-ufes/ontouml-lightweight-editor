/**
 */
package sml2.impl;

import RefOntoUML.RefOntoUMLPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import sml2.AllenKind;
import sml2.AllenLink;
import sml2.AttributeLink;
import sml2.AttributeReference;
import sml2.ClassLiteral;
import sml2.ComparativeKind;
import sml2.ComparativeRelation;
import sml2.ContextFormalLink;
import sml2.DataTypeLiteral;
import sml2.EntityParticipant;
import sml2.EqualsLink;
import sml2.FormalRelation;
import sml2.Function;
import sml2.FunctionParameter;
import sml2.InstantiationLink;
import sml2.Literal;
import sml2.MediationLink;
import sml2.Node;
import sml2.OrderedComparativeLink;
import sml2.Participant;
import sml2.ReferableElement;
import sml2.ReferenceNode;
import sml2.ReflectedParticipant;
import sml2.ReflectedReference;
import sml2.RelatorParticipant;
import sml2.SMLModel;
import sml2.SituationParticipant;
import sml2.SituationType;
import sml2.SituationTypeAssociation;
import sml2.SituationTypeElement;
import sml2.Sml2Factory;
import sml2.Sml2Package;
import sml2.TemporalKind;
import sml2.util.Sml2Validator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Sml2PackageImpl extends EPackageImpl implements Sml2Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass allenLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comparativeRelationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contextFormalLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataTypeLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formalRelationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityParticipantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass equalsLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instantiationLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mediationLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orderedComparativeLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass participantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reflectedParticipantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reflectedReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relatorParticipantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass situationParticipantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass situationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass situationTypeAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass situationTypeElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass smlModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum allenKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum temporalKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum comparativeKindEEnum = null;

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
	 * @see sml2.Sml2Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Sml2PackageImpl() {
		super(eNS_URI, Sml2Factory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Sml2Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Sml2Package init() {
		if (isInited) return (Sml2Package)EPackage.Registry.INSTANCE.getEPackage(Sml2Package.eNS_URI);

		// Obtain or create and register package
		Sml2PackageImpl theSml2Package = (Sml2PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Sml2PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Sml2PackageImpl());

		isInited = true;

		// Initialize simple dependencies
		RefOntoUMLPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSml2Package.createPackageContents();

		// Initialize created meta-data
		theSml2Package.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theSml2Package, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return Sml2Validator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theSml2Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Sml2Package.eNS_URI, theSml2Package);
		return theSml2Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAllenLink() {
		return allenLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllenLink_Type() {
		return (EAttribute)allenLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getAllenLink__GetSource() {
		return allenLinkEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getAllenLink__GetTarget() {
		return allenLinkEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttributeLink() {
		return attributeLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getAttributeLink__GetEntity() {
		return attributeLinkEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getAttributeLink__GetAttribute() {
		return attributeLinkEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttributeReference() {
		return attributeReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttributeReference_Type() {
		return (EReference)attributeReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getAttributeReference__GetEntity() {
		return attributeReferenceEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassLiteral() {
		return classLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassLiteral_Type() {
		return (EReference)classLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComparativeRelation() {
		return comparativeRelationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContextFormalLink() {
		return contextFormalLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContextFormalLink_Parameter() {
		return (EAttribute)contextFormalLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContextFormalLink_Type() {
		return (EReference)contextFormalLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataTypeLiteral() {
		return dataTypeLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataTypeLiteral_Value() {
		return (EAttribute)dataTypeLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataTypeLiteral_Type() {
		return (EReference)dataTypeLiteralEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormalRelation() {
		return formalRelationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormalRelation_Negated() {
		return (EAttribute)formalRelationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntityParticipant() {
		return entityParticipantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityParticipant_Type() {
		return (EReference)entityParticipantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEqualsLink() {
		return equalsLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunction() {
		return functionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunction_Name() {
		return (EAttribute)functionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunction_Parameters() {
		return (EReference)functionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionParameter() {
		return functionParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunctionParameter_Label() {
		return (EAttribute)functionParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionParameter_Parameter() {
		return (EReference)functionParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionParameter__GetFunction() {
		return functionParameterEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstantiationLink() {
		return instantiationLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getInstantiationLink__GetSource() {
		return instantiationLinkEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getInstantiationLink__GetTarget() {
		return instantiationLinkEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLiteral() {
		return literalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMediationLink() {
		return mediationLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMediationLink_Type() {
		return (EReference)mediationLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getMediationLink__GetRelator() {
		return mediationLinkEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getMediationLink__GetEntity() {
		return mediationLinkEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNode() {
		return nodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getNode__SourceRelation() {
		return nodeEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getNode__TargetRelation() {
		return nodeEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOrderedComparativeLink() {
		return orderedComparativeLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOrderedComparativeLink_Type() {
		return (EAttribute)orderedComparativeLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParticipant() {
		return participantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParticipant_Shareable() {
		return (EAttribute)participantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParticipant_Immutable() {
		return (EAttribute)participantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParticipant_Min() {
		return (EAttribute)participantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParticipant_Reflection() {
		return (EReference)participantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParticipant_Max() {
		return (EAttribute)participantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferableElement() {
		return referableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceNode() {
		return referenceNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceNode_Label() {
		return (EAttribute)referenceNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceNode_Reference() {
		return (EReference)referenceNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceNode_Situation() {
		return (EReference)referenceNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReflectedParticipant() {
		return reflectedParticipantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReflectedParticipant_Participant() {
		return (EReference)reflectedParticipantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReflectedParticipant_References() {
		return (EReference)reflectedParticipantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReflectedReference() {
		return reflectedReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReflectedReference_OwningReflection() {
		return (EReference)reflectedReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReflectedReference_Reference() {
		return (EReference)reflectedReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelatorParticipant() {
		return relatorParticipantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelatorParticipant_Type() {
		return (EReference)relatorParticipantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSituationParticipant() {
		return situationParticipantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSituationParticipant_Temporality() {
		return (EAttribute)situationParticipantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationParticipant_References() {
		return (EReference)situationParticipantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationParticipant_Type() {
		return (EReference)situationParticipantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSituationType() {
		return situationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSituationType_Name() {
		return (EAttribute)situationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationType_Elements() {
		return (EReference)situationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSituationTypeAssociation() {
		return situationTypeAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationTypeAssociation_Source() {
		return (EReference)situationTypeAssociationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationTypeAssociation_Target() {
		return (EReference)situationTypeAssociationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSituationTypeElement() {
		return situationTypeElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getSituationTypeElement__GetSituation() {
		return situationTypeElementEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSMLModel() {
		return smlModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSMLModel_Elements() {
		return (EReference)smlModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSMLModel_ContextModel() {
		return (EReference)smlModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAllenKind() {
		return allenKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTemporalKind() {
		return temporalKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getComparativeKind() {
		return comparativeKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sml2Factory getSml2Factory() {
		return (Sml2Factory)getEFactoryInstance();
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
		allenLinkEClass = createEClass(ALLEN_LINK);
		createEAttribute(allenLinkEClass, ALLEN_LINK__TYPE);
		createEOperation(allenLinkEClass, ALLEN_LINK___GET_SOURCE);
		createEOperation(allenLinkEClass, ALLEN_LINK___GET_TARGET);

		attributeLinkEClass = createEClass(ATTRIBUTE_LINK);
		createEOperation(attributeLinkEClass, ATTRIBUTE_LINK___GET_ENTITY);
		createEOperation(attributeLinkEClass, ATTRIBUTE_LINK___GET_ATTRIBUTE);

		attributeReferenceEClass = createEClass(ATTRIBUTE_REFERENCE);
		createEReference(attributeReferenceEClass, ATTRIBUTE_REFERENCE__TYPE);
		createEOperation(attributeReferenceEClass, ATTRIBUTE_REFERENCE___GET_ENTITY);

		classLiteralEClass = createEClass(CLASS_LITERAL);
		createEReference(classLiteralEClass, CLASS_LITERAL__TYPE);

		comparativeRelationEClass = createEClass(COMPARATIVE_RELATION);

		contextFormalLinkEClass = createEClass(CONTEXT_FORMAL_LINK);
		createEAttribute(contextFormalLinkEClass, CONTEXT_FORMAL_LINK__PARAMETER);
		createEReference(contextFormalLinkEClass, CONTEXT_FORMAL_LINK__TYPE);

		dataTypeLiteralEClass = createEClass(DATA_TYPE_LITERAL);
		createEAttribute(dataTypeLiteralEClass, DATA_TYPE_LITERAL__VALUE);
		createEReference(dataTypeLiteralEClass, DATA_TYPE_LITERAL__TYPE);

		formalRelationEClass = createEClass(FORMAL_RELATION);
		createEAttribute(formalRelationEClass, FORMAL_RELATION__NEGATED);

		entityParticipantEClass = createEClass(ENTITY_PARTICIPANT);
		createEReference(entityParticipantEClass, ENTITY_PARTICIPANT__TYPE);

		equalsLinkEClass = createEClass(EQUALS_LINK);

		functionEClass = createEClass(FUNCTION);
		createEAttribute(functionEClass, FUNCTION__NAME);
		createEReference(functionEClass, FUNCTION__PARAMETERS);

		functionParameterEClass = createEClass(FUNCTION_PARAMETER);
		createEAttribute(functionParameterEClass, FUNCTION_PARAMETER__LABEL);
		createEReference(functionParameterEClass, FUNCTION_PARAMETER__PARAMETER);
		createEOperation(functionParameterEClass, FUNCTION_PARAMETER___GET_FUNCTION);

		instantiationLinkEClass = createEClass(INSTANTIATION_LINK);
		createEOperation(instantiationLinkEClass, INSTANTIATION_LINK___GET_SOURCE);
		createEOperation(instantiationLinkEClass, INSTANTIATION_LINK___GET_TARGET);

		literalEClass = createEClass(LITERAL);

		mediationLinkEClass = createEClass(MEDIATION_LINK);
		createEReference(mediationLinkEClass, MEDIATION_LINK__TYPE);
		createEOperation(mediationLinkEClass, MEDIATION_LINK___GET_RELATOR);
		createEOperation(mediationLinkEClass, MEDIATION_LINK___GET_ENTITY);

		nodeEClass = createEClass(NODE);
		createEOperation(nodeEClass, NODE___SOURCE_RELATION);
		createEOperation(nodeEClass, NODE___TARGET_RELATION);

		orderedComparativeLinkEClass = createEClass(ORDERED_COMPARATIVE_LINK);
		createEAttribute(orderedComparativeLinkEClass, ORDERED_COMPARATIVE_LINK__TYPE);

		participantEClass = createEClass(PARTICIPANT);
		createEAttribute(participantEClass, PARTICIPANT__SHAREABLE);
		createEAttribute(participantEClass, PARTICIPANT__IMMUTABLE);
		createEAttribute(participantEClass, PARTICIPANT__MIN);
		createEReference(participantEClass, PARTICIPANT__REFLECTION);
		createEAttribute(participantEClass, PARTICIPANT__MAX);

		referableElementEClass = createEClass(REFERABLE_ELEMENT);

		referenceNodeEClass = createEClass(REFERENCE_NODE);
		createEAttribute(referenceNodeEClass, REFERENCE_NODE__LABEL);
		createEReference(referenceNodeEClass, REFERENCE_NODE__SITUATION);
		createEReference(referenceNodeEClass, REFERENCE_NODE__REFERENCE);

		reflectedParticipantEClass = createEClass(REFLECTED_PARTICIPANT);
		createEReference(reflectedParticipantEClass, REFLECTED_PARTICIPANT__PARTICIPANT);
		createEReference(reflectedParticipantEClass, REFLECTED_PARTICIPANT__REFERENCES);

		reflectedReferenceEClass = createEClass(REFLECTED_REFERENCE);
		createEReference(reflectedReferenceEClass, REFLECTED_REFERENCE__OWNING_REFLECTION);
		createEReference(reflectedReferenceEClass, REFLECTED_REFERENCE__REFERENCE);

		relatorParticipantEClass = createEClass(RELATOR_PARTICIPANT);
		createEReference(relatorParticipantEClass, RELATOR_PARTICIPANT__TYPE);

		situationParticipantEClass = createEClass(SITUATION_PARTICIPANT);
		createEAttribute(situationParticipantEClass, SITUATION_PARTICIPANT__TEMPORALITY);
		createEReference(situationParticipantEClass, SITUATION_PARTICIPANT__REFERENCES);
		createEReference(situationParticipantEClass, SITUATION_PARTICIPANT__TYPE);

		situationTypeEClass = createEClass(SITUATION_TYPE);
		createEAttribute(situationTypeEClass, SITUATION_TYPE__NAME);
		createEReference(situationTypeEClass, SITUATION_TYPE__ELEMENTS);

		situationTypeAssociationEClass = createEClass(SITUATION_TYPE_ASSOCIATION);
		createEReference(situationTypeAssociationEClass, SITUATION_TYPE_ASSOCIATION__SOURCE);
		createEReference(situationTypeAssociationEClass, SITUATION_TYPE_ASSOCIATION__TARGET);

		situationTypeElementEClass = createEClass(SITUATION_TYPE_ELEMENT);
		createEOperation(situationTypeElementEClass, SITUATION_TYPE_ELEMENT___GET_SITUATION);

		smlModelEClass = createEClass(SML_MODEL);
		createEReference(smlModelEClass, SML_MODEL__ELEMENTS);
		createEReference(smlModelEClass, SML_MODEL__CONTEXT_MODEL);

		// Create enums
		allenKindEEnum = createEEnum(ALLEN_KIND);
		comparativeKindEEnum = createEEnum(COMPARATIVE_KIND);
		temporalKindEEnum = createEEnum(TEMPORAL_KIND);
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

		// Obtain other dependent packages
		RefOntoUMLPackage theRefOntoUMLPackage = (RefOntoUMLPackage)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		allenLinkEClass.getESuperTypes().add(this.getFormalRelation());
		attributeLinkEClass.getESuperTypes().add(this.getSituationTypeAssociation());
		attributeReferenceEClass.getESuperTypes().add(this.getReferableElement());
		classLiteralEClass.getESuperTypes().add(this.getLiteral());
		comparativeRelationEClass.getESuperTypes().add(this.getFormalRelation());
		contextFormalLinkEClass.getESuperTypes().add(this.getFormalRelation());
		dataTypeLiteralEClass.getESuperTypes().add(this.getLiteral());
		formalRelationEClass.getESuperTypes().add(this.getSituationTypeAssociation());
		entityParticipantEClass.getESuperTypes().add(this.getParticipant());
		equalsLinkEClass.getESuperTypes().add(this.getComparativeRelation());
		functionEClass.getESuperTypes().add(this.getReferableElement());
		instantiationLinkEClass.getESuperTypes().add(this.getFormalRelation());
		literalEClass.getESuperTypes().add(this.getNode());
		literalEClass.getESuperTypes().add(this.getSituationTypeElement());
		mediationLinkEClass.getESuperTypes().add(this.getSituationTypeAssociation());
		orderedComparativeLinkEClass.getESuperTypes().add(this.getComparativeRelation());
		participantEClass.getESuperTypes().add(this.getReferableElement());
		referableElementEClass.getESuperTypes().add(this.getNode());
		referableElementEClass.getESuperTypes().add(this.getSituationTypeElement());
		referenceNodeEClass.getESuperTypes().add(this.getNode());
		reflectedParticipantEClass.getESuperTypes().add(this.getNode());
		reflectedParticipantEClass.getESuperTypes().add(this.getSituationTypeElement());
		reflectedReferenceEClass.getESuperTypes().add(this.getNode());
		relatorParticipantEClass.getESuperTypes().add(this.getParticipant());
		situationParticipantEClass.getESuperTypes().add(this.getParticipant());
		situationTypeAssociationEClass.getESuperTypes().add(this.getSituationTypeElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(allenLinkEClass, AllenLink.class, "AllenLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAllenLink_Type(), this.getAllenKind(), "type", null, 1, 1, AllenLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getAllenLink__GetSource(), this.getSituationParticipant(), "getSource", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getAllenLink__GetTarget(), this.getSituationParticipant(), "getTarget", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(attributeLinkEClass, AttributeLink.class, "AttributeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getAttributeLink__GetEntity(), this.getNode(), "getEntity", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getAttributeLink__GetAttribute(), this.getAttributeReference(), "getAttribute", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(attributeReferenceEClass, AttributeReference.class, "AttributeReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttributeReference_Type(), theRefOntoUMLPackage.getProperty(), null, "type", null, 1, 1, AttributeReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getAttributeReference__GetEntity(), this.getNode(), "getEntity", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(classLiteralEClass, ClassLiteral.class, "ClassLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassLiteral_Type(), theRefOntoUMLPackage.getClass_(), null, "type", null, 1, 1, ClassLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(comparativeRelationEClass, ComparativeRelation.class, "ComparativeRelation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(contextFormalLinkEClass, ContextFormalLink.class, "ContextFormalLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContextFormalLink_Parameter(), ecorePackage.getEString(), "parameter", null, 0, 1, ContextFormalLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContextFormalLink_Type(), theRefOntoUMLPackage.getFormalAssociation(), null, "type", null, 1, 1, ContextFormalLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataTypeLiteralEClass, DataTypeLiteral.class, "DataTypeLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataTypeLiteral_Value(), ecorePackage.getEString(), "value", null, 0, 1, DataTypeLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataTypeLiteral_Type(), theRefOntoUMLPackage.getDataType(), null, "type", null, 1, 1, DataTypeLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formalRelationEClass, FormalRelation.class, "FormalRelation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFormalRelation_Negated(), ecorePackage.getEBoolean(), "negated", null, 0, 1, FormalRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityParticipantEClass, EntityParticipant.class, "EntityParticipant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEntityParticipant_Type(), theRefOntoUMLPackage.getObjectClass(), null, "type", null, 1, 1, EntityParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(equalsLinkEClass, EqualsLink.class, "EqualsLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(functionEClass, Function.class, "Function", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFunction_Name(), ecorePackage.getEString(), "name", null, 1, 1, Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunction_Parameters(), this.getFunctionParameter(), null, "parameters", null, 1, -1, Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(functionParameterEClass, FunctionParameter.class, "FunctionParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFunctionParameter_Label(), ecorePackage.getEString(), "label", null, 0, 1, FunctionParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionParameter_Parameter(), this.getNode(), null, "parameter", null, 1, 1, FunctionParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getFunctionParameter__GetFunction(), this.getFunction(), "getFunction", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(instantiationLinkEClass, InstantiationLink.class, "InstantiationLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getInstantiationLink__GetSource(), this.getParticipant(), "getSource", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getInstantiationLink__GetTarget(), this.getClassLiteral(), "getTarget", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(literalEClass, Literal.class, "Literal", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(mediationLinkEClass, MediationLink.class, "MediationLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMediationLink_Type(), theRefOntoUMLPackage.getMediation(), null, "type", null, 1, 1, MediationLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getMediationLink__GetRelator(), this.getRelatorParticipant(), "getRelator", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getMediationLink__GetEntity(), this.getEntityParticipant(), "getEntity", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(nodeEClass, Node.class, "Node", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getNode__SourceRelation(), this.getSituationTypeAssociation(), "sourceRelation", 0, -1, IS_UNIQUE, !IS_ORDERED);

		initEOperation(getNode__TargetRelation(), this.getSituationTypeAssociation(), "targetRelation", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(orderedComparativeLinkEClass, OrderedComparativeLink.class, "OrderedComparativeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOrderedComparativeLink_Type(), this.getComparativeKind(), "type", null, 1, 1, OrderedComparativeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(participantEClass, Participant.class, "Participant", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParticipant_Shareable(), ecorePackage.getEBoolean(), "shareable", "false", 1, 1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipant_Immutable(), ecorePackage.getEBoolean(), "immutable", "true", 1, 1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipant_Min(), ecorePackage.getEInt(), "min", "1", 1, 1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParticipant_Reflection(), this.getReflectedParticipant(), this.getReflectedParticipant_Participant(), "reflection", null, 0, 1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipant_Max(), ecorePackage.getEInt(), "max", "1", 1, 1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referableElementEClass, ReferableElement.class, "ReferableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(referenceNodeEClass, ReferenceNode.class, "ReferenceNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReferenceNode_Label(), ecorePackage.getEString(), "label", null, 0, 1, ReferenceNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReferenceNode_Situation(), this.getSituationParticipant(), this.getSituationParticipant_References(), "situation", null, 1, 1, ReferenceNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReferenceNode_Reference(), this.getReferableElement(), null, "reference", null, 1, 1, ReferenceNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reflectedParticipantEClass, ReflectedParticipant.class, "ReflectedParticipant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReflectedParticipant_Participant(), this.getParticipant(), this.getParticipant_Reflection(), "participant", null, 1, 1, ReflectedParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReflectedParticipant_References(), this.getReflectedReference(), this.getReflectedReference_OwningReflection(), "references", null, 0, -1, ReflectedParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reflectedReferenceEClass, ReflectedReference.class, "ReflectedReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReflectedReference_OwningReflection(), this.getReflectedParticipant(), this.getReflectedParticipant_References(), "owningReflection", null, 1, 1, ReflectedReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReflectedReference_Reference(), this.getReferenceNode(), null, "reference", null, 1, 1, ReflectedReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relatorParticipantEClass, RelatorParticipant.class, "RelatorParticipant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRelatorParticipant_Type(), theRefOntoUMLPackage.getRelator(), null, "type", null, 1, 1, RelatorParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(situationParticipantEClass, SituationParticipant.class, "SituationParticipant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSituationParticipant_Temporality(), this.getTemporalKind(), "temporality", null, 1, 1, SituationParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSituationParticipant_References(), this.getReferenceNode(), this.getReferenceNode_Situation(), "references", null, 0, -1, SituationParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSituationParticipant_Type(), this.getSituationType(), null, "type", null, 1, 1, SituationParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(situationTypeEClass, SituationType.class, "SituationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSituationType_Name(), ecorePackage.getEString(), "name", null, 1, 1, SituationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSituationType_Elements(), this.getSituationTypeElement(), null, "elements", null, 0, -1, SituationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(situationTypeAssociationEClass, SituationTypeAssociation.class, "SituationTypeAssociation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSituationTypeAssociation_Source(), this.getNode(), null, "source", null, 1, 1, SituationTypeAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSituationTypeAssociation_Target(), this.getNode(), null, "target", null, 1, 1, SituationTypeAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(situationTypeElementEClass, SituationTypeElement.class, "SituationTypeElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getSituationTypeElement__GetSituation(), this.getSituationType(), "getSituation", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(smlModelEClass, SMLModel.class, "SMLModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSMLModel_Elements(), this.getSituationType(), null, "elements", null, 0, -1, SMLModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSMLModel_ContextModel(), theRefOntoUMLPackage.getModel(), null, "contextModel", null, 0, 1, SMLModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(allenKindEEnum, AllenKind.class, "AllenKind");
		addEEnumLiteral(allenKindEEnum, AllenKind.BEFORE);
		addEEnumLiteral(allenKindEEnum, AllenKind.AFTER);
		addEEnumLiteral(allenKindEEnum, AllenKind.MEETS);
		addEEnumLiteral(allenKindEEnum, AllenKind.MET_BY);
		addEEnumLiteral(allenKindEEnum, AllenKind.OVERLAPS);
		addEEnumLiteral(allenKindEEnum, AllenKind.OVERLAPPED_BY);
		addEEnumLiteral(allenKindEEnum, AllenKind.FINISHES);
		addEEnumLiteral(allenKindEEnum, AllenKind.FINISHED_BY);
		addEEnumLiteral(allenKindEEnum, AllenKind.INCLUDES);
		addEEnumLiteral(allenKindEEnum, AllenKind.DURING);
		addEEnumLiteral(allenKindEEnum, AllenKind.STARTS);
		addEEnumLiteral(allenKindEEnum, AllenKind.STARTED_BY);
		addEEnumLiteral(allenKindEEnum, AllenKind.COINCIDES);

		initEEnum(comparativeKindEEnum, ComparativeKind.class, "ComparativeKind");
		addEEnumLiteral(comparativeKindEEnum, ComparativeKind.LESS_THAN);
		addEEnumLiteral(comparativeKindEEnum, ComparativeKind.LESS_THAN_EQUAL);
		addEEnumLiteral(comparativeKindEEnum, ComparativeKind.GREATER_THAN);
		addEEnumLiteral(comparativeKindEEnum, ComparativeKind.GREATER_THAN_EQUAL);

		initEEnum(temporalKindEEnum, TemporalKind.class, "TemporalKind");
		addEEnumLiteral(temporalKindEEnum, TemporalKind.PRESENT);
		addEEnumLiteral(temporalKindEEnum, TemporalKind.PAST);
		addEEnumLiteral(temporalKindEEnum, TemporalKind.ANY);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/ocl/examples/OCL
		createOCLAnnotations();
		// Comments
		createCommentsAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/ocl/examples/OCL</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOCLAnnotations() {
		String source = "http://www.eclipse.org/ocl/examples/OCL";	
		addAnnotation
		  (allenLinkEClass, 
		   source, 
		   new String[] {
			 "sourceIsSituation", "self.source.oclIsKindOf(SituationParticipant)",
			 "targetIsSituation", "self.target.oclIsKindOf(SituationParticipant)"
		   });	
		addAnnotation
		  (attributeLinkEClass, 
		   source, 
		   new String[] {
			 "sourceIsParticipant_Reference2", "self.source.oclIsKindOf(Participant) or (self.source.oclIsKindOf(ReferenceNode) and self.source.oclAsType(ReferenceNode).reference.oclIsKindOf(Participant))",
			 "targetIsAttribute", "self.target.oclIsKindOf(AttributeReference)"
		   });	
		addAnnotation
		  (getAttributeLink__GetEntity(), 
		   source, 
		   new String[] {
			 "body", "self.source"
		   });	
		addAnnotation
		  (getAttributeLink__GetAttribute(), 
		   source, 
		   new String[] {
			 "body", "self.target"
		   });	
		addAnnotation
		  (getAttributeReference__GetEntity(), 
		   source, 
		   new String[] {
			 "body", "AttributeLink.allInstances()->select(x | x.getAttribute() = self)->asOrderedSet()->at(1).getEntity()"
		   });	
		addAnnotation
		  (contextFormalLinkEClass, 
		   source, 
		   new String[] {
			 "sameEndsAsType2", "self.type.oclAsType(RefOntoUML::FormalAssociation).memberEnd->exists(x,y | x = self.source.type and y = self.target.type)"
		   });	
		addAnnotation
		  (functionParameterEClass, 
		   source, 
		   new String[] {
			 "sourceIsFunction", "self.source.oclIsKindOf(Function)"
		   });	
		addAnnotation
		  (getFunctionParameter__GetFunction(), 
		   source, 
		   new String[] {
			 "body", "self.source"
		   });	
		addAnnotation
		  (instantiationLinkEClass, 
		   source, 
		   new String[] {
			 "sourceIsParticipant_Reference", "self.source.oclIsKindOf(EntityParticipant) or self.source.oclIsKindOf(RelatorParticipant) or (self.source.oclIsKindOf(ReferenceNode) and (self.source.oclAsType(ReferenceNode).reference.oclIsKindOf(EntityParticipant) or self.source.oclAsType(ReferenceNode).reference.oclIsKindOf(RelatorParticipant)))",
			 "targetIsLiteral", "self.target.oclIsKindOf(Literal) and self.target.oclAsType(Literal).type.oclIsKindOf(RefOntoUML::Class)"
		   });	
		addAnnotation
		  (literalEClass, 
		   source, 
		   new String[] {
			 "typeIsClass_Datatype", "self.type.oclIsKindOf(RefOntoUML::Class) or self.type.oclIsKindOf(RefOntoUML::DataType)"
		   });	
		addAnnotation
		  (mediationLinkEClass, 
		   source, 
		   new String[] {
			 "sourceIsRelator", "self.source.oclIsKindOf(RelatorParticipant)",
			 "targetIsEntity", "self.target.oclIsKindOf(EntityParticipant)",
			 "sameEndsAsType", "self.type.oclAsType(RefOntoUML::Mediation).relator() = self.source.oclAsType(RelatorParticipant).type and\r\nself.type.oclAsType(RefOntoUML::Mediation).mediated() = self.target.oclAsType(EntityParticipant).type"
		   });	
		addAnnotation
		  (getMediationLink__GetRelator(), 
		   source, 
		   new String[] {
			 "body", "self.source"
		   });	
		addAnnotation
		  (getMediationLink__GetEntity(), 
		   source, 
		   new String[] {
			 "body", "self.target"
		   });	
		addAnnotation
		  (getNode__SourceRelation(), 
		   source, 
		   new String[] {
			 "body", "SituationTypeAssociation.allInstances()->select(r | r.source = self)"
		   });	
		addAnnotation
		  (getNode__TargetRelation(), 
		   source, 
		   new String[] {
			 "body", "SituationTypeAssociation.allInstances()->select(r | r.target = self)"
		   });	
		addAnnotation
		  (participantEClass, 
		   source, 
		   new String[] {
			 "maxGreatherThanMin", "if (self.upperBound <> -1) then self.upperBound >= self.lowerBound else self.lowerBound <> -1 endif",
			 "moreThanOneMustReflect", "if (self.lowerBound > 1 or self.upperBound > 1) then self.reflection->notEmpty() else true endif"
		   });	
		addAnnotation
		  (reflectedParticipantEClass, 
		   source, 
		   new String[] {
			 "onlyMultipleReflected", "self.participant.lowerBound > 1 or self.participant.upperBound > 1"
		   });	
		addAnnotation
		  (getSituationTypeElement__GetSituation(), 
		   source, 
		   new String[] {
			 "body", "SituationType.allInstances()->select(x | x.elements->exists(y | y = self))->asOrderedSet()->at(1)"
		   });
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
		  (allenLinkEClass, 
		   source, 
		   new String[] {
			 "constraints", "sourceIsSituation targetIsSituation"
		   });	
		addAnnotation
		  (attributeLinkEClass, 
		   source, 
		   new String[] {
			 "constraints", "sourceIsParticipant_Reference2 targetIsAttribute"
		   });	
		addAnnotation
		  (contextFormalLinkEClass, 
		   source, 
		   new String[] {
			 "constraints", "sameEndsAsType2"
		   });	
		addAnnotation
		  (functionParameterEClass, 
		   source, 
		   new String[] {
			 "constraints", "sourceIsFunction"
		   });	
		addAnnotation
		  (instantiationLinkEClass, 
		   source, 
		   new String[] {
			 "constraints", "sourceIsParticipant_Reference targetIsLiteral"
		   });	
		addAnnotation
		  (literalEClass, 
		   source, 
		   new String[] {
			 "constraints", "typeIsClass_Datatype"
		   });	
		addAnnotation
		  (mediationLinkEClass, 
		   source, 
		   new String[] {
			 "constraints", "sourceIsRelator targetIsEntity sameEndsAsType"
		   });	
		addAnnotation
		  (participantEClass, 
		   source, 
		   new String[] {
			 "constraints", "maxGreatherThanMin moreThanOneMustReflect"
		   });	
		addAnnotation
		  (reflectedParticipantEClass, 
		   source, 
		   new String[] {
			 "constraints", "onlyMultipleReflected"
		   });
	}

	/**
	 * Initializes the annotations for <b>Comments</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createCommentsAnnotations() {
		String source = "Comments";	
		addAnnotation
		  (allenLinkEClass, 
		   source, 
		   new String[] {
			 "sourceIsSituation", "The source of an AllenRelation must be a SituationParticipant",
			 "targetIsSituation", "The target of an AllenRelation must be a SituationParticipant"
		   });	
		addAnnotation
		  (attributeLinkEClass, 
		   source, 
		   new String[] {
			 "sourceIsParticipant_Reference2", "The source of an AttributeLink must be a Participant or a ReferenceNode, which in this case must be a reference to a Participant",
			 "targetIsAttribute", "The target of an AttributeLink must be an Attribute"
		   });	
		addAnnotation
		  (attributeReferenceEClass, 
		   source, 
		   new String[] {
			 "complexAttr_Function", "An attribute that has a type that is not a PrimitiveType must be addressed by a Function"
		   });	
		addAnnotation
		  (getAttributeReference__GetEntity(), 
		   source, 
		   new String[] {
			 "getEntity", "Returns the Node that owns the AttributeReference. The Node can only be a Participant or a NodeReference, which in this case will be a reference also to a Participant."
		   });	
		addAnnotation
		  (contextFormalLinkEClass, 
		   source, 
		   new String[] {
			 "sameEndsAsType2", "A FormalLink must connect the same entities as such its FormalAssociation type does"
		   });	
		addAnnotation
		  (functionParameterEClass, 
		   source, 
		   new String[] {
			 "sourceIsFunction", "The source of a FunctionParameter must be a Function"
		   });	
		addAnnotation
		  (instantiationLinkEClass, 
		   source, 
		   new String[] {
			 "sourceIsParticipant_Reference", "The source of an Instantiation must be an EntityParticipant, a RelatorParticipant or a ReferenceNode, which in this case must be a reference to an EntityParticipant or RelatorParticipant",
			 "targetIsLiteral", "The target of an Instantiation must be a Literal and its type must be a RefOntoUML::Class"
		   });	
		addAnnotation
		  (literalEClass, 
		   source, 
		   new String[] {
			 "typeIsClass_Datatype", "The type of a Literal must be a Class or a DataType"
		   });	
		addAnnotation
		  (mediationLinkEClass, 
		   source, 
		   new String[] {
			 "sourceIsRelator", "The source of a Link must be a RelatorParticipant",
			 "targetIsEntity", "The target of a Link must be an EntityParticipant",
			 "sameEndsAsType", "A Link must connect the same entities as such its Mediation type does"
		   });	
		addAnnotation
		  (getNode__SourceRelation(), 
		   source, 
		   new String[] {
			 "sourceRelation", "Gets all associations in which the node element is source"
		   });	
		addAnnotation
		  (getNode__TargetRelation(), 
		   source, 
		   new String[] {
			 "targetRelation", "Gets all associations in which the node element is target"
		   });	
		addAnnotation
		  (participantEClass, 
		   source, 
		   new String[] {
			 "maxGreatherThanMin", "The maximum instances number of a Participant must be greather than or equal the minimum",
			 "moreThanOneMustReflect", "A Participant that has a minimum greather than 1 or a maximum greather than 1 must be reflected, so that a constraint can be applied to the extra instances"
		   });	
		addAnnotation
		  (reflectedParticipantEClass, 
		   source, 
		   new String[] {
			 "onlyMultipleReflected", "Only Participants with minimum or maximum greather than 1 can be reflected"
		   });	
		addAnnotation
		  (getSituationTypeElement__GetSituation(), 
		   source, 
		   new String[] {
			 "situation", "Gets the SituationType that contains the SituationTypeElement"
		   });
	}

} //Sml2PackageImpl
