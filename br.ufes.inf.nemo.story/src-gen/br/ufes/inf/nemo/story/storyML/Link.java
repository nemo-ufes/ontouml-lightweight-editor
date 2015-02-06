/**
 */
package br.ufes.inf.nemo.story.storyML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.story.storyML.Link#getSource <em>Source</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.story.storyML.Link#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.story.storyML.StoryMLPackage#getLink()
 * @model
 * @generated
 */
public interface Link extends Individual
{
  /**
   * Returns the value of the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(Node)
   * @see br.ufes.inf.nemo.story.storyML.StoryMLPackage#getLink_Source()
   * @model
   * @generated
   */
  Node getSource();

  /**
   * Sets the value of the '{@link br.ufes.inf.nemo.story.storyML.Link#getSource <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */
  void setSource(Node value);

  /**
   * Returns the value of the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(Node)
   * @see br.ufes.inf.nemo.story.storyML.StoryMLPackage#getLink_Target()
   * @model
   * @generated
   */
  Node getTarget();

  /**
   * Sets the value of the '{@link br.ufes.inf.nemo.story.storyML.Link#getTarget <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(Node value);

} // Link
