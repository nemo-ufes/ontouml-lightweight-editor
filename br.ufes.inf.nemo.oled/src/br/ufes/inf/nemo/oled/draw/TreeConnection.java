package br.ufes.inf.nemo.oled.draw;

public class TreeConnection extends RectilinearConnection {

	private static final long serialVersionUID = 3266764084036485018L;	

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
		if ((conn.getNode1()!=null) && (conn.getNode2()!=null))
			setPoints(TreeLineBuilder.getInstance().calculateLineSegments(getNode1(), getNode2()));
		if ((conn.getNode1()==null) && (conn.getNode2()!=null))
			setPoints(TreeLineBuilder.getInstance().calculateLineSegments(getConnection1(), getNode2()));		
	}
	
	@Override
	public void reconnectPulledOffNodes() 
	{
		if (getPoints().size() <= 3) 
		{
			// If there are at most three connection points, simply recalculate the
			// connection between the two nodes completely
			if(getNode1()!=null && getNode2()!=null)
				setPoints(TreeLineBuilder.getInstance().calculateLineSegments(getNode1(), getNode2()));
			else if (getNode1()==null && getNode2()!=null)
				setPoints(TreeLineBuilder.getInstance().calculateLineSegments(getConnection1(), getNode2()));			
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
