/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.alloy.Expression;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Disjoint Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.DisjointExpressionImpl#getSet <em>Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DisjointExpressionImpl extends ExpressionImpl implements DisjointExpression {
	/**
	 * The cached value of the '{@link #getSet() <em>Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> set;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DisjointExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.DISJOINT_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings({ })
	public EList<Expression> getSet() {
		if (set == null) {
			set = new EObjectContainmentEList<Expression>(Expression.class, this, AlloyPackage.DISJOINT_EXPRESSION__SET);
		}
		return set;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlloyPackage.DISJOINT_EXPRESSION__SET:
				return ((InternalEList<?>)getSet()).basicRemove(otherEnd, msgs);
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
			case AlloyPackage.DISJOINT_EXPRESSION__SET:
				return getSet();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AlloyPackage.DISJOINT_EXPRESSION__SET:
				getSet().clear();
				getSet().addAll((Collection<? extends Expression>)newValue);
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
			case AlloyPackage.DISJOINT_EXPRESSION__SET:
				getSet().clear();
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
			case AlloyPackage.DISJOINT_EXPRESSION__SET:
				return set != null && !set.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("disj[");
		for(Expression exp : getSet())
		{
			if(exp.equals(getSet().get(getSet().size()-1)))
				result.append(exp);
			else
				result.append(exp+",");
		}
		result.append("]");
		return result.toString();
	}
} //DisjointExpressionImpl
