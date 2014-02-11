/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.util;

import RefOntoUML.AntiRigidMixinClass;
import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Association;
import RefOntoUML.BasicMeasurementRegion;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Comment;
import RefOntoUML.ComposedMeasurementRegion;
import RefOntoUML.Constraintx;
import RefOntoUML.DataType;
import RefOntoUML.DecimalIntervalDimension;
import RefOntoUML.DecimalMeasurementRegion;
import RefOntoUML.DecimalOrdinalDimension;
import RefOntoUML.DecimalRationalDimension;
import RefOntoUML.Dependency;
import RefOntoUML.DependencyRelationship;
import RefOntoUML.Derivation;
import RefOntoUML.DirectedBinaryAssociation;
import RefOntoUML.DirectedRelationship;
import RefOntoUML.Element;
import RefOntoUML.ElementImport;
import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.Expression;
import RefOntoUML.Feature;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.InstanceSpecification;
import RefOntoUML.InstanceValue;
import RefOntoUML.IntegerIntervalDimension;
import RefOntoUML.IntegerMeasurementRegion;
import RefOntoUML.IntegerOrdinalDimension;
import RefOntoUML.IntegerRationalDimension;
import RefOntoUML.IntervalDimension;
import RefOntoUML.IntrinsicMomentClass;
import RefOntoUML.Kind;
import RefOntoUML.LiteralBoolean;
import RefOntoUML.LiteralDecimal;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralNull;
import RefOntoUML.LiteralSpecification;
import RefOntoUML.LiteralString;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.MeasurableQuality;
import RefOntoUML.MeasurementDimension;
import RefOntoUML.MeasurementDomain;
import RefOntoUML.MeasurementRegion;
import RefOntoUML.MeasurementStructure;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mixin;
import RefOntoUML.MixinClass;
import RefOntoUML.Mode;
import RefOntoUML.Model;
import RefOntoUML.MomentClass;
import RefOntoUML.MultiplicityElement;
import RefOntoUML.NamedElement;
import RefOntoUML.Namespace;
import RefOntoUML.NominalQuality;
import RefOntoUML.NominalRegion;
import RefOntoUML.NominalStructure;
import RefOntoUML.NonPerceivableQuality;
import RefOntoUML.NonRigidMixinClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.OpaqueExpression;
import RefOntoUML.OrdinalDimension;
import RefOntoUML.OrdinalEnumeration;
import RefOntoUML.OrdinalLiteral;
import RefOntoUML.PackageImport;
import RefOntoUML.PackageMerge;
import RefOntoUML.PackageableElement;
import RefOntoUML.PerceivableQuality;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Quality;
import RefOntoUML.Quantity;
import RefOntoUML.RationalDimension;
import RefOntoUML.RedefinableElement;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.ReferenceRegion;
import RefOntoUML.ReferenceStructure;
import RefOntoUML.Relationship;
import RefOntoUML.Relator;
import RefOntoUML.RigidMixinClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SemiRigidMixinClass;
import RefOntoUML.Slot;
import RefOntoUML.SortalClass;
import RefOntoUML.StringExpression;
import RefOntoUML.StringNominalRegion;
import RefOntoUML.StringNominalStructure;
import RefOntoUML.StructuralFeature;
import RefOntoUML.Structuration;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.Type;
import RefOntoUML.TypedElement;
import RefOntoUML.ValueSpecification;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see RefOntoUML.RefOntoUMLPackage
 * @generated
 */
public class RefOntoUMLSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static RefOntoUMLPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUMLSwitch() {
		if (modelPackage == null) {
			modelPackage = RefOntoUMLPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case RefOntoUMLPackage.COMMENT: {
				Comment comment = (Comment)theEObject;
				T result = caseComment(comment);
				if (result == null) result = caseElement(comment);
				if (result == null) result = caseEModelElement(comment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.ELEMENT: {
				Element element = (Element)theEObject;
				T result = caseElement(element);
				if (result == null) result = caseEModelElement(element);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.PACKAGE: {
				RefOntoUML.Package package_ = (RefOntoUML.Package)theEObject;
				T result = casePackage(package_);
				if (result == null) result = caseNamespace(package_);
				if (result == null) result = casePackageableElement(package_);
				if (result == null) result = caseNamedElement(package_);
				if (result == null) result = caseElement(package_);
				if (result == null) result = caseEModelElement(package_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.PACKAGEABLE_ELEMENT: {
				PackageableElement packageableElement = (PackageableElement)theEObject;
				T result = casePackageableElement(packageableElement);
				if (result == null) result = caseNamedElement(packageableElement);
				if (result == null) result = caseElement(packageableElement);
				if (result == null) result = caseEModelElement(packageableElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.NAMED_ELEMENT: {
				NamedElement namedElement = (NamedElement)theEObject;
				T result = caseNamedElement(namedElement);
				if (result == null) result = caseElement(namedElement);
				if (result == null) result = caseEModelElement(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.DEPENDENCY: {
				Dependency dependency = (Dependency)theEObject;
				T result = caseDependency(dependency);
				if (result == null) result = casePackageableElement(dependency);
				if (result == null) result = caseDirectedRelationship(dependency);
				if (result == null) result = caseNamedElement(dependency);
				if (result == null) result = caseRelationship(dependency);
				if (result == null) result = caseElement(dependency);
				if (result == null) result = caseEModelElement(dependency);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.DIRECTED_RELATIONSHIP: {
				DirectedRelationship directedRelationship = (DirectedRelationship)theEObject;
				T result = caseDirectedRelationship(directedRelationship);
				if (result == null) result = caseRelationship(directedRelationship);
				if (result == null) result = caseElement(directedRelationship);
				if (result == null) result = caseEModelElement(directedRelationship);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.RELATIONSHIP: {
				Relationship relationship = (Relationship)theEObject;
				T result = caseRelationship(relationship);
				if (result == null) result = caseElement(relationship);
				if (result == null) result = caseEModelElement(relationship);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.NAMESPACE: {
				Namespace namespace = (Namespace)theEObject;
				T result = caseNamespace(namespace);
				if (result == null) result = caseNamedElement(namespace);
				if (result == null) result = caseElement(namespace);
				if (result == null) result = caseEModelElement(namespace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.ELEMENT_IMPORT: {
				ElementImport elementImport = (ElementImport)theEObject;
				T result = caseElementImport(elementImport);
				if (result == null) result = caseDirectedRelationship(elementImport);
				if (result == null) result = caseRelationship(elementImport);
				if (result == null) result = caseElement(elementImport);
				if (result == null) result = caseEModelElement(elementImport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.PACKAGE_IMPORT: {
				PackageImport packageImport = (PackageImport)theEObject;
				T result = casePackageImport(packageImport);
				if (result == null) result = caseDirectedRelationship(packageImport);
				if (result == null) result = caseRelationship(packageImport);
				if (result == null) result = caseElement(packageImport);
				if (result == null) result = caseEModelElement(packageImport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.CONSTRAINTX: {
				Constraintx constraintx = (Constraintx)theEObject;
				T result = caseConstraintx(constraintx);
				if (result == null) result = casePackageableElement(constraintx);
				if (result == null) result = caseNamedElement(constraintx);
				if (result == null) result = caseElement(constraintx);
				if (result == null) result = caseEModelElement(constraintx);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.VALUE_SPECIFICATION: {
				ValueSpecification valueSpecification = (ValueSpecification)theEObject;
				T result = caseValueSpecification(valueSpecification);
				if (result == null) result = casePackageableElement(valueSpecification);
				if (result == null) result = caseTypedElement(valueSpecification);
				if (result == null) result = caseNamedElement(valueSpecification);
				if (result == null) result = caseElement(valueSpecification);
				if (result == null) result = caseEModelElement(valueSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.TYPED_ELEMENT: {
				TypedElement typedElement = (TypedElement)theEObject;
				T result = caseTypedElement(typedElement);
				if (result == null) result = caseNamedElement(typedElement);
				if (result == null) result = caseElement(typedElement);
				if (result == null) result = caseEModelElement(typedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.TYPE: {
				Type type = (Type)theEObject;
				T result = caseType(type);
				if (result == null) result = casePackageableElement(type);
				if (result == null) result = caseNamedElement(type);
				if (result == null) result = caseElement(type);
				if (result == null) result = caseEModelElement(type);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.ASSOCIATION: {
				Association association = (Association)theEObject;
				T result = caseAssociation(association);
				if (result == null) result = caseClassifier(association);
				if (result == null) result = caseRelationship(association);
				if (result == null) result = caseNamespace(association);
				if (result == null) result = caseRedefinableElement(association);
				if (result == null) result = caseType(association);
				if (result == null) result = casePackageableElement(association);
				if (result == null) result = caseNamedElement(association);
				if (result == null) result = caseElement(association);
				if (result == null) result = caseEModelElement(association);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.CLASSIFIER: {
				Classifier classifier = (Classifier)theEObject;
				T result = caseClassifier(classifier);
				if (result == null) result = caseNamespace(classifier);
				if (result == null) result = caseRedefinableElement(classifier);
				if (result == null) result = caseType(classifier);
				if (result == null) result = casePackageableElement(classifier);
				if (result == null) result = caseNamedElement(classifier);
				if (result == null) result = caseElement(classifier);
				if (result == null) result = caseEModelElement(classifier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.REDEFINABLE_ELEMENT: {
				RedefinableElement redefinableElement = (RedefinableElement)theEObject;
				T result = caseRedefinableElement(redefinableElement);
				if (result == null) result = caseNamedElement(redefinableElement);
				if (result == null) result = caseElement(redefinableElement);
				if (result == null) result = caseEModelElement(redefinableElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.GENERALIZATION: {
				Generalization generalization = (Generalization)theEObject;
				T result = caseGeneralization(generalization);
				if (result == null) result = caseDirectedRelationship(generalization);
				if (result == null) result = caseRelationship(generalization);
				if (result == null) result = caseElement(generalization);
				if (result == null) result = caseEModelElement(generalization);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.GENERALIZATION_SET: {
				GeneralizationSet generalizationSet = (GeneralizationSet)theEObject;
				T result = caseGeneralizationSet(generalizationSet);
				if (result == null) result = casePackageableElement(generalizationSet);
				if (result == null) result = caseNamedElement(generalizationSet);
				if (result == null) result = caseElement(generalizationSet);
				if (result == null) result = caseEModelElement(generalizationSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.FEATURE: {
				Feature feature = (Feature)theEObject;
				T result = caseFeature(feature);
				if (result == null) result = caseRedefinableElement(feature);
				if (result == null) result = caseNamedElement(feature);
				if (result == null) result = caseElement(feature);
				if (result == null) result = caseEModelElement(feature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.OPAQUE_EXPRESSION: {
				OpaqueExpression opaqueExpression = (OpaqueExpression)theEObject;
				T result = caseOpaqueExpression(opaqueExpression);
				if (result == null) result = caseValueSpecification(opaqueExpression);
				if (result == null) result = casePackageableElement(opaqueExpression);
				if (result == null) result = caseTypedElement(opaqueExpression);
				if (result == null) result = caseNamedElement(opaqueExpression);
				if (result == null) result = caseElement(opaqueExpression);
				if (result == null) result = caseEModelElement(opaqueExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT: {
				MultiplicityElement multiplicityElement = (MultiplicityElement)theEObject;
				T result = caseMultiplicityElement(multiplicityElement);
				if (result == null) result = caseElement(multiplicityElement);
				if (result == null) result = caseEModelElement(multiplicityElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.PROPERTY: {
				Property property = (Property)theEObject;
				T result = caseProperty(property);
				if (result == null) result = caseStructuralFeature(property);
				if (result == null) result = caseFeature(property);
				if (result == null) result = caseTypedElement(property);
				if (result == null) result = caseMultiplicityElement(property);
				if (result == null) result = caseRedefinableElement(property);
				if (result == null) result = caseNamedElement(property);
				if (result == null) result = caseElement(property);
				if (result == null) result = caseEModelElement(property);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.CLASS: {
				RefOntoUML.Class class_ = (RefOntoUML.Class)theEObject;
				T result = caseClass(class_);
				if (result == null) result = caseClassifier(class_);
				if (result == null) result = caseNamespace(class_);
				if (result == null) result = caseRedefinableElement(class_);
				if (result == null) result = caseType(class_);
				if (result == null) result = casePackageableElement(class_);
				if (result == null) result = caseNamedElement(class_);
				if (result == null) result = caseElement(class_);
				if (result == null) result = caseEModelElement(class_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MODEL: {
				Model model = (Model)theEObject;
				T result = caseModel(model);
				if (result == null) result = casePackage(model);
				if (result == null) result = caseNamespace(model);
				if (result == null) result = casePackageableElement(model);
				if (result == null) result = caseNamedElement(model);
				if (result == null) result = caseElement(model);
				if (result == null) result = caseEModelElement(model);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.DATA_TYPE: {
				DataType dataType = (DataType)theEObject;
				T result = caseDataType(dataType);
				if (result == null) result = caseClassifier(dataType);
				if (result == null) result = caseNamespace(dataType);
				if (result == null) result = caseRedefinableElement(dataType);
				if (result == null) result = caseType(dataType);
				if (result == null) result = casePackageableElement(dataType);
				if (result == null) result = caseNamedElement(dataType);
				if (result == null) result = caseElement(dataType);
				if (result == null) result = caseEModelElement(dataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.STRUCTURAL_FEATURE: {
				StructuralFeature structuralFeature = (StructuralFeature)theEObject;
				T result = caseStructuralFeature(structuralFeature);
				if (result == null) result = caseFeature(structuralFeature);
				if (result == null) result = caseTypedElement(structuralFeature);
				if (result == null) result = caseMultiplicityElement(structuralFeature);
				if (result == null) result = caseRedefinableElement(structuralFeature);
				if (result == null) result = caseNamedElement(structuralFeature);
				if (result == null) result = caseElement(structuralFeature);
				if (result == null) result = caseEModelElement(structuralFeature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.STRING_EXPRESSION: {
				StringExpression stringExpression = (StringExpression)theEObject;
				T result = caseStringExpression(stringExpression);
				if (result == null) result = caseExpression(stringExpression);
				if (result == null) result = caseValueSpecification(stringExpression);
				if (result == null) result = casePackageableElement(stringExpression);
				if (result == null) result = caseTypedElement(stringExpression);
				if (result == null) result = caseNamedElement(stringExpression);
				if (result == null) result = caseElement(stringExpression);
				if (result == null) result = caseEModelElement(stringExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.EXPRESSION: {
				Expression expression = (Expression)theEObject;
				T result = caseExpression(expression);
				if (result == null) result = caseValueSpecification(expression);
				if (result == null) result = casePackageableElement(expression);
				if (result == null) result = caseTypedElement(expression);
				if (result == null) result = caseNamedElement(expression);
				if (result == null) result = caseElement(expression);
				if (result == null) result = caseEModelElement(expression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.PACKAGE_MERGE: {
				PackageMerge packageMerge = (PackageMerge)theEObject;
				T result = casePackageMerge(packageMerge);
				if (result == null) result = caseDirectedRelationship(packageMerge);
				if (result == null) result = caseRelationship(packageMerge);
				if (result == null) result = caseElement(packageMerge);
				if (result == null) result = caseEModelElement(packageMerge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.INSTANCE_SPECIFICATION: {
				InstanceSpecification instanceSpecification = (InstanceSpecification)theEObject;
				T result = caseInstanceSpecification(instanceSpecification);
				if (result == null) result = casePackageableElement(instanceSpecification);
				if (result == null) result = caseNamedElement(instanceSpecification);
				if (result == null) result = caseElement(instanceSpecification);
				if (result == null) result = caseEModelElement(instanceSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.SLOT: {
				Slot slot = (Slot)theEObject;
				T result = caseSlot(slot);
				if (result == null) result = caseElement(slot);
				if (result == null) result = caseEModelElement(slot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.LITERAL_SPECIFICATION: {
				LiteralSpecification literalSpecification = (LiteralSpecification)theEObject;
				T result = caseLiteralSpecification(literalSpecification);
				if (result == null) result = caseValueSpecification(literalSpecification);
				if (result == null) result = casePackageableElement(literalSpecification);
				if (result == null) result = caseTypedElement(literalSpecification);
				if (result == null) result = caseNamedElement(literalSpecification);
				if (result == null) result = caseElement(literalSpecification);
				if (result == null) result = caseEModelElement(literalSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.ENUMERATION: {
				Enumeration enumeration = (Enumeration)theEObject;
				T result = caseEnumeration(enumeration);
				if (result == null) result = caseDataType(enumeration);
				if (result == null) result = caseClassifier(enumeration);
				if (result == null) result = caseNamespace(enumeration);
				if (result == null) result = caseRedefinableElement(enumeration);
				if (result == null) result = caseType(enumeration);
				if (result == null) result = casePackageableElement(enumeration);
				if (result == null) result = caseNamedElement(enumeration);
				if (result == null) result = caseElement(enumeration);
				if (result == null) result = caseEModelElement(enumeration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.ENUMERATION_LITERAL: {
				EnumerationLiteral enumerationLiteral = (EnumerationLiteral)theEObject;
				T result = caseEnumerationLiteral(enumerationLiteral);
				if (result == null) result = caseInstanceSpecification(enumerationLiteral);
				if (result == null) result = casePackageableElement(enumerationLiteral);
				if (result == null) result = caseNamedElement(enumerationLiteral);
				if (result == null) result = caseElement(enumerationLiteral);
				if (result == null) result = caseEModelElement(enumerationLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.ORDINAL_ENUMERATION: {
				OrdinalEnumeration ordinalEnumeration = (OrdinalEnumeration)theEObject;
				T result = caseOrdinalEnumeration(ordinalEnumeration);
				if (result == null) result = caseOrdinalDimension(ordinalEnumeration);
				if (result == null) result = caseEnumeration(ordinalEnumeration);
				if (result == null) result = caseMeasurementDimension(ordinalEnumeration);
				if (result == null) result = caseMeasurementStructure(ordinalEnumeration);
				if (result == null) result = caseReferenceStructure(ordinalEnumeration);
				if (result == null) result = caseDataType(ordinalEnumeration);
				if (result == null) result = caseClassifier(ordinalEnumeration);
				if (result == null) result = caseNamespace(ordinalEnumeration);
				if (result == null) result = caseRedefinableElement(ordinalEnumeration);
				if (result == null) result = caseType(ordinalEnumeration);
				if (result == null) result = casePackageableElement(ordinalEnumeration);
				if (result == null) result = caseNamedElement(ordinalEnumeration);
				if (result == null) result = caseElement(ordinalEnumeration);
				if (result == null) result = caseEModelElement(ordinalEnumeration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.ORDINAL_LITERAL: {
				OrdinalLiteral ordinalLiteral = (OrdinalLiteral)theEObject;
				T result = caseOrdinalLiteral(ordinalLiteral);
				if (result == null) result = caseEnumerationLiteral(ordinalLiteral);
				if (result == null) result = caseBasicMeasurementRegion(ordinalLiteral);
				if (result == null) result = caseInstanceSpecification(ordinalLiteral);
				if (result == null) result = caseMeasurementRegion(ordinalLiteral);
				if (result == null) result = caseReferenceRegion(ordinalLiteral);
				if (result == null) result = caseLiteralSpecification(ordinalLiteral);
				if (result == null) result = caseElement(ordinalLiteral);
				if (result == null) result = caseValueSpecification(ordinalLiteral);
				if (result == null) result = casePackageableElement(ordinalLiteral);
				if (result == null) result = caseEModelElement(ordinalLiteral);
				if (result == null) result = caseTypedElement(ordinalLiteral);
				if (result == null) result = caseNamedElement(ordinalLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.PRIMITIVE_TYPE: {
				PrimitiveType primitiveType = (PrimitiveType)theEObject;
				T result = casePrimitiveType(primitiveType);
				if (result == null) result = caseDataType(primitiveType);
				if (result == null) result = caseClassifier(primitiveType);
				if (result == null) result = caseNamespace(primitiveType);
				if (result == null) result = caseRedefinableElement(primitiveType);
				if (result == null) result = caseType(primitiveType);
				if (result == null) result = casePackageableElement(primitiveType);
				if (result == null) result = caseNamedElement(primitiveType);
				if (result == null) result = caseElement(primitiveType);
				if (result == null) result = caseEModelElement(primitiveType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.REFERENCE_STRUCTURE: {
				ReferenceStructure referenceStructure = (ReferenceStructure)theEObject;
				T result = caseReferenceStructure(referenceStructure);
				if (result == null) result = caseDataType(referenceStructure);
				if (result == null) result = caseClassifier(referenceStructure);
				if (result == null) result = caseNamespace(referenceStructure);
				if (result == null) result = caseRedefinableElement(referenceStructure);
				if (result == null) result = caseType(referenceStructure);
				if (result == null) result = casePackageableElement(referenceStructure);
				if (result == null) result = caseNamedElement(referenceStructure);
				if (result == null) result = caseElement(referenceStructure);
				if (result == null) result = caseEModelElement(referenceStructure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.NOMINAL_STRUCTURE: {
				NominalStructure nominalStructure = (NominalStructure)theEObject;
				T result = caseNominalStructure(nominalStructure);
				if (result == null) result = caseReferenceStructure(nominalStructure);
				if (result == null) result = caseDataType(nominalStructure);
				if (result == null) result = caseClassifier(nominalStructure);
				if (result == null) result = caseNamespace(nominalStructure);
				if (result == null) result = caseRedefinableElement(nominalStructure);
				if (result == null) result = caseType(nominalStructure);
				if (result == null) result = casePackageableElement(nominalStructure);
				if (result == null) result = caseNamedElement(nominalStructure);
				if (result == null) result = caseElement(nominalStructure);
				if (result == null) result = caseEModelElement(nominalStructure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.STRING_NOMINAL_STRUCTURE: {
				StringNominalStructure stringNominalStructure = (StringNominalStructure)theEObject;
				T result = caseStringNominalStructure(stringNominalStructure);
				if (result == null) result = caseNominalStructure(stringNominalStructure);
				if (result == null) result = caseReferenceStructure(stringNominalStructure);
				if (result == null) result = caseDataType(stringNominalStructure);
				if (result == null) result = caseClassifier(stringNominalStructure);
				if (result == null) result = caseNamespace(stringNominalStructure);
				if (result == null) result = caseRedefinableElement(stringNominalStructure);
				if (result == null) result = caseType(stringNominalStructure);
				if (result == null) result = casePackageableElement(stringNominalStructure);
				if (result == null) result = caseNamedElement(stringNominalStructure);
				if (result == null) result = caseElement(stringNominalStructure);
				if (result == null) result = caseEModelElement(stringNominalStructure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MEASUREMENT_STRUCTURE: {
				MeasurementStructure measurementStructure = (MeasurementStructure)theEObject;
				T result = caseMeasurementStructure(measurementStructure);
				if (result == null) result = caseReferenceStructure(measurementStructure);
				if (result == null) result = caseDataType(measurementStructure);
				if (result == null) result = caseClassifier(measurementStructure);
				if (result == null) result = caseNamespace(measurementStructure);
				if (result == null) result = caseRedefinableElement(measurementStructure);
				if (result == null) result = caseType(measurementStructure);
				if (result == null) result = casePackageableElement(measurementStructure);
				if (result == null) result = caseNamedElement(measurementStructure);
				if (result == null) result = caseElement(measurementStructure);
				if (result == null) result = caseEModelElement(measurementStructure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION: {
				MeasurementDimension measurementDimension = (MeasurementDimension)theEObject;
				T result = caseMeasurementDimension(measurementDimension);
				if (result == null) result = caseMeasurementStructure(measurementDimension);
				if (result == null) result = caseReferenceStructure(measurementDimension);
				if (result == null) result = caseDataType(measurementDimension);
				if (result == null) result = caseClassifier(measurementDimension);
				if (result == null) result = caseNamespace(measurementDimension);
				if (result == null) result = caseRedefinableElement(measurementDimension);
				if (result == null) result = caseType(measurementDimension);
				if (result == null) result = casePackageableElement(measurementDimension);
				if (result == null) result = caseNamedElement(measurementDimension);
				if (result == null) result = caseElement(measurementDimension);
				if (result == null) result = caseEModelElement(measurementDimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.ORDINAL_DIMENSION: {
				OrdinalDimension ordinalDimension = (OrdinalDimension)theEObject;
				T result = caseOrdinalDimension(ordinalDimension);
				if (result == null) result = caseMeasurementDimension(ordinalDimension);
				if (result == null) result = caseMeasurementStructure(ordinalDimension);
				if (result == null) result = caseReferenceStructure(ordinalDimension);
				if (result == null) result = caseDataType(ordinalDimension);
				if (result == null) result = caseClassifier(ordinalDimension);
				if (result == null) result = caseNamespace(ordinalDimension);
				if (result == null) result = caseRedefinableElement(ordinalDimension);
				if (result == null) result = caseType(ordinalDimension);
				if (result == null) result = casePackageableElement(ordinalDimension);
				if (result == null) result = caseNamedElement(ordinalDimension);
				if (result == null) result = caseElement(ordinalDimension);
				if (result == null) result = caseEModelElement(ordinalDimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.INTEGER_ORDINAL_DIMENSION: {
				IntegerOrdinalDimension integerOrdinalDimension = (IntegerOrdinalDimension)theEObject;
				T result = caseIntegerOrdinalDimension(integerOrdinalDimension);
				if (result == null) result = caseOrdinalDimension(integerOrdinalDimension);
				if (result == null) result = caseMeasurementDimension(integerOrdinalDimension);
				if (result == null) result = caseMeasurementStructure(integerOrdinalDimension);
				if (result == null) result = caseReferenceStructure(integerOrdinalDimension);
				if (result == null) result = caseDataType(integerOrdinalDimension);
				if (result == null) result = caseClassifier(integerOrdinalDimension);
				if (result == null) result = caseNamespace(integerOrdinalDimension);
				if (result == null) result = caseRedefinableElement(integerOrdinalDimension);
				if (result == null) result = caseType(integerOrdinalDimension);
				if (result == null) result = casePackageableElement(integerOrdinalDimension);
				if (result == null) result = caseNamedElement(integerOrdinalDimension);
				if (result == null) result = caseElement(integerOrdinalDimension);
				if (result == null) result = caseEModelElement(integerOrdinalDimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.DECIMAL_ORDINAL_DIMENSION: {
				DecimalOrdinalDimension decimalOrdinalDimension = (DecimalOrdinalDimension)theEObject;
				T result = caseDecimalOrdinalDimension(decimalOrdinalDimension);
				if (result == null) result = caseOrdinalDimension(decimalOrdinalDimension);
				if (result == null) result = caseMeasurementDimension(decimalOrdinalDimension);
				if (result == null) result = caseMeasurementStructure(decimalOrdinalDimension);
				if (result == null) result = caseReferenceStructure(decimalOrdinalDimension);
				if (result == null) result = caseDataType(decimalOrdinalDimension);
				if (result == null) result = caseClassifier(decimalOrdinalDimension);
				if (result == null) result = caseNamespace(decimalOrdinalDimension);
				if (result == null) result = caseRedefinableElement(decimalOrdinalDimension);
				if (result == null) result = caseType(decimalOrdinalDimension);
				if (result == null) result = casePackageableElement(decimalOrdinalDimension);
				if (result == null) result = caseNamedElement(decimalOrdinalDimension);
				if (result == null) result = caseElement(decimalOrdinalDimension);
				if (result == null) result = caseEModelElement(decimalOrdinalDimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.INTERVAL_DIMENSION: {
				IntervalDimension intervalDimension = (IntervalDimension)theEObject;
				T result = caseIntervalDimension(intervalDimension);
				if (result == null) result = caseMeasurementDimension(intervalDimension);
				if (result == null) result = caseMeasurementStructure(intervalDimension);
				if (result == null) result = caseReferenceStructure(intervalDimension);
				if (result == null) result = caseDataType(intervalDimension);
				if (result == null) result = caseClassifier(intervalDimension);
				if (result == null) result = caseNamespace(intervalDimension);
				if (result == null) result = caseRedefinableElement(intervalDimension);
				if (result == null) result = caseType(intervalDimension);
				if (result == null) result = casePackageableElement(intervalDimension);
				if (result == null) result = caseNamedElement(intervalDimension);
				if (result == null) result = caseElement(intervalDimension);
				if (result == null) result = caseEModelElement(intervalDimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.INTEGER_INTERVAL_DIMENSION: {
				IntegerIntervalDimension integerIntervalDimension = (IntegerIntervalDimension)theEObject;
				T result = caseIntegerIntervalDimension(integerIntervalDimension);
				if (result == null) result = caseIntervalDimension(integerIntervalDimension);
				if (result == null) result = caseMeasurementDimension(integerIntervalDimension);
				if (result == null) result = caseMeasurementStructure(integerIntervalDimension);
				if (result == null) result = caseReferenceStructure(integerIntervalDimension);
				if (result == null) result = caseDataType(integerIntervalDimension);
				if (result == null) result = caseClassifier(integerIntervalDimension);
				if (result == null) result = caseNamespace(integerIntervalDimension);
				if (result == null) result = caseRedefinableElement(integerIntervalDimension);
				if (result == null) result = caseType(integerIntervalDimension);
				if (result == null) result = casePackageableElement(integerIntervalDimension);
				if (result == null) result = caseNamedElement(integerIntervalDimension);
				if (result == null) result = caseElement(integerIntervalDimension);
				if (result == null) result = caseEModelElement(integerIntervalDimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.DECIMAL_INTERVAL_DIMENSION: {
				DecimalIntervalDimension decimalIntervalDimension = (DecimalIntervalDimension)theEObject;
				T result = caseDecimalIntervalDimension(decimalIntervalDimension);
				if (result == null) result = caseIntervalDimension(decimalIntervalDimension);
				if (result == null) result = caseMeasurementDimension(decimalIntervalDimension);
				if (result == null) result = caseMeasurementStructure(decimalIntervalDimension);
				if (result == null) result = caseReferenceStructure(decimalIntervalDimension);
				if (result == null) result = caseDataType(decimalIntervalDimension);
				if (result == null) result = caseClassifier(decimalIntervalDimension);
				if (result == null) result = caseNamespace(decimalIntervalDimension);
				if (result == null) result = caseRedefinableElement(decimalIntervalDimension);
				if (result == null) result = caseType(decimalIntervalDimension);
				if (result == null) result = casePackageableElement(decimalIntervalDimension);
				if (result == null) result = caseNamedElement(decimalIntervalDimension);
				if (result == null) result = caseElement(decimalIntervalDimension);
				if (result == null) result = caseEModelElement(decimalIntervalDimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.RATIONAL_DIMENSION: {
				RationalDimension rationalDimension = (RationalDimension)theEObject;
				T result = caseRationalDimension(rationalDimension);
				if (result == null) result = caseMeasurementDimension(rationalDimension);
				if (result == null) result = caseMeasurementStructure(rationalDimension);
				if (result == null) result = caseReferenceStructure(rationalDimension);
				if (result == null) result = caseDataType(rationalDimension);
				if (result == null) result = caseClassifier(rationalDimension);
				if (result == null) result = caseNamespace(rationalDimension);
				if (result == null) result = caseRedefinableElement(rationalDimension);
				if (result == null) result = caseType(rationalDimension);
				if (result == null) result = casePackageableElement(rationalDimension);
				if (result == null) result = caseNamedElement(rationalDimension);
				if (result == null) result = caseElement(rationalDimension);
				if (result == null) result = caseEModelElement(rationalDimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.INTEGER_RATIONAL_DIMENSION: {
				IntegerRationalDimension integerRationalDimension = (IntegerRationalDimension)theEObject;
				T result = caseIntegerRationalDimension(integerRationalDimension);
				if (result == null) result = caseRationalDimension(integerRationalDimension);
				if (result == null) result = caseMeasurementDimension(integerRationalDimension);
				if (result == null) result = caseMeasurementStructure(integerRationalDimension);
				if (result == null) result = caseReferenceStructure(integerRationalDimension);
				if (result == null) result = caseDataType(integerRationalDimension);
				if (result == null) result = caseClassifier(integerRationalDimension);
				if (result == null) result = caseNamespace(integerRationalDimension);
				if (result == null) result = caseRedefinableElement(integerRationalDimension);
				if (result == null) result = caseType(integerRationalDimension);
				if (result == null) result = casePackageableElement(integerRationalDimension);
				if (result == null) result = caseNamedElement(integerRationalDimension);
				if (result == null) result = caseElement(integerRationalDimension);
				if (result == null) result = caseEModelElement(integerRationalDimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.DECIMAL_RATIONAL_DIMENSION: {
				DecimalRationalDimension decimalRationalDimension = (DecimalRationalDimension)theEObject;
				T result = caseDecimalRationalDimension(decimalRationalDimension);
				if (result == null) result = caseRationalDimension(decimalRationalDimension);
				if (result == null) result = caseMeasurementDimension(decimalRationalDimension);
				if (result == null) result = caseMeasurementStructure(decimalRationalDimension);
				if (result == null) result = caseReferenceStructure(decimalRationalDimension);
				if (result == null) result = caseDataType(decimalRationalDimension);
				if (result == null) result = caseClassifier(decimalRationalDimension);
				if (result == null) result = caseNamespace(decimalRationalDimension);
				if (result == null) result = caseRedefinableElement(decimalRationalDimension);
				if (result == null) result = caseType(decimalRationalDimension);
				if (result == null) result = casePackageableElement(decimalRationalDimension);
				if (result == null) result = caseNamedElement(decimalRationalDimension);
				if (result == null) result = caseElement(decimalRationalDimension);
				if (result == null) result = caseEModelElement(decimalRationalDimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN: {
				MeasurementDomain measurementDomain = (MeasurementDomain)theEObject;
				T result = caseMeasurementDomain(measurementDomain);
				if (result == null) result = caseMeasurementStructure(measurementDomain);
				if (result == null) result = caseReferenceStructure(measurementDomain);
				if (result == null) result = caseDataType(measurementDomain);
				if (result == null) result = caseClassifier(measurementDomain);
				if (result == null) result = caseNamespace(measurementDomain);
				if (result == null) result = caseRedefinableElement(measurementDomain);
				if (result == null) result = caseType(measurementDomain);
				if (result == null) result = casePackageableElement(measurementDomain);
				if (result == null) result = caseNamedElement(measurementDomain);
				if (result == null) result = caseElement(measurementDomain);
				if (result == null) result = caseEModelElement(measurementDomain);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.REFERENCE_REGION: {
				ReferenceRegion referenceRegion = (ReferenceRegion)theEObject;
				T result = caseReferenceRegion(referenceRegion);
				if (result == null) result = caseLiteralSpecification(referenceRegion);
				if (result == null) result = caseValueSpecification(referenceRegion);
				if (result == null) result = casePackageableElement(referenceRegion);
				if (result == null) result = caseTypedElement(referenceRegion);
				if (result == null) result = caseNamedElement(referenceRegion);
				if (result == null) result = caseElement(referenceRegion);
				if (result == null) result = caseEModelElement(referenceRegion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MEASUREMENT_REGION: {
				MeasurementRegion measurementRegion = (MeasurementRegion)theEObject;
				T result = caseMeasurementRegion(measurementRegion);
				if (result == null) result = caseReferenceRegion(measurementRegion);
				if (result == null) result = caseLiteralSpecification(measurementRegion);
				if (result == null) result = caseValueSpecification(measurementRegion);
				if (result == null) result = casePackageableElement(measurementRegion);
				if (result == null) result = caseTypedElement(measurementRegion);
				if (result == null) result = caseNamedElement(measurementRegion);
				if (result == null) result = caseElement(measurementRegion);
				if (result == null) result = caseEModelElement(measurementRegion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.BASIC_MEASUREMENT_REGION: {
				BasicMeasurementRegion basicMeasurementRegion = (BasicMeasurementRegion)theEObject;
				T result = caseBasicMeasurementRegion(basicMeasurementRegion);
				if (result == null) result = caseMeasurementRegion(basicMeasurementRegion);
				if (result == null) result = caseReferenceRegion(basicMeasurementRegion);
				if (result == null) result = caseLiteralSpecification(basicMeasurementRegion);
				if (result == null) result = caseValueSpecification(basicMeasurementRegion);
				if (result == null) result = casePackageableElement(basicMeasurementRegion);
				if (result == null) result = caseTypedElement(basicMeasurementRegion);
				if (result == null) result = caseNamedElement(basicMeasurementRegion);
				if (result == null) result = caseElement(basicMeasurementRegion);
				if (result == null) result = caseEModelElement(basicMeasurementRegion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.DECIMAL_MEASUREMENT_REGION: {
				DecimalMeasurementRegion decimalMeasurementRegion = (DecimalMeasurementRegion)theEObject;
				T result = caseDecimalMeasurementRegion(decimalMeasurementRegion);
				if (result == null) result = caseBasicMeasurementRegion(decimalMeasurementRegion);
				if (result == null) result = caseLiteralDecimal(decimalMeasurementRegion);
				if (result == null) result = caseMeasurementRegion(decimalMeasurementRegion);
				if (result == null) result = caseReferenceRegion(decimalMeasurementRegion);
				if (result == null) result = caseLiteralSpecification(decimalMeasurementRegion);
				if (result == null) result = caseValueSpecification(decimalMeasurementRegion);
				if (result == null) result = casePackageableElement(decimalMeasurementRegion);
				if (result == null) result = caseTypedElement(decimalMeasurementRegion);
				if (result == null) result = caseNamedElement(decimalMeasurementRegion);
				if (result == null) result = caseElement(decimalMeasurementRegion);
				if (result == null) result = caseEModelElement(decimalMeasurementRegion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.INTEGER_MEASUREMENT_REGION: {
				IntegerMeasurementRegion integerMeasurementRegion = (IntegerMeasurementRegion)theEObject;
				T result = caseIntegerMeasurementRegion(integerMeasurementRegion);
				if (result == null) result = caseBasicMeasurementRegion(integerMeasurementRegion);
				if (result == null) result = caseLiteralInteger(integerMeasurementRegion);
				if (result == null) result = caseMeasurementRegion(integerMeasurementRegion);
				if (result == null) result = caseReferenceRegion(integerMeasurementRegion);
				if (result == null) result = caseLiteralSpecification(integerMeasurementRegion);
				if (result == null) result = caseValueSpecification(integerMeasurementRegion);
				if (result == null) result = casePackageableElement(integerMeasurementRegion);
				if (result == null) result = caseTypedElement(integerMeasurementRegion);
				if (result == null) result = caseNamedElement(integerMeasurementRegion);
				if (result == null) result = caseElement(integerMeasurementRegion);
				if (result == null) result = caseEModelElement(integerMeasurementRegion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.COMPOSED_MEASUREMENT_REGION: {
				ComposedMeasurementRegion composedMeasurementRegion = (ComposedMeasurementRegion)theEObject;
				T result = caseComposedMeasurementRegion(composedMeasurementRegion);
				if (result == null) result = caseMeasurementRegion(composedMeasurementRegion);
				if (result == null) result = caseReferenceRegion(composedMeasurementRegion);
				if (result == null) result = caseLiteralSpecification(composedMeasurementRegion);
				if (result == null) result = caseValueSpecification(composedMeasurementRegion);
				if (result == null) result = casePackageableElement(composedMeasurementRegion);
				if (result == null) result = caseTypedElement(composedMeasurementRegion);
				if (result == null) result = caseNamedElement(composedMeasurementRegion);
				if (result == null) result = caseElement(composedMeasurementRegion);
				if (result == null) result = caseEModelElement(composedMeasurementRegion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.NOMINAL_REGION: {
				NominalRegion nominalRegion = (NominalRegion)theEObject;
				T result = caseNominalRegion(nominalRegion);
				if (result == null) result = caseReferenceRegion(nominalRegion);
				if (result == null) result = caseLiteralSpecification(nominalRegion);
				if (result == null) result = caseValueSpecification(nominalRegion);
				if (result == null) result = casePackageableElement(nominalRegion);
				if (result == null) result = caseTypedElement(nominalRegion);
				if (result == null) result = caseNamedElement(nominalRegion);
				if (result == null) result = caseElement(nominalRegion);
				if (result == null) result = caseEModelElement(nominalRegion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.STRING_NOMINAL_REGION: {
				StringNominalRegion stringNominalRegion = (StringNominalRegion)theEObject;
				T result = caseStringNominalRegion(stringNominalRegion);
				if (result == null) result = caseNominalRegion(stringNominalRegion);
				if (result == null) result = caseLiteralString(stringNominalRegion);
				if (result == null) result = caseReferenceRegion(stringNominalRegion);
				if (result == null) result = caseLiteralSpecification(stringNominalRegion);
				if (result == null) result = caseValueSpecification(stringNominalRegion);
				if (result == null) result = casePackageableElement(stringNominalRegion);
				if (result == null) result = caseTypedElement(stringNominalRegion);
				if (result == null) result = caseNamedElement(stringNominalRegion);
				if (result == null) result = caseElement(stringNominalRegion);
				if (result == null) result = caseEModelElement(stringNominalRegion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.LITERAL_INTEGER: {
				LiteralInteger literalInteger = (LiteralInteger)theEObject;
				T result = caseLiteralInteger(literalInteger);
				if (result == null) result = caseLiteralSpecification(literalInteger);
				if (result == null) result = caseValueSpecification(literalInteger);
				if (result == null) result = casePackageableElement(literalInteger);
				if (result == null) result = caseTypedElement(literalInteger);
				if (result == null) result = caseNamedElement(literalInteger);
				if (result == null) result = caseElement(literalInteger);
				if (result == null) result = caseEModelElement(literalInteger);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.LITERAL_DECIMAL: {
				LiteralDecimal literalDecimal = (LiteralDecimal)theEObject;
				T result = caseLiteralDecimal(literalDecimal);
				if (result == null) result = caseLiteralSpecification(literalDecimal);
				if (result == null) result = caseValueSpecification(literalDecimal);
				if (result == null) result = casePackageableElement(literalDecimal);
				if (result == null) result = caseTypedElement(literalDecimal);
				if (result == null) result = caseNamedElement(literalDecimal);
				if (result == null) result = caseElement(literalDecimal);
				if (result == null) result = caseEModelElement(literalDecimal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.LITERAL_STRING: {
				LiteralString literalString = (LiteralString)theEObject;
				T result = caseLiteralString(literalString);
				if (result == null) result = caseLiteralSpecification(literalString);
				if (result == null) result = caseValueSpecification(literalString);
				if (result == null) result = casePackageableElement(literalString);
				if (result == null) result = caseTypedElement(literalString);
				if (result == null) result = caseNamedElement(literalString);
				if (result == null) result = caseElement(literalString);
				if (result == null) result = caseEModelElement(literalString);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.LITERAL_BOOLEAN: {
				LiteralBoolean literalBoolean = (LiteralBoolean)theEObject;
				T result = caseLiteralBoolean(literalBoolean);
				if (result == null) result = caseLiteralSpecification(literalBoolean);
				if (result == null) result = caseValueSpecification(literalBoolean);
				if (result == null) result = casePackageableElement(literalBoolean);
				if (result == null) result = caseTypedElement(literalBoolean);
				if (result == null) result = caseNamedElement(literalBoolean);
				if (result == null) result = caseElement(literalBoolean);
				if (result == null) result = caseEModelElement(literalBoolean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.LITERAL_NULL: {
				LiteralNull literalNull = (LiteralNull)theEObject;
				T result = caseLiteralNull(literalNull);
				if (result == null) result = caseLiteralSpecification(literalNull);
				if (result == null) result = caseValueSpecification(literalNull);
				if (result == null) result = casePackageableElement(literalNull);
				if (result == null) result = caseTypedElement(literalNull);
				if (result == null) result = caseNamedElement(literalNull);
				if (result == null) result = caseElement(literalNull);
				if (result == null) result = caseEModelElement(literalNull);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.INSTANCE_VALUE: {
				InstanceValue instanceValue = (InstanceValue)theEObject;
				T result = caseInstanceValue(instanceValue);
				if (result == null) result = caseValueSpecification(instanceValue);
				if (result == null) result = casePackageableElement(instanceValue);
				if (result == null) result = caseTypedElement(instanceValue);
				if (result == null) result = caseNamedElement(instanceValue);
				if (result == null) result = caseElement(instanceValue);
				if (result == null) result = caseEModelElement(instanceValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.LITERAL_UNLIMITED_NATURAL: {
				LiteralUnlimitedNatural literalUnlimitedNatural = (LiteralUnlimitedNatural)theEObject;
				T result = caseLiteralUnlimitedNatural(literalUnlimitedNatural);
				if (result == null) result = caseLiteralSpecification(literalUnlimitedNatural);
				if (result == null) result = caseValueSpecification(literalUnlimitedNatural);
				if (result == null) result = casePackageableElement(literalUnlimitedNatural);
				if (result == null) result = caseTypedElement(literalUnlimitedNatural);
				if (result == null) result = caseNamedElement(literalUnlimitedNatural);
				if (result == null) result = caseElement(literalUnlimitedNatural);
				if (result == null) result = caseEModelElement(literalUnlimitedNatural);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.OBJECT_CLASS: {
				ObjectClass objectClass = (ObjectClass)theEObject;
				T result = caseObjectClass(objectClass);
				if (result == null) result = caseClass(objectClass);
				if (result == null) result = caseClassifier(objectClass);
				if (result == null) result = caseNamespace(objectClass);
				if (result == null) result = caseRedefinableElement(objectClass);
				if (result == null) result = caseType(objectClass);
				if (result == null) result = casePackageableElement(objectClass);
				if (result == null) result = caseNamedElement(objectClass);
				if (result == null) result = caseElement(objectClass);
				if (result == null) result = caseEModelElement(objectClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MOMENT_CLASS: {
				MomentClass momentClass = (MomentClass)theEObject;
				T result = caseMomentClass(momentClass);
				if (result == null) result = caseClass(momentClass);
				if (result == null) result = caseClassifier(momentClass);
				if (result == null) result = caseNamespace(momentClass);
				if (result == null) result = caseRedefinableElement(momentClass);
				if (result == null) result = caseType(momentClass);
				if (result == null) result = casePackageableElement(momentClass);
				if (result == null) result = caseNamedElement(momentClass);
				if (result == null) result = caseElement(momentClass);
				if (result == null) result = caseEModelElement(momentClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.SORTAL_CLASS: {
				SortalClass sortalClass = (SortalClass)theEObject;
				T result = caseSortalClass(sortalClass);
				if (result == null) result = caseObjectClass(sortalClass);
				if (result == null) result = caseClass(sortalClass);
				if (result == null) result = caseClassifier(sortalClass);
				if (result == null) result = caseNamespace(sortalClass);
				if (result == null) result = caseRedefinableElement(sortalClass);
				if (result == null) result = caseType(sortalClass);
				if (result == null) result = casePackageableElement(sortalClass);
				if (result == null) result = caseNamedElement(sortalClass);
				if (result == null) result = caseElement(sortalClass);
				if (result == null) result = caseEModelElement(sortalClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MIXIN_CLASS: {
				MixinClass mixinClass = (MixinClass)theEObject;
				T result = caseMixinClass(mixinClass);
				if (result == null) result = caseObjectClass(mixinClass);
				if (result == null) result = caseClass(mixinClass);
				if (result == null) result = caseClassifier(mixinClass);
				if (result == null) result = caseNamespace(mixinClass);
				if (result == null) result = caseRedefinableElement(mixinClass);
				if (result == null) result = caseType(mixinClass);
				if (result == null) result = casePackageableElement(mixinClass);
				if (result == null) result = caseNamedElement(mixinClass);
				if (result == null) result = caseElement(mixinClass);
				if (result == null) result = caseEModelElement(mixinClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.RIGID_SORTAL_CLASS: {
				RigidSortalClass rigidSortalClass = (RigidSortalClass)theEObject;
				T result = caseRigidSortalClass(rigidSortalClass);
				if (result == null) result = caseSortalClass(rigidSortalClass);
				if (result == null) result = caseObjectClass(rigidSortalClass);
				if (result == null) result = caseClass(rigidSortalClass);
				if (result == null) result = caseClassifier(rigidSortalClass);
				if (result == null) result = caseNamespace(rigidSortalClass);
				if (result == null) result = caseRedefinableElement(rigidSortalClass);
				if (result == null) result = caseType(rigidSortalClass);
				if (result == null) result = casePackageableElement(rigidSortalClass);
				if (result == null) result = caseNamedElement(rigidSortalClass);
				if (result == null) result = caseElement(rigidSortalClass);
				if (result == null) result = caseEModelElement(rigidSortalClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.ANTI_RIGID_SORTAL_CLASS: {
				AntiRigidSortalClass antiRigidSortalClass = (AntiRigidSortalClass)theEObject;
				T result = caseAntiRigidSortalClass(antiRigidSortalClass);
				if (result == null) result = caseSortalClass(antiRigidSortalClass);
				if (result == null) result = caseObjectClass(antiRigidSortalClass);
				if (result == null) result = caseClass(antiRigidSortalClass);
				if (result == null) result = caseClassifier(antiRigidSortalClass);
				if (result == null) result = caseNamespace(antiRigidSortalClass);
				if (result == null) result = caseRedefinableElement(antiRigidSortalClass);
				if (result == null) result = caseType(antiRigidSortalClass);
				if (result == null) result = casePackageableElement(antiRigidSortalClass);
				if (result == null) result = caseNamedElement(antiRigidSortalClass);
				if (result == null) result = caseElement(antiRigidSortalClass);
				if (result == null) result = caseEModelElement(antiRigidSortalClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.SUBSTANCE_SORTAL: {
				SubstanceSortal substanceSortal = (SubstanceSortal)theEObject;
				T result = caseSubstanceSortal(substanceSortal);
				if (result == null) result = caseRigidSortalClass(substanceSortal);
				if (result == null) result = caseSortalClass(substanceSortal);
				if (result == null) result = caseObjectClass(substanceSortal);
				if (result == null) result = caseClass(substanceSortal);
				if (result == null) result = caseClassifier(substanceSortal);
				if (result == null) result = caseNamespace(substanceSortal);
				if (result == null) result = caseRedefinableElement(substanceSortal);
				if (result == null) result = caseType(substanceSortal);
				if (result == null) result = casePackageableElement(substanceSortal);
				if (result == null) result = caseNamedElement(substanceSortal);
				if (result == null) result = caseElement(substanceSortal);
				if (result == null) result = caseEModelElement(substanceSortal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.SUB_KIND: {
				SubKind subKind = (SubKind)theEObject;
				T result = caseSubKind(subKind);
				if (result == null) result = caseRigidSortalClass(subKind);
				if (result == null) result = caseSortalClass(subKind);
				if (result == null) result = caseObjectClass(subKind);
				if (result == null) result = caseClass(subKind);
				if (result == null) result = caseClassifier(subKind);
				if (result == null) result = caseNamespace(subKind);
				if (result == null) result = caseRedefinableElement(subKind);
				if (result == null) result = caseType(subKind);
				if (result == null) result = casePackageableElement(subKind);
				if (result == null) result = caseNamedElement(subKind);
				if (result == null) result = caseElement(subKind);
				if (result == null) result = caseEModelElement(subKind);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.KIND: {
				Kind kind = (Kind)theEObject;
				T result = caseKind(kind);
				if (result == null) result = caseSubstanceSortal(kind);
				if (result == null) result = caseRigidSortalClass(kind);
				if (result == null) result = caseSortalClass(kind);
				if (result == null) result = caseObjectClass(kind);
				if (result == null) result = caseClass(kind);
				if (result == null) result = caseClassifier(kind);
				if (result == null) result = caseNamespace(kind);
				if (result == null) result = caseRedefinableElement(kind);
				if (result == null) result = caseType(kind);
				if (result == null) result = casePackageableElement(kind);
				if (result == null) result = caseNamedElement(kind);
				if (result == null) result = caseElement(kind);
				if (result == null) result = caseEModelElement(kind);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.QUANTITY: {
				Quantity quantity = (Quantity)theEObject;
				T result = caseQuantity(quantity);
				if (result == null) result = caseSubstanceSortal(quantity);
				if (result == null) result = caseRigidSortalClass(quantity);
				if (result == null) result = caseSortalClass(quantity);
				if (result == null) result = caseObjectClass(quantity);
				if (result == null) result = caseClass(quantity);
				if (result == null) result = caseClassifier(quantity);
				if (result == null) result = caseNamespace(quantity);
				if (result == null) result = caseRedefinableElement(quantity);
				if (result == null) result = caseType(quantity);
				if (result == null) result = casePackageableElement(quantity);
				if (result == null) result = caseNamedElement(quantity);
				if (result == null) result = caseElement(quantity);
				if (result == null) result = caseEModelElement(quantity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.COLLECTIVE: {
				Collective collective = (Collective)theEObject;
				T result = caseCollective(collective);
				if (result == null) result = caseSubstanceSortal(collective);
				if (result == null) result = caseRigidSortalClass(collective);
				if (result == null) result = caseSortalClass(collective);
				if (result == null) result = caseObjectClass(collective);
				if (result == null) result = caseClass(collective);
				if (result == null) result = caseClassifier(collective);
				if (result == null) result = caseNamespace(collective);
				if (result == null) result = caseRedefinableElement(collective);
				if (result == null) result = caseType(collective);
				if (result == null) result = casePackageableElement(collective);
				if (result == null) result = caseNamedElement(collective);
				if (result == null) result = caseElement(collective);
				if (result == null) result = caseEModelElement(collective);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.PHASE: {
				Phase phase = (Phase)theEObject;
				T result = casePhase(phase);
				if (result == null) result = caseAntiRigidSortalClass(phase);
				if (result == null) result = caseSortalClass(phase);
				if (result == null) result = caseObjectClass(phase);
				if (result == null) result = caseClass(phase);
				if (result == null) result = caseClassifier(phase);
				if (result == null) result = caseNamespace(phase);
				if (result == null) result = caseRedefinableElement(phase);
				if (result == null) result = caseType(phase);
				if (result == null) result = casePackageableElement(phase);
				if (result == null) result = caseNamedElement(phase);
				if (result == null) result = caseElement(phase);
				if (result == null) result = caseEModelElement(phase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.ROLE: {
				Role role = (Role)theEObject;
				T result = caseRole(role);
				if (result == null) result = caseAntiRigidSortalClass(role);
				if (result == null) result = caseSortalClass(role);
				if (result == null) result = caseObjectClass(role);
				if (result == null) result = caseClass(role);
				if (result == null) result = caseClassifier(role);
				if (result == null) result = caseNamespace(role);
				if (result == null) result = caseRedefinableElement(role);
				if (result == null) result = caseType(role);
				if (result == null) result = casePackageableElement(role);
				if (result == null) result = caseNamedElement(role);
				if (result == null) result = caseElement(role);
				if (result == null) result = caseEModelElement(role);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.RIGID_MIXIN_CLASS: {
				RigidMixinClass rigidMixinClass = (RigidMixinClass)theEObject;
				T result = caseRigidMixinClass(rigidMixinClass);
				if (result == null) result = caseMixinClass(rigidMixinClass);
				if (result == null) result = caseObjectClass(rigidMixinClass);
				if (result == null) result = caseClass(rigidMixinClass);
				if (result == null) result = caseClassifier(rigidMixinClass);
				if (result == null) result = caseNamespace(rigidMixinClass);
				if (result == null) result = caseRedefinableElement(rigidMixinClass);
				if (result == null) result = caseType(rigidMixinClass);
				if (result == null) result = casePackageableElement(rigidMixinClass);
				if (result == null) result = caseNamedElement(rigidMixinClass);
				if (result == null) result = caseElement(rigidMixinClass);
				if (result == null) result = caseEModelElement(rigidMixinClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.NON_RIGID_MIXIN_CLASS: {
				NonRigidMixinClass nonRigidMixinClass = (NonRigidMixinClass)theEObject;
				T result = caseNonRigidMixinClass(nonRigidMixinClass);
				if (result == null) result = caseMixinClass(nonRigidMixinClass);
				if (result == null) result = caseObjectClass(nonRigidMixinClass);
				if (result == null) result = caseClass(nonRigidMixinClass);
				if (result == null) result = caseClassifier(nonRigidMixinClass);
				if (result == null) result = caseNamespace(nonRigidMixinClass);
				if (result == null) result = caseRedefinableElement(nonRigidMixinClass);
				if (result == null) result = caseType(nonRigidMixinClass);
				if (result == null) result = casePackageableElement(nonRigidMixinClass);
				if (result == null) result = caseNamedElement(nonRigidMixinClass);
				if (result == null) result = caseElement(nonRigidMixinClass);
				if (result == null) result = caseEModelElement(nonRigidMixinClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.CATEGORY: {
				Category category = (Category)theEObject;
				T result = caseCategory(category);
				if (result == null) result = caseRigidMixinClass(category);
				if (result == null) result = caseMixinClass(category);
				if (result == null) result = caseObjectClass(category);
				if (result == null) result = caseClass(category);
				if (result == null) result = caseClassifier(category);
				if (result == null) result = caseNamespace(category);
				if (result == null) result = caseRedefinableElement(category);
				if (result == null) result = caseType(category);
				if (result == null) result = casePackageableElement(category);
				if (result == null) result = caseNamedElement(category);
				if (result == null) result = caseElement(category);
				if (result == null) result = caseEModelElement(category);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.ANTI_RIGID_MIXIN_CLASS: {
				AntiRigidMixinClass antiRigidMixinClass = (AntiRigidMixinClass)theEObject;
				T result = caseAntiRigidMixinClass(antiRigidMixinClass);
				if (result == null) result = caseNonRigidMixinClass(antiRigidMixinClass);
				if (result == null) result = caseMixinClass(antiRigidMixinClass);
				if (result == null) result = caseObjectClass(antiRigidMixinClass);
				if (result == null) result = caseClass(antiRigidMixinClass);
				if (result == null) result = caseClassifier(antiRigidMixinClass);
				if (result == null) result = caseNamespace(antiRigidMixinClass);
				if (result == null) result = caseRedefinableElement(antiRigidMixinClass);
				if (result == null) result = caseType(antiRigidMixinClass);
				if (result == null) result = casePackageableElement(antiRigidMixinClass);
				if (result == null) result = caseNamedElement(antiRigidMixinClass);
				if (result == null) result = caseElement(antiRigidMixinClass);
				if (result == null) result = caseEModelElement(antiRigidMixinClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.SEMI_RIGID_MIXIN_CLASS: {
				SemiRigidMixinClass semiRigidMixinClass = (SemiRigidMixinClass)theEObject;
				T result = caseSemiRigidMixinClass(semiRigidMixinClass);
				if (result == null) result = caseNonRigidMixinClass(semiRigidMixinClass);
				if (result == null) result = caseMixinClass(semiRigidMixinClass);
				if (result == null) result = caseObjectClass(semiRigidMixinClass);
				if (result == null) result = caseClass(semiRigidMixinClass);
				if (result == null) result = caseClassifier(semiRigidMixinClass);
				if (result == null) result = caseNamespace(semiRigidMixinClass);
				if (result == null) result = caseRedefinableElement(semiRigidMixinClass);
				if (result == null) result = caseType(semiRigidMixinClass);
				if (result == null) result = casePackageableElement(semiRigidMixinClass);
				if (result == null) result = caseNamedElement(semiRigidMixinClass);
				if (result == null) result = caseElement(semiRigidMixinClass);
				if (result == null) result = caseEModelElement(semiRigidMixinClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.ROLE_MIXIN: {
				RoleMixin roleMixin = (RoleMixin)theEObject;
				T result = caseRoleMixin(roleMixin);
				if (result == null) result = caseAntiRigidMixinClass(roleMixin);
				if (result == null) result = caseNonRigidMixinClass(roleMixin);
				if (result == null) result = caseMixinClass(roleMixin);
				if (result == null) result = caseObjectClass(roleMixin);
				if (result == null) result = caseClass(roleMixin);
				if (result == null) result = caseClassifier(roleMixin);
				if (result == null) result = caseNamespace(roleMixin);
				if (result == null) result = caseRedefinableElement(roleMixin);
				if (result == null) result = caseType(roleMixin);
				if (result == null) result = casePackageableElement(roleMixin);
				if (result == null) result = caseNamedElement(roleMixin);
				if (result == null) result = caseElement(roleMixin);
				if (result == null) result = caseEModelElement(roleMixin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MIXIN: {
				Mixin mixin = (Mixin)theEObject;
				T result = caseMixin(mixin);
				if (result == null) result = caseSemiRigidMixinClass(mixin);
				if (result == null) result = caseNonRigidMixinClass(mixin);
				if (result == null) result = caseMixinClass(mixin);
				if (result == null) result = caseObjectClass(mixin);
				if (result == null) result = caseClass(mixin);
				if (result == null) result = caseClassifier(mixin);
				if (result == null) result = caseNamespace(mixin);
				if (result == null) result = caseRedefinableElement(mixin);
				if (result == null) result = caseType(mixin);
				if (result == null) result = casePackageableElement(mixin);
				if (result == null) result = caseNamedElement(mixin);
				if (result == null) result = caseElement(mixin);
				if (result == null) result = caseEModelElement(mixin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.INTRINSIC_MOMENT_CLASS: {
				IntrinsicMomentClass intrinsicMomentClass = (IntrinsicMomentClass)theEObject;
				T result = caseIntrinsicMomentClass(intrinsicMomentClass);
				if (result == null) result = caseMomentClass(intrinsicMomentClass);
				if (result == null) result = caseClass(intrinsicMomentClass);
				if (result == null) result = caseClassifier(intrinsicMomentClass);
				if (result == null) result = caseNamespace(intrinsicMomentClass);
				if (result == null) result = caseRedefinableElement(intrinsicMomentClass);
				if (result == null) result = caseType(intrinsicMomentClass);
				if (result == null) result = casePackageableElement(intrinsicMomentClass);
				if (result == null) result = caseNamedElement(intrinsicMomentClass);
				if (result == null) result = caseElement(intrinsicMomentClass);
				if (result == null) result = caseEModelElement(intrinsicMomentClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MODE: {
				Mode mode = (Mode)theEObject;
				T result = caseMode(mode);
				if (result == null) result = caseIntrinsicMomentClass(mode);
				if (result == null) result = caseMomentClass(mode);
				if (result == null) result = caseClass(mode);
				if (result == null) result = caseClassifier(mode);
				if (result == null) result = caseNamespace(mode);
				if (result == null) result = caseRedefinableElement(mode);
				if (result == null) result = caseType(mode);
				if (result == null) result = casePackageableElement(mode);
				if (result == null) result = caseNamedElement(mode);
				if (result == null) result = caseElement(mode);
				if (result == null) result = caseEModelElement(mode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.QUALITY: {
				Quality quality = (Quality)theEObject;
				T result = caseQuality(quality);
				if (result == null) result = caseIntrinsicMomentClass(quality);
				if (result == null) result = caseMomentClass(quality);
				if (result == null) result = caseClass(quality);
				if (result == null) result = caseClassifier(quality);
				if (result == null) result = caseNamespace(quality);
				if (result == null) result = caseRedefinableElement(quality);
				if (result == null) result = caseType(quality);
				if (result == null) result = casePackageableElement(quality);
				if (result == null) result = caseNamedElement(quality);
				if (result == null) result = caseElement(quality);
				if (result == null) result = caseEModelElement(quality);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MEASURABLE_QUALITY: {
				MeasurableQuality measurableQuality = (MeasurableQuality)theEObject;
				T result = caseMeasurableQuality(measurableQuality);
				if (result == null) result = caseQuality(measurableQuality);
				if (result == null) result = caseIntrinsicMomentClass(measurableQuality);
				if (result == null) result = caseMomentClass(measurableQuality);
				if (result == null) result = caseClass(measurableQuality);
				if (result == null) result = caseClassifier(measurableQuality);
				if (result == null) result = caseNamespace(measurableQuality);
				if (result == null) result = caseRedefinableElement(measurableQuality);
				if (result == null) result = caseType(measurableQuality);
				if (result == null) result = casePackageableElement(measurableQuality);
				if (result == null) result = caseNamedElement(measurableQuality);
				if (result == null) result = caseElement(measurableQuality);
				if (result == null) result = caseEModelElement(measurableQuality);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.NOMINAL_QUALITY: {
				NominalQuality nominalQuality = (NominalQuality)theEObject;
				T result = caseNominalQuality(nominalQuality);
				if (result == null) result = caseQuality(nominalQuality);
				if (result == null) result = caseIntrinsicMomentClass(nominalQuality);
				if (result == null) result = caseMomentClass(nominalQuality);
				if (result == null) result = caseClass(nominalQuality);
				if (result == null) result = caseClassifier(nominalQuality);
				if (result == null) result = caseNamespace(nominalQuality);
				if (result == null) result = caseRedefinableElement(nominalQuality);
				if (result == null) result = caseType(nominalQuality);
				if (result == null) result = casePackageableElement(nominalQuality);
				if (result == null) result = caseNamedElement(nominalQuality);
				if (result == null) result = caseElement(nominalQuality);
				if (result == null) result = caseEModelElement(nominalQuality);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.NON_PERCEIVABLE_QUALITY: {
				NonPerceivableQuality nonPerceivableQuality = (NonPerceivableQuality)theEObject;
				T result = caseNonPerceivableQuality(nonPerceivableQuality);
				if (result == null) result = caseMeasurableQuality(nonPerceivableQuality);
				if (result == null) result = caseQuality(nonPerceivableQuality);
				if (result == null) result = caseIntrinsicMomentClass(nonPerceivableQuality);
				if (result == null) result = caseMomentClass(nonPerceivableQuality);
				if (result == null) result = caseClass(nonPerceivableQuality);
				if (result == null) result = caseClassifier(nonPerceivableQuality);
				if (result == null) result = caseNamespace(nonPerceivableQuality);
				if (result == null) result = caseRedefinableElement(nonPerceivableQuality);
				if (result == null) result = caseType(nonPerceivableQuality);
				if (result == null) result = casePackageableElement(nonPerceivableQuality);
				if (result == null) result = caseNamedElement(nonPerceivableQuality);
				if (result == null) result = caseElement(nonPerceivableQuality);
				if (result == null) result = caseEModelElement(nonPerceivableQuality);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.PERCEIVABLE_QUALITY: {
				PerceivableQuality perceivableQuality = (PerceivableQuality)theEObject;
				T result = casePerceivableQuality(perceivableQuality);
				if (result == null) result = caseMeasurableQuality(perceivableQuality);
				if (result == null) result = caseQuality(perceivableQuality);
				if (result == null) result = caseIntrinsicMomentClass(perceivableQuality);
				if (result == null) result = caseMomentClass(perceivableQuality);
				if (result == null) result = caseClass(perceivableQuality);
				if (result == null) result = caseClassifier(perceivableQuality);
				if (result == null) result = caseNamespace(perceivableQuality);
				if (result == null) result = caseRedefinableElement(perceivableQuality);
				if (result == null) result = caseType(perceivableQuality);
				if (result == null) result = casePackageableElement(perceivableQuality);
				if (result == null) result = caseNamedElement(perceivableQuality);
				if (result == null) result = caseElement(perceivableQuality);
				if (result == null) result = caseEModelElement(perceivableQuality);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.RELATOR: {
				Relator relator = (Relator)theEObject;
				T result = caseRelator(relator);
				if (result == null) result = caseMomentClass(relator);
				if (result == null) result = caseClass(relator);
				if (result == null) result = caseClassifier(relator);
				if (result == null) result = caseNamespace(relator);
				if (result == null) result = caseRedefinableElement(relator);
				if (result == null) result = caseType(relator);
				if (result == null) result = casePackageableElement(relator);
				if (result == null) result = caseNamedElement(relator);
				if (result == null) result = caseElement(relator);
				if (result == null) result = caseEModelElement(relator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.DIRECTED_BINARY_ASSOCIATION: {
				DirectedBinaryAssociation directedBinaryAssociation = (DirectedBinaryAssociation)theEObject;
				T result = caseDirectedBinaryAssociation(directedBinaryAssociation);
				if (result == null) result = caseAssociation(directedBinaryAssociation);
				if (result == null) result = caseClassifier(directedBinaryAssociation);
				if (result == null) result = caseRelationship(directedBinaryAssociation);
				if (result == null) result = caseNamespace(directedBinaryAssociation);
				if (result == null) result = caseRedefinableElement(directedBinaryAssociation);
				if (result == null) result = caseType(directedBinaryAssociation);
				if (result == null) result = casePackageableElement(directedBinaryAssociation);
				if (result == null) result = caseNamedElement(directedBinaryAssociation);
				if (result == null) result = caseElement(directedBinaryAssociation);
				if (result == null) result = caseEModelElement(directedBinaryAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MERONYMIC: {
				Meronymic meronymic = (Meronymic)theEObject;
				T result = caseMeronymic(meronymic);
				if (result == null) result = caseDirectedBinaryAssociation(meronymic);
				if (result == null) result = caseAssociation(meronymic);
				if (result == null) result = caseClassifier(meronymic);
				if (result == null) result = caseRelationship(meronymic);
				if (result == null) result = caseNamespace(meronymic);
				if (result == null) result = caseRedefinableElement(meronymic);
				if (result == null) result = caseType(meronymic);
				if (result == null) result = casePackageableElement(meronymic);
				if (result == null) result = caseNamedElement(meronymic);
				if (result == null) result = caseElement(meronymic);
				if (result == null) result = caseEModelElement(meronymic);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.SUB_QUANTITY_OF: {
				subQuantityOf subQuantityOf = (subQuantityOf)theEObject;
				T result = casesubQuantityOf(subQuantityOf);
				if (result == null) result = caseMeronymic(subQuantityOf);
				if (result == null) result = caseDirectedBinaryAssociation(subQuantityOf);
				if (result == null) result = caseAssociation(subQuantityOf);
				if (result == null) result = caseClassifier(subQuantityOf);
				if (result == null) result = caseRelationship(subQuantityOf);
				if (result == null) result = caseNamespace(subQuantityOf);
				if (result == null) result = caseRedefinableElement(subQuantityOf);
				if (result == null) result = caseType(subQuantityOf);
				if (result == null) result = casePackageableElement(subQuantityOf);
				if (result == null) result = caseNamedElement(subQuantityOf);
				if (result == null) result = caseElement(subQuantityOf);
				if (result == null) result = caseEModelElement(subQuantityOf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.SUB_COLLECTION_OF: {
				subCollectionOf subCollectionOf = (subCollectionOf)theEObject;
				T result = casesubCollectionOf(subCollectionOf);
				if (result == null) result = caseMeronymic(subCollectionOf);
				if (result == null) result = caseDirectedBinaryAssociation(subCollectionOf);
				if (result == null) result = caseAssociation(subCollectionOf);
				if (result == null) result = caseClassifier(subCollectionOf);
				if (result == null) result = caseRelationship(subCollectionOf);
				if (result == null) result = caseNamespace(subCollectionOf);
				if (result == null) result = caseRedefinableElement(subCollectionOf);
				if (result == null) result = caseType(subCollectionOf);
				if (result == null) result = casePackageableElement(subCollectionOf);
				if (result == null) result = caseNamedElement(subCollectionOf);
				if (result == null) result = caseElement(subCollectionOf);
				if (result == null) result = caseEModelElement(subCollectionOf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MEMBER_OF: {
				memberOf memberOf = (memberOf)theEObject;
				T result = casememberOf(memberOf);
				if (result == null) result = caseMeronymic(memberOf);
				if (result == null) result = caseDirectedBinaryAssociation(memberOf);
				if (result == null) result = caseAssociation(memberOf);
				if (result == null) result = caseClassifier(memberOf);
				if (result == null) result = caseRelationship(memberOf);
				if (result == null) result = caseNamespace(memberOf);
				if (result == null) result = caseRedefinableElement(memberOf);
				if (result == null) result = caseType(memberOf);
				if (result == null) result = casePackageableElement(memberOf);
				if (result == null) result = caseNamedElement(memberOf);
				if (result == null) result = caseElement(memberOf);
				if (result == null) result = caseEModelElement(memberOf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.COMPONENT_OF: {
				componentOf componentOf = (componentOf)theEObject;
				T result = casecomponentOf(componentOf);
				if (result == null) result = caseMeronymic(componentOf);
				if (result == null) result = caseDirectedBinaryAssociation(componentOf);
				if (result == null) result = caseAssociation(componentOf);
				if (result == null) result = caseClassifier(componentOf);
				if (result == null) result = caseRelationship(componentOf);
				if (result == null) result = caseNamespace(componentOf);
				if (result == null) result = caseRedefinableElement(componentOf);
				if (result == null) result = caseType(componentOf);
				if (result == null) result = casePackageableElement(componentOf);
				if (result == null) result = caseNamedElement(componentOf);
				if (result == null) result = caseElement(componentOf);
				if (result == null) result = caseEModelElement(componentOf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.DEPENDENCY_RELATIONSHIP: {
				DependencyRelationship dependencyRelationship = (DependencyRelationship)theEObject;
				T result = caseDependencyRelationship(dependencyRelationship);
				if (result == null) result = caseDirectedBinaryAssociation(dependencyRelationship);
				if (result == null) result = caseAssociation(dependencyRelationship);
				if (result == null) result = caseClassifier(dependencyRelationship);
				if (result == null) result = caseRelationship(dependencyRelationship);
				if (result == null) result = caseNamespace(dependencyRelationship);
				if (result == null) result = caseRedefinableElement(dependencyRelationship);
				if (result == null) result = caseType(dependencyRelationship);
				if (result == null) result = casePackageableElement(dependencyRelationship);
				if (result == null) result = caseNamedElement(dependencyRelationship);
				if (result == null) result = caseElement(dependencyRelationship);
				if (result == null) result = caseEModelElement(dependencyRelationship);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.CHARACTERIZATION: {
				Characterization characterization = (Characterization)theEObject;
				T result = caseCharacterization(characterization);
				if (result == null) result = caseDependencyRelationship(characterization);
				if (result == null) result = caseDirectedBinaryAssociation(characterization);
				if (result == null) result = caseAssociation(characterization);
				if (result == null) result = caseClassifier(characterization);
				if (result == null) result = caseRelationship(characterization);
				if (result == null) result = caseNamespace(characterization);
				if (result == null) result = caseRedefinableElement(characterization);
				if (result == null) result = caseType(characterization);
				if (result == null) result = casePackageableElement(characterization);
				if (result == null) result = caseNamedElement(characterization);
				if (result == null) result = caseElement(characterization);
				if (result == null) result = caseEModelElement(characterization);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MEDIATION: {
				Mediation mediation = (Mediation)theEObject;
				T result = caseMediation(mediation);
				if (result == null) result = caseDependencyRelationship(mediation);
				if (result == null) result = caseDirectedBinaryAssociation(mediation);
				if (result == null) result = caseAssociation(mediation);
				if (result == null) result = caseClassifier(mediation);
				if (result == null) result = caseRelationship(mediation);
				if (result == null) result = caseNamespace(mediation);
				if (result == null) result = caseRedefinableElement(mediation);
				if (result == null) result = caseType(mediation);
				if (result == null) result = casePackageableElement(mediation);
				if (result == null) result = caseNamedElement(mediation);
				if (result == null) result = caseElement(mediation);
				if (result == null) result = caseEModelElement(mediation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.DERIVATION: {
				Derivation derivation = (Derivation)theEObject;
				T result = caseDerivation(derivation);
				if (result == null) result = caseDependencyRelationship(derivation);
				if (result == null) result = caseDirectedBinaryAssociation(derivation);
				if (result == null) result = caseAssociation(derivation);
				if (result == null) result = caseClassifier(derivation);
				if (result == null) result = caseRelationship(derivation);
				if (result == null) result = caseNamespace(derivation);
				if (result == null) result = caseRedefinableElement(derivation);
				if (result == null) result = caseType(derivation);
				if (result == null) result = casePackageableElement(derivation);
				if (result == null) result = caseNamedElement(derivation);
				if (result == null) result = caseElement(derivation);
				if (result == null) result = caseEModelElement(derivation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.STRUCTURATION: {
				Structuration structuration = (Structuration)theEObject;
				T result = caseStructuration(structuration);
				if (result == null) result = caseDependencyRelationship(structuration);
				if (result == null) result = caseDirectedBinaryAssociation(structuration);
				if (result == null) result = caseAssociation(structuration);
				if (result == null) result = caseClassifier(structuration);
				if (result == null) result = caseRelationship(structuration);
				if (result == null) result = caseNamespace(structuration);
				if (result == null) result = caseRedefinableElement(structuration);
				if (result == null) result = caseType(structuration);
				if (result == null) result = casePackageableElement(structuration);
				if (result == null) result = caseNamedElement(structuration);
				if (result == null) result = caseElement(structuration);
				if (result == null) result = caseEModelElement(structuration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.FORMAL_ASSOCIATION: {
				FormalAssociation formalAssociation = (FormalAssociation)theEObject;
				T result = caseFormalAssociation(formalAssociation);
				if (result == null) result = caseAssociation(formalAssociation);
				if (result == null) result = caseClassifier(formalAssociation);
				if (result == null) result = caseRelationship(formalAssociation);
				if (result == null) result = caseNamespace(formalAssociation);
				if (result == null) result = caseRedefinableElement(formalAssociation);
				if (result == null) result = caseType(formalAssociation);
				if (result == null) result = casePackageableElement(formalAssociation);
				if (result == null) result = caseNamedElement(formalAssociation);
				if (result == null) result = caseElement(formalAssociation);
				if (result == null) result = caseEModelElement(formalAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RefOntoUMLPackage.MATERIAL_ASSOCIATION: {
				MaterialAssociation materialAssociation = (MaterialAssociation)theEObject;
				T result = caseMaterialAssociation(materialAssociation);
				if (result == null) result = caseAssociation(materialAssociation);
				if (result == null) result = caseClassifier(materialAssociation);
				if (result == null) result = caseRelationship(materialAssociation);
				if (result == null) result = caseNamespace(materialAssociation);
				if (result == null) result = caseRedefinableElement(materialAssociation);
				if (result == null) result = caseType(materialAssociation);
				if (result == null) result = casePackageableElement(materialAssociation);
				if (result == null) result = caseNamedElement(materialAssociation);
				if (result == null) result = caseElement(materialAssociation);
				if (result == null) result = caseEModelElement(materialAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComment(Comment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElement(Element object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackage(RefOntoUML.Package object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Packageable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Packageable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageableElement(PackageableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dependency</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dependency</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependency(Dependency object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Directed Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Directed Relationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDirectedRelationship(DirectedRelationship object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationship(Relationship object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamespace(Namespace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Import</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Import</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElementImport(ElementImport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package Import</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package Import</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageImport(PackageImport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraintx</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraintx</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstraintx(Constraintx object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueSpecification(ValueSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypedElement(TypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseType(Type object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssociation(Association object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Classifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassifier(Classifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Redefinable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Redefinable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRedefinableElement(RedefinableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generalization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generalization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeneralization(Generalization object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generalization Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generalization Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeneralizationSet(GeneralizationSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeature(Feature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Opaque Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Opaque Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOpaqueExpression(OpaqueExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multiplicity Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multiplicity Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultiplicityElement(MultiplicityElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProperty(Property object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClass(RefOntoUML.Class object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModel(Model object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataType(DataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Structural Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Structural Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructuralFeature(StructuralFeature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringExpression(StringExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpression(Expression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package Merge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package Merge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageMerge(PackageMerge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instance Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instance Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstanceSpecification(InstanceSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Slot</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Slot</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSlot(Slot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Literal Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Literal Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLiteralSpecification(LiteralSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumeration(Enumeration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumerationLiteral(EnumerationLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordinal Enumeration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordinal Enumeration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrdinalEnumeration(OrdinalEnumeration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordinal Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordinal Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrdinalLiteral(OrdinalLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveType(PrimitiveType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Structure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Structure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceStructure(ReferenceStructure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nominal Structure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nominal Structure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNominalStructure(NominalStructure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Nominal Structure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Nominal Structure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringNominalStructure(StringNominalStructure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurement Structure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement Structure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurementStructure(MeasurementStructure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurement Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurementDimension(MeasurementDimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordinal Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordinal Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrdinalDimension(OrdinalDimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Ordinal Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Ordinal Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerOrdinalDimension(IntegerOrdinalDimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Decimal Ordinal Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Decimal Ordinal Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDecimalOrdinalDimension(DecimalOrdinalDimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interval Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interval Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntervalDimension(IntervalDimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Interval Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Interval Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerIntervalDimension(IntegerIntervalDimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Decimal Interval Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Decimal Interval Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDecimalIntervalDimension(DecimalIntervalDimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rational Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rational Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRationalDimension(RationalDimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Rational Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Rational Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerRationalDimension(IntegerRationalDimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Decimal Rational Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Decimal Rational Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDecimalRationalDimension(DecimalRationalDimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurement Domain</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement Domain</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurementDomain(MeasurementDomain object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Region</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Region</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceRegion(ReferenceRegion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurement Region</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement Region</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurementRegion(MeasurementRegion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Basic Measurement Region</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Basic Measurement Region</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBasicMeasurementRegion(BasicMeasurementRegion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Decimal Measurement Region</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Decimal Measurement Region</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDecimalMeasurementRegion(DecimalMeasurementRegion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Measurement Region</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Measurement Region</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerMeasurementRegion(IntegerMeasurementRegion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composed Measurement Region</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composed Measurement Region</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComposedMeasurementRegion(ComposedMeasurementRegion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nominal Region</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nominal Region</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNominalRegion(NominalRegion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Nominal Region</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Nominal Region</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringNominalRegion(StringNominalRegion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Literal Integer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Literal Integer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLiteralInteger(LiteralInteger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Literal Decimal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Literal Decimal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLiteralDecimal(LiteralDecimal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Literal String</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Literal String</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLiteralString(LiteralString object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Literal Boolean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Literal Boolean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLiteralBoolean(LiteralBoolean object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Literal Null</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Literal Null</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLiteralNull(LiteralNull object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instance Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instance Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstanceValue(InstanceValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Literal Unlimited Natural</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Literal Unlimited Natural</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLiteralUnlimitedNatural(LiteralUnlimitedNatural object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Object Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObjectClass(ObjectClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Moment Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Moment Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMomentClass(MomentClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sortal Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sortal Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSortalClass(SortalClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mixin Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mixin Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMixinClass(MixinClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rigid Sortal Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rigid Sortal Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRigidSortalClass(RigidSortalClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Anti Rigid Sortal Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Anti Rigid Sortal Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAntiRigidSortalClass(AntiRigidSortalClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Substance Sortal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Substance Sortal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSubstanceSortal(SubstanceSortal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sub Kind</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sub Kind</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSubKind(SubKind object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Kind</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Kind</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseKind(Kind object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quantity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quantity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQuantity(Quantity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collective</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collective</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCollective(Collective object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Phase</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Phase</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePhase(Phase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRole(Role object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rigid Mixin Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rigid Mixin Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRigidMixinClass(RigidMixinClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Non Rigid Mixin Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Non Rigid Mixin Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNonRigidMixinClass(NonRigidMixinClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCategory(Category object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Anti Rigid Mixin Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Anti Rigid Mixin Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAntiRigidMixinClass(AntiRigidMixinClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Semi Rigid Mixin Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Semi Rigid Mixin Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSemiRigidMixinClass(SemiRigidMixinClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Mixin</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Mixin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleMixin(RoleMixin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mixin</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mixin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMixin(Mixin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Intrinsic Moment Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Intrinsic Moment Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntrinsicMomentClass(IntrinsicMomentClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mode</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mode</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMode(Mode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quality</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quality</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQuality(Quality object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurable Quality</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurable Quality</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurableQuality(MeasurableQuality object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nominal Quality</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nominal Quality</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNominalQuality(NominalQuality object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Non Perceivable Quality</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Non Perceivable Quality</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNonPerceivableQuality(NonPerceivableQuality object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Perceivable Quality</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Perceivable Quality</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePerceivableQuality(PerceivableQuality object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelator(Relator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Directed Binary Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Directed Binary Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDirectedBinaryAssociation(DirectedBinaryAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Meronymic</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Meronymic</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeronymic(Meronymic object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>sub Quantity Of</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>sub Quantity Of</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casesubQuantityOf(subQuantityOf object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>sub Collection Of</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>sub Collection Of</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casesubCollectionOf(subCollectionOf object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>member Of</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>member Of</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casememberOf(memberOf object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>component Of</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>component Of</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casecomponentOf(componentOf object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dependency Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dependency Relationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependencyRelationship(DependencyRelationship object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Characterization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Characterization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCharacterization(Characterization object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mediation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mediation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMediation(Mediation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Derivation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Derivation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDerivation(Derivation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Structuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Structuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructuration(Structuration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formal Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formal Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormalAssociation(FormalAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Material Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Material Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMaterialAssociation(MaterialAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EModel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EModel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEModelElement(EModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //RefOntoUMLSwitch
