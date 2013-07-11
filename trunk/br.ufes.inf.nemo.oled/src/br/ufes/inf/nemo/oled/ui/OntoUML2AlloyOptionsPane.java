package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * 
 * This class represents a Panel for OntoUML2Alloy Options.
 * 
 * @author John Guerson
 */

public class OntoUML2AlloyOptionsPane extends JPanel {

	private static final long serialVersionUID = 4762609414668205905L;
		
	@SuppressWarnings("unused")
	private OntoUML2AlloyOptions refOptions;
		
	private AppFrame frame;
	private JCheckBox cbxRelator;
	private JCheckBox cbxWeak ;	
	private JCheckBox cbxIdentity ;
	private JCheckBox cbxAntirigidity;
	private JButton btnEnableall;
	private JButton btnDisableall;
	private JPanel btnPanel;
	private JPanel titlePanel;
	private JLabel lblDescriptionRelator;
	private JLabel lblDescriptionWeak;
	private JLabel lblDesciptionIdentity;
	private JLabel lblDescriptionAntirigidity;
	private JLabel lblSateRelator;
	private JLabel lblStateWeak;
	private JLabel lblStateIdentity;
	private JLabel lblStateAntirigidity;
	private JLabel lblChooseWhichAxioms;
	
	/**
	 * Creates a View from OntoUML Options Model and the main frame application.
	 * 
	 * @param optModel
	 * @param frame
	 */
	public OntoUML2AlloyOptionsPane(OntoUML2AlloyOptions refOptions, AppFrame frame)
	{
		this();
		
		this.refOptions = refOptions;
		this.frame = frame;
	
		setOntoUMLOptionsPane(refOptions);
	}

	/**
	 * Set OntoUML Options Pane from OntoUML2Alloy Options.
	 */
	public void setOntoUMLOptionsPane (OntoUML2AlloyOptions refOptions)
	{
		this.cbxAntirigidity.setSelected(refOptions.antiRigidity);
				
		this.cbxIdentity.setSelected(refOptions.identityPrinciple);
		if (!cbxIdentity.isSelected()) setStateIdentityPrinciple("not recommended");
		
		this.cbxRelator.setSelected(refOptions.relatorAxiom);
		if (!cbxRelator.isSelected()) setStateRelatorConstraint("not recommended");
		
		this.cbxWeak.setSelected(refOptions.weakSupplementationAxiom);
		if (!cbxWeak.isSelected()) setStateWeakSupplementation("not recommended");
		
		validate();
		repaint();		
	}

	/**
	 * Creates an Empty Pane for OntoUML2Alloy Options.
	 */
	public OntoUML2AlloyOptionsPane() 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(UIManager.getColor("Panel.background"));
		setPreferredSize(new Dimension(591, 293));
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
		
		btnEnableall = new JButton("Enable All");
		btnEnableall.setPreferredSize(new Dimension(100, 25));
		
		btnDisableall = new JButton("Disable All");
		btnDisableall.setPreferredSize(new Dimension(100, 25));
		
		btnDisableall.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cbxAntirigidity.setSelected(false);
				cbxIdentity.setSelected(false);
				cbxRelator.setSelected(false);
				cbxWeak.setSelected(false);
			}
		});
			
		btnEnableall.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cbxAntirigidity.setSelected(true);
				cbxIdentity.setSelected(true);
				cbxRelator.setSelected(true);
				cbxWeak.setSelected(true);
			}
		});
		
		cbxRelator = new JCheckBox("Enforce Relator Constraint");
		cbxRelator.setBackground(UIManager.getColor("Panel.background"));
		cbxWeak = new JCheckBox("Enforce Weak Supplementation");
		cbxWeak.setBackground(UIManager.getColor("Panel.background"));
		cbxIdentity = new JCheckBox("Enforce Identity Principle");
		cbxIdentity.setBackground(UIManager.getColor("Panel.background"));
		cbxAntirigidity = new JCheckBox("Enforce Antirigidity Visualization");
		cbxAntirigidity.setBackground(UIManager.getColor("Panel.background"));
			
		lblDescriptionRelator = new JLabel("Mark if all relators have the sum of its mediation's lower cardinality (at the target) greater or equal to 2.");
		lblDescriptionRelator.setForeground(Color.GRAY);
		
		lblDescriptionWeak = new JLabel("Mark if all wholes have the sum of its part's lower cardinality greater or equal to 2.");
		lblDescriptionWeak.setForeground(Color.GRAY);
		
		lblDesciptionIdentity = new JLabel("Mark if all elements have an identity principle.");
		lblDesciptionIdentity.setForeground(Color.GRAY);
		
		lblDescriptionAntirigidity = new JLabel("Mark to visualize an instance of an anti-rigid type in a World and not an instance of it in another World.");
		lblDescriptionAntirigidity.setForeground(Color.GRAY);
		
		lblSateRelator = new JLabel("");
		lblSateRelator.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSateRelator.setForeground(Color.RED);
		
		lblStateWeak = new JLabel("");
		lblStateWeak.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblStateWeak.setForeground(Color.RED);
		
		lblStateIdentity = new JLabel("");
		lblStateIdentity.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblStateIdentity.setForeground(Color.RED);
		
		lblStateAntirigidity = new JLabel("");
		lblStateAntirigidity.setForeground(Color.RED);
		lblStateAntirigidity.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		GroupLayout gl_cbxPanel = new GroupLayout(cbxPanel);
		gl_cbxPanel.setHorizontalGroup(
			gl_cbxPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cbxPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cbxPanel.createSequentialGroup()
							.addComponent(cbxIdentity, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblStateIdentity, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
						.addComponent(lblDescriptionRelator, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.addGroup(gl_cbxPanel.createSequentialGroup()
							.addComponent(cbxRelator, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSateRelator, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
						.addComponent(lblDescriptionWeak, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.addComponent(lblDesciptionIdentity, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.addGroup(gl_cbxPanel.createSequentialGroup()
							.addComponent(cbxWeak, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblStateWeak, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
						.addGroup(gl_cbxPanel.createSequentialGroup()
							.addComponent(cbxAntirigidity, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblStateAntirigidity, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
						.addComponent(lblDescriptionAntirigidity, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_cbxPanel.setVerticalGroup(
			gl_cbxPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cbxPanel.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxRelator, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSateRelator, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDescriptionRelator)
					.addGap(13)
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxWeak, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStateWeak, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDescriptionWeak)
					.addGap(11)
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxIdentity, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStateIdentity, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDesciptionIdentity)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxAntirigidity, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStateAntirigidity, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDescriptionAntirigidity)
					.addGap(25))
		);
		
		cbxPanel.setLayout(gl_cbxPanel);
		cbxPanel.setPreferredSize(new Dimension(100,100));
		setLayout(new BorderLayout(0, 0));
		add(titlePanel, BorderLayout.NORTH);
		
		lblChooseWhichAxioms = new JLabel("Choose which axioms do you want to enforce... (the checked ones are recommented by default)");
		
		GroupLayout gl_titlePanel = new GroupLayout(titlePanel);
		gl_titlePanel.setHorizontalGroup(
			gl_titlePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_titlePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChooseWhichAxioms, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_titlePanel.setVerticalGroup(
			gl_titlePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titlePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChooseWhichAxioms)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		titlePanel.setLayout(gl_titlePanel);
		add(scrollPane,BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);
		GroupLayout gl_btnPanel = new GroupLayout(btnPanel);
		gl_btnPanel.setHorizontalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnEnableall, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDisableall, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(375))
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
	
	public void setStateRelatorConstraint(String text) 
	{
		this.lblSateRelator.setText(text);
	}

	public void setStateWeakSupplementation(String text)
	{
		this.lblStateWeak.setText(text);
	}

	public void setStateIdentityPrinciple(String text)
	{
		this.lblStateIdentity.setText(text);
	}

	public void setStateAntirigidity(String text)
	{
		this.lblStateAntirigidity.setText(text);
	}
	
	/**
	 * Is Selected Relator Constraint Option.
	 * 
	 * @return
	 */	
	public boolean isSelectedRelatorConstraint() { return cbxRelator.isSelected(); }
	
	/**
	 * Is Selected Weak Supplementation Option.
	 * 
	 * @return
	 */
	public boolean isSelectedWeakSupplementation() { return cbxWeak.isSelected(); }
	
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
	public boolean isSelectedIdentityPrinciple() { return cbxIdentity.isSelected(); }
	
	/**
	 * Get the main frame application.
	 * 
	 * @return
	 */
	public AppFrame getFrame() { return frame; }
}
