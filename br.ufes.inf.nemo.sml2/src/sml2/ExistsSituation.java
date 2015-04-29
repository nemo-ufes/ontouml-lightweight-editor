/**
 */
package sml2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exists Situation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.ExistsSituation#isIsNegated <em>Is Negated</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getExistsSituation()
 * @model
 * @generated
 */
public interface ExistsSituation extends SituationParticipant {
	/**
	 * Returns the value of the '<em><b>Is Negated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Negated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Negated</em>' attribute.
	 * @see #setIsNegated(boolean)
	 * @see sml2.Sml2Package#getExistsSituation_IsNegated()
	 * @model
	 * @generated
	 */
	boolean isIsNegated();

	/**
	 * Sets the value of the '{@link sml2.ExistsSituation#isIsNegated <em>Is Negated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Negated</em>' attribute.
	 * @see #isIsNegated()
	 * @generated
	 */
	void setIsNegated(boolean value);

} // ExistsSituation
