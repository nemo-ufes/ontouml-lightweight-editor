/**
 */
package sml2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reflected Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.ReflectedParticipant#getParticipant <em>Participant</em>}</li>
 *   <li>{@link sml2.ReflectedParticipant#getReferences <em>References</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getReflectedParticipant()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='onlyMultipleReflected'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL onlyMultipleReflected='self.participant.lowerBound > 1 or self.participant.upperBound > 1'"
 *        annotation="Comments onlyMultipleReflected='Only Participants with minimum or maximum greather than 1 can be reflected'"
 * @generated
 */
public interface ReflectedParticipant extends Node, SituationTypeElement {
	/**
	 * Returns the value of the '<em><b>Participant</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link sml2.Participant#getReflection <em>Reflection</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant</em>' reference.
	 * @see #setParticipant(Participant)
	 * @see sml2.Sml2Package#getReflectedParticipant_Participant()
	 * @see sml2.Participant#getReflection
	 * @model opposite="reflection" required="true"
	 * @generated
	 */
	Participant getParticipant();

	/**
	 * Sets the value of the '{@link sml2.ReflectedParticipant#getParticipant <em>Participant</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participant</em>' reference.
	 * @see #getParticipant()
	 * @generated
	 */
	void setParticipant(Participant value);

	/**
	 * Returns the value of the '<em><b>References</b></em>' containment reference list.
	 * The list contents are of type {@link sml2.ReflectedReference}.
	 * It is bidirectional and its opposite is '{@link sml2.ReflectedReference#getOwningReflection <em>Owning Reflection</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>References</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>References</em>' containment reference list.
	 * @see sml2.Sml2Package#getReflectedParticipant_References()
	 * @see sml2.ReflectedReference#getOwningReflection
	 * @model opposite="owningReflection" containment="true"
	 * @generated
	 */
	EList<ReflectedReference> getReferences();

} // ReflectedParticipant
