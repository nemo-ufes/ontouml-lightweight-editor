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
 * A representation of the model object '<em><b>Inheritance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.Inheritance#getSupertype <em>Supertype</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.Inheritance#isIsSubset <em>Is Subset</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.Inheritance#isIsExtension <em>Is Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getInheritance()
 * @model
 * @generated
 */
public interface Inheritance extends EObject {
	/**
	 * Returns the value of the '<em><b>Supertype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supertype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supertype</em>' attribute.
	 * @see #setSupertype(String)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getInheritance_Supertype()
	 * @model required="true"
	 * @generated
	 */
	String getSupertype();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.Inheritance#getSupertype <em>Supertype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supertype</em>' attribute.
	 * @see #getSupertype()
	 * @generated
	 */
	void setSupertype(String value);

	/**
	 * Returns the value of the '<em><b>Is Subset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Subset</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Subset</em>' attribute.
	 * @see #setIsSubset(boolean)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getInheritance_IsSubset()
	 * @model required="true"
	 * @generated
	 */
	boolean isIsSubset();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.Inheritance#isIsSubset <em>Is Subset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Subset</em>' attribute.
	 * @see #isIsSubset()
	 * @generated
	 */
	void setIsSubset(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Extension</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Extension</em>' attribute.
	 * @see #setIsExtension(boolean)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getInheritance_IsExtension()
	 * @model required="true"
	 * @generated
	 */
	boolean isIsExtension();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.Inheritance#isIsExtension <em>Is Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Extension</em>' attribute.
	 * @see #isIsExtension()
	 * @generated
	 */
	void setIsExtension(boolean value);

} // Inheritance
