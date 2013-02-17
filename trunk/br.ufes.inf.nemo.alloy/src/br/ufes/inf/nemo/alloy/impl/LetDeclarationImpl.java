/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.Block;
import br.ufes.inf.nemo.alloy.Expression;
import br.ufes.inf.nemo.alloy.LetDeclaration;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Let Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.LetDeclarationImpl#getEqualExpression <em>Equal Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.LetDeclarationImpl#getBlock <em>Block</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.LetDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.LetDeclarationImpl#getBarExpression <em>Bar Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.LetDeclarationImpl#isHasBlock <em>Has Block</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.LetDeclarationImpl#isHasExpression <em>Has Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LetDeclarationImpl extends ExpressionImpl implements LetDeclaration {
	/**
	 * The cached value of the '{@link #getEqualExpression() <em>Equal Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEqualExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression equalExpression;

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
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected EList<String> name;

	/**
	 * The cached value of the '{@link #getBarExpression() <em>Bar Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBarExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression barExpression;

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
	protected LetDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.LET_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getEqualExpression() {
		return equalExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEqualExpression(Expression newEqualExpression, NotificationChain msgs) {
		Expression oldEqualExpression = equalExpression;
		equalExpression = newEqualExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.LET_DECLARATION__EQUAL_EXPRESSION, oldEqualExpression, newEqualExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEqualExpression(Expression newEqualExpression) {
		if (newEqualExpression != equalExpression) {
			NotificationChain msgs = null;
			if (equalExpression != null)
				msgs = ((InternalEObject)equalExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.LET_DECLARATION__EQUAL_EXPRESSION, null, msgs);
			if (newEqualExpression != null)
				msgs = ((InternalEObject)newEqualExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.LET_DECLARATION__EQUAL_EXPRESSION, null, msgs);
			msgs = basicSetEqualExpression(newEqualExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.LET_DECLARATION__EQUAL_EXPRESSION, newEqualExpression, newEqualExpression));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.LET_DECLARATION__BLOCK, oldBlock, newBlock);
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
				msgs = ((InternalEObject)block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.LET_DECLARATION__BLOCK, null, msgs);
			if (newBlock != null)
				msgs = ((InternalEObject)newBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.LET_DECLARATION__BLOCK, null, msgs);
			msgs = basicSetBlock(newBlock, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.LET_DECLARATION__BLOCK, newBlock, newBlock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings({ })
	public EList<String> getName() {
		if (name == null) {
			name = new EDataTypeUniqueEList<String>(String.class, this, AlloyPackage.LET_DECLARATION__NAME);
		}
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getBarExpression() {
		if (barExpression != null && barExpression.eIsProxy()) {
			InternalEObject oldBarExpression = (InternalEObject)barExpression;
			barExpression = (Expression)eResolveProxy(oldBarExpression);
			if (barExpression != oldBarExpression) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AlloyPackage.LET_DECLARATION__BAR_EXPRESSION, oldBarExpression, barExpression));
			}
		}
		return barExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetBarExpression() {
		return barExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBarExpression(Expression newBarExpression) {
		Expression oldBarExpression = barExpression;
		barExpression = newBarExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.LET_DECLARATION__BAR_EXPRESSION, oldBarExpression, barExpression));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.LET_DECLARATION__HAS_BLOCK, oldHasBlock, hasBlock));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.LET_DECLARATION__HAS_EXPRESSION, oldHasExpression, hasExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlloyPackage.LET_DECLARATION__EQUAL_EXPRESSION:
				return basicSetEqualExpression(null, msgs);
			case AlloyPackage.LET_DECLARATION__BLOCK:
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
			case AlloyPackage.LET_DECLARATION__EQUAL_EXPRESSION:
				return getEqualExpression();
			case AlloyPackage.LET_DECLARATION__BLOCK:
				return getBlock();
			case AlloyPackage.LET_DECLARATION__NAME:
				return getName();
			case AlloyPackage.LET_DECLARATION__BAR_EXPRESSION:
				if (resolve) return getBarExpression();
				return basicGetBarExpression();
			case AlloyPackage.LET_DECLARATION__HAS_BLOCK:
				return isHasBlock();
			case AlloyPackage.LET_DECLARATION__HAS_EXPRESSION:
				return isHasExpression();
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
			case AlloyPackage.LET_DECLARATION__EQUAL_EXPRESSION:
				setEqualExpression((Expression)newValue);
				return;
			case AlloyPackage.LET_DECLARATION__BLOCK:
				setBlock((Block)newValue);
				return;
			case AlloyPackage.LET_DECLARATION__NAME:
				getName().clear();
				getName().addAll((Collection<? extends String>)newValue);
				return;
			case AlloyPackage.LET_DECLARATION__BAR_EXPRESSION:
				setBarExpression((Expression)newValue);
				return;
			case AlloyPackage.LET_DECLARATION__HAS_BLOCK:
				setHasBlock((Boolean)newValue);
				return;
			case AlloyPackage.LET_DECLARATION__HAS_EXPRESSION:
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
			case AlloyPackage.LET_DECLARATION__EQUAL_EXPRESSION:
				setEqualExpression((Expression)null);
				return;
			case AlloyPackage.LET_DECLARATION__BLOCK:
				setBlock((Block)null);
				return;
			case AlloyPackage.LET_DECLARATION__NAME:
				getName().clear();
				return;
			case AlloyPackage.LET_DECLARATION__BAR_EXPRESSION:
				setBarExpression((Expression)null);
				return;
			case AlloyPackage.LET_DECLARATION__HAS_BLOCK:
				setHasBlock(HAS_BLOCK_EDEFAULT);
				return;
			case AlloyPackage.LET_DECLARATION__HAS_EXPRESSION:
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
			case AlloyPackage.LET_DECLARATION__EQUAL_EXPRESSION:
				return equalExpression != null;
			case AlloyPackage.LET_DECLARATION__BLOCK:
				return block != null;
			case AlloyPackage.LET_DECLARATION__NAME:
				return name != null && !name.isEmpty();
			case AlloyPackage.LET_DECLARATION__BAR_EXPRESSION:
				return barExpression != null;
			case AlloyPackage.LET_DECLARATION__HAS_BLOCK:
				return hasBlock != HAS_BLOCK_EDEFAULT;
			case AlloyPackage.LET_DECLARATION__HAS_EXPRESSION:
				return hasExpression != HAS_EXPRESSION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", hasBlock: ");
		result.append(hasBlock);
		result.append(", hasExpression: ");
		result.append(hasExpression);
		result.append(')');
		return result.toString();
	}

} //LetDeclarationImpl
