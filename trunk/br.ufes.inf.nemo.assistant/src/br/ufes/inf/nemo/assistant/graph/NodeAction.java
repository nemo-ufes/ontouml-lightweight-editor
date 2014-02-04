package br.ufes.inf.nemo.assistant.graph;

import br.ufes.inf.nemo.assistant.util.ActionEnum;

public class NodeAction extends NodeAssistant {
	private ActionEnum action;
	
	public NodeAction(GraphAssistant graph) {
		super(graph);
		//Turn this a action node
		isAction = true;
	}
	
	public void setAction(ActionEnum action){
		this.action = action;
	}
	
	public ActionEnum getAction(){
		return action;
	}
	
	/*
	 * This method considers the action to be taken
	 * */
	@Override
	public NodeAssistant getNextNode() {
		boolean returnAction = false;
		
		switch (action) {
		case  EXIST_SOME_ULTIMATE_SORTAL:
			returnAction = graph.getManagerPattern().existSomeUltimateSortal();
			break;
		}
		
		/*
		 * The currentNode attribute will be set by its 
		 * respectively method
		 * */
		if(returnAction){
			return getTrueNode();
		}else{
			return getFalseNode();
		}
	}
	
}
