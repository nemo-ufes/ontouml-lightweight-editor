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
 * A representation of the model object '<em><b>Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Relationship is an abstract concept that specifies some kind of relationship between elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Relationship#getRelatedElement <em>Related Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getRelationship()
 * @model abstract="true"
 * @generated
 */
public interface Relationship extends Element {
	/**
	 * Returns the value of the '<em><b>Related Element</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Element}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the elements related by the Relationship.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Related Element</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getRelationship_RelatedElement()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='if self.oclIsKindOf(DirectedRelationship) then\r\n\tself.oclAsType(DirectedRelationship).source->union(self.oclAsType(DirectedRelationship).target)\r\nelse\r\n\tif self.oclIsKindOf(Association) then\r\n\t\tself.oclAsType(Association).endType\r\n\telse\r\n\t\tnull\r\n\tendif\r\nendif'"
	 * @generated
	 */
	EList<Element> getRelatedElement();

} // Relationship
