package br.ufes.inf.nemo.assistant.graph;

import java.util.ArrayList;

import br.ufes.inf.nemo.assistant.manager.ManagerPattern;
import br.ufes.inf.nemo.assistant.wizard.WizardAssitant;

public class GraphAssistant {
	private WizardAssitant wizardAssistant;
	private ManagerPattern managerPattern;

	private NodeAssistant startNode;
	private NodeAssistant currentNode;

	private ArrayList<NodeAssistant> nodes = new ArrayList<NodeAssistant>();

	public WizardAssitant getWizardAssistant() {
		return wizardAssistant;
	}
	public void setWizardAssistant(WizardAssitant wizardAssistant) {
		this.wizardAssistant = wizardAssistant;
	}
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
				s += "\n"+node.getPage().getName(); 
			}else{
				s += "\nAction: "+((NodeAction)node).getAction().toString();
			}
		}
		return s;
	}
	

}
