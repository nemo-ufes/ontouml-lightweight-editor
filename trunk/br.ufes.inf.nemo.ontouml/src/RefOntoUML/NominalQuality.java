/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Nominal Quality</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getNominalQuality()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='NominalQualityConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL NominalQualityConstraint1='let\r\n\tstructs : Set(Structuration) = Structuration.allInstances()->select( x | allParents()->including(self)->includes(x.structured()))\r\nin\r\n\tif structs->size() > 1 then\r\n\t\tstructs->forAll( x | x.structuring().oclIsKindOf(NominalStructure))\r\n\telse\r\n\t\ttrue\r\n\tendif\r\n'"
 *        annotation="Comments NominalQualityConstraint1='All Reference Structures of a Nominal Quality should be Nominal Reference Structures.'"
 * @generated
 */
public interface NominalQuality extends Quality {
} // NominalQuality
