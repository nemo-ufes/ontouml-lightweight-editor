package br.ufes.inf.nemo.move.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.ufes.inf.nemo.move.ui.dialog.TheDescriptionsDialog;

/**
 * @author John Guerson
 */

public class TheMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	private TheFrame frame;
	private JMenuItem mntmRelatorConstraint;	
	private JMenuItem mntmWeakSupplementation;	
	private JMenuItem mntmIdentityPrinciple;	
	private JMenuItem mntmAntirigidity;	
	private JMenu mnOutput;
	private JMenuItem mntmChangeLocation;
	private JMenu mnFile;
	
	/**
	 * Constructor.
	 * 
	 * @param parent
	 */
	public TheMenuBar (TheFrame parent)
	{
		this();
		
		this.frame = parent;
	}
	
	/**
	 * Constructor.
	 */
	public TheMenuBar()
	{	
		super();
		
		mnFile = new JMenu("File");
		add(mnFile);
		
		mnOutput = new JMenu("Output");
		add(mnOutput);
		
		mntmChangeLocation = new JMenuItem("Change Location...");
		mnOutput.add(mntmChangeLocation);
		
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
		
		mntmChangeLocation.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 frame.getOutputView().setVisible(true);       			        			 
       		}
       	});	
	}
}
