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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.ontouml2alloy.util.ResourceUtil;

/**
 * This panel was created using the Windows Builder in Eclipse.
 * 
 * 	@author John Guerson, Tiago Sales and Lucas Thom
 *
 */

public class FilesPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JTextField txtOntoUML;
	
	public JTextField txtAlloy;
	
	public JButton btnBrowseAlloy;
	
	public JButton btnLoadOntoUML;	
	
	public RefOntoUML.Package refmodel;
	
	public String alsPath;
	
	
	/** Get OntoUML String Path.  */
	public String getOntoUMLPath ()  { 	return txtOntoUML.getText(); }
	
	/** Get Alloy String Path. */
	public String getAlloyPath () {  return txtAlloy.getText(); }
	
	/** Set OntoUML Path */
	public void setOntoUMLPath(String path) {  txtOntoUML.setText(path);  }
	
	/** Set Alloy Path */
	public void setAlloyPath(String path) {  txtAlloy.setText(path);  }
	
	/**
	 * Create the panel to load the OntUML model.
	 */
	public void load (RefOntoUML.Package model, String alloyPath)
	{				
		refmodel = model;
		alsPath = alloyPath;		
		
		txtOntoUML.setText("Loaded...");		
		txtOntoUML.setEditable(false);
		txtOntoUML.setEnabled(false);
		
		btnLoadOntoUML.setEnabled(false);
		
		txtAlloy.setText(alsPath);		
	}	
	
	/**
	 * Create the panel.
	 */
	public FilesPanel() 
	{				
		txtOntoUML = new JTextField();
		txtOntoUML.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtOntoUML.setText("C:"+File.separator+"teste.refontouml");
		txtOntoUML.setColumns(10);
		
		btnLoadOntoUML = new JButton("Browse...");		
		btnLoadOntoUML.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtAlloy = new JTextField();
		txtAlloy.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtAlloy.setText("C:"+File.separator+"teste.als");
		txtAlloy.setColumns(10);
		
		btnBrowseAlloy = new JButton("Browse...");
		btnBrowseAlloy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblOntouml = new JLabel("OntoUML Model Input:");
		lblOntouml.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblAlloy = new JLabel("Alloy Specification Output:");
		lblAlloy.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblFiles = new JLabel("Files");
		lblFiles.setFont(new Font("Dialog", Font.BOLD, 14));
		
		btnLoadOntoUML.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				BrowseOntoUMLActionPerformed(arg0);				
			}
		});
	
		btnBrowseAlloy.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				BrowseAlloyActionPerformed(arg0);
			}
		});		
		
		GroupLayout gl_FilesPanel = new GroupLayout(this);
		gl_FilesPanel.setHorizontalGroup(
			gl_FilesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_FilesPanel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_FilesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOntouml)
						.addGroup(gl_FilesPanel.createSequentialGroup()
							.addComponent(txtAlloy, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBrowseAlloy))
						.addComponent(lblAlloy, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.addComponent(lblFiles, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_FilesPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtOntoUML, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoadOntoUML, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_FilesPanel.setVerticalGroup(
			gl_FilesPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_FilesPanel.createSequentialGroup()
					.addGap(29)
					.addComponent(lblFiles)
					.addGap(22)
					.addComponent(lblOntouml)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_FilesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLoadOntoUML)
						.addComponent(txtOntoUML, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAlloy)
					.addGap(7)
					.addGroup(gl_FilesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtAlloy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowseAlloy))
					.addGap(108))
		);
		this.setLayout(gl_FilesPanel);
	}	
	
	/**
	 *	Action Performed for Browse OntoUML JButton in FilesPanel. 
	 */
	public void BrowseOntoUMLActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open");
		FileNameExtensionFilter ontoumlFilter = new FileNameExtensionFilter("OntoUML Model (*.refontouml)", "refontouml");
		fileChooser.addChoosableFileFilter(ontoumlFilter);
		fileChooser.setFileFilter(ontoumlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == ontoumlFilter) 
			{				
				txtOntoUML.setText( fileChooser.getSelectedFile().getPath() );
				
				try {
				
					Resource resource = ResourceUtil.loadOntoUML(txtOntoUML.getText());
					refmodel = (RefOntoUML.Package) resource.getContents().get(0);
					
				} catch (IOException e) {				
					String msg = "An error ocurred while loading the model into a resource";
					JOptionPane.showMessageDialog(this,msg,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/**
	 *	Action Performed for Browse Alloy JButton in FilesPanel. 
	 */
	public void BrowseAlloyActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open");
		FileNameExtensionFilter alloyFilter = new FileNameExtensionFilter("Alloy Specification (*.als)", "als");
		fileChooser.addChoosableFileFilter(alloyFilter);
		fileChooser.setFileFilter(alloyFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == alloyFilter) 
			{				
				txtAlloy.setText( fileChooser.getSelectedFile().getPath() );
				alsPath = txtAlloy.getText();
			}
		}
	}
}
