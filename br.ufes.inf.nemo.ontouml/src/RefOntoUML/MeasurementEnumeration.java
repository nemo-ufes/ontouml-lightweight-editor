package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement Enumeration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.MeasurementEnumeration#getGroundingStructure <em>Grounding Structure</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementEnumeration()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='MeasurementEnumerationConstraint1 MeasurementEnumerationConstraint2'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL MeasurementEnumerationConstraint1='ownedLiteral->size() > 1' MeasurementEnumerationConstraint2='if groundingStructure->isEmpty() then\r\n\tfalse\r\nelse\r\n\ttrue\r\nendif'"
 *        annotation="Comments MeasurementEnumerationConstraint1='A Measurement Enumeration should have two or more ordinal literals' MeasurementEnumerationConstraint2='A Measurement Enumeration needs to be grounded by a Measurement Structure'"
 * @generated
 */
public interface MeasurementEnumeration extends Enumeration {
	/**
	 * Returns the value of the '<em><b>Grounding Structure</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.MeasurementStructure#getGroundedEnumeration <em>Grounded Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grounding Structure</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grounding Structure</em>' containment reference.
	 * @see #setGroundingStructure(MeasurementStructure)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementEnumeration_GroundingStructure()
	 * @see RefOntoUML.MeasurementStructure#getGroundedEnumeration
	 * @model opposite="groundedEnumeration" containment="true"
	 * @generated
	 */
	MeasurementStructure getGroundingStructure();

	/**
	 * Sets the value of the '{@link RefOntoUML.MeasurementEnumeration#getGroundingStructure <em>Grounding Structure</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grounding Structure</em>' containment reference.
	 * @see #getGroundingStructure()
	 * @generated
	 */
	void setGroundingStructure(MeasurementStructure value);

} // MeasurementEnumeration