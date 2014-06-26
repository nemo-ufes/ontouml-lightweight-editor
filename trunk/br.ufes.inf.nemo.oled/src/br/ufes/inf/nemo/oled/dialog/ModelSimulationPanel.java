/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * @author John Guerson
 */

public class ModelSimulationPanel extends JPanel {

	private static final long serialVersionUID = 4762609414668205905L;
		
	@SuppressWarnings("unused")
	private OntoUML2AlloyOptions refOptions;
		
	private AppFrame frame;
	private JCheckBox cbxRelator;
	private JCheckBox cbxWeak ;	
	private JCheckBox cbxIdentity ;
	private JCheckBox cbxAntirigidity;
	private JButton btnEnableall;
	private JButton btnDisableall;
	private JButton lblSateRelator;
	private JButton lblStateWeak;
	private JButton lblStateIdentity;
	private JButton lblStateAntirigidity;
	private JLabel lblChooseWhichAxioms;

	private JButton btndefault;
	private JButton relatorInfoButton;
	private JButton identityInfoButton;
	private JButton AntiRigidInfoButton;

	private JButton weakInfoButton;
	
	/**
	 * Creates a View from OntoUML Options Model and the main frame application.
	 * 
	 * @param optModel
	 * @param frame
	 */
	public ModelSimulationPanel(OntoUML2AlloyOptions refOptions, AppFrame frame)
	{
		this();
		
		this.refOptions = refOptions;
		this.frame = frame;
	
		setOntoUMLOptionsPane(refOptions,frame);
	}

	/**
	 * Set OntoUML Options Pane from OntoUML2Alloy Options.
	 */
	public void setOntoUMLOptionsPane (OntoUML2AlloyOptions refOptions, AppFrame frame)
	{
		this.frame = frame;
		this.cbxAntirigidity.setSelected(refOptions.antiRigidity);
				
		this.cbxIdentity.setSelected(refOptions.identityPrinciple);
		if (refOptions.identityPrincipleInvalid) {
			lblStateIdentity.setIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/error.png")));
			lblStateIdentity.setRolloverIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/error-rollover.png")));
			lblStateIdentity.setToolTipText("<html>"+refOptions.identityPrincipleInvalidMsg.replace("\n", "<br>")+"</html>");
		}else{
			lblStateIdentity.setIcon(null);
		}
		
		this.cbxRelator.setSelected(refOptions.relatorConstraint);
		if (refOptions.relatorConstraintInvalid) {
			lblSateRelator.setIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/error.png")));
			lblSateRelator.setRolloverIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/error-rollover.png")));
			lblSateRelator.setToolTipText("<html>"+refOptions.relatorConstraintInvalidMsg.replace("\n", "<br>")+"</html>");
		}else{
			lblSateRelator.setIcon(null);
		}
		
		this.cbxWeak.setSelected(refOptions.weakSupplementation);
		if (refOptions.weakSupplementationInvalid) {
			lblStateWeak.setIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/error.png")));
			lblStateWeak.setRolloverIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/error-rollover.png")));
			lblStateWeak.setToolTipText("<html>"+refOptions.weakSupplementationInvalidMsg.replace("\n", "<br>")+"</html>");
		}else{
			lblStateWeak.setIcon(null);
		}
		
		invalidate();	
	}

	/**
	 * Creates an Empty Pane for OntoUML2Alloy Options.
	 */
	public ModelSimulationPanel() 
	{
		setBorder(BorderFactory.createTitledBorder("Model"));		
		setBackground(UIManager.getColor("Panel.background"));
		setPreferredSize(new Dimension(532, 177));
		setSize(new Dimension(186, 228));
		
		JPanel panel = new JPanel();		
		panel.setBorder(BorderFactory.createTitledBorder(""));
		
		lblChooseWhichAxioms = new JLabel("<html>Choose which axioms do you want to enforce in simulation.  <br>\r\nThe checked ones are recommented by default.</html>\r\n");
		
		btnEnableall = new JButton("Enable All");
		btnEnableall.setPreferredSize(new Dimension(130, 25));
		
		btnEnableall.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent arg0) 
		{
			cbxAntirigidity.setSelected(true);
			cbxIdentity.setSelected(true);
			cbxRelator.setSelected(true);
			cbxWeak.setSelected(true);
		}
		});
		
		btnDisableall = new JButton("Disable All");
		btnDisableall.setPreferredSize(new Dimension(100, 25));
		
		btnDisableall.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cbxAntirigidity.setSelected(false);
				cbxIdentity.setSelected(false);
				cbxRelator.setSelected(false);
				cbxWeak.setSelected(false);
			}
		});
		
		btndefault = new JButton("Default");
		btndefault.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(lblSateRelator.getIcon()==null) cbxRelator.setSelected(true);
				if(lblStateWeak.getIcon()==null) cbxWeak.setSelected(true);
				if(lblStateIdentity.getIcon()==null) cbxIdentity.setSelected(true);
				cbxAntirigidity.setSelected(false);				
			}
		});		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(122)
							.addComponent(btndefault)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEnableall, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDisableall, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblChooseWhichAxioms, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(4)
					.addComponent(lblChooseWhichAxioms)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEnableall, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDisableall, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btndefault, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(9))
		);

		cbxRelator = new JCheckBox("Relator constraint");		
		cbxRelator.setPreferredSize(new Dimension(160, 20));
		cbxRelator.setToolTipText("");
				
		cbxWeak = new JCheckBox("Weak supplementation");
		cbxWeak.setPreferredSize(new Dimension(160, 20));
		cbxWeak.setToolTipText("");
		
		lblStateWeak = new JButton("");	
		lblStateWeak.setHorizontalAlignment(SwingConstants.CENTER);		
		lblStateWeak.setPreferredSize(new Dimension(30, 20));
		lblStateWeak.setFocusable(false);
		lblStateWeak.setOpaque(false);
		lblStateWeak.setContentAreaFilled(false);
		lblStateWeak.setBorderPainted(false);
		
		weakInfoButton = new JButton("");
		weakInfoButton.setToolTipText("<html>\r\nMark this option if in your model all wholes have two or more parts. <br>\r\nIf this is not true, leave this option unchecked. </html>");
		weakInfoButton.setIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/help.png")));
		weakInfoButton.setRolloverIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/help-rollover.png")));
		weakInfoButton.setPreferredSize(new Dimension(30, 20));
		weakInfoButton.setFocusable(false);
		weakInfoButton.setOpaque(false);
		weakInfoButton.setContentAreaFilled(false);
		weakInfoButton.setBorderPainted(false);
		
		lblSateRelator = new JButton("");		
		lblSateRelator.setHorizontalAlignment(SwingConstants.CENTER);
		lblSateRelator.setPreferredSize(new Dimension(30, 20));
		lblSateRelator.setOpaque(false);
		lblSateRelator.setContentAreaFilled(false);
		lblSateRelator.setBorderPainted(false);
		lblSateRelator.setFocusable(false);
		
		relatorInfoButton = new JButton("");
		relatorInfoButton.setToolTipText("<html>\r\nMark this option if in your model all relators mediate at least two distinct objects. <br>\r\nIf this is not true, leave this option unchecked. </html>\r\n\r\n");
		relatorInfoButton.setIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/help.png")));
		relatorInfoButton.setRolloverIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/help-rollover.png")));
		relatorInfoButton.setPreferredSize(new Dimension(30, 20));
		relatorInfoButton.setFocusable(false);
		relatorInfoButton.setOpaque(false);
		relatorInfoButton.setContentAreaFilled(false);
		relatorInfoButton.setBorderPainted(false);
		
		lblStateIdentity = new JButton("");
		lblStateIdentity.setHorizontalAlignment(SwingConstants.CENTER);
		lblStateIdentity.setPreferredSize(new Dimension(30, 20));
		lblStateIdentity.setOpaque(false);
		lblStateIdentity.setFocusable(false);
		lblStateIdentity.setContentAreaFilled(false);
		lblStateIdentity.setBorderPainted(false);
		
		identityInfoButton = new JButton("");
		identityInfoButton.setToolTipText("<html>\r\nMark this option if you want to visualize objects that does not have identity principle.<br>\r\nIf not, leave this option unchecked. </html>");
		identityInfoButton.setIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/help.png")));
		identityInfoButton.setRolloverIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/help-rollover.png")));
		identityInfoButton.setFocusable(false);
		identityInfoButton.setOpaque(false);
		identityInfoButton.setContentAreaFilled(false);
		identityInfoButton.setBorderPainted(false);
		identityInfoButton.setPreferredSize(new Dimension(30, 20));
		cbxIdentity = new JCheckBox("Identity principle");
		cbxIdentity.setToolTipText("");
		cbxIdentity.setPreferredSize(new Dimension(160, 20));
		
		lblStateAntirigidity = new JButton("");
		
		lblStateAntirigidity.setHorizontalAlignment(SwingConstants.CENTER);
		lblStateAntirigidity.setPreferredSize(new Dimension(30, 20));
		lblStateAntirigidity.setOpaque(false);
		lblStateAntirigidity.setContentAreaFilled(false);
		lblStateAntirigidity.setBorderPainted(false);
		lblStateAntirigidity.setFocusable(false);
		
		AntiRigidInfoButton = new JButton("");
		AntiRigidInfoButton.setToolTipText("<html>\r\nMark this option if you want to enforce the visualization of anti-rigid objects.<br>\r\nIf not, leave this option unchecked. </html>\r\n");
		AntiRigidInfoButton.setIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/help.png")));
		AntiRigidInfoButton.setFocusable(false);
		AntiRigidInfoButton.setOpaque(false);
		AntiRigidInfoButton.setRolloverIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/help-rollover.png")));
		AntiRigidInfoButton.setContentAreaFilled(false);
		AntiRigidInfoButton.setBorderPainted(false);
		AntiRigidInfoButton.setPreferredSize(new Dimension(30, 20));
		cbxAntirigidity = new JCheckBox("Antirigidity visualization");
		cbxAntirigidity.setToolTipText("");
		cbxAntirigidity.setFocusable(false);
		cbxAntirigidity.setPreferredSize(new Dimension(160, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblStateWeak, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(weakInfoButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(cbxWeak, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(lblSateRelator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(relatorInfoButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(cbxRelator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblStateIdentity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(identityInfoButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(cbxIdentity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(lblStateAntirigidity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(AntiRigidInfoButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(cbxAntirigidity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStateWeak, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(weakInfoButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxWeak, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSateRelator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(relatorInfoButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxRelator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStateIdentity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(identityInfoButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxIdentity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStateAntirigidity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(AntiRigidInfoButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxAntirigidity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
	}
	
	public void setStateRelatorConstraint(String text) 
	{
		this.lblSateRelator.setText(text);
	}

	public void setStateWeakSupplementation(String text)
	{
		this.lblStateWeak.setText(text);
	}

	public void setStateIdentityPrinciple(String text)
	{
		this.lblStateIdentity.setText(text);
	}

	public void setStateAntirigidity(String text)
	{
		this.lblStateAntirigidity.setText(text);
	}
	
	/**
	 * Is Selected Relator Constraint Option.
	 * 
	 * @return
	 */	
	public boolean isSelectedRelatorConstraint() { return cbxRelator.isSelected(); }
	
	/**
	 * Is Selected Weak Supplementation Option.
	 * 
	 * @return
	 */
	public boolean isSelectedWeakSupplementation() { return cbxWeak.isSelected(); }
	
	/**
	 * Is Selected AntiRigidity Option.
	 * 
	 * @return
	 */
	public boolean isSelectedAntirigidity() { return cbxAntirigidity.isSelected(); }
		
	/**
	 * Is Selected Identity Principle Option.
	 * 
	 * @return
	 */
	public boolean isSelectedIdentityPrinciple() { return cbxIdentity.isSelected(); }
	
	/**
	 * Get the main frame application.
	 * 
	 * @return
	 */
	public AppFrame getFrame() { return frame; }
}
