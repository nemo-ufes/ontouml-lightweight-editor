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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.eclipse.swt.widgets.Item;

import br.ufes.inf.nemo.derivedtypes.DerivedByExclusion;
import br.ufes.inf.nemo.oled.DiagramManager;

/**
 * @author Cássio Reginato
 */
public class ExclusionPattern extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtSupertype;
	private JTextField txtBase;
	private JTextField txtDerived;
	@SuppressWarnings("rawtypes")
	JComboBox cmb_base = new JComboBox();
	boolean updated=true;
	@SuppressWarnings("rawtypes")
	Vector comboBoxItems=new Vector();
	@SuppressWarnings("rawtypes")
	Vector comboBoxItems2=new Vector();
	@SuppressWarnings("rawtypes")
	JComboBox cmb_derived = new JComboBox();
	private Point2D.Double location= new Point2D.Double();
	JCheckBox chckbxNewCheckBox = new JCheckBox("generate OCL rule");
	DiagramManager dman;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	final DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmb_super = new JComboBox(model);


	public void setPosition(java.lang.Double x, java.lang.Double y){
		location.x= x;
		location.y= y;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setCombo(){

		if(updated){
			ArrayList<String> stereo=DerivedByExclusion.getInstance().inferStereotype(cmb_super.getModel().getSelectedItem().toString(), cmb_base.getModel().getSelectedItem().toString());
			if(stereo!=null){
				comboBoxItems2.removeAllElements();
				DefaultComboBoxModel model2 = new DefaultComboBoxModel(comboBoxItems2);	
				for (String string : stereo) {
					model2.addElement(string);
				}
				cmb_derived.setModel(model2);
				cmb_derived.getModel().setSelectedItem(model2.getElementAt(0));
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ExclusionPattern(DiagramManager dm) {
		setForeground(Color.WHITE);
		setResizable(false);
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setTitle("Derivation by Exclusion");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExclusionPattern.class.getResource("/resources/icons/x16/sitemap.png")));

		dman = dm;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		location.x= this.getLocation().getX();
		location.y= this.getLocation().getY();

		comboBoxItems.add("Kind");
		setBounds(100, 100, 412, 485);
		getContentPane().setLayout(new BorderLayout());
		//contentPanel.setBackground(SystemColor.menu);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Supertype:");

		txtSupertype = new JTextField();
		txtSupertype.setText("Supertype");
		txtSupertype.setColumns(10);

		txtBase = new JTextField();
		txtBase.setText("Base");
		txtBase.setColumns(10);

		txtDerived = new JTextField();
		txtDerived.setText("Derived");
		txtDerived.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("From Type:");

		JLabel lblNewLabel_2 = new JLabel("Exclusion Derived Type:");
		cmb_super.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCombo();
			}
		});


		cmb_super.setModel(new DefaultComboBoxModel(new String[] {"Category", "Mixin"}));


		cmb_base.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> values= DerivedByExclusion.getInstance().getPossibleGeneralization(cmb_base.getModel().getSelectedItem().toString());
				String valor_padrao="";
				for (String val : values) {
					if(val.equals(cmb_super.getModel().getSelectedItem().toString())){
						valor_padrao=val;
					}
				}
				
				if(values !=null){
					updated=false;
					cmb_super.removeAllItems();
					comboBoxItems.removeAllElements();
					for (String string : values) {
						model.addElement(string);
					}	
					cmb_super.setModel(model);
					if(!valor_padrao.equals("")){
						cmb_super.getModel().setSelectedItem(valor_padrao);
					}else{
						cmb_super.getModel().setSelectedItem(values.get(0));
					}
					contentPanel.repaint();
					contentPanel.validate();
					updated=true;
				}
				setCombo();

			}
		});
		cmb_base.setModel(new DefaultComboBoxModel(new String[] {"Kind", "SubKind", "Role", "Phase", "Quantity", "Collection", "Mixin", "RoleMixin", "Category"}));

		final JCheckBox chckbxNewCheckBox = new JCheckBox("Generate OCL Constraint");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setSelected(true);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(ExclusionPattern.class.getResource("/resources/figures/derivationbyexclusion.PNG")));
		cmb_derived.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Collection", "Quantity", "SubKind", "Category"}));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ExclusionPattern.class.getResource("/resources/figures/text_exclusion.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(36)
							.addComponent(lblNewLabel_3))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(47)
							.addComponent(lblNewLabel_4))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 4, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(txtSupertype, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtBase, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtDerived, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(cmb_derived, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
												.addComponent(cmb_base, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
												.addComponent(cmb_super, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))))
								.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblNewLabel_4)
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDerived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_derived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addGap(4)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtBase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_base, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addGap(5)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSupertype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_super, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addComponent(chckbxNewCheckBox)
					.addGap(1)
					.addComponent(lblNewLabel_3)
					.addGap(16))
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
						ArrayList<String> values = new ArrayList<String>();
						if(cmb_super.getModel().getSelectedItem()!=null && cmb_base.getModel().getSelectedItem()!=null && cmb_derived.getModel().getSelectedItem()!=null){

							//pai
							values.add(cmb_super.getModel().getSelectedItem().toString());
							//filho
							values.add(cmb_base.getModel().getSelectedItem().toString());
							//filho
							values.add(cmb_derived.getModel().getSelectedItem().toString());

							values.add(txtSupertype.getText());
							values.add(txtBase.getText());
							values.add(txtDerived.getText());
							if(chckbxNewCheckBox.isSelected()){
								if(!(cmb_base.getModel().getSelectedItem().toString().equals("Role") && cmb_super.getModel().getSelectedItem().toString().equals("Kind")) )
								{
									if(!((txtSupertype.getText().equals("") || txtBase.getText().equals("") || txtDerived.getText().equals("")))){
										String rule="\ncontext: _'"+txtSupertype.getText()+"'\n"+"inv: not oclIsTypeOf(_'"+txtBase.getText()+"') implies oclIsTypeOf(_'"+txtDerived.getText()+"')";
										dman.getFrame().getBrowserManager().getProjectBrowser().getOCLDocuments().get(0).addContent(rule);
										DerivedTypesOperations.exclusionPattern(dman,values,location);
										dispose();
									}
									else{
										DerivedTypesOperations.wrongSelection("Please set the names for generating the OCL rule");
										return;
									}
								}else{
									DerivedTypesOperations.exclusionPattern(dman,values,location);
								}
							}
						}
						else{
							DerivedTypesOperations.wrongSelection("Please, select all the stereotypes");
							return;
						}
						dispose();
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
}
