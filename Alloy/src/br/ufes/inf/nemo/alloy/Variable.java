/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.Variable#getName <em>Name</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.Variable#getDeclaration <em>Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getVariable()
 * @model
 * @generated
 */
public interface Variable extends EObject {
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
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getVariable_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.Variable#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Declaration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link br.ufes.inf.nemo.alloy.Declaration#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declaration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declaration</em>' container reference.
	 * @see #setDeclaration(Declaration)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getVariable_Declaration()
	 * @see br.ufes.inf.nemo.alloy.Declaration#getVariable
	 * @model opposite="variable" required="true" transient="false"
	 * @generated
	 */
	Declaration getDeclaration();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.Variable#getDeclaration <em>Declaration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declaration</em>' container reference.
	 * @see #getDeclaration()
	 * @generated
	 */
	void setDeclaration(Declaration value);

} // Variable
