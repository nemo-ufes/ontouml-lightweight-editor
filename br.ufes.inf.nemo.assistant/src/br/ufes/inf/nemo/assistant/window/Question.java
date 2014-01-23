package br.ufes.inf.nemo.assistant.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import br.ufes.inf.nemo.assistant.manager.ManagerNode;

public class Question extends AbstractWindow {
	private JLabel lbQuestion = new JLabel("<Question>");
	private JButton btnTrue = new JButton("True");
	private JButton btnFalse = new JButton("False");
	
	/**
	 * Create the application.
	 */
	public Question() {
		instance = this;
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		this.setBounds(100, 100, 854, 160);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(null);

		lbQuestion.setBounds(47, 23, 793, 47);
		this.getContentPane().add(lbQuestion);
		btnFalse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerNode.goFalse(myNode);
				instance.killMySelf();
			}
		});

		btnFalse.setBounds(322, 82, 79, 25);
		this.getContentPane().add(btnFalse);
		btnTrue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerNode.goTrue(myNode);
				instance.killMySelf();
			}
		});

		btnTrue.setBounds(414, 82, 79, 25);
		this.getContentPane().add(btnTrue);
	}

	public void setQuestion(String q){
		lbQuestion.setText(q);
	}
}

