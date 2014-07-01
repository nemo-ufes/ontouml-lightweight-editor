/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

import br.ufes.inf.nemo.oled.Main;

/**
 * @author John Guerson
 */
public class ExtractorUtil {

	public static String extractAlloyJar() throws IOException
	{
		//Tony's Edit: Made these changes to correct an error while copying the alloy file to folders with space " " in the path.
		String destFolderPath = ExtractorUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();	
		destFolderPath += "alloy4.2.jar";
		String alloyPath = URLDecoder.decode(destFolderPath, "UTF-8");
		
		File alloyJarFile = new File(alloyPath);
		if (alloyJarFile.exists()) return alloyJarFile.getAbsolutePath();
				
		// Copy "alloy4.2.jar" 
		InputStream is = ExtractorUtil.class.getClassLoader().getResourceAsStream("alloy4.2.jar");		
		if(is == null) is = new FileInputStream("lib/"+"alloy4.2.jar");		
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
		
		Main.printOutLine("Extracted: "+alloyJarFile.getAbsolutePath());
		return alloyJarFile.getAbsolutePath();
	}	
}
