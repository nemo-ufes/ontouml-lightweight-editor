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
 * A representation of the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A property is a structural feature of a classifier that characterizes instances of the classifier. A property related by ownedAttribute to a classifier (other than an association) represents an attribute and might also represent an association end. It relates an instance of the class to a value or set of values of the type of the attribute. A property related by memberEnd or its specializations to an association represents an end of the association. The type of the property is the type of the end of the association.
 * A property represents a set of instances that are owned by a containing classifier instance.
 * A property has the capability of being a deployment target in a deployment relationship. This enables modeling the deployment to hierarchical nodes that have properties functioning as internal parts.
 * Property represents a declared state of one or more instances in terms of a named relationship to a value or values. When a property is an attribute of a classifier, the value or values are related to the instance of the classifier by being held in slots of the instance. When a property is an association end, the value or values are related to the instance or instances at the other end(s) of the association. The range of valid values represented by the property can be controlled by setting the property's type.
 * Property specializes ParameterableElement to specify that a property can be exposed as a formal template parameter, and provided as an actual parameter in a binding of a template.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Property#getClass_ <em>Class</em>}</li>
 *   <li>{@link RefOntoUML.Property#getDatatype <em>Datatype</em>}</li>
 *   <li>{@link RefOntoUML.Property#isIsDerived <em>Is Derived</em>}</li>
 *   <li>{@link RefOntoUML.Property#isIsDerivedUnion <em>Is Derived Union</em>}</li>
 *   <li>{@link RefOntoUML.Property#getDefault <em>Default</em>}</li>
 *   <li>{@link RefOntoUML.Property#getAggregation <em>Aggregation</em>}</li>
 *   <li>{@link RefOntoUML.Property#isIsComposite <em>Is Composite</em>}</li>
 *   <li>{@link RefOntoUML.Property#getRedefinedProperty <em>Redefined Property</em>}</li>
 *   <li>{@link RefOntoUML.Property#getOwningAssociation <em>Owning Association</em>}</li>
 *   <li>{@link RefOntoUML.Property#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link RefOntoUML.Property#getOpposite <em>Opposite</em>}</li>
 *   <li>{@link RefOntoUML.Property#getSubsettedProperty <em>Subsetted Property</em>}</li>
 *   <li>{@link RefOntoUML.Property#getAssociation <em>Association</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getProperty()
 * @model
 * @generated
 */
public interface Property extends StructuralFeature {
	/**
	 * Returns the value of the '<em><b>Class</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Class#getOwnedAttribute <em>Owned Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the Class that owns the Property.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class</em>' container reference.
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_Class()
	 * @see RefOntoUML.Class#getOwnedAttribute
	 * @model opposite="ownedAttribute" changeable="false" ordered="false"
	 * @generated
	 */
	RefOntoUML.Class getClass_();

	/**
	 * Returns the value of the '<em><b>Datatype</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.DataType#getOwnedAttribute <em>Owned Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The DataType that owns this Property.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Datatype</em>' container reference.
	 * @see #setDatatype(DataType)
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_Datatype()
	 * @see RefOntoUML.DataType#getOwnedAttribute
	 * @model opposite="ownedAttribute" transient="false" ordered="false"
	 * @generated
	 */
	DataType getDatatype();

	/**
	 * Sets the value of the '{@link RefOntoUML.Property#getDatatype <em>Datatype</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Datatype</em>' container reference.
	 * @see #getDatatype()
	 * @generated
	 */
	void setDatatype(DataType value);

	/**
	 * Returns the value of the '<em><b>Is Derived</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If isDerived is true, the value of the attribute is derived from information elsewhere.
	 * Specifies whether the Property is derived, i.e., whether its value or values can be computed from other information.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Derived</em>' attribute.
	 * @see #setIsDerived(boolean)
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_IsDerived()
	 * @model default="false" dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isIsDerived();

	/**
	 * Sets the value of the '{@link RefOntoUML.Property#isIsDerived <em>Is Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Derived</em>' attribute.
	 * @see #isIsDerived()
	 * @generated
	 */
	void setIsDerived(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Derived Union</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether the property is derived as the union of all of the properties that are constrained to subset it.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Derived Union</em>' attribute.
	 * @see #setIsDerivedUnion(boolean)
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_IsDerivedUnion()
	 * @model default="false" dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isIsDerivedUnion();

	/**
	 * Sets the value of the '{@link RefOntoUML.Property#isIsDerivedUnion <em>Is Derived Union</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Derived Union</em>' attribute.
	 * @see #isIsDerivedUnion()
	 * @generated
	 */
	void setIsDerivedUnion(boolean value);

	/**
	 * Returns the value of the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies a String that represents a value to be used when no argument is supplied for the Property.
	 * A String that is evaluated to give a default value for the Property when an object of the owning Classifier is instantiated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default</em>' attribute.
	 * @see #isSetDefault()
	 * @see #unsetDefault()
	 * @see #setDefault(String)
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_Default()
	 * @model unsettable="true" dataType="RefOntoUML.String" transient="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='null'"
	 * @generated
	 */
	String getDefault();

	/**
	 * Sets the value of the '{@link RefOntoUML.Property#getDefault <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default</em>' attribute.
	 * @see #isSetDefault()
	 * @see #unsetDefault()
	 * @see #getDefault()
	 * @generated
	 */
	void setDefault(String value);

	/**
	 * Unsets the value of the '{@link RefOntoUML.Property#getDefault <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDefault()
	 * @see #getDefault()
	 * @see #setDefault(String)
	 * @generated
	 */
	void unsetDefault();

	/**
	 * Returns whether the value of the '{@link RefOntoUML.Property#getDefault <em>Default</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Default</em>' attribute is set.
	 * @see #unsetDefault()
	 * @see #getDefault()
	 * @see #setDefault(String)
	 * @generated
	 */
	boolean isSetDefault();

	/**
	 * Returns the value of the '<em><b>Aggregation</b></em>' attribute.
	 * The default value is <code>"none"</code>.
	 * The literals are from the enumeration {@link RefOntoUML.AggregationKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the kind of aggregation that applies to the Property.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Aggregation</em>' attribute.
	 * @see RefOntoUML.AggregationKind
	 * @see #setAggregation(AggregationKind)
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_Aggregation()
	 * @model default="none" required="true" ordered="false"
	 * @generated
	 */
	AggregationKind getAggregation();

	/**
	 * Sets the value of the '{@link RefOntoUML.Property#getAggregation <em>Aggregation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aggregation</em>' attribute.
	 * @see RefOntoUML.AggregationKind
	 * @see #getAggregation()
	 * @generated
	 */
	void setAggregation(AggregationKind value);

	/**
	 * Returns the value of the '<em><b>Is Composite</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If isComposite is true, the object containing the attribute is a container for the object or value contained in the attribute.
	 * This is a derived value, indicating whether the aggregation of the Property is composite or not.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Composite</em>' attribute.
	 * @see #setIsComposite(boolean)
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_IsComposite()
	 * @model default="false" dataType="RefOntoUML.Boolean" required="true" transient="true" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='self.aggregation = AggregationKind::composite'"
	 * @generated
	 */
	boolean isIsComposite();

	/**
	 * Sets the value of the '{@link RefOntoUML.Property#isIsComposite <em>Is Composite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Composite</em>' attribute.
	 * @see #isIsComposite()
	 * @generated
	 */
	void setIsComposite(boolean value);

	/**
	 * Returns the value of the '<em><b>Redefined Property</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the properties that are redefined by this property.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Redefined Property</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_RedefinedProperty()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Property> getRedefinedProperty();

	/**
	 * Returns the value of the '<em><b>Owning Association</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Association#getOwnedEnd <em>Owned End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the owning association of this property, if any.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning Association</em>' container reference.
	 * @see #setOwningAssociation(Association)
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_OwningAssociation()
	 * @see RefOntoUML.Association#getOwnedEnd
	 * @model opposite="ownedEnd" transient="false" ordered="false"
	 * @generated
	 */
	Association getOwningAssociation();

	/**
	 * Sets the value of the '{@link RefOntoUML.Property#getOwningAssociation <em>Owning Association</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Association</em>' container reference.
	 * @see #getOwningAssociation()
	 * @generated
	 */
	void setOwningAssociation(Association value);

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A ValueSpecification that is evaluated to give a default value for the Property when an object of the owning Classifier is instantiated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Value</em>' containment reference.
	 * @see #setDefaultValue(ValueSpecification)
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_DefaultValue()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	ValueSpecification getDefaultValue();

	/**
	 * Sets the value of the '{@link RefOntoUML.Property#getDefaultValue <em>Default Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' containment reference.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(ValueSpecification value);

	/**
	 * Returns the value of the '<em><b>Opposite</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * In the case where the property is one navigable end of a binary association with both ends navigable, this gives the other end.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Opposite</em>' reference.
	 * @see #setOpposite(Property)
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_Opposite()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='if self.association.memberEnd->size() = 2 then\r\n\tlet otherEnd : Property  = association.memberEnd->any( x | x <> self) in \r\n\r\n\tif self.owningAssociation->isEmpty() then\r\n\t\tif otherEnd.owningAssociation->isEmpty() then\r\n\t\t\totherEnd\r\n\t\telse\r\n\t\t\tnull\r\n\t\tendif\r\n\telse\r\n\t\totherEnd\r\n\tendif\r\nelse\r\n\tnull\r\nendif'"
	 * @generated
	 */
	Property getOpposite();

	/**
	 * Sets the value of the '{@link RefOntoUML.Property#getOpposite <em>Opposite</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Opposite</em>' reference.
	 * @see #getOpposite()
	 * @generated
	 */
	void setOpposite(Property value);

	/**
	 * Returns the value of the '<em><b>Subsetted Property</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the properties of which this property is constrained to be a subset.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Subsetted Property</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_SubsettedProperty()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Property> getSubsettedProperty();

	/**
	 * Returns the value of the '<em><b>Association</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Association#getMemberEnd <em>Member End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the association of which this property is a member, if any.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Association</em>' reference.
	 * @see #setAssociation(Association)
	 * @see RefOntoUML.RefOntoUMLPackage#getProperty_Association()
	 * @see RefOntoUML.Association#getMemberEnd
	 * @model opposite="memberEnd" ordered="false"
	 * @generated
	 */
	Association getAssociation();

	/**
	 * Sets the value of the '{@link RefOntoUML.Property#getAssociation <em>Association</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association</em>' reference.
	 * @see #getAssociation()
	 * @generated
	 */
	void setAssociation(Association value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A multiplicity of a composite aggregation must not have an upper bound greater than 1.
	 * A multiplicity on an aggregate end of a composite aggregation must not have an upper bound greater than 1.
	 * isComposite implies (upperBound()->isEmpty() or upperBound() <= 1)
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean multiplicity_of_composite(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Subsetting may only occur when the context of the subsetting property conforms to the context of the subsetted property.
	 * self.subsettedProperty->notEmpty() implies
	 *   (self.subsettingContext()->notEmpty() and self.subsettingContext()->forAll (sc |
	 *     self.subsettedProperty->forAll(sp |
	 *       sp.subsettingContext()->exists(c | sc.conformsTo(c)))))
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean subsetting_context_conforms(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A redefined property must be inherited from a more general classifier containing the redefining property.
	 * if (redefinedProperty->notEmpty()) then
	 *   (redefinitionContext->notEmpty() and
	 *       redefinedProperty->forAll(rp|
	 *         ((redefinitionContext->collect(fc|
	 *           fc.allParents()))->asSet())->collect(c| c.allFeatures())->asSet()->includes(rp))
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean redefined_property_inherited(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A subsetting property may strengthen the type of the subsetted property, and its upper bound may be less.
	 * self.subsettedProperty->forAll(sp |
	 *   self.type.conformsTo(sp.type) and
	 *     ((self.upperBound()->notEmpty() and sp.upperBound()->notEmpty()) implies
	 *       self.upperBound()<=sp.upperBound() ))
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean subsetting_rules(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Only a navigable property can be marked as readOnly.
	 * isReadOnly implies isNavigable()
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean navigable_readonly(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A derived union is derived.
	 * isDerivedUnion implies isDerived
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean derived_union_is_derived(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A derived union is read only.
	 * isDerivedUnion implies isReadOnly
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean derived_union_is_read_only(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A property may not subset a property with the same name.
	 * true
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean subsetted_property_names(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A Property can be a DeploymentTarget if it is a kind of Node and functions as a part in the internal structure of an encompassing Node.
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
	 * A binding of a property template parameter representing an attribute must be to an attribute.
	 * (isAttribute(self) and (templateParameterSubstitution->notEmpty())
	 *   implies (templateParameterSubstitution->forAll(ts | isAttribute(ts.formal)))
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean binding_to_attribute(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sets the navigability of this property as indicated.
	 * @param isNavigable The new navigability.
	 * <!-- end-model-doc -->
	 * @model isNavigableDataType="RefOntoUML.Boolean" isNavigableRequired="true" isNavigableOrdered="false"
	 * @generated
	 */
	void setIsNavigable(boolean isNavigable);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the other end of the (binary) association in which this property is a member end.
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	Property getOtherEnd();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sets the default value for this property to the specified Boolean value.
	 * @param value The new default value.
	 * <!-- end-model-doc -->
	 * @model valueDataType="RefOntoUML.Boolean" valueRequired="true" valueOrdered="false"
	 * @generated
	 */
	void setBooleanDefaultValue(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sets the default value for this property to the specified integer value.
	 * @param value The new default value.
	 * <!-- end-model-doc -->
	 * @model valueDataType="RefOntoUML.Integer" valueRequired="true" valueOrdered="false"
	 * @generated
	 */
	void setIntegerDefaultValue(int value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sets the default value for this property to the specified string value.
	 * @param value The new default value.
	 * <!-- end-model-doc -->
	 * @model valueDataType="RefOntoUML.String" valueRequired="true" valueOrdered="false"
	 * @generated
	 */
	void setStringDefaultValue(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sets the default value for this property to the specified unlimited natural value.
	 * @param value The new default value.
	 * <!-- end-model-doc -->
	 * @model valueDataType="RefOntoUML.UnlimitedNatural" valueRequired="true" valueOrdered="false"
	 * @generated
	 */
	void setUnlimitedNaturalDefaultValue(int value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sets the default value for this property to the null value.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void setNullDefaultValue();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query isAttribute() is true if the Property is defined as an attribute of some classifier.
	 * result = Classifier.allInstances->exists(c | c.attribute->includes(p))
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.Boolean" required="true" ordered="false" pRequired="true" pOrdered="false"
	 * @generated
	 */
	boolean isAttribute(Property p);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value of isComposite is true only if aggregation is composite.
	 * result = (self.aggregation = #composite)
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isComposite();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query subsettingContext() gives the context for subsetting a property. It consists, in the case of an attribute, of the corresponding classifier, and in the case of an association end, all of the classifiers at the other ends.
	 * result = if association->notEmpty()
	 * then association.endType-type
	 * else if classifier->notEmpty() then Set{classifier} else Set{} endif
	 * endif
	 * <!-- end-model-doc -->
	 * @model ordered="false"
	 * @generated
	 */
	EList<Type> subsettingContext();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query isNavigable() indicates whether it is possible to navigate across the property.
	 * result = not classifier->isEmpty() or association.owningAssociation.navigableOwnedEnd->includes(self)
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isNavigable();

} // Property
