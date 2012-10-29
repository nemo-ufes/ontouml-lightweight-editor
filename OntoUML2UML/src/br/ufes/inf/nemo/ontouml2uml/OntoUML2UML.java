package br.ufes.inf.nemo.ontouml2uml;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.resource.ResourceUtil;

public class OntoUML2UML {
		 	  
	public static Transformation transformer;
		
	public static Resource Transformation (String refontoumlPath) throws IOException
	{
	   Resource refontoumlResource = ResourceUtil.loadReferenceOntoUML(refontoumlPath);
	   	   
	   EObject model = refontoumlResource.getContents().get(0);    	
	   Resource umlResource = null;   
	   
	   if(model instanceof RefOntoUML.Package)
	   {		   		
		   transformer = new Transformation((RefOntoUML.Package)model);			  
		   org.eclipse.uml2.uml.Package umlmodel = transformer.Transform();	
		   
		   String noExtPath = refontoumlPath.substring(0, refontoumlPath.lastIndexOf(File.separator));
	       String noExtName = refontoumlPath.substring(refontoumlPath.lastIndexOf(File.separator)+1,refontoumlPath.length()-11);    	
		   		   
		   umlResource = ResourceUtil.saveUML(noExtPath+File.separator+noExtName+".uml",umlmodel);		   
		}
	   
	    return umlResource;
	}
    		
	/** 
	 * I Use this one for a particular case. 
	 */
	public static org.eclipse.uml2.uml.Package Transformation (RefOntoUML.Package model)
	{
	   transformer = new Transformation((RefOntoUML.Package)model);	
	   
	   org.eclipse.uml2.uml.Package umlmodel = transformer.Transform();
	   
	   return umlmodel;
	}
	
	public static Resource Transformation (String refontoumlPath, String umlPath) throws IOException
	{
	   Resource refontoumlResource = ResourceUtil.loadReferenceOntoUML(refontoumlPath);
	   	   
	   EObject model = refontoumlResource.getContents().get(0);    	
	   Resource umlResource = null;   
	   
	   if(model instanceof RefOntoUML.Package)
	   {		   		
		   transformer = new Transformation((RefOntoUML.Package)model);			  
		   org.eclipse.uml2.uml.Package umlmodel = transformer.Transform();		   		   
		   umlResource = ResourceUtil.saveUML(umlPath,umlmodel);		   
		}
	   
	    return umlResource;
	}
}
