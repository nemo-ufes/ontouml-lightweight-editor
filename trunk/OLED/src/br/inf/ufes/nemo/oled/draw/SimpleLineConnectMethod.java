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

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the simple version of the LineConnectMethod interface.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public final class SimpleLineConnectMethod implements LineConnectMethod {

  private static SimpleLineConnectMethod instance =
    new SimpleLineConnectMethod();

  /**
   * Returns the singleton instance.
   * @return the singleton instance
   */
  public static SimpleLineConnectMethod getInstance() { return instance; }

  /**
   * Private constructor.
   */
  private SimpleLineConnectMethod() { }

  /**
   * {@inheritDoc}
   */
  ////FIXME To enable associations like material to source
  public void generateAndSetPointsToConnection(Connection conn,
    Node node1, Node node2, Point2D point1, Point2D point2) {
    double x1 = node1.getAbsCenterX(), y1 = node1.getAbsCenterY();
    double x2 = node2.getAbsCenterX(), y2 = node2.getAbsCenterY();
    Line2D segment = new Line2D.Double(x1, y1, x2, y2);
    List<Point2D> points = new ArrayList<Point2D>();
    points.add(new Point2D.Double());
    points.add(new Point2D.Double());
    node1.calculateIntersection(segment, points.get(0));
    node2.calculateIntersection(segment, points.get(points.size() - 1));
    conn.setPoints(points);
  }

  /**
   * {@inheritDoc}
   */
  public void drawLineSegments(DrawingContext drawingContext,
    Point2D source, Point2D dest) {
    drawingContext.drawLine(source.getX(), source.getY(), dest.getX(),
      dest.getY());
  }
}
