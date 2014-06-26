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
import java.text.MessageFormat;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.oled.util.ApplicationResources;

/**
 * This class is an abstract super class that provides useful functionality
 * for graphics exporter classes.
 *
 * @author Wei-ju Wu
 */
public abstract class FileWriter extends FileHandler {

  /**
   * Returns true if the file can be written. This method checks the existence
   * of the file and if it exists, the user will be asked whether to overwrite
   * the file.
   * @param component the parent component for the message dialog
   * @param file the file to write
   * @return true if the file does not yet exist or if the user confirmed
   * overwriting it, false if the file should not be written
   */
  protected boolean canWrite(Component component, File file) {
    if (file.exists()) {
      ApplicationResources resources = ApplicationResources.getInstance();
      String message = resources.getString("dialog.replacefile.confirm.msg");
      message = MessageFormat.format(message, file.getName());
      String title = resources.getString("dialog.replacefile.confirm.title");
      return JOptionPane.showConfirmDialog(component, message, title,
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
    return true;
  }
}
