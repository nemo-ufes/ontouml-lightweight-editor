/**
 */
package sml2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instantiation Link</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see sml2.Sml2Package#getInstantiationLink()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='sourceIsParticipant_Reference targetIsLiteral'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL sourceIsParticipant_Reference='self.source.oclIsKindOf(EntityParticipant) or self.source.oclIsKindOf(RelatorParticipant) or (self.source.oclIsKindOf(ReferenceNode) and (self.source.oclAsType(ReferenceNode).reference.oclIsKindOf(EntityParticipant) or self.source.oclAsType(ReferenceNode).reference.oclIsKindOf(RelatorParticipant)))' targetIsLiteral='self.target.oclIsKindOf(TypeLiteral) and self.target.oclAsType(TypeLiteral).type.oclIsKindOf(RefOntoUML::Class)'"
 *        annotation="Comments sourceIsParticipant_Reference='The source of an Instantiation must be an EntityParticipant, a RelatorParticipant or a ReferenceNode, which in this case must be a reference to an EntityParticipant or RelatorParticipant' targetIsLiteral='The target of an Instantiation must be a TypeLiteral and its type must be a RefOntoUML::Class'"
 * @generated
 */
public interface InstantiationLink extends FormalRelation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	Participant getSource();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	TypeLiteral getTarget();

} // InstantiationLink
