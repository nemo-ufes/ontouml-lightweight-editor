/**
 */
package stories.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.EcoreUtil.ExternalCrossReferencer;
import stories.Link;
import stories.Node;
import stories.StoriesFactory;
import stories.StoriesPackage;
import stories.Story;
import stories.Story_element;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Story</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link stories.impl.StoryImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link stories.impl.StoryImpl#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StoryImpl extends MinimalEObjectImpl.Container implements Story {
	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<Story_element> elements;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = "story";
	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StoriesPackage.Literals.STORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Story_element> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentEList<Story_element>(Story_element.class, this, StoriesPackage.STORY__ELEMENTS);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StoriesPackage.STORY__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StoriesPackage.STORY__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StoriesPackage.STORY__ELEMENTS:
				return getElements();
			case StoriesPackage.STORY__LABEL:
				return getLabel();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StoriesPackage.STORY__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends Story_element>)newValue);
				return;
			case StoriesPackage.STORY__LABEL:
				setLabel((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StoriesPackage.STORY__ELEMENTS:
				getElements().clear();
				return;
			case StoriesPackage.STORY__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StoriesPackage.STORY__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case StoriesPackage.STORY__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
		}
		return super.eIsSet(featureID);
	}
	
	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		//StringBuffer result = new StringBuffer(super.toString());
		//result.append(" (label: ");
		//result.append(label);
		//result.append(')');
		//return result.toString();
		return this.label;
	}

	public String predicateHead(){
		String head = "pred "+this.getLabel()+" [";
		for(Story_element se : this.getElements()){
			if("Node".equals(se.eClass().getName())){
				head = head+se.getLabel()+":one Object+Property,";
			}else if("World".equals(se.eClass().getName())){
				head = head+se.getLabel()+":one World,";
			}
			
		}
		return head + "]{"+'\n';
	}
	
	
	
	/**
	 * <!-- begin-user-doc -->
	 * generates the Alloy predicates that formalize this node's class instantiations
	 * these predicates will be called in the final total predicate
	 * <!-- end-user-doc -->
	 * 
	 */
	public String generatePredicates(){
		
		String predicate = this.predicateHead();
		for(Story_element se : this.getElements()){
				//predicate = predicate + '\t'+ this.getLabel() + " in World." +c.getName()+'\n';
			//if("Individual".equals(se.eClass().getName())){
				//((Individual)se).;
			if("Node".equals(se.eClass().getName())){
				predicate = predicate+((Node)se).static_classification();
				predicate = predicate+((Node)se).existance();
				predicate = predicate+((Node)se).identity();
				predicate = predicate+((Node)se).states();
			}
			if("Link".equals(se.eClass().getName())){
				Link l = ((Link)se);
				predicate = predicate + l.existance();
			}
			//}
		}
		return predicate+"}";
	}
	
	public Story mergeReferences(){
		int counter = 0; 
		
		//set of all stories. This story plus any nested story
		Set<Story> all_stories = new HashSet<Story>();
		all_stories.add(this);
		
		//the story that combines this and all nested stories
		Story merged = StoriesFactory.eINSTANCE.createStory();
		//here we look for any external references in the story. They may be other story's elements or classes from a model
		Map<EObject, Collection<EStructuralFeature.Setting>> map = ExternalCrossReferencer.find(this);
		
		for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : map.entrySet()) { 
			EObject proxyEObject = entry. getKey (); 
			if(stories.StoriesPackage.Literals.STORY_ELEMENT.isSuperTypeOf(proxyEObject.eClass())){
				//here we have story elements. We will gather all stories (containers) and create a unique container with all story elements
				
				all_stories.add((Story)proxyEObject.eContainer());
			}
			
		} 
		for(Story s: all_stories){
			 merged.getElements().addAll(s.getElements());
			System.out.println("adding elements...");
		}
		
		for(Story_element se:merged.getElements()){
			System.out.println(se.getLabel());
		}
		
		return merged;
	}
} //StoryImpl
