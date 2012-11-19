package br.ufes.inf.nemo.move.ocl;

import java.io.IOException;

import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.ocl2alloy.OCLParser;

/**
 * @author John Guerson
 */

public class OCLModel {
		
	private String oclmodel;	
	private String oclPath;
	
	@SuppressWarnings("unused")
	private OCLParser oclparser;
		
	/**
	 * COnstructor.
	 * 
	 * @param str
	 * @param type
	 * @throws IOException
	 */
	public OCLModel(String str, String type) throws IOException
	{
		setOCL(str,type);
	}
	
	/**
	 * Constructor.
	 */
	public OCLModel()
	{
		
	}
	
	/**
	 * Set OCL.
	 * 
	 * If type="PATH", OCL will be loaded from a Path file, 
	 * else if type="CONTENT", OCL will be loaded from OCL String content.
	 */
	public void setOCL (String str, String type) throws IOException
	{
		if  (type=="PATH") 
		{
			String content = FileUtil.readFile(str);		
			this.oclmodel = content;		
			this.oclPath = str;
			
		} else  if (type=="CONTENT") {
			
			String content = str;
			this.oclmodel = content;
			this.oclPath=null;
		}			
	}
	
	/**
	 * Set OCL Parser
	 * 
	 * If type="PATH", OCL will be loaded from a Path file, 
	 * else if type="CONTENT", OCL will be loaded from OCL String content.
	 * 
	 * @param str : String Constraints or OCL Model Path
	 * @param type : PATH ot CONTENT
	 * @param refmodel: RefOntoUML.Package
	 * @param umlPath^UML Model Path
	 */
	public void parse(RefOntoUML.Package refmodel, String umlPath) throws IOException, ParserException
	{
		if (oclmodel !=null)
		{
			this.oclparser = new OCLParser(oclmodel,refmodel, umlPath);
		}
	}
	
	public String getOCLModelInstance () 
	{ 
		return oclmodel; 
	}
	
	public String getOCLPath() 
	{ 
		return oclPath; 
	}

}

