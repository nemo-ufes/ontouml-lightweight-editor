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

import java.awt.Cursor;
import java.util.List;

/**
 * A generalization for selections. This interface is designed to handle any
 * kind of selection. The Selection interface is member of the draw package,
 * because every DiagramElement has a selection property. Implementing classes
 * could and should possibly be in a different package.
 * Serialization note: Selections should not be persisted and therefore do not
 * inherit from Serializable.
 * @author Wei-ju Wu
 */
public interface Selection {

  /**
   * Returns the current selected element.
   * @return the selected element
   */
  DiagramElement getElement();

  /**
   * Returns the currently selected allElements. Single selections only contain
   * one element.
   * @return the selected allElements
   */
  List<DiagramElement> getElements();

  /**
   * Returns the dragging state for this object.
   * @return true if the user is dragging this object, false otherwise
   */
  boolean isDragging();

  /**
   * Initiates the dragging process.
   * @param xcoord the x coordinate
   * @param ycoord the y coordinate
   */
  void startDragging(double xcoord, double ycoord);

  /**
   * Stops the dragging process.
   * @param xcoord the x coordinate
   * @param ycoord the y coordinate
   */
  void stopDragging(double xcoord, double ycoord);

  /**
   * Cancels the dragging action.
   */
  void cancelDragging();

  /**
   * Updates the position while dragging.
   * @param xcoord the x coordinate
   * @param ycoord the y coordianate
   */
  void updatePosition(double xcoord, double ycoord);

  /**
   * Draws this selection object.
   * @param drawingContext the drawingContext
   */
  void draw(DrawingContext drawingContext);

  /**
   * Returns true if the selection contains this position.
   * @param xcoord the x coordinate
   * @param ycoord the y coordinate
   * @return true if the position is contained in the Selection, false otherwise
   */
  boolean contains(double xcoord, double ycoord);

  /**
   * Returns the cursor for the specified position.
   * @param xcoord the x coordinate
   * @param ycoord the y coordinate
   * @return the Cursor
   */
  Cursor getCursorForPosition(double xcoord, double ycoord);

  /**
   * Updates the dimensions of the selection according to the underlying
   * object.
   */
  void updateDimensions();
}
