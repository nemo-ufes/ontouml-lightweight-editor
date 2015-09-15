/**
 */
package sml2;

import RefOntoUML.ObjectClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.EntityParticipant#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getEntityParticipant()
 * @model
 * @generated
 */
public interface EntityParticipant extends Participant {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(ObjectClass)
	 * @see sml2.Sml2Package#getEntityParticipant_Type()
	 * @model required="true"
	 * @generated
	 */
	ObjectClass getType();

	/**
	 * Sets the value of the '{@link sml2.EntityParticipant#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(ObjectClass value);

} // EntityParticipant
