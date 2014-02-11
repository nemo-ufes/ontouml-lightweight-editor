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
 * A representation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A classifier is a classification of instances - it describes a set of instances that have features in common. A classifier can specify a generalization hierarchy by referencing its general classifiers.
 * A classifier has the capability to own collaboration uses. These collaboration uses link a collaboration with the classifier to give a description of the workings of the classifier.
 * A classifier has the capability to own use cases. Although the owning classifier typically represents the subject to which the owned use cases apply, this is not necessarily the case. In principle, the same use case can be applied to multiple subjects, as identified by the subject association role of a use case.
 * Classifier is defined to be a kind of templateable element so that a classifier can be parameterized. It is also defined to be a kind of parameterable element so that a classifier can be a formal template parameter.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Classifier#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link RefOntoUML.Classifier#getGeneralization <em>Generalization</em>}</li>
 *   <li>{@link RefOntoUML.Classifier#getPowertypeExtent <em>Powertype Extent</em>}</li>
 *   <li>{@link RefOntoUML.Classifier#getFeature <em>Feature</em>}</li>
 *   <li>{@link RefOntoUML.Classifier#getInheritedMember <em>Inherited Member</em>}</li>
 *   <li>{@link RefOntoUML.Classifier#getRedefinedClassifier <em>Redefined Classifier</em>}</li>
 *   <li>{@link RefOntoUML.Classifier#getGeneral <em>General</em>}</li>
 *   <li>{@link RefOntoUML.Classifier#getAttribute <em>Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getClassifier()
 * @model abstract="true"
 * @generated
 */
public interface Classifier extends Namespace, RedefinableElement, Type {
	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, the Classifier does not provide a complete declaration and can typically not be instantiated. An abstract classifier is intended to be used by other classifiers e.g. as the target of general metarelationships or generalization relationships.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(boolean)
	 * @see RefOntoUML.RefOntoUMLPackage#getClassifier_IsAbstract()
	 * @model default="false" dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isIsAbstract();

	/**
	 * Sets the value of the '{@link RefOntoUML.Classifier#isIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #isIsAbstract()
	 * @generated
	 */
	void setIsAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Generalization</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.Generalization}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Generalization#getSpecific <em>Specific</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the Generalization relationships for this Classifier. These Generalizations navigaten to more general classifiers in the generalization hierarchy.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Generalization</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getClassifier_Generalization()
	 * @see RefOntoUML.Generalization#getSpecific
	 * @model opposite="specific" containment="true" ordered="false"
	 * @generated
	 */
	EList<Generalization> getGeneralization();

	/**
	 * Returns the value of the '<em><b>Powertype Extent</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.GeneralizationSet}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.GeneralizationSet#getPowertype <em>Powertype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Designates the GeneralizationSet of which the associated Classifier is a power type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Powertype Extent</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getClassifier_PowertypeExtent()
	 * @see RefOntoUML.GeneralizationSet#getPowertype
	 * @model opposite="powertype" ordered="false"
	 * @generated
	 */
	EList<GeneralizationSet> getPowertypeExtent();

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Feature}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Feature#getFeaturingClassifier <em>Featuring Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Note that there may be members of the Classifier that are of the type Feature but are not included in this association, e.g. inherited features.
	 * Specifies each feature defined in the classifier.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Feature</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getClassifier_Feature()
	 * @see RefOntoUML.Feature#getFeaturingClassifier
	 * @model opposite="featuringClassifier" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='if oclIsKindOf(Association) then\r\n\toclAsType(Association).ownedEnd\r\nelse\r\n\tattribute\r\nendif'"
	 * @generated
	 */
	EList<Feature> getFeature();

	/**
	 * Returns the value of the '<em><b>Inherited Member</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.NamedElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies all elements inherited by this classifier from the general classifiers.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inherited Member</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getClassifier_InheritedMember()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='Set {}'"
	 * @generated
	 */
	EList<NamedElement> getInheritedMember();

	/**
	 * Returns the value of the '<em><b>Redefined Classifier</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Classifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the Classifiers that are redefined by this Classifier.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Redefined Classifier</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getClassifier_RedefinedClassifier()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Classifier> getRedefinedClassifier();

	/**
	 * Returns the value of the '<em><b>General</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Classifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the general classifier in the Generalization relationship.
	 * Specifies the general Classifiers for this Classifier.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>General</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getClassifier_General()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='parents()'"
	 * @generated
	 */
	EList<Classifier> getGeneral();

	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Refers to all of the Properties that are direct (i.e. not inherited or imported) attributes of the classifier.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attribute</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getClassifier_Attribute()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='if self.oclIsKindOf(Class) then\r\n\tself.oclAsType(Class).ownedAttribute\r\nelse\r\n\tif self.oclIsKindOf(DataType) then\r\n\t\tself.oclAsType(DataType).ownedAttribute\r\n\telse\r\n\t\tSet {}\r\n\tendif\r\nendif'"
	 * @generated
	 */
	EList<Property> getAttribute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Generalization hierarchies must be directed and acyclical. A classifier can not be both a transitively general and transitively specific classifier of the same classifier.
	 * not self.allParents()->includes(self)
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean no_cycles_in_generalization(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Generalization hierarchies must be directed and acyclical. A classifier can not be both a transitively general and transitively specific classifier of the same classifier.
	 * not self.allParents()->includes(self)
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean generalization_hierarchies(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A classifier may only specialize classifiers of a valid type.
	 * self.parents()->forAll(c | self.maySpecializeType(c))
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean specialize_type(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Classifier that maps to a GeneralizationSet may neither be a specific nor a general Classifier in any of the Generalization relationships defined for that GeneralizationSet. In other words, a power type may not be an instance of itself nor may its instances also be its subclasses.
	 * true
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean maps_to_generalization_set(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves all the attributes of this classifier, including those inherited from its parents.
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<Property> getAllAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The general classifiers are the classifiers referenced by the generalization relationships.
	 * result = self.parents()
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<Classifier> getGenerals();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The inheritedMember association is derived by inheriting the inheritable members of the parents.
	 * result = self.inherit(self.parents()->collect(p | p.inheritableMembers(self))
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<NamedElement> getInheritedMembers();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query allFeatures() gives all of the features in the namespace of the classifier. In general, through mechanisms such as inheritance, this will be a larger set than feature.
	 * result = member->select(oclIsKindOf(Feature))
	 * <!-- end-model-doc -->
	 * @model ordered="false"
	 * @generated
	 */
	EList<Feature> allFeatures();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query parents() gives all of the immediate ancestors of a generalized Classifier.
	 * result = generalization.general
	 * <!-- end-model-doc -->
	 * @model ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='generalization.general'"
	 * @generated
	 */
	EList<Classifier> parents();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query inheritableMembers() gives all of the members of a classifier that may be inherited in one of its descendants, subject to whatever visibility restrictions apply.
	 * c.allParents()->includes(self)
	 * result = member->select(m | c.hasVisibilityOf(m))
	 * <!-- end-model-doc -->
	 * @model ordered="false" cRequired="true" cOrdered="false"
	 * @generated
	 */
	EList<NamedElement> inheritableMembers(Classifier c);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query hasVisibilityOf() determines whether a named element is visible in the classifier. By default all are visible. It is only called when the argument is something owned by a parent.
	 * self.allParents()->collect(c | c.member)->includes(n)
	 * result = if (self.inheritedMember->includes(n)) then (n.visibility <> #private) else true
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.Boolean" required="true" ordered="false" nRequired="true" nOrdered="false"
	 * @generated
	 */
	boolean hasVisibilityOf(NamedElement n);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query conformsTo() gives true for a classifier that defines a type that conforms to another. This is used, for example, in the specification of signature conformance for operations.
	 * result = (self=other) or (self.allParents()->includes(other))
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.Boolean" required="true" ordered="false" otherRequired="true" otherOrdered="false"
	 * @generated
	 */
	boolean conformsTo(Classifier other);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The inherit operation is overridden to exclude redefined properties.
	 * The query inherit() defines how to inherit a set of elements. Here the operation is defined to inherit them all. It is intended to be redefined in circumstances where inheritance is affected by redefinition.
	 * result = inhs
	 * <!-- end-model-doc -->
	 * @model ordered="false" inhsMany="true" inhsOrdered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='inhs'"
	 * @generated
	 */
	EList<NamedElement> inherit(EList<NamedElement> inhs);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query maySpecializeType() determines whether this classifier may have a generalization relationship to classifiers of the specified type. By default a classifier may specialize classifiers of the same or a more general type. It is intended to be redefined by classifiers that have different specialization constraints.
	 * result = self.oclIsKindOf(c.oclType)
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.Boolean" required="true" ordered="false" cRequired="true" cOrdered="false"
	 * @generated
	 */
	boolean maySpecializeType(Classifier c);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query allParents() gives all of the direct and indirect ancestors of a generalized Classifier.
	 * result = self.parents()->union(self.parents()->collect(p | p.allParents())
	 * <!-- end-model-doc -->
	 * @model ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='self.parents()->union(self.parents()->collect(p | p.allParents()))'"
	 * @generated
	 */
	EList<Classifier> allParents();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='allParents()->including(self)->exists ( x | x.oclIsKindOf (Kind) )'"
	 * @generated
	 */
	boolean hasKindAncestor();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='allParents()->including(self)->exists ( x | x.oclIsKindOf (Quantity) )'"
	 * @generated
	 */
	boolean hasQuantityAncestor();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='allParents()->including(self)->exists ( x | x.oclIsKindOf (Collective) )'"
	 * @generated
	 */
	boolean hasCollectiveAncestor();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='ObjectClass.allInstances()->select ( x | x.allParents()->includes(self) )->forAll ( y | not y.hasQuantityAncestor() and not y.hasCollectiveAncestor() )'"
	 * @generated
	 */
	boolean hasKindOffspring();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='ObjectClass.allInstances()->select ( x | x.allParents()->includes(self) )->forAll ( y | not y.hasKindAncestor() and not y.hasCollectiveAncestor() )'"
	 * @generated
	 */
	boolean hasQuantityOffspring();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='ObjectClass.allInstances()->select ( x | x.allParents()->includes(self) )->forAll ( y | not y.hasKindAncestor() and not y.hasQuantityAncestor() )'"
	 * @generated
	 */
	boolean hasCollectiveOffspring();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='if oclIsKindOf (SortalClass) then\r\n\thasKindAncestor()\r\nelse\r\n\tif oclIsKindOf (MixinClass) then\r\n\t\thasKindOffspring()\r\n\telse\r\n\t\tfalse\r\n\tendif\r\nendif'"
	 * @generated
	 */
	boolean hasFunctionalComplexInstances();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='if oclIsKindOf (SortalClass) then\r\n\thasQuantityAncestor()\r\nelse\r\n\tif oclIsKindOf (MixinClass) then\r\n\t\thasQuantityOffspring()\r\n\telse\r\n\t\tfalse\r\n\tendif\r\nendif'"
	 * @generated
	 */
	boolean hasQuantityInstances();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='if oclIsKindOf (SortalClass) then\r\n\thasCollectiveAncestor()\r\nelse\r\n\tif oclIsKindOf (MixinClass) then\r\n\t\thasCollectiveOffspring()\r\n\telse\r\n\t\tfalse\r\n\tendif\r\nendif'"
	 * @generated
	 */
	boolean hasCollectiveInstances();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='Generalization.allInstances()->select (g | g.general = self.oclAsType(Classifier)).specific'"
	 *        annotation="Comments children='This is NOT from the UML specification. Gets the immediate children.'"
	 * @generated
	 */
	EList<Classifier> children();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='self.children()->union(self.children()->collect(c | c.allChildren()))'"
	 *        annotation="Comments allChildren='This is NOT from the UML specification. Gets all children.'"
	 * @generated
	 */
	EList<Classifier> allChildren();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='GeneralizationSet.allInstances()->select (gs | gs.parent() = self)'"
	 *        annotation="Comments partitions='This is NOT from the UML specification. Gets all the GeneralizationSets in which this Classifier acts as a parent.'"
	 * @generated
	 */
	EList<GeneralizationSet> partitions();

} // Classifier
