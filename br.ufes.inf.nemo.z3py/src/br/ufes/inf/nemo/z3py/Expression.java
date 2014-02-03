/**
 */
package br.ufes.inf.nemo.z3py;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.Expression#getTeste <em>Teste</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.Expression#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getExpression()
 * @model
 * @generated
 */
public interface Expression extends EObject {

	/**
	 * Returns the value of the '<em><b>Teste</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Teste</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Teste</em>' reference.
	 * @see #setTeste(Function)
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getExpression_Teste()
	 * @model
	 * @generated
	 */
	Function getTeste();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.z3py.Expression#getTeste <em>Teste</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Teste</em>' reference.
	 * @see #getTeste()
	 * @generated
	 */
	void setTeste(Function value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getExpression_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.z3py.Expression#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);
} // Expression
