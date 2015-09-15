/**
 */
package sml2;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.FunctionParameter#getLabel <em>Label</em>}</li>
 *   <li>{@link sml2.FunctionParameter#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getFunctionParameter()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='sourceIsFunction'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL sourceIsFunction='self.source.oclIsKindOf(Function)'"
 *        annotation="Comments sourceIsFunction='The source of a FunctionParameter must be a Function'"
 * @generated
 */
public interface FunctionParameter extends EObject {
	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see sml2.Sml2Package#getFunctionParameter_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link sml2.FunctionParameter#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference.
	 * @see #setParameter(Node)
	 * @see sml2.Sml2Package#getFunctionParameter_Parameter()
	 * @model required="true"
	 * @generated
	 */
	Node getParameter();

	/**
	 * Sets the value of the '{@link sml2.FunctionParameter#getParameter <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(Node value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='self.source'"
	 * @generated
	 */
	Function getFunction();

} // FunctionParameter
