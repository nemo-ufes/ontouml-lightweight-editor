/**
 */
package stories.impl;

import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import stories.Individual;
import stories.StoriesPackage;
import stories.World;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Individual</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link stories.impl.IndividualImpl#getDifferent_from <em>Different from</em>}</li>
 *   <li>{@link stories.impl.IndividualImpl#getSame_as <em>Same as</em>}</li>
 *   <li>{@link stories.impl.IndividualImpl#getPresent_in <em>Present in</em>}</li>
 *   <li>{@link stories.impl.IndividualImpl#getAbsent_from <em>Absent from</em>}</li>
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
	 * The cached value of the '{@link #getPresent_in() <em>Present in</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPresent_in()
	 * @generated
	 * @ordered
	 */
	protected EList<World> present_in;
	/**
	 * The cached value of the '{@link #getAbsent_from() <em>Absent from</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbsent_from()
	 * @generated
	 * @ordered
	 */
	protected EList<World> absent_from;

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
	public EList<World> getPresent_in() {
		if (present_in == null) {
			present_in = new EObjectResolvingEList<World>(World.class, this, StoriesPackage.INDIVIDUAL__PRESENT_IN);
		}
		return present_in;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<World> getAbsent_from() {
		if (absent_from == null) {
			absent_from = new EObjectResolvingEList<World>(World.class, this, StoriesPackage.INDIVIDUAL__ABSENT_FROM);
		}
		return absent_from;
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
			case StoriesPackage.INDIVIDUAL__PRESENT_IN:
				return getPresent_in();
			case StoriesPackage.INDIVIDUAL__ABSENT_FROM:
				return getAbsent_from();
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
			case StoriesPackage.INDIVIDUAL__PRESENT_IN:
				getPresent_in().clear();
				getPresent_in().addAll((Collection<? extends World>)newValue);
				return;
			case StoriesPackage.INDIVIDUAL__ABSENT_FROM:
				getAbsent_from().clear();
				getAbsent_from().addAll((Collection<? extends World>)newValue);
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
			case StoriesPackage.INDIVIDUAL__PRESENT_IN:
				getPresent_in().clear();
				return;
			case StoriesPackage.INDIVIDUAL__ABSENT_FROM:
				getAbsent_from().clear();
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
			case StoriesPackage.INDIVIDUAL__PRESENT_IN:
				return present_in != null && !present_in.isEmpty();
			case StoriesPackage.INDIVIDUAL__ABSENT_FROM:
				return absent_from != null && !absent_from.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * Inserts rules that constrain a node to be either different or the same as other nodes.
	 * <!-- end-user-doc -->
	 * 
	 */
	public String identity(){
		String diffs = "";
		for(Individual n : this.getDifferent_from()){
			diffs = diffs + '\t'+ this.getLabel() + " not = " + n.getLabel() +'\n';
		}
		for(Individual n : this.getSame_as()){
			diffs = diffs + '\t'+ this.getLabel() + " = " + n.getLabel() +'\n';
		}
		return diffs;
	}
	
	/*public void clear(){
		super.clear();
		this.getPresent_in().clear();
		this.getAbsent_from().clear();
		this.getDifferent_from().clear();
	}*/
} //IndividualImpl
