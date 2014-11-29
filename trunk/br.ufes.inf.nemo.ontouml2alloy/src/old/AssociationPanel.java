package old;

import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.AbstractListModel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class AssociationPanel extends JPanel {
	private JComboBox textField;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public AssociationPanel() {
		
		JLabel lblClass = new JLabel("Constraint Type:");
		
		JLabel lblMinimum = new JLabel("Mode:");
		
		JLabel lblMaximum = new JLabel("k =");
		lblMaximum.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblConstraintType = new JLabel("Context:");
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"every world", "no world", "at least <n> worlds", "at most <n> worlds", "exactly <n> worlds"}));
		
		JSpinner spinner = new JSpinner();
		
		JComboBox spinner_1 = new JComboBox();
		spinner_1.setModel(new DefaultComboBoxModel(new String[] {"every instance", "no instance", "at least <k> instances", "at most <k> instances", "exactly <k> instances"}));
		
		JLabel lblN = new JLabel("n =");
		lblN.setHorizontalAlignment(SwingConstants.LEFT);
		
		JSpinner spinner_2 = new JSpinner();
		
		JLabel lblNewLabel = new JLabel("In this tab you can define constraints regarding associations:");
		
		JButton button = new JButton("");
		button.setRolloverIcon(new ImageIcon(PopulationPanel.class.getResource("/resources/icons/x16/help-rollover.png")));
		button.setIcon(new ImageIcon(PopulationPanel.class.getResource("/resources/icons/x16/help.png")));
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(null);
		
		JLabel lblNewLabel_1 = new JLabel("Source:");
		
		JTextField comboBox_1 = new JTextField();
		comboBox_1.setEditable(false);
		
		JButton btnNewButton = new JButton("Save");
		
		JButton btnDel = new JButton("Del");
		
		JButton btnNew = new JButton("New");
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_2 = new JLabel("Association Scenario List:");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Association Cardinality", "Association Changeability", "Association Depth"}));
		
		JLabel lblStereotype = new JLabel("Association:");
		
		textField = new JComboBox();
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		
		JLabel lblTarget = new JLabel("Target:");
		
		JLabel lblDirection = new JLabel("Direction:");
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Source -> Target", "Target -> Source"}));
		
		JLabel lblChangeability = new JLabel("Changeable?");
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"yes", "no"}));
		
		JLabel lblMultiplicity = new JLabel("Multiplicity:");
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"at least <x>", "at most <x>", "exactly <x>"}));
		
		JLabel lblX = new JLabel("x =");
		lblX.setHorizontalAlignment(SwingConstants.LEFT);
		
		JSpinner spinner_3 = new JSpinner();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, 660, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
									.addComponent(btnNew)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton))
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblStereotype, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblClass, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblMinimum, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblConstraintType, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(spinner_1, Alignment.LEADING, 0, 289, Short.MAX_VALUE)
												.addComponent(comboBox_2, 0, 289, Short.MAX_VALUE)
												.addComponent(comboBox, Alignment.LEADING, 0, 289, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblDirection, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(comboBox_3, 0, 155, Short.MAX_VALUE))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(1)
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblMaximum, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblN, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(spinner_2, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
														.addComponent(spinner, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))))
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
										.addComponent(textField, 0, 536, Short.MAX_VALUE)
										.addComponent(comboBox_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTarget, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
							.addGap(609))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblChangeability, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_4, 0, 289, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblMultiplicity, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_5, 0, 289, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblX, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_3, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
							.addGap(16))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblClass)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDirection))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStereotype)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTarget)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChangeability)
						.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMultiplicity)
						.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblX)
						.addComponent(spinner_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMinimum)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaximum))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConstraintType)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblN))
					.addGap(16)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton)
							.addComponent(btnDel)
							.addComponent(btnNew))
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {lblNewLabel, button});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblClass, lblMinimum, lblConstraintType, lblNewLabel_1});
		
		JList<?> list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		setLayout(groupLayout);

	}
}
