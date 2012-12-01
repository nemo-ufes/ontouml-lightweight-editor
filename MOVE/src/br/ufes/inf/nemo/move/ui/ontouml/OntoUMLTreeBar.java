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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

/**
 * @author John Guerson
 */

public class OntoUMLTreeBar extends JPanel {
	
	private static final long serialVersionUID = -115797584019893402L;
	
	public JTextField textPath;	
	public JButton btnOpen;
	public JButton btnVerify;
	public JButton btnShowUnique;
	public JCheckBox cbxIncludeHierarchy;
	public JButton btnCompleteSelect;
	
	public OntoUMLTreeBar() 
	{		
		textPath = new JTextField();
		textPath.setBackground(Color.WHITE);
		textPath.setEditable(false);
		textPath.setColumns(10);
		
		setPreferredSize(new Dimension(401, 80));
		
		btnOpen = new JButton("");
		btnOpen.setToolTipText("Open OntoUML Model");
		btnOpen.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/br/ufes/inf/nemo/move/open-16x16.png")));
		
		btnVerify = new JButton("");
		btnVerify.setToolTipText("Verify Model Sintactically");
		btnVerify.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/br/ufes/inf/nemo/move/check-16x16.png")));
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		btnShowUnique = new JButton("");
		btnShowUnique.setToolTipText("Show Aliases");
		btnShowUnique.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/br/ufes/inf/nemo/move/visible-16x16.png")));
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		
		cbxIncludeHierarchy = new JCheckBox("All Hierarchy");
		cbxIncludeHierarchy.setToolTipText("Includes every hierarchy of every class that is selected.");
		
		btnCompleteSelect = new JButton("");
		btnCompleteSelect.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/br/ufes/inf/nemo/move/selection-16x16.png")));
		btnCompleteSelect.setToolTipText("Complete Selections");
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
				
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVerify, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnShowUnique, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxIncludeHierarchy)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCompleteSelect, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE))
						.addComponent(textPath, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textPath, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnOpen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(btnVerify, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(separator_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(btnShowUnique, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(separator_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(separator_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxIncludeHierarchy, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCompleteSelect, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		setLayout(groupLayout);
	}	
}
