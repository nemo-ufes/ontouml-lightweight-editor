/**
 */
package stories.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import stories.AllDifferent;
import stories.Individual;
import stories.StoriesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>All Different</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link stories.impl.AllDifferentImpl#getDistinct_members <em>Distinct members</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AllDifferentImpl extends Story_elementImpl implements AllDifferent {
	/**
	 * The cached value of the '{@link #getDistinct_members() <em>Distinct members</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDistinct_members()
	 * @generated
	 * @ordered
	 */
	protected EList<Individual> distinct_members;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AllDifferentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StoriesPackage.Literals.ALL_DIFFERENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Individual> getDistinct_members() {
		if (distinct_members == null) {
			distinct_members = new EObjectResolvingEList<Individual>(Individual.class, this, StoriesPackage.ALL_DIFFERENT__DISTINCT_MEMBERS);
		}
		return distinct_members;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StoriesPackage.ALL_DIFFERENT__DISTINCT_MEMBERS:
				return getDistinct_members();
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
			case StoriesPackage.ALL_DIFFERENT__DISTINCT_MEMBERS:
				getDistinct_members().clear();
				getDistinct_members().addAll((Collection<? extends Individual>)newValue);
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
			case StoriesPackage.ALL_DIFFERENT__DISTINCT_MEMBERS:
				getDistinct_members().clear();
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
			case StoriesPackage.ALL_DIFFERENT__DISTINCT_MEMBERS:
				return distinct_members != null && !distinct_members.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AllDifferentImpl
