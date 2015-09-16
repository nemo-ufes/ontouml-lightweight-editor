/**
 */
package sml2.impl;

import RefOntoUML.Model;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import sml2.SMLModel;
import sml2.SituationType;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SML Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sml2.impl.SMLModelImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link sml2.impl.SMLModelImpl#getContextModel <em>Context Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SMLModelImpl extends MinimalEObjectImpl.Container implements SMLModel {
	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<SituationType> elements;

	/**
	 * The cached value of the '{@link #getContextModel() <em>Context Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextModel()
	 * @generated
	 * @ordered
	 */
	protected Model contextModel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SMLModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.SML_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SituationType> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentEList<SituationType>(SituationType.class, this, Sml2Package.SML_MODEL__ELEMENTS);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getContextModel() {
		if (contextModel != null && contextModel.eIsProxy()) {
			InternalEObject oldContextModel = (InternalEObject)contextModel;
			contextModel = (Model)eResolveProxy(oldContextModel);
			if (contextModel != oldContextModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sml2Package.SML_MODEL__CONTEXT_MODEL, oldContextModel, contextModel));
			}
		}
		return contextModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model basicGetContextModel() {
		return contextModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContextModel(Model newContextModel) {
		Model oldContextModel = contextModel;
		contextModel = newContextModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sml2Package.SML_MODEL__CONTEXT_MODEL, oldContextModel, contextModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Sml2Package.SML_MODEL__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
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
			case Sml2Package.SML_MODEL__ELEMENTS:
				return getElements();
			case Sml2Package.SML_MODEL__CONTEXT_MODEL:
				if (resolve) return getContextModel();
				return basicGetContextModel();
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
			case Sml2Package.SML_MODEL__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends SituationType>)newValue);
				return;
			case Sml2Package.SML_MODEL__CONTEXT_MODEL:
				setContextModel((Model)newValue);
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
			case Sml2Package.SML_MODEL__ELEMENTS:
				getElements().clear();
				return;
			case Sml2Package.SML_MODEL__CONTEXT_MODEL:
				setContextModel((Model)null);
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
			case Sml2Package.SML_MODEL__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case Sml2Package.SML_MODEL__CONTEXT_MODEL:
				return contextModel != null;
		}
		return super.eIsSet(featureID);
	}

} //SMLModelImpl
