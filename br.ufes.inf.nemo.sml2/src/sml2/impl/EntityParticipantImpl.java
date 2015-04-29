/**
 */
package sml2.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import sml2.EntityParticipant;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity Participant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sml2.impl.EntityParticipantImpl#getIsOfType <em>Is Of Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EntityParticipantImpl extends ParticipantImpl implements EntityParticipant {
	/**
	 * The cached value of the '{@link #getIsOfType() <em>Is Of Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsOfType()
	 * @generated
	 * @ordered
	 */
	protected RefOntoUML.Class isOfType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityParticipantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.ENTITY_PARTICIPANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUML.Class getIsOfType() {
		if (isOfType != null && isOfType.eIsProxy()) {
			InternalEObject oldIsOfType = (InternalEObject)isOfType;
			isOfType = (RefOntoUML.Class)eResolveProxy(oldIsOfType);
			if (isOfType != oldIsOfType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.ENTITY_PARTICIPANT__IS_OF_TYPE, oldIsOfType, isOfType));
			}
		}
		return isOfType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUML.Class basicGetIsOfType() {
		return isOfType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsOfType(RefOntoUML.Class newIsOfType) {
		RefOntoUML.Class oldIsOfType = isOfType;
		isOfType = newIsOfType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.ENTITY_PARTICIPANT__IS_OF_TYPE, oldIsOfType, isOfType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Sml2Package.ENTITY_PARTICIPANT__IS_OF_TYPE:
				if (resolve) return getIsOfType();
				return basicGetIsOfType();
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
			case Sml2Package.ENTITY_PARTICIPANT__IS_OF_TYPE:
				setIsOfType((RefOntoUML.Class)newValue);
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
			case Sml2Package.ENTITY_PARTICIPANT__IS_OF_TYPE:
				setIsOfType((RefOntoUML.Class)null);
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
			case Sml2Package.ENTITY_PARTICIPANT__IS_OF_TYPE:
				return isOfType != null;
		}
		return super.eIsSet(featureID);
	}

} //EntityParticipantImpl
