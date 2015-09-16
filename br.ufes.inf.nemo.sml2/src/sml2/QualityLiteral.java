/**
 */
package sml2;

import RefOntoUML.ReferenceStructure;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quality Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.QualityLiteral#getValue <em>Value</em>}</li>
 *   <li>{@link sml2.QualityLiteral#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getQualityLiteral()
 * @model
 * @generated
 */
public interface QualityLiteral extends Literal {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see sml2.Sml2Package#getQualityLiteral_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link sml2.QualityLiteral#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(ReferenceStructure)
	 * @see sml2.Sml2Package#getQualityLiteral_Type()
	 * @model required="true"
	 * @generated
	 */
	ReferenceStructure getType();

	/**
	 * Sets the value of the '{@link sml2.QualityLiteral#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(ReferenceStructure value);

} // QualityLiteral
