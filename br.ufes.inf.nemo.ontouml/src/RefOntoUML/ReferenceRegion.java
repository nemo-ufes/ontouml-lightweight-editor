/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Region</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A reference region denotes a region within a reference structure. This region is implemented as a value within a value specification.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.ReferenceRegion#getStructure <em>Structure</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getReferenceRegion()
 * @model abstract="true"
 * @generated
 */
public interface ReferenceRegion extends LiteralSpecification {
	/**
	 * Returns the value of the '<em><b>Structure</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.ReferenceStructure#getOwnedRegions <em>Owned Regions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Structure</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Structure</em>' container reference.
	 * @see #setStructure(ReferenceStructure)
	 * @see RefOntoUML.RefOntoUMLPackage#getReferenceRegion_Structure()
	 * @see RefOntoUML.ReferenceStructure#getOwnedRegions
	 * @model opposite="ownedRegions" transient="false" ordered="false"
	 * @generated
	 */
	ReferenceStructure getStructure();

	/**
	 * Sets the value of the '{@link RefOntoUML.ReferenceRegion#getStructure <em>Structure</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Structure</em>' container reference.
	 * @see #getStructure()
	 * @generated
	 */
	void setStructure(ReferenceStructure value);

} // ReferenceRegion
