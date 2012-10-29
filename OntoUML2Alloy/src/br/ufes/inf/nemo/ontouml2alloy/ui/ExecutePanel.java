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
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This panel was created using the Windows Builder in Eclipse.
 * 
 * 	@author John Guerson
 *
 */

public class ExecutePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JButton btnExecuteWithAnalyzer;
	
	/**
	 * Create the panel.
	 */
	public ExecutePanel() 
	{		
		btnExecuteWithAnalyzer = new JButton("Execute with Analyzer");
		btnExecuteWithAnalyzer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		setPreferredSize(new Dimension(535, 71));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(174)
					.addComponent(btnExecuteWithAnalyzer, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(164, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(btnExecuteWithAnalyzer)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}	
}
