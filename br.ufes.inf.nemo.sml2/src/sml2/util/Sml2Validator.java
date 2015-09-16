/**
 */
package sml2.util;


import java.util.Map;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;
import sml2.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see sml2.Sml2Package
 * @generated
 */
public class Sml2Validator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final Sml2Validator INSTANCE = new Sml2Validator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "sml2";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The parsed OCL expression for the definition of the '<em>sourceIsSituation</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint allenLink_sourceIsSituationInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>targetIsSituation</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint allenLink_targetIsSituationInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>sourceIsParticipant_Reference2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint attributeLink_sourceIsParticipant_Reference2InvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>targetIsAttribute</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint attributeLink_targetIsAttributeInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>sourceIsMode</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint characterizationLink_sourceIsModeInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>targetIsEntity_Reference</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint characterizationLink_targetIsEntity_ReferenceInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>sameEndsAsType3</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint characterizationLink_sameEndsAsType3InvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>sameEndsAsType2</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint contextFormalLink_sameEndsAsType2InvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>sourceIsFunction</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint functionParameter_sourceIsFunctionInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>sourceIsParticipant_Reference</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint instantiationLink_sourceIsParticipant_ReferenceInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>targetIsLiteral</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint instantiationLink_targetIsLiteralInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>sourceIsRelator</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint mediationLink_sourceIsRelatorInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>targetIsEntity</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint mediationLink_targetIsEntityInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>sameEndsAsType</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint mediationLink_sameEndsAsTypeInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>maxGreatherThanMin</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint participant_maxGreatherThanMinInvOCL;

	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";

	private static final OCL OCL_ENV = OCL.newInstance();

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sml2Validator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return Sml2Package.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresonding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case Sml2Package.ALLEN_LINK:
				return validateAllenLink((AllenLink)value, diagnostics, context);
			case Sml2Package.ATTRIBUTE_LINK:
				return validateAttributeLink((AttributeLink)value, diagnostics, context);
			case Sml2Package.ATTRIBUTE_REFERENCE:
				return validateAttributeReference((AttributeReference)value, diagnostics, context);
			case Sml2Package.CHARACTERIZATION_LINK:
				return validateCharacterizationLink((CharacterizationLink)value, diagnostics, context);
			case Sml2Package.COMPARATIVE_RELATION:
				return validateComparativeRelation((ComparativeRelation)value, diagnostics, context);
			case Sml2Package.CONTEXT_FORMAL_LINK:
				return validateContextFormalLink((ContextFormalLink)value, diagnostics, context);
			case Sml2Package.FORMAL_RELATION:
				return validateFormalRelation((FormalRelation)value, diagnostics, context);
			case Sml2Package.ENTITY_PARTICIPANT:
				return validateEntityParticipant((EntityParticipant)value, diagnostics, context);
			case Sml2Package.EQUALS_LINK:
				return validateEqualsLink((EqualsLink)value, diagnostics, context);
			case Sml2Package.FUNCTION:
				return validateFunction((Function)value, diagnostics, context);
			case Sml2Package.FUNCTION_PARAMETER:
				return validateFunctionParameter((FunctionParameter)value, diagnostics, context);
			case Sml2Package.INSTANTIATION_LINK:
				return validateInstantiationLink((InstantiationLink)value, diagnostics, context);
			case Sml2Package.LITERAL:
				return validateLiteral((Literal)value, diagnostics, context);
			case Sml2Package.MEDIATION_LINK:
				return validateMediationLink((MediationLink)value, diagnostics, context);
			case Sml2Package.MODE_REFERENCE:
				return validateModeReference((ModeReference)value, diagnostics, context);
			case Sml2Package.NODE:
				return validateNode((Node)value, diagnostics, context);
			case Sml2Package.ORDERED_COMPARATIVE_LINK:
				return validateOrderedComparativeLink((OrderedComparativeLink)value, diagnostics, context);
			case Sml2Package.PARTICIPANT:
				return validateParticipant((Participant)value, diagnostics, context);
			case Sml2Package.QUALITY_LITERAL:
				return validateQualityLiteral((QualityLiteral)value, diagnostics, context);
			case Sml2Package.REFERABLE_ELEMENT:
				return validateReferableElement((ReferableElement)value, diagnostics, context);
			case Sml2Package.REFERENCE_NODE:
				return validateReferenceNode((ReferenceNode)value, diagnostics, context);
			case Sml2Package.RELATOR_PARTICIPANT:
				return validateRelatorParticipant((RelatorParticipant)value, diagnostics, context);
			case Sml2Package.SELF_REFERENCE:
				return validateSelfReference((SelfReference)value, diagnostics, context);
			case Sml2Package.SITUATION_PARTICIPANT:
				return validateSituationParticipant((SituationParticipant)value, diagnostics, context);
			case Sml2Package.SITUATION_TYPE:
				return validateSituationType((SituationType)value, diagnostics, context);
			case Sml2Package.SITUATION_TYPE_ASSOCIATION:
				return validateSituationTypeAssociation((SituationTypeAssociation)value, diagnostics, context);
			case Sml2Package.SITUATION_TYPE_ELEMENT:
				return validateSituationTypeElement((SituationTypeElement)value, diagnostics, context);
			case Sml2Package.SML_MODEL:
				return validateSMLModel((SMLModel)value, diagnostics, context);
			case Sml2Package.TYPE_LITERAL:
				return validateTypeLiteral((TypeLiteral)value, diagnostics, context);
			case Sml2Package.ALLEN_KIND:
				return validateAllenKind((AllenKind)value, diagnostics, context);
			case Sml2Package.COMPARATIVE_KIND:
				return validateComparativeKind((ComparativeKind)value, diagnostics, context);
			case Sml2Package.TEMPORAL_KIND:
				return validateTemporalKind((TemporalKind)value, diagnostics, context);
			default: 
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAllenLink(AllenLink allenLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(allenLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(allenLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(allenLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(allenLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(allenLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(allenLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(allenLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(allenLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(allenLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateAllenLink_sourceIsSituation(allenLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateAllenLink_targetIsSituation(allenLink, diagnostics, context);
		return result;
	}

	/**
	 * Validates the sourceIsSituation constraint of '<em>Allen Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAllenLink_sourceIsSituation(AllenLink allenLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (allenLink_sourceIsSituationInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.ALLEN_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.ALLEN_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("sourceIsSituation");
			
			try {
				allenLink_sourceIsSituationInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(allenLink_sourceIsSituationInvOCL);
		
		if (!query.check(allenLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.ALLEN_LINK, new Object[] { "sourceIsSituation", getObjectLabel(allenLink, context) }),
						 new Object[] { allenLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the targetIsSituation constraint of '<em>Allen Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAllenLink_targetIsSituation(AllenLink allenLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (allenLink_targetIsSituationInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.ALLEN_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.ALLEN_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("targetIsSituation");
			
			try {
				allenLink_targetIsSituationInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(allenLink_targetIsSituationInvOCL);
		
		if (!query.check(allenLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.ALLEN_LINK, new Object[] { "targetIsSituation", getObjectLabel(allenLink, context) }),
						 new Object[] { allenLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAttributeLink(AttributeLink attributeLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(attributeLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(attributeLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(attributeLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(attributeLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(attributeLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(attributeLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(attributeLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(attributeLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(attributeLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateAttributeLink_sourceIsParticipant_Reference2(attributeLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateAttributeLink_targetIsAttribute(attributeLink, diagnostics, context);
		return result;
	}

	/**
	 * Validates the sourceIsParticipant_Reference2 constraint of '<em>Attribute Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAttributeLink_sourceIsParticipant_Reference2(AttributeLink attributeLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (attributeLink_sourceIsParticipant_Reference2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.ATTRIBUTE_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.ATTRIBUTE_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("sourceIsParticipant_Reference2");
			
			try {
				attributeLink_sourceIsParticipant_Reference2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(attributeLink_sourceIsParticipant_Reference2InvOCL);
		
		if (!query.check(attributeLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.ATTRIBUTE_LINK, new Object[] { "sourceIsParticipant_Reference2", getObjectLabel(attributeLink, context) }),
						 new Object[] { attributeLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the targetIsAttribute constraint of '<em>Attribute Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAttributeLink_targetIsAttribute(AttributeLink attributeLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (attributeLink_targetIsAttributeInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.ATTRIBUTE_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.ATTRIBUTE_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("targetIsAttribute");
			
			try {
				attributeLink_targetIsAttributeInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(attributeLink_targetIsAttributeInvOCL);
		
		if (!query.check(attributeLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.ATTRIBUTE_LINK, new Object[] { "targetIsAttribute", getObjectLabel(attributeLink, context) }),
						 new Object[] { attributeLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAttributeReference(AttributeReference attributeReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(attributeReference, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCharacterizationLink(CharacterizationLink characterizationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(characterizationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(characterizationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(characterizationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(characterizationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(characterizationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(characterizationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(characterizationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(characterizationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(characterizationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateCharacterizationLink_sourceIsMode(characterizationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateCharacterizationLink_targetIsEntity_Reference(characterizationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateCharacterizationLink_sameEndsAsType3(characterizationLink, diagnostics, context);
		return result;
	}

	/**
	 * Validates the sourceIsMode constraint of '<em>Characterization Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCharacterizationLink_sourceIsMode(CharacterizationLink characterizationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (characterizationLink_sourceIsModeInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.CHARACTERIZATION_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.CHARACTERIZATION_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("sourceIsMode");
			
			try {
				characterizationLink_sourceIsModeInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(characterizationLink_sourceIsModeInvOCL);
		
		if (!query.check(characterizationLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.CHARACTERIZATION_LINK, new Object[] { "sourceIsMode", getObjectLabel(characterizationLink, context) }),
						 new Object[] { characterizationLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the targetIsEntity_Reference constraint of '<em>Characterization Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCharacterizationLink_targetIsEntity_Reference(CharacterizationLink characterizationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (characterizationLink_targetIsEntity_ReferenceInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.CHARACTERIZATION_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.CHARACTERIZATION_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("targetIsEntity_Reference");
			
			try {
				characterizationLink_targetIsEntity_ReferenceInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(characterizationLink_targetIsEntity_ReferenceInvOCL);
		
		if (!query.check(characterizationLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.CHARACTERIZATION_LINK, new Object[] { "targetIsEntity_Reference", getObjectLabel(characterizationLink, context) }),
						 new Object[] { characterizationLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the sameEndsAsType3 constraint of '<em>Characterization Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCharacterizationLink_sameEndsAsType3(CharacterizationLink characterizationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (characterizationLink_sameEndsAsType3InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.CHARACTERIZATION_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.CHARACTERIZATION_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("sameEndsAsType3");
			
			try {
				characterizationLink_sameEndsAsType3InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(characterizationLink_sameEndsAsType3InvOCL);
		
		if (!query.check(characterizationLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.CHARACTERIZATION_LINK, new Object[] { "sameEndsAsType3", getObjectLabel(characterizationLink, context) }),
						 new Object[] { characterizationLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComparativeRelation(ComparativeRelation comparativeRelation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(comparativeRelation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContextFormalLink(ContextFormalLink contextFormalLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(contextFormalLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(contextFormalLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(contextFormalLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(contextFormalLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(contextFormalLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(contextFormalLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(contextFormalLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(contextFormalLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(contextFormalLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateContextFormalLink_sameEndsAsType2(contextFormalLink, diagnostics, context);
		return result;
	}

	/**
	 * Validates the sameEndsAsType2 constraint of '<em>Context Formal Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContextFormalLink_sameEndsAsType2(ContextFormalLink contextFormalLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (contextFormalLink_sameEndsAsType2InvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.CONTEXT_FORMAL_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.CONTEXT_FORMAL_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("sameEndsAsType2");
			
			try {
				contextFormalLink_sameEndsAsType2InvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(contextFormalLink_sameEndsAsType2InvOCL);
		
		if (!query.check(contextFormalLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.CONTEXT_FORMAL_LINK, new Object[] { "sameEndsAsType2", getObjectLabel(contextFormalLink, context) }),
						 new Object[] { contextFormalLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormalRelation(FormalRelation formalRelation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(formalRelation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEntityParticipant(EntityParticipant entityParticipant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(entityParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(entityParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(entityParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(entityParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(entityParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(entityParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(entityParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(entityParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(entityParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validateParticipant_maxGreatherThanMin(entityParticipant, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEqualsLink(EqualsLink equalsLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(equalsLink, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFunction(Function function, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(function, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFunctionParameter(FunctionParameter functionParameter, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(functionParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(functionParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(functionParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(functionParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(functionParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(functionParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(functionParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(functionParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(functionParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validateFunctionParameter_sourceIsFunction(functionParameter, diagnostics, context);
		return result;
	}

	/**
	 * Validates the sourceIsFunction constraint of '<em>Function Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFunctionParameter_sourceIsFunction(FunctionParameter functionParameter, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (functionParameter_sourceIsFunctionInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.FUNCTION_PARAMETER);
			
			EAnnotation ocl = Sml2Package.Literals.FUNCTION_PARAMETER.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("sourceIsFunction");
			
			try {
				functionParameter_sourceIsFunctionInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(functionParameter_sourceIsFunctionInvOCL);
		
		if (!query.check(functionParameter)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.FUNCTION_PARAMETER, new Object[] { "sourceIsFunction", getObjectLabel(functionParameter, context) }),
						 new Object[] { functionParameter }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstantiationLink(InstantiationLink instantiationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(instantiationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(instantiationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(instantiationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(instantiationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(instantiationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(instantiationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(instantiationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(instantiationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(instantiationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstantiationLink_sourceIsParticipant_Reference(instantiationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstantiationLink_targetIsLiteral(instantiationLink, diagnostics, context);
		return result;
	}

	/**
	 * Validates the sourceIsParticipant_Reference constraint of '<em>Instantiation Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstantiationLink_sourceIsParticipant_Reference(InstantiationLink instantiationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (instantiationLink_sourceIsParticipant_ReferenceInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.INSTANTIATION_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.INSTANTIATION_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("sourceIsParticipant_Reference");
			
			try {
				instantiationLink_sourceIsParticipant_ReferenceInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(instantiationLink_sourceIsParticipant_ReferenceInvOCL);
		
		if (!query.check(instantiationLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.INSTANTIATION_LINK, new Object[] { "sourceIsParticipant_Reference", getObjectLabel(instantiationLink, context) }),
						 new Object[] { instantiationLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the targetIsLiteral constraint of '<em>Instantiation Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstantiationLink_targetIsLiteral(InstantiationLink instantiationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (instantiationLink_targetIsLiteralInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.INSTANTIATION_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.INSTANTIATION_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("targetIsLiteral");
			
			try {
				instantiationLink_targetIsLiteralInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(instantiationLink_targetIsLiteralInvOCL);
		
		if (!query.check(instantiationLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.INSTANTIATION_LINK, new Object[] { "targetIsLiteral", getObjectLabel(instantiationLink, context) }),
						 new Object[] { instantiationLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLiteral(Literal literal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(literal, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMediationLink(MediationLink mediationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(mediationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(mediationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(mediationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(mediationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(mediationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(mediationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(mediationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(mediationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(mediationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateMediationLink_sourceIsRelator(mediationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateMediationLink_targetIsEntity(mediationLink, diagnostics, context);
		if (result || diagnostics != null) result &= validateMediationLink_sameEndsAsType(mediationLink, diagnostics, context);
		return result;
	}

	/**
	 * Validates the sourceIsRelator constraint of '<em>Mediation Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMediationLink_sourceIsRelator(MediationLink mediationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (mediationLink_sourceIsRelatorInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.MEDIATION_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.MEDIATION_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("sourceIsRelator");
			
			try {
				mediationLink_sourceIsRelatorInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(mediationLink_sourceIsRelatorInvOCL);
		
		if (!query.check(mediationLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.MEDIATION_LINK, new Object[] { "sourceIsRelator", getObjectLabel(mediationLink, context) }),
						 new Object[] { mediationLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the targetIsEntity constraint of '<em>Mediation Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMediationLink_targetIsEntity(MediationLink mediationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (mediationLink_targetIsEntityInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.MEDIATION_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.MEDIATION_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("targetIsEntity");
			
			try {
				mediationLink_targetIsEntityInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(mediationLink_targetIsEntityInvOCL);
		
		if (!query.check(mediationLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.MEDIATION_LINK, new Object[] { "targetIsEntity", getObjectLabel(mediationLink, context) }),
						 new Object[] { mediationLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the sameEndsAsType constraint of '<em>Mediation Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMediationLink_sameEndsAsType(MediationLink mediationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (mediationLink_sameEndsAsTypeInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.MEDIATION_LINK);
			
			EAnnotation ocl = Sml2Package.Literals.MEDIATION_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("sameEndsAsType");
			
			try {
				mediationLink_sameEndsAsTypeInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(mediationLink_sameEndsAsTypeInvOCL);
		
		if (!query.check(mediationLink)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.MEDIATION_LINK, new Object[] { "sameEndsAsType", getObjectLabel(mediationLink, context) }),
						 new Object[] { mediationLink }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModeReference(ModeReference modeReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(modeReference, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNode(Node node, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(node, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrderedComparativeLink(OrderedComparativeLink orderedComparativeLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(orderedComparativeLink, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateParticipant(Participant participant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validateParticipant_maxGreatherThanMin(participant, diagnostics, context);
		return result;
	}

	/**
	 * Validates the maxGreatherThanMin constraint of '<em>Participant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateParticipant_maxGreatherThanMin(Participant participant, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (participant_maxGreatherThanMinInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(Sml2Package.Literals.PARTICIPANT);
			
			EAnnotation ocl = Sml2Package.Literals.PARTICIPANT.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("maxGreatherThanMin");
			
			try {
				participant_maxGreatherThanMinInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(participant_maxGreatherThanMinInvOCL);
		
		if (!query.check(participant)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 Sml2Util.getVerificationMessage(Sml2Package.Literals.PARTICIPANT, new Object[] { "maxGreatherThanMin", getObjectLabel(participant, context) }),
						 new Object[] { participant }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateQualityLiteral(QualityLiteral qualityLiteral, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(qualityLiteral, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateReferableElement(ReferableElement referableElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(referableElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateReferenceNode(ReferenceNode referenceNode, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(referenceNode, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRelatorParticipant(RelatorParticipant relatorParticipant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(relatorParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(relatorParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(relatorParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(relatorParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(relatorParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(relatorParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(relatorParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(relatorParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(relatorParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validateParticipant_maxGreatherThanMin(relatorParticipant, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSelfReference(SelfReference selfReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(selfReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(selfReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(selfReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(selfReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(selfReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(selfReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(selfReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(selfReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(selfReference, diagnostics, context);
		if (result || diagnostics != null) result &= validateParticipant_maxGreatherThanMin(selfReference, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSituationParticipant(SituationParticipant situationParticipant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(situationParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(situationParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(situationParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(situationParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(situationParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(situationParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(situationParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(situationParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(situationParticipant, diagnostics, context);
		if (result || diagnostics != null) result &= validateParticipant_maxGreatherThanMin(situationParticipant, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSituationType(SituationType situationType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(situationType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSituationTypeAssociation(SituationTypeAssociation situationTypeAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(situationTypeAssociation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSituationTypeElement(SituationTypeElement situationTypeElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(situationTypeElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSMLModel(SMLModel smlModel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(smlModel, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeLiteral(TypeLiteral typeLiteral, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(typeLiteral, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAllenKind(AllenKind allenKind, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTemporalKind(TemporalKind temporalKind, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComparativeKind(ComparativeKind comparativeKind, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

} //Sml2Validator
