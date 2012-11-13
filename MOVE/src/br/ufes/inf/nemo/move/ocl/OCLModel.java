package br.ufes.inf.nemo.move.ocl;

import java.io.IOException;

import br.ufes.inf.nemo.common.file.FileUtil;

/**
 * @author John Guerson
 */

public class OCLModel {
		
	private String oclmodel;		
	private String oclPath;	
		
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
			
		} else  if (type=="CONTENT") {
			
			String content = str;
			this.oclmodel = content;
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

