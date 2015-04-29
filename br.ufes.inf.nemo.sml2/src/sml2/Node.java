/**
 */
package sml2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see sml2.Sml2Package#getNode()
 * @model abstract="true"
 * @generated
 */
public interface Node extends SituationTypeElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='ComparativeRelation.allInstances()->select(r | r.source = self)'"
	 *        annotation="Comments associations='Gets all comparative relations in which the element is source'"
	 * @generated
	 */
	EList<ComparativeRelation> sourceRelation();

} // Node
