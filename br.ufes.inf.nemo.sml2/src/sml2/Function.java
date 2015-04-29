/**
 */
package sml2;

import RefOntoUML.FormalAssociation;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.Function#getParameter <em>Parameter</em>}</li>
 *   <li>{@link sml2.Function#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getFunction()
 * @model
 * @generated
 */
public interface Function extends ExportableNode {
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link sml2.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' containment reference list.
	 * @see sml2.Sml2Package#getFunction_Parameter()
	 * @model containment="true"
	 * @generated
	 */
	EList<Parameter> getParameter();

	/**
	 * Returns the value of the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function</em>' reference.
	 * @see #setFunction(FormalAssociation)
	 * @see sml2.Sml2Package#getFunction_Function()
	 * @model
	 * @generated
	 */
	FormalAssociation getFunction();

	/**
	 * Sets the value of the '{@link sml2.Function#getFunction <em>Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' reference.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(FormalAssociation value);

} // Function
