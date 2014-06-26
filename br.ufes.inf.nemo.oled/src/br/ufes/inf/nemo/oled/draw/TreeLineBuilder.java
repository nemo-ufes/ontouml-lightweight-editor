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

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * @author John Guerson
 */
public class TreeLineBuilder extends RectilinearLineBuilder {
	
  /**
   * Static instance.
   */
  private static TreeLineBuilder instance =  new TreeLineBuilder();

  /**
   * Returns the singleton instance.
   * @return the instance
   */
  public static TreeLineBuilder getInstance() {
    return instance;
  }

  /**
   * Private constructor.
   */
  protected TreeLineBuilder() { }

	@Override
	public List<Point2D> calculateLineSegments(Node node1, Node node2) 
	{		
		if(node1.equals(node2)) return calculateSelfLineSegments(node1, node2, node1.getOrigin(), node2.getOrigin());
	    NodeDirection direction = getNodeDirection(node1, node2);
	    switch (direction) {
	    //==================================== (OK)
	      case WEST_EAST: {
	        return super.createHorizontalLineSegment(node1, node2, true);
	      }
	      case EAST_WEST: {
	        return super.createHorizontalLineSegment(node1, node2, false);
	      }
	      //==================================== (OK)
	      case NORTH_SOUTH: {
	    	  ArrayList<Point2D> points = new ArrayList<Point2D>(); 
	    	  points.add(new Point2D.Double(node1.getAbsCenterX(),node1.getAbsoluteY2()));
	    	  points.add(new Point2D.Double(node1.getAbsCenterX(),node2.getAbsoluteY1()-30));
	    	  points.add(new Point2D.Double(node2.getAbsCenterX(),node2.getAbsoluteY1()-30));
	    	  points.add(new Point2D.Double(node2.getAbsCenterX(),node2.getAbsoluteY1()));
	    	  return points;
	      }
	      case SOUTH_NORTH: {
	    	  ArrayList<Point2D> points = new ArrayList<Point2D>();
	    	  points.add(new Point2D.Double(node1.getAbsCenterX(),node1.getAbsoluteY1()));
	    	  points.add(new Point2D.Double(node1.getAbsCenterX(),node2.getAbsoluteY2()+30));
	    	  points.add(new Point2D.Double(node2.getAbsCenterX(),node2.getAbsoluteY2()+30));
	    	  points.add(new Point2D.Double(node2.getAbsCenterX(),node2.getAbsoluteY2()));
	    	  return points;	        
	      }
	      //==================================== (OK)
	      case SE_NW: {	    	  
	    	  ArrayList<Point2D> points = new ArrayList<Point2D>();
	    	  points.add(new Point2D.Double(node1.getAbsCenterX(),node1.getAbsoluteY1()));
	    	  points.add(new Point2D.Double(node1.getAbsCenterX(),node2.getAbsoluteY2()+30));
	    	  points.add(new Point2D.Double(node2.getAbsCenterX(),node2.getAbsoluteY2()+30));
	    	  points.add(new Point2D.Double(node2.getAbsCenterX(),node2.getAbsoluteY2()));
	    	  return points;
	      }
	      case SW_NE: {	    	  
	    	  ArrayList<Point2D> points = new ArrayList<Point2D>(); 
	    	  points.add(new Point2D.Double(node1.getAbsCenterX(),node1.getAbsoluteY1()));
	    	  points.add(new Point2D.Double(node1.getAbsCenterX(),node2.getAbsoluteY2()+30));
	    	  points.add(new Point2D.Double(node2.getAbsCenterX(),node2.getAbsoluteY2()+30));
	    	  points.add(new Point2D.Double(node2.getAbsCenterX(),node2.getAbsoluteY2()));
	    	  return points;
	      }
	      //==================================== (OK)
	      case NW_SE:{	    	  
	    	  ArrayList<Point2D> points = new ArrayList<Point2D>(); 
	    	  points.add(new Point2D.Double(node1.getAbsCenterX(),node1.getAbsoluteY2()));
	    	  points.add(new Point2D.Double(node1.getAbsCenterX(),node2.getAbsoluteY1()-30));
	    	  points.add(new Point2D.Double(node2.getAbsCenterX(),node2.getAbsoluteY1()-30));
	    	  points.add(new Point2D.Double(node2.getAbsCenterX(),node2.getAbsoluteY1()));
	    	  return points;	    	  
	      }
	      case NE_SW:{	    	  
	    	  ArrayList<Point2D> points = new ArrayList<Point2D>(); 
	    	  points.add(new Point2D.Double(node1.getAbsCenterX(),node1.getAbsoluteY2()));
	    	  points.add(new Point2D.Double(node1.getAbsCenterX(),node2.getAbsoluteY1()-30));
	    	  points.add(new Point2D.Double(node2.getAbsCenterX(),node2.getAbsoluteY1()-30));
	    	  points.add(new Point2D.Double(node2.getAbsCenterX(),node2.getAbsoluteY1()));
	    	  return points;	    	  
	      }
	      default: {	    	  
	    	  return super.calculateLineSegments(node1, node2);	    	  
	      }
	    }		
	}
	
	public List<Point2D> calculateHorizontalLineSegments(Node node1, Node node2) 
	{		
		if(node1.equals(node2)) return calculateSelfLineSegments(node1, node2, node1.getOrigin(), node2.getOrigin());
	    NodeDirection direction = getNodeDirection(node1, node2);
	    switch (direction) {
	      // ==================================== (OK)
	      case NORTH_SOUTH: {
	    	  return super.createVerticalLineSegment(node1, node2, false);
	      }
	      case SOUTH_NORTH: {
	    	  return super.createVerticalLineSegment(node1, node2, false);
	      }
	      // ==================================== (OK) 
	      case WEST_EAST: {
	    	  ArrayList<Point2D> points = new ArrayList<Point2D>(); 
	    	  points.add(new Point2D.Double(node1.getAbsoluteX2(),node1.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX1()-30,node1.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX1()-30,node2.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX1(),node2.getAbsCenterY()));
	    	  return points;
	      }
	      case EAST_WEST: {
	    	  ArrayList<Point2D> points = new ArrayList<Point2D>();  
	    	  points.add(new Point2D.Double(node1.getAbsoluteX1(),node1.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX2()+30,node1.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX2()+30,node2.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX2(),node2.getAbsCenterY()));
	    	  return points;	        
	      }
	      //==================================== (OK)
	      case SE_NW: {	  
	    	  ArrayList<Point2D> points = new ArrayList<Point2D>();  
	    	  points.add(new Point2D.Double(node1.getAbsoluteX1(),node1.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX2()+30,node1.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX2()+30,node2.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX2(),node2.getAbsCenterY()));
	    	  return points;	      
	      }	     
	      case NE_SW:{	    	  
	    	  ArrayList<Point2D> points = new ArrayList<Point2D>();
	    	  points.add(new Point2D.Double(node1.getAbsoluteX1(),node1.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX2()+30,node1.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX2()+30,node2.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX2(),node2.getAbsCenterY()));
	    	  return points;	    	 	    	  
	      }
	      //==================================== (OK)
	      case SW_NE: {	    	  
	    	  ArrayList<Point2D> points = new ArrayList<Point2D>(); 
	    	  points.add(new Point2D.Double(node1.getAbsoluteX2(),node1.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX1()-30,node1.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX1()-30,node2.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX1(),node2.getAbsCenterY()));
	    	  return points;
	      }
	      case NW_SE:{	    	  
	    	  ArrayList<Point2D> points = new ArrayList<Point2D>(); 
	    	  points.add(new Point2D.Double(node1.getAbsoluteX2(),node1.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX1()-30,node1.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX1()-30,node2.getAbsCenterY()));
	    	  points.add(new Point2D.Double(node2.getAbsoluteX1(),node2.getAbsCenterY()));
	    	  return points;    	  
	      }
	      default: {	    	  
	    	  return super.calculateLineSegments(node1, node2);	    	  
	      }
	    }		
	}

}
