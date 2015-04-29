/**
 */
package sml2.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import sml2.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see sml2.Sml2Package
 * @generated
 */
public class Sml2Switch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Sml2Package modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sml2Switch() {
		if (modelPackage == null) {
			modelPackage = Sml2Package.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case Sml2Package.SML_MODEL: {
				SMLModel smlModel = (SMLModel)theEObject;
				T result = caseSMLModel(smlModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.SITUATION_TYPE: {
				SituationType situationType = (SituationType)theEObject;
				T result = caseSituationType(situationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.SITUATION_TYPE_BLOCK: {
				SituationTypeBlock situationTypeBlock = (SituationTypeBlock)theEObject;
				T result = caseSituationTypeBlock(situationTypeBlock);
				if (result == null) result = caseNode(situationTypeBlock);
				if (result == null) result = caseSituationTypeElement(situationTypeBlock);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.SITUATION_TYPE_ELEMENT: {
				SituationTypeElement situationTypeElement = (SituationTypeElement)theEObject;
				T result = caseSituationTypeElement(situationTypeElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.NODE: {
				Node node = (Node)theEObject;
				T result = caseNode(node);
				if (result == null) result = caseSituationTypeElement(node);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.EXPORTABLE_NODE: {
				ExportableNode exportableNode = (ExportableNode)theEObject;
				T result = caseExportableNode(exportableNode);
				if (result == null) result = caseNode(exportableNode);
				if (result == null) result = caseSituationTypeElement(exportableNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.ENTITY_PARTICIPANT: {
				EntityParticipant entityParticipant = (EntityParticipant)theEObject;
				T result = caseEntityParticipant(entityParticipant);
				if (result == null) result = caseParticipant(entityParticipant);
				if (result == null) result = caseExportableNode(entityParticipant);
				if (result == null) result = caseNode(entityParticipant);
				if (result == null) result = caseSituationTypeElement(entityParticipant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.RELATOR_PARTICIPANT: {
				RelatorParticipant relatorParticipant = (RelatorParticipant)theEObject;
				T result = caseRelatorParticipant(relatorParticipant);
				if (result == null) result = caseParticipant(relatorParticipant);
				if (result == null) result = caseExportableNode(relatorParticipant);
				if (result == null) result = caseNode(relatorParticipant);
				if (result == null) result = caseSituationTypeElement(relatorParticipant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.LINK: {
				Link link = (Link)theEObject;
				T result = caseLink(link);
				if (result == null) result = caseSituationTypeElement(link);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.SITUATION_TYPE_PARAMETER: {
				SituationTypeParameter situationTypeParameter = (SituationTypeParameter)theEObject;
				T result = caseSituationTypeParameter(situationTypeParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.ATTRIBUTE_REFERENCE: {
				AttributeReference attributeReference = (AttributeReference)theEObject;
				T result = caseAttributeReference(attributeReference);
				if (result == null) result = caseExportableNode(attributeReference);
				if (result == null) result = caseNode(attributeReference);
				if (result == null) result = caseSituationTypeElement(attributeReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.COMPARATIVE_RELATION: {
				ComparativeRelation comparativeRelation = (ComparativeRelation)theEObject;
				T result = caseComparativeRelation(comparativeRelation);
				if (result == null) result = caseSituationTypeElement(comparativeRelation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.LITERAL: {
				Literal literal = (Literal)theEObject;
				T result = caseLiteral(literal);
				if (result == null) result = caseNode(literal);
				if (result == null) result = caseSituationTypeElement(literal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.PARTICIPANT: {
				Participant participant = (Participant)theEObject;
				T result = caseParticipant(participant);
				if (result == null) result = caseExportableNode(participant);
				if (result == null) result = caseNode(participant);
				if (result == null) result = caseSituationTypeElement(participant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.SITUATION_PARTICIPANT: {
				SituationParticipant situationParticipant = (SituationParticipant)theEObject;
				T result = caseSituationParticipant(situationParticipant);
				if (result == null) result = caseParticipant(situationParticipant);
				if (result == null) result = caseExportableNode(situationParticipant);
				if (result == null) result = caseNode(situationParticipant);
				if (result == null) result = caseSituationTypeElement(situationParticipant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.SITUATION_PARAMETER_REFERENCE: {
				SituationParameterReference situationParameterReference = (SituationParameterReference)theEObject;
				T result = caseSituationParameterReference(situationParameterReference);
				if (result == null) result = caseNode(situationParameterReference);
				if (result == null) result = caseSituationTypeElement(situationParameterReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.EXISTS_SITUATION: {
				ExistsSituation existsSituation = (ExistsSituation)theEObject;
				T result = caseExistsSituation(existsSituation);
				if (result == null) result = caseSituationParticipant(existsSituation);
				if (result == null) result = caseParticipant(existsSituation);
				if (result == null) result = caseExportableNode(existsSituation);
				if (result == null) result = caseNode(existsSituation);
				if (result == null) result = caseSituationTypeElement(existsSituation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.FUNCTION: {
				Function function = (Function)theEObject;
				T result = caseFunction(function);
				if (result == null) result = caseExportableNode(function);
				if (result == null) result = caseNode(function);
				if (result == null) result = caseSituationTypeElement(function);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.PARAMETER: {
				Parameter parameter = (Parameter)theEObject;
				T result = caseParameter(parameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SML Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SML Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSMLModel(SMLModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Situation Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Situation Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSituationType(SituationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Situation Type Block</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Situation Type Block</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSituationTypeBlock(SituationTypeBlock object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Situation Type Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Situation Type Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSituationTypeElement(SituationTypeElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNode(Node object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exportable Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exportable Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExportableNode(ExportableNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity Participant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity Participant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntityParticipant(EntityParticipant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relator Participant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relator Participant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelatorParticipant(RelatorParticipant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLink(Link object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Situation Type Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Situation Type Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSituationTypeParameter(SituationTypeParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeReference(AttributeReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comparative Relation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comparative Relation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComparativeRelation(ComparativeRelation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLiteral(Literal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Participant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Participant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParticipant(Participant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Situation Participant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Situation Participant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSituationParticipant(SituationParticipant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Situation Parameter Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Situation Parameter Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSituationParameterReference(SituationParameterReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exists Situation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exists Situation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExistsSituation(ExistsSituation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunction(Function object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameter(Parameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //Sml2Switch
