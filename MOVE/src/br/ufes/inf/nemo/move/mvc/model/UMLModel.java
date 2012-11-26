package br.ufes.inf.nemo.move.mvc.model;

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
	private String logDetails= new String();
	
	/**
	 * Constructor.
	 * 
	 * @param umlPath
	 */
	public UMLModel(String umlPath)
	{
		this();
		
		setUMLModel(umlPath);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param umlPath
	 * @param refmodel
	 */
	public UMLModel(String umlPath, OntoUMLModel ontoumlmodel)
	{
		this();
		
		setUMLModel(umlPath);
		setUMLModel(umlPath,ontoumlmodel);
	}
	
	/**
	 * Constructor.
	 */
	public UMLModel ()
	{
		
	}
	
	/**
	 * Set Path of UML Model.
	 * 
	 * @param umlPath
	 */
	public void setUMLModel(String umlPath)
	{
		this.umlPath = umlPath;
		
		umlOutDirectory = umlPath.substring(0, umlPath.lastIndexOf(File.separator)+1);		
		umlmodelName = umlPath.substring(umlPath.lastIndexOf(File.separator)+1,umlPath.length()).replace(".uml","");
	}
	
	/**
	 * Set UML Model from OntoUML...
	 * 
	 * @param umlPath
	 * @param ontoumlmodel
	 */
	public void setUMLModel(String umlPath,OntoUMLModel ontoumlmodel)
	{
		umlResource = OntoUML2UML.Transformation(ontoumlmodel.getOntoUMLModelInstance(),umlPath);
		
		logDetails = OntoUML2UML.logDetails;
		
		umlmodel = (Package)umlResource.getContents().get(0);
		
		setUMLModel(umlPath);
	}

	public String getDetails()
	{		
		return logDetails;
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
