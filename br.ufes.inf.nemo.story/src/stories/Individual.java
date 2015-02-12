/**
 */
package stories;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Individual</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link stories.Individual#getDifferent_from <em>Different from</em>}</li>
 *   <li>{@link stories.Individual#getSame_as <em>Same as</em>}</li>
 *   <li>{@link stories.Individual#getPresent_in <em>Present in</em>}</li>
 *   <li>{@link stories.Individual#getAbsent_from <em>Absent from</em>}</li>
 * </ul>
 * </p>
 *
 * @see stories.StoriesPackage#getIndividual()
 * @model abstract="true"
 * @generated
 */
public interface Individual extends Story_element {

	/**
	 * Returns the value of the '<em><b>Different from</b></em>' reference list.
	 * The list contents are of type {@link stories.Individual}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Different from</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Different from</em>' reference list.
	 * @see stories.StoriesPackage#getIndividual_Different_from()
	 * @model
	 * @generated
	 */
	EList<Individual> getDifferent_from();

	/**
	 * Returns the value of the '<em><b>Same as</b></em>' reference list.
	 * The list contents are of type {@link stories.Individual}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Same as</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Same as</em>' reference list.
	 * @see stories.StoriesPackage#getIndividual_Same_as()
	 * @model
	 * @generated
	 */
	EList<Individual> getSame_as();

	/**
	 * Returns the value of the '<em><b>Present in</b></em>' reference list.
	 * The list contents are of type {@link stories.World}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Present in</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Present in</em>' reference list.
	 * @see stories.StoriesPackage#getIndividual_Present_in()
	 * @model
	 * @generated
	 */
	EList<World> getPresent_in();

	/**
	 * Returns the value of the '<em><b>Absent from</b></em>' reference list.
	 * The list contents are of type {@link stories.World}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Absent from</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Absent from</em>' reference list.
	 * @see stories.StoriesPackage#getIndividual_Absent_from()
	 * @model
	 * @generated
	 */
	EList<World> getAbsent_from();
	
	public String identity();
	
	/*void clear();*/
} // Individual
