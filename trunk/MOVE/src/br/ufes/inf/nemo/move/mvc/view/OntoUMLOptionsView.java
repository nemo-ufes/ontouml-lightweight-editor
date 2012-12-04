package br.ufes.inf.nemo.move.mvc.view;

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
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import br.ufes.inf.nemo.move.mvc.model.OntoUMLOptionsModel;
import br.ufes.inf.nemo.move.ui.TheFrame;
import java.awt.FlowLayout;
import javax.swing.border.EmptyBorder;

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
	private JCheckBox cbxOpenAnalyzer;	
	private JLabel lblEnforceAxioms;
	private JButton btnEnableall;
	private JButton btnDisableall;
	private JPanel btnPanel;
	private JPanel titlePanel;
		
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
		this.cbxOpenAnalyzer.setSelected(optModel.getOptions().openAnalyzer);
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
		setPreferredSize(new Dimension(591, 228));
		setSize(new Dimension(186, 228));				
								
		JLabel lblDomainConstraintsOptions = new JLabel("OCL Domain Constraints");
		lblDomainConstraintsOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblDomainConstraintsOptions.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JPanel cbxPanel = new JPanel();
		cbxPanel.setBackground(UIManager.getColor("Panel.background"));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollPane.setViewportView(cbxPanel);
				
		btnPanel = new JPanel();
		btnPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		btnPanel.setPreferredSize(new Dimension(100,40));
		
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setPreferredSize(new Dimension(100, 30));
		lblEnforceAxioms = new JLabel("Enforce Axioms.");
		lblEnforceAxioms.setBackground(Color.WHITE);
		lblEnforceAxioms.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnforceAxioms.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnEnableall = new JButton("Enable All");
		btnPanel.add(btnEnableall);
		btnEnableall.setPreferredSize(new Dimension(100, 25));
		
		btnDisableall = new JButton("Disable All");
		btnPanel.add(btnDisableall);
		btnDisableall.setPreferredSize(new Dimension(100, 25));
		
		btnDisableall.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cbxAntirigidity.setSelected(true);
				cbxIdentityPrinciple.setSelected(true);
				cbxRelatorConstraint.setSelected(true);
				cbxWeakSupplementation.setSelected(true);				
			}
		});
			
		btnEnableall.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cbxAntirigidity.setSelected(false);
				cbxIdentityPrinciple.setSelected(false);
				cbxRelatorConstraint.setSelected(false);
				cbxWeakSupplementation.setSelected(false);		
			}
		});
		
		cbxRelatorConstraint = new JCheckBox("Relator Constraint");
		cbxRelatorConstraint.setBackground(UIManager.getColor("Panel.background"));
		cbxWeakSupplementation = new JCheckBox("Weak Supplementation");
		cbxWeakSupplementation.setBackground(UIManager.getColor("Panel.background"));
		cbxIdentityPrinciple = new JCheckBox("Identity Principle");
		cbxIdentityPrinciple.setBackground(UIManager.getColor("Panel.background"));
		cbxAntirigidity = new JCheckBox("Antirigidity");
		cbxAntirigidity.setBackground(UIManager.getColor("Panel.background"));
		cbxOpenAnalyzer = new JCheckBox("Open With Analyzer");
		cbxOpenAnalyzer.setBackground(UIManager.getColor("Panel.background"));
		cbxOpenAnalyzer.setEnabled(false);
		cbxOpenAnalyzer.setSelected(true);
		
		GroupLayout gl_cbxPanel = new GroupLayout(cbxPanel);
		gl_cbxPanel.setHorizontalGroup(
			gl_cbxPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cbxPanel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cbxOpenAnalyzer, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxAntirigidity, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxIdentityPrinciple, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxWeakSupplementation, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
						.addComponent(cbxRelatorConstraint, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_cbxPanel.setVerticalGroup(
			gl_cbxPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cbxPanel.createSequentialGroup()
					.addGap(9)
					.addComponent(cbxRelatorConstraint)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxWeakSupplementation)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxIdentityPrinciple)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxAntirigidity)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxOpenAnalyzer)
					.addGap(18))
		);
		
		cbxPanel.setLayout(gl_cbxPanel);
		setLayout(new BorderLayout(0, 0));
		add(titlePanel, BorderLayout.NORTH);
		
		JLabel lblModel = new JLabel("Model Options : ");
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		GroupLayout gl_titlePanel = new GroupLayout(titlePanel);
		gl_titlePanel.setHorizontalGroup(
			gl_titlePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titlePanel.createSequentialGroup()
					.addGap(26)
					.addComponent(lblModel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEnforceAxioms)
					.addContainerGap(443, Short.MAX_VALUE))
		);
		gl_titlePanel.setVerticalGroup(
			gl_titlePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_titlePanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_titlePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModel)
						.addComponent(lblEnforceAxioms))
					.addContainerGap())
		);
		titlePanel.setLayout(gl_titlePanel);
		add(scrollPane);
		add(btnPanel, BorderLayout.SOUTH);
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
	 * Is Selected Open Analyzer Option.
	 * 
	 * @return
	 */
	public boolean isSelectedOpenAnalyzer() { return cbxOpenAnalyzer.isSelected(); }
	
	/**
	 * Get the main frame application.
	 * 
	 * @return
	 */
	public TheFrame getTheFrame() { return frame; }
}
