/**
 */
package br.ufes.inf.nemo.z3py;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Equality</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.Equality#getOperand1 <em>Operand1</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.Equality#getOperand2 <em>Operand2</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getEquality()
 * @model
 * @generated
 */
public interface Equality extends Expression {
	/**
	 * Returns the value of the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operand1</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand1</em>' reference.
	 * @see #setOperand1(IntConstant)
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getEquality_Operand1()
	 * @model required="true"
	 * @generated
	 */
	IntConstant getOperand1();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.z3py.Equality#getOperand1 <em>Operand1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand1</em>' reference.
	 * @see #getOperand1()
	 * @generated
	 */
	void setOperand1(IntConstant value);

	/**
	 * Returns the value of the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operand2</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand2</em>' reference.
	 * @see #setOperand2(IntConstant)
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getEquality_Operand2()
	 * @model required="true"
	 * @generated
	 */
	IntConstant getOperand2();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.z3py.Equality#getOperand2 <em>Operand2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand2</em>' reference.
	 * @see #getOperand2()
	 * @generated
	 */
	void setOperand2(IntConstant value);

} // Equality
