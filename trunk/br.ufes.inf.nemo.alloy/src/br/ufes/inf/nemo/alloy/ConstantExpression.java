/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constant Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.ConstantExpression#getConstant <em>Constant</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getConstantExpression()
 * @model
 * @generated
 */
public interface ConstantExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Constant</b></em>' attribute.
	 * The literals are from the enumeration {@link br.ufes.inf.nemo.alloy.Constant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constant</em>' attribute.
	 * @see br.ufes.inf.nemo.alloy.Constant
	 * @see #setConstant(Constant)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getConstantExpression_Constant()
	 * @model required="true"
	 * @generated
	 */
	Constant getConstant();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.ConstantExpression#getConstant <em>Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constant</em>' attribute.
	 * @see br.ufes.inf.nemo.alloy.Constant
	 * @see #getConstant()
	 * @generated
	 */
	void setConstant(Constant value);

} // ConstantExpression
