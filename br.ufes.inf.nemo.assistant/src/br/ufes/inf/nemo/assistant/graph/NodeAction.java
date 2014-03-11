package br.ufes.inf.nemo.assistant.graph;

import br.ufes.inf.nemo.assistant.util.ActionEnum;

public class NodeAction extends NodeAssistant {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	@Override
	public NodeAssistant getNextNode() {
		boolean	returnAction = graph.getManagerPattern().getActionProcessor().process(action);

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
