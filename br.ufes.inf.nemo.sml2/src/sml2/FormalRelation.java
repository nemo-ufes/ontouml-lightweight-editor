/**
 */
package sml2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Relation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.FormalRelation#isNegated <em>Negated</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getFormalRelation()
 * @model abstract="true"
 * @generated
 */
public interface FormalRelation extends SituationTypeAssociation {
	/**
	 * Returns the value of the '<em><b>Negated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Negated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Negated</em>' attribute.
	 * @see #setNegated(boolean)
	 * @see sml2.Sml2Package#getFormalRelation_Negated()
	 * @model
	 * @generated
	 */
	boolean isNegated();

	/**
	 * Sets the value of the '{@link sml2.FormalRelation#isNegated <em>Negated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Negated</em>' attribute.
	 * @see #isNegated()
	 * @generated
	 */
	void setNegated(boolean value);

} // FormalRelation
