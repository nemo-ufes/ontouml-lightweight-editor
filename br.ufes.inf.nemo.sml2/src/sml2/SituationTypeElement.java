/**
 */
package sml2;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Situation Type Element</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see sml2.Sml2Package#getSituationTypeElement()
 * @model abstract="true"
 * @generated
 */
public interface SituationTypeElement extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/ocl/examples/OCL body='SituationType.allInstances()->select(x | x.elements->exists(y | y = self))->asOrderedSet()->at(1)'"
	 *        annotation="Comments situation='Gets the SituationType that contains the SituationTypeElement'"
	 * @generated
	 */
	SituationType getSituation();

} // SituationTypeElement
