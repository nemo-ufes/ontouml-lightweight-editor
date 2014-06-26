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

/**
 * A Scaling is a collection of drawing settings for a specific scale factor.
 *
 * @author Wei-ju Wu
 */
public final class Scaling {

  private double scaleFactor;
  public static final Scaling SCALING_50 = new Scaling(0.5);
  public static final Scaling SCALING_55 = new Scaling(0.55);
  public static final Scaling SCALING_60 = new Scaling(0.60);
  public static final Scaling SCALING_65 = new Scaling(0.65);
  public static final Scaling SCALING_70 = new Scaling(0.70);
  public static final Scaling SCALING_75 = new Scaling(0.75);
  public static final Scaling SCALING_80 = new Scaling(0.80);
  public static final Scaling SCALING_85 = new Scaling(0.85);
  public static final Scaling SCALING_90 = new Scaling(0.90);
  public static final Scaling SCALING_95 = new Scaling(0.95);
  public static final Scaling SCALING_100 = new Scaling(1.0);
  public static final Scaling SCALING_105 = new Scaling(1.05);
  public static final Scaling SCALING_110 = new Scaling(1.10);
  public static final Scaling SCALING_115 = new Scaling(1.15);
  public static final Scaling SCALING_120 = new Scaling(1.20);
  public static final Scaling SCALING_125 = new Scaling(1.25);
  public static final Scaling SCALING_130 = new Scaling(1.30);
  public static final Scaling SCALING_135 = new Scaling(1.35);
  public static final Scaling SCALING_140 = new Scaling(1.40);  
  public static final Scaling SCALING_145 = new Scaling(1.45);
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
  
  public static Scaling getScaling (double value)
  {
	  if (new Double(value).compareTo(new Double(1.00))==0) return SCALING_100;
	  else if (new Double(value).compareTo(new Double(0.95))==0) return SCALING_95;
	  else if (new Double(value).compareTo(new Double(1.05))==0) return SCALING_105;
	  else if (new Double(value).compareTo(new Double(0.90))==0) return SCALING_90;
	  else if (new Double(value).compareTo(new Double(1.10))==0) return SCALING_110;
	  else if (new Double(value).compareTo(new Double(0.85))==0) return SCALING_85;
	  else if (new Double(value).compareTo(new Double(1.15))==0) return SCALING_115;
	  else if (new Double(value).compareTo(new Double(0.80))==0)return SCALING_80;
	  else if (new Double(value).compareTo(new Double(1.20))==0) return SCALING_120;
	  else if (new Double(value).compareTo(new Double(0.75))==0) return SCALING_75;
	  else if (new Double(value).compareTo(new Double(1.25))==0) return SCALING_125;
	  else if (new Double(value).compareTo(new Double(0.70))==0) return SCALING_70;	  
	  else if (new Double(value).compareTo(new Double(1.30))==0) return SCALING_130;
	  else if (new Double(value).compareTo(new Double(0.65))==0)return SCALING_65;
	  else if (new Double(value).compareTo(new Double(1.35))==0)return SCALING_135;
	  else if (new Double(value).compareTo(new Double(0.60))==0)return SCALING_60;
	  else if (new Double(value).compareTo(new Double(1.40))==0) return SCALING_140;
	  else if (new Double(value).compareTo(new Double(0.55))==0) return SCALING_55;
	  else if (new Double(value).compareTo(new Double(1.45))==0) return SCALING_145;
	  else if (new Double(value).compareTo(new Double(0.50))==0) return SCALING_50;
	  else if (new Double(value).compareTo(new Double(1.50))==0) return SCALING_150;
	  return SCALING_100;
  }
  
  public String toString()
  {
	  double value = (scaleFactor*10000)/100;	  
	  return (new Double(value)).toString();
  }
}

