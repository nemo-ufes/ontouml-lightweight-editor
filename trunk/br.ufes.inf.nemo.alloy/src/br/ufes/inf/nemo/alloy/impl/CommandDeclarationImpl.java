/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.AssertionDeclaration;
import br.ufes.inf.nemo.alloy.Block;
import br.ufes.inf.nemo.alloy.CommandDeclaration;
import br.ufes.inf.nemo.alloy.GenericScope;
import br.ufes.inf.nemo.alloy.PredicateDeclaration;
import br.ufes.inf.nemo.alloy.ScopeSpecification;
import br.ufes.inf.nemo.alloy.Scopeable;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Command Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.CommandDeclarationImpl#getScope <em>Scope</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.CommandDeclarationImpl#getBlock <em>Block</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.CommandDeclarationImpl#getAssertion <em>Assertion</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.CommandDeclarationImpl#getPredicate <em>Predicate</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.CommandDeclarationImpl#isIsRun <em>Is Run</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.CommandDeclarationImpl#isIsCheck <em>Is Check</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CommandDeclarationImpl extends ParagraphImpl implements CommandDeclaration {
	/**
	 * The cached value of the '{@link #getScope() <em>Scope</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScope()
	 * @generated
	 * @ordered
	 */
	protected ScopeSpecification scope;

	/**
	 * The cached value of the '{@link #getBlock() <em>Block</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlock()
	 * @generated
	 * @ordered
	 */
	protected Block block;

	/**
	 * The cached value of the '{@link #getAssertion() <em>Assertion</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssertion()
	 * @generated
	 * @ordered
	 */
	protected AssertionDeclaration assertion;

	/**
	 * The cached value of the '{@link #getPredicate() <em>Predicate</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredicate()
	 * @generated
	 * @ordered
	 */
	protected PredicateDeclaration predicate;

	/**
	 * The default value of the '{@link #isIsRun() <em>Is Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRun()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_RUN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsRun() <em>Is Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRun()
	 * @generated
	 * @ordered
	 */
	protected boolean isRun = IS_RUN_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsCheck() <em>Is Check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCheck()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CHECK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsCheck() <em>Is Check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCheck()
	 * @generated
	 * @ordered
	 */
	protected boolean isCheck = IS_CHECK_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommandDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.COMMAND_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopeSpecification getScope() {
		return scope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScope(ScopeSpecification newScope, NotificationChain msgs) {
		ScopeSpecification oldScope = scope;
		scope = newScope;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.COMMAND_DECLARATION__SCOPE, oldScope, newScope);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScope(ScopeSpecification newScope) {
		if (newScope != scope) {
			NotificationChain msgs = null;
			if (scope != null)
				msgs = ((InternalEObject)scope).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.COMMAND_DECLARATION__SCOPE, null, msgs);
			if (newScope != null)
				msgs = ((InternalEObject)newScope).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.COMMAND_DECLARATION__SCOPE, null, msgs);
			msgs = basicSetScope(newScope, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMMAND_DECLARATION__SCOPE, newScope, newScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getBlock() {
		if (block != null && block.eIsProxy()) {
			InternalEObject oldBlock = (InternalEObject)block;
			block = (Block)eResolveProxy(oldBlock);
			if (block != oldBlock) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AlloyPackage.COMMAND_DECLARATION__BLOCK, oldBlock, block));
			}
		}
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block basicGetBlock() {
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlock(Block newBlock) {
		Block oldBlock = block;
		block = newBlock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMMAND_DECLARATION__BLOCK, oldBlock, block));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssertionDeclaration getAssertion() {
		if (assertion != null && assertion.eIsProxy()) {
			InternalEObject oldAssertion = (InternalEObject)assertion;
			assertion = (AssertionDeclaration)eResolveProxy(oldAssertion);
			if (assertion != oldAssertion) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AlloyPackage.COMMAND_DECLARATION__ASSERTION, oldAssertion, assertion));
			}
		}
		return assertion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssertionDeclaration basicGetAssertion() {
		return assertion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssertion(AssertionDeclaration newAssertion) {
		AssertionDeclaration oldAssertion = assertion;
		assertion = newAssertion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMMAND_DECLARATION__ASSERTION, oldAssertion, assertion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredicateDeclaration getPredicate() {
		if (predicate != null && predicate.eIsProxy()) {
			InternalEObject oldPredicate = (InternalEObject)predicate;
			predicate = (PredicateDeclaration)eResolveProxy(oldPredicate);
			if (predicate != oldPredicate) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AlloyPackage.COMMAND_DECLARATION__PREDICATE, oldPredicate, predicate));
			}
		}
		return predicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredicateDeclaration basicGetPredicate() {
		return predicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredicate(PredicateDeclaration newPredicate) {
		PredicateDeclaration oldPredicate = predicate;
		predicate = newPredicate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMMAND_DECLARATION__PREDICATE, oldPredicate, predicate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsRun() {
		return isRun;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsRun(boolean newIsRun) {
		boolean oldIsRun = isRun;
		isRun = newIsRun;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMMAND_DECLARATION__IS_RUN, oldIsRun, isRun));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsCheck() {
		return isCheck;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsCheck(boolean newIsCheck) {
		boolean oldIsCheck = isCheck;
		isCheck = newIsCheck;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.COMMAND_DECLARATION__IS_CHECK, oldIsCheck, isCheck));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlloyPackage.COMMAND_DECLARATION__SCOPE:
				return basicSetScope(null, msgs);
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
			case AlloyPackage.COMMAND_DECLARATION__SCOPE:
				return getScope();
			case AlloyPackage.COMMAND_DECLARATION__BLOCK:
				if (resolve) return getBlock();
				return basicGetBlock();
			case AlloyPackage.COMMAND_DECLARATION__ASSERTION:
				if (resolve) return getAssertion();
				return basicGetAssertion();
			case AlloyPackage.COMMAND_DECLARATION__PREDICATE:
				if (resolve) return getPredicate();
				return basicGetPredicate();
			case AlloyPackage.COMMAND_DECLARATION__IS_RUN:
				return isIsRun();
			case AlloyPackage.COMMAND_DECLARATION__IS_CHECK:
				return isIsCheck();
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
			case AlloyPackage.COMMAND_DECLARATION__SCOPE:
				setScope((ScopeSpecification)newValue);
				return;
			case AlloyPackage.COMMAND_DECLARATION__BLOCK:
				setBlock((Block)newValue);
				return;
			case AlloyPackage.COMMAND_DECLARATION__ASSERTION:
				setAssertion((AssertionDeclaration)newValue);
				return;
			case AlloyPackage.COMMAND_DECLARATION__PREDICATE:
				setPredicate((PredicateDeclaration)newValue);
				return;
			case AlloyPackage.COMMAND_DECLARATION__IS_RUN:
				setIsRun((Boolean)newValue);
				return;
			case AlloyPackage.COMMAND_DECLARATION__IS_CHECK:
				setIsCheck((Boolean)newValue);
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
			case AlloyPackage.COMMAND_DECLARATION__SCOPE:
				setScope((ScopeSpecification)null);
				return;
			case AlloyPackage.COMMAND_DECLARATION__BLOCK:
				setBlock((Block)null);
				return;
			case AlloyPackage.COMMAND_DECLARATION__ASSERTION:
				setAssertion((AssertionDeclaration)null);
				return;
			case AlloyPackage.COMMAND_DECLARATION__PREDICATE:
				setPredicate((PredicateDeclaration)null);
				return;
			case AlloyPackage.COMMAND_DECLARATION__IS_RUN:
				setIsRun(IS_RUN_EDEFAULT);
				return;
			case AlloyPackage.COMMAND_DECLARATION__IS_CHECK:
				setIsCheck(IS_CHECK_EDEFAULT);
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
			case AlloyPackage.COMMAND_DECLARATION__SCOPE:
				return scope != null;
			case AlloyPackage.COMMAND_DECLARATION__BLOCK:
				return block != null;
			case AlloyPackage.COMMAND_DECLARATION__ASSERTION:
				return assertion != null;
			case AlloyPackage.COMMAND_DECLARATION__PREDICATE:
				return predicate != null;
			case AlloyPackage.COMMAND_DECLARATION__IS_RUN:
				return isRun != IS_RUN_EDEFAULT;
			case AlloyPackage.COMMAND_DECLARATION__IS_CHECK:
				return isCheck != IS_CHECK_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}


	public String toString() {
		StringBuffer result = new StringBuffer();
		
		if(isRun) 
		{
			GenericScope genScope = (GenericScope)scope;
			result.append("run { } for "+genScope.getScopeSize());
			
			if (genScope.getScopeable().size()>0)
			{
				result.append(" but ");
				
				Scopeable s1 = ((Scopeable)genScope.getScopeable().get(0));
				result.append(s1.getScopeSize()+" "+s1.getSignature()+", ");
				
				Scopeable s2 = ((Scopeable)genScope.getScopeable().get(1));
				result.append(s2.getScopeSize()+" "+s2.getSignature());
					
			}									
		}
		
		return result.toString();
	}

} //CommandDeclarationImpl
