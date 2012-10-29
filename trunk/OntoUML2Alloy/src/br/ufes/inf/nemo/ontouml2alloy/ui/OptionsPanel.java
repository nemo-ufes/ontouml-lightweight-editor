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
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This panel was created using the Windows Builder in Eclipse.
 * 
 * 	@author John Guerson
 *
 */

public class OptionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JPanel panel;
	
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
	
	public OptionsPanel(Options opt)
	{
		cbxAntiRigidity.setSelected(opt.antiRigidity);
		cbxIdentityPrinciple.setSelected(opt.identityPrinciple);
		cbxRelator.setSelected(opt.relatorConstraint);
		cbxWeakSupplementation.setSelected(opt.weakSupplementationConstraint);
	}
	
	/**
	 * Create the panel.
	 */
	
	public OptionsPanel() 
	{
		
		JTextPane txtpnUncheckingTheEnforcement = new JTextPane();
		txtpnUncheckingTheEnforcement.setEditable(false);
		txtpnUncheckingTheEnforcement.setBackground(UIManager.getColor("Panel.background"));
		txtpnUncheckingTheEnforcement.setText("Unchecking the enforcement of any axiom will allow the full simulation of incomplete models. \r\n\r\nOnly uncheck them if you are aware that your model violates them and exactly why that happens. \r\n\r\nIf you are unsure, run the Syntatic Validation before simulating your model.\r\n\r\nEach enforcable axiom have a description of what it does and when to uncheck it.");
		
		panel = new JPanel();
		
		GroupLayout gl_RulesPanel = new GroupLayout(this);
		gl_RulesPanel.setHorizontalGroup(
			gl_RulesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RulesPanel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_RulesPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
						.addComponent(txtpnUncheckingTheEnforcement, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
					.addGap(26))
		);
		gl_RulesPanel.setVerticalGroup(
			gl_RulesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RulesPanel.createSequentialGroup()
					.addGap(22)
					.addComponent(txtpnUncheckingTheEnforcement, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		
		/* =================== Relator Constraint ===================== */
		
		cbxRelator = new JCheckBox("");
		cbxRelator.setSelected(true);
		
		JLabel lblRelator = new JLabel("Relator Constraint");
		lblRelator.setToolTipText("");
		lblRelator.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnRelatorInfo = new JButton("");
		btnRelatorInfo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String info = 
						""+"This rule enforces that the concrete relators mediate two distinct entities."+"\n\n"+
						"Uncheck this box if you have at least one relator that the sum of its mediation's"+"\n" + 
						"cardinality at the target side is less than 2."+"\n"; 
				AxiomInfoDialog.open(OptionsPanel.this,"Relator Axiom",info);				
			}
		});
		btnRelatorInfo.setToolTipText("Click here to see the Axiom Description...");
		btnRelatorInfo.setIcon(new ImageIcon(OptionsPanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/about.png")));
		btnRelatorInfo.setFocusPainted(false);
		btnRelatorInfo.setBorderPainted(false);
		btnRelatorInfo.setContentAreaFilled(false);
		btnRelatorInfo.setRolloverIcon(new ImageIcon(OptionsPanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/about-rollover.png")));
		
		/* =================== Weak Supplementation Axiom ==================== */
		
		cbxWeakSupplementation = new JCheckBox("");	
		cbxWeakSupplementation.setSelected(true);
		
		JLabel lblWeakSupplementation = new JLabel("Weak Supplementation Constraint");
		lblWeakSupplementation.setToolTipText("");
		lblWeakSupplementation.setFont(new Font("Tahoma", Font.PLAIN, 11));
				
		btnWeakInfo = new JButton("");
		btnWeakInfo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String info = 
						""+"This rule enforces that each whole has at least two disjoint parts."+"\n\n"+
						"Uncheck this box if there's a meronymic relation where the part side has minimum \n"+"cardinality less than 2."+"\n"; 
				AxiomInfoDialog.open(OptionsPanel.this,"Weak Supplementation",info);				
			}
		});
		btnWeakInfo.setIcon(new ImageIcon(OptionsPanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/about.png")));
		btnWeakInfo.setToolTipText("Click here to see the Axiom Description...");
		btnWeakInfo.setFocusPainted(false);
		btnWeakInfo.setContentAreaFilled(false);
		btnWeakInfo.setBorderPainted(false);
		btnWeakInfo.setRolloverIcon(new ImageIcon(OptionsPanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/about-rollover.png")));
		
		/* ======================= Identity Axiom ======================= */
		
		cbxIdentityPrinciple = new JCheckBox("");
		cbxIdentityPrinciple.setSelected(true);
		
		JLabel lblIdentityPrinciple = new JLabel(" Identity Principle");
		lblIdentityPrinciple.setToolTipText("");
		lblIdentityPrinciple.setFont(new Font("Tahoma", Font.PLAIN, 11));

		btnIdentityInfo = new JButton("");
		btnIdentityInfo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String info = 
						""+"This rule enforces that all objects have an identity principle."+"\n\n"+
						"Uncheck this box if you have at least one element without identity principle " +"\n"+
						"(i.e. an element that has no kind, quantity or collective as its supertype)."+"\n";
				AxiomInfoDialog.open(OptionsPanel.this,"Identity Principle",info);		
			}
		});
		btnIdentityInfo.setIcon(new ImageIcon(OptionsPanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/about.png")));
		btnIdentityInfo.setToolTipText("Click here to see the Axiom Description...");
		btnIdentityInfo.setFocusPainted(false);
		btnIdentityInfo.setContentAreaFilled(false);
		btnIdentityInfo.setBorderPainted(false);		
		btnIdentityInfo.setRolloverIcon(new ImageIcon(OptionsPanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/about-rollover.png")));
		
		/* ======================= Antirigidity Axiom ======================= */
		
		cbxAntiRigidity = new JCheckBox("");
		
		JLabel lblAntirigidity = new JLabel("Show AntiRigidity");
		lblAntirigidity.setToolTipText("");
		lblAntirigidity.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnAntirigidityInfo = new JButton("");
		btnAntirigidityInfo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String info = 
						""+"This rule enforces the anti-rigidity axiom."+"\n\n"+ 
						"Check this box if you always want to visualize objects instantiating an anti-rigid type"+"\n"+ 
						"in a World and not instantiating it in another World."+"\n\n"+
						"Note: if you enforce this axiom you need to simulate the model with at least two Worlds."+"\n";
				AxiomInfoDialog.open(OptionsPanel.this,"Antirigidity",info);	
			}
		});
		btnAntirigidityInfo.setIcon(new ImageIcon(OptionsPanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/about.png")));
		btnAntirigidityInfo.setToolTipText("Click here to see the Axiom Description...");
		btnAntirigidityInfo.setFocusPainted(false);
		btnAntirigidityInfo.setContentAreaFilled(false);
		btnAntirigidityInfo.setBorderPainted(false);
		btnAntirigidityInfo.setRolloverIcon(new ImageIcon(OptionsPanel.class.getResource("/resources/br/ufes/inf/nemo/ontouml2alloy/about-rollover.png")));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(cbxAntiRigidity)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblAntirigidity)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAntirigidityInfo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(cbxIdentityPrinciple)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblIdentityPrinciple)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnIdentityInfo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(cbxWeakSupplementation)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblWeakSupplementation, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(btnWeakInfo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(cbxRelator)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblRelator)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRelatorInfo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addGap(244))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnRelatorInfo, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
						.addComponent(lblRelator, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxRelator, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnWeakInfo, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(lblWeakSupplementation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxWeakSupplementation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnIdentityInfo, 0, 0, Short.MAX_VALUE)
						.addComponent(lblIdentityPrinciple, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxIdentityPrinciple, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnAntirigidityInfo, 0, 0, Short.MAX_VALUE)
						.addComponent(lblAntirigidity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxAntiRigidity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		this.setLayout(gl_RulesPanel);		
	}
}
