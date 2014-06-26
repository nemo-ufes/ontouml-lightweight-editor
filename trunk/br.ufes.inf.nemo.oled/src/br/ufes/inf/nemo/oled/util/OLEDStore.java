/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
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
package br.ufes.inf.nemo.oled.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import br.ufes.inf.nemo.oled.model.UmlProject;

public class OLEDStore {

	enum FileType
	{
		MODEL,
		PROJECT,
		SIMULATION,
		SIMULATION_OUTPUT
	}
	
	/**
	 * Returns an input stream to the model file.
	 */
	public static InputStream getFile(String path, FileType type) throws IOException
	{
		ZipFile zf = null;
		if(!path.endsWith(getStoreExtension()))
			throw new IOException("File type not supported");
			
		try {
			
			zf = new ZipFile(path);
			ZipEntry entry = zf.getEntry(getFileName(type));
			InputStream in = zf.getInputStream(entry);
						
			return in;
			
		} finally {
			zf.close();
		}
	}
	
	public static String getFileName(FileType type)
	{
		switch (type) {
		case MODEL: 
			return "model.ontouml";
		case PROJECT: 
			return "project.dat";
		case SIMULATION: 
			return "simulation.als";
		case SIMULATION_OUTPUT: 
			return "simulation_output.xml";
		default:
			return null;
		}
	}
	
	public static String getStoreExtension()
	{
		return ".oled";
	}

	
	public static void save(UmlProject project, String path) {
		
		
	}
	
}
