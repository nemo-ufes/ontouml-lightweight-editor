package br.ufes.inf.nemo.refontouml2alloy.util;

import java.io.File;

public class AlloyThemeFile {

	public static String standart_theme = 
			
			"<?xml version=\"1.0\"?>" + "\n" +					
			"<alloy>"+ "\n\n"+
					
			"<view>"+ "\n\n"+
			
			"<projection> <type name=\"World\"/> </projection>"+ "\n\n"+
			
			"<defaultnode/>"+ "\n\n"+
			
			"<defaultedge/>"+ "\n\n"+
			
			"<node>"+ "\n" +
			"	<type name=\"Int\"/>" + "\n" +
			"	<type name=\"String\"/>" + "\n" + 
			"	<type name=\"World\"/>" + "\n" +
			"	<type name=\"seq/Int\"/>" + "\n" +
			"	<type name=\"world_structure/CounterfactualWorld\"/>" + "\n" +
			"	<type name=\"world_structure/CurrentWorld\"/>" + "\n" +
			"	<type name=\"world_structure/FutureWorld\"/>" + "\n" +
			"	<type name=\"world_structure/PastWorld\"/>" + "\n" +
			"	<type name=\"world_structure/TemporalWorld\"/>" + "\n" +   
			"</node>" + "\n\n" +

			"<node shape=\"Box\" color=\"Yellow\">" + "\n" +
			"	<type name=\"Object\"/>" + "\n" +
			"</node>" + "\n\n" +

			"<node shape=\"Ellipse\" color=\"Red\">" + "\n" +
			"	<type name=\"Property\"/>" + "\n" +
			"</node>" + "\n\n" +

			"<node shape=\"Hexagon\" color=\"Gray\">" + "\n"+
			"	<type name=\"DataType\"/>" + "\n" +
			"</node>" + "\n\n" +

			"<node showlabel=\"no\">" + "\n" +
			"	<set name=\"$all_elements_exists_w\" type=\"univ\"/>" + "\n" +
			"	<set name=\"exists\" type=\"DataType\"/>" + "\n" +
			"	<set name=\"exists\" type=\"Object\"/>" + "\n" +
			"</node>" + "\n\n" +

			"<node showlabel=\"no\" color=\"Red\">" + "\n" +
			"	<set name=\"exists\" type=\"Property\"/>" + "\n" +
			"</node>" + "\n\n" +

			"<node visible=\"no\" numberatoms=\"yes\">" + "\n" +
			"	<type name=\"univ\"/>" + "\n" +
			"</node>" + "\n\n" +

			"<node visible=\"yes\" showlabel=\"no\">" + "\n" +
			"	<set name=\"$visible\" type=\"univ\"/>" + "\n" +
			"</node>" + "\n\n" +

			"</view>" + "\n\n" +

			"</alloy>" + "\n\n";
		
	/** Generate "world_structure.als" and "ontological_properties.als" into the directory path "dirPath". */
	public static void generateAlloyThemeFile (String dirPath)
	{		
		File themeFile = new File(dirPath + File.separator + "standart_theme.thm");		
		themeFile.deleteOnExit();		
		FileUtil.copyStringToFile(standart_theme, dirPath + File.separator + "standart_theme.thm");
	}
	
}
