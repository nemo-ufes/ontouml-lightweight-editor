package br.ufes.inf.nemo.oled.draw;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import br.ufes.inf.nemo.oled.draw.GeometryUtil.Orientation;

public class TreeLineConnectMethod extends RectilinearLineConnectMethod {

	  private static TreeLineConnectMethod instance = new TreeLineConnectMethod();

	  /**
	   * Returns the singleton instance.
	   * @return the singleton instance
	   */
	  public static TreeLineConnectMethod getInstance() { return instance; }

	  /**
	   * Private constructor.
	   */
	  protected TreeLineConnectMethod() { }

	  //FIXME To enable associations like material to source
	  //FIXME - problem in "conn.setPoints(linepoints)" method, return null...
			  
	  /** Node to Node */
	  @Override
	  public void generateAndSetPointsToConnection(Connection conn, Node sourceNode, Node targetNode, Point2D source, Point2D dest) 
	  {
		  TreeLineBuilder linebuilder = TreeLineBuilder.getInstance();
		  List<Point2D> points=null;
		  if (sourceNode.equals(targetNode)) points = linebuilder.calculateSelfLineSegments(sourceNode, targetNode, source, dest);
		  else points = linebuilder.calculateLineSegments(sourceNode, targetNode);
		    List<Point2D> linepoints = new LinkedList<Point2D>();
		    for (Point2D point : points) linepoints.add(point);
		    
		    // calculate intersections with the nodes
		    Line2D line = new Line2D.Double();
		    // first check if we could start at the second segment
		    if (points.size() > 2) { line.setLine(points.get(1), points.get(2)); }

		    // if not, start at the first segment
		    if (points.size() > 2 && sourceNode.intersects(line)) linepoints.remove(0);
		    else line.setLine(points.get(0), points.get(1));	    
		    sourceNode.calculateIntersection(line, linepoints.get(0));

		    // last
		    // check if we could end at the segment before the last one if yes,
		    // remove the last control point
		    if (points.size() > 2) {
		      line.setLine(points.get(points.size() - 3), points.get(points.size() - 2));
		      if (targetNode.intersects(line)) {
		        linepoints.remove(linepoints.size() - 1);
		      } else {
		        line.setLine(points.get(points.size() - 2), points.get(points.size() - 1));
		      }
		    }
		    
		    targetNode.calculateIntersection(line, linepoints.get(linepoints.size() - 1)); 
		    conn.setPoints(linepoints);
	   }
			  
	  /**
	   * {@inheritDoc} Draw Line Segments
	   */
	  @Override
	  public void drawLineSegments(DrawingContext drawingContext, Point2D source, Point2D dest) 
	  {
	    // draw in a right angle
	    TreeLineBuilder linebuilder = TreeLineBuilder.getInstance();
	    List<Point2D> points = linebuilder.calculateLineSegments(source, dest, Orientation.HORIZONTAL);
	    
	    if (points.size() > 0) 
	    {
	      Point2D lastPoint = points.get(0);
	      Point2D nextPoint;
	      for (int i = 1; i < points.size(); i++) 
	      {
	        nextPoint = points.get(i);
	        drawingContext.drawLine(lastPoint.getX(), lastPoint.getY(),  nextPoint.getX(), nextPoint.getY());
	        lastPoint = nextPoint;
	      }
	    }
	  }
			  
		/** Node to Connection */
	  	@Override
		public void generateAndSetPointsToConnection(Connection conn, Node sourceNode, Connection targetConnection, Point2D source, Point2D dest) 
		{
	  		TreeLineBuilder linebuilder = TreeLineBuilder.getInstance();
			List<Point2D> points = linebuilder.calculateLineSegments(source, dest, Orientation.HORIZONTAL);
			List<Point2D> linepoints = new LinkedList<Point2D>();
			for (Point2D point : points) linepoints.add(point);
			    
			// calculate intersections with the nodes
			Line2D line = new Line2D.Double();
			// first
			// check if we could start at the second segment
			if (points.size() > 2) line.setLine(points.get(1), points.get(2));
			
			// if not, start at the first segment
			if (points.size() > 2 && sourceNode.intersects(line)) linepoints.remove(0);
			else line.setLine(points.get(0), points.get(1));    
			sourceNode.calculateIntersection(line, linepoints.get(0));
			
			// last
			// check if we could end at the segment before the last one if yes,
			// remove the last control point
			if (points.size() > 2) {
				line.setLine(points.get(points.size() - 3), points.get(points.size() - 2));
					
				if (targetConnection.intersects(line)) {
					linepoints.remove(linepoints.size() - 1);
				} else {
					line.setLine(points.get(points.size() - 2), points.get(points.size() - 1));
				}
			}
			
			targetConnection.calculateIntersection(line, linepoints.get(linepoints.size() - 1));
			conn.setPoints(linepoints);
		}
	  
		/** Connection to Node */
	  	@Override
		public void generateAndSetPointsToConnection(Connection conn, Connection sourceConnection, Node targetNode, Point2D source, Point2D dest) 
		{
	  		TreeLineBuilder linebuilder = TreeLineBuilder.getInstance();
		    List<Point2D> points = linebuilder.calculateLineSegments(source, dest, Orientation.HORIZONTAL);
		    List<Point2D> linepoints = new LinkedList<Point2D>();
		    for (Point2D point : points) linepoints.add(point); 
		    
		    // calculate intersections with the nodes
		    Line2D line = new Line2D.Double();
		    // first
		    // check if we could start at the second segment
		    if (points.size() > 2) line.setLine(points.get(1), points.get(2));
		    
		    // if not, start at the first segment
		    if (points.size() > 2 && sourceConnection.intersects(line)) {
		      linepoints.remove(0);
		    } else {
		      line.setLine(points.get(0), points.get(1));
		    }
		    sourceConnection.calculateIntersection(line, linepoints.get(0));
		
		    // last
		    // check if we could end at the segment before the last one if yes,
		    // remove the last control point
		    if (points.size() > 2) {
		      line.setLine(points.get(points.size() - 3), points.get(points.size() - 2));
		      if (targetNode.intersects(line)) {
		        linepoints.remove(linepoints.size() - 1);
		      } else {
		        line.setLine(points.get(points.size() - 2), points.get(points.size() - 1));
		      }
		    }
		    
		    targetNode.calculateIntersection(line, linepoints.get(linepoints.size() - 1));
		    conn.setPoints(linepoints);		
		}				
				  
		/** Connection to Connection */
	  	@Override
		public void generateAndSetPointsToConnection(Connection conn, Connection sourceConnection, Connection targetConnection, Point2D source, Point2D dest) 
		{
			TreeLineBuilder linebuilder = TreeLineBuilder.getInstance();
		    List<Point2D> points = linebuilder.calculateLineSegments(source, dest, Orientation.HORIZONTAL);
		    List<Point2D> linepoints = new LinkedList<Point2D>();
		    for (Point2D point : points) linepoints.add(point); 
		    
		    // calculate intersections with the nodes
		    Line2D line = new Line2D.Double();
		    // first
		    // check if we could start at the second segment
		    if (points.size() > 2) line.setLine(points.get(1), points.get(2));
		    
		    // if not, start at the first segment
		    if (points.size() > 2 && sourceConnection.intersects(line)) {
		      linepoints.remove(0);
		    } else {
		      line.setLine(points.get(0), points.get(1));
		    }
		    sourceConnection.calculateIntersection(line, linepoints.get(0));
		
		    // last
		    // check if we could end at the segment before the last one if yes,
		    // remove the last control point
		    if (points.size() > 2) {
		      line.setLine(points.get(points.size() - 3), points.get(points.size() - 2));
		      if (targetConnection.intersects(line)) {
		        linepoints.remove(linepoints.size() - 1);
		      } else {
		        line.setLine(points.get(points.size() - 2), points.get(points.size() - 1));
		      }
		    }
		    
		    targetConnection.calculateIntersection(line, linepoints.get(linepoints.size() - 1));
		    conn.setPoints(linepoints);		
		}	
}