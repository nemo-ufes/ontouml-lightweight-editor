package br.ufes.inf.nemo.move.ui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author John Guerson
 */

public class Extractor {

	public static String alloyAnalyzerJAR() throws IOException
	{
		String destFolderPath = Extractor.class.getProtectionDomain().getCodeSource().getLocation().getPath();		
		File alloyJarFile = new File(destFolderPath + "alloy4.2.jar");
		if (alloyJarFile.exists()) return alloyJarFile.getAbsolutePath();
				
		// Copy "alloy4.2.jar" 
		InputStream is = Extractor.class.getClassLoader().getResourceAsStream("alloy4.2.jar");
		if(is == null) 
			is = new FileInputStream("lib/"+"alloy4.2.jar");	
		
		OutputStream out = new FileOutputStream(alloyJarFile);
				
		// copy data flow -> MB x MB
		byte[] src = new byte[1024];
		int read = 0;
		while ((read = is.read(src)) != -1){
			out.write(src, 0, read);
		}
		is.close();
		out.flush();
		out.close();
		
		return alloyJarFile.getAbsolutePath();
	}
}
