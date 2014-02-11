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
 * A representation of the model object '<em><b>Directed Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A directed relationship represents a relationship between a collection of source model elements and a collection of target model elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.DirectedRelationship#getSource <em>Source</em>}</li>
 *   <li>{@link RefOntoUML.DirectedRelationship#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getDirectedRelationship()
 * @model abstract="true"
 * @generated
 */
public interface DirectedRelationship extends Relationship {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Element}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the sources of the DirectedRelationship.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getDirectedRelationship_Source()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='if self.oclIsKindOf(Generalization) then\r\n\tSet{self.oclAsType(Generalization).specific}\r\nelse\r\n\tnull\r\nendif'"
	 * @generated
	 */
	EList<Element> getSource();

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Element}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the targets of the DirectedRelationship.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getDirectedRelationship_Target()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='if self.oclIsKindOf(Generalization) then\r\n\tSet{self.oclAsType(Generalization).general}\r\nelse\r\n\tnull\r\nendif'"
	 * @generated
	 */
	EList<Element> getTarget();

} // DirectedRelationship
