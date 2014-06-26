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

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

/**
 * CornerSnaps are a concept to support snapping while resizing a node
 * selection.
 *
 * @author Wei-ju Wu
 */
public class CornerSnap {

  private Point2D pos;
  private Dimension2D size;
  private double x1diff, y1diff, x2diff, y2diff;
  private double x2adjust, y2adjust;
  private Snapping snapping;

  /**
   * Constructor.
   * @param aSnapping a Snapping object
   * @param aPos a position
   * @param aSize the size
   */
  public CornerSnap(Snapping aSnapping, Point2D aPos, Dimension2D aSize) {
    snapping = aSnapping;
    pos = aPos;
    size = aSize;
  }

  /**
   * Perform a snap on the right lower corner.
   */
  public void snapRightLower() {
    // snap right lower
    double x2 = pos.getX() + size.getWidth();
    double y2 = pos.getY() + size.getHeight();
    Point2D rightLower = new Point2D.Double(x2, y2);
    snapping.snap(rightLower);
    x2diff = x2 - rightLower.getX();
    y2diff = y2 - rightLower.getY();
    makeAdjustments();
  }

  /**
   * Performs a snap on the right upper corner.
   */
  public void snapRightUpper() {
    double x2 = pos.getX() + size.getWidth();
    double y1 = pos.getY();
    Point2D rightUpper = new Point2D.Double(x2, y1);
    snapping.snap(rightUpper);
    y1diff = y1 - rightUpper.getY();
    x2diff = x2 - rightUpper.getX();
    calculateY2Adjust();
    makeAdjustments();
  }

  /**
   * Performs a snap on the left upper corner.
   */
  public void snapLeftUpper() {
    double x1 = pos.getX();
    double y1 = pos.getY();
    // use this as the most generic version to extract a general method
    Point2D leftUpper = new Point2D.Double(x1, y1);
    snapping.snap(leftUpper);
    x1diff = x1 - leftUpper.getX();
    y1diff = y1 - leftUpper.getY();
    calculateX2Adjust();
    calculateY2Adjust();
    makeAdjustments();
  }

  /**
   * Performs a snap on the left lower corner.
   */
  public void snapLeftLower() {
    double x1 = pos.getX();
    double y2 = pos.getY() + size.getHeight();
    // use this as the most generic version to extract a general method
    Point2D leftLower = new Point2D.Double(x1, y2);
    snapping.snap(leftLower);
    x1diff = x1 - leftLower.getX();
    y2diff = y2 - leftLower.getY();
    calculateX2Adjust();
    makeAdjustments();
  }

  /**
   * Calculates adjustments to the x2 coordinate which need to be made in
   * order to keep the x2 value at its previous position.
   */
  private void calculateX2Adjust() {
    double x2 = pos.getX() + size.getWidth();
    x2adjust = x2 -
      ((pos.getX() - x1diff) + (size.getWidth() - x1diff));
  }

  /**
   * Calculates adjustments to the y2 coordinate which need to be made in
   * order to keep the y2 value at its previous position.
   */
  private void calculateY2Adjust() {
    double y2 = pos.getY() + size.getHeight();
    y2adjust = y2 - ((pos.getY() - y1diff) + (size.getHeight() - y1diff));
  }

  /**
   * Performs the adjustments on the position and size objects.
   */
  private void makeAdjustments() {
    pos.setLocation(pos.getX() - x1diff, pos.getY() - y1diff);
    size.setSize(size.getWidth() - x1diff - x2diff + x2adjust,
      size.getHeight() - y1diff - y2diff + y2adjust);
  }
}
