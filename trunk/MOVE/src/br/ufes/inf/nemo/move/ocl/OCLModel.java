package br.ufes.inf.nemo.move.ocl;

import java.io.IOException;

import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.ocl2alloy.OCLParser;

/**
 * @author John Guerson
 */

public class OCLModel {
		
	private String oclstring;
	private String oclpath;	
	private OCLParser oclparser;
		
	/**
	 * Constructor.
	 */
	public OCLModel()
	{
		
	}
		
	public void clearModel()
	{		
		oclstring="";
		oclpath="";
		oclparser=null;
	}

	/**
	 * Set OCL Constraints from PATH or CONTENT.
	 * 
	 * If type="PATH", OCL will be loaded from a Path file, 
	 * else if type="CONTENT", OCL will be loaded from OCL String content.
	 */
	public void setConstraints (String str, String type) throws IOException
	{
		if  (type=="PATH") 
		{
			String content = FileUtil.readFile(str);			
			this.oclstring = content;						
			this.oclpath = str;
			
		} else  if (type=="CONTENT") {
			
			String content = str;			
			this.oclstring = content;		
			this.oclpath=null;
		}
	}
		
	/**
	 * Set OCL Parser.
	 * 
	 * @param refmodel: RefOntoUML.Package
	 * @param umlPath^UML Model Path
	 */
	public void parse(RefOntoUML.Package refmodel, String umlPath) throws IOException, ParserException
	{
		if (oclstring !=null)
		{
			this.oclparser = new OCLParser(oclstring,refmodel, umlPath);			
		}
	}	

	public String getOCLString() 
	{ 
		return oclstring; 
	}
	
	public void setOCLString(String text)
	{
		oclstring = text;
	}
	
	public String getOCLPath() 
	{ 
		return oclpath; 
	}
	
	public void setOCLPath (String path)
	{
		oclpath = path;
	}
		
	public OCLParser getOCLParser ()
	{
		return oclparser;
	}
}

