/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement Structure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.MeasurementStructure#getGroundedEnumeration <em>Grounded Enumeration</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementStructure()
 * @model abstract="true"
 * @generated
 */
public interface MeasurementStructure extends ReferenceStructure {
	/**
	 * Returns the value of the '<em><b>Grounded Enumeration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.MeasurementEnumeration#getGroundingStructure <em>Grounding Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grounded Enumeration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grounded Enumeration</em>' container reference.
	 * @see #setGroundedEnumeration(MeasurementEnumeration)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementStructure_GroundedEnumeration()
	 * @see RefOntoUML.MeasurementEnumeration#getGroundingStructure
	 * @model opposite="groundingStructure" transient="false"
	 * @generated
	 */
	MeasurementEnumeration getGroundedEnumeration();

	/**
	 * Sets the value of the '{@link RefOntoUML.MeasurementStructure#getGroundedEnumeration <em>Grounded Enumeration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grounded Enumeration</em>' container reference.
	 * @see #getGroundedEnumeration()
	 * @generated
	 */
	void setGroundedEnumeration(MeasurementEnumeration value);

} // MeasurementStructure
