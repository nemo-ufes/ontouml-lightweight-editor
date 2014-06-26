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
import java.io.Serializable;

/**
 * This class is an implementation of Dimension2D with double precision. Since
 * the JDK only provides the integer precision variant, we put a very simple one
 * here.
 * @author Wei-ju Wu
 */
public class DoubleDimension extends Dimension2D implements Serializable {

  private static final long serialVersionUID = -4062411314479728855L;
  private double width, height;

  /**
   * Default constructor.
   */
  public DoubleDimension() { }

  /**
   * Constructor with parameters.
   * @param w the width
   * @param h the height
   */
  public DoubleDimension(double w, double h) {
    setSize(w, h);
  }

  /**
   * {@inheritDoc}
   */
  public void setSize(double w, double h) {
    width = w;
    height = h;
  }

  /**
   * {@inheritDoc}
   */
  public double getHeight() { return height; }

  /**
   * {@inheritDoc}
   */
  public double getWidth() { return width; }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "DoubleDimension[" + width + ", " + height + "]";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (o instanceof DoubleDimension) {
      DoubleDimension dim = (DoubleDimension) o;
      return width == dim.width && height == dim.height;
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return (int) (width + height);
  }
}
