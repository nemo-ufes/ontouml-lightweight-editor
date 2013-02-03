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
package br.ufes.inf.nemo.oled.ui.diagram;

import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;

/**
 * An interface to report state changes within the diagram editor.
 */
public interface EditorStateListener {

  /**
   * Reports the current mouse coordinates. The coordinates reported are
   * transformed in the diagram coordinate system.
   * @param event the mouse event
   */
  void mouseMoved(EditorMouseEvent event);

  /**
   * Some state changed in the editor.
   * @param editor the editor that changed
   * @param the type of the change made in the diagram
   */
  void stateChanged(DiagramEditor editor, ChangeType changeType);

}
