/**
 */
package sml2.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;
import sml2.Participant;
import sml2.ReflectedParticipant;
import sml2.ReflectedReference;
import sml2.SituationType;
import sml2.SituationTypeElement;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reflected Participant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sml2.impl.ReflectedParticipantImpl#getParticipant <em>Participant</em>}</li>
 *   <li>{@link sml2.impl.ReflectedParticipantImpl#getReferences <em>References</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReflectedParticipantImpl extends NodeImpl implements ReflectedParticipant {
	/**
	 * The cached value of the '{@link #getParticipant() <em>Participant</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipant()
	 * @generated
	 * @ordered
	 */
	protected Participant participant;

	/**
	 * The cached value of the '{@link #getReferences() <em>References</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferences()
	 * @generated
	 * @ordered
	 */
	protected EList<ReflectedReference> references;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReflectedParticipantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.REFLECTED_PARTICIPANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Participant getParticipant() {
		if (participant != null && participant.eIsProxy()) {
			InternalEObject oldParticipant = (InternalEObject)participant;
			participant = (Participant)eResolveProxy(oldParticipant);
			if (participant != oldParticipant) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.REFLECTED_PARTICIPANT__PARTICIPANT, oldParticipant, participant));
			}
		}
		return participant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Participant basicGetParticipant() {
		return participant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParticipant(Participant newParticipant, NotificationChain msgs) {
		Participant oldParticipant = participant;
		participant = newParticipant;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Sml2Package.REFLECTED_PARTICIPANT__PARTICIPANT, oldParticipant, newParticipant);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipant(Participant newParticipant) {
		if (newParticipant != participant) {
			NotificationChain msgs = null;
			if (participant != null)
				msgs = ((InternalEObject)participant).eInverseRemove(this, Sml2Package.PARTICIPANT__REFLECTION, Participant.class, msgs);
			if (newParticipant != null)
				msgs = ((InternalEObject)newParticipant).eInverseAdd(this, Sml2Package.PARTICIPANT__REFLECTION, Participant.class, msgs);
			msgs = basicSetParticipant(newParticipant, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.REFLECTED_PARTICIPANT__PARTICIPANT, newParticipant, newParticipant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ReflectedReference> getReferences() {
		if (references == null) {
			references = new EObjectContainmentWithInverseEList<ReflectedReference>(ReflectedReference.class, this, Sml2Package.REFLECTED_PARTICIPANT__REFERENCES, Sml2Package.REFLECTED_REFERENCE__OWNING_REFLECTION);
		}
		return references;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SituationType getSituation() {
		if (getSituationBodyOCL == null) {
			EOperation eOperation = Sml2Package.Literals.SITUATION_TYPE_ELEMENT.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(Sml2Package.Literals.SITUATION_TYPE_ELEMENT, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getSituationBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getSituationBodyOCL);
	
		return (SituationType) query.evaluate(this);
	
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
			case Sml2Package.REFLECTED_PARTICIPANT__PARTICIPANT:
				if (participant != null)
					msgs = ((InternalEObject)participant).eInverseRemove(this, Sml2Package.PARTICIPANT__REFLECTION, Participant.class, msgs);
				return basicSetParticipant((Participant)otherEnd, msgs);
			case Sml2Package.REFLECTED_PARTICIPANT__REFERENCES:
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
			case Sml2Package.REFLECTED_PARTICIPANT__PARTICIPANT:
				return basicSetParticipant(null, msgs);
			case Sml2Package.REFLECTED_PARTICIPANT__REFERENCES:
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
			case Sml2Package.REFLECTED_PARTICIPANT__PARTICIPANT:
				if (resolve) return getParticipant();
				return basicGetParticipant();
			case Sml2Package.REFLECTED_PARTICIPANT__REFERENCES:
				return getReferences();
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
			case Sml2Package.REFLECTED_PARTICIPANT__PARTICIPANT:
				setParticipant((Participant)newValue);
				return;
			case Sml2Package.REFLECTED_PARTICIPANT__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection<? extends ReflectedReference>)newValue);
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
			case Sml2Package.REFLECTED_PARTICIPANT__PARTICIPANT:
				setParticipant((Participant)null);
				return;
			case Sml2Package.REFLECTED_PARTICIPANT__REFERENCES:
				getReferences().clear();
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
			case Sml2Package.REFLECTED_PARTICIPANT__PARTICIPANT:
				return participant != null;
			case Sml2Package.REFLECTED_PARTICIPANT__REFERENCES:
				return references != null && !references.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == SituationTypeElement.class) {
			switch (baseOperationID) {
				case Sml2Package.SITUATION_TYPE_ELEMENT___GET_SITUATION: return Sml2Package.REFLECTED_PARTICIPANT___GET_SITUATION;
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case Sml2Package.REFLECTED_PARTICIPANT___GET_SITUATION:
				return getSituation();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getSituation <em>Get Situation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSituation
	 * @generated
	 */
	private static OCLExpression<EClassifier> getSituationBodyOCL;

	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";

	private static final OCL OCL_ENV = OCL.newInstance();

} //ReflectedParticipantImpl
