/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.MeasurementLiteral;
import RefOntoUML.MeasurementRegion;
import RefOntoUML.RefOntoUMLPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measurement Region</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.MeasurementRegionImpl#getGroundedLiteral <em>Grounded Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MeasurementRegionImpl extends ReferenceRegionImpl implements MeasurementRegion {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasurementRegionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getMeasurementRegion();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementLiteral getGroundedLiteral() {
		if (eContainerFeatureID() != RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL) return null;
		return (MeasurementLiteral)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroundedLiteral(MeasurementLiteral newGroundedLiteral, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGroundedLiteral, RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroundedLiteral(MeasurementLiteral newGroundedLiteral) {
		if (newGroundedLiteral != eInternalContainer() || (eContainerFeatureID() != RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL && newGroundedLiteral != null)) {
			if (EcoreUtil.isAncestor(this, newGroundedLiteral))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroundedLiteral != null)
				msgs = ((InternalEObject)newGroundedLiteral).eInverseAdd(this, RefOntoUMLPackage.MEASUREMENT_LITERAL__GROUNDING_REGION, MeasurementLiteral.class, msgs);
			msgs = basicSetGroundedLiteral(newGroundedLiteral, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL, newGroundedLiteral, newGroundedLiteral));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGroundedLiteral((MeasurementLiteral)otherEnd, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL:
				return basicSetGroundedLiteral(null, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL:
				return eInternalContainer().eInverseRemove(this, RefOntoUMLPackage.MEASUREMENT_LITERAL__GROUNDING_REGION, MeasurementLiteral.class, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL:
				return getGroundedLiteral();
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
			case RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL:
				setGroundedLiteral((MeasurementLiteral)newValue);
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
			case RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL:
				setGroundedLiteral((MeasurementLiteral)null);
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
			case RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL:
				return getGroundedLiteral() != null;
		}
		return super.eIsSet(featureID);
	}

} //MeasurementRegionImpl


