package br.ufes.inf.nemo.assistant.graph;

import java.io.Serializable;

import br.ufes.inf.nemo.assistant.wizard.pageassistant.WizardPageAssistant;

public class NodeAssistant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected GraphAssistant graph;

	private NodeAssistant nextNode = null;
	private NodeAssistant backNode = null;
	private NodeAssistant trueNode = null;
	private NodeAssistant falseNode = null;

	private WizardPageAssistant page = null;

	private String nodeDescription;
	
	public boolean isEndNode(){
		//return isEndNode;
		if(nextNode == null && (trueNode == null || falseNode == null)){
			return true;
		}
		return false;
	}

	public NodeAssistant(GraphAssistant graph){
		this.graph = graph;
	}

	public NodeAssistant(GraphAssistant graph, WizardPageAssistant page){
		this.graph = graph;
		this.page = page;
	}

	public void setPage(WizardPageAssistant page){
		this.page = page;
	}
	
	public WizardPageAssistant getPage(){
		page.init();
		page.setDescription(nodeDescription);
		return page;
	}

	public NodeAssistant getNextNode() {
		graph.setCurrentNode(nextNode);
		if(nextNode != null){
			nextNode.setBackNode(this);
		}
		return nextNode;
	}

	public void setNextNode(NodeAssistant nextNode) {
		this.nextNode = nextNode;
	}

	public NodeAssistant getBackNode() {
		graph.setCurrentNode(backNode);
		return backNode;
	}

	public void setBackNode(NodeAssistant backNode) {
		this.backNode = backNode;
	}

	public NodeAssistant getTrueNode() {
		graph.setCurrentNode(trueNode);
		if(trueNode != null){
			trueNode.setBackNode(this);
		}
		return trueNode;
	}

	public void setTrueNode(NodeAssistant trueNode) {
		this.trueNode = trueNode;
	}

	public NodeAssistant getFalseNode() {
		graph.setCurrentNode(falseNode);
		if(falseNode != null){
			falseNode.setBackNode(this);
		}
		return falseNode;
	}

	public void setFalseNode(NodeAssistant falseNode) {
		this.falseNode = falseNode;
	}

	public GraphAssistant getGraph() {
		return graph;
	}

	public void setGraph(GraphAssistant graph) {
		this.graph = graph;
	}

	public boolean canGoFalse(){
		return falseNode != null;
	}
	
	public boolean canGoTrue(){
		return trueNode != null;
	}
	
	protected boolean isAction = false;
	public boolean isAction(){
		return isAction;
	}

	public void setDescription(String description) {
		nodeDescription = description;
	}
}