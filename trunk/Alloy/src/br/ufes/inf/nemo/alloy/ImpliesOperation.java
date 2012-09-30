/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implies Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.ImpliesOperation#getElse <em>Else</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.ImpliesOperation#getImplication <em>Implication</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.ImpliesOperation#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getImpliesOperation()
 * @model
 * @generated
 */
public interface ImpliesOperation extends Expression {
	/**
	 * Returns the value of the '<em><b>Else</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Else</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Else</em>' containment reference.
	 * @see #setElse(Expression)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getImpliesOperation_Else()
	 * @model containment="true"
	 * @generated
	 */
	Expression getElse();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.ImpliesOperation#getElse <em>Else</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Else</em>' containment reference.
	 * @see #getElse()
	 * @generated
	 */
	void setElse(Expression value);

	/**
	 * Returns the value of the '<em><b>Implication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implication</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implication</em>' containment reference.
	 * @see #setImplication(Expression)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getImpliesOperation_Implication()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getImplication();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.ImpliesOperation#getImplication <em>Implication</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implication</em>' containment reference.
	 * @see #getImplication()
	 * @generated
	 */
	void setImplication(Expression value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(Expression)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getImpliesOperation_Condition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getCondition();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.ImpliesOperation#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Expression value);

} // ImpliesOperation
