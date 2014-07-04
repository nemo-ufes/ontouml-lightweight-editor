/**
 */
package stories;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>All Different</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link stories.AllDifferent#getDistinct_members <em>Distinct members</em>}</li>
 * </ul>
 * </p>
 *
 * @see stories.StoriesPackage#getAllDifferent()
 * @model
 * @generated
 */
public interface AllDifferent extends Story_element {
	/**
	 * Returns the value of the '<em><b>Distinct members</b></em>' reference list.
	 * The list contents are of type {@link stories.Individual}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Distinct members</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Distinct members</em>' reference list.
	 * @see stories.StoriesPackage#getAllDifferent_Distinct_members()
	 * @model lower="2"
	 * @generated
	 */
	EList<Individual> getDistinct_members();

} // AllDifferent
