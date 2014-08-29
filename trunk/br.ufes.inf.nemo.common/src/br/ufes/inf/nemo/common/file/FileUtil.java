package br.ufes.inf.nemo.common.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *	This class is used as a File utility.
 */

public class FileUtil {

	/**
	 * Copy String to a File.
	 * 
	 * @param content: String content to be copied to the File.
	 * @param FilePath: File Absolute Path.
	 * 
	 * @throws IOException
	 */
	@Deprecated
	public static void copyStringToFile(String content, String FilePath) throws IOException
	{
		File file = createFile(FilePath);
		FileWriter fw = new FileWriter(file,false);
		PrintWriter pWriter = new PrintWriter(new BufferedWriter(fw));		
		pWriter.append(content);		
		pWriter.close();
		fw.close();		
	}
	
    /** Procedure for creating a File */	
    public static File createFile (String path) 
    {    	
		File file = new File(path);		
		if (!file.exists()) {			
			try{
				file.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}			
		}
		return file;
	}
	
    public static String readFile (String filePath) throws IOException
	{
		String result = new String();
		
		FileInputStream fstream = new FileInputStream(filePath);			
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;			
		while ((strLine = br.readLine()) != null)   
		{
			result += strLine+"\n";
		}
		in.close();		
		return result;
	}
    
    public static void writeToFile (String content, String filePath) throws IOException
	{
		File file = createFile(filePath);
		FileWriter fw = new FileWriter(file,false);
		PrintWriter pWriter = new PrintWriter(new BufferedWriter(fw));		
		pWriter.println(content);		
		pWriter.close();
		fw.close();						
	}
}
