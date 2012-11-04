package br.ufes.inf.nemo.move.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.ufes.inf.nemo.ontouml2alloy.util.Options;

/**
 * This MenuBar was created using the Windows Builder in Eclipse. 
 * 
 * @author John Guerson
 */

public class TheMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private JCheckBoxMenuItem cbxRelator;	
	private JCheckBoxMenuItem cbxWeakSup;	
	private JCheckBoxMenuItem cbxIdentity;	
	private JCheckBoxMenuItem cbxAntirigidity;
	
	private JMenuItem mntmRelatorConstraint;	
	private JMenuItem mntmWeakSupplementation;	
	private JMenuItem mntmIdentityPrinciple;	
	private JMenuItem mntmAntirigidity;
	
	private TheFrame frame;
	
	/**
	 *	Get Options from Panel. 
	 */
	public Options getOptions ()
	{		
		Options opt = new Options();
		opt.antiRigidity = cbxAntirigidity.isSelected();
		opt.identityPrinciple = cbxIdentity.isSelected();
		opt.relatorConstraint = cbxRelator.isSelected();
		opt.weakSupplementationConstraint = cbxWeakSup.isSelected();
		return opt;
	}
	
	/**
	 *	Set Options of the Panel. 
	 */
	public void setOptions(Options opt)
	{
		cbxAntirigidity.setSelected(opt.antiRigidity);
		cbxIdentity.setSelected(opt.identityPrinciple);
		cbxRelator.setSelected(opt.relatorConstraint);
		cbxWeakSup.setSelected(opt.weakSupplementationConstraint);
	}	

	/**
	 * Create MenuBar.
	 * 
	 * @param parent
	 */
	public TheMenuBar (TheFrame parent)
	{
		this();
		
		this.frame = parent;
	}
	
	/**
	 * Create Menu Bar.
	 */
	public TheMenuBar()
	{	
		super();
		
		JMenu mnEnforceAxioms = new JMenu("Enforce Axioms");
		add(mnEnforceAxioms);
		
		cbxRelator = new JCheckBoxMenuItem("Relator Constraint");
		cbxRelator.setSelected(true);
		mnEnforceAxioms.add(cbxRelator);
		
		cbxWeakSup = new JCheckBoxMenuItem("Weak Supplementation");
		cbxWeakSup.setSelected(true);
		mnEnforceAxioms.add(cbxWeakSup);
		
		cbxIdentity = new JCheckBoxMenuItem("Identity Principle");
		cbxIdentity.setSelected(true);
		mnEnforceAxioms.add(cbxIdentity);
		
		cbxAntirigidity = new JCheckBoxMenuItem("AntiRigidity");
		mnEnforceAxioms.add(cbxAntirigidity);
		JMenu mnHelp = new JMenu("Help");
		add(mnHelp);
		
		JMenu mnAxiomsDescriptions = new JMenu("See Axioms Descriptions");
		mnHelp.add(mnAxiomsDescriptions);
		
		mntmRelatorConstraint = new JMenuItem("Relator Constraint");
		mntmRelatorConstraint.setIcon(new ImageIcon(TheMenuBar.class.getResource("/resources/br/ufes/inf/nemo/move/about.png")));
		mnAxiomsDescriptions.add(mntmRelatorConstraint);		
	
		mntmWeakSupplementation = new JMenuItem("Weak Supplementation");
		mntmWeakSupplementation.setIcon(new ImageIcon(TheMenuBar.class.getResource("/resources/br/ufes/inf/nemo/move/about.png")));
		mnAxiomsDescriptions.add(mntmWeakSupplementation);		
		
		mntmIdentityPrinciple = new JMenuItem("Identity Principle");
		mntmIdentityPrinciple.setIcon(new ImageIcon(TheMenuBar.class.getResource("/resources/br/ufes/inf/nemo/move/about.png")));
		mnAxiomsDescriptions.add(mntmIdentityPrinciple);		
		
		mntmAntirigidity = new JMenuItem("AntiRigidity");		
		mntmAntirigidity.setIcon(new ImageIcon(TheMenuBar.class.getResource("/resources/br/ufes/inf/nemo/move/about.png")));
		mnAxiomsDescriptions.add(mntmAntirigidity);		
		
		addActionListeners();
	}
	
	/**
	 * Add Action Listener on Menu Items.
	 */
	private void addActionListeners()
	{
		mntmRelatorConstraint.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 TheDescriptionsDialog.open(frame, "RELATOR_CONSTRAINT_AXIOM");
       		}
       	});		
		
		mntmWeakSupplementation.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			TheDescriptionsDialog.open(frame, "WEAK_SUPPLEMENTATION_AXIOM");				
       		}
       	});
		
		mntmIdentityPrinciple.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			TheDescriptionsDialog.open(frame, "IDENTITY_PRINCIPLE_AXIOM");				
       		}
       	});
		
		mntmAntirigidity.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			TheDescriptionsDialog.open(frame, "ANTIRIGIDITY_AXIOM");
       		}
       	});
	
	}


}
