/**
 */
package sml2;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see sml2.Sml2Package
 * @generated
 */
public interface Sml2Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Sml2Factory eINSTANCE = sml2.impl.Sml2FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Allen Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Allen Link</em>'.
	 * @generated
	 */
	AllenLink createAllenLink();

	/**
	 * Returns a new object of class '<em>Attribute Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute Link</em>'.
	 * @generated
	 */
	AttributeLink createAttributeLink();

	/**
	 * Returns a new object of class '<em>Attribute Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute Reference</em>'.
	 * @generated
	 */
	AttributeReference createAttributeReference();

	/**
	 * Returns a new object of class '<em>Characterization Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Characterization Link</em>'.
	 * @generated
	 */
	CharacterizationLink createCharacterizationLink();

	/**
	 * Returns a new object of class '<em>Context Formal Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Context Formal Link</em>'.
	 * @generated
	 */
	ContextFormalLink createContextFormalLink();

	/**
	 * Returns a new object of class '<em>Entity Participant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entity Participant</em>'.
	 * @generated
	 */
	EntityParticipant createEntityParticipant();

	/**
	 * Returns a new object of class '<em>Equals Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Equals Link</em>'.
	 * @generated
	 */
	EqualsLink createEqualsLink();

	/**
	 * Returns a new object of class '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function</em>'.
	 * @generated
	 */
	Function createFunction();

	/**
	 * Returns a new object of class '<em>Function Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function Parameter</em>'.
	 * @generated
	 */
	FunctionParameter createFunctionParameter();

	/**
	 * Returns a new object of class '<em>Instantiation Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instantiation Link</em>'.
	 * @generated
	 */
	InstantiationLink createInstantiationLink();

	/**
	 * Returns a new object of class '<em>Mediation Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mediation Link</em>'.
	 * @generated
	 */
	MediationLink createMediationLink();

	/**
	 * Returns a new object of class '<em>Mode Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mode Reference</em>'.
	 * @generated
	 */
	ModeReference createModeReference();

	/**
	 * Returns a new object of class '<em>Ordered Comparative Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ordered Comparative Link</em>'.
	 * @generated
	 */
	OrderedComparativeLink createOrderedComparativeLink();

	/**
	 * Returns a new object of class '<em>Quality Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quality Literal</em>'.
	 * @generated
	 */
	QualityLiteral createQualityLiteral();

	/**
	 * Returns a new object of class '<em>Reference Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reference Node</em>'.
	 * @generated
	 */
	ReferenceNode createReferenceNode();

	/**
	 * Returns a new object of class '<em>Relator Participant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Relator Participant</em>'.
	 * @generated
	 */
	RelatorParticipant createRelatorParticipant();

	/**
	 * Returns a new object of class '<em>Self Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Self Reference</em>'.
	 * @generated
	 */
	SelfReference createSelfReference();

	/**
	 * Returns a new object of class '<em>Situation Participant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Situation Participant</em>'.
	 * @generated
	 */
	SituationParticipant createSituationParticipant();

	/**
	 * Returns a new object of class '<em>Situation Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Situation Type</em>'.
	 * @generated
	 */
	SituationType createSituationType();

	/**
	 * Returns a new object of class '<em>SML Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SML Model</em>'.
	 * @generated
	 */
	SMLModel createSMLModel();

	/**
	 * Returns a new object of class '<em>Type Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type Literal</em>'.
	 * @generated
	 */
	TypeLiteral createTypeLiteral();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Sml2Package getSml2Package();

} //Sml2Factory
