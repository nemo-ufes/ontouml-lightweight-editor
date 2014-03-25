package br.ufes.inf.nemo.assistant.graph;

import java.io.Serializable;
import java.util.ArrayList;

import br.ufes.inf.nemo.assistant.manager.ManagerPattern;

public class GraphAssistant implements Serializable {
	/**
	 * TODO change if some attribute was changed
	 */
	private static final long serialVersionUID = 1L;
	
	private transient ManagerPattern managerPattern;

	private NodeAssistant startNode;
	private NodeAssistant currentNode;

	private ArrayList<NodeAssistant> nodes = new ArrayList<NodeAssistant>();

	public ManagerPattern getManagerPattern() {
		return managerPattern;
	}
	public void setManagerPattern(ManagerPattern managerPattern) {
		this.managerPattern = managerPattern;
	}
	public NodeAssistant getStartNode() {
		currentNode = startNode;
		return startNode;
	}
	public void setStartNode(NodeAssistant startNode) {
		this.startNode = startNode;
		this.currentNode = startNode;
	}

	public NodeAssistant getCurrentNode() {
		return currentNode;
	}
	public void setCurrentNode(NodeAssistant node) {
		currentNode = node;
	}

	public ArrayList<NodeAssistant> getNodeList(){
		return nodes;
	}
	
	public void updateNodeList() {
		nodes = new ArrayList<>();
		updateNode(startNode);
		currentNode = startNode;
	}

	private void updateNode(NodeAssistant node){
		if(node == null)
			return;
		if(nodes.contains(node))
			return;
		nodes.add(node);
		
		if(node.isAction()){
			updateNode(node.getFalseNode());
			updateNode(node.getTrueNode());
		}else{
			updateNode(node.getNextNode());
			updateNode(node.getFalseNode());
			updateNode(node.getTrueNode());	
		}
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "Total Nodes: "+nodes.size();
		for(NodeAssistant node :nodes){
			if(!node.isAction()){
				s += "\n"+node.getPage().getTitle(); 
			}else{
				s += "\nAction: "+((NodeAction)node).getAction().toString();
			}
		}
		return s;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
