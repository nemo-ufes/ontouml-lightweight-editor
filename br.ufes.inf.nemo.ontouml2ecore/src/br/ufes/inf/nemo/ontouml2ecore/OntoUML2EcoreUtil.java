package br.ufes.inf.nemo.ontouml2ecore;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

/**
 * 
 * This class has both methods: reading and recording UML models into resources.
 * 
 * @author John Guerson
 */

public class OntoUML2EcoreUtil {

	public static Resource saveEcore (String ecorepath, EPackage ecoremodel) 
	{
		ResourceSet ecoreResourceSet = new ResourceSetImpl();
		URI ecoreURI = URI.createFileURI(ecorepath);
		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		ecoreResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		ecoreResourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

		// enable extended metadata
		final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(ecoreResourceSet	.getPackageRegistry());
		ecoreResourceSet.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA,
		    extendedMetaData);
				    	
	    Resource resource = ecoreResourceSet.createResource(ecoreURI);    	
	    resource.getContents().add(ecoremodel);    	
	    try{
	    	resource.save(Collections.emptyMap());
	    }catch(IOException e){
	    	e.printStackTrace();
	    }	    	    
	    return resource;		   	
	}
	
	public static EPackage readEcore (String ecorePath)
	{	
		ResourceSet ecoreResourceSet = new ResourceSetImpl();		
		URI ecoreURI = URI.createFileURI(ecorePath);
		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		ecoreResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		ecoreResourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
				
		// enable extended metadata
		final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(ecoreResourceSet	.getPackageRegistry());
		ecoreResourceSet.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA,
		    extendedMetaData);
		
	    Resource ecoreResource = ecoreResourceSet.getResource(ecoreURI,true);
	    EPackage ecoremodel = (EPackage) ecoreResource.getContents().get(0);
	    
	    ecoreResource.getResourceSet().getPackageRegistry().put(ecoremodel.getNsURI(),ecoremodel);
	    EPackage.Registry.INSTANCE.put(ecoremodel.getNsURI(), ecoremodel);
	    
	    return ecoremodel;
	}	
}
