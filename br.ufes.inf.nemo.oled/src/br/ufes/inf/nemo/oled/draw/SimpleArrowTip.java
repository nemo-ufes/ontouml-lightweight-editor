/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
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

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * This class implements a simple arrow tip as used in dependencies and
 * navigations.
 *
 * @author Wei-ju Wu
 */
public class SimpleArrowTip {

  /**
   * Draws the arrow.
   * @param drawingContext the drawing context
   * @param endpoint the end point to draw the arrow at
   * @param rotationTransform the rotation
   */
  public void draw(DrawingContext drawingContext, Point2D endpoint,
    AffineTransform rotationTransform) {
    Point2D arrowPoint0 = new Point2D.Double(),
            arrowPoint1 = new Point2D.Double();
    arrowPoint0.setLocation(endpoint.getX() - 10,
                            endpoint.getY() - 4);
    arrowPoint1.setLocation(endpoint.getX() - 10,
                            endpoint.getY() + 4);
    rotationTransform.transform(arrowPoint0, arrowPoint0);
    rotationTransform.transform(arrowPoint1, arrowPoint1);

    drawingContext.drawLine(arrowPoint0.getX(), arrowPoint0.getY(),
      endpoint.getX(), endpoint.getY());
    drawingContext.drawLine(arrowPoint1.getX(), arrowPoint1.getY(),
      endpoint.getX(), endpoint.getY());
  }
}
