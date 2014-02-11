/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mediation</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getMediation()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='MediationConstraint1 MediationConstraint2'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL MediationConstraint1='relator().oclIsTypeOf(Relator)' MediationConstraint2='mediatedEnd().lower >= 1'"
 *        annotation="Comments MediationConstraint1='The source must be a Relator' MediationConstraint2='The mediated end minimum cardinality must be greater or equal to 1'"
 * @generated
 */
public interface Mediation extends DependencyRelationship {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='sourceEnd()'"
	 * @generated
	 */
	Property relatorEnd();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='targetEnd()'"
	 * @generated
	 */
	Property mediatedEnd();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='relatorEnd().type'"
	 * @generated
	 */
	Classifier relator();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='mediatedEnd().type'"
	 * @generated
	 */
	Classifier mediated();

} // Mediation
