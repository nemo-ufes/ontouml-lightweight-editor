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

	public static String dirPath = "";	
	public static String alsPath = "";
	public static String themePath = "";	
	public static String[] argsAnalyzer = {"",""};

	/* ============================================================================*/
	
	public static boolean transformToAlloyFile (Model refmodel, String destinationPath, String alloyFileName, String themeFileName) throws Exception 
	{
		dirPath = destinationPath;
		
		if (alloyFileName == null || alloyFileName == "") alloyFileName = "simulation.als";
		alsPath = dirPath + "\\"+ alloyFileName;
		File alloyFile = new File(alsPath);
		alloyFile.deleteOnExit();
		
		if (themeFileName == null || themeFileName == "") themeFileName = "standart_theme.thm";
		themePath = dirPath + "\\" + themeFileName;
		File themeFile = new File(themePath);
		themeFile.deleteOnExit();
		
		// Parameters to call SimpleGUI 
		argsAnalyzer[0] = alsPath;
		argsAnalyzer[1] = themePath;
				
		/* ============================================================================*/
		
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
		
		/* ============================================================================*/
		
		// Copy standart_theme.thm into directory Path		
		InputStream is2 = OntoUML2Alloy.class.getClassLoader().getResourceAsStream(themeFileName);
		if(is2 == null) is2 = new FileInputStream("src/standart_theme.thm");		
		OutputStream out2 = new FileOutputStream(new File(themePath));
		
		// Copy data flow -> MB x MB
		src = new byte[1024];
		read = 0;
		while ((read = is2.read(src)) != -1) {
			out2.write(src, 0, read);
		}
		is2.close();
		out2.flush();
		out2.close();
		
		/* ============================================================================*/
		
		// Modules that are used in generated Alloy code
		try {
			File lib1File = new File(dirPath + File.separator + "world_structure.als");
			File lib2File = new File(dirPath + File.separator + "ontological_properties.als");
			lib1File.deleteOnExit();
			lib2File.deleteOnExit();			
			copyfile(LibraryFiles.world_structure, dirPath + File.separator + "world_structure.als");
			copyfile(LibraryFiles.ontological_properties, dirPath + File.separator + "ontological_properties.als");
		
		} catch (Exception e) {
			return false;
		}		
		
		/* ============================================================================*/
		
		// Here the transformation begins...
		Reader.init(refmodel);			
		
		/* ============================================================================*/
		
		// Open Alloy Analyzer
		SimpleGUI_custom.main(OntoUML2Alloy.argsAnalyzer);

		return true;
	}
		
	private static void copyfile(String srFile, String dtFile) {
		try {
			FileWriter fstream = new FileWriter(dtFile);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(srFile);
			out.close();
			System.out.println("Done!");
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}	
	
}