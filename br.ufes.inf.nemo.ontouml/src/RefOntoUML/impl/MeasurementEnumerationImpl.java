package RefOntoUML.impl;


import RefOntoUML.MeasurementEnumeration;
import RefOntoUML.MeasurementStructure;
import RefOntoUML.RefOntoUMLPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measurement Enumeration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.MeasurementEnumerationImpl#getGroundingStructure <em>Grounding Structure</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasurementEnumerationImpl extends EnumerationImpl implements MeasurementEnumeration {
	/**
	 * The cached value of the '{@link #getGroundingStructure() <em>Grounding Structure</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroundingStructure()
	 * @generated
	 * @ordered
	 */
	protected MeasurementStructure groundingStructure;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasurementEnumerationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getMeasurementEnumeration();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementStructure getGroundingStructure() {
		return groundingStructure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroundingStructure(MeasurementStructure newGroundingStructure, NotificationChain msgs) {
		MeasurementStructure oldGroundingStructure = groundingStructure;
		groundingStructure = newGroundingStructure;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_ENUMERATION__GROUNDING_STRUCTURE, oldGroundingStructure, newGroundingStructure);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroundingStructure(MeasurementStructure newGroundingStructure) {
		if (newGroundingStructure != groundingStructure) {
			NotificationChain msgs = null;
			if (groundingStructure != null)
				msgs = ((InternalEObject)groundingStructure).eInverseRemove(this, RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION, MeasurementStructure.class, msgs);
			if (newGroundingStructure != null)
				msgs = ((InternalEObject)newGroundingStructure).eInverseAdd(this, RefOntoUMLPackage.MEASUREMENT_STRUCTURE__GROUNDED_ENUMERATION, MeasurementStructure.class, msgs);
			msgs = basicSetGroundingStructure(newGroundingStructure, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_ENUMERATION__GROUNDING_STRUCTURE, newGroundingStructure, newGroundingStructure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.MEASUREMENT_ENUMERATION__GROUNDING_STRUCTURE:
				if (groundingStructure != null)
					msgs = ((InternalEObject)groundingStructure).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RefOntoUMLPackage.MEASUREMENT_ENUMERATION__GROUNDING_STRUCTURE, null, msgs);
				return basicSetGroundingStructure((MeasurementStructure)otherEnd, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_ENUMERATION__GROUNDING_STRUCTURE:
				return basicSetGroundingStructure(null, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_ENUMERATION__GROUNDING_STRUCTURE:
				return getGroundingStructure();
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
			case RefOntoUMLPackage.MEASUREMENT_ENUMERATION__GROUNDING_STRUCTURE:
				setGroundingStructure((MeasurementStructure)newValue);
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
			case RefOntoUMLPackage.MEASUREMENT_ENUMERATION__GROUNDING_STRUCTURE:
				setGroundingStructure((MeasurementStructure)null);
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
			case RefOntoUMLPackage.MEASUREMENT_ENUMERATION__GROUNDING_STRUCTURE:
				return groundingStructure != null;
		}
		return super.eIsSet(featureID);
	}

} //MeasurementEnumerationImpl