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
import java.util.ArrayList;
import java.util.List;

/**
 * A neutral selection. By using a NullSelection object, a lot of null related
 * logic and errors can be avoided. The methods do nothing, and only return
 * a meaningful default if necessary.
 * @author Wei-ju Wu
 */
public final class NullSelection implements Selection {

  private static NullSelection instance = new NullSelection();

  /**
   * Returns the singleton instance.
   * @return the singleton instance
   */
  public static NullSelection getInstance() {
    return instance;
  }

  /**
   * Private constructor.
   */
  private NullSelection() { }

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
  public boolean isDragging() { return false; }

  /**
   * {@inheritDoc}
   */
  public void startDragging(double xcoord, double ycoord) { }

  /**
   * {@inheritDoc}
   */
  public void stopDragging(double xcoord, double ycoord) { }

  /**
   * {@inheritDoc}
   */
  public void cancelDragging() { }

  /**
   * {@inheritDoc}
   */
  public void updatePosition(double xcoord, double ycoord) { }

  /**
   * {@inheritDoc}
   */
  public void draw(DrawingContext drawingContext) { }

  /**
   * {@inheritDoc}
   */
  public boolean contains(double xcoord, double ycoord) { return false; }

  /**
   * {@inheritDoc}
   */
  public Cursor getCursorForPosition(double xcoord, double ycoord) {
    return Cursor.getDefaultCursor();
  }

  /**
   * {@inheritDoc}
   */
  public void updateDimensions() { }
}
