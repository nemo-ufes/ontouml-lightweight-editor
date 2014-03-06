package br.ufes.inf.nemo.ontouml.editor.model;

import java.util.Map;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

//import RefOntoUML.provider.RefOntoUMLItemProviderAdapterFactory;

public class EcoreHelper {

	private static Diagnostician validator;
	private static ResourceSet resourceSet;
	private static ComposedAdapterFactory adapterFactory;
	private static AdapterFactoryEditingDomain editingDomain;
	
	private static boolean initialized = false;
	
	public static void initializeHelper() {
		
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,new EcoreResourceFactory());
		resourceSet.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI, RefOntoUML.RefOntoUMLPackage.eINSTANCE);		
		validator = Diagnostician.INSTANCE;
		
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
//		adapterFactory.addAdapterFactory(new RefOntoUMLItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		
		initialized = true;
	}
	
	public static Resource createResource() {
		if (!initialized) {
			initializeHelper();
		}
		Resource resource = resourceSet.createResource(URI.createFileURI(""));
		return resource;
	}
	
	public static boolean validate(EObject eobject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {

		if (!initialized) {
			initializeHelper();
		}
				
		return validator.validate(eobject, diagnostics, context);
	}
	
	public static AdapterFactoryEditingDomain getEditingDomain() {
		if (!initialized) {
			initializeHelper();
		}
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
				new BasicCommandStack(), resourceSet);
		return editingDomain;
	}
	
	public static String getUUIDFromElement(EObject eobject)
	{
		return eobject.eResource().getURIFragment(eobject);
	}
}


