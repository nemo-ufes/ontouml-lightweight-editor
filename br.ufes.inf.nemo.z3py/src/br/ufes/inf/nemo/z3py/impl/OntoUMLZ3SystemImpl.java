/**
 */
package br.ufes.inf.nemo.z3py.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import br.ufes.inf.nemo.z3py.BooleanFunctionDefinition;
import br.ufes.inf.nemo.z3py.IntConstant;
import br.ufes.inf.nemo.z3py.OntoUMLZ3System;
import br.ufes.inf.nemo.z3py.Quantification;
import br.ufes.inf.nemo.z3py.Z3pyPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Onto UMLZ3 System</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.OntoUMLZ3SystemImpl#getFunctions <em>Functions</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.OntoUMLZ3SystemImpl#getConstants <em>Constants</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.OntoUMLZ3SystemImpl#getFileName <em>File Name</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.OntoUMLZ3SystemImpl#getFormulas <em>Formulas</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OntoUMLZ3SystemImpl extends EObjectImpl implements OntoUMLZ3System {
	/**
	 * The cached value of the '{@link #getFunctions() <em>Functions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctions()
	 * @generated
	 * @ordered
	 */
	protected EList<BooleanFunctionDefinition> functions;

	/**
	 * The cached value of the '{@link #getConstants() <em>Constants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstants()
	 * @generated
	 * @ordered
	 */
	protected EList<IntConstant> constants;

	/**
	 * The default value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
	protected String fileName = FILE_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFormulas() <em>Formulas</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormulas()
	 * @generated
	 * @ordered
	 */
	protected EList<Quantification> formulas;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OntoUMLZ3SystemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Z3pyPackage.Literals.ONTO_UMLZ3_SYSTEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BooleanFunctionDefinition> getFunctions() {
		if (functions == null) {
			functions = new EObjectContainmentEList<BooleanFunctionDefinition>(BooleanFunctionDefinition.class, this, Z3pyPackage.ONTO_UMLZ3_SYSTEM__FUNCTIONS);
		}
		return functions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IntConstant> getConstants() {
		if (constants == null) {
			constants = new EObjectContainmentEList<IntConstant>(IntConstant.class, this, Z3pyPackage.ONTO_UMLZ3_SYSTEM__CONSTANTS);
		}
		return constants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileName(String newFileName) {
		String oldFileName = fileName;
		fileName = newFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Z3pyPackage.ONTO_UMLZ3_SYSTEM__FILE_NAME, oldFileName, fileName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Quantification> getFormulas() {
		if (formulas == null) {
			formulas = new EObjectContainmentEList<Quantification>(Quantification.class, this, Z3pyPackage.ONTO_UMLZ3_SYSTEM__FORMULAS);
		}
		return formulas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FUNCTIONS:
				return ((InternalEList<?>)getFunctions()).basicRemove(otherEnd, msgs);
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__CONSTANTS:
				return ((InternalEList<?>)getConstants()).basicRemove(otherEnd, msgs);
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FORMULAS:
				return ((InternalEList<?>)getFormulas()).basicRemove(otherEnd, msgs);
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
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FUNCTIONS:
				return getFunctions();
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__CONSTANTS:
				return getConstants();
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FILE_NAME:
				return getFileName();
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FORMULAS:
				return getFormulas();
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
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FUNCTIONS:
				getFunctions().clear();
				getFunctions().addAll((Collection<? extends BooleanFunctionDefinition>)newValue);
				return;
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__CONSTANTS:
				getConstants().clear();
				getConstants().addAll((Collection<? extends IntConstant>)newValue);
				return;
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FILE_NAME:
				setFileName((String)newValue);
				return;
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FORMULAS:
				getFormulas().clear();
				getFormulas().addAll((Collection<? extends Quantification>)newValue);
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
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FUNCTIONS:
				getFunctions().clear();
				return;
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__CONSTANTS:
				getConstants().clear();
				return;
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FILE_NAME:
				setFileName(FILE_NAME_EDEFAULT);
				return;
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FORMULAS:
				getFormulas().clear();
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
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FUNCTIONS:
				return functions != null && !functions.isEmpty();
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__CONSTANTS:
				return constants != null && !constants.isEmpty();
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FILE_NAME:
				return FILE_NAME_EDEFAULT == null ? fileName != null : !FILE_NAME_EDEFAULT.equals(fileName);
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM__FORMULAS:
				return formulas != null && !formulas.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	*/
	@Override
	public String toString() {
		/*if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (fileName: ");
		result.append(fileName);
		result.append(')');
		return result.toString();*/
		
		String result = "from z3 import * \n";
		result = result.concat("\n''' ----- Constants definitions -----''' \n");
		for(IntConstant c: this.constants){
			result = result.concat(c.toString() + "\n");
		}
		
		result = result.concat("\n''' ----- Functions definitions -----''' \n");
		for(BooleanFunctionDefinition f: this.functions){
			result = result.concat(f.toString() + "\n");
		}
		
		int i = 1;
		result = result.concat("\n''' ----- Formulas definitions -----''' \n");
		
		for(Quantification e: this.formulas){
			result = result.concat("\n'''" + e.getComments() +"''' \n");
			result = result.concat("F" + i + " = "+ e.toString() + "\n");
			i++;
		}
		
		
	
		result = result.concat("\n''' ----- Solver Configuration -----''' \n\n");
		int j;
		for (j = 1; j<i-1; j++){
			result = result.concat("a" + j+", ");
		}
		result = result.concat("a" + j+" = Bools('");
		for (j = 1; j<i-1; j++){
			result = result.concat("a" + j+" ");
		}
		result = result.concat("a" + j+"')\n");
		
		result = result.concat("\ns=Solver()\n");
		//result = result.concat("\n''' ----- Constants Definition to allow unsat_core verification-----''' \n");
		for (j = 1; j<i; j++){
			result = result.concat("s.add(Implies(a" +j + ", F"+j+"))\n");
		}
		result = result.concat("resp = s.check(");
		for (j = 1; j<i-1; j++){
			result = result.concat("a" + j+", ");
		}
		result = result.concat("a" + j+")\nif resp == sat:\n\tprint 'Modelo satisfativel'\nelse:\n\tprint 'Modelo insatisfativel'\n\tprint s.unsat_core()");

		return result;
		
	}
	
	

} //OntoUMLZ3SystemImpl
