/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.Inheritance;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Inheritance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.InheritanceImpl#getSupertype <em>Supertype</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.InheritanceImpl#isIsSubset <em>Is Subset</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.InheritanceImpl#isIsExtension <em>Is Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InheritanceImpl extends EObjectImpl implements Inheritance {
	/**
	 * The default value of the '{@link #getSupertype() <em>Supertype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupertype()
	 * @generated
	 * @ordered
	 */
	protected static final String SUPERTYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSupertype() <em>Supertype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupertype()
	 * @generated
	 * @ordered
	 */
	protected String supertype = SUPERTYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsSubset() <em>Is Subset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSubset()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SUBSET_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsSubset() <em>Is Subset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSubset()
	 * @generated
	 * @ordered
	 */
	protected boolean isSubset = IS_SUBSET_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsExtension() <em>Is Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsExtension()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_EXTENSION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsExtension() <em>Is Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsExtension()
	 * @generated
	 * @ordered
	 */
	protected boolean isExtension = IS_EXTENSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InheritanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.INHERITANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSupertype() {
		return supertype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupertype(String newSupertype) {
		String oldSupertype = supertype;
		supertype = newSupertype;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.INHERITANCE__SUPERTYPE, oldSupertype, supertype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsSubset() {
		return isSubset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsSubset(boolean newIsSubset) {
		boolean oldIsSubset = isSubset;
		isSubset = newIsSubset;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.INHERITANCE__IS_SUBSET, oldIsSubset, isSubset));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsExtension() {
		return isExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsExtension(boolean newIsExtension) {
		boolean oldIsExtension = isExtension;
		isExtension = newIsExtension;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.INHERITANCE__IS_EXTENSION, oldIsExtension, isExtension));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AlloyPackage.INHERITANCE__SUPERTYPE:
				return getSupertype();
			case AlloyPackage.INHERITANCE__IS_SUBSET:
				return isIsSubset();
			case AlloyPackage.INHERITANCE__IS_EXTENSION:
				return isIsExtension();
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
			case AlloyPackage.INHERITANCE__SUPERTYPE:
				setSupertype((String)newValue);
				return;
			case AlloyPackage.INHERITANCE__IS_SUBSET:
				setIsSubset((Boolean)newValue);
				return;
			case AlloyPackage.INHERITANCE__IS_EXTENSION:
				setIsExtension((Boolean)newValue);
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
			case AlloyPackage.INHERITANCE__SUPERTYPE:
				setSupertype(SUPERTYPE_EDEFAULT);
				return;
			case AlloyPackage.INHERITANCE__IS_SUBSET:
				setIsSubset(IS_SUBSET_EDEFAULT);
				return;
			case AlloyPackage.INHERITANCE__IS_EXTENSION:
				setIsExtension(IS_EXTENSION_EDEFAULT);
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
			case AlloyPackage.INHERITANCE__SUPERTYPE:
				return SUPERTYPE_EDEFAULT == null ? supertype != null : !SUPERTYPE_EDEFAULT.equals(supertype);
			case AlloyPackage.INHERITANCE__IS_SUBSET:
				return isSubset != IS_SUBSET_EDEFAULT;
			case AlloyPackage.INHERITANCE__IS_EXTENSION:
				return isExtension != IS_EXTENSION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (supertype: ");
		result.append(supertype);
		result.append(", isSubset: ");
		result.append(isSubset);
		result.append(", isExtension: ");
		result.append(isExtension);
		result.append(')');
		return result.toString();
	}

} //InheritanceImpl
