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
 * A representation of the model object '<em><b>Reference Structure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.ReferenceStructure#getOwnedRegions <em>Owned Regions</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getReferenceStructure()
 * @model abstract="true"
 * @generated
 */
public interface ReferenceStructure extends DataType {
	/**
	 * Returns the value of the '<em><b>Owned Regions</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.ReferenceRegion}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.ReferenceRegion#getStructure <em>Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Regions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Regions</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getReferenceStructure_OwnedRegions()
	 * @see RefOntoUML.ReferenceRegion#getStructure
	 * @model opposite="structure" containment="true" ordered="false"
	 * @generated
	 */
	EList<ReferenceRegion> getOwnedRegions();

} // ReferenceStructure
