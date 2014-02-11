/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sortal Class</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getSortalClass()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='SortalClassConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL SortalClassConstraint1='not isAbstract and not oclIsKindOf(SubstanceSortal) implies allParents()->exists( x | x.oclIsKindOf(SubstanceSortal) )'"
 *        annotation="Comments SubstanceSortalConstraint1='Every non abstract Sortal must have a Substance Sortal ancestor (or be a Substance Sortal)'"
 * @generated
 */
public interface SortalClass extends ObjectClass {
} // SortalClass
