package br.ufes.inf.nemo.common.positioning;

import java.awt.geom.Point2D;

public class ClassPosition {

	
	//it returns a good position for a generic type acording to the position of its two subtypes
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
	
	// it returns a good position for a type in function of its supertype and its sibling. 
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

	// this method receive the number of childs of the generic type and the position base and returns a list of positions where the first member is the father position and the rest is the childs positions
	public static Point2D.Double[] GSpositioning(int qtd, Point2D.Double location) {
		// TODO Auto-generated method stub
		boolean par=true;
		Point2D.Double[] positions= new Point2D.Double[qtd+1];
		positions[0]= location;
		
		if(qtd % 2 !=0)
			par=false;
		if(par){
			positions[1] = new Point2D.Double();
			positions[1].x=location.x- ((qtd-1)*100);
			positions[1].y=location.y +100;
			
			for(int i=2;i<qtd+1;i++){
				positions[i] = new Point2D.Double();
				if((qtd+1)/i >2){
					positions[i].x= location.x - ((qtd  -1) * 100);
					positions[i].y= location.y + 100;
				}else{
					positions[i].x= location.x + ((qtd  -1) * 100);
					positions[i].y= location.y + 100;
				}
			}	
			
		}
		else{
			positions[1] = new Point2D.Double();
			positions[1].x=location.x;
			positions[1].y=location.y+100;
			
			for(int i=2;i<qtd+1;i++){
				positions[i] = new Point2D.Double();
				positions[i].x= positions[i-1].x -  100;
				positions[i].y= location.y + 100;
			}		
			
		}
		return positions;
		
	}
}
