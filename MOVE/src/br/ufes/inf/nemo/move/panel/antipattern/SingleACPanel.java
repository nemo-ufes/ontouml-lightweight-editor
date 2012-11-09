package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.ui.TheModelBar;
import br.ufes.inf.nemo.move.util.AlloyJARExtractor;
import br.ufes.inf.nemo.ontouml.antipattern.ACAntiPattern;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

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
	private JLabel lblScope;
	private JLabel lblClassCycle;
	
	private ACAntiPattern ac;		
	private TheFrame frame;
	private Integer uniqueId = 0;
		
	/**
	 * Create a Single AC AntiPattern Panel.
	 * 
	 * @param rbos
	 */
	public SingleACPanel(ACAntiPattern ac, TheFrame frame, Integer uniqueId)
	{
		this();
		
		this.ac = ac;
		this.frame = frame;
		this.uniqueId = uniqueId;
		
		int i=1;
		String resultBuffer= new String();
		for (RefOntoUML.Class c : ac.getCycle())
		{			
			if (i != ac.getCycle().size()) resultBuffer += c.getName()+"->";			
			if(i==ac.getCycle().size()) resultBuffer += ac.getCycle().get(0).getName();
			i++;
		}
		txtClassCycle.setText(resultBuffer);
		
		lblClassCycle.setText("Class Cycle "+uniqueId);
		
		lblScope.setText("\""+ac.getCycle().get(0).getName()+"\""+" Scope:");
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
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "", TitledBorder.RIGHT, TitledBorder.BELOW_TOP, null, new Color(255, 0, 0)));
		setPreferredSize(new Dimension(330, 246));

		txtClassCycle = new JTextPane();
		txtClassCycle.setEditable(false);
		txtClassCycle.setBorder(new LineBorder(Color.GRAY, 1, true));
		
		lblClassCycle = new JLabel("Class Cycle "+uniqueId);
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
		btnGenerateAlloy.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{       			
       			try{
       				
       				GenerateAlloyActionPerformed(event);
       			
       			} catch (Exception e) {
       				JOptionPane.showMessageDialog(SingleACPanel.this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
       				e.printStackTrace();
       			}       			
       		}
       	});
		btnPanel.add(btnGenerateAlloy);
		
		btnOclSolution = new JButton("OCL Solution");
		btnOclSolution.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			/* Generate OCL */
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
	
	/**
	 * Generate Alloy.
	 * 
	 * @param event
	 */
	public void GenerateAlloyActionPerformed(ActionEvent event) throws Exception
	{
		Integer cardinality = (Integer)spinScope.getModel().getValue();  
		
		Boolean openCycle = cbxOpenCycle.isSelected();
		Boolean closedCycle = cbxClosedCycle.isSelected();
		
		String openCyclePred = new String();
		String closedCyclePred = new String();
			
		/* generate predicates */
		if(openCycle) openCyclePred = ac.generateOpenCyclePredicate(frame.getTheModelBar().getOntoUMLParser(),cardinality);
		if(closedCycle) closedCyclePred = ac.generateClosedCyclePredicate(frame.getTheModelBar().getOntoUMLParser(), cardinality); 
				
		/* ontouml2alloy */
		Options opt = frame.getTheMenuBar().getOptions();		
		String alsPath = TheModelBar.alsOutDirectory+frame.getTheModelBar().alsmodelName+"$ac"+uniqueId+".als";		
		RefOntoUML.Package refmodel = frame.getTheModelBar().getOntoUMLModel();		
		OntoUML2Alloy.Transformation(refmodel, alsPath, opt);
		
		/* add predicates */
		String predicates = new String();
		if (openCycle) predicates += "\n\n"+openCyclePred;
		if (closedCycle) predicates += "\n\n"+closedCyclePred;
		
		/* include to alloy file */
		FileUtil.writeToFile(predicates, alsPath);
		
		/* open analyzer */
		if (opt.openAnalyzer)
		{
			AlloyJARExtractor.extractAlloyJaRTo("alloy4.2.jar", TheModelBar.alsOutDirectory);
			
			String[] argsAnalyzer = { "","" };
			argsAnalyzer[0] = alsPath;
			argsAnalyzer[1] = TheModelBar.alsOutDirectory + "standart_theme.thm"	;	
			SimpleGUICustom.main(argsAnalyzer);
		}
		
		frame.getTheConsolePanel().write(openCyclePred+"\n\n"+closedCyclePred);		
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
			
		if(openCycle) openCycleConstraint = ac.generateCycleOcl(ACAntiPattern.OPEN);		
		if(closedCycle) closedCycleConstraint = ac.generateCycleOcl(ACAntiPattern.CLOSED);		
			
		frame.getTheConsolePanel().write(openCycleConstraint+"\n\n"+closedCycleConstraint);
		frame.ShowConsole();
	}
}
