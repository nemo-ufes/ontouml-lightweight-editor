/**
 */
package stories;

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
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see stories.StoriesFactory
 * @model kind="package"
 * @generated
 */
public interface StoriesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "stories";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://stories.bernardofbbraga.com.br";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "stories";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StoriesPackage eINSTANCE = stories.impl.StoriesPackageImpl.init();

	/**
	 * The meta object id for the '{@link stories.impl.StoryImpl <em>Story</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see stories.impl.StoryImpl
	 * @see stories.impl.StoriesPackageImpl#getStory()
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
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY__LABEL = 1;

	/**
	 * The number of structural features of the '<em>Story</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Story</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link stories.impl.Story_elementImpl <em>Story element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see stories.impl.Story_elementImpl
	 * @see stories.impl.StoriesPackageImpl#getStory_element()
	 * @generated
	 */
	int STORY_ELEMENT = 6;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_ELEMENT__LABEL = 0;

	/**
	 * The number of structural features of the '<em>Story element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Story element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link stories.impl.IndividualImpl <em>Individual</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see stories.impl.IndividualImpl
	 * @see stories.impl.StoriesPackageImpl#getIndividual()
	 * @generated
	 */
	int INDIVIDUAL = 1;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIVIDUAL__LABEL = STORY_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Different from</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIVIDUAL__DIFFERENT_FROM = STORY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Same as</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIVIDUAL__SAME_AS = STORY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Present in</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIVIDUAL__PRESENT_IN = STORY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Absent from</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIVIDUAL__ABSENT_FROM = STORY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Individual</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIVIDUAL_FEATURE_COUNT = STORY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Individual</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIVIDUAL_OPERATION_COUNT = STORY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link stories.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see stories.impl.NodeImpl
	 * @see stories.impl.StoriesPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 2;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__LABEL = INDIVIDUAL__LABEL;

	/**
	 * The feature id for the '<em><b>Different from</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__DIFFERENT_FROM = INDIVIDUAL__DIFFERENT_FROM;

	/**
	 * The feature id for the '<em><b>Same as</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__SAME_AS = INDIVIDUAL__SAME_AS;

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
	 * The feature id for the '<em><b>Performed</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__PERFORMED = INDIVIDUAL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instance of</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__INSTANCE_OF = INDIVIDUAL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__STATES = INDIVIDUAL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Not instance of</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NOT_INSTANCE_OF = INDIVIDUAL_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = INDIVIDUAL_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = INDIVIDUAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link stories.impl.LinkImpl <em>Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see stories.impl.LinkImpl
	 * @see stories.impl.StoriesPackageImpl#getLink()
	 * @generated
	 */
	int LINK = 3;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__LABEL = INDIVIDUAL__LABEL;

	/**
	 * The feature id for the '<em><b>Different from</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__DIFFERENT_FROM = INDIVIDUAL__DIFFERENT_FROM;

	/**
	 * The feature id for the '<em><b>Same as</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__SAME_AS = INDIVIDUAL__SAME_AS;

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
	 * The feature id for the '<em><b>Instance of</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__INSTANCE_OF = INDIVIDUAL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Not instance of</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__NOT_INSTANCE_OF = INDIVIDUAL_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = INDIVIDUAL_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_OPERATION_COUNT = INDIVIDUAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link stories.impl.WorldImpl <em>World</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see stories.impl.WorldImpl
	 * @see stories.impl.StoriesPackageImpl#getWorld()
	 * @generated
	 */
	int WORLD = 4;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__LABEL = STORY_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__ENABLED = STORY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Brought about by</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__BROUGHT_ABOUT_BY = STORY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__NEXT = STORY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Prev</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__PREV = STORY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>World</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD_FEATURE_COUNT = STORY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>World</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD_OPERATION_COUNT = STORY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link stories.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see stories.impl.EventImpl
	 * @see stories.impl.StoriesPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 5;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__LABEL = STORY_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Directly causes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__DIRECTLY_CAUSES = STORY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Causes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__CAUSES = STORY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Brings about</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__BRINGS_ABOUT = STORY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Happened in</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__HAPPENED_IN = STORY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Has part</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__HAS_PART = STORY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FEATURE_COUNT = STORY_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OPERATION_COUNT = STORY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link stories.impl.HappeningImpl <em>Happening</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see stories.impl.HappeningImpl
	 * @see stories.impl.StoriesPackageImpl#getHappening()
	 * @generated
	 */
	int HAPPENING = 7;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAPPENING__LABEL = EVENT__LABEL;

	/**
	 * The feature id for the '<em><b>Directly causes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAPPENING__DIRECTLY_CAUSES = EVENT__DIRECTLY_CAUSES;

	/**
	 * The feature id for the '<em><b>Causes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAPPENING__CAUSES = EVENT__CAUSES;

	/**
	 * The feature id for the '<em><b>Brings about</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAPPENING__BRINGS_ABOUT = EVENT__BRINGS_ABOUT;

	/**
	 * The feature id for the '<em><b>Happened in</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAPPENING__HAPPENED_IN = EVENT__HAPPENED_IN;

	/**
	 * The feature id for the '<em><b>Has part</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAPPENING__HAS_PART = EVENT__HAS_PART;

	/**
	 * The number of structural features of the '<em>Happening</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAPPENING_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Happening</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAPPENING_OPERATION_COUNT = EVENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link stories.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see stories.impl.ActionImpl
	 * @see stories.impl.StoriesPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 8;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__LABEL = EVENT__LABEL;

	/**
	 * The feature id for the '<em><b>Directly causes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DIRECTLY_CAUSES = EVENT__DIRECTLY_CAUSES;

	/**
	 * The feature id for the '<em><b>Causes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CAUSES = EVENT__CAUSES;

	/**
	 * The feature id for the '<em><b>Brings about</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__BRINGS_ABOUT = EVENT__BRINGS_ABOUT;

	/**
	 * The feature id for the '<em><b>Happened in</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__HAPPENED_IN = EVENT__HAPPENED_IN;

	/**
	 * The feature id for the '<em><b>Has part</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__HAS_PART = EVENT__HAS_PART;

	/**
	 * The feature id for the '<em><b>Motivated by</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__MOTIVATED_BY = EVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Performed by</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__PERFORMED_BY = EVENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = EVENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_OPERATION_COUNT = EVENT_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link stories.impl.AllDifferentImpl <em>All Different</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see stories.impl.AllDifferentImpl
	 * @see stories.impl.StoriesPackageImpl#getAllDifferent()
	 * @generated
	 */
	int ALL_DIFFERENT = 9;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT__LABEL = STORY_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Distinct members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT__DISTINCT_MEMBERS = STORY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>All Different</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_FEATURE_COUNT = STORY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>All Different</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATION_COUNT = STORY_ELEMENT_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link stories.impl.Node_stateImpl <em>Node state</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see stories.impl.Node_stateImpl
	 * @see stories.impl.StoriesPackageImpl#getNode_state()
	 * @generated
	 */
	int NODE_STATE = 10;

	/**
	 * The feature id for the '<em><b>Classified in</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_STATE__CLASSIFIED_IN = 0;

	/**
	 * The feature id for the '<em><b>Not classified in</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_STATE__NOT_CLASSIFIED_IN = 1;

	/**
	 * The feature id for the '<em><b>Anti Rigid Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_STATE__ANTI_RIGID_CLASSES = 2;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_STATE__LABEL = 3;

	/**
	 * The number of structural features of the '<em>Node state</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_STATE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Node state</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_STATE_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link stories.Story <em>Story</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Story</em>'.
	 * @see stories.Story
	 * @generated
	 */
	EClass getStory();

	/**
	 * Returns the meta object for the containment reference list '{@link stories.Story#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see stories.Story#getElements()
	 * @see #getStory()
	 * @generated
	 */
	EReference getStory_Elements();

	/**
	 * Returns the meta object for the attribute '{@link stories.Story#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see stories.Story#getLabel()
	 * @see #getStory()
	 * @generated
	 */
	EAttribute getStory_Label();

	/**
	 * Returns the meta object for class '{@link stories.Individual <em>Individual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Individual</em>'.
	 * @see stories.Individual
	 * @generated
	 */
	EClass getIndividual();

	/**
	 * Returns the meta object for the reference list '{@link stories.Individual#getDifferent_from <em>Different from</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Different from</em>'.
	 * @see stories.Individual#getDifferent_from()
	 * @see #getIndividual()
	 * @generated
	 */
	EReference getIndividual_Different_from();

	/**
	 * Returns the meta object for the reference list '{@link stories.Individual#getSame_as <em>Same as</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Same as</em>'.
	 * @see stories.Individual#getSame_as()
	 * @see #getIndividual()
	 * @generated
	 */
	EReference getIndividual_Same_as();

	/**
	 * Returns the meta object for the reference list '{@link stories.Individual#getPresent_in <em>Present in</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Present in</em>'.
	 * @see stories.Individual#getPresent_in()
	 * @see #getIndividual()
	 * @generated
	 */
	EReference getIndividual_Present_in();

	/**
	 * Returns the meta object for the reference list '{@link stories.Individual#getAbsent_from <em>Absent from</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Absent from</em>'.
	 * @see stories.Individual#getAbsent_from()
	 * @see #getIndividual()
	 * @generated
	 */
	EReference getIndividual_Absent_from();

	/**
	 * Returns the meta object for class '{@link stories.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see stories.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the containment reference list '{@link stories.Node#getPerformed <em>Performed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Performed</em>'.
	 * @see stories.Node#getPerformed()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Performed();

	/**
	 * Returns the meta object for the reference list '{@link stories.Node#getInstance_of <em>Instance of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Instance of</em>'.
	 * @see stories.Node#getInstance_of()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Instance_of();

	/**
	 * Returns the meta object for the containment reference list '{@link stories.Node#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see stories.Node#getStates()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_States();

	/**
	 * Returns the meta object for the reference list '{@link stories.Node#getNot_instance_of <em>Not instance of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Not instance of</em>'.
	 * @see stories.Node#getNot_instance_of()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Not_instance_of();

	/**
	 * Returns the meta object for class '{@link stories.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link</em>'.
	 * @see stories.Link
	 * @generated
	 */
	EClass getLink();

	/**
	 * Returns the meta object for the reference '{@link stories.Link#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see stories.Link#getSource()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Source();

	/**
	 * Returns the meta object for the reference '{@link stories.Link#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see stories.Link#getTarget()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Target();

	/**
	 * Returns the meta object for the reference list '{@link stories.Link#getInstance_of <em>Instance of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Instance of</em>'.
	 * @see stories.Link#getInstance_of()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Instance_of();

	/**
	 * Returns the meta object for the reference list '{@link stories.Link#getNot_instance_of <em>Not instance of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Not instance of</em>'.
	 * @see stories.Link#getNot_instance_of()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Not_instance_of();

	/**
	 * Returns the meta object for class '{@link stories.World <em>World</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>World</em>'.
	 * @see stories.World
	 * @generated
	 */
	EClass getWorld();

	/**
	 * Returns the meta object for the reference list '{@link stories.World#getEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Enabled</em>'.
	 * @see stories.World#getEnabled()
	 * @see #getWorld()
	 * @generated
	 */
	EReference getWorld_Enabled();

	/**
	 * Returns the meta object for the reference list '{@link stories.World#getBrought_about_by <em>Brought about by</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Brought about by</em>'.
	 * @see stories.World#getBrought_about_by()
	 * @see #getWorld()
	 * @generated
	 */
	EReference getWorld_Brought_about_by();

	/**
	 * Returns the meta object for the reference '{@link stories.World#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Next</em>'.
	 * @see stories.World#getNext()
	 * @see #getWorld()
	 * @generated
	 */
	EReference getWorld_Next();

	/**
	 * Returns the meta object for the reference '{@link stories.World#getPrev <em>Prev</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Prev</em>'.
	 * @see stories.World#getPrev()
	 * @see #getWorld()
	 * @generated
	 */
	EReference getWorld_Prev();

	/**
	 * Returns the meta object for class '{@link stories.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see stories.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for the reference list '{@link stories.Event#getDirectly_causes <em>Directly causes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Directly causes</em>'.
	 * @see stories.Event#getDirectly_causes()
	 * @see #getEvent()
	 * @generated
	 */
	EReference getEvent_Directly_causes();

	/**
	 * Returns the meta object for the reference list '{@link stories.Event#getCauses <em>Causes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Causes</em>'.
	 * @see stories.Event#getCauses()
	 * @see #getEvent()
	 * @generated
	 */
	EReference getEvent_Causes();

	/**
	 * Returns the meta object for the reference '{@link stories.Event#getBrings_about <em>Brings about</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Brings about</em>'.
	 * @see stories.Event#getBrings_about()
	 * @see #getEvent()
	 * @generated
	 */
	EReference getEvent_Brings_about();

	/**
	 * Returns the meta object for the reference '{@link stories.Event#getHappened_in <em>Happened in</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Happened in</em>'.
	 * @see stories.Event#getHappened_in()
	 * @see #getEvent()
	 * @generated
	 */
	EReference getEvent_Happened_in();

	/**
	 * Returns the meta object for the reference list '{@link stories.Event#getHas_part <em>Has part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Has part</em>'.
	 * @see stories.Event#getHas_part()
	 * @see #getEvent()
	 * @generated
	 */
	EReference getEvent_Has_part();

	/**
	 * Returns the meta object for class '{@link stories.Story_element <em>Story element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Story element</em>'.
	 * @see stories.Story_element
	 * @generated
	 */
	EClass getStory_element();

	/**
	 * Returns the meta object for the attribute '{@link stories.Story_element#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see stories.Story_element#getLabel()
	 * @see #getStory_element()
	 * @generated
	 */
	EAttribute getStory_element_Label();

	/**
	 * Returns the meta object for class '{@link stories.Happening <em>Happening</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Happening</em>'.
	 * @see stories.Happening
	 * @generated
	 */
	EClass getHappening();

	/**
	 * Returns the meta object for class '{@link stories.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see stories.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the reference list '{@link stories.Action#getMotivated_by <em>Motivated by</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Motivated by</em>'.
	 * @see stories.Action#getMotivated_by()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Motivated_by();

	/**
	 * Returns the meta object for the container reference '{@link stories.Action#getPerformed_by <em>Performed by</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Performed by</em>'.
	 * @see stories.Action#getPerformed_by()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Performed_by();

	/**
	 * Returns the meta object for class '{@link stories.AllDifferent <em>All Different</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>All Different</em>'.
	 * @see stories.AllDifferent
	 * @generated
	 */
	EClass getAllDifferent();

	/**
	 * Returns the meta object for the reference list '{@link stories.AllDifferent#getDistinct_members <em>Distinct members</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Distinct members</em>'.
	 * @see stories.AllDifferent#getDistinct_members()
	 * @see #getAllDifferent()
	 * @generated
	 */
	EReference getAllDifferent_Distinct_members();

	/**
	 * Returns the meta object for class '{@link stories.Node_state <em>Node state</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node state</em>'.
	 * @see stories.Node_state
	 * @generated
	 */
	EClass getNode_state();

	/**
	 * Returns the meta object for the reference list '{@link stories.Node_state#getClassified_in <em>Classified in</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Classified in</em>'.
	 * @see stories.Node_state#getClassified_in()
	 * @see #getNode_state()
	 * @generated
	 */
	EReference getNode_state_Classified_in();

	/**
	 * Returns the meta object for the reference list '{@link stories.Node_state#getNot_classified_in <em>Not classified in</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Not classified in</em>'.
	 * @see stories.Node_state#getNot_classified_in()
	 * @see #getNode_state()
	 * @generated
	 */
	EReference getNode_state_Not_classified_in();

	/**
	 * Returns the meta object for the reference list '{@link stories.Node_state#getAntiRigidClasses <em>Anti Rigid Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Anti Rigid Classes</em>'.
	 * @see stories.Node_state#getAntiRigidClasses()
	 * @see #getNode_state()
	 * @generated
	 */
	EReference getNode_state_AntiRigidClasses();

	/**
	 * Returns the meta object for the attribute '{@link stories.Node_state#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see stories.Node_state#getLabel()
	 * @see #getNode_state()
	 * @generated
	 */
	EAttribute getNode_state_Label();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StoriesFactory getStoriesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link stories.impl.StoryImpl <em>Story</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see stories.impl.StoryImpl
		 * @see stories.impl.StoriesPackageImpl#getStory()
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
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORY__LABEL = eINSTANCE.getStory_Label();

		/**
		 * The meta object literal for the '{@link stories.impl.IndividualImpl <em>Individual</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see stories.impl.IndividualImpl
		 * @see stories.impl.StoriesPackageImpl#getIndividual()
		 * @generated
		 */
		EClass INDIVIDUAL = eINSTANCE.getIndividual();

		/**
		 * The meta object literal for the '<em><b>Different from</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INDIVIDUAL__DIFFERENT_FROM = eINSTANCE.getIndividual_Different_from();

		/**
		 * The meta object literal for the '<em><b>Same as</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INDIVIDUAL__SAME_AS = eINSTANCE.getIndividual_Same_as();

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
		 * The meta object literal for the '{@link stories.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see stories.impl.NodeImpl
		 * @see stories.impl.StoriesPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Performed</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__PERFORMED = eINSTANCE.getNode_Performed();

		/**
		 * The meta object literal for the '<em><b>Instance of</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__INSTANCE_OF = eINSTANCE.getNode_Instance_of();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__STATES = eINSTANCE.getNode_States();

		/**
		 * The meta object literal for the '<em><b>Not instance of</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__NOT_INSTANCE_OF = eINSTANCE.getNode_Not_instance_of();

		/**
		 * The meta object literal for the '{@link stories.impl.LinkImpl <em>Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see stories.impl.LinkImpl
		 * @see stories.impl.StoriesPackageImpl#getLink()
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
		 * The meta object literal for the '<em><b>Instance of</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__INSTANCE_OF = eINSTANCE.getLink_Instance_of();

		/**
		 * The meta object literal for the '<em><b>Not instance of</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__NOT_INSTANCE_OF = eINSTANCE.getLink_Not_instance_of();

		/**
		 * The meta object literal for the '{@link stories.impl.WorldImpl <em>World</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see stories.impl.WorldImpl
		 * @see stories.impl.StoriesPackageImpl#getWorld()
		 * @generated
		 */
		EClass WORLD = eINSTANCE.getWorld();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORLD__ENABLED = eINSTANCE.getWorld_Enabled();

		/**
		 * The meta object literal for the '<em><b>Brought about by</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORLD__BROUGHT_ABOUT_BY = eINSTANCE.getWorld_Brought_about_by();

		/**
		 * The meta object literal for the '<em><b>Next</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORLD__NEXT = eINSTANCE.getWorld_Next();

		/**
		 * The meta object literal for the '<em><b>Prev</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORLD__PREV = eINSTANCE.getWorld_Prev();

		/**
		 * The meta object literal for the '{@link stories.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see stories.impl.EventImpl
		 * @see stories.impl.StoriesPackageImpl#getEvent()
		 * @generated
		 */
		EClass EVENT = eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '<em><b>Directly causes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT__DIRECTLY_CAUSES = eINSTANCE.getEvent_Directly_causes();

		/**
		 * The meta object literal for the '<em><b>Causes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT__CAUSES = eINSTANCE.getEvent_Causes();

		/**
		 * The meta object literal for the '<em><b>Brings about</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT__BRINGS_ABOUT = eINSTANCE.getEvent_Brings_about();

		/**
		 * The meta object literal for the '<em><b>Happened in</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT__HAPPENED_IN = eINSTANCE.getEvent_Happened_in();

		/**
		 * The meta object literal for the '<em><b>Has part</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT__HAS_PART = eINSTANCE.getEvent_Has_part();

		/**
		 * The meta object literal for the '{@link stories.impl.Story_elementImpl <em>Story element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see stories.impl.Story_elementImpl
		 * @see stories.impl.StoriesPackageImpl#getStory_element()
		 * @generated
		 */
		EClass STORY_ELEMENT = eINSTANCE.getStory_element();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORY_ELEMENT__LABEL = eINSTANCE.getStory_element_Label();

		/**
		 * The meta object literal for the '{@link stories.impl.HappeningImpl <em>Happening</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see stories.impl.HappeningImpl
		 * @see stories.impl.StoriesPackageImpl#getHappening()
		 * @generated
		 */
		EClass HAPPENING = eINSTANCE.getHappening();

		/**
		 * The meta object literal for the '{@link stories.impl.ActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see stories.impl.ActionImpl
		 * @see stories.impl.StoriesPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '<em><b>Motivated by</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__MOTIVATED_BY = eINSTANCE.getAction_Motivated_by();

		/**
		 * The meta object literal for the '<em><b>Performed by</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__PERFORMED_BY = eINSTANCE.getAction_Performed_by();

		/**
		 * The meta object literal for the '{@link stories.impl.AllDifferentImpl <em>All Different</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see stories.impl.AllDifferentImpl
		 * @see stories.impl.StoriesPackageImpl#getAllDifferent()
		 * @generated
		 */
		EClass ALL_DIFFERENT = eINSTANCE.getAllDifferent();

		/**
		 * The meta object literal for the '<em><b>Distinct members</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALL_DIFFERENT__DISTINCT_MEMBERS = eINSTANCE.getAllDifferent_Distinct_members();

		/**
		 * The meta object literal for the '{@link stories.impl.Node_stateImpl <em>Node state</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see stories.impl.Node_stateImpl
		 * @see stories.impl.StoriesPackageImpl#getNode_state()
		 * @generated
		 */
		EClass NODE_STATE = eINSTANCE.getNode_state();

		/**
		 * The meta object literal for the '<em><b>Classified in</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_STATE__CLASSIFIED_IN = eINSTANCE.getNode_state_Classified_in();

		/**
		 * The meta object literal for the '<em><b>Not classified in</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_STATE__NOT_CLASSIFIED_IN = eINSTANCE.getNode_state_Not_classified_in();

		/**
		 * The meta object literal for the '<em><b>Anti Rigid Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_STATE__ANTI_RIGID_CLASSES = eINSTANCE.getNode_state_AntiRigidClasses();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_STATE__LABEL = eINSTANCE.getNode_state_Label();

	}

} //StoriesPackage
