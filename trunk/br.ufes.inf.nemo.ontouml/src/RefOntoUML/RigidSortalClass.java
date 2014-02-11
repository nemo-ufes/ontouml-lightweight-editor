/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rigid Sortal Class</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getRigidSortalClass()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='RigidSortalClassConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL RigidSortalClassConstraint1='parents()->select( x | x.oclIsKindOf(AntiRigidSortalClass) or x.oclIsKindOf(RoleMixin) )->isEmpty()'"
 *        annotation="Comments RigidSortalClassConstraint1='A RigidSortalClass cannot have an Anti-Rigid parent (role, phase, roleMixin)'"
 * @generated
 */
public interface RigidSortalClass extends SortalClass {
} // RigidSortalClass
