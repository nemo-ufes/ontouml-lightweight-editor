/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.Scopeable;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scopeable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.ScopeableImpl#getScopeSize <em>Scope Size</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.ScopeableImpl#isIsExactly <em>Is Exactly</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.ScopeableImpl#getSignature <em>Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScopeableImpl extends EObjectImpl implements Scopeable {
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
	 * The default value of the '{@link #isIsExactly() <em>Is Exactly</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsExactly()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_EXACTLY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsExactly() <em>Is Exactly</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsExactly()
	 * @generated
	 * @ordered
	 */
	protected boolean isExactly = IS_EXACTLY_EDEFAULT;

	/**
	 * The default value of the '{@link #getSignature() <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignature()
	 * @generated
	 * @ordered
	 */
	protected static final String SIGNATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSignature() <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignature()
	 * @generated
	 * @ordered
	 */
	protected String signature = SIGNATURE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScopeableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.SCOPEABLE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.SCOPEABLE__SCOPE_SIZE, oldScopeSize, scopeSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsExactly() {
		return isExactly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsExactly(boolean newIsExactly) {
		boolean oldIsExactly = isExactly;
		isExactly = newIsExactly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.SCOPEABLE__IS_EXACTLY, oldIsExactly, isExactly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignature(String newSignature) {
		String oldSignature = signature;
		signature = newSignature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.SCOPEABLE__SIGNATURE, oldSignature, signature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AlloyPackage.SCOPEABLE__SCOPE_SIZE:
				return getScopeSize();
			case AlloyPackage.SCOPEABLE__IS_EXACTLY:
				return isIsExactly();
			case AlloyPackage.SCOPEABLE__SIGNATURE:
				return getSignature();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AlloyPackage.SCOPEABLE__SCOPE_SIZE:
				setScopeSize((Integer)newValue);
				return;
			case AlloyPackage.SCOPEABLE__IS_EXACTLY:
				setIsExactly((Boolean)newValue);
				return;
			case AlloyPackage.SCOPEABLE__SIGNATURE:
				setSignature((String)newValue);
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
			case AlloyPackage.SCOPEABLE__SCOPE_SIZE:
				setScopeSize(SCOPE_SIZE_EDEFAULT);
				return;
			case AlloyPackage.SCOPEABLE__IS_EXACTLY:
				setIsExactly(IS_EXACTLY_EDEFAULT);
				return;
			case AlloyPackage.SCOPEABLE__SIGNATURE:
				setSignature(SIGNATURE_EDEFAULT);
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
			case AlloyPackage.SCOPEABLE__SCOPE_SIZE:
				return scopeSize != SCOPE_SIZE_EDEFAULT;
			case AlloyPackage.SCOPEABLE__IS_EXACTLY:
				return isExactly != IS_EXACTLY_EDEFAULT;
			case AlloyPackage.SCOPEABLE__SIGNATURE:
				return SIGNATURE_EDEFAULT == null ? signature != null : !SIGNATURE_EDEFAULT.equals(signature);
		}
		return super.eIsSet(featureID);
	}

	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (scopeSize: ");
		result.append(scopeSize);
		result.append(", isExactly: ");
		result.append(isExactly);
		result.append(", signature: ");
		result.append(signature);
		result.append(')');
		return result.toString();
	}

} //ScopeableImpl
