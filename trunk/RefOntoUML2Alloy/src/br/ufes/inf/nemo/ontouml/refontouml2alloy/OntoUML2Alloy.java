package br.ufes.inf.nemo.ontouml.refontouml2alloy;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import edu.mit.csail.sdg.alloy4whole.SimpleGUI;

import RefOntoUML.Model;
import RefOntoUML.PackageableElement;


public class OntoUML2Alloy {
	
	public static String themePath = "alloy/simulation.thm";
	public static String alsPath = "out.als";
	public static String dirPath = "";
	
	public static boolean transformToAlloyFile(Model m, ArrayList<PackageableElement> elements, String alloyFilePath, String themeFilePath) throws Exception {
		
		alsPath = alloyFilePath;
		themePath = themeFilePath;
		
		try {
			copyfile(AuxFiles.world_structure, dirPath + "\\world_structure.als");
			copyfile(AuxFiles.ontological_properties, dirPath + "\\ontological_properties.als");
			copyfile(AuxFiles.alloyTheme, themeFilePath);
		}catch (Exception e) {
			return false;
		}
		
		Transformer.init(m);
		String[] filenames = {alsPath};
		
		try {
			SimpleGUI.main(filenames);
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static void call(Model m) throws Exception {
		
		//Call inicialization of transformation Class
		copyfile(AuxFiles.world_structure, dirPath + "world_structure.als");
		copyfile(AuxFiles.ontological_properties, dirPath + "ontological_properties.als");
		Transformer.init(m);
		String[] filenames = {alsPath};
		SimpleGUI.main(filenames);
	}
	
	private static void copyfile(String srFile, String dtFile){
		try{
			//Create file 
			FileWriter fstream = new FileWriter(dtFile);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(srFile);
			//Close the output stream
			out.close();
			System.out.println("Done!");
		  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
		  }
	}

}
