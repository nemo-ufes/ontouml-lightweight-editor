/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relator</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getRelator()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='RelatorConstraint1 RelatorConstraint2'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL RelatorConstraint1='Mediation.allInstances()->exists( x | allParents()->including(self)->includes(x.relator()) )' RelatorConstraint2='Mediation.allInstances()->select( x | allParents()->including(self)->includes(x.relator()) )->collect ( y | y.mediatedEnd().lower )->sum() >= 2'"
 *        annotation="Comments RelatorConstraint1='A Relator must be connected (directly or indirectly) to a Mediation' RelatorConstraint2='The sum of the minimum cardinalities of the mediated ends must be greater or equal to 2'"
 * @generated
 */
public interface Relator extends MomentClass {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='Mediation.allInstances()->select( x | x.relator() = self )'"
	 * @generated
	 */
	EList<Mediation> mediations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='mediations().mediated()'"
	 * @generated
	 */
	EList<Classifier> mediated();

} // Relator
