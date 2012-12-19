/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binary Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.BinaryOperation#getLeftExpression <em>Left Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.BinaryOperation#getRightExpression <em>Right Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.BinaryOperation#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getBinaryOperation()
 * @model
 * @generated
 */
public interface BinaryOperation extends Expression {
	/**
	 * Returns the value of the '<em><b>Left Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Expression</em>' containment reference.
	 * @see #setLeftExpression(Expression)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getBinaryOperation_LeftExpression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getLeftExpression();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.BinaryOperation#getLeftExpression <em>Left Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Expression</em>' containment reference.
	 * @see #getLeftExpression()
	 * @generated
	 */
	void setLeftExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Right Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Expression</em>' containment reference.
	 * @see #setRightExpression(Expression)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getBinaryOperation_RightExpression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getRightExpression();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.BinaryOperation#getRightExpression <em>Right Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Expression</em>' containment reference.
	 * @see #getRightExpression()
	 * @generated
	 */
	void setRightExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link br.ufes.inf.nemo.alloy.BinaryOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see br.ufes.inf.nemo.alloy.BinaryOperator
	 * @see #setOperator(BinaryOperator)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getBinaryOperation_Operator()
	 * @model required="true"
	 * @generated
	 */
	BinaryOperator getOperator();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.BinaryOperation#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see br.ufes.inf.nemo.alloy.BinaryOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(BinaryOperator value);

} // BinaryOperation
