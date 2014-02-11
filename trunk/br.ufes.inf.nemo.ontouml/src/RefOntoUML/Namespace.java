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
 * A representation of the model object '<em><b>Namespace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A namespace is an element in a model that contains a set of named elements that can be identified by name.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Namespace#getElementImport <em>Element Import</em>}</li>
 *   <li>{@link RefOntoUML.Namespace#getPackageImport <em>Package Import</em>}</li>
 *   <li>{@link RefOntoUML.Namespace#getOwnedRule <em>Owned Rule</em>}</li>
 *   <li>{@link RefOntoUML.Namespace#getMember <em>Member</em>}</li>
 *   <li>{@link RefOntoUML.Namespace#getImportedMember <em>Imported Member</em>}</li>
 *   <li>{@link RefOntoUML.Namespace#getOwnedMember <em>Owned Member</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getNamespace()
 * @model abstract="true"
 * @generated
 */
public interface Namespace extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Element Import</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.ElementImport}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.ElementImport#getImportingNamespace <em>Importing Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the ElementImports owned by the Namespace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element Import</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getNamespace_ElementImport()
	 * @see RefOntoUML.ElementImport#getImportingNamespace
	 * @model opposite="importingNamespace" containment="true" ordered="false"
	 * @generated
	 */
	EList<ElementImport> getElementImport();

	/**
	 * Returns the value of the '<em><b>Package Import</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.PackageImport}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.PackageImport#getImportingNamespace <em>Importing Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the PackageImports owned by the Namespace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Package Import</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getNamespace_PackageImport()
	 * @see RefOntoUML.PackageImport#getImportingNamespace
	 * @model opposite="importingNamespace" containment="true" ordered="false"
	 * @generated
	 */
	EList<PackageImport> getPackageImport();

	/**
	 * Returns the value of the '<em><b>Owned Rule</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.Constraintx}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Constraintx#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies a set of Constraints owned by this Namespace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Rule</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getNamespace_OwnedRule()
	 * @see RefOntoUML.Constraintx#getContext
	 * @model opposite="context" containment="true" ordered="false"
	 * @generated
	 */
	EList<Constraintx> getOwnedRule();

	/**
	 * Returns the value of the '<em><b>Member</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.NamedElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A collection of NamedElements identifiable within the Namespace, either by being owned or by being introduced by importing or inheritance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Member</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getNamespace_Member()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='if oclIsKindOf(Association) then\r\n\toclAsType(Association).memberEnd\r\nelse\r\n\tif oclIsKindOf(Classifier) then\r\n\t\toclAsType(Classifier).feature.oclAsType(NamedElement)->union(oclAsType(Classifier).inheritedMember)\r\n\telse\r\n\t\tSet {}\r\n\tendif\r\nendif'"
	 * @generated
	 */
	EList<NamedElement> getMember();

	/**
	 * Returns the value of the '<em><b>Imported Member</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.PackageableElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the PackageableElements that are members of this Namespace as a result of either PackageImports or ElementImports.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Imported Member</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getNamespace_ImportedMember()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='Set {}'"
	 * @generated
	 */
	EList<PackageableElement> getImportedMember();

	/**
	 * Returns the value of the '<em><b>Owned Member</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.NamedElement}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.NamedElement#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A collection of NamedElements owned by the Namespace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Member</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getNamespace_OwnedMember()
	 * @see RefOntoUML.NamedElement#getNamespace
	 * @model opposite="namespace" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL derive='if oclIsKindOf(Class) then\r\n\toclAsType(Class).ownedAttribute\r\nelse\r\n\tif oclIsKindOf(DataType) then\r\n\t\toclAsType(DataType).ownedAttribute\t\r\n\telse\r\n\t\tif oclIsKindOf(Association) then\r\n\t\t\toclAsType(Association).ownedEnd\r\n\t\telse\r\n\t\t\tSet {}\r\n\t\tendif\r\n\tendif\r\nendif\r\n'"
	 * @generated
	 */
	EList<NamedElement> getOwnedMember();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * All the members of a Namespace are distinguishable within it.
	 * membersAreDistinguishable()
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean members_distinguishable(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates an import of the specified element into this namespace with the specified visibility.
	 * @param element The element to import.
	 * @param visibility The visibility for the new element import.
	 * <!-- end-model-doc -->
	 * @model required="true" ordered="false" elementRequired="true" elementOrdered="false" visibilityRequired="true" visibilityOrdered="false"
	 * @generated
	 */
	ElementImport createElementImport(PackageableElement element, VisibilityKind visibility);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates an import of the specified package into this namespace with the specified visibility.
	 * @param package_ The package to import.
	 * @param visibility The visibility for the new package import.
	 * <!-- end-model-doc -->
	 * @model required="true" ordered="false" package_Required="true" package_Ordered="false" visibilityRequired="true" visibilityOrdered="false"
	 * @generated
	 */
	PackageImport createPackageImport(RefOntoUML.Package package_, VisibilityKind visibility);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the elements imported by this namespace.
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<PackageableElement> getImportedElements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Retrieves the packages imported by this namespace.
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<RefOntoUML.Package> getImportedPackages();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The importedMember property is derived from the ElementImports and the PackageImports. References the PackageableElements that are members of this Namespace as a result of either PackageImports or ElementImports.
	 * result = self.importMembers(self.elementImport.importedElement.asSet()-
	 * >union(self.packageImport.importedPackage->collect(p | p.visibleMembers())))
	 * <!-- end-model-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<PackageableElement> getImportedMembers();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query getNamesOfMember() takes importing into account. It gives back the set of names that an element would have in an importing namespace, either because it is owned, or if not owned then imported individually, or if not individually then from a package.
	 * The query getNamesOfMember() gives a set of all of the names that a member would have in a Namespace. In general a member can have multiple names in a Namespace if it is imported more than once with different aliases. The query takes account of importing. It gives back the set of names that an element would have in an importing namespace, either because it is owned, or if not owned then imported individually, or if not individually then from a package.
	 * result = if self.ownedMember ->includes(element)
	 * then Set{}->include(element.name)
	 * else let elementImports: ElementImport = self.elementImport->select(ei | ei.importedElement = element) in
	 *   if elementImports->notEmpty()
	 *   then elementImports->collect(el | el.getName())
	 *   else self.packageImport->select(pi | pi.importedPackage.visibleMembers()->includes(element))-> collect(pi | pi.importedPackage.getNamesOfMember(element))
	 *   endif
	 * endif
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.String" ordered="false" elementRequired="true" elementOrdered="false"
	 * @generated
	 */
	EList<String> getNamesOfMember(NamedElement element);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Boolean query membersAreDistinguishable() determines whether all of the namespace's members are distinguishable within it.
	 * result = self.member->forAll( memb |
	 * self.member->excluding(memb)->forAll(other |
	 * memb.isDistinguishableFrom(other, self)))
	 * <!-- end-model-doc -->
	 * @model dataType="RefOntoUML.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean membersAreDistinguishable();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query importMembers() defines which of a set of PackageableElements are actually imported into the namespace. This excludes hidden ones, i.e., those which have names that conflict with names of owned members, and also excludes elements which would have the same name when imported.
	 * result = self.excludeCollisions(imps)->select(imp | self.ownedMember->forAll(mem |
	 * mem.imp.isDistinguishableFrom(mem, self)))
	 * <!-- end-model-doc -->
	 * @model ordered="false" impsMany="true" impsOrdered="false"
	 * @generated
	 */
	EList<PackageableElement> importMembers(EList<PackageableElement> imps);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query excludeCollisions() excludes from a set of PackageableElements any that would not be distinguishable from each other in this namespace.
	 * result = imps->reject(imp1 | imps.exists(imp2 | not imp1.isDistinguishableFrom(imp2, self)))
	 * <!-- end-model-doc -->
	 * @model ordered="false" impsMany="true" impsOrdered="false"
	 * @generated
	 */
	EList<PackageableElement> excludeCollisions(EList<PackageableElement> imps);

} // Namespace
