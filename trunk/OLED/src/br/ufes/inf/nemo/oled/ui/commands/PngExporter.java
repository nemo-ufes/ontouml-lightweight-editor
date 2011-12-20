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

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;

/**
 * This class exports a diagram to a Portable Network Graphics file.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class PngExporter extends FileWriter {

  /**
   * Export the editor graphics to a file in PNG format.
   * @param editor the editor
   * @param file the file to write
   * @throws IOException if error occurred
   */
  public void writePNG(DiagramEditor editor, File file) throws IOException {
    Dimension size = editor.getTotalCanvasSize();
    File theFile = getFileWithExtension(file);
    if (canWrite(editor, theFile)) {
      BufferedImage image = new BufferedImage((int) size.getWidth(),
        (int) size.getHeight(), BufferedImage.TYPE_INT_RGB);
      editor.paintComponentNonScreen(image.getGraphics());
      ImageIO.write(image, "png", theFile);
    }
  }

  /**
   * {@inheritDoc}
   */
  protected String getSuffix() { return ".png"; }
}
