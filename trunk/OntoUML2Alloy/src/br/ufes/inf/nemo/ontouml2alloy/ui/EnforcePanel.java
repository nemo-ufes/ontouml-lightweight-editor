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

public class EnforcePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JCheckBox cbxWeakSupplementation;
	
	public JCheckBox cbxRelator;
	
	public JCheckBox cbxIdentityPrinciple;
	
	/**
	 * Create the panel.
	 */
	
	public EnforcePanel() 
	{
		cbxRelator = new JCheckBox("Enforce");
		cbxRelator.setSelected(true);
		
		cbxWeakSupplementation = new JCheckBox("Enforce");
		cbxWeakSupplementation.setSelected(true);
		
		JLabel lblRelatorsDescription = new JLabel("This rule enforces that the concrete relators mediate two distinct entities.");
		lblRelatorsDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRelatorsDescription.setForeground(Color.DARK_GRAY);
		lblRelatorsDescription.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel lblWeakDescription = new JLabel("This rule enforces that each whole has at least two disjoint parts.");
		lblWeakDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWeakDescription.setForeground(Color.DARK_GRAY);
		lblWeakDescription.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel lblRelator = new JLabel("Relator Constraint");
		lblRelator.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblWeakSupplementation = new JLabel("Weak Supplementation Constraint");
		lblWeakSupplementation.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblIdentityPrinciple = new JLabel("Identity Principle");
		lblIdentityPrinciple.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		cbxIdentityPrinciple = new JCheckBox("Enforce");
		cbxIdentityPrinciple.setSelected(true);
		
		JLabel lblIdentityDescription = new JLabel("This rule enforces that all objects have an identity principle.");
		lblIdentityDescription.setForeground(Color.DARK_GRAY);
		lblIdentityDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		GroupLayout gl_RulesPanel = new GroupLayout(this);
		gl_RulesPanel.setHorizontalGroup(
			gl_RulesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RulesPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_RulesPanel.createSequentialGroup()
							.addComponent(lblWeakSupplementation)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbxWeakSupplementation))
						.addGroup(gl_RulesPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRelator)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbxRelator))
						.addComponent(lblRelatorsDescription, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
						.addGroup(gl_RulesPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblIdentityDescription, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_RulesPanel.createSequentialGroup()
									.addComponent(lblIdentityPrinciple)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cbxIdentityPrinciple))))
						.addComponent(lblWeakDescription, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_RulesPanel.setVerticalGroup(
			gl_RulesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RulesPanel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRelator)
						.addComponent(cbxRelator))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblRelatorsDescription, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWeakSupplementation)
						.addComponent(cbxWeakSupplementation))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblWeakDescription, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdentityPrinciple)
						.addComponent(cbxIdentityPrinciple))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblIdentityDescription)
					.addGap(72))
		);
		this.setLayout(gl_RulesPanel);		
	}
}
