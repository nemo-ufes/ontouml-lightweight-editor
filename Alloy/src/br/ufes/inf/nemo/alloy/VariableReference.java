/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.VariableReference#isPrefixAt <em>Prefix At</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.VariableReference#getVariable <em>Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getVariableReference()
 * @model
 * @generated
 */
public interface VariableReference extends Expression {
	/**
	 * Returns the value of the '<em><b>Prefix At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefix At</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefix At</em>' attribute.
	 * @see #setPrefixAt(boolean)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getVariableReference_PrefixAt()
	 * @model required="true"
	 * @generated
	 */
	boolean isPrefixAt();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.VariableReference#isPrefixAt <em>Prefix At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix At</em>' attribute.
	 * @see #isPrefixAt()
	 * @generated
	 */
	void setPrefixAt(boolean value);

	/**
	 * Returns the value of the '<em><b>Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' attribute.
	 * @see #setVariable(String)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getVariableReference_Variable()
	 * @model required="true"
	 * @generated
	 */
	String getVariable();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.VariableReference#getVariable <em>Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable</em>' attribute.
	 * @see #getVariable()
	 * @generated
	 */
	void setVariable(String value);

} // VariableReference
