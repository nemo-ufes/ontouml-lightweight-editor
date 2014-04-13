package br.ufes.inf.nemo.oled.draw;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

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
	    NodeDirection direction = getNodeDirection(node1, node2);
	    switch (direction) {
	      case WEST_EAST: {
	        return super.createHorizontalLineSegment(node1, node2, true);
	      }
	      case EAST_WEST: {
	        return super.createHorizontalLineSegment(node1, node2, false);
	      }
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
	    NodeDirection direction = getNodeDirection(node1, node2);
	    switch (direction) {
	      case WEST_EAST: {
	        return super.createHorizontalLineSegment(node1, node2, true);
	      }
	      case EAST_WEST: {
	        return super.createHorizontalLineSegment(node1, node2, false);
	      }
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

}
