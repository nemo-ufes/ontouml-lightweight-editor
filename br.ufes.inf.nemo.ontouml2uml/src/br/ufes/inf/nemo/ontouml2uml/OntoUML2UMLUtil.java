package br.ufes.inf.nemo.ontouml2uml;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * 
 * This class has both methods: reading and recording UML models into resources.
 * 
 * @author John Guerson
 *
 */
public class OntoUML2UMLUtil {

	public static Resource saveUML (String umlpath, org.eclipse.uml2.uml.Package umlmodel) 
	{
		ResourceSet rset = new ResourceSetImpl();	
		
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);	
		rset.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);    
		
		URI fileURI = URI.createFileURI(umlpath);    	
	    Resource resource = rset.createResource(fileURI);    	
	    resource.getContents().add(umlmodel);    	
	    try{
	    	resource.save(Collections.emptyMap());
	    }catch(IOException e){
	    	e.printStackTrace();
	    }	    	    
	    return resource;		   	
	}
	
	public static org.eclipse.uml2.uml.Package readUML (String umlPath)
	{
		URI umlURI = URI.createFileURI(umlPath);	
		
		ResourceSet umlResourceSet = new ResourceSetImpl();

		umlResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);	
		umlResourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);	
		
	    Resource umlResource = umlResourceSet.getResource(umlURI,true);
	    org.eclipse.uml2.uml.Package umlmodel = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);
	    umlResource.getResourceSet().getPackageRegistry().put(null,umlmodel);
	    
	    return umlmodel;
	}
	
}
