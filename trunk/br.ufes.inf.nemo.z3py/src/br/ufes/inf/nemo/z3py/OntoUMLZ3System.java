/**
 */
package br.ufes.inf.nemo.z3py;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Onto UMLZ3 System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.OntoUMLZ3System#getFunctions <em>Functions</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.OntoUMLZ3System#getConstants <em>Constants</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.OntoUMLZ3System#getFileName <em>File Name</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.OntoUMLZ3System#getFormulas <em>Formulas</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getOntoUMLZ3System()
 * @model
 * @generated
 */
public interface OntoUMLZ3System extends EObject {
	/**
	 * Returns the value of the '<em><b>Functions</b></em>' containment reference list.
	 * The list contents are of type {@link br.ufes.inf.nemo.z3py.BooleanFunctionDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Functions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Functions</em>' containment reference list.
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getOntoUMLZ3System_Functions()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<BooleanFunctionDefinition> getFunctions();

	/**
	 * Returns the value of the '<em><b>Constants</b></em>' containment reference list.
	 * The list contents are of type {@link br.ufes.inf.nemo.z3py.IntConstant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constants</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constants</em>' containment reference list.
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getOntoUMLZ3System_Constants()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<IntConstant> getConstants();

	/**
	 * Returns the value of the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Name</em>' attribute.
	 * @see #setFileName(String)
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getOntoUMLZ3System_FileName()
	 * @model required="true"
	 * @generated
	 */
	String getFileName();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.z3py.OntoUMLZ3System#getFileName <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Name</em>' attribute.
	 * @see #getFileName()
	 * @generated
	 */
	void setFileName(String value);

	/**
	 * Returns the value of the '<em><b>Formulas</b></em>' containment reference list.
	 * The list contents are of type {@link br.ufes.inf.nemo.z3py.Quantification}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formulas</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formulas</em>' containment reference list.
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getOntoUMLZ3System_Formulas()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Quantification> getFormulas();

} // OntoUMLZ3System
