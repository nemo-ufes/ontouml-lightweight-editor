package br.ufes.inf.nemo.move.ontouml;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.ResourceUtil;

/**
 * @author John Guerson
 */

public class OntoUMLModel {

	private RefOntoUML.Package refmodel;
	private OntoUMLParser refparser;	
	private String refPath;
	
	/**
	 * Constructor.
	 * 
	 * @param refPath
	 * @throws IOException
	 */
	public OntoUMLModel(String refPath) throws IOException
	{
		setOntoUML(refPath);
	}	
	
	/**
	 * Constructor.
	 */
	public OntoUMLModel()
	{
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param refmodel
	 */
	public OntoUMLModel(RefOntoUML.Package refmodel)
	{
		setOntoUML(refmodel);
	}
	
	public void setOntoUML (RefOntoUML.Package refmodel)
	{
		this.refmodel = refmodel;
		this.refparser = new OntoUMLParser(refmodel);
		this.refPath = null;
	}
	
	public void setOntoUML (String refPath) throws IOException
	{
		this.refPath = refPath;
		Resource resource = ResourceUtil.loadReferenceOntoUML(refPath);		
		this.refmodel = (RefOntoUML.Package) resource.getContents().get(0);		
		this.refparser = new OntoUMLParser(refmodel);		
	}	
	
	public RefOntoUML.Package getOntoUMLModelInstance ()
	{
		return refmodel;
	}
	
	public OntoUMLParser getOntoUMLParser ()
	{
		return refparser;
	}
	
	public String getOntoUMLPath ()
	{
		return refPath;
	}
}
