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

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * This class offers a few mathematical utility functions that Java2D does not
 * offer.
 *
 * @author Wei-ju Wu
 */
public final class GeometryUtil {

  private static final double EPS = 0.001;

  /**
   * The orientation for the first line segment if two segments are created.
   */
  public enum Orientation {
    HORIZONTAL, VERTICAL, UNDEFINED
  }

  private static GeometryUtil instance = new GeometryUtil();

  /**
   * Returns the singleton instance.
   * @return the singleton instance
   */
  public static GeometryUtil getInstance() { return instance; }

  /**
   * Private constructor.
   */
  private GeometryUtil() { }

  /**
   * Because of rounding errors caused by scaling, a custom outcode() method
   * is provided here.
   * @param bounds the Rectangle2D
   * @param point the Point2D
   * @return the outcode
   */
  public int outcode(Rectangle2D bounds, Point2D point) {
    return bounds.outcode(Math.round(point.getX()), Math.round(point.getY()));
  }

  /**
   * Returns the orientation of the segment formed by the two points.
   * @param p1 the first point
   * @param p2 the second point
   * @return the orientation
   */
  public Orientation getSegmentOrientation(Point2D p1, Point2D p2) {
    if (!equals(p1.getX(), p2.getX()) && equals(p1.getY(), p2.getY())) {
      return Orientation.HORIZONTAL;
    }
    if (equals(p1.getX(), p2.getX()) && !equals(p1.getY(), p2.getY())) {
      return Orientation.VERTICAL;
    }
    return Orientation.UNDEFINED;
  }

  /**
   * Provides a tolerant method to test two double values for equality.
   * @param value1 the first value
   * @param value2 the second value
   * @return true if value1 and value2 are close enough to each other, false
   * otherwise
   */
  public boolean equals(double value1, double value2) {
    return Math.abs(value1 - value2) < EPS;
  }

  /**
   * Returns true if the ranges r1 and r2 overlap.
   * Precondition: r1start <= r1end and r2start <= r2end
   * @param r1start the start of the first range
   * @param r1end the end of the first range
   * @param r2start the start of the second range
   * @param r2end the end of the second range
   * @return true if r1 and r2 overlap, false otherwise
   */
  public boolean rangesOverlap(double r1start, double r1end, double r2start,
    double r2end) {
    return ((r1start >= r2start && r1start <= r2end) ||
            (r1end >= r2start && r1end <= r2end) ||
            (r2start >= r1start && r2start <= r1end) ||
            (r2end >= r1start && r2end <= r1end));
  }

  /**
   * Computes the intersection point of the two specified line segments and
   * stores the result in intersectionPoint. It seems that this is a
   * computational harder problem than determining whether two line segments
   * intersect each other, as it is not included in the java.awt.geom package.
   * This function is used to determine the visual connection points between
   * a shape and a connection.
   * This is actually a Java adaptation of the function found on:
   * http://www.whisqu.se/per/docs/math28.htm
   * @param line0 line 0
   * @param line1 line 1
   * @param intersectionPoint the result is stored into this variable
   */
  public void computeLineIntersection(Line2D line0, Line2D line1,
    Point2D intersectionPoint) {

    // constants of linear equations
    double a0, b0, c0, a1, b1, c1;
    // the inverse of the determinant of the coefficient matrix
    double inverseDet;
    // the slopes of each line
    double m0, m1;

    // compute slopes, note the cludge for infinity, however, this will
    // be close enough
    if ((line0.getX2() - line0.getX1()) != 0) {
      m0 = (line0.getY2() - line0.getY1()) / (line0.getX2() - line0.getX1());
    } else {
      m0 = 1e+10;   // close enough to infinity
    }

    if ((line1.getX2() - line1.getX1()) != 0) {
      m1 = (line1.getY2() - line1.getY1()) / (line1.getX2() - line1.getX1());
    } else {
      m1 = 1e+10;   // close enough to infinity
    }

    // compute constants
    a0 = m0;
    a1 = m1;
    b0 = -1;
    b1 = -1;

    c0 = (line0.getY1() - m0 * line0.getX1());
    c1 = (line1.getY1() - m1 * line1.getX1());

    // compute the inverse of the determinate
    inverseDet = 1 / (a0 * b1 - a1 * b0);

    // use Kramers rule to compute xi and yi
    double ix = (b0 * c1 - b1 * c0) * inverseDet;
    double iy = (a1 * c0 - a0 * c1) * inverseDet;
    intersectionPoint.setLocation(ix, iy);
  }
}
