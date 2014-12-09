/**
 */
package stories;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link stories.Node#getPerformed <em>Performed</em>}</li>
 *   <li>{@link stories.Node#getInstance_of <em>Instance of</em>}</li>
 *   <li>{@link stories.Node#getStates <em>States</em>}</li>
 *   <li>{@link stories.Node#getNot_instance_of <em>Not instance of</em>}</li>
 * </ul>
 * </p>
 *
 * @see stories.StoriesPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends Individual {
	/**
	 * Returns the value of the '<em><b>Performed</b></em>' containment reference list.
	 * The list contents are of type {@link stories.Action}.
	 * It is bidirectional and its opposite is '{@link stories.Action#getPerformed_by <em>Performed by</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed</em>' containment reference list.
	 * @see stories.StoriesPackage#getNode_Performed()
	 * @see stories.Action#getPerformed_by
	 * @model opposite="performed_by" containment="true"
	 * @generated
	 */
	EList<Action> getPerformed();

	/**
	 * Returns the value of the '<em><b>Instance of</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance of</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance of</em>' reference list.
	 * @see stories.StoriesPackage#getNode_Instance_of()
	 * @model
	 * @generated
	 */
	EList<RefOntoUML.Class> getInstance_of();
	
	

	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link stories.Node_state}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see stories.StoriesPackage#getNode_States()
	 * @model containment="true"
	 * @generated
	 */
	EList<Node_state> getStates();

	/**
	 * Returns the value of the '<em><b>Not instance of</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Not instance of</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Not instance of</em>' reference list.
	 * @see stories.StoriesPackage#getNode_Not_instance_of()
	 * @model
	 * @generated
	 */
	EList<RefOntoUML.Class> getNot_instance_of();

	public String static_classification();

	public String existance();

	String states();

	

} // Node
