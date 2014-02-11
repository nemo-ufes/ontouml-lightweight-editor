/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>member Of</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getmemberOf()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='memberOfConstraint1a memberOfConstraint1b memberOfConstraint2'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL memberOfConstraint1a='whole().hasCollectiveInstances()' memberOfConstraint1b='part().hasCollectiveInstances() or part().hasFunctionalComplexInstances()' memberOfConstraint2='isEssential implies \r\n\r\nif whole().oclIsKindOf (MixinClass) then\r\n\tObjectClass.allInstances()->select( x | x.allParents()->includes(self) )->forAll( y | y.oclIsKindOf(Collective) implies y.oclAsType(Collective).isExtensional )\r\nelse\r\n\twhole().allParents()->including(whole())->forAll( x | x.oclIsKindOf(Collective) implies x.oclAsType(Collective).isExtensional )\r\nendif'"
 *        annotation="Comments memberOfConstraint1a='memberOf relates individuals that are functional complexes or collectives as parts of individuals that are collectives (whole)' memberOfConstraint1b='memberOf relates individuals that are functional complexes or collectives as parts of individuals that are collectives (part)' memberOfConstraint2='memberOf with essential parthood implies an extensional whole'"
 * @generated
 */
public interface memberOf extends Meronymic {
} // memberOf
