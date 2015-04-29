/**
 */
package sml2.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import sml2.ExportableNode;
import sml2.SituationTypeParameter;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Situation Type Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sml2.impl.SituationTypeParameterImpl#getNodeReference <em>Node Reference</em>}</li>
 *   <li>{@link sml2.impl.SituationTypeParameterImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SituationTypeParameterImpl extends MinimalEObjectImpl.Container implements SituationTypeParameter {
	/**
	 * The cached value of the '{@link #getNodeReference() <em>Node Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeReference()
	 * @generated
	 * @ordered
	 */
	protected ExportableNode nodeReference;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SituationTypeParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.SITUATION_TYPE_PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExportableNode getNodeReference() {
		if (nodeReference != null && nodeReference.eIsProxy()) {
			InternalEObject oldNodeReference = (InternalEObject)nodeReference;
			nodeReference = (ExportableNode)eResolveProxy(oldNodeReference);
			if (nodeReference != oldNodeReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.SITUATION_TYPE_PARAMETER__NODE_REFERENCE, oldNodeReference, nodeReference));
			}
		}
		return nodeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExportableNode basicGetNodeReference() {
		return nodeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNodeReference(ExportableNode newNodeReference, NotificationChain msgs) {
		ExportableNode oldNodeReference = nodeReference;
		nodeReference = newNodeReference;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Sml2Package.SITUATION_TYPE_PARAMETER__NODE_REFERENCE, oldNodeReference, newNodeReference);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeReference(ExportableNode newNodeReference) {
		if (newNodeReference != nodeReference) {
			NotificationChain msgs = null;
			if (nodeReference != null)
				msgs = ((InternalEObject)nodeReference).eInverseRemove(this, Sml2Package.EXPORTABLE_NODE__NODE_PARAMETER, ExportableNode.class, msgs);
			if (newNodeReference != null)
				msgs = ((InternalEObject)newNodeReference).eInverseAdd(this, Sml2Package.EXPORTABLE_NODE__NODE_PARAMETER, ExportableNode.class, msgs);
			msgs = basicSetNodeReference(newNodeReference, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.SITUATION_TYPE_PARAMETER__NODE_REFERENCE, newNodeReference, newNodeReference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.SITUATION_TYPE_PARAMETER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Sml2Package.SITUATION_TYPE_PARAMETER__NODE_REFERENCE:
				if (nodeReference != null)
					msgs = ((InternalEObject)nodeReference).eInverseRemove(this, Sml2Package.EXPORTABLE_NODE__NODE_PARAMETER, ExportableNode.class, msgs);
				return basicSetNodeReference((ExportableNode)otherEnd, msgs);
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
			case Sml2Package.SITUATION_TYPE_PARAMETER__NODE_REFERENCE:
				return basicSetNodeReference(null, msgs);
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
			case Sml2Package.SITUATION_TYPE_PARAMETER__NODE_REFERENCE:
				if (resolve) return getNodeReference();
				return basicGetNodeReference();
			case Sml2Package.SITUATION_TYPE_PARAMETER__NAME:
				return getName();
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
			case Sml2Package.SITUATION_TYPE_PARAMETER__NODE_REFERENCE:
				setNodeReference((ExportableNode)newValue);
				return;
			case Sml2Package.SITUATION_TYPE_PARAMETER__NAME:
				setName((String)newValue);
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
			case Sml2Package.SITUATION_TYPE_PARAMETER__NODE_REFERENCE:
				setNodeReference((ExportableNode)null);
				return;
			case Sml2Package.SITUATION_TYPE_PARAMETER__NAME:
				setName(NAME_EDEFAULT);
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
			case Sml2Package.SITUATION_TYPE_PARAMETER__NODE_REFERENCE:
				return nodeReference != null;
			case Sml2Package.SITUATION_TYPE_PARAMETER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //SituationTypeParameterImpl
