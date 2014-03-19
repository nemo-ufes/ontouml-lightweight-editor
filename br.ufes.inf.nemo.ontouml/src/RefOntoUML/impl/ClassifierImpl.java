/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Feature;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.NamedElement;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
import RefOntoUML.RedefinableElement;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.Type;

import RefOntoUML.util.RefOntoUMLValidator;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;

import org.eclipse.ocl.ecore.OCL;

import org.eclipse.ocl.ecore.OCL.Helper;

import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.ClassifierImpl#isIsLeaf <em>Is Leaf</em>}</li>
 *   <li>{@link RefOntoUML.impl.ClassifierImpl#getRedefinedElement <em>Redefined Element</em>}</li>
 *   <li>{@link RefOntoUML.impl.ClassifierImpl#getRedefinitionContext <em>Redefinition Context</em>}</li>
 *   <li>{@link RefOntoUML.impl.ClassifierImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link RefOntoUML.impl.ClassifierImpl#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link RefOntoUML.impl.ClassifierImpl#getGeneralization <em>Generalization</em>}</li>
 *   <li>{@link RefOntoUML.impl.ClassifierImpl#getPowertypeExtent <em>Powertype Extent</em>}</li>
 *   <li>{@link RefOntoUML.impl.ClassifierImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link RefOntoUML.impl.ClassifierImpl#getInheritedMember <em>Inherited Member</em>}</li>
 *   <li>{@link RefOntoUML.impl.ClassifierImpl#getRedefinedClassifier <em>Redefined Classifier</em>}</li>
 *   <li>{@link RefOntoUML.impl.ClassifierImpl#getGeneral <em>General</em>}</li>
 *   <li>{@link RefOntoUML.impl.ClassifierImpl#getAttribute <em>Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ClassifierImpl extends NamespaceImpl implements Classifier {
	/**
	 * The default value of the '{@link #isIsLeaf() <em>Is Leaf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsLeaf()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_LEAF_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsLeaf() <em>Is Leaf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsLeaf()
	 * @generated
	 * @ordered
	 */
	protected boolean isLeaf = IS_LEAF_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean isAbstract = IS_ABSTRACT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGeneralization() <em>Generalization</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneralization()
	 * @generated
	 * @ordered
	 */
	protected EList<Generalization> generalization;

	/**
	 * The cached value of the '{@link #getPowertypeExtent() <em>Powertype Extent</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowertypeExtent()
	 * @generated
	 * @ordered
	 */
	protected EList<GeneralizationSet> powertypeExtent;

	/**
	 * The cached value of the '{@link #getRedefinedClassifier() <em>Redefined Classifier</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedefinedClassifier()
	 * @generated
	 * @ordered
	 */
	protected EList<Classifier> redefinedClassifier;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassifierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getClassifier();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsLeaf() {
		return isLeaf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsLeaf(boolean newIsLeaf) {
		boolean oldIsLeaf = isLeaf;
		isLeaf = newIsLeaf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.CLASSIFIER__IS_LEAF, oldIsLeaf, isLeaf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RedefinableElement> getRedefinedElement() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getRedefinableElement_RedefinedElement();
	
		if (redefinedElementDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getRedefinableElement(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				redefinedElementDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(redefinedElementDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<RedefinableElement> result = (Collection<RedefinableElement>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<RedefinableElement>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Classifier> getRedefinitionContext() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getRedefinableElement_RedefinitionContext();
	
		if (redefinitionContextDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getRedefinableElement(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				redefinitionContextDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(redefinitionContextDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Classifier> result = (Collection<Classifier>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<Classifier>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUML.Package getPackage() {
		RefOntoUML.Package package_ = basicGetPackage();
		return package_ != null && package_.eIsProxy() ? (RefOntoUML.Package)eResolveProxy((InternalEObject)package_) : package_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUML.Package basicGetPackage() {
		if (package_DeriveOCL == null) { 
			EStructuralFeature eFeature = (RefOntoUMLPackage.eINSTANCE.getType_Package());
			
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getType(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				package_DeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(package_DeriveOCL);
	
		return (RefOntoUML.Package) query.evaluate(this);
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackage(RefOntoUML.Package newPackage) {
		// TODO: implement this method to set the 'Package' reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsAbstract() {
		return isAbstract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsAbstract(boolean newIsAbstract) {
		boolean oldIsAbstract = isAbstract;
		isAbstract = newIsAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.CLASSIFIER__IS_ABSTRACT, oldIsAbstract, isAbstract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Generalization> getGeneralization() {
		if (generalization == null) {
			generalization = new EObjectContainmentWithInverseEList<Generalization>(Generalization.class, this, RefOntoUMLPackage.CLASSIFIER__GENERALIZATION, RefOntoUMLPackage.GENERALIZATION__SPECIFIC);
		}
		return generalization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GeneralizationSet> getPowertypeExtent() {
		if (powertypeExtent == null) {
			powertypeExtent = new EObjectWithInverseResolvingEList<GeneralizationSet>(GeneralizationSet.class, this, RefOntoUMLPackage.CLASSIFIER__POWERTYPE_EXTENT, RefOntoUMLPackage.GENERALIZATION_SET__POWERTYPE);
		}
		return powertypeExtent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Feature> getFeature() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getClassifier_Feature();
	
		if (featureDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				featureDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(featureDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Feature> result = (Collection<Feature>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<Feature>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedElement> getInheritedMember() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getClassifier_InheritedMember();
	
		if (inheritedMemberDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				inheritedMemberDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(inheritedMemberDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<NamedElement> result = (Collection<NamedElement>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<NamedElement>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Classifier> getRedefinedClassifier() {
		if (redefinedClassifier == null) {
			redefinedClassifier = new EObjectResolvingEList<Classifier>(Classifier.class, this, RefOntoUMLPackage.CLASSIFIER__REDEFINED_CLASSIFIER);
		}
		return redefinedClassifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Classifier> getGeneral() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getClassifier_General();
	
		if (generalDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				generalDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(generalDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Classifier> result = (Collection<Classifier>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<Classifier>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Property> getAttribute() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getClassifier_Attribute();
	
		if (attributeDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				attributeDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(attributeDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Property> result = (Collection<Property>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<Property>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unused")
	public boolean no_cycles_in_generalization(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 RefOntoUMLValidator.DIAGNOSTIC_SOURCE,
						 RefOntoUMLValidator.CLASSIFIER__NO_CYCLES_IN_GENERALIZATION,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "no_cycles_in_generalization", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
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
	@SuppressWarnings("unused")
	public boolean generalization_hierarchies(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 RefOntoUMLValidator.DIAGNOSTIC_SOURCE,
						 RefOntoUMLValidator.CLASSIFIER__GENERALIZATION_HIERARCHIES,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "generalization_hierarchies", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
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
	@SuppressWarnings("unused")
	public boolean specialize_type(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 RefOntoUMLValidator.DIAGNOSTIC_SOURCE,
						 RefOntoUMLValidator.CLASSIFIER__SPECIALIZE_TYPE,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "specialize_type", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
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
	@SuppressWarnings("unused")
	public boolean maps_to_generalization_set(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 RefOntoUMLValidator.DIAGNOSTIC_SOURCE,
						 RefOntoUMLValidator.CLASSIFIER__MAPS_TO_GENERALIZATION_SET,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "maps_to_generalization_set", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
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
	public EList<Property> getAllAttributes() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Classifier> getGenerals() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedElement> getInheritedMembers() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Feature> allFeatures() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Classifier> parents() {
		if (parentsBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(8);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				parentsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(parentsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Classifier> result = (Collection<Classifier>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Classifier>(result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedElement> inheritableMembers(Classifier c) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean hasVisibilityOf(NamedElement n) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean conformsTo(Classifier other) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedElement> inherit(EList<NamedElement> inhs) {
		if (inheritBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(12);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				inheritBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(inheritBodyOCL);
	 
		EvaluationEnvironment<?, ?, ?, ?, ?> evalEnv = query.getEvaluationEnvironment();
		
		evalEnv.add("inhs", inhs);
	  
		@SuppressWarnings("unchecked")
		Collection<NamedElement> result = (Collection<NamedElement>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<NamedElement>(result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean maySpecializeType(Classifier c) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Classifier> allParents() {
		if (allParentsBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(14);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				allParentsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(allParentsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Classifier> result = (Collection<Classifier>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Classifier>(result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean hasKindAncestor() {
		if (hasKindAncestorBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(15);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				hasKindAncestorBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(hasKindAncestorBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean hasQuantityAncestor() {
		if (hasQuantityAncestorBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(16);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				hasQuantityAncestorBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(hasQuantityAncestorBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean hasCollectiveAncestor() {
		if (hasCollectiveAncestorBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(17);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				hasCollectiveAncestorBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(hasCollectiveAncestorBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean hasKindOffspring() {
		if (hasKindOffspringBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(18);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				hasKindOffspringBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(hasKindOffspringBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean hasQuantityOffspring() {
		if (hasQuantityOffspringBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(19);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				hasQuantityOffspringBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(hasQuantityOffspringBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean hasCollectiveOffspring() {
		if (hasCollectiveOffspringBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(20);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				hasCollectiveOffspringBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(hasCollectiveOffspringBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean hasFunctionalComplexInstances() {
		if (hasFunctionalComplexInstancesBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(21);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				hasFunctionalComplexInstancesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(hasFunctionalComplexInstancesBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean hasQuantityInstances() {
		if (hasQuantityInstancesBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(22);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				hasQuantityInstancesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(hasQuantityInstancesBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean hasCollectiveInstances() {
		if (hasCollectiveInstancesBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(23);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				hasCollectiveInstancesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(hasCollectiveInstancesBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Classifier> children() {
		if (childrenBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(24);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				childrenBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(childrenBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Classifier> result = (Collection<Classifier>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Classifier>(result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Classifier> allChildren() {
		if (allChildrenBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(25);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				allChildrenBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(allChildrenBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Classifier> result = (Collection<Classifier>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Classifier>(result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GeneralizationSet> partitions() {
		if (partitionsBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getClassifier().getEOperations().get(26);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getClassifier(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				partitionsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(partitionsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<GeneralizationSet> result = (Collection<GeneralizationSet>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<GeneralizationSet>(result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Association createAssociation(boolean end1IsNavigable, AggregationKind end1Aggregation, String end1Name, int end1Lower, int end1Upper, Type end1Type, boolean end2IsNavigable, AggregationKind end2Aggregation, String end2Name, int end2Lower, int end2Upper) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getAssociations() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean conformsTo(Type other) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unused")
	public boolean redefinition_context_valid(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 RefOntoUMLValidator.DIAGNOSTIC_SOURCE,
						 RefOntoUMLValidator.REDEFINABLE_ELEMENT__REDEFINITION_CONTEXT_VALID,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "redefinition_context_valid", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
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
	@SuppressWarnings("unused")
	public boolean redefinition_consistent(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 RefOntoUMLValidator.DIAGNOSTIC_SOURCE,
						 RefOntoUMLValidator.REDEFINABLE_ELEMENT__REDEFINITION_CONSISTENT,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "redefinition_consistent", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
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
	public boolean isConsistentWith(RedefinableElement redefinee) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRedefinitionContextValid(RedefinableElement redefined) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.CLASSIFIER__GENERALIZATION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGeneralization()).basicAdd(otherEnd, msgs);
			case RefOntoUMLPackage.CLASSIFIER__POWERTYPE_EXTENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPowertypeExtent()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.CLASSIFIER__GENERALIZATION:
				return ((InternalEList<?>)getGeneralization()).basicRemove(otherEnd, msgs);
			case RefOntoUMLPackage.CLASSIFIER__POWERTYPE_EXTENT:
				return ((InternalEList<?>)getPowertypeExtent()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RefOntoUMLPackage.CLASSIFIER__IS_LEAF:
				return isIsLeaf();
			case RefOntoUMLPackage.CLASSIFIER__REDEFINED_ELEMENT:
				return getRedefinedElement();
			case RefOntoUMLPackage.CLASSIFIER__REDEFINITION_CONTEXT:
				return getRedefinitionContext();
			case RefOntoUMLPackage.CLASSIFIER__PACKAGE:
				if (resolve) return getPackage();
				return basicGetPackage();
			case RefOntoUMLPackage.CLASSIFIER__IS_ABSTRACT:
				return isIsAbstract();
			case RefOntoUMLPackage.CLASSIFIER__GENERALIZATION:
				return getGeneralization();
			case RefOntoUMLPackage.CLASSIFIER__POWERTYPE_EXTENT:
				return getPowertypeExtent();
			case RefOntoUMLPackage.CLASSIFIER__FEATURE:
				return getFeature();
			case RefOntoUMLPackage.CLASSIFIER__INHERITED_MEMBER:
				return getInheritedMember();
			case RefOntoUMLPackage.CLASSIFIER__REDEFINED_CLASSIFIER:
				return getRedefinedClassifier();
			case RefOntoUMLPackage.CLASSIFIER__GENERAL:
				return getGeneral();
			case RefOntoUMLPackage.CLASSIFIER__ATTRIBUTE:
				return getAttribute();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RefOntoUMLPackage.CLASSIFIER__IS_LEAF:
				setIsLeaf((Boolean)newValue);
				return;
			case RefOntoUMLPackage.CLASSIFIER__PACKAGE:
				setPackage((RefOntoUML.Package)newValue);
				return;
			case RefOntoUMLPackage.CLASSIFIER__IS_ABSTRACT:
				setIsAbstract((Boolean)newValue);
				return;
			case RefOntoUMLPackage.CLASSIFIER__GENERALIZATION:
				getGeneralization().clear();
				getGeneralization().addAll((Collection<? extends Generalization>)newValue);
				return;
			case RefOntoUMLPackage.CLASSIFIER__POWERTYPE_EXTENT:
				getPowertypeExtent().clear();
				getPowertypeExtent().addAll((Collection<? extends GeneralizationSet>)newValue);
				return;
			case RefOntoUMLPackage.CLASSIFIER__REDEFINED_CLASSIFIER:
				getRedefinedClassifier().clear();
				getRedefinedClassifier().addAll((Collection<? extends Classifier>)newValue);
				return;
			case RefOntoUMLPackage.CLASSIFIER__GENERAL:
				getGeneral().clear();
				getGeneral().addAll((Collection<? extends Classifier>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RefOntoUMLPackage.CLASSIFIER__IS_LEAF:
				setIsLeaf(IS_LEAF_EDEFAULT);
				return;
			case RefOntoUMLPackage.CLASSIFIER__PACKAGE:
				setPackage((RefOntoUML.Package)null);
				return;
			case RefOntoUMLPackage.CLASSIFIER__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case RefOntoUMLPackage.CLASSIFIER__GENERALIZATION:
				getGeneralization().clear();
				return;
			case RefOntoUMLPackage.CLASSIFIER__POWERTYPE_EXTENT:
				getPowertypeExtent().clear();
				return;
			case RefOntoUMLPackage.CLASSIFIER__REDEFINED_CLASSIFIER:
				getRedefinedClassifier().clear();
				return;
			case RefOntoUMLPackage.CLASSIFIER__GENERAL:
				getGeneral().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RefOntoUMLPackage.CLASSIFIER__IS_LEAF:
				return isLeaf != IS_LEAF_EDEFAULT;
			case RefOntoUMLPackage.CLASSIFIER__REDEFINED_ELEMENT:
				return !getRedefinedElement().isEmpty();
			case RefOntoUMLPackage.CLASSIFIER__REDEFINITION_CONTEXT:
				return !getRedefinitionContext().isEmpty();
			case RefOntoUMLPackage.CLASSIFIER__PACKAGE:
				return basicGetPackage() != null;
			case RefOntoUMLPackage.CLASSIFIER__IS_ABSTRACT:
				return isAbstract != IS_ABSTRACT_EDEFAULT;
			case RefOntoUMLPackage.CLASSIFIER__GENERALIZATION:
				return generalization != null && !generalization.isEmpty();
			case RefOntoUMLPackage.CLASSIFIER__POWERTYPE_EXTENT:
				return powertypeExtent != null && !powertypeExtent.isEmpty();
			case RefOntoUMLPackage.CLASSIFIER__FEATURE:
				return !getFeature().isEmpty();
			case RefOntoUMLPackage.CLASSIFIER__INHERITED_MEMBER:
				return !getInheritedMember().isEmpty();
			case RefOntoUMLPackage.CLASSIFIER__REDEFINED_CLASSIFIER:
				return redefinedClassifier != null && !redefinedClassifier.isEmpty();
			case RefOntoUMLPackage.CLASSIFIER__GENERAL:
				return !getGeneral().isEmpty();
			case RefOntoUMLPackage.CLASSIFIER__ATTRIBUTE:
				return !getAttribute().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == RedefinableElement.class) {
			switch (derivedFeatureID) {
				case RefOntoUMLPackage.CLASSIFIER__IS_LEAF: return RefOntoUMLPackage.REDEFINABLE_ELEMENT__IS_LEAF;
				case RefOntoUMLPackage.CLASSIFIER__REDEFINED_ELEMENT: return RefOntoUMLPackage.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT;
				case RefOntoUMLPackage.CLASSIFIER__REDEFINITION_CONTEXT: return RefOntoUMLPackage.REDEFINABLE_ELEMENT__REDEFINITION_CONTEXT;
				default: return -1;
			}
		}
		if (baseClass == PackageableElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Type.class) {
			switch (derivedFeatureID) {
				case RefOntoUMLPackage.CLASSIFIER__PACKAGE: return RefOntoUMLPackage.TYPE__PACKAGE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == RedefinableElement.class) {
			switch (baseFeatureID) {
				case RefOntoUMLPackage.REDEFINABLE_ELEMENT__IS_LEAF: return RefOntoUMLPackage.CLASSIFIER__IS_LEAF;
				case RefOntoUMLPackage.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT: return RefOntoUMLPackage.CLASSIFIER__REDEFINED_ELEMENT;
				case RefOntoUMLPackage.REDEFINABLE_ELEMENT__REDEFINITION_CONTEXT: return RefOntoUMLPackage.CLASSIFIER__REDEFINITION_CONTEXT;
				default: return -1;
			}
		}
		if (baseClass == PackageableElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Type.class) {
			switch (baseFeatureID) {
				case RefOntoUMLPackage.TYPE__PACKAGE: return RefOntoUMLPackage.CLASSIFIER__PACKAGE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isLeaf: ");
		result.append(isLeaf);
		result.append(", isAbstract: ");
		result.append(isAbstract);
		result.append(')');
		return result.toString();
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #parents <em>Parents</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #parents
	 * @generated
	 */
	private static OCLExpression<EClassifier> parentsBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #inherit <em>Inherit</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #inherit
	 * @generated
	 */
	private static OCLExpression<EClassifier> inheritBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #allParents <em>All Parents</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #allParents
	 * @generated
	 */
	private static OCLExpression<EClassifier> allParentsBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #hasKindAncestor <em>Has Kind Ancestor</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #hasKindAncestor
	 * @generated
	 */
	private static OCLExpression<EClassifier> hasKindAncestorBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #hasQuantityAncestor <em>Has Quantity Ancestor</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #hasQuantityAncestor
	 * @generated
	 */
	private static OCLExpression<EClassifier> hasQuantityAncestorBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #hasCollectiveAncestor <em>Has Collective Ancestor</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #hasCollectiveAncestor
	 * @generated
	 */
	private static OCLExpression<EClassifier> hasCollectiveAncestorBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #hasKindOffspring <em>Has Kind Offspring</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #hasKindOffspring
	 * @generated
	 */
	private static OCLExpression<EClassifier> hasKindOffspringBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #hasQuantityOffspring <em>Has Quantity Offspring</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #hasQuantityOffspring
	 * @generated
	 */
	private static OCLExpression<EClassifier> hasQuantityOffspringBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #hasCollectiveOffspring <em>Has Collective Offspring</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #hasCollectiveOffspring
	 * @generated
	 */
	private static OCLExpression<EClassifier> hasCollectiveOffspringBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #hasFunctionalComplexInstances <em>Has Functional Complex Instances</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #hasFunctionalComplexInstances
	 * @generated
	 */
	private static OCLExpression<EClassifier> hasFunctionalComplexInstancesBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #hasQuantityInstances <em>Has Quantity Instances</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #hasQuantityInstances
	 * @generated
	 */
	private static OCLExpression<EClassifier> hasQuantityInstancesBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #hasCollectiveInstances <em>Has Collective Instances</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #hasCollectiveInstances
	 * @generated
	 */
	private static OCLExpression<EClassifier> hasCollectiveInstancesBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #children <em>Children</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #children
	 * @generated
	 */
	private static OCLExpression<EClassifier> childrenBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #allChildren <em>All Children</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #allChildren
	 * @generated
	 */
	private static OCLExpression<EClassifier> allChildrenBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #partitions <em>Partitions</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #partitions
	 * @generated
	 */
	private static OCLExpression<EClassifier> partitionsBodyOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getRedefinedElement <em>Redefined Element</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedefinedElement
	 * @generated
	 */
	private static OCLExpression<EClassifier> redefinedElementDeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getRedefinitionContext <em>Redefinition Context</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedefinitionContext
	 * @generated
	 */
	private static OCLExpression<EClassifier> redefinitionContextDeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getPackage <em>Package</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackage
	 * @generated
	 */
	private static OCLExpression<EClassifier> package_DeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getFeature <em>Feature</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeature
	 * @generated
	 */
	private static OCLExpression<EClassifier> featureDeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getInheritedMember <em>Inherited Member</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInheritedMember
	 * @generated
	 */
	private static OCLExpression<EClassifier> inheritedMemberDeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getGeneral <em>General</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneral
	 * @generated
	 */
	private static OCLExpression<EClassifier> generalDeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getAttribute <em>Attribute</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttribute
	 * @generated
	 */
	private static OCLExpression<EClassifier> attributeDeriveOCL;
	
	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
} //ClassifierImpl
