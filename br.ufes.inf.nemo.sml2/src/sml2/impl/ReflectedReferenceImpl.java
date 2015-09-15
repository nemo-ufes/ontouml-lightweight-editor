/**
 */
package sml2.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import sml2.ReferenceNode;
import sml2.ReflectedParticipant;
import sml2.ReflectedReference;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reflected Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sml2.impl.ReflectedReferenceImpl#getOwningReflection <em>Owning Reflection</em>}</li>
 *   <li>{@link sml2.impl.ReflectedReferenceImpl#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReflectedReferenceImpl extends NodeImpl implements ReflectedReference {
	/**
	 * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReference()
	 * @generated
	 * @ordered
	 */
	protected ReferenceNode reference;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReflectedReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.REFLECTED_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReflectedParticipant getOwningReflection() {
		if (eContainerFeatureID() != Sml2Package.REFLECTED_REFERENCE__OWNING_REFLECTION) return null;
		return (ReflectedParticipant)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningReflection(ReflectedParticipant newOwningReflection, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwningReflection, Sml2Package.REFLECTED_REFERENCE__OWNING_REFLECTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwningReflection(ReflectedParticipant newOwningReflection) {
		if (newOwningReflection != eInternalContainer() || (eContainerFeatureID() != Sml2Package.REFLECTED_REFERENCE__OWNING_REFLECTION && newOwningReflection != null)) {
			if (EcoreUtil.isAncestor(this, newOwningReflection))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningReflection != null)
				msgs = ((InternalEObject)newOwningReflection).eInverseAdd(this, Sml2Package.REFLECTED_PARTICIPANT__REFERENCES, ReflectedParticipant.class, msgs);
			msgs = basicSetOwningReflection(newOwningReflection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.REFLECTED_REFERENCE__OWNING_REFLECTION, newOwningReflection, newOwningReflection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceNode getReference() {
		if (reference != null && reference.eIsProxy()) {
			InternalEObject oldReference = (InternalEObject)reference;
			reference = (ReferenceNode)eResolveProxy(oldReference);
			if (reference != oldReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.REFLECTED_REFERENCE__REFERENCE, oldReference, reference));
			}
		}
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceNode basicGetReference() {
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReference(ReferenceNode newReference) {
		ReferenceNode oldReference = reference;
		reference = newReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.REFLECTED_REFERENCE__REFERENCE, oldReference, reference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Sml2Package.REFLECTED_REFERENCE__OWNING_REFLECTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningReflection((ReflectedParticipant)otherEnd, msgs);
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
			case Sml2Package.REFLECTED_REFERENCE__OWNING_REFLECTION:
				return basicSetOwningReflection(null, msgs);
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
			case Sml2Package.REFLECTED_REFERENCE__OWNING_REFLECTION:
				return eInternalContainer().eInverseRemove(this, Sml2Package.REFLECTED_PARTICIPANT__REFERENCES, ReflectedParticipant.class, msgs);
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
			case Sml2Package.REFLECTED_REFERENCE__OWNING_REFLECTION:
				return getOwningReflection();
			case Sml2Package.REFLECTED_REFERENCE__REFERENCE:
				if (resolve) return getReference();
				return basicGetReference();
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
			case Sml2Package.REFLECTED_REFERENCE__OWNING_REFLECTION:
				setOwningReflection((ReflectedParticipant)newValue);
				return;
			case Sml2Package.REFLECTED_REFERENCE__REFERENCE:
				setReference((ReferenceNode)newValue);
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
			case Sml2Package.REFLECTED_REFERENCE__OWNING_REFLECTION:
				setOwningReflection((ReflectedParticipant)null);
				return;
			case Sml2Package.REFLECTED_REFERENCE__REFERENCE:
				setReference((ReferenceNode)null);
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
			case Sml2Package.REFLECTED_REFERENCE__OWNING_REFLECTION:
				return getOwningReflection() != null;
			case Sml2Package.REFLECTED_REFERENCE__REFERENCE:
				return reference != null;
		}
		return super.eIsSet(featureID);
	}

} //ReflectedReferenceImpl
