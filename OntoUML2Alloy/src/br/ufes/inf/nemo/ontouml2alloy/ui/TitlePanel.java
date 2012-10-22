package br.ufes.inf.nemo.ontouml2alloy.ui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TitlePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TitlePanel() 
	{
		setPreferredSize(new Dimension(450, 81));
		
		JLabel lblOntoumlModelSimulation = new JLabel("OntoUML Model Simulation");
		lblOntoumlModelSimulation.setHorizontalAlignment(SwingConstants.CENTER);
		lblOntoumlModelSimulation.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblSeeTheOptions = new JLabel("See the options on the tabs before executing.");
		lblSeeTheOptions.setVerticalAlignment(SwingConstants.TOP);
		lblSeeTheOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeeTheOptions.setForeground(Color.BLACK);
		lblSeeTheOptions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblOntoumlModelSimulation, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSeeTheOptions, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblOntoumlModelSimulation)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSeeTheOptions, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

}
