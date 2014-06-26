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

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * This interface generalizes a visible element within the TinyUML editor.
 * Note that DiagramElements have to be Serializable, because the data is
 * written using Java serialization.
 * @author Wei-ju Wu
 */
public interface DiagramElement extends Serializable, Cloneable {

  /**
   * Defines the clone() method as public.
   * @return the cloned object
   */
  Object clone();

  /**
   * Returns the element's bounding box in absolute coordinates.
   * @return the bounding box
   */
  Rectangle2D getAbsoluteBounds();

  /**
   * Determines whether this allElements intersects with the specified Rectangle2D.
   * @param bounds the rectangle to check against
   * @return true if intersects, false otherwise
   */
  boolean intersects(Rectangle2D bounds);

  /**
   * Determines whether a point lies within this UmlShape. This method is used
   * in order to select items in the editor.
   * @param xcoord the x coordinate
   * @param ycoord the y coordinate
   * @return true if the specified point is within the shape, false otherwise
   */
  boolean contains(double xcoord, double ycoord);

  /**
   * Determines whether this element is visible within the specified bounds.
   * @param bounds the bounds
   * @return true if visible, false otherwise
   */
  boolean isVisible(Rectangle2D bounds);

  /**
   * Renders this shape into the specified DrawingContext object.
   * @param drawingContext the drawing context object
   */
  void draw(DrawingContext drawingContext);

  /**
   * Every UmlShape except for the top-level Shape (the diagram itself) has a
   * parent. The shape's coordinates are defined in the parent's local
   * coordinate system.
   * @return this Shape's parent element
   */
  CompositeNode getParent();

  /**
   * a helper method which returns the elements diagram parent 
   * @return the elements diagram parent 
   */
  Diagram getDiagram();
    
  void addedToDiagram(Diagram diagram);
  
  /**
   * Recursively goes up the hierarchy of parents and returns true if element
   * is an ancestor of this element.
   * @param element the element to check
   * @return true if ancestor, false otherwise
   */
  boolean isAncestor(DiagramElement element);

  /**
   * Sets the parent of this element.
   * @param parent the parent element
   */
  void setParent(CompositeNode parent);

  /**
   * Recalculates changes to internal sizes.
   * @param drawingContext the DrawingContext
   */
  void recalculateSize(DrawingContext drawingContext);

  /**
   * Returns the Selection object that is associated with this element.
   * @param operations a DiagramOperations object
   * @return the Selection object
   */
  Selection getSelection(DiagramOperations operations);

  /**
   * Invalidates the internal layout.
   */
  void invalidate();

  /**
   * Returns true if this element's layout is valid.
   * @return true if layout is valid, false otherwise
   */
  boolean isValid();

  /**
   * Returns the Label at the specified position if available. Returns null if
   * no Label is available at the position.
   * @param mx the x coordinate
   * @param my the y coordinate
   * @return the Label at the position or null
   */
  Label getLabelAt(double mx, double my);

  // ***********************************************************************
  // **** Nesting information:
  // **** It was decided to include this on the fundamental draw level,
  // **** because selection handling is by definition a fundamental
  // **** concept. An alternative would have been to define it on the
  // **** UML level, but with the selection handling defined in draw,
  // **** it better fits here.
  // **********************************************************************

  /**
   * This property indicates whether this DiagramElement can nest other
   * allElements. This is defined separately from containment in a composite
   * node, because a composite does not automatically mean that it can
   * nest other allElements. In fact, the default value is false and at the
   * moment only makes sense for PackageElements.
   * @return true if nestable, false otherwise
   */
  boolean canNestElements();

  /**
   * This property indicates whether this DiagramElement can be nested in
   * other allElements. By default (and throughout the primitive allElements in the
   * draw package), this is false and should be overridden in client packages.
   * @return true if the element is nestable in other allElements, false otherwise
   */
  boolean isNestable();
}
