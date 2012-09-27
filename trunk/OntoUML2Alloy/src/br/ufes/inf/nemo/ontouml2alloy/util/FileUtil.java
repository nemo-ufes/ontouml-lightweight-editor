package br.ufes.inf.nemo.ontouml2alloy.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *	This class is used as a File utility.
 *  
 *  @author John Guerson    
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
	public static void copyStringToFile(String content, String FilePath) throws IOException
	{
		FileWriter fstream = new FileWriter(FilePath);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(content);
		out.close();
	}
}
