package br.ufes.inf.nemo.ontouml2alloy.ui;

/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OntoUML2Alloy (OntoUML to Alloy Transformation).
 *
 * OntoUML2Alloy is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OntoUML2Alloy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OntoUML2Alloy; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

/**
 * This panel was created using the Windows Builder in Eclipse.
 * 
 * 	@author John Guerson 
 *  @author Tiago Sales 
 *  @author Lucas Thom
 *
 */

public class FilesPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JTextField txtOntoUML;
	
	public JTextField txtAlloy;
	
	public JButton btnBrowseAlloy;
	
	public JButton btnBrowseOntoUML;
	
	public JButton btnExecute;
	
	public RefOntoUML.Model refmodel;
	
	public String alsPath;
	
	/**
	 * Create the panel.
	 */
	public FilesPanel() 
	{				
		txtOntoUML = new JTextField();
		txtOntoUML.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtOntoUML.setText("C:"+File.separator+"teste.refontouml");
		txtOntoUML.setColumns(10);
		
		btnBrowseOntoUML = new JButton("Browse...");		
		btnBrowseOntoUML.setFont(new Font("Tahoma", Font.PLAIN, 12));
				
		btnExecute = new JButton("Execute");
		btnExecute.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblDescription = new JLabel("Check the options on the Rules tab before executing.");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setForeground(Color.BLACK);
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		
		txtAlloy = new JTextField();
		txtAlloy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAlloy.setText("C:"+File.separator+"teste.als");
		txtAlloy.setColumns(10);
		
		btnBrowseAlloy = new JButton("Browse...");
		btnBrowseAlloy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblOntouml = new JLabel("OntoUML:");
		lblOntouml.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblAlloy = new JLabel("Alloy:");
		lblAlloy.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblWelcomeToOntoumlalloy = new JLabel("OntoUML2Alloy");
		lblWelcomeToOntoumlalloy.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToOntoumlalloy.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		GroupLayout gl_FilesPanel = new GroupLayout(this);
		gl_FilesPanel.setHorizontalGroup(
			gl_FilesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_FilesPanel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_FilesPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblOntouml, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
						.addComponent(lblDescription, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
						.addComponent(lblAlloy, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
						.addComponent(lblWelcomeToOntoumlalloy, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
						.addGroup(gl_FilesPanel.createSequentialGroup()
							.addGroup(gl_FilesPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtAlloy, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
								.addComponent(txtOntoUML, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_FilesPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBrowseAlloy)
								.addComponent(btnBrowseOntoUML, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(24))
				.addGroup(Alignment.TRAILING, gl_FilesPanel.createSequentialGroup()
					.addContainerGap(173, Short.MAX_VALUE)
					.addComponent(btnExecute, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(158))
		);
		gl_FilesPanel.setVerticalGroup(
			gl_FilesPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_FilesPanel.createSequentialGroup()
					.addGap(24)
					.addComponent(lblWelcomeToOntoumlalloy)
					.addGap(18)
					.addComponent(lblDescription)
					.addGap(18)
					.addComponent(lblOntouml)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_FilesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOntoUML, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowseOntoUML))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAlloy)
					.addGap(11)
					.addGroup(gl_FilesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtAlloy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowseAlloy))
					.addGap(27)
					.addComponent(btnExecute)
					.addGap(54))
		);
		this.setLayout(gl_FilesPanel);
	}
	
	/**
	 * Create the panel from a model.
	 */
	public void configurePanelForOLED (RefOntoUML.Model model, String alloyPath)
	{				
		refmodel = model;
		alsPath = alloyPath;		
		txtOntoUML.setText("Loaded...");		
		txtOntoUML.setEditable(false);
		txtOntoUML.setEnabled(false);
		btnBrowseOntoUML.setEnabled(false);
		txtAlloy.setText(alsPath);	 
	}
}
