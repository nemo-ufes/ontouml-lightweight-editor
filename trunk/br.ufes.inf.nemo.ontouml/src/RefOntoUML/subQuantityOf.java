/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>sub Quantity Of</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getsubQuantityOf()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='subQuantityOfConstraint1a subQuantityOfConstraint1b subQuantityOfConstraint2 subQuantityOfConstraint3 subQuantityOfConstraint4'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL subQuantityOfConstraint1a='whole().hasQuantityInstances()' subQuantityOfConstraint1b='part().hasQuantityInstances()' subQuantityOfConstraint2='not isShareable' subQuantityOfConstraint3='isEssential' subQuantityOfConstraint4='partEnd().upper = 1'"
 *        annotation="Comments subQuantityOfConstraint1a='subQuantityOf relates individuals that are quantities (whole)' subQuantityOfConstraint1b='subQuantityOf relates individuals that are quantities (part)' subQuantityOfConstraint2='A part is always non-shareable' subQuantityOfConstraint3='A part is always essential' subQuantityOfConstraint4='The maximum cardinality of the part end is equal to 1'"
 * @generated
 */
public interface subQuantityOf extends Meronymic {
} // subQuantityOf
