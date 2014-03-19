/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.ElementImport;
import RefOntoUML.Namespace;
import RefOntoUML.PackageableElement;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.VisibilityKind;

import RefOntoUML.util.RefOntoUMLValidator;

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Import</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RefOntoUML.impl.ElementImportImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link RefOntoUML.impl.ElementImportImpl#getAlias <em>Alias</em>}</li>
 *   <li>{@link RefOntoUML.impl.ElementImportImpl#getImportedElement <em>Imported Element</em>}</li>
 *   <li>{@link RefOntoUML.impl.ElementImportImpl#getImportingNamespace <em>Importing Namespace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementImportImpl extends DirectedRelationshipImpl implements ElementImport {
	/**
	 * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected static final VisibilityKind VISIBILITY_EDEFAULT = VisibilityKind.PUBLIC;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected VisibilityKind visibility = VISIBILITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getAlias() <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected static final String ALIAS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlias() <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected String alias = ALIAS_EDEFAULT;

	/**
	 * This is true if the Alias attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean aliasESet;

	/**
	 * The cached value of the '{@link #getImportedElement() <em>Imported Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportedElement()
	 * @generated
	 * @ordered
	 */
	protected PackageableElement importedElement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementImportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getElementImport();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisibilityKind getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(VisibilityKind newVisibility) {
		VisibilityKind oldVisibility = visibility;
		visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.ELEMENT_IMPORT__VISIBILITY, oldVisibility, visibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlias(String newAlias) {
		String oldAlias = alias;
		alias = newAlias;
		boolean oldAliasESet = aliasESet;
		aliasESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.ELEMENT_IMPORT__ALIAS, oldAlias, alias, !oldAliasESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAlias() {
		String oldAlias = alias;
		boolean oldAliasESet = aliasESet;
		alias = ALIAS_EDEFAULT;
		aliasESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, RefOntoUMLPackage.ELEMENT_IMPORT__ALIAS, oldAlias, ALIAS_EDEFAULT, oldAliasESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAlias() {
		return aliasESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageableElement getImportedElement() {
		if (importedElement != null && importedElement.eIsProxy()) {
			InternalEObject oldImportedElement = (InternalEObject)importedElement;
			importedElement = (PackageableElement)eResolveProxy(oldImportedElement);
			if (importedElement != oldImportedElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTED_ELEMENT, oldImportedElement, importedElement));
			}
		}
		return importedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageableElement basicGetImportedElement() {
		return importedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportedElement(PackageableElement newImportedElement) {
		PackageableElement oldImportedElement = importedElement;
		importedElement = newImportedElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTED_ELEMENT, oldImportedElement, importedElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Namespace getImportingNamespace() {
		if (eContainerFeatureID() != RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE) return null;
		return (Namespace)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImportingNamespace(Namespace newImportingNamespace, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newImportingNamespace, RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportingNamespace(Namespace newImportingNamespace) {
		if (newImportingNamespace != eInternalContainer() || (eContainerFeatureID() != RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE && newImportingNamespace != null)) {
			if (EcoreUtil.isAncestor(this, newImportingNamespace))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newImportingNamespace != null)
				msgs = ((InternalEObject)newImportingNamespace).eInverseAdd(this, RefOntoUMLPackage.NAMESPACE__ELEMENT_IMPORT, Namespace.class, msgs);
			msgs = basicSetImportingNamespace(newImportingNamespace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE, newImportingNamespace, newImportingNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unused")
	public boolean visibility_public_or_private(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 RefOntoUMLValidator.DIAGNOSTIC_SOURCE,
						 RefOntoUMLValidator.ELEMENT_IMPORT__VISIBILITY_PUBLIC_OR_PRIVATE,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "visibility_public_or_private", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unused")
	public boolean imported_element_is_public(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 RefOntoUMLValidator.DIAGNOSTIC_SOURCE,
						 RefOntoUMLValidator.ELEMENT_IMPORT__IMPORTED_ELEMENT_IS_PUBLIC,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "imported_element_is_public", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetImportingNamespace((Namespace)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE:
				return basicSetImportingNamespace(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE:
				return eInternalContainer().eInverseRemove(this, RefOntoUMLPackage.NAMESPACE__ELEMENT_IMPORT, Namespace.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RefOntoUMLPackage.ELEMENT_IMPORT__VISIBILITY:
				return getVisibility();
			case RefOntoUMLPackage.ELEMENT_IMPORT__ALIAS:
				return getAlias();
			case RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTED_ELEMENT:
				if (resolve) return getImportedElement();
				return basicGetImportedElement();
			case RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE:
				return getImportingNamespace();
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
			case RefOntoUMLPackage.ELEMENT_IMPORT__VISIBILITY:
				setVisibility((VisibilityKind)newValue);
				return;
			case RefOntoUMLPackage.ELEMENT_IMPORT__ALIAS:
				setAlias((String)newValue);
				return;
			case RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTED_ELEMENT:
				setImportedElement((PackageableElement)newValue);
				return;
			case RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE:
				setImportingNamespace((Namespace)newValue);
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
			case RefOntoUMLPackage.ELEMENT_IMPORT__VISIBILITY:
				setVisibility(VISIBILITY_EDEFAULT);
				return;
			case RefOntoUMLPackage.ELEMENT_IMPORT__ALIAS:
				unsetAlias();
				return;
			case RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTED_ELEMENT:
				setImportedElement((PackageableElement)null);
				return;
			case RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE:
				setImportingNamespace((Namespace)null);
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
			case RefOntoUMLPackage.ELEMENT_IMPORT__VISIBILITY:
				return visibility != VISIBILITY_EDEFAULT;
			case RefOntoUMLPackage.ELEMENT_IMPORT__ALIAS:
				return isSetAlias();
			case RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTED_ELEMENT:
				return importedElement != null;
			case RefOntoUMLPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE:
				return getImportingNamespace() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (visibility: ");
		result.append(visibility);
		result.append(", alias: ");
		if (aliasESet) result.append(alias); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ElementImportImpl
