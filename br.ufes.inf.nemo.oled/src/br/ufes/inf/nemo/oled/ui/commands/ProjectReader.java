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
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

import br.ufes.inf.nemo.oled.Main;
import br.ufes.inf.nemo.oled.model.OCLDocument;
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
	@SuppressWarnings("unused")
	public ArrayList<Object> readProject(File file) throws IOException, ClassNotFoundException {
		
		// first element is UmlProject, the second the OCL String content.
		ArrayList<Object> list = new ArrayList<Object>();
		
		boolean modelLoaded = false, projectLoaded = false, constraintLoaded = false;
		ArrayList<OCLDocument> constraintContent = new ArrayList<OCLDocument>();
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
				Main.printOutLine("Loading model XMI information from OLED file...");
				InputStream in = inFile.getInputStream(entry);
				
				/**Load options that significantly improved the performance of loading EMF Model instances (by Tiago)*/
				Map<Object,Object> loadOptions = ((XMLResourceImpl)resource).getDefaultLoadOptions();
				loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
				loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
				resource.load(in,loadOptions);
				
				//resource.load(in, Collections.EMPTY_MAP);

				in.close();
				modelLoaded = true;
			}
			else if (entry.getName().equals(OLEDSettings.PROJECT_DEFAULT_FILE.getValue()) && !projectLoaded)
			{
				Main.printOutLine("Loading project DAT information from OLED file...");
				InputStream in = inFile.getInputStream(entry);
				ObjectInputStream oin = new ObjectInputStream(in);
				project = (UmlProject) oin.readObject(); 
				in.close();
				projectLoaded = true;
			}
			else if (entry.getName().contains("ocl"))
			{
				Main.printOutLine("Loading constraints information from OLED file...");
				InputStream is = inFile.getInputStream(entry);
								
				byte[] b = new byte[is.available()];
				is.read(b);
				OCLDocument oclDoc = new OCLDocument();
				oclDoc.setName(entry.getName().replace(".ocl",""));
				oclDoc.addContent(new String(b));
				constraintContent.add(oclDoc);
					
				is.close();
				constraintLoaded = true;
			}
		}
		
		inFile.close();
		
		if(!projectLoaded || !modelLoaded)
			throw new IOException("Failed to load OLED Project!");
		
		project.setResource(resource);
		
		list.add(project);
		list.addAll(constraintContent);
		
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getSuffix() {
		return ".oled";
	}
}
