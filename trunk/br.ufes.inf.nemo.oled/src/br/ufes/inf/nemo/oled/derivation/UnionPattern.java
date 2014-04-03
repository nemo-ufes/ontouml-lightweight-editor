package br.ufes.inf.nemo.oled.derivation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

import br.ufes.inf.nemo.derivedtypes.DerivedByUnion;
import br.ufes.inf.nemo.oled.DiagramManager;
import apple.laf.JRSUIUtils.ComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

public class UnionPattern extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	JComboBox comboBox_1 = new JComboBox();
	private JTextField textField;
	private JTextField textField_2;
	private JComboBox comboBox = new JComboBox();
	private JComboBox comboBox1 = new JComboBox();
	private JLabel lblSecondSubtype = new JLabel("Second Subtype");
	private JLabel lblNewLabel = new JLabel("First Subtype");
	private DiagramManager diagramMan;
	private JLabel lblNewLabel_1;
	private Point2D.Double location= new Point2D.Double();
	private final JComboBox comboBox_2 = new JComboBox();
	Vector comboBoxItems=new Vector();
	Vector comboBoxItems2=new Vector();
	final DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public void setPosition(java.lang.Double x, java.lang.Double y){
		location.x= this.getLocation().getX();
		location.y= this.getLocation().getY();
	}
	
	
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
	public UnionPattern(DiagramManager diagramManager) {
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Category"}));
		this.diagramMan= diagramManager;
		setBounds(100, 100, 453, 239);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(245, 245, 245));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		location.x= this.getLocation().getX();
		location.y= this.getLocation().getY();
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{

			lblNewLabel.setForeground(SystemColor.textInactiveText);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
		}
		{
			comboBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setCombo();
				}
			});

			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "SubKind", "Category", "Role", "Phase", "Role Mixin", "Mixin"}));
		}
		{
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setCombo();
				}
			});
			
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "SubKind", "Category", "Role", "Phase", "Role Mixin", "Mixin"}));
		}
		{
			
			lblSecondSubtype.setForeground(SystemColor.windowBorder);
			lblSecondSubtype.setBackground(SystemColor.activeCaptionBorder);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
		}
		{

			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "SubKind", "Category", "Role", "Phase", "Role Mixin", "Mixin"}));
		}
		{
			lblNewLabel_1 = new JLabel("Derived Type");
			lblNewLabel_1.setForeground(SystemColor.windowBorder);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
		}
		
		JLabel lblDerivationByUnion = new JLabel("Derivation By Union");
		lblDerivationByUnion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(141)
							.addComponent(lblDerivationByUnion))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(21)
							.addComponent(lblNewLabel_1)
							.addGap(4)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(21)
							.addComponent(lblNewLabel)
							.addGap(4)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblSecondSubtype)
							.addGap(1)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(8, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblDerivationByUnion)
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(22)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblSecondSubtype))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
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

							values.add(textField_2.getText());
							values.add(textField_1.getText());
							values.add(textField.getText());
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
