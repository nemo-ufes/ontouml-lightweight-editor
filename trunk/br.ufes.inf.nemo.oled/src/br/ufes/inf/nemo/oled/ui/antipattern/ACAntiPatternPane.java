package br.ufes.inf.nemo.oled.ui.antipattern;

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

import br.ufes.inf.nemo.antipattern.ac.ACAntiPattern;
import br.ufes.inf.nemo.oled.ui.AppFrame;

/**
 * @author John Guerson
 */

public class ACAntiPatternPane extends JPanel {

	private static final long serialVersionUID = 397547249389577827L;
	
	@SuppressWarnings("unused")
	private ACAntiPattern ac;	
	
	private AppFrame parent;	
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
	 * @param ac
	 * @param parent
	 */
	public ACAntiPatternPane (ACAntiPattern ac, AppFrame parent)
	{
		this();
		
		this.ac = ac;
		this.parent = parent; 
		
		int i=0;
		String resultBuffer= new String();
		resultBuffer += "  ";
		for (RefOntoUML.Class c : ac.getCycle())
		{			
			if (i != ac.getCycle().size()) resultBuffer += c.getName()+"->";			
			if(i==ac.getCycle().size()-1) resultBuffer += ac.getCycle().get(0).getName();
			i++;
		}
		txtClassCycle.setText(resultBuffer);
			
		lblClassCycle.setText("Class Cycle");
			
		lblScope.setText("\""+ac.getCycle().get(0).getName()+"\""+" Scope:");
	}		
	
	/**
	 * Constructor.
	 */
	public ACAntiPatternPane() 
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

	public AppFrame getFrame()
	{
		return parent;
	}
	
	public void addExecuteWithAnalzyerListener(ActionListener actionListener) 
	{
		btnGenerateAlloy.addActionListener(actionListener);
	}

	public void addOCLSolutionListener(ActionListener actionListener) 
	{
		btnOclSolution.addActionListener(actionListener);
	}	
	
}

