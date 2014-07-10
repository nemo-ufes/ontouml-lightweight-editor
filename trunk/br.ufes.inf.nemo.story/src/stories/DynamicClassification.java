/**
 */
package stories;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic Classification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link stories.DynamicClassification#getClass_ <em>Class</em>}</li>
 *   <li>{@link stories.DynamicClassification#getClassified_in <em>Classified in</em>}</li>
 *   <li>{@link stories.DynamicClassification#getNot_classified_in <em>Not classified in</em>}</li>
 *   <li>{@link stories.DynamicClassification#getClassified_node <em>Classified node</em>}</li>
 * </ul>
 * </p>
 *
 * @see stories.StoriesPackage#getDynamicClassification()
 * @model
 * @generated
 */
public interface DynamicClassification extends Happening {
	/**
	 * Returns the value of the '<em><b>Class</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' reference list.
	 * @see stories.StoriesPackage#getDynamicClassification_Class()
	 * @model required="true"
	 * @generated
	 */
	EList<RefOntoUML.Class> getClass_();

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
	 * @see stories.StoriesPackage#getDynamicClassification_Classified_in()
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
	 * @see stories.StoriesPackage#getDynamicClassification_Not_classified_in()
	 * @model
	 * @generated
	 */
	EList<World> getNot_classified_in();

	/**
	 * Returns the value of the '<em><b>Classified node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classified node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classified node</em>' reference.
	 * @see #setClassified_node(Node)
	 * @see stories.StoriesPackage#getDynamicClassification_Classified_node()
	 * @model required="true"
	 * @generated
	 */
	Node getClassified_node();

	/**
	 * Sets the value of the '{@link stories.DynamicClassification#getClassified_node <em>Classified node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classified node</em>' reference.
	 * @see #getClassified_node()
	 * @generated
	 */
	void setClassified_node(Node value);

} // DynamicClassification
