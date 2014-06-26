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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.eclipse.ocl.uml.UMLEnvironment;
import org.eclipse.uml2.uml.Constraint;

/**
 * @author John Guerson
 */

public class SingleConstraintPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JComboBox<?> comboTransformationType;	
	public JTextField txtConstraintName;	
	public JCheckBox checkEnforce;
	public JSpinner spinCommandScope;
	public JLabel lblScope;
	public Constraint constraint;	
	public JLabel lblBitwidht;
	public JPanel ctPanel;
	public JSpinner bitSpinner;
	public JLabel lblWorlds;
	public JSpinner worldSpinner;
	
	/**
	 * Constructor.
	 * 
	 * @param ct
	 * @param ctType
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SingleConstraintPanel(Constraint ct, String ctType, UMLEnvironment env)
	{
		this();
		
		this.constraint = ct;		
		if(ctType=="invariant") ctType = "inv";
		else if (ctType=="derivation") ctType = "derive";
		else if (ctType=="temporal") ctType = "temp";
		
		if (ctType=="inv" && ct.getName()==null) txtConstraintName.setText(ctType+" <empty-name>");
		else if (ctType=="inv") txtConstraintName.setText(ctType+" "+ct.getName());
		
		if (ctType=="temp" && ct.getName()==null) txtConstraintName.setText(ctType+" <empty-name>");
		else if (ctType=="temp") txtConstraintName.setText(ctType+" "+ct.getName());
		
		org.eclipse.ocl.util.ToStringVisitor visitor = org.eclipse.ocl.util.ToStringVisitor.getInstance(env);
		String text = visitor.visitConstraint(ct);
		
		if (ctType=="derive") {			
			txtConstraintName.setText(ctType+" "+text.substring(8,text.indexOf("derive")).trim());
		}
				
		txtConstraintName.setToolTipText("<html>"+text.replace("\n","<br>").replace("null", "")+"</html>");
	}
		
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SingleConstraintPanel() 
	{
//		setBorder(BorderFactory.createTitledBorder(""));		
		setPreferredSize(new Dimension(433, 76));
		
		JPanel optPanel = new JPanel();		
		FlowLayout fl_optPanel = (FlowLayout) optPanel.getLayout();
		fl_optPanel.setAlignment(FlowLayout.LEFT);
		//optPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		ctPanel = new JPanel();
//		panel_1.setBorder(BorderFactory.createTitledBorder(""));
		
		GroupLayout gl_panel = new GroupLayout(this);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(ctPanel, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
						.addComponent(optPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(22))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(ctPanel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(optPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5))
		);
		ctPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(SingleConstraintPanel.class.getResource("/resources/icons/x16/text-editor.png")));
		lblIcon.setPreferredSize(new Dimension(30, 20));
		ctPanel.add(lblIcon);
		
		txtConstraintName = new JTextField();
		txtConstraintName.setPreferredSize(new Dimension(73, 20));
		txtConstraintName.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtConstraintName.setHorizontalAlignment(SwingConstants.LEFT);
		txtConstraintName.setEditable(false);		
		txtConstraintName.setColumns(45);
		ctPanel.add(txtConstraintName);
		
		checkEnforce = new JCheckBox("");		
		optPanel.add(checkEnforce);
		checkEnforce.setPreferredSize(new Dimension(30, 20));
		checkEnforce.setBorder(BorderFactory.createTitledBorder(""));
		checkEnforce.setHorizontalAlignment(SwingConstants.CENTER);
		checkEnforce.setSelected(true);
		
		comboTransformationType = new JComboBox();		
		optPanel.add(comboTransformationType);
		comboTransformationType.setPreferredSize(new Dimension(80, 20));
		comboTransformationType.setModel(new DefaultComboBoxModel(new String[] {"FACT ", "SIMULATE", "CHECK"}));
		
		comboTransformationType.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboTransformationType.getSelectedIndex()==0){
					spinCommandScope.setEnabled(false);
					bitSpinner.setEnabled(false);
					lblBitwidht.setEnabled(false);
					lblScope.setEnabled(false);
					lblWorlds.setEnabled(false);
					worldSpinner.setEnabled(false);
				}else{
					spinCommandScope.setEnabled(true);
					bitSpinner.setEnabled(true);
					lblBitwidht.setEnabled(true);
					lblScope.setEnabled(true);
					lblWorlds.setEnabled(true);
					worldSpinner.setEnabled(true);
				}					
			}
		});
		
		lblScope = new JLabel("Scope:");
		lblScope.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScope.setPreferredSize(new Dimension(40, 20));
		optPanel.add(lblScope);
		
		spinCommandScope = new JSpinner();
		spinCommandScope.setPreferredSize(new Dimension(40, 20));
		optPanel.add(spinCommandScope);
		spinCommandScope.setModel(new SpinnerNumberModel(10, 0, 32, 1));
		
		lblBitwidht = new JLabel("Bitwidth:");
		lblBitwidht.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBitwidht.setPreferredSize(new Dimension(50, 20));
		optPanel.add(lblBitwidht);
		
		bitSpinner = new JSpinner();
		bitSpinner.setPreferredSize(new Dimension(40, 20));
		optPanel.add(bitSpinner);
		bitSpinner.setModel(new SpinnerNumberModel(7, 0, 32, 1));
		setLayout(gl_panel);		
		
		lblWorlds = new JLabel("Worlds:");
		lblWorlds.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWorlds.setPreferredSize(new Dimension(45, 20));
		optPanel.add(lblWorlds);
		
		worldSpinner = new JSpinner();
		worldSpinner.setModel(new SpinnerNumberModel(3, 0, 32, 1));
		worldSpinner.setPreferredSize(new Dimension(40, 20));
		optPanel.add(worldSpinner);
		
		spinCommandScope.setEnabled(false);
		bitSpinner.setEnabled(false);
		lblBitwidht.setEnabled(false);
		lblScope.setEnabled(false);
		lblWorlds.setEnabled(false);
		worldSpinner.setEnabled(false);
	}
}
