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
 * A representation of the model object '<em><b>Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A data type is a type whose instances are identified only by their value. A data type may contain attributes to support the modeling of structured data types.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.DataType#getOwnedAttribute <em>Owned Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getDataType()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='DataTypeAttributeConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL DataTypeAttributeConstraint1='ownedAttribute->forAll( x | x.lower >= 1 )'"
 *        annotation="Comments DataTypeAttributeConstraint1='The minimum cardinality of every attribute must greater or equal to 1'"
 * @generated
 */
public interface DataType extends Classifier {
	/**
	 * Returns the value of the '<em><b>Owned Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.Property}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Property#getDatatype <em>Datatype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Attributes owned by the DataType.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Attribute</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getDataType_OwnedAttribute()
	 * @see RefOntoUML.Property#getDatatype
	 * @model opposite="datatype" containment="true"
	 * @generated
	 */
	EList<Property> getOwnedAttribute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates an operation with the specified name, parameter names, parameter types, and return type (or null) as an owned operation of this data type.
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
	 * Creates a property with the specified name, type, lower bound, and upper bound as an owned attribute of this data type.
	 * @param name The name for the new attribute, or null.
	 * @param type The type for the new attribute, or null.
	 * @param lower The lower bound for the new attribute.
	 * @param upper The upper bound for the new attribute.
	 * <!-- end-model-doc -->
	 * @model required="true" ordered="false" nameDataType="RefOntoUML.String" nameOrdered="false" typeOrdered="false" lowerDataType="RefOntoUML.Integer" lowerRequired="true" lowerOrdered="false" upperDataType="RefOntoUML.UnlimitedNatural" upperRequired="true" upperOrdered="false"
	 * @generated
	 */
	Property createOwnedAttribute(String name, Type type, int lower, int upper);

} // DataType
