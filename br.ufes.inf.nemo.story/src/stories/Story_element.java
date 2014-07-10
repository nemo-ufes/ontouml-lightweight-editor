/**
 */
package stories;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Story element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link stories.Story_element#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see stories.StoriesPackage#getStory_element()
 * @model abstract="true"
 * @generated
 */
public interface Story_element extends EObject {
	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * The default value is <code>"element"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see stories.StoriesPackage#getStory_element_Label()
	 * @model default="element" id="true"
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link stories.Story_element#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

} // Story_element
