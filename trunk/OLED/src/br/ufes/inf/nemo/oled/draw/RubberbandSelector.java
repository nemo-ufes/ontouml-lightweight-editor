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

import java.awt.Cursor;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the rubber band for multi selection.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class RubberbandSelector implements Selection {

  private Point2D anchor = new Point2D.Double();
  private Point2D current = new Point2D.Double();
  private boolean isDragging;
  private CompositeNode compositeNode;
  private List<DiagramElement> selectedElements =
    new ArrayList<DiagramElement>();

  /**
   * Sets the diagram object this selector belongs to.
   * @param aDiagram the diagram
   */
  public void setDiagram(CompositeNode aDiagram) {
    compositeNode = aDiagram;
  }

  /**
   * Returns the selected elements.
   * @return the selected elements
   */
  public List<DiagramElement> getSelectedElements() {
    return selectedElements;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * {@inheritDoc}
   */
  public void startDragging(double xcoord, double ycoord) {
    anchor.setLocation(xcoord, ycoord);
    isDragging = true;
  }

  /**
   * {@inheritDoc}
   */
  public void stopDragging(double xcoord, double ycoord) {
    double x = Math.min(xcoord, anchor.getX());
    double y = Math.min(ycoord, anchor.getY());
    double w = Math.max(xcoord, anchor.getX()) - x;
    double h = Math.max(ycoord, anchor.getY()) - y;
    selectedElements = new ArrayList<DiagramElement>();
    compositeNode.getChildrenInSpecifiedBounds(
      new Rectangle2D.Double(x, y, w, h), selectedElements);
    isDragging = false;
  }

  /**
   * {@inheritDoc}
   */
  public void cancelDragging() {
    isDragging = false;
  }

  /**
   * {@inheritDoc}
   */
  public void updatePosition(double xcoord, double ycoord) {
    current.setLocation(xcoord, ycoord);
  }

  /**
   * {@inheritDoc}
   */
  public void updateDimensions() { }

  /**
   * {@inheritDoc}
   */
  public Cursor getCursorForPosition(double xcoord, double ycoord) {
    return Cursor.getDefaultCursor();
  }

  /**
   * {@inheritDoc}
   */
  public boolean contains(double xcoord, double ycoord) { return false; }

  /**
   * {@inheritDoc}
   */
  public DiagramElement getElement() { return NullElement.getInstance(); }

  /**
   * {@inheritDoc}
   */
  public List<DiagramElement> getElements() {
    return new ArrayList<DiagramElement>();
  }

  /**
   * {@inheritDoc}
   */
  public void draw(DrawingContext drawingContext) {
    if (isDragging) {
      double diffx = current.getX() - anchor.getX();
      double diffy = current.getY() - anchor.getY();
      double x = diffx > 0 ? anchor.getX() : current.getX();
      double y = diffy > 0 ? anchor.getY() : current.getY();
      drawingContext.drawRubberband(x, y, Math.abs(diffx), Math.abs(diffy));
    }
  }
}
