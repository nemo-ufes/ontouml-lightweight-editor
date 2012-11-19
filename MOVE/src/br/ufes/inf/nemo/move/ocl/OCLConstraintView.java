package br.ufes.inf.nemo.move.ocl;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class OCLConstraintView extends JPanel {
	
	private static final long serialVersionUID = 1347036762872517047L;
	private JLabel lblConstraint;
	private JCheckBox cbxEnable;
	private JEditorPane editConstraint;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboModeList;
	
	/**
	 * Constructor.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public OCLConstraintView() 
	{
		
		lblConstraint = new JLabel("Constraint");
		lblConstraint.setHorizontalAlignment(SwingConstants.CENTER);
		
		comboModeList = new JComboBox();
		comboModeList.setModel(new DefaultComboBoxModel(new String[] {"FACT", "SIMULATION", "ASSERTION"}));
		
		cbxEnable = new JCheckBox("Enable");
		
		editConstraint = new JEditorPane();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new java.awt.Dimension(400, 360));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
		scrollPane.setViewportView(editConstraint);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
						.addComponent(lblConstraint, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboModeList, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
							.addComponent(cbxEnable)))
					.addGap(28))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblConstraint)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboModeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxEnable))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}	
}
