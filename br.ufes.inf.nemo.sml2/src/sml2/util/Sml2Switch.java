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
			case Sml2Package.ALLEN_LINK: {
				AllenLink allenLink = (AllenLink)theEObject;
				T result = caseAllenLink(allenLink);
				if (result == null) result = caseFormalRelation(allenLink);
				if (result == null) result = caseSituationTypeAssociation(allenLink);
				if (result == null) result = caseSituationTypeElement(allenLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.ATTRIBUTE_LINK: {
				AttributeLink attributeLink = (AttributeLink)theEObject;
				T result = caseAttributeLink(attributeLink);
				if (result == null) result = caseSituationTypeAssociation(attributeLink);
				if (result == null) result = caseSituationTypeElement(attributeLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.ATTRIBUTE_REFERENCE: {
				AttributeReference attributeReference = (AttributeReference)theEObject;
				T result = caseAttributeReference(attributeReference);
				if (result == null) result = caseReferableElement(attributeReference);
				if (result == null) result = caseNode(attributeReference);
				if (result == null) result = caseSituationTypeElement(attributeReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.CHARACTERIZATION_LINK: {
				CharacterizationLink characterizationLink = (CharacterizationLink)theEObject;
				T result = caseCharacterizationLink(characterizationLink);
				if (result == null) result = caseSituationTypeAssociation(characterizationLink);
				if (result == null) result = caseSituationTypeElement(characterizationLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.COMPARATIVE_RELATION: {
				ComparativeRelation comparativeRelation = (ComparativeRelation)theEObject;
				T result = caseComparativeRelation(comparativeRelation);
				if (result == null) result = caseFormalRelation(comparativeRelation);
				if (result == null) result = caseSituationTypeAssociation(comparativeRelation);
				if (result == null) result = caseSituationTypeElement(comparativeRelation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.CONTEXT_FORMAL_LINK: {
				ContextFormalLink contextFormalLink = (ContextFormalLink)theEObject;
				T result = caseContextFormalLink(contextFormalLink);
				if (result == null) result = caseFormalRelation(contextFormalLink);
				if (result == null) result = caseSituationTypeAssociation(contextFormalLink);
				if (result == null) result = caseSituationTypeElement(contextFormalLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.FORMAL_RELATION: {
				FormalRelation formalRelation = (FormalRelation)theEObject;
				T result = caseFormalRelation(formalRelation);
				if (result == null) result = caseSituationTypeAssociation(formalRelation);
				if (result == null) result = caseSituationTypeElement(formalRelation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.ENTITY_PARTICIPANT: {
				EntityParticipant entityParticipant = (EntityParticipant)theEObject;
				T result = caseEntityParticipant(entityParticipant);
				if (result == null) result = caseParticipant(entityParticipant);
				if (result == null) result = caseReferableElement(entityParticipant);
				if (result == null) result = caseNode(entityParticipant);
				if (result == null) result = caseSituationTypeElement(entityParticipant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.EQUALS_LINK: {
				EqualsLink equalsLink = (EqualsLink)theEObject;
				T result = caseEqualsLink(equalsLink);
				if (result == null) result = caseComparativeRelation(equalsLink);
				if (result == null) result = caseFormalRelation(equalsLink);
				if (result == null) result = caseSituationTypeAssociation(equalsLink);
				if (result == null) result = caseSituationTypeElement(equalsLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.FUNCTION: {
				Function function = (Function)theEObject;
				T result = caseFunction(function);
				if (result == null) result = caseReferableElement(function);
				if (result == null) result = caseNode(function);
				if (result == null) result = caseSituationTypeElement(function);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.FUNCTION_PARAMETER: {
				FunctionParameter functionParameter = (FunctionParameter)theEObject;
				T result = caseFunctionParameter(functionParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.INSTANTIATION_LINK: {
				InstantiationLink instantiationLink = (InstantiationLink)theEObject;
				T result = caseInstantiationLink(instantiationLink);
				if (result == null) result = caseFormalRelation(instantiationLink);
				if (result == null) result = caseSituationTypeAssociation(instantiationLink);
				if (result == null) result = caseSituationTypeElement(instantiationLink);
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
			case Sml2Package.MEDIATION_LINK: {
				MediationLink mediationLink = (MediationLink)theEObject;
				T result = caseMediationLink(mediationLink);
				if (result == null) result = caseSituationTypeAssociation(mediationLink);
				if (result == null) result = caseSituationTypeElement(mediationLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.MODE_REFERENCE: {
				ModeReference modeReference = (ModeReference)theEObject;
				T result = caseModeReference(modeReference);
				if (result == null) result = caseReferableElement(modeReference);
				if (result == null) result = caseNode(modeReference);
				if (result == null) result = caseSituationTypeElement(modeReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.NODE: {
				Node node = (Node)theEObject;
				T result = caseNode(node);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.ORDERED_COMPARATIVE_LINK: {
				OrderedComparativeLink orderedComparativeLink = (OrderedComparativeLink)theEObject;
				T result = caseOrderedComparativeLink(orderedComparativeLink);
				if (result == null) result = caseComparativeRelation(orderedComparativeLink);
				if (result == null) result = caseFormalRelation(orderedComparativeLink);
				if (result == null) result = caseSituationTypeAssociation(orderedComparativeLink);
				if (result == null) result = caseSituationTypeElement(orderedComparativeLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.PARTICIPANT: {
				Participant participant = (Participant)theEObject;
				T result = caseParticipant(participant);
				if (result == null) result = caseReferableElement(participant);
				if (result == null) result = caseNode(participant);
				if (result == null) result = caseSituationTypeElement(participant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.QUALITY_LITERAL: {
				QualityLiteral qualityLiteral = (QualityLiteral)theEObject;
				T result = caseQualityLiteral(qualityLiteral);
				if (result == null) result = caseLiteral(qualityLiteral);
				if (result == null) result = caseNode(qualityLiteral);
				if (result == null) result = caseSituationTypeElement(qualityLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.REFERABLE_ELEMENT: {
				ReferableElement referableElement = (ReferableElement)theEObject;
				T result = caseReferableElement(referableElement);
				if (result == null) result = caseNode(referableElement);
				if (result == null) result = caseSituationTypeElement(referableElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.REFERENCE_NODE: {
				ReferenceNode referenceNode = (ReferenceNode)theEObject;
				T result = caseReferenceNode(referenceNode);
				if (result == null) result = caseNode(referenceNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.RELATOR_PARTICIPANT: {
				RelatorParticipant relatorParticipant = (RelatorParticipant)theEObject;
				T result = caseRelatorParticipant(relatorParticipant);
				if (result == null) result = caseParticipant(relatorParticipant);
				if (result == null) result = caseReferableElement(relatorParticipant);
				if (result == null) result = caseNode(relatorParticipant);
				if (result == null) result = caseSituationTypeElement(relatorParticipant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.SELF_REFERENCE: {
				SelfReference selfReference = (SelfReference)theEObject;
				T result = caseSelfReference(selfReference);
				if (result == null) result = caseSituationParticipant(selfReference);
				if (result == null) result = caseParticipant(selfReference);
				if (result == null) result = caseReferableElement(selfReference);
				if (result == null) result = caseNode(selfReference);
				if (result == null) result = caseSituationTypeElement(selfReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.SITUATION_PARTICIPANT: {
				SituationParticipant situationParticipant = (SituationParticipant)theEObject;
				T result = caseSituationParticipant(situationParticipant);
				if (result == null) result = caseParticipant(situationParticipant);
				if (result == null) result = caseReferableElement(situationParticipant);
				if (result == null) result = caseNode(situationParticipant);
				if (result == null) result = caseSituationTypeElement(situationParticipant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.SITUATION_TYPE: {
				SituationType situationType = (SituationType)theEObject;
				T result = caseSituationType(situationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.SITUATION_TYPE_ASSOCIATION: {
				SituationTypeAssociation situationTypeAssociation = (SituationTypeAssociation)theEObject;
				T result = caseSituationTypeAssociation(situationTypeAssociation);
				if (result == null) result = caseSituationTypeElement(situationTypeAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.SITUATION_TYPE_ELEMENT: {
				SituationTypeElement situationTypeElement = (SituationTypeElement)theEObject;
				T result = caseSituationTypeElement(situationTypeElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.SML_MODEL: {
				SMLModel smlModel = (SMLModel)theEObject;
				T result = caseSMLModel(smlModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Sml2Package.TYPE_LITERAL: {
				TypeLiteral typeLiteral = (TypeLiteral)theEObject;
				T result = caseTypeLiteral(typeLiteral);
				if (result == null) result = caseLiteral(typeLiteral);
				if (result == null) result = caseNode(typeLiteral);
				if (result == null) result = caseSituationTypeElement(typeLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Allen Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Allen Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAllenLink(AllenLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeLink(AttributeLink object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Characterization Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Characterization Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCharacterizationLink(CharacterizationLink object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Context Formal Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Context Formal Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContextFormalLink(ContextFormalLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formal Relation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formal Relation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormalRelation(FormalRelation object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Equals Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Equals Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEqualsLink(EqualsLink object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Function Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunctionParameter(FunctionParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instantiation Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instantiation Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstantiationLink(InstantiationLink object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Mediation Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mediation Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMediationLink(MediationLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mode Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mode Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModeReference(ModeReference object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Ordered Comparative Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordered Comparative Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrderedComparativeLink(OrderedComparativeLink object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Quality Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quality Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQualityLiteral(QualityLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Referable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Referable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferableElement(ReferableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceNode(ReferenceNode object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Self Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Self Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSelfReference(SelfReference object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Situation Type Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Situation Type Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSituationTypeAssociation(SituationTypeAssociation object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Type Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeLiteral(TypeLiteral object) {
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
