package br.ufes.inf.nemo.oled.derivation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;

import javax.swing.ImageIcon;

import br.ufes.inf.nemo.derivedtypes.DerivedByIntersection;
import br.ufes.inf.nemo.oled.DiagramManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class IntersectionPattern extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBase;
	private JTextField txtBase_1;
	private JTextField txtDerived;
	JComboBox combo_base_1 = new JComboBox();
	JComboBox combo_base_2 = new JComboBox();
	JComboBox combo_derived = new JComboBox();
	Vector comboBoxItems2=new Vector();
	Vector comboBoxItemsDer=new Vector();
	private Point2D.Double location= new Point2D.Double();
	static DiagramManager dm;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public IntersectionPattern(final DiagramManager dm) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IntersectionPattern.class.getResource("/resources/icons/x16/sitemap.png")));
		setTitle("Derivation by Intersection");
		setResizable(false);
	
		//this.setLocation(Integer., y);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Base Types");
		
		JLabel lblNewLabel_2 = new JLabel("Derived Type");
		
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
			public void actionPerformed(ActionEvent e) {
				
				if(combo_base_1.getSelectedItem().toString().equals("Kind")){
					
					comboBoxItems2.removeAllElements();
					
					DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems2);	
					model.addElement("Role Mixin");
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
					}
					model.addElement("Role");
					model.addElement("Phase");
					model.addElement("Role Mixin");
					model.addElement("Mixin");
					model.addElement("Category");
					combo_base_2.setModel(model);
					combo_base_2.getModel().setSelectedItem(model.getElementAt(0));
				}
				setDerivedStereotype();
			}

			
		});
		
		
		combo_base_1.setModel(new DefaultComboBoxModel(new String[] {"Subkind", "Kind", "Role", "Phase", "Category", "Mixin", "Role Mixin"}));
		
		
		combo_base_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDerivedStereotype();
			}
		});
		combo_base_2.setModel(new DefaultComboBoxModel(new String[] {"Subkind", "Role", "Phase", "Category", "Mixin", "Role Mixin"}));
		
		
		combo_derived.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDerivedStereotype();
			}
		});
		
		combo_derived.setModel(new DefaultComboBoxModel(new String[] {"Subkind"}));
		
		JTextPane txtpnTheDerivedType = new JTextPane();
		txtpnTheDerivedType.setEditable(false);
		txtpnTheDerivedType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnTheDerivedType.setText("The population of a derived type by intersection is the overlap of the base types");
		
		JLabel lblNewLabel_3 = new JLabel("");
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(IntersectionPattern.class.getResource("/resources/figures/derivation_by_intersection.png")));
		
		JLabel lblNewLabel_1 = new JLabel("");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtpnTheDerivedType, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNewLabel_2)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(txtDerived, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(combo_derived, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addGap(10))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtBase_1)
												.addComponent(txtBase, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(combo_base_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(combo_base_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(104)
							.addComponent(label)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(txtpnTheDerivedType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(143)
							.addComponent(lblNewLabel_3)
							.addGap(18)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtBase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(combo_base_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtBase_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(combo_base_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addGap(28))
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
									.addComponent(combo_derived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtDerived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addComponent(label)
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
