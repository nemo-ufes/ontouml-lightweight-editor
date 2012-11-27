package br.ufes.inf.nemo.ontouml2uml;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

import br.ufes.inf.nemo.common.resource.ResourceUtil;

/**
 * @author John Guerson
 */

public class OntoUML2UML {
		 	  
	public static Transformation transformer;
	
	public static String logDetails = new String();
	
	/**
	 * Test.
	 * @param args
	 */
	public static void main(String[] args)
	{
		try {
			Transformation("C:\\Users\\John\\SVNs\\SVN-OLED\\OntoUML2UML\\model\\project.refontouml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Transforming to UML from an OntoUML Path.
	 */
	public static Resource Transformation (String refontoumlPath) throws IOException
	{
	   logDetails="";
	   Resource refontoumlResource = ResourceUtil.loadReferenceOntoUML(refontoumlPath);
	   	   
	   EObject model = refontoumlResource.getContents().get(0);    	
	   Resource umlResource = null;   
	   
	   if(model instanceof RefOntoUML.Package)
	   {		   		
		   transformer = new Transformation((RefOntoUML.Package)model);			  
		   org.eclipse.uml2.uml.Package umlmodel = transformer.Transform();	
		   
		   String noExtPath = refontoumlPath.substring(0, refontoumlPath.lastIndexOf(File.separator));
	       String noExtName = refontoumlPath.substring(refontoumlPath.lastIndexOf(File.separator)+1,refontoumlPath.length()-11);    	
		   		   
		   umlResource = saveUML(noExtPath+File.separator+noExtName+".uml",umlmodel);		   
		}
	   
	    return umlResource;
	}
    		
	/** 
	 * Transforming to UML from an OntoUML Package and UML Path. I Use this one for a particular case. 
	 */
	public static Resource Transformation (RefOntoUML.Package model, String umlPath)
	{
	   logDetails="";
	   transformer = new Transformation((RefOntoUML.Package)model);	
	   
	   org.eclipse.uml2.uml.Package umlmodel = transformer.Transform();
	   Resource umlResource = saveUML(umlPath, umlmodel);	
	   
	   return umlResource;
	}
	
	/**
	 * Transforming to UML from an OntoUML Path and UML Path.
	 */
	public static Resource Transformation (String refontoumlPath, String umlPath) throws IOException
	{
	   logDetails="";
	   Resource refontoumlResource = ResourceUtil.loadReferenceOntoUML(refontoumlPath);
	   	   
	   EObject model = refontoumlResource.getContents().get(0);    	
	   Resource umlResource = null;   
	   
	   if(model instanceof RefOntoUML.Package)
	   {		   		
		   transformer = new Transformation((RefOntoUML.Package)model);			  
		   org.eclipse.uml2.uml.Package umlmodel = transformer.Transform();		   		   
		   umlResource = saveUML(umlPath,umlmodel);		   
		}
	   
	    return umlResource;
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
