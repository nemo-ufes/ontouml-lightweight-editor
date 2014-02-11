/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intrinsic Moment Class</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getIntrinsicMomentClass()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='IntrinsicMomentConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL IntrinsicMomentConstraint1='Characterization.allInstances()->exists( x | allParents()->including(self)->includes(x.characterizing()) )'"
 *        annotation="Comments IntrinsicMomentConstraint1='An IntrinsicMoment must be connected (directly or indirectly) to a Characterization'"
 * @generated
 */
public interface IntrinsicMomentClass extends MomentClass {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='Characterization.allInstances()->select( x | x.characterizing() = self )->any(true)'"
	 * @generated
	 */
	Characterization characterization();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='characterization().characterized()'"
	 * @generated
	 */
	Classifier characterized();

} // IntrinsicMomentClass
