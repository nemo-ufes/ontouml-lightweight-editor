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

import br.ufes.inf.nemo.oled.util.ConfigurationHelper;


/**
 * This class generalizes classes which deal with some kind of files with
 * extensions.
 *
 * @author Wei-ju Wu
 */
public abstract class FileHandler {

  /**
   * Returns the suffix of the file format. Implemented by derived classes.
   * @return the suffix for the file format
   */
  protected abstract String getSuffix();

  /**
   * Inspects the name of the specified file and checks if it ends with the
   * specified suffix. If not, a new file will be created, appending the suffix
   * to the file name, otherwise the original file object will be returned.
   * @param file the file to check
   * @return the file that ends with the specified suffix
   */
  protected File getFileWithExtension(File file) {
	  return ConfigurationHelper.getFileWithExtension(file, getSuffix());
  }
 
}
