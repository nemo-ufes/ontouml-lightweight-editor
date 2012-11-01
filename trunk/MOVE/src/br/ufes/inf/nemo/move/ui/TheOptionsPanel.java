package br.ufes.inf.nemo.move.ui;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.ontouml2alloy.util.Options;

/**
 * This Panel was created using the Windows Builder in Eclipse. 
 * 
 * @author John Guerson
 */

public class TheOptionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
			
	/* checkboxes */
	private JCheckBox chckbxRelatorAxiom;	
	private JCheckBox chckbxWeakSupplementationAxiom;	
	private JCheckBox chckbxIdentityPrincipleAxiom;		
	private JCheckBox chckbxAntirigidity;
	
	/* buttons */
	private JButton btnWeakSupplementation;
	private JButton btnRelatorConstraint;
	private JButton btnIdentityPrinciple;
	private JButton btnAntiRigidity;
	
	/**
	 *	Get Options from Panel. 
	 */
	public Options getOptions ()
	{		
		Options opt = new Options();
		opt.antiRigidity = chckbxAntirigidity.isSelected();
		opt.identityPrinciple = chckbxIdentityPrincipleAxiom.isSelected();
		opt.relatorConstraint = chckbxRelatorAxiom.isSelected();
		opt.weakSupplementationConstraint = chckbxWeakSupplementationAxiom.isSelected();
		return opt;
	}
	
	/**
	 *	Set Options of the Panel. 
	 */
	public void setOptions(Options opt)
	{
		chckbxAntirigidity.setSelected(opt.antiRigidity);
		chckbxIdentityPrincipleAxiom.setSelected(opt.identityPrinciple);
		chckbxRelatorAxiom.setSelected(opt.relatorConstraint);
		chckbxWeakSupplementationAxiom.setSelected(opt.weakSupplementationConstraint);
	}
	
	/**
	 * Create the panel.
	 */
	public TheOptionsPanel() 
	{
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enforce Axioms", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setBackground(SystemColor.inactiveCaption);
		setPreferredSize(new Dimension(232, 149));
		
		chckbxRelatorAxiom = new JCheckBox("Relator Constraint");
		chckbxRelatorAxiom.setSelected(true);
		chckbxRelatorAxiom.setBackground(UIManager.getColor("inactiveCaption"));
		
		chckbxWeakSupplementationAxiom = new JCheckBox("Weak Supplementation ");
		chckbxWeakSupplementationAxiom.setSelected(true);
		chckbxWeakSupplementationAxiom.setBackground(UIManager.getColor("inactiveCaption"));
		
		chckbxIdentityPrincipleAxiom = new JCheckBox("Identity Principle");
		chckbxIdentityPrincipleAxiom.setSelected(true);
		chckbxIdentityPrincipleAxiom.setBackground(UIManager.getColor("inactiveCaption"));
		
		chckbxAntirigidity = new JCheckBox("AntiRigidity");
		chckbxAntirigidity.setBackground(UIManager.getColor("inactiveCaption"));
		
		btnWeakSupplementation = new JButton("?");
		btnWeakSupplementation.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				 
				TheDescriptionsDialog.open(null,"WEAK SUPPLEMENTATION AXIOM");					
			}
		});
		
		btnRelatorConstraint = new JButton("?");		
		btnRelatorConstraint.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				TheDescriptionsDialog.open(null,"RELATOR CONSTRAINT AXIOM");				
			}
		});
		
		btnIdentityPrinciple = new JButton("?");		
		btnIdentityPrinciple.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
				TheDescriptionsDialog.open(null,"IDENTITY PRINCIPLE AXIOM");		
			}
		});
		
		btnAntiRigidity = new JButton("?");
		btnAntiRigidity.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
				TheDescriptionsDialog.open(null,"ANTIRIGIDITY AXIOM");	
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chckbxAntirigidity)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnAntiRigidity, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(chckbxWeakSupplementationAxiom)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnWeakSupplementation))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(chckbxIdentityPrincipleAxiom)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnIdentityPrinciple, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chckbxRelatorAxiom)
							.addGap(28)
							.addComponent(btnRelatorConstraint, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(154, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnRelatorConstraint, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxRelatorAxiom))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnWeakSupplementation, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxWeakSupplementationAxiom))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxIdentityPrincipleAxiom)
						.addComponent(btnIdentityPrinciple, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxAntirigidity)
						.addComponent(btnAntiRigidity, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(13))
		);
		setLayout(groupLayout);
	}
}