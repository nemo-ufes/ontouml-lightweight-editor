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
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A package is used to group elements, and provides a namespace for the grouped elements.
 * A package can have one or more profile applications to indicate which profiles have been applied. Because a profile is a package, it is possible to apply a profile not only to packages, but also to profiles.
 * Package specializes TemplateableElement and PackageableElement specializes ParameterableElement to specify that a package can be used as a template and a PackageableElement as a template parameter.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Package#getOwnedType <em>Owned Type</em>}</li>
 *   <li>{@link RefOntoUML.Package#getPackageMerge <em>Package Merge</em>}</li>
 *   <li>{@link RefOntoUML.Package#getPackagedElement <em>Packaged Element</em>}</li>
 *   <li>{@link RefOntoUML.Package#getNestedPackage <em>Nested Package</em>}</li>
 *   <li>{@link RefOntoUML.Package#getNestingPackage <em>Nesting Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getPackage()
 * @model
 * @generated
 */
public interface Package extends Namespace, PackageableElement {
	/**
	 * Returns the value of the '<em><b>Owned Type</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Type}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Type#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the packaged elements that are Types.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Type</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getPackage_OwnedType()
	 * @see RefOntoUML.Type#getPackage
	 * @model opposite="package" transient="true" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='Set {}'"
	 * @generated
	 */
	EList<Type> getOwnedType();

	/**
	 * Returns the value of the '<em><b>Package Merge</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.PackageMerge}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.PackageMerge#getReceivingPackage <em>Receiving Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the PackageMerges that are owned by this Package.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Package Merge</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getPackage_PackageMerge()
	 * @see RefOntoUML.PackageMerge#getReceivingPackage
	 * @model opposite="receivingPackage" containment="true" ordered="false"
	 * @generated
	 */
	EList<PackageMerge> getPackageMerge();

	/**
	 * Returns the value of the '<em><b>Packaged Element</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.PackageableElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the packageable elements that are owned by this Package.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Packaged Element</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getPackage_PackagedElement()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<PackageableElement> getPackagedElement();

	/**
	 * Returns the value of the '<em><b>Nested Package</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Package}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Package#getNestingPackage <em>Nesting Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the packaged elements that are Packages.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Nested Package</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getPackage_NestedPackage()
	 * @see RefOntoUML.Package#getNestingPackage
	 * @model opposite="nestingPackage" transient="true" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='Set {}'"
	 * @generated
	 */
	EList<Package> getNestedPackage();

	/**
	 * Returns the value of the '<em><b>Nesting Package</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Package#getNestedPackage <em>Nested Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the Package that owns this Package.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Nesting Package</em>' reference.
	 * @see #setNestingPackage(Package)
	 * @see RefOntoUML.RefOntoUMLPackage#getPackage_NestingPackage()
	 * @see RefOntoUML.Package#getNestedPackage
	 * @model opposite="nestedPackage" transient="true" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='null'"
	 * @generated
	 */
	Package getNestingPackage();

	/**
	 * Sets the value of the '{@link RefOntoUML.Package#getNestingPackage <em>Nesting Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nesting Package</em>' reference.
	 * @see #getNestingPackage()
	 * @generated
	 */
	void setNestingPackage(Package value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If an element that is owned by a package has visibility, it is public or private.
	 * self.ownedElements->forAll(e | e.visibility->notEmpty() implies e.visbility = #public or e.visibility = #private)
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean elements_public_or_private(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates a(n) (abstract) class with the specified name as an owned type of this package.
	 * @param name The name for the new class, or null.
	 * @param isAbstract Whether the new class should be abstract.
	 * <!-- end-model-doc -->
	 * @model required="true" ordered="false" nameDataType="RefOntoUML.String" nameRequired="true" nameOrdered="false" isAbstractDataType="RefOntoUML.Boolean" isAbstractRequired="true" isAbstractOrdered="false"
	 * @generated
	 */
	RefOntoUML.Class createOwnedClass(String name, boolean isAbstract);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates a enumeration with the specified name as an owned type of this package.
	 * @param name The name for the new enumeration, or null.
	 * <!-- end-model-doc -->
	 * @model required="true" ordered="false" nameDataType="RefOntoUML.String" nameRequired="true" nameOrdered="false"
	 * @generated
	 */
	Enumeration createOwnedEnumeration(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates a primitive type with the specified name as an owned type of this package.
	 * @param name The name for the new primitive type, or null.
	 * <!-- end-model-doc -->
	 * @model required="true" ordered="false" nameDataType="RefOntoUML.String" nameRequired="true" nameOrdered="false"
	 * @generated
	 */
	PrimitiveType createOwnedPrimitiveType(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates an interface with the specified name as an owned type of this package.
	 * @param name The name for the new interface, or null.
	 * <!-- end-model-doc -->
	 * @model nameDataType="RefOntoUML.String" nameRequired="true" nameOrdered="false"
	 * @generated
	 */
	void createOwnedInterface(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the profile with the specified qualified name that is applied to this package, or null if no such profile is applied.
	 * @param qualifiedName The qualified name of the applied profile to retrieve.
	 * <!-- end-model-doc -->
	 * @model qualifiedNameDataType="RefOntoUML.String" qualifiedNameRequired="true" qualifiedNameOrdered="false"
	 * @generated
	 */
	void getAppliedProfile(String qualifiedName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the profile with the specified qualified name that is applied to this package or any of its nesting packages (if indicated), or null if no such profile is applied.
	 * @param qualifiedName The qualified name of the applied profile to retrieve.
	 * @param recurse Whether to look in nesting packages.
	 * <!-- end-model-doc -->
	 * @model qualifiedNameDataType="RefOntoUML.String" qualifiedNameRequired="true" qualifiedNameOrdered="false" recurseDataType="RefOntoUML.Boolean" recurseRequired="true" recurseOrdered="false"
	 * @generated
	 */
	void getAppliedProfile(String qualifiedName, boolean recurse);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Determines whether this package is a model library.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isModelLibrary();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query visibleMembers() defines which members of a Package can be accessed outside it.
	 * result = member->select( m | self.makesVisible(m))
	 * <!-- end-model-doc -->
	 * @model ordered="false"
	 * @generated
	 */
	EList<PackageableElement> visibleMembers();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query makesVisible() defines whether a Package makes an element visible outside itself. Elements with no visibility and elements with public visibility are made visible.
	 * self.member->includes(el)
	 * result = (ownedMember->includes(el)) or
	 * (elementImport->select(ei|ei.importedElement = #public)->collect(ei|ei.importedElement)->includes(el)) or
	 * (packageImport->select(pi|pi.visibility = #public)->collect(pi|pi.importedPackage.member->includes(el))->notEmpty())
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.Boolean" required="true" ordered="false" elRequired="true" elOrdered="false"
	 * @generated
	 */
	boolean makesVisible(NamedElement el);

} // Package
