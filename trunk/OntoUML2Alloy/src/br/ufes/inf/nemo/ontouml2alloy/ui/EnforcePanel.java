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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

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
	
	public JButton btnRelatorInfo;
	
	public JButton btnWeakInfo;	
	
	public JButton btnIdentityInfo;
	
	public JButton btnAntirigidityInfo;
	
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
		
		cbxIdentityPrinciple = new JCheckBox("");
		cbxIdentityPrinciple.setSelected(true);
		
		cbxAntiRigidity = new JCheckBox("");
		
		JLabel lblRelator = new JLabel("Relator Constraint");
		lblRelator.setToolTipText("");
		lblRelator.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblWeakSupplementation = new JLabel("Weak Supplementation Constraint");
		lblWeakSupplementation.setToolTipText("");
		lblWeakSupplementation.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblIdentityPrinciple = new JLabel(" Identity Principle");
		lblIdentityPrinciple.setToolTipText("");
		lblIdentityPrinciple.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblAntirigidity = new JLabel("Show AntiRigidity");
		lblAntirigidity.setToolTipText("");
		lblAntirigidity.setFont(new Font("Dialog", Font.BOLD, 12));
				
		JLabel lblEnforcements = new JLabel("Enforcement Rules");
		lblEnforcements.setFont(new Font("Dialog", Font.BOLD, 14));
		
		// relator constraint (INFO)
		btnRelatorInfo = new JButton("");
		btnRelatorInfo.setToolTipText("<html>\r\n\r\n<br/>\r\nThis rule enforces that the concrete relators mediate two distinct entities.\r\n<br/><br/>\r\nUncheck this box if you have at least one relator that the sum of its mediation's \r\n<br/>\r\ncardinality at the target side is less than 2. \r\n<br/><br/>\r\n\r\n</html>\r\n");
		btnRelatorInfo.setIcon(new ImageIcon(EnforcePanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/info.png")));
		btnRelatorInfo.setFocusPainted(false);
		btnRelatorInfo.setBorderPainted(false);
		btnRelatorInfo.setContentAreaFilled(false);
		btnRelatorInfo.setRolloverIcon(new ImageIcon(EnforcePanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/info-rollover.png")));

		// weak supplementation constraint (INFO)
		btnWeakInfo = new JButton("");
		btnWeakInfo.setIcon(new ImageIcon(EnforcePanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/info.png")));
		btnWeakInfo.setToolTipText("<html>\r\n\r\n<br/>\r\nThis rule enforces that each whole has at least two disjoint parts.\r\n<br/><br/>\r\nUncheck this box if there's a meronymic relation where the part side has minimum cardinality less than 2.\r\n<br/><br/>\r\n\r\n</html>");
		btnWeakInfo.setFocusPainted(false);
		btnWeakInfo.setContentAreaFilled(false);
		btnWeakInfo.setBorderPainted(false);
		btnWeakInfo.setRolloverIcon(new ImageIcon(EnforcePanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/info-rollover.png")));
		
		// identity principle (INFO)
		btnIdentityInfo = new JButton("");
		btnIdentityInfo.setIcon(new ImageIcon(EnforcePanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/info.png")));
		btnIdentityInfo.setToolTipText("<html>\r\n\r\n<br/>\r\nThis rule enforces that all objects have an identity principle.\r\n<br/><br/>\r\nUncheck this box if you have at least one element without identity principle (i.e. an element <br/>\r\nthat has no kind, quantity or collective as its supertype).\r\n<br/><br/>\r\n\r\n</html>");
		btnIdentityInfo.setFocusPainted(false);
		btnIdentityInfo.setContentAreaFilled(false);
		btnIdentityInfo.setBorderPainted(false);		
		btnIdentityInfo.setRolloverIcon(new ImageIcon(EnforcePanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/info-rollover.png")));
		
		// Show Antirigidity (INFO)
		btnAntirigidityInfo = new JButton("");
		btnAntirigidityInfo.setIcon(new ImageIcon(EnforcePanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/info.png")));
		btnAntirigidityInfo.setToolTipText("<html>\r\n\r\n<br/>\r\nThis rule enforces the anti-rigidity axiom. \r\n<br/><br/>\r\nCheck this box if you always want to visualize objects instantiating an anti-rigid type \r\nin a World and not instantiating it in another World.\r\n<br/><br/>\r\nNote: if you enforce this axiom you need to simulate the model with at least two Worlds.\r\n<br/><br/>\r\n\r\n</html>");
		btnAntirigidityInfo.setFocusPainted(false);
		btnAntirigidityInfo.setContentAreaFilled(false);
		btnAntirigidityInfo.setBorderPainted(false);
		btnAntirigidityInfo.setRolloverIcon(new ImageIcon(EnforcePanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/info-rollover.png")));
		
		GroupLayout gl_RulesPanel = new GroupLayout(this);
		gl_RulesPanel.setHorizontalGroup(
			gl_RulesPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_RulesPanel.createSequentialGroup()
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_RulesPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEnforcements, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_RulesPanel.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_RulesPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAntirigidityInfo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cbxAntiRigidity)
									.addGap(6)
									.addComponent(lblAntirigidity, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
								.addGroup(gl_RulesPanel.createSequentialGroup()
									.addGroup(gl_RulesPanel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_RulesPanel.createSequentialGroup()
											.addComponent(btnIdentityInfo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cbxIdentityPrinciple))
										.addGroup(gl_RulesPanel.createSequentialGroup()
											.addComponent(btnRelatorInfo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cbxRelator))
										.addGroup(gl_RulesPanel.createSequentialGroup()
											.addComponent(btnWeakInfo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cbxWeakSupplementation)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblIdentityPrinciple, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblWeakSupplementation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblRelator, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))))))
					.addGap(30))
		);
		gl_RulesPanel.setVerticalGroup(
			gl_RulesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RulesPanel.createSequentialGroup()
					.addGap(22)
					.addComponent(lblEnforcements, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRelator, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
						.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(cbxRelator, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnRelatorInfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(9)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWeakSupplementation, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
						.addComponent(cbxWeakSupplementation, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
						.addComponent(btnWeakInfo, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIdentityPrinciple, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(btnIdentityInfo, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(cbxIdentityPrinciple, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAntirigidityInfo, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
						.addComponent(cbxAntiRigidity, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
						.addComponent(lblAntirigidity, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
					.addGap(36))
		);
		this.setLayout(gl_RulesPanel);		
	}
}
