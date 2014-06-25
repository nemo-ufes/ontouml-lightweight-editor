package br.ufes.inf.nemo.oled.derivation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class PastSpecializationPattern extends JDialog {

	private static final long serialVersionUID = 4236441153290954092L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_supertype;
	private JTextArea txtrADerivedType;
	private JTextField txt_special;
	private JTextField txt_past;
	@SuppressWarnings("rawtypes")
	JComboBox cmb_past = new JComboBox();
	@SuppressWarnings("rawtypes")
	JComboBox cmb_special = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PastSpecializationPattern dialog = new PastSpecializationPattern();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PastSpecializationPattern() {
		setTitle("Derivation By Past Specialzation");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PastSpecializationPattern.class.getResource("/resources/icons/x16/sitemap.png")));
		setResizable(false);
		setBounds(100, 100, 426, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			txtrADerivedType = new JTextArea();
			txtrADerivedType.setEditable(false);
			txtrADerivedType.setText("All individuals of the type derived by past specialization \r\ninstantiated a specific type in the past but not currently.");
			txtrADerivedType.setRows(3);
			txtrADerivedType.setColumns(3);
			txtrADerivedType.setWrapStyleWord(true);
			txtrADerivedType.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		{
			txt_supertype = new JTextField();
			txt_supertype.setColumns(10);
		}
		
		JLabel lblNewLabel = new JLabel("Supertype");
		
		JLabel lblNewLabel_1 = new JLabel("Specialization");
		
		txt_special = new JTextField();
		txt_special.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Derived By Past Specialization");
		
		txt_past = new JTextField();
		txt_past.setColumns(10);
		
		JComboBox cmb_super = new JComboBox();
		cmb_super.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Quantity", "Collective", "Subkind", "Role", "Phase"}));
		
		
		cmb_special.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmb_special.getSelectedItem().toString().equals("Role")){
					cmb_past.setSelectedIndex(1);
				}else{
					cmb_past.setSelectedIndex(0);
				}
			}
		});
		cmb_special.setModel(new DefaultComboBoxModel(new String[] {"Phase", "Role"}));
		
		
		cmb_past.setModel(new DefaultComboBoxModel(new String[] {"Phase", "Role"}));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(PastSpecializationPattern.class.getResource("/resources/figures/derivation_by_past.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txt_past, Alignment.LEADING)
										.addComponent(txt_special, Alignment.LEADING)
										.addComponent(txt_supertype, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
										.addComponent(lblNewLabel_3))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(cmb_past, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cmb_special, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cmb_super, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(32)
							.addComponent(txtrADerivedType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(txtrADerivedType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_supertype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_super, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_special, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_special, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_past, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_past, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel_3))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
