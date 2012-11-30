package br.ufes.inf.nemo.move.mvc.model;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Package;

import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;

/**
 * This class represents an UML Model.
 * 
 * @author John Guerson
 */

public class UMLModel {
	
	/** Absolute path of UML model in file system. */
	private String umlPath;	
	
	/** File name of UML model. */
	public String umlmodelName;
	
	/** Destination directory of UML model file. */	
	public static String umlOutDirectory;	
	
	/** UML resource containing the UML model. */	
	private Resource umlResource;
	
	/** UML root Package or root Model. */
	private Package umlmodel;
	
	/** Details of some operations. */
	private String logDetails= new String();
	
	/**
	 * This constructor creates an UML model from a absolute path. i.e. without any content.
	 * 
	 * @param umlPath
	 */
	public UMLModel(String umlPath)
	{
		this();
		
		setUMLModel(umlPath);
	}
	
	/**
	 * This constructor creates an UML model from a absolute path initializing the UML model content
	 * with the transformation of OntoUML model into UML.
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
	 * Creates an Empty UML model.
	 */
	public UMLModel () { }
	
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
