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

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import br.ufes.inf.nemo.oled.draw.GeometryUtil.Orientation;

/**
 * This class implements a connection selection which can be edited in
 * rectilinear fashion. The editing scheme is very complex and absolutely
 * requires a test suite.
 *
 * @author Wei-ju Wu
 */
public class RectilinearConnectionSelection extends ConnectionSelection {

  private List<Point2D> editpoints = new LinkedList<Point2D>();
  private Line2D editedSegment;
  private int dragIndex;
  private Point2D anchor;
  private int editedOffset;

  /**
   * Constructor.
   * @param operations the DiagramOperations object
   * @param conn the Connection object
   */
  public RectilinearConnectionSelection(DiagramOperations operations,
    Connection conn) {
    super(operations, conn);
  }

  /**
   * {@inheritDoc}
   */
  public List<Point2D> getEditPoints() { return editpoints; }

  /**
   * {@inheritDoc}
   */
  protected void startDragControlPoint(double xcoord, double ycoord) {
    copyConnectionPointsToEditPoints();
    Point2D draggedControlPoint = getControlPoint(xcoord, ycoord);
    if (draggedControlPoint != null) {
      anchor = new Point2D.Double(xcoord, ycoord);
      List<Point2D> connectionPoints = getConnection().getPoints();
      dragIndex = connectionPoints.indexOf(draggedControlPoint);
      setIsDragControlPoint(true);
    }
  }

  /**
   * {@inheritDoc}
   */
  protected void startDragSegment(double xcoord, double ycoord) {
    copyConnectionPointsToEditPoints();
    editedSegment = getConnection().getSegmentAtPoint(xcoord, ycoord);
    if (editedSegment != null) {
      anchor = new Point2D.Double(xcoord, ycoord);
      dragIndex = getConnection().getPoints().indexOf(editedSegment.getP1());
      setIsDragSegment(true);
    }
  }

  /**
   * Copies the control points to the edit point list.
   */
  private void copyConnectionPointsToEditPoints() {
    editpoints.clear();
    editedOffset = 0;
    for (Point2D point : getConnection().getPoints()) {
      editpoints.add((Point2D) point.clone());
    }
  }

  /**
   * {@inheritDoc}
   */
  protected void finishDragSegment(double xcoord, double ycoord) {
    updatePosition(xcoord, ycoord);
  }

  /**
   * {@inheritDoc}
   */
  protected void finishDragControlPoint(double xcoord, double ycoord) {
    updatePosition(xcoord, ycoord);
  }

  /**
   * {@inheritDoc}
   */
  public void updatePosition(double xcoord, double ycoord) {
    if (isDragSegment()) {
      dragSegment(dragIndex, xcoord, ycoord);
    } else if (isDragControlPoint()) {
      dragControlPoint(dragIndex, xcoord, ycoord);
    }
  }

  // ************************************************************************
  // ***** Drags a control point
  // *******************************

  /**
   * Top level method for dragging a control point.
   * @param index the point index within the connection
   * @param xcoord the xcoord to drag to
   * @param ycoord the ycoord to drag to
   */
  private void dragControlPoint(int index, double xcoord, double ycoord) {
    double diffx = xcoord - anchor.getX();
    double diffy = ycoord - anchor.getY();
    moveDragPoint(index, diffx, diffy);
    dragConnectedSegmentPointBefore(index, diffx, diffy);
    dragConnectedSegmentPointAfter(index, diffx, diffy);
    meltinNextToOuterPoints();
    restoreConnectionToNode();
  }

  /**
   * Drags the point that is connected to the point at the specified index
   * and comes immediately before it in the sequence of connection points.
   * @param index the index of the control point
   * @param diffx the x amount to drag
   * @param diffy the y amount to drag
   */
  private void dragConnectedSegmentPointBefore(int index, double diffx,
    double diffy) {
    if (index > 0) {
      moveSegmentPoint(index - 1, index - 1, diffx, diffy);
    }
  }

  /**
   * Drags the point that is connected to the point at the specified index
   * and comes immediately after it in the sequence of connection points.
   * @param index the index of the control point
   * @param diffx the x amount to drag
   * @param diffy the y amount to drag
   */
  private void dragConnectedSegmentPointAfter(int index, double diffx,
    double diffy) {
    if (index < getConnection().getPoints().size() - 1) {
      moveSegmentPoint(index, index + 1, diffx, diffy);
    }
  }

  /**
   * Coordinates the moving of the drag point.
   * @param indexInConnection the index within the connection
   * @param diffx the x amount to move the point
   * @param diffy the y amount to move the point
   */
  private void moveDragPoint(int indexInConnection, double diffx,
    double diffy) {
    // first or last point, which means connects to a node -> only one direction
    if (indexInConnection == 0) {
    	if(getConnection().getNode1()!=null)
    		moveConstrained(indexInConnection, getConnection().getNode1(), diffx, diffy);
    	else
    		moveConstrained(indexInConnection, getConnection().getConnection1(), diffx, diffy);
    } else if (indexInConnection == getConnection().getPoints().size() - 1) {
    	if(getConnection().getNode2()!=null)
    		moveConstrained(indexInConnection, getConnection().getNode2(), diffx, diffy);
    	else
    		moveConstrained(indexInConnection, getConnection().getConnection2(), diffx, diffy);
    } else {
      // unconstrained
      Point2D p = getConnection().getPoints().get(indexInConnection);
      editpoints.get(mapToEditPointIndex(indexInConnection)).setLocation(
        p.getX() + diffx, p.getY() + diffy);
    }
  }

  /**
   * This method is called on either the first or last node in the connection.
   * Depending on where it attaches to the node, the drag point can only be
   * moved in the direction of the attaching side.
   * @param indexInConnection the index within the connection
   * @param node the attached node
   * @param diffx the x amount to move
   * @param diffy the y amount to move
   */
  private void moveConstrained(int indexInConnection, Node node, double diffx, double diffy) 
  {
    double amountX = diffx, amountY = diffy;
    int mappedIndex = mapToEditPointIndex(indexInConnection);
    Point2D p = getConnection().getPoints().get(indexInConnection);
    if (p.getX() == node.getAbsoluteX1() ||
        p.getX() == node.getAbsoluteX2()) {
      amountX = 0;
    }
    if (p.getY() == node.getAbsoluteY1() ||
        p.getY() == node.getAbsoluteY2()) {
      amountY = 0;
    }
    editpoints.get(mappedIndex).setLocation(p.getX() + amountX,
      p.getY() + amountY);
  }

  private void moveConstrained(int indexInConnection, Connection c, double diffx, double diffy) 
  {
    double amountX = diffx, amountY = diffy;
    int mappedIndex = mapToEditPointIndex(indexInConnection);
    Point2D p = getConnection().getPoints().get(indexInConnection);
    if (p.getX() == c.getAbsoluteX1() ||
        p.getX() == c.getAbsoluteX2()) {
      amountX = 0;
    }
    if (p.getY() == c.getAbsoluteY1() ||
        p.getY() == c.getAbsoluteY2()) {
      amountY = 0;
    }
    editpoints.get(mappedIndex).setLocation(p.getX() + amountX,
      p.getY() + amountY);
  }

  /**
   * Moves the specified segment point in the perpendicular direction of
   * the segment that it forms with the drag point.
   * @param segmentPointIndex the first index of the segment that defines the
   * direction
   * @param moveIndex the index of the point to move
   * @param diffx the x amount to move
   * @param diffy the y amount to move
   */
  private void moveSegmentPoint(int segmentPointIndex, int moveIndex,
    double diffx, double diffy) {
    Point2D point = getConnection().getPoints().get(moveIndex);
    // adjusts the index of the point within the editpoints list FIXME Bicheira
    int editMoveIndex = mapToEditPointIndex(moveIndex);
    if (isSegmentHorizontal(segmentPointIndex)) {
      editpoints.get(editMoveIndex).setLocation(point.getX(),
        point.getY() + diffy);
    } else {
      editpoints.get(editMoveIndex).setLocation(point.getX() + diffx,
        point.getY());
    }
  }

  // ************************************************************************
  // ***** Drags a segment
  // *******************************

  /**
   * Top level method which is called when the user drags a whole rectilinear
   * segment.
   * @param index the start index of the segment within the connection
   * @param xcoord the target x coordinate
   * @param ycoord the target y coordinate
   */
  private void dragSegment(int index, double xcoord, double ycoord) {
    if (isSegmentHorizontal(index)) {
      dragHorizontalSegment(index, ycoord);
    } else {
      dragVerticalSegment(index, xcoord);
    }
    meltinNextToOuterPoints();
    restoreConnectionToNode();
    truncateSegmentToNode(index);
  }

  /**
   * The dragged segment is a horizontal segment, so only allow dragging
   * in the vertical direction.
   * @param index the start index of the segment within the connection
   * @param ycoord the target y coordinate
   */
  private void dragHorizontalSegment(int index, double ycoord) {
    Point2D p1 = getConnection().getPoints().get(index);
    Point2D p2 = getConnection().getPoints().get(index + 1);
    double diffy = ycoord - anchor.getY();
    int segIndex = mapToEditPointIndex(index);
    editpoints.get(segIndex).setLocation(p1.getX(), p1.getY() + diffy);
    editpoints.get(segIndex + 1).setLocation(p2.getX(), p2.getY() + diffy);
  }

  /**
   * The dragged segment is a vertical segment, so only allow dragging in the
   * horizontal direction.
   * @param index the start index of the segment within the connection
   * @param xcoord the target x coordinate
   */
  private void dragVerticalSegment(int index, double xcoord) {
    Point2D p1 = getConnection().getPoints().get(index);
    Point2D p2 = getConnection().getPoints().get(index + 1);
    double diffx = xcoord - anchor.getX();
    int segIndex = mapToEditPointIndex(index);
    editpoints.get(segIndex).setLocation(p1.getX() + diffx, p1.getY());
    editpoints.get(segIndex + 1).setLocation(p2.getX() + diffx, p2.getY());
  }

  // ************************************************************************
  // ***** Restores the connection to the node
  // *******************************************

  /**
   * If the drag operations resulted in the control point being dragged off
   * the node, restore the contact by inserting a new point which connects to
   * the node again.
   */
  private void restoreConnectionToNode() {
    Point2D p0 = editpoints.get(0);
    Point2D pn = editpoints.get(editpoints.size() - 1);
    Node node1 = getConnection().getNode1();
    Node node2 = getConnection().getNode2();
    Connection c1 = getConnection().getConnection1();
    if(node1!=null && node2 !=null){
	    int outcode1 = outcode(node1.getAbsoluteBounds(), p0);
	    int outcode2 = outcode(node2.getAbsoluteBounds(), pn);
	    if (outcode1 != 0) {
	      insertConnectionPointToNode(node1, outcode1, 0);
	    }
	    if (outcode2 != 0) {
	      insertConnectionPointToNode(node2, outcode2, getConnection().getPoints().size() - 1);
	    }
	    
    }else if (node1==null && node2!=null){
    	int outcode1 = outcode(c1.getAbsoluteBounds(), p0);
  	    int outcode2 = outcode(node2.getAbsoluteBounds(), pn);
  	    if (outcode1 != 0) {
  	      insertConnectionPointToNode(c1, outcode1, 0);
  	    }
  	    if (outcode2 != 0) {
  	      insertConnectionPointToNode(node2, outcode2, getConnection().getPoints().size() - 1);
  	    }
    }
  }

  /**
   * Inserts a new point at the specified position. This is called only if
   * the point previously connecting to node was dragged off it.
   * @param node the Node
   * @param outcode the position of the dragged off point, relative to the node
   * @param index if 0, the point is added at the start, otherwise at the end
   */
  private void insertConnectionPointToNode(Node node, int outcode, int index) {
    Point2D newpoint = new Point2D.Double();
    Point2D segmentPoint = getConnection().getPoints().get(index);
    if (outcode == Rectangle2D.OUT_BOTTOM) {
      newpoint.setLocation(segmentPoint.getX(), node.getAbsoluteY2());
      addEditPoint(index, newpoint);
    } else if (outcode == Rectangle2D.OUT_TOP) {
      newpoint.setLocation(segmentPoint.getX(), node.getAbsoluteY1());
      addEditPoint(index, newpoint);
    } else if (outcode == Rectangle2D.OUT_LEFT) {
      newpoint.setLocation(node.getAbsoluteX1(), segmentPoint.getY());
      addEditPoint(index, newpoint);
    } else if (outcode == Rectangle2D.OUT_RIGHT) {
      newpoint.setLocation(node.getAbsoluteX2(), segmentPoint.getY());
      addEditPoint(index, newpoint);
    }
  }
  
  private void insertConnectionPointToNode(Connection c, int outcode, int index) {
    Point2D newpoint = new Point2D.Double();
    Point2D segmentPoint = getConnection().getPoints().get(index);
    if (outcode == Rectangle2D.OUT_BOTTOM) {
      newpoint.setLocation(segmentPoint.getX(), c.getAbsoluteY2());
      addEditPoint(index, newpoint);
    } else if (outcode == Rectangle2D.OUT_TOP) {
      newpoint.setLocation(segmentPoint.getX(), c.getAbsoluteY1());
      addEditPoint(index, newpoint);
    } else if (outcode == Rectangle2D.OUT_LEFT) {
      newpoint.setLocation(c.getAbsoluteX1(), segmentPoint.getY());
      addEditPoint(index, newpoint);
    } else if (outcode == Rectangle2D.OUT_RIGHT) {
      newpoint.setLocation(c.getAbsoluteX2(), segmentPoint.getY());
      addEditPoint(index, newpoint);
    }
  }

  // ************************************************************************
  // ***** Melts in the points next to the outer points if possible
  // ****************************************************************

  /**
   * Melts in the outermost points if the points next to them already connect
   * to the connection's nodes. "Melting in" means removing them.
   */
  private void meltinNextToOuterPoints() {
	  
    Node node1 = getConnection().getNode1();
    Node node2 = getConnection().getNode2();
    Connection c1 = getConnection().getConnection1();
    
    if (editpoints.size() > 2) {
      // remove last point if possible
      Point2D p = editpoints.get(editpoints.size() - 2);      
      if (outcode(node2.getAbsoluteBounds(), p) == 0) {
        // see above
        removeEditPoint(editpoints.size() - 1);
      }
    }
    if (editpoints.size() > 2) {
      // remove point 0 if possible
      Point2D p = editpoints.get(1);
      if(node1!=null){
    	  if (outcode(node1.getAbsoluteBounds(), p) == 0) { removeEditPoint(0); }
      }else {
    	  if (outcode(c1.getAbsoluteBounds(), p) == 0) { removeEditPoint(0); }
      }      
    }
  }

  // ************************************************************************
  // ***** Truncation of segments to node borders
  // ****************************************************************

  /**
   * Truncates the specified segment to the connected node if this is either
   * the first or the last segment.
   * @param segmentIndex the segment index within the connection
   */
  void truncateSegmentToNode(int segmentIndex) {
    int editSegmentIndex = mapToEditPointIndex(segmentIndex);
    Line2D segment = new Line2D.Double(editpoints.get(editSegmentIndex),
      editpoints.get(editSegmentIndex + 1));
    if (editSegmentIndex == 0) {
      if(getConnection().getNode1()!=null)
    	getConnection().getNode1().calculateIntersection(segment,editpoints.get(editSegmentIndex));
      else
        getConnection().getConnection1().calculateIntersection(segment,editpoints.get(editSegmentIndex));  
    } else if (editSegmentIndex == editpoints.size() - 2) {
    	if(getConnection().getNode2()!=null)
    		getConnection().getNode2().calculateIntersection(segment, editpoints.get(editSegmentIndex + 1));
    	else
    		getConnection().getConnection2().calculateIntersection(segment, editpoints.get(editSegmentIndex + 1));
    }
  }

  // ************************************************************************
  // ***** Helpers
  // ****************************************************************

  /**
   * Determines whether the segment defined by the point at the specified index
   * and its successor is horizontal or not.
   * @param index the start index of the segment
   * @return true if horizontal, false if vertical
   */
  private boolean isSegmentHorizontal(int index) {
    Point2D p1 = getConnection().getPoints().get(index);
    Point2D p2 = getConnection().getPoints().get(index + 1);
    return GeometryUtil.getInstance().getSegmentOrientation(p1, p2) ==
      Orientation.HORIZONTAL;
  }

  /**
   * Because of rounding errors caused by scaling, a custom outcode() method
   * is provided here.
   * @param bounds the Rectangle2D
   * @param point the Point2D
   * @return the outcode
   */
  private int outcode(Rectangle2D bounds, Point2D point) {
    return GeometryUtil.getInstance().outcode(bounds, point);
  }

  /**
   * Adds the specified point to the editpoints list, either at the start or
   * the end. If index is 0 it is added to the beginning, otherwise it is
   * appended to the end of the list.
   * @param index the insertion index
   * @param point the point to insert
   */
  private void addEditPoint(int index, Point2D point) {
    if (index == 0) {
      editpoints.add(0, point);
      editedOffset++;
    } else {
      editpoints.add(point);
    }
  }

  /**
   * Removes a point in the edit point list at the specified index.
   * @param index the remove index
   */
  private void removeEditPoint(int index) {
    if (index == 0) {
      editpoints.remove(0);
      editedOffset--;
    } else {
      editpoints.remove(index);
    }
  }

  /**
   * Maps an index in the connection point list to an index in the edit point
   * list. Depends on the insertedAtStart and removedAtStart flags.
   * @param connectionPointIndex the index within the connection point list
   * @return the mapped index
   */
  private int mapToEditPointIndex(int connectionPointIndex) {
    return connectionPointIndex + editedOffset;
  }
}
