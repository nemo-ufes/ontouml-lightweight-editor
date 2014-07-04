/**
 */
package stories.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import stories.Event;
import stories.StoriesPackage;
import stories.World;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link stories.impl.EventImpl#getDirectly_causes <em>Directly causes</em>}</li>
 *   <li>{@link stories.impl.EventImpl#getCauses <em>Causes</em>}</li>
 *   <li>{@link stories.impl.EventImpl#getBrings_about <em>Brings about</em>}</li>
 *   <li>{@link stories.impl.EventImpl#getHappened_in <em>Happened in</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EventImpl extends Story_elementImpl implements Event {
	/**
	 * The cached value of the '{@link #getDirectly_causes() <em>Directly causes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirectly_causes()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> directly_causes;

	/**
	 * The cached value of the '{@link #getCauses() <em>Causes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCauses()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> causes;

	/**
	 * The cached value of the '{@link #getBrings_about() <em>Brings about</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBrings_about()
	 * @generated
	 * @ordered
	 */
	protected EList<World> brings_about;

	/**
	 * The cached value of the '{@link #getHappened_in() <em>Happened in</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHappened_in()
	 * @generated
	 * @ordered
	 */
	protected World happened_in;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StoriesPackage.Literals.EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getDirectly_causes() {
		if (directly_causes == null) {
			directly_causes = new EObjectResolvingEList<Event>(Event.class, this, StoriesPackage.EVENT__DIRECTLY_CAUSES);
		}
		return directly_causes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getCauses() {
		if (causes == null) {
			causes = new EObjectResolvingEList<Event>(Event.class, this, StoriesPackage.EVENT__CAUSES);
		}
		return causes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<World> getBrings_about() {
		if (brings_about == null) {
			brings_about = new EObjectWithInverseResolvingEList.ManyInverse<World>(World.class, this, StoriesPackage.EVENT__BRINGS_ABOUT, StoriesPackage.WORLD__BROUGHT_ABOUT_BY);
		}
		return brings_about;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public World getHappened_in() {
		if (happened_in != null && happened_in.eIsProxy()) {
			InternalEObject oldHappened_in = (InternalEObject)happened_in;
			happened_in = (World)eResolveProxy(oldHappened_in);
			if (happened_in != oldHappened_in) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StoriesPackage.EVENT__HAPPENED_IN, oldHappened_in, happened_in));
			}
		}
		return happened_in;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public World basicGetHappened_in() {
		return happened_in;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHappened_in(World newHappened_in, NotificationChain msgs) {
		World oldHappened_in = happened_in;
		happened_in = newHappened_in;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StoriesPackage.EVENT__HAPPENED_IN, oldHappened_in, newHappened_in);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHappened_in(World newHappened_in) {
		if (newHappened_in != happened_in) {
			NotificationChain msgs = null;
			if (happened_in != null)
				msgs = ((InternalEObject)happened_in).eInverseRemove(this, StoriesPackage.WORLD__ENABLED, World.class, msgs);
			if (newHappened_in != null)
				msgs = ((InternalEObject)newHappened_in).eInverseAdd(this, StoriesPackage.WORLD__ENABLED, World.class, msgs);
			msgs = basicSetHappened_in(newHappened_in, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StoriesPackage.EVENT__HAPPENED_IN, newHappened_in, newHappened_in));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StoriesPackage.EVENT__BRINGS_ABOUT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBrings_about()).basicAdd(otherEnd, msgs);
			case StoriesPackage.EVENT__HAPPENED_IN:
				if (happened_in != null)
					msgs = ((InternalEObject)happened_in).eInverseRemove(this, StoriesPackage.WORLD__ENABLED, World.class, msgs);
				return basicSetHappened_in((World)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StoriesPackage.EVENT__BRINGS_ABOUT:
				return ((InternalEList<?>)getBrings_about()).basicRemove(otherEnd, msgs);
			case StoriesPackage.EVENT__HAPPENED_IN:
				return basicSetHappened_in(null, msgs);
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
			case StoriesPackage.EVENT__DIRECTLY_CAUSES:
				return getDirectly_causes();
			case StoriesPackage.EVENT__CAUSES:
				return getCauses();
			case StoriesPackage.EVENT__BRINGS_ABOUT:
				return getBrings_about();
			case StoriesPackage.EVENT__HAPPENED_IN:
				if (resolve) return getHappened_in();
				return basicGetHappened_in();
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
			case StoriesPackage.EVENT__DIRECTLY_CAUSES:
				getDirectly_causes().clear();
				getDirectly_causes().addAll((Collection<? extends Event>)newValue);
				return;
			case StoriesPackage.EVENT__CAUSES:
				getCauses().clear();
				getCauses().addAll((Collection<? extends Event>)newValue);
				return;
			case StoriesPackage.EVENT__BRINGS_ABOUT:
				getBrings_about().clear();
				getBrings_about().addAll((Collection<? extends World>)newValue);
				return;
			case StoriesPackage.EVENT__HAPPENED_IN:
				setHappened_in((World)newValue);
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
			case StoriesPackage.EVENT__DIRECTLY_CAUSES:
				getDirectly_causes().clear();
				return;
			case StoriesPackage.EVENT__CAUSES:
				getCauses().clear();
				return;
			case StoriesPackage.EVENT__BRINGS_ABOUT:
				getBrings_about().clear();
				return;
			case StoriesPackage.EVENT__HAPPENED_IN:
				setHappened_in((World)null);
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
			case StoriesPackage.EVENT__DIRECTLY_CAUSES:
				return directly_causes != null && !directly_causes.isEmpty();
			case StoriesPackage.EVENT__CAUSES:
				return causes != null && !causes.isEmpty();
			case StoriesPackage.EVENT__BRINGS_ABOUT:
				return brings_about != null && !brings_about.isEmpty();
			case StoriesPackage.EVENT__HAPPENED_IN:
				return happened_in != null;
		}
		return super.eIsSet(featureID);
	}

} //EventImpl
