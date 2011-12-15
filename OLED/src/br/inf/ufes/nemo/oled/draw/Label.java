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
package br.inf.ufes.nemo.oled.draw;

import br.inf.ufes.nemo.oled.draw.DrawingContext.FontType;


/**
 * Label is a representation of a text label with one or more lines. It could
 * in principle be implemented as a JComponent. It is instead opted to keep it
 * as light-weight as possible and integrate it within the DiagramElement
 * hierarchy.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public interface Label extends Node {

  /**
   * Sets the label source.
   * @param aSource the label source
   */
  void setSource(LabelSource aSource);

  /**
   * Returns the LabelSource.
   * @return the LabelSource
   */
  LabelSource getSource();

  /**
   * Returns the Label text.
   * @return the text
   */
  String getNameLabelText();

  /**
   * Sets the Label's text.
   * @param text the text to set
   */
  void setNameLabelText(String text);

  /**
   * Sets the Label font type.
   * @param aFontType the FontType
   */
  void setFontType(FontType aFontType);

  /**
   * Centers this Label horizontally in its parent.
   */
  void centerHorizontally();
  
  /**
   * Checks if the label is editable 
   * @return editable label
   */
  boolean isEditable();
  
  /**
   * Enables or disables the label for editing with the text box editor (when avaliable)
   * @param flag the flag
   */
  void setEditable(boolean flag);
}
