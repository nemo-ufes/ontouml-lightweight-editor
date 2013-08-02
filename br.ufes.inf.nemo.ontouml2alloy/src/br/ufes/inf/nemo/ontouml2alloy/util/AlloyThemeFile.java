package br.ufes.inf.nemo.ontouml2alloy.util;

import java.io.File;
import java.io.IOException;

import br.ufes.inf.nemo.common.file.FileUtil;

/**
 *	Generates our standard Alloy theme File into destination directory path.
 *  
 * 	@authors Tiago Prince, John Guerson and Lucas Thom
 */

public class AlloyThemeFile {

	/**
	 * Generate "standart_theme.thm" into the directory path "dirPath".
	 *  
	 * @param dirPath
	 * @throws IOException
	 */
	public static void generateAlloyThemeFile (String dirPath) throws IOException
	{		
		File themeFile = new File(dirPath + "standart_theme.thm");		
		themeFile.deleteOnExit();		
		FileUtil.copyStringToFile(standart_theme, dirPath + "standart_theme.thm");
	}
	
	
	/** Content of the alloy standard theme file. */	
	public static String standart_theme = 
			
			"<?xml version=\"1.0\"?>" + "\n" +					
			"<alloy>"+ "\n\n"+
					
			"<view>"+ "\n\n"+
			
			"<projection> <type name=\"World\"/> </projection>"+ "\n\n"+
			
			"<defaultnode/>"+ "\n\n"+
			
			"<defaultedge/>"+ "\n\n"+
			
			"<node>"+ "\n" +
			"	<type name=\"Int\"/>" + "\n" +
			"   <type name=\"boolean/Bool\"/> " + "\n" + 
			"   <type name=\"boolean/False\"/> " + "\n" +
			"   <type name=\"boolean/True\"/> " + "\n" +
			"   <type name=\"ordering/Ord\"/> " + "\n" +
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
			"	<set name=\"$elements_existence_w\" type=\"univ\"/>" + "\n" +			
			"   <set name=\"$antirigidity_x\" type=\"univ\"/> " +
			"   <set name=\"$antirigidity_x&apos;\" type=\"univ\"/> " + 
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
			"   <set name=\"$dataTypeVisibility\" type=\"univ\"/> "  + "\n" +
			"   <set name=\"$enumerationVisibility\" type=\"univ\"/> " + "\n" +
			"   <set name=\"$primitiveTypeVisibility\" type=\"univ\"/>" + "\n" +
			"</node>" + "\n\n" +

			"<edge visible=\"no\" attribute=\"yes\"> " + "\n" +
			
			"</edge>" + "\n" +
			
			"</view>" + "\n\n" +

			"</alloy>" + "\n\n";	
}
