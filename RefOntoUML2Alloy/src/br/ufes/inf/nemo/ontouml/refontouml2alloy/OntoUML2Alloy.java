package br.ufes.inf.nemo.ontouml.refontouml2alloy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;

import edu.mit.csail.sdg.alloy4whole.SimpleGUI_custom;

import RefOntoUML.Model;
import RefOntoUML.PackageableElement;

public class OntoUML2Alloy {

	public static String themePath = "alloy/simulation.thm";
	public static String alsPath = "out.als";
	public static String dirPath = "";
	public static String[] filenames = { "" };

	public static boolean transformToAlloyFile(Model m,
			ArrayList<PackageableElement> elements, String alloyFilePath,
			String themeFilePath) throws Exception {

		alsPath = alloyFilePath;
		themePath = themeFilePath;

		InputStream is = OntoUML2Alloy.class.getClassLoader().getResourceAsStream("alloy4.2-rc.jar");
		if(is == null)
			is = new FileInputStream("lib/alloy4.2-rc.jar");
		OutputStream out = new FileOutputStream(new File(dirPath + "\\alloy4.2-rc.jar"));
		byte[] src = new byte[1024];

		int read = 0;
		while ((read = is.read(src)) != -1) {
			out.write(src, 0, read);
		}
		
		is.close();
		out.flush();
		out.close();

		System.out.println(dirPath);
		
		try {
			copyfile(AuxFiles.world_structure, dirPath
					+ "\\world_structure.als");
			copyfile(AuxFiles.ontological_properties, dirPath
					+ "\\ontological_properties.als");
			copyfile(AuxFiles.alloyTheme, themeFilePath);
		} catch (Exception e) {
			return false;
		}

		Reader.init(m);
		filenames[0] = alsPath;

		SimpleGUI_custom.main(OntoUML2Alloy.filenames);

		return true;
	}

	public static void call(Model m) throws Exception {

		// Call inicialization of transformation Class
		copyfile(AuxFiles.world_structure, dirPath + "world_structure.als");
		copyfile(AuxFiles.ontological_properties, dirPath
				+ "ontological_properties.als");
		Reader.init(m);
		String[] filenames = { alsPath };
		SimpleGUI_custom.main(filenames);
	}

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