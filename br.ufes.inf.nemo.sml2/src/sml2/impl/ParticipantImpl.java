/**
 */
package sml2.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import sml2.Participant;
import sml2.ReflectedParticipant;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Participant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sml2.impl.ParticipantImpl#isShareable <em>Shareable</em>}</li>
 *   <li>{@link sml2.impl.ParticipantImpl#isImmutable <em>Immutable</em>}</li>
 *   <li>{@link sml2.impl.ParticipantImpl#getMin <em>Min</em>}</li>
 *   <li>{@link sml2.impl.ParticipantImpl#getReflection <em>Reflection</em>}</li>
 *   <li>{@link sml2.impl.ParticipantImpl#getMax <em>Max</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ParticipantImpl extends ReferableElementImpl implements Participant {
	/**
	 * The default value of the '{@link #isShareable() <em>Shareable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isShareable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SHAREABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isShareable() <em>Shareable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isShareable()
	 * @generated
	 * @ordered
	 */
	protected boolean shareable = SHAREABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isImmutable() <em>Immutable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImmutable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IMMUTABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isImmutable() <em>Immutable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImmutable()
	 * @generated
	 * @ordered
	 */
	protected boolean immutable = IMMUTABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMin() <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getMin() <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin()
	 * @generated
	 * @ordered
	 */
	protected int min = MIN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReflection() <em>Reflection</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReflection()
	 * @generated
	 * @ordered
	 */
	protected ReflectedParticipant reflection;

	/**
	 * The default value of the '{@link #getMax() <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getMax() <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax()
	 * @generated
	 * @ordered
	 */
	protected int max = MAX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParticipantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.PARTICIPANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isShareable() {
		return shareable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShareable(boolean newShareable) {
		boolean oldShareable = shareable;
		shareable = newShareable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.PARTICIPANT__SHAREABLE, oldShareable, shareable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isImmutable() {
		return immutable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImmutable(boolean newImmutable) {
		boolean oldImmutable = immutable;
		immutable = newImmutable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.PARTICIPANT__IMMUTABLE, oldImmutable, immutable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMin() {
		return min;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMin(int newMin) {
		int oldMin = min;
		min = newMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.PARTICIPANT__MIN, oldMin, min));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReflectedParticipant getReflection() {
		if (reflection != null && reflection.eIsProxy()) {
			InternalEObject oldReflection = (InternalEObject)reflection;
			reflection = (ReflectedParticipant)eResolveProxy(oldReflection);
			if (reflection != oldReflection) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.PARTICIPANT__REFLECTION, oldReflection, reflection));
			}
		}
		return reflection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReflectedParticipant basicGetReflection() {
		return reflection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReflection(ReflectedParticipant newReflection, NotificationChain msgs) {
		ReflectedParticipant oldReflection = reflection;
		reflection = newReflection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Sml2Package.PARTICIPANT__REFLECTION, oldReflection, newReflection);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReflection(ReflectedParticipant newReflection) {
		if (newReflection != reflection) {
			NotificationChain msgs = null;
			if (reflection != null)
				msgs = ((InternalEObject)reflection).eInverseRemove(this, Sml2Package.REFLECTED_PARTICIPANT__PARTICIPANT, ReflectedParticipant.class, msgs);
			if (newReflection != null)
				msgs = ((InternalEObject)newReflection).eInverseAdd(this, Sml2Package.REFLECTED_PARTICIPANT__PARTICIPANT, ReflectedParticipant.class, msgs);
			msgs = basicSetReflection(newReflection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.PARTICIPANT__REFLECTION, newReflection, newReflection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMax() {
		return max;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMax(int newMax) {
		int oldMax = max;
		max = newMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.PARTICIPANT__MAX, oldMax, max));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Sml2Package.PARTICIPANT__REFLECTION:
				if (reflection != null)
					msgs = ((InternalEObject)reflection).eInverseRemove(this, Sml2Package.REFLECTED_PARTICIPANT__PARTICIPANT, ReflectedParticipant.class, msgs);
				return basicSetReflection((ReflectedParticipant)otherEnd, msgs);
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
			case Sml2Package.PARTICIPANT__REFLECTION:
				return basicSetReflection(null, msgs);
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
			case Sml2Package.PARTICIPANT__SHAREABLE:
				return isShareable();
			case Sml2Package.PARTICIPANT__IMMUTABLE:
				return isImmutable();
			case Sml2Package.PARTICIPANT__MIN:
				return getMin();
			case Sml2Package.PARTICIPANT__REFLECTION:
				if (resolve) return getReflection();
				return basicGetReflection();
			case Sml2Package.PARTICIPANT__MAX:
				return getMax();
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
			case Sml2Package.PARTICIPANT__SHAREABLE:
				setShareable((Boolean)newValue);
				return;
			case Sml2Package.PARTICIPANT__IMMUTABLE:
				setImmutable((Boolean)newValue);
				return;
			case Sml2Package.PARTICIPANT__MIN:
				setMin((Integer)newValue);
				return;
			case Sml2Package.PARTICIPANT__REFLECTION:
				setReflection((ReflectedParticipant)newValue);
				return;
			case Sml2Package.PARTICIPANT__MAX:
				setMax((Integer)newValue);
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
			case Sml2Package.PARTICIPANT__SHAREABLE:
				setShareable(SHAREABLE_EDEFAULT);
				return;
			case Sml2Package.PARTICIPANT__IMMUTABLE:
				setImmutable(IMMUTABLE_EDEFAULT);
				return;
			case Sml2Package.PARTICIPANT__MIN:
				setMin(MIN_EDEFAULT);
				return;
			case Sml2Package.PARTICIPANT__REFLECTION:
				setReflection((ReflectedParticipant)null);
				return;
			case Sml2Package.PARTICIPANT__MAX:
				setMax(MAX_EDEFAULT);
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
			case Sml2Package.PARTICIPANT__SHAREABLE:
				return shareable != SHAREABLE_EDEFAULT;
			case Sml2Package.PARTICIPANT__IMMUTABLE:
				return immutable != IMMUTABLE_EDEFAULT;
			case Sml2Package.PARTICIPANT__MIN:
				return min != MIN_EDEFAULT;
			case Sml2Package.PARTICIPANT__REFLECTION:
				return reflection != null;
			case Sml2Package.PARTICIPANT__MAX:
				return max != MAX_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (shareable: ");
		result.append(shareable);
		result.append(", immutable: ");
		result.append(immutable);
		result.append(", min: ");
		result.append(min);
		result.append(", max: ");
		result.append(max);
		result.append(')');
		return result.toString();
	}

} //ParticipantImpl
