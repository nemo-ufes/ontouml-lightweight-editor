package br.ufes.inf.nemo.move.panel;

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

import br.ufes.inf.nemo.move.dialog.DescriptionDialog;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;

/**
 * This Panel was created using the Windows Builder in Eclipse. 
 */

public class OptionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
			
	private JCheckBox chckbxRelatorAxiom;
	
	private JCheckBox chckbxWeakSupplementationAxiom;
	
	private JCheckBox chckbxIdentityPrincipleAxiom;
		
	private JCheckBox chckbxAntirigidity;
	
	public Options getOptions ()
	{		
		Options opt = new Options();
		opt.antiRigidity = chckbxAntirigidity.isSelected();
		opt.identityPrinciple = chckbxIdentityPrincipleAxiom.isSelected();
		opt.relatorConstraint = chckbxRelatorAxiom.isSelected();
		opt.weakSupplementationConstraint = chckbxWeakSupplementationAxiom.isSelected();
		return opt;
	}
	
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
	public OptionsPanel() 
	{
		setBorder(new TitledBorder(null, "Enforce Axioms", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setBackground(SystemColor.inactiveCaption);
		setPreferredSize(new Dimension(423, 127));
		
		chckbxRelatorAxiom = new JCheckBox("Relator Constraint");
		chckbxRelatorAxiom.setBackground(UIManager.getColor("inactiveCaption"));
		
		chckbxWeakSupplementationAxiom = new JCheckBox("Weak Supplementation ");
		chckbxWeakSupplementationAxiom.setBackground(UIManager.getColor("inactiveCaption"));
		
		chckbxIdentityPrincipleAxiom = new JCheckBox("Identity Principle");
		chckbxIdentityPrincipleAxiom.setBackground(UIManager.getColor("inactiveCaption"));
		
		chckbxAntirigidity = new JCheckBox("AntiRigidity Visualization");
		chckbxAntirigidity.setBackground(UIManager.getColor("inactiveCaption"));
		
		JButton btnWeakSupplementation = new JButton("?");
		btnWeakSupplementation.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String info = 
						""+"This rule enforces that each whole has at least two disjoint parts."+"\n\n"+
						"Uncheck this box if there's a meronymic relation where the part side has minimum \n"+"cardinality less than 2."+"\n"; 
				DescriptionDialog.open(null,"Weak Supplementation Axiom",info);					
			}
		});
		
		JButton btnRelatorConstraint = new JButton("?");		
		btnRelatorConstraint.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String info = 
						""+"This rule enforces that the concrete relators mediate two distinct entities."+"\n\n"+
						"Uncheck this box if you have at least one relator that the sum of its mediation's"+"\n" + 
						"cardinality at the target side is less than 2."+"\n"; 
				DescriptionDialog.open(null,"Relator Constraint Axiom",info);				
			}
		});
		
		JButton btnIdentityPrinciple = new JButton("?");		
		btnIdentityPrinciple.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String info = 
						""+"This rule enforces that all objects have an identity principle."+"\n\n"+
						"Uncheck this box if you have at least one element without identity principle " +"\n"+
						"(i.e. an element that has no kind, quantity or collective as its supertype)."+"\n";
				DescriptionDialog.open(null,"Identity Principle Axiom",info);		
			}
		});
		
		JButton btnAntiRigidity = new JButton("?");
		btnAntiRigidity.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String info = 
						""+"This rule enforces the anti-rigidity axiom."+"\n\n"+ 
						"Check this box if you always want to visualize objects instantiating an anti-rigid type"+"\n"+ 
						"in a World and not instantiating it in another World."+"\n\n"+
						"Note: if you enforce this axiom you need to simulate the model with at least two Worlds."+"\n";
				DescriptionDialog.open(null,"Antirigidity Visualization Axiom",info);	
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
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
					.addGap(18)
					.addComponent(chckbxAntirigidity)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAntiRigidity, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnRelatorConstraint, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(chckbxAntirigidity)
							.addComponent(btnAntiRigidity, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addComponent(chckbxRelatorAxiom))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnWeakSupplementation, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxWeakSupplementationAxiom))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxIdentityPrincipleAxiom)
						.addComponent(btnIdentityPrinciple, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		setLayout(groupLayout);
	}
}