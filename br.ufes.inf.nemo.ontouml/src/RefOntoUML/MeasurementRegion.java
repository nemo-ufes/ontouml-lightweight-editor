/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement Region</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.MeasurementRegion#getGroundedLiteral <em>Grounded Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementRegion()
 * @model abstract="true"
 * @generated
 */
public interface MeasurementRegion extends ReferenceRegion {
	/**
	 * Returns the value of the '<em><b>Grounded Literal</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.MeasurementLiteral#getGroundingRegion <em>Grounding Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grounded Literal</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grounded Literal</em>' container reference.
	 * @see #setGroundedLiteral(MeasurementLiteral)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementRegion_GroundedLiteral()
	 * @see RefOntoUML.MeasurementLiteral#getGroundingRegion
	 * @model opposite="groundingRegion" transient="false"
	 * @generated
	 */
	MeasurementLiteral getGroundedLiteral();

	/**
	 * Sets the value of the '{@link RefOntoUML.MeasurementRegion#getGroundedLiteral <em>Grounded Literal</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grounded Literal</em>' container reference.
	 * @see #getGroundedLiteral()
	 * @generated
	 */
	void setGroundedLiteral(MeasurementLiteral value);

} // MeasurementRegion
