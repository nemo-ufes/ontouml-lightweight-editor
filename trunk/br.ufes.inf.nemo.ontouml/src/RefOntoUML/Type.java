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
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A type is a named element that is used as the type for a typed element. A type can be contained in a package.
 * A type constrains the values represented by a typed element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Type#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getType()
 * @model abstract="true"
 * @generated
 */
public interface Type extends PackageableElement {
	/**
	 * Returns the value of the '<em><b>Package</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Package#getOwnedType <em>Owned Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the owning package of this classifier, if any.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Package</em>' reference.
	 * @see #setPackage(RefOntoUML.Package)
	 * @see RefOntoUML.RefOntoUMLPackage#getType_Package()
	 * @see RefOntoUML.Package#getOwnedType
	 * @model opposite="ownedType" transient="true" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='null'"
	 * @generated
	 */
	RefOntoUML.Package getPackage();

	/**
	 * Sets the value of the '{@link RefOntoUML.Type#getPackage <em>Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' reference.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(RefOntoUML.Package value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates a(n) (binary) association between this type and the specified other type, with the specified navigabilities, aggregations, names, lower bounds, and upper bounds, and owned by this type's nearest package.
	 * @param end1IsNavigable The navigability for the first end of the new association.
	 * @param end1Aggregation The aggregation for the first end of the new association.
	 * @param end1Name The name for the first end of the new association.
	 * @param end1Lower The lower bound for the first end of the new association.
	 * @param end1Upper The upper bound for the first end of the new association.
	 * @param end1Type The type for the first end of the new association.
	 * @param end2IsNavigable The navigability for the second end of the new association.
	 * @param end2Aggregation The aggregation for the second end of the new association.
	 * @param end2Name The name for the second end of the new association.
	 * @param end2Lower The lower bound for the second end of the new association.
	 * @param end2Upper The upper bound for the second end of the new association.
	 * <!-- end-model-doc -->
	 * @model required="true" ordered="false" end1IsNavigableDataType="RefOntoUML.Boolean" end1IsNavigableRequired="true" end1IsNavigableOrdered="false" end1AggregationRequired="true" end1AggregationOrdered="false" end1NameDataType="RefOntoUML.String" end1NameRequired="true" end1NameOrdered="false" end1LowerDataType="RefOntoUML.Integer" end1LowerRequired="true" end1LowerOrdered="false" end1UpperDataType="RefOntoUML.UnlimitedNatural" end1UpperRequired="true" end1UpperOrdered="false" end1TypeRequired="true" end1TypeOrdered="false" end2IsNavigableDataType="RefOntoUML.Boolean" end2IsNavigableRequired="true" end2IsNavigableOrdered="false" end2AggregationRequired="true" end2AggregationOrdered="false" end2NameDataType="RefOntoUML.String" end2NameRequired="true" end2NameOrdered="false" end2LowerDataType="RefOntoUML.Integer" end2LowerRequired="true" end2LowerOrdered="false" end2UpperDataType="RefOntoUML.UnlimitedNatural" end2UpperRequired="true" end2UpperOrdered="false"
	 * @generated
	 */
	Association createAssociation(boolean end1IsNavigable, AggregationKind end1Aggregation, String end1Name, int end1Lower, int end1Upper, Type end1Type, boolean end2IsNavigable, AggregationKind end2Aggregation, String end2Name, int end2Lower, int end2Upper);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the associations in which this type is involved.
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<Association> getAssociations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query conformsTo() gives true for a type that conforms to another. By default, two types do not conform to each other. This query is intended to be redefined for specific conformance situations.
	 * result = false
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.Boolean" required="true" ordered="false" otherRequired="true" otherOrdered="false"
	 * @generated
	 */
	boolean conformsTo(Type other);

} // Type
