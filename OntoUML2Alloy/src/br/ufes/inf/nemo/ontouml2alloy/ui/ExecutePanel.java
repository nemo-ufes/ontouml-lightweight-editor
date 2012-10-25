package br.ufes.inf.nemo.ontouml2alloy.ui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class ExecutePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JButton btnExecuteWithAnalyzer;
	
	/**
	 * Create the panel.
	 */
	public ExecutePanel() 
	{		
		btnExecuteWithAnalyzer = new JButton("Execute with Analyzer");
		btnExecuteWithAnalyzer.setIcon(new ImageIcon(ExecutePanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/play.png")));
		btnExecuteWithAnalyzer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		setPreferredSize(new Dimension(535, 71));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(174, Short.MAX_VALUE)
					.addComponent(btnExecuteWithAnalyzer, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addGap(164))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addComponent(btnExecuteWithAnalyzer)
					.addGap(22))
		);
		setLayout(groupLayout);
	}	
}
