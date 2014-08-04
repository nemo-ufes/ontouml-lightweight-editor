package br.ufes.inf.nemo.common.ontouml2directedgraph;

import java.util.ArrayList;

public class EdgePath {
	ArrayList<DirectedEdge> edges;
	
	public EdgePath(){
		edges = new ArrayList<DirectedEdge>();
	}
	
	public EdgePath(EdgePath edgePath){
		edges = new ArrayList<DirectedEdge>(edgePath.edges);
	}
	
	public ArrayList<DirectedEdge> getEdges() {
		return edges;
	}
	
	public boolean addEdge(DirectedEdge edge){
		if(edges.size()==0){
			edges.add(edge);
			return true;
		}
		
		if(edges.get(edges.size()-1).target.equals(edge.source)){
			edges.add(edge);
			return true;
		}
		
		return false;
			
	}
	
	public boolean isCycle(){
		return edges!=null && edges.size()>0 && edges.get(0).getSource().equals(edges.get(edges.size()-1).getTarget());
	}
	
	public boolean containsSourceNode(Node node){
		if(edges==null || edges.size()==0)
			return false;
		
		for (DirectedEdge edge : edges) {
			if(edge.source.equals(node))
				return true;
		}
		
		return false;
	}
	
	public boolean containSameEdges(EdgePath path, boolean compareEdgeId){
		
		if(compareEdgeId)
			return getEdgeIds().containsAll(path.getEdgeIds()) && path.getEdgeIds().containsAll(getEdgeIds());
		
		return this.edges.containsAll(path.edges) && path.edges.containsAll(this.edges);
	}
	
	public ArrayList<Object> getNodeIds(boolean repeatFirstLast){
		ArrayList<Object> ids = new ArrayList<Object>();
		
		if(edges.size()==0)
			return ids;
		
		if(repeatFirstLast)
			ids.add(edges.get(0).source.id);
		
		ids.add(edges.get(0).target.id);
		
		if(edges.size()==1)
			return ids;
		
		for (int i = 1; i < edges.size(); i++) {
			ids.add(edges.get(i).target.id);
		}
		
		return ids;
	}
	
	public <T> ArrayList<T> getNodeIdsOfType(Class<T> type, boolean repeatFirstLast){
		ArrayList<T> ids = new ArrayList<T>();
		
		for (Object object : getNodeIds(repeatFirstLast)) {
			if(type.isInstance(object))
				ids.add(type.cast(object));
		}
		
		return ids;
	}
	
	public ArrayList<Object> getEdgeIds(){
		ArrayList<Object> ids = new ArrayList<Object>();
		
		if(edges.size()==0)
			return ids;
		
		for (DirectedEdge edge : edges)
			ids.add(edge.id);
		
		return ids;
	}
	
	public <T> ArrayList<T> getEdgeIdsOfType(Class<T> type){
		ArrayList<T> ids = new ArrayList<T>();
		
		if(edges.size()==0)
			return ids;
		
		for (Object object : getEdgeIds()) {
			if(type.isInstance(object))
				ids.add(type.cast(object));
		}
		
		return ids;
	}
	
	public Object getIdOfNode(int i) throws NullPointerException{
		if(edges.size()==0)
			throw new NullPointerException();
		
		if(i==0)
			return edges.get(0).getSource().id;
		
		return edges.get(i-1).getTarget().id;
	}

	public int edges() {
		return edges.size();
	}
	
	public int nodes() {
		return edges.size()+1;
	}
}
