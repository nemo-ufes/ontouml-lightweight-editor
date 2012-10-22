package br.ufes.inf.nemo.ontouml2alloy.ui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ExecutePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JButton btnExecuteWithAnalyzer;
	
	/**
	 * Create the panel.
	 */
	public ExecutePanel() 
	{		
		btnExecuteWithAnalyzer = new JButton("Execute with Analyzer");
		btnExecuteWithAnalyzer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		setPreferredSize(new Dimension(450, 71));
		
		GroupLayout groupLayout = new GroupLayout(this);
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(140)
					.addComponent(btnExecuteWithAnalyzer, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(140, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(btnExecuteWithAnalyzer)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}	
}
