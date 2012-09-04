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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import RefOntoUML.Model;
import edu.mit.csail.sdg.alloy4whole.SimpleGUI_custom;

public class OntoUML2Alloy {
		
	public static String dirPath;	
	public static String alsPath;	
	public static String[] argsAnalyzer = {"",""};	
	public static boolean relatorRuleFlag = true;	
	public static boolean weakSupplementationRuleFlag = true;
	
	public static boolean Transformation (Model refmodel, String destinationPath, boolean RelatorRuleFlag, boolean WeakSupplementationRuleFlag) throws Exception 
	{
		dirPath = destinationPath;

		if (refmodel.getName()=="" || refmodel.getName()==null) 
			alsPath = dirPath + "\\"+ "Model" + ".als";
		else
			alsPath = dirPath + "\\"+ refmodel.getName() + ".als";		
		File f = new File(alsPath);
		f.deleteOnExit();	
		
		relatorRuleFlag = RelatorRuleFlag;
		weakSupplementationRuleFlag = WeakSupplementationRuleFlag;
		
		// "alloy4.2-rc.jar" 
		InputStream is = OntoUML2Alloy.class.getClassLoader().getResourceAsStream("alloy4.2-rc.jar");
		if(is == null) is = new FileInputStream("lib/alloy4.2-rc.jar");
		File alloyJarFile = new File(dirPath + "\\" + "alloy4.2-rc.jar");
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

		AlloyThemeFile.generateAlloyThemeFile(dirPath);	

		OntoLibraryFiles.generateLibraryFiles(dirPath);	
		
		Reader.init(refmodel);
		
		argsAnalyzer[0] = alsPath;
		argsAnalyzer[1] = dirPath + "\\" + "standart_theme.thm"	;	
		SimpleGUI_custom.main(OntoUML2Alloy.argsAnalyzer);

		return true;
	}	
}
