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
 * A representation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A comment is a textual annotation that can be attached to a set of elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.Comment#getBody <em>Body</em>}</li>
 *   <li>{@link RefOntoUML.Comment#getAnnotatedElement <em>Annotated Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getComment()
 * @model
 * @generated
 */
public interface Comment extends Element {
	/**
	 * Returns the value of the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies a string that is the comment.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Body</em>' attribute.
	 * @see #isSetBody()
	 * @see #unsetBody()
	 * @see #setBody(String)
	 * @see RefOntoUML.RefOntoUMLPackage#getComment_Body()
	 * @model unsettable="true" dataType="RefOntoUML.String" ordered="false"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	String getBody();

	/**
	 * Sets the value of the '{@link RefOntoUML.Comment#getBody <em>Body</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' attribute.
	 * @see #isSetBody()
	 * @see #unsetBody()
	 * @see #getBody()
	 * @generated
	 */
	void setBody(String value);

	/**
	 * Unsets the value of the '{@link RefOntoUML.Comment#getBody <em>Body</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBody()
	 * @see #getBody()
	 * @see #setBody(String)
	 * @generated
	 */
	void unsetBody();

	/**
	 * Returns whether the value of the '{@link RefOntoUML.Comment#getBody <em>Body</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Body</em>' attribute is set.
	 * @see #unsetBody()
	 * @see #getBody()
	 * @see #setBody(String)
	 * @generated
	 */
	boolean isSetBody();

	/**
	 * Returns the value of the '<em><b>Annotated Element</b></em>' reference list.
	 * The list contents are of type {@link RefOntoUML.Element}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the Element(s) being commented.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Annotated Element</em>' reference list.
	 * @see RefOntoUML.RefOntoUMLPackage#getComment_AnnotatedElement()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Element> getAnnotatedElement();

} // Comment
