package br.ufes.inf.nemo.move.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TheModelTreePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtOntoumlModelTree;

	/**
	 * Create the panel.
	 */
	public TheModelTreePanel() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		txtOntoumlModelTree = new JTextField();
		txtOntoumlModelTree.setHorizontalAlignment(SwingConstants.CENTER);
		txtOntoumlModelTree.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtOntoumlModelTree.setForeground(SystemColor.window);
		txtOntoumlModelTree.setText("OntoUML Model Tree View");
		txtOntoumlModelTree.setEditable(false);
		txtOntoumlModelTree.setBackground(Color.BLACK);
		txtOntoumlModelTree.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(txtOntoumlModelTree, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(txtOntoumlModelTree, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(280, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
