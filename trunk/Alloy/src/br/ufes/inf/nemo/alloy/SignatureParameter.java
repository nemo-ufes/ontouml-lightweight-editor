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
 * A representation of the model object '<em><b>Signature Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.SignatureParameter#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getSignatureParameter()
 * @model
 * @generated
 */
public interface SignatureParameter extends EObject {
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
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getSignatureParameter_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.SignatureParameter#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // SignatureParameter
