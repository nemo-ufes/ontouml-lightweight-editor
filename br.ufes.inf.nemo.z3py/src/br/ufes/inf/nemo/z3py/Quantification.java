/**
 */
package br.ufes.inf.nemo.z3py;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quantification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.Quantification#getExpression <em>Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.Quantification#getQuantifiesOver <em>Quantifies Over</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.Quantification#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getQuantification()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='IrreflexiveQuant'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot IrreflexiveQuant='self.expression <> self'"
 * @generated
 */
public interface Quantification extends Expression {
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' reference.
	 * @see #setExpression(Expression)
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getQuantification_Expression()
	 * @model required="true"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.z3py.Quantification#getExpression <em>Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Quantifies Over</b></em>' reference list.
	 * The list contents are of type {@link br.ufes.inf.nemo.z3py.IntConstant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quantifies Over</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantifies Over</em>' reference list.
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getQuantification_QuantifiesOver()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EList<IntConstant> getQuantifiesOver();

	/**
	 * Returns the value of the '<em><b>Comments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments</em>' attribute.
	 * @see #setComments(String)
	 * @see br.ufes.inf.nemo.z3py.Z3pyPackage#getQuantification_Comments()
	 * @model
	 * @generated
	 */
	String getComments();

	/**
	 * Sets the value of the '{@link br.ufes.inf.nemo.z3py.Quantification#getComments <em>Comments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comments</em>' attribute.
	 * @see #getComments()
	 * @generated
	 */
	void setComments(String value);

} // Quantification
