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

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An element is a constituent of a model. As such, it has the capability of owning other elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Element#getOwnedElement <em>Owned Element</em>}</li>
 *   <li>{@link RefOntoUML.Element#getOwner <em>Owner</em>}</li>
 *   <li>{@link RefOntoUML.Element#getOwnedComment <em>Owned Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getElement()
 * @model abstract="true"
 * @generated
 */
public interface Element extends EModelElement {
	/**
	 * Returns the value of the '<em><b>Owned Element</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Element}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Element#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Elements owned by this element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Element</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getElement_OwnedElement()
	 * @see RefOntoUML.Element#getOwner
	 * @model opposite="owner" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='Set {}'"
	 * @generated
	 */
	EList<Element> getOwnedElement();

	/**
	 * Returns the value of the '<em><b>Owner</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Element#getOwnedElement <em>Owned Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Element that owns this element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owner</em>' reference.
	 * @see RefOntoUML.RefOntoUMLPackage#getElement_Owner()
	 * @see RefOntoUML.Element#getOwnedElement
	 * @model opposite="ownedElement" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='null'"
	 * @generated
	 */
	Element getOwner();

	/**
	 * Returns the value of the '<em><b>Owned Comment</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.Comment}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Comments owned by this element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Comment</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getElement_OwnedComment()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Comment> getOwnedComment();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An element may not directly or indirectly own itself.
	 * not self.allOwnedElements()->includes(self)
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean not_own_self(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Elements that must be owned must have an owner.
	 * self.mustBeOwned() implies owner->notEmpty()
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean has_owner(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Destroys this element by removing all cross references to/from it and removing it from its containing resource or object.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void destroy();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Determines whether this element has the specified keyword.
	 * @param keyword The keyword in question.
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.Boolean" required="true" ordered="false" keywordDataType="RefOntoUML.String" keywordRequired="true" keywordOrdered="false"
	 * @generated
	 */
	boolean hasKeyword(String keyword);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the keywords for this element.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="RefOntoUML.String" ordered="false"
	 * @generated
	 */
	EList<String> getKeywords();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Adds the specified keyword to this element.
	 * @param keyword The keyword to add.
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.Boolean" required="true" ordered="false" keywordDataType="RefOntoUML.String" keywordRequired="true" keywordOrdered="false"
	 * @generated
	 */
	boolean addKeyword(String keyword);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Removes the specified keyword from this element.
	 * @param keyword The keyword to remove.
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.Boolean" required="true" ordered="false" keywordDataType="RefOntoUML.String" keywordRequired="true" keywordOrdered="false"
	 * @generated
	 */
	boolean removeKeyword(String keyword);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the nearest package that owns (either directly or indirectly) this element, or the element itself (if it is a package).
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	RefOntoUML.Package getNearestPackage();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the model that owns (either directly or indirectly) this element.
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	Model getModel();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the stereotype with the specified qualified name that is applicable to this element, or null if no such stereotype is applicable.
	 * @param qualifiedName The qualified name of the applicable stereotype to retrieve.
	 * <!-- end-model-doc -->
	 * @model qualifiedNameDataType="RefOntoUML.String" qualifiedNameRequired="true" qualifiedNameOrdered="false"
	 * @generated
	 */
	void getApplicableStereotype(String qualifiedName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the stereotype applications for this element.
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<EObject> getStereotypeApplications();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the stereotype with the specified qualified name that is required for this element, or null if no such stereotype is required.
	 * @param qualifiedName The qualified name of the required stereotype to retrieve.
	 * <!-- end-model-doc -->
	 * @model qualifiedNameDataType="RefOntoUML.String" qualifiedNameRequired="true" qualifiedNameOrdered="false"
	 * @generated
	 */
	void getRequiredStereotype(String qualifiedName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the stereotype with the specified qualified name that is applied to this element, or null if no such stereotype is  applied.
	 * @param qualifiedName The qualified name of the applied stereotype to retrieve.
	 * <!-- end-model-doc -->
	 * @model qualifiedNameDataType="RefOntoUML.String" qualifiedNameRequired="true" qualifiedNameOrdered="false"
	 * @generated
	 */
	void getAppliedStereotype(String qualifiedName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates an annotation with the specified source and this element as its model element.
	 * @param source The source for the new annotation.
	 * <!-- end-model-doc -->
	 * @model required="true" ordered="false" sourceDataType="RefOntoUML.String" sourceRequired="true" sourceOrdered="false"
	 * @generated
	 */
	EAnnotation createEAnnotation(String source);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the relationships in which this element is involved.
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<Relationship> getRelationships();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the relationships of the specified type in which this element is involved.
	 * @param eClass The (meta)type of the relationships to retrieve.
	 * <!-- end-model-doc -->
	 * @model ordered="false" eClassRequired="true" eClassOrdered="false"
	 * @generated
	 */
	EList<Relationship> getRelationships(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the directed relationships for which this element is a source.
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<DirectedRelationship> getSourceDirectedRelationships();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the directed relationships of the specified type for which this element is a source.
	 * @param eClass The (meta)type of the directed relationships to retrieve.
	 * <!-- end-model-doc -->
	 * @model ordered="false" eClassRequired="true" eClassOrdered="false"
	 * @generated
	 */
	EList<DirectedRelationship> getSourceDirectedRelationships(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the directed relationships for which this element is a target.
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<DirectedRelationship> getTargetDirectedRelationships();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the directed relationships of the specified type for which this element is a target.
	 * @param eClass The (meta)type of the directed relationships to retrieve.
	 * <!-- end-model-doc -->
	 * @model ordered="false" eClassRequired="true" eClassOrdered="false"
	 * @generated
	 */
	EList<DirectedRelationship> getTargetDirectedRelationships(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query allOwnedElements() gives all of the direct and indirect owned elements of an element.
	 * result = ownedElement->union(ownedElement->collect(e | e.allOwnedElements()))
	 * <!-- end-model-doc -->
	 * @model ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='ownedElement->union( ownedElement->collect(e | e.allOwnedElements()) )'"
	 * @generated
	 */
	EList<Element> allOwnedElements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query mustBeOwned() indicates whether elements of this type must have an owner. Subclasses of Element that do not require an owner must override this operation.
	 * result = true
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean mustBeOwned();

} // Element
