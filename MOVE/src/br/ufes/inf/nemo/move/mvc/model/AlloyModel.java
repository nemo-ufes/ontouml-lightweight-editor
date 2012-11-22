package br.ufes.inf.nemo.move.mvc.model;

import java.io.File;

import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;

/**
 * @author John Guerson
 */

public class AlloyModel {

	public static String alsOutDirectory = AlloyModel.class.getProtectionDomain().getCodeSource().getLocation().getPath();	
	private String alsPath = new String();
	private String alsmodelName = "OUTPUT";	
	private String content = new String();

	/**
	 * Constructor.
	 * 
	 * @param alloyPath
	 */
	public AlloyModel(String alloyPath)
	{
		this();
		
		setAlloyModel(alloyPath);
	}	
	
	/**
	 * COnstructor.
	 * 
	 * @param alloyPath
	 * @param ontoumlmodel
	 * @param optmodel
	 * @throws Exception
	 */
	public AlloyModel(String alloyPath,OntoUMLModel ontoumlmodel, OptionsModel optmodel) throws Exception
	{
		this();
		
		setAlloyModel(alloyPath,ontoumlmodel,optmodel);
	}
	
	/**
	 * Constructor.
	 */
	public AlloyModel()
	{
		
	}	
	
	/**
	 * Set Alloy Model from OntoUML and Options.
	 * 
	 * @param alloyPath
	 * @param refmodel
	 * @param opt
	 * @throws Exception
	 */
	public void setAlloyModel(String alloyPath, OntoUMLModel ontoumlmodel, OptionsModel optmodel) throws Exception
	{
		setAlloyModel(alloyPath);				
		setAlloyModel(ontoumlmodel,optmodel);	
	}
	
	/**
	 * Set Alloy Model.
	 * 
	 * @param ontoumlmodel
	 * @param optmodel
	 * @throws Exception
	 */
	public void setAlloyModel(OntoUMLModel ontoumlmodel, OptionsModel optmodel) throws Exception
	{
		// Transforming OntoUML into Alloy
		content = OntoUML2Alloy.Transformation(ontoumlmodel.getOntoUMLModelInstance(), alsPath, optmodel.getOptions());		
	}
	
	/**
	 * Set Path of Alloy Model.
	 * 
	 * @param alloyPath
	 */
	public void setAlloyModel(String alloyPath)
	{			
		this.alsPath = alloyPath;
		
		alsOutDirectory = alsPath.substring(0, alsPath.lastIndexOf(File.separator)+1);		
		alsmodelName = alsPath.substring(alsPath.lastIndexOf(File.separator)+1,alsPath.length()).replace(".als","");		
	}
	
	public String getAlloyPath()
	{
		return alsPath;
	}
	
	public String getAlloyModelName()
	{
		return alsmodelName;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void addToContent(String text)
	{
		content+=text;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
}
