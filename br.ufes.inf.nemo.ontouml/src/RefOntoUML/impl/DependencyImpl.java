/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.Dependency;
import RefOntoUML.DirectedRelationship;
import RefOntoUML.Element;
import RefOntoUML.NamedElement;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.Relationship;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;

import org.eclipse.ocl.ecore.OCL;

import org.eclipse.ocl.ecore.OCL.Helper;

import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dependency</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.DependencyImpl#getRelatedElement <em>Related Element</em>}</li>
 *   <li>{@link RefOntoUML.impl.DependencyImpl#getSource <em>Source</em>}</li>
 *   <li>{@link RefOntoUML.impl.DependencyImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link RefOntoUML.impl.DependencyImpl#getSupplier <em>Supplier</em>}</li>
 *   <li>{@link RefOntoUML.impl.DependencyImpl#getClient <em>Client</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DependencyImpl extends PackageableElementImpl implements Dependency {
	/**
	 * The cached value of the '{@link #getSupplier() <em>Supplier</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupplier()
	 * @generated
	 * @ordered
	 */
	protected EList<NamedElement> supplier;

	/**
	 * The cached value of the '{@link #getClient() <em>Client</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClient()
	 * @generated
	 * @ordered
	 */
	protected EList<NamedElement> client;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DependencyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getDependency();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Element> getRelatedElement() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getRelationship_RelatedElement();
	
		if (relatedElementDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getRelationship(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				relatedElementDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(relatedElementDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Element> result = (Collection<Element>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<Element>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Element> getSource() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getDirectedRelationship_Source();
	
		if (sourceDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getDirectedRelationship(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				sourceDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(sourceDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Element> result = (Collection<Element>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<Element>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Element> getTarget() {
		EStructuralFeature eFeature = RefOntoUMLPackage.eINSTANCE.getDirectedRelationship_Target();
	
		if (targetDeriveOCL == null) { 
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getDirectedRelationship(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				targetDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(targetDeriveOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Element> result = (Collection<Element>) query.evaluate(this);
		return new EcoreEList.UnmodifiableEList<Element>(this, eFeature, result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedElement> getSupplier() {
		if (supplier == null) {
			supplier = new EObjectResolvingEList<NamedElement>(NamedElement.class, this, RefOntoUMLPackage.DEPENDENCY__SUPPLIER);
		}
		return supplier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedElement> getClient() {
		if (client == null) {
			client = new EObjectWithInverseResolvingEList.ManyInverse<NamedElement>(NamedElement.class, this, RefOntoUMLPackage.DEPENDENCY__CLIENT, RefOntoUMLPackage.NAMED_ELEMENT__CLIENT_DEPENDENCY);
		}
		return client;
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
			case RefOntoUMLPackage.DEPENDENCY__CLIENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getClient()).basicAdd(otherEnd, msgs);
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
			case RefOntoUMLPackage.DEPENDENCY__CLIENT:
				return ((InternalEList<?>)getClient()).basicRemove(otherEnd, msgs);
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
			case RefOntoUMLPackage.DEPENDENCY__RELATED_ELEMENT:
				return getRelatedElement();
			case RefOntoUMLPackage.DEPENDENCY__SOURCE:
				return getSource();
			case RefOntoUMLPackage.DEPENDENCY__TARGET:
				return getTarget();
			case RefOntoUMLPackage.DEPENDENCY__SUPPLIER:
				return getSupplier();
			case RefOntoUMLPackage.DEPENDENCY__CLIENT:
				return getClient();
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
			case RefOntoUMLPackage.DEPENDENCY__SUPPLIER:
				getSupplier().clear();
				getSupplier().addAll((Collection<? extends NamedElement>)newValue);
				return;
			case RefOntoUMLPackage.DEPENDENCY__CLIENT:
				getClient().clear();
				getClient().addAll((Collection<? extends NamedElement>)newValue);
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
			case RefOntoUMLPackage.DEPENDENCY__SUPPLIER:
				getSupplier().clear();
				return;
			case RefOntoUMLPackage.DEPENDENCY__CLIENT:
				getClient().clear();
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
			case RefOntoUMLPackage.DEPENDENCY__RELATED_ELEMENT:
				return !getRelatedElement().isEmpty();
			case RefOntoUMLPackage.DEPENDENCY__SOURCE:
				return !getSource().isEmpty();
			case RefOntoUMLPackage.DEPENDENCY__TARGET:
				return !getTarget().isEmpty();
			case RefOntoUMLPackage.DEPENDENCY__SUPPLIER:
				return supplier != null && !supplier.isEmpty();
			case RefOntoUMLPackage.DEPENDENCY__CLIENT:
				return client != null && !client.isEmpty();
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
		if (baseClass == Relationship.class) {
			switch (derivedFeatureID) {
				case RefOntoUMLPackage.DEPENDENCY__RELATED_ELEMENT: return RefOntoUMLPackage.RELATIONSHIP__RELATED_ELEMENT;
				default: return -1;
			}
		}
		if (baseClass == DirectedRelationship.class) {
			switch (derivedFeatureID) {
				case RefOntoUMLPackage.DEPENDENCY__SOURCE: return RefOntoUMLPackage.DIRECTED_RELATIONSHIP__SOURCE;
				case RefOntoUMLPackage.DEPENDENCY__TARGET: return RefOntoUMLPackage.DIRECTED_RELATIONSHIP__TARGET;
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
		if (baseClass == Relationship.class) {
			switch (baseFeatureID) {
				case RefOntoUMLPackage.RELATIONSHIP__RELATED_ELEMENT: return RefOntoUMLPackage.DEPENDENCY__RELATED_ELEMENT;
				default: return -1;
			}
		}
		if (baseClass == DirectedRelationship.class) {
			switch (baseFeatureID) {
				case RefOntoUMLPackage.DIRECTED_RELATIONSHIP__SOURCE: return RefOntoUMLPackage.DEPENDENCY__SOURCE;
				case RefOntoUMLPackage.DIRECTED_RELATIONSHIP__TARGET: return RefOntoUMLPackage.DEPENDENCY__TARGET;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * The parsed OCL expression for the derivation of '{@link #getRelatedElement <em>Related Element</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelatedElement
	 * @generated
	 */
	private static OCLExpression<EClassifier> relatedElementDeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getSource <em>Source</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource
	 * @generated
	 */
	private static OCLExpression<EClassifier> sourceDeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getTarget <em>Target</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget
	 * @generated
	 */
	private static OCLExpression<EClassifier> targetDeriveOCL;
	
	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
} //DependencyImpl
