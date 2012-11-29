package br.ufes.inf.nemo.move.mvc.model;

import java.io.IOException;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.ocl2alloy.parser.OCLParser;

/**
 * This class represents an OCL Model.
 * 
 * @author John Guerson
 */

public class OCLModel {
		
	/** All OCL Constraints in one single String. This is our "model.*/
	private String oclstring;
	
	/** Absolute path of ocl document. */
	private String oclpath;	
	
	/** OCL Parser . */
	private OCLParser oclparser;
		
	/**
	 * Creates an empty ocl model.
	 */
	public OCLModel() {}
		
	/**
	 * Clear this model.
	 */
	public void clearModel()
	{		
		oclstring="";
		oclpath="";
		oclparser=null;
	}

	/**
	 * Set OCL Constraints from a file Path or string Content.
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
	 * @param oclparser
	 */
	public void setParser(OCLParser oclparser) { this.oclparser = oclparser; }
	
	/** Get OCL single String containing all Constraints. */
	public String getOCLString() { return oclstring; }
	
	/**
	 * Set OCL single String containing all Constraints.
	 * 
	 * @param text
	 */
	public void setOCLString(String text) { oclstring = text; }
	
	/** Get Absolute path of OCL document.	*/
	public String getOCLPath() { return oclpath; }
	
	/**
	 * Set Absolute path of OCL document.
	 * 
	 * @param path
	 */
	public void setOCLPath (String path) { oclpath = path; }
		
	/** Get OCL Parser. */
	public OCLParser getOCLParser () { return oclparser; }
}

