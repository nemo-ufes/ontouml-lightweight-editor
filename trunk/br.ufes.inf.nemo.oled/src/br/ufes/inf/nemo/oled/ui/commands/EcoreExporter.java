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

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * This class exports a diagram to a Owl File.
 */
public class EcoreExporter extends FileWriter {

	/**
	 * Export the editor graphics to a file in PNG format.
	 * 
	 * @param editor
	 *            the editor
	 * @param file
	 *            the file to write
	 * @throws IOException
	 *             if error occurred
	 */
	public void writeEcore(Component comp, File file, UmlProject project) throws IOException {
		if (canWrite(comp, file)) {
			URI path = URI.createFileURI(getFileWithExtension(file).getPath());
			Resource resource = project.getResource();
			resource.setURI(path);
			ModelHelper.saveXMI(resource);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	protected String getSuffix() {
		return ".refontouml";
	}
}
