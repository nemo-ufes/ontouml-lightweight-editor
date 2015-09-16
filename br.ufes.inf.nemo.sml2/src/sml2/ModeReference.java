/**
 */
package sml2;

import RefOntoUML.Mode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mode Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.ModeReference#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getModeReference()
 * @model
 * @generated
 */
public interface ModeReference extends ReferableElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Mode)
	 * @see sml2.Sml2Package#getModeReference_Type()
	 * @model required="true"
	 * @generated
	 */
	Mode getType();

	/**
	 * Sets the value of the '{@link sml2.ModeReference#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Mode value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='CharacterizationLink.allInstances()->select(x | x.getMode() = self)->asOrderedSet()->at(1).getCharacterized()'"
	 *        annotation="Comments getEntity='Returns the EntityParticipant that owns the ModeReference.'"
	 * @generated
	 */
	EntityParticipant getEntity();

} // ModeReference
