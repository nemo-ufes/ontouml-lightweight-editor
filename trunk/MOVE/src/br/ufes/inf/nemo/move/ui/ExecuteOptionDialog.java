package br.ufes.inf.nemo.move.ui;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/** 
 * @author John Guerson
 */

public class ExecuteOptionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JCheckBox chckbxRelatorConstraintAxiom;
	private JCheckBox chckbxWeakSupplementationAxiom;
	private JCheckBox chckbxIdentityPrinciple;
	private JCheckBox chckbxAntirigidity;
	private JLabel lblEnforceAxioms;
			
	/**
	 * Launch Dialog.
	 */
	public static void open (Component parent, String title, String info) 
	{
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			ExecuteOptionDialog dialog = new ExecuteOptionDialog(title, info);
			
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(parent);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ExecuteOptionDialog(String title, String info) 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExecuteOptionDialog.class.getResource("/resources/br/ufes/inf/nemo/move/play.png")));		
		setTitle("Execute ...");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 301, 233);
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		chckbxRelatorConstraintAxiom = new JCheckBox("Relator Constraint");		
		chckbxWeakSupplementationAxiom = new JCheckBox("Weak Supplementation ");		
		chckbxIdentityPrinciple = new JCheckBox("Identity Principle ");		
		chckbxAntirigidity = new JCheckBox("Antirigidity");
		
		lblEnforceAxioms = new JLabel("Enforce Axioms :");
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEnforceAxioms, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(chckbxRelatorConstraintAxiom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
						.addComponent(chckbxWeakSupplementationAxiom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(chckbxIdentityPrinciple, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(chckbxAntirigidity, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(15))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEnforceAxioms)
					.addGap(16)
					.addComponent(chckbxRelatorConstraintAxiom)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxWeakSupplementationAxiom)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxIdentityPrinciple)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxAntirigidity)
					.addContainerGap(88, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		getContentPane().add(contentPanel,BorderLayout.NORTH);
	}
}
