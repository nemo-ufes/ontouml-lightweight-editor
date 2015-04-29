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
	 * Returns a new object of class '<em>SML Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SML Model</em>'.
	 * @generated
	 */
	SMLModel createSMLModel();

	/**
	 * Returns a new object of class '<em>Situation Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Situation Type</em>'.
	 * @generated
	 */
	SituationType createSituationType();

	/**
	 * Returns a new object of class '<em>Situation Type Block</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Situation Type Block</em>'.
	 * @generated
	 */
	SituationTypeBlock createSituationTypeBlock();

	/**
	 * Returns a new object of class '<em>Entity Participant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entity Participant</em>'.
	 * @generated
	 */
	EntityParticipant createEntityParticipant();

	/**
	 * Returns a new object of class '<em>Relator Participant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Relator Participant</em>'.
	 * @generated
	 */
	RelatorParticipant createRelatorParticipant();

	/**
	 * Returns a new object of class '<em>Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Link</em>'.
	 * @generated
	 */
	Link createLink();

	/**
	 * Returns a new object of class '<em>Situation Type Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Situation Type Parameter</em>'.
	 * @generated
	 */
	SituationTypeParameter createSituationTypeParameter();

	/**
	 * Returns a new object of class '<em>Attribute Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute Reference</em>'.
	 * @generated
	 */
	AttributeReference createAttributeReference();

	/**
	 * Returns a new object of class '<em>Comparative Relation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Comparative Relation</em>'.
	 * @generated
	 */
	ComparativeRelation createComparativeRelation();

	/**
	 * Returns a new object of class '<em>Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Literal</em>'.
	 * @generated
	 */
	Literal createLiteral();

	/**
	 * Returns a new object of class '<em>Situation Participant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Situation Participant</em>'.
	 * @generated
	 */
	SituationParticipant createSituationParticipant();

	/**
	 * Returns a new object of class '<em>Situation Parameter Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Situation Parameter Reference</em>'.
	 * @generated
	 */
	SituationParameterReference createSituationParameterReference();

	/**
	 * Returns a new object of class '<em>Exists Situation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exists Situation</em>'.
	 * @generated
	 */
	ExistsSituation createExistsSituation();

	/**
	 * Returns a new object of class '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function</em>'.
	 * @generated
	 */
	Function createFunction();

	/**
	 * Returns a new object of class '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter</em>'.
	 * @generated
	 */
	Parameter createParameter();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Sml2Package getSml2Package();

} //Sml2Factory
