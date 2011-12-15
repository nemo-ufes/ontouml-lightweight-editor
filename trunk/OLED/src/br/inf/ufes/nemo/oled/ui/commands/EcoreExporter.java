/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
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
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.inf.ufes.nemo.oled.ui.commands;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import br.inf.ufes.nemo.oled.model.UmlProject;
import br.inf.ufes.nemo.oled.util.ModelHelper;

/**
 * This class exports a diagram to a Owl File.
 * 
 * @version 1.0
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
		return ".ecore";
	}
}
