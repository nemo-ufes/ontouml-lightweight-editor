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
package br.ufes.inf.nemo.oled.draw;

import java.awt.font.TextLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.ufes.inf.nemo.oled.draw.DrawingContext.FontType;
import br.ufes.inf.nemo.oled.draw.MultilineLayouter.MultilineLayout;

/**
 * This class implements a Label with multiple lines. It is important for
 * performance to implement a dedicated multiline label because layout is
 * a rather slow operation.
 *
 * @author Wei-ju Wu
 */
public class MultiLineLabel extends SimpleLabel {

  private static final long serialVersionUID = 3993300155345177335L;
  private transient MultilineLayout layout;

  /**
   * Writes the instance variables to the stream.
   * @param stream an ObjectOutputStream
   * @throws IOException if I/O error occured
   */
 
  private void writeObject(ObjectOutputStream stream) throws IOException {
    // layout should not be written
  }

  /**
   * Reset the transient values for serialization.
   * @param stream an ObjectInputStream
   * @throws IOException if I/O error occured
   * @throws ClassNotFoundException if class was not found
   */
  
  private void readObject(ObjectInputStream stream)
    throws IOException, ClassNotFoundException {
    layout = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void draw(DrawingContext drawingContext) {
    double x = getAbsoluteX1(), y = getAbsoluteY1();
    double layoutWidth = getSize().getWidth();
    double textY = y;
    if (layout == null) recalculateSize(drawingContext);
    for (TextLayout line : layout.getLines()) {
      // Set the left position of the text depending on the text layout
      // direction
      double textX = line.isLeftToRight() ? x : layoutWidth - line.getAdvance();
      textY += line.getAscent();
      line.draw(drawingContext.getGraphics2D(), (float) textX, (float) textY);
      // Move text position another step downward
      textY += line.getDescent() + line.getLeading();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void recalculateSize(DrawingContext drawingContext) {
    layout = MultilineLayouter.getInstance().calculateLayout(
      drawingContext.getGraphics2D().getFontRenderContext(),
      drawingContext.getFont(FontType.DEFAULT), getNameLabelText(),
      getSize().getWidth());
    setSize(layout.getSize().getWidth(), layout.getSize().getHeight());
    setValid(true);
  }
}
