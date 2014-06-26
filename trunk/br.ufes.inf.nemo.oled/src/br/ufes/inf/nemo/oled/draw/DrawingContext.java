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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 * DrawingContext is a very simple abstraction above the Java2D API. It provides
 * elementary drawing allElements needed to draw UML diagrams. It also hides
 * information about the style with which the allElements are drawn. UmlDiagram
 * allElements render themselves by invoking methods on this interface.
 *
 * @author Wei-ju Wu
 */
public interface DrawingContext {

  /**
   * The available FontTypes.
   */
  public enum FontType {
    DEFAULT, ELEMENT_NAME, ABSTRACT_ELEMENT, SMALL
  }

  public enum StrokeType { DASHED_BOLD, DEFAULT }
  
  /**
   * Sets the Graphics2D object and bounds.
   * @param g2d the Graphics2D object
   * @param bounds the bounding rectangle
   */
  void setGraphics2D(Graphics2D g2d, Rectangle bounds);

  /**
   * Draws a dashed line with the default line color.
   * @param x0 the x0 coordinate
   * @param y0 the y0 coordinate
   * @param x1 the x1 coordinate
   * @param y1 the y1 coordinate
   */
  void drawDashedLine(double x0, double y0, double x1, double y1);

  /**
   * Draws a grid line with the default grid line color.
   * @param x0 the x0 coordinate
   * @param y0 the y0 coordinate
   * @param x1 the x1 coordinate
   * @param y1 the y1 coordinate
   */
  void drawGridLine(double x0, double y0, double x1, double y1);

  /**
   * Draws a line with the default line color.
   * @param x0 the x0 coordinate
   * @param y0 the y0 coordinate
   * @param x1 the x1 coordinate
   * @param y1 the y1 coordinate
   */
  void drawLine(double x0, double y0, double x1, double y1);

  // *************************************************************************
  // ****** Rectangles
  // **********************************

  /**
   * Draws a rectangle with the default border color and fills it with a
   * background color.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param width the width
   * @param height the height
   * @param fillColor the fill color
   */
  void drawRectangle(double x, double y, double width, double height,
    Color fillColor);

  /**
   * Fills the specfied rectangle.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param width the width
   * @param height the height
   * @param fillColor the fill color
   */
  void fillRectangle(double x, double y, double width, double height,
    Color fillColor);

  /**
   * Draws the specified rectangle with the given fill color and stroke color.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param width the width
   * @param height the height
   * @param strokeColor the stroke color
   * @param fillColor the fill color
   */
  void drawRectangle(double x, double y, double width, double height,
    Color strokeColor, Color fillColor);

  /**
   * Draws a "rubber band", a dashed rectangle.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param width the width
   * @param height the height
   */
  void drawRubberband(double x, double y, double width, double height);

  /**
   * Returns the clip bounds for this DrawingContext.
   * @return the clip bounds
   */
  Rectangle getClipBounds();

  /**
   * Draws the outline of the specified Shape object.
   * @param shape the Shape object
   * @param fillColor the fill color
   */
  void draw(Shape shape, Color fillColor);

  // ***********************************************************************
  // ***** Drawing text
  // ******************************************

  /**
   * Draws the given text at the specified position.
   * @param text the text
   * @param x the x coordinate
   * @param y the y coordinate
   * @param fontType the FontType
   */
  void drawLabel(String text, double x, double y, FontType fontType);

  void drawLabel(String text, double x, double y, FontType fontType, Color fontColor);
  
  /**
   * Returns the font for the specified font type.
   * @param fontType the font type
   * @return the font
   */
  Font getFont(FontType fontType);

  /**
   * Returns the font for the specified font type.
   * @param fontType the font type
   * @return the FontMetrics
   */
  FontMetrics getFontMetrics(FontType fontType);

  /**
   * Exposes the Graphics2D object.
   * @return the Graphics2D object
   */
  Graphics2D getGraphics2D();

  void draw(Shape shape, Color fillColor, char content, Color fontColor, FontType fontType);
  
  void setStrokeType(StrokeType strokeType);
	
  StrokeType getStrokeType();
	
}
