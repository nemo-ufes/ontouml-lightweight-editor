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
package br.ufes.inf.nemo.oled.ui.commands;

import java.io.File;
import java.util.regex.Pattern;


/**
 * This class generalizes classes which deal with some kind of files with
 * extensions.
 *
 * @author Wei-ju Wu
 * @version 1.0
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
    String path = file.getPath();
    File result = file;
    Pattern pattern = Pattern.compile(".*\\" + getSuffix());
    if (!pattern.matcher(path).matches()) {
      path = path + getSuffix();
      result = new File(path);
    }
    return result;
  }
 
}
