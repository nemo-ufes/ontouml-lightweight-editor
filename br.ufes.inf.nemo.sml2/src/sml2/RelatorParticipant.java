/**
 */
package sml2;

import RefOntoUML.Relator;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relator Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.RelatorParticipant#getIsOfType <em>Is Of Type</em>}</li>
 *   <li>{@link sml2.RelatorParticipant#getLinks <em>Links</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getRelatorParticipant()
 * @model
 * @generated
 */
public interface RelatorParticipant extends Participant {
	/**
	 * Returns the value of the '<em><b>Is Of Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Of Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Of Type</em>' reference.
	 * @see #setIsOfType(Relator)
	 * @see sml2.Sml2Package#getRelatorParticipant_IsOfType()
	 * @model
	 * @generated
	 */
	Relator getIsOfType();

	/**
	 * Sets the value of the '{@link sml2.RelatorParticipant#getIsOfType <em>Is Of Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Of Type</em>' reference.
	 * @see #getIsOfType()
	 * @generated
	 */
	void setIsOfType(Relator value);

	/**
	 * Returns the value of the '<em><b>Links</b></em>' reference list.
	 * The list contents are of type {@link sml2.Link}.
	 * It is bidirectional and its opposite is '{@link sml2.Link#getRelator <em>Relator</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Links</em>' reference list.
	 * @see sml2.Sml2Package#getRelatorParticipant_Links()
	 * @see sml2.Link#getRelator
	 * @model opposite="relator"
	 * @generated
	 */
	EList<Link> getLinks();

} // RelatorParticipant
