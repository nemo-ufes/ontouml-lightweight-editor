package br.ufes.inf.nemo.common.resource;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * This class is used as a Resource utility.
 */

public class ResourceUtil {

	/**
	 * Load Reference OntoUML Model to a Resource.
	 */
	public static Resource loadReferenceOntoUML (String refontoumlpath) throws IOException
	{
		ResourceSet rset = new ResourceSetImpl();				
		
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("refontouml",new XMIResourceFactoryImpl());	
		
		rset.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,	RefOntoUML.RefOntoUMLPackage.eINSTANCE);		
	    rset.getPackageRegistry().put("http://nemo.inf.ufes.br/ontouml/refontouml", RefOntoUML.RefOntoUMLPackage.eINSTANCE);
		
	    File file = new File(refontoumlpath);
		URI fileURI = URI.createFileURI(file.getAbsolutePath());		
		Resource resource = rset.createResource(fileURI);		
		
		resource.load(Collections.emptyMap());
		
		return resource;		
	}
	
	/** 
	 * Save UML Model to a Resource 
	 */	
	public static Resource saveUML (String umlpath, org.eclipse.uml2.uml.Package umlmodel) 
	{
		final ResourceSet rset = new ResourceSetImpl();
		
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);	
		rset.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
    	
		URI fileURI = URI.createFileURI(umlpath);    	
	    final Resource resource = rset.createResource(fileURI);    	
	    resource.getContents().add(umlmodel);    	
	
	    try{
	    	resource.save(Collections.emptyMap());
	    }catch(IOException e){
	    	e.printStackTrace();
	    }
	    
	    return resource;		   	
	}
	
	
}
