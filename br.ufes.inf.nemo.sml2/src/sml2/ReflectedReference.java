/**
 */
package sml2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reflected Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.ReflectedReference#getOwningReflection <em>Owning Reflection</em>}</li>
 *   <li>{@link sml2.ReflectedReference#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getReflectedReference()
 * @model
 * @generated
 */
public interface ReflectedReference extends Node {
	/**
	 * Returns the value of the '<em><b>Owning Reflection</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link sml2.ReflectedParticipant#getReferences <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Reflection</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Reflection</em>' container reference.
	 * @see #setOwningReflection(ReflectedParticipant)
	 * @see sml2.Sml2Package#getReflectedReference_OwningReflection()
	 * @see sml2.ReflectedParticipant#getReferences
	 * @model opposite="references" required="true" transient="false"
	 * @generated
	 */
	ReflectedParticipant getOwningReflection();

	/**
	 * Sets the value of the '{@link sml2.ReflectedReference#getOwningReflection <em>Owning Reflection</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Reflection</em>' container reference.
	 * @see #getOwningReflection()
	 * @generated
	 */
	void setOwningReflection(ReflectedParticipant value);

	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference.
	 * @see #setReference(ReferenceNode)
	 * @see sml2.Sml2Package#getReflectedReference_Reference()
	 * @model required="true"
	 * @generated
	 */
	ReferenceNode getReference();

	/**
	 * Sets the value of the '{@link sml2.ReflectedReference#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(ReferenceNode value);

} // ReflectedReference
