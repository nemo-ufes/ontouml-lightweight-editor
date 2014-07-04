/**
 */
package stories.impl;

import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import stories.Individual;
import stories.StoriesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Individual</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link stories.impl.IndividualImpl#getDifferent_from <em>Different from</em>}</li>
 *   <li>{@link stories.impl.IndividualImpl#getSame_as <em>Same as</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class IndividualImpl extends Story_elementImpl implements Individual {
	/**
	 * The cached value of the '{@link #getDifferent_from() <em>Different from</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDifferent_from()
	 * @generated
	 * @ordered
	 */
	protected EList<Individual> different_from;
	/**
	 * The cached value of the '{@link #getSame_as() <em>Same as</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSame_as()
	 * @generated
	 * @ordered
	 */
	protected EList<Individual> same_as;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndividualImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StoriesPackage.Literals.INDIVIDUAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Individual> getDifferent_from() {
		if (different_from == null) {
			different_from = new EObjectResolvingEList<Individual>(Individual.class, this, StoriesPackage.INDIVIDUAL__DIFFERENT_FROM);
		}
		return different_from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Individual> getSame_as() {
		if (same_as == null) {
			same_as = new EObjectResolvingEList<Individual>(Individual.class, this, StoriesPackage.INDIVIDUAL__SAME_AS);
		}
		return same_as;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StoriesPackage.INDIVIDUAL__DIFFERENT_FROM:
				return getDifferent_from();
			case StoriesPackage.INDIVIDUAL__SAME_AS:
				return getSame_as();
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
			case StoriesPackage.INDIVIDUAL__DIFFERENT_FROM:
				getDifferent_from().clear();
				getDifferent_from().addAll((Collection<? extends Individual>)newValue);
				return;
			case StoriesPackage.INDIVIDUAL__SAME_AS:
				getSame_as().clear();
				getSame_as().addAll((Collection<? extends Individual>)newValue);
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
			case StoriesPackage.INDIVIDUAL__DIFFERENT_FROM:
				getDifferent_from().clear();
				return;
			case StoriesPackage.INDIVIDUAL__SAME_AS:
				getSame_as().clear();
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
			case StoriesPackage.INDIVIDUAL__DIFFERENT_FROM:
				return different_from != null && !different_from.isEmpty();
			case StoriesPackage.INDIVIDUAL__SAME_AS:
				return same_as != null && !same_as.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //IndividualImpl
