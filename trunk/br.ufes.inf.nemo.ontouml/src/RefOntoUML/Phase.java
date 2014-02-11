/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Phase</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getPhase()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='PhaseConstraint2'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL PhaseConstraint2='let\r\n\tgsets : Bag(GeneralizationSet) = generalization.generalizationSet\r\nin\r\n\tif gsets->size() = 1 then\r\n\t\tlet\r\n\t\t\tgs : GeneralizationSet = gsets->any(true)\r\n\t\tin\r\n\t\t\tgs.generalization.specific->forAll( x | x.oclIsKindOf(Phase) ) and gs.generalization->size() > 1 and gs.isDisjoint and gs.isCovering\r\n\telse\r\n\t\tfalse\r\n\tendif'"
 *        annotation="Comments PhaseConstraint2='A Phase of a Substance Sortal must be grouped in exactly one {disjoint, complete} Generalization Set with other Phases'"
 * @generated
 */
public interface Phase extends AntiRigidSortalClass {
} // Phase
