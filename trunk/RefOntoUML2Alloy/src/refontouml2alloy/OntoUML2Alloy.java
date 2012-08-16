package refontouml2alloy;

/**
 * Copyright 2012 NEMO (http://nemo.inf.ufes.br/en)
 * 
 * @author Tiago Prince Salles
 * @author Lucas Thom
 *  
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import refontouml2alloy.util.LibraryFiles;

import edu.mit.csail.sdg.alloy4whole.SimpleGUI_custom;

import RefOntoUML.Model;
import RefOntoUML.PackageableElement;

public class OntoUML2Alloy {

	// Path to Default Theme {value assigned by OLED::util::VerificationHelper.java} 
	public static String themePath = "standart_theme.thm";
	
	// Path to Alloy Output Specification {value assigned by OLED::util::VerificationHelper.java}
	public static String alsPath = "simulation.als";
	
	// Temporary directory for files {value assigned by OLED::util::VerificationHelper.java}
	public static String dirPath = "";
	
	// Array for calling Alloy Analyzer {alsFile,themePath}
	public static String[] filenames = { "", "" };

	/** 
	 * This Method is used to call the transformation.
	 * 
	 * @param m : RefOntoUML.Model
	 * @param elements : -
	 * @param alloyFilePath: Alloy file
	 * @param themeFilePath: Alloy standard theme file
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean transformToAlloyFile (Model m, ArrayList<PackageableElement> elements, String alloyFilePath, String themeFilePath) throws Exception {

		alsPath = alloyFilePath;
		themePath = themeFilePath;

		// Copy alloy4.2-rc.jar into OLED temporary directory
		InputStream is = OntoUML2Alloy.class.getClassLoader().getResourceAsStream("alloy4.2-rc.jar");
		if(is == null) is = new FileInputStream("lib/alloy4.2-rc.jar");
		OutputStream out = new FileOutputStream(new File(dirPath + "\\alloy4.2-rc.jar"));

		// copy data flow -> MB x MB
		byte[] src = new byte[1024];
		int read = 0;
		while ((read = is.read(src)) != -1) {
			out.write(src, 0, read);
		}		
		is.close();
		out.flush();
		out.close();
		
		// Copy standart_theme.thm into temporary directory
		InputStream is2 = OntoUML2Alloy.class.getClassLoader().getResourceAsStream("standart_theme.thm");
		if(is2 == null) is2 = new FileInputStream("alloy/standart_theme.thm");		
		OutputStream out2 = new FileOutputStream(new File(dirPath + "\\standart_theme.thm"));
		
		// Copy data flow -> MB x MB
		src = new byte[1024];
		read = 0;
		while ((read = is2.read(src)) != -1) {
			out2.write(src, 0, read);
		}
		is2.close();
		out2.flush();
		out2.close();		
		
		// Modules that are used in generated Alloy code
		try {
			copyfile(LibraryFiles.world_structure, dirPath + File.separator + "world_structure.als");
			copyfile(LibraryFiles.ontological_properties, dirPath + File.separator + "ontological_properties.als");
		} catch (Exception e) {
			return false;
		}

		// Here the transformation begins...
		Reader.init(m);
		
		// Parameters to call SimpleGUI 
		filenames[0] = alsPath;
		filenames[1] = themePath;		

		// Open Alloy Analyzer
		SimpleGUI_custom.main(OntoUML2Alloy.filenames);

		return true;
	}

	private static void copyfile(String srFile, String dtFile) {
		try {
			FileWriter fstream = new FileWriter(dtFile);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(srFile);
			out.close();
			System.out.println("Done!");
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

}