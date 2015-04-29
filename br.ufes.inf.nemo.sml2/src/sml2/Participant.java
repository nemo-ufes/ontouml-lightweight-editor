/**
 */
package sml2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.Participant#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getParticipant()
 * @model abstract="true"
 * @generated
 */
public interface Participant extends ExportableNode {
	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference list.
	 * The list contents are of type {@link sml2.AttributeReference}.
	 * It is bidirectional and its opposite is '{@link sml2.AttributeReference#getEntity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference list.
	 * @see sml2.Sml2Package#getParticipant_Reference()
	 * @see sml2.AttributeReference#getEntity
	 * @model opposite="entity"
	 * @generated
	 */
	EList<AttributeReference> getReference();

} // Participant
