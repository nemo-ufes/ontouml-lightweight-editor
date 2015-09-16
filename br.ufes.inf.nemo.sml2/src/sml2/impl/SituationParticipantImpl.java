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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import sml2.ReferenceNode;
import sml2.SituationParticipant;
import sml2.SituationType;
import sml2.Sml2Package;
import sml2.TemporalKind;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Situation Participant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sml2.impl.SituationParticipantImpl#getTemporality <em>Temporality</em>}</li>
 *   <li>{@link sml2.impl.SituationParticipantImpl#getReferences <em>References</em>}</li>
 *   <li>{@link sml2.impl.SituationParticipantImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SituationParticipantImpl extends ParticipantImpl implements SituationParticipant {
	/**
	 * The default value of the '{@link #getTemporality() <em>Temporality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemporality()
	 * @generated
	 * @ordered
	 */
	protected static final TemporalKind TEMPORALITY_EDEFAULT = TemporalKind.PRESENT;

	/**
	 * The cached value of the '{@link #getTemporality() <em>Temporality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemporality()
	 * @generated
	 * @ordered
	 */
	protected TemporalKind temporality = TEMPORALITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReferences() <em>References</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferences()
	 * @generated
	 * @ordered
	 */
	protected EList<ReferenceNode> references;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected SituationType type;

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
	public TemporalKind getTemporality() {
		return temporality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemporality(TemporalKind newTemporality) {
		TemporalKind oldTemporality = temporality;
		temporality = newTemporality == null ? TEMPORALITY_EDEFAULT : newTemporality;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.SITUATION_PARTICIPANT__TEMPORALITY, oldTemporality, temporality));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ReferenceNode> getReferences() {
		if (references == null) {
			references = new EObjectContainmentWithInverseEList<ReferenceNode>(ReferenceNode.class, this, Sml2Package.SITUATION_PARTICIPANT__REFERENCES, Sml2Package.REFERENCE_NODE__SITUATION);
		}
		return references;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SituationType getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (SituationType)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.SITUATION_PARTICIPANT__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SituationType basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(SituationType newType) {
		SituationType oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.SITUATION_PARTICIPANT__TYPE, oldType, type));
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
			case Sml2Package.SITUATION_PARTICIPANT__REFERENCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferences()).basicAdd(otherEnd, msgs);
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
			case Sml2Package.SITUATION_PARTICIPANT__REFERENCES:
				return ((InternalEList<?>)getReferences()).basicRemove(otherEnd, msgs);
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
			case Sml2Package.SITUATION_PARTICIPANT__TEMPORALITY:
				return getTemporality();
			case Sml2Package.SITUATION_PARTICIPANT__REFERENCES:
				return getReferences();
			case Sml2Package.SITUATION_PARTICIPANT__TYPE:
				if (resolve) return getType();
				return basicGetType();
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
			case Sml2Package.SITUATION_PARTICIPANT__TEMPORALITY:
				setTemporality((TemporalKind)newValue);
				return;
			case Sml2Package.SITUATION_PARTICIPANT__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection<? extends ReferenceNode>)newValue);
				return;
			case Sml2Package.SITUATION_PARTICIPANT__TYPE:
				setType((SituationType)newValue);
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
			case Sml2Package.SITUATION_PARTICIPANT__TEMPORALITY:
				setTemporality(TEMPORALITY_EDEFAULT);
				return;
			case Sml2Package.SITUATION_PARTICIPANT__REFERENCES:
				getReferences().clear();
				return;
			case Sml2Package.SITUATION_PARTICIPANT__TYPE:
				setType((SituationType)null);
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
			case Sml2Package.SITUATION_PARTICIPANT__TEMPORALITY:
				return temporality != TEMPORALITY_EDEFAULT;
			case Sml2Package.SITUATION_PARTICIPANT__REFERENCES:
				return references != null && !references.isEmpty();
			case Sml2Package.SITUATION_PARTICIPANT__TYPE:
				return type != null;
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
		result.append(" (temporality: ");
		result.append(temporality);
		result.append(')');
		return result.toString();
	}

} //SituationParticipantImpl
