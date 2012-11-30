package br.ufes.inf.nemo.move.mvc.model;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlverificator.SyntacticVerificator;
import br.ufes.inf.nemo.common.resource.ResourceUtil;

/**
 * This class represents an OntoUML Model.
 * 
 * @author John Guerson
 */

public class OntoUMLModel {

	/** root ontoUML Package (or ontoUML Model). */	
	private RefOntoUML.Package refmodel;
	
	/** OntoUML parser of the root ontoUML Package. */
	private OntoUMLParser refparser;	
	
	/** Absolute path of ontoUML model. */
	private String refPath;
	
	/**
	 * This constructor initializes the path of the ontoUML model.  i.e. without any content.
	 * 
	 * @param refPath
	 * @throws IOException
	 */
	public OntoUMLModel(String refPath) throws IOException
	{
		setOntoUML(refPath);
	}	
	
	/**
	 * Creates an Empty ontoUML model.
	 */
	public OntoUMLModel() { }
	
	/**
	 * This constructor initializes the content (root ontoUML Package or Model) .  i.e. without the absolute path.
	 * 
	 * @param refmodel
	 */
	public OntoUMLModel(RefOntoUML.Package refmodel)
	{
		setOntoUML(refmodel);
	}
	
	/**
	 * This methods verifies the model Syntactically.
	 * 
	 * @return
	 */
	public String verifyModel()
	{
		return SyntacticVerificator.verify(refmodel);
	}
	
	/**
	 * This method set the content (root ontoUML Package or Model) .  i.e. without the absolute path.
	 * 
	 * @param refmodel
	 */
	public void setOntoUML (RefOntoUML.Package refmodel)
	{
		this.refmodel = refmodel;
		this.refparser = new OntoUMLParser(refmodel);
		this.refPath = null;
	}
	
	/**
	 * This method set the ontoUML model from a absolute path in file system.
	 * 
	 * @param refPath
	 * @throws IOException
	 */
	public void setOntoUML (String refPath) throws IOException
	{
		this.refPath = refPath;
		Resource resource = ResourceUtil.loadReferenceOntoUML(refPath);		
		this.refmodel = (RefOntoUML.Package) resource.getContents().get(0);		
		this.refparser = new OntoUMLParser(refmodel);		
	}	
	
	/** Get ontoUML model instance (root Package or Model). */
	public RefOntoUML.Package getOntoUMLModelInstance () { return refmodel; }
	
	/** Get ontoUML Parser. */
	public OntoUMLParser getOntoUMLParser () { return refparser; }
	
	/** Get ontoUML Absolute path. */
	public String getOntoUMLPath () { return refPath; }
}
