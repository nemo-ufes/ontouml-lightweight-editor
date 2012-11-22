package br.ufes.inf.nemo.move.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.ufes.inf.nemo.move.mvc.model.OptionsModel;
import br.ufes.inf.nemo.move.ui.TheFrame;

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
	private JPanel optpanel;
	private JPanel centerpanel;
	
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
	}

	/**
	 * Constructor
	 */
	public OptionsView() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(OptionsView.class.getResource("/resources/br/ufes/inf/nemo/move/options.png")));
		setPreferredSize(new Dimension(323,300));
		setSize(new Dimension(386, 321));
		
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
		
		JLabel lblEnforceAxioms = new JLabel("Enforce Axioms");
		lblEnforceAxioms.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnforceAxioms.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		cbxOpenAnalyzer = new JCheckBox("Open With Analyzer");
		cbxOpenAnalyzer.setEnabled(false);
		cbxOpenAnalyzer.setSelected(true);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		GroupLayout gl_optpanel = new GroupLayout(optpanel);
		gl_optpanel.setHorizontalGroup(
			gl_optpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optpanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_optpanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cbxOpenAnalyzer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxRelatorConstraint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblEnforceAxioms, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(cbxWeakSupplementation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxIdentityPrinciple, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxAntirigidity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		gl_optpanel.setVerticalGroup(
			gl_optpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optpanel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblEnforceAxioms, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(cbxRelatorConstraint)
					.addGap(2)
					.addComponent(cbxWeakSupplementation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxIdentityPrinciple)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxAntirigidity)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxOpenAnalyzer)
					.addContainerGap(21, Short.MAX_VALUE))
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
		);
		optpanel.setLayout(gl_optpanel);
		
		JPanel titlepanel = new JPanel();
		titlepanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		titlepanel.setBackground(Color.WHITE);
		titlepanel.setPreferredSize(new Dimension(200,50));
		
		getContentPane().add(titlepanel, BorderLayout.NORTH);
		GroupLayout gl_titlepanel = new GroupLayout(titlepanel);
		gl_titlepanel.setHorizontalGroup(
			gl_titlepanel.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 370, Short.MAX_VALUE)
		);
		gl_titlepanel.setVerticalGroup(
			gl_titlepanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 50, Short.MAX_VALUE)
		);
		titlepanel.setLayout(gl_titlepanel);
		
		JPanel btnpanel = new JPanel();
		getContentPane().add(btnpanel, BorderLayout.SOUTH);
		
		btnOk = new JButton("OK");
		
		JButton btnCancel = new JButton("Cancel");
		GroupLayout gl_btnpanel = new GroupLayout(btnpanel);
		gl_btnpanel.setHorizontalGroup(
			gl_btnpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_btnpanel.createSequentialGroup()
					.addContainerGap(93, Short.MAX_VALUE)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(83))
		);
		gl_btnpanel.setVerticalGroup(
			gl_btnpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_btnpanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_btnpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		btnpanel.setLayout(gl_btnpanel);
		
		centerpanel = new JPanel();
		centerpanel.setBorder(null);
		getContentPane().add(centerpanel, BorderLayout.CENTER);
		GroupLayout gl_centerpanel = new GroupLayout(centerpanel);
		gl_centerpanel.setHorizontalGroup(
			gl_centerpanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 170, Short.MAX_VALUE)
		);
		gl_centerpanel.setVerticalGroup(
			gl_centerpanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 188, Short.MAX_VALUE)
		);
		centerpanel.setLayout(gl_centerpanel);
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
