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

import java.awt.geom.Point2D;

/**
 * The interface to providing a line connecting method.
 * @author Wei-ju Wu
 */
public interface LineConnectMethod {

  /**
   * Generates the connection points between the specified nodes and given
   * two end points.
   * @param conn the Connection
   * @param sourceNode the source node
   * @param targetNode the target node
   * @param source the source point
   * @param dest the destination point
   */
  void generateAndSetPointsToConnection(Connection conn, Node sourceNode, Node targetNode, Point2D source, Point2D dest);
  
  void generateAndSetPointsToConnection(Connection conn, Node sourceNode, Connection targetConnection, Point2D source, Point2D dest);

  void generateAndSetPointsToConnection(Connection conn, Connection sourceConnection, Node targetNode, Point2D source, Point2D dest);
  
  void generateAndSetPointsToConnection(Connection conn, Connection sourceConnection, Connection targetConnection, Point2D source, Point2D dest);
  
  /**
   * Draws the line segments according to the method between the two specified
   * points.
   * @param drawingContext the DrawingContext
   * @param source the source point
   * @param dest the destitination point
   */
  void drawLineSegments(DrawingContext drawingContext,
    Point2D source, Point2D dest);
}
