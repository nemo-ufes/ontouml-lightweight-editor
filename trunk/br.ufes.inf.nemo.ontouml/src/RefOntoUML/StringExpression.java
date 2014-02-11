/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An expression that specifies a string value that is derived by concatenating a set of sub string expressions, some of which might be template parameters.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.StringExpression#getSubExpression <em>Sub Expression</em>}</li>
 *   <li>{@link RefOntoUML.StringExpression#getOwningExpression <em>Owning Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getStringExpression()
 * @model
 * @generated
 */
public interface StringExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Sub Expression</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.StringExpression}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.StringExpression#getOwningExpression <em>Owning Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The StringExpressions that constitute this StringExpression.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sub Expression</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getStringExpression_SubExpression()
	 * @see RefOntoUML.StringExpression#getOwningExpression
	 * @model opposite="owningExpression" containment="true" ordered="false"
	 * @generated
	 */
	EList<StringExpression> getSubExpression();

	/**
	 * Returns the value of the '<em><b>Owning Expression</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.StringExpression#getSubExpression <em>Sub Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The string expression of which this expression is a substring.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning Expression</em>' container reference.
	 * @see #setOwningExpression(StringExpression)
	 * @see RefOntoUML.RefOntoUMLPackage#getStringExpression_OwningExpression()
	 * @see RefOntoUML.StringExpression#getSubExpression
	 * @model opposite="subExpression" transient="false" ordered="false"
	 * @generated
	 */
	StringExpression getOwningExpression();

	/**
	 * Sets the value of the '{@link RefOntoUML.StringExpression#getOwningExpression <em>Owning Expression</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Expression</em>' container reference.
	 * @see #getOwningExpression()
	 * @generated
	 */
	void setOwningExpression(StringExpression value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * All the operands of a StringExpression must be LiteralStrings
	 * operand->forAll (op | op.oclIsKindOf (LiteralString))
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean operands(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If a StringExpression has sub-expressions, it cannot have operands and vice versa (this avoids the problem of having to
	 * define a collating sequence between operands and subexpressions).
	 * 
	 * if subExpression->notEmpty() then operand->isEmpty() else operand->notEmpty()
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean subexpressions(DiagnosticChain diagnostics, Map<Object, Object> context);

} // StringExpression
