package br.ufes.inf.nemo.move.ui.util;

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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import org.eclipse.uml2.uml.Constraint;

import java.awt.Color;

/**
 * @author John Guerson
 */

public class SingleConstraintPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JComboBox<?> comboTransformationType;	
	public JTextField txtConstraintName;	
	public JCheckBox checkEnforce;
	public JSpinner spinCommandScope;
	public JLabel lblScope;
	public Constraint constraint;	
	
	/**
	 * Constructor.
	 * 
	 * @param ct
	 * @param ctType
	 */
	public SingleConstraintPanel(Constraint ct, String ctType)
	{
		this();
		
		this.constraint = ct;
		txtConstraintName.setText(""+ctType+"  "+ct.getName());
	}
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SingleConstraintPanel() 
	{
		setBorder(null);
		setBackground(UIManager.getColor("Panel.background"));
		setPreferredSize(new Dimension(576, 41));
		
		comboTransformationType = new JComboBox();
		comboTransformationType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboTransformationType.setModel(new DefaultComboBoxModel(new String[] {"FACT ", "SIMULATION", "ASSERTION"}));		
				
		txtConstraintName = new JTextField();
		txtConstraintName.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtConstraintName.setHorizontalAlignment(SwingConstants.LEFT);
		txtConstraintName.setEditable(false);
		txtConstraintName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtConstraintName.setColumns(10);
		
		checkEnforce = new JCheckBox("");
		checkEnforce.setBackground(UIManager.getColor("Panel.background"));
		checkEnforce.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkEnforce.setSelected(true);
		
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
					.addGap(18)
					.addComponent(txtConstraintName, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboTransformationType, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblScope, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinCommandScope, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(checkEnforce, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtConstraintName, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboTransformationType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblScope, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addComponent(spinCommandScope, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		setLayout(gl_panel);	
	}
}
