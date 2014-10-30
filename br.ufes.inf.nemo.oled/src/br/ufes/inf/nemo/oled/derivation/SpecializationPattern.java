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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.oled.DiagramManager;

/**
 * @author Cássio Reginato
 */

public class SpecializationPattern extends JDialog {

	private static final long serialVersionUID = 2667323744482017452L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField lbl_base;
	private JTextField lbl_derived;
	private JTextField lbl_attribute;
	private JTextField lbl_type_att;
	private DiagramManager dm;
	@SuppressWarnings("rawtypes")
	JComboBox cmb_stereo_base = new JComboBox();
	JLabel lblAttribute = new JLabel("Attribute:");
	@SuppressWarnings("rawtypes")
	JComboBox cmb_stereo_der = new JComboBox();
	JLabel lblType = new JLabel("Type:");
	private Point2D.Double location= new Point2D.Double();
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 * @param diagramManager 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SpecializationPattern(DiagramManager diagramManager) {
		setResizable(false);
		dm= diagramManager;
		setTitle("Derivation By Specialization");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SpecializationPattern.class.getResource("/resources/icons/x16/sitemap.png")));
		setBounds(100, 100, 409, 491);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setForeground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Base Type:");
		
		lbl_base = new JTextField();
		lbl_base.setText("Base");
		lbl_base.setColumns(10);
		
		JLabel lblDerivedBySpecialization = new JLabel("Derived By Specialization:");
		
		lbl_derived = new JTextField();
		lbl_derived.setText("Derived");
		lbl_derived.setColumns(10);
		

		cmb_stereo_base.setModel(new DefaultComboBoxModel(new String[] {"Kind", "SubKind", "Phase", "Role"}));
		

		cmb_stereo_der.setModel(new DefaultComboBoxModel(new String[] {"Phase", "SubKind"}));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(SpecializationPattern.class.getResource("/resources/figures/derivation_by_specialization.jpg")));
		

		
		lbl_attribute = new JTextField();
		lbl_attribute.setEnabled(false);
		lbl_attribute.setColumns(10);
		
		lbl_type_att = new JTextField();
		lbl_type_att.setEnabled(false);
		lbl_type_att.setColumns(10);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Set attribute");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected()){
					lbl_attribute.setEnabled(true);
					lbl_type_att.setEnabled(true);
				}else{
					lbl_attribute.setEnabled(false);
					lbl_type_att.setEnabled(false);
				}
			}
		});
		chckbxNewCheckBox.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(SpecializationPattern.class.getResource("/resources/figures/text-specialization.png")));
		

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
								.addComponent(lblDerivedBySpecialization, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lbl_base, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cmb_stereo_base, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblAttribute, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lbl_attribute, Alignment.LEADING, 126, 126, Short.MAX_VALUE)
												.addComponent(chckbxNewCheckBox, Alignment.LEADING))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lbl_type_att, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblType, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(lbl_derived, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(cmb_stereo_der, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)))
									.addGap(52))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(129)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(31)
							.addComponent(lblNewLabel_2)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addGap(12)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_base, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_stereo_base, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDerivedBySpecialization)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_derived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_stereo_der, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAttribute)
						.addComponent(lblType))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_attribute, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_type_att, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxNewCheckBox)
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addContainerGap())
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
					public void actionPerformed(ActionEvent arg0) {
						if(chckbxNewCheckBox.isSelected()){
							if(lblAttribute.getText().equals("")){
								DerivedTypesOperations.wrongSelection("Please, set the atribute name");
								return;
							}else
							if(lbl_type_att.getText().equals("")){
								DerivedTypesOperations.wrongSelection("Please, set the atribute type");
								return;
							}
							
							DerivedTypesOperations.createDerivedTypeBySpecialization(lbl_base.getText(),lbl_derived.getText(),cmb_stereo_base.getSelectedItem().toString(),cmb_stereo_der.getSelectedItem().toString(), lbl_attribute.getText(), lbl_type_att.getText(),dm,location);
							dispose();
						}else{
							DerivedTypesOperations.createDerivedTypeBySpecialization(lbl_base.getText(),lbl_derived.getText(),cmb_stereo_base.getSelectedItem().toString(),cmb_stereo_der.getSelectedItem().toString(), dm,location);

						}
						
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
		location.x=x;
		location.y=y;
	}
}
