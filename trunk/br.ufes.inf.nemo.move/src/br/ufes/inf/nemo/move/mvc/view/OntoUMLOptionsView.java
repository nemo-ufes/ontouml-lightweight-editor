package br.ufes.inf.nemo.move.mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.move.mvc.model.OntoUMLOptionsModel;
import br.ufes.inf.nemo.move.ui.TheFrame;

/**
 * 
 * This class represents a View for OntoUML Options Model.
 * 
 * @author John Guerson
 */

public class OntoUMLOptionsView extends JPanel {

	private static final long serialVersionUID = 4762609414668205905L;
		
	@SuppressWarnings("unused")
	private OntoUMLOptionsModel optModel;
		
	private TheFrame frame;
	private JCheckBox cbxRelatorConstraint;
	private JCheckBox cbxWeakSupplementation ;	
	private JCheckBox cbxIdentityPrinciple ;
	private JCheckBox cbxAntirigidity;
	private JLabel lblEnforceAxioms;
	private JButton btnEnableall;
	private JButton btnDisableall;
	private JPanel btnPanel;
	private JPanel titlePanel;
	private JButton btnDetailsRelator;	
	private JButton btnDetailsWeakSupp;	
	private JButton btnDetailsIdentity;	
	private JButton btnDetailsAntiRigidity;	
	private JTextArea textArea;
	private String detailsRelator;
	private String detailsWeakSupp;
	private String detailsIdentity;
	private String detailsAntirigidity;
	
	/**
	 * Creates a View from OntoUML Options Model and the main frame application.
	 * 
	 * @param optModel
	 * @param frame
	 */
	public OntoUMLOptionsView(OntoUMLOptionsModel optModel, TheFrame frame)
	{
		this();
		
		this.optModel = optModel;
		this.frame = frame;
	
		setOntoUMLOptionsView(optModel);
	}

	/**
	 * Set OntoUML Options View from OntoUML Options Model.
	 * 
	 * @param optModel
	 */
	public void setOntoUMLOptionsView (OntoUMLOptionsModel optModel)
	{
		this.cbxAntirigidity.setSelected(optModel.getOptions().antiRigidity);
		this.cbxIdentityPrinciple.setSelected(optModel.getOptions().identityPrinciple);
		this.cbxRelatorConstraint.setSelected(optModel.getOptions().relatorConstraint);
		this.cbxWeakSupplementation.setSelected(optModel.getOptions().weakSupplementationConstraint);		
		validate();
		repaint();		
	}

	/**
	 * Creates an Empty View for OntoUML Options Model.
	 */
	public OntoUMLOptionsView() 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(UIManager.getColor("Panel.background"));
		setPreferredSize(new Dimension(591, 280));
		setSize(new Dimension(186, 228));				

		JPanel cbxPanel = new JPanel();
		cbxPanel.setBackground(UIManager.getColor("Panel.background"));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollPane.setPreferredSize(new Dimension(300, 100));
		scrollPane.setViewportView(cbxPanel);
				
		btnPanel = new JPanel();
		btnPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnPanel.setPreferredSize(new Dimension(100,40));
		
		titlePanel = new JPanel();
		titlePanel.setBackground(UIManager.getColor("Panel.background"));
		titlePanel.setPreferredSize(new Dimension(100, 30));
		lblEnforceAxioms = new JLabel("Enforce Axioms.");
		lblEnforceAxioms.setBackground(UIManager.getColor("Label.background"));
		lblEnforceAxioms.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnforceAxioms.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnEnableall = new JButton("Enable All");
		btnEnableall.setPreferredSize(new Dimension(100, 25));
		
		btnDisableall = new JButton("Disable All");
		btnDisableall.setPreferredSize(new Dimension(100, 25));
		
		btnDisableall.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cbxAntirigidity.setSelected(false);
				cbxIdentityPrinciple.setSelected(false);
				cbxRelatorConstraint.setSelected(false);
				cbxWeakSupplementation.setSelected(false);
			}
		});
			
		btnEnableall.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cbxAntirigidity.setSelected(true);
				cbxIdentityPrinciple.setSelected(true);
				cbxRelatorConstraint.setSelected(true);
				cbxWeakSupplementation.setSelected(true);
			}
		});
		
		cbxRelatorConstraint = new JCheckBox("Relator Constraint");
		cbxRelatorConstraint.setBackground(UIManager.getColor("Panel.background"));
		cbxWeakSupplementation = new JCheckBox("Weak Supplementation Constraint");
		cbxWeakSupplementation.setBackground(UIManager.getColor("Panel.background"));
		cbxIdentityPrinciple = new JCheckBox("Identity Principle");
		cbxIdentityPrinciple.setBackground(UIManager.getColor("Panel.background"));
		cbxAntirigidity = new JCheckBox("Antirigidity");
		cbxAntirigidity.setBackground(UIManager.getColor("Panel.background"));
							
		detailsRelator = new String();				
		detailsRelator = "Relator Constraint.\n\n"+
		"This rule enforces that the concrete relators \n" +
		"mediate at least two distinct entities.\n\n"+
		"Uncheck this box if you have at least one relator \n" +
		"that the sum of its mediation's cardinality at the \n" +
		"target side is less than 2.\n\n";
		
		detailsWeakSupp = new String();				
		detailsWeakSupp = "Weak Supplementation Constraint.\n\n"+
		"This rule enforces that each whole has at least \n" +
		"two disjoint parts.\n\n"+
		"Uncheck this box if there's a meronymic relation \n"+ 
		"where the part side has minimum cardinality less \n"+
		"than 2.\n\n";	
		
		detailsIdentity = new String();				
		detailsIdentity = "Identity Principle.\n\n"+
		"This rule enforces that all objects have an \n"+
		"identity principle.\n\n"+
		"Uncheck this box if you have at least one element \n"+
		"without identity principle i.e. an element that \n"+
		"has no kind, quantity or collective as its supertype.\n\n";	
		
		detailsAntirigidity = new String();				
		detailsAntirigidity = "Anti-Rigidity.\n\n"+
		"This rule enforces the anti-rigidity axiom.\n"+
		"Check this box if you always want to visualize \n" +
		"objects instantiating an anti-rigid type \n"+
		"in a World and not instantiating it in another \n"+
		"World.\n\n"+
		"Note: if you enforce this axiom you need to simulate " +
		"the model with at least two Worlds.\n\n";	
		
		textArea = new JTextArea(detailsRelator);
		textArea.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea.setEditable(false);
		textArea.setCaretPosition(0);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane2 = new JScrollPane();		
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane2.setPreferredSize(new Dimension(100, 100));
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);		
		scrollPane2.setViewportView(textArea);
		
		btnDetailsRelator = new JButton("->");	
		btnDetailsRelator.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{							
				textArea.setText(detailsRelator);		
				textArea.setCaretPosition(0);
			}
		});
		
		btnDetailsWeakSupp = new JButton("->");	
		btnDetailsWeakSupp.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{						
				textArea.setText(detailsWeakSupp);		
				textArea.setCaretPosition(0);								
			}
		});		 
				 
		btnDetailsIdentity = new JButton("->");		
		btnDetailsIdentity.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{											
				textArea.setText(detailsIdentity);		
				textArea.setCaretPosition(0);					
			}
		});		
		
		btnDetailsAntiRigidity = new JButton("->");
		btnDetailsAntiRigidity.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{							
				textArea.setText(detailsAntirigidity);		
				textArea.setCaretPosition(0);				
			}
		});
		
		GroupLayout gl_cbxPanel = new GroupLayout(cbxPanel);
		gl_cbxPanel.setHorizontalGroup(
			gl_cbxPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cbxPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxRelatorConstraint, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
						.addComponent(cbxWeakSupplementation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxIdentityPrinciple, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
						.addComponent(cbxAntirigidity, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_cbxPanel.createSequentialGroup()
							.addComponent(btnDetailsRelator)
							.addContainerGap())
						.addGroup(gl_cbxPanel.createSequentialGroup()
							.addComponent(btnDetailsWeakSupp)
							.addContainerGap())
						.addGroup(gl_cbxPanel.createSequentialGroup()
							.addComponent(btnDetailsIdentity)
							.addContainerGap())
						.addGroup(gl_cbxPanel.createSequentialGroup()
							.addComponent(btnDetailsAntiRigidity)
							.addContainerGap())))
		);
		gl_cbxPanel.setVerticalGroup(
			gl_cbxPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cbxPanel.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxRelatorConstraint)
						.addComponent(btnDetailsRelator))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxWeakSupplementation)
						.addComponent(btnDetailsWeakSupp))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxIdentityPrinciple)
						.addComponent(btnDetailsIdentity))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxAntirigidity)
						.addComponent(btnDetailsAntiRigidity))
					.addGap(50))
		);
		
		cbxPanel.setLayout(gl_cbxPanel);
		cbxPanel.setPreferredSize(new Dimension(100,100));
		setLayout(new BorderLayout(0, 0));
		add(titlePanel, BorderLayout.NORTH);
		
		GroupLayout gl_titlePanel = new GroupLayout(titlePanel);
		gl_titlePanel.setHorizontalGroup(
			gl_titlePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_titlePanel.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addComponent(lblEnforceAxioms, GroupLayout.PREFERRED_SIZE, 563, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_titlePanel.setVerticalGroup(
			gl_titlePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titlePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEnforceAxioms)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		titlePanel.setLayout(gl_titlePanel);
		add(scrollPane,BorderLayout.WEST);
		add(scrollPane2,BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);
		GroupLayout gl_btnPanel = new GroupLayout(btnPanel);
		gl_btnPanel.setHorizontalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addGap(18)
					.addComponent(btnEnableall, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDisableall, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(367))
		);
		gl_btnPanel.setVerticalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_btnPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEnableall, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDisableall, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		btnPanel.setLayout(gl_btnPanel);
	}
	
	/**
	 * Is Selected Relator Constraint Option.
	 * 
	 * @return
	 */	
	public boolean isSelectedRelatorConstraint() { return cbxRelatorConstraint.isSelected(); }
	
	/**
	 * Is Selected Weak Supplementation Option.
	 * 
	 * @return
	 */
	public boolean isSelectedWeakSupplementation() { return cbxWeakSupplementation.isSelected(); }
	
	/**
	 * Is Selected AntiRigidity Option.
	 * 
	 * @return
	 */
	public boolean isSelectedAntirigidity() { return cbxAntirigidity.isSelected(); }
		
	/**
	 * Is Selected Identity Principle Option.
	 * 
	 * @return
	 */
	public boolean isSelectedIdentityPrinciple() { return cbxIdentityPrinciple.isSelected(); }
	
	/**
	 * Get the main frame application.
	 * 
	 * @return
	 */
	public TheFrame getTheFrame() { return frame; }
}
