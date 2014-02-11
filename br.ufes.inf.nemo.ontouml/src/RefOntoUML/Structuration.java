/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structuration</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getStructuration()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='StructurationConstraint1 StructurationConstraint2 StructurationConstraint3'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL StructurationConstraint1='structuring().oclIsKindOf(ReferenceStructure)' StructurationConstraint2='structuredEnd().lower = 1 and structuredEnd().upper = 1' StructurationConstraint3='structured().oclIsKindOf(Quality)'"
 *        annotation="Comments StructurationConstraint1='The source must be a ReferenceStructure' StructurationConstraint2='The structured end cardinality is exactly one' StructurationConstraint3='The target must be a Quality'"
 * @generated
 */
public interface Structuration extends DependencyRelationship {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='sourceEnd()'"
	 * @generated
	 */
	Property structuringEnd();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='targetEnd()'"
	 * @generated
	 */
	Property structuredEnd();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='structuringEnd().type'"
	 * @generated
	 */
	Classifier structuring();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='structuredEnd().type'"
	 * @generated
	 */
	Classifier structured();

} // Structuration
