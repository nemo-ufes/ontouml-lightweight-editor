/**
 */
package sml2.impl;

import RefOntoUML.RefOntoUMLPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import sml2.AttributeReference;
import sml2.ComparativeRelation;
import sml2.EntityParticipant;
import sml2.ExistsSituation;
import sml2.ExportableNode;
import sml2.Function;
import sml2.Link;
import sml2.Literal;
import sml2.Node;
import sml2.Parameter;
import sml2.Participant;
import sml2.RelatorParticipant;
import sml2.SMLModel;
import sml2.SituationParameterReference;
import sml2.SituationParticipant;
import sml2.SituationType;
import sml2.SituationTypeBlock;
import sml2.SituationTypeElement;
import sml2.SituationTypeParameter;
import sml2.Sml2Factory;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Sml2PackageImpl extends EPackageImpl implements Sml2Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass smlModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass situationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass situationTypeBlockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass situationTypeElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exportableNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityParticipantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relatorParticipantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass situationTypeParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comparativeRelationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass participantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass situationParticipantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass situationParameterReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass existsSituationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see sml2.Sml2Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Sml2PackageImpl() {
		super(eNS_URI, Sml2Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link Sml2Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Sml2Package init() {
		if (isInited) return (Sml2Package)EPackage.Registry.INSTANCE.getEPackage(Sml2Package.eNS_URI);

		// Obtain or create and register package
		Sml2PackageImpl theSml2Package = (Sml2PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Sml2PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Sml2PackageImpl());

		isInited = true;

		// Initialize simple dependencies
		RefOntoUMLPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSml2Package.createPackageContents();

		// Initialize created meta-data
		theSml2Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSml2Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Sml2Package.eNS_URI, theSml2Package);
		return theSml2Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSMLModel() {
		return smlModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSMLModel_Elements() {
		return (EReference)smlModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSMLModel_ContextModel() {
		return (EReference)smlModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSMLModel_PrimitiveContextElements() {
		return (EReference)smlModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSituationType() {
		return situationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSituationType_Name() {
		return (EAttribute)situationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationType_Parameter() {
		return (EReference)situationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationType_Elements() {
		return (EReference)situationTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSituationTypeBlock() {
		return situationTypeBlockEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationTypeBlock_Elements() {
		return (EReference)situationTypeBlockEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSituationTypeElement() {
		return situationTypeElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNode() {
		return nodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getNode__SourceRelation() {
		return nodeEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExportableNode() {
		return exportableNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExportableNode_NodeParameter() {
		return (EReference)exportableNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntityParticipant() {
		return entityParticipantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityParticipant_IsOfType() {
		return (EReference)entityParticipantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelatorParticipant() {
		return relatorParticipantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelatorParticipant_IsOfType() {
		return (EReference)relatorParticipantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelatorParticipant_Links() {
		return (EReference)relatorParticipantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLink() {
		return linkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLink_IsOfType() {
		return (EReference)linkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLink_Entity() {
		return (EReference)linkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLink_Relator() {
		return (EReference)linkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSituationTypeParameter() {
		return situationTypeParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationTypeParameter_NodeReference() {
		return (EReference)situationTypeParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSituationTypeParameter_Name() {
		return (EAttribute)situationTypeParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttributeReference() {
		return attributeReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttributeReference_Entity() {
		return (EReference)attributeReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttributeReference_Attribute() {
		return (EReference)attributeReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComparativeRelation() {
		return comparativeRelationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComparativeRelation_Source() {
		return (EReference)comparativeRelationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComparativeRelation_Target() {
		return (EReference)comparativeRelationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComparativeRelation_Relation() {
		return (EReference)comparativeRelationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComparativeRelation_Parameter() {
		return (EAttribute)comparativeRelationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComparativeRelation_IsNegated() {
		return (EAttribute)comparativeRelationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLiteral() {
		return literalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLiteral_Value() {
		return (EAttribute)literalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLiteral_DataType() {
		return (EReference)literalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParticipant() {
		return participantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParticipant_Reference() {
		return (EReference)participantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSituationParticipant() {
		return situationParticipantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationParticipant_SituationType() {
		return (EReference)situationParticipantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationParticipant_Parameter() {
		return (EReference)situationParticipantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSituationParticipant_IsPast() {
		return (EAttribute)situationParticipantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSituationParameterReference() {
		return situationParameterReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationParameterReference_Parameter() {
		return (EReference)situationParameterReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSituationParameterReference_Situation() {
		return (EReference)situationParameterReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExistsSituation() {
		return existsSituationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExistsSituation_IsNegated() {
		return (EAttribute)existsSituationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunction() {
		return functionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunction_Parameter() {
		return (EReference)functionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunction_Function() {
		return (EReference)functionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameter() {
		return parameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameter_Value() {
		return (EReference)parameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameter_Parameter() {
		return (EReference)parameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sml2Factory getSml2Factory() {
		return (Sml2Factory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		smlModelEClass = createEClass(SML_MODEL);
		createEReference(smlModelEClass, SML_MODEL__ELEMENTS);
		createEReference(smlModelEClass, SML_MODEL__CONTEXT_MODEL);
		createEReference(smlModelEClass, SML_MODEL__PRIMITIVE_CONTEXT_ELEMENTS);

		situationTypeEClass = createEClass(SITUATION_TYPE);
		createEAttribute(situationTypeEClass, SITUATION_TYPE__NAME);
		createEReference(situationTypeEClass, SITUATION_TYPE__PARAMETER);
		createEReference(situationTypeEClass, SITUATION_TYPE__ELEMENTS);

		situationTypeBlockEClass = createEClass(SITUATION_TYPE_BLOCK);
		createEReference(situationTypeBlockEClass, SITUATION_TYPE_BLOCK__ELEMENTS);

		situationTypeElementEClass = createEClass(SITUATION_TYPE_ELEMENT);

		nodeEClass = createEClass(NODE);
		createEOperation(nodeEClass, NODE___SOURCE_RELATION);

		exportableNodeEClass = createEClass(EXPORTABLE_NODE);
		createEReference(exportableNodeEClass, EXPORTABLE_NODE__NODE_PARAMETER);

		entityParticipantEClass = createEClass(ENTITY_PARTICIPANT);
		createEReference(entityParticipantEClass, ENTITY_PARTICIPANT__IS_OF_TYPE);

		relatorParticipantEClass = createEClass(RELATOR_PARTICIPANT);
		createEReference(relatorParticipantEClass, RELATOR_PARTICIPANT__IS_OF_TYPE);
		createEReference(relatorParticipantEClass, RELATOR_PARTICIPANT__LINKS);

		linkEClass = createEClass(LINK);
		createEReference(linkEClass, LINK__IS_OF_TYPE);
		createEReference(linkEClass, LINK__ENTITY);
		createEReference(linkEClass, LINK__RELATOR);

		situationTypeParameterEClass = createEClass(SITUATION_TYPE_PARAMETER);
		createEReference(situationTypeParameterEClass, SITUATION_TYPE_PARAMETER__NODE_REFERENCE);
		createEAttribute(situationTypeParameterEClass, SITUATION_TYPE_PARAMETER__NAME);

		attributeReferenceEClass = createEClass(ATTRIBUTE_REFERENCE);
		createEReference(attributeReferenceEClass, ATTRIBUTE_REFERENCE__ENTITY);
		createEReference(attributeReferenceEClass, ATTRIBUTE_REFERENCE__ATTRIBUTE);

		comparativeRelationEClass = createEClass(COMPARATIVE_RELATION);
		createEReference(comparativeRelationEClass, COMPARATIVE_RELATION__SOURCE);
		createEReference(comparativeRelationEClass, COMPARATIVE_RELATION__TARGET);
		createEReference(comparativeRelationEClass, COMPARATIVE_RELATION__RELATION);
		createEAttribute(comparativeRelationEClass, COMPARATIVE_RELATION__PARAMETER);
		createEAttribute(comparativeRelationEClass, COMPARATIVE_RELATION__IS_NEGATED);

		literalEClass = createEClass(LITERAL);
		createEAttribute(literalEClass, LITERAL__VALUE);
		createEReference(literalEClass, LITERAL__DATA_TYPE);

		participantEClass = createEClass(PARTICIPANT);
		createEReference(participantEClass, PARTICIPANT__REFERENCE);

		situationParticipantEClass = createEClass(SITUATION_PARTICIPANT);
		createEReference(situationParticipantEClass, SITUATION_PARTICIPANT__SITUATION_TYPE);
		createEReference(situationParticipantEClass, SITUATION_PARTICIPANT__PARAMETER);
		createEAttribute(situationParticipantEClass, SITUATION_PARTICIPANT__IS_PAST);

		situationParameterReferenceEClass = createEClass(SITUATION_PARAMETER_REFERENCE);
		createEReference(situationParameterReferenceEClass, SITUATION_PARAMETER_REFERENCE__PARAMETER);
		createEReference(situationParameterReferenceEClass, SITUATION_PARAMETER_REFERENCE__SITUATION);

		existsSituationEClass = createEClass(EXISTS_SITUATION);
		createEAttribute(existsSituationEClass, EXISTS_SITUATION__IS_NEGATED);

		functionEClass = createEClass(FUNCTION);
		createEReference(functionEClass, FUNCTION__PARAMETER);
		createEReference(functionEClass, FUNCTION__FUNCTION);

		parameterEClass = createEClass(PARAMETER);
		createEReference(parameterEClass, PARAMETER__VALUE);
		createEReference(parameterEClass, PARAMETER__PARAMETER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		RefOntoUMLPackage theRefOntoUMLPackage = (RefOntoUMLPackage)EPackage.Registry.INSTANCE.getEPackage(RefOntoUMLPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		situationTypeBlockEClass.getESuperTypes().add(this.getNode());
		nodeEClass.getESuperTypes().add(this.getSituationTypeElement());
		exportableNodeEClass.getESuperTypes().add(this.getNode());
		entityParticipantEClass.getESuperTypes().add(this.getParticipant());
		relatorParticipantEClass.getESuperTypes().add(this.getParticipant());
		linkEClass.getESuperTypes().add(this.getSituationTypeElement());
		attributeReferenceEClass.getESuperTypes().add(this.getExportableNode());
		comparativeRelationEClass.getESuperTypes().add(this.getSituationTypeElement());
		literalEClass.getESuperTypes().add(this.getNode());
		participantEClass.getESuperTypes().add(this.getExportableNode());
		situationParticipantEClass.getESuperTypes().add(this.getParticipant());
		situationParameterReferenceEClass.getESuperTypes().add(this.getNode());
		existsSituationEClass.getESuperTypes().add(this.getSituationParticipant());
		functionEClass.getESuperTypes().add(this.getExportableNode());

		// Initialize classes, features, and operations; add parameters
		initEClass(smlModelEClass, SMLModel.class, "SMLModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSMLModel_Elements(), this.getSituationType(), null, "elements", null, 0, -1, SMLModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSMLModel_ContextModel(), theRefOntoUMLPackage.getModel(), null, "contextModel", null, 0, 1, SMLModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSMLModel_PrimitiveContextElements(), theRefOntoUMLPackage.getPackageableElement(), null, "primitiveContextElements", null, 0, -1, SMLModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(situationTypeEClass, SituationType.class, "SituationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSituationType_Name(), ecorePackage.getEString(), "name", null, 0, 1, SituationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSituationType_Parameter(), this.getSituationTypeParameter(), null, "parameter", null, 0, -1, SituationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSituationType_Elements(), this.getSituationTypeElement(), null, "elements", null, 0, -1, SituationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(situationTypeBlockEClass, SituationTypeBlock.class, "SituationTypeBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSituationTypeBlock_Elements(), this.getNode(), null, "elements", null, 0, -1, SituationTypeBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(situationTypeElementEClass, SituationTypeElement.class, "SituationTypeElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(nodeEClass, Node.class, "Node", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getNode__SourceRelation(), this.getComparativeRelation(), "sourceRelation", 0, -1, IS_UNIQUE, !IS_ORDERED);

		initEClass(exportableNodeEClass, ExportableNode.class, "ExportableNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExportableNode_NodeParameter(), this.getSituationTypeParameter(), this.getSituationTypeParameter_NodeReference(), "nodeParameter", null, 0, 1, ExportableNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityParticipantEClass, EntityParticipant.class, "EntityParticipant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEntityParticipant_IsOfType(), theRefOntoUMLPackage.getClass_(), null, "isOfType", null, 0, 1, EntityParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relatorParticipantEClass, RelatorParticipant.class, "RelatorParticipant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRelatorParticipant_IsOfType(), theRefOntoUMLPackage.getRelator(), null, "isOfType", null, 0, 1, RelatorParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelatorParticipant_Links(), this.getLink(), this.getLink_Relator(), "links", null, 0, -1, RelatorParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linkEClass, Link.class, "Link", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLink_IsOfType(), theRefOntoUMLPackage.getMediation(), null, "isOfType", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLink_Entity(), this.getEntityParticipant(), null, "entity", null, 1, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLink_Relator(), this.getRelatorParticipant(), this.getRelatorParticipant_Links(), "relator", null, 1, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(situationTypeParameterEClass, SituationTypeParameter.class, "SituationTypeParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSituationTypeParameter_NodeReference(), this.getExportableNode(), this.getExportableNode_NodeParameter(), "nodeReference", null, 1, 1, SituationTypeParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSituationTypeParameter_Name(), ecorePackage.getEString(), "name", null, 0, 1, SituationTypeParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeReferenceEClass, AttributeReference.class, "AttributeReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttributeReference_Entity(), this.getParticipant(), this.getParticipant_Reference(), "entity", null, 1, 1, AttributeReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttributeReference_Attribute(), theRefOntoUMLPackage.getProperty(), null, "attribute", null, 0, 1, AttributeReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(comparativeRelationEClass, ComparativeRelation.class, "ComparativeRelation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComparativeRelation_Source(), this.getNode(), null, "source", null, 1, 1, ComparativeRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComparativeRelation_Target(), this.getNode(), null, "target", null, 1, 1, ComparativeRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComparativeRelation_Relation(), theRefOntoUMLPackage.getFormalAssociation(), null, "relation", null, 0, 1, ComparativeRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComparativeRelation_Parameter(), ecorePackage.getEString(), "parameter", null, 0, 1, ComparativeRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComparativeRelation_IsNegated(), ecorePackage.getEBoolean(), "isNegated", null, 0, 1, ComparativeRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(literalEClass, Literal.class, "Literal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLiteral_Value(), ecorePackage.getEString(), "value", null, 0, 1, Literal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLiteral_DataType(), theRefOntoUMLPackage.getDataType(), null, "dataType", null, 0, 1, Literal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(participantEClass, Participant.class, "Participant", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParticipant_Reference(), this.getAttributeReference(), this.getAttributeReference_Entity(), "reference", null, 0, -1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(situationParticipantEClass, SituationParticipant.class, "SituationParticipant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSituationParticipant_SituationType(), this.getSituationType(), null, "situationType", null, 1, 1, SituationParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSituationParticipant_Parameter(), this.getSituationParameterReference(), this.getSituationParameterReference_Situation(), "parameter", null, 0, -1, SituationParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSituationParticipant_IsPast(), ecorePackage.getEBoolean(), "isPast", null, 0, 1, SituationParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(situationParameterReferenceEClass, SituationParameterReference.class, "SituationParameterReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSituationParameterReference_Parameter(), this.getSituationTypeParameter(), null, "parameter", null, 1, 1, SituationParameterReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSituationParameterReference_Situation(), this.getSituationParticipant(), this.getSituationParticipant_Parameter(), "situation", null, 1, 1, SituationParameterReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(existsSituationEClass, ExistsSituation.class, "ExistsSituation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExistsSituation_IsNegated(), ecorePackage.getEBoolean(), "isNegated", null, 0, 1, ExistsSituation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(functionEClass, Function.class, "Function", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFunction_Parameter(), this.getParameter(), null, "parameter", null, 0, -1, Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunction_Function(), theRefOntoUMLPackage.getFormalAssociation(), null, "function", null, 0, 1, Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameter_Value(), this.getNode(), null, "value", null, 1, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParameter_Parameter(), theRefOntoUMLPackage.getDataType(), null, "parameter", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/ocl/examples/OCL
		createOCLAnnotations();
		// Comments
		createCommentsAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/ocl/examples/OCL</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOCLAnnotations() {
		String source = "http://www.eclipse.org/ocl/examples/OCL";	
		addAnnotation
		  (getNode__SourceRelation(), 
		   source, 
		   new String[] {
			 "body", "ComparativeRelation.allInstances()->select(r | r.source = self)"
		   });
	}

	/**
	 * Initializes the annotations for <b>Comments</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createCommentsAnnotations() {
		String source = "Comments";	
		addAnnotation
		  (getNode__SourceRelation(), 
		   source, 
		   new String[] {
			 "associations", "Gets all comparative relations in which the element is source"
		   });
	}

} //Sml2PackageImpl
