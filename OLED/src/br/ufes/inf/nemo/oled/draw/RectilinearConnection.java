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

import java.awt.geom.Point2D;
import java.util.List;

import br.ufes.inf.nemo.oled.draw.GeometryUtil.Orientation;

/**
 * This class implements a connection that is constructed and drawn in
 * rectilinear fashion.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class RectilinearConnection extends AbstractConnection {

  private static final long serialVersionUID = -1969143695568555407L;
  private Point2D node1ConnectPoint = null, node2ConnectPoint = null;

  /**
   * {@inheritDoc}
   */
  public boolean isRectilinear() { return true; }

  /**
   * {@inheritDoc}
   */
  public Selection getSelection(DiagramOperations operations) {
    ConnectionSelection selection = new RectilinearConnectionSelection(
      operations, this);
    return selection;
  }

  /**
   * {@inheritDoc}
   */
  public LineConnectMethod getConnectMethod() {
    return RectilinearLineConnectMethod.getInstance();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPoints(List<Point2D> thePoints) {
    super.setPoints(thePoints);
    node1ConnectPoint = getPointInNodeCoordinates(getNode1(), thePoints.get(0));
    node2ConnectPoint = getPointInNodeCoordinates(getNode2(),
      thePoints.get(thePoints.size() - 1));
  }

  /**
   * Returns a new point which contains the coordinates of point in the
   * specified node's coordinates.
   * @param node the node
   * @param point the point
   * @return the new Point2D object
   */
  private Point2D getPointInNodeCoordinates(Node node, Point2D point) {
    return new Point2D.Double(point.getX() - node.getAbsoluteX1(),
      point.getY() - node.getAbsoluteY1());
  }

  /**
   * Returns a new point that represents the parameter point in absolute
   * coordinates.
   * @param node the node
   * @param point the point to map
   * @return the new point
   */
  private Point2D getPointInAbsoluteCoordinates(Node node, Point2D point) {
    return new Point2D.Double(point.getX() + node.getAbsoluteX1(),
      point.getY() + node.getAbsoluteY1());
  }

  /**
   * {@inheritDoc}
   */
  public void resetPoints() {
    getPoints().clear();
    reconnectPulledOffNodes();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void draw(DrawingContext drawingContext) {
    if (!isValid()) {
      // invalidate() is usually called by moving the connected nodes
      reconnectPulledOffNodes();
    }
    super.draw(drawingContext);
  }

  /**
   * Reconnects the connection to the pulled off nodes.
   */
  public void reconnectPulledOffNodes() {
    if (getPoints().size() <= 3) {
      // If there are at most three connection points, simply recalculate the
      // connection between the two nodes completely
      setPoints(RectilinearLineBuilder.getInstance().calculateLineSegments(
        getNode1(), getNode2()));
    } else {
      reattachConnectionPoint(getNode1(), node1ConnectPoint, 0, 1);
      reattachConnectionPoint(getNode2(),
        node2ConnectPoint, getPoints().size() - 1, getPoints().size() - 2);
    // TODO: If the node intersects a middle segment, reduce the segments
    }
    
    //reattachConnectionPoint(getNode1(), node1ConnectPoint, 0, 1);
    //reattachConnectionPoint(getNode2(),
    //  node2ConnectPoint, getPoints().size() - 1, getPoints().size() - 2);
    
    setValid(true);
  }
  
   
  /**
   * Reattaches a connection point to the specified node.
   * @param node the node
   * @param nodeConnectPoint the connection point that attaches to the node
   * @param indexAtNode the index of the point at the node
   * @param indexInner the inner point next ot the point at the node
   */
  private void reattachConnectionPoint(Node node, Point2D nodeConnectPoint,
    int indexAtNode, int indexInner) {

    if (wasPulledOff(node, nodeConnectPoint, getPoints().get(indexAtNode))) {
      Point2D pointAtNode = getPoints().get(indexAtNode);
      Point2D pointInner = getPoints().get(indexInner);
      Point2D absConnectPoint = getPointInAbsoluteCoordinates(node,
          nodeConnectPoint);
      // sets the first segment in parallel
      double diffx = 0, diffy = 0;
      if (GeometryUtil.getInstance().getSegmentOrientation(pointAtNode,
          pointInner) == Orientation.HORIZONTAL) {
        // the segment can only be moved in y direction
        diffy = absConnectPoint.getY() - pointAtNode.getY();
      } else if (GeometryUtil.getInstance().getSegmentOrientation(
                 pointAtNode, pointInner) == Orientation.VERTICAL) {
        // the segment can only be moved in x direction
        diffx = absConnectPoint.getX() - pointAtNode.getX();
      }
      pointAtNode.setLocation(absConnectPoint);
      pointInner.setLocation(pointInner.getX() + diffx,
        pointInner.getY() + diffy);
    }
  }

  /**
   * Determines whether the specified node was pulled of the segment at the
   * point indexes (segmentIndex, segmentIndex + 1).
   * @param node the node
   * @param connectPoint the connect point
   * @param cmpPoint the comparison point
   * @return true if pulled off, false otherwise
   */
  private boolean wasPulledOff(Node node, Point2D connectPoint,
    Point2D cmpPoint) {
    Point2D absConnectPoint = getPointInAbsoluteCoordinates(node, connectPoint);
    return !GeometryUtil.getInstance().equals(absConnectPoint.getX(),
      cmpPoint.getX()) || !GeometryUtil.getInstance().equals(
      absConnectPoint.getY(), cmpPoint.getY());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void copyData(Connection conn) {
    super.copyData(conn);
    setPoints(RectilinearLineBuilder.getInstance().calculateLineSegments(
      getNode1(), getNode2()));
  }
}
