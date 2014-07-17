/**
 */
package stories;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Story</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link stories.Story#getElements <em>Elements</em>}</li>
 *   <li>{@link stories.Story#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see stories.StoriesPackage#getStory()
 * @model
 * @generated
 */
public interface Story extends EObject {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link stories.Story_element}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see stories.StoriesPackage#getStory_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Story_element> getElements();

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * The default value is <code>"story"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see stories.StoriesPackage#getStory_Label()
	 * @model default="story"
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link stories.Story#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);
	
	public String generatePredicates();
	
	public Story mergeReferences();

} // Story
