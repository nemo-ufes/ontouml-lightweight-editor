/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An enumeration is a data type whose values are enumerated in the model as enumeration literals.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Enumeration#getOwnedLiteral <em>Owned Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getEnumeration()
 * @model
 * @generated
 */
public interface Enumeration extends DataType {
	/**
	 * Returns the value of the '<em><b>Owned Literal</b></em>' containment reference list.
	 * The list contents are of type {@link RefOntoUML.EnumerationLiteral}.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.EnumerationLiteral#getEnumeration <em>Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The ordered set of literals for this Enumeration.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Literal</em>' containment reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getEnumeration_OwnedLiteral()
	 * @see RefOntoUML.EnumerationLiteral#getEnumeration
	 * @model opposite="enumeration" containment="true"
	 * @generated
	 */
	EList<EnumerationLiteral> getOwnedLiteral();

} // Enumeration
