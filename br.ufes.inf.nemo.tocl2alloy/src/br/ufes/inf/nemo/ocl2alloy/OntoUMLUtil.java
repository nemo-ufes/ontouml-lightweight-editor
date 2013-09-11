package br.ufes.inf.nemo.ocl2alloy;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class OntoUMLUtil {

	public static Resource readOntoUML (String refontoumlpath) throws IOException
	{
		ResourceSet rset = new ResourceSetImpl();					
			
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("refontouml",new XMIResourceFactoryImpl());
		
		rset.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,	RefOntoUML.RefOntoUMLPackage.eINSTANCE);		
		
	    File file = new File(refontoumlpath);
		URI fileURI = URI.createFileURI(file.getAbsolutePath());		
		Resource resource = rset.createResource(fileURI);		
		
		resource.load(Collections.emptyMap());
		
		return resource;		
	}
	
	public static Resource saveOntoUML (String refontoumlpath, RefOntoUML.Package refmodel) 
	{
		ResourceSet rset = new ResourceSetImpl();					
		
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("refontouml",new XMIResourceFactoryImpl());
		
		rset.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,	RefOntoUML.RefOntoUMLPackage.eINSTANCE);
    	
		URI fileURI = URI.createFileURI(refontoumlpath);    	
	    final Resource resource = rset.createResource(fileURI);    	
	    resource.getContents().add(refmodel);    	
	
	    try{
	    	resource.save(Collections.emptyMap());
	    }catch(IOException e){
	    	e.printStackTrace();
	    }
	    
	    return resource;		   	
	}

	
}
