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
import br.ufes.inf.nemo.alloy.Inheritance;
import br.ufes.inf.nemo.alloy.Multiplicity;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;

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
 * An implementation of the model object '<em><b>Signature Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.SignatureDeclarationImpl#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.SignatureDeclarationImpl#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.SignatureDeclarationImpl#getRelation <em>Relation</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.SignatureDeclarationImpl#getBlock <em>Block</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.SignatureDeclarationImpl#isExists <em>Exists</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.SignatureDeclarationImpl#getInheritance <em>Inheritance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SignatureDeclarationImpl extends ParagraphImpl implements SignatureDeclaration {
	/**
	 * The default value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean isAbstract = IS_ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected static final Multiplicity MULTIPLICITY_EDEFAULT = Multiplicity.SOME;

	/**
	 * The cached value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected Multiplicity multiplicity = MULTIPLICITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRelation() <em>Relation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelation()
	 * @generated
	 * @ordered
	 */
	protected EList<Declaration> relation;

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
	 * The default value of the '{@link #isExists() <em>Exists</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExists()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXISTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExists() <em>Exists</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExists()
	 * @generated
	 * @ordered
	 */
	protected boolean exists = EXISTS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInheritance() <em>Inheritance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInheritance()
	 * @generated
	 * @ordered
	 */
	protected Inheritance inheritance;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SignatureDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.SIGNATURE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsAbstract() {
		return isAbstract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsAbstract(boolean newIsAbstract) {
		boolean oldIsAbstract = isAbstract;
		isAbstract = newIsAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.SIGNATURE_DECLARATION__IS_ABSTRACT, oldIsAbstract, isAbstract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Multiplicity getMultiplicity() {
		return multiplicity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultiplicity(Multiplicity newMultiplicity) {
		Multiplicity oldMultiplicity = multiplicity;
		multiplicity = newMultiplicity == null ? MULTIPLICITY_EDEFAULT : newMultiplicity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.SIGNATURE_DECLARATION__MULTIPLICITY, oldMultiplicity, multiplicity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings({ })
	public EList<Declaration> getRelation() {
		if (relation == null) {
			relation = new EObjectContainmentEList<Declaration>(Declaration.class, this, AlloyPackage.SIGNATURE_DECLARATION__RELATION);
		}
		return relation;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.SIGNATURE_DECLARATION__BLOCK, oldBlock, newBlock);
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
				msgs = ((InternalEObject)block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.SIGNATURE_DECLARATION__BLOCK, null, msgs);
			if (newBlock != null)
				msgs = ((InternalEObject)newBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.SIGNATURE_DECLARATION__BLOCK, null, msgs);
			msgs = basicSetBlock(newBlock, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.SIGNATURE_DECLARATION__BLOCK, newBlock, newBlock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExists() {
		return exists;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExists(boolean newExists) {
		boolean oldExists = exists;
		exists = newExists;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.SIGNATURE_DECLARATION__EXISTS, oldExists, exists));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Inheritance getInheritance() {
		return inheritance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInheritance(Inheritance newInheritance, NotificationChain msgs) {
		Inheritance oldInheritance = inheritance;
		inheritance = newInheritance;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlloyPackage.SIGNATURE_DECLARATION__INHERITANCE, oldInheritance, newInheritance);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInheritance(Inheritance newInheritance) {
		if (newInheritance != inheritance) {
			NotificationChain msgs = null;
			if (inheritance != null)
				msgs = ((InternalEObject)inheritance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.SIGNATURE_DECLARATION__INHERITANCE, null, msgs);
			if (newInheritance != null)
				msgs = ((InternalEObject)newInheritance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlloyPackage.SIGNATURE_DECLARATION__INHERITANCE, null, msgs);
			msgs = basicSetInheritance(newInheritance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.SIGNATURE_DECLARATION__INHERITANCE, newInheritance, newInheritance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlloyPackage.SIGNATURE_DECLARATION__RELATION:
				return ((InternalEList<?>)getRelation()).basicRemove(otherEnd, msgs);
			case AlloyPackage.SIGNATURE_DECLARATION__BLOCK:
				return basicSetBlock(null, msgs);
			case AlloyPackage.SIGNATURE_DECLARATION__INHERITANCE:
				return basicSetInheritance(null, msgs);
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
			case AlloyPackage.SIGNATURE_DECLARATION__IS_ABSTRACT:
				return isIsAbstract();
			case AlloyPackage.SIGNATURE_DECLARATION__MULTIPLICITY:
				return getMultiplicity();
			case AlloyPackage.SIGNATURE_DECLARATION__RELATION:
				return getRelation();
			case AlloyPackage.SIGNATURE_DECLARATION__BLOCK:
				return getBlock();
			case AlloyPackage.SIGNATURE_DECLARATION__EXISTS:
				return isExists();
			case AlloyPackage.SIGNATURE_DECLARATION__INHERITANCE:
				return getInheritance();
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
			case AlloyPackage.SIGNATURE_DECLARATION__IS_ABSTRACT:
				setIsAbstract((Boolean)newValue);
				return;
			case AlloyPackage.SIGNATURE_DECLARATION__MULTIPLICITY:
				setMultiplicity((Multiplicity)newValue);
				return;
			case AlloyPackage.SIGNATURE_DECLARATION__RELATION:
				getRelation().clear();
				getRelation().addAll((Collection<? extends Declaration>)newValue);
				return;
			case AlloyPackage.SIGNATURE_DECLARATION__BLOCK:
				setBlock((Block)newValue);
				return;
			case AlloyPackage.SIGNATURE_DECLARATION__EXISTS:
				setExists((Boolean)newValue);
				return;
			case AlloyPackage.SIGNATURE_DECLARATION__INHERITANCE:
				setInheritance((Inheritance)newValue);
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
			case AlloyPackage.SIGNATURE_DECLARATION__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case AlloyPackage.SIGNATURE_DECLARATION__MULTIPLICITY:
				setMultiplicity(MULTIPLICITY_EDEFAULT);
				return;
			case AlloyPackage.SIGNATURE_DECLARATION__RELATION:
				getRelation().clear();
				return;
			case AlloyPackage.SIGNATURE_DECLARATION__BLOCK:
				setBlock((Block)null);
				return;
			case AlloyPackage.SIGNATURE_DECLARATION__EXISTS:
				setExists(EXISTS_EDEFAULT);
				return;
			case AlloyPackage.SIGNATURE_DECLARATION__INHERITANCE:
				setInheritance((Inheritance)null);
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
			case AlloyPackage.SIGNATURE_DECLARATION__IS_ABSTRACT:
				return isAbstract != IS_ABSTRACT_EDEFAULT;
			case AlloyPackage.SIGNATURE_DECLARATION__MULTIPLICITY:
				return multiplicity != MULTIPLICITY_EDEFAULT;
			case AlloyPackage.SIGNATURE_DECLARATION__RELATION:
				return relation != null && !relation.isEmpty();
			case AlloyPackage.SIGNATURE_DECLARATION__BLOCK:
				return block != null;
			case AlloyPackage.SIGNATURE_DECLARATION__EXISTS:
				return exists != EXISTS_EDEFAULT;
			case AlloyPackage.SIGNATURE_DECLARATION__INHERITANCE:
				return inheritance != null;
		}
		return super.eIsSet(featureID);
	}

	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer();
		
		if(isAbstract) result.append("abstract ");
		
		//if(multiplicity != null) result.append(multiplicity.getName().toLowerCase()+" ");
		
		result.append("sig "+name);
		
		if (getInheritance()!=null)
		{
			if(getInheritance().isIsExtension()) result.append(" extends "+getInheritance().getSupertype());
			if(getInheritance().isIsSubset()) result.append(" in "+getInheritance().getSupertype());
		}
		
		if(getRelation().size() > 1) result.append(" {\n");		
		else result.append(" {");
		
		for(Declaration decl : getRelation())
		{
			if(decl.equals(relation.get(relation.size()-1))) result.append("\t"+decl+"\n");
			else result.append("\t"+decl+",\n");
		}
		
		result.append("}");
		
		if(getBlock() == null) result.append("\n");
		
		if(getBlock() != null) result.append("{\n"+getBlock()+"}\n");
		
		return result.toString();
	}

} //SignatureDeclarationImpl
