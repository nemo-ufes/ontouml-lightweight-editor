package br.ufes.inf.nemo.move.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 * @author John Guerson
 */

public class SingleConstraintPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JComboBox<?> comboTransformationType;	
	public JTextField txtConstraintName;	
	public JTextField txtConstraintType;	
	public JCheckBox checkEnforce;
	public JSpinner spinCommandScope;
	public JLabel lblScope;
		
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SingleConstraintPanel() 
	{
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(412, 72));
		
		comboTransformationType = new JComboBox();
		comboTransformationType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboTransformationType.setModel(new DefaultComboBoxModel(new String[] {"FACT ", "SIMULATION", "ASSERTION"}));		
				
		txtConstraintName = new JTextField();
		txtConstraintName.setHorizontalAlignment(SwingConstants.LEFT);
		txtConstraintName.setEditable(false);
		txtConstraintName.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtConstraintName.setColumns(10);
		
		txtConstraintType = new JTextField();
		txtConstraintType.setBackground(Color.WHITE);
		txtConstraintType.setText("INVARIANT");
		txtConstraintType.setBorder(null);
		txtConstraintType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtConstraintType.setForeground(Color.BLACK);
		txtConstraintType.setHorizontalAlignment(SwingConstants.LEFT);
		txtConstraintType.setEditable(false);
		txtConstraintType.setColumns(10);
		
		checkEnforce = new JCheckBox("");
		checkEnforce.setBackground(Color.WHITE);
		checkEnforce.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkEnforce.setSelected(false);
		
		spinCommandScope = new JSpinner();
		spinCommandScope.setModel(new SpinnerNumberModel(10, 0, 32, 1));
		
		lblScope = new JLabel("Scope:");
		lblScope.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		GroupLayout gl_panel = new GroupLayout(this);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(checkEnforce)
					.addGap(6)
					.addComponent(txtConstraintType, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(comboTransformationType, 0, 202, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblScope, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinCommandScope, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtConstraintName, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(checkEnforce)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtConstraintName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtConstraintType, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(spinCommandScope, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblScope, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboTransformationType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		setLayout(gl_panel);	
	}
}
