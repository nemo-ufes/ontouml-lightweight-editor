/**
 */
package br.ufes.inf.nemo.story.storyML.impl;

import br.ufes.inf.nemo.story.storyML.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StoryMLFactoryImpl extends EFactoryImpl implements StoryMLFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static StoryMLFactory init()
  {
    try
    {
      StoryMLFactory theStoryMLFactory = (StoryMLFactory)EPackage.Registry.INSTANCE.getEFactory(StoryMLPackage.eNS_URI);
      if (theStoryMLFactory != null)
      {
        return theStoryMLFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new StoryMLFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StoryMLFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case StoryMLPackage.STORY: return createStory();
      case StoryMLPackage.STORY_ELEMENT: return createStory_element();
      case StoryMLPackage.INDIVIDUAL: return createIndividual();
      case StoryMLPackage.LINK: return createLink();
      case StoryMLPackage.NODE: return createNode();
      case StoryMLPackage.WORLD: return createWorld();
      case StoryMLPackage.EVENT: return createEvent();
      case StoryMLPackage.ACTION: return createAction();
      case StoryMLPackage.HAPPENING: return createHappening();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Story createStory()
  {
    StoryImpl story = new StoryImpl();
    return story;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Story_element createStory_element()
  {
    Story_elementImpl story_element = new Story_elementImpl();
    return story_element;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Individual createIndividual()
  {
    IndividualImpl individual = new IndividualImpl();
    return individual;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Link createLink()
  {
    LinkImpl link = new LinkImpl();
    return link;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Node createNode()
  {
    NodeImpl node = new NodeImpl();
    return node;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public World createWorld()
  {
    WorldImpl world = new WorldImpl();
    return world;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Event createEvent()
  {
    EventImpl event = new EventImpl();
    return event;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Action createAction()
  {
    ActionImpl action = new ActionImpl();
    return action;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Happening createHappening()
  {
    HappeningImpl happening = new HappeningImpl();
    return happening;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StoryMLPackage getStoryMLPackage()
  {
    return (StoryMLPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static StoryMLPackage getPackage()
  {
    return StoryMLPackage.eINSTANCE;
  }

} //StoryMLFactoryImpl
