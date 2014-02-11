/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;

import java.math.BigDecimal;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Literal Decimal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.LiteralDecimal#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getLiteralDecimal()
 * @model
 * @generated
 */
public interface LiteralDecimal extends LiteralSpecification {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(BigDecimal)
	 * @see RefOntoUML.RefOntoUMLPackage#getLiteralDecimal_Value()
	 * @model default="0" dataType="RefOntoUML.Decimal" required="true" ordered="false"
	 * @generated
	 */
	BigDecimal getValue();

	/**
	 * Sets the value of the '{@link RefOntoUML.LiteralDecimal#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(BigDecimal value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query decimalValue() gives a single Decimal value when one can be computed.
	 * result = Set{}
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.Decimal" required="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='value'"
	 * @generated
	 */
	BigDecimal decimalValue();

} // LiteralDecimal
