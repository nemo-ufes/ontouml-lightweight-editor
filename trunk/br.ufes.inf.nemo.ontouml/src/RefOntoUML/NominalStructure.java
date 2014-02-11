/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Nominal Structure</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getNominalStructure()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='NominalStructureConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL NominalStructureConstraint1='Structuration.allInstances()->exists( x | allParents()->including(self)->includes(x.structuring()) )'"
 *        annotation="Comments NominalStructureConstraint1='A Nominal Structure must be connected to a Structuration'"
 * @generated
 */
public interface NominalStructure extends ReferenceStructure {
} // NominalStructure
