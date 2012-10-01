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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *	This class is used as a File utility.
 *  
 * 	@author John Guerson 
 *  @author Tiago Sales 
 *  @author Lucas Thom    
 */

public class FileUtil {

	/**
	 * Copy String to a File.
	 * 
	 * @param content: String content to be copied to the File.
	 * @param FilePath: File Absolute Path.
	 * 
	 * @throws IOException
	 */
	public static void copyStringToFile(String content, String FilePath) throws IOException
	{
		FileWriter fstream = new FileWriter(FilePath);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(content);
		out.close();
	}
}
