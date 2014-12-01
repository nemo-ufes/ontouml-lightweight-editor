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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import stories.Event;
import stories.StoriesPackage;
import stories.World;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>World</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link stories.impl.WorldImpl#getEnabled <em>Enabled</em>}</li>
 *   <li>{@link stories.impl.WorldImpl#getBrought_about_by <em>Brought about by</em>}</li>
 *   <li>{@link stories.impl.WorldImpl#getNext <em>Next</em>}</li>
 *   <li>{@link stories.impl.WorldImpl#getPrev <em>Prev</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorldImpl extends Story_elementImpl implements World {
	protected static final String LABEL_EDEFAULT = "World";
	/**
	 * The cached value of the '{@link #getEnabled() <em>Enabled</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnabled()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> enabled;

	/**
	 * The cached value of the '{@link #getBrought_about_by() <em>Brought about by</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBrought_about_by()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> brought_about_by;

	/**
	 * The cached value of the '{@link #getNext() <em>Next</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNext()
	 * @generated
	 * @ordered
	 */
	protected World next;
	/**
	 * The cached value of the '{@link #getPrev() <em>Prev</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrev()
	 * @generated
	 * @ordered
	 */
	protected World prev;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorldImpl() {
		super();
	}
	@Override
	protected String getDefaultLabel() {
		
		return WorldImpl.LABEL_EDEFAULT;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StoriesPackage.Literals.WORLD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getEnabled() {
		if (enabled == null) {
			enabled = new EObjectWithInverseResolvingEList<Event>(Event.class, this, StoriesPackage.WORLD__ENABLED, StoriesPackage.EVENT__HAPPENED_IN);
		}
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getBrought_about_by() {
		if (brought_about_by == null) {
			brought_about_by = new EObjectWithInverseResolvingEList<Event>(Event.class, this, StoriesPackage.WORLD__BROUGHT_ABOUT_BY, StoriesPackage.EVENT__BRINGS_ABOUT);
		}
		return brought_about_by;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public World getNext() {
		if (next != null && next.eIsProxy()) {
			InternalEObject oldNext = (InternalEObject)next;
			next = (World)eResolveProxy(oldNext);
			if (next != oldNext) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StoriesPackage.WORLD__NEXT, oldNext, next));
			}
		}
		return next;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public World basicGetNext() {
		return next;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNext(World newNext, NotificationChain msgs) {
		World oldNext = next;
		next = newNext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StoriesPackage.WORLD__NEXT, oldNext, newNext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNext(World newNext) {
		if (newNext != next) {
			NotificationChain msgs = null;
			if (next != null)
				msgs = ((InternalEObject)next).eInverseRemove(this, StoriesPackage.WORLD__PREV, World.class, msgs);
			if (newNext != null)
				msgs = ((InternalEObject)newNext).eInverseAdd(this, StoriesPackage.WORLD__PREV, World.class, msgs);
			msgs = basicSetNext(newNext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StoriesPackage.WORLD__NEXT, newNext, newNext));
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public World getPrev() {
		if (prev != null && prev.eIsProxy()) {
			InternalEObject oldPrev = (InternalEObject)prev;
			prev = (World)eResolveProxy(oldPrev);
			if (prev != oldPrev) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StoriesPackage.WORLD__PREV, oldPrev, prev));
			}
		}
		return prev;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public World basicGetPrev() {
		return prev;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrev(World newPrev, NotificationChain msgs) {
		World oldPrev = prev;
		prev = newPrev;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StoriesPackage.WORLD__PREV, oldPrev, newPrev);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrev(World newPrev) {
		if (newPrev != prev) {
			NotificationChain msgs = null;
			if (prev != null)
				msgs = ((InternalEObject)prev).eInverseRemove(this, StoriesPackage.WORLD__NEXT, World.class, msgs);
			if (newPrev != null)
				msgs = ((InternalEObject)newPrev).eInverseAdd(this, StoriesPackage.WORLD__NEXT, World.class, msgs);
			msgs = basicSetPrev(newPrev, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StoriesPackage.WORLD__PREV, newPrev, newPrev));
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
			case StoriesPackage.WORLD__ENABLED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEnabled()).basicAdd(otherEnd, msgs);
			case StoriesPackage.WORLD__BROUGHT_ABOUT_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBrought_about_by()).basicAdd(otherEnd, msgs);
			case StoriesPackage.WORLD__NEXT:
				if (next != null)
					msgs = ((InternalEObject)next).eInverseRemove(this, StoriesPackage.WORLD__PREV, World.class, msgs);
				return basicSetNext((World)otherEnd, msgs);
			case StoriesPackage.WORLD__PREV:
				if (prev != null)
					msgs = ((InternalEObject)prev).eInverseRemove(this, StoriesPackage.WORLD__NEXT, World.class, msgs);
				return basicSetPrev((World)otherEnd, msgs);
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
			case StoriesPackage.WORLD__ENABLED:
				return ((InternalEList<?>)getEnabled()).basicRemove(otherEnd, msgs);
			case StoriesPackage.WORLD__BROUGHT_ABOUT_BY:
				return ((InternalEList<?>)getBrought_about_by()).basicRemove(otherEnd, msgs);
			case StoriesPackage.WORLD__NEXT:
				return basicSetNext(null, msgs);
			case StoriesPackage.WORLD__PREV:
				return basicSetPrev(null, msgs);
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
			case StoriesPackage.WORLD__ENABLED:
				return getEnabled();
			case StoriesPackage.WORLD__BROUGHT_ABOUT_BY:
				return getBrought_about_by();
			case StoriesPackage.WORLD__NEXT:
				if (resolve) return getNext();
				return basicGetNext();
			case StoriesPackage.WORLD__PREV:
				if (resolve) return getPrev();
				return basicGetPrev();
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
			case StoriesPackage.WORLD__ENABLED:
				getEnabled().clear();
				getEnabled().addAll((Collection<? extends Event>)newValue);
				return;
			case StoriesPackage.WORLD__BROUGHT_ABOUT_BY:
				getBrought_about_by().clear();
				getBrought_about_by().addAll((Collection<? extends Event>)newValue);
				return;
			case StoriesPackage.WORLD__NEXT:
				setNext((World)newValue);
				return;
			case StoriesPackage.WORLD__PREV:
				setPrev((World)newValue);
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
			case StoriesPackage.WORLD__ENABLED:
				getEnabled().clear();
				return;
			case StoriesPackage.WORLD__BROUGHT_ABOUT_BY:
				getBrought_about_by().clear();
				return;
			case StoriesPackage.WORLD__NEXT:
				setNext((World)null);
				return;
			case StoriesPackage.WORLD__PREV:
				setPrev((World)null);
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
			case StoriesPackage.WORLD__ENABLED:
				return enabled != null && !enabled.isEmpty();
			case StoriesPackage.WORLD__BROUGHT_ABOUT_BY:
				return brought_about_by != null && !brought_about_by.isEmpty();
			case StoriesPackage.WORLD__NEXT:
				return next != null;
			case StoriesPackage.WORLD__PREV:
				return prev != null;
		}
		return super.eIsSet(featureID);
	}

} //WorldImpl
