/**
 */
package br.ufes.inf.nemo.z3py.impl;

import br.ufes.inf.nemo.z3py.Expression;
import br.ufes.inf.nemo.z3py.IntConstant;
import br.ufes.inf.nemo.z3py.Quantification;
import br.ufes.inf.nemo.z3py.Z3pyPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quantification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.QuantificationImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.QuantificationImpl#getConstants <em>Constants</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.QuantificationImpl#getQuantifiesOver <em>Quantifies Over</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class QuantificationImpl extends ExpressionImpl implements Quantification {
	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

	/**
	 * The cached value of the '{@link #getConstants() <em>Constants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstants()
	 * @generated
	 * @ordered
	 */
	protected EList<IntConstant> constants;

	/**
	 * The cached value of the '{@link #getQuantifiesOver() <em>Quantifies Over</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantifiesOver()
	 * @generated
	 * @ordered
	 */
	protected EList<IntConstant> quantifiesOver;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QuantificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Z3pyPackage.Literals.QUANTIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
		Expression oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Z3pyPackage.QUANTIFICATION__EXPRESSION, oldExpression, newExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		if (newExpression != expression) {
			NotificationChain msgs = null;
			if (expression != null)
				msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Z3pyPackage.QUANTIFICATION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Z3pyPackage.QUANTIFICATION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Z3pyPackage.QUANTIFICATION__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IntConstant> getConstants() {
		if (constants == null) {
			constants = new EObjectContainmentEList<IntConstant>(IntConstant.class, this, Z3pyPackage.QUANTIFICATION__CONSTANTS);
		}
		return constants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IntConstant> getQuantifiesOver() {
		if (quantifiesOver == null) {
			quantifiesOver = new EObjectResolvingEList<IntConstant>(IntConstant.class, this, Z3pyPackage.QUANTIFICATION__QUANTIFIES_OVER);
		}
		return quantifiesOver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Z3pyPackage.QUANTIFICATION__EXPRESSION:
				return basicSetExpression(null, msgs);
			case Z3pyPackage.QUANTIFICATION__CONSTANTS:
				return ((InternalEList<?>)getConstants()).basicRemove(otherEnd, msgs);
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
			case Z3pyPackage.QUANTIFICATION__EXPRESSION:
				return getExpression();
			case Z3pyPackage.QUANTIFICATION__CONSTANTS:
				return getConstants();
			case Z3pyPackage.QUANTIFICATION__QUANTIFIES_OVER:
				return getQuantifiesOver();
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
			case Z3pyPackage.QUANTIFICATION__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case Z3pyPackage.QUANTIFICATION__CONSTANTS:
				getConstants().clear();
				getConstants().addAll((Collection<? extends IntConstant>)newValue);
				return;
			case Z3pyPackage.QUANTIFICATION__QUANTIFIES_OVER:
				getQuantifiesOver().clear();
				getQuantifiesOver().addAll((Collection<? extends IntConstant>)newValue);
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
			case Z3pyPackage.QUANTIFICATION__EXPRESSION:
				setExpression((Expression)null);
				return;
			case Z3pyPackage.QUANTIFICATION__CONSTANTS:
				getConstants().clear();
				return;
			case Z3pyPackage.QUANTIFICATION__QUANTIFIES_OVER:
				getQuantifiesOver().clear();
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
			case Z3pyPackage.QUANTIFICATION__EXPRESSION:
				return expression != null;
			case Z3pyPackage.QUANTIFICATION__CONSTANTS:
				return constants != null && !constants.isEmpty();
			case Z3pyPackage.QUANTIFICATION__QUANTIFIES_OVER:
				return quantifiesOver != null && !quantifiesOver.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //QuantificationImpl
