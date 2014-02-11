/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurable Quality</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getMeasurableQuality()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='MeasurableQualityConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL MeasurableQualityConstraint1='let\r\n\tstructs : Set(Structuration) = Structuration.allInstances()->select( x | allParents()->including(self)->includes(x.structured()))\r\nin\r\n\tif structs->size() > 1 then\r\n\t\tstructs->forAll( x | x.structuring().oclIsKindOf(MeasurementStructure))\r\n\telse\r\n\t\ttrue\r\n\tendif\r\n'"
 *        annotation="Comments MeasurableQualityConstraint1='All Reference Structures of a Measurable Quality should be Measurement Reference Structures.'"
 * @generated
 */
public interface MeasurableQuality extends Quality {
} // MeasurableQuality
