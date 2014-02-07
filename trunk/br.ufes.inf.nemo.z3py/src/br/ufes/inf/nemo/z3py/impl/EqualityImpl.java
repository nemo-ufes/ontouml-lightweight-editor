/**
 */
package br.ufes.inf.nemo.z3py.impl;

import br.ufes.inf.nemo.z3py.Equality;
import br.ufes.inf.nemo.z3py.IntConstant;
import br.ufes.inf.nemo.z3py.Z3pyPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Equality</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.EqualityImpl#getOperand1 <em>Operand1</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.EqualityImpl#getOperand2 <em>Operand2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EqualityImpl extends ExpressionImpl implements Equality {
	/**
	 * The cached value of the '{@link #getOperand1() <em>Operand1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperand1()
	 * @generated
	 * @ordered
	 */
	protected IntConstant operand1;

	/**
	 * The cached value of the '{@link #getOperand2() <em>Operand2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperand2()
	 * @generated
	 * @ordered
	 */
	protected IntConstant operand2;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EqualityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Z3pyPackage.Literals.EQUALITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntConstant getOperand1() {
		if (operand1 != null && operand1.eIsProxy()) {
			InternalEObject oldOperand1 = (InternalEObject)operand1;
			operand1 = (IntConstant)eResolveProxy(oldOperand1);
			if (operand1 != oldOperand1) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Z3pyPackage.EQUALITY__OPERAND1, oldOperand1, operand1));
			}
		}
		return operand1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntConstant basicGetOperand1() {
		return operand1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperand1(IntConstant newOperand1) {
		IntConstant oldOperand1 = operand1;
		operand1 = newOperand1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Z3pyPackage.EQUALITY__OPERAND1, oldOperand1, operand1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntConstant getOperand2() {
		if (operand2 != null && operand2.eIsProxy()) {
			InternalEObject oldOperand2 = (InternalEObject)operand2;
			operand2 = (IntConstant)eResolveProxy(oldOperand2);
			if (operand2 != oldOperand2) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Z3pyPackage.EQUALITY__OPERAND2, oldOperand2, operand2));
			}
		}
		return operand2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntConstant basicGetOperand2() {
		return operand2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperand2(IntConstant newOperand2) {
		IntConstant oldOperand2 = operand2;
		operand2 = newOperand2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Z3pyPackage.EQUALITY__OPERAND2, oldOperand2, operand2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Z3pyPackage.EQUALITY__OPERAND1:
				if (resolve) return getOperand1();
				return basicGetOperand1();
			case Z3pyPackage.EQUALITY__OPERAND2:
				if (resolve) return getOperand2();
				return basicGetOperand2();
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
			case Z3pyPackage.EQUALITY__OPERAND1:
				setOperand1((IntConstant)newValue);
				return;
			case Z3pyPackage.EQUALITY__OPERAND2:
				setOperand2((IntConstant)newValue);
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
			case Z3pyPackage.EQUALITY__OPERAND1:
				setOperand1((IntConstant)null);
				return;
			case Z3pyPackage.EQUALITY__OPERAND2:
				setOperand2((IntConstant)null);
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
			case Z3pyPackage.EQUALITY__OPERAND1:
				return operand1 != null;
			case Z3pyPackage.EQUALITY__OPERAND2:
				return operand2 != null;
		}
		return super.eIsSet(featureID);
	}
	
	@Override
	public String toString() {
		return this.operand1.getName() + " == " + this.operand2.getName();
	}
} //EqualityImpl
