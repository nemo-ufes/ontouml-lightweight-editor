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
	
	public JLabel lblStandalone;
	
	public JLabel lblModelAlreadyLoaded;
	
	/**
	 * Create the panel.
	 */
	public FilesPanel() 
	{				
		txtOntoUML = new JTextField();
		txtOntoUML.setText("C:"+File.separator+"teste.refontouml");
		txtOntoUML.setColumns(10);
		
		btnBrowseOntoUML = new JButton("Browse...");		
				
		btnExecute = new JButton("Execute");
						
		JLabel lblProvideTheOntouml = new JLabel("OntoUML2Alloy");
		lblProvideTheOntouml.setHorizontalAlignment(SwingConstants.CENTER);
		lblProvideTheOntouml.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblDescription = new JLabel("Check the options on the Rules tab before executing.");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setForeground(Color.BLACK);
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		
		txtAlloy = new JTextField();
		txtAlloy.setText("C:"+File.separator+"teste.als");
		txtAlloy.setColumns(10);
		
		btnBrowseAlloy = new JButton("Browse...");
								
		lblStandalone = new JLabel("(Standalone)");
		
		lblStandalone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStandalone.setHorizontalAlignment(SwingConstants.CENTER);
		lblStandalone.setForeground(Color.GRAY);
		
		lblModelAlreadyLoaded = new JLabel("");
		lblModelAlreadyLoaded.setForeground(new Color(0, 128, 0));
		lblModelAlreadyLoaded.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblOntouml = new JLabel("OntoUML :");
		lblOntouml.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblAlloy = new JLabel("Alloy :");
		lblAlloy.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		GroupLayout gl_FilesPanel = new GroupLayout(this);
		gl_FilesPanel.setHorizontalGroup(
			gl_FilesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_FilesPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDescription, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
					.addGap(35))
				.addGroup(gl_FilesPanel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_FilesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblModelAlreadyLoaded, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
						.addGroup(gl_FilesPanel.createSequentialGroup()
							.addGroup(gl_FilesPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAlloy)
								.addComponent(lblOntouml))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_FilesPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtAlloy, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
								.addComponent(txtOntoUML, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_FilesPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnBrowseAlloy, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBrowseOntoUML, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addComponent(lblStandalone, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
						.addComponent(lblProvideTheOntouml, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
					.addGap(24))
				.addGroup(Alignment.TRAILING, gl_FilesPanel.createSequentialGroup()
					.addContainerGap(179, Short.MAX_VALUE)
					.addComponent(btnExecute, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(152))
		);
		gl_FilesPanel.setVerticalGroup(
			gl_FilesPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_FilesPanel.createSequentialGroup()
					.addGap(22)
					.addComponent(lblProvideTheOntouml)
					.addGap(18)
					.addComponent(lblStandalone)
					.addGap(18)
					.addComponent(lblDescription)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_FilesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBrowseOntoUML)
						.addComponent(txtOntoUML, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOntouml))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_FilesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBrowseAlloy)
						.addComponent(txtAlloy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAlloy))
					.addGap(18)
					.addComponent(lblModelAlreadyLoaded, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExecute)
					.addGap(50))
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
		lblStandalone.setText("(From OLED)");
		lblModelAlreadyLoaded.setText("");		 
	}
}
