/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.impl;

import br.ufes.inf.nemo.alloy.AlloyModule;
import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.ModuleImportation;
import br.ufes.inf.nemo.alloy.Paragraph;
import br.ufes.inf.nemo.alloy.SignatureParameter;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.AlloyModuleImpl#getName <em>Name</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.AlloyModuleImpl#getPath <em>Path</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.AlloyModuleImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.AlloyModuleImpl#getParagraph <em>Paragraph</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.alloy.impl.AlloyModuleImpl#getImports <em>Imports</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AlloyModuleImpl extends EObjectImpl implements AlloyModule {
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
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<SignatureParameter> parameters;

	/**
	 * The cached value of the '{@link #getParagraph() <em>Paragraph</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParagraph()
	 * @generated
	 * @ordered
	 */
	protected EList<Paragraph> paragraph;

	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<ModuleImportation> imports;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AlloyModuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlloyPackage.Literals.ALLOY_MODULE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.ALLOY_MODULE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlloyPackage.ALLOY_MODULE__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings({ })
	public EList<SignatureParameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<SignatureParameter>(SignatureParameter.class, this, AlloyPackage.ALLOY_MODULE__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings({ })
	public EList<Paragraph> getParagraph() {
		if (paragraph == null) {
			paragraph = new EObjectContainmentEList<Paragraph>(Paragraph.class, this, AlloyPackage.ALLOY_MODULE__PARAGRAPH);
		}
		return paragraph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings({ })
	public EList<ModuleImportation> getImports() {
		if (imports == null) {
			imports = new EObjectContainmentEList<ModuleImportation>(ModuleImportation.class, this, AlloyPackage.ALLOY_MODULE__IMPORTS);
		}
		return imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlloyPackage.ALLOY_MODULE__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
			case AlloyPackage.ALLOY_MODULE__PARAGRAPH:
				return ((InternalEList<?>)getParagraph()).basicRemove(otherEnd, msgs);
			case AlloyPackage.ALLOY_MODULE__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
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
			case AlloyPackage.ALLOY_MODULE__NAME:
				return getName();
			case AlloyPackage.ALLOY_MODULE__PATH:
				return getPath();
			case AlloyPackage.ALLOY_MODULE__PARAMETERS:
				return getParameters();
			case AlloyPackage.ALLOY_MODULE__PARAGRAPH:
				return getParagraph();
			case AlloyPackage.ALLOY_MODULE__IMPORTS:
				return getImports();
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
			case AlloyPackage.ALLOY_MODULE__NAME:
				setName((String)newValue);
				return;
			case AlloyPackage.ALLOY_MODULE__PATH:
				setPath((String)newValue);
				return;
			case AlloyPackage.ALLOY_MODULE__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends SignatureParameter>)newValue);
				return;
			case AlloyPackage.ALLOY_MODULE__PARAGRAPH:
				getParagraph().clear();
				getParagraph().addAll((Collection<? extends Paragraph>)newValue);
				return;
			case AlloyPackage.ALLOY_MODULE__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends ModuleImportation>)newValue);
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
			case AlloyPackage.ALLOY_MODULE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case AlloyPackage.ALLOY_MODULE__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case AlloyPackage.ALLOY_MODULE__PARAMETERS:
				getParameters().clear();
				return;
			case AlloyPackage.ALLOY_MODULE__PARAGRAPH:
				getParagraph().clear();
				return;
			case AlloyPackage.ALLOY_MODULE__IMPORTS:
				getImports().clear();
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
			case AlloyPackage.ALLOY_MODULE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case AlloyPackage.ALLOY_MODULE__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case AlloyPackage.ALLOY_MODULE__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case AlloyPackage.ALLOY_MODULE__PARAGRAPH:
				return paragraph != null && !paragraph.isEmpty();
			case AlloyPackage.ALLOY_MODULE__IMPORTS:
				return imports != null && !imports.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		
		result.append("module "+name+"\n\n");
		int cont=1;
		for(ModuleImportation mi : imports)
		{
			String params = new String();
			
			for(String param : mi.getParameters())
			{
				if(param.equals(mi.getParameters().get(mi.getParameters().size()-1)))
				{
					params = params + param;
				}
				else
				{
					params = params + param + ",";
				}
			}
			
			if(mi.getPath()!=null && !(mi.getPath().isEmpty()) )
				result.append("open "+mi.getPath()+"/");
			else
				result.append("open ");
			
			result.append(mi.getName());
			if(mi.getParameters().size()!=0)
				result.append("["+params+"]");
			
			if (cont < imports.size()) 
				result.append("\n");
			else 
				result.append("\n\n");
			cont++;
		}
		
		for(Paragraph p : paragraph)
		{
			result.append(p+"\n");
		}
		
		return result.toString();
	}

} //AlloyModuleImpl
