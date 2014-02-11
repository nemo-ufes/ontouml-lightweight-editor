/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collective</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Collective#isIsExtensional <em>Is Extensional</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getCollective()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='CollectiveConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL CollectiveConstraint1='isExtensional implies Meronymic.allInstances()->forAll( x | x.whole() = self implies x.isEssential )'"
 *        annotation="Comments CollectiveConstraint1='All the parts of an extensional Collective are essential'"
 * @generated
 */
public interface Collective extends SubstanceSortal {
	/**
	 * Returns the value of the '<em><b>Is Extensional</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Extensional</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Extensional</em>' attribute.
	 * @see #setIsExtensional(boolean)
	 * @see RefOntoUML.RefOntoUMLPackage#getCollective_IsExtensional()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isIsExtensional();

	/**
	 * Sets the value of the '{@link RefOntoUML.Collective#isIsExtensional <em>Is Extensional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Extensional</em>' attribute.
	 * @see #isIsExtensional()
	 * @generated
	 */
	void setIsExtensional(boolean value);

} // Collective
