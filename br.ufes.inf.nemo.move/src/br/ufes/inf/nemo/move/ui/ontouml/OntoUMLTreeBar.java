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
		
		setPreferredSize(new Dimension(376, 80));
		
		btnOpen = new JButton("Open");
		btnOpen.setToolTipText("Open OntoUML Model (*.refontouml)");
		btnOpen.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/icon/open-16x16.png")));
		
		btnVerify = new JButton("Diagnostic");
		btnVerify.setToolTipText("See some problems found in your model");
		btnVerify.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/icon/diagnostic-16x16.png")));
		
		btnShowUnique = new JButton("Aliases");
		btnShowUnique.setToolTipText("Show Generated Aliases");
		btnShowUnique.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/icon/label2-16x16.png")));
				
		btnSaveAs = new JButton("Save");
		btnSaveAs.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/icon/save-16x16.png")));
		btnSaveAs.setToolTipText("Save OntoUML Model (*.refontouml)");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textPath, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnOpen)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSaveAs)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVerify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnShowUnique, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textPath, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnShowUnique, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
							.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnSaveAs, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnVerify, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		setLayout(groupLayout);
							
	}	
}
