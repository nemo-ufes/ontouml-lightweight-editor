/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Import</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An element import identifies an element in another package, and allows the element to be referenced using its name without a qualifier.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.ElementImport#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link RefOntoUML.ElementImport#getAlias <em>Alias</em>}</li>
 *   <li>{@link RefOntoUML.ElementImport#getImportedElement <em>Imported Element</em>}</li>
 *   <li>{@link RefOntoUML.ElementImport#getImportingNamespace <em>Importing Namespace</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getElementImport()
 * @model
 * @generated
 */
public interface ElementImport extends DirectedRelationship {
	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' attribute.
	 * The default value is <code>"public"</code>.
	 * The literals are from the enumeration {@link RefOntoUML.VisibilityKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the visibility of the imported PackageableElement within the importing Package. The default visibility is the same as that of the imported element. If the imported element does not have a visibility, it is possible to add visibility to the element import.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Visibility</em>' attribute.
	 * @see RefOntoUML.VisibilityKind
	 * @see #setVisibility(VisibilityKind)
	 * @see RefOntoUML.RefOntoUMLPackage#getElementImport_Visibility()
	 * @model default="public" required="true" ordered="false"
	 * @generated
	 */
	VisibilityKind getVisibility();

	/**
	 * Sets the value of the '{@link RefOntoUML.ElementImport#getVisibility <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' attribute.
	 * @see RefOntoUML.VisibilityKind
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(VisibilityKind value);

	/**
	 * Returns the value of the '<em><b>Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the name that should be added to the namespace of the importing package in lieu of the name of the imported packagable element. The aliased name must not clash with any other member name in the importing package. By default, no alias is used.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Alias</em>' attribute.
	 * @see #isSetAlias()
	 * @see #unsetAlias()
	 * @see #setAlias(String)
	 * @see RefOntoUML.RefOntoUMLPackage#getElementImport_Alias()
	 * @model unsettable="true" dataType="RefOntoUML.String" ordered="false"
	 * @generated
	 */
	String getAlias();

	/**
	 * Sets the value of the '{@link RefOntoUML.ElementImport#getAlias <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alias</em>' attribute.
	 * @see #isSetAlias()
	 * @see #unsetAlias()
	 * @see #getAlias()
	 * @generated
	 */
	void setAlias(String value);

	/**
	 * Unsets the value of the '{@link RefOntoUML.ElementImport#getAlias <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAlias()
	 * @see #getAlias()
	 * @see #setAlias(String)
	 * @generated
	 */
	void unsetAlias();

	/**
	 * Returns whether the value of the '{@link RefOntoUML.ElementImport#getAlias <em>Alias</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Alias</em>' attribute is set.
	 * @see #unsetAlias()
	 * @see #getAlias()
	 * @see #setAlias(String)
	 * @generated
	 */
	boolean isSetAlias();

	/**
	 * Returns the value of the '<em><b>Imported Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the PackageableElement whose name is to be added to a Namespace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Imported Element</em>' reference.
	 * @see #setImportedElement(PackageableElement)
	 * @see RefOntoUML.RefOntoUMLPackage#getElementImport_ImportedElement()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	PackageableElement getImportedElement();

	/**
	 * Sets the value of the '{@link RefOntoUML.ElementImport#getImportedElement <em>Imported Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Imported Element</em>' reference.
	 * @see #getImportedElement()
	 * @generated
	 */
	void setImportedElement(PackageableElement value);

	/**
	 * Returns the value of the '<em><b>Importing Namespace</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Namespace#getElementImport <em>Element Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the Namespace that imports a PackageableElement from another Package.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Importing Namespace</em>' container reference.
	 * @see #setImportingNamespace(Namespace)
	 * @see RefOntoUML.RefOntoUMLPackage#getElementImport_ImportingNamespace()
	 * @see RefOntoUML.Namespace#getElementImport
	 * @model opposite="elementImport" required="true" transient="false" ordered="false"
	 * @generated
	 */
	Namespace getImportingNamespace();

	/**
	 * Sets the value of the '{@link RefOntoUML.ElementImport#getImportingNamespace <em>Importing Namespace</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Importing Namespace</em>' container reference.
	 * @see #getImportingNamespace()
	 * @generated
	 */
	void setImportingNamespace(Namespace value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The visibility of an ElementImport is either public or private.
	 * self.visibility = #public or self.visibility = #private
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean visibility_public_or_private(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An importedElement has either public visibility or no visibility at all.
	 * self.importedElement.visibility.notEmpty() implies self.importedElement.visibility = #public
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean imported_element_is_public(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query getName() returns the name under which the imported PackageableElement will be known in the importing namespace.
	 * result = if self.alias->notEmpty() then
	 *   self.alias
	 * else
	 *   self.importedElement.name
	 * endif
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="RefOntoUML.String" required="true" ordered="false"
	 * @generated
	 */
	String getName();

} // ElementImport
