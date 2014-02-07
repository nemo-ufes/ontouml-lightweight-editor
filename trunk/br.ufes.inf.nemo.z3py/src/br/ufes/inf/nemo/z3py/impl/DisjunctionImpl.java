/**
 */
package br.ufes.inf.nemo.z3py.impl;

import br.ufes.inf.nemo.z3py.Disjunction;
import br.ufes.inf.nemo.z3py.Z3pyPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Disjunction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DisjunctionImpl extends LogicalBinaryExpressionImpl implements Disjunction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DisjunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Z3pyPackage.Literals.DISJUNCTION;
	}

	public String toString() {
		return "Or(" + this.operand1.toString() + ", "+ this.operand2.toString() + ")";
	}	
} //DisjunctionImpl
