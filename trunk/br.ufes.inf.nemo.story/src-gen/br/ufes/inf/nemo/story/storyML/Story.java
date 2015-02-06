/**
 */
package br.ufes.inf.nemo.story.storyML;

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
 *   <li>{@link br.ufes.inf.nemo.story.storyML.Story#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.story.storyML.StoryMLPackage#getStory()
 * @model
 * @generated
 */
public interface Story extends EObject
{
  /**
   * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
   * The list contents are of type {@link br.ufes.inf.nemo.story.storyML.Story_element}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elements</em>' containment reference list.
   * @see br.ufes.inf.nemo.story.storyML.StoryMLPackage#getStory_Elements()
   * @model containment="true"
   * @generated
   */
  EList<Story_element> getElements();

} // Story
