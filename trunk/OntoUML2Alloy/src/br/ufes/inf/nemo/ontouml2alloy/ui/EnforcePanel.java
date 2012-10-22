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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import br.ufes.inf.nemo.ontouml2alloy.util.Options;

/**
 * This panel was created using the Windows Builder in Eclipse.
 * 
 * 	@author John Guerson, Tiago Sales and Lucas Thom
 *
 */

public class EnforcePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JCheckBox cbxWeakSupplementation;
	
	public JCheckBox cbxRelator;
	
	public JCheckBox cbxIdentityPrinciple;
	
	public JCheckBox cbxAntiRigidity;
	
	/**
	 * Get Options fromEnforcements CheckBox. 
	 */
	public Options getOptions ()
	{
		Options opt = new Options();
		opt.antiRigidity = cbxAntiRigidity.isSelected();
		opt.identityPrinciple = cbxIdentityPrinciple.isSelected();
		opt.relatorConstraint = cbxRelator.isSelected();
		opt.weakSupplementationConstraint = cbxWeakSupplementation.isSelected();
		return opt;
	}
	
	/**
	 * Create the panel.
	 */
	
	public EnforcePanel() 
	{
		cbxRelator = new JCheckBox("");
		cbxRelator.setSelected(true);
		
		cbxWeakSupplementation = new JCheckBox("");
		cbxWeakSupplementation.setSelected(true);
		
		JLabel lblRelator = new JLabel("Relator Constraint");
		lblRelator.setToolTipText("<html>\r\n\r\n<br/>\r\nThis rule enforces that the concrete relators mediate two distinct entities.\r\n<br/><br/>\r\nUncheck this box if you have at least one relator that the sum of its mediation's \r\n<br/>\r\ncardinality at the target side is less than 2. \r\n<br/><br/>\r\n\r\n</html>\r\n");
		lblRelator.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblWeakSupplementation = new JLabel("Weak Supplementation Constraint");
		lblWeakSupplementation.setToolTipText("<html>\r\n\r\n<br/>\r\nThis rule enforces that each whole has at least two disjoint parts.\r\n<br/><br/>\r\nUncheck this box if there's a meronymic relation where the part side has minimum cardinality less than 2.\r\n<br/><br/>\r\n\r\n</html>");
		lblWeakSupplementation.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblIdentityPrinciple = new JLabel(" Identity Principle");
		lblIdentityPrinciple.setToolTipText("<html>\r\n\r\n<br/>\r\nThis rule enforces that all objects have an identity principle.\r\n<br/><br/>\r\nUncheck this box if you have at least one element without identity principle (i.e. an element <br/>\r\nthat has no kind, quantity or collective as its supertype).\r\n<br/><br/>\r\n\r\n</html>");
		lblIdentityPrinciple.setFont(new Font("Dialog", Font.BOLD, 12));
		
		cbxIdentityPrinciple = new JCheckBox("");
		cbxIdentityPrinciple.setSelected(true);
		
		JLabel lblAntirigidity = new JLabel("Show AntiRigidity");
		lblAntirigidity.setToolTipText("<html>\r\n\r\n<br/>\r\nThis rule enforces the antirigid objects to appear at least once in visualization.\r\n<br/><br/>\r\n\r\n</html>");
		lblAntirigidity.setFont(new Font("Dialog", Font.BOLD, 12));
		
		cbxAntiRigidity = new JCheckBox("");
		
		JTextPane txtWeakDescription = new JTextPane();
		txtWeakDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtWeakDescription.setEditable(false);
		txtWeakDescription.setBackground(UIManager.getColor("Panel.background"));
		txtWeakDescription.setText("This rule enforces that each whole has at least two disjoint parts.\r\n\r\nUncheck this box if there's a meronymic relation where the part side has minimum cardinality less than 2.");
		
		JLabel lblEnforcements = new JLabel("Enforcement Rules");
		lblEnforcements.setFont(new Font("Dialog", Font.BOLD, 16));
		
		GroupLayout gl_RulesPanel = new GroupLayout(this);
		gl_RulesPanel.setHorizontalGroup(
			gl_RulesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RulesPanel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEnforcements, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
						.addGroup(gl_RulesPanel.createSequentialGroup()
							.addComponent(cbxAntiRigidity)
							.addGap(18)
							.addComponent(lblAntirigidity, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
						.addGroup(gl_RulesPanel.createSequentialGroup()
							.addComponent(cbxIdentityPrinciple)
							.addGap(18)
							.addComponent(lblIdentityPrinciple, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
						.addGroup(gl_RulesPanel.createSequentialGroup()
							.addComponent(cbxWeakSupplementation)
							.addGap(18)
							.addComponent(lblWeakSupplementation, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
						.addGroup(gl_RulesPanel.createSequentialGroup()
							.addComponent(cbxRelator)
							.addGap(18)
							.addComponent(lblRelator, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
						.addComponent(txtWeakDescription, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_RulesPanel.setVerticalGroup(
			gl_RulesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RulesPanel.createSequentialGroup()
					.addGap(22)
					.addComponent(lblEnforcements, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxRelator)
						.addComponent(lblRelator))
					.addGap(18)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxWeakSupplementation)
						.addComponent(lblWeakSupplementation))
					.addGap(18)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxIdentityPrinciple)
						.addComponent(lblIdentityPrinciple))
					.addGap(18)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_RulesPanel.createSequentialGroup()
							.addComponent(lblAntirigidity)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtWeakDescription, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addComponent(cbxAntiRigidity)))
		);
		this.setLayout(gl_RulesPanel);		
	}
}
