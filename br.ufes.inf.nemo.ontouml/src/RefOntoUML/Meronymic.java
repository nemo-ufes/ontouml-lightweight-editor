/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Meronymic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Meronymic#isIsShareable <em>Is Shareable</em>}</li>
 *   <li>{@link RefOntoUML.Meronymic#isIsEssential <em>Is Essential</em>}</li>
 *   <li>{@link RefOntoUML.Meronymic#isIsInseparable <em>Is Inseparable</em>}</li>
 *   <li>{@link RefOntoUML.Meronymic#isIsImmutablePart <em>Is Immutable Part</em>}</li>
 *   <li>{@link RefOntoUML.Meronymic#isIsImmutableWhole <em>Is Immutable Whole</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getMeronymic()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='MeronymicConstraint1 MeronymicConstraint2a MeronymicConstraint2b'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL MeronymicConstraint1='Meronymic.allInstances()->select( x | x.whole() = whole() )->collect( y | y.partEnd().lower )->sum() >= 2' MeronymicConstraint2a='whole().oclIsKindOf (AntiRigidSortalClass) or whole().oclIsKindOf (AntiRigidMixinClass) implies not isEssential' MeronymicConstraint2b='true -- isEssential implies isImmutablePart' MeronymicConstraint3='true -- isInseparable implies isImmutableWhole'"
 *        annotation="Comments MeronymicConstraint1='The sum of the minimum cardinalities of the parts must be greater or equal to 2' MeronymicConstraint2a='AntiRigid whole implies that specific part dependence with de re modality is not possible' MeronymicConstraint2b='(Deactivated) Specific part dependence with de re modality implies specific part dependence with de dicto modality' MeronymicConstraint3='(Deactivated) Specific whole dependence with de re modality implies specific whole dependence with de dicto modality'"
 * @generated
 */
public interface Meronymic extends DirectedBinaryAssociation {
	/**
	 * Returns the value of the '<em><b>Is Shareable</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Shareable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Shareable</em>' attribute.
	 * @see #setIsShareable(boolean)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeronymic_IsShareable()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isIsShareable();

	/**
	 * Sets the value of the '{@link RefOntoUML.Meronymic#isIsShareable <em>Is Shareable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Shareable</em>' attribute.
	 * @see #isIsShareable()
	 * @generated
	 */
	void setIsShareable(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Essential</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Essential</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Essential</em>' attribute.
	 * @see #setIsEssential(boolean)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeronymic_IsEssential()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isIsEssential();

	/**
	 * Sets the value of the '{@link RefOntoUML.Meronymic#isIsEssential <em>Is Essential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Essential</em>' attribute.
	 * @see #isIsEssential()
	 * @generated
	 */
	void setIsEssential(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Inseparable</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Inseparable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Inseparable</em>' attribute.
	 * @see #setIsInseparable(boolean)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeronymic_IsInseparable()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isIsInseparable();

	/**
	 * Sets the value of the '{@link RefOntoUML.Meronymic#isIsInseparable <em>Is Inseparable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Inseparable</em>' attribute.
	 * @see #isIsInseparable()
	 * @generated
	 */
	void setIsInseparable(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Immutable Part</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Immutable Part</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Immutable Part</em>' attribute.
	 * @see #setIsImmutablePart(boolean)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeronymic_IsImmutablePart()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isIsImmutablePart();

	/**
	 * Sets the value of the '{@link RefOntoUML.Meronymic#isIsImmutablePart <em>Is Immutable Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Immutable Part</em>' attribute.
	 * @see #isIsImmutablePart()
	 * @generated
	 */
	void setIsImmutablePart(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Immutable Whole</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Immutable Whole</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Immutable Whole</em>' attribute.
	 * @see #setIsImmutableWhole(boolean)
	 * @see RefOntoUML.RefOntoUMLPackage#getMeronymic_IsImmutableWhole()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isIsImmutableWhole();

	/**
	 * Sets the value of the '{@link RefOntoUML.Meronymic#isIsImmutableWhole <em>Is Immutable Whole</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Immutable Whole</em>' attribute.
	 * @see #isIsImmutableWhole()
	 * @generated
	 */
	void setIsImmutableWhole(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='sourceEnd()'"
	 * @generated
	 */
	Property wholeEnd();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='targetEnd()'"
	 * @generated
	 */
	Property partEnd();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='wholeEnd().type'"
	 * @generated
	 */
	Classifier whole();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/ocl/examples/OCL body='partEnd().type'"
	 * @generated
	 */
	Classifier part();

} // Meronymic
