package br.ufes.inf.nemo.move.ui;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

/**
 * This Panel was created using the Windows Builder in Eclipse. 
 * 
 * @author John Guerson
 */

public class TheDescriptionsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();

	/* Description */
	private JTextPane textDescription;
	
	/* Title */
	private JLabel lblTitle;
	
	/* Quit Button */
	private JButton btnClose;
	
	/**
	 * Launch Dialog according to the identifier.
	 */
	public static void open (Component parent, String identifier)
	{
		if (identifier=="RELATOR CONSTRAINT AXIOM") 
		{
			String info = 
				""+"This rule enforces that the concrete relators mediate two distinct entities."+"\n\n"+
				"Uncheck this box if you have at least one relator that the sum of its mediation's"+"\n" + 
				"cardinality at the target side is less than 2."+"\n";			
			
			open(parent,"Relator Constraint Axiom",info);
		}
		
		if (identifier=="WEAK SUPPLEMENTATION AXIOM") 
		{
			String info = 
				""+"This rule enforces that each whole has at least two disjoint parts."+"\n\n"+
				"Uncheck this box if there's a meronymic relation where the part side has minimum \n"+
				"cardinality less than 2."+"\n";	
			
			open(parent,"Weak Supplementation Axiom",info);
		}
		
		if (identifier=="IDENTITY PRINCIPLE AXIOM") 
		{
			String info = 
				""+"This rule enforces that all objects have an identity principle."+"\n\n"+
				"Uncheck this box if you have at least one element without identity principle " +"\n"+
				"(i.e. an element that has no kind, quantity or collective as its supertype)."+"\n";
			
			open(parent,"Identity Principle Axiom",info);
		}
		
		if (identifier=="ANTIRIGIDITY AXIOM") 
		{
			String info = 
				""+"This rule enforces the anti-rigidity axiom."+"\n\n"+ 
				"Check this box if you always want to visualize objects instantiating an anti-rigid type"+"\n"+ 
				"in a World and not instantiating it in another World."+"\n\n"+
				"Note: if you enforce this axiom you need to simulate the model with at least two Worlds."+"\n";
			
			open(parent,"Antirigidity Axiom",info);
		}	
	}
	
	/**
	 * Launch Dialog.
	 */
	public static void open (Component parent, String title, String info) 
	{
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			TheDescriptionsDialog dialog = new TheDescriptionsDialog(title, info);
			
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
	public TheDescriptionsDialog(String title, String info) 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(TheDescriptionsDialog.class.getResource("/resources/br/ufes/inf/nemo/move/about.png")));		
		setTitle("Axiom Description");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 501, 251);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		textDescription = new JTextPane();
		textDescription.setEditable(false);
		textDescription.setBackground(UIManager.getColor("Panel.background"));
		textDescription.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textDescription.setText(info);
		
		lblTitle = new JLabel(title);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnClose = new JButton("Close");		
		btnClose.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 dispose();				
       		}
       	});		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(textDescription, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
								.addComponent(lblTitle)))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addContainerGap(383, Short.MAX_VALUE)
							.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textDescription, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnClose)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
