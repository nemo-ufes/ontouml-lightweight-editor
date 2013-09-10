package br.ufes.inf.nemo.ontouml2ecore;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

public class OntoUML2EcoreUtil {

	public static Resource saveEcore (String ecorepath, EPackage ecoremodel) 
	{
		ResourceSet rset = new ResourceSetImpl();
		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		rset.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		
		URI fileURI = URI.createFileURI(ecorepath);    	
	    Resource resource = rset.createResource(fileURI);    	
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
		URI ecoreURI = URI.createFileURI(ecorePath);	
		
		ResourceSet ecoreResourceSet = new ResourceSetImpl();
				
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		ecoreResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		ecoreResourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		    	
	    Resource ecoreResource = ecoreResourceSet.getResource(ecoreURI,true);
	    EPackage ecoremodel = (EPackage) ecoreResource.getContents().get(0);
	    ecoreResource.getResourceSet().getPackageRegistry().put(null,ecoremodel);
	    
	    return ecoremodel;
	}
}
