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
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Collection;

/**
 * This class implements basic functionality for rectangular allElements.
 *
 * @author Wei-ju Wu
 */
public interface Node extends DiagramElement {

  /**
   * Return the origin.
   * @return the origin
   */
  Point2D getOrigin();

  /**
   * Sets the position.
   * @param xpos the x-position
   * @param ypos the y position
   */
  void setOrigin(double xpos, double ypos);

  /**
   * Returns the x position in world coordinates.
   * @return the x position in world coordinates
   */
  double getAbsoluteX1();

  /**
   * Returns the y position in world coordinates.
   * @return the y position in world coordinates
   */
  double getAbsoluteY1();

  /**
   * Returns the minimum size.
   * @return the minimum size
   */
  Dimension2D getMinimumSize();

  /**
   * Sets the minimum size.
   * @param width the width
   * @param height the height
   */
  void setMinimumSize(double width, double height);

  /**
   * Returns the size.
   * @return the size
   */
  Dimension2D getSize();

  /**
   * Sets the size.
   * @param w the width
   * @param h the height
   */
  void setSize(double w, double h);

  /**
   * Sets the width of this Node.
   * @param width the width
   */
  void setWidth(double width);

  /**
   * Sets the height of this Node.
   * @param height the height
   */
  void setHeight(double height);

  /**
   * Sets the position in world coordinates.
   * @param xpos the x position
   * @param ypos the y position
   */
  void setAbsolutePos(double xpos, double ypos);

  /**
   * Returns the center x position.
   * @return the center x position
   */
  double getAbsCenterX();

  /**
   * Returns the center y position.
   * @return the center y position
   */
  double getAbsCenterY();

  /**
   * Determines whether the given line intersects this Node.
   * @param line the line
   * @return true if the line intersects this node, false otherwise
   */
  boolean intersects(Line2D line);

  /**
   * Calculates the intersection point between this object's bounding box and
   * the specified line object.
   * @param line the line to intersect with
   * @param intersectionPoint the point that stores the result
   */
  void calculateIntersection(Line2D line, Point2D intersectionPoint);

  /**
   * Calculates the x2 coordinate and returns its value. x2 is the sum of x1
   * and width.
   * @return the x2 coordinate
   */
  double getAbsoluteX2();

  /**
   * Calculates the y2 coordinate and returns its value. y2 is the sum of y1
   * and height.
   * @return the y2 coordinate
   */
  double getAbsoluteY2();

  /**
   * Adds the specified NodeChangeListener.
   * @param l the NodeChangeListener to add
   */
  void addNodeChangeListener(NodeChangeListener l);

  /**
   * Removes the specified NodeChangeListener.
   * @param l the NodeChangeListener to remove
   */
  void removeNodeChangeListener(NodeChangeListener l);

  /**
   * Returns the Connections this node has.
   * @return this object's Connections
   */
  Collection<? extends Connection> getConnections();

  /**
   * Adds the specified connection to this node.
   * @param conn the connection to add
   */
  void addConnection(Connection conn);

  /**
   * Removes the specified connection from this node.
   * @param conn the connection to remove
   */
  void removeConnection(Connection conn);
}
