package br.ufes.inf.nemo.refontouml2alloy;

/**
 * 
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * licence terms.
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

import RefOntoUML.Model;
import edu.mit.csail.sdg.alloy4whole.SimpleGUI_custom;

public class OntoUML2Alloy {

	/* ============================================================================*/
	
	/** Path of the Destination Directory. */
	public static String dirPath = "";
	
	/** Alloy File. (Example: simulation.als) */
	public static String alsPath = "";	
	
	/** Alloy Theme File. (Example: standart_theme.thm) */
	public static String themePath = "";
	
	/** Parameters to call the Alloy Analyzer. */
	public static String[] argsAnalyzer = {"",""};

	/* ============================================================================*/
	
	/**
	 * This method is use to call the transformation From OLED Editor.
	 * 
	 * @param refmodel: RefOntoUML.Model
	 * @param destinationPath: Path of the Destination Directory
	 * @param alloyFileName: Alloy File  (Example: simulation.als)
	 * @param themeFileName: Alloy Theme File  (Example: standart_theme.thm)
	 * @return
	 * @throws Exception
	 */
	public static boolean Transformation (Model refmodel, String destinationPath, String alloyFile, String themeFile) throws Exception 
	{
		// dirPath Initialization
		dirPath = destinationPath;
		
		// alsPath Initialization
		if (alloyFile == null || alloyFile == "") alloyFile = "simulation.als";
		alsPath = dirPath + "\\"+ alloyFile;
		File f = new File(alsPath);
		f.deleteOnExit();
		
		// themePath Initialization
		if (themeFile == null || themeFile == "") themeFile = "standart_theme.thm";
		themePath = dirPath + "\\" + themeFile;
		File t = new File(themePath);
		t.deleteOnExit();
		
		// Parameters to call SimpleGUI 
		argsAnalyzer[0] = alsPath;
		argsAnalyzer[1] = themePath;		
		
		// Copy alloy4.2-rc.jar into directory Path
		InputStream is = OntoUML2Alloy.class.getClassLoader().getResourceAsStream("alloy4.2-rc.jar");
		if(is == null) is = new FileInputStream("lib/alloy4.2-rc.jar");
		File alloyJarFile = new File(dirPath + "\\alloy4.2-rc.jar");
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
						
		// Copy standart_theme.thm into directory Path		
		InputStream is2 = OntoUML2Alloy.class.getClassLoader().getResourceAsStream(themeFile);
		if(is2 == null) is2 = new FileInputStream("src/standart_theme.thm");		
		OutputStream out2 = new FileOutputStream(new File(themePath));
		
		// Copy data flow -> MB x MB
		byte[]src2 = new byte[1024];
		int read2 = 0;
		while ((read2 = is2.read(src2)) != -1) {
			out2.write(src2, 0, read2);
		}
		is2.close();
		out2.flush();
		out2.close();		
		
		// Copy the alloy modules into directory: world_structure.als and ontological_properties.als
		try 
		{
			File lib1File = new File(dirPath + File.separator + "world_structure.als");
			File lib2File = new File(dirPath + File.separator + "ontological_properties.als");
			
			lib1File.deleteOnExit();
			lib2File.deleteOnExit();	
			
			copyStringToFile(LibraryFiles.world_structure, dirPath + File.separator + "world_structure.als");
			copyStringToFile(LibraryFiles.ontological_properties, dirPath + File.separator + "ontological_properties.als");
			
		} catch (Exception e) {
			return false;
		}		
		
		// Here the transformation begins...
		Reader.init(refmodel);		
				
		// Open Alloy Analyzer
		SimpleGUI_custom.main(OntoUML2Alloy.argsAnalyzer);

		return true;
	}
		
	/* ============================================================================*/
	
	/** Copy String to a File */
	private static void copyStringToFile(String content, String FilePath) {
		try {
			FileWriter fstream = new FileWriter(FilePath);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(content);
			out.close();
			System.out.println("Done!");
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}	
	
}