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
package br.ufes.inf.nemo.oled.derivation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.oled.DiagramManager;

/**
 * @author Cássio Reginato
 */
public class PastSpecializationPattern extends JDialog {

	private static final long serialVersionUID = 4236441153290954092L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_supertype;
	private JTextArea txtrADerivedType;
	private JTextField txt_special;
	private JTextField txt_past;
	@SuppressWarnings("rawtypes")
	JComboBox cmb_past = new JComboBox();
	@SuppressWarnings("rawtypes")
	JComboBox cmb_special = new JComboBox();
	Point2D.Double location = new Point2D.Double();
	DiagramManager dman;
	@SuppressWarnings("rawtypes")
	JComboBox cmb_super = new JComboBox();
	
	/**


	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PastSpecializationPattern(DiagramManager dm) {
		dman=dm;
		setTitle("Derivation By Past Specialization");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PastSpecializationPattern.class.getResource("/resources/icons/x16/sitemap.png")));
		setResizable(false);
		setBounds(100, 100, 426, 523);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			txtrADerivedType = new JTextArea();
			txtrADerivedType.setEditable(false);
			txtrADerivedType.setText("All individuals of the type derived by past specialization \r\ninstantiated a specific type in the past but not currently.");
			txtrADerivedType.setRows(3);
			txtrADerivedType.setColumns(3);
			txtrADerivedType.setWrapStyleWord(true);
		}
		{
			txt_supertype = new JTextField();
			txt_supertype.setText("Supertype");
			txt_supertype.setColumns(10);
		}
		
		JLabel lblNewLabel = new JLabel("Supertype");
		
		JLabel lblNewLabel_1 = new JLabel("Specialization");
		
		txt_special = new JTextField();
		txt_special.setText("Specialization");
		txt_special.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Derived By Past Specialization");
		
		txt_past = new JTextField();
		txt_past.setText("Derived");
		txt_past.setColumns(10);
		

		cmb_super.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "Subkind", "Role", "Phase"}));
		
		
		cmb_special.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmb_special.getSelectedItem().toString().equals("Role")){
					cmb_past.setSelectedIndex(1);
				}else{
					cmb_past.setSelectedIndex(0);
				}
			}
		});
		cmb_special.setModel(new DefaultComboBoxModel(new String[] {"Phase", "Role"}));
		
		
		cmb_past.setModel(new DefaultComboBoxModel(new String[] {"Phase", "Role"}));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(PastSpecializationPattern.class.getResource("/resources/figures/derivation_by_past.png")));
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("generate temporal OCL rule");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		
		JLabel lblTempexWorkerallinstancesselfforallwk = new JLabel("");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txt_past, Alignment.LEADING)
										.addComponent(txt_special, Alignment.LEADING)
										.addComponent(txt_supertype, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2, Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(lblTempexWorkerallinstancesselfforallwk)
											.addGap(18)
											.addComponent(lblNewLabel_3))
										.addComponent(chckbxNewCheckBox, Alignment.LEADING))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(cmb_past, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cmb_special, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cmb_super, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(46)
							.addComponent(txtrADerivedType, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(txtrADerivedType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_supertype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_super, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_special, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_special, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_past, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_past, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(chckbxNewCheckBox)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
							.addComponent(lblTempexWorkerallinstancesselfforallwk))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(lblNewLabel_3)
							.addContainerGap())))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DerivedTypesOperations.createPastSpecializationPattern(dman,location,txt_supertype.getText(),cmb_super.getSelectedItem().toString(),txt_special.getText(),cmb_special.getSelectedItem().toString(),txt_past.getText(),cmb_past.getSelectedItem().toString(),chckbxNewCheckBox.isSelected());
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void setPosition(double x, double y) {
		// TODO Auto-generated method stub
		location.x= x;
		location.y= y;
		
	}
}
