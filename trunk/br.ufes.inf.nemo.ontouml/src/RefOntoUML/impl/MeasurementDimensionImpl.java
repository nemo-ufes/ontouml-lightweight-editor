/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.BasicMeasurementRegion;
import RefOntoUML.MeasurementDimension;
import RefOntoUML.MeasurementDomain;
import RefOntoUML.RefOntoUMLPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;

import org.eclipse.ocl.ecore.OCL;

import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measurement Dimension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.MeasurementDimensionImpl#getDomain <em>Domain</em>}</li>
 *   <li>{@link RefOntoUML.impl.MeasurementDimensionImpl#getUnitOfMeasure <em>Unit Of Measure</em>}</li>
 *   <li>{@link RefOntoUML.impl.MeasurementDimensionImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link RefOntoUML.impl.MeasurementDimensionImpl#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MeasurementDimensionImpl extends MeasurementStructureImpl implements MeasurementDimension {
	/**
	 * The default value of the '{@link #getUnitOfMeasure() <em>Unit Of Measure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnitOfMeasure()
	 * @generated
	 * @ordered
	 */
	protected static final String UNIT_OF_MEASURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUnitOfMeasure() <em>Unit Of Measure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnitOfMeasure()
	 * @generated
	 * @ordered
	 */
	protected String unitOfMeasure = UNIT_OF_MEASURE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected BasicMeasurementRegion lowerBound;

	/**
	 * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected BasicMeasurementRegion upperBound;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasurementDimensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getMeasurementDimension();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementDomain getDomain() {
		if (eContainerFeatureID() != RefOntoUMLPackage.MEASUREMENT_DIMENSION__DOMAIN) return null;
		return (MeasurementDomain)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDomain(MeasurementDomain newDomain, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDomain, RefOntoUMLPackage.MEASUREMENT_DIMENSION__DOMAIN, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomain(MeasurementDomain newDomain) {
		if (newDomain != eInternalContainer() || (eContainerFeatureID() != RefOntoUMLPackage.MEASUREMENT_DIMENSION__DOMAIN && newDomain != null)) {
			if (EcoreUtil.isAncestor(this, newDomain))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDomain != null)
				msgs = ((InternalEObject)newDomain).eInverseAdd(this, RefOntoUMLPackage.MEASUREMENT_DOMAIN__OWNED_DIMENSIONS, MeasurementDomain.class, msgs);
			msgs = basicSetDomain(newDomain, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_DIMENSION__DOMAIN, newDomain, newDomain));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnitOfMeasure(String newUnitOfMeasure) {
		String oldUnitOfMeasure = unitOfMeasure;
		unitOfMeasure = newUnitOfMeasure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_DIMENSION__UNIT_OF_MEASURE, oldUnitOfMeasure, unitOfMeasure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicMeasurementRegion getLowerBound() {
		return lowerBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLowerBound(BasicMeasurementRegion newLowerBound, NotificationChain msgs) {
		BasicMeasurementRegion oldLowerBound = lowerBound;
		lowerBound = newLowerBound;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_DIMENSION__LOWER_BOUND, oldLowerBound, newLowerBound);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowerBound(BasicMeasurementRegion newLowerBound) {
		if (newLowerBound != lowerBound) {
			NotificationChain msgs = null;
			if (lowerBound != null)
				msgs = ((InternalEObject)lowerBound).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RefOntoUMLPackage.MEASUREMENT_DIMENSION__LOWER_BOUND, null, msgs);
			if (newLowerBound != null)
				msgs = ((InternalEObject)newLowerBound).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RefOntoUMLPackage.MEASUREMENT_DIMENSION__LOWER_BOUND, null, msgs);
			msgs = basicSetLowerBound(newLowerBound, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_DIMENSION__LOWER_BOUND, newLowerBound, newLowerBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicMeasurementRegion getUpperBound() {
		return upperBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUpperBound(BasicMeasurementRegion newUpperBound, NotificationChain msgs) {
		BasicMeasurementRegion oldUpperBound = upperBound;
		upperBound = newUpperBound;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_DIMENSION__UPPER_BOUND, oldUpperBound, newUpperBound);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperBound(BasicMeasurementRegion newUpperBound) {
		if (newUpperBound != upperBound) {
			NotificationChain msgs = null;
			if (upperBound != null)
				msgs = ((InternalEObject)upperBound).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RefOntoUMLPackage.MEASUREMENT_DIMENSION__UPPER_BOUND, null, msgs);
			if (newUpperBound != null)
				msgs = ((InternalEObject)newUpperBound).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RefOntoUMLPackage.MEASUREMENT_DIMENSION__UPPER_BOUND, null, msgs);
			msgs = basicSetUpperBound(newUpperBound, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MEASUREMENT_DIMENSION__UPPER_BOUND, newUpperBound, newUpperBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNonBoundary() {
		if (isNonBoundaryBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getMeasurementDimension().getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getMeasurementDimension(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				isNonBoundaryBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(isNonBoundaryBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOneBoundary() {
		if (isOneBoundaryBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getMeasurementDimension().getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getMeasurementDimension(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				isOneBoundaryBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(isOneBoundaryBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCircular() {
		if (isCircularBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getMeasurementDimension().getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getMeasurementDimension(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				isCircularBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(isCircularBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__DOMAIN:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDomain((MeasurementDomain)otherEnd, msgs);
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
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__DOMAIN:
				return basicSetDomain(null, msgs);
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__LOWER_BOUND:
				return basicSetLowerBound(null, msgs);
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__UPPER_BOUND:
				return basicSetUpperBound(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__DOMAIN:
				return eInternalContainer().eInverseRemove(this, RefOntoUMLPackage.MEASUREMENT_DOMAIN__OWNED_DIMENSIONS, MeasurementDomain.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__DOMAIN:
				return getDomain();
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__UNIT_OF_MEASURE:
				return getUnitOfMeasure();
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__LOWER_BOUND:
				return getLowerBound();
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__UPPER_BOUND:
				return getUpperBound();
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
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__DOMAIN:
				setDomain((MeasurementDomain)newValue);
				return;
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__UNIT_OF_MEASURE:
				setUnitOfMeasure((String)newValue);
				return;
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__LOWER_BOUND:
				setLowerBound((BasicMeasurementRegion)newValue);
				return;
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__UPPER_BOUND:
				setUpperBound((BasicMeasurementRegion)newValue);
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
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__DOMAIN:
				setDomain((MeasurementDomain)null);
				return;
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__UNIT_OF_MEASURE:
				setUnitOfMeasure(UNIT_OF_MEASURE_EDEFAULT);
				return;
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__LOWER_BOUND:
				setLowerBound((BasicMeasurementRegion)null);
				return;
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__UPPER_BOUND:
				setUpperBound((BasicMeasurementRegion)null);
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
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__DOMAIN:
				return getDomain() != null;
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__UNIT_OF_MEASURE:
				return UNIT_OF_MEASURE_EDEFAULT == null ? unitOfMeasure != null : !UNIT_OF_MEASURE_EDEFAULT.equals(unitOfMeasure);
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__LOWER_BOUND:
				return lowerBound != null;
			case RefOntoUMLPackage.MEASUREMENT_DIMENSION__UPPER_BOUND:
				return upperBound != null;
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
		result.append(" (unitOfMeasure: ");
		result.append(unitOfMeasure);
		result.append(')');
		return result.toString();
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #isNonBoundary <em>Is Non Boundary</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNonBoundary
	 * @generated
	 */
	private static OCLExpression<EClassifier> isNonBoundaryBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #isOneBoundary <em>Is One Boundary</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOneBoundary
	 * @generated
	 */
	private static OCLExpression<EClassifier> isOneBoundaryBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #isCircular <em>Is Circular</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCircular
	 * @generated
	 */
	private static OCLExpression<EClassifier> isCircularBodyOCL;
	
	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
} //MeasurementDimensionImpl
