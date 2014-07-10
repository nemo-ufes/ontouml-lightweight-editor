/**
 */
package stories.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import stories.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StoriesFactoryImpl extends EFactoryImpl implements StoriesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StoriesFactory init() {
		try {
			StoriesFactory theStoriesFactory = (StoriesFactory)EPackage.Registry.INSTANCE.getEFactory(StoriesPackage.eNS_URI);
			if (theStoriesFactory != null) {
				return theStoriesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StoriesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StoriesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case StoriesPackage.STORY: return createStory();
			case StoriesPackage.NODE: return createNode();
			case StoriesPackage.LINK: return createLink();
			case StoriesPackage.WORLD: return createWorld();
			case StoriesPackage.HAPPENING: return createHappening();
			case StoriesPackage.ACTION: return createAction();
			case StoriesPackage.ALL_DIFFERENT: return createAllDifferent();
			case StoriesPackage.NODE_STATE: return createNode_state();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Story createStory() {
		StoryImpl story = new StoryImpl();
		return story;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node createNode() {
		NodeImpl node = new NodeImpl();
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Link createLink() {
		LinkImpl link = new LinkImpl();
		return link;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public World createWorld() {
		WorldImpl world = new WorldImpl();
		return world;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Happening createHappening() {
		HappeningImpl happening = new HappeningImpl();
		return happening;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Action createAction() {
		ActionImpl action = new ActionImpl();
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllDifferent createAllDifferent() {
		AllDifferentImpl allDifferent = new AllDifferentImpl();
		return allDifferent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node_state createNode_state() {
		Node_stateImpl node_state = new Node_stateImpl();
		return node_state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StoriesPackage getStoriesPackage() {
		return (StoriesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static StoriesPackage getPackage() {
		return StoriesPackage.eINSTANCE;
	}

} //StoriesFactoryImpl
