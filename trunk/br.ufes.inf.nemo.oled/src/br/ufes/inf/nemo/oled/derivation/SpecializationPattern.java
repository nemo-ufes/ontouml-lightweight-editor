package br.ufes.inf.nemo.oled.derivation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

import br.ufes.inf.nemo.oled.DiagramManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;

public class SpecializationPattern extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField lbl_base;
	private JTextField lbl_derived;
	private JTextField lbl_attribute;
	private JTextField lbl_type_att;
	private DiagramManager dm;
	JComboBox cmb_stereo_base = new JComboBox();
	JComboBox cmb_stereo_der = new JComboBox();
	JLabel lblType = new JLabel("Type");
	private Point2D.Double location= new Point2D.Double();
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 * @param diagramManager 
	 */
	public SpecializationPattern(DiagramManager diagramManager) {
		setResizable(false);
		dm= diagramManager;
		setTitle("Derivation By Specialization");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SpecializationPattern.class.getResource("/resources/icons/x16/sitemap.png")));
		setBounds(100, 100, 412, 518);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setForeground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Base Type");
		
		lbl_base = new JTextField();
		lbl_base.setColumns(10);
		
		JLabel lblDerivedBySpecialization = new JLabel("Derived By Specialization");
		
		lbl_derived = new JTextField();
		lbl_derived.setColumns(10);
		

		cmb_stereo_base.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Subkind", "Phase", "Role"}));
		

		cmb_stereo_der.setModel(new DefaultComboBoxModel(new String[] {"Phase", "Subkind"}));
		
		JTextArea txtrATypeIs = new JTextArea();
		txtrATypeIs.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrATypeIs.setLineWrap(true);
		txtrATypeIs.setText("A type is derived by specialization when it specializes a base type for some condition.");
		
		JTextPane txtpnUseOclEditor = new JTextPane();
		txtpnUseOclEditor.setEditable(false);
		txtpnUseOclEditor.setText("Use the OCL editor to describe the condition.");
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(SpecializationPattern.class.getResource("/resources/figures/derivation_by_specialization.jpg")));
		
		JLabel lblAttribute = new JLabel("Attribute");
		
		lbl_attribute = new JTextField();
		lbl_attribute.setColumns(10);
		
		lbl_type_att = new JTextField();
		lbl_type_att.setColumns(10);
		

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtrATypeIs, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDerivedBySpecialization)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lbl_base, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cmb_stereo_base, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lbl_attribute, Alignment.LEADING)
										.addComponent(lblAttribute, Alignment.LEADING)
										.addComponent(lbl_derived, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblType)
										.addComponent(cmb_stereo_der, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_type_att, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(111)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(75)
							.addComponent(txtpnUseOclEditor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrATypeIs, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
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
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(txtpnUseOclEditor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
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
						DerivedTypesOperations.createDerivedTypeBySpecialization(lbl_base.getText(),lbl_derived.getText(),cmb_stereo_base.getSelectedItem().toString(),cmb_stereo_der.getSelectedItem().toString(), lbl_attribute.getText(), lblType.getText(),dm,location);
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

	public void setPosition(double x, double y) {
		// TODO Auto-generated method stub
		location.x=x;
		location.y=y;
	}
}
