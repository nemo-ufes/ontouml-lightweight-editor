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
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

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
	private JLabel lblName;
	private JLabel lblStereotype;
	private JLabel lblNewLabel_1;
	private Point2D.Double location= new Point2D.Double();
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
	
	public UnionPattern(DiagramManager diagramManager) {
		this.diagramMan= diagramManager;
		setBounds(100, 100, 450, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		location.x= this.getLocation().getX();
		location.y= this.getLocation().getY();
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			lblName = new JLabel("Name");
		}
		{
			lblStereotype = new JLabel("Stereotype");
		}
		{

			lblNewLabel.setForeground(SystemColor.textInactiveText);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
		}
		{

			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "SubKind", "Category", "Role", "Phase", "Role Mixin", "Mixin"}));
		}
		{
			
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
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(89)
					.addComponent(lblName)
					.addGap(203)
					.addComponent(lblStereotype))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNewLabel)
					.addGap(4)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSecondSubtype)
						.addComponent(lblNewLabel_1))
					.addGap(4)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
					.addGap(7))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(lblStereotype))
					.addGap(4)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblSecondSubtype))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(26))
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
						values.add((String) (comboBox_1.getModel().getSelectedItem()));
						values.add((String) (comboBox.getModel().getSelectedItem()));
						values.add((String) (textField.getText()));
						values.add((String) (textField_1.getText()));
						values.add((String) (textField_2.getText()));
						dispose();
						DerivedTypesOperations.UnionPattern(diagramMan, values,location);
						
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
