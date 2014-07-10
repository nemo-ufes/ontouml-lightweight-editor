/**
 */
package stories;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link stories.Event#getDirectly_causes <em>Directly causes</em>}</li>
 *   <li>{@link stories.Event#getCauses <em>Causes</em>}</li>
 *   <li>{@link stories.Event#getBrings_about <em>Brings about</em>}</li>
 *   <li>{@link stories.Event#getHappened_in <em>Happened in</em>}</li>
 *   <li>{@link stories.Event#getHas_part <em>Has part</em>}</li>
 * </ul>
 * </p>
 *
 * @see stories.StoriesPackage#getEvent()
 * @model abstract="true"
 * @generated
 */
public interface Event extends Story_element {
	/**
	 * Returns the value of the '<em><b>Directly causes</b></em>' reference list.
	 * The list contents are of type {@link stories.Event}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Directly causes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Directly causes</em>' reference list.
	 * @see stories.StoriesPackage#getEvent_Directly_causes()
	 * @model
	 * @generated
	 */
	EList<Event> getDirectly_causes();

	/**
	 * Returns the value of the '<em><b>Causes</b></em>' reference list.
	 * The list contents are of type {@link stories.Event}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Causes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Causes</em>' reference list.
	 * @see stories.StoriesPackage#getEvent_Causes()
	 * @model
	 * @generated
	 */
	EList<Event> getCauses();

	/**
	 * Returns the value of the '<em><b>Brings about</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link stories.World#getBrought_about_by <em>Brought about by</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Brings about</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Brings about</em>' reference.
	 * @see #setBrings_about(World)
	 * @see stories.StoriesPackage#getEvent_Brings_about()
	 * @see stories.World#getBrought_about_by
	 * @model opposite="brought_about_by" required="true"
	 * @generated
	 */
	World getBrings_about();

	/**
	 * Sets the value of the '{@link stories.Event#getBrings_about <em>Brings about</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Brings about</em>' reference.
	 * @see #getBrings_about()
	 * @generated
	 */
	void setBrings_about(World value);

	/**
	 * Returns the value of the '<em><b>Happened in</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link stories.World#getEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Happened in</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Happened in</em>' reference.
	 * @see #setHappened_in(World)
	 * @see stories.StoriesPackage#getEvent_Happened_in()
	 * @see stories.World#getEnabled
	 * @model opposite="enabled" required="true"
	 * @generated
	 */
	World getHappened_in();

	/**
	 * Sets the value of the '{@link stories.Event#getHappened_in <em>Happened in</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Happened in</em>' reference.
	 * @see #getHappened_in()
	 * @generated
	 */
	void setHappened_in(World value);

	/**
	 * Returns the value of the '<em><b>Has part</b></em>' reference list.
	 * The list contents are of type {@link stories.Event}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has part</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has part</em>' reference list.
	 * @see stories.StoriesPackage#getEvent_Has_part()
	 * @model
	 * @generated
	 */
	EList<Event> getHas_part();

} // Event
