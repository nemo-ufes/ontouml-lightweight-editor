/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arrow Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.ArrowOperation#getLeftMultiplicity <em>Left Multiplicity</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.ArrowOperation#getRightMultiplicity <em>Right Multiplicity</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.ArrowOperation#getLeftExpression <em>Left Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.ArrowOperation#getRightExpression <em>Right Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getArrowOperation()
 * @model
 * @generated
 */
public interface ArrowOperation extends Expression {
	/**
	 * Returns the value of the '<em><b>Left Multiplicity</b></em>' attribute.
	 * The literals are from the enumeration {@link br.ufes.inf.nemo.alloy.Multiplicity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Multiplicity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Multiplicity</em>' attribute.
	 * @see br.ufes.inf.nemo.alloy.Multiplicity
	 * @see #setLeftMultiplicity(Multiplicity)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getArrowOperation_LeftMultiplicity()
	 * @model
	 * @generated
	 */
	Multiplicity getLeftMultiplicity();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.ArrowOperation#getLeftMultiplicity <em>Left Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Multiplicity</em>' attribute.
	 * @see br.ufes.inf.nemo.alloy.Multiplicity
	 * @see #getLeftMultiplicity()
	 * @generated
	 */
	void setLeftMultiplicity(Multiplicity value);

	/**
	 * Returns the value of the '<em><b>Right Multiplicity</b></em>' attribute.
	 * The literals are from the enumeration {@link br.ufes.inf.nemo.alloy.Multiplicity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Multiplicity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Multiplicity</em>' attribute.
	 * @see br.ufes.inf.nemo.alloy.Multiplicity
	 * @see #setRightMultiplicity(Multiplicity)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getArrowOperation_RightMultiplicity()
	 * @model
	 * @generated
	 */
	Multiplicity getRightMultiplicity();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.ArrowOperation#getRightMultiplicity <em>Right Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Multiplicity</em>' attribute.
	 * @see br.ufes.inf.nemo.alloy.Multiplicity
	 * @see #getRightMultiplicity()
	 * @generated
	 */
	void setRightMultiplicity(Multiplicity value);

	/**
	 * Returns the value of the '<em><b>Left Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Expression</em>' containment reference.
	 * @see #setLeftExpression(Expression)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getArrowOperation_LeftExpression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getLeftExpression();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.ArrowOperation#getLeftExpression <em>Left Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Expression</em>' containment reference.
	 * @see #getLeftExpression()
	 * @generated
	 */
	void setLeftExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Right Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Expression</em>' containment reference.
	 * @see #setRightExpression(Expression)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getArrowOperation_RightExpression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getRightExpression();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.ArrowOperation#getRightExpression <em>Right Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Expression</em>' containment reference.
	 * @see #getRightExpression()
	 * @generated
	 */
	void setRightExpression(Expression value);

} // ArrowOperation
