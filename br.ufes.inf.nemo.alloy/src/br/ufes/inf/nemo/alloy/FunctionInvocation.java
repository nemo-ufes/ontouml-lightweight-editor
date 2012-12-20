/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Invocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.FunctionInvocation#getParameter <em>Parameter</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.FunctionInvocation#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getFunctionInvocation()
 * @model
 * @generated
 */
public interface FunctionInvocation extends Expression {
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link br.ufes.inf.nemo.alloy.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' containment reference list.
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getFunctionInvocation_Parameter()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getParameter();

	/**
	 * Returns the value of the '<em><b>Function</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function</em>' attribute.
	 * @see #setFunction(String)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getFunctionInvocation_Function()
	 * @model required="true"
	 * @generated
	 */
	String getFunction();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.FunctionInvocation#getFunction <em>Function</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' attribute.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(String value);

} // FunctionInvocation
