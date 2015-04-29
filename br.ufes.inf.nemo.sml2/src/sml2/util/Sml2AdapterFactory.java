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
			public Adapter caseSMLModel(SMLModel object) {
				return createSMLModelAdapter();
			}
			@Override
			public Adapter caseSituationType(SituationType object) {
				return createSituationTypeAdapter();
			}
			@Override
			public Adapter caseSituationTypeBlock(SituationTypeBlock object) {
				return createSituationTypeBlockAdapter();
			}
			@Override
			public Adapter caseSituationTypeElement(SituationTypeElement object) {
				return createSituationTypeElementAdapter();
			}
			@Override
			public Adapter caseNode(Node object) {
				return createNodeAdapter();
			}
			@Override
			public Adapter caseExportableNode(ExportableNode object) {
				return createExportableNodeAdapter();
			}
			@Override
			public Adapter caseEntityParticipant(EntityParticipant object) {
				return createEntityParticipantAdapter();
			}
			@Override
			public Adapter caseRelatorParticipant(RelatorParticipant object) {
				return createRelatorParticipantAdapter();
			}
			@Override
			public Adapter caseLink(Link object) {
				return createLinkAdapter();
			}
			@Override
			public Adapter caseSituationTypeParameter(SituationTypeParameter object) {
				return createSituationTypeParameterAdapter();
			}
			@Override
			public Adapter caseAttributeReference(AttributeReference object) {
				return createAttributeReferenceAdapter();
			}
			@Override
			public Adapter caseComparativeRelation(ComparativeRelation object) {
				return createComparativeRelationAdapter();
			}
			@Override
			public Adapter caseLiteral(Literal object) {
				return createLiteralAdapter();
			}
			@Override
			public Adapter caseParticipant(Participant object) {
				return createParticipantAdapter();
			}
			@Override
			public Adapter caseSituationParticipant(SituationParticipant object) {
				return createSituationParticipantAdapter();
			}
			@Override
			public Adapter caseSituationParameterReference(SituationParameterReference object) {
				return createSituationParameterReferenceAdapter();
			}
			@Override
			public Adapter caseExistsSituation(ExistsSituation object) {
				return createExistsSituationAdapter();
			}
			@Override
			public Adapter caseFunction(Function object) {
				return createFunctionAdapter();
			}
			@Override
			public Adapter caseParameter(Parameter object) {
				return createParameterAdapter();
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
	 * Creates a new adapter for an object of class '{@link sml2.SituationTypeBlock <em>Situation Type Block</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.SituationTypeBlock
	 * @generated
	 */
	public Adapter createSituationTypeBlockAdapter() {
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
	 * Creates a new adapter for an object of class '{@link sml2.ExportableNode <em>Exportable Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.ExportableNode
	 * @generated
	 */
	public Adapter createExportableNodeAdapter() {
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
	 * Creates a new adapter for an object of class '{@link sml2.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.Link
	 * @generated
	 */
	public Adapter createLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.SituationTypeParameter <em>Situation Type Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.SituationTypeParameter
	 * @generated
	 */
	public Adapter createSituationTypeParameterAdapter() {
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
	 * Creates a new adapter for an object of class '{@link sml2.SituationParameterReference <em>Situation Parameter Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.SituationParameterReference
	 * @generated
	 */
	public Adapter createSituationParameterReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link sml2.ExistsSituation <em>Exists Situation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.ExistsSituation
	 * @generated
	 */
	public Adapter createExistsSituationAdapter() {
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
	 * Creates a new adapter for an object of class '{@link sml2.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see sml2.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter() {
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
