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
import java.awt.SystemColor;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.derivedtypes.DerivedByUnion;
import br.ufes.inf.nemo.oled.DiagramManager;

/**
 * @author Cássio Reginato
 */
public class UnionPattern extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtBase_1;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_1 = new JComboBox();
	private JTextField txtBase;
	private JTextField txtDerived;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox = new JComboBox();
	@SuppressWarnings({ "rawtypes", "unused" })
	private JComboBox comboBox1 = new JComboBox();
	private JLabel lblNewLabel = new JLabel("Types:");
	private DiagramManager diagramMan;
	private JLabel lblNewLabel_1;
	private Point2D.Double location= new Point2D.Double();
	@SuppressWarnings("rawtypes")
	private final JComboBox comboBox_2 = new JComboBox();
	@SuppressWarnings("rawtypes")
	Vector comboBoxItems=new Vector();
	@SuppressWarnings("rawtypes")
	Vector comboBoxItems2=new Vector();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	final DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);
	private final JLabel lblNewLabel_3 = new JLabel("");
	private final JLabel lblNewLabel_4 = new JLabel("");
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public void setPosition(java.lang.Double x, java.lang.Double y){
		location.x= x;
		location.y= y;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setCombo(){
		ArrayList<String> stereo=DerivedByUnion.getInstance().inferStereotype(comboBox.getModel().getSelectedItem().toString(), comboBox_1.getModel().getSelectedItem().toString());
		if(stereo!=null){
			comboBoxItems2.removeAllElements();
			DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems2);	
			for (String string : stereo) {
				model.addElement(string);
			}
			comboBox_2.setModel(model);
			comboBox_2.getModel().setSelectedItem(model.getElementAt(0));
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UnionPattern(DiagramManager diagramManager) {
		lblNewLabel_4.setIcon(new ImageIcon(UnionPattern.class.getResource("/resources/figures/text-union.png")));
		setBackground(SystemColor.controlLtHighlight);
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setModal(true);
		setTitle("Derivation by Union");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UnionPattern.class.getResource("/resources/icons/x16/sitemap.png")));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Category"}));
		this.diagramMan= diagramManager;
		setBounds(100, 100, 413, 487);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		location.x= this.getLocation().getX();
		location.y= this.getLocation().getY();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{

			lblNewLabel.setForeground(Color.BLACK);
		}
		{
			txtBase = new JTextField();
			txtBase.setText("Base 1");
			txtBase.setColumns(10);
		}
		{
			comboBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setCombo();
				}
			});

			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "SubKind", "Category", "Role", "Phase", "RoleMixin", "Mixin"}));
		}
		{
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setCombo();
				}
			});
			
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "SubKind", "Category", "Role", "Phase", "RoleMixin", "Mixin"}));
		}
		{
			txtBase_1 = new JTextField();
			txtBase_1.setText("Base 2");
			txtBase_1.setColumns(10);
		}
		{

			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "SubKind", "Category", "Role", "Phase", "RoleMixin", "Mixin"}));
		}
		{
			lblNewLabel_1 = new JLabel("Derived Type:");
			lblNewLabel_1.setForeground(Color.BLACK);
		}
		{
			txtDerived = new JTextField();
			txtDerived.setText("Derived");
			txtDerived.setColumns(10);
		}
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setIcon(new ImageIcon(UnionPattern.class.getResource("/resources/figures/derivation_by_union.PNG")));
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(txtBase_1)
											.addComponent(txtBase, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
											.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
									.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(txtDerived, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(63)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_3))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(41)
							.addComponent(lblNewLabel_4)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4))
					.addGap(23)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDerived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtBase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtBase_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(39, Short.MAX_VALUE))
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
						ArrayList<String>values= new ArrayList<String>();
						if(comboBox.getModel().getSelectedItem()!=null && comboBox_1.getModel().getSelectedItem()!=null && comboBox_2.getModel().getSelectedItem()!=null){
							values.add(comboBox_2.getModel().getSelectedItem().toString());
							values.add(comboBox.getModel().getSelectedItem().toString());
							values.add(comboBox_1.getModel().getSelectedItem().toString());

							values.add(txtDerived.getText());
							values.add(txtBase_1.getText());
							values.add(txtBase.getText());
							DerivedTypesOperations.exclusionPattern(diagramMan,values,location);
							dispose();
						}
						else{
							DerivedTypesOperations.wrongSelection("Please, select all the stereotypes");
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
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
