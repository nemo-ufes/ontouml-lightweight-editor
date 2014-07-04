/**
 */
package stories;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link stories.Action#getMotivated_by <em>Motivated by</em>}</li>
 *   <li>{@link stories.Action#getPerformed_by <em>Performed by</em>}</li>
 * </ul>
 * </p>
 *
 * @see stories.StoriesPackage#getAction()
 * @model
 * @generated
 */
public interface Action extends Event {
	/**
	 * Returns the value of the '<em><b>Motivated by</b></em>' reference list.
	 * The list contents are of type {@link stories.Event}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Motivated by</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Motivated by</em>' reference list.
	 * @see stories.StoriesPackage#getAction_Motivated_by()
	 * @model
	 * @generated
	 */
	EList<Event> getMotivated_by();

	/**
	 * Returns the value of the '<em><b>Performed by</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link stories.Node#getPerformed <em>Performed</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed by</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed by</em>' container reference.
	 * @see #setPerformed_by(Node)
	 * @see stories.StoriesPackage#getAction_Performed_by()
	 * @see stories.Node#getPerformed
	 * @model opposite="performed" required="true" transient="false"
	 * @generated
	 */
	Node getPerformed_by();

	/**
	 * Sets the value of the '{@link stories.Action#getPerformed_by <em>Performed by</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Performed by</em>' container reference.
	 * @see #getPerformed_by()
	 * @generated
	 */
	void setPerformed_by(Node value);

} // Action
