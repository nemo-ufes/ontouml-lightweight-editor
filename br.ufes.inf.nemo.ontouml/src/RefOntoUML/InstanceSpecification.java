/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instance Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An instance specification is a model element that represents an instance in a modeled system.
 * An instance specification has the capability of being a deployment target in a deployment relationship, in the case that it is an instance of a node. It is also has the capability of being a deployed artifact, if it is an instance of an artifact.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.InstanceSpecification#getClassifier <em>Classifier</em>}</li>
 *   <li>{@link RefOntoUML.InstanceSpecification#getSlot <em>Slot</em>}</li>
 *   <li>{@link RefOntoUML.InstanceSpecification#getSpecification <em>Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getInstanceSpecification()
 * @model
 * @generated
 */
public interface InstanceSpecification extends PackageableElement {
	/**
	 * Returns the value of the '<em><b>Classifier</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Classifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The classifier or classifiers of the represented instance. If multiple classifiers are specified, the instance is classified by all of them.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Classifier</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getInstanceSpecification_Classifier()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Classifier> getClassifier();

	/**
	 * Returns the value of the '<em><b>Slot</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.Slot}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Slot#getOwningInstance <em>Owning Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A slot giving the value or values of a structural feature of the instance. An instance specification can have one slot per structural feature of its classifiers, including inherited features. It is not necessary to model a slot for each structural feature, in which case the instance specification is a partial description.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Slot</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getInstanceSpecification_Slot()
	 * @see RefOntoUML.Slot#getOwningInstance
	 * @model opposite="owningInstance" containment="true" ordered="false"
	 * @generated
	 */
	EList<Slot> getSlot();

	/**
	 * Returns the value of the '<em><b>Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A specification of how to compute, derive, or construct the instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Specification</em>' containment reference.
	 * @see #setSpecification(ValueSpecification)
	 * @see RefOntoUML.RefOntoUMLPackage#getInstanceSpecification_Specification()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	ValueSpecification getSpecification();

	/**
	 * Sets the value of the '{@link RefOntoUML.InstanceSpecification#getSpecification <em>Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specification</em>' containment reference.
	 * @see #getSpecification()
	 * @generated
	 */
	void setSpecification(ValueSpecification value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The defining feature of each slot is a structural feature (directly or inherited) of a classifier of the instance specification.
	 * slot->forAll(s | classifier->exists (c | c.allFeatures()->includes (s.definingFeature)))
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean defining_feature(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * One structural feature (including the same feature inherited from multiple classifiers) is the defining feature of at most one slot in an instance specification.
	 * classifier->forAll(c | (c.allFeatures()->forAll(f | slot->select(s | s.definingFeature = f)->size() <= 1)))
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean structural_feature(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An InstanceSpecification can be a DeploymentTarget if it is the instance specification of a Node and functions as a part in the internal structure of an encompassing Node.
	 * true
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean deployment_target(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An InstanceSpecification can be a DeployedArtifact if it is the instance specification of an Artifact.
	 * true
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean deployment_artifact(DiagnosticChain diagnostics, Map<Object, Object> context);

} // InstanceSpecification
