/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.Block;
import br.ufes.inf.nemo.alloy.ComprehensionExpression;
import br.ufes.inf.nemo.alloy.Declaration;
import br.ufes.inf.nemo.alloy.Expression;

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
 * An implementation of the model object '<em><b>Comprehension Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.ComprehensionExpressionImpl#getDeclaration <em>Declaration</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.ComprehensionExpressionImpl#getBlock <em>Block</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.ComprehensionExpressionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.ComprehensionExpressionImpl#isHasBlock <em>Has Block</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.ComprehensionExpressionImpl#isHasExpression <em>Has Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComprehensionExpressionImpl extends ExpressionImpl implements ComprehensionExpression {
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
	 * The cached value of the '{@link #getBlock() <em>Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlock()
	 * @generated
	 * @ordered
	 */
	protected Block block;

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
	 * The default value of the '{@link #isHasBlock() <em>Has Block</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasBlock()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_BLOCK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasBlock() <em>Has Block</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasBlock()
	 * @generated
	 * @ordered
	 */
	protected boolean hasBlock = HAS_BLOCK_EDEFAULT;

	/**
	 * The default value of the '{@link #isHasExpression() <em>Has Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasExpression()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_EXPRESSION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasExpression() <em>Has Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasExpression()
	 * @generated
	 * @ordered
	 */
	protected boolean hasExpression = HAS_EXPRESSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComprehensionExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.COMPREHENSION_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Declaration> getDeclaration() {
		if (declaration == null) {
			declaration = new EObjectContainmentEList<Declaration>(Declaration.class, this, AlloyPackage.COMPREHENSION_EXPRESSION__DECLARATION);
		}
		return declaration;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.COMPREHENSION_EXPRESSION__BLOCK, oldBlock, newBlock);
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
				msgs = ((InternalEObject)block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.COMPREHENSION_EXPRESSION__BLOCK, null, msgs);
			if (newBlock != null)
				msgs = ((InternalEObject)newBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.COMPREHENSION_EXPRESSION__BLOCK, null, msgs);
			msgs = basicSetBlock(newBlock, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMPREHENSION_EXPRESSION__BLOCK, newBlock, newBlock));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.COMPREHENSION_EXPRESSION__EXPRESSION, oldExpression, newExpression);
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
				msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.COMPREHENSION_EXPRESSION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.COMPREHENSION_EXPRESSION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMPREHENSION_EXPRESSION__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasBlock() {
		return hasBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasBlock(boolean newHasBlock) {
		boolean oldHasBlock = hasBlock;
		hasBlock = newHasBlock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMPREHENSION_EXPRESSION__HAS_BLOCK, oldHasBlock, hasBlock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasExpression() {
		return hasExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasExpression(boolean newHasExpression) {
		boolean oldHasExpression = hasExpression;
		hasExpression = newHasExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMPREHENSION_EXPRESSION__HAS_EXPRESSION, oldHasExpression, hasExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlloyPackage.COMPREHENSION_EXPRESSION__DECLARATION:
				return ((InternalEList<?>)getDeclaration()).basicRemove(otherEnd, msgs);
			case AlloyPackage.COMPREHENSION_EXPRESSION__BLOCK:
				return basicSetBlock(null, msgs);
			case AlloyPackage.COMPREHENSION_EXPRESSION__EXPRESSION:
				return basicSetExpression(null, msgs);
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
			case AlloyPackage.COMPREHENSION_EXPRESSION__DECLARATION:
				return getDeclaration();
			case AlloyPackage.COMPREHENSION_EXPRESSION__BLOCK:
				return getBlock();
			case AlloyPackage.COMPREHENSION_EXPRESSION__EXPRESSION:
				return getExpression();
			case AlloyPackage.COMPREHENSION_EXPRESSION__HAS_BLOCK:
				return isHasBlock();
			case AlloyPackage.COMPREHENSION_EXPRESSION__HAS_EXPRESSION:
				return isHasExpression();
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
			case AlloyPackage.COMPREHENSION_EXPRESSION__DECLARATION:
				getDeclaration().clear();
				getDeclaration().addAll((Collection<? extends Declaration>)newValue);
				return;
			case AlloyPackage.COMPREHENSION_EXPRESSION__BLOCK:
				setBlock((Block)newValue);
				return;
			case AlloyPackage.COMPREHENSION_EXPRESSION__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case AlloyPackage.COMPREHENSION_EXPRESSION__HAS_BLOCK:
				setHasBlock((Boolean)newValue);
				return;
			case AlloyPackage.COMPREHENSION_EXPRESSION__HAS_EXPRESSION:
				setHasExpression((Boolean)newValue);
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
			case AlloyPackage.COMPREHENSION_EXPRESSION__DECLARATION:
				getDeclaration().clear();
				return;
			case AlloyPackage.COMPREHENSION_EXPRESSION__BLOCK:
				setBlock((Block)null);
				return;
			case AlloyPackage.COMPREHENSION_EXPRESSION__EXPRESSION:
				setExpression((Expression)null);
				return;
			case AlloyPackage.COMPREHENSION_EXPRESSION__HAS_BLOCK:
				setHasBlock(HAS_BLOCK_EDEFAULT);
				return;
			case AlloyPackage.COMPREHENSION_EXPRESSION__HAS_EXPRESSION:
				setHasExpression(HAS_EXPRESSION_EDEFAULT);
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
			case AlloyPackage.COMPREHENSION_EXPRESSION__DECLARATION:
				return declaration != null && !declaration.isEmpty();
			case AlloyPackage.COMPREHENSION_EXPRESSION__BLOCK:
				return block != null;
			case AlloyPackage.COMPREHENSION_EXPRESSION__EXPRESSION:
				return expression != null;
			case AlloyPackage.COMPREHENSION_EXPRESSION__HAS_BLOCK:
				return hasBlock != HAS_BLOCK_EDEFAULT;
			case AlloyPackage.COMPREHENSION_EXPRESSION__HAS_EXPRESSION:
				return hasExpression != HAS_EXPRESSION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}


	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (hasBlock: ");
		result.append(hasBlock);
		result.append(", hasExpression: ");
		result.append(hasExpression);
		result.append(')');
		return result.toString();
	}

} //ComprehensionExpressionImpl
