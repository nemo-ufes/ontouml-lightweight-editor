/**
 */
package sml2.impl;

import RefOntoUML.Mediation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import sml2.EntityParticipant;
import sml2.Link;
import sml2.RelatorParticipant;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sml2.impl.LinkImpl#getIsOfType <em>Is Of Type</em>}</li>
 *   <li>{@link sml2.impl.LinkImpl#getEntity <em>Entity</em>}</li>
 *   <li>{@link sml2.impl.LinkImpl#getRelator <em>Relator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkImpl extends SituationTypeElementImpl implements Link {
	/**
	 * The cached value of the '{@link #getIsOfType() <em>Is Of Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsOfType()
	 * @generated
	 * @ordered
	 */
	protected Mediation isOfType;

	/**
	 * The cached value of the '{@link #getEntity() <em>Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntity()
	 * @generated
	 * @ordered
	 */
	protected EntityParticipant entity;

	/**
	 * The cached value of the '{@link #getRelator() <em>Relator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelator()
	 * @generated
	 * @ordered
	 */
	protected RelatorParticipant relator;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mediation getIsOfType() {
		if (isOfType != null && isOfType.eIsProxy()) {
			InternalEObject oldIsOfType = (InternalEObject)isOfType;
			isOfType = (Mediation)eResolveProxy(oldIsOfType);
			if (isOfType != oldIsOfType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.LINK__IS_OF_TYPE, oldIsOfType, isOfType));
			}
		}
		return isOfType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mediation basicGetIsOfType() {
		return isOfType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsOfType(Mediation newIsOfType) {
		Mediation oldIsOfType = isOfType;
		isOfType = newIsOfType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.LINK__IS_OF_TYPE, oldIsOfType, isOfType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityParticipant getEntity() {
		if (entity != null && entity.eIsProxy()) {
			InternalEObject oldEntity = (InternalEObject)entity;
			entity = (EntityParticipant)eResolveProxy(oldEntity);
			if (entity != oldEntity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.LINK__ENTITY, oldEntity, entity));
			}
		}
		return entity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityParticipant basicGetEntity() {
		return entity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntity(EntityParticipant newEntity) {
		EntityParticipant oldEntity = entity;
		entity = newEntity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.LINK__ENTITY, oldEntity, entity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelatorParticipant getRelator() {
		if (relator != null && relator.eIsProxy()) {
			InternalEObject oldRelator = (InternalEObject)relator;
			relator = (RelatorParticipant)eResolveProxy(oldRelator);
			if (relator != oldRelator) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.LINK__RELATOR, oldRelator, relator));
			}
		}
		return relator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelatorParticipant basicGetRelator() {
		return relator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRelator(RelatorParticipant newRelator, NotificationChain msgs) {
		RelatorParticipant oldRelator = relator;
		relator = newRelator;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Sml2Package.LINK__RELATOR, oldRelator, newRelator);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRelator(RelatorParticipant newRelator) {
		if (newRelator != relator) {
			NotificationChain msgs = null;
			if (relator != null)
				msgs = ((InternalEObject)relator).eInverseRemove(this, Sml2Package.RELATOR_PARTICIPANT__LINKS, RelatorParticipant.class, msgs);
			if (newRelator != null)
				msgs = ((InternalEObject)newRelator).eInverseAdd(this, Sml2Package.RELATOR_PARTICIPANT__LINKS, RelatorParticipant.class, msgs);
			msgs = basicSetRelator(newRelator, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.LINK__RELATOR, newRelator, newRelator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Sml2Package.LINK__RELATOR:
				if (relator != null)
					msgs = ((InternalEObject)relator).eInverseRemove(this, Sml2Package.RELATOR_PARTICIPANT__LINKS, RelatorParticipant.class, msgs);
				return basicSetRelator((RelatorParticipant)otherEnd, msgs);
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
			case Sml2Package.LINK__RELATOR:
				return basicSetRelator(null, msgs);
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
			case Sml2Package.LINK__IS_OF_TYPE:
				if (resolve) return getIsOfType();
				return basicGetIsOfType();
			case Sml2Package.LINK__ENTITY:
				if (resolve) return getEntity();
				return basicGetEntity();
			case Sml2Package.LINK__RELATOR:
				if (resolve) return getRelator();
				return basicGetRelator();
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
			case Sml2Package.LINK__IS_OF_TYPE:
				setIsOfType((Mediation)newValue);
				return;
			case Sml2Package.LINK__ENTITY:
				setEntity((EntityParticipant)newValue);
				return;
			case Sml2Package.LINK__RELATOR:
				setRelator((RelatorParticipant)newValue);
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
			case Sml2Package.LINK__IS_OF_TYPE:
				setIsOfType((Mediation)null);
				return;
			case Sml2Package.LINK__ENTITY:
				setEntity((EntityParticipant)null);
				return;
			case Sml2Package.LINK__RELATOR:
				setRelator((RelatorParticipant)null);
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
			case Sml2Package.LINK__IS_OF_TYPE:
				return isOfType != null;
			case Sml2Package.LINK__ENTITY:
				return entity != null;
			case Sml2Package.LINK__RELATOR:
				return relator != null;
		}
		return super.eIsSet(featureID);
	}

} //LinkImpl
