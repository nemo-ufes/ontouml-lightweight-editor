package br.ufes.inf.nemo.move.ocl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
		
	public void clearPathAndString ()
	{		
		oclstring="";
		oclpath="";
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
	
	/**
	 * This method is used to separate textually the constraints from 'oclmodel'.
	 * 
	 * @param oclmodel
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private ArrayList<String> divideIntoConstraints(String oclmodel) throws IOException
	{  
		ArrayList<String> result = new ArrayList<String>();
		InputStream fstream = new ByteArrayInputStream(oclmodel.getBytes("UTF-8"));
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine=new String();	
		int countContext=0;		
		String constraint = new String();
		while ((strLine = br.readLine()) != null)   
		{			
			if (strLine.contains("context")) countContext++;
			
			if(!strLine.contains("context")) constraint += strLine+"\n";
			else if(strLine.contains("context") && countContext==1) constraint += strLine+"\n";
			else { result.add(constraint); constraint = strLine+"\n"; }			
		}
		result.add(constraint);		
		in.close();		
		return result;
	}
	
}

