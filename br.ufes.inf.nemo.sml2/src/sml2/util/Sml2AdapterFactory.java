/**
 */
package sml2.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import sml2.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see sml2.Sml2Package
 * @generated
 */
public class Sml2AdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Sml2Package modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sml2AdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Sml2Package.eINSTANCE;
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
	protected Sml2Switch<Adapter> modelSwitch =
		new Sml2Switch<Adapter>() {
			@Override
			public Adapter caseAllenLink(AllenLink object) {
				return createAllenLinkAdapter();
			}
			@Override
			public Adapter caseAttributeLink(AttributeLink object) {
				return createAttributeLinkAdapter();
			}
			@Override
			public Adapter caseAttributeReference(AttributeReference object) {
				return createAttributeReferenceAdapter();
			}
			@Override
			public Adapter caseCharacterizationLink(CharacterizationLink object) {
				return createCharacterizationLinkAdapter();
			}
			@Override
			public Adapter caseComparativeRelation(ComparativeRelation object) {
				return createComparativeRelationAdapter();
			}
			@Override
			public Adapter caseContextFormalLink(ContextFormalLink object) {
				return createContextFormalLinkAdapter();
			}
			@Override
			public Adapter caseFormalRelation(FormalRelation object) {
				return createFormalRelationAdapter();
			}
			@Override
			public Adapter caseEntityParticipant(EntityParticipant object) {
				return createEntityParticipantAdapter();
			}
			@Override
			public Adapter caseEqualsLink(EqualsLink object) {
				return createEqualsLinkAdapter();
			}
			@Override
			public Adapter caseFunction(Function object) {
				return createFunctionAdapter();
			}
			@Override
			public Adapter caseFunctionParameter(FunctionParameter object) {
				return createFunctionParameterAdapter();
			}
			@Override
			public Adapter caseInstantiationLink(InstantiationLink object) {
				return createInstantiationLinkAdapter();
			}
			@Override
			public Adapter caseLiteral(Literal object) {
				return createLiteralAdapter();
			}
			@Override
			public Adapter caseMediationLink(MediationLink object) {
				return createMediationLinkAdapter();
			}
			@Override
			public Adapter caseModeReference(ModeReference object) {
				return createModeReferenceAdapter();
			}
			@Override
			public Adapter caseNode(Node object) {
				return createNodeAdapter();
			}
			@Override
			public Adapter caseOrderedComparativeLink(OrderedComparativeLink object) {
				return createOrderedComparativeLinkAdapter();
			}
			@Override
			public Adapter caseParticipant(Participant object) {
				return createParticipantAdapter();
			}
			@Override
			public Adapter caseQualityLiteral(QualityLiteral object) {
				return createQualityLiteralAdapter();
			}
			@Override
			public Adapter caseReferableElement(ReferableElement object) {
				return createReferableElementAdapter();
			}
			@Override
			public Adapter caseReferenceNode(ReferenceNode object) {
				return createReferenceNodeAdapter();
			}
			@Override
			public Adapter caseRelatorParticipant(RelatorParticipant object) {
				return createRelatorParticipantAdapter();
			}
			@Override
			public Adapter caseSelfReference(SelfReference object) {
				return createSelfReferenceAdapter();
			}
			@Override
			public Adapter caseSituationParticipant(SituationParticipant object) {
				return createSituationParticipantAdapter();
			}
			@Override
			public Adapter caseSituationType(SituationType object) {
				return createSituationTypeAdapter();
			}
			@Override
			public Adapter caseSituationTypeAssociation(SituationTypeAssociation object) {
				return createSituationTypeAssociationAdapter();
			}
			@Override
			public Adapter caseSituationTypeElement(SituationTypeElement object) {
				return createSituationTypeElementAdapter();
			}
			@Override
			public Adapter caseSMLModel(SMLModel object) {
				return createSMLModelAdapter();
			}
			@Override
			public Adapter caseTypeLiteral(TypeLiteral object) {
				return createTypeLiteralAdapter();
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
	 * Creates a new adapter for an object of class '{@link sml2.AllenLink <em>Allen Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.AllenLink
	 * @generated
	 */
	public Adapter createAllenLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.AttributeLink <em>Attribute Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.AttributeLink
	 * @generated
	 */
	public Adapter createAttributeLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.AttributeReference <em>Attribute Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.AttributeReference
	 * @generated
	 */
	public Adapter createAttributeReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.CharacterizationLink <em>Characterization Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.CharacterizationLink
	 * @generated
	 */
	public Adapter createCharacterizationLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.ComparativeRelation <em>Comparative Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.ComparativeRelation
	 * @generated
	 */
	public Adapter createComparativeRelationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.ContextFormalLink <em>Context Formal Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.ContextFormalLink
	 * @generated
	 */
	public Adapter createContextFormalLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.FormalRelation <em>Formal Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.FormalRelation
	 * @generated
	 */
	public Adapter createFormalRelationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.EntityParticipant <em>Entity Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.EntityParticipant
	 * @generated
	 */
	public Adapter createEntityParticipantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.EqualsLink <em>Equals Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.EqualsLink
	 * @generated
	 */
	public Adapter createEqualsLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.Function
	 * @generated
	 */
	public Adapter createFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.FunctionParameter <em>Function Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.FunctionParameter
	 * @generated
	 */
	public Adapter createFunctionParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.InstantiationLink <em>Instantiation Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.InstantiationLink
	 * @generated
	 */
	public Adapter createInstantiationLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.Literal <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.Literal
	 * @generated
	 */
	public Adapter createLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.MediationLink <em>Mediation Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.MediationLink
	 * @generated
	 */
	public Adapter createMediationLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.ModeReference <em>Mode Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.ModeReference
	 * @generated
	 */
	public Adapter createModeReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.Node
	 * @generated
	 */
	public Adapter createNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.OrderedComparativeLink <em>Ordered Comparative Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.OrderedComparativeLink
	 * @generated
	 */
	public Adapter createOrderedComparativeLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.Participant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.Participant
	 * @generated
	 */
	public Adapter createParticipantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.QualityLiteral <em>Quality Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.QualityLiteral
	 * @generated
	 */
	public Adapter createQualityLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.ReferableElement <em>Referable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.ReferableElement
	 * @generated
	 */
	public Adapter createReferableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.ReferenceNode <em>Reference Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.ReferenceNode
	 * @generated
	 */
	public Adapter createReferenceNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.RelatorParticipant <em>Relator Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.RelatorParticipant
	 * @generated
	 */
	public Adapter createRelatorParticipantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.SelfReference <em>Self Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.SelfReference
	 * @generated
	 */
	public Adapter createSelfReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.SituationParticipant <em>Situation Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.SituationParticipant
	 * @generated
	 */
	public Adapter createSituationParticipantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.SituationType <em>Situation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.SituationType
	 * @generated
	 */
	public Adapter createSituationTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.SituationTypeAssociation <em>Situation Type Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.SituationTypeAssociation
	 * @generated
	 */
	public Adapter createSituationTypeAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.SituationTypeElement <em>Situation Type Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.SituationTypeElement
	 * @generated
	 */
	public Adapter createSituationTypeElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.SMLModel <em>SML Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.SMLModel
	 * @generated
	 */
	public Adapter createSMLModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.TypeLiteral <em>Type Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.TypeLiteral
	 * @generated
	 */
	public Adapter createTypeLiteralAdapter() {
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

} //Sml2AdapterFactory
