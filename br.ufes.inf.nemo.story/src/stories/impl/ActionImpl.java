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
import org.eclipse.emf.ecore.util.EcoreUtil;

import stories.Action;
import stories.Event;
import stories.Node;
import stories.StoriesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link stories.impl.ActionImpl#getMotivated_by <em>Motivated by</em>}</li>
 *   <li>{@link stories.impl.ActionImpl#getPerformed_by <em>Performed by</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActionImpl extends EventImpl implements Action {
	/**
	 * The cached value of the '{@link #getMotivated_by() <em>Motivated by</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMotivated_by()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> motivated_by;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StoriesPackage.Literals.ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getMotivated_by() {
		if (motivated_by == null) {
			motivated_by = new EObjectResolvingEList<Event>(Event.class, this, StoriesPackage.ACTION__MOTIVATED_BY);
		}
		return motivated_by;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getPerformed_by() {
		if (eContainerFeatureID() != StoriesPackage.ACTION__PERFORMED_BY) return null;
		return (Node)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPerformed_by(Node newPerformed_by, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPerformed_by, StoriesPackage.ACTION__PERFORMED_BY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerformed_by(Node newPerformed_by) {
		if (newPerformed_by != eInternalContainer() || (eContainerFeatureID() != StoriesPackage.ACTION__PERFORMED_BY && newPerformed_by != null)) {
			if (EcoreUtil.isAncestor(this, newPerformed_by))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPerformed_by != null)
				msgs = ((InternalEObject)newPerformed_by).eInverseAdd(this, StoriesPackage.NODE__PERFORMED, Node.class, msgs);
			msgs = basicSetPerformed_by(newPerformed_by, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StoriesPackage.ACTION__PERFORMED_BY, newPerformed_by, newPerformed_by));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StoriesPackage.ACTION__PERFORMED_BY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPerformed_by((Node)otherEnd, msgs);
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
			case StoriesPackage.ACTION__PERFORMED_BY:
				return basicSetPerformed_by(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case StoriesPackage.ACTION__PERFORMED_BY:
				return eInternalContainer().eInverseRemove(this, StoriesPackage.NODE__PERFORMED, Node.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StoriesPackage.ACTION__MOTIVATED_BY:
				return getMotivated_by();
			case StoriesPackage.ACTION__PERFORMED_BY:
				return getPerformed_by();
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
			case StoriesPackage.ACTION__MOTIVATED_BY:
				getMotivated_by().clear();
				getMotivated_by().addAll((Collection<? extends Event>)newValue);
				return;
			case StoriesPackage.ACTION__PERFORMED_BY:
				setPerformed_by((Node)newValue);
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
			case StoriesPackage.ACTION__MOTIVATED_BY:
				getMotivated_by().clear();
				return;
			case StoriesPackage.ACTION__PERFORMED_BY:
				setPerformed_by((Node)null);
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
			case StoriesPackage.ACTION__MOTIVATED_BY:
				return motivated_by != null && !motivated_by.isEmpty();
			case StoriesPackage.ACTION__PERFORMED_BY:
				return getPerformed_by() != null;
		}
		return super.eIsSet(featureID);
	}

} //ActionImpl
