/**
 */
package sml2;

import RefOntoUML.Characterization;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Characterization Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.CharacterizationLink#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getCharacterizationLink()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='sourceIsMode targetIsEntity_Reference sameEndsAsType3'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL sourceIsMode='self.source.oclIsKindOf(ModeReference)' targetIsEntity_Reference='self.target.oclIsKindOf(EntityParticipant) or \r\n(self.target.oclIsKindOf(ReferenceNode) and \r\n\t(self.target.oclAsType(ReferenceNode).reference.oclIsKindOf(EntityParticipant))' sameEndsAsType3='self.type.oclAsType(RefOntoUML::Characterization).characterizing() = self.source.oclAsType(ModeReference).type and\r\nself.type.oclAsType(RefOntoUML::Characterization).characterized() = self.target.oclAsType(EntityParticipant).type'"
 *        annotation="Comments sourceIsMode='The source of a CharacterizationLink must be a ModeReference' targetIsEntity_Reference='The target of a CharacterizationLink must be an EntityParticipant or a ReferenceNode, which in this case must be a reference to an EntityParticipant' sameEndsAsType3='A CharacterizationLink must connect the same entities as its Characterization does'"
 * @generated
 */
public interface CharacterizationLink extends SituationTypeAssociation {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Characterization)
	 * @see sml2.Sml2Package#getCharacterizationLink_Type()
	 * @model required="true"
	 * @generated
	 */
	Characterization getType();

	/**
	 * Sets the value of the '{@link sml2.CharacterizationLink#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Characterization value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='self.source'"
	 * @generated
	 */
	ModeReference getMode();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='self.target'"
	 * @generated
	 */
	EntityParticipant getCharacterized();

} // CharacterizationLink
