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
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.derivedtypes.DerivedByIntersection;
import br.ufes.inf.nemo.oled.DiagramManager;
import java.awt.Dimension;

/**
 * @author Cássio Reginato
 */
public class IntersectionPattern extends JDialog {

	private static final long serialVersionUID = 7188569027915040661L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtBase;
	private JTextField txtBase_1;
	private JTextField txtDerived;
	@SuppressWarnings("rawtypes")
	JComboBox combo_base_1 = new JComboBox();
	@SuppressWarnings("rawtypes")
	JComboBox combo_base_2 = new JComboBox();
	@SuppressWarnings("rawtypes")
	JComboBox combo_derived = new JComboBox();
	@SuppressWarnings("rawtypes")
	Vector comboBoxItems2=new Vector();
	@SuppressWarnings("rawtypes")
	Vector comboBoxItemsDer=new Vector();
	private Point2D.Double location= new Point2D.Double();
	static DiagramManager dm;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public IntersectionPattern(final DiagramManager dm) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IntersectionPattern.class.getResource("/resources/icons/x16/sitemap.png")));
		setTitle("Derivation by Intersection");
		setResizable(false);
	
		//this.setLocation(Integer., y);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 477, 410);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(400, 350));
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Base Types:");
		
		JLabel lblNewLabel_2 = new JLabel("Derived Type:");
		
		txtBase = new JTextField();
		txtBase.setForeground(Color.DARK_GRAY);
		txtBase.setText("Base 1");
		txtBase.setColumns(10);
		
		txtBase_1 = new JTextField();
		txtBase_1.setForeground(Color.DARK_GRAY);
		txtBase_1.setText("Base 2");
		txtBase_1.setColumns(10);
		
		txtDerived = new JTextField();
		txtDerived.setForeground(Color.DARK_GRAY);
		txtDerived.setText("Derived");
		txtDerived.setColumns(10);
		
		
		combo_base_1.addActionListener(new ActionListener() {
			@SuppressWarnings({ })
			public void actionPerformed(ActionEvent e) {
				
				if(combo_base_1.getSelectedItem().toString().equals("Kind") ||  combo_base_1.getSelectedItem().toString().equals("Collective") || combo_base_1.getSelectedItem().toString().equals("Quantity")){
					
					comboBoxItems2.removeAllElements();
					
					DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems2);	
					model.addElement("RoleMixin");
					model.addElement("Mixin");
					model.addElement("Category");
					combo_base_2.setModel(model);
					combo_base_2.getModel().setSelectedItem(model.getElementAt(0));
				}else{
					comboBoxItems2.removeAllElements();
					DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems2);	
					model.addElement("Subkind");
					if(combo_base_1.getSelectedItem().toString().equals("Role Mixin") || combo_base_1.getSelectedItem().toString().equals("Mixin") || combo_base_1.getSelectedItem().toString().equals("Category")){
						model.addElement("Kind");
						model.addElement("Collective");
						model.addElement("Quantity");
					}
					model.addElement("Role");
					model.addElement("Phase");
					model.addElement("RoleMixin");
					model.addElement("Mixin");
					model.addElement("Category");
					combo_base_2.setModel(model);
					combo_base_2.getModel().setSelectedItem(model.getElementAt(0));
				}
				setDerivedStereotype();
			}

			
		});
		
		
		combo_base_1.setModel(new DefaultComboBoxModel(new String[] {"Subkind", "Kind", "Collective", "Quantity", "Role", "Phase", "Category", "Mixin", "RoleMixin"}));
		
		
		combo_base_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDerivedStereotype();
			}
		});
		combo_base_2.setModel(new DefaultComboBoxModel(new String[] {"Subkind", "Role", "Phase", "Category", "Mixin", "RoleMixin"}));
		
		
		combo_derived.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDerivedStereotype();
			}
		});
		
		combo_derived.setModel(new DefaultComboBoxModel(new String[] {"Subkind"}));
		
		JTextPane txtpnTheDerivedType = new JTextPane();
		txtpnTheDerivedType.setEditable(false);		
		txtpnTheDerivedType.setText("The population of a derived type by intersection is the overlap of the base types");
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(IntersectionPattern.class.getResource("/resources/figures/derivationbyintersection.PNG")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtpnTheDerivedType, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(txtBase, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtDerived, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(combo_base_1, 0, 90, Short.MAX_VALUE)
											.addComponent(combo_derived, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(txtBase_1, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(combo_base_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(124)
							.addComponent(label)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnTheDerivedType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addGap(6)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(combo_base_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtBase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtBase_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(combo_base_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDerived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(combo_derived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setHorizontalAlignment(SwingConstants.RIGHT);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DerivedTypesOperations.intersectionPattern(dm, txtBase.getText(),txtBase_1.getText(),txtDerived.getText(),location,combo_base_1.getSelectedItem().toString(),combo_base_2.getSelectedItem().toString(), combo_derived.getSelectedItem().toString());
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setHorizontalAlignment(SwingConstants.RIGHT);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setDerivedStereotype() {
		// TODO Auto-generated method stub
		
		ArrayList<String> result = DerivedByIntersection.getInstance().inferStereotype(combo_base_1.getSelectedItem().toString(), combo_base_2.getSelectedItem().toString());
		
			comboBoxItemsDer.removeAllElements();
			DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItemsDer);	
			for (String res : result) {
				model.addElement(res);
			}
			combo_derived.setModel(model);
			combo_derived.getModel().setSelectedItem(model.getElementAt(0));
		
		
	}
	public void setPosition(java.lang.Double x, java.lang.Double y){
		location.x= x;
		location.y= y;
	}
}
