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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.util.ApplicationResources;

/**
 * Reads a model from a file. Models are stored and retrieved using
 * Serialization.
 * 
 * @author Wei-ju Wu
 */
public final class ModelReader extends FileHandler {

	private static ModelReader instance = new ModelReader();

	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static ModelReader getInstance() {
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
	 */
	public UmlProject readModel(File file) throws IOException {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			in = new ObjectInputStream(bis);
			//UmlProject project = (UmlProject) in.readObject(); 
			//project.refreshModel();
			return (UmlProject) in.readObject();
		} catch (ClassNotFoundException ex) {
			throw new IOException(ApplicationResources.getInstance().getString(
					"error.readfile.message"));
		} finally {
			if (in != null)
				in.close();
			if (bis != null)
				bis.close();
			if (fis != null)
				fis.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String getSuffix() {
		return ".oled";
	}
}
