package br.ufes.inf.nemo.ontouml2uml;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

/**
 * @author John Guerson
 */

public class OntoUML2UML {
		 	  
	public static Transformation transformer;	
	public static String logDetails = new String();
	
	/**
	 * Testing...
	 * @param args
	 */
	public static void main(String[] args)
	{
		try {
			String path = "model/teste.refontouml";
			Transformation(new OntoUMLParser(path),path.replace(".refontouml", ".uml" ),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Makes the transformation from OntoUML to UML.
	 * 
	 * @param refmodel
	 * @param umlPath
	 * @param ignorePackageHierarchy
	 * @return
	 */
	public static Resource Transformation (RefOntoUML.Package refmodel, String umlPath, boolean ignorePackageHierarchy)
	{
		logDetails="";
		Resource umlResource = null;
		
		OntoUMLParser refparser = new OntoUMLParser(refmodel);
		
		transformer = new Transformation(refparser,ignorePackageHierarchy);			  
		org.eclipse.uml2.uml.Package umlmodel = transformer.Transform();	
   		   
		umlResource = saveUML(umlPath,umlmodel);		   
		return umlResource;
	}
	
	/**
	 * Makes the transformation from OntoUML to UML.
	 * 
	 * @param refparser
	 * @param umlPath
	 * @param ignorePackageHierarchy
	 * @return
	 */
	public static Resource Transformation (OntoUMLParser refparser, String umlPath, boolean ignorePackageHierarchy)
	{
		logDetails="";
		Resource umlResource = null;
		
		transformer = new Transformation(refparser,ignorePackageHierarchy);			  
		org.eclipse.uml2.uml.Package umlmodel = transformer.Transform();	
   		   
		umlResource = saveUML(umlPath,umlmodel);		   
		return umlResource;
	}
		
	/**
	 * Auxiliar Method to save the UML Model to a Resource.
	 * 
	 * @param umlpath
	 * @param umlmodel
	 * @return
	 */
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
}
