package br.ufes.inf.nemo.move.mvc.model;

import java.io.File;
import java.io.IOException;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.ocl2alloy.transformer.OCL2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;

/**
 * 
 * This class represents an Alloy Model.
 * 
 * @author John Guerson
 */

public class AlloyModel {

	/** Absolute directory path of alloy specification. */
	public static String alsOutDirectory = AlloyModel.class.getProtectionDomain().getCodeSource().getLocation().getPath();	
	
	/** File name of alloy specification. */
	private String alsmodelName = "OUTPUT";	
	
	/** Absolute path of alloy specification. */
	private String alsPath = AlloyModel.class.getProtectionDomain().getCodeSource().getLocation().getPath()+alsmodelName+".als";	
	
	/** Content of alloy specification. */	
	private String content = new String();
	
	/** Log details for operations made. */
	private String logDetails = new String();

	/**
	 * This constructor basically initialize the path of alloy model, i.e. without any content.
	 * 
	 * @param alloyPath
	 */
	public AlloyModel(String alloyPath)
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
	public AlloyModel(String alloyPath,OntoUMLModel ontoumlmodel, OntoUMLOptionsModel optmodel) throws Exception
	{
		this();
		
		setAlloyModel(alloyPath,ontoumlmodel,optmodel);
	}
	
	/**
	 * Creates an empty alloy model.
	 */
	public AlloyModel() { }	
	
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
	public void setAlloyModel(String alloyPath, OntoUMLModel ontoumlmodel, OntoUMLOptionsModel optmodel) throws Exception
	{
		setAlloyModel(alloyPath);				
		setAlloyModel(ontoumlmodel,optmodel);	
	}
	
	/**
	 * This method transforms the OntoUML model into alloy according to the Options, 
	 * saving the resulting code of the transformation into the alloy specification.
	 * 
	 * @param ontoumlmodel
	 * @param optmodel
	 * 
	 * @throws Exception
	 */
	public void setAlloyModel(OntoUMLModel ontoumlmodel, OntoUMLOptionsModel optmodel) throws Exception
	{
		content = OntoUML2Alloy.Transformation(ontoumlmodel.getOntoUMLModelInstance(), alsPath, optmodel.getOptions());		
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
	public String addConstraints(OntoUMLModel ontoumlmodel, OCLModel oclmodel) throws IOException
	{
		String result = new String();
		result = "\n";
		for(Constraint ct: oclmodel.getOCLParser().getConstraints())
		{
			result += OCL2Alloy.Transformation(ct, "FACT", oclmodel.getOCLParser(), ontoumlmodel.getOntoUMLParser())+"\n";
			//result += OCL2Alloy.Transformation(ct, "SIMULATION", oclmodel.getOCLParser(), ontoumlmodel.getOntoUMLParser())+"\n";
			//result += OCL2Alloy.Transformation(ct, "ASSERTION", oclmodel.getOCLParser(), ontoumlmodel.getOntoUMLParser())+"\n";
		}
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
	
}
