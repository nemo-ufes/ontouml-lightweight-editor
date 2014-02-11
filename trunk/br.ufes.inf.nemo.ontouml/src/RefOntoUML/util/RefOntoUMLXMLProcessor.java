/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.util;

import RefOntoUML.RefOntoUMLPackage;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RefOntoUMLXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefOntoUMLXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		RefOntoUMLPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the RefOntoUMLResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new RefOntoUMLResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new RefOntoUMLResourceFactoryImpl());
		}
		return registrations;
	}

} //RefOntoUMLXMLProcessor
