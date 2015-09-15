/**
 */
package sml2;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

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
public interface Node extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='SituationTypeAssociation.allInstances()->select(r | r.source = self)'"
	 *        annotation="Comments sourceRelation='Gets all associations in which the node element is source'"
	 * @generated
	 */
	EList<SituationTypeAssociation> sourceRelation();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='SituationTypeAssociation.allInstances()->select(r | r.target = self)'"
	 *        annotation="Comments targetRelation='Gets all associations in which the node element is target'"
	 * @generated
	 */
	EList<SituationTypeAssociation> targetRelation();

} // Node
