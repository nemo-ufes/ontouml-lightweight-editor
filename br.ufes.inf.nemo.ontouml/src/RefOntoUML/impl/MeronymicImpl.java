/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;

import org.eclipse.ocl.ecore.OCL;

import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Meronymic</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.MeronymicImpl#isIsShareable <em>Is Shareable</em>}</li>
 *   <li>{@link RefOntoUML.impl.MeronymicImpl#isIsEssential <em>Is Essential</em>}</li>
 *   <li>{@link RefOntoUML.impl.MeronymicImpl#isIsInseparable <em>Is Inseparable</em>}</li>
 *   <li>{@link RefOntoUML.impl.MeronymicImpl#isIsImmutablePart <em>Is Immutable Part</em>}</li>
 *   <li>{@link RefOntoUML.impl.MeronymicImpl#isIsImmutableWhole <em>Is Immutable Whole</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MeronymicImpl extends DirectedBinaryAssociationImpl implements Meronymic {
	/**
	 * The default value of the '{@link #isIsShareable() <em>Is Shareable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsShareable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SHAREABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isIsShareable() <em>Is Shareable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsShareable()
	 * @generated
	 * @ordered
	 */
	protected boolean isShareable = IS_SHAREABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsEssential() <em>Is Essential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEssential()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ESSENTIAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsEssential() <em>Is Essential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEssential()
	 * @generated
	 * @ordered
	 */
	protected boolean isEssential = IS_ESSENTIAL_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsInseparable() <em>Is Inseparable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInseparable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_INSEPARABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsInseparable() <em>Is Inseparable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInseparable()
	 * @generated
	 * @ordered
	 */
	protected boolean isInseparable = IS_INSEPARABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsImmutablePart() <em>Is Immutable Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImmutablePart()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_IMMUTABLE_PART_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsImmutablePart() <em>Is Immutable Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImmutablePart()
	 * @generated
	 * @ordered
	 */
	protected boolean isImmutablePart = IS_IMMUTABLE_PART_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsImmutableWhole() <em>Is Immutable Whole</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImmutableWhole()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_IMMUTABLE_WHOLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsImmutableWhole() <em>Is Immutable Whole</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImmutableWhole()
	 * @generated
	 * @ordered
	 */
	protected boolean isImmutableWhole = IS_IMMUTABLE_WHOLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeronymicImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getMeronymic();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsShareable() {
		return isShareable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsShareable(boolean newIsShareable) {
		boolean oldIsShareable = isShareable;
		isShareable = newIsShareable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MERONYMIC__IS_SHAREABLE, oldIsShareable, isShareable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsEssential() {
		return isEssential;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEssential(boolean newIsEssential) {
		boolean oldIsEssential = isEssential;
		isEssential = newIsEssential;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MERONYMIC__IS_ESSENTIAL, oldIsEssential, isEssential));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsInseparable() {
		return isInseparable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsInseparable(boolean newIsInseparable) {
		boolean oldIsInseparable = isInseparable;
		isInseparable = newIsInseparable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MERONYMIC__IS_INSEPARABLE, oldIsInseparable, isInseparable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsImmutablePart() {
		return isImmutablePart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsImmutablePart(boolean newIsImmutablePart) {
		boolean oldIsImmutablePart = isImmutablePart;
		isImmutablePart = newIsImmutablePart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MERONYMIC__IS_IMMUTABLE_PART, oldIsImmutablePart, isImmutablePart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsImmutableWhole() {
		return isImmutableWhole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsImmutableWhole(boolean newIsImmutableWhole) {
		boolean oldIsImmutableWhole = isImmutableWhole;
		isImmutableWhole = newIsImmutableWhole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.MERONYMIC__IS_IMMUTABLE_WHOLE, oldIsImmutableWhole, isImmutableWhole));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property wholeEnd() {
		if (wholeEndBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getMeronymic().getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getMeronymic(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				wholeEndBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(wholeEndBodyOCL);
	
		return (Property) query.evaluate(this);
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property partEnd() {
		if (partEndBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getMeronymic().getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getMeronymic(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				partEndBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(partEndBodyOCL);
	
		return (Property) query.evaluate(this);
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier whole() {
		if (wholeBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getMeronymic().getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getMeronymic(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				wholeBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(wholeBodyOCL);
	
		return (Classifier) query.evaluate(this);
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier part() {
		if (partBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getMeronymic().getEOperations().get(3);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getMeronymic(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				partBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(partBodyOCL);
	
		return (Classifier) query.evaluate(this);
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RefOntoUMLPackage.MERONYMIC__IS_SHAREABLE:
				return isIsShareable();
			case RefOntoUMLPackage.MERONYMIC__IS_ESSENTIAL:
				return isIsEssential();
			case RefOntoUMLPackage.MERONYMIC__IS_INSEPARABLE:
				return isIsInseparable();
			case RefOntoUMLPackage.MERONYMIC__IS_IMMUTABLE_PART:
				return isIsImmutablePart();
			case RefOntoUMLPackage.MERONYMIC__IS_IMMUTABLE_WHOLE:
				return isIsImmutableWhole();
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
			case RefOntoUMLPackage.MERONYMIC__IS_SHAREABLE:
				setIsShareable((Boolean)newValue);
				return;
			case RefOntoUMLPackage.MERONYMIC__IS_ESSENTIAL:
				setIsEssential((Boolean)newValue);
				return;
			case RefOntoUMLPackage.MERONYMIC__IS_INSEPARABLE:
				setIsInseparable((Boolean)newValue);
				return;
			case RefOntoUMLPackage.MERONYMIC__IS_IMMUTABLE_PART:
				setIsImmutablePart((Boolean)newValue);
				return;
			case RefOntoUMLPackage.MERONYMIC__IS_IMMUTABLE_WHOLE:
				setIsImmutableWhole((Boolean)newValue);
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
			case RefOntoUMLPackage.MERONYMIC__IS_SHAREABLE:
				setIsShareable(IS_SHAREABLE_EDEFAULT);
				return;
			case RefOntoUMLPackage.MERONYMIC__IS_ESSENTIAL:
				setIsEssential(IS_ESSENTIAL_EDEFAULT);
				return;
			case RefOntoUMLPackage.MERONYMIC__IS_INSEPARABLE:
				setIsInseparable(IS_INSEPARABLE_EDEFAULT);
				return;
			case RefOntoUMLPackage.MERONYMIC__IS_IMMUTABLE_PART:
				setIsImmutablePart(IS_IMMUTABLE_PART_EDEFAULT);
				return;
			case RefOntoUMLPackage.MERONYMIC__IS_IMMUTABLE_WHOLE:
				setIsImmutableWhole(IS_IMMUTABLE_WHOLE_EDEFAULT);
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
			case RefOntoUMLPackage.MERONYMIC__IS_SHAREABLE:
				return isShareable != IS_SHAREABLE_EDEFAULT;
			case RefOntoUMLPackage.MERONYMIC__IS_ESSENTIAL:
				return isEssential != IS_ESSENTIAL_EDEFAULT;
			case RefOntoUMLPackage.MERONYMIC__IS_INSEPARABLE:
				return isInseparable != IS_INSEPARABLE_EDEFAULT;
			case RefOntoUMLPackage.MERONYMIC__IS_IMMUTABLE_PART:
				return isImmutablePart != IS_IMMUTABLE_PART_EDEFAULT;
			case RefOntoUMLPackage.MERONYMIC__IS_IMMUTABLE_WHOLE:
				return isImmutableWhole != IS_IMMUTABLE_WHOLE_EDEFAULT;
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
		result.append(" (isShareable: ");
		result.append(isShareable);
		result.append(", isEssential: ");
		result.append(isEssential);
		result.append(", isInseparable: ");
		result.append(isInseparable);
		result.append(", isImmutablePart: ");
		result.append(isImmutablePart);
		result.append(", isImmutableWhole: ");
		result.append(isImmutableWhole);
		result.append(')');
		return result.toString();
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #wholeEnd <em>Whole End</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #wholeEnd
	 * @generated
	 */
	private static OCLExpression<EClassifier> wholeEndBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #partEnd <em>Part End</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #partEnd
	 * @generated
	 */
	private static OCLExpression<EClassifier> partEndBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #whole <em>Whole</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #whole
	 * @generated
	 */
	private static OCLExpression<EClassifier> wholeBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #part <em>Part</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #part
	 * @generated
	 */
	private static OCLExpression<EClassifier> partBodyOCL;
	
	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
} //MeronymicImpl
