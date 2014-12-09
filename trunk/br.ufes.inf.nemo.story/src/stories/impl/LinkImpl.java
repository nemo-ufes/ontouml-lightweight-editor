/**
 */
package stories.impl;

import RefOntoUML.Association;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import stories.Link;
import stories.Node;
import stories.StoriesPackage;
import stories.World;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link stories.impl.LinkImpl#getSource <em>Source</em>}</li>
 *   <li>{@link stories.impl.LinkImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link stories.impl.LinkImpl#getInstance_of <em>Instance of</em>}</li>
 *   <li>{@link stories.impl.LinkImpl#getNot_instance_of <em>Not instance of</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkImpl extends IndividualImpl implements Link {
	protected static final String LABEL_EDEFAULT = "Link";
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Node source;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Node target;

	/**
	 * The cached value of the '{@link #getInstance_of() <em>Instance of</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstance_of()
	 * @generated
	 * @ordered
	 */
	protected EList<Association> instance_of;

	/**
	 * The cached value of the '{@link #getNot_instance_of() <em>Not instance of</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNot_instance_of()
	 * @generated
	 * @ordered
	 */
	protected EList<Association> not_instance_of;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkImpl() {
		super();
	}
	
	@Override
	protected String getDefaultLabel() {
		
		return LinkImpl.LABEL_EDEFAULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StoriesPackage.Literals.LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (Node)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StoriesPackage.LINK__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Node newSource) {
		Node oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StoriesPackage.LINK__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Node)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StoriesPackage.LINK__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Node newTarget) {
		Node oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StoriesPackage.LINK__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getInstance_of() {
		if (instance_of == null) {
			instance_of = new EObjectResolvingEList<Association>(Association.class, this, StoriesPackage.LINK__INSTANCE_OF);
		}
		return instance_of;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getNot_instance_of() {
		if (not_instance_of == null) {
			not_instance_of = new EObjectResolvingEList<Association>(Association.class, this, StoriesPackage.LINK__NOT_INSTANCE_OF);
		}
		return not_instance_of;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StoriesPackage.LINK__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case StoriesPackage.LINK__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case StoriesPackage.LINK__INSTANCE_OF:
				return getInstance_of();
			case StoriesPackage.LINK__NOT_INSTANCE_OF:
				return getNot_instance_of();
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
			case StoriesPackage.LINK__SOURCE:
				setSource((Node)newValue);
				return;
			case StoriesPackage.LINK__TARGET:
				setTarget((Node)newValue);
				return;
			case StoriesPackage.LINK__INSTANCE_OF:
				getInstance_of().clear();
				getInstance_of().addAll((Collection<? extends Association>)newValue);
				return;
			case StoriesPackage.LINK__NOT_INSTANCE_OF:
				getNot_instance_of().clear();
				getNot_instance_of().addAll((Collection<? extends Association>)newValue);
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
			case StoriesPackage.LINK__SOURCE:
				setSource((Node)null);
				return;
			case StoriesPackage.LINK__TARGET:
				setTarget((Node)null);
				return;
			case StoriesPackage.LINK__INSTANCE_OF:
				getInstance_of().clear();
				return;
			case StoriesPackage.LINK__NOT_INSTANCE_OF:
				getNot_instance_of().clear();
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
			case StoriesPackage.LINK__SOURCE:
				return source != null;
			case StoriesPackage.LINK__TARGET:
				return target != null;
			case StoriesPackage.LINK__INSTANCE_OF:
				return instance_of != null && !instance_of.isEmpty();
			case StoriesPackage.LINK__NOT_INSTANCE_OF:
				return not_instance_of != null && !not_instance_of.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	@Override
	public String existance() {
		String predicate = "";
		String sourceName,targetName;
		if(this.getSource() != null){
			sourceName = this.getSource().getLabel();
		}else{
			sourceName = "Object+Property";//any Node
		}
		
		if(this.getTarget() != null){
			targetName = this.getTarget().getLabel();
		}else{
			targetName = "Object+Property";//any Node
		}
		
		EList<World> world_list = this.getPresent_in();
		EList<World> absent_world_list = this.getAbsent_from();
		if(world_list.isEmpty()){
			if(absent_world_list.isEmpty()){
				predicate = predicate+'\t'+"direct_rel["+sourceName+","+targetName+"]"+'\n';//no definition, just setting it to happen, no constrains
			}					
		}else{
			for(World w: world_list){
				predicate = predicate+'\t'+ "direct_rel_in_w[("+sourceName+")->("+targetName+"),"+w.getLabel()+"]"+'\n';
			}
			
		}
		if(! absent_world_list.isEmpty()){
			for(World w: absent_world_list){
				predicate = predicate+'\t'+ "not direct_rel_in_w[("+sourceName+")->("+targetName+"),"+w.getLabel()+"]"+'\n';
			}
		}
		return predicate;
	}
	
	

} //LinkImpl
