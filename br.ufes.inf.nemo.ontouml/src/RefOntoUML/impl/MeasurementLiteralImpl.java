package RefOntoUML.impl;


import RefOntoUML.MeasurementLiteral;
import RefOntoUML.MeasurementRegion;
import RefOntoUML.RefOntoUMLPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measurement Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.MeasurementLiteralImpl#getGroundingRegion <em>Grounding Region</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasurementLiteralImpl extends EnumerationLiteralImpl implements MeasurementLiteral {
	/**
	 * The cached value of the '{@link #getGroundingRegion() <em>Grounding Region</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroundingRegion()
	 * @generated
	 * @ordered
	 */
	protected MeasurementRegion groundingRegion;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasurementLiteralImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getMeasurementLiteral();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementRegion getGroundingRegion() {
		return groundingRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroundingRegion(MeasurementRegion newGroundingRegion, NotificationChain msgs) {
		MeasurementRegion oldGroundingRegion = groundingRegion;
		groundingRegion = newGroundingRegion;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_LITERAL__GROUNDING_REGION, oldGroundingRegion, newGroundingRegion);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroundingRegion(MeasurementRegion newGroundingRegion) {
		if (newGroundingRegion != groundingRegion) {
			NotificationChain msgs = null;
			if (groundingRegion != null)
				msgs = ((InternalEObject)groundingRegion).eInverseRemove(this, RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL, MeasurementRegion.class, msgs);
			if (newGroundingRegion != null)
				msgs = ((InternalEObject)newGroundingRegion).eInverseAdd(this, RefOntoUMLPackage.MEASUREMENT_REGION__GROUNDED_LITERAL, MeasurementRegion.class, msgs);
			msgs = basicSetGroundingRegion(newGroundingRegion, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_LITERAL__GROUNDING_REGION, newGroundingRegion, newGroundingRegion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.MEASUREMENT_LITERAL__GROUNDING_REGION:
				if (groundingRegion != null)
					msgs = ((InternalEObject)groundingRegion).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RefOntoUMLPackage.MEASUREMENT_LITERAL__GROUNDING_REGION, null, msgs);
				return basicSetGroundingRegion((MeasurementRegion)otherEnd, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_LITERAL__GROUNDING_REGION:
				return basicSetGroundingRegion(null, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_LITERAL__GROUNDING_REGION:
				return getGroundingRegion();
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
			case RefOntoUMLPackage.MEASUREMENT_LITERAL__GROUNDING_REGION:
				setGroundingRegion((MeasurementRegion)newValue);
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
			case RefOntoUMLPackage.MEASUREMENT_LITERAL__GROUNDING_REGION:
				setGroundingRegion((MeasurementRegion)null);
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
			case RefOntoUMLPackage.MEASUREMENT_LITERAL__GROUNDING_REGION:
				return groundingRegion != null;
		}
		return super.eIsSet(featureID);
	}

} //MeasurementLiteralImpl