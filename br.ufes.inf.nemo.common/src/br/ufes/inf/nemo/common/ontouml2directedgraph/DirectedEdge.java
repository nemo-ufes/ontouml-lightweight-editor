package br.ufes.inf.nemo.common.ontouml2directedgraph;

public class DirectedEdge {
	final Object id;
	final Node source;
	final Node target;
	
	public DirectedEdge(Object id, Node source, Node target) {
		this.id = id;
		this.source = source;
		this.target = target;
	}

	public Object getId() {
		return id;
	}

	public Node getTarget() {
		return target;
	}
	
	public Node getSource() {
		return source;
	}
	
	@Override
	public String toString(){
		return source.toString() + " -> " + target.toString();
	}
}