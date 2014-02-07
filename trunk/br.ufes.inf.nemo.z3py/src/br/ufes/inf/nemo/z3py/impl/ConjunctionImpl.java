/**
 */
package br.ufes.inf.nemo.z3py.impl;

import br.ufes.inf.nemo.z3py.Conjunction;
import br.ufes.inf.nemo.z3py.Z3pyPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conjunction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ConjunctionImpl extends LogicalBinaryExpressionImpl implements Conjunction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConjunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Z3pyPackage.Literals.CONJUNCTION;
	}

	public String toString() {
		return "And(" + this.operand1.toString() + ", "+ this.operand2.toString() + ")";
	}
} //ConjunctionImpl
