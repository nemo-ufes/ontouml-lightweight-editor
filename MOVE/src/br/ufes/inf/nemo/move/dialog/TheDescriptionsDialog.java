package br.ufes.inf.nemo.move.dialog;


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
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

/** 
 * @author John Guerson
 */

public class TheDescriptionsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();

	private JTextPane textDescription;
	
	private JLabel lblTitle;
	
	private JButton btnClose;
	
	private JSeparator separator;
	
	private JPanel buttonPanel;
	
	/**
	 * Launch Dialog according to the identifier.
	 */
	public static void open (Component parent, String identifier)
	{
		if (identifier=="RELATOR_CONSTRAINT_AXIOM") 
		{
			String info = 
				""+"This rule enforces that the concrete relators mediate two distinct entities."+"\n\n"+
				"Uncheck this box if you have at least one relator that the sum of its mediation's"+"\n" + 
				"cardinality at the target side is less than 2."+"\n";			
			
			open(parent,"Relator Constraint Axiom",info);
		}
		
		if (identifier=="WEAK_SUPPLEMENTATION_AXIOM") 
		{
			String info = 
				""+"This rule enforces that each whole has at least two disjoint parts."+"\n\n"+
				"Uncheck this box if there's a meronymic relation where the part side has minimum \n"+
				"cardinality less than 2."+"\n";	
			
			open(parent,"Weak Supplementation Axiom",info);
		}
		
		if (identifier=="IDENTITY_PRINCIPLE_AXIOM") 
		{
			String info = 
				""+"This rule enforces that all objects have an identity principle."+"\n\n"+
				"Uncheck this box if you have at least one element without identity principle " +"\n"+
				"(i.e. an element that has no kind, quantity or collective as its supertype)."+"\n";
			
			open(parent,"Identity Principle Axiom",info);
		}
		
		if (identifier=="ANTIRIGIDITY_AXIOM") 
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
		setBounds(100, 100, 501, 323);
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		textDescription = new JTextPane();
		textDescription.setEditable(false);
		textDescription.setBackground(UIManager.getColor("Panel.background"));
		textDescription.setText(info);
		
		lblTitle = new JLabel(title);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		separator = new JSeparator();
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textDescription)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(27)
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textDescription, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(45))
		);
		contentPanel.setLayout(gl_contentPanel);
		getContentPane().add(contentPanel,BorderLayout.NORTH);
		
		buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.CENTER);
		
		btnClose = new JButton("Close");
		GroupLayout gl_buttonPanel = new GroupLayout(buttonPanel);
		gl_buttonPanel.setHorizontalGroup(
			gl_buttonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPanel.createSequentialGroup()
					.addGap(383)
					.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_buttonPanel.setVerticalGroup(
			gl_buttonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnClose)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		buttonPanel.setLayout(gl_buttonPanel);
		btnClose.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 dispose();				
       		}
       	});
	}
}
