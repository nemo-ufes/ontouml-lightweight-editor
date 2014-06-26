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

import java.io.Serializable;

/**
 * This class contains default sizes such as margins that are shared across
 * the the drawing package.
 *
 * @author Wei-ju Wu
 */
public final class Defaults implements Serializable {

  private static final long serialVersionUID = -7192992504021225602L;
  private static Defaults instance = new Defaults();
  private static final double DEFAULT_MARGIN = 5;

  /**
   * Returns the singleton instance.
   * @return the singleton instance
   */
  public static Defaults getInstance() { return instance; }

  /**
   * Private constructor.
   */
  private Defaults() { }

  /**
   * Returns the left margin.
   * @return the left margin
   */
  public double getMarginLeft() { return DEFAULT_MARGIN; }

  /**
   * Returns the right margin.
   * @return the right margin
   */
  public double getMarginRight() { return DEFAULT_MARGIN; }

  /**
   * Returns the sum of the side margins.
   * @return the sum of the side margins
   */
  public double getMarginSide() { return getMarginLeft() + getMarginRight(); }

  /**
   * Returns the top margin.
   * @return the top margin
   */
  public double getMarginTop() { return DEFAULT_MARGIN; }

  /**
   * Returns the bottom margin.
   * @return the bottom margin
   */
  public double getMarginBottom() { return DEFAULT_MARGIN; }
}
