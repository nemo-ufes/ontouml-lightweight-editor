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

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

import br.ufes.inf.nemo.oled.DiagramManager;
import apple.laf.JRSUIUtils.ComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public UnionPattern(DiagramManager diagramManager) {
		this.diagramMan= diagramManager;
		setBounds(100, 100, 450, 262);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new MigLayout("", "[66px][200px:n,grow][grow]", "[14px][][][100px]"));
		{
			JLabel lblName = new JLabel("Name");
			contentPanel.add(lblName, "cell 1 0");
		}
		{
			JLabel lblStereotype = new JLabel("Stereotype");
			contentPanel.add(lblStereotype, "cell 2 0");
		}
		{

			lblNewLabel.setForeground(SystemColor.textInactiveText);
			contentPanel.add(lblNewLabel, "cell 0 1,alignx trailing,aligny top");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "cell 1 1,growx");
			textField.setColumns(10);
		}
		{

			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "SubKind", "Category", "Role", "Phase", "Role Mixin", "Mixin"}));
			contentPanel.add(comboBox_1, "cell 2 1,growx");
		}
		{
			
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "SubKind", "Category", "Role", "Phase", "Role Mixin", "Mixin"}));
			contentPanel.add(comboBox, "cell 2 1,growx");
		}
		{
			
			lblSecondSubtype.setForeground(SystemColor.windowBorder);
			lblSecondSubtype.setBackground(SystemColor.activeCaptionBorder);
			contentPanel.add(lblSecondSubtype, "cell 0 2,alignx trailing");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "cell 1 2,growx");
			textField_1.setColumns(10);
		}
		{

			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "SubKind", "Category", "Role", "Phase", "Role Mixin", "Mixin"}));
			contentPanel.add(comboBox, "cell 2 2,growx");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Derived Type");
			lblNewLabel_1.setForeground(SystemColor.windowBorder);
			contentPanel.add(lblNewLabel_1, "cell 0 3,alignx trailing");
		}
		{
			textField_2 = new JTextField();
			contentPanel.add(textField_2, "cell 1 3,growx");
			textField_2.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ArrayList<String>values= new ArrayList<String>();
						values.add((String) (comboBox_1.getModel().getSelectedItem()));
						values.add((String) (comboBox.getModel().getSelectedItem()));
						values.add((String) (textField.getText()));
						values.add((String) (textField_1.getText()));
						values.add((String) (textField_2.getText()));
						dispose();
						DerivedTypesOperations.UnionPattern(diagramMan, values);
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
