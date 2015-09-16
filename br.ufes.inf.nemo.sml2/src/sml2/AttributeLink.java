/**
 */
package sml2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Link</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see sml2.Sml2Package#getAttributeLink()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='sourceIsParticipant_Reference2 targetIsAttribute'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL sourceIsParticipant_Reference2='self.source.oclIsKindOf(Participant) or \r\nself.source.oclIsKindOf(ModeReference) or\r\n(self.source.oclIsKindOf(ReferenceNode) and \r\n\t(self.source.oclAsType(ReferenceNode).reference.oclIsKindOf(Participant) or\r\n\t(self.source.oclAsType(ReferenceNode).reference.oclIsKindOf(ModeReference))' targetIsAttribute='self.target.oclIsKindOf(AttributeReference)'"
 *        annotation="Comments sourceIsParticipant_Reference2='The source of an AttributeLink must be a Participant, a ModeReference or a ReferenceNode, which in this case must be a reference to a Participant or a ModeReference' targetIsAttribute='The target of an AttributeLink must be an Attribute'"
 * @generated
 */
public interface AttributeLink extends SituationTypeAssociation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='self.source'"
	 * @generated
	 */
	Node getEntity();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='self.target'"
	 * @generated
	 */
	AttributeReference getAttribute();

} // AttributeLink
