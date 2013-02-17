/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.GenericScope;
import br.ufes.inf.nemo.alloy.Scopeable;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Scope</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.GenericScopeImpl#getScopeable <em>Scopeable</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.GenericScopeImpl#getScopeSize <em>Scope Size</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenericScopeImpl extends ScopeSpecificationImpl implements GenericScope {
	/**
	 * The cached value of the '{@link #getScopeable() <em>Scopeable</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopeable()
	 * @generated
	 * @ordered
	 */
	protected EList<Scopeable> scopeable;

	/**
	 * The default value of the '{@link #getScopeSize() <em>Scope Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopeSize()
	 * @generated
	 * @ordered
	 */
	protected static final int SCOPE_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getScopeSize() <em>Scope Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopeSize()
	 * @generated
	 * @ordered
	 */
	protected int scopeSize = SCOPE_SIZE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericScopeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.GENERIC_SCOPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings({ })
	public EList<Scopeable> getScopeable() {
		if (scopeable == null) {
			scopeable = new EObjectContainmentEList<Scopeable>(Scopeable.class, this, AlloyPackage.GENERIC_SCOPE__SCOPEABLE);
		}
		return scopeable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getScopeSize() {
		return scopeSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScopeSize(int newScopeSize) {
		int oldScopeSize = scopeSize;
		scopeSize = newScopeSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.GENERIC_SCOPE__SCOPE_SIZE, oldScopeSize, scopeSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlloyPackage.GENERIC_SCOPE__SCOPEABLE:
				return ((InternalEList<?>)getScopeable()).basicRemove(otherEnd, msgs);
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
			case AlloyPackage.GENERIC_SCOPE__SCOPEABLE:
				return getScopeable();
			case AlloyPackage.GENERIC_SCOPE__SCOPE_SIZE:
				return getScopeSize();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AlloyPackage.GENERIC_SCOPE__SCOPEABLE:
				getScopeable().clear();
				getScopeable().addAll((Collection<? extends Scopeable>)newValue);
				return;
			case AlloyPackage.GENERIC_SCOPE__SCOPE_SIZE:
				setScopeSize((Integer)newValue);
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
			case AlloyPackage.GENERIC_SCOPE__SCOPEABLE:
				getScopeable().clear();
				return;
			case AlloyPackage.GENERIC_SCOPE__SCOPE_SIZE:
				setScopeSize(SCOPE_SIZE_EDEFAULT);
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
			case AlloyPackage.GENERIC_SCOPE__SCOPEABLE:
				return scopeable != null && !scopeable.isEmpty();
			case AlloyPackage.GENERIC_SCOPE__SCOPE_SIZE:
				return scopeSize != SCOPE_SIZE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (scopeSize: ");
		result.append(scopeSize);
		result.append(')');
		return result.toString();
	}

} //GenericScopeImpl
