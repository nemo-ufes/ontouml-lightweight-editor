package br.ufes.inf.nemo.assistant.graph;

import br.ufes.inf.nemo.assistant.manager.*;

public class GraphAssistant {
	Node start;
	Node current;
	
	ManagerPattern mp;
	
	public ManagerPattern getManagerPatern() {
		return mp;
	}
	public void setManagerPatern(ManagerPattern mp) {
		this.mp = mp;
	}
	public Node getStart() {
		return start;
	}
	public void setStart(Node start) {
		this.start = start;
	}
	public Node getCurrent() {
		return current;
	}
	public void setCurrent(Node current) {
		this.current = current;
	}
	
	public void print(Node n){
		System.out.println("\t"+n.getWin().getClass());
		if(n.getGoTrue() != null){
			System.out.print("True: ");
			print(n.getGoTrue());
		}
		if(n.getGoFalse() != null){
			System.out.print("False: ");
			print(n.getGoFalse());
		}
		if(n.getNext() != null){
			System.out.print("Next: ");
			print(n.getNext());
		}
		System.out.println("-----");
	}
	
}
