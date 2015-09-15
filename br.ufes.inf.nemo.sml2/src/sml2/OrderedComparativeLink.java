/**
 */
package sml2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ordered Comparative Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.OrderedComparativeLink#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getOrderedComparativeLink()
 * @model
 * @generated
 */
public interface OrderedComparativeLink extends ComparativeRelation {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link sml2.ComparativeKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see sml2.ComparativeKind
	 * @see #setType(ComparativeKind)
	 * @see sml2.Sml2Package#getOrderedComparativeLink_Type()
	 * @model required="true"
	 * @generated
	 */
	ComparativeKind getType();

	/**
	 * Sets the value of the '{@link sml2.OrderedComparativeLink#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see sml2.ComparativeKind
	 * @see #getType()
	 * @generated
	 */
	void setType(ComparativeKind value);

} // OrderedComparativeLink
