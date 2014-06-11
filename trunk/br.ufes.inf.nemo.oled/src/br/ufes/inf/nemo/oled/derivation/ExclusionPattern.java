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
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.Font;

public class ExclusionPattern extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtSupertype;
	private JTextField txtBase;
	private JTextField txtDerived;
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
		location.x= x;
		location.y= y;
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
		setBounds(100, 100, 442, 512);
		getContentPane().setLayout(new BorderLayout());
		//contentPanel.setBackground(SystemColor.menu);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Supertype");

		txtSupertype = new JTextField();
		txtSupertype.setText("Supertype");
		txtSupertype.setColumns(10);

		txtBase = new JTextField();
		txtBase.setText("Base");
		txtBase.setColumns(10);

		txtDerived = new JTextField();
		txtDerived.setText("Derived");
		txtDerived.setColumns(10);

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
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setSelected(true);
		
		JTextPane txtpnATypeIs = new JTextPane();
		txtpnATypeIs.setEditable(false);
		txtpnATypeIs.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnATypeIs.setText("A type is derived by exclusion when it is just the complement of other type.");
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(ExclusionPattern.class.getResource("/resources/figures/derivationbyexclusion.PNG")));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Collection", "Quantity", "SubKind", "Category"}));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(12, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtpnATypeIs, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(txtDerived, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(txtBase, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
									.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(txtSupertype, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))))
							.addGap(20))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(11)
					.addComponent(txtpnATypeIs, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lblNewLabel_2)
					.addGap(6)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtDerived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(lblNewLabel_1)
					.addGap(6)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtBase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addComponent(lblNewLabel)
					.addGap(6)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtSupertype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addComponent(chckbxNewCheckBox)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
						ArrayList<String> values = new ArrayList<String>();
						if(comboBox.getModel().getSelectedItem()!=null && comboBox_1.getModel().getSelectedItem()!=null && comboBox_2.getModel().getSelectedItem()!=null){

							//pai
							values.add(comboBox.getModel().getSelectedItem().toString());
							//filho
							values.add(comboBox_1.getModel().getSelectedItem().toString());
							//filho
							values.add(comboBox_2.getModel().getSelectedItem().toString());

							values.add(txtSupertype.getText());
							values.add(txtBase.getText());
							values.add(txtDerived.getText());
							if(chckbxNewCheckBox.isSelected()){
								if(!(comboBox_1.getModel().getSelectedItem().toString().equals("Role") && comboBox.getModel().getSelectedItem().toString().equals("Kind")) )
								{
									if(!((txtSupertype.getText().equals("") || txtBase.getText().equals("") || txtDerived.getText().equals("")))){
										String rule="\n context: _'"+txtSupertype.getText()+"'\n"+"inv: not oclIsTypeOf(_'"+txtBase.getText()+"') implies oclIsTypeOf(_'"+txtDerived.getText()+"')";
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
