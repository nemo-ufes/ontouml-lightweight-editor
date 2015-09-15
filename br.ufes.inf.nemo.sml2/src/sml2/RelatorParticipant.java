/**
 */
package sml2;

import RefOntoUML.Relator;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relator Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.RelatorParticipant#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getRelatorParticipant()
 * @model
 * @generated
 */
public interface RelatorParticipant extends Participant {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Relator)
	 * @see sml2.Sml2Package#getRelatorParticipant_Type()
	 * @model required="true"
	 * @generated
	 */
	Relator getType();

	/**
	 * Sets the value of the '{@link sml2.RelatorParticipant#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Relator value);

} // RelatorParticipant
