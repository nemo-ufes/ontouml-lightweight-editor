package br.ufes.inf.nemo.move.panel.antipattern;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class ACPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JTextPane txtCycle;
	
	public JCheckBox chckbxOpenCycle;
	
	public JCheckBox chckbxClosedCycle;
	
	public JButton btnGenerateAlloy;
	
	public JButton btnGenerateOclSolution;
	
	/**
	 * Create the panel.
	 */
	public ACPanel() {
		
		JLabel lblClass = new JLabel("Cycle\r\n");
		lblClass.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtCycle = new JTextPane();
		txtCycle.setEditable(false);
		
		chckbxOpenCycle = new JCheckBox("Open Cycle");
		
		chckbxClosedCycle = new JCheckBox("Closed Cycle");
		
		btnGenerateAlloy = new JButton("Generate Alloy");
		
		btnGenerateOclSolution = new JButton("Generate OCL Solution");
		
		GroupLayout groupLayout = new GroupLayout(this);
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnGenerateAlloy)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnGenerateOclSolution)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblClass, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
							.addComponent(txtCycle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(chckbxOpenCycle)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(chckbxClosedCycle))))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClass)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCycle, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxClosedCycle)
						.addComponent(chckbxOpenCycle))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGenerateAlloy)
						.addComponent(btnGenerateOclSolution))
					.addGap(20))
		);
		setLayout(groupLayout);

	}
}
