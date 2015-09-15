/**
 */
package sml2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.Participant#isShareable <em>Shareable</em>}</li>
 *   <li>{@link sml2.Participant#isImmutable <em>Immutable</em>}</li>
 *   <li>{@link sml2.Participant#getMin <em>Min</em>}</li>
 *   <li>{@link sml2.Participant#getReflection <em>Reflection</em>}</li>
 *   <li>{@link sml2.Participant#getMax <em>Max</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getParticipant()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='maxGreatherThanMin moreThanOneMustReflect'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL maxGreatherThanMin='if (self.upperBound <> -1) then self.upperBound >= self.lowerBound else self.lowerBound <> -1 endif' moreThanOneMustReflect='if (self.lowerBound > 1 or self.upperBound > 1) then self.reflection->notEmpty() else true endif'"
 *        annotation="Comments maxGreatherThanMin='The maximum instances number of a Participant must be greather than or equal the minimum' moreThanOneMustReflect='A Participant that has a minimum greather than 1 or a maximum greather than 1 must be reflected, so that a constraint can be applied to the extra instances'"
 * @generated
 */
public interface Participant extends ReferableElement {
	/**
	 * Returns the value of the '<em><b>Shareable</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shareable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shareable</em>' attribute.
	 * @see #setShareable(boolean)
	 * @see sml2.Sml2Package#getParticipant_Shareable()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isShareable();

	/**
	 * Sets the value of the '{@link sml2.Participant#isShareable <em>Shareable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shareable</em>' attribute.
	 * @see #isShareable()
	 * @generated
	 */
	void setShareable(boolean value);

	/**
	 * Returns the value of the '<em><b>Immutable</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Immutable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Immutable</em>' attribute.
	 * @see #setImmutable(boolean)
	 * @see sml2.Sml2Package#getParticipant_Immutable()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isImmutable();

	/**
	 * Sets the value of the '{@link sml2.Participant#isImmutable <em>Immutable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Immutable</em>' attribute.
	 * @see #isImmutable()
	 * @generated
	 */
	void setImmutable(boolean value);

	/**
	 * Returns the value of the '<em><b>Min</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min</em>' attribute.
	 * @see #setMin(int)
	 * @see sml2.Sml2Package#getParticipant_Min()
	 * @model default="1" required="true"
	 * @generated
	 */
	int getMin();

	/**
	 * Sets the value of the '{@link sml2.Participant#getMin <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min</em>' attribute.
	 * @see #getMin()
	 * @generated
	 */
	void setMin(int value);

	/**
	 * Returns the value of the '<em><b>Reflection</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link sml2.ReflectedParticipant#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reflection</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reflection</em>' reference.
	 * @see #setReflection(ReflectedParticipant)
	 * @see sml2.Sml2Package#getParticipant_Reflection()
	 * @see sml2.ReflectedParticipant#getParticipant
	 * @model opposite="participant"
	 * @generated
	 */
	ReflectedParticipant getReflection();

	/**
	 * Sets the value of the '{@link sml2.Participant#getReflection <em>Reflection</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reflection</em>' reference.
	 * @see #getReflection()
	 * @generated
	 */
	void setReflection(ReflectedParticipant value);

	/**
	 * Returns the value of the '<em><b>Max</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max</em>' attribute.
	 * @see #setMax(int)
	 * @see sml2.Sml2Package#getParticipant_Max()
	 * @model default="1" required="true"
	 * @generated
	 */
	int getMax();

	/**
	 * Sets the value of the '{@link sml2.Participant#getMax <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max</em>' attribute.
	 * @see #getMax()
	 * @generated
	 */
	void setMax(int value);

} // Participant
