/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement Dimension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.MeasurementDimension#getDomain <em>Domain</em>}</li>
 *   <li>{@link RefOntoUML.MeasurementDimension#getUnitOfMeasure <em>Unit Of Measure</em>}</li>
 *   <li>{@link RefOntoUML.MeasurementDimension#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link RefOntoUML.MeasurementDimension#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementDimension()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='MeasurementDimensionConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL MeasurementDimensionConstraint1='if domain->isEmpty() then\r\n\t\tStructuration.allInstances()->exists( x | allParents()->including(self)->includes(x.structuring()))\r\n\telse\r\n\t\ttrue\r\n\tendif\r\n'"
 *        annotation="Comments MeasurementDimensionConstraint1='A Measurement Dimension must be part of a Measurement Domain or connected to a Structuration'"
 * @generated
 */
public interface MeasurementDimension extends MeasurementStructure {
	/**
	 * Returns the value of the '<em><b>Domain</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.MeasurementDomain#getOwnedDimensions <em>Owned Dimensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain</em>' container reference.
	 * @see #setDomain(MeasurementDomain)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementDimension_Domain()
	 * @see RefOntoUML.MeasurementDomain#getOwnedDimensions
	 * @model opposite="ownedDimensions" transient="false" ordered="false"
	 * @generated
	 */
	MeasurementDomain getDomain();

	/**
	 * Sets the value of the '{@link RefOntoUML.MeasurementDimension#getDomain <em>Domain</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain</em>' container reference.
	 * @see #getDomain()
	 * @generated
	 */
	void setDomain(MeasurementDomain value);

	/**
	 * Returns the value of the '<em><b>Unit Of Measure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the unit of measurement of the dimension
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Unit Of Measure</em>' attribute.
	 * @see #setUnitOfMeasure(String)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementDimension_UnitOfMeasure()
	 * @model dataType="RefOntoUML.String"
	 * @generated
	 */
	String getUnitOfMeasure();

	/**
	 * Sets the value of the '{@link RefOntoUML.MeasurementDimension#getUnitOfMeasure <em>Unit Of Measure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit Of Measure</em>' attribute.
	 * @see #getUnitOfMeasure()
	 * @generated
	 */
	void setUnitOfMeasure(String value);

	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower Bound</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Bound</em>' containment reference.
	 * @see #setLowerBound(BasicMeasurementRegion)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementDimension_LowerBound()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	BasicMeasurementRegion getLowerBound();

	/**
	 * Sets the value of the '{@link RefOntoUML.MeasurementDimension#getLowerBound <em>Lower Bound</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Bound</em>' containment reference.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(BasicMeasurementRegion value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Bound</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Bound</em>' containment reference.
	 * @see #setUpperBound(BasicMeasurementRegion)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementDimension_UpperBound()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	BasicMeasurementRegion getUpperBound();

	/**
	 * Sets the value of the '{@link RefOntoUML.MeasurementDimension#getUpperBound <em>Upper Bound</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bound</em>' containment reference.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(BasicMeasurementRegion value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Informs whether this MeasurementDimension has no boundaries defined
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='(lowerBound->isEmpty() and upperBound->isEmpty())'"
	 * @generated
	 */
	boolean isNonBoundary();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Informs whether this MeasurementDimension has only one boundary defined
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='((lowerBound->notEmpty() and upperBound->isEmpty()) or (lowerBound->isEmpty() and upperBound->notEmpty()))'"
	 * @generated
	 */
	boolean isOneBoundary();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Informs whether this MeasurementDimension has only one boundary defined
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='(lowerBound->notEmpty() and upperBound->notEmpty())'"
	 * @generated
	 */
	boolean isCircular();

} // MeasurementDimension
