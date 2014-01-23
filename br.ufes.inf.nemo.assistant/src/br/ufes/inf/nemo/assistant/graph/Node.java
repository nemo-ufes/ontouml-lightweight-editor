package br.ufes.inf.nemo.assistant.graph;

import br.ufes.inf.nemo.assistant.window.AbstractWindow;

public class Node {
	private GraphAssistant tree;
	private Node back;
	private Node goTrue;
	private Node goFalse;
	private Node next;

	private AbstractWindow win;

	public Node(){
		
	}
	
	public Node(GraphAssistant tree){
		this.tree = tree;
	}
	
	public GraphAssistant getTree() {
		return tree;
	}

	public void setTree(GraphAssistant tree) {
		this.tree = tree;
	}

	public Node getBack() {
		return back;
	}

	public void setBack(Node back) {
		this.back = back;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
	
	
	public Node getGoTrue() {
		return goTrue;
	}

	public void setGoTrue(Node goTrue) {
		this.goTrue = goTrue;
	}

	public Node getGoFalse() {
		return goFalse;
	}

	public void setGoFalse(Node goFalse) {
		this.goFalse = goFalse;
	}

	public AbstractWindow getWin() {
		return win;
	}

	public void setWin(AbstractWindow win) {
		this.win = win;
	}
	
	public void run(){
		this.win.run(this);
	}
}
