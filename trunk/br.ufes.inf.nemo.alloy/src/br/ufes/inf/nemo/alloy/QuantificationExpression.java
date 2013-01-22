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
 * A representation of the model object '<em><b>Quantification Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.QuantificationExpression#getQuantificator <em>Quantificator</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.QuantificationExpression#getDeclaration <em>Declaration</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.QuantificationExpression#getExpression <em>Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.QuantificationExpression#getBlock <em>Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getQuantificationExpression()
 * @model
 * @generated
 */
public interface QuantificationExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Quantificator</b></em>' attribute.
	 * The literals are from the enumeration {@link br.ufes.inf.nemo.alloy.Quantificator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quantificator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantificator</em>' attribute.
	 * @see br.ufes.inf.nemo.alloy.Quantificator
	 * @see #setQuantificator(Quantificator)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getQuantificationExpression_Quantificator()
	 * @model required="true"
	 * @generated
	 */
	Quantificator getQuantificator();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.QuantificationExpression#getQuantificator <em>Quantificator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantificator</em>' attribute.
	 * @see br.ufes.inf.nemo.alloy.Quantificator
	 * @see #getQuantificator()
	 * @generated
	 */
	void setQuantificator(Quantificator value);

	/**
	 * Returns the value of the '<em><b>Declaration</b></em>' containment reference list.
	 * The list contents are of type {@link br.ufes.inf.nemo.alloy.Declaration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declaration</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declaration</em>' containment reference list.
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getQuantificationExpression_Declaration()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Declaration> getDeclaration();

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getQuantificationExpression_Expression()
	 * @model containment="true"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.QuantificationExpression#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Block</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Block</em>' containment reference.
	 * @see #setBlock(Block)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getQuantificationExpression_Block()
	 * @model containment="true"
	 * @generated
	 */
	Block getBlock();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.QuantificationExpression#getBlock <em>Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Block</em>' containment reference.
	 * @see #getBlock()
	 * @generated
	 */
	void setBlock(Block value);

} // QuantificationExpression
