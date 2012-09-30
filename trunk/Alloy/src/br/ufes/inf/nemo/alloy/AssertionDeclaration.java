/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assertion Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.AssertionDeclaration#getBlock <em>Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getAssertionDeclaration()
 * @model
 * @generated
 */
public interface AssertionDeclaration extends Paragraph {
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
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getAssertionDeclaration_Block()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Block getBlock();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.AssertionDeclaration#getBlock <em>Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Block</em>' containment reference.
	 * @see #getBlock()
	 * @generated
	 */
	void setBlock(Block value);

} // AssertionDeclaration
