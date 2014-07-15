package br.ufes.inf.nemo.assistant.pattern.window.selctionbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PatternHelpPage extends JFrame {

	public PatternHelpPage() {
		getContentPane().setLayout(null);
		
		JLabel lblHelpPage = new JLabel("Help Page");
		lblHelpPage.setBounds(10, 11, 117, 14);
		getContentPane().add(lblHelpPage);
		
		JLabel lblNewLabel = new JLabel("Pattern Name:");
		lblNewLabel.setBounds(10, 36, 83, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblOverview = new JLabel("Overview");
		lblOverview.setBounds(10, 147, 60, 14);
		getContentPane().add(lblOverview);
		
		JLabel lblNewLabel_1 = new JLabel("Applicability");
		lblNewLabel_1.setBounds(10, 327, 83, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblSolution = new JLabel("Solution");
		lblSolution.setBounds(10, 236, 60, 14);
		getContentPane().add(lblSolution);
		
		JLabel lblExample = new JLabel("Example");
		lblExample.setBounds(10, 439, 60, 14);
		getContentPane().add(lblExample);
		
		JLabel lblRelatedPatterns = new JLabel("Related Patterns");
		lblRelatedPatterns.setBounds(10, 541, 83, 14);
		getContentPane().add(lblRelatedPatterns);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(164, 590, 89, 23);
		getContentPane().add(btnClose);
	}
}
