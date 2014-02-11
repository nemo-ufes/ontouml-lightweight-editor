/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.InstanceSpecification;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.Slot;
import RefOntoUML.StructuralFeature;
import RefOntoUML.ValueSpecification;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Slot</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.SlotImpl#getDefiningFeature <em>Defining Feature</em>}</li>
 *   <li>{@link RefOntoUML.impl.SlotImpl#getValue <em>Value</em>}</li>
 *   <li>{@link RefOntoUML.impl.SlotImpl#getOwningInstance <em>Owning Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SlotImpl extends ElementImpl implements Slot {
	/**
	 * The cached value of the '{@link #getDefiningFeature() <em>Defining Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefiningFeature()
	 * @generated
	 * @ordered
	 */
	protected StructuralFeature definingFeature;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected EList<ValueSpecification> value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SlotImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getSlot();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralFeature getDefiningFeature() {
		if (definingFeature != null && definingFeature.eIsProxy()) {
			InternalEObject oldDefiningFeature = (InternalEObject)definingFeature;
			definingFeature = (StructuralFeature)eResolveProxy(oldDefiningFeature);
			if (definingFeature != oldDefiningFeature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RefOntoUMLPackage.SLOT__DEFINING_FEATURE, oldDefiningFeature, definingFeature));
			}
		}
		return definingFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralFeature basicGetDefiningFeature() {
		return definingFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefiningFeature(StructuralFeature newDefiningFeature) {
		StructuralFeature oldDefiningFeature = definingFeature;
		definingFeature = newDefiningFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.SLOT__DEFINING_FEATURE, oldDefiningFeature, definingFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ValueSpecification> getValue() {
		if (value == null) {
			value = new EObjectContainmentEList<ValueSpecification>(ValueSpecification.class, this, RefOntoUMLPackage.SLOT__VALUE);
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanceSpecification getOwningInstance() {
		if (eContainerFeatureID() != RefOntoUMLPackage.SLOT__OWNING_INSTANCE) return null;
		return (InstanceSpecification)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningInstance(InstanceSpecification newOwningInstance, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwningInstance, RefOntoUMLPackage.SLOT__OWNING_INSTANCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwningInstance(InstanceSpecification newOwningInstance) {
		if (newOwningInstance != eInternalContainer() || (eContainerFeatureID() != RefOntoUMLPackage.SLOT__OWNING_INSTANCE && newOwningInstance != null)) {
			if (EcoreUtil.isAncestor(this, newOwningInstance))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningInstance != null)
				msgs = ((InternalEObject)newOwningInstance).eInverseAdd(this, RefOntoUMLPackage.INSTANCE_SPECIFICATION__SLOT, InstanceSpecification.class, msgs);
			msgs = basicSetOwningInstance(newOwningInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.SLOT__OWNING_INSTANCE, newOwningInstance, newOwningInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.SLOT__OWNING_INSTANCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningInstance((InstanceSpecification)otherEnd, msgs);
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
			case RefOntoUMLPackage.SLOT__VALUE:
				return ((InternalEList<?>)getValue()).basicRemove(otherEnd, msgs);
			case RefOntoUMLPackage.SLOT__OWNING_INSTANCE:
				return basicSetOwningInstance(null, msgs);
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
			case RefOntoUMLPackage.SLOT__OWNING_INSTANCE:
				return eInternalContainer().eInverseRemove(this, RefOntoUMLPackage.INSTANCE_SPECIFICATION__SLOT, InstanceSpecification.class, msgs);
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
			case RefOntoUMLPackage.SLOT__DEFINING_FEATURE:
				if (resolve) return getDefiningFeature();
				return basicGetDefiningFeature();
			case RefOntoUMLPackage.SLOT__VALUE:
				return getValue();
			case RefOntoUMLPackage.SLOT__OWNING_INSTANCE:
				return getOwningInstance();
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
			case RefOntoUMLPackage.SLOT__DEFINING_FEATURE:
				setDefiningFeature((StructuralFeature)newValue);
				return;
			case RefOntoUMLPackage.SLOT__VALUE:
				getValue().clear();
				getValue().addAll((Collection<? extends ValueSpecification>)newValue);
				return;
			case RefOntoUMLPackage.SLOT__OWNING_INSTANCE:
				setOwningInstance((InstanceSpecification)newValue);
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
			case RefOntoUMLPackage.SLOT__DEFINING_FEATURE:
				setDefiningFeature((StructuralFeature)null);
				return;
			case RefOntoUMLPackage.SLOT__VALUE:
				getValue().clear();
				return;
			case RefOntoUMLPackage.SLOT__OWNING_INSTANCE:
				setOwningInstance((InstanceSpecification)null);
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
			case RefOntoUMLPackage.SLOT__DEFINING_FEATURE:
				return definingFeature != null;
			case RefOntoUMLPackage.SLOT__VALUE:
				return value != null && !value.isEmpty();
			case RefOntoUMLPackage.SLOT__OWNING_INSTANCE:
				return getOwningInstance() != null;
		}
		return super.eIsSet(featureID);
	}

} //SlotImpl
