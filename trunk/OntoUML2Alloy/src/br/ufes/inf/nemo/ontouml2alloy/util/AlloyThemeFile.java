package br.ufes.inf.nemo.ontouml2alloy.util;

/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OntoUML2Alloy (OntoUML to Alloy Transformation).
 *
 * OntoUML2Alloy is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OntoUML2Alloy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OntoUML2Alloy; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import java.io.File;
import java.io.IOException;

/**
 *	This class is used to generate our standard Alloy theme File into destination directory path.
 *  
 * 	@authors Tiago Sales, John Guerson and Lucas Thom
 */

public class AlloyThemeFile {

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
			"</node>" + "\n\n" +

			"</view>" + "\n\n" +

			"</alloy>" + "\n\n";
		
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
	
}
