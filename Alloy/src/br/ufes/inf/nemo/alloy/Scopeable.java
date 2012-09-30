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
 * A representation of the model object '<em><b>Scopeable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.Scopeable#getScopeSize <em>Scope Size</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.Scopeable#isIsExactly <em>Is Exactly</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.Scopeable#getSignature <em>Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getScopeable()
 * @model
 * @generated
 */
public interface Scopeable extends EObject {
	/**
	 * Returns the value of the '<em><b>Scope Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope Size</em>' attribute.
	 * @see #setScopeSize(int)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getScopeable_ScopeSize()
	 * @model required="true"
	 * @generated
	 */
	int getScopeSize();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.Scopeable#getScopeSize <em>Scope Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope Size</em>' attribute.
	 * @see #getScopeSize()
	 * @generated
	 */
	void setScopeSize(int value);

	/**
	 * Returns the value of the '<em><b>Is Exactly</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Exactly</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Exactly</em>' attribute.
	 * @see #setIsExactly(boolean)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getScopeable_IsExactly()
	 * @model
	 * @generated
	 */
	boolean isIsExactly();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.Scopeable#isIsExactly <em>Is Exactly</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Exactly</em>' attribute.
	 * @see #isIsExactly()
	 * @generated
	 */
	void setIsExactly(boolean value);

	/**
	 * Returns the value of the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signature</em>' attribute.
	 * @see #setSignature(String)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getScopeable_Signature()
	 * @model required="true"
	 * @generated
	 */
	String getSignature();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.Scopeable#getSignature <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signature</em>' attribute.
	 * @see #getSignature()
	 * @generated
	 */
	void setSignature(String value);

} // Scopeable
