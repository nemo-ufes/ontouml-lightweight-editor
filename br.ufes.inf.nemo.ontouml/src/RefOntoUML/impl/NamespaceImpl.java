/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.Constraintx;
import RefOntoUML.ElementImport;
import RefOntoUML.NamedElement;
import RefOntoUML.Namespace;
import RefOntoUML.PackageImport;
import RefOntoUML.PackageableElement;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.VisibilityKind;

import RefOntoUML.util.RefOntoUMLValidator;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;

import org.eclipse.ocl.ecore.OCL;

import org.eclipse.ocl.ecore.OCL.Helper;

import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Namespace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.NamespaceImpl#getElementImport <em>Element Import</em>}</li>
 *   <li>{@link RefOntoUML.impl.NamespaceImpl#getPackageImport <em>Package Import</em>}</li>
 *   <li>{@link RefOntoUML.impl.NamespaceImpl#getOwnedRule <em>Owned Rule</em>}</li>
 *   <li>{@link RefOntoUML.impl.NamespaceImpl#getMember <em>Member</em>}</li>
 *   <li>{@link RefOntoUML.impl.NamespaceImpl#getImportedMember <em>Imported Member</em>}</li>
 *   <li>{@link RefOntoUML.impl.NamespaceImpl#getOwnedMember <em>Owned Member</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class NamespaceImpl extends NamedElementImpl implements Namespace {
	/**
	 * The cached value of the '{@link #getElementImport() <em>Element Import</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementImport()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementImport> elementImport;

	/**
	 * The cached value of the '{@link #getPackageImport() <em>Package Import</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageImport()
	 * @generated
	 * @ordered
	 */
	protected EList<PackageImport> packageImport;

	/**
	 * The cached value of the '{@link #getOwnedRule() <em>Owned Rule</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedRule()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraintx> ownedRule;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NamespaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getNamespace();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ElementImport> getElementImport() {
		if (elementImport == null) {
			elementImport = new EObjectContainmentWithInverseEList<ElementImport>(ElementImport.class, this, RefOntoUMLPackage.NAMESPACE__ELEMENT_IMPORT, RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE);
		}
		return elementImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageImport> getPackageImport() {
		if (packageImport == null) {
			packageImport = new EObjectContainmentWithInverseEList<PackageImport>(PackageImport.class, this, RefOntoUMLPackage.NAMESPACE__PACKAGE_IMPORT, RefOntoUMLPackage.PACKAGE_IMPORT__IMPORTING_NAMESPACE);
		}
		return packageImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Constraintx> getOwnedRule() {
		if (ownedRule == null) {
			ownedRule = new EObjectContainmentWithInverseEList<Constraintx>(Constraintx.class, this, RefOntoUMLPackage.NAMESPACE__OWNED_RULE, RefOntoUMLPackage.CONSTRAINTX__CONTEXT);
		}
		return ownedRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedElement> getMember() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getNamespace_Member();
	
		if (memberDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getNamespace(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				memberDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(memberDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<NamedElement> result = (Collection<NamedElement>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<NamedElement>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageableElement> getImportedMember() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getNamespace_ImportedMember();
	
		if (importedMemberDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getNamespace(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				importedMemberDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(importedMemberDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<PackageableElement> result = (Collection<PackageableElement>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<PackageableElement>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedElement> getOwnedMember() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getNamespace_OwnedMember();
	
		if (ownedMemberDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getNamespace(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				ownedMemberDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(ownedMemberDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<NamedElement> result = (Collection<NamedElement>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<NamedElement>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unused")
	public boolean members_distinguishable(DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 RefOntoUMLValidator.NAMESPACE__MEMBERS_DISTINGUISHABLE,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "members_distinguishable", EObjectValidator.getObjectLabel(this, context) }),
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
	public ElementImport createElementImport(PackageableElement element, VisibilityKind visibility) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageImport createPackageImport(RefOntoUML.Package package_, VisibilityKind visibility) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageableElement> getImportedElements() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RefOntoUML.Package> getImportedPackages() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageableElement> getImportedMembers() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getNamesOfMember(NamedElement element) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean membersAreDistinguishable() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageableElement> importMembers(EList<PackageableElement> imps) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageableElement> excludeCollisions(EList<PackageableElement> imps) {
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
			case RefOntoUMLPackage.NAMESPACE__ELEMENT_IMPORT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getElementImport()).basicAdd(otherEnd, msgs);
			case RefOntoUMLPackage.NAMESPACE__PACKAGE_IMPORT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPackageImport()).basicAdd(otherEnd, msgs);
			case RefOntoUMLPackage.NAMESPACE__OWNED_RULE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedRule()).basicAdd(otherEnd, msgs);
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
			case RefOntoUMLPackage.NAMESPACE__ELEMENT_IMPORT:
				return ((InternalEList<?>)getElementImport()).basicRemove(otherEnd, msgs);
			case RefOntoUMLPackage.NAMESPACE__PACKAGE_IMPORT:
				return ((InternalEList<?>)getPackageImport()).basicRemove(otherEnd, msgs);
			case RefOntoUMLPackage.NAMESPACE__OWNED_RULE:
				return ((InternalEList<?>)getOwnedRule()).basicRemove(otherEnd, msgs);
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
			case RefOntoUMLPackage.NAMESPACE__ELEMENT_IMPORT:
				return getElementImport();
			case RefOntoUMLPackage.NAMESPACE__PACKAGE_IMPORT:
				return getPackageImport();
			case RefOntoUMLPackage.NAMESPACE__OWNED_RULE:
				return getOwnedRule();
			case RefOntoUMLPackage.NAMESPACE__MEMBER:
				return getMember();
			case RefOntoUMLPackage.NAMESPACE__IMPORTED_MEMBER:
				return getImportedMember();
			case RefOntoUMLPackage.NAMESPACE__OWNED_MEMBER:
				return getOwnedMember();
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
			case RefOntoUMLPackage.NAMESPACE__ELEMENT_IMPORT:
				getElementImport().clear();
				getElementImport().addAll((Collection<? extends ElementImport>)newValue);
				return;
			case RefOntoUMLPackage.NAMESPACE__PACKAGE_IMPORT:
				getPackageImport().clear();
				getPackageImport().addAll((Collection<? extends PackageImport>)newValue);
				return;
			case RefOntoUMLPackage.NAMESPACE__OWNED_RULE:
				getOwnedRule().clear();
				getOwnedRule().addAll((Collection<? extends Constraintx>)newValue);
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
			case RefOntoUMLPackage.NAMESPACE__ELEMENT_IMPORT:
				getElementImport().clear();
				return;
			case RefOntoUMLPackage.NAMESPACE__PACKAGE_IMPORT:
				getPackageImport().clear();
				return;
			case RefOntoUMLPackage.NAMESPACE__OWNED_RULE:
				getOwnedRule().clear();
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
			case RefOntoUMLPackage.NAMESPACE__ELEMENT_IMPORT:
				return elementImport != null && !elementImport.isEmpty();
			case RefOntoUMLPackage.NAMESPACE__PACKAGE_IMPORT:
				return packageImport != null && !packageImport.isEmpty();
			case RefOntoUMLPackage.NAMESPACE__OWNED_RULE:
				return ownedRule != null && !ownedRule.isEmpty();
			case RefOntoUMLPackage.NAMESPACE__MEMBER:
				return !getMember().isEmpty();
			case RefOntoUMLPackage.NAMESPACE__IMPORTED_MEMBER:
				return !getImportedMember().isEmpty();
			case RefOntoUMLPackage.NAMESPACE__OWNED_MEMBER:
				return !getOwnedMember().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * The parsed OCL expression for the derivation of '{@link #getMember <em>Member</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMember
	 * @generated
	 */
	private static OCLExpression<EClassifier> memberDeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getImportedMember <em>Imported Member</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportedMember
	 * @generated
	 */
	private static OCLExpression<EClassifier> importedMemberDeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getOwnedMember <em>Owned Member</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedMember
	 * @generated
	 */
	private static OCLExpression<EClassifier> ownedMemberDeriveOCL;
	
	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
} //NamespaceImpl
