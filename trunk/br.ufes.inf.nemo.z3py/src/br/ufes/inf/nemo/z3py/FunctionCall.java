/**
 */
package br.ufes.inf.nemo.z3py;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.FunctionCall#getCalledFunction <em>Called Function</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.FunctionCall#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getFunctionCall()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='FunctionCallNumberOfArguments'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot FunctionCallNumberOfArguments='self.calledFunction.numberOfArguments = self.arguments->size()'"
 * @generated
 */
public interface FunctionCall extends Expression {
	/**
	 * Returns the value of the '<em><b>Called Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Function</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Function</em>' reference.
	 * @see #setCalledFunction(BooleanFunctionDefinition)
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getFunctionCall_CalledFunction()
	 * @model required="true"
	 * @generated
	 */
	BooleanFunctionDefinition getCalledFunction();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.z3py.FunctionCall#getCalledFunction <em>Called Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Called Function</em>' reference.
	 * @see #getCalledFunction()
	 * @generated
	 */
	void setCalledFunction(BooleanFunctionDefinition value);

	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' reference list.
	 * The list contents are of type {@link br.ufes.inf.nemo.z3py.IntConstant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arguments</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arguments</em>' reference list.
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getFunctionCall_Arguments()
	 * @model resolveProxies="false" required="true" ordered="false"
	 * @generated
	 */
	EList<IntConstant> getArguments();

} // FunctionCall
