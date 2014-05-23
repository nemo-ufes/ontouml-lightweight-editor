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

public class UnionPattern extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_1 = new JComboBox();
	private JTextField textField;
	private JTextField textField_2;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox = new JComboBox();
	@SuppressWarnings({ "rawtypes", "unused" })
	private JComboBox comboBox1 = new JComboBox();
	private JLabel lblNewLabel = new JLabel("Types");
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
		setTitle("Derivation by Union");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UnionPattern.class.getResource("/resources/icons/x16/sitemap.png")));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Category"}));
		this.diagramMan= diagramManager;
		setBounds(100, 100, 453, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(245, 245, 245));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		location.x= this.getLocation().getX();
		location.y= this.getLocation().getY();
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{

			lblNewLabel.setForeground(Color.BLACK);
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
			textField_1 = new JTextField();
			textField_1.setColumns(10);
		}
		{

			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "SubKind", "Category", "Role", "Phase", "Role Mixin", "Mixin"}));
		}
		{
			lblNewLabel_1 = new JLabel("Derived Type");
			lblNewLabel_1.setForeground(Color.BLACK);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
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
