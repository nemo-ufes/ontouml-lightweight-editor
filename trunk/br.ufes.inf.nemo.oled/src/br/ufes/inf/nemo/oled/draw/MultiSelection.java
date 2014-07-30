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

import java.awt.Cursor;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.ufes.inf.nemo.oled.util.Command;


/**
 * This class is a Selection that contains more than one Selections.
 *
 * @author Wei-ju Wu
 */
public class MultiSelection implements Selection {

  private List<DiagramElement> elements;
  private Collection<Selection> selections = new ArrayList<Selection>();
  private Point2D startPos = new Point2D.Double();
  private Point2D anchor = new Point2D.Double();
  private Point2D tmpPos = new Point2D.Double();
  private DiagramOperations editor;
  private Rectangle2D bounds = new Rectangle2D.Double();
  private boolean isDragging = false;
  
  /**
   * Constructor.
   * @param operations the DiagramOperations object
   * @param theElements the diagram allElements that are the base for this object
   */
  public MultiSelection(DiagramOperations operations,
    List<DiagramElement> theElements) {
    editor = operations;
    elements = theElements;
    for (DiagramElement element : elements) {
      selections.add(element.getSelection(operations));
    }
  }

  /**
   * {@inheritDoc}
   */
  public DiagramElement getElement() {
	  return null;
  }

  /**
   * {@inheritDoc}
   */
  public List<DiagramElement> getElements() {
    return elements;
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
  public void startDragging(double x, double y) {
	
    isDragging = true;
    startPos.setLocation(x, y);
    anchor.setLocation(x, y);
    double minx = Double.MAX_VALUE, miny = Double.MAX_VALUE;
    double maxy = Double.MIN_VALUE, maxx = Double.MIN_VALUE;
    for (DiagramElement element : elements) {
      Rectangle2D elemBounds = element.getAbsoluteBounds();
      minx = Math.min(minx, elemBounds.getX());
      miny = Math.min(miny, elemBounds.getY());
      maxx = Math.max(maxx, elemBounds.getX() + elemBounds.getWidth());
      maxy = Math.max(maxy, elemBounds.getY() + elemBounds.getHeight());
    }
    bounds.setRect(minx, miny, maxx - minx, maxy - miny);
  }

  /**
   * {@inheritDoc}
   */
  public void stopDragging(double x, double y) {  
	//Test if there was an effective move or resize
	if(x != startPos.getX() || y != startPos.getY())
	{
	    List<Command> moveOperations = new ArrayList<Command>();
	    double transx = tmpPos.getX() - bounds.getX();
	    double transy = tmpPos.getY() - bounds.getY();
	    for (DiagramElement element : elements) {
	      if (element instanceof Node) {
	        addMoveNodeOperation(moveOperations, (Node) element, transx, transy);
	      } else if (element instanceof Connection) {
	        addTranslateConnectionOperations(moveOperations, (Connection) element,
	          transx, transy);	        
	      }
	    }
	    
	    //FIXME Problem while moving groups of elements
	    editor.moveElements(moveOperations.toArray(new MoveOperation[0]));
 
	    updateDimensions();
	}
    isDragging = false;    
  }

  /**
   * Adds a command for moving nodes.
   * @param moveOperations the list of move operations
   * @param node the node to move
   * @param transx the x translation
   * @param transy the y translation
   */
  private void addMoveNodeOperation(List<Command> moveOperations, Node node, double transx, double transy) {
    Point2D targetPos = new Point2D.Double(node.getAbsoluteX1() + transx, node.getAbsoluteY1() + transy);
    moveOperations.add(new MoveNodeOperation(node, node.getParent(), targetPos));
  }

  /**
   * Adds a command for translating connection points.
   * @param moveOperations the list of move operations
   * @param conn the connection
   * @param transx the x translation
   * @param transy the y translation
   */
  private void addTranslateConnectionOperations(List<Command> moveOperations,
    Connection conn, double transx, double transy) {
    moveOperations.add(new TranslateConnectionOperation(conn, transx, transy));
  }

  /**
   * {@inheritDoc}
   */
  public void cancelDragging() { isDragging = false; }

  /**
   * {@inheritDoc}
   */
  public void updatePosition(double xcoord, double ycoord) {
    double diffx = xcoord - anchor.getX();
    double diffy = ycoord - anchor.getY();
    Diagram diagram = editor.getDiagram();
    // Can not move out of the left border
    if (bounds.getX() + diffx < diagram.getOrigin().getX()) {
      diffx = diagram.getOrigin().getX() - bounds.getX();
    }
    // and not out of the top border
    if (bounds.getY() + diffy < diagram.getOrigin().getY()) {
      diffy = diagram.getOrigin().getY() - bounds.getY();
    }

    tmpPos.setLocation(bounds.getX() + diffx, bounds.getY() + diffy);
    diagram.snap(tmpPos);
  }

  /**
   * {@inheritDoc}
   */
  public void draw(DrawingContext drawingContext) {
    if (isDragging()) {
      drawingContext.drawRectangle(tmpPos.getX(), tmpPos.getY(),
        bounds.getWidth(), bounds.getHeight(), null);
    } else {
      for (Selection selection : selections) {
        selection.draw(drawingContext);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public boolean contains(double xcoord, double ycoord) {
    for (Selection selection : selections) {
      if (selection.contains(xcoord, ycoord)) return true;
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  public Cursor getCursorForPosition(double xcoord, double ycoord) {
    return Cursor.getDefaultCursor();
  }

  /**
   * {@inheritDoc}
   */
  public void updateDimensions() {
    for (Selection selection : selections) {
      selection.updateDimensions();
    }
  }
}
