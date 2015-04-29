/**
 */
package sml2.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import sml2.SituationParameterReference;
import sml2.SituationParticipant;
import sml2.SituationType;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Situation Participant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sml2.impl.SituationParticipantImpl#getSituationType <em>Situation Type</em>}</li>
 *   <li>{@link sml2.impl.SituationParticipantImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link sml2.impl.SituationParticipantImpl#isIsPast <em>Is Past</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SituationParticipantImpl extends ParticipantImpl implements SituationParticipant {
	/**
	 * The cached value of the '{@link #getSituationType() <em>Situation Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSituationType()
	 * @generated
	 * @ordered
	 */
	protected SituationType situationType;

	/**
	 * The cached value of the '{@link #getParameter() <em>Parameter</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected EList<SituationParameterReference> parameter;

	/**
	 * The default value of the '{@link #isIsPast() <em>Is Past</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsPast()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_PAST_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsPast() <em>Is Past</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsPast()
	 * @generated
	 * @ordered
	 */
	protected boolean isPast = IS_PAST_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SituationParticipantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.SITUATION_PARTICIPANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SituationType getSituationType() {
		if (situationType != null && situationType.eIsProxy()) {
			InternalEObject oldSituationType = (InternalEObject)situationType;
			situationType = (SituationType)eResolveProxy(oldSituationType);
			if (situationType != oldSituationType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.SITUATION_PARTICIPANT__SITUATION_TYPE, oldSituationType, situationType));
			}
		}
		return situationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SituationType basicGetSituationType() {
		return situationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSituationType(SituationType newSituationType) {
		SituationType oldSituationType = situationType;
		situationType = newSituationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.SITUATION_PARTICIPANT__SITUATION_TYPE, oldSituationType, situationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SituationParameterReference> getParameter() {
		if (parameter == null) {
			parameter = new EObjectWithInverseResolvingEList<SituationParameterReference>(SituationParameterReference.class, this, Sml2Package.SITUATION_PARTICIPANT__PARAMETER, Sml2Package.SITUATION_PARAMETER_REFERENCE__SITUATION);
		}
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsPast() {
		return isPast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsPast(boolean newIsPast) {
		boolean oldIsPast = isPast;
		isPast = newIsPast;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.SITUATION_PARTICIPANT__IS_PAST, oldIsPast, isPast));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Sml2Package.SITUATION_PARTICIPANT__PARAMETER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParameter()).basicAdd(otherEnd, msgs);
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
			case Sml2Package.SITUATION_PARTICIPANT__PARAMETER:
				return ((InternalEList<?>)getParameter()).basicRemove(otherEnd, msgs);
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
			case Sml2Package.SITUATION_PARTICIPANT__SITUATION_TYPE:
				if (resolve) return getSituationType();
				return basicGetSituationType();
			case Sml2Package.SITUATION_PARTICIPANT__PARAMETER:
				return getParameter();
			case Sml2Package.SITUATION_PARTICIPANT__IS_PAST:
				return isIsPast();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Sml2Package.SITUATION_PARTICIPANT__SITUATION_TYPE:
				setSituationType((SituationType)newValue);
				return;
			case Sml2Package.SITUATION_PARTICIPANT__PARAMETER:
				getParameter().clear();
				getParameter().addAll((Collection<? extends SituationParameterReference>)newValue);
				return;
			case Sml2Package.SITUATION_PARTICIPANT__IS_PAST:
				setIsPast((Boolean)newValue);
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
			case Sml2Package.SITUATION_PARTICIPANT__SITUATION_TYPE:
				setSituationType((SituationType)null);
				return;
			case Sml2Package.SITUATION_PARTICIPANT__PARAMETER:
				getParameter().clear();
				return;
			case Sml2Package.SITUATION_PARTICIPANT__IS_PAST:
				setIsPast(IS_PAST_EDEFAULT);
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
			case Sml2Package.SITUATION_PARTICIPANT__SITUATION_TYPE:
				return situationType != null;
			case Sml2Package.SITUATION_PARTICIPANT__PARAMETER:
				return parameter != null && !parameter.isEmpty();
			case Sml2Package.SITUATION_PARTICIPANT__IS_PAST:
				return isPast != IS_PAST_EDEFAULT;
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
		result.append(" (isPast: ");
		result.append(isPast);
		result.append(')');
		return result.toString();
	}

} //SituationParticipantImpl
