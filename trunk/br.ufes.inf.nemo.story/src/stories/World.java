/**
 */
package stories;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>World</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link stories.World#getEnabled <em>Enabled</em>}</li>
 *   <li>{@link stories.World#getBrought_about_by <em>Brought about by</em>}</li>
 *   <li>{@link stories.World#getNext <em>Next</em>}</li>
 *   <li>{@link stories.World#getPrev <em>Prev</em>}</li>
 * </ul>
 * </p>
 *
 * @see stories.StoriesPackage#getWorld()
 * @model
 * @generated
 */
public interface World extends Story_element {
	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' reference list.
	 * The list contents are of type {@link stories.Event}.
	 * It is bidirectional and its opposite is '{@link stories.Event#getHappened_in <em>Happened in</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' reference list.
	 * @see stories.StoriesPackage#getWorld_Enabled()
	 * @see stories.Event#getHappened_in
	 * @model opposite="happened_in"
	 * @generated
	 */
	EList<Event> getEnabled();

	/**
	 * Returns the value of the '<em><b>Brought about by</b></em>' reference list.
	 * The list contents are of type {@link stories.Event}.
	 * It is bidirectional and its opposite is '{@link stories.Event#getBrings_about <em>Brings about</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Brought about by</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Brought about by</em>' reference list.
	 * @see stories.StoriesPackage#getWorld_Brought_about_by()
	 * @see stories.Event#getBrings_about
	 * @model opposite="brings_about"
	 * @generated
	 */
	EList<Event> getBrought_about_by();

	/**
	 * Returns the value of the '<em><b>Next</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link stories.World#getPrev <em>Prev</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next</em>' reference.
	 * @see #setNext(World)
	 * @see stories.StoriesPackage#getWorld_Next()
	 * @see stories.World#getPrev
	 * @model opposite="prev"
	 * @generated
	 */
	World getNext();

	/**
	 * Sets the value of the '{@link stories.World#getNext <em>Next</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next</em>' reference.
	 * @see #getNext()
	 * @generated
	 */
	void setNext(World value);

	/**
	 * Returns the value of the '<em><b>Prev</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link stories.World#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prev</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prev</em>' reference.
	 * @see #setPrev(World)
	 * @see stories.StoriesPackage#getWorld_Prev()
	 * @see stories.World#getNext
	 * @model opposite="next"
	 * @generated
	 */
	World getPrev();

	/**
	 * Sets the value of the '{@link stories.World#getPrev <em>Prev</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prev</em>' reference.
	 * @see #getPrev()
	 * @generated
	 */
	void setPrev(World value);

} // World
