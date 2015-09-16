/**
 */
package sml2;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see sml2.Sml2Factory
 * @model kind="package"
 * @generated
 */
public interface Sml2Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sml2";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://br.ufes.inf.nemo/sml2";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sml2";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Sml2Package eINSTANCE = sml2.impl.Sml2PackageImpl.init();

	/**
	 * The meta object id for the '{@link sml2.impl.SituationTypeElementImpl <em>Situation Type Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SituationTypeElementImpl
	 * @see sml2.impl.Sml2PackageImpl#getSituationTypeElement()
	 * @generated
	 */
	int SITUATION_TYPE_ELEMENT = 26;

	/**
	 * The number of structural features of the '<em>Situation Type Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_ELEMENT___GET_SITUATION = 0;

	/**
	 * The number of operations of the '<em>Situation Type Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_ELEMENT_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link sml2.impl.SituationTypeAssociationImpl <em>Situation Type Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SituationTypeAssociationImpl
	 * @see sml2.impl.Sml2PackageImpl#getSituationTypeAssociation()
	 * @generated
	 */
	int SITUATION_TYPE_ASSOCIATION = 25;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_ASSOCIATION__SOURCE = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_ASSOCIATION__TARGET = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Situation Type Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_ASSOCIATION_FEATURE_COUNT = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_ASSOCIATION___GET_SITUATION = SITUATION_TYPE_ELEMENT___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Situation Type Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_ASSOCIATION_OPERATION_COUNT = SITUATION_TYPE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.FormalRelationImpl <em>Formal Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.FormalRelationImpl
	 * @see sml2.impl.Sml2PackageImpl#getFormalRelation()
	 * @generated
	 */
	int FORMAL_RELATION = 6;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_RELATION__SOURCE = SITUATION_TYPE_ASSOCIATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_RELATION__TARGET = SITUATION_TYPE_ASSOCIATION__TARGET;

	/**
	 * The feature id for the '<em><b>Negated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_RELATION__NEGATED = SITUATION_TYPE_ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Formal Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_RELATION_FEATURE_COUNT = SITUATION_TYPE_ASSOCIATION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_RELATION___GET_SITUATION = SITUATION_TYPE_ASSOCIATION___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Formal Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_RELATION_OPERATION_COUNT = SITUATION_TYPE_ASSOCIATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.AllenLinkImpl <em>Allen Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.AllenLinkImpl
	 * @see sml2.impl.Sml2PackageImpl#getAllenLink()
	 * @generated
	 */
	int ALLEN_LINK = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLEN_LINK__SOURCE = FORMAL_RELATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLEN_LINK__TARGET = FORMAL_RELATION__TARGET;

	/**
	 * The feature id for the '<em><b>Negated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLEN_LINK__NEGATED = FORMAL_RELATION__NEGATED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLEN_LINK__TYPE = FORMAL_RELATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Allen Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLEN_LINK_FEATURE_COUNT = FORMAL_RELATION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLEN_LINK___GET_SITUATION = FORMAL_RELATION___GET_SITUATION;

	/**
	 * The operation id for the '<em>Get Source</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLEN_LINK___GET_SOURCE = FORMAL_RELATION_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Target</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLEN_LINK___GET_TARGET = FORMAL_RELATION_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Allen Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLEN_LINK_OPERATION_COUNT = FORMAL_RELATION_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link sml2.impl.AttributeLinkImpl <em>Attribute Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.AttributeLinkImpl
	 * @see sml2.impl.Sml2PackageImpl#getAttributeLink()
	 * @generated
	 */
	int ATTRIBUTE_LINK = 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_LINK__SOURCE = SITUATION_TYPE_ASSOCIATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_LINK__TARGET = SITUATION_TYPE_ASSOCIATION__TARGET;

	/**
	 * The number of structural features of the '<em>Attribute Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_LINK_FEATURE_COUNT = SITUATION_TYPE_ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_LINK___GET_SITUATION = SITUATION_TYPE_ASSOCIATION___GET_SITUATION;

	/**
	 * The operation id for the '<em>Get Entity</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_LINK___GET_ENTITY = SITUATION_TYPE_ASSOCIATION_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Attribute</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_LINK___GET_ATTRIBUTE = SITUATION_TYPE_ASSOCIATION_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Attribute Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_LINK_OPERATION_COUNT = SITUATION_TYPE_ASSOCIATION_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link sml2.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.NodeImpl
	 * @see sml2.impl.Sml2PackageImpl#getNode()
	 * @generated
	 */
	int NODE = 15;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE___SOURCE_RELATION = 0;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE___TARGET_RELATION = 1;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link sml2.impl.ReferableElementImpl <em>Referable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.ReferableElementImpl
	 * @see sml2.impl.Sml2PackageImpl#getReferableElement()
	 * @generated
	 */
	int REFERABLE_ELEMENT = 19;

	/**
	 * The number of structural features of the '<em>Referable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERABLE_ELEMENT_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERABLE_ELEMENT___SOURCE_RELATION = NODE___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERABLE_ELEMENT___TARGET_RELATION = NODE___TARGET_RELATION;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERABLE_ELEMENT___GET_SITUATION = NODE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Referable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERABLE_ELEMENT_OPERATION_COUNT = NODE_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link sml2.impl.AttributeReferenceImpl <em>Attribute Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.AttributeReferenceImpl
	 * @see sml2.impl.Sml2PackageImpl#getAttributeReference()
	 * @generated
	 */
	int ATTRIBUTE_REFERENCE = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE__TYPE = REFERABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Attribute Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE_FEATURE_COUNT = REFERABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE___SOURCE_RELATION = REFERABLE_ELEMENT___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE___TARGET_RELATION = REFERABLE_ELEMENT___TARGET_RELATION;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE___GET_SITUATION = REFERABLE_ELEMENT___GET_SITUATION;

	/**
	 * The operation id for the '<em>Get Entity</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE___GET_ENTITY = REFERABLE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Attribute Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE_OPERATION_COUNT = REFERABLE_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link sml2.impl.CharacterizationLinkImpl <em>Characterization Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.CharacterizationLinkImpl
	 * @see sml2.impl.Sml2PackageImpl#getCharacterizationLink()
	 * @generated
	 */
	int CHARACTERIZATION_LINK = 3;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZATION_LINK__SOURCE = SITUATION_TYPE_ASSOCIATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZATION_LINK__TARGET = SITUATION_TYPE_ASSOCIATION__TARGET;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZATION_LINK__TYPE = SITUATION_TYPE_ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Characterization Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZATION_LINK_FEATURE_COUNT = SITUATION_TYPE_ASSOCIATION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZATION_LINK___GET_SITUATION = SITUATION_TYPE_ASSOCIATION___GET_SITUATION;

	/**
	 * The operation id for the '<em>Get Mode</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZATION_LINK___GET_MODE = SITUATION_TYPE_ASSOCIATION_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Characterized</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZATION_LINK___GET_CHARACTERIZED = SITUATION_TYPE_ASSOCIATION_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Characterization Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZATION_LINK_OPERATION_COUNT = SITUATION_TYPE_ASSOCIATION_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link sml2.impl.ComparativeRelationImpl <em>Comparative Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.ComparativeRelationImpl
	 * @see sml2.impl.Sml2PackageImpl#getComparativeRelation()
	 * @generated
	 */
	int COMPARATIVE_RELATION = 4;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION__SOURCE = FORMAL_RELATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION__TARGET = FORMAL_RELATION__TARGET;

	/**
	 * The feature id for the '<em><b>Negated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION__NEGATED = FORMAL_RELATION__NEGATED;

	/**
	 * The number of structural features of the '<em>Comparative Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION_FEATURE_COUNT = FORMAL_RELATION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION___GET_SITUATION = FORMAL_RELATION___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Comparative Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION_OPERATION_COUNT = FORMAL_RELATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.ContextFormalLinkImpl <em>Context Formal Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.ContextFormalLinkImpl
	 * @see sml2.impl.Sml2PackageImpl#getContextFormalLink()
	 * @generated
	 */
	int CONTEXT_FORMAL_LINK = 5;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_FORMAL_LINK__SOURCE = FORMAL_RELATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_FORMAL_LINK__TARGET = FORMAL_RELATION__TARGET;

	/**
	 * The feature id for the '<em><b>Negated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_FORMAL_LINK__NEGATED = FORMAL_RELATION__NEGATED;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_FORMAL_LINK__PARAMETER = FORMAL_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_FORMAL_LINK__TYPE = FORMAL_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Context Formal Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_FORMAL_LINK_FEATURE_COUNT = FORMAL_RELATION_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_FORMAL_LINK___GET_SITUATION = FORMAL_RELATION___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Context Formal Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_FORMAL_LINK_OPERATION_COUNT = FORMAL_RELATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.ParticipantImpl <em>Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.ParticipantImpl
	 * @see sml2.impl.Sml2PackageImpl#getParticipant()
	 * @generated
	 */
	int PARTICIPANT = 17;

	/**
	 * The feature id for the '<em><b>Immutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__IMMUTABLE = REFERABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__MIN = REFERABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__MAX = REFERABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Is Image Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__IS_IMAGE_OF = REFERABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_FEATURE_COUNT = REFERABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT___SOURCE_RELATION = REFERABLE_ELEMENT___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT___TARGET_RELATION = REFERABLE_ELEMENT___TARGET_RELATION;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT___GET_SITUATION = REFERABLE_ELEMENT___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_OPERATION_COUNT = REFERABLE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.EntityParticipantImpl <em>Entity Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.EntityParticipantImpl
	 * @see sml2.impl.Sml2PackageImpl#getEntityParticipant()
	 * @generated
	 */
	int ENTITY_PARTICIPANT = 7;

	/**
	 * The feature id for the '<em><b>Immutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT__IMMUTABLE = PARTICIPANT__IMMUTABLE;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT__MIN = PARTICIPANT__MIN;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT__MAX = PARTICIPANT__MAX;

	/**
	 * The feature id for the '<em><b>Is Image Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT__IS_IMAGE_OF = PARTICIPANT__IS_IMAGE_OF;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT__TYPE = PARTICIPANT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Entity Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT_FEATURE_COUNT = PARTICIPANT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT___SOURCE_RELATION = PARTICIPANT___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT___TARGET_RELATION = PARTICIPANT___TARGET_RELATION;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT___GET_SITUATION = PARTICIPANT___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Entity Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT_OPERATION_COUNT = PARTICIPANT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.EqualsLinkImpl <em>Equals Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.EqualsLinkImpl
	 * @see sml2.impl.Sml2PackageImpl#getEqualsLink()
	 * @generated
	 */
	int EQUALS_LINK = 8;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALS_LINK__SOURCE = COMPARATIVE_RELATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALS_LINK__TARGET = COMPARATIVE_RELATION__TARGET;

	/**
	 * The feature id for the '<em><b>Negated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALS_LINK__NEGATED = COMPARATIVE_RELATION__NEGATED;

	/**
	 * The number of structural features of the '<em>Equals Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALS_LINK_FEATURE_COUNT = COMPARATIVE_RELATION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALS_LINK___GET_SITUATION = COMPARATIVE_RELATION___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Equals Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALS_LINK_OPERATION_COUNT = COMPARATIVE_RELATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.FunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.FunctionImpl
	 * @see sml2.impl.Sml2PackageImpl#getFunction()
	 * @generated
	 */
	int FUNCTION = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__NAME = REFERABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__PARAMETERS = REFERABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_FEATURE_COUNT = REFERABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION___SOURCE_RELATION = REFERABLE_ELEMENT___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION___TARGET_RELATION = REFERABLE_ELEMENT___TARGET_RELATION;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION___GET_SITUATION = REFERABLE_ELEMENT___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_OPERATION_COUNT = REFERABLE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.FunctionParameterImpl <em>Function Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.FunctionParameterImpl
	 * @see sml2.impl.Sml2PackageImpl#getFunctionParameter()
	 * @generated
	 */
	int FUNCTION_PARAMETER = 10;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_PARAMETER__LABEL = 0;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_PARAMETER__PARAMETER = 1;

	/**
	 * The number of structural features of the '<em>Function Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_PARAMETER_FEATURE_COUNT = 2;

	/**
	 * The operation id for the '<em>Get Function</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_PARAMETER___GET_FUNCTION = 0;

	/**
	 * The number of operations of the '<em>Function Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_PARAMETER_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link sml2.impl.InstantiationLinkImpl <em>Instantiation Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.InstantiationLinkImpl
	 * @see sml2.impl.Sml2PackageImpl#getInstantiationLink()
	 * @generated
	 */
	int INSTANTIATION_LINK = 11;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIATION_LINK__SOURCE = FORMAL_RELATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIATION_LINK__TARGET = FORMAL_RELATION__TARGET;

	/**
	 * The feature id for the '<em><b>Negated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIATION_LINK__NEGATED = FORMAL_RELATION__NEGATED;

	/**
	 * The number of structural features of the '<em>Instantiation Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIATION_LINK_FEATURE_COUNT = FORMAL_RELATION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIATION_LINK___GET_SITUATION = FORMAL_RELATION___GET_SITUATION;

	/**
	 * The operation id for the '<em>Get Source</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIATION_LINK___GET_SOURCE = FORMAL_RELATION_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Target</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIATION_LINK___GET_TARGET = FORMAL_RELATION_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Instantiation Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIATION_LINK_OPERATION_COUNT = FORMAL_RELATION_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link sml2.impl.LiteralImpl <em>Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.LiteralImpl
	 * @see sml2.impl.Sml2PackageImpl#getLiteral()
	 * @generated
	 */
	int LITERAL = 12;

	/**
	 * The number of structural features of the '<em>Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL___SOURCE_RELATION = NODE___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL___TARGET_RELATION = NODE___TARGET_RELATION;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL___GET_SITUATION = NODE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_OPERATION_COUNT = NODE_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link sml2.impl.MediationLinkImpl <em>Mediation Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.MediationLinkImpl
	 * @see sml2.impl.Sml2PackageImpl#getMediationLink()
	 * @generated
	 */
	int MEDIATION_LINK = 13;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION_LINK__SOURCE = SITUATION_TYPE_ASSOCIATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION_LINK__TARGET = SITUATION_TYPE_ASSOCIATION__TARGET;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION_LINK__TYPE = SITUATION_TYPE_ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Mediation Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION_LINK_FEATURE_COUNT = SITUATION_TYPE_ASSOCIATION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION_LINK___GET_SITUATION = SITUATION_TYPE_ASSOCIATION___GET_SITUATION;

	/**
	 * The operation id for the '<em>Get Relator</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION_LINK___GET_RELATOR = SITUATION_TYPE_ASSOCIATION_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Entity</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION_LINK___GET_ENTITY = SITUATION_TYPE_ASSOCIATION_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Mediation Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION_LINK_OPERATION_COUNT = SITUATION_TYPE_ASSOCIATION_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link sml2.impl.ModeReferenceImpl <em>Mode Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.ModeReferenceImpl
	 * @see sml2.impl.Sml2PackageImpl#getModeReference()
	 * @generated
	 */
	int MODE_REFERENCE = 14;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODE_REFERENCE__TYPE = REFERABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Mode Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODE_REFERENCE_FEATURE_COUNT = REFERABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODE_REFERENCE___SOURCE_RELATION = REFERABLE_ELEMENT___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODE_REFERENCE___TARGET_RELATION = REFERABLE_ELEMENT___TARGET_RELATION;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODE_REFERENCE___GET_SITUATION = REFERABLE_ELEMENT___GET_SITUATION;

	/**
	 * The operation id for the '<em>Get Entity</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODE_REFERENCE___GET_ENTITY = REFERABLE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Mode Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODE_REFERENCE_OPERATION_COUNT = REFERABLE_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link sml2.impl.OrderedComparativeLinkImpl <em>Ordered Comparative Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.OrderedComparativeLinkImpl
	 * @see sml2.impl.Sml2PackageImpl#getOrderedComparativeLink()
	 * @generated
	 */
	int ORDERED_COMPARATIVE_LINK = 16;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_COMPARATIVE_LINK__SOURCE = COMPARATIVE_RELATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_COMPARATIVE_LINK__TARGET = COMPARATIVE_RELATION__TARGET;

	/**
	 * The feature id for the '<em><b>Negated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_COMPARATIVE_LINK__NEGATED = COMPARATIVE_RELATION__NEGATED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_COMPARATIVE_LINK__TYPE = COMPARATIVE_RELATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ordered Comparative Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_COMPARATIVE_LINK_FEATURE_COUNT = COMPARATIVE_RELATION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_COMPARATIVE_LINK___GET_SITUATION = COMPARATIVE_RELATION___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Ordered Comparative Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_COMPARATIVE_LINK_OPERATION_COUNT = COMPARATIVE_RELATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.QualityLiteralImpl <em>Quality Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.QualityLiteralImpl
	 * @see sml2.impl.Sml2PackageImpl#getQualityLiteral()
	 * @generated
	 */
	int QUALITY_LITERAL = 18;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_LITERAL__VALUE = LITERAL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_LITERAL__TYPE = LITERAL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Quality Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_LITERAL___SOURCE_RELATION = LITERAL___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_LITERAL___TARGET_RELATION = LITERAL___TARGET_RELATION;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_LITERAL___GET_SITUATION = LITERAL___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Quality Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_LITERAL_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.ReferenceNodeImpl <em>Reference Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.ReferenceNodeImpl
	 * @see sml2.impl.Sml2PackageImpl#getReferenceNode()
	 * @generated
	 */
	int REFERENCE_NODE = 20;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_NODE__LABEL = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Situation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_NODE__SITUATION = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_NODE__REFERENCE = NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Reference Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_NODE___SOURCE_RELATION = NODE___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_NODE___TARGET_RELATION = NODE___TARGET_RELATION;

	/**
	 * The number of operations of the '<em>Reference Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_NODE_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.RelatorParticipantImpl <em>Relator Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.RelatorParticipantImpl
	 * @see sml2.impl.Sml2PackageImpl#getRelatorParticipant()
	 * @generated
	 */
	int RELATOR_PARTICIPANT = 21;

	/**
	 * The feature id for the '<em><b>Immutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT__IMMUTABLE = PARTICIPANT__IMMUTABLE;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT__MIN = PARTICIPANT__MIN;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT__MAX = PARTICIPANT__MAX;

	/**
	 * The feature id for the '<em><b>Is Image Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT__IS_IMAGE_OF = PARTICIPANT__IS_IMAGE_OF;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT__TYPE = PARTICIPANT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Relator Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT_FEATURE_COUNT = PARTICIPANT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT___SOURCE_RELATION = PARTICIPANT___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT___TARGET_RELATION = PARTICIPANT___TARGET_RELATION;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT___GET_SITUATION = PARTICIPANT___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Relator Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT_OPERATION_COUNT = PARTICIPANT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.SelfReferenceImpl <em>Self Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SelfReferenceImpl
	 * @see sml2.impl.Sml2PackageImpl#getSelfReference()
	 * @generated
	 */
	int SELF_REFERENCE = 22;

	/**
	 * The meta object id for the '{@link sml2.impl.SituationParticipantImpl <em>Situation Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SituationParticipantImpl
	 * @see sml2.impl.Sml2PackageImpl#getSituationParticipant()
	 * @generated
	 */
	int SITUATION_PARTICIPANT = 23;

	/**
	 * The feature id for the '<em><b>Immutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT__IMMUTABLE = PARTICIPANT__IMMUTABLE;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT__MIN = PARTICIPANT__MIN;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT__MAX = PARTICIPANT__MAX;

	/**
	 * The feature id for the '<em><b>Is Image Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT__IS_IMAGE_OF = PARTICIPANT__IS_IMAGE_OF;

	/**
	 * The feature id for the '<em><b>Temporality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT__TEMPORALITY = PARTICIPANT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>References</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT__REFERENCES = PARTICIPANT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT__TYPE = PARTICIPANT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Situation Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT_FEATURE_COUNT = PARTICIPANT_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT___SOURCE_RELATION = PARTICIPANT___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT___TARGET_RELATION = PARTICIPANT___TARGET_RELATION;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT___GET_SITUATION = PARTICIPANT___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Situation Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT_OPERATION_COUNT = PARTICIPANT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Immutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REFERENCE__IMMUTABLE = SITUATION_PARTICIPANT__IMMUTABLE;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REFERENCE__MIN = SITUATION_PARTICIPANT__MIN;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REFERENCE__MAX = SITUATION_PARTICIPANT__MAX;

	/**
	 * The feature id for the '<em><b>Is Image Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REFERENCE__IS_IMAGE_OF = SITUATION_PARTICIPANT__IS_IMAGE_OF;

	/**
	 * The feature id for the '<em><b>Temporality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REFERENCE__TEMPORALITY = SITUATION_PARTICIPANT__TEMPORALITY;

	/**
	 * The feature id for the '<em><b>References</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REFERENCE__REFERENCES = SITUATION_PARTICIPANT__REFERENCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REFERENCE__TYPE = SITUATION_PARTICIPANT__TYPE;

	/**
	 * The number of structural features of the '<em>Self Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REFERENCE_FEATURE_COUNT = SITUATION_PARTICIPANT_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REFERENCE___SOURCE_RELATION = SITUATION_PARTICIPANT___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REFERENCE___TARGET_RELATION = SITUATION_PARTICIPANT___TARGET_RELATION;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REFERENCE___GET_SITUATION = SITUATION_PARTICIPANT___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Self Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REFERENCE_OPERATION_COUNT = SITUATION_PARTICIPANT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.SituationTypeImpl <em>Situation Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SituationTypeImpl
	 * @see sml2.impl.Sml2PackageImpl#getSituationType()
	 * @generated
	 */
	int SITUATION_TYPE = 24;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE__ELEMENTS = 1;

	/**
	 * The number of structural features of the '<em>Situation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Situation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link sml2.impl.SMLModelImpl <em>SML Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SMLModelImpl
	 * @see sml2.impl.Sml2PackageImpl#getSMLModel()
	 * @generated
	 */
	int SML_MODEL = 27;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SML_MODEL__ELEMENTS = 0;

	/**
	 * The feature id for the '<em><b>Context Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SML_MODEL__CONTEXT_MODEL = 1;

	/**
	 * The number of structural features of the '<em>SML Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SML_MODEL_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>SML Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SML_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link sml2.impl.TypeLiteralImpl <em>Type Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.TypeLiteralImpl
	 * @see sml2.impl.Sml2PackageImpl#getTypeLiteral()
	 * @generated
	 */
	int TYPE_LITERAL = 28;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL__TYPE = LITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL___SOURCE_RELATION = LITERAL___SOURCE_RELATION;

	/**
	 * The operation id for the '<em>Target Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL___TARGET_RELATION = LITERAL___TARGET_RELATION;

	/**
	 * The operation id for the '<em>Get Situation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL___GET_SITUATION = LITERAL___GET_SITUATION;

	/**
	 * The number of operations of the '<em>Type Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.AllenKind <em>Allen Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.AllenKind
	 * @see sml2.impl.Sml2PackageImpl#getAllenKind()
	 * @generated
	 */
	int ALLEN_KIND = 29;

	/**
	 * The meta object id for the '{@link sml2.ComparativeKind <em>Comparative Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.ComparativeKind
	 * @see sml2.impl.Sml2PackageImpl#getComparativeKind()
	 * @generated
	 */
	int COMPARATIVE_KIND = 30;

	/**
	 * The meta object id for the '{@link sml2.TemporalKind <em>Temporal Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.TemporalKind
	 * @see sml2.impl.Sml2PackageImpl#getTemporalKind()
	 * @generated
	 */
	int TEMPORAL_KIND = 31;


	/**
	 * Returns the meta object for class '{@link sml2.AllenLink <em>Allen Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Allen Link</em>'.
	 * @see sml2.AllenLink
	 * @generated
	 */
	EClass getAllenLink();

	/**
	 * Returns the meta object for the attribute '{@link sml2.AllenLink#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see sml2.AllenLink#getType()
	 * @see #getAllenLink()
	 * @generated
	 */
	EAttribute getAllenLink_Type();

	/**
	 * Returns the meta object for the '{@link sml2.AllenLink#getSource() <em>Get Source</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Source</em>' operation.
	 * @see sml2.AllenLink#getSource()
	 * @generated
	 */
	EOperation getAllenLink__GetSource();

	/**
	 * Returns the meta object for the '{@link sml2.AllenLink#getTarget() <em>Get Target</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Target</em>' operation.
	 * @see sml2.AllenLink#getTarget()
	 * @generated
	 */
	EOperation getAllenLink__GetTarget();

	/**
	 * Returns the meta object for class '{@link sml2.AttributeLink <em>Attribute Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Link</em>'.
	 * @see sml2.AttributeLink
	 * @generated
	 */
	EClass getAttributeLink();

	/**
	 * Returns the meta object for the '{@link sml2.AttributeLink#getEntity() <em>Get Entity</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Entity</em>' operation.
	 * @see sml2.AttributeLink#getEntity()
	 * @generated
	 */
	EOperation getAttributeLink__GetEntity();

	/**
	 * Returns the meta object for the '{@link sml2.AttributeLink#getAttribute() <em>Get Attribute</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Attribute</em>' operation.
	 * @see sml2.AttributeLink#getAttribute()
	 * @generated
	 */
	EOperation getAttributeLink__GetAttribute();

	/**
	 * Returns the meta object for class '{@link sml2.AttributeReference <em>Attribute Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Reference</em>'.
	 * @see sml2.AttributeReference
	 * @generated
	 */
	EClass getAttributeReference();

	/**
	 * Returns the meta object for the reference '{@link sml2.AttributeReference#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see sml2.AttributeReference#getType()
	 * @see #getAttributeReference()
	 * @generated
	 */
	EReference getAttributeReference_Type();

	/**
	 * Returns the meta object for the '{@link sml2.AttributeReference#getEntity() <em>Get Entity</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Entity</em>' operation.
	 * @see sml2.AttributeReference#getEntity()
	 * @generated
	 */
	EOperation getAttributeReference__GetEntity();

	/**
	 * Returns the meta object for class '{@link sml2.CharacterizationLink <em>Characterization Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Characterization Link</em>'.
	 * @see sml2.CharacterizationLink
	 * @generated
	 */
	EClass getCharacterizationLink();

	/**
	 * Returns the meta object for the reference '{@link sml2.CharacterizationLink#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see sml2.CharacterizationLink#getType()
	 * @see #getCharacterizationLink()
	 * @generated
	 */
	EReference getCharacterizationLink_Type();

	/**
	 * Returns the meta object for the '{@link sml2.CharacterizationLink#getMode() <em>Get Mode</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Mode</em>' operation.
	 * @see sml2.CharacterizationLink#getMode()
	 * @generated
	 */
	EOperation getCharacterizationLink__GetMode();

	/**
	 * Returns the meta object for the '{@link sml2.CharacterizationLink#getCharacterized() <em>Get Characterized</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Characterized</em>' operation.
	 * @see sml2.CharacterizationLink#getCharacterized()
	 * @generated
	 */
	EOperation getCharacterizationLink__GetCharacterized();

	/**
	 * Returns the meta object for class '{@link sml2.ComparativeRelation <em>Comparative Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comparative Relation</em>'.
	 * @see sml2.ComparativeRelation
	 * @generated
	 */
	EClass getComparativeRelation();

	/**
	 * Returns the meta object for class '{@link sml2.ContextFormalLink <em>Context Formal Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Context Formal Link</em>'.
	 * @see sml2.ContextFormalLink
	 * @generated
	 */
	EClass getContextFormalLink();

	/**
	 * Returns the meta object for the attribute '{@link sml2.ContextFormalLink#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter</em>'.
	 * @see sml2.ContextFormalLink#getParameter()
	 * @see #getContextFormalLink()
	 * @generated
	 */
	EAttribute getContextFormalLink_Parameter();

	/**
	 * Returns the meta object for the reference '{@link sml2.ContextFormalLink#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see sml2.ContextFormalLink#getType()
	 * @see #getContextFormalLink()
	 * @generated
	 */
	EReference getContextFormalLink_Type();

	/**
	 * Returns the meta object for class '{@link sml2.FormalRelation <em>Formal Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formal Relation</em>'.
	 * @see sml2.FormalRelation
	 * @generated
	 */
	EClass getFormalRelation();

	/**
	 * Returns the meta object for the attribute '{@link sml2.FormalRelation#isNegated <em>Negated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Negated</em>'.
	 * @see sml2.FormalRelation#isNegated()
	 * @see #getFormalRelation()
	 * @generated
	 */
	EAttribute getFormalRelation_Negated();

	/**
	 * Returns the meta object for class '{@link sml2.EntityParticipant <em>Entity Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Participant</em>'.
	 * @see sml2.EntityParticipant
	 * @generated
	 */
	EClass getEntityParticipant();

	/**
	 * Returns the meta object for the reference '{@link sml2.EntityParticipant#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see sml2.EntityParticipant#getType()
	 * @see #getEntityParticipant()
	 * @generated
	 */
	EReference getEntityParticipant_Type();

	/**
	 * Returns the meta object for class '{@link sml2.EqualsLink <em>Equals Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Equals Link</em>'.
	 * @see sml2.EqualsLink
	 * @generated
	 */
	EClass getEqualsLink();

	/**
	 * Returns the meta object for class '{@link sml2.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see sml2.Function
	 * @generated
	 */
	EClass getFunction();

	/**
	 * Returns the meta object for the attribute '{@link sml2.Function#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see sml2.Function#getName()
	 * @see #getFunction()
	 * @generated
	 */
	EAttribute getFunction_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link sml2.Function#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see sml2.Function#getParameters()
	 * @see #getFunction()
	 * @generated
	 */
	EReference getFunction_Parameters();

	/**
	 * Returns the meta object for class '{@link sml2.FunctionParameter <em>Function Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Parameter</em>'.
	 * @see sml2.FunctionParameter
	 * @generated
	 */
	EClass getFunctionParameter();

	/**
	 * Returns the meta object for the attribute '{@link sml2.FunctionParameter#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see sml2.FunctionParameter#getLabel()
	 * @see #getFunctionParameter()
	 * @generated
	 */
	EAttribute getFunctionParameter_Label();

	/**
	 * Returns the meta object for the reference '{@link sml2.FunctionParameter#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see sml2.FunctionParameter#getParameter()
	 * @see #getFunctionParameter()
	 * @generated
	 */
	EReference getFunctionParameter_Parameter();

	/**
	 * Returns the meta object for the '{@link sml2.FunctionParameter#getFunction() <em>Get Function</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Function</em>' operation.
	 * @see sml2.FunctionParameter#getFunction()
	 * @generated
	 */
	EOperation getFunctionParameter__GetFunction();

	/**
	 * Returns the meta object for class '{@link sml2.InstantiationLink <em>Instantiation Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instantiation Link</em>'.
	 * @see sml2.InstantiationLink
	 * @generated
	 */
	EClass getInstantiationLink();

	/**
	 * Returns the meta object for the '{@link sml2.InstantiationLink#getSource() <em>Get Source</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Source</em>' operation.
	 * @see sml2.InstantiationLink#getSource()
	 * @generated
	 */
	EOperation getInstantiationLink__GetSource();

	/**
	 * Returns the meta object for the '{@link sml2.InstantiationLink#getTarget() <em>Get Target</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Target</em>' operation.
	 * @see sml2.InstantiationLink#getTarget()
	 * @generated
	 */
	EOperation getInstantiationLink__GetTarget();

	/**
	 * Returns the meta object for class '{@link sml2.Literal <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Literal</em>'.
	 * @see sml2.Literal
	 * @generated
	 */
	EClass getLiteral();

	/**
	 * Returns the meta object for class '{@link sml2.MediationLink <em>Mediation Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mediation Link</em>'.
	 * @see sml2.MediationLink
	 * @generated
	 */
	EClass getMediationLink();

	/**
	 * Returns the meta object for the reference '{@link sml2.MediationLink#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see sml2.MediationLink#getType()
	 * @see #getMediationLink()
	 * @generated
	 */
	EReference getMediationLink_Type();

	/**
	 * Returns the meta object for the '{@link sml2.MediationLink#getRelator() <em>Get Relator</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Relator</em>' operation.
	 * @see sml2.MediationLink#getRelator()
	 * @generated
	 */
	EOperation getMediationLink__GetRelator();

	/**
	 * Returns the meta object for the '{@link sml2.MediationLink#getEntity() <em>Get Entity</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Entity</em>' operation.
	 * @see sml2.MediationLink#getEntity()
	 * @generated
	 */
	EOperation getMediationLink__GetEntity();

	/**
	 * Returns the meta object for class '{@link sml2.ModeReference <em>Mode Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mode Reference</em>'.
	 * @see sml2.ModeReference
	 * @generated
	 */
	EClass getModeReference();

	/**
	 * Returns the meta object for the reference '{@link sml2.ModeReference#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see sml2.ModeReference#getType()
	 * @see #getModeReference()
	 * @generated
	 */
	EReference getModeReference_Type();

	/**
	 * Returns the meta object for the '{@link sml2.ModeReference#getEntity() <em>Get Entity</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Entity</em>' operation.
	 * @see sml2.ModeReference#getEntity()
	 * @generated
	 */
	EOperation getModeReference__GetEntity();

	/**
	 * Returns the meta object for class '{@link sml2.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see sml2.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the '{@link sml2.Node#sourceRelation() <em>Source Relation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Source Relation</em>' operation.
	 * @see sml2.Node#sourceRelation()
	 * @generated
	 */
	EOperation getNode__SourceRelation();

	/**
	 * Returns the meta object for the '{@link sml2.Node#targetRelation() <em>Target Relation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Target Relation</em>' operation.
	 * @see sml2.Node#targetRelation()
	 * @generated
	 */
	EOperation getNode__TargetRelation();

	/**
	 * Returns the meta object for class '{@link sml2.OrderedComparativeLink <em>Ordered Comparative Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordered Comparative Link</em>'.
	 * @see sml2.OrderedComparativeLink
	 * @generated
	 */
	EClass getOrderedComparativeLink();

	/**
	 * Returns the meta object for the attribute '{@link sml2.OrderedComparativeLink#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see sml2.OrderedComparativeLink#getType()
	 * @see #getOrderedComparativeLink()
	 * @generated
	 */
	EAttribute getOrderedComparativeLink_Type();

	/**
	 * Returns the meta object for class '{@link sml2.Participant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant</em>'.
	 * @see sml2.Participant
	 * @generated
	 */
	EClass getParticipant();

	/**
	 * Returns the meta object for the attribute '{@link sml2.Participant#isImmutable <em>Immutable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Immutable</em>'.
	 * @see sml2.Participant#isImmutable()
	 * @see #getParticipant()
	 * @generated
	 */
	EAttribute getParticipant_Immutable();

	/**
	 * Returns the meta object for the attribute '{@link sml2.Participant#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see sml2.Participant#getMin()
	 * @see #getParticipant()
	 * @generated
	 */
	EAttribute getParticipant_Min();

	/**
	 * Returns the meta object for the attribute '{@link sml2.Participant#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see sml2.Participant#getMax()
	 * @see #getParticipant()
	 * @generated
	 */
	EAttribute getParticipant_Max();

	/**
	 * Returns the meta object for the reference '{@link sml2.Participant#getIsImageOf <em>Is Image Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Is Image Of</em>'.
	 * @see sml2.Participant#getIsImageOf()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_IsImageOf();

	/**
	 * Returns the meta object for class '{@link sml2.QualityLiteral <em>Quality Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quality Literal</em>'.
	 * @see sml2.QualityLiteral
	 * @generated
	 */
	EClass getQualityLiteral();

	/**
	 * Returns the meta object for the attribute '{@link sml2.QualityLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see sml2.QualityLiteral#getValue()
	 * @see #getQualityLiteral()
	 * @generated
	 */
	EAttribute getQualityLiteral_Value();

	/**
	 * Returns the meta object for the reference '{@link sml2.QualityLiteral#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see sml2.QualityLiteral#getType()
	 * @see #getQualityLiteral()
	 * @generated
	 */
	EReference getQualityLiteral_Type();

	/**
	 * Returns the meta object for class '{@link sml2.ReferableElement <em>Referable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Referable Element</em>'.
	 * @see sml2.ReferableElement
	 * @generated
	 */
	EClass getReferableElement();

	/**
	 * Returns the meta object for class '{@link sml2.ReferenceNode <em>Reference Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Node</em>'.
	 * @see sml2.ReferenceNode
	 * @generated
	 */
	EClass getReferenceNode();

	/**
	 * Returns the meta object for the attribute '{@link sml2.ReferenceNode#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see sml2.ReferenceNode#getLabel()
	 * @see #getReferenceNode()
	 * @generated
	 */
	EAttribute getReferenceNode_Label();

	/**
	 * Returns the meta object for the container reference '{@link sml2.ReferenceNode#getSituation <em>Situation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Situation</em>'.
	 * @see sml2.ReferenceNode#getSituation()
	 * @see #getReferenceNode()
	 * @generated
	 */
	EReference getReferenceNode_Situation();

	/**
	 * Returns the meta object for the reference '{@link sml2.ReferenceNode#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see sml2.ReferenceNode#getReference()
	 * @see #getReferenceNode()
	 * @generated
	 */
	EReference getReferenceNode_Reference();

	/**
	 * Returns the meta object for class '{@link sml2.RelatorParticipant <em>Relator Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relator Participant</em>'.
	 * @see sml2.RelatorParticipant
	 * @generated
	 */
	EClass getRelatorParticipant();

	/**
	 * Returns the meta object for the reference '{@link sml2.RelatorParticipant#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see sml2.RelatorParticipant#getType()
	 * @see #getRelatorParticipant()
	 * @generated
	 */
	EReference getRelatorParticipant_Type();

	/**
	 * Returns the meta object for class '{@link sml2.SelfReference <em>Self Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Self Reference</em>'.
	 * @see sml2.SelfReference
	 * @generated
	 */
	EClass getSelfReference();

	/**
	 * Returns the meta object for class '{@link sml2.SituationParticipant <em>Situation Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Situation Participant</em>'.
	 * @see sml2.SituationParticipant
	 * @generated
	 */
	EClass getSituationParticipant();

	/**
	 * Returns the meta object for the attribute '{@link sml2.SituationParticipant#getTemporality <em>Temporality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temporality</em>'.
	 * @see sml2.SituationParticipant#getTemporality()
	 * @see #getSituationParticipant()
	 * @generated
	 */
	EAttribute getSituationParticipant_Temporality();

	/**
	 * Returns the meta object for the containment reference list '{@link sml2.SituationParticipant#getReferences <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>References</em>'.
	 * @see sml2.SituationParticipant#getReferences()
	 * @see #getSituationParticipant()
	 * @generated
	 */
	EReference getSituationParticipant_References();

	/**
	 * Returns the meta object for the reference '{@link sml2.SituationParticipant#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see sml2.SituationParticipant#getType()
	 * @see #getSituationParticipant()
	 * @generated
	 */
	EReference getSituationParticipant_Type();

	/**
	 * Returns the meta object for class '{@link sml2.SituationType <em>Situation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Situation Type</em>'.
	 * @see sml2.SituationType
	 * @generated
	 */
	EClass getSituationType();

	/**
	 * Returns the meta object for the attribute '{@link sml2.SituationType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see sml2.SituationType#getName()
	 * @see #getSituationType()
	 * @generated
	 */
	EAttribute getSituationType_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link sml2.SituationType#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see sml2.SituationType#getElements()
	 * @see #getSituationType()
	 * @generated
	 */
	EReference getSituationType_Elements();

	/**
	 * Returns the meta object for class '{@link sml2.SituationTypeAssociation <em>Situation Type Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Situation Type Association</em>'.
	 * @see sml2.SituationTypeAssociation
	 * @generated
	 */
	EClass getSituationTypeAssociation();

	/**
	 * Returns the meta object for the reference '{@link sml2.SituationTypeAssociation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see sml2.SituationTypeAssociation#getSource()
	 * @see #getSituationTypeAssociation()
	 * @generated
	 */
	EReference getSituationTypeAssociation_Source();

	/**
	 * Returns the meta object for the reference '{@link sml2.SituationTypeAssociation#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see sml2.SituationTypeAssociation#getTarget()
	 * @see #getSituationTypeAssociation()
	 * @generated
	 */
	EReference getSituationTypeAssociation_Target();

	/**
	 * Returns the meta object for class '{@link sml2.SituationTypeElement <em>Situation Type Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Situation Type Element</em>'.
	 * @see sml2.SituationTypeElement
	 * @generated
	 */
	EClass getSituationTypeElement();

	/**
	 * Returns the meta object for the '{@link sml2.SituationTypeElement#getSituation() <em>Get Situation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Situation</em>' operation.
	 * @see sml2.SituationTypeElement#getSituation()
	 * @generated
	 */
	EOperation getSituationTypeElement__GetSituation();

	/**
	 * Returns the meta object for class '{@link sml2.SMLModel <em>SML Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SML Model</em>'.
	 * @see sml2.SMLModel
	 * @generated
	 */
	EClass getSMLModel();

	/**
	 * Returns the meta object for the containment reference list '{@link sml2.SMLModel#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see sml2.SMLModel#getElements()
	 * @see #getSMLModel()
	 * @generated
	 */
	EReference getSMLModel_Elements();

	/**
	 * Returns the meta object for the reference '{@link sml2.SMLModel#getContextModel <em>Context Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Context Model</em>'.
	 * @see sml2.SMLModel#getContextModel()
	 * @see #getSMLModel()
	 * @generated
	 */
	EReference getSMLModel_ContextModel();

	/**
	 * Returns the meta object for class '{@link sml2.TypeLiteral <em>Type Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Literal</em>'.
	 * @see sml2.TypeLiteral
	 * @generated
	 */
	EClass getTypeLiteral();

	/**
	 * Returns the meta object for the reference '{@link sml2.TypeLiteral#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see sml2.TypeLiteral#getType()
	 * @see #getTypeLiteral()
	 * @generated
	 */
	EReference getTypeLiteral_Type();

	/**
	 * Returns the meta object for enum '{@link sml2.AllenKind <em>Allen Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Allen Kind</em>'.
	 * @see sml2.AllenKind
	 * @generated
	 */
	EEnum getAllenKind();

	/**
	 * Returns the meta object for enum '{@link sml2.ComparativeKind <em>Comparative Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Comparative Kind</em>'.
	 * @see sml2.ComparativeKind
	 * @generated
	 */
	EEnum getComparativeKind();

	/**
	 * Returns the meta object for enum '{@link sml2.TemporalKind <em>Temporal Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Temporal Kind</em>'.
	 * @see sml2.TemporalKind
	 * @generated
	 */
	EEnum getTemporalKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Sml2Factory getSml2Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link sml2.impl.AllenLinkImpl <em>Allen Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.AllenLinkImpl
		 * @see sml2.impl.Sml2PackageImpl#getAllenLink()
		 * @generated
		 */
		EClass ALLEN_LINK = eINSTANCE.getAllenLink();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALLEN_LINK__TYPE = eINSTANCE.getAllenLink_Type();

		/**
		 * The meta object literal for the '<em><b>Get Source</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ALLEN_LINK___GET_SOURCE = eINSTANCE.getAllenLink__GetSource();

		/**
		 * The meta object literal for the '<em><b>Get Target</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ALLEN_LINK___GET_TARGET = eINSTANCE.getAllenLink__GetTarget();

		/**
		 * The meta object literal for the '{@link sml2.impl.AttributeLinkImpl <em>Attribute Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.AttributeLinkImpl
		 * @see sml2.impl.Sml2PackageImpl#getAttributeLink()
		 * @generated
		 */
		EClass ATTRIBUTE_LINK = eINSTANCE.getAttributeLink();

		/**
		 * The meta object literal for the '<em><b>Get Entity</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ATTRIBUTE_LINK___GET_ENTITY = eINSTANCE.getAttributeLink__GetEntity();

		/**
		 * The meta object literal for the '<em><b>Get Attribute</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ATTRIBUTE_LINK___GET_ATTRIBUTE = eINSTANCE.getAttributeLink__GetAttribute();

		/**
		 * The meta object literal for the '{@link sml2.impl.AttributeReferenceImpl <em>Attribute Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.AttributeReferenceImpl
		 * @see sml2.impl.Sml2PackageImpl#getAttributeReference()
		 * @generated
		 */
		EClass ATTRIBUTE_REFERENCE = eINSTANCE.getAttributeReference();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE_REFERENCE__TYPE = eINSTANCE.getAttributeReference_Type();

		/**
		 * The meta object literal for the '<em><b>Get Entity</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ATTRIBUTE_REFERENCE___GET_ENTITY = eINSTANCE.getAttributeReference__GetEntity();

		/**
		 * The meta object literal for the '{@link sml2.impl.CharacterizationLinkImpl <em>Characterization Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.CharacterizationLinkImpl
		 * @see sml2.impl.Sml2PackageImpl#getCharacterizationLink()
		 * @generated
		 */
		EClass CHARACTERIZATION_LINK = eINSTANCE.getCharacterizationLink();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHARACTERIZATION_LINK__TYPE = eINSTANCE.getCharacterizationLink_Type();

		/**
		 * The meta object literal for the '<em><b>Get Mode</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CHARACTERIZATION_LINK___GET_MODE = eINSTANCE.getCharacterizationLink__GetMode();

		/**
		 * The meta object literal for the '<em><b>Get Characterized</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CHARACTERIZATION_LINK___GET_CHARACTERIZED = eINSTANCE.getCharacterizationLink__GetCharacterized();

		/**
		 * The meta object literal for the '{@link sml2.impl.ComparativeRelationImpl <em>Comparative Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.ComparativeRelationImpl
		 * @see sml2.impl.Sml2PackageImpl#getComparativeRelation()
		 * @generated
		 */
		EClass COMPARATIVE_RELATION = eINSTANCE.getComparativeRelation();

		/**
		 * The meta object literal for the '{@link sml2.impl.ContextFormalLinkImpl <em>Context Formal Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.ContextFormalLinkImpl
		 * @see sml2.impl.Sml2PackageImpl#getContextFormalLink()
		 * @generated
		 */
		EClass CONTEXT_FORMAL_LINK = eINSTANCE.getContextFormalLink();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTEXT_FORMAL_LINK__PARAMETER = eINSTANCE.getContextFormalLink_Parameter();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT_FORMAL_LINK__TYPE = eINSTANCE.getContextFormalLink_Type();

		/**
		 * The meta object literal for the '{@link sml2.impl.FormalRelationImpl <em>Formal Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.FormalRelationImpl
		 * @see sml2.impl.Sml2PackageImpl#getFormalRelation()
		 * @generated
		 */
		EClass FORMAL_RELATION = eINSTANCE.getFormalRelation();

		/**
		 * The meta object literal for the '<em><b>Negated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMAL_RELATION__NEGATED = eINSTANCE.getFormalRelation_Negated();

		/**
		 * The meta object literal for the '{@link sml2.impl.EntityParticipantImpl <em>Entity Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.EntityParticipantImpl
		 * @see sml2.impl.Sml2PackageImpl#getEntityParticipant()
		 * @generated
		 */
		EClass ENTITY_PARTICIPANT = eINSTANCE.getEntityParticipant();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_PARTICIPANT__TYPE = eINSTANCE.getEntityParticipant_Type();

		/**
		 * The meta object literal for the '{@link sml2.impl.EqualsLinkImpl <em>Equals Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.EqualsLinkImpl
		 * @see sml2.impl.Sml2PackageImpl#getEqualsLink()
		 * @generated
		 */
		EClass EQUALS_LINK = eINSTANCE.getEqualsLink();

		/**
		 * The meta object literal for the '{@link sml2.impl.FunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.FunctionImpl
		 * @see sml2.impl.Sml2PackageImpl#getFunction()
		 * @generated
		 */
		EClass FUNCTION = eINSTANCE.getFunction();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION__NAME = eINSTANCE.getFunction_Name();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION__PARAMETERS = eINSTANCE.getFunction_Parameters();

		/**
		 * The meta object literal for the '{@link sml2.impl.FunctionParameterImpl <em>Function Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.FunctionParameterImpl
		 * @see sml2.impl.Sml2PackageImpl#getFunctionParameter()
		 * @generated
		 */
		EClass FUNCTION_PARAMETER = eINSTANCE.getFunctionParameter();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION_PARAMETER__LABEL = eINSTANCE.getFunctionParameter_Label();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_PARAMETER__PARAMETER = eINSTANCE.getFunctionParameter_Parameter();

		/**
		 * The meta object literal for the '<em><b>Get Function</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation FUNCTION_PARAMETER___GET_FUNCTION = eINSTANCE.getFunctionParameter__GetFunction();

		/**
		 * The meta object literal for the '{@link sml2.impl.InstantiationLinkImpl <em>Instantiation Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.InstantiationLinkImpl
		 * @see sml2.impl.Sml2PackageImpl#getInstantiationLink()
		 * @generated
		 */
		EClass INSTANTIATION_LINK = eINSTANCE.getInstantiationLink();

		/**
		 * The meta object literal for the '<em><b>Get Source</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation INSTANTIATION_LINK___GET_SOURCE = eINSTANCE.getInstantiationLink__GetSource();

		/**
		 * The meta object literal for the '<em><b>Get Target</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation INSTANTIATION_LINK___GET_TARGET = eINSTANCE.getInstantiationLink__GetTarget();

		/**
		 * The meta object literal for the '{@link sml2.impl.LiteralImpl <em>Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.LiteralImpl
		 * @see sml2.impl.Sml2PackageImpl#getLiteral()
		 * @generated
		 */
		EClass LITERAL = eINSTANCE.getLiteral();

		/**
		 * The meta object literal for the '{@link sml2.impl.MediationLinkImpl <em>Mediation Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.MediationLinkImpl
		 * @see sml2.impl.Sml2PackageImpl#getMediationLink()
		 * @generated
		 */
		EClass MEDIATION_LINK = eINSTANCE.getMediationLink();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEDIATION_LINK__TYPE = eINSTANCE.getMediationLink_Type();

		/**
		 * The meta object literal for the '<em><b>Get Relator</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MEDIATION_LINK___GET_RELATOR = eINSTANCE.getMediationLink__GetRelator();

		/**
		 * The meta object literal for the '<em><b>Get Entity</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MEDIATION_LINK___GET_ENTITY = eINSTANCE.getMediationLink__GetEntity();

		/**
		 * The meta object literal for the '{@link sml2.impl.ModeReferenceImpl <em>Mode Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.ModeReferenceImpl
		 * @see sml2.impl.Sml2PackageImpl#getModeReference()
		 * @generated
		 */
		EClass MODE_REFERENCE = eINSTANCE.getModeReference();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODE_REFERENCE__TYPE = eINSTANCE.getModeReference_Type();

		/**
		 * The meta object literal for the '<em><b>Get Entity</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MODE_REFERENCE___GET_ENTITY = eINSTANCE.getModeReference__GetEntity();

		/**
		 * The meta object literal for the '{@link sml2.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.NodeImpl
		 * @see sml2.impl.Sml2PackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Source Relation</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation NODE___SOURCE_RELATION = eINSTANCE.getNode__SourceRelation();

		/**
		 * The meta object literal for the '<em><b>Target Relation</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation NODE___TARGET_RELATION = eINSTANCE.getNode__TargetRelation();

		/**
		 * The meta object literal for the '{@link sml2.impl.OrderedComparativeLinkImpl <em>Ordered Comparative Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.OrderedComparativeLinkImpl
		 * @see sml2.impl.Sml2PackageImpl#getOrderedComparativeLink()
		 * @generated
		 */
		EClass ORDERED_COMPARATIVE_LINK = eINSTANCE.getOrderedComparativeLink();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORDERED_COMPARATIVE_LINK__TYPE = eINSTANCE.getOrderedComparativeLink_Type();

		/**
		 * The meta object literal for the '{@link sml2.impl.ParticipantImpl <em>Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.ParticipantImpl
		 * @see sml2.impl.Sml2PackageImpl#getParticipant()
		 * @generated
		 */
		EClass PARTICIPANT = eINSTANCE.getParticipant();

		/**
		 * The meta object literal for the '<em><b>Immutable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT__IMMUTABLE = eINSTANCE.getParticipant_Immutable();

		/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT__MIN = eINSTANCE.getParticipant_Min();

		/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT__MAX = eINSTANCE.getParticipant_Max();

		/**
		 * The meta object literal for the '<em><b>Is Image Of</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__IS_IMAGE_OF = eINSTANCE.getParticipant_IsImageOf();

		/**
		 * The meta object literal for the '{@link sml2.impl.QualityLiteralImpl <em>Quality Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.QualityLiteralImpl
		 * @see sml2.impl.Sml2PackageImpl#getQualityLiteral()
		 * @generated
		 */
		EClass QUALITY_LITERAL = eINSTANCE.getQualityLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITY_LITERAL__VALUE = eINSTANCE.getQualityLiteral_Value();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_LITERAL__TYPE = eINSTANCE.getQualityLiteral_Type();

		/**
		 * The meta object literal for the '{@link sml2.impl.ReferableElementImpl <em>Referable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.ReferableElementImpl
		 * @see sml2.impl.Sml2PackageImpl#getReferableElement()
		 * @generated
		 */
		EClass REFERABLE_ELEMENT = eINSTANCE.getReferableElement();

		/**
		 * The meta object literal for the '{@link sml2.impl.ReferenceNodeImpl <em>Reference Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.ReferenceNodeImpl
		 * @see sml2.impl.Sml2PackageImpl#getReferenceNode()
		 * @generated
		 */
		EClass REFERENCE_NODE = eINSTANCE.getReferenceNode();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_NODE__LABEL = eINSTANCE.getReferenceNode_Label();

		/**
		 * The meta object literal for the '<em><b>Situation</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_NODE__SITUATION = eINSTANCE.getReferenceNode_Situation();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_NODE__REFERENCE = eINSTANCE.getReferenceNode_Reference();

		/**
		 * The meta object literal for the '{@link sml2.impl.RelatorParticipantImpl <em>Relator Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.RelatorParticipantImpl
		 * @see sml2.impl.Sml2PackageImpl#getRelatorParticipant()
		 * @generated
		 */
		EClass RELATOR_PARTICIPANT = eINSTANCE.getRelatorParticipant();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATOR_PARTICIPANT__TYPE = eINSTANCE.getRelatorParticipant_Type();

		/**
		 * The meta object literal for the '{@link sml2.impl.SelfReferenceImpl <em>Self Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.SelfReferenceImpl
		 * @see sml2.impl.Sml2PackageImpl#getSelfReference()
		 * @generated
		 */
		EClass SELF_REFERENCE = eINSTANCE.getSelfReference();

		/**
		 * The meta object literal for the '{@link sml2.impl.SituationParticipantImpl <em>Situation Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.SituationParticipantImpl
		 * @see sml2.impl.Sml2PackageImpl#getSituationParticipant()
		 * @generated
		 */
		EClass SITUATION_PARTICIPANT = eINSTANCE.getSituationParticipant();

		/**
		 * The meta object literal for the '<em><b>Temporality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITUATION_PARTICIPANT__TEMPORALITY = eINSTANCE.getSituationParticipant_Temporality();

		/**
		 * The meta object literal for the '<em><b>References</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_PARTICIPANT__REFERENCES = eINSTANCE.getSituationParticipant_References();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_PARTICIPANT__TYPE = eINSTANCE.getSituationParticipant_Type();

		/**
		 * The meta object literal for the '{@link sml2.impl.SituationTypeImpl <em>Situation Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.SituationTypeImpl
		 * @see sml2.impl.Sml2PackageImpl#getSituationType()
		 * @generated
		 */
		EClass SITUATION_TYPE = eINSTANCE.getSituationType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITUATION_TYPE__NAME = eINSTANCE.getSituationType_Name();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_TYPE__ELEMENTS = eINSTANCE.getSituationType_Elements();

		/**
		 * The meta object literal for the '{@link sml2.impl.SituationTypeAssociationImpl <em>Situation Type Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.SituationTypeAssociationImpl
		 * @see sml2.impl.Sml2PackageImpl#getSituationTypeAssociation()
		 * @generated
		 */
		EClass SITUATION_TYPE_ASSOCIATION = eINSTANCE.getSituationTypeAssociation();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_TYPE_ASSOCIATION__SOURCE = eINSTANCE.getSituationTypeAssociation_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_TYPE_ASSOCIATION__TARGET = eINSTANCE.getSituationTypeAssociation_Target();

		/**
		 * The meta object literal for the '{@link sml2.impl.SituationTypeElementImpl <em>Situation Type Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.SituationTypeElementImpl
		 * @see sml2.impl.Sml2PackageImpl#getSituationTypeElement()
		 * @generated
		 */
		EClass SITUATION_TYPE_ELEMENT = eINSTANCE.getSituationTypeElement();

		/**
		 * The meta object literal for the '<em><b>Get Situation</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SITUATION_TYPE_ELEMENT___GET_SITUATION = eINSTANCE.getSituationTypeElement__GetSituation();

		/**
		 * The meta object literal for the '{@link sml2.impl.SMLModelImpl <em>SML Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.SMLModelImpl
		 * @see sml2.impl.Sml2PackageImpl#getSMLModel()
		 * @generated
		 */
		EClass SML_MODEL = eINSTANCE.getSMLModel();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SML_MODEL__ELEMENTS = eINSTANCE.getSMLModel_Elements();

		/**
		 * The meta object literal for the '<em><b>Context Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SML_MODEL__CONTEXT_MODEL = eINSTANCE.getSMLModel_ContextModel();

		/**
		 * The meta object literal for the '{@link sml2.impl.TypeLiteralImpl <em>Type Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.TypeLiteralImpl
		 * @see sml2.impl.Sml2PackageImpl#getTypeLiteral()
		 * @generated
		 */
		EClass TYPE_LITERAL = eINSTANCE.getTypeLiteral();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_LITERAL__TYPE = eINSTANCE.getTypeLiteral_Type();

		/**
		 * The meta object literal for the '{@link sml2.AllenKind <em>Allen Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.AllenKind
		 * @see sml2.impl.Sml2PackageImpl#getAllenKind()
		 * @generated
		 */
		EEnum ALLEN_KIND = eINSTANCE.getAllenKind();

		/**
		 * The meta object literal for the '{@link sml2.ComparativeKind <em>Comparative Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.ComparativeKind
		 * @see sml2.impl.Sml2PackageImpl#getComparativeKind()
		 * @generated
		 */
		EEnum COMPARATIVE_KIND = eINSTANCE.getComparativeKind();

		/**
		 * The meta object literal for the '{@link sml2.TemporalKind <em>Temporal Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.TemporalKind
		 * @see sml2.impl.Sml2PackageImpl#getTemporalKind()
		 * @generated
		 */
		EEnum TEMPORAL_KIND = eINSTANCE.getTemporalKind();

	}

} //Sml2Package
