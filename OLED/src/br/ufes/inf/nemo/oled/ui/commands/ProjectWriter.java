/**
 * Copyright 2011 NEMO
 *
 * This file is part of OLED.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.ui.commands;

import java.awt.Component;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.oled.model.UmlProject;

/**
 * This class writes the current model and diagram data to an XML file.
 * 
 * @author Antognoni Albuquerque
 * @version 1.0
 */
public final class ProjectWriter extends FileWriter {

	private static ProjectWriter instance = new ProjectWriter();

	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static ProjectWriter getInstance() {
		return instance;
	}

	/**
	 * Writes the specified UmlProject to a file.
	 * 
	 * @param comp
	 *            the parent component for the confirmation dialog
	 * @param file
	 *            the file
	 * @param project
	 *            the model
	 * @return the file that was actually written
	 * @throws IOException
	 *             if error occurred
	 */
	public File writeProject(Component comp, File file, UmlProject project) throws IOException {

		File outFile = getFileWithExtension(file);
		FileOutputStream dest = new FileOutputStream(outFile);
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
		
		//Save the model as a resource inside the file
		ZipEntry modelEntry = new ZipEntry(getFileName(FileType.MODEL));			
		out.putNextEntry(modelEntry);
		Resource resource = project.getResource();
		resource.save(out, Collections.EMPTY_MAP);
		out.closeEntry();
		
		//Save the project a.k.a the diagrams inside the file
		ZipEntry projectEntry = new ZipEntry(getFileName(FileType.PROJECT));
		out.putNextEntry(projectEntry);
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(project); 
		oos.flush();
		out.closeEntry();
		
		//Flushes and closes the zip file
		out.flush();
		out.finish();
		out.close();

		return outFile;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getSuffix() {
		return ".oled";
	}


}
