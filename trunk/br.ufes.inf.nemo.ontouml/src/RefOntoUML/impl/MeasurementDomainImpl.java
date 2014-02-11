/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.Expression;
import RefOntoUML.MeasurementDimension;
import RefOntoUML.MeasurementDomain;
import RefOntoUML.RefOntoUMLPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;

import org.eclipse.ocl.ecore.OCL;

import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measurement Domain</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.MeasurementDomainImpl#getOwnedDimensions <em>Owned Dimensions</em>}</li>
 *   <li>{@link RefOntoUML.impl.MeasurementDomainImpl#getCompositionRule <em>Composition Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasurementDomainImpl extends MeasurementStructureImpl implements MeasurementDomain {
	/**
	 * The cached value of the '{@link #getOwnedDimensions() <em>Owned Dimensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedDimensions()
	 * @generated
	 * @ordered
	 */
	protected EList<MeasurementDimension> ownedDimensions;

	/**
	 * The cached value of the '{@link #getCompositionRule() <em>Composition Rule</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompositionRule()
	 * @generated
	 * @ordered
	 */
	protected Expression compositionRule;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasurementDomainImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getMeasurementDomain();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MeasurementDimension> getOwnedDimensions() {
		if (ownedDimensions == null) {
			ownedDimensions = new EObjectContainmentWithInverseEList<MeasurementDimension>(MeasurementDimension.class, this, RefOntoUMLPackage.MEASUREMENT_DOMAIN__OWNED_DIMENSIONS, RefOntoUMLPackage.MEASUREMENT_DIMENSION__DOMAIN);
		}
		return ownedDimensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getCompositionRule() {
		return compositionRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompositionRule(Expression newCompositionRule, NotificationChain msgs) {
		Expression oldCompositionRule = compositionRule;
		compositionRule = newCompositionRule;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_DOMAIN__COMPOSITION_RULE, oldCompositionRule, newCompositionRule);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompositionRule(Expression newCompositionRule) {
		if (newCompositionRule != compositionRule) {
			NotificationChain msgs = null;
			if (compositionRule != null)
				msgs = ((InternalEObject)compositionRule).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RefOntoUMLPackage.MEASUREMENT_DOMAIN__COMPOSITION_RULE, null, msgs);
			if (newCompositionRule != null)
				msgs = ((InternalEObject)newCompositionRule).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RefOntoUMLPackage.MEASUREMENT_DOMAIN__COMPOSITION_RULE, null, msgs);
			msgs = basicSetCompositionRule(newCompositionRule, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_DOMAIN__COMPOSITION_RULE, newCompositionRule, newCompositionRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isScientific() {
		if (isScientificBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getMeasurementDomain().getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getMeasurementDomain(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				isScientificBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(isScientificBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
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
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN__OWNED_DIMENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedDimensions()).basicAdd(otherEnd, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN__OWNED_DIMENSIONS:
				return ((InternalEList<?>)getOwnedDimensions()).basicRemove(otherEnd, msgs);
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN__COMPOSITION_RULE:
				return basicSetCompositionRule(null, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN__OWNED_DIMENSIONS:
				return getOwnedDimensions();
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN__COMPOSITION_RULE:
				return getCompositionRule();
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
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN__OWNED_DIMENSIONS:
				getOwnedDimensions().clear();
				getOwnedDimensions().addAll((Collection<? extends MeasurementDimension>)newValue);
				return;
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN__COMPOSITION_RULE:
				setCompositionRule((Expression)newValue);
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
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN__OWNED_DIMENSIONS:
				getOwnedDimensions().clear();
				return;
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN__COMPOSITION_RULE:
				setCompositionRule((Expression)null);
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
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN__OWNED_DIMENSIONS:
				return ownedDimensions != null && !ownedDimensions.isEmpty();
			case RefOntoUMLPackage.MEASUREMENT_DOMAIN__COMPOSITION_RULE:
				return compositionRule != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #isScientific <em>Is Scientific</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScientific
	 * @generated
	 */
	private static OCLExpression<EClassifier> isScientificBodyOCL;
	
	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
} //MeasurementDomainImpl
