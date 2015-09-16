/**
 */
package sml2.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import sml2.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Sml2FactoryImpl extends EFactoryImpl implements Sml2Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Sml2Factory init() {
		try {
			Sml2Factory theSml2Factory = (Sml2Factory)EPackage.Registry.INSTANCE.getEFactory(Sml2Package.eNS_URI);
			if (theSml2Factory != null) {
				return theSml2Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Sml2FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sml2FactoryImpl() {
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
			case Sml2Package.ALLEN_LINK: return createAllenLink();
			case Sml2Package.ATTRIBUTE_LINK: return createAttributeLink();
			case Sml2Package.ATTRIBUTE_REFERENCE: return createAttributeReference();
			case Sml2Package.CHARACTERIZATION_LINK: return createCharacterizationLink();
			case Sml2Package.CONTEXT_FORMAL_LINK: return createContextFormalLink();
			case Sml2Package.ENTITY_PARTICIPANT: return createEntityParticipant();
			case Sml2Package.EQUALS_LINK: return createEqualsLink();
			case Sml2Package.FUNCTION: return createFunction();
			case Sml2Package.FUNCTION_PARAMETER: return createFunctionParameter();
			case Sml2Package.INSTANTIATION_LINK: return createInstantiationLink();
			case Sml2Package.MEDIATION_LINK: return createMediationLink();
			case Sml2Package.MODE_REFERENCE: return createModeReference();
			case Sml2Package.ORDERED_COMPARATIVE_LINK: return createOrderedComparativeLink();
			case Sml2Package.QUALITY_LITERAL: return createQualityLiteral();
			case Sml2Package.REFERENCE_NODE: return createReferenceNode();
			case Sml2Package.RELATOR_PARTICIPANT: return createRelatorParticipant();
			case Sml2Package.SELF_REFERENCE: return createSelfReference();
			case Sml2Package.SITUATION_PARTICIPANT: return createSituationParticipant();
			case Sml2Package.SITUATION_TYPE: return createSituationType();
			case Sml2Package.SML_MODEL: return createSMLModel();
			case Sml2Package.TYPE_LITERAL: return createTypeLiteral();
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
			case Sml2Package.ALLEN_KIND:
				return createAllenKindFromString(eDataType, initialValue);
			case Sml2Package.COMPARATIVE_KIND:
				return createComparativeKindFromString(eDataType, initialValue);
			case Sml2Package.TEMPORAL_KIND:
				return createTemporalKindFromString(eDataType, initialValue);
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
			case Sml2Package.ALLEN_KIND:
				return convertAllenKindToString(eDataType, instanceValue);
			case Sml2Package.COMPARATIVE_KIND:
				return convertComparativeKindToString(eDataType, instanceValue);
			case Sml2Package.TEMPORAL_KIND:
				return convertTemporalKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllenLink createAllenLink() {
		AllenLinkImpl allenLink = new AllenLinkImpl();
		return allenLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeLink createAttributeLink() {
		AttributeLinkImpl attributeLink = new AttributeLinkImpl();
		return attributeLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeReference createAttributeReference() {
		AttributeReferenceImpl attributeReference = new AttributeReferenceImpl();
		return attributeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacterizationLink createCharacterizationLink() {
		CharacterizationLinkImpl characterizationLink = new CharacterizationLinkImpl();
		return characterizationLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContextFormalLink createContextFormalLink() {
		ContextFormalLinkImpl contextFormalLink = new ContextFormalLinkImpl();
		return contextFormalLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityParticipant createEntityParticipant() {
		EntityParticipantImpl entityParticipant = new EntityParticipantImpl();
		return entityParticipant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EqualsLink createEqualsLink() {
		EqualsLinkImpl equalsLink = new EqualsLinkImpl();
		return equalsLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function createFunction() {
		FunctionImpl function = new FunctionImpl();
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionParameter createFunctionParameter() {
		FunctionParameterImpl functionParameter = new FunctionParameterImpl();
		return functionParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstantiationLink createInstantiationLink() {
		InstantiationLinkImpl instantiationLink = new InstantiationLinkImpl();
		return instantiationLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MediationLink createMediationLink() {
		MediationLinkImpl mediationLink = new MediationLinkImpl();
		return mediationLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModeReference createModeReference() {
		ModeReferenceImpl modeReference = new ModeReferenceImpl();
		return modeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderedComparativeLink createOrderedComparativeLink() {
		OrderedComparativeLinkImpl orderedComparativeLink = new OrderedComparativeLinkImpl();
		return orderedComparativeLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualityLiteral createQualityLiteral() {
		QualityLiteralImpl qualityLiteral = new QualityLiteralImpl();
		return qualityLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceNode createReferenceNode() {
		ReferenceNodeImpl referenceNode = new ReferenceNodeImpl();
		return referenceNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelatorParticipant createRelatorParticipant() {
		RelatorParticipantImpl relatorParticipant = new RelatorParticipantImpl();
		return relatorParticipant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelfReference createSelfReference() {
		SelfReferenceImpl selfReference = new SelfReferenceImpl();
		return selfReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SituationParticipant createSituationParticipant() {
		SituationParticipantImpl situationParticipant = new SituationParticipantImpl();
		return situationParticipant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SituationType createSituationType() {
		SituationTypeImpl situationType = new SituationTypeImpl();
		return situationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SMLModel createSMLModel() {
		SMLModelImpl smlModel = new SMLModelImpl();
		return smlModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeLiteral createTypeLiteral() {
		TypeLiteralImpl typeLiteral = new TypeLiteralImpl();
		return typeLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllenKind createAllenKindFromString(EDataType eDataType, String initialValue) {
		AllenKind result = AllenKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAllenKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemporalKind createTemporalKindFromString(EDataType eDataType, String initialValue) {
		TemporalKind result = TemporalKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTemporalKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComparativeKind createComparativeKindFromString(EDataType eDataType, String initialValue) {
		ComparativeKind result = ComparativeKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertComparativeKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sml2Package getSml2Package() {
		return (Sml2Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Sml2Package getPackage() {
		return Sml2Package.eINSTANCE;
	}

} //Sml2FactoryImpl
