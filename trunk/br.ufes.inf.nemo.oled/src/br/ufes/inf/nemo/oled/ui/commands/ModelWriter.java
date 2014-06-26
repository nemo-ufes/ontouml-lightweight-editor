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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import br.ufes.inf.nemo.oled.model.UmlProject;

/**
 * This class writes the current model and diagram data to an XML file.
 *
 * @author Wei-ju Wu
 */
public final class ModelWriter extends FileWriter {

  private static ModelWriter instance = new ModelWriter();

  /**
   * Returns the singleton instance.
   * @return the singleton instance
   */
  public static ModelWriter getInstance() { return instance; }

  /**
   * Writes the specified UmlProject to a file.
   * @param comp the parent component for the confirmation dialog
   * @param file the file
   * @param project the model
   * @return the file that was actually written
   * @throws IOException if error occurred
   */
  public File writeModel(Component comp, File file, UmlProject project)
    throws IOException {
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    ObjectOutputStream os = null;
    File theFile = getFileWithExtension(file);
    if (canWrite(comp, theFile)) {
      try {
        fos = new FileOutputStream(theFile);
        bos = new BufferedOutputStream(fos);
        os = new ObjectOutputStream(bos);
        os.writeObject(project);
        os.flush();
      } finally {
        if (os != null) os.close();
        if (bos != null) bos.close();
        if (fos != null) fos.close();
      }
    }
    return theFile;
  }

  /**
   * {@inheritDoc}
   */
  public String getSuffix() { return ".oled"; }
}
