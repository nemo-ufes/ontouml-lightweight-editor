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

import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;

/**
 * @author John Guerson
 */
public class TreeConnection extends RectilinearConnection {

	private static final long serialVersionUID = 3266764084036485018L;	
	boolean vertical = true;
	
	public TreeConnection(UmlConnection conn, boolean vertical)
	{
		super(conn);
		this.vertical = vertical;
	}
	
	@Override
	public Selection getSelection(DiagramOperations operations) 
	{
		ConnectionSelection selection = new TreeConnectionSelection(operations, this);
		return selection;
	}

	@Override
	public LineConnectMethod getConnectMethod() 
	{
		return TreeLineConnectMethod.getInstance();
	}

	@Override
	public void copyData(Connection conn) 
	{
		super.copyData(conn);
		if ((conn.getNode1()!=null) && (conn.getNode2()!=null)){
			if (vertical) setPoints(TreeLineBuilder.getInstance().calculateLineSegments(getNode1(), getNode2()));
			else setPoints(TreeLineBuilder.getInstance().calculateHorizontalLineSegments(getNode1(), getNode2()));
		}
		if ((conn.getNode1()==null) && (conn.getNode2()!=null)){
			if (vertical) setPoints(TreeLineBuilder.getInstance().calculateLineSegments(getConnection1(), getNode2()));
			//else setPoints(TreeLineBuilder.getInstance().calculateHorizontalLineSegments(getConnection1(), getNode2()));
		}
	}
	
	@Override
	public void reconnectPulledOffNodes() 
	{
		if (getPoints().size() <= 3) 
		{
			// If there are at most three connection points, simply recalculate the
			// connection between the two nodes completely
			if(getNode1()!=null && getNode2()!=null){
				if (vertical) setPoints(TreeLineBuilder.getInstance().calculateLineSegments(getNode1(), getNode2()));
				else setPoints(TreeLineBuilder.getInstance().calculateHorizontalLineSegments(getNode1(), getNode2()));
			}
			else if (getNode1()==null && getNode2()!=null){
				if (vertical) setPoints(TreeLineBuilder.getInstance().calculateLineSegments(getConnection1(), getNode2()));
				//else setPoints(TreeLineBuilder.getInstance().calculateHorizontalLineSegments(getConnection1(), getNode2()));
			}
		} else {
			if(getNode1()!=null && getNode2()!=null){
				reattachConnectionPoint(getNode1(), node1ConnectPoint, 0, 1);
				reattachConnectionPoint(getNode2(),	node2ConnectPoint, getPoints().size() - 1, getPoints().size() - 2);
			}else if (getNode1()==null && getNode2()!=null){
				reattachConnectionPoint(getConnection1(), node1ConnectPoint, 0, 1);
				reattachConnectionPoint(getNode2(),	node2ConnectPoint, getPoints().size() - 1, getPoints().size() - 2);
			}			
			// TODO: If the node intersects a middle segment, reduce the segments
		}
		//reattachConnectionPoint(getNode1(), node1ConnectPoint, 0, 1);
		//reattachConnectionPoint(getNode2(),
		//node2ConnectPoint, getPoints().size() - 1, getPoints().size() - 2);
		setValid(true);
	}

}
