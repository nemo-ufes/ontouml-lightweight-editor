/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role Mixin</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getRoleMixin()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='RoleMixinConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL RoleMixinConstraint1='Mediation.allInstances()->exists( x | allParents()->including(self)->includes(x.mediated()) )'"
 *        annotation="Comments RoleMixinConstraint1='A RoleMixin must be connected (directly or indirectly) to a Mediation'"
 * @generated
 */
public interface RoleMixin extends AntiRigidMixinClass {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='Mediation.allInstances()->any( m | m.mediated() = self )'"
	 * @generated
	 */
	Mediation mediation();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='if mediation()->notEmpty() then mediation().relator() else null endif'"
	 * @generated
	 */
	Relator relator();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='children()->select(oclIsTypeOf(Role)).oclAsType(Role)->asSet()'"
	 *        annotation="Comments roles='Returns all the children Roles of the RoleMixin.'"
	 * @generated
	 */
	EList<Role> roles();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='roles().rigidParent()->asSet()'"
	 *        annotation="Comments rigidSortals='Returns the more specific rigid parents underlying each Role of the RoleMixin.'"
	 * @generated
	 */
	EList<RigidSortalClass> rigidSortals();

} // RoleMixin
