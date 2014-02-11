/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Characterization</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getCharacterization()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='CharacterizationConstraint1 CharacterizationConstraint2'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL CharacterizationConstraint1='characterizing().oclIsKindOf(IntrinsicMomentClass)' CharacterizationConstraint2='characterizedEnd().lower = 1 and characterizedEnd().upper = 1'"
 *        annotation="Comments CharacterizationConstraint1='The source must be an IntrinsicMoment' CharacterizationConstraint2='The characterized end cardinality is exactly one'"
 * @generated
 */
public interface Characterization extends DependencyRelationship {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='sourceEnd()'"
	 * @generated
	 */
	Property characterizingEnd();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='targetEnd()'"
	 * @generated
	 */
	Property characterizedEnd();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='characterizingEnd().type'"
	 * @generated
	 */
	Classifier characterizing();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='characterizedEnd().type'"
	 * @generated
	 */
	Classifier characterized();

} // Characterization
