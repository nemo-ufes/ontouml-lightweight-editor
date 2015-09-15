/**
 */
package sml2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Situation Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.SituationParticipant#getTemporality <em>Temporality</em>}</li>
 *   <li>{@link sml2.SituationParticipant#getReferences <em>References</em>}</li>
 *   <li>{@link sml2.SituationParticipant#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getSituationParticipant()
 * @model
 * @generated
 */
public interface SituationParticipant extends Participant {
	/**
	 * Returns the value of the '<em><b>Temporality</b></em>' attribute.
	 * The literals are from the enumeration {@link sml2.TemporalKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Temporality</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Temporality</em>' attribute.
	 * @see sml2.TemporalKind
	 * @see #setTemporality(TemporalKind)
	 * @see sml2.Sml2Package#getSituationParticipant_Temporality()
	 * @model required="true"
	 * @generated
	 */
	TemporalKind getTemporality();

	/**
	 * Sets the value of the '{@link sml2.SituationParticipant#getTemporality <em>Temporality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temporality</em>' attribute.
	 * @see sml2.TemporalKind
	 * @see #getTemporality()
	 * @generated
	 */
	void setTemporality(TemporalKind value);

	/**
	 * Returns the value of the '<em><b>References</b></em>' containment reference list.
	 * The list contents are of type {@link sml2.ReferenceNode}.
	 * It is bidirectional and its opposite is '{@link sml2.ReferenceNode#getSituation <em>Situation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>References</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>References</em>' containment reference list.
	 * @see sml2.Sml2Package#getSituationParticipant_References()
	 * @see sml2.ReferenceNode#getSituation
	 * @model opposite="situation" containment="true"
	 * @generated
	 */
	EList<ReferenceNode> getReferences();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(SituationType)
	 * @see sml2.Sml2Package#getSituationParticipant_Type()
	 * @model required="true"
	 * @generated
	 */
	SituationType getType();

	/**
	 * Sets the value of the '{@link sml2.SituationParticipant#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(SituationType value);

} // SituationParticipant
