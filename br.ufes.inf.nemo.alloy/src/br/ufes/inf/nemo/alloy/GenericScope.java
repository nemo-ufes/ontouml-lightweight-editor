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
 * A representation of the model object '<em><b>Generic Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.GenericScope#getScopeable <em>Scopeable</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.GenericScope#getScopeSize <em>Scope Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getGenericScope()
 * @model
 * @generated
 */
public interface GenericScope extends ScopeSpecification {
	/**
	 * Returns the value of the '<em><b>Scopeable</b></em>' containment reference list.
	 * The list contents are of type {@link br.ufes.inf.nemo.alloy.Scopeable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scopeable</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scopeable</em>' containment reference list.
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getGenericScope_Scopeable()
	 * @model containment="true"
	 * @generated
	 */
	EList<Scopeable> getScopeable();

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
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getGenericScope_ScopeSize()
	 * @model required="true"
	 * @generated
	 */
	int getScopeSize();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.GenericScope#getScopeSize <em>Scope Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope Size</em>' attribute.
	 * @see #getScopeSize()
	 * @generated
	 */
	void setScopeSize(int value);

} // GenericScope
