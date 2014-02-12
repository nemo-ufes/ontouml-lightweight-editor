/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.ArrowOperation;
import br.ufes.inf.nemo.alloy.Expression;
import br.ufes.inf.nemo.alloy.Multiplicity;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Arrow Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.ArrowOperationImpl#getLeftMultiplicity <em>Left Multiplicity</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.ArrowOperationImpl#getRightMultiplicity <em>Right Multiplicity</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.ArrowOperationImpl#getLeftExpression <em>Left Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.ArrowOperationImpl#getRightExpression <em>Right Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrowOperationImpl extends ExpressionImpl implements ArrowOperation {
	/**
	 * The default value of the '{@link #getLeftMultiplicity() <em>Left Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected static final Multiplicity LEFT_MULTIPLICITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLeftMultiplicity() <em>Left Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected Multiplicity leftMultiplicity = LEFT_MULTIPLICITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getRightMultiplicity() <em>Right Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected static final Multiplicity RIGHT_MULTIPLICITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRightMultiplicity() <em>Right Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected Multiplicity rightMultiplicity = RIGHT_MULTIPLICITY_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrowOperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.ARROW_OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Multiplicity getLeftMultiplicity() {
		return leftMultiplicity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftMultiplicity(Multiplicity newLeftMultiplicity) {
		Multiplicity oldLeftMultiplicity = leftMultiplicity;
		leftMultiplicity = newLeftMultiplicity == null ? LEFT_MULTIPLICITY_EDEFAULT : newLeftMultiplicity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.ARROW_OPERATION__LEFT_MULTIPLICITY, oldLeftMultiplicity, leftMultiplicity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Multiplicity getRightMultiplicity() {
		return rightMultiplicity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightMultiplicity(Multiplicity newRightMultiplicity) {
		Multiplicity oldRightMultiplicity = rightMultiplicity;
		rightMultiplicity = newRightMultiplicity == null ? RIGHT_MULTIPLICITY_EDEFAULT : newRightMultiplicity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.ARROW_OPERATION__RIGHT_MULTIPLICITY, oldRightMultiplicity, rightMultiplicity));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.ARROW_OPERATION__LEFT_EXPRESSION, oldLeftExpression, newLeftExpression);
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
				msgs = ((InternalEObject)leftExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.ARROW_OPERATION__LEFT_EXPRESSION, null, msgs);
			if (newLeftExpression != null)
				msgs = ((InternalEObject)newLeftExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.ARROW_OPERATION__LEFT_EXPRESSION, null, msgs);
			msgs = basicSetLeftExpression(newLeftExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.ARROW_OPERATION__LEFT_EXPRESSION, newLeftExpression, newLeftExpression));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.ARROW_OPERATION__RIGHT_EXPRESSION, oldRightExpression, newRightExpression);
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
				msgs = ((InternalEObject)rightExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.ARROW_OPERATION__RIGHT_EXPRESSION, null, msgs);
			if (newRightExpression != null)
				msgs = ((InternalEObject)newRightExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.ARROW_OPERATION__RIGHT_EXPRESSION, null, msgs);
			msgs = basicSetRightExpression(newRightExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.ARROW_OPERATION__RIGHT_EXPRESSION, newRightExpression, newRightExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlloyPackage.ARROW_OPERATION__LEFT_EXPRESSION:
				return basicSetLeftExpression(null, msgs);
			case AlloyPackage.ARROW_OPERATION__RIGHT_EXPRESSION:
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
			case AlloyPackage.ARROW_OPERATION__LEFT_MULTIPLICITY:
				return getLeftMultiplicity();
			case AlloyPackage.ARROW_OPERATION__RIGHT_MULTIPLICITY:
				return getRightMultiplicity();
			case AlloyPackage.ARROW_OPERATION__LEFT_EXPRESSION:
				return getLeftExpression();
			case AlloyPackage.ARROW_OPERATION__RIGHT_EXPRESSION:
				return getRightExpression();
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
			case AlloyPackage.ARROW_OPERATION__LEFT_MULTIPLICITY:
				setLeftMultiplicity((Multiplicity)newValue);
				return;
			case AlloyPackage.ARROW_OPERATION__RIGHT_MULTIPLICITY:
				setRightMultiplicity((Multiplicity)newValue);
				return;
			case AlloyPackage.ARROW_OPERATION__LEFT_EXPRESSION:
				setLeftExpression((Expression)newValue);
				return;
			case AlloyPackage.ARROW_OPERATION__RIGHT_EXPRESSION:
				setRightExpression((Expression)newValue);
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
			case AlloyPackage.ARROW_OPERATION__LEFT_MULTIPLICITY:
				setLeftMultiplicity(LEFT_MULTIPLICITY_EDEFAULT);
				return;
			case AlloyPackage.ARROW_OPERATION__RIGHT_MULTIPLICITY:
				setRightMultiplicity(RIGHT_MULTIPLICITY_EDEFAULT);
				return;
			case AlloyPackage.ARROW_OPERATION__LEFT_EXPRESSION:
				setLeftExpression((Expression)null);
				return;
			case AlloyPackage.ARROW_OPERATION__RIGHT_EXPRESSION:
				setRightExpression((Expression)null);
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
			case AlloyPackage.ARROW_OPERATION__LEFT_MULTIPLICITY:
				return leftMultiplicity != LEFT_MULTIPLICITY_EDEFAULT;
			case AlloyPackage.ARROW_OPERATION__RIGHT_MULTIPLICITY:
				return rightMultiplicity != RIGHT_MULTIPLICITY_EDEFAULT;
			case AlloyPackage.ARROW_OPERATION__LEFT_EXPRESSION:
				return leftExpression != null;
			case AlloyPackage.ARROW_OPERATION__RIGHT_EXPRESSION:
				return rightExpression != null;
		}
		return super.eIsSet(featureID);
	}

	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer();
		
		result.append(getLeftExpression());
		
		if(getLeftMultiplicity() != null) result.append(" "+getLeftMultiplicity()+"");
		
		result.append(" -> ");
		
		if(getRightMultiplicity() != null) result.append(""+getRightMultiplicity()+" ");
		
		result.append(getRightExpression());
		
		return result.toString();
	}

} //ArrowOperationImpl
