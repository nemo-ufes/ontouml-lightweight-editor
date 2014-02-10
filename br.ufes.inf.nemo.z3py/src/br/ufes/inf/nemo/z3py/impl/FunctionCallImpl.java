/**
 */
package br.ufes.inf.nemo.z3py.impl;

import br.ufes.inf.nemo.z3py.BooleanFunctionDefinition;
import br.ufes.inf.nemo.z3py.FunctionCall;
import br.ufes.inf.nemo.z3py.IntConstant;
import br.ufes.inf.nemo.z3py.Z3pyPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.FunctionCallImpl#getCalledFunction <em>Called Function</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.FunctionCallImpl#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionCallImpl extends ExpressionImpl implements FunctionCall {
	/**
	 * The cached value of the '{@link #getCalledFunction() <em>Called Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledFunction()
	 * @generated
	 * @ordered
	 */
	protected BooleanFunctionDefinition calledFunction;

	/**
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<IntConstant> arguments;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FunctionCallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Z3pyPackage.Literals.FUNCTION_CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanFunctionDefinition getCalledFunction() {
		if (calledFunction != null && calledFunction.eIsProxy()) {
			InternalEObject oldCalledFunction = (InternalEObject)calledFunction;
			calledFunction = (BooleanFunctionDefinition)eResolveProxy(oldCalledFunction);
			if (calledFunction != oldCalledFunction) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Z3pyPackage.FUNCTION_CALL__CALLED_FUNCTION, oldCalledFunction, calledFunction));
			}
		}
		return calledFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanFunctionDefinition basicGetCalledFunction() {
		return calledFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalledFunction(BooleanFunctionDefinition newCalledFunction) {
		BooleanFunctionDefinition oldCalledFunction = calledFunction;
		calledFunction = newCalledFunction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Z3pyPackage.FUNCTION_CALL__CALLED_FUNCTION, oldCalledFunction, calledFunction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<IntConstant> getArguments() {
		if (arguments == null) {
			arguments = new EObjectEList<IntConstant>(IntConstant.class, this, Z3pyPackage.FUNCTION_CALL__ARGUMENTS) {
//Tive de fazer isso pois, devido a um bug do eclipse o isUNique nao funciona. Esse workaround foi proposto no site de bugs do Eclipse:
//https://bugs.eclipse.org/bugs/show_bug.cgi?id=331209
//https://bugs.eclipse.org/bugs/show_bug.cgi?id=89325
				@Override
			      protected boolean isUnique() {
			        return false;
			      }
			    };
		}
		return arguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Z3pyPackage.FUNCTION_CALL__CALLED_FUNCTION:
				if (resolve) return getCalledFunction();
				return basicGetCalledFunction();
			case Z3pyPackage.FUNCTION_CALL__ARGUMENTS:
				return getArguments();
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
			case Z3pyPackage.FUNCTION_CALL__CALLED_FUNCTION:
				setCalledFunction((BooleanFunctionDefinition)newValue);
				return;
			case Z3pyPackage.FUNCTION_CALL__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends IntConstant>)newValue);
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
			case Z3pyPackage.FUNCTION_CALL__CALLED_FUNCTION:
				setCalledFunction((BooleanFunctionDefinition)null);
				return;
			case Z3pyPackage.FUNCTION_CALL__ARGUMENTS:
				getArguments().clear();
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
			case Z3pyPackage.FUNCTION_CALL__CALLED_FUNCTION:
				return calledFunction != null;
			case Z3pyPackage.FUNCTION_CALL__ARGUMENTS:
				return arguments != null && !arguments.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	@Override
	public String toString() {
		int i;
		String result = this.calledFunction.getName() + "(";
		for(i=0; i<this.arguments.size()-1;i++){
			result = result.concat(this.arguments.get(i).getName()+",");
		}
		result = result.concat(this.arguments.get(i).getName()+")");		
		return result;
	}

} //FunctionCallImpl
