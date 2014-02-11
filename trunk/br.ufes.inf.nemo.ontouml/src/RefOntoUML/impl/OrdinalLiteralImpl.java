/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.BasicMeasurementRegion;
import RefOntoUML.LiteralSpecification;
import RefOntoUML.MeasurementRegion;
import RefOntoUML.OrdinalLiteral;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.ReferenceRegion;
import RefOntoUML.ReferenceStructure;
import RefOntoUML.Type;
import RefOntoUML.TypedElement;
import RefOntoUML.ValueSpecification;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ordinal Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.OrdinalLiteralImpl#getType <em>Type</em>}</li>
 *   <li>{@link RefOntoUML.impl.OrdinalLiteralImpl#getStructure <em>Structure</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrdinalLiteralImpl extends EnumerationLiteralImpl implements OrdinalLiteral {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Type type;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrdinalLiteralImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getOrdinalLiteral();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (Type)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RefOntoUMLPackage.ORDINAL_LITERAL__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Type newType) {
		Type oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.ORDINAL_LITERAL__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceStructure getStructure() {
		if (eContainerFeatureID() != RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE) return null;
		return (ReferenceStructure)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStructure(ReferenceStructure newStructure, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newStructure, RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStructure(ReferenceStructure newStructure) {
		if (newStructure != eInternalContainer() || (eContainerFeatureID() != RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE && newStructure != null)) {
			if (EcoreUtil.isAncestor(this, newStructure))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newStructure != null)
				msgs = ((InternalEObject)newStructure).eInverseAdd(this, RefOntoUMLPackage.REFERENCE_STRUCTURE__OWNED_REGIONS, ReferenceStructure.class, msgs);
			msgs = basicSetStructure(newStructure, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE, newStructure, newStructure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isComputable() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int integerValue() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean booleanValue() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String stringValue() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int unlimitedValue() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNull() {
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetStructure((ReferenceStructure)otherEnd, msgs);
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
			case RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE:
				return basicSetStructure(null, msgs);
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
			case RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE:
				return eInternalContainer().eInverseRemove(this, RefOntoUMLPackage.REFERENCE_STRUCTURE__OWNED_REGIONS, ReferenceStructure.class, msgs);
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
			case RefOntoUMLPackage.ORDINAL_LITERAL__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE:
				return getStructure();
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
			case RefOntoUMLPackage.ORDINAL_LITERAL__TYPE:
				setType((Type)newValue);
				return;
			case RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE:
				setStructure((ReferenceStructure)newValue);
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
			case RefOntoUMLPackage.ORDINAL_LITERAL__TYPE:
				setType((Type)null);
				return;
			case RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE:
				setStructure((ReferenceStructure)null);
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
			case RefOntoUMLPackage.ORDINAL_LITERAL__TYPE:
				return type != null;
			case RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE:
				return getStructure() != null;
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
		if (baseClass == TypedElement.class) {
			switch (derivedFeatureID) {
				case RefOntoUMLPackage.ORDINAL_LITERAL__TYPE: return RefOntoUMLPackage.TYPED_ELEMENT__TYPE;
				default: return -1;
			}
		}
		if (baseClass == ValueSpecification.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == LiteralSpecification.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ReferenceRegion.class) {
			switch (derivedFeatureID) {
				case RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE: return RefOntoUMLPackage.REFERENCE_REGION__STRUCTURE;
				default: return -1;
			}
		}
		if (baseClass == MeasurementRegion.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == BasicMeasurementRegion.class) {
			switch (derivedFeatureID) {
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
		if (baseClass == TypedElement.class) {
			switch (baseFeatureID) {
				case RefOntoUMLPackage.TYPED_ELEMENT__TYPE: return RefOntoUMLPackage.ORDINAL_LITERAL__TYPE;
				default: return -1;
			}
		}
		if (baseClass == ValueSpecification.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == LiteralSpecification.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ReferenceRegion.class) {
			switch (baseFeatureID) {
				case RefOntoUMLPackage.REFERENCE_REGION__STRUCTURE: return RefOntoUMLPackage.ORDINAL_LITERAL__STRUCTURE;
				default: return -1;
			}
		}
		if (baseClass == MeasurementRegion.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == BasicMeasurementRegion.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //OrdinalLiteralImpl
