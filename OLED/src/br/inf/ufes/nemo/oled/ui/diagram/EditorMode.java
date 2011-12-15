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

import br.inf.ufes.nemo.oled.draw.DrawingContext;


/**
 * This interface defines an abstract editor mode, which covers selection
 * and creation. The methods are primarily operating on mouse events with
 * mouse pointer coordinates which are defined in the editor's coordinate
 * system rather than the ones defined by the operating system. Therefore
 * the diagram editor need to transform the mouse coordinates from the event
 * system before invoking these methods.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public interface EditorMode extends EditorMouseHandler {

  /**
   * Draws any additional elements or decorations supported by this mode.
   * @param drawingContext the DrawingContext
   */
  void draw(DrawingContext drawingContext);

  /**
   * The editor state was changed, update any elements.
   */
  void stateChanged();

  /**
   * Cancels the current action.
   */
  void cancel();
}
