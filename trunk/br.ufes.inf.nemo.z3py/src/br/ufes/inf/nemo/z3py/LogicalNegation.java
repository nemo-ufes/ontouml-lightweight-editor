/**
 */
package br.ufes.inf.nemo.z3py;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Logical Negation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.LogicalNegation#getOperand <em>Operand</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getLogicalNegation()
 * @model
 * @generated
 */
public interface LogicalNegation extends Expression {
	/**
	 * Returns the value of the '<em><b>Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand</em>' reference.
	 * @see #setOperand(Expression)
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getLogicalNegation_Operand()
	 * @model required="true"
	 * @generated
	 */
	Expression getOperand();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.z3py.LogicalNegation#getOperand <em>Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand</em>' reference.
	 * @see #getOperand()
	 * @generated
	 */
	void setOperand(Expression value);

} // LogicalNegation
