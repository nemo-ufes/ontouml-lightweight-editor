package br.ufes.inf.nemo.move.output;

import java.io.File;

/**
 * @author John Guerson
 */

public class OutputModel {

	private String alsPath;
	public String alsmodelName;
	public static String alsOutDirectory;
			
	private String umlPath;
	public String umlmodelName;
	public static String umlOutDirectory;

	/**
	 * Constructor.
	 * 
	 * @param alloyPath
	 * @param umlpath
	 */
	public OutputModel(String alloyPath, String umlpath)
	{
		this();
		
		setAlloy(alloyPath);
		setUMLPath(umlpath);
	}	
	
	/**
	 * COnstructor.
	 */
	public OutputModel()
	{
		
	}	
	
	public void setAlloy(String alloyPath)
	{			
		this.alsPath = alloyPath;
		
		alsOutDirectory = alsPath.substring(0, alsPath.lastIndexOf(File.separator)+1);		
		alsmodelName = alsPath.substring(alsPath.lastIndexOf(File.separator)+1,alsPath.length()).replace(".als","");		
	}

	public String getAlloyPath() 
	{ 
		return this.alsPath;	
	}	
	
	public void setUMLPath(String umlpath)
	{			
		this.umlPath = umlpath;
		
		umlOutDirectory = umlPath.substring(0, umlPath.lastIndexOf(File.separator)+1);		
		umlmodelName = umlPath.substring(umlPath.lastIndexOf(File.separator)+1,umlPath.length()).replace(".uml","");		
	}

	public String getUMLPath() 
	{ 
		return this.umlPath; 
	}	

	
}
