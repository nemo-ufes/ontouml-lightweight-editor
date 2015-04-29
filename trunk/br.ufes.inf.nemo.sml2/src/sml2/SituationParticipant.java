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
 *   <li>{@link sml2.SituationParticipant#getSituationType <em>Situation Type</em>}</li>
 *   <li>{@link sml2.SituationParticipant#getParameter <em>Parameter</em>}</li>
 *   <li>{@link sml2.SituationParticipant#isIsPast <em>Is Past</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getSituationParticipant()
 * @model
 * @generated
 */
public interface SituationParticipant extends Participant {
	/**
	 * Returns the value of the '<em><b>Situation Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Situation Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Situation Type</em>' reference.
	 * @see #setSituationType(SituationType)
	 * @see sml2.Sml2Package#getSituationParticipant_SituationType()
	 * @model required="true"
	 * @generated
	 */
	SituationType getSituationType();

	/**
	 * Sets the value of the '{@link sml2.SituationParticipant#getSituationType <em>Situation Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Situation Type</em>' reference.
	 * @see #getSituationType()
	 * @generated
	 */
	void setSituationType(SituationType value);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference list.
	 * The list contents are of type {@link sml2.SituationParameterReference}.
	 * It is bidirectional and its opposite is '{@link sml2.SituationParameterReference#getSituation <em>Situation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference list.
	 * @see sml2.Sml2Package#getSituationParticipant_Parameter()
	 * @see sml2.SituationParameterReference#getSituation
	 * @model opposite="situation"
	 * @generated
	 */
	EList<SituationParameterReference> getParameter();

	/**
	 * Returns the value of the '<em><b>Is Past</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Past</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Past</em>' attribute.
	 * @see #setIsPast(boolean)
	 * @see sml2.Sml2Package#getSituationParticipant_IsPast()
	 * @model
	 * @generated
	 */
	boolean isIsPast();

	/**
	 * Sets the value of the '{@link sml2.SituationParticipant#isIsPast <em>Is Past</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Past</em>' attribute.
	 * @see #isIsPast()
	 * @generated
	 */
	void setIsPast(boolean value);

} // SituationParticipant
