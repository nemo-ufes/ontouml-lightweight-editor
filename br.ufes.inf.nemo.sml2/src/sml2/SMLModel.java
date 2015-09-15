/**
 */
package sml2;

import RefOntoUML.Model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SML Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.SMLModel#getElements <em>Elements</em>}</li>
 *   <li>{@link sml2.SMLModel#getContextModel <em>Context Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getSMLModel()
 * @model
 * @generated
 */
public interface SMLModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link sml2.SituationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see sml2.Sml2Package#getSMLModel_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<SituationType> getElements();

	/**
	 * Returns the value of the '<em><b>Context Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context Model</em>' reference.
	 * @see #setContextModel(Model)
	 * @see sml2.Sml2Package#getSMLModel_ContextModel()
	 * @model
	 * @generated
	 */
	Model getContextModel();

	/**
	 * Sets the value of the '{@link sml2.SMLModel#getContextModel <em>Context Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context Model</em>' reference.
	 * @see #getContextModel()
	 * @generated
	 */
	void setContextModel(Model value);

} // SMLModel
