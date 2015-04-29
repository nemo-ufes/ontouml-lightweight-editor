/**
 */
package sml2.impl;

import RefOntoUML.FormalAssociation;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import sml2.ComparativeRelation;
import sml2.Node;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Comparative Relation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sml2.impl.ComparativeRelationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link sml2.impl.ComparativeRelationImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link sml2.impl.ComparativeRelationImpl#getRelation <em>Relation</em>}</li>
 *   <li>{@link sml2.impl.ComparativeRelationImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link sml2.impl.ComparativeRelationImpl#isIsNegated <em>Is Negated</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComparativeRelationImpl extends SituationTypeElementImpl implements ComparativeRelation {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Node source;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Node target;

	/**
	 * The cached value of the '{@link #getRelation() <em>Relation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelation()
	 * @generated
	 * @ordered
	 */
	protected FormalAssociation relation;

	/**
	 * The default value of the '{@link #getParameter() <em>Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected static final String PARAMETER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParameter() <em>Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected String parameter = PARAMETER_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsNegated() <em>Is Negated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNegated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NEGATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsNegated() <em>Is Negated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNegated()
	 * @generated
	 * @ordered
	 */
	protected boolean isNegated = IS_NEGATED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComparativeRelationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.COMPARATIVE_RELATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (Node)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.COMPARATIVE_RELATION__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Node newSource) {
		Node oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.COMPARATIVE_RELATION__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Node)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.COMPARATIVE_RELATION__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Node newTarget) {
		Node oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.COMPARATIVE_RELATION__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalAssociation getRelation() {
		if (relation != null && relation.eIsProxy()) {
			InternalEObject oldRelation = (InternalEObject)relation;
			relation = (FormalAssociation)eResolveProxy(oldRelation);
			if (relation != oldRelation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.COMPARATIVE_RELATION__RELATION, oldRelation, relation));
			}
		}
		return relation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalAssociation basicGetRelation() {
		return relation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRelation(FormalAssociation newRelation) {
		FormalAssociation oldRelation = relation;
		relation = newRelation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.COMPARATIVE_RELATION__RELATION, oldRelation, relation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameter(String newParameter) {
		String oldParameter = parameter;
		parameter = newParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.COMPARATIVE_RELATION__PARAMETER, oldParameter, parameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsNegated() {
		return isNegated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsNegated(boolean newIsNegated) {
		boolean oldIsNegated = isNegated;
		isNegated = newIsNegated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.COMPARATIVE_RELATION__IS_NEGATED, oldIsNegated, isNegated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Sml2Package.COMPARATIVE_RELATION__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case Sml2Package.COMPARATIVE_RELATION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case Sml2Package.COMPARATIVE_RELATION__RELATION:
				if (resolve) return getRelation();
				return basicGetRelation();
			case Sml2Package.COMPARATIVE_RELATION__PARAMETER:
				return getParameter();
			case Sml2Package.COMPARATIVE_RELATION__IS_NEGATED:
				return isIsNegated();
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
			case Sml2Package.COMPARATIVE_RELATION__SOURCE:
				setSource((Node)newValue);
				return;
			case Sml2Package.COMPARATIVE_RELATION__TARGET:
				setTarget((Node)newValue);
				return;
			case Sml2Package.COMPARATIVE_RELATION__RELATION:
				setRelation((FormalAssociation)newValue);
				return;
			case Sml2Package.COMPARATIVE_RELATION__PARAMETER:
				setParameter((String)newValue);
				return;
			case Sml2Package.COMPARATIVE_RELATION__IS_NEGATED:
				setIsNegated((Boolean)newValue);
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
			case Sml2Package.COMPARATIVE_RELATION__SOURCE:
				setSource((Node)null);
				return;
			case Sml2Package.COMPARATIVE_RELATION__TARGET:
				setTarget((Node)null);
				return;
			case Sml2Package.COMPARATIVE_RELATION__RELATION:
				setRelation((FormalAssociation)null);
				return;
			case Sml2Package.COMPARATIVE_RELATION__PARAMETER:
				setParameter(PARAMETER_EDEFAULT);
				return;
			case Sml2Package.COMPARATIVE_RELATION__IS_NEGATED:
				setIsNegated(IS_NEGATED_EDEFAULT);
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
			case Sml2Package.COMPARATIVE_RELATION__SOURCE:
				return source != null;
			case Sml2Package.COMPARATIVE_RELATION__TARGET:
				return target != null;
			case Sml2Package.COMPARATIVE_RELATION__RELATION:
				return relation != null;
			case Sml2Package.COMPARATIVE_RELATION__PARAMETER:
				return PARAMETER_EDEFAULT == null ? parameter != null : !PARAMETER_EDEFAULT.equals(parameter);
			case Sml2Package.COMPARATIVE_RELATION__IS_NEGATED:
				return isNegated != IS_NEGATED_EDEFAULT;
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
		result.append(" (parameter: ");
		result.append(parameter);
		result.append(", isNegated: ");
		result.append(isNegated);
		result.append(')');
		return result.toString();
	}

} //ComparativeRelationImpl
