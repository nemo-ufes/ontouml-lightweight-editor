/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An expression is a structured tree of symbols that denotes a (possibly empty) set of values when evaluated in a context.
 * An expression represents a node in an expression tree, which may be non-terminal or terminal. It defines a symbol, and has a possibly empty sequence of operands which are value specifications.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Expression#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link RefOntoUML.Expression#getOperand <em>Operand</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getExpression()
 * @model
 * @generated
 */
public interface Expression extends ValueSpecification {
	/**
	 * Returns the value of the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The symbol associated with the node in the expression tree.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Symbol</em>' attribute.
	 * @see #isSetSymbol()
	 * @see #unsetSymbol()
	 * @see #setSymbol(String)
	 * @see RefOntoUML.RefOntoUMLPackage#getExpression_Symbol()
	 * @model unsettable="true" dataType="RefOntoUML.String" ordered="false"
	 * @generated
	 */
	String getSymbol();

	/**
	 * Sets the value of the '{@link RefOntoUML.Expression#getSymbol <em>Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Symbol</em>' attribute.
	 * @see #isSetSymbol()
	 * @see #unsetSymbol()
	 * @see #getSymbol()
	 * @generated
	 */
	void setSymbol(String value);

	/**
	 * Unsets the value of the '{@link RefOntoUML.Expression#getSymbol <em>Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSymbol()
	 * @see #getSymbol()
	 * @see #setSymbol(String)
	 * @generated
	 */
	void unsetSymbol();

	/**
	 * Returns whether the value of the '{@link RefOntoUML.Expression#getSymbol <em>Symbol</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Symbol</em>' attribute is set.
	 * @see #unsetSymbol()
	 * @see #getSymbol()
	 * @see #setSymbol(String)
	 * @generated
	 */
	boolean isSetSymbol();

	/**
	 * Returns the value of the '<em><b>Operand</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.ValueSpecification}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies a sequence of operands.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Operand</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getExpression_Operand()
	 * @model containment="true"
	 * @generated
	 */
	EList<ValueSpecification> getOperand();

} // Expression
