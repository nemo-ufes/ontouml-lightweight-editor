package br.ufes.inf.nemo.move.ui.ocl;

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

public class OCLEditorBar extends JPanel {
	
	private static final long serialVersionUID = -115797584019893402L;
	
	public JTextField textPath;	
	public JButton btnOpen;
	public JButton btnSave;
	public JButton btnNew;
	public JButton btnParse;
	
	public OCLEditorBar() 
	{		
		textPath = new JTextField();
		textPath.setToolTipText("");
		textPath.setBackground(Color.WHITE);
		textPath.setEditable(false);
		textPath.setColumns(10);
		
		setPreferredSize(new Dimension(360, 80));
		
		btnOpen = new JButton("Open");
		btnOpen.setToolTipText("Open OCL textual Document (*.ocl)");
		btnOpen.setIcon(new ImageIcon(OCLEditorBar.class.getResource("/resources/icon/open-16x16.png")));
		
		btnSave = new JButton("Save");
		btnSave.setToolTipText("Save Rules to the OCL Textual Document (*.ocl)");
		btnSave.setIcon(new ImageIcon(OCLEditorBar.class.getResource("/resources/icon/save-16x16.png")));
		
		btnNew = new JButton("New");
		btnNew.setToolTipText("New OCL Textual Document (*.ocl)");
		btnNew.setIcon(new ImageIcon(OCLEditorBar.class.getResource("/resources/icon/doc-16x16.png")));
		
		btnParse = new JButton("Parse");
		btnParse.setToolTipText("Verify Syntactically the Domain Rules ");
		btnParse.setIcon(new ImageIcon(OCLEditorBar.class.getResource("/resources/icon/check-16x16.png")));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOpen)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnParse, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addComponent(textPath, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textPath, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
							.addComponent(btnParse, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		setLayout(groupLayout);
	}	
}
