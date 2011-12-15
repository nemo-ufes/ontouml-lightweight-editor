/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.inf.ufes.nemo.oled.draw;

/**
 * A Scaling is a collection of drawing settings for a specific scale factor.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public final class Scaling {

  private static final long serialVersionUID = 6922910336017674609L;
  private double scaleFactor;
  public static final Scaling SCALING_50 = new Scaling(0.5);
  public static final Scaling SCALING_75 = new Scaling(0.75);
  public static final Scaling SCALING_100 = new Scaling(1.0);
  public static final Scaling SCALING_150 = new Scaling(1.5);

  /**
   * Constructor.
   * @param scaleFactor the scaling factor
   */
  private Scaling(double scaleFactor) {
    this.scaleFactor = scaleFactor;
  }

  /**
   * Returns the scaling factor.
   * @return the scaling factor
   */
  public double getScaleFactor() { return scaleFactor; }
}
