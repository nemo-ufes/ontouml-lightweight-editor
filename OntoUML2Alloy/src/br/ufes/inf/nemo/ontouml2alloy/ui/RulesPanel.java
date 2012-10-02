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

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * This panel was created using the Windows Builder in Eclipse.
 * 
 * 	@author John Guerson 
 *  @author Tiago Sales 
 *  @author Lucas Thom
 *
 */

public class RulesPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JCheckBox cbxWeakSupplementation;
	
	public JCheckBox cbxRelators;
	
	/**
	 * Create the panel.
	 */
	
	public RulesPanel() 
	{
		cbxRelators = new JCheckBox("Enforce");
		cbxRelators.setSelected(true);
		
		cbxWeakSupplementation = new JCheckBox("Enforce");
		cbxWeakSupplementation.setSelected(true);
		
		JLabel lblRelatorsDescription = new JLabel("This rule enforces that the concrete relators mediate two distinct entities.");
		lblRelatorsDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRelatorsDescription.setForeground(Color.GRAY);
		lblRelatorsDescription.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel lblWeakDescription = new JLabel("This rule enforces that each whole has at least two disjoint parts.");
		lblWeakDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWeakDescription.setForeground(Color.GRAY);
		lblWeakDescription.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel lblRelatorsRule = new JLabel("Relator Constraint");
		lblRelatorsRule.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblWeakSupplementationRule = new JLabel("Weak Supplementation");
		lblWeakSupplementationRule.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		GroupLayout gl_RulesPanel = new GroupLayout(this);
		gl_RulesPanel.setHorizontalGroup(
			gl_RulesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RulesPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWeakDescription, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
						.addGroup(gl_RulesPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblWeakSupplementationRule)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbxWeakSupplementation))
						.addGroup(gl_RulesPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRelatorsRule)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbxRelators))
						.addComponent(lblRelatorsDescription, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_RulesPanel.setVerticalGroup(
			gl_RulesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RulesPanel.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRelatorsRule)
						.addComponent(cbxRelators))
					.addGap(21)
					.addComponent(lblRelatorsDescription, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWeakSupplementationRule)
						.addComponent(cbxWeakSupplementation))
					.addGap(18)
					.addComponent(lblWeakDescription, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(132))
		);
		this.setLayout(gl_RulesPanel);		
	}
}
