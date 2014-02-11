/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Derivation</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getDerivation()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='DerivationConstraint1a DerivationConstraint1b DerivationConstraint2'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL DerivationConstraint1a='material().oclIsTypeOf(MaterialAssociation)' DerivationConstraint1b='relator().oclIsTypeOf(Relator)' DerivationConstraint2='relatorEnd().lower = 1 and relatorEnd().upper = 1'"
 *        annotation="Comments DerivationConstraint1a='The source must be a Material Association' DerivationConstraint1b='The target must be a Relator' DerivationConstraint2='The relator end cardinality is exactly one'"
 * @generated
 */
public interface Derivation extends DependencyRelationship {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='sourceEnd()'"
	 * @generated
	 */
	Property materialEnd();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='targetEnd()'"
	 * @generated
	 */
	Property relatorEnd();

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
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='materialEnd().type'"
	 * @generated
	 */
	Classifier material();

} // Derivation
