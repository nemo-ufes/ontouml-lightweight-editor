/**
 */
package sml2;

import RefOntoUML.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.AttributeReference#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getAttributeReference()
 * @model annotation="Comments complexAttr_Function='An attribute that has a type that is not a PrimitiveType must be addressed by a Function'"
 * @generated
 */
public interface AttributeReference extends ReferableElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Property)
	 * @see sml2.Sml2Package#getAttributeReference_Type()
	 * @model required="true"
	 * @generated
	 */
	Property getType();

	/**
	 * Sets the value of the '{@link sml2.AttributeReference#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Property value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='AttributeLink.allInstances()->select(x | x.getAttribute() = self)->asOrderedSet()->at(1).getEntity()'"
	 *        annotation="Comments getEntity='Returns the Node that owns the AttributeReference. The Node can only be a Participant or a NodeReference, which in this case will be a reference also to a Participant.'"
	 * @generated
	 */
	Node getEntity();

} // AttributeReference
