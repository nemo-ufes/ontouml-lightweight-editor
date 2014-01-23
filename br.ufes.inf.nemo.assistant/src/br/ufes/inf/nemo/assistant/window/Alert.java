package br.ufes.inf.nemo.assistant.window;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;

import br.ufes.inf.nemo.assistant.manager.ManagerNode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Alert extends AbstractWindow{
	JLabel lbAlert = new JLabel("<AlertMenssage>");
	JButton btOk = new JButton("Ok");

	/**
	 * Create the application.
	 */
	public Alert() {
		instance = this;
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		this.setBounds(100, 100, 575, 330);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		lbAlert.setBounds(63, 34, 411, 167);
		this.getContentPane().add(lbAlert);
		
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerNode.goNext(myNode);
				instance.killMySelf();
			}
		});
		btOk.setBounds(220, 203, 111, 25);
		this.getContentPane().add(btOk);
	}
	
	public void setAlert(String alert){
		lbAlert.setText(alert);
	}
}
