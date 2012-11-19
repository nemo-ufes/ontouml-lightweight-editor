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

import br.ufes.inf.nemo.move.ocl.constraint.OCLConstraintModel;

/**
 * @author John Guerson
 */

public class OCLConstraintListModel {
		
	private String oclstring;	
	private String oclpath;	
	private ArrayList<String> oclstringlist;	
	private OCLParser oclparser;
	
	private ArrayList<OCLConstraintModel> oclListModel = new ArrayList<OCLConstraintModel>();
		
	/**
	 * Constructor.
	 * 
	 * @param oclListModel
	 */
	public OCLConstraintListModel(ArrayList<OCLConstraintModel> oclListModel)
	{
		this();
		
		this.oclListModel = oclListModel;		
	}
	
	/**
	 * Constructor.
	 */
	public OCLConstraintListModel()
	{
		
	}
	
	/**
	 * Set OCL Constraint List from PATH or CONTENT.
	 * 
	 * If type="PATH", OCL will be loaded from a Path file, 
	 * else if type="CONTENT", OCL will be loaded from OCL String content.
	 */
	public void setConstraintList (String str, String type) throws IOException
	{
		if  (type=="PATH") 
		{
			String content = FileUtil.readFile(str);			
			this.oclstring = content;			
			this.oclstringlist = divideIntoConstraints(oclstring);
			for(String ct: oclstringlist) this.oclListModel.add(new OCLConstraintModel(ct));
			this.oclpath = str;
			
		} else  if (type=="CONTENT") {
			
			String content = str;
			this.oclstring = content;			
			this.oclstringlist = divideIntoConstraints(oclstring);
			for(String ct: oclstringlist) this.oclListModel.add(new OCLConstraintModel(ct));
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
	
	/**
	 * This method is used to separate textually the constraints from 'oclmodel'.
	 * 
	 * @param oclmodel
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> divideIntoConstraints(String oclmodel) throws IOException
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
	
	public String getOCLString() 
	{ 
		return oclstring; 
	}
	
	public String getOCLPath() 
	{ 
		return oclpath; 
	}
	
	public ArrayList<String> getOCLStringList()
	{
		return oclstringlist;
	}
	
	public OCLParser getOCLParser ()
	{
		return oclparser;
	}	
	
	public ArrayList<OCLConstraintModel> getConstraintModelList()
	{
		return oclListModel;
	}
}

