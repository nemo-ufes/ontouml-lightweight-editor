package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class TheAntiPatternOptionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	
	private JCheckBox cbxSTR;
	private JCheckBox cbxIA;
	private JCheckBox cbxRWOR;
	private JCheckBox cbxRBOS;
	private JCheckBox cbxAC;
	private JCheckBox cbxRS;
	
	/** 
	 * Check if AntiPattern is selected.
	 */
	public Boolean ACisSelected() { return cbxAC.isSelected(); }	
	public Boolean STRisSelected() { return cbxSTR.isSelected(); }
	public Boolean RBOSisSelected() { return cbxRBOS.isSelected(); }
	public Boolean IAisSelected() { return cbxIA.isSelected(); }
	public Boolean RSisSelected() { return cbxRS.isSelected(); }
	public Boolean RWORisSelected() { return cbxRWOR.isSelected(); }
	
	/**
	 * Open the Dialog.
	 */
	public static void  open (Component parent)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			TheAntiPatternOptionDialog dialog = new TheAntiPatternOptionDialog();
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
	public TheAntiPatternOptionDialog() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(TheAntiPatternOptionDialog.class.getResource("/resources/br/ufes/inf/nemo/move/window.png")));
		setTitle("AntiPattern Options");
		setBounds(100, 100, 342, 268);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		cbxSTR = new JCheckBox("STR : Self-Type Relationship");
		cbxSTR.setBackground(UIManager.getColor("Panel.background"));
		cbxIA = new JCheckBox("IA : Imprecise Abstraction");
		cbxIA.setBackground(UIManager.getColor("Panel.background"));
		cbxRWOR = new JCheckBox("RWOR : Relator With Overlapping Roles");
		cbxRWOR.setBackground(UIManager.getColor("Panel.background"));
		cbxRBOS = new JCheckBox("RBOS : Relation Between Overlapping SubTypes");
		cbxRBOS.setBackground(UIManager.getColor("Panel.background"));
		cbxAC = new JCheckBox("AC : Association Cycle");
		cbxAC.setBackground(UIManager.getColor("Panel.background"));
		cbxRS = new JCheckBox("RS : Relation Specialization");
		cbxRS.setBackground(UIManager.getColor("Panel.background"));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxRS, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxAC, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxRBOS, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxRWOR, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxSTR, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxIA, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(cbxSTR)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxIA)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRWOR)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRBOS)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxAC)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRS)
					.addContainerGap(33, Short.MAX_VALUE))
		);		
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton identifyButton = new JButton("Identify");		
		buttonPane.add(identifyButton);		
		identifyButton.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			// not implemented yet...
       		}
       	});
		
		JButton btnEnableall = new JButton("EnableAll");
		buttonPane.add(btnEnableall);
				
		btnEnableall.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			if (!ACisSelected()) cbxAC.setSelected(true);
       			if (!STRisSelected()) cbxSTR.setSelected(true);
       			if (!RBOSisSelected()) cbxRBOS.setSelected(true);
       			if (!IAisSelected()) cbxIA.setSelected(true);
       			if (!RSisSelected()) cbxRS.setSelected(true);
       			if (!RWORisSelected()) cbxRWOR.setSelected(true);
       		}
       	});
		
		JButton btnDisableall = new JButton("DisableAll");
		buttonPane.add(btnDisableall);
		
		btnDisableall.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			if (ACisSelected()) cbxAC.setSelected(false);
       			if (STRisSelected()) cbxSTR.setSelected(false);
       			if (RBOSisSelected()) cbxRBOS.setSelected(false);
       			if (IAisSelected()) cbxIA.setSelected(false);
       			if (RSisSelected()) cbxRS.setSelected(false);
       			if (RWORisSelected()) cbxRWOR.setSelected(false);
       		}
       	});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);		
	}
}
