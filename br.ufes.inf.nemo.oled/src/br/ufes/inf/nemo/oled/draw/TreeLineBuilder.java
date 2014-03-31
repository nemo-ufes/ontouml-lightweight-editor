package br.ufes.inf.nemo.oled.draw;

import java.awt.geom.Point2D;
import java.util.List;

import br.ufes.inf.nemo.oled.draw.GeometryUtil.Orientation;

public class TreeLineBuilder extends RectilinearLineBuilder {
	
	@Override
	public List<Point2D> calculateLineSegments(Node node1, Node node2) 
	{		
	    NodeDirection direction = getNodeDirection(node1, node2);
	    switch (direction) {
	      case WEST_EAST:
	        return super.createHorizontalLineSegment(node1, node2, true);
	      case EAST_WEST:
	        return super.createHorizontalLineSegment(node1, node2, false);
	      case NORTH_SOUTH:
	        return super.createVerticalLineSegment(node1, node2, true);
	      case SOUTH_NORTH:
	        return super.createVerticalLineSegment(node1, node2, false);
	      case SE_NW:
	      {
	    	  	    	  
	    	  return super.calculateLineSegments(node1.getAbsoluteX1(), node1.getAbsCenterY(), node2.getAbsCenterX(), node2.getAbsoluteY2(),  Orientation.HORIZONTAL);
	      }
	      case SW_NE:
	        return super.calculateLineSegments(node1.getAbsoluteX2(), node1.getAbsCenterY(), node2.getAbsCenterX(), node2.getAbsoluteY2(),  Orientation.HORIZONTAL);
	      case NW_SE:
	        return super.calculateLineSegments(node1.getAbsoluteX2(), node1.getAbsCenterY(), node2.getAbsCenterX(), node2.getAbsoluteY1(),  Orientation.HORIZONTAL);
	      case NE_SW:
	      default:
	        return super.calculateLineSegments(node1.getAbsoluteX1(), node1.getAbsCenterY(), node2.getAbsCenterX(), node2.getAbsoluteY1(), Orientation.HORIZONTAL);
	    }		
	}
}
