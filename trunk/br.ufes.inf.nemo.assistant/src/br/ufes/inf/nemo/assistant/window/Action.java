package br.ufes.inf.nemo.assistant.window;

import br.ufes.inf.nemo.assistant.graph.Node;
import br.ufes.inf.nemo.assistant.manager.ManagerNode;
import br.ufes.inf.nemo.assistant.util.ActionEnum;


public class Action extends AbstractWindow{

	private static final long serialVersionUID = 1L;
	private ActionEnum action;
	private String filter = null;

	public void setAction(ActionEnum a){
		action = a;
	}

	public ActionEnum getAction(){
		return action;
	}

	@Override
	public void run(Node n) {
		switch (action) {
		case EXIST_SOME_CATEGORY_OR_MIXIN:
			if(n.getTree().getManagerPatern().existSomeMixinUniversal()){
				ManagerNode.goTrue(n);	
			}else{
				ManagerNode.goFalse(n);
			}
			break;

		case EXIST_SOME_ULTIMATE_SORTAL:
			if(n.getTree().getManagerPatern().existSomeUltimateSortal()){
				ManagerNode.goTrue(n);	
			}else{
				ManagerNode.goFalse(n);
			}
			break;

		case CONNECT_LAST_CLASSES:
			if(filter == null){
				n.getTree().getManagerPatern().connectLastClasses();
			}else{
				n.getTree().getManagerPatern().connectLastClasses(filter);	
			}
			ManagerNode.goNext(n);
			break;

		default:
			System.out.println("ERRO");
			break;
		}
	}

	public void addFilter(String filterParam) {
		filter = filterParam;
	}
}
