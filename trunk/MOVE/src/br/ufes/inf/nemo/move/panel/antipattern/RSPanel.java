package br.ufes.inf.nemo.move.panel.antipattern;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class RSPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	public RSPanel() {
		
		JLabel lblSourceType = new JLabel("General");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel lblSepecificSourceType = new JLabel("Association ");
		lblSepecificSourceType.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel lblSpecificAssociation = new JLabel("Target");
		lblSpecificAssociation.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		JCheckBox chckbxSubtype = new JCheckBox("Subsetting\r\n");
		
		JCheckBox chckbxRedefinition = new JCheckBox("Redefinition");
		
		JCheckBox chckbxNonsubsetting = new JCheckBox("Non-Subsetting");
		
		JCheckBox chckbxDisjoint = new JCheckBox("Disjointness");
		
		JButton btnGenerateAlloy = new JButton("Generate Alloy");
		
		JButton btnGenerateOclSolution = new JButton("Generate OCL Solution");
		
		JSeparator separator = new JSeparator();
		
		JLabel lblSourceType_1 = new JLabel("Source ");
		lblSourceType_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSpecific = new JLabel("Specific");
		lblSpecific.setHorizontalAlignment(SwingConstants.LEFT);
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel label = new JLabel("Source ");
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		JLabel label_1 = new JLabel("Association ");
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		
		JLabel label_2 = new JLabel("Target");
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSourceType, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnGenerateAlloy)
								.addGap(18)
								.addComponent(btnGenerateOclSolution))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(chckbxNonsubsetting)
									.addComponent(chckbxSubtype)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
									.addComponent(chckbxRedefinition)
									.addComponent(chckbxDisjoint)))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblSpecificAssociation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblSepecificSourceType, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblSourceType_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textField_1)
									.addComponent(textField_2)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))
							.addComponent(lblSpecific, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(109, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSourceType)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSourceType_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSepecificSourceType)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSpecificAssociation)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addComponent(lblSpecific)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxSubtype)
						.addComponent(chckbxRedefinition))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxNonsubsetting)
						.addComponent(chckbxDisjoint))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGenerateAlloy)
						.addComponent(btnGenerateOclSolution))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
