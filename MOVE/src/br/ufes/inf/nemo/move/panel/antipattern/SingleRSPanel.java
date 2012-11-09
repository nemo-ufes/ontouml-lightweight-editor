package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.ui.TheModelBar;
import br.ufes.inf.nemo.move.ui.TheTextField;
import br.ufes.inf.nemo.move.util.AlloyJARExtractor;
import br.ufes.inf.nemo.ontouml.antipattern.RSAntiPattern;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

/**
 * @author John Guerson
 */

public class SingleRSPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField textGeneralSource;
	private JTextField textGeneralTarget;
	private JTextField textGeneralAssociation;	
	private JTextField textSpecificSource;
	private JTextField textSpecificAssociation;
	private JTextField textSpecificTarget;
	
	private JCheckBox cbxSubtype;
	private JCheckBox cbxRedefinition;
	private JCheckBox cbxNonsubsetting; 
	private JCheckBox cbxDisjoint;	
	
	private JButton btnGenerateAlloy;
	private JButton btnGenerateOclSolution;	
	
	private RSAntiPattern rs;
	private TheFrame frame;
	private Integer uniqueId = 0;
	
	private JLabel lblRelationSpecialization;
		
	/**
	 * Create a Single RS AntiPattern Panel.
	 * 
	 * @param rbos
	 */
	public SingleRSPanel(RSAntiPattern rs,TheFrame frame, Integer uniqueId)
	{
		this();
		
		this.rs = rs;
		this.frame = frame;
		this.uniqueId = uniqueId;
		
		textGeneralSource.setText("  "+rs.getGeneralSource().getName());
		textGeneralTarget.setText("  "+rs.getGeneralTarget().getName());
		textGeneralAssociation.setText("  "+rs.getGeneral().getName());
		textSpecificAssociation.setText("  "+rs.getSpecific().getName());
		textSpecificSource.setText("  "+rs.getSpecificSource().getName());
		textSpecificTarget.setText("  "+rs.getSpecificTarget().getName());
		
		lblRelationSpecialization.setText("Relation Specialization "+uniqueId);
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
	 * Create the panel.
	 */
	public SingleRSPanel() 
	{
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "", TitledBorder.RIGHT, TitledBorder.BELOW_TOP, null, new Color(255, 0, 0)));
		setPreferredSize(new Dimension(330, 414));
		
		JPanel btnPanel = new JPanel();	
		
		JPanel cbxPanel1 = new JPanel();	
		JPanel cbxPanel2 = new JPanel();		
		cbxPanel2.setLayout(new GridLayout(2, 2, 0, 0));
		cbxPanel1.setLayout(new GridLayout(0, 1, 0, 0));
		
		cbxRedefinition = new JCheckBox("Redefinition");		
		cbxPanel2.add(cbxRedefinition);		
		cbxRedefinition.setHorizontalAlignment(SwingConstants.LEFT);
		
		cbxNonsubsetting = new JCheckBox("Non-Subsetting");
		cbxPanel2.add(cbxNonsubsetting);		
		cbxNonsubsetting.setHorizontalAlignment(SwingConstants.LEFT);		
		
		cbxSubtype = new JCheckBox("Subsetting");
		cbxPanel1.add(cbxSubtype);
		cbxSubtype.setHorizontalAlignment(SwingConstants.LEFT);
		
		cbxDisjoint = new JCheckBox("Disjointness");
		cbxPanel1.add(cbxDisjoint);
		cbxDisjoint.setHorizontalAlignment(SwingConstants.LEFT);
				
		btnGenerateAlloy = new JButton("Execute With Analyzer");	
		btnGenerateAlloy.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			try{
       				
       				GenerateAlloyActionPerformed(event);
       			
       			} catch (Exception e) {
       				JOptionPane.showMessageDialog(SingleRSPanel.this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
       				e.printStackTrace();
       			};
       		}
       	});		
		btnPanel.add(btnGenerateAlloy);
		
		btnGenerateOclSolution = new JButton("OCL Solution");
		btnGenerateOclSolution.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			try{
       				GenerateOCLSolutionActionPerformed(event);
       				
       			} catch (Exception e) {
       				JOptionPane.showMessageDialog(SingleRSPanel.this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
       				e.printStackTrace();
       			};
       		}
       	});		
		btnPanel.add(btnGenerateOclSolution);
		
		JPanel generalPanel = new JPanel();
		generalPanel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "General", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel specificPanel = new JPanel();
		specificPanel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "Specific", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		
		lblRelationSpecialization = new JLabel("Relation Specialization "+uniqueId);
		lblRelationSpecialization.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPanel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(cbxPanel2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxPanel1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
						.addComponent(generalPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addComponent(lblRelationSpecialization, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addComponent(specificPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRelationSpecialization)
					.addGap(6)
					.addComponent(generalPanel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(specificPanel, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxPanel2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxPanel1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		
		JLabel lblSpecificSource = new JLabel("Source:");		
		JLabel lblSpecificAssociation = new JLabel("Association:");
		JLabel lblSpecificTarget = new JLabel("Target:");
		
		textSpecificSource = new TheTextField();		
		textSpecificAssociation = new TheTextField();		
		textSpecificTarget = new TheTextField();
		
		GroupLayout gl_specificPanel = new GroupLayout(specificPanel);
		gl_specificPanel.setHorizontalGroup(
			gl_specificPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_specificPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_specificPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblSpecificTarget, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblSpecificSource, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblSpecificAssociation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_specificPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textSpecificAssociation, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
						.addComponent(textSpecificSource, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
						.addComponent(textSpecificTarget, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_specificPanel.setVerticalGroup(
			gl_specificPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_specificPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_specificPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSpecificSource, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(textSpecificSource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_specificPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSpecificAssociation)
						.addComponent(textSpecificAssociation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_specificPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSpecificTarget)
						.addComponent(textSpecificTarget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		specificPanel.setLayout(gl_specificPanel);
		
		JLabel lblGeneralAssociation = new JLabel("Association:");
		lblGeneralAssociation.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblGeneralSource = new JLabel("Source:");
		lblGeneralSource.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblGeneralTarget = new JLabel("Target:");
		lblGeneralTarget.setHorizontalAlignment(SwingConstants.LEFT);
		
		textGeneralSource = new TheTextField();
		textGeneralTarget = new TheTextField();
		textGeneralAssociation = new TheTextField();				
		
		GroupLayout gl_generalPanel = new GroupLayout(generalPanel);
		gl_generalPanel.setHorizontalGroup(
			gl_generalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_generalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_generalPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblGeneralTarget, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblGeneralSource, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblGeneralAssociation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_generalPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textGeneralAssociation, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
						.addComponent(textGeneralSource, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
						.addComponent(textGeneralTarget, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_generalPanel.setVerticalGroup(
			gl_generalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_generalPanel.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_generalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeneralSource, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(textGeneralSource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_generalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeneralAssociation, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(textGeneralAssociation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_generalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeneralTarget, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(textGeneralTarget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		generalPanel.setLayout(gl_generalPanel);
		
		setLayout(groupLayout);
	}
	
	/**
	 * Generate Alloy.
	 * 
	 * @param event
	 */
	public void GenerateAlloyActionPerformed(ActionEvent event) throws Exception
	{
		Boolean redefinition = cbxRedefinition.isSelected();
		Boolean nonSubsetting = cbxNonsubsetting.isSelected();
		Boolean subtype = cbxSubtype.isSelected();
		Boolean disj = cbxDisjoint.isSelected();
		
		String redefinitionPred = new String();
		String disjPred = new String();
		String nonSubsettingPred = new String();
		String subtypePred = new String();
		
		if (disj) disjPred = rs.generatePredicate(frame.getTheModelBar().getOntoUMLParser(),RSAntiPattern.DISJOINT);
		if (redefinition) redefinitionPred = rs.generatePredicate(frame.getTheModelBar().getOntoUMLParser(),RSAntiPattern.REDEFINE);
		if (nonSubsetting) nonSubsettingPred = rs.generatePredicate(frame.getTheModelBar().getOntoUMLParser(),RSAntiPattern.NONSUBSET);
		if (subtype) subtypePred = rs.generatePredicate(frame.getTheModelBar().getOntoUMLParser(),RSAntiPattern.SUBSET);
		
		/* ontouml2alloy */
		Options opt = frame.getTheMenuBar().getOptions();		
		String alsPath = TheModelBar.alsOutDirectory+frame.getTheModelBar().alsmodelName+"$rs"+uniqueId+".als";		
		RefOntoUML.Package refmodel = frame.getTheModelBar().getOntoUMLModel();		
		OntoUML2Alloy.Transformation(refmodel, alsPath, opt);
		
		/* add predicates */
		String predicates = new String();
		if (disj) predicates += "\n\n"+disjPred;
		if (redefinition) predicates += "\n\n"+redefinitionPred;
		if (nonSubsetting) predicates += "\n\n"+nonSubsettingPred;
		if (subtype) predicates += "\n\n"+subtypePred;
		
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
		
		frame.getTheConsolePanel().write(disjPred +"\n\n"+redefinitionPred+"\n\n"+nonSubsettingPred+"\n\n"+subtypePred);
	}
	
	/**
	 * Generate OCL Solution.
	 * 
	 * @param event
	 */
	public void GenerateOCLSolutionActionPerformed(ActionEvent event) throws Exception
	{
		Boolean redefinition = cbxRedefinition.isSelected();
		Boolean nonSubsetting = cbxNonsubsetting.isSelected();
		Boolean subtype = cbxSubtype.isSelected();
		Boolean disj = cbxDisjoint.isSelected();
		
		String redefinitionConstraint = new String();
		String disjConstraint = new String();
		String nonSubsettingConstraint = new String();
		String subtypeConstraint = new String();
		
		if (disj) disjConstraint = rs.generateOcl(RSAntiPattern.DISJOINT);
		if (redefinition) redefinitionConstraint = rs.generateOcl(RSAntiPattern.REDEFINE);
		if (nonSubsetting) nonSubsettingConstraint = rs.generateOcl(RSAntiPattern.NONSUBSET);
		if (subtype) subtypeConstraint = rs.generateOcl(RSAntiPattern.SUBSET);
				
		frame.getTheConsolePanel().write(disjConstraint +"\n\n"+redefinitionConstraint+"\n\n"+nonSubsettingConstraint+"\n\n"+subtypeConstraint);
		frame.ShowConsole();		
	}
}
