/**
 */
package sml2.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import sml2.ExportableNode;
import sml2.SituationTypeParameter;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exportable Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sml2.impl.ExportableNodeImpl#getNodeParameter <em>Node Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ExportableNodeImpl extends NodeImpl implements ExportableNode {
	/**
	 * The cached value of the '{@link #getNodeParameter() <em>Node Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeParameter()
	 * @generated
	 * @ordered
	 */
	protected SituationTypeParameter nodeParameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExportableNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.EXPORTABLE_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SituationTypeParameter getNodeParameter() {
		if (nodeParameter != null && nodeParameter.eIsProxy()) {
			InternalEObject oldNodeParameter = (InternalEObject)nodeParameter;
			nodeParameter = (SituationTypeParameter)eResolveProxy(oldNodeParameter);
			if (nodeParameter != oldNodeParameter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.EXPORTABLE_NODE__NODE_PARAMETER, oldNodeParameter, nodeParameter));
			}
		}
		return nodeParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SituationTypeParameter basicGetNodeParameter() {
		return nodeParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNodeParameter(SituationTypeParameter newNodeParameter, NotificationChain msgs) {
		SituationTypeParameter oldNodeParameter = nodeParameter;
		nodeParameter = newNodeParameter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Sml2Package.EXPORTABLE_NODE__NODE_PARAMETER, oldNodeParameter, newNodeParameter);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeParameter(SituationTypeParameter newNodeParameter) {
		if (newNodeParameter != nodeParameter) {
			NotificationChain msgs = null;
			if (nodeParameter != null)
				msgs = ((InternalEObject)nodeParameter).eInverseRemove(this, Sml2Package.SITUATION_TYPE_PARAMETER__NODE_REFERENCE, SituationTypeParameter.class, msgs);
			if (newNodeParameter != null)
				msgs = ((InternalEObject)newNodeParameter).eInverseAdd(this, Sml2Package.SITUATION_TYPE_PARAMETER__NODE_REFERENCE, SituationTypeParameter.class, msgs);
			msgs = basicSetNodeParameter(newNodeParameter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.EXPORTABLE_NODE__NODE_PARAMETER, newNodeParameter, newNodeParameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Sml2Package.EXPORTABLE_NODE__NODE_PARAMETER:
				if (nodeParameter != null)
					msgs = ((InternalEObject)nodeParameter).eInverseRemove(this, Sml2Package.SITUATION_TYPE_PARAMETER__NODE_REFERENCE, SituationTypeParameter.class, msgs);
				return basicSetNodeParameter((SituationTypeParameter)otherEnd, msgs);
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
			case Sml2Package.EXPORTABLE_NODE__NODE_PARAMETER:
				return basicSetNodeParameter(null, msgs);
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
			case Sml2Package.EXPORTABLE_NODE__NODE_PARAMETER:
				if (resolve) return getNodeParameter();
				return basicGetNodeParameter();
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
			case Sml2Package.EXPORTABLE_NODE__NODE_PARAMETER:
				setNodeParameter((SituationTypeParameter)newValue);
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
			case Sml2Package.EXPORTABLE_NODE__NODE_PARAMETER:
				setNodeParameter((SituationTypeParameter)null);
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
			case Sml2Package.EXPORTABLE_NODE__NODE_PARAMETER:
				return nodeParameter != null;
		}
		return super.eIsSet(featureID);
	}

} //ExportableNodeImpl
