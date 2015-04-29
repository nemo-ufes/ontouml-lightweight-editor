/**
 */
package sml2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Situation Type Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sml2.SituationTypeBlock#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see sml2.Sml2Package#getSituationTypeBlock()
 * @model
 * @generated
 */
public interface SituationTypeBlock extends Node {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link sml2.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see sml2.Sml2Package#getSituationTypeBlock_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Node> getElements();

} // SituationTypeBlock
