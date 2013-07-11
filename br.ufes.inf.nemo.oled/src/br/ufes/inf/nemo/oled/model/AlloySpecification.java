package br.ufes.inf.nemo.oled.model;

import java.io.File;
import java.io.IOException;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2alloy.OCL2Alloy;
import br.ufes.inf.nemo.ocl2alloy.OCL2AlloyOptions;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * 
 * This class represents an Alloy Model.
 * 
 * @author John Guerson
 */

public class AlloySpecification {

	/** Absolute directory path of alloy specification. */
	public String alsOutDirectory;	
	
	/** File name of alloy specification. */
	private String alsmodelName;	
	
	/** Absolute path of alloy specification. */
	private String alsPath;	
	
	/** Content of alloy specification. */	
	private String content = new String();
	
	/** Log details for operations made. */
	private String logDetails = new String();

	/**
	 * This constructor basically initialize the path of alloy model, i.e. without any content.
	 * 
	 * @param alloyPath
	 */
	public AlloySpecification(String alloyPath)
	{
		this();
		
		setAlloyModel(alloyPath);
	}	
	
	/**
	 * This constructor initialize the path of this alloy model and transforms
	 * the OntoUML model into alloy according to the Options, saving the resultant
	 * code of the transformation into the alloy specification.
	 * 
	 * @param alloyPath
	 * @param ontoumlmodel
	 * @param optmodel
	 * 
	 * @throws Exception
	 */
	public AlloySpecification(String alloyPath,OntoUMLParser refparser, OntoUML2AlloyOptions optmodel) throws Exception
	{
		this();
		
		setAlloyModel(alloyPath,refparser,optmodel);
	}
	
	/**
	 * Creates an empty alloy model.
	 */
	public AlloySpecification() { }	
	
	/**
	 * This method initialize the path of this alloy model and transforms
	 * the OntoUML model into alloy according to the Options, saving the resultant
	 * code of the transformation into the alloy specification.
	 * 
	 * @param alloyPath
	 * @param refmodel
	 * @param opt
	 * 
	 * @throws Exception
	 */
	public void setAlloyModel(String alloyPath, OntoUMLParser refparser, OntoUML2AlloyOptions optmodel) throws Exception
	{
		setAlloyModel(alloyPath);				
		setAlloyModel(refparser,optmodel);	
	}
	
	/**
	 * This method transforms the OntoUML model into alloy according to the Options, 
	 * saving the resulting code of the transformation into the alloy specification.
	 * 
	 * @param ontoumlmodel
	 * @param ontoOptions
	 * 
	 * @throws Exception
	 */
	public void setAlloyModel(OntoUMLParser refparser, OntoUML2AlloyOptions ontoOptions) throws Exception
	{
		OntoUML2Alloy ontouml2alloy = new OntoUML2Alloy(refparser, alsPath, ontoOptions);
		content = ontouml2alloy.transform();		
	}
	
	public void setContent(String content) throws IOException
	{ 
		this.content = content; FileUtil.copyStringToFile(content, alsPath); 
	}	
	
	/**
	 * This method basically initialize the path of alloy model, i.e. without any content.
	 * 
	 * @param alloyPath
	 */
	public void setAlloyModel(String alloyPath)
	{			
		this.alsPath = alloyPath;
		
		alsOutDirectory = alsPath.substring(0, alsPath.lastIndexOf(File.separator)+1);		
		alsmodelName = alsPath.substring(alsPath.lastIndexOf(File.separator)+1,alsPath.length()).replace(".als","");		
	}
	
	/**
	 * This method transforms the OCL constraints according to the OntoUML model 
	 * into alloy, adding the resulting code into the alloy specification .
	 * 
	 * @param ontoumlmodel
	 * @param oclmodel
	 * 
	 * @return
	 */
	public String addConstraints(OntoUMLParser refparser, OCLDocument oclmodel, OCL2AlloyOptions oclOptions) throws IOException
	{
		String result = new String();
		result = "\n";
		
		result += OCL2Alloy.Transformation(oclmodel.getOCLParser(), oclOptions, refparser);
		
		FileUtil.writeToFile(result, alsPath);
		
		return OCL2Alloy.log;		
	}
	
	/** Get Log details for made operations. */
	public String getDetails() { return logDetails; }
	
	/**  Get absolute path of alloy specification. */
	public String getAlloyPath() { return alsPath; }
	
	/** Get file name of alloy specification. */
	public String getAlloyModelName() {	return alsmodelName; }
	
	/** Get content of alloy specification. */
	public String getContent() { return content; }
	
	
	/** Get the Destination Directory of this model. */
	public String getDirectory() { return alsOutDirectory; }
	
}
