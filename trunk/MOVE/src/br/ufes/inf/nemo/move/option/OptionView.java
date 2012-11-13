package br.ufes.inf.nemo.move.option;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.ufes.inf.nemo.move.ui.TheFrame;

/**
 * @author John Guerson
 */

public class OptionView extends JDialog {
	
	private static final long serialVersionUID = 7877781445149017806L;
	
	@SuppressWarnings("unused")
	private OptionModel optModel;
		
	private TheFrame frame;
	private JCheckBox cbxRelatorConstraint;
	private JCheckBox cbxWeakSupplementation ;	
	private JCheckBox cbxIdentityPrinciple ;
	private JCheckBox cbxAntirigidity;
	private JCheckBox cbxOpenAnalyzer;
	private JButton btnOk;
	
	/**
	 * Constructor.
	 * 
	 * @param optModel
	 */
	public OptionView(OptionModel optModel, TheFrame frame)
	{
		this();
		
		this.optModel = optModel;
		this.frame = frame;
		
		setLocationRelativeTo(frame);			
		setOptionView(optModel);
	}

	public void setOptionView (OptionModel optModel)
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
	public OptionView() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(OptionView.class.getResource("/resources/br/ufes/inf/nemo/move/options.png")));
		setPreferredSize(new Dimension(323,300));
		setSize(new Dimension(266, 300));
		
		setTitle("Options");
		
		cbxRelatorConstraint = new JCheckBox("Relator Constraint");		
		cbxWeakSupplementation = new JCheckBox("Weak Supplementation");		
		cbxIdentityPrinciple = new JCheckBox("Identity Principle");		
		cbxAntirigidity = new JCheckBox("Antirigidity");		
				
		cbxOpenAnalyzer = new JCheckBox("Open With Analyzer");
		cbxOpenAnalyzer.setEnabled(false);
		cbxOpenAnalyzer.setSelected(true);
		
		JLabel lblEnforceAxioms = new JLabel("Enforce Axioms:");
		
		JSeparator separator = new JSeparator();
		
		btnOk = new JButton("OK");		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(cbxOpenAnalyzer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addComponent(lblEnforceAxioms, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addComponent(cbxAntirigidity, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addComponent(cbxIdentityPrinciple, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addComponent(cbxWeakSupplementation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addComponent(cbxRelatorConstraint, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
					.addGap(33))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(79)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(79, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblEnforceAxioms)
					.addGap(18)
					.addComponent(cbxRelatorConstraint)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxWeakSupplementation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxIdentityPrinciple)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxAntirigidity)
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxOpenAnalyzer)
					.addGap(18)
					.addComponent(btnOk)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
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
