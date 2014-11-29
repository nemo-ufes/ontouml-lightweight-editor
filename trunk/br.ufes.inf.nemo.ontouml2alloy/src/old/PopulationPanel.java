package old;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.SystemColor;

public class PopulationPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PopulationPanel() {
		
		JTextPane lblNewLabel_2 = new JTextPane();
		lblNewLabel_2.setBackground(SystemColor.control);
		lblNewLabel_2.setText("In this tab you can specify constraints regarding the population of the worlds in the story:");
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JButton button_1 = new JButton("");
		button_1.setMargin(new Insets(0, 0, 0, 0));
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setBorder(null);
		button_1.setRolloverIcon(new ImageIcon(PopulationPanel.class.getResource("/resources/icons/x16/help-rollover.png")));
		button_1.setIcon(new ImageIcon(PopulationPanel.class.getResource("/resources/icons/x16/help.png")));

		JLabel lblPopulationSizeper = new JLabel("Population size (per world):");
		
		JCheckBox checkBox = new JCheckBox("Population");
		
		JCheckBox checkBox_1 = new JCheckBox("Object Population");
		
		JCheckBox checkBox_2 = new JCheckBox("Moment Population");
		
		JLabel label_5 = new JLabel("Min:");
		
		JLabel label_6 = new JLabel("Min:");
		
		JLabel label_7 = new JLabel("Min:");
		
		JSpinner spinner_6 = new JSpinner();
		
		JSpinner spinner_7 = new JSpinner();
		
		JSpinner spinner_8 = new JSpinner();
		
		JLabel label_8 = new JLabel("Max:");
		
		JLabel label_9 = new JLabel("Max:");
		
		JLabel label_10 = new JLabel("Max:");
		
		JSpinner spinner_9 = new JSpinner();
		
		JSpinner spinner_10 = new JSpinner();
		
		JSpinner spinner_11 = new JSpinner();
		
		JLabel label_11 = new JLabel("[required >0]");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(button_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(checkBox_2, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_6, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_11, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addGap(113))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(checkBox_1, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_6)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_7, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_10, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addGap(113))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(checkBox, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_7)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_8, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_9, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_11)
							.addGap(1))
						.addComponent(lblPopulationSizeper, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1)
						.addComponent(lblPopulationSizeper))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox)
						.addComponent(label_7)
						.addComponent(spinner_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_8)
						.addComponent(spinner_9, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_11))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_1)
						.addComponent(label_6)
						.addComponent(spinner_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_9)
						.addComponent(spinner_10, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_2)
						.addComponent(label_5)
						.addComponent(spinner_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_10)
						.addComponent(spinner_11, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.linkSize(SwingConstants.VERTICAL, new Component[] {button_1, lblPopulationSizeper});
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
					.addGap(22))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(472))
		);
		
		JButton button_2 = new JButton("");
		button_2.setMargin(new Insets(0, 0, 0, 0));
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		button_2.setBorder(null);
		button_2.setRolloverIcon(new ImageIcon(PopulationPanel.class.getResource("/resources/icons/x16/help-rollover.png")));
		button_2.setIcon(new ImageIcon(PopulationPanel.class.getResource("/resources/icons/x16/help.png")));
		
		JLabel lblPopulationGrowth = new JLabel("Population growth:");
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Undefined", "Incremental", "Decremental"}));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(button_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_3, 0, 401, Short.MAX_VALUE)
						.addComponent(lblPopulationGrowth))
					.addGap(124))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(button_2)
						.addComponent(lblPopulationGrowth))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_3.linkSize(SwingConstants.VERTICAL, new Component[] {button_2, lblPopulationGrowth});
		panel_3.setLayout(gl_panel_3);
		
		JButton helpButton = new JButton("");
		helpButton.setRolloverIcon(new ImageIcon(PopulationPanel.class.getResource("/resources/icons/x16/help-rollover.png")));
		helpButton.setIcon(new ImageIcon(PopulationPanel.class.getResource("/resources/icons/x16/help.png")));
		
		helpButton.setMargin(new Insets(0, 0, 0, 0));
		helpButton.setContentAreaFilled(false);
		helpButton.setBorderPainted(false);
		helpButton.setBorder(null);
		
		JLabel lblPopulationVariability = new JLabel("Population variability:");
		
		JCheckBox chckbxPopulation = new JCheckBox("Population");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Variable", "Constant"}));
		
		JCheckBox chckbxObjectPopulation = new JCheckBox("Object Population");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Variable", "Constant"}));
		
		JCheckBox chckbxMomentpopulation = new JCheckBox("Moment Population");
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Variable", "Constant"}));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addComponent(helpButton)
					.addGap(5)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPopulationVariability)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxPopulation)
								.addComponent(chckbxObjectPopulation)
								.addComponent(chckbxMomentpopulation))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, 0, 222, Short.MAX_VALUE)
								.addComponent(comboBox_1, 0, 222, Short.MAX_VALUE)
								.addComponent(comboBox_2, 0, 0, Short.MAX_VALUE))))
					.addGap(124))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(11)
							.addComponent(helpButton))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(9)
							.addComponent(lblPopulationVariability)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxPopulation)
								.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxObjectPopulation)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxMomentpopulation)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37))
		);
		gl_panel_2.linkSize(SwingConstants.HORIZONTAL, new Component[] {chckbxPopulation, chckbxObjectPopulation, chckbxMomentpopulation});
		panel_2.setLayout(gl_panel_2);
		
		JButton button = new JButton("");
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setRolloverIcon(new ImageIcon(PopulationPanel.class.getResource("/resources/icons/x16/help-rollover.png")));
		button.setIcon(new ImageIcon(PopulationPanel.class.getResource("/resources/icons/x16/help.png")));
		
		JLabel lblNewLabel = new JLabel("Population size (story):");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Population");
		
		JCheckBox chckbxSetMinimumIn = new JCheckBox("Object Population");
		
		JCheckBox chckbxSetMaximumPer = new JCheckBox("Moment Population");
		
		JLabel label_1 = new JLabel("Min:");
		
		JLabel label = new JLabel("Min:");
		
		JLabel lblNewLabel_3 = new JLabel("Min:");
		
		JSpinner spinner = new JSpinner();
		
		JSpinner spinner_1 = new JSpinner();
		
		JSpinner spinner_3 = new JSpinner();
		
		JLabel lblMax = new JLabel("Max:");
		
		JLabel label_2 = new JLabel("Max:");
		
		JLabel label_3 = new JLabel("Max:");
		
		JSpinner spinner_2 = new JSpinner();
		
		JSpinner spinner_4 = new JSpinner();
		
		JSpinner spinner_5 = new JSpinner();
		
		JLabel lblNewLabel_1 = new JLabel("[required >0]");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(chckbxSetMaximumPer, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_5, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addGap(112))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(chckbxSetMinimumIn, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_1, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_4, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addGap(112))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_3, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblMax, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_2, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1))
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
					.addGap(16))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(button)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxNewCheckBox)
						.addComponent(lblNewLabel_3)
						.addComponent(spinner_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMax)
						.addComponent(spinner_2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxSetMinimumIn)
						.addComponent(label)
						.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(spinner_4, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxSetMaximumPer)
						.addComponent(label_1)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(spinner_5, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.linkSize(SwingConstants.VERTICAL, new Component[] {button, lblNewLabel});
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}

}
