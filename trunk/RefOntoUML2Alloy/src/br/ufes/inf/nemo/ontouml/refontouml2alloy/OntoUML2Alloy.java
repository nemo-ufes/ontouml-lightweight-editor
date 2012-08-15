package br.ufes.inf.nemo.ontouml.refontouml2alloy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import edu.mit.csail.sdg.alloy4whole.SimpleGUI_custom;

import RefOntoUML.Model;
import RefOntoUML.PackageableElement;

public class OntoUML2Alloy {

	//Path to the default theme 
	public static String themePath = "alloy/simulation.thm";
	//Path to the Alloy output specification
	public static String alsPath = "out.als";
	//Temp dir to put some files(provided by OLED)
	public static String dirPath = "";
	//Aux variable to call Alloy Analizer
	public static String[] filenames = { "", "" };

	//Method used to call the transformation
	public static boolean transformToAlloyFile(Model m,
			ArrayList<PackageableElement> elements, String alloyFilePath,
			String themeFilePath) throws Exception {

		alsPath = alloyFilePath;
		themePath = themeFilePath;

		//Copy alloy4.2-rc.jar into OLED temp dir (TODO: Think in a way to run it internally)
		InputStream is = OntoUML2Alloy.class.getClassLoader().getResourceAsStream("alloy4.2-rc.jar");
		if(is == null)
			is = new FileInputStream("lib/alloy4.2-rc.jar");
		OutputStream out = new FileOutputStream(new File(dirPath + "\\alloy4.2-rc.jar"));
		//MB per MB
		byte[] src = new byte[1024];
		int read = 0;
		while ((read = is.read(src)) != -1) {
			out.write(src, 0, read);
		}		
		is.close();
		out.flush();
		out.close();
		
		// Copy theme into dir temp path
		InputStream is2 = OntoUML2Alloy.class.getClassLoader().getResourceAsStream("standart_theme.thm");
		if(is2 == null) is2 = new FileInputStream("alloy/standart_theme.thm");		
		OutputStream out2 = new FileOutputStream(new File(dirPath + "\\standart_theme.thm"));
		src = new byte[1024];
		read = 0;
		while ((read = is2.read(src)) != -1) {
			out2.write(src, 0, read);
		}
		is2.close();
		out2.flush();
		out2.close();		
		
		//Modules that are used in generated Alloy code
		try {
			copyfile(AuxFiles.world_structure, dirPath
					+ File.separator + "world_structure.als");
			copyfile(AuxFiles.ontological_properties, dirPath
					+ File.separator + "ontological_properties.als");
		} catch (Exception e) {
			return false;
		}

		//Here the transformation begins
		Reader.init(m);
		
		//param to call SimpleGUI 
		filenames[0] = alsPath;
		filenames[1] = themePath;		

		//Open Alloy Analyzer
		SimpleGUI_custom.main(OntoUML2Alloy.filenames);

		return true;
	}

	//Aux function
	private static void copyfile(String srFile, String dtFile) {
		try {
			// Create file
			FileWriter fstream = new FileWriter(dtFile);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(srFile);
			// Close the output stream
			out.close();
			System.out.println("Done!");
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

}