/**
 */
package stories.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import stories.Action;
import stories.Node;
import stories.StoriesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link stories.impl.NodeImpl#getPerformed <em>Performed</em>}</li>
 *   <li>{@link stories.impl.NodeImpl#getInstance_of <em>Instance of</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeImpl extends IndividualImpl implements Node {
	/**
	 * The cached value of the '{@link #getPerformed() <em>Performed</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformed()
	 * @generated
	 * @ordered
	 */
	protected EList<Action> performed;

	/**
	 * The cached value of the '{@link #getInstance_of() <em>Instance of</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstance_of()
	 * @generated
	 * @ordered
	 */
	protected EList<RefOntoUML.Class> instance_of;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StoriesPackage.Literals.NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Action> getPerformed() {
		if (performed == null) {
			performed = new EObjectContainmentWithInverseEList<Action>(Action.class, this, StoriesPackage.NODE__PERFORMED, StoriesPackage.ACTION__PERFORMED_BY);
		}
		return performed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RefOntoUML.Class> getInstance_of() {
		if (instance_of == null) {
			instance_of = new EObjectResolvingEList<RefOntoUML.Class>(RefOntoUML.Class.class, this, StoriesPackage.NODE__INSTANCE_OF);
		}
		return instance_of;
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
			case StoriesPackage.NODE__PERFORMED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformed()).basicAdd(otherEnd, msgs);
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
			case StoriesPackage.NODE__PERFORMED:
				return ((InternalEList<?>)getPerformed()).basicRemove(otherEnd, msgs);
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
			case StoriesPackage.NODE__PERFORMED:
				return getPerformed();
			case StoriesPackage.NODE__INSTANCE_OF:
				return getInstance_of();
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
			case StoriesPackage.NODE__PERFORMED:
				getPerformed().clear();
				getPerformed().addAll((Collection<? extends Action>)newValue);
				return;
			case StoriesPackage.NODE__INSTANCE_OF:
				getInstance_of().clear();
				getInstance_of().addAll((Collection<? extends RefOntoUML.Class>)newValue);
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
			case StoriesPackage.NODE__PERFORMED:
				getPerformed().clear();
				return;
			case StoriesPackage.NODE__INSTANCE_OF:
				getInstance_of().clear();
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
			case StoriesPackage.NODE__PERFORMED:
				return performed != null && !performed.isEmpty();
			case StoriesPackage.NODE__INSTANCE_OF:
				return instance_of != null && !instance_of.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //NodeImpl
