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
 * A representation of the model object '<em><b>Let Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.LetDeclaration#getEqualExpression <em>Equal Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.LetDeclaration#getBlock <em>Block</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.LetDeclaration#getName <em>Name</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.LetDeclaration#getBarExpression <em>Bar Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.LetDeclaration#isHasBlock <em>Has Block</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.LetDeclaration#isHasExpression <em>Has Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getLetDeclaration()
 * @model
 * @generated
 */
public interface LetDeclaration extends Expression {
	/**
	 * Returns the value of the '<em><b>Equal Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Equal Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Equal Expression</em>' containment reference.
	 * @see #setEqualExpression(Expression)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getLetDeclaration_EqualExpression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getEqualExpression();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.LetDeclaration#getEqualExpression <em>Equal Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Equal Expression</em>' containment reference.
	 * @see #getEqualExpression()
	 * @generated
	 */
	void setEqualExpression(Expression value);

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
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getLetDeclaration_Block()
	 * @model containment="true"
	 * @generated
	 */
	Block getBlock();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.LetDeclaration#getBlock <em>Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Block</em>' containment reference.
	 * @see #getBlock()
	 * @generated
	 */
	void setBlock(Block value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute list.
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getLetDeclaration_Name()
	 * @model required="true"
	 * @generated
	 */
	EList<String> getName();

	/**
	 * Returns the value of the '<em><b>Bar Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bar Expression</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bar Expression</em>' reference.
	 * @see #setBarExpression(Expression)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getLetDeclaration_BarExpression()
	 * @model
	 * @generated
	 */
	Expression getBarExpression();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.LetDeclaration#getBarExpression <em>Bar Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bar Expression</em>' reference.
	 * @see #getBarExpression()
	 * @generated
	 */
	void setBarExpression(Expression value);

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
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getLetDeclaration_HasBlock()
	 * @model required="true"
	 * @generated
	 */
	boolean isHasBlock();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.LetDeclaration#isHasBlock <em>Has Block</em>}' attribute.
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
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getLetDeclaration_HasExpression()
	 * @model required="true"
	 * @generated
	 */
	boolean isHasExpression();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.LetDeclaration#isHasExpression <em>Has Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Expression</em>' attribute.
	 * @see #isHasExpression()
	 * @generated
	 */
	void setHasExpression(boolean value);

} // LetDeclaration
