/**
 */
package sml2;

import RefOntoUML.FormalAssociation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context Formal Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.ContextFormalLink#getParameter <em>Parameter</em>}</li>
 *   <li>{@link sml2.ContextFormalLink#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getContextFormalLink()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='sameEndsAsType2'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL sameEndsAsType2='self.type.oclAsType(RefOntoUML::FormalAssociation).memberEnd->exists(x,y | x = self.source.type and y = self.target.type)'"
 *        annotation="Comments sameEndsAsType2='A FormalLink must connect the same entities as its FormalAssociation does'"
 * @generated
 */
public interface ContextFormalLink extends FormalRelation {
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' attribute.
	 * @see #setParameter(String)
	 * @see sml2.Sml2Package#getContextFormalLink_Parameter()
	 * @model
	 * @generated
	 */
	String getParameter();

	/**
	 * Sets the value of the '{@link sml2.ContextFormalLink#getParameter <em>Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' attribute.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(FormalAssociation)
	 * @see sml2.Sml2Package#getContextFormalLink_Type()
	 * @model required="true"
	 * @generated
	 */
	FormalAssociation getType();

	/**
	 * Sets the value of the '{@link sml2.ContextFormalLink#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(FormalAssociation value);

} // ContextFormalLink
