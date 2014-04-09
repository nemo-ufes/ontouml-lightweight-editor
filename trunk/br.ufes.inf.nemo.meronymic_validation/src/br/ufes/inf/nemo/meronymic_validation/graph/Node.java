package br.ufes.inf.nemo.meronymic_validation.graph;

import java.util.ArrayList;

import RefOntoUML.NamedElement;

public class Node {

	Object id;
	ArrayList<DirectedEdge> edges;
	
	public Node (Object id){
		this.id = id;
		edges = new ArrayList<DirectedEdge>();
	}
	
	public void connect(Object edgeId, Node next){
		edges.add(new DirectedEdge(edgeId, this, next));
	}
	
	@Override
	public String toString(){
		
		if(id instanceof NamedElement)
			return ((NamedElement) id).getName();
		
		return id.toString();
	}
}




