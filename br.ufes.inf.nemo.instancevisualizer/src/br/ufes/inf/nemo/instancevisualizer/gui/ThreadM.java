package br.ufes.inf.nemo.instancevisualizer.gui;

import br.ufes.inf.nemo.instancevisualizer.graph.GraphManager;

public class ThreadM extends Thread {
	private GraphManager gm;
	private int n;
	
	public ThreadM(GraphManager gm) {
		this.gm = gm;
		this.n = 0;
	}
	
	public void run() {
		while(true) {
			try {
				
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gm.getSelectedGraph().addNode(String.valueOf(n++));
		}
	}

}
