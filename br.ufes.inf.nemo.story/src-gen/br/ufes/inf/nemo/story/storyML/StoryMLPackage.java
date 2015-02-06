/**
 */
package br.ufes.inf.nemo.story.storyML;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see br.ufes.inf.nemo.story.storyML.StoryMLFactory
 * @model kind="package"
 * @generated
 */
public interface StoryMLPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "storyML";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.ufes.br/inf/nemo/story/StoryML";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "storyML";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  StoryMLPackage eINSTANCE = br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl.init();

  /**
   * The meta object id for the '{@link br.ufes.inf.nemo.story.storyML.impl.StoryImpl <em>Story</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see br.ufes.inf.nemo.story.storyML.impl.StoryImpl
   * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getStory()
   * @generated
   */
  int STORY = 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STORY__ELEMENTS = 0;

  /**
   * The number of structural features of the '<em>Story</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STORY_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link br.ufes.inf.nemo.story.storyML.impl.Story_elementImpl <em>Story element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see br.ufes.inf.nemo.story.storyML.impl.Story_elementImpl
   * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getStory_element()
   * @generated
   */
  int STORY_ELEMENT = 1;

  /**
   * The number of structural features of the '<em>Story element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STORY_ELEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link br.ufes.inf.nemo.story.storyML.impl.IndividualImpl <em>Individual</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see br.ufes.inf.nemo.story.storyML.impl.IndividualImpl
   * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getIndividual()
   * @generated
   */
  int INDIVIDUAL = 2;

  /**
   * The feature id for the '<em><b>Present in</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDIVIDUAL__PRESENT_IN = STORY_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Absent from</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDIVIDUAL__ABSENT_FROM = STORY_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Individual</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDIVIDUAL_FEATURE_COUNT = STORY_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link br.ufes.inf.nemo.story.storyML.impl.LinkImpl <em>Link</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see br.ufes.inf.nemo.story.storyML.impl.LinkImpl
   * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getLink()
   * @generated
   */
  int LINK = 3;

  /**
   * The feature id for the '<em><b>Present in</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK__PRESENT_IN = INDIVIDUAL__PRESENT_IN;

  /**
   * The feature id for the '<em><b>Absent from</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK__ABSENT_FROM = INDIVIDUAL__ABSENT_FROM;

  /**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK__SOURCE = INDIVIDUAL_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK__TARGET = INDIVIDUAL_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Link</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK_FEATURE_COUNT = INDIVIDUAL_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link br.ufes.inf.nemo.story.storyML.impl.NodeImpl <em>Node</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see br.ufes.inf.nemo.story.storyML.impl.NodeImpl
   * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getNode()
   * @generated
   */
  int NODE = 4;

  /**
   * The feature id for the '<em><b>Present in</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE__PRESENT_IN = INDIVIDUAL__PRESENT_IN;

  /**
   * The feature id for the '<em><b>Absent from</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE__ABSENT_FROM = INDIVIDUAL__ABSENT_FROM;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE__NAME = INDIVIDUAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Node</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_FEATURE_COUNT = INDIVIDUAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link br.ufes.inf.nemo.story.storyML.impl.WorldImpl <em>World</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see br.ufes.inf.nemo.story.storyML.impl.WorldImpl
   * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getWorld()
   * @generated
   */
  int WORLD = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WORLD__NAME = STORY_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>World</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WORLD_FEATURE_COUNT = STORY_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link br.ufes.inf.nemo.story.storyML.impl.EventImpl <em>Event</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see br.ufes.inf.nemo.story.storyML.impl.EventImpl
   * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getEvent()
   * @generated
   */
  int EVENT = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVENT__NAME = STORY_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Event</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVENT_FEATURE_COUNT = STORY_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link br.ufes.inf.nemo.story.storyML.impl.ActionImpl <em>Action</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see br.ufes.inf.nemo.story.storyML.impl.ActionImpl
   * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getAction()
   * @generated
   */
  int ACTION = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION__NAME = EVENT__NAME;

  /**
   * The number of structural features of the '<em>Action</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link br.ufes.inf.nemo.story.storyML.impl.HappeningImpl <em>Happening</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see br.ufes.inf.nemo.story.storyML.impl.HappeningImpl
   * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getHappening()
   * @generated
   */
  int HAPPENING = 8;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HAPPENING__NAME = EVENT__NAME;

  /**
   * The number of structural features of the '<em>Happening</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HAPPENING_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;


  /**
   * Returns the meta object for class '{@link br.ufes.inf.nemo.story.storyML.Story <em>Story</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Story</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Story
   * @generated
   */
  EClass getStory();

  /**
   * Returns the meta object for the containment reference list '{@link br.ufes.inf.nemo.story.storyML.Story#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Story#getElements()
   * @see #getStory()
   * @generated
   */
  EReference getStory_Elements();

  /**
   * Returns the meta object for class '{@link br.ufes.inf.nemo.story.storyML.Story_element <em>Story element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Story element</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Story_element
   * @generated
   */
  EClass getStory_element();

  /**
   * Returns the meta object for class '{@link br.ufes.inf.nemo.story.storyML.Individual <em>Individual</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Individual</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Individual
   * @generated
   */
  EClass getIndividual();

  /**
   * Returns the meta object for the reference list '{@link br.ufes.inf.nemo.story.storyML.Individual#getPresent_in <em>Present in</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Present in</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Individual#getPresent_in()
   * @see #getIndividual()
   * @generated
   */
  EReference getIndividual_Present_in();

  /**
   * Returns the meta object for the reference list '{@link br.ufes.inf.nemo.story.storyML.Individual#getAbsent_from <em>Absent from</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Absent from</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Individual#getAbsent_from()
   * @see #getIndividual()
   * @generated
   */
  EReference getIndividual_Absent_from();

  /**
   * Returns the meta object for class '{@link br.ufes.inf.nemo.story.storyML.Link <em>Link</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Link</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Link
   * @generated
   */
  EClass getLink();

  /**
   * Returns the meta object for the reference '{@link br.ufes.inf.nemo.story.storyML.Link#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Link#getSource()
   * @see #getLink()
   * @generated
   */
  EReference getLink_Source();

  /**
   * Returns the meta object for the reference '{@link br.ufes.inf.nemo.story.storyML.Link#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Link#getTarget()
   * @see #getLink()
   * @generated
   */
  EReference getLink_Target();

  /**
   * Returns the meta object for class '{@link br.ufes.inf.nemo.story.storyML.Node <em>Node</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Node</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Node
   * @generated
   */
  EClass getNode();

  /**
   * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.story.storyML.Node#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Node#getName()
   * @see #getNode()
   * @generated
   */
  EAttribute getNode_Name();

  /**
   * Returns the meta object for class '{@link br.ufes.inf.nemo.story.storyML.World <em>World</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>World</em>'.
   * @see br.ufes.inf.nemo.story.storyML.World
   * @generated
   */
  EClass getWorld();

  /**
   * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.story.storyML.World#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see br.ufes.inf.nemo.story.storyML.World#getName()
   * @see #getWorld()
   * @generated
   */
  EAttribute getWorld_Name();

  /**
   * Returns the meta object for class '{@link br.ufes.inf.nemo.story.storyML.Event <em>Event</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Event</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Event
   * @generated
   */
  EClass getEvent();

  /**
   * Returns the meta object for the attribute '{@link br.ufes.inf.nemo.story.storyML.Event#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Event#getName()
   * @see #getEvent()
   * @generated
   */
  EAttribute getEvent_Name();

  /**
   * Returns the meta object for class '{@link br.ufes.inf.nemo.story.storyML.Action <em>Action</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Action</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Action
   * @generated
   */
  EClass getAction();

  /**
   * Returns the meta object for class '{@link br.ufes.inf.nemo.story.storyML.Happening <em>Happening</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Happening</em>'.
   * @see br.ufes.inf.nemo.story.storyML.Happening
   * @generated
   */
  EClass getHappening();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  StoryMLFactory getStoryMLFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link br.ufes.inf.nemo.story.storyML.impl.StoryImpl <em>Story</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see br.ufes.inf.nemo.story.storyML.impl.StoryImpl
     * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getStory()
     * @generated
     */
    EClass STORY = eINSTANCE.getStory();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STORY__ELEMENTS = eINSTANCE.getStory_Elements();

    /**
     * The meta object literal for the '{@link br.ufes.inf.nemo.story.storyML.impl.Story_elementImpl <em>Story element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see br.ufes.inf.nemo.story.storyML.impl.Story_elementImpl
     * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getStory_element()
     * @generated
     */
    EClass STORY_ELEMENT = eINSTANCE.getStory_element();

    /**
     * The meta object literal for the '{@link br.ufes.inf.nemo.story.storyML.impl.IndividualImpl <em>Individual</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see br.ufes.inf.nemo.story.storyML.impl.IndividualImpl
     * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getIndividual()
     * @generated
     */
    EClass INDIVIDUAL = eINSTANCE.getIndividual();

    /**
     * The meta object literal for the '<em><b>Present in</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INDIVIDUAL__PRESENT_IN = eINSTANCE.getIndividual_Present_in();

    /**
     * The meta object literal for the '<em><b>Absent from</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INDIVIDUAL__ABSENT_FROM = eINSTANCE.getIndividual_Absent_from();

    /**
     * The meta object literal for the '{@link br.ufes.inf.nemo.story.storyML.impl.LinkImpl <em>Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see br.ufes.inf.nemo.story.storyML.impl.LinkImpl
     * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getLink()
     * @generated
     */
    EClass LINK = eINSTANCE.getLink();

    /**
     * The meta object literal for the '<em><b>Source</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LINK__SOURCE = eINSTANCE.getLink_Source();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LINK__TARGET = eINSTANCE.getLink_Target();

    /**
     * The meta object literal for the '{@link br.ufes.inf.nemo.story.storyML.impl.NodeImpl <em>Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see br.ufes.inf.nemo.story.storyML.impl.NodeImpl
     * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getNode()
     * @generated
     */
    EClass NODE = eINSTANCE.getNode();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NODE__NAME = eINSTANCE.getNode_Name();

    /**
     * The meta object literal for the '{@link br.ufes.inf.nemo.story.storyML.impl.WorldImpl <em>World</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see br.ufes.inf.nemo.story.storyML.impl.WorldImpl
     * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getWorld()
     * @generated
     */
    EClass WORLD = eINSTANCE.getWorld();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WORLD__NAME = eINSTANCE.getWorld_Name();

    /**
     * The meta object literal for the '{@link br.ufes.inf.nemo.story.storyML.impl.EventImpl <em>Event</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see br.ufes.inf.nemo.story.storyML.impl.EventImpl
     * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getEvent()
     * @generated
     */
    EClass EVENT = eINSTANCE.getEvent();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EVENT__NAME = eINSTANCE.getEvent_Name();

    /**
     * The meta object literal for the '{@link br.ufes.inf.nemo.story.storyML.impl.ActionImpl <em>Action</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see br.ufes.inf.nemo.story.storyML.impl.ActionImpl
     * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getAction()
     * @generated
     */
    EClass ACTION = eINSTANCE.getAction();

    /**
     * The meta object literal for the '{@link br.ufes.inf.nemo.story.storyML.impl.HappeningImpl <em>Happening</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see br.ufes.inf.nemo.story.storyML.impl.HappeningImpl
     * @see br.ufes.inf.nemo.story.storyML.impl.StoryMLPackageImpl#getHappening()
     * @generated
     */
    EClass HAPPENING = eINSTANCE.getHappening();

  }

} //StoryMLPackage
