package br.ufes.inf.nemo.move.panel.antipattern;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class STRPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtClass;
	private JTextField txtAssociation;

	/**
	 * Create the panel.
	 */
	public STRPanel() {
		
		JLabel lblStrSelftype = new JLabel("STR : Self-Type Relationship");
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStrSelftype))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addComponent(lblStrSelftype)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(124, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("Type");
		
		txtClass = new JTextField();
		txtClass.setEditable(false);
		txtClass.setText("Class1");
		txtClass.setColumns(10);
		
		JLabel lblAssociation = new JLabel("Association");
		
		txtAssociation = new JTextField();
		txtAssociation.setEditable(false);
		txtAssociation.setText("Association1");
		txtAssociation.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JCheckBox chckbxSymmetryc = new JCheckBox("Symmetric ");
		
		JCheckBox chckbxAntisymmetric = new JCheckBox("AntiSymmetric");
		
		JCheckBox chckbxReflexive = new JCheckBox("Reflexive");
		
		JCheckBox chckbxAntireflexive = new JCheckBox("AntiReflexive");
		
		JCheckBox chckbxTransitive = new JCheckBox("Transitive");
		
		JCheckBox chckbxNontransitive = new JCheckBox("Non-Transitive");
		
		JButton btnExecuteWithAnalyzer = new JButton("Generate Alloy");
		
		JButton btnGenerateOclSolution = new JButton("Generate OCL Solution");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(40)
									.addComponent(txtClass, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblAssociation)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtAssociation, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(chckbxSymmetryc)
										.addComponent(chckbxAntisymmetric)
										.addComponent(btnExecuteWithAnalyzer, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(chckbxReflexive)
												.addComponent(chckbxAntireflexive))
											.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(chckbxTransitive, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(chckbxNontransitive, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(btnGenerateOclSolution)
											.addPreferredGap(ComponentPlacement.RELATED)))))
							.addGap(8))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
							.addGap(8))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAssociation)
						.addComponent(txtAssociation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxSymmetryc)
						.addComponent(chckbxReflexive)
						.addComponent(chckbxTransitive))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxAntisymmetric)
						.addComponent(chckbxAntireflexive)
						.addComponent(chckbxNontransitive))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGenerateOclSolution)
						.addComponent(btnExecuteWithAnalyzer))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
}
