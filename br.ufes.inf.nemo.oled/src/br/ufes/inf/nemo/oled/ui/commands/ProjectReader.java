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
package br.ufes.inf.nemo.oled.ui.commands;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.util.ModelHelper;
import br.ufes.inf.nemo.oled.util.OLEDSettings;

/**
 * Reads a model from a file. Models are stored and retrieved using
 * Serialization.
 * 
 * @author Antognoni Albuquerque
 */
public final class ProjectReader extends FileHandler {

	private static ProjectReader instance = new ProjectReader();

	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static ProjectReader getInstance() {
		return instance;
	}

	/**
	 * Reads a UmlProject object from a file.
	 * 
	 * @param file
	 *            the file
	 * @return the UmlProject object
	 * @throws IOException
	 *             if I/O error occurred
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<Object> readProject(File file) throws IOException, ClassNotFoundException {
		
		// first element is UmlProject, the second the OCL String content.
		ArrayList<Object> list = new ArrayList<Object>();
		
		boolean modelLoaded = false, projectLoaded = false, constraintLoaded = false;
		String constraintContent = new String();
		ZipFile inFile = new ZipFile(file);	
		
		//Read the model and the project file 
		Resource resource = ModelHelper.createResource();
		UmlProject project = null;
		
		@SuppressWarnings("unchecked")
		Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) inFile.entries();
		   
		ZipEntry entry;
		while(entries.hasMoreElements()) {
			entry = entries.nextElement();
			if(entry.getName().equals(OLEDSettings.MODEL_DEFAULT_FILE.getValue()) && !modelLoaded)
			{
				InputStream in = inFile.getInputStream(entry);
				resource.load(in, Collections.EMPTY_MAP);
				in.close();
				modelLoaded = true;
			}
			else if (entry.getName().equals(OLEDSettings.PROJECT_DEFAULT_FILE.getValue()) && !projectLoaded)
			{
				InputStream in = inFile.getInputStream(entry);
				ObjectInputStream oin = new ObjectInputStream(in);
				project = (UmlProject) oin.readObject(); 
				in.close();
				projectLoaded = true;
			}
			else if (entry.getName().equals(OLEDSettings.OCL_DEFAULT_FILE.getValue()) && !constraintLoaded)
			{
				InputStream is = inFile.getInputStream(entry);
								
				byte[] b = new byte[is.available()];
				is.read(b);
				constraintContent= new String(b);
					
				is.close();
				constraintLoaded = true;
			}
		}
		
		inFile.close();
		
		if(!projectLoaded || !modelLoaded)
			throw new IOException("Project or model file not loaded.");
		
		project.setResource(resource);
		
		list.add(project);
		list.add(constraintContent);
		
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getSuffix() {
		return ".oled";
	}
}
