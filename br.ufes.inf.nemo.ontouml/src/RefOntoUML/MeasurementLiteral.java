package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.MeasurementLiteral#getGroundingRegion <em>Grounding Region</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementLiteral()
 * @model annotation="Ecore constraints='MeasurementLiteralConstraint1'"
 *        annotation="OCL MeasurementLiteralConstraint1='if groundingRegion->isEmpty() then\r\n\tfalse\r\nelse\r\n\ttrue\r\nendif'"
 *        annotation="Comments MeasurementLiteralConstraint1='A Measurement Literal needs to be grounded by a Measurement Region'"
 * @generated
 */
public interface MeasurementLiteral extends EnumerationLiteral {
	/**
	 * Returns the value of the '<em><b>Grounding Region</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.MeasurementRegion#getGroundedLiteral <em>Grounded Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grounding Region</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grounding Region</em>' containment reference.
	 * @see #setGroundingRegion(MeasurementRegion)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementLiteral_GroundingRegion()
	 * @see RefOntoUML.MeasurementRegion#getGroundedLiteral
	 * @model opposite="groundedLiteral" containment="true"
	 * @generated
	 */
	MeasurementRegion getGroundingRegion();

	/**
	 * Sets the value of the '{@link RefOntoUML.MeasurementLiteral#getGroundingRegion <em>Grounding Region</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grounding Region</em>' containment reference.
	 * @see #getGroundingRegion()
	 * @generated
	 */
	void setGroundingRegion(MeasurementRegion value);

} // MeasurementLiteral