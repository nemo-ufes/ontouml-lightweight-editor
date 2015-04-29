/**
 */
package sml2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Situation Parameter Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.SituationParameterReference#getParameter <em>Parameter</em>}</li>
 *   <li>{@link sml2.SituationParameterReference#getSituation <em>Situation</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getSituationParameterReference()
 * @model
 * @generated
 */
public interface SituationParameterReference extends Node {
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference.
	 * @see #setParameter(SituationTypeParameter)
	 * @see sml2.Sml2Package#getSituationParameterReference_Parameter()
	 * @model required="true"
	 * @generated
	 */
	SituationTypeParameter getParameter();

	/**
	 * Sets the value of the '{@link sml2.SituationParameterReference#getParameter <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(SituationTypeParameter value);

	/**
	 * Returns the value of the '<em><b>Situation</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link sml2.SituationParticipant#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Situation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Situation</em>' reference.
	 * @see #setSituation(SituationParticipant)
	 * @see sml2.Sml2Package#getSituationParameterReference_Situation()
	 * @see sml2.SituationParticipant#getParameter
	 * @model opposite="parameter" required="true"
	 * @generated
	 */
	SituationParticipant getSituation();

	/**
	 * Sets the value of the '{@link sml2.SituationParameterReference#getSituation <em>Situation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Situation</em>' reference.
	 * @see #getSituation()
	 * @generated
	 */
	void setSituation(SituationParticipant value);

} // SituationParameterReference
