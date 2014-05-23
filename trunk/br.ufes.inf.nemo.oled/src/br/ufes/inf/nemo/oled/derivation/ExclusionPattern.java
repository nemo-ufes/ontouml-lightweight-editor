package br.ufes.inf.nemo.oled.derivation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.derivedtypes.DerivedByExclusion;
import br.ufes.inf.nemo.oled.DiagramManager;

import javax.swing.JCheckBox;
import java.awt.Toolkit;

public class ExclusionPattern extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_1 = new JComboBox();
	boolean updated=false;
	@SuppressWarnings("rawtypes")
	Vector comboBoxItems=new Vector();
	@SuppressWarnings("rawtypes")
	Vector comboBoxItems2=new Vector();
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_2 = new JComboBox();
	private Point2D.Double location= new Point2D.Double();
	JCheckBox chckbxNewCheckBox = new JCheckBox("generate OCL rule");
	DiagramManager dman;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	final DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox comboBox = new JComboBox(model);


	public void setPosition(java.lang.Double x, java.lang.Double y){
		location.x= this.getLocation().getX();
		location.y= this.getLocation().getY();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setCombo(){

		if(updated){
			ArrayList<String> stereo=DerivedByExclusion.getInstance().inferStereotype(comboBox.getModel().getSelectedItem().toString(), comboBox_1.getModel().getSelectedItem().toString());
			if(stereo!=null){
				comboBoxItems2.removeAllElements();
				DefaultComboBoxModel model2 = new DefaultComboBoxModel(comboBoxItems2);	
				for (String string : stereo) {
					model2.addElement(string);
				}
				comboBox_2.setModel(model2);
				comboBox_2.getModel().setSelectedItem(model2.getElementAt(0));
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ExclusionPattern(DiagramManager dm) {
		setTitle("Derivation by Exclusion");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExclusionPattern.class.getResource("/resources/icons/x16/sitemap.png")));

		dman = dm;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(245, 245, 245));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		location.x= this.getLocation().getX();
		location.y= this.getLocation().getY();

		comboBoxItems.add("Kind");
		setBounds(100, 100, 442, 273);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.menu);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Supertype");

		textField = new JTextField();
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("From Type");

		JLabel lblNewLabel_2 = new JLabel("Exclusion Derived Type");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCombo();
			}
		});


		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Category", "Mixin"}));


		comboBox_1.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> values= DerivedByExclusion.getInstance().getPossibleGeneralization(comboBox_1.getModel().getSelectedItem().toString());

				if(values !=null){
					updated=false;
					comboBox.removeAllItems();
					for (String string : values) {
						model.addElement(string);
					}	
					comboBox.setModel(model);
					comboBox.getModel().setSelectedItem(model.getElementAt(0));
					contentPanel.repaint();
					contentPanel.validate();
					updated=true;
				}
				comboBox.setEnabled(true);
				comboBox_2.setEnabled(true);
				setCombo();

			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Kind", "SubKind", "Role", "Phase", "Quantity", "Collection", "Mixin", "Role Mixin", "Category"}));

		final JCheckBox chckbxNewCheckBox = new JCheckBox("Generate OCL Constraint");
		chckbxNewCheckBox.setSelected(true);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(chckbxNewCheckBox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(textField_2, 302, 302, 302)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxNewCheckBox)
					.addGap(26))
		);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Collection", "Quantity", "SubKind", "Category"}));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ArrayList<String> values = new ArrayList<String>();
						if(comboBox.getModel().getSelectedItem()!=null && comboBox_1.getModel().getSelectedItem()!=null && comboBox_2.getModel().getSelectedItem()!=null){

							//pai
							values.add(comboBox.getModel().getSelectedItem().toString());
							//filho
							values.add(comboBox_1.getModel().getSelectedItem().toString());
							//filho
							values.add(comboBox_2.getModel().getSelectedItem().toString());

							values.add(textField.getText());
							values.add(textField_1.getText());
							values.add(textField_2.getText());
							if(chckbxNewCheckBox.isSelected()){
								if(!(comboBox_1.getModel().getSelectedItem().toString().equals("Role") && comboBox.getModel().getSelectedItem().toString().equals("Kind")) )
								{
									if(!((textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("")))){
										String rule="context: "+textField.getText()+"\n"+"inv: not oclIsTypeOf(_'"+textField_1.getText()+"') implies oclIsTypeOf(_'"+textField_2.getText()+"')";
										dman.getFrame().getInfoManager().getOcleditor().addText(rule);
										DerivedTypesOperations.exclusionPattern(dman,values,location);
										dispose();
									}
									else{
										DerivedTypesOperations.wrongSelection("Please set the names for generating the OCL rule");
									}
								}
							}
							else{
								DerivedTypesOperations.exclusionPattern(dman,values,location);
								dispose();
							}
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
