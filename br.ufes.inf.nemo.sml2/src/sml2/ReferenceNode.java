/**
 */
package sml2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.ReferenceNode#getLabel <em>Label</em>}</li>
 *   <li>{@link sml2.ReferenceNode#getSituation <em>Situation</em>}</li>
 *   <li>{@link sml2.ReferenceNode#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getReferenceNode()
 * @model
 * @generated
 */
public interface ReferenceNode extends Node {
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
	 * @see sml2.Sml2Package#getReferenceNode_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link sml2.ReferenceNode#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Situation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link sml2.SituationParticipant#getReferences <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Situation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Situation</em>' container reference.
	 * @see #setSituation(SituationParticipant)
	 * @see sml2.Sml2Package#getReferenceNode_Situation()
	 * @see sml2.SituationParticipant#getReferences
	 * @model opposite="references" required="true" transient="false"
	 * @generated
	 */
	SituationParticipant getSituation();

	/**
	 * Sets the value of the '{@link sml2.ReferenceNode#getSituation <em>Situation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Situation</em>' container reference.
	 * @see #getSituation()
	 * @generated
	 */
	void setSituation(SituationParticipant value);

	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference.
	 * @see #setReference(ReferableElement)
	 * @see sml2.Sml2Package#getReferenceNode_Reference()
	 * @model required="true"
	 * @generated
	 */
	ReferableElement getReference();

	/**
	 * Sets the value of the '{@link sml2.ReferenceNode#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(ReferableElement value);

} // ReferenceNode
