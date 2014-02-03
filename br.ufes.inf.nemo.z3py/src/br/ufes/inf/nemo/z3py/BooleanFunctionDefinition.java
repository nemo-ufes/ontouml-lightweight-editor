/**
 */
package br.ufes.inf.nemo.z3py;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Function Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.BooleanFunctionDefinition#getName <em>Name</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.BooleanFunctionDefinition#getNumberOfArguments <em>Number Of Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getBooleanFunctionDefinition()
 * @model
 * @generated
 */
public interface BooleanFunctionDefinition extends EObject {
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
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getBooleanFunctionDefinition_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.z3py.BooleanFunctionDefinition#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Number Of Arguments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of Arguments</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Arguments</em>' attribute.
	 * @see #setNumberOfArguments(int)
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getBooleanFunctionDefinition_NumberOfArguments()
	 * @model required="true"
	 * @generated
	 */
	int getNumberOfArguments();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.z3py.BooleanFunctionDefinition#getNumberOfArguments <em>Number Of Arguments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Arguments</em>' attribute.
	 * @see #getNumberOfArguments()
	 * @generated
	 */
	void setNumberOfArguments(int value);

} // BooleanFunctionDefinition
