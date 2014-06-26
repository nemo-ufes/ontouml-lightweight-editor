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

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * An abstract class that implements the additional functionality introduced
 * in the CompositeNode interface.
 *
 * @author Wei-ju Wu
 */
public abstract class AbstractCompositeNode extends AbstractNode
implements CompositeNode {

  private static final long serialVersionUID = 3667605559862445493L;
  private List<DiagramElement> children = new LinkedList<DiagramElement>();

  /**
   * {@inheritDoc}
   */
  @Override
  public Object clone() {
    AbstractCompositeNode cloned = null;
    cloned = (AbstractCompositeNode) super.clone();
    cloned.children = new LinkedList<DiagramElement>();
    for (DiagramElement element : children) {
      DiagramElement clonedChild = (DiagramElement) element.clone();
      clonedChild.setParent(cloned);
      cloned.children.add(clonedChild);
    }
    return cloned;
  }

  /**
   * Returns the children in this node. Mainly for testing purposes.
   * @return the children
   */
  public List<DiagramElement> getChildren() { return children; }

  /**
   * {@inheritDoc}
   */
  public void addChild(DiagramElement child) {
    if (!children.contains(child)) {
      child.setParent(this);
      children.add(child);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void removeChild(DiagramElement child) {
	  if(children.contains(child))
		  children.remove(child);
  }

  /**
   * {@inheritDoc}
   */
  public void bringChildToFront(DiagramElement child) {
    // If we want to use getChildren() here, we need to take care
    // of the UmlDiagram, which joins the children collection with the connections
    // A solution would be to implement the diagram through delegation
    children.remove(child);
    children.add(child);
  }

  /**
   * {@inheritDoc}
   */
  public void putChildToBack(DiagramElement child) {
    // If we want to use getChildren() here, we need to take care
    // of the UmlDiagram, which joins the children collection with the connections
    children.remove(child);
    children.add(0, child);
  }

  /**
   * Returns the child element at the specified coordinates.
   * @param x the x coordinate
   * @param y the y coordinate
   * @return the child element at the specified position
   */
  public DiagramElement getChildAt(double x, double y) {
    // This is a little trick: We check children in the reverse drawing
    // order, so it there is an element in front of another, we select
    // the element in the front
    ListIterator<DiagramElement> iter =
      getChildren().listIterator(getChildren().size());
    while (iter.hasPrevious()) {
      DiagramElement child = iter.previous();
      if (child.contains(x, y)) {
        return getDeepestElement(child, x, y);
      }
    }
    return NullElement.getInstance();
  }

  /**
   * Returns the deepest nested element at the specified position. This function
   * searches its descendant hierarchy recursively and returns either said
   * deepest child at that position or the element itself if no child was found.
   * @param element the element to search
   * @param x the x position
   * @param y the y position
   * @return either the deepest child or the element itself
   */
  private DiagramElement getDeepestElement(DiagramElement element, double x,
    double y) {
    DiagramElement deepestChild = NullElement.getInstance();
    if (element instanceof CompositeNode) {
      deepestChild = ((CompositeNode) element).getChildAt(x, y);
    }
    return deepestChild != NullElement.getInstance() ? deepestChild : element;
  }

  /**
   * {@inheritDoc}
   */
  public void draw(DrawingContext drawingContext) {
    Rectangle clipBounds = drawingContext.getClipBounds();
    for (DiagramElement child : children) {
      if (child.isVisible(clipBounds)) {
   		  child.draw(drawingContext);   		  
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public void recalculateSize(DrawingContext drawingContext) {
    for (DiagramElement child : children) {
      child.recalculateSize(drawingContext);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void getChildrenInSpecifiedBounds(Rectangle2D bounds,
    Collection<DiagramElement> resultCollection) {
    if (intersects(bounds)) {
      for (DiagramElement child : getChildren()) {
        if (child.intersects(bounds)) {
          resultCollection.add(child);
          // Recursively ask the child
          if (child instanceof CompositeNode) {
            ((CompositeNode) child).getChildrenInSpecifiedBounds(bounds,
              resultCollection);
          }
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void notifyNodeMoved() {
    super.notifyNodeMoved();
    for (DiagramElement child : getChildren()) {
      if (child instanceof AbstractNode) {
        ((AbstractNode) child).notifyNodeMoved();
      }
    }
  }
}
