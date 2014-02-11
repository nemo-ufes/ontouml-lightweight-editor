/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.PackageMerge;
import RefOntoUML.RefOntoUMLPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Merge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.PackageMergeImpl#getMergedPackage <em>Merged Package</em>}</li>
 *   <li>{@link RefOntoUML.impl.PackageMergeImpl#getReceivingPackage <em>Receiving Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageMergeImpl extends DirectedRelationshipImpl implements PackageMerge {
	/**
	 * The cached value of the '{@link #getMergedPackage() <em>Merged Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMergedPackage()
	 * @generated
	 * @ordered
	 */
	protected RefOntoUML.Package mergedPackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageMergeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getPackageMerge();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUML.Package getMergedPackage() {
		if (mergedPackage != null && mergedPackage.eIsProxy()) {
			InternalEObject oldMergedPackage = (InternalEObject)mergedPackage;
			mergedPackage = (RefOntoUML.Package)eResolveProxy(oldMergedPackage);
			if (mergedPackage != oldMergedPackage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RefOntoUMLPackage.PACKAGE_MERGE__MERGED_PACKAGE, oldMergedPackage, mergedPackage));
			}
		}
		return mergedPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUML.Package basicGetMergedPackage() {
		return mergedPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMergedPackage(RefOntoUML.Package newMergedPackage) {
		RefOntoUML.Package oldMergedPackage = mergedPackage;
		mergedPackage = newMergedPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.PACKAGE_MERGE__MERGED_PACKAGE, oldMergedPackage, mergedPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUML.Package getReceivingPackage() {
		if (eContainerFeatureID() != RefOntoUMLPackage.PACKAGE_MERGE__RECEIVING_PACKAGE) return null;
		return (RefOntoUML.Package)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReceivingPackage(RefOntoUML.Package newReceivingPackage, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newReceivingPackage, RefOntoUMLPackage.PACKAGE_MERGE__RECEIVING_PACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReceivingPackage(RefOntoUML.Package newReceivingPackage) {
		if (newReceivingPackage != eInternalContainer() || (eContainerFeatureID() != RefOntoUMLPackage.PACKAGE_MERGE__RECEIVING_PACKAGE && newReceivingPackage != null)) {
			if (EcoreUtil.isAncestor(this, newReceivingPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newReceivingPackage != null)
				msgs = ((InternalEObject)newReceivingPackage).eInverseAdd(this, RefOntoUMLPackage.PACKAGE__PACKAGE_MERGE, RefOntoUML.Package.class, msgs);
			msgs = basicSetReceivingPackage(newReceivingPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.PACKAGE_MERGE__RECEIVING_PACKAGE, newReceivingPackage, newReceivingPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.PACKAGE_MERGE__RECEIVING_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetReceivingPackage((RefOntoUML.Package)otherEnd, msgs);
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
			case RefOntoUMLPackage.PACKAGE_MERGE__RECEIVING_PACKAGE:
				return basicSetReceivingPackage(null, msgs);
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
			case RefOntoUMLPackage.PACKAGE_MERGE__RECEIVING_PACKAGE:
				return eInternalContainer().eInverseRemove(this, RefOntoUMLPackage.PACKAGE__PACKAGE_MERGE, RefOntoUML.Package.class, msgs);
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
			case RefOntoUMLPackage.PACKAGE_MERGE__MERGED_PACKAGE:
				if (resolve) return getMergedPackage();
				return basicGetMergedPackage();
			case RefOntoUMLPackage.PACKAGE_MERGE__RECEIVING_PACKAGE:
				return getReceivingPackage();
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
			case RefOntoUMLPackage.PACKAGE_MERGE__MERGED_PACKAGE:
				setMergedPackage((RefOntoUML.Package)newValue);
				return;
			case RefOntoUMLPackage.PACKAGE_MERGE__RECEIVING_PACKAGE:
				setReceivingPackage((RefOntoUML.Package)newValue);
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
			case RefOntoUMLPackage.PACKAGE_MERGE__MERGED_PACKAGE:
				setMergedPackage((RefOntoUML.Package)null);
				return;
			case RefOntoUMLPackage.PACKAGE_MERGE__RECEIVING_PACKAGE:
				setReceivingPackage((RefOntoUML.Package)null);
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
			case RefOntoUMLPackage.PACKAGE_MERGE__MERGED_PACKAGE:
				return mergedPackage != null;
			case RefOntoUMLPackage.PACKAGE_MERGE__RECEIVING_PACKAGE:
				return getReceivingPackage() != null;
		}
		return super.eIsSet(featureID);
	}

} //PackageMergeImpl
