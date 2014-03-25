package br.ufes.inf.nemo.common.positioning;

import java.awt.geom.Point2D;

public class ClassPosition {

	public static Point2D.Double findPositionGeneralization(Point2D.Double point, Point2D.Double point2){

		Point2D.Double newpoint = new Point2D.Double();
		if(Math.abs(point.getX()-point2.getX())>Math.abs(point.getY()-point2.getY())){	
			if(point.getX()>point2.getX())
				newpoint.x=(point.getX()+point2.getX())/2;
			else
				newpoint.x=(point2.getX()+point.getX())/2;

			if(point.getY()<point2.getY())
				newpoint.y=point.getY()-110;
			else
				newpoint.y=point2.getY()-110;
		}else{
			if(point.getY()>point2.getY())
				newpoint.y=(point.getY()+point2.getY())/2;
			else
				newpoint.y=(point2.getY()+point.getY())/2;

			if(point.getX()<point2.getX())
				newpoint.x=point.getX()+150;
			else
				newpoint.x=point2.getX()+150;

		}
		return newpoint;
	}
	
	public static Point2D.Double findPositionGeneralizationMember(Point2D.Double point, Point2D.Double point2){

		Point2D.Double newpoint = new Point2D.Double();
		newpoint.y=point2.y;
		if(point.getX()>point2.getX()){	
			newpoint.x=point.getX()+150;
		}else{
			newpoint.x=point.getX()-150;
		}
		return newpoint;
	}
}
