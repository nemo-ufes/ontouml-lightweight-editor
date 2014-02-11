/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dependency Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getDependencyRelationship()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='DependencyRelationshipConstraint1 DependencyRelationshipConstraint2'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL DependencyRelationshipConstraint1='sourceEnd().lower >= 1' DependencyRelationshipConstraint2='targetEnd().isReadOnly'"
 *        annotation="Comments DependencyRelationshipConstraint1='The source end minimum cardinality must be greater or equal to 1' DependencyRelationshipConstraint2='The target end is read only'"
 * @generated
 */
public interface DependencyRelationship extends DirectedBinaryAssociation {
} // DependencyRelationship
