/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Anti Rigid Sortal Class</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getAntiRigidSortalClass()
 * @model abstract="true"
 * @generated
 */
public interface AntiRigidSortalClass extends SortalClass {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='if parents()->exists ( oclIsKindOf(RigidSortalClass) ) then\r\n\tparents()->any ( oclIsKindOf(RigidSortalClass) )\r\nelse\r\n\tparents()->select ( oclIsKindOf(AntiRigidSortalClass) ).oclAsType(AntiRigidSortalClass).rigidParent()->any(true)\r\nendif'"
	 *        annotation="Comments rigidParent='Returns the more specific rigid parent.'"
	 * @generated
	 */
	RigidSortalClass rigidParent();

} // AntiRigidSortalClass
