/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Directed Binary Association</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getDirectedBinaryAssociation()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='DirectedBinaryAssociationConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL DirectedBinaryAssociationConstraint1='memberEnd->size() = 2'"
 *        annotation="Comments DirectedBinaryAssociationConstraint1='DirectedBinaryAssociations are always binary'"
 * @generated
 */
public interface DirectedBinaryAssociation extends Association {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='memberEnd->at(1)'"
	 * @generated
	 */
	Property sourceEnd();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='memberEnd->at(2)'"
	 * @generated
	 */
	Property targetEnd();

} // DirectedBinaryAssociation
