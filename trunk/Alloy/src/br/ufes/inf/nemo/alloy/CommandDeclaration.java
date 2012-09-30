/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Command Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.CommandDeclaration#getScope <em>Scope</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.CommandDeclaration#getBlock <em>Block</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.CommandDeclaration#getAssertion <em>Assertion</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.CommandDeclaration#getPredicate <em>Predicate</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.CommandDeclaration#isIsRun <em>Is Run</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.CommandDeclaration#isIsCheck <em>Is Check</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getCommandDeclaration()
 * @model
 * @generated
 */
public interface CommandDeclaration extends Paragraph {
	/**
	 * Returns the value of the '<em><b>Scope</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope</em>' containment reference.
	 * @see #setScope(ScopeSpecification)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getCommandDeclaration_Scope()
	 * @model containment="true"
	 * @generated
	 */
	ScopeSpecification getScope();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.CommandDeclaration#getScope <em>Scope</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope</em>' containment reference.
	 * @see #getScope()
	 * @generated
	 */
	void setScope(ScopeSpecification value);

	/**
	 * Returns the value of the '<em><b>Block</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Block</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Block</em>' reference.
	 * @see #setBlock(Block)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getCommandDeclaration_Block()
	 * @model
	 * @generated
	 */
	Block getBlock();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.CommandDeclaration#getBlock <em>Block</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Block</em>' reference.
	 * @see #getBlock()
	 * @generated
	 */
	void setBlock(Block value);

	/**
	 * Returns the value of the '<em><b>Assertion</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assertion</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assertion</em>' reference.
	 * @see #setAssertion(AssertionDeclaration)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getCommandDeclaration_Assertion()
	 * @model
	 * @generated
	 */
	AssertionDeclaration getAssertion();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.CommandDeclaration#getAssertion <em>Assertion</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assertion</em>' reference.
	 * @see #getAssertion()
	 * @generated
	 */
	void setAssertion(AssertionDeclaration value);

	/**
	 * Returns the value of the '<em><b>Predicate</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predicate</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predicate</em>' reference.
	 * @see #setPredicate(PredicateDeclaration)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getCommandDeclaration_Predicate()
	 * @model
	 * @generated
	 */
	PredicateDeclaration getPredicate();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.CommandDeclaration#getPredicate <em>Predicate</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicate</em>' reference.
	 * @see #getPredicate()
	 * @generated
	 */
	void setPredicate(PredicateDeclaration value);

	/**
	 * Returns the value of the '<em><b>Is Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Run</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Run</em>' attribute.
	 * @see #setIsRun(boolean)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getCommandDeclaration_IsRun()
	 * @model required="true"
	 * @generated
	 */
	boolean isIsRun();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.CommandDeclaration#isIsRun <em>Is Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Run</em>' attribute.
	 * @see #isIsRun()
	 * @generated
	 */
	void setIsRun(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Check</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Check</em>' attribute.
	 * @see #setIsCheck(boolean)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getCommandDeclaration_IsCheck()
	 * @model required="true"
	 * @generated
	 */
	boolean isIsCheck();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.CommandDeclaration#isIsCheck <em>Is Check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Check</em>' attribute.
	 * @see #isIsCheck()
	 * @generated
	 */
	void setIsCheck(boolean value);

} // CommandDeclaration
