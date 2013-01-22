/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.Expression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binary Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.BinaryOperationImpl#getLeftExpression <em>Left Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.BinaryOperationImpl#getRightExpression <em>Right Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.BinaryOperationImpl#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BinaryOperationImpl extends ExpressionImpl implements BinaryOperation {
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
	protected static final BinaryOperator OPERATOR_EDEFAULT = BinaryOperator.DIFFERENCE;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected BinaryOperator operator = OPERATOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BinaryOperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.BINARY_OPERATION;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.BINARY_OPERATION__LEFT_EXPRESSION, oldLeftExpression, newLeftExpression);
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
				msgs = ((InternalEObject)leftExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.BINARY_OPERATION__LEFT_EXPRESSION, null, msgs);
			if (newLeftExpression != null)
				msgs = ((InternalEObject)newLeftExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.BINARY_OPERATION__LEFT_EXPRESSION, null, msgs);
			msgs = basicSetLeftExpression(newLeftExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.BINARY_OPERATION__LEFT_EXPRESSION, newLeftExpression, newLeftExpression));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.BINARY_OPERATION__RIGHT_EXPRESSION, oldRightExpression, newRightExpression);
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
				msgs = ((InternalEObject)rightExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.BINARY_OPERATION__RIGHT_EXPRESSION, null, msgs);
			if (newRightExpression != null)
				msgs = ((InternalEObject)newRightExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.BINARY_OPERATION__RIGHT_EXPRESSION, null, msgs);
			msgs = basicSetRightExpression(newRightExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.BINARY_OPERATION__RIGHT_EXPRESSION, newRightExpression, newRightExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryOperator getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(BinaryOperator newOperator) {
		BinaryOperator oldOperator = operator;
		operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.BINARY_OPERATION__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlloyPackage.BINARY_OPERATION__LEFT_EXPRESSION:
				return basicSetLeftExpression(null, msgs);
			case AlloyPackage.BINARY_OPERATION__RIGHT_EXPRESSION:
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
			case AlloyPackage.BINARY_OPERATION__LEFT_EXPRESSION:
				return getLeftExpression();
			case AlloyPackage.BINARY_OPERATION__RIGHT_EXPRESSION:
				return getRightExpression();
			case AlloyPackage.BINARY_OPERATION__OPERATOR:
				return getOperator();
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
			case AlloyPackage.BINARY_OPERATION__LEFT_EXPRESSION:
				setLeftExpression((Expression)newValue);
				return;
			case AlloyPackage.BINARY_OPERATION__RIGHT_EXPRESSION:
				setRightExpression((Expression)newValue);
				return;
			case AlloyPackage.BINARY_OPERATION__OPERATOR:
				setOperator((BinaryOperator)newValue);
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
			case AlloyPackage.BINARY_OPERATION__LEFT_EXPRESSION:
				setLeftExpression((Expression)null);
				return;
			case AlloyPackage.BINARY_OPERATION__RIGHT_EXPRESSION:
				setRightExpression((Expression)null);
				return;
			case AlloyPackage.BINARY_OPERATION__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
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
			case AlloyPackage.BINARY_OPERATION__LEFT_EXPRESSION:
				return leftExpression != null;
			case AlloyPackage.BINARY_OPERATION__RIGHT_EXPRESSION:
				return rightExpression != null;
			case AlloyPackage.BINARY_OPERATION__OPERATOR:
				return operator != OPERATOR_EDEFAULT;
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
			
			// case of: (x.y).z
			if (getLeftExpression() instanceof BinaryOperation && !(getRightExpression() instanceof BinaryOperation) && 
		       ((BinaryOperation)getLeftExpression()).getOperator().equals(BinaryOperator.JOIN)) {
					result.append("("+getLeftExpression()+")" + operator + getRightExpression());
			}
			// case of: x.(y.z)
			else if (getRightExpression() instanceof BinaryOperation && !(getLeftExpression() instanceof BinaryOperation) && 
					((BinaryOperation)getRightExpression()).getOperator().equals(BinaryOperator.JOIN)) {
					result.append(""+getLeftExpression() + operator + "("+getRightExpression()+")");			
			}
			// otherwise no precedence
			else {
				result.append(""+getLeftExpression() + operator + getRightExpression());
			}
			
		}
		return result.toString();
	}

} //BinaryOperationImpl
