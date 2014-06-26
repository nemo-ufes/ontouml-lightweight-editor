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

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.List;


/**
 * An abstract interface to define operations on a diagram. These are invoked
 * from selections. We need to define this interface because the operations
 * are defined in an uppper abstraction to realize an undoable operation.
 * @author Wei-ju Wu
 */
public interface DiagramOperations {

  /**
   * Moves the specified allElements using a list of Commands.
   * @param moveOperations the move operations
   */
  void moveElements(MoveOperation[] moveOperations);

  /**
   * Resizes the specified element and moves it in one step.
   * @param element the element to change
   * @param newpos the new position
   * @param newsize the new size
   */
  void resizeElement(Node element, Point2D newpos, Dimension2D newsize);

  /**
   * Sets a new list of connection points.
   * @param conn the connection
   * @param points the new point list
   */
  void setNewConnectionPoints(Connection conn, List<Point2D> points);

  /**
   * Tells the editor to show the properties of the element.
   * @param element the element
   */
  void editProperties(DiagramElement element);

  /**
   * Returns the diagram that belongs to this object.
   * @return the diagram
   */
  Diagram getDiagram();
}
