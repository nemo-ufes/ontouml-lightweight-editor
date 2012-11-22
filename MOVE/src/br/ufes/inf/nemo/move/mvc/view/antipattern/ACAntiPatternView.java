package br.ufes.inf.nemo.move.mvc.view.antipattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.move.mvc.model.antipattern.ACAntiPatternModel;
import br.ufes.inf.nemo.move.ui.TheFrame;

/**
 * @author John Guerson
 */

public class ACAntiPatternView extends JPanel {

	private static final long serialVersionUID = 397547249389577827L;
	
	@SuppressWarnings("unused")
	private ACAntiPatternModel acModel;	
	
	private TheFrame frame;	
	private JTextPane txtClassCycle;	
	private JCheckBox cbxOpenCycle;	
	private JCheckBox cbxClosedCycle;	
	private JSpinner spinScope;	
	private JButton btnGenerateAlloy;	
	private JButton btnOclSolution;
	private JLabel lblScope;
	private JLabel lblClassCycle;	
			
	/**
	 * Constructor.
	 * 
	 * @param acModel
	 * @param frame
	 */
	public ACAntiPatternView (ACAntiPatternModel acModel, TheFrame frame)
	{
		this();
		
		this.acModel = acModel;
		this.frame = frame; 
		
		int i=1;
		String resultBuffer= new String();
		resultBuffer += "  ";
		for (RefOntoUML.Class c : acModel.getACAntiPattern().getCycle())
		{			
			if (i != acModel.getACAntiPattern().getCycle().size()) resultBuffer += c.getName()+"->";			
			if(i==acModel.getACAntiPattern().getCycle().size()) resultBuffer += acModel.getACAntiPattern().getCycle().get(0).getName();
			i++;
		}
		txtClassCycle.setText(resultBuffer);
			
		lblClassCycle.setText("Class Cycle "+acModel.getId());
			
		lblScope.setText("\""+acModel.getACAntiPattern().getCycle().get(0).getName()+"\""+" Scope:");
	}		
	
	/**
	 * Constructor.
	 */
	public ACAntiPatternView() 
	{
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "", TitledBorder.RIGHT, TitledBorder.BELOW_TOP, null, new Color(255, 0, 0)));
		setPreferredSize(new Dimension(330, 246));
		txtClassCycle = new JTextPane();
		txtClassCycle.setEditable(false);
		txtClassCycle.setBorder(new LineBorder(new Color(128, 128, 128)));
		
		lblClassCycle = new JLabel("Class Cycle ");
		lblClassCycle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel cbxPanel = new JPanel();		
		JPanel btnPanel = new JPanel();		
		JPanel scopePanel = new JPanel();
		scopePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblScope = new JLabel("Scope :");
		scopePanel.add(lblScope);
		
		spinScope = new JSpinner();
		spinScope.setModel(new SpinnerNumberModel(new Integer(2), new Integer(2), null, new Integer(1)));
		spinScope.setPreferredSize(new Dimension(60, 20));
		scopePanel.add(spinScope);
		
		JLabel lblGenerate = new JLabel("Generate Predicate:");
		cbxPanel.add(lblGenerate);
		
		cbxOpenCycle = new JCheckBox("Open Cycle");		
		cbxPanel.add(cbxOpenCycle);
		
		cbxClosedCycle = new JCheckBox("Closed Cycle");
		cbxPanel.add(cbxClosedCycle);
		
		btnGenerateAlloy = new JButton("Execute With Analyzer");
		btnPanel.add(btnGenerateAlloy);
		
		btnOclSolution = new JButton("OCL Solution");
		btnPanel.add(btnOclSolution);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addComponent(btnPanel, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scopePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
						.addComponent(lblClassCycle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
						.addComponent(txtClassCycle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
						.addComponent(cbxPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
					.addGap(13))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(lblClassCycle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtClassCycle, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scopePanel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(77))
		);							
		
		JLabel lblatLeast = new JLabel("(at least)");
		scopePanel.add(lblatLeast);
				
		setLayout(groupLayout);
	}
	
	public boolean isSelectedOpenCycle()
	{
		return cbxOpenCycle.isSelected();
	}
	
	public boolean isSelectedClosedCycle()
	{
		return cbxClosedCycle.isSelected();
	}
	
	public Integer getScope()
	{
		return (Integer)spinScope.getModel().getValue();
	}

	public TheFrame getTheFrame()
	{
		return frame;
	}
	
	public void addExecuteWithAnalzyerListener(ActionListener actionListener) 
	{
		btnGenerateAlloy.addActionListener(actionListener);
	}

	public void addOCLSolutionListener(ActionListener actionListener) 
	{
		btnOclSolution.addActionListener(actionListener);
	}
	
	/*
	public void GenerateOCLSolutionActionPerformed(ActionEvent event)
	{		
		Boolean openCycle = cbxOpenCycle.isSelected();
		Boolean closedCycle = cbxClosedCycle.isSelected();
		String openCycleConstraint = new String();
		String closedCycleConstraint = new String();
			
		if(openCycle) openCycleConstraint = ac.generateCycleOcl(ACAntiPattern.OPEN);		
		if(closedCycle) closedCycleConstraint = ac.generateCycleOcl(ACAntiPattern.CLOSED);		
			
		frame.getConsole().write(openCycleConstraint+"\n\n"+closedCycleConstraint);
		frame.ShowConsole();		
	}*/
}

