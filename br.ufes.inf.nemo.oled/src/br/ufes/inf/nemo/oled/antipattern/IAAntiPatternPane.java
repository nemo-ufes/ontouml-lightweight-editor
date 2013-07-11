package br.ufes.inf.nemo.oled.antipattern;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import RefOntoUML.Classifier;
import javax.swing.ScrollPaneConstants;

import br.ufes.inf.nemo.antipattern.IAAntiPattern;

/**
 * @author John Guerson
 */

public class IAAntiPatternPane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private IAAntiPattern ia;
	
	private Component parent;
	private JTextField textAssociation;	
	private JTextField textTarget;
	private JTextField textSource;
	private JCheckBox cbxSourceCustom;		
	private JCheckBox cbxTargetCustom;
	private HashMap<Classifier,JCheckBox> sourceSubtypesMap;
	private HashMap<Classifier,JCheckBox> targetSubtypesMap;
	private CheckBoxListScrollPane checkListSource;		
	private CheckBoxListScrollPane checkListTarget;
	private JLabel lblImpreciseAbstraction;
	private JButton btnOclSolution;
	private JButton btnExecuteWithAnalyzer;
		
	/**
	 * Constructor.
	 * 
	 * @param ia
	 * @param parent
	 */
	public IAAntiPatternPane(IAAntiPattern ia,Component parent)
	{
		this();
		
		this.ia = ia;
		this.parent = parent;
		
		textAssociation.setText(ia.getAssociation().getName());
		textSource.setText(ia.getSource().getName());
		textTarget.setText(ia.getTarget().getName());
		
		if (ia.getSourceChildren().size()==0)
		{
			checkListSource.setEnabled(false);
			cbxSourceCustom.setEnabled(false);
		}else{		
			for(Classifier c: ia.getSourceChildren())
			{
				JCheckBox cbx = new JCheckBox(c.getName());
				cbx.setBackground(Color.WHITE);
				cbx.setHorizontalAlignment(SwingConstants.LEFT);
				sourceSubtypesMap.put(c,cbx);
			}
			checkListSource.setCheckBoxList(sourceSubtypesMap.values());
		}

		if (ia.getTargetChildren().size()==0)
		{
			checkListTarget.setEnabled(false);
			cbxTargetCustom.setEnabled(false);
		}else{				
			for(Classifier c: ia.getTargetChildren())
			{
				JCheckBox cbx = new JCheckBox(c.getName());
				cbx.setBackground(Color.WHITE);
				cbx.setHorizontalAlignment(SwingConstants.LEFT);
				targetSubtypesMap.put(c,cbx);
			}
			checkListTarget.setCheckBoxList(targetSubtypesMap.values());
		}	
		
		lblImpreciseAbstraction.setText("Imprecise Abstraction");
	}	
	
	/**
	 * Constructor.
	 */
	public IAAntiPatternPane() 
	{
		setPreferredSize(new Dimension(390, 449));	
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "", TitledBorder.RIGHT, TitledBorder.BELOW_TOP, null, Color.BLACK));
		
		lblImpreciseAbstraction = new JLabel("Imprecise Abstraction");
		lblImpreciseAbstraction.setHorizontalAlignment(SwingConstants.CENTER);
		
		sourceSubtypesMap = new HashMap<Classifier,JCheckBox>();
		targetSubtypesMap = new HashMap<Classifier,JCheckBox>();
		
		JPanel textPanel = new JPanel();		
		JPanel cbxPanel = new JPanel();		
		JPanel btnPanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblImpreciseAbstraction, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
							.addGap(17))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
								.addComponent(cbxPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
								.addComponent(textPanel, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
							.addGap(17))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblImpreciseAbstraction)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPanel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxPanel, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		
		btnExecuteWithAnalyzer = new JButton("Execute With Analyzer");
		btnPanel.add(btnExecuteWithAnalyzer);
		
		btnOclSolution = new JButton("OCL Solution");
		btnPanel.add(btnOclSolution);
		
		cbxSourceCustom = new JCheckBox("Source Custom");		
		cbxTargetCustom = new JCheckBox("Target Custom");
		
		checkListSource = new CheckBoxListScrollPane();		
		checkListSource.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		checkListTarget = new CheckBoxListScrollPane();
		checkListTarget.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		GroupLayout gl_cbxPanel = new GroupLayout(cbxPanel);
		gl_cbxPanel.setHorizontalGroup(
			gl_cbxPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cbxPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cbxSourceCustom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(checkListSource, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(cbxTargetCustom, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkListTarget, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_cbxPanel.setVerticalGroup(
			gl_cbxPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cbxPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxSourceCustom)
						.addComponent(cbxTargetCustom))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_cbxPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(checkListTarget, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkListSource, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		cbxPanel.setLayout(gl_cbxPanel);
		
		JLabel lblAssociation = new JLabel("Association:");
		
		textAssociation = new JTextField();
		textAssociation.setBackground(Color.WHITE);
		textAssociation.setEditable(false);
		textAssociation.setColumns(10);
		
		JLabel lblSource = new JLabel("Source:");
		
		textSource = new JTextField();
		textSource.setBackground(Color.WHITE);
		textSource.setEditable(false);
		textSource.setColumns(10);
		
		JLabel lblTarget = new JLabel("Target:");
		
		textTarget = new JTextField();
		textTarget.setBackground(Color.WHITE);
		textTarget.setEditable(false);
		textTarget.setColumns(10);
		
		GroupLayout gl_textPanel = new GroupLayout(textPanel);
		gl_textPanel.setHorizontalGroup(
			gl_textPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_textPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_textPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblTarget, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblSource, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblAssociation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_textPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textSource, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
						.addComponent(textAssociation, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
						.addComponent(textTarget, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_textPanel.setVerticalGroup(
			gl_textPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_textPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_textPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAssociation)
						.addComponent(textAssociation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_textPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSource)
						.addComponent(textSource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_textPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTarget)
						.addComponent(textTarget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		textPanel.setLayout(gl_textPanel);
		setLayout(groupLayout);
	}
		
	public ArrayList<Classifier> getSourceCustomClassifiers()		 
	{
		ArrayList<Classifier> srcClassifiers = new ArrayList<Classifier>(); 
		
		if (!cbxSourceCustom.isSelected() || !cbxSourceCustom.isEnabled()) return srcClassifiers;
			
	    for (Entry<Classifier,JCheckBox> entry : sourceSubtypesMap.entrySet()) 
	    {
	        if (((JCheckBox)entry.getValue()).isSelected()) 
	        {
	            srcClassifiers.add(entry.getKey());
	        }
	    }
	    
	    return srcClassifiers;
	}
	
	public ArrayList<Classifier> getTargetCustomClassifiers()		 
	{
		ArrayList<Classifier> tgtClassifiers = new ArrayList<Classifier>(); 
		
		if (!cbxTargetCustom.isSelected() || !cbxTargetCustom.isEnabled()) return tgtClassifiers;
			
	    for (Entry<Classifier,JCheckBox> entry : targetSubtypesMap.entrySet()) 
	    {
	        if (((JCheckBox)entry.getValue()).isSelected()) 
	        {
	            tgtClassifiers.add(entry.getKey());
	        }
	    }
	    
	    return tgtClassifiers;
	}
	
	public Component getTheFrame()
	{ 
		return parent;
	}
	
	public boolean isSelectedSourceCustom()
	{
		return cbxSourceCustom.isSelected();
	}
	
	public boolean isSelectedTargetCustom()
	{
		return cbxTargetCustom.isSelected();
	}

	public void addExecuteWithAnalzyerListener(ActionListener actionListener) 
	{
		btnExecuteWithAnalyzer.addActionListener(actionListener);
	}

	public void addOCLSolutionListener(ActionListener actionListener) 
	{
		btnOclSolution.addActionListener(actionListener);
	}
	
}
