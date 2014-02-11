/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Substance Sortal</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getSubstanceSortal()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='SubstanceSortalConstraint2b'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL SubstanceSortalConstraint2b='parents()->select( x | x.oclIsKindOf(RigidSortalClass) )->isEmpty()'"
 *        annotation="Comments SubstanceSortalConstraint2b='A Substance Sortal cannot have a Rigid Sortal parent'"
 * @generated
 */
public interface SubstanceSortal extends RigidSortalClass {
} // SubstanceSortal
