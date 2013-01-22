/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.CompareOperation;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.Expression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compare Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.CompareOperationImpl#getLeftExpression <em>Left Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.CompareOperationImpl#getRightExpression <em>Right Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.CompareOperationImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.CompareOperationImpl#isNegation <em>Negation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompareOperationImpl extends ExpressionImpl implements CompareOperation {
	/**
	 * The cached value of the '{@link #getLeftExpression() <em>Left Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression leftExpression;

	/**
	 * The cached value of the '{@link #getRightExpression() <em>Right Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression rightExpression;

	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final CompareOperator OPERATOR_EDEFAULT = CompareOperator.SUBSET;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected CompareOperator operator = OPERATOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isNegation() <em>Negation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNegation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEGATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNegation() <em>Negation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNegation()
	 * @generated
	 * @ordered
	 */
	protected boolean negation = NEGATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompareOperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.COMPARE_OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftExpression() {
		return leftExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftExpression(Expression newLeftExpression, NotificationChain msgs) {
		Expression oldLeftExpression = leftExpression;
		leftExpression = newLeftExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.COMPARE_OPERATION__LEFT_EXPRESSION, oldLeftExpression, newLeftExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftExpression(Expression newLeftExpression) {
		if (newLeftExpression != leftExpression) {
			NotificationChain msgs = null;
			if (leftExpression != null)
				msgs = ((InternalEObject)leftExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.COMPARE_OPERATION__LEFT_EXPRESSION, null, msgs);
			if (newLeftExpression != null)
				msgs = ((InternalEObject)newLeftExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.COMPARE_OPERATION__LEFT_EXPRESSION, null, msgs);
			msgs = basicSetLeftExpression(newLeftExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMPARE_OPERATION__LEFT_EXPRESSION, newLeftExpression, newLeftExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightExpression() {
		return rightExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightExpression(Expression newRightExpression, NotificationChain msgs) {
		Expression oldRightExpression = rightExpression;
		rightExpression = newRightExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.COMPARE_OPERATION__RIGHT_EXPRESSION, oldRightExpression, newRightExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightExpression(Expression newRightExpression) {
		if (newRightExpression != rightExpression) {
			NotificationChain msgs = null;
			if (rightExpression != null)
				msgs = ((InternalEObject)rightExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.COMPARE_OPERATION__RIGHT_EXPRESSION, null, msgs);
			if (newRightExpression != null)
				msgs = ((InternalEObject)newRightExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.COMPARE_OPERATION__RIGHT_EXPRESSION, null, msgs);
			msgs = basicSetRightExpression(newRightExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMPARE_OPERATION__RIGHT_EXPRESSION, newRightExpression, newRightExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompareOperator getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(CompareOperator newOperator) {
		CompareOperator oldOperator = operator;
		operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMPARE_OPERATION__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNegation() {
		return negation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNegation(boolean newNegation) {
		boolean oldNegation = negation;
		negation = newNegation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMPARE_OPERATION__NEGATION, oldNegation, negation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlloyPackage.COMPARE_OPERATION__LEFT_EXPRESSION:
				return basicSetLeftExpression(null, msgs);
			case AlloyPackage.COMPARE_OPERATION__RIGHT_EXPRESSION:
				return basicSetRightExpression(null, msgs);
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
			case AlloyPackage.COMPARE_OPERATION__LEFT_EXPRESSION:
				return getLeftExpression();
			case AlloyPackage.COMPARE_OPERATION__RIGHT_EXPRESSION:
				return getRightExpression();
			case AlloyPackage.COMPARE_OPERATION__OPERATOR:
				return getOperator();
			case AlloyPackage.COMPARE_OPERATION__NEGATION:
				return isNegation();
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
			case AlloyPackage.COMPARE_OPERATION__LEFT_EXPRESSION:
				setLeftExpression((Expression)newValue);
				return;
			case AlloyPackage.COMPARE_OPERATION__RIGHT_EXPRESSION:
				setRightExpression((Expression)newValue);
				return;
			case AlloyPackage.COMPARE_OPERATION__OPERATOR:
				setOperator((CompareOperator)newValue);
				return;
			case AlloyPackage.COMPARE_OPERATION__NEGATION:
				setNegation((Boolean)newValue);
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
			case AlloyPackage.COMPARE_OPERATION__LEFT_EXPRESSION:
				setLeftExpression((Expression)null);
				return;
			case AlloyPackage.COMPARE_OPERATION__RIGHT_EXPRESSION:
				setRightExpression((Expression)null);
				return;
			case AlloyPackage.COMPARE_OPERATION__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
				return;
			case AlloyPackage.COMPARE_OPERATION__NEGATION:
				setNegation(NEGATION_EDEFAULT);
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
			case AlloyPackage.COMPARE_OPERATION__LEFT_EXPRESSION:
				return leftExpression != null;
			case AlloyPackage.COMPARE_OPERATION__RIGHT_EXPRESSION:
				return rightExpression != null;
			case AlloyPackage.COMPARE_OPERATION__OPERATOR:
				return operator != OPERATOR_EDEFAULT;
			case AlloyPackage.COMPARE_OPERATION__NEGATION:
				return negation != NEGATION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}


	public String toString() 
	{
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer();
		
		if (operator == OPERATOR_EDEFAULT && rightExpression==null) 
		{
			result.append(""+getLeftExpression());
		}else if (operator == OPERATOR_EDEFAULT && leftExpression==null) 
		{
			result.append(""+getRightExpression());
		}else{
		
			result.append(getLeftExpression() + " " + operator + " " + getRightExpression()+"");
		}
		
		return result.toString();
	}

} //CompareOperationImpl
