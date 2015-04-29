/**
 */
package sml2;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
	String eNS_URI = "http://www.example.org/sml2";

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
	 * The meta object id for the '{@link sml2.impl.SMLModelImpl <em>SML Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SMLModelImpl
	 * @see sml2.impl.Sml2PackageImpl#getSMLModel()
	 * @generated
	 */
	int SML_MODEL = 0;

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
	 * The feature id for the '<em><b>Primitive Context Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SML_MODEL__PRIMITIVE_CONTEXT_ELEMENTS = 2;

	/**
	 * The number of structural features of the '<em>SML Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SML_MODEL_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>SML Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SML_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link sml2.impl.SituationTypeImpl <em>Situation Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SituationTypeImpl
	 * @see sml2.impl.Sml2PackageImpl#getSituationType()
	 * @generated
	 */
	int SITUATION_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE__PARAMETER = 1;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE__ELEMENTS = 2;

	/**
	 * The number of structural features of the '<em>Situation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Situation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link sml2.impl.SituationTypeElementImpl <em>Situation Type Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SituationTypeElementImpl
	 * @see sml2.impl.Sml2PackageImpl#getSituationTypeElement()
	 * @generated
	 */
	int SITUATION_TYPE_ELEMENT = 3;

	/**
	 * The number of structural features of the '<em>Situation Type Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Situation Type Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link sml2.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.NodeImpl
	 * @see sml2.impl.Sml2PackageImpl#getNode()
	 * @generated
	 */
	int NODE = 4;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE___SOURCE_RELATION = SITUATION_TYPE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = SITUATION_TYPE_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link sml2.impl.SituationTypeBlockImpl <em>Situation Type Block</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SituationTypeBlockImpl
	 * @see sml2.impl.Sml2PackageImpl#getSituationTypeBlock()
	 * @generated
	 */
	int SITUATION_TYPE_BLOCK = 2;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_BLOCK__ELEMENTS = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Situation Type Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_BLOCK_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_BLOCK___SOURCE_RELATION = NODE___SOURCE_RELATION;

	/**
	 * The number of operations of the '<em>Situation Type Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_BLOCK_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.ExportableNodeImpl <em>Exportable Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.ExportableNodeImpl
	 * @see sml2.impl.Sml2PackageImpl#getExportableNode()
	 * @generated
	 */
	int EXPORTABLE_NODE = 5;

	/**
	 * The feature id for the '<em><b>Node Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORTABLE_NODE__NODE_PARAMETER = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Exportable Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORTABLE_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORTABLE_NODE___SOURCE_RELATION = NODE___SOURCE_RELATION;

	/**
	 * The number of operations of the '<em>Exportable Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORTABLE_NODE_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.ParticipantImpl <em>Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.ParticipantImpl
	 * @see sml2.impl.Sml2PackageImpl#getParticipant()
	 * @generated
	 */
	int PARTICIPANT = 13;

	/**
	 * The feature id for the '<em><b>Node Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__NODE_PARAMETER = EXPORTABLE_NODE__NODE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__REFERENCE = EXPORTABLE_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_FEATURE_COUNT = EXPORTABLE_NODE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT___SOURCE_RELATION = EXPORTABLE_NODE___SOURCE_RELATION;

	/**
	 * The number of operations of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_OPERATION_COUNT = EXPORTABLE_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.EntityParticipantImpl <em>Entity Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.EntityParticipantImpl
	 * @see sml2.impl.Sml2PackageImpl#getEntityParticipant()
	 * @generated
	 */
	int ENTITY_PARTICIPANT = 6;

	/**
	 * The feature id for the '<em><b>Node Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT__NODE_PARAMETER = PARTICIPANT__NODE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT__REFERENCE = PARTICIPANT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Is Of Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT__IS_OF_TYPE = PARTICIPANT_FEATURE_COUNT + 0;

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
	 * The number of operations of the '<em>Entity Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARTICIPANT_OPERATION_COUNT = PARTICIPANT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.RelatorParticipantImpl <em>Relator Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.RelatorParticipantImpl
	 * @see sml2.impl.Sml2PackageImpl#getRelatorParticipant()
	 * @generated
	 */
	int RELATOR_PARTICIPANT = 7;

	/**
	 * The feature id for the '<em><b>Node Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT__NODE_PARAMETER = PARTICIPANT__NODE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT__REFERENCE = PARTICIPANT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Is Of Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT__IS_OF_TYPE = PARTICIPANT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT__LINKS = PARTICIPANT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Relator Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT_FEATURE_COUNT = PARTICIPANT_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT___SOURCE_RELATION = PARTICIPANT___SOURCE_RELATION;

	/**
	 * The number of operations of the '<em>Relator Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATOR_PARTICIPANT_OPERATION_COUNT = PARTICIPANT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.LinkImpl <em>Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.LinkImpl
	 * @see sml2.impl.Sml2PackageImpl#getLink()
	 * @generated
	 */
	int LINK = 8;

	/**
	 * The feature id for the '<em><b>Is Of Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__IS_OF_TYPE = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__ENTITY = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Relator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__RELATOR = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_OPERATION_COUNT = SITUATION_TYPE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.SituationTypeParameterImpl <em>Situation Type Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SituationTypeParameterImpl
	 * @see sml2.impl.Sml2PackageImpl#getSituationTypeParameter()
	 * @generated
	 */
	int SITUATION_TYPE_PARAMETER = 9;

	/**
	 * The feature id for the '<em><b>Node Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_PARAMETER__NODE_REFERENCE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_PARAMETER__NAME = 1;

	/**
	 * The number of structural features of the '<em>Situation Type Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_PARAMETER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Situation Type Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_TYPE_PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link sml2.impl.AttributeReferenceImpl <em>Attribute Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.AttributeReferenceImpl
	 * @see sml2.impl.Sml2PackageImpl#getAttributeReference()
	 * @generated
	 */
	int ATTRIBUTE_REFERENCE = 10;

	/**
	 * The feature id for the '<em><b>Node Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE__NODE_PARAMETER = EXPORTABLE_NODE__NODE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE__ENTITY = EXPORTABLE_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE__ATTRIBUTE = EXPORTABLE_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Attribute Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE_FEATURE_COUNT = EXPORTABLE_NODE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE___SOURCE_RELATION = EXPORTABLE_NODE___SOURCE_RELATION;

	/**
	 * The number of operations of the '<em>Attribute Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_REFERENCE_OPERATION_COUNT = EXPORTABLE_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.ComparativeRelationImpl <em>Comparative Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.ComparativeRelationImpl
	 * @see sml2.impl.Sml2PackageImpl#getComparativeRelation()
	 * @generated
	 */
	int COMPARATIVE_RELATION = 11;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION__SOURCE = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION__TARGET = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Relation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION__RELATION = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION__PARAMETER = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Is Negated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION__IS_NEGATED = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Comparative Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION_FEATURE_COUNT = SITUATION_TYPE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Comparative Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARATIVE_RELATION_OPERATION_COUNT = SITUATION_TYPE_ELEMENT_OPERATION_COUNT + 0;

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
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL__VALUE = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL__DATA_TYPE = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL___SOURCE_RELATION = NODE___SOURCE_RELATION;

	/**
	 * The number of operations of the '<em>Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.SituationParticipantImpl <em>Situation Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SituationParticipantImpl
	 * @see sml2.impl.Sml2PackageImpl#getSituationParticipant()
	 * @generated
	 */
	int SITUATION_PARTICIPANT = 14;

	/**
	 * The feature id for the '<em><b>Node Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT__NODE_PARAMETER = PARTICIPANT__NODE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT__REFERENCE = PARTICIPANT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Situation Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT__SITUATION_TYPE = PARTICIPANT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT__PARAMETER = PARTICIPANT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Past</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT__IS_PAST = PARTICIPANT_FEATURE_COUNT + 2;

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
	 * The number of operations of the '<em>Situation Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARTICIPANT_OPERATION_COUNT = PARTICIPANT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.SituationParameterReferenceImpl <em>Situation Parameter Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.SituationParameterReferenceImpl
	 * @see sml2.impl.Sml2PackageImpl#getSituationParameterReference()
	 * @generated
	 */
	int SITUATION_PARAMETER_REFERENCE = 15;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARAMETER_REFERENCE__PARAMETER = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Situation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARAMETER_REFERENCE__SITUATION = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Situation Parameter Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARAMETER_REFERENCE_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARAMETER_REFERENCE___SOURCE_RELATION = NODE___SOURCE_RELATION;

	/**
	 * The number of operations of the '<em>Situation Parameter Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITUATION_PARAMETER_REFERENCE_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.ExistsSituationImpl <em>Exists Situation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.ExistsSituationImpl
	 * @see sml2.impl.Sml2PackageImpl#getExistsSituation()
	 * @generated
	 */
	int EXISTS_SITUATION = 16;

	/**
	 * The feature id for the '<em><b>Node Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTS_SITUATION__NODE_PARAMETER = SITUATION_PARTICIPANT__NODE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTS_SITUATION__REFERENCE = SITUATION_PARTICIPANT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Situation Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTS_SITUATION__SITUATION_TYPE = SITUATION_PARTICIPANT__SITUATION_TYPE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTS_SITUATION__PARAMETER = SITUATION_PARTICIPANT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Is Past</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTS_SITUATION__IS_PAST = SITUATION_PARTICIPANT__IS_PAST;

	/**
	 * The feature id for the '<em><b>Is Negated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTS_SITUATION__IS_NEGATED = SITUATION_PARTICIPANT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Exists Situation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTS_SITUATION_FEATURE_COUNT = SITUATION_PARTICIPANT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTS_SITUATION___SOURCE_RELATION = SITUATION_PARTICIPANT___SOURCE_RELATION;

	/**
	 * The number of operations of the '<em>Exists Situation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTS_SITUATION_OPERATION_COUNT = SITUATION_PARTICIPANT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.FunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.FunctionImpl
	 * @see sml2.impl.Sml2PackageImpl#getFunction()
	 * @generated
	 */
	int FUNCTION = 17;

	/**
	 * The feature id for the '<em><b>Node Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__NODE_PARAMETER = EXPORTABLE_NODE__NODE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__PARAMETER = EXPORTABLE_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__FUNCTION = EXPORTABLE_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_FEATURE_COUNT = EXPORTABLE_NODE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Source Relation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION___SOURCE_RELATION = EXPORTABLE_NODE___SOURCE_RELATION;

	/**
	 * The number of operations of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_OPERATION_COUNT = EXPORTABLE_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link sml2.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see sml2.impl.ParameterImpl
	 * @see sml2.impl.Sml2PackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 18;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__PARAMETER = 1;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_OPERATION_COUNT = 0;


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
	 * Returns the meta object for the containment reference list '{@link sml2.SMLModel#getPrimitiveContextElements <em>Primitive Context Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Primitive Context Elements</em>'.
	 * @see sml2.SMLModel#getPrimitiveContextElements()
	 * @see #getSMLModel()
	 * @generated
	 */
	EReference getSMLModel_PrimitiveContextElements();

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
	 * Returns the meta object for the containment reference list '{@link sml2.SituationType#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter</em>'.
	 * @see sml2.SituationType#getParameter()
	 * @see #getSituationType()
	 * @generated
	 */
	EReference getSituationType_Parameter();

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
	 * Returns the meta object for class '{@link sml2.SituationTypeBlock <em>Situation Type Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Situation Type Block</em>'.
	 * @see sml2.SituationTypeBlock
	 * @generated
	 */
	EClass getSituationTypeBlock();

	/**
	 * Returns the meta object for the containment reference list '{@link sml2.SituationTypeBlock#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see sml2.SituationTypeBlock#getElements()
	 * @see #getSituationTypeBlock()
	 * @generated
	 */
	EReference getSituationTypeBlock_Elements();

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
	 * Returns the meta object for class '{@link sml2.ExportableNode <em>Exportable Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exportable Node</em>'.
	 * @see sml2.ExportableNode
	 * @generated
	 */
	EClass getExportableNode();

	/**
	 * Returns the meta object for the reference '{@link sml2.ExportableNode#getNodeParameter <em>Node Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Node Parameter</em>'.
	 * @see sml2.ExportableNode#getNodeParameter()
	 * @see #getExportableNode()
	 * @generated
	 */
	EReference getExportableNode_NodeParameter();

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
	 * Returns the meta object for the reference '{@link sml2.EntityParticipant#getIsOfType <em>Is Of Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Is Of Type</em>'.
	 * @see sml2.EntityParticipant#getIsOfType()
	 * @see #getEntityParticipant()
	 * @generated
	 */
	EReference getEntityParticipant_IsOfType();

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
	 * Returns the meta object for the reference '{@link sml2.RelatorParticipant#getIsOfType <em>Is Of Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Is Of Type</em>'.
	 * @see sml2.RelatorParticipant#getIsOfType()
	 * @see #getRelatorParticipant()
	 * @generated
	 */
	EReference getRelatorParticipant_IsOfType();

	/**
	 * Returns the meta object for the reference list '{@link sml2.RelatorParticipant#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Links</em>'.
	 * @see sml2.RelatorParticipant#getLinks()
	 * @see #getRelatorParticipant()
	 * @generated
	 */
	EReference getRelatorParticipant_Links();

	/**
	 * Returns the meta object for class '{@link sml2.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link</em>'.
	 * @see sml2.Link
	 * @generated
	 */
	EClass getLink();

	/**
	 * Returns the meta object for the reference '{@link sml2.Link#getIsOfType <em>Is Of Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Is Of Type</em>'.
	 * @see sml2.Link#getIsOfType()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_IsOfType();

	/**
	 * Returns the meta object for the reference '{@link sml2.Link#getEntity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entity</em>'.
	 * @see sml2.Link#getEntity()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Entity();

	/**
	 * Returns the meta object for the reference '{@link sml2.Link#getRelator <em>Relator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Relator</em>'.
	 * @see sml2.Link#getRelator()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Relator();

	/**
	 * Returns the meta object for class '{@link sml2.SituationTypeParameter <em>Situation Type Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Situation Type Parameter</em>'.
	 * @see sml2.SituationTypeParameter
	 * @generated
	 */
	EClass getSituationTypeParameter();

	/**
	 * Returns the meta object for the reference '{@link sml2.SituationTypeParameter#getNodeReference <em>Node Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Node Reference</em>'.
	 * @see sml2.SituationTypeParameter#getNodeReference()
	 * @see #getSituationTypeParameter()
	 * @generated
	 */
	EReference getSituationTypeParameter_NodeReference();

	/**
	 * Returns the meta object for the attribute '{@link sml2.SituationTypeParameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see sml2.SituationTypeParameter#getName()
	 * @see #getSituationTypeParameter()
	 * @generated
	 */
	EAttribute getSituationTypeParameter_Name();

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
	 * Returns the meta object for the reference '{@link sml2.AttributeReference#getEntity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entity</em>'.
	 * @see sml2.AttributeReference#getEntity()
	 * @see #getAttributeReference()
	 * @generated
	 */
	EReference getAttributeReference_Entity();

	/**
	 * Returns the meta object for the reference '{@link sml2.AttributeReference#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Attribute</em>'.
	 * @see sml2.AttributeReference#getAttribute()
	 * @see #getAttributeReference()
	 * @generated
	 */
	EReference getAttributeReference_Attribute();

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
	 * Returns the meta object for the reference '{@link sml2.ComparativeRelation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see sml2.ComparativeRelation#getSource()
	 * @see #getComparativeRelation()
	 * @generated
	 */
	EReference getComparativeRelation_Source();

	/**
	 * Returns the meta object for the reference '{@link sml2.ComparativeRelation#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see sml2.ComparativeRelation#getTarget()
	 * @see #getComparativeRelation()
	 * @generated
	 */
	EReference getComparativeRelation_Target();

	/**
	 * Returns the meta object for the reference '{@link sml2.ComparativeRelation#getRelation <em>Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Relation</em>'.
	 * @see sml2.ComparativeRelation#getRelation()
	 * @see #getComparativeRelation()
	 * @generated
	 */
	EReference getComparativeRelation_Relation();

	/**
	 * Returns the meta object for the attribute '{@link sml2.ComparativeRelation#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter</em>'.
	 * @see sml2.ComparativeRelation#getParameter()
	 * @see #getComparativeRelation()
	 * @generated
	 */
	EAttribute getComparativeRelation_Parameter();

	/**
	 * Returns the meta object for the attribute '{@link sml2.ComparativeRelation#isIsNegated <em>Is Negated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Negated</em>'.
	 * @see sml2.ComparativeRelation#isIsNegated()
	 * @see #getComparativeRelation()
	 * @generated
	 */
	EAttribute getComparativeRelation_IsNegated();

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
	 * Returns the meta object for the attribute '{@link sml2.Literal#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see sml2.Literal#getValue()
	 * @see #getLiteral()
	 * @generated
	 */
	EAttribute getLiteral_Value();

	/**
	 * Returns the meta object for the reference '{@link sml2.Literal#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data Type</em>'.
	 * @see sml2.Literal#getDataType()
	 * @see #getLiteral()
	 * @generated
	 */
	EReference getLiteral_DataType();

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
	 * Returns the meta object for the reference list '{@link sml2.Participant#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reference</em>'.
	 * @see sml2.Participant#getReference()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Reference();

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
	 * Returns the meta object for the reference '{@link sml2.SituationParticipant#getSituationType <em>Situation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Situation Type</em>'.
	 * @see sml2.SituationParticipant#getSituationType()
	 * @see #getSituationParticipant()
	 * @generated
	 */
	EReference getSituationParticipant_SituationType();

	/**
	 * Returns the meta object for the reference list '{@link sml2.SituationParticipant#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameter</em>'.
	 * @see sml2.SituationParticipant#getParameter()
	 * @see #getSituationParticipant()
	 * @generated
	 */
	EReference getSituationParticipant_Parameter();

	/**
	 * Returns the meta object for the attribute '{@link sml2.SituationParticipant#isIsPast <em>Is Past</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Past</em>'.
	 * @see sml2.SituationParticipant#isIsPast()
	 * @see #getSituationParticipant()
	 * @generated
	 */
	EAttribute getSituationParticipant_IsPast();

	/**
	 * Returns the meta object for class '{@link sml2.SituationParameterReference <em>Situation Parameter Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Situation Parameter Reference</em>'.
	 * @see sml2.SituationParameterReference
	 * @generated
	 */
	EClass getSituationParameterReference();

	/**
	 * Returns the meta object for the reference '{@link sml2.SituationParameterReference#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see sml2.SituationParameterReference#getParameter()
	 * @see #getSituationParameterReference()
	 * @generated
	 */
	EReference getSituationParameterReference_Parameter();

	/**
	 * Returns the meta object for the reference '{@link sml2.SituationParameterReference#getSituation <em>Situation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Situation</em>'.
	 * @see sml2.SituationParameterReference#getSituation()
	 * @see #getSituationParameterReference()
	 * @generated
	 */
	EReference getSituationParameterReference_Situation();

	/**
	 * Returns the meta object for class '{@link sml2.ExistsSituation <em>Exists Situation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exists Situation</em>'.
	 * @see sml2.ExistsSituation
	 * @generated
	 */
	EClass getExistsSituation();

	/**
	 * Returns the meta object for the attribute '{@link sml2.ExistsSituation#isIsNegated <em>Is Negated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Negated</em>'.
	 * @see sml2.ExistsSituation#isIsNegated()
	 * @see #getExistsSituation()
	 * @generated
	 */
	EAttribute getExistsSituation_IsNegated();

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
	 * Returns the meta object for the containment reference list '{@link sml2.Function#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter</em>'.
	 * @see sml2.Function#getParameter()
	 * @see #getFunction()
	 * @generated
	 */
	EReference getFunction_Parameter();

	/**
	 * Returns the meta object for the reference '{@link sml2.Function#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Function</em>'.
	 * @see sml2.Function#getFunction()
	 * @see #getFunction()
	 * @generated
	 */
	EReference getFunction_Function();

	/**
	 * Returns the meta object for class '{@link sml2.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see sml2.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the reference '{@link sml2.Parameter#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see sml2.Parameter#getValue()
	 * @see #getParameter()
	 * @generated
	 */
	EReference getParameter_Value();

	/**
	 * Returns the meta object for the reference '{@link sml2.Parameter#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see sml2.Parameter#getParameter()
	 * @see #getParameter()
	 * @generated
	 */
	EReference getParameter_Parameter();

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
		 * The meta object literal for the '<em><b>Primitive Context Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SML_MODEL__PRIMITIVE_CONTEXT_ELEMENTS = eINSTANCE.getSMLModel_PrimitiveContextElements();

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
		 * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_TYPE__PARAMETER = eINSTANCE.getSituationType_Parameter();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_TYPE__ELEMENTS = eINSTANCE.getSituationType_Elements();

		/**
		 * The meta object literal for the '{@link sml2.impl.SituationTypeBlockImpl <em>Situation Type Block</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.SituationTypeBlockImpl
		 * @see sml2.impl.Sml2PackageImpl#getSituationTypeBlock()
		 * @generated
		 */
		EClass SITUATION_TYPE_BLOCK = eINSTANCE.getSituationTypeBlock();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_TYPE_BLOCK__ELEMENTS = eINSTANCE.getSituationTypeBlock_Elements();

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
		 * The meta object literal for the '{@link sml2.impl.ExportableNodeImpl <em>Exportable Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.ExportableNodeImpl
		 * @see sml2.impl.Sml2PackageImpl#getExportableNode()
		 * @generated
		 */
		EClass EXPORTABLE_NODE = eINSTANCE.getExportableNode();

		/**
		 * The meta object literal for the '<em><b>Node Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPORTABLE_NODE__NODE_PARAMETER = eINSTANCE.getExportableNode_NodeParameter();

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
		 * The meta object literal for the '<em><b>Is Of Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_PARTICIPANT__IS_OF_TYPE = eINSTANCE.getEntityParticipant_IsOfType();

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
		 * The meta object literal for the '<em><b>Is Of Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATOR_PARTICIPANT__IS_OF_TYPE = eINSTANCE.getRelatorParticipant_IsOfType();

		/**
		 * The meta object literal for the '<em><b>Links</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATOR_PARTICIPANT__LINKS = eINSTANCE.getRelatorParticipant_Links();

		/**
		 * The meta object literal for the '{@link sml2.impl.LinkImpl <em>Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.LinkImpl
		 * @see sml2.impl.Sml2PackageImpl#getLink()
		 * @generated
		 */
		EClass LINK = eINSTANCE.getLink();

		/**
		 * The meta object literal for the '<em><b>Is Of Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__IS_OF_TYPE = eINSTANCE.getLink_IsOfType();

		/**
		 * The meta object literal for the '<em><b>Entity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__ENTITY = eINSTANCE.getLink_Entity();

		/**
		 * The meta object literal for the '<em><b>Relator</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__RELATOR = eINSTANCE.getLink_Relator();

		/**
		 * The meta object literal for the '{@link sml2.impl.SituationTypeParameterImpl <em>Situation Type Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.SituationTypeParameterImpl
		 * @see sml2.impl.Sml2PackageImpl#getSituationTypeParameter()
		 * @generated
		 */
		EClass SITUATION_TYPE_PARAMETER = eINSTANCE.getSituationTypeParameter();

		/**
		 * The meta object literal for the '<em><b>Node Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_TYPE_PARAMETER__NODE_REFERENCE = eINSTANCE.getSituationTypeParameter_NodeReference();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITUATION_TYPE_PARAMETER__NAME = eINSTANCE.getSituationTypeParameter_Name();

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
		 * The meta object literal for the '<em><b>Entity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE_REFERENCE__ENTITY = eINSTANCE.getAttributeReference_Entity();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE_REFERENCE__ATTRIBUTE = eINSTANCE.getAttributeReference_Attribute();

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
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARATIVE_RELATION__SOURCE = eINSTANCE.getComparativeRelation_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARATIVE_RELATION__TARGET = eINSTANCE.getComparativeRelation_Target();

		/**
		 * The meta object literal for the '<em><b>Relation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARATIVE_RELATION__RELATION = eINSTANCE.getComparativeRelation_Relation();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARATIVE_RELATION__PARAMETER = eINSTANCE.getComparativeRelation_Parameter();

		/**
		 * The meta object literal for the '<em><b>Is Negated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARATIVE_RELATION__IS_NEGATED = eINSTANCE.getComparativeRelation_IsNegated();

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
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LITERAL__VALUE = eINSTANCE.getLiteral_Value();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LITERAL__DATA_TYPE = eINSTANCE.getLiteral_DataType();

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
		 * The meta object literal for the '<em><b>Reference</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__REFERENCE = eINSTANCE.getParticipant_Reference();

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
		 * The meta object literal for the '<em><b>Situation Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_PARTICIPANT__SITUATION_TYPE = eINSTANCE.getSituationParticipant_SituationType();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_PARTICIPANT__PARAMETER = eINSTANCE.getSituationParticipant_Parameter();

		/**
		 * The meta object literal for the '<em><b>Is Past</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITUATION_PARTICIPANT__IS_PAST = eINSTANCE.getSituationParticipant_IsPast();

		/**
		 * The meta object literal for the '{@link sml2.impl.SituationParameterReferenceImpl <em>Situation Parameter Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.SituationParameterReferenceImpl
		 * @see sml2.impl.Sml2PackageImpl#getSituationParameterReference()
		 * @generated
		 */
		EClass SITUATION_PARAMETER_REFERENCE = eINSTANCE.getSituationParameterReference();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_PARAMETER_REFERENCE__PARAMETER = eINSTANCE.getSituationParameterReference_Parameter();

		/**
		 * The meta object literal for the '<em><b>Situation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITUATION_PARAMETER_REFERENCE__SITUATION = eINSTANCE.getSituationParameterReference_Situation();

		/**
		 * The meta object literal for the '{@link sml2.impl.ExistsSituationImpl <em>Exists Situation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.ExistsSituationImpl
		 * @see sml2.impl.Sml2PackageImpl#getExistsSituation()
		 * @generated
		 */
		EClass EXISTS_SITUATION = eINSTANCE.getExistsSituation();

		/**
		 * The meta object literal for the '<em><b>Is Negated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXISTS_SITUATION__IS_NEGATED = eINSTANCE.getExistsSituation_IsNegated();

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
		 * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION__PARAMETER = eINSTANCE.getFunction_Parameter();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION__FUNCTION = eINSTANCE.getFunction_Function();

		/**
		 * The meta object literal for the '{@link sml2.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see sml2.impl.ParameterImpl
		 * @see sml2.impl.Sml2PackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER__VALUE = eINSTANCE.getParameter_Value();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER__PARAMETER = eINSTANCE.getParameter_Parameter();

	}

} //Sml2Package
