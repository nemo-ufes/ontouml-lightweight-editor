/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Material Association</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getMaterialAssociation()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='MaterialAssociationConstraint1 MaterialAssociationConstraint2 MaterialAssociationConstraint3'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL MaterialAssociationConstraint1='Derivation.allInstances()->one( x | x.material() = self )' MaterialAssociationConstraint2='memberEnd->forAll( x | x.lower >= 1 )' MaterialAssociationConstraint3='isDerived'"
 *        annotation="Comments MaterialAssociationConstraint1='Every MaterialAssociation must be connected to exactly one Derivation' MaterialAssociationConstraint2='The minimum cardinality of every end must be greater or equal to 1' MaterialAssociationConstraint3='A MaterialAssociation must be derived'"
 * @generated
 */
public interface MaterialAssociation extends Association {
} // MaterialAssociation
