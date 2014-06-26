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

import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.geom.Dimension2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * A factory class for creating drawing Shapes for the Java2D system. It relies
 * on the fact that rendering is done in a single thread. We can reduce creation
 * of unnecessary Shape objects by reusing them in the Factory and
 * reinitializing them.
 *
 * @author Wei-ju Wu
 */
public final class DrawingShapeFactory {

  private Rectangle2D rect2d = new Rectangle2D.Double(0, 0, 10, 10);
  private Line2D line2d = new Line2D.Double(0, 0, 10, 10);
  private Stroke borderStroke = new BasicStroke(1);
  private float[] dashes = {4.0f, 6.0f};
  private Stroke gridStroke = new BasicStroke(1);
  private Stroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
    BasicStroke.JOIN_MITER, 3.0f, dashes, 0.0f);
  private float[] bigDashes = {8.0f, 6.0f};
  private Stroke dashedStrokeBold = new BasicStroke(1.5f, BasicStroke.CAP_BUTT,
		    BasicStroke.JOIN_MITER, 3.0f, bigDashes, 2.0f);
  private Stroke stdStroke = new BasicStroke(1);

  private static DrawingShapeFactory instance = new DrawingShapeFactory();

  /**
   * Constructor.
   */
  private DrawingShapeFactory() { }

  /**
   * Returns the singleton instance.
   * @return the factory instance
   */
  public static DrawingShapeFactory getInstance() {
    return instance;
  }

  // ***********************************************************************
  // ****** Shapes
  // ****************************
  /**
   * Returns a Rectangle2D object with the specified dimensions.
   * @param origin the origin
   * @param size the size
   * @return a Rectangle2D object
   */
  public Rectangle2D createRect2d(Point2D origin, Dimension2D size) {
    rect2d.setFrame(origin, size);
    return rect2d;
  }

  /**
   * Returns a Rectangle2D object with the specified dimensions.
   * @param origin the origin
   * @param width the width
   * @param height the height
   * @return a Rectangle2D object
   */
  public Rectangle2D createRect2d(Point2D origin, double width, double height) {
    rect2d.setFrame(origin.getX(), origin.getY(), width, height);
    return rect2d;
  }

  /**
   * Returns a Rectangle2D object with the specified dimensions.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param width the width
   * @param height the height
   * @return a Rectangle2D object
   */
  public Rectangle2D createRect2d(double x, double y, double width,
    double height) {
    rect2d.setFrame(x, y, width, height);
    return rect2d;
  }

  /**
   * Returns a Line2D object as specified.
   * @param p0 the first point
   * @param p1 the second point
   * @return a Line2D object
   */
  public Line2D createLine2d(Point2D p0, Point2D p1) {
    line2d.setLine(p0, p1);
    return line2d;
  }

  /**
   * Returns a Line2D object as specified.
   * @param x0 x0 coordinate
   * @param y0 y0 coordinate
   * @param x1 x1 coordinate
   * @param y1 y1 coordinate
   * @return the Line2D object
   */
  public Line2D createLine2d(double x0, double y0, double x1, double y1) {
    line2d.setLine(x0, y0, x1, y1);
    return line2d;
  }

  // *************************************************************************
  // **** Strokes
  // *****************************************************
  /**
   * Returns the Stroke object to draw the diagram border.
   * @return the diagram border stroke
   */
  public Stroke createDiagramBorderStroke() {
    return borderStroke;
  }

  /**
   * Returns the Stroke used to draw grid lines.
   * @return the grid Stroke
   */
  public Stroke createGridStroke() {
    return gridStroke;
  }

  /**
   * Returns the standard drawing stroke.
   * @return the standard drawing stroke
   */
  public Stroke getStandardStroke() {
    return stdStroke;
  }

  /**
   * Return the standard dashed stroke.
   * @return the standard dashed stroke
   */
  public Stroke getDashedStroke() {
    return dashedStroke;
  }
  
  public Stroke getDashedStrokeBold() {
	    return dashedStrokeBold;
  }
  
}
