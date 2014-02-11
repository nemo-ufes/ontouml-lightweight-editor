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
 * A representation of the model object '<em><b>Measurement Domain</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.MeasurementDomain#getOwnedDimensions <em>Owned Dimensions</em>}</li>
 *   <li>{@link RefOntoUML.MeasurementDomain#getCompositionRule <em>Composition Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementDomain()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='MeasurementDomainConstraint1 MeasurementDomainConstraint2'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL MeasurementDomainConstraint1='Structuration.allInstances()->exists( x | allParents()->including(self)->includes(x.structuring()) )' MeasurementDomainConstraint2='ownedDimensions->size() > 1'"
 *        annotation="Comments MeasurementDomainConstraint1='A Measurement Domain must be connected to a Structuration' MeasurementDomainConstraint2='A Measurement Domain is composed by at least two dimensions'"
 * @generated
 */
public interface MeasurementDomain extends MeasurementStructure {
	/**
	 * Returns the value of the '<em><b>Owned Dimensions</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.MeasurementDimension}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.MeasurementDimension#getDomain <em>Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Dimensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Dimensions</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementDomain_OwnedDimensions()
	 * @see RefOntoUML.MeasurementDimension#getDomain
	 * @model opposite="domain" containment="true"
	 * @generated
	 */
	EList<MeasurementDimension> getOwnedDimensions();

	/**
	 * Returns the value of the '<em><b>Composition Rule</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composition Rule</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composition Rule</em>' containment reference.
	 * @see #setCompositionRule(Expression)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeasurementDomain_CompositionRule()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	Expression getCompositionRule();

	/**
	 * Sets the value of the '{@link RefOntoUML.MeasurementDomain#getCompositionRule <em>Composition Rule</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Composition Rule</em>' containment reference.
	 * @see #getCompositionRule()
	 * @generated
	 */
	void setCompositionRule(Expression value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query isScientific() informs about the existence of a compositionRule caracterizing a scientific measurement domain or a cognitive measurement domain otherwise
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='compositionRule->isEmpty()'"
	 * @generated
	 */
	boolean isScientific();

} // MeasurementDomain
