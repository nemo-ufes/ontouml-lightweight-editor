package br.inf.ufes.nemo.oled.ui;

public class Assistent extends javax.swing.JPanel {

	private static final long serialVersionUID = 2323044086263322275L;
		
	public Assistent() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(200, 500));
			this.setSize(200, 500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
