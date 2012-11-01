package br.ufes.inf.nemo.move.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import br.ufes.inf.nemo.move.ui.TheFrame;

/**
 * Copy "alloy4.2.jar" to the destination directory.
 * 
 * @author John Guerson
 */

public class AlloyJARExtractor {

	public static void extractAlloyJaRTo (String FileNameWithExtension, String dirPath) throws IOException
	{
		// Copy "alloy4.2.jar" to the destination directory 
		InputStream is = TheFrame.class.getClassLoader().getResourceAsStream(FileNameWithExtension);
		if(is == null) is = new FileInputStream("lib/"+FileNameWithExtension);
		File alloyJarFile = new File(dirPath + FileNameWithExtension);
		alloyJarFile.deleteOnExit();
		OutputStream out = new FileOutputStream(alloyJarFile);

		// copy data flow -> MB x MB
		byte[] src = new byte[1024];
		int read = 0;
		while ((read = is.read(src)) != -1) {
			out.write(src, 0, read);
		}		
		is.close();
		out.flush();
		out.close();
	}
}
