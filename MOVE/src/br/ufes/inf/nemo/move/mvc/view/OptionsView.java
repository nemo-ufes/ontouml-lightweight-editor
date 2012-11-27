package br.ufes.inf.nemo.move.mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.move.mvc.model.OptionsModel;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.util.SingleConstraintPanel;

/**
 * @author John Guerson
 */

public class OptionsView extends JDialog {
	
	private static final long serialVersionUID = 7877781445149017806L;
	
	@SuppressWarnings("unused")
	private OptionsModel optModel;
		
	private TheFrame frame;
	private JCheckBox cbxRelatorConstraint;
	private JCheckBox cbxWeakSupplementation ;	
	private JCheckBox cbxIdentityPrinciple ;
	private JCheckBox cbxAntirigidity;
	private JCheckBox cbxOpenAnalyzer;	
	private JButton btnOk;	
	private JButton btnCancel;
	private JPanel optpanel;
	private JPanel oclOptPanel;
	private JPanel ctpanel;
	private JScrollPane scrollPane;	
	private ArrayList<SingleConstraintPanel> singleConstraintsListPanel;
	
	/**
	 * Constructor.
	 * 
	 * @param optModel
	 */
	public OptionsView(OptionsModel optModel, TheFrame frame)
	{
		this();
		
		this.optModel = optModel;
		this.frame = frame;
		
		setLocationRelativeTo(frame);			
		setOptionView(optModel);
	}

	/**
	 * Set Option Model.
	 * 
	 * @param optModel
	 */
	public void setOptionView (OptionsModel optModel)
	{
		this.cbxAntirigidity.setSelected(optModel.getOptions().antiRigidity);
		this.cbxIdentityPrinciple.setSelected(optModel.getOptions().identityPrinciple);
		this.cbxRelatorConstraint.setSelected(optModel.getOptions().relatorConstraint);
		this.cbxWeakSupplementation.setSelected(optModel.getOptions().weakSupplementationConstraint);		
		this.cbxOpenAnalyzer.setSelected(optModel.getOptions().openAnalyzer);
		
		if (optModel.getOCLOptions().getConstraintList().size()<3)
			ctpanel.setLayout(new GridLayout(3, 1, 0, 0));
		else
			ctpanel.setLayout(new GridLayout(optModel.getOCLOptions().getConstraintList().size(), 1, 0, 0));
		
		for(Constraint ct : optModel.getOCLOptions().getConstraintList())
		{
			SingleConstraintPanel singleConstraint = new SingleConstraintPanel();
			
			singleConstraint.txtConstraintName.setText(ct.getName());
			singleConstraint.txtConstraintType.setText(optModel.getOCLOptions().getConstraintType(ct));	
			
			singleConstraintsListPanel.add(singleConstraint);
		}
	}

	/**
	 * Constructor
	 */
	public OptionsView() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(OptionsView.class.getResource("/resources/br/ufes/inf/nemo/move/options.png")));
		setPreferredSize(new Dimension(323,300));
		setSize(new Dimension(689, 352));				
		setTitle("Options");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		optpanel = new JPanel();
		optpanel.setBorder(null);
		optpanel.setPreferredSize(new Dimension(200, 100));		
		getContentPane().add(optpanel, BorderLayout.WEST);
		
		cbxRelatorConstraint = new JCheckBox("Relator Constraint");		
		cbxWeakSupplementation = new JCheckBox("Weak Supplementation");		
		cbxIdentityPrinciple = new JCheckBox("Identity Principle");		
		cbxAntirigidity = new JCheckBox("Antirigidity");		
		cbxOpenAnalyzer = new JCheckBox("Open With Analyzer");
		cbxOpenAnalyzer.setEnabled(false);
		cbxOpenAnalyzer.setSelected(true);
		
		JLabel lblEnforceAxioms = new JLabel("OntoUML Axioms");
		lblEnforceAxioms.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnforceAxioms.setFont(new Font("Tahoma", Font.BOLD, 11));
								
		JLabel lblDomainConstraintsOptions = new JLabel("OCL Domain Constraints");
		lblDomainConstraintsOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblDomainConstraintsOptions.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		GroupLayout gl_optpanel = new GroupLayout(optpanel);
		gl_optpanel.setHorizontalGroup(
			gl_optpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optpanel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_optpanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEnforceAxioms, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addComponent(cbxRelatorConstraint, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addComponent(cbxWeakSupplementation, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addComponent(cbxIdentityPrinciple, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addComponent(cbxAntirigidity, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addComponent(cbxOpenAnalyzer, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
					.addGap(21))
		);
		gl_optpanel.setVerticalGroup(
			gl_optpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optpanel.createSequentialGroup()
					.addGap(31)
					.addComponent(lblEnforceAxioms)
					.addGap(18)
					.addComponent(cbxRelatorConstraint)
					.addGap(18)
					.addComponent(cbxWeakSupplementation)
					.addGap(18)
					.addComponent(cbxIdentityPrinciple)
					.addGap(18)
					.addComponent(cbxAntirigidity)
					.addGap(18)
					.addComponent(cbxOpenAnalyzer)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		optpanel.setLayout(gl_optpanel);
		
		JPanel btnpanel = new JPanel();
		getContentPane().add(btnpanel, BorderLayout.SOUTH);
		
		btnOk = new JButton("OK");		
		btnCancel = new JButton("Cancel");
		
		GroupLayout gl_btnpanel = new GroupLayout(btnpanel);
		gl_btnpanel.setHorizontalGroup(
			gl_btnpanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_btnpanel.createSequentialGroup()
					.addGap(93)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(83, Short.MAX_VALUE))
		);
		gl_btnpanel.setVerticalGroup(
			gl_btnpanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_btnpanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_btnpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		btnpanel.setLayout(gl_btnpanel);
		
		oclOptPanel = new JPanel();
		oclOptPanel.setBorder(null);
				
		ctpanel = new JPanel();
		ctpanel.setPreferredSize(new Dimension(412,3*72));

		scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollPane.setViewportView(ctpanel);
		ctpanel.setLayout(new GridLayout(3, 0, 0, 0));
				
		singleConstraintsListPanel = new ArrayList<SingleConstraintPanel>();
				
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		GroupLayout gl_oclOptPanel = new GroupLayout(oclOptPanel);
		gl_oclOptPanel.setHorizontalGroup(
			gl_oclOptPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_oclOptPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_oclOptPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
						.addComponent(lblDomainConstraintsOptions, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_oclOptPanel.setVerticalGroup(
			gl_oclOptPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_oclOptPanel.createSequentialGroup()
					.addGap(24)
					.addComponent(lblDomainConstraintsOptions)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
					.addContainerGap())
		);
		oclOptPanel.setLayout(gl_oclOptPanel);
				
		getContentPane().add(oclOptPanel, BorderLayout.CENTER);
	}
	
	public void addOKActionListener(ActionListener actionListener) 
	{
		btnOk.addActionListener(actionListener);
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
