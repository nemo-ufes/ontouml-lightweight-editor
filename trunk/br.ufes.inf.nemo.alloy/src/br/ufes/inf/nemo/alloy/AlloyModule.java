/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.AlloyModule#getName <em>Name</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.AlloyModule#getPath <em>Path</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.AlloyModule#getParameters <em>Parameters</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.AlloyModule#getParagraph <em>Paragraph</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.AlloyModule#getImports <em>Imports</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getAlloyModule()
 * @model
 * @generated
 */
public interface AlloyModule extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getAlloyModule_Name()
	 * @model id="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.AlloyModule#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getAlloyModule_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.alloy.AlloyModule#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link br.ufes.inf.nemo.alloy.SignatureParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getAlloyModule_Parameters()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<SignatureParameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Paragraph</b></em>' containment reference list.
	 * The list contents are of type {@link br.ufes.inf.nemo.alloy.Paragraph}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Paragraph</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Paragraph</em>' containment reference list.
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getAlloyModule_Paragraph()
	 * @model containment="true"
	 * @generated
	 */
	EList<Paragraph> getParagraph();

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link br.ufes.inf.nemo.alloy.ModuleImportation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference list.
	 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getAlloyModule_Imports()
	 * @model containment="true"
	 * @generated
	 */
	EList<ModuleImportation> getImports();

} // AlloyModule
