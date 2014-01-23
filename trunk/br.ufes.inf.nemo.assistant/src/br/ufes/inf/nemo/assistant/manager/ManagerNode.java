package br.ufes.inf.nemo.assistant.manager;

import br.ufes.inf.nemo.assistant.graph.Node;

public class ManagerNode {
	public static void goTrue(Node n){
		if(n.getGoTrue() == null){
			System.out.println("END");
		}else{
			n.getGoTrue().setBack(n);
			n.getGoTrue().run();	
		}
	}

	public static void goFalse(Node n){
		if(n.getGoFalse() == null){
			System.out.println("END");
		}else{
			n.getGoFalse().setBack(n);
			n.getGoFalse().run();	
		}
	}

	public static void goNext(Node n){
		if(n.getNext() == null){
			System.out.println("END");
		}else{
			n.getNext().setBack(n);
			n.getNext().run();	
		}
	}

	public static void goBack(Node n){
		if(n.getBack() == null){
			System.out.println("END");
		}else{
			n.getBack().run();	
		}
	}
}
