package br.ufes.inf.nemo.move.panel.antipattern;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class RBOSPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JTextField textSuperType;
	
	public JTextField textAssociation;
	
	public JTextField textSource;
		
	public JTextField textTarget;
	
	public JCheckBox chckbxRflexive;
	
	public JCheckBox chckbxIrreflexive;
	
	public JButton btnGenerateAlloy;
	
	public JButton btnGenerationOclSolution;

	/**
	 * Create the panel.
	 */
	public RBOSPanel() {
		
		JLabel lblSupertype = new JLabel("SuperType");
		
		textSuperType = new JTextField();
		textSuperType.setEditable(false);
		textSuperType.setColumns(10);
		
		JLabel lblAssociation = new JLabel("Association");
		
		textAssociation = new JTextField();
		textAssociation.setEditable(false);
		textAssociation.setColumns(10);
		
		JLabel lblSubtype = new JLabel("Source");
		
		textSource = new JTextField();
		textSource.setEditable(false);
		textSource.setColumns(10);
		
		JLabel lblTarget = new JLabel("Target");
		
		textTarget = new JTextField();
		textTarget.setEditable(false);
		textTarget.setColumns(10);
		
		chckbxRflexive = new JCheckBox("Reflexive");
		
		chckbxIrreflexive = new JCheckBox("Irreflexive");
		
		btnGenerateAlloy = new JButton("Generate Alloy");
		
		btnGenerationOclSolution = new JButton("Generation OCL Solution");
		
		GroupLayout groupLayout = new GroupLayout(this);
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(39)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAssociation)
								.addComponent(lblSupertype)
								.addComponent(lblSubtype)
								.addComponent(lblTarget))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textTarget)
								.addComponent(textSource)
								.addComponent(textAssociation)
								.addComponent(textSuperType, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnGenerateAlloy)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnGenerationOclSolution)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(chckbxRflexive)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(chckbxIrreflexive)))))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupertype)
						.addComponent(textSuperType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAssociation)
						.addComponent(textAssociation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubtype)
						.addComponent(textSource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTarget)
						.addComponent(textTarget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxIrreflexive)
						.addComponent(chckbxRflexive))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGenerationOclSolution)
						.addComponent(btnGenerateAlloy))
					.addContainerGap(145, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
