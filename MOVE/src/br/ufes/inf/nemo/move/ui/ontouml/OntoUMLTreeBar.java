package br.ufes.inf.nemo.move.ui.ontouml;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * @author John Guerson
 */

public class OntoUMLTreeBar extends JPanel {
	
	private static final long serialVersionUID = -115797584019893402L;
	
	public JTextField textPath;	
	public JButton btnOpen;
	public JButton btnVerify;
	public JButton btnShowUnique;
	public JButton btnSaveAs;
	
	public OntoUMLTreeBar() 
	{		
		textPath = new JTextField();
		textPath.setBackground(Color.WHITE);
		textPath.setEditable(false);
		textPath.setColumns(10);
		
		setPreferredSize(new Dimension(360, 80));
		
		btnOpen = new JButton("");
		btnOpen.setToolTipText("Open OntoUML Model");
		btnOpen.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/br/ufes/inf/nemo/move/open-16x16.png")));
		
		btnVerify = new JButton("");
		btnVerify.setToolTipText("Verify Model Sintactically");
		btnVerify.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/br/ufes/inf/nemo/move/check-16x16.png")));
		
		btnShowUnique = new JButton("Alias");
		btnShowUnique.setToolTipText("Show Aliases");
		btnShowUnique.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/br/ufes/inf/nemo/move/label-16x16.png")));
				
		btnSaveAs = new JButton("");
		btnSaveAs.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/br/ufes/inf/nemo/move/save-16x16.png")));
		btnSaveAs.setToolTipText("Save Model ");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textPath, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVerify, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSaveAs, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
							.addComponent(btnShowUnique)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textPath, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVerify, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(btnOpen, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(btnSaveAs, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnShowUnique, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
					.addContainerGap())
		);
		setLayout(groupLayout);
							
	}	
}
