package br.ufes.inf.nemo.oled.modellingassistant.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class MainFrame {

	private JDialog frame;
	private JPanel mainPanel;

	
	/**
	 * Put the current panel in the mainPanel
	 * */
	
	public void putPanel(JPanel panel){
		this.mainPanel = panel;
		frame.getContentPane().removeAll();
		frame.getContentPane().add(mainPanel);
	}
	
	/**
	 * Set visibility of the frame
	 * */
	
	public void setVisibility(boolean b){
		frame.setVisible(b);
	}

	/**
	 * Create the application.
	 */
	public MainFrame(JFrame ownerFrame) {
		frame = new JDialog(ownerFrame, true);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setBounds(100, 100, 455, 314);
		frame.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
	}
	
	public JDialog getFrame(){
		return frame;
	}

}
