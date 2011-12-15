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
package br.inf.ufes.nemo.oled.ui.diagram.commands;

import br.inf.ufes.nemo.oled.draw.DiagramElement;



/**
 * Defines an interface to the diagram editor that handles change notifications.
 * @author Wei-ju Wu
 * @version 1.0
 */
public interface DiagramEditorNotification {

  /**
   * Method is called when an element was added by a Command.
   * @param element the element which was added
   */
  void notifyElementAdded(DiagramElement element);

  /**
   * Method is called when an element was removed by a command.
   * @param element the removed element
   */
  void notifyElementRemoved(DiagramElement element);

  /**
   * Update method called after a state change from a Command. Such state
   * changes include move operations.
   */
  void notifyElementsMoved();

  /**
   * Called when a DiagramElement was resized.
   * @param element the element that was resized
   */
  void notifyElementResized(DiagramElement element);
}
