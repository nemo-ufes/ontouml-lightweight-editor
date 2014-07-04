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
 *   <li>{@link stories.World#getPresent <em>Present</em>}</li>
 *   <li>{@link stories.World#getEnabled <em>Enabled</em>}</li>
 *   <li>{@link stories.World#getBrought_about_by <em>Brought about by</em>}</li>
 *   <li>{@link stories.World#getAbsent <em>Absent</em>}</li>
 * </ul>
 * </p>
 *
 * @see stories.StoriesPackage#getWorld()
 * @model
 * @generated
 */
public interface World extends Story_element {
	/**
	 * Returns the value of the '<em><b>Present</b></em>' reference list.
	 * The list contents are of type {@link stories.Individual}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Present</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Present</em>' reference list.
	 * @see stories.StoriesPackage#getWorld_Present()
	 * @model
	 * @generated
	 */
	EList<Individual> getPresent();

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
	 * Returns the value of the '<em><b>Absent</b></em>' reference list.
	 * The list contents are of type {@link stories.Individual}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Absent</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Absent</em>' reference list.
	 * @see stories.StoriesPackage#getWorld_Absent()
	 * @model
	 * @generated
	 */
	EList<Individual> getAbsent();

} // World
