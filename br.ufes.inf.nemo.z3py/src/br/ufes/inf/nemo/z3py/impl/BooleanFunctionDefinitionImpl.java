/**
 */
package br.ufes.inf.nemo.z3py.impl;

import br.ufes.inf.nemo.z3py.BooleanFunctionDefinition;
import br.ufes.inf.nemo.z3py.Z3pyPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Function Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.BooleanFunctionDefinitionImpl#getName <em>Name</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.BooleanFunctionDefinitionImpl#getNumberOfArguments <em>Number Of Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BooleanFunctionDefinitionImpl extends EObjectImpl implements BooleanFunctionDefinition {
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
	 * The default value of the '{@link #getNumberOfArguments() <em>Number Of Arguments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfArguments()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_OF_ARGUMENTS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumberOfArguments() <em>Number Of Arguments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfArguments()
	 * @generated
	 * @ordered
	 */
	protected int numberOfArguments = NUMBER_OF_ARGUMENTS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanFunctionDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Z3pyPackage.Literals.BOOLEAN_FUNCTION_DEFINITION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumberOfArguments() {
		return numberOfArguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberOfArguments(int newNumberOfArguments) {
		int oldNumberOfArguments = numberOfArguments;
		numberOfArguments = newNumberOfArguments;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION__NUMBER_OF_ARGUMENTS, oldNumberOfArguments, numberOfArguments));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION__NAME:
				return getName();
			case Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION__NUMBER_OF_ARGUMENTS:
				return getNumberOfArguments();
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
			case Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION__NAME:
				setName((String)newValue);
				return;
			case Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION__NUMBER_OF_ARGUMENTS:
				setNumberOfArguments((Integer)newValue);
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
			case Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION__NUMBER_OF_ARGUMENTS:
				setNumberOfArguments(NUMBER_OF_ARGUMENTS_EDEFAULT);
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
			case Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION__NUMBER_OF_ARGUMENTS:
				return numberOfArguments != NUMBER_OF_ARGUMENTS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	@Override
	public String toString() {
		/*//CODIGO GERADO PELO EMF
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", numberOfArguments: ");
		result.append(numberOfArguments);
		result.append(')');
		return result.toString();
		*/
		
		String result = this.name + " = Function ('" + this.name + "', ";
		for(int i=1; i<=this.numberOfArguments;i++)
			result=result.concat("IntSort(), ");
		result=result.concat("BoolSort())");
		return result;
	}

} //BooleanFunctionDefinitionImpl
