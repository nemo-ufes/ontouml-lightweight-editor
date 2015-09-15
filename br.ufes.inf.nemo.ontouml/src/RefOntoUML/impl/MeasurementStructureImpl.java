/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.MeasurementEnumeration;
import RefOntoUML.MeasurementStructure;
import RefOntoUML.RefOntoUMLPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measurement Structure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.MeasurementStructureImpl#getGroundedEnumeration <em>Grounded Enumeration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MeasurementStructureImpl extends ReferenceStructureImpl implements MeasurementStructure {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasurementStructureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getMeasurementStructure();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementEnumeration getGroundedEnumeration() {
		if (eContainerFeatureID() != RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION) return null;
		return (MeasurementEnumeration)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroundedEnumeration(MeasurementEnumeration newGroundedEnumeration, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGroundedEnumeration, RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroundedEnumeration(MeasurementEnumeration newGroundedEnumeration) {
		if (newGroundedEnumeration != eInternalContainer() || (eContainerFeatureID() != RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION && newGroundedEnumeration != null)) {
			if (EcoreUtil.isAncestor(this, newGroundedEnumeration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroundedEnumeration != null)
				msgs = ((InternalEObject)newGroundedEnumeration).eInverseAdd(this, RefOntoUMLPackage.MEASUREMENT_ENUMERATION__GROUNDING_STRUCTURE, MeasurementEnumeration.class, msgs);
			msgs = basicSetGroundedEnumeration(newGroundedEnumeration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION, newGroundedEnumeration, newGroundedEnumeration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGroundedEnumeration((MeasurementEnumeration)otherEnd, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION:
				return basicSetGroundedEnumeration(null, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION:
				return eInternalContainer().eInverseRemove(this, RefOntoUMLPackage.MEASUREMENT_ENUMERATION__GROUNDING_STRUCTURE, MeasurementEnumeration.class, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION:
				return getGroundedEnumeration();
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
			case RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION:
				setGroundedEnumeration((MeasurementEnumeration)newValue);
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
			case RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION:
				setGroundedEnumeration((MeasurementEnumeration)null);
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
			case RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION:
				return getGroundedEnumeration() != null;
		}
		return super.eIsSet(featureID);
	}

} //MeasurementStructureImpl


