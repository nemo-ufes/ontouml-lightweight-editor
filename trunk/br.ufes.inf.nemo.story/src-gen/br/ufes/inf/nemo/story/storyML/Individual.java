/**
 */
package br.ufes.inf.nemo.story.storyML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Individual</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.story.storyML.Individual#getPresent_in <em>Present in</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.story.storyML.Individual#getAbsent_from <em>Absent from</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.story.storyML.StoryMLPackage#getIndividual()
 * @model
 * @generated
 */
public interface Individual extends Story_element
{
  /**
   * Returns the value of the '<em><b>Present in</b></em>' reference list.
   * The list contents are of type {@link br.ufes.inf.nemo.story.storyML.World}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Present in</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Present in</em>' reference list.
   * @see br.ufes.inf.nemo.story.storyML.StoryMLPackage#getIndividual_Present_in()
   * @model
   * @generated
   */
  EList<World> getPresent_in();

  /**
   * Returns the value of the '<em><b>Absent from</b></em>' reference list.
   * The list contents are of type {@link br.ufes.inf.nemo.story.storyML.World}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Absent from</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Absent from</em>' reference list.
   * @see br.ufes.inf.nemo.story.storyML.StoryMLPackage#getIndividual_Absent_from()
   * @model
   * @generated
   */
  EList<World> getAbsent_from();

} // Individual
