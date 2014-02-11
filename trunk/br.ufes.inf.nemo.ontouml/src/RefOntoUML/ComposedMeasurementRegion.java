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
 * A representation of the model object '<em><b>Composed Measurement Region</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.ComposedMeasurementRegion#getOwnedRegions <em>Owned Regions</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getComposedMeasurementRegion()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ComposedMeasurementRegionConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL ComposedMeasurementRegionConstraint1='ownedRegions->size() > 1'"
 *        annotation="Comments ComposedMeasurementRegionConstraint1='A Composed Measurement Region must have more than one measurement region'"
 * @generated
 */
public interface ComposedMeasurementRegion extends MeasurementRegion {
	/**
	 * Returns the value of the '<em><b>Owned Regions</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.BasicMeasurementRegion}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Regions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Regions</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getComposedMeasurementRegion_OwnedRegions()
	 * @model containment="true" lower="2"
	 * @generated
	 */
	EList<BasicMeasurementRegion> getOwnedRegions();

} // ComposedMeasurementRegion
