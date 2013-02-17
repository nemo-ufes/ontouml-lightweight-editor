/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.Block;
import br.ufes.inf.nemo.alloy.Declaration;
import br.ufes.inf.nemo.alloy.Expression;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.alloy.Quantificator;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quantification Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.QuantificationExpressionImpl#getQuantificator <em>Quantificator</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.QuantificationExpressionImpl#getDeclaration <em>Declaration</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.QuantificationExpressionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.QuantificationExpressionImpl#getBlock <em>Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QuantificationExpressionImpl extends ExpressionImpl implements QuantificationExpression {
	/**
	 * The default value of the '{@link #getQuantificator() <em>Quantificator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantificator()
	 * @generated
	 * @ordered
	 */
	protected static final Quantificator QUANTIFICATOR_EDEFAULT = Quantificator.ALL;

	/**
	 * The cached value of the '{@link #getQuantificator() <em>Quantificator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantificator()
	 * @generated
	 * @ordered
	 */
	protected Quantificator quantificator = QUANTIFICATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDeclaration() <em>Declaration</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaration()
	 * @generated
	 * @ordered
	 */
	protected EList<Declaration> declaration;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

	/**
	 * The cached value of the '{@link #getBlock() <em>Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlock()
	 * @generated
	 * @ordered
	 */
	protected Block block;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QuantificationExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.QUANTIFICATION_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Quantificator getQuantificator() {
		return quantificator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuantificator(Quantificator newQuantificator) {
		Quantificator oldQuantificator = quantificator;
		quantificator = newQuantificator == null ? QUANTIFICATOR_EDEFAULT : newQuantificator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.QUANTIFICATION_EXPRESSION__QUANTIFICATOR, oldQuantificator, quantificator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings({ })
	public EList<Declaration> getDeclaration() {
		if (declaration == null) {
			declaration = new EObjectContainmentEList<Declaration>(Declaration.class, this, AlloyPackage.QUANTIFICATION_EXPRESSION__DECLARATION);
		}
		return declaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
		Expression oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.QUANTIFICATION_EXPRESSION__EXPRESSION, oldExpression, newExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		if (newExpression != expression) {
			NotificationChain msgs = null;
			if (expression != null)
				msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.QUANTIFICATION_EXPRESSION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.QUANTIFICATION_EXPRESSION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.QUANTIFICATION_EXPRESSION__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getBlock() {
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBlock(Block newBlock, NotificationChain msgs) {
		Block oldBlock = block;
		block = newBlock;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.QUANTIFICATION_EXPRESSION__BLOCK, oldBlock, newBlock);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlock(Block newBlock) {
		if (newBlock != block) {
			NotificationChain msgs = null;
			if (block != null)
				msgs = ((InternalEObject)block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.QUANTIFICATION_EXPRESSION__BLOCK, null, msgs);
			if (newBlock != null)
				msgs = ((InternalEObject)newBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.QUANTIFICATION_EXPRESSION__BLOCK, null, msgs);
			msgs = basicSetBlock(newBlock, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.QUANTIFICATION_EXPRESSION__BLOCK, newBlock, newBlock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlloyPackage.QUANTIFICATION_EXPRESSION__DECLARATION:
				return ((InternalEList<?>)getDeclaration()).basicRemove(otherEnd, msgs);
			case AlloyPackage.QUANTIFICATION_EXPRESSION__EXPRESSION:
				return basicSetExpression(null, msgs);
			case AlloyPackage.QUANTIFICATION_EXPRESSION__BLOCK:
				return basicSetBlock(null, msgs);
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
			case AlloyPackage.QUANTIFICATION_EXPRESSION__QUANTIFICATOR:
				return getQuantificator();
			case AlloyPackage.QUANTIFICATION_EXPRESSION__DECLARATION:
				return getDeclaration();
			case AlloyPackage.QUANTIFICATION_EXPRESSION__EXPRESSION:
				return getExpression();
			case AlloyPackage.QUANTIFICATION_EXPRESSION__BLOCK:
				return getBlock();
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
			case AlloyPackage.QUANTIFICATION_EXPRESSION__QUANTIFICATOR:
				setQuantificator((Quantificator)newValue);
				return;
			case AlloyPackage.QUANTIFICATION_EXPRESSION__DECLARATION:
				getDeclaration().clear();
				getDeclaration().addAll((Collection<? extends Declaration>)newValue);
				return;
			case AlloyPackage.QUANTIFICATION_EXPRESSION__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case AlloyPackage.QUANTIFICATION_EXPRESSION__BLOCK:
				setBlock((Block)newValue);
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
			case AlloyPackage.QUANTIFICATION_EXPRESSION__QUANTIFICATOR:
				setQuantificator(QUANTIFICATOR_EDEFAULT);
				return;
			case AlloyPackage.QUANTIFICATION_EXPRESSION__DECLARATION:
				getDeclaration().clear();
				return;
			case AlloyPackage.QUANTIFICATION_EXPRESSION__EXPRESSION:
				setExpression((Expression)null);
				return;
			case AlloyPackage.QUANTIFICATION_EXPRESSION__BLOCK:
				setBlock((Block)null);
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
			case AlloyPackage.QUANTIFICATION_EXPRESSION__QUANTIFICATOR:
				return quantificator != QUANTIFICATOR_EDEFAULT;
			case AlloyPackage.QUANTIFICATION_EXPRESSION__DECLARATION:
				return declaration != null && !declaration.isEmpty();
			case AlloyPackage.QUANTIFICATION_EXPRESSION__EXPRESSION:
				return expression != null;
			case AlloyPackage.QUANTIFICATION_EXPRESSION__BLOCK:
				return block != null;
		}
		return super.eIsSet(featureID);
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		
		result.append(getQuantificator()+" ");
		for(Declaration d : getDeclaration()) {
			if(d.equals(getDeclaration().get(getDeclaration().size()-1)))
				result.append(d+" ");
			else
				result.append(d+", ");
		}
		result.append("| "+getExpression());
		return result.toString();
	}

} //QuantificationExpressionImpl
