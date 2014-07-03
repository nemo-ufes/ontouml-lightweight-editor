package br.ufes.inf.nemo.oled.derivation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

import br.ufes.inf.nemo.oled.DiagramManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BidirectionalParticipationPattern extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField lbl_base_left;
	private JTextField lbl_base_right;
	private JTextField lbl_relator;
	private JTextField lbl_derived_left;
	private JTextField lbl_derived_right;
	Point2D.Double location;
	DiagramManager dm;
	JComboBox cmb_base_left = new JComboBox();
	JComboBox cmb_base_right = new JComboBox();
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 * @param dman 
	 */
	public BidirectionalParticipationPattern(DiagramManager dman) {
		dm=dman;
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setTitle("Derivation by Participation Bidirectional");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BidirectionalParticipationPattern.class.getResource("/resources/icons/x16/sitemap.png")));
		setBounds(100, 100, 468, 510);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		JLabel lblNewLabel = new JLabel("Base Left");
		JLabel lblNewLabel_1 = new JLabel("Base Right");
		JLabel lblNewLabel_2 = new JLabel("Relator");
		JLabel lblNewLabel_3 = new JLabel("Derived Left");
		JLabel lblNewLabel_4 = new JLabel("Derived Right");
		
		lbl_base_left = new JTextField();
		lbl_base_left.setText("Base_1");
		lbl_base_left.setColumns(10);
		
		lbl_base_right = new JTextField();
		lbl_base_right.setText("Base_2");
		lbl_base_right.setColumns(10);
		
		lbl_relator = new JTextField();
		lbl_relator.setText("Relator");
		lbl_relator.setColumns(10);
		
		lbl_derived_left = new JTextField();
		lbl_derived_left.setText("Derived_1");
		lbl_derived_left.setColumns(10);
		
		lbl_derived_right = new JTextField();
		lbl_derived_right.setText("Derived_2");
		lbl_derived_right.setColumns(10);
		
		
		cmb_base_left.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Collective", "Quantity", "Subkind", "Role", "Phase", "Category", "RoleMixin", "Mixin"}));
		
		JComboBox comboBox_1 = new JComboBox();
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(BidirectionalParticipationPattern.class.getResource("/resources/figures/derivationbyparticipation_bi.PNG")));
		
		
		cmb_base_right.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Collective", "Quantity", "Subkind", "Role", "Phase", "Category", "RoleMixin", "Mixin"}));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_1)
								.addComponent(lbl_base_left, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(128))
								.addComponent(lbl_base_right, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(lbl_relator, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(lbl_derived_right, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(lblNewLabel_4)
								.addComponent(lbl_derived_left, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(lblNewLabel_3))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cmb_base_left, Alignment.TRAILING, 0, 82, Short.MAX_VALUE)
								.addComponent(cmb_base_right, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(189)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(64)
							.addComponent(lblNewLabel_5)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(4)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_base_left, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_base_left, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_base_right, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_base_right, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl_relator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl_derived_left, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4)
					.addGap(8)
					.addComponent(lbl_derived_right, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_5)
					.addGap(6))
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
						DerivedTypesOperations.createParticipationBi(dm, lbl_base_left.getText(), lbl_base_right.getText(),lbl_relator.getText(),lbl_derived_left.getText(), lbl_derived_right.getText(), cmb_base_left.getSelectedItem().toString(), cmb_base_right.getSelectedItem().toString(),location);
						dispose();
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

	public void setPosition(Point2D.Double point) {
		// TODO Auto-generated method stub
		location= point;
	}
}
