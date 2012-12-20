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
 * A representation of the model object '<em><b>Comprehension Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#getDeclaration <em>Declaration</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#getBlock <em>Block</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#getExpression <em>Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#isHasBlock <em>Has Block</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#isHasExpression <em>Has Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getComprehensionExpression()
 * @model
 * @generated
 */
public interface ComprehensionExpression extends Expression {
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
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getComprehensionExpression_Declaration()
	 * @model containment="true" required="true"
	 * @generated
	 */
	
	EList<Declaration> getDeclaration();

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
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getComprehensionExpression_Block()
	 * @model containment="true"
	 * @generated
	 */
	Block getBlock();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#getBlock <em>Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Block</em>' containment reference.
	 * @see #getBlock()
	 * @generated
	 */
	void setBlock(Block value);

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
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getComprehensionExpression_Expression()
	 * @model containment="true"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Has Block</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Block</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Block</em>' attribute.
	 * @see #setHasBlock(boolean)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getComprehensionExpression_HasBlock()
	 * @model required="true"
	 * @generated
	 */
	boolean isHasBlock();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#isHasBlock <em>Has Block</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Block</em>' attribute.
	 * @see #isHasBlock()
	 * @generated
	 */
	void setHasBlock(boolean value);

	/**
	 * Returns the value of the '<em><b>Has Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Expression</em>' attribute.
	 * @see #setHasExpression(boolean)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getComprehensionExpression_HasExpression()
	 * @model required="true"
	 * @generated
	 */
	boolean isHasExpression();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.ComprehensionExpression#isHasExpression <em>Has Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Expression</em>' attribute.
	 * @see #isHasExpression()
	 * @generated
	 */
	void setHasExpression(boolean value);

} // ComprehensionExpression
