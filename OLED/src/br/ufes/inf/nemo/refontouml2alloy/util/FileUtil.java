package br.ufes.inf.nemo.refontouml2alloy.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

	/** Copy String to a File. */
	public static void copyStringToFile(String content, String FilePath) throws IOException
	{
		FileWriter fstream = new FileWriter(FilePath);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(content);
		out.close();
	}
}
