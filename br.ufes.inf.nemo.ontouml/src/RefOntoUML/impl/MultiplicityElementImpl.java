/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.MultiplicityElement;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.ValueSpecification;

import RefOntoUML.util.RefOntoUMLValidator;

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;

import org.eclipse.ocl.ecore.OCL;

import org.eclipse.ocl.ecore.OCL.Helper;

import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Multiplicity Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.MultiplicityElementImpl#isIsOrdered <em>Is Ordered</em>}</li>
 *   <li>{@link RefOntoUML.impl.MultiplicityElementImpl#isIsUnique <em>Is Unique</em>}</li>
 *   <li>{@link RefOntoUML.impl.MultiplicityElementImpl#getUpper <em>Upper</em>}</li>
 *   <li>{@link RefOntoUML.impl.MultiplicityElementImpl#getLower <em>Lower</em>}</li>
 *   <li>{@link RefOntoUML.impl.MultiplicityElementImpl#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link RefOntoUML.impl.MultiplicityElementImpl#getLowerValue <em>Lower Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MultiplicityElementImpl extends ElementImpl implements MultiplicityElement {
	/**
	 * The default value of the '{@link #isIsOrdered() <em>Is Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsOrdered()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ORDERED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsOrdered() <em>Is Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsOrdered()
	 * @generated
	 * @ordered
	 */
	protected boolean isOrdered = IS_ORDERED_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsUnique() <em>Is Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUnique()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_UNIQUE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isIsUnique() <em>Is Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUnique()
	 * @generated
	 * @ordered
	 */
	protected boolean isUnique = IS_UNIQUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpper() <em>Upper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpper()
	 * @generated
	 * @ordered
	 */
	protected static final int UPPER_EDEFAULT = 1;

	/**
	 * The default value of the '{@link #getLower() <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLower()
	 * @generated
	 * @ordered
	 */
	protected static final int LOWER_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getUpperValue() <em>Upper Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperValue()
	 * @generated
	 * @ordered
	 */
	protected ValueSpecification upperValue;

	/**
	 * The cached value of the '{@link #getLowerValue() <em>Lower Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerValue()
	 * @generated
	 * @ordered
	 */
	protected ValueSpecification lowerValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MultiplicityElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getMultiplicityElement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsOrdered() {
		return isOrdered;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsOrdered(boolean newIsOrdered) {
		boolean oldIsOrdered = isOrdered;
		isOrdered = newIsOrdered;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MULTIPLICITY_ELEMENT__IS_ORDERED, oldIsOrdered, isOrdered));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsUnique() {
		return isUnique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsUnique(boolean newIsUnique) {
		boolean oldIsUnique = isUnique;
		isUnique = newIsUnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MULTIPLICITY_ELEMENT__IS_UNIQUE, oldIsUnique, isUnique));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getUpper() {
		if (upperDeriveOCL == null) { 
			EStructuralFeature eFeature = (RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_Upper());
			
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				upperDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(upperDeriveOCL);
	
		return ((Integer) query.evaluate(this)).intValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpper(int newUpper) {
		// TODO: implement this method to set the 'Upper' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLower() {
		if (lowerDeriveOCL == null) { 
			EStructuralFeature eFeature = (RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_Lower());
			
			Helper helper = OCL_ENV.createOCLHelper();
			helper.setAttributeContext(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement(), eFeature);
			
			EAnnotation ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String derive = (String) ocl.getDetails().get("derive");
			
			try {
				lowerDeriveOCL = helper.createQuery(derive);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(lowerDeriveOCL);
	
		return ((Integer) query.evaluate(this)).intValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLower(int newLower) {
		// TODO: implement this method to set the 'Lower' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSpecification getUpperValue() {
		return upperValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUpperValue(ValueSpecification newUpperValue, NotificationChain msgs) {
		ValueSpecification oldUpperValue = upperValue;
		upperValue = newUpperValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE, oldUpperValue, newUpperValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperValue(ValueSpecification newUpperValue) {
		if (newUpperValue != upperValue) {
			NotificationChain msgs = null;
			if (upperValue != null)
				msgs = ((InternalEObject)upperValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE, null, msgs);
			if (newUpperValue != null)
				msgs = ((InternalEObject)newUpperValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE, null, msgs);
			msgs = basicSetUpperValue(newUpperValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE, newUpperValue, newUpperValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSpecification getLowerValue() {
		return lowerValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLowerValue(ValueSpecification newLowerValue, NotificationChain msgs) {
		ValueSpecification oldLowerValue = lowerValue;
		lowerValue = newLowerValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE, oldLowerValue, newLowerValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowerValue(ValueSpecification newLowerValue) {
		if (newLowerValue != lowerValue) {
			NotificationChain msgs = null;
			if (lowerValue != null)
				msgs = ((InternalEObject)lowerValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE, null, msgs);
			if (newLowerValue != null)
				msgs = ((InternalEObject)newLowerValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE, null, msgs);
			msgs = basicSetLowerValue(newLowerValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE, newLowerValue, newLowerValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unused")
	public boolean lower_ge_0(DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 RefOntoUMLValidator.MULTIPLICITY_ELEMENT__LOWER_GE_0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "lower_ge_0", EObjectValidator.getObjectLabel(this, context) }),
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
	public boolean upper_ge_lower(DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 RefOntoUMLValidator.MULTIPLICITY_ELEMENT__UPPER_GE_LOWER,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "upper_ge_lower", EObjectValidator.getObjectLabel(this, context) }),
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
	public boolean value_specification_no_side_effects(DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 RefOntoUMLValidator.MULTIPLICITY_ELEMENT__VALUE_SPECIFICATION_NO_SIDE_EFFECTS,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "value_specification_no_side_effects", EObjectValidator.getObjectLabel(this, context) }),
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
	public boolean value_specification_constant(DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 RefOntoUMLValidator.MULTIPLICITY_ELEMENT__VALUE_SPECIFICATION_CONSTANT,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "value_specification_constant", EObjectValidator.getObjectLabel(this, context) }),
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
	public boolean isMultivalued() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean includesCardinality(int C) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean includesMultiplicity(MultiplicityElement M) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int lowerBound() {
		if (lowerBoundBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getMultiplicityElement().getEOperations().get(11);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				lowerBoundBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(lowerBoundBodyOCL);
	
		return ((Integer) query.evaluate(this)).intValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int upperBound() {
		if (upperBoundBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getMultiplicityElement().getEOperations().get(12);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				upperBoundBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(upperBoundBodyOCL);
	
		return ((Integer) query.evaluate(this)).intValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean compatibleWith(MultiplicityElement other) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean is(int lowerbound, int upperbound) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE:
				return basicSetUpperValue(null, msgs);
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE:
				return basicSetLowerValue(null, msgs);
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
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__IS_ORDERED:
				return isIsOrdered();
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__IS_UNIQUE:
				return isIsUnique();
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER:
				return getUpper();
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER:
				return getLower();
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE:
				return getUpperValue();
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE:
				return getLowerValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__IS_ORDERED:
				setIsOrdered((Boolean)newValue);
				return;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__IS_UNIQUE:
				setIsUnique((Boolean)newValue);
				return;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER:
				setUpper((Integer)newValue);
				return;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER:
				setLower((Integer)newValue);
				return;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE:
				setUpperValue((ValueSpecification)newValue);
				return;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE:
				setLowerValue((ValueSpecification)newValue);
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
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__IS_ORDERED:
				setIsOrdered(IS_ORDERED_EDEFAULT);
				return;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__IS_UNIQUE:
				setIsUnique(IS_UNIQUE_EDEFAULT);
				return;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER:
				setUpper(UPPER_EDEFAULT);
				return;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER:
				setLower(LOWER_EDEFAULT);
				return;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE:
				setUpperValue((ValueSpecification)null);
				return;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE:
				setLowerValue((ValueSpecification)null);
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
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__IS_ORDERED:
				return isOrdered != IS_ORDERED_EDEFAULT;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__IS_UNIQUE:
				return isUnique != IS_UNIQUE_EDEFAULT;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER:
				return getUpper() != UPPER_EDEFAULT;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER:
				return getLower() != LOWER_EDEFAULT;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE:
				return upperValue != null;
			case RefOntoUMLPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE:
				return lowerValue != null;
		}
		return super.eIsSet(featureID);
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
		result.append(" (isOrdered: ");
		result.append(isOrdered);
		result.append(", isUnique: ");
		result.append(isUnique);
		result.append(')');
		return result.toString();
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #lowerBound <em>Lower Bound</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #lowerBound
	 * @generated
	 */
	private static OCLExpression<EClassifier> lowerBoundBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #upperBound <em>Upper Bound</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #upperBound
	 * @generated
	 */
	private static OCLExpression<EClassifier> upperBoundBodyOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getUpper <em>Upper</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpper
	 * @generated
	 */
	private static OCLExpression<EClassifier> upperDeriveOCL;
	
	/**
	 * The parsed OCL expression for the derivation of '{@link #getLower <em>Lower</em>}' property.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLower
	 * @generated
	 */
	private static OCLExpression<EClassifier> lowerDeriveOCL;
	
	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
} //MultiplicityElementImpl
