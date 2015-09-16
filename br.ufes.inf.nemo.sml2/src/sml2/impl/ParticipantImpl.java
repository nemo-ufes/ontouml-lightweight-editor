/**
 */
package sml2.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import sml2.Participant;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Participant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sml2.impl.ParticipantImpl#isImmutable <em>Immutable</em>}</li>
 *   <li>{@link sml2.impl.ParticipantImpl#getMin <em>Min</em>}</li>
 *   <li>{@link sml2.impl.ParticipantImpl#getMax <em>Max</em>}</li>
 *   <li>{@link sml2.impl.ParticipantImpl#getIsImageOf <em>Is Image Of</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ParticipantImpl extends ReferableElementImpl implements Participant {
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
	 * The cached value of the '{@link #getIsImageOf() <em>Is Image Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsImageOf()
	 * @generated
	 * @ordered
	 */
	protected Participant isImageOf;

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
	public Participant getIsImageOf() {
		if (isImageOf != null && isImageOf.eIsProxy()) {
			InternalEObject oldIsImageOf = (InternalEObject)isImageOf;
			isImageOf = (Participant)eResolveProxy(oldIsImageOf);
			if (isImageOf != oldIsImageOf) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.PARTICIPANT__IS_IMAGE_OF, oldIsImageOf, isImageOf));
			}
		}
		return isImageOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Participant basicGetIsImageOf() {
		return isImageOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsImageOf(Participant newIsImageOf) {
		Participant oldIsImageOf = isImageOf;
		isImageOf = newIsImageOf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.PARTICIPANT__IS_IMAGE_OF, oldIsImageOf, isImageOf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Sml2Package.PARTICIPANT__IMMUTABLE:
				return isImmutable();
			case Sml2Package.PARTICIPANT__MIN:
				return getMin();
			case Sml2Package.PARTICIPANT__MAX:
				return getMax();
			case Sml2Package.PARTICIPANT__IS_IMAGE_OF:
				if (resolve) return getIsImageOf();
				return basicGetIsImageOf();
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
			case Sml2Package.PARTICIPANT__IMMUTABLE:
				setImmutable((Boolean)newValue);
				return;
			case Sml2Package.PARTICIPANT__MIN:
				setMin((Integer)newValue);
				return;
			case Sml2Package.PARTICIPANT__MAX:
				setMax((Integer)newValue);
				return;
			case Sml2Package.PARTICIPANT__IS_IMAGE_OF:
				setIsImageOf((Participant)newValue);
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
			case Sml2Package.PARTICIPANT__IMMUTABLE:
				setImmutable(IMMUTABLE_EDEFAULT);
				return;
			case Sml2Package.PARTICIPANT__MIN:
				setMin(MIN_EDEFAULT);
				return;
			case Sml2Package.PARTICIPANT__MAX:
				setMax(MAX_EDEFAULT);
				return;
			case Sml2Package.PARTICIPANT__IS_IMAGE_OF:
				setIsImageOf((Participant)null);
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
			case Sml2Package.PARTICIPANT__IMMUTABLE:
				return immutable != IMMUTABLE_EDEFAULT;
			case Sml2Package.PARTICIPANT__MIN:
				return min != MIN_EDEFAULT;
			case Sml2Package.PARTICIPANT__MAX:
				return max != MAX_EDEFAULT;
			case Sml2Package.PARTICIPANT__IS_IMAGE_OF:
				return isImageOf != null;
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
		result.append(" (immutable: ");
		result.append(immutable);
		result.append(", min: ");
		result.append(min);
		result.append(", max: ");
		result.append(max);
		result.append(')');
		return result.toString();
	}

} //ParticipantImpl
