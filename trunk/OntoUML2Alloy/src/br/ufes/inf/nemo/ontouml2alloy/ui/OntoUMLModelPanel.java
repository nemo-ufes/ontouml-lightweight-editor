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

import java.awt.Dimension;
import java.awt.SystemColor;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.resource.ResourceUtil;

/**
 * This Panel was created using the Windows Builder in Eclipse. 
 *
 * 	@author John Guerson
 */

public class OntoUMLModelPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	
	public RefOntoUML.Package refmodel;
	
	public void setModel (String modelpath) throws IOException
	{		
		Resource resource = ResourceUtil.loadReferenceOntoUML(modelpath);
		refmodel = (RefOntoUML.Package) resource.getContents().get(0);
		
		textField.setText(modelpath);
	}

	public void setModel (RefOntoUML.Package refmodel)
	{
		this.refmodel = refmodel;
		
		textField.setText("Loaded...");
	}
	
	public RefOntoUML.Package getModel ()
	{
		return refmodel;
	}
	
	public String getModelPath()
	{
		return textField.getText();
	}
	
	/**
	 * Create the panel.
	 */
	public OntoUMLModelPanel() 
	{
		setBackground(SystemColor.inactiveCaption);
		setPreferredSize(new Dimension(534,63));
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel lblYourOntoumlModel = new JLabel("Load Model : (Ctrl+O)");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
						.addComponent(lblYourOntoumlModel))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblYourOntoumlModel)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);
		setLayout(groupLayout);
	}
}
