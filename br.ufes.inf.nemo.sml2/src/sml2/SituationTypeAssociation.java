/**
 */
package sml2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Situation Type Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.SituationTypeAssociation#getSource <em>Source</em>}</li>
 *   <li>{@link sml2.SituationTypeAssociation#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getSituationTypeAssociation()
 * @model abstract="true"
 * @generated
 */
public interface SituationTypeAssociation extends SituationTypeElement {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Node)
	 * @see sml2.Sml2Package#getSituationTypeAssociation_Source()
	 * @model required="true"
	 * @generated
	 */
	Node getSource();

	/**
	 * Sets the value of the '{@link sml2.SituationTypeAssociation#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Node value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Node)
	 * @see sml2.Sml2Package#getSituationTypeAssociation_Target()
	 * @model required="true"
	 * @generated
	 */
	Node getTarget();

	/**
	 * Sets the value of the '{@link sml2.SituationTypeAssociation#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Node value);

} // SituationTypeAssociation
