package br.ufes.inf.nemo.oled.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

/**
 * @author John Guerson
 *  
 */

public class AlloyExtractorUtil {

	public static String alloyAnalyzerJAR() throws IOException
	{
		//Tony's Edit: Made these changes to correct an error while copying the alloy file to folders with space " " in the path.
		String destFolderPath = AlloyExtractorUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();	
		destFolderPath += "alloy4.2.jar";
		String alloyPath = URLDecoder.decode(destFolderPath, "UTF-8");
		
		File alloyJarFile = new File(alloyPath);
		if (alloyJarFile.exists()) return alloyJarFile.getAbsolutePath();
				
		// Copy "alloy4.2.jar" 
		InputStream is = AlloyExtractorUtil.class.getClassLoader().getResourceAsStream("alloy4.2.jar");
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
