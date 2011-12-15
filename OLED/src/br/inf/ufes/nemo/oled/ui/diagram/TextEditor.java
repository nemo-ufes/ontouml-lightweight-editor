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
package br.inf.ufes.nemo.oled.ui.diagram;

import java.awt.Graphics;

import br.inf.ufes.nemo.oled.draw.Label;

/**
 * The interface to generalize access to text editors.
 * @author Wei-ju Wu
 * @version 1.0
 */
public interface TextEditor {

  /**
   * Returns the currently edited Label.
   * @return the label
   */
  Label getLabel();

  /**
   * Makes the editor invisible.
   */
  void hideEditor();

  /**
   * Displays the editor at the specified position.
   * @param aLabel the label to edit
   * @param g the Graphics object
   */
  void showEditor(Label aLabel, Graphics g);

  /**
   * Returns the visibility state of this editor.
   * @return true if visible, false if not visible
   */
  boolean isVisible();

  /**
   * Returns the text within the editor.
   * @return the text
   */
  String getText();
}
