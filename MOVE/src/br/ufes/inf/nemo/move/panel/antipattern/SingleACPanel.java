package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.ontouml.antipattern.ACAntiPattern;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.FlowLayout;

/**
 * @author John Guerson
 */

public class SingleACPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextPane txtClassCycle;	
	private JCheckBox cbxOpenCycle;	
	private JCheckBox cbxClosedCycle;	
	private JSpinner spinScope;	
	private JButton btnGenerateAlloy;	
	private JButton btnOclSolution;		
	private ACAntiPattern ac;		
	private TheFrame frame;
	
	/**
	 * Create a Single AC AntiPattern Panel.
	 * 
	 * @param rbos
	 */
	public SingleACPanel(ACAntiPattern ac, TheFrame frame)
	{
		this();
		
		this.ac = ac;
		this.frame = frame;
		
		int i=1;
		String resultBuffer= new String();
		for (RefOntoUML.Class c : ac.getCycle())
		{			
			if (i != ac.getCycle().size()) resultBuffer += c.getName()+"->";
			else resultBuffer += c.getName();
			i++;
		}		
		txtClassCycle.setText(resultBuffer);		
	}	
	
	/**
	 * Set the title of the Line Border.
	 * 
	 * @param title
	 */
	public void setTitleBorder (String title)
	{
		((TitledBorder)getBorder()).setTitle(title);	
	}	
	
	/**
	 * Create the AC AntiPattern Panel.
	 */
	public SingleACPanel() 
	{
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Association Cycle 1", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(255, 0, 0)));
		setPreferredSize(new Dimension(330, 276));

		txtClassCycle = new JTextPane();
		txtClassCycle.setEditable(false);
		txtClassCycle.setBorder(new LineBorder(Color.GRAY, 1, true));
		
		JLabel lblClassCycle = new JLabel("Class Cycle");
		lblClassCycle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel cbxPanel = new JPanel();		
		JPanel btnPanel = new JPanel();		
		JPanel btnScope = new JPanel();
		btnScope.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblEscope = new JLabel("Escope :");
		btnScope.add(lblEscope);
		
		spinScope = new JSpinner();
		spinScope.setModel(new SpinnerNumberModel(new Integer(2), new Integer(0), null, new Integer(1)));
		spinScope.setPreferredSize(new Dimension(60, 20));
		btnScope.add(spinScope);
		
		cbxOpenCycle = new JCheckBox("Open Cycle");		
		cbxPanel.add(cbxOpenCycle);
		
		cbxClosedCycle = new JCheckBox("Closed Cycle");
		cbxPanel.add(cbxClosedCycle);
		
		btnGenerateAlloy = new JButton("Generate Alloy");
		btnGenerateAlloy.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{       			
       			GenerateAlloyActionPerformed(event);
       		}
       	});
		btnPanel.add(btnGenerateAlloy);
		
		btnOclSolution = new JButton("Generate OCL Solution");
		btnOclSolution.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			GenerateOCLSolutionActionPerformed(event);
       		}
       	});
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
						.addComponent(btnScope, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
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
					.addComponent(btnScope, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(77))
		);							
				
		setLayout(groupLayout);
	}
	
	/**
	 * Generate Alloy.
	 * 
	 * @param event
	 */
	public void GenerateAlloyActionPerformed(ActionEvent event)
	{
		Integer cardinality = (Integer)spinScope.getModel().getValue();       			
		Boolean openCycle = cbxOpenCycle.isSelected();
		Boolean closedCycle = cbxClosedCycle.isSelected();
		String openCyclePred = new String();
		String closedCyclePred = new String();
			
		if(openCycle) 
		{
			openCyclePred = ac.generateOpenCyclePredicate(frame.getTheModelsPanel().getOntoUMLParser(),cardinality);
		}
		if(closedCycle) 
		{
			closedCyclePred = ac.generateClosedCyclePredicate(frame.getTheModelsPanel().getOntoUMLParser(), cardinality); 
		}
		
		frame.getTheConsolePanel().write(openCyclePred+"\n\n"+closedCyclePred);
		frame.ShowConsole();
	}
	
	/**
	 * Generate OCL Constraint Solution.
	 * 
	 * @param event
	 */
	public void GenerateOCLSolutionActionPerformed(ActionEvent event)
	{		       			
		Boolean openCycle = cbxOpenCycle.isSelected();
		Boolean closedCycle = cbxClosedCycle.isSelected();
		String openCycleConstraint = new String();
		String closedCycleConstraint = new String();
			
		if(openCycle) 
		{
			openCycleConstraint = ac.generateCycleOcl(ACAntiPattern.OPEN);
		}
		if(closedCycle) 
		{
			closedCycleConstraint = ac.generateCycleOcl(ACAntiPattern.CLOSED); 
		}
			
		frame.getTheConsolePanel().write(openCycleConstraint+"\n\n"+closedCycleConstraint);
		frame.ShowConsole();
	}
	
}
