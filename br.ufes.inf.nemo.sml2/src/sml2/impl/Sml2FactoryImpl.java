/**
 */
package sml2.impl;

import org.eclipse.emf.ecore.EClass;
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
			case Sml2Package.SML_MODEL: return createSMLModel();
			case Sml2Package.SITUATION_TYPE: return createSituationType();
			case Sml2Package.SITUATION_TYPE_BLOCK: return createSituationTypeBlock();
			case Sml2Package.ENTITY_PARTICIPANT: return createEntityParticipant();
			case Sml2Package.RELATOR_PARTICIPANT: return createRelatorParticipant();
			case Sml2Package.LINK: return createLink();
			case Sml2Package.SITUATION_TYPE_PARAMETER: return createSituationTypeParameter();
			case Sml2Package.ATTRIBUTE_REFERENCE: return createAttributeReference();
			case Sml2Package.COMPARATIVE_RELATION: return createComparativeRelation();
			case Sml2Package.LITERAL: return createLiteral();
			case Sml2Package.SITUATION_PARTICIPANT: return createSituationParticipant();
			case Sml2Package.SITUATION_PARAMETER_REFERENCE: return createSituationParameterReference();
			case Sml2Package.EXISTS_SITUATION: return createExistsSituation();
			case Sml2Package.FUNCTION: return createFunction();
			case Sml2Package.PARAMETER: return createParameter();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
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
	public SituationType createSituationType() {
		SituationTypeImpl situationType = new SituationTypeImpl();
		return situationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SituationTypeBlock createSituationTypeBlock() {
		SituationTypeBlockImpl situationTypeBlock = new SituationTypeBlockImpl();
		return situationTypeBlock;
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
	public RelatorParticipant createRelatorParticipant() {
		RelatorParticipantImpl relatorParticipant = new RelatorParticipantImpl();
		return relatorParticipant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Link createLink() {
		LinkImpl link = new LinkImpl();
		return link;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SituationTypeParameter createSituationTypeParameter() {
		SituationTypeParameterImpl situationTypeParameter = new SituationTypeParameterImpl();
		return situationTypeParameter;
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
	public ComparativeRelation createComparativeRelation() {
		ComparativeRelationImpl comparativeRelation = new ComparativeRelationImpl();
		return comparativeRelation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Literal createLiteral() {
		LiteralImpl literal = new LiteralImpl();
		return literal;
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
	public SituationParameterReference createSituationParameterReference() {
		SituationParameterReferenceImpl situationParameterReference = new SituationParameterReferenceImpl();
		return situationParameterReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExistsSituation createExistsSituation() {
		ExistsSituationImpl existsSituation = new ExistsSituationImpl();
		return existsSituation;
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
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		return parameter;
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
