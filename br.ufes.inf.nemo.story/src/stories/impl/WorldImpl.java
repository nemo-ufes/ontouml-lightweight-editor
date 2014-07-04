/**
 */
package stories.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import stories.Event;
import stories.Individual;
import stories.StoriesPackage;
import stories.World;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>World</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link stories.impl.WorldImpl#getPresent <em>Present</em>}</li>
 *   <li>{@link stories.impl.WorldImpl#getEnabled <em>Enabled</em>}</li>
 *   <li>{@link stories.impl.WorldImpl#getBrought_about_by <em>Brought about by</em>}</li>
 *   <li>{@link stories.impl.WorldImpl#getAbsent <em>Absent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorldImpl extends Story_elementImpl implements World {
	/**
	 * The cached value of the '{@link #getPresent() <em>Present</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPresent()
	 * @generated
	 * @ordered
	 */
	protected EList<Individual> present;

	/**
	 * The cached value of the '{@link #getEnabled() <em>Enabled</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnabled()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> enabled;

	/**
	 * The cached value of the '{@link #getBrought_about_by() <em>Brought about by</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBrought_about_by()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> brought_about_by;

	/**
	 * The cached value of the '{@link #getAbsent() <em>Absent</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbsent()
	 * @generated
	 * @ordered
	 */
	protected EList<Individual> absent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StoriesPackage.Literals.WORLD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Individual> getPresent() {
		if (present == null) {
			present = new EObjectResolvingEList<Individual>(Individual.class, this, StoriesPackage.WORLD__PRESENT);
		}
		return present;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getEnabled() {
		if (enabled == null) {
			enabled = new EObjectWithInverseResolvingEList<Event>(Event.class, this, StoriesPackage.WORLD__ENABLED, StoriesPackage.EVENT__HAPPENED_IN);
		}
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getBrought_about_by() {
		if (brought_about_by == null) {
			brought_about_by = new EObjectWithInverseResolvingEList.ManyInverse<Event>(Event.class, this, StoriesPackage.WORLD__BROUGHT_ABOUT_BY, StoriesPackage.EVENT__BRINGS_ABOUT);
		}
		return brought_about_by;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Individual> getAbsent() {
		if (absent == null) {
			absent = new EObjectResolvingEList<Individual>(Individual.class, this, StoriesPackage.WORLD__ABSENT);
		}
		return absent;
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
			case StoriesPackage.WORLD__ENABLED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEnabled()).basicAdd(otherEnd, msgs);
			case StoriesPackage.WORLD__BROUGHT_ABOUT_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBrought_about_by()).basicAdd(otherEnd, msgs);
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
			case StoriesPackage.WORLD__ENABLED:
				return ((InternalEList<?>)getEnabled()).basicRemove(otherEnd, msgs);
			case StoriesPackage.WORLD__BROUGHT_ABOUT_BY:
				return ((InternalEList<?>)getBrought_about_by()).basicRemove(otherEnd, msgs);
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
			case StoriesPackage.WORLD__PRESENT:
				return getPresent();
			case StoriesPackage.WORLD__ENABLED:
				return getEnabled();
			case StoriesPackage.WORLD__BROUGHT_ABOUT_BY:
				return getBrought_about_by();
			case StoriesPackage.WORLD__ABSENT:
				return getAbsent();
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
			case StoriesPackage.WORLD__PRESENT:
				getPresent().clear();
				getPresent().addAll((Collection<? extends Individual>)newValue);
				return;
			case StoriesPackage.WORLD__ENABLED:
				getEnabled().clear();
				getEnabled().addAll((Collection<? extends Event>)newValue);
				return;
			case StoriesPackage.WORLD__BROUGHT_ABOUT_BY:
				getBrought_about_by().clear();
				getBrought_about_by().addAll((Collection<? extends Event>)newValue);
				return;
			case StoriesPackage.WORLD__ABSENT:
				getAbsent().clear();
				getAbsent().addAll((Collection<? extends Individual>)newValue);
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
			case StoriesPackage.WORLD__PRESENT:
				getPresent().clear();
				return;
			case StoriesPackage.WORLD__ENABLED:
				getEnabled().clear();
				return;
			case StoriesPackage.WORLD__BROUGHT_ABOUT_BY:
				getBrought_about_by().clear();
				return;
			case StoriesPackage.WORLD__ABSENT:
				getAbsent().clear();
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
			case StoriesPackage.WORLD__PRESENT:
				return present != null && !present.isEmpty();
			case StoriesPackage.WORLD__ENABLED:
				return enabled != null && !enabled.isEmpty();
			case StoriesPackage.WORLD__BROUGHT_ABOUT_BY:
				return brought_about_by != null && !brought_about_by.isEmpty();
			case StoriesPackage.WORLD__ABSENT:
				return absent != null && !absent.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //WorldImpl
