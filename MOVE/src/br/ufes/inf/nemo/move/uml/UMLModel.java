package br.ufes.inf.nemo.move.uml;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Package;

import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;

/**
 * @author John Guerson
 */

public class UMLModel {
	
	private String umlPath;	
	public String umlmodelName;
	public static String umlOutDirectory;	
	private Resource umlResource;
	private Package umlmodel;
	
	/**
	 * Constructor.
	 * 
	 * @param umlPath
	 */
	public UMLModel(String umlPath)
	{
		this();
		
		setPath(umlPath);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param umlPath
	 * @param refmodel
	 */
	public UMLModel(String umlPath, RefOntoUML.Package refmodel)
	{
		this();
		
		setPath(umlPath);
		setUMLModel(umlPath,refmodel);
	}
	
	/**
	 * Constructor.
	 */
	public UMLModel ()
	{
		
	}
	
	public void setPath(String umlPath)
	{
		this.umlPath = umlPath;
		
		umlOutDirectory = umlPath.substring(0, umlPath.lastIndexOf(File.separator)+1);		
		umlmodelName = umlPath.substring(umlPath.lastIndexOf(File.separator)+1,umlPath.length()).replace(".uml","");
	}
	
	public void setUMLModel(String umlPath, RefOntoUML.Package refmodel)
	{
		umlResource = OntoUML2UML.Transformation(refmodel,umlPath);
		umlmodel = (Package)umlResource.getContents().get(0);
		
		setPath(umlPath);
	}

	public Package getUMLModelInstance()
	{
		return umlmodel;
	}
	
	public Resource getUMLResource()
	{
		return umlResource;
	}	

	public String getUMLPath() 
	{ 
		return this.umlPath; 
	}	

}
