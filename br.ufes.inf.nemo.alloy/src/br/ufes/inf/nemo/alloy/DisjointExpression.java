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
 * A representation of the model object '<em><b>Disjoint Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.DisjointExpression#getSet <em>Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getDisjointExpression()
 * @model
 * @generated
 */
public interface DisjointExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Set</b></em>' containment reference list.
	 * The list contents are of type {@link br.ufes.inf.nemo.alloy.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Set</em>' containment reference list.
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getDisjointExpression_Set()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getSet();

} // DisjointExpression
