/**
 */
package br.ufes.inf.nemo.z3py.impl;

import br.ufes.inf.nemo.z3py.ExclusiveDisjunction;
import br.ufes.inf.nemo.z3py.Z3pyPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exclusive Disjunction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ExclusiveDisjunctionImpl extends LogicalBinaryExpressionImpl implements ExclusiveDisjunction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExclusiveDisjunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Z3pyPackage.Literals.EXCLUSIVE_DISJUNCTION;
	}

	public String toString() {
		return "Xor(" + this.operand1.toString() + ", "+ this.operand2.toString() + ")";
	}
} //ExclusiveDisjunctionImpl
