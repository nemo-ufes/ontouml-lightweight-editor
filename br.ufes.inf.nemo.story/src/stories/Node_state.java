/**
 */
package stories;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node state</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link stories.Node_state#getClassified_in <em>Classified in</em>}</li>
 *   <li>{@link stories.Node_state#getNot_classified_in <em>Not classified in</em>}</li>
 *   <li>{@link stories.Node_state#getAntiRigidClasses <em>Anti Rigid Classes</em>}</li>
 *   <li>{@link stories.Node_state#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see stories.StoriesPackage#getNode_state()
 * @model
 * @generated
 */
public interface Node_state extends EObject {
	/**
	 * Returns the value of the '<em><b>Classified in</b></em>' reference list.
	 * The list contents are of type {@link stories.World}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classified in</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classified in</em>' reference list.
	 * @see stories.StoriesPackage#getNode_state_Classified_in()
	 * @model
	 * @generated
	 */
	EList<World> getClassified_in();

	/**
	 * Returns the value of the '<em><b>Not classified in</b></em>' reference list.
	 * The list contents are of type {@link stories.World}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Not classified in</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Not classified in</em>' reference list.
	 * @see stories.StoriesPackage#getNode_state_Not_classified_in()
	 * @model
	 * @generated
	 */
	EList<World> getNot_classified_in();

	/**
	 * Returns the value of the '<em><b>Anti Rigid Classes</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anti Rigid Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anti Rigid Classes</em>' reference list.
	 * @see stories.StoriesPackage#getNode_state_AntiRigidClasses()
	 * @model required="true"
	 * @generated
	 */
	EList<RefOntoUML.Class> getAntiRigidClasses();

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see stories.StoriesPackage#getNode_state_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link stories.Node_state#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	String existance(Node target);

} // Node_state
