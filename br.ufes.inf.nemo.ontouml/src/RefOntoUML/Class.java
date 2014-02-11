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
 * A representation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A class describes a set of objects that share the same specifications of features, constraints, and semantics.
 * A class may be designated as active (i.e., each of its instances having its own thread of control) or passive (i.e., each of its instances executing within the context of some other object). A class may also specify which signals the instances of this class handle.
 * A class has the capability to have an internal structure and ports.
 * Class has derived association that indicates how it may be extended through one or more stereotypes. Stereotype is the only kind of metaclass that cannot be extended by stereotypes.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Class#getNestedClassifier <em>Nested Classifier</em>}</li>
 *   <li>{@link RefOntoUML.Class#getSuperClass <em>Super Class</em>}</li>
 *   <li>{@link RefOntoUML.Class#isIsActive <em>Is Active</em>}</li>
 *   <li>{@link RefOntoUML.Class#getOwnedAttribute <em>Owned Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getClass_()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ClassAttributeConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL ClassAttributeConstraint1='ownedAttribute->forAll( x | x.lower >= 1 )'"
 *        annotation="Comments ClassAttributeConstraint1='The minimum cardinality of every attribute must greater or equal to 1'"
 * @generated
 */
public interface Class extends Classifier {
	/**
	 * Returns the value of the '<em><b>Nested Classifier</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.Classifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References all the Classifiers that are defined (nested) within the Class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Nested Classifier</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getClass_NestedClassifier()
	 * @model containment="true"
	 * @generated
	 */
	EList<Classifier> getNestedClassifier();

	/**
	 * Returns the value of the '<em><b>Super Class</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Class}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This gives the superclasses of a class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Super Class</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getClass_SuperClass()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='Set {}'"
	 * @generated
	 */
	EList<Class> getSuperClass();

	/**
	 * Returns the value of the '<em><b>Is Active</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Determines whether an object specified by this class is active or not. If true, then the owning class is referred to as an active class. If false, then such a class is referred to as a passive class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Active</em>' attribute.
	 * @see #setIsActive(boolean)
	 * @see RefOntoUML.RefOntoUMLPackage#getClass_IsActive()
	 * @model default="false" dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isIsActive();

	/**
	 * Sets the value of the '{@link RefOntoUML.Class#isIsActive <em>Is Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Active</em>' attribute.
	 * @see #isIsActive()
	 * @generated
	 */
	void setIsActive(boolean value);

	/**
	 * Returns the value of the '<em><b>Owned Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.Property}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Property#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Attribute</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Attribute</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getClass_OwnedAttribute()
	 * @see RefOntoUML.Property#getClass_
	 * @model opposite="class" containment="true"
	 * @generated
	 */
	EList<Property> getOwnedAttribute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A passive class may not own receptions.
	 * not self.isActive implies self.ownedReception.isEmpty()
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean passive_class(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates an operation with the specified name, parameter names, parameter types, and return type (or null) as an owned operation of this class.
	 * @param name The name for the new operation, or null.
	 * @param parameterNames The parameter names for the new operation, or null.
	 * @param parameterTypes The parameter types for the new operation, or null.
	 * @param returnType The return type for the new operation, or null.
	 * <!-- end-model-doc -->
	 * @model nameDataType="RefOntoUML.String" nameOrdered="false" parameterNamesDataType="RefOntoUML.String" parameterNamesMany="true" parameterNamesOrdered="false" parameterTypesMany="true" parameterTypesOrdered="false" returnTypeOrdered="false"
	 * @generated
	 */
	void createOwnedOperation(String name, EList<String> parameterNames, EList<Type> parameterTypes, Type returnType);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Determines whether this class is a metaclass.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isMetaclass();

} // Class
