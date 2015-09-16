/**
 */
package sml2;

import RefOntoUML.Mediation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mediation Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.MediationLink#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getMediationLink()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='sourceIsRelator targetIsEntity sameEndsAsType'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL sourceIsRelator='self.source.oclIsKindOf(RelatorParticipant)' targetIsEntity='self.target.oclIsKindOf(EntityParticipant)' sameEndsAsType='self.type.oclAsType(RefOntoUML::Mediation).relator() = self.source.oclAsType(RelatorParticipant).type and\r\nself.type.oclAsType(RefOntoUML::Mediation).mediated() = self.target.oclAsType(EntityParticipant).type'"
 *        annotation="Comments sourceIsRelator='The source of a MediationLink must be a RelatorParticipant' targetIsEntity='The target of a MediationLink must be an EntityParticipant' sameEndsAsType='A MediationLink must connect the same entities as its Mediation does'"
 * @generated
 */
public interface MediationLink extends SituationTypeAssociation {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Mediation)
	 * @see sml2.Sml2Package#getMediationLink_Type()
	 * @model required="true"
	 * @generated
	 */
	Mediation getType();

	/**
	 * Sets the value of the '{@link sml2.MediationLink#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Mediation value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='self.source'"
	 * @generated
	 */
	RelatorParticipant getRelator();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='self.target'"
	 * @generated
	 */
	EntityParticipant getEntity();

} // MediationLink
