package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.ocl.ocl2alloy.OCL2AlloyOptions;
import br.ufes.inf.nemo.oled.AppFrame;

/**
 * 
 * This class is a View for OCL2Alloy Options.
 * 
 * @author John Guerson
 */

public class OCL2AlloyOptionsPane extends JPanel {

	private static final long serialVersionUID = 566520388850119106L;

	@SuppressWarnings("unused")
	private OCL2AlloyOptions oclOptions;
	
	private AppFrame frame;	
	
	private JPanel ctpanel;
	private JScrollPane scrollPane; 
	private ArrayList<SingleConstraintPane> singleConstraintsListPanel;
	private JButton btnDisableAll;
	private JButton btnEnableAll;
	private JLabel lblChooseWhichConstraints;

	public OCL2AlloyOptionsPane (OCL2AlloyOptions oclOptions,AppFrame frame)
	{		
		this();
	
		this.frame = frame;
		this.oclOptions = oclOptions;
		
		setOCLOptionPane(oclOptions);
	}
	
	public void setOCLOptionPane (OCL2AlloyOptions oclOptions)
	{
		ctpanel.removeAll();
		this.oclOptions = oclOptions;
		
		if (oclOptions.getConstraintList().size()<4)
		{
			ctpanel.setLayout(new GridLayout(4, 1, 0, 0));			
		} else {
			ctpanel.setLayout(new GridLayout(oclOptions.getConstraintList().size(), 1, 0, 0));			
		}
		
		for(Constraint ct : oclOptions.getConstraintList())
		{
			SingleConstraintPane singleConstraint = new SingleConstraintPane(ct,oclOptions.getConstraintType(ct));
						
			singleConstraintsListPanel.add(singleConstraint);
			ctpanel.add(singleConstraint);
		}		
		validate();
		repaint();
	}
		
	/**
	 * Get a list of Scopes from Options View.
	 * 
	 * @return
	 */
	public ArrayList<Integer> getScopesListSelected()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(SingleConstraintPane singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected())
				list.add((Integer)singleCt.spinCommandScope.getValue());
		}
		return list;
	}
	
	/**
	 * Get a list of Transformation Types from Options Pane. i.e. if it is "RESTRICT","SIMULATE" OR "CHECK"
	 * 
	 * @return
	 */
	public ArrayList<String> getTransformationsTypesListSelected()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(SingleConstraintPane singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected())
				list.add((String)singleCt.comboTransformationType.getSelectedItem());
		}
		return list;
	}
	
	/**
	 * Get a list of Constraint from Options Pane.
	 * 
	 * @return
	 */
	public ArrayList<Constraint> getConstraintListSelected()
	{
		ArrayList<Constraint> list = new ArrayList<Constraint>();
		for(SingleConstraintPane singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected())
				list.add((Constraint)singleCt.constraint);
		}
		return list;
	}
			
	/**
	 * Create an Empty Pane for OCL Options.
	 */
	public OCL2AlloyOptionsPane() 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setPreferredSize(new Dimension(591,179));
		setLayout(new BorderLayout(0,0));
		
		ctpanel = new JPanel();		
		ctpanel.setLayout(new GridLayout(4, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollPane.setViewportView(ctpanel);
		scrollPane.setPreferredSize(new Dimension(591,179));
				
		singleConstraintsListPanel = new ArrayList<SingleConstraintPane>();
		
		JPanel btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(591, 40));
		
		JPanel introPanel = new JPanel();
		introPanel.setBackground(UIManager.getColor("Panel.background"));
		introPanel.setPreferredSize(new Dimension(591, 30));
		
		add(introPanel,BorderLayout.NORTH);
		
		lblChooseWhichConstraints = new JLabel("Choose which constraints do you want to record as facts, assertions or simulations...");
		GroupLayout gl_introPanel = new GroupLayout(introPanel);
		gl_introPanel.setHorizontalGroup(
			gl_introPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_introPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChooseWhichConstraints, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_introPanel.setVerticalGroup(
			gl_introPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_introPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChooseWhichConstraints)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		introPanel.setLayout(gl_introPanel);
		add(scrollPane,BorderLayout.CENTER);
		add(btnPanel,BorderLayout.SOUTH);
		
		btnEnableAll = new JButton("Enable All");	
		btnEnableAll.setPreferredSize(new Dimension(100, 25));
		btnDisableAll = new JButton("Disable All");
		btnDisableAll.setPreferredSize(new Dimension(100, 25));
		
		btnEnableAll.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				for(SingleConstraintPane singleCt : singleConstraintsListPanel)
				{
					singleCt.checkEnforce.setSelected(true);
				}
			}
		});
		
		
		btnDisableAll.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				for(SingleConstraintPane singleCt : singleConstraintsListPanel)
				{
					singleCt.checkEnforce.setSelected(false);
				}
			}
		});
		GroupLayout gl_btnPanel = new GroupLayout(btnPanel);
		gl_btnPanel.setHorizontalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addGap(19)
					.addComponent(btnEnableAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDisableAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(366))
		);
		gl_btnPanel.setVerticalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_btnPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEnableAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDisableAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		btnPanel.setLayout(gl_btnPanel);
	}			
	
	/**
	 * Get the main Frame of Application.
	 * 
	 * @return
	 */
	public AppFrame getFrame()
	{
		return frame;
	}

}
