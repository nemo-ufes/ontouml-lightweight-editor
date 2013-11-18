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
package br.ufes.inf.nemo.oled.draw;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a simple connection which simply consists of a
 * sequence of connected points. It can be either drawn dashed or as a
 * regular line. It is intended to be used by wrapping it.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class SimpleConnection extends AbstractConnection {

  private static final long serialVersionUID = -8016036142125462914L;

  /**
   * Constructor.
   */
  public SimpleConnection() { }

  /**
   * {@inheritDoc}
   */
  public boolean isRectilinear() { return false; }

  /**
   * {@inheritDoc}
   */
  public Selection getSelection(DiagramOperations operations) {
    ConnectionSelection selection = new SimpleConnectionSelection(operations,
      this);
    return selection;
  }

  /**
   * {@inheritDoc}
   */
  public LineConnectMethod getConnectMethod() {
    return SimpleLineConnectMethod.getInstance();
  }

  /**
   * {@inheritDoc}
   */
  public void resetPoints() {
    setPoints(new ArrayList<Point2D>());
    getPoints().add(new Point2D.Double(getNode1().getAbsCenterX(),
      getNode1().getAbsCenterY()));
    getPoints().add(new Point2D.Double(getNode2().getAbsCenterX(),
      getNode2().getAbsCenterY()));
    reconnectEndPointsToNodes();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void draw(DrawingContext drawingContext) {
    if (!isValid()) {
      reconnectEndPointsToNodes();
    }
    super.draw(drawingContext);
  }

  /**
   * Reconnects the end points to the nodes.
   */
  private void reconnectEndPointsToNodes() {
    connectStartPointToNode1();
    connectEndPointToNode2();
    setValid(true);
  }

  /**
   * Connects the start point to Node 1.
   */
  private void connectStartPointToNode1() {
    double x = getNode1().getAbsCenterX(), y = getNode1().getAbsCenterY();
    getPoints().get(0).setLocation(x, y);
    Line2D segment = getSegments().get(0);
    getNode1().calculateIntersection(segment, getPoints().get(0));
  }

  /**
   * Connects the end point to Node 2.
   */
  private void connectEndPointToNode2() {
    List<Point2D> points = getPoints();
    Point2D pointToNode2 = points.get(points.size() - 1);
    double x = getNode2().getAbsCenterX(), y = getNode2().getAbsCenterY();
    pointToNode2.setLocation(x, y);
    List<Line2D> segments = getSegments();
    Line2D segment = segments.get(segments.size() - 1);
    getNode2().calculateIntersection(segment, points.get(points.size() - 1));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void copyData(Connection conn) {
    super.copyData(conn);
    resetPoints();
  }

}
