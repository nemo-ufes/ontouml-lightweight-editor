package br.ufes.inf.nemo.move.mvc.view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.ufes.inf.nemo.move.mvc.model.OntoUMLOptionsModel;
import br.ufes.inf.nemo.move.ui.TheFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
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
		
	/**
	 * COnstructor.
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
	 * Set OntoUML Options View from Model.
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
	}

	/**
	 * Constructor
	 */
	public OntoUMLOptionsView() 
	{
		setPreferredSize(new Dimension(215, 169));
		setSize(new Dimension(689, 352));				
	
		cbxRelatorConstraint = new JCheckBox("Relator Constraint");		
		cbxWeakSupplementation = new JCheckBox("Weak Supplementation");		
		cbxIdentityPrinciple = new JCheckBox("Identity Principle");		
		cbxAntirigidity = new JCheckBox("Antirigidity");		
		cbxOpenAnalyzer = new JCheckBox("Open With Analyzer");
		cbxOpenAnalyzer.setEnabled(false);
		cbxOpenAnalyzer.setSelected(true);
		
		JLabel lblEnforceAxioms = new JLabel("Enforce Axioms:");
		lblEnforceAxioms.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnforceAxioms.setFont(new Font("Tahoma", Font.PLAIN, 11));
								
		JLabel lblDomainConstraintsOptions = new JLabel("OCL Domain Constraints");
		lblDomainConstraintsOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblDomainConstraintsOptions.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		GroupLayout gl_optpanel = new GroupLayout(this);
		gl_optpanel.setHorizontalGroup(
			gl_optpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optpanel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_optpanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxAntirigidity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxIdentityPrinciple, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addComponent(cbxWeakSupplementation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxRelatorConstraint, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
						.addComponent(lblEnforceAxioms, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxOpenAnalyzer, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_optpanel.setVerticalGroup(
			gl_optpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optpanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEnforceAxioms)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxRelatorConstraint)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxWeakSupplementation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxIdentityPrinciple)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxAntirigidity)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxOpenAnalyzer)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		setLayout(gl_optpanel);	
	}
	
	public boolean isSelectedRelatorConstraint()
	{
		return cbxRelatorConstraint.isSelected();
	}
	
	public boolean isSelectedWeakSupplementation()
	{
		return cbxWeakSupplementation.isSelected();
	}
	
	public boolean isSelectedAntirigidity()
	{
		return cbxAntirigidity.isSelected();
	}
	
	public boolean isSelectedIdentityPrinciple()
	{
		return cbxIdentityPrinciple.isSelected();
	}
	
	public boolean isSelectedOpenAnalyzer()
	{
		return cbxOpenAnalyzer.isSelected();
	}
	
	public TheFrame getTheFrame()
	{
		return frame;
	}
}
