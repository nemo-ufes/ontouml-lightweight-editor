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
import java.util.Collection;


/**
 * The base interface for Nodes that can contain other chidren. To allow for
 * separation of leaf nodes and non-left Nodes, a specific composite node
 * is introduced.
 * @author Wei-ju Wu
 */
public interface CompositeNode extends Node, CompositeElement {

  /**
   * Determines all the children that lie in the specified bounds and delegates
   * to the method to the child composites if necessary. The combined results
   * are stored in the resultCollection.
   * @param bounds the bounds
   * @param resultCollection the resulting collection
   */
  void getChildrenInSpecifiedBounds(Rectangle2D bounds,
    Collection<DiagramElement> resultCollection);

  /**
   * Brings the child in front of other children in the drawing order.
   * @param child the child to bring to the front
   */
  void bringChildToFront(DiagramElement child);

  /**
   * Puts the child to the back of other children in drawing order.
   * @param child the child to bring to the back
   */
  void putChildToBack(DiagramElement child);
}
