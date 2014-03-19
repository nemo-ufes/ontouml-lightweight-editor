/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.Enumeration;
import RefOntoUML.NamedElement;
import RefOntoUML.PackageMerge;
import RefOntoUML.PackageableElement;
import RefOntoUML.PrimitiveType;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.Type;

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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
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
 * An implementation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.PackageImpl#getOwnedType <em>Owned Type</em>}</li>
 *   <li>{@link RefOntoUML.impl.PackageImpl#getPackageMerge <em>Package Merge</em>}</li>
 *   <li>{@link RefOntoUML.impl.PackageImpl#getPackagedElement <em>Packaged Element</em>}</li>
 *   <li>{@link RefOntoUML.impl.PackageImpl#getNestedPackage <em>Nested Package</em>}</li>
 *   <li>{@link RefOntoUML.impl.PackageImpl#getNestingPackage <em>Nesting Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageImpl extends NamespaceImpl implements RefOntoUML.Package {
	/**
	 * The cached value of the '{@link #getPackageMerge() <em>Package Merge</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageMerge()
	 * @generated
	 * @ordered
	 */
	protected EList<PackageMerge> packageMerge;

	/**
	 * The cached value of the '{@link #getPackagedElement() <em>Packaged Element</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackagedElement()
	 * @generated
	 * @ordered
	 */
	protected EList<PackageableElement> packagedElement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getOwnedType() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getPackage_OwnedType();
	
		if (ownedTypeDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getPackage(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				ownedTypeDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(ownedTypeDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Type> result = (Collection<Type>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<Type>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageMerge> getPackageMerge() {
		if (packageMerge == null) {
			packageMerge = new EObjectContainmentWithInverseEList<PackageMerge>(PackageMerge.class, this, RefOntoUMLPackage.PACKAGE__PACKAGE_MERGE, RefOntoUMLPackage.PACKAGE_MERGE__RECEIVING_PACKAGE);
		}
		return packageMerge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageableElement> getPackagedElement() {
		if (packagedElement == null) {
			packagedElement = new EObjectContainmentEList<PackageableElement>(PackageableElement.class, this, RefOntoUMLPackage.PACKAGE__PACKAGED_ELEMENT);
		}
		return packagedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RefOntoUML.Package> getNestedPackage() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getPackage_NestedPackage();
	
		if (nestedPackageDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getPackage(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				nestedPackageDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(nestedPackageDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<RefOntoUML.Package> result = (Collection<RefOntoUML.Package>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<RefOntoUML.Package>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUML.Package getNestingPackage() {
		RefOntoUML.Package nestingPackage = basicGetNestingPackage();
		return nestingPackage != null && nestingPackage.eIsProxy() ? (RefOntoUML.Package)eResolveProxy((InternalEObject)nestingPackage) : nestingPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUML.Package basicGetNestingPackage() {
		if (nestingPackageDeriveOCL == null) { 
			EStructuralFeature eFeature = (RefOntoUMLPackage.eINSTANCE.getPackage_NestingPackage());
			
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getPackage(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				nestingPackageDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(nestingPackageDeriveOCL);
	
		return (RefOntoUML.Package) query.evaluate(this);
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNestingPackage(RefOntoUML.Package newNestingPackage) {
		// TODO: implement this method to set the 'Nesting Package' reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unused")
	public boolean elements_public_or_private(DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 RefOntoUMLValidator.PACKAGE__ELEMENTS_PUBLIC_OR_PRIVATE,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "elements_public_or_private", EObjectValidator.getObjectLabel(this, context) }),
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
	public RefOntoUML.Class createOwnedClass(String name, boolean isAbstract) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration createOwnedEnumeration(String name) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType createOwnedPrimitiveType(String name) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createOwnedInterface(String name) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void getAppliedProfile(String qualifiedName) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void getAppliedProfile(String qualifiedName, boolean recurse) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isModelLibrary() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageableElement> visibleMembers() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean makesVisible(NamedElement el) {
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
			case RefOntoUMLPackage.PACKAGE__PACKAGE_MERGE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPackageMerge()).basicAdd(otherEnd, msgs);
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
			case RefOntoUMLPackage.PACKAGE__PACKAGE_MERGE:
				return ((InternalEList<?>)getPackageMerge()).basicRemove(otherEnd, msgs);
			case RefOntoUMLPackage.PACKAGE__PACKAGED_ELEMENT:
				return ((InternalEList<?>)getPackagedElement()).basicRemove(otherEnd, msgs);
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
			case RefOntoUMLPackage.PACKAGE__OWNED_TYPE:
				return getOwnedType();
			case RefOntoUMLPackage.PACKAGE__PACKAGE_MERGE:
				return getPackageMerge();
			case RefOntoUMLPackage.PACKAGE__PACKAGED_ELEMENT:
				return getPackagedElement();
			case RefOntoUMLPackage.PACKAGE__NESTED_PACKAGE:
				return getNestedPackage();
			case RefOntoUMLPackage.PACKAGE__NESTING_PACKAGE:
				if (resolve) return getNestingPackage();
				return basicGetNestingPackage();
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
			case RefOntoUMLPackage.PACKAGE__OWNED_TYPE:
				getOwnedType().clear();
				getOwnedType().addAll((Collection<? extends Type>)newValue);
				return;
			case RefOntoUMLPackage.PACKAGE__PACKAGE_MERGE:
				getPackageMerge().clear();
				getPackageMerge().addAll((Collection<? extends PackageMerge>)newValue);
				return;
			case RefOntoUMLPackage.PACKAGE__PACKAGED_ELEMENT:
				getPackagedElement().clear();
				getPackagedElement().addAll((Collection<? extends PackageableElement>)newValue);
				return;
			case RefOntoUMLPackage.PACKAGE__NESTED_PACKAGE:
				getNestedPackage().clear();
				getNestedPackage().addAll((Collection<? extends RefOntoUML.Package>)newValue);
				return;
			case RefOntoUMLPackage.PACKAGE__NESTING_PACKAGE:
				setNestingPackage((RefOntoUML.Package)newValue);
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
			case RefOntoUMLPackage.PACKAGE__OWNED_TYPE:
				getOwnedType().clear();
				return;
			case RefOntoUMLPackage.PACKAGE__PACKAGE_MERGE:
				getPackageMerge().clear();
				return;
			case RefOntoUMLPackage.PACKAGE__PACKAGED_ELEMENT:
				getPackagedElement().clear();
				return;
			case RefOntoUMLPackage.PACKAGE__NESTED_PACKAGE:
				getNestedPackage().clear();
				return;
			case RefOntoUMLPackage.PACKAGE__NESTING_PACKAGE:
				setNestingPackage((RefOntoUML.Package)null);
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
			case RefOntoUMLPackage.PACKAGE__OWNED_TYPE:
				return !getOwnedType().isEmpty();
			case RefOntoUMLPackage.PACKAGE__PACKAGE_MERGE:
				return packageMerge != null && !packageMerge.isEmpty();
			case RefOntoUMLPackage.PACKAGE__PACKAGED_ELEMENT:
				return packagedElement != null && !packagedElement.isEmpty();
			case RefOntoUMLPackage.PACKAGE__NESTED_PACKAGE:
				return !getNestedPackage().isEmpty();
			case RefOntoUMLPackage.PACKAGE__NESTING_PACKAGE:
				return basicGetNestingPackage() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * The parsed OCL expression for the derivation of '{@link #getOwnedType <em>Owned Type</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedType
	 * @generated
	 */
	private static OCLExpression<EClassifier> ownedTypeDeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getNestedPackage <em>Nested Package</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNestedPackage
	 * @generated
	 */
	private static OCLExpression<EClassifier> nestedPackageDeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getNestingPackage <em>Nesting Package</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNestingPackage
	 * @generated
	 */
	private static OCLExpression<EClassifier> nestingPackageDeriveOCL;
	
	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
} //PackageImpl
