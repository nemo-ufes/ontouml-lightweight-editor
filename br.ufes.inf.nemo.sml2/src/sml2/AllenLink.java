/**
 */
package sml2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Allen Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.AllenLink#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getAllenLink()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='sourceIsSituation targetIsSituation'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL sourceIsSituation='self.source.oclIsKindOf(SituationParticipant)' targetIsSituation='self.target.oclIsKindOf(SituationParticipant)'"
 *        annotation="Comments sourceIsSituation='The source of an AllenRelation must be a SituationParticipant' targetIsSituation='The target of an AllenRelation must be a SituationParticipant'"
 * @generated
 */
public interface AllenLink extends FormalRelation {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link sml2.AllenKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see sml2.AllenKind
	 * @see #setType(AllenKind)
	 * @see sml2.Sml2Package#getAllenLink_Type()
	 * @model required="true"
	 * @generated
	 */
	AllenKind getType();

	/**
	 * Sets the value of the '{@link sml2.AllenLink#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see sml2.AllenKind
	 * @see #getType()
	 * @generated
	 */
	void setType(AllenKind value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	SituationParticipant getSource();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	SituationParticipant getTarget();

} // AllenLink
