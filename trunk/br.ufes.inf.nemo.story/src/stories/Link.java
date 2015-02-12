/**
 */
package stories;

import RefOntoUML.Association;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link stories.Link#getSource <em>Source</em>}</li>
 *   <li>{@link stories.Link#getTarget <em>Target</em>}</li>
 *   <li>{@link stories.Link#getInstance_of <em>Instance of</em>}</li>
 *   <li>{@link stories.Link#getNot_instance_of <em>Not instance of</em>}</li>
 * </ul>
 * </p>
 *
 * @see stories.StoriesPackage#getLink()
 * @model
 * @generated
 */
public interface Link extends Individual {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Node)
	 * @see stories.StoriesPackage#getLink_Source()
	 * @model required="true"
	 * @generated
	 */
	Node getSource();

	/**
	 * Sets the value of the '{@link stories.Link#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Node value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Node)
	 * @see stories.StoriesPackage#getLink_Target()
	 * @model required="true"
	 * @generated
	 */
	Node getTarget();

	/**
	 * Sets the value of the '{@link stories.Link#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Node value);

	/**
	 * Returns the value of the '<em><b>Instance of</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Association}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance of</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance of</em>' reference list.
	 * @see stories.StoriesPackage#getLink_Instance_of()
	 * @model
	 * @generated
	 */
	EList<Association> getInstance_of();

	/**
	 * Returns the value of the '<em><b>Not instance of</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Association}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Not instance of</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Not instance of</em>' reference list.
	 * @see stories.StoriesPackage#getLink_Not_instance_of()
	 * @model
	 * @generated
	 */
	EList<Association> getNot_instance_of();

	String existance();

	/*void clear();*/

} // Link
