package br.ufes.inf.nemo.move.mvc.model;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Package;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
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
	public UMLModel(String umlPath, OntoUMLParser refparser)
	{
		this();
		
		setUMLModel(umlPath);
		setUMLModel(umlPath,refparser);
	}
	
	/**
	 * Creates an Empty UML model.
	 */
	public UMLModel () { }
	
	/**
	 * Set Absolute path of UML model. i.e. without any content.
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
	 * Set UML model content from a OntoUML model by transforming the OntoUML model into UML.
	 * 
	 * @param umlPath
	 * @param ontoumlmodel
	 */
	public void setUMLModel(String umlPath, OntoUMLParser refparser)
	{
		umlResource = OntoUML2UML.Transformation(refparser,umlPath);
		
		logDetails = OntoUML2UML.logDetails;
		
		umlmodel = (Package)umlResource.getContents().get(0);
		
		setUMLModel(umlPath);
	}

	/**
	 * Get the operations details.
	 * 
	 * @return
	 */
	public String getDetails() { return logDetails; }
	
	/** Get the UML model package instance.
	 * 
	 * @return
	 */
	public Package getUMLModelInstance() { return umlmodel; }
	
	/**
	 * Get UML resource containing the UML model intance Package.
	 * 
	 * @return
	 */
	public Resource getUMLResource() { return umlResource; }	

	/**
	 * Get UML absolute path in file system. 
	 * 
	 * @return
	 */
	public String getUMLPath() { return this.umlPath; }	

}
