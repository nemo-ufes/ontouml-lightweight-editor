package br.ufes.inf.nemo.move.mvc.view.antipattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.move.mvc.model.antipattern.STRAntiPatternModel;
import br.ufes.inf.nemo.move.ui.TheFrame;


/**
 * @author John Guerson
 */

public class STRAntiPatternView extends JPanel {

	private static final long serialVersionUID = 497601694951872890L;

	@SuppressWarnings("unused")
	private STRAntiPatternModel strModel;
	
	private TheFrame frame;
	private JTextField txtType;	
	private JTextField txtAssociation;
	private JCheckBox cbxSymmetryc;
	private JCheckBox cbxReflexive;
	private JCheckBox cbxTransitive;
	private JCheckBox cbxAntisymmetric;
	private JCheckBox cbxAntireflexive;
	private JCheckBox cbxNontransitive;	
	private JButton btnGenerateAlloy;
	private JButton btnGenerateOclSolution;	
	private JSpinner spinScope;
	private JPanel textPanel;
	private JLabel lblTitle;
	private JLabel lblScope;
	private JLabel lblatLeast;
			
	/**
	 * Constructor.
	 * 
	 * @param strModel
	 * @param frame
	 */
	public STRAntiPatternView(STRAntiPatternModel strModel,TheFrame frame)
	{
		this();
		
		this.strModel = strModel;
		this.frame = frame;
		
		txtAssociation.setText("  "+strModel.getSTRAntiPattern().getAssociation().getName());
		txtType.setText("  "+strModel.getSTRAntiPattern().getType().getName());	
		
		lblTitle.setText("Self-Type Relationship "+strModel.getId());
		
		lblScope.setText("\""+strModel.getSTRAntiPattern().getAssociation().getName()+"\""+" Scope: ");
	}		
		
	/**
	 * Constructor.
	 */
	public STRAntiPatternView() 
	{
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "", TitledBorder.RIGHT, TitledBorder.BELOW_TOP, null, new Color(255, 0, 0)));
		setPreferredSize(new Dimension(330, 305));
		
		JPanel btnPanel = new JPanel();		
		JPanel checkPanel1 = new JPanel();
		JPanel scopePanel = new JPanel();
		
		lblScope = new JLabel("Scope: ");
		scopePanel.add(lblScope);
		
		spinScope = new JSpinner();
		spinScope.setPreferredSize(new Dimension(40, 20));		
		spinScope.setModel(new SpinnerNumberModel(new Integer(2), new Integer(0), null, new Integer(1)));
		scopePanel.add(spinScope);		
		cbxSymmetryc = new JCheckBox("Symmetric ");
		cbxAntisymmetric = new JCheckBox("AntiSymmetric");
		cbxReflexive = new JCheckBox("Reflexive");
		
		checkPanel1.setLayout(new GridLayout(3, 1, 0, 0));				
		checkPanel1.add(cbxSymmetryc);
		
		cbxAntireflexive = new JCheckBox("AntiReflexive");		
		checkPanel1.add(cbxAntireflexive);
		checkPanel1.add(cbxAntisymmetric);				
		cbxTransitive = new JCheckBox("Transitive");
		checkPanel1.add(cbxTransitive);
		checkPanel1.add(cbxReflexive);
		
		btnGenerateAlloy = new JButton("Execute With Analyzer");		
		btnPanel.add(btnGenerateAlloy);
		
		btnGenerateOclSolution = new JButton("OCL Solution");
		btnPanel.add(btnGenerateOclSolution);
		
		textPanel = new JPanel();
		
		lblTitle = new JLabel("Self-Type Relationship ");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
							.addGap(21))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPanel, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
								.addComponent(textPanel, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scopePanel, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)))
							.addGap(12))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(checkPanel1, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textPanel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scopePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(129))
		);
		cbxNontransitive = new JCheckBox("Non-Transitive");
		checkPanel1.add(cbxNontransitive);
		
		lblatLeast = new JLabel("(at least)");
		scopePanel.add(lblatLeast);
		
		JLabel lblType = new JLabel("Type:");		
		txtType = new JTextField();
		txtType.setPreferredSize(new Dimension(30, 20));
		txtType.setBorder(new LineBorder(new Color(128, 128, 128)));
		txtType.setBackground(Color.WHITE);
		txtType.setEditable(false);		
		txtType.setColumns(10);	
		
		JLabel lblAssociation = new JLabel("Association:");		
		txtAssociation = new JTextField();
		txtAssociation.setPreferredSize(new Dimension(30, 20));
		txtAssociation.setBorder(new LineBorder(new Color(128, 128, 128)));
		txtAssociation.setBackground(Color.WHITE);
		txtAssociation.setEditable(false);		
		txtAssociation.setColumns(10);	
						
		GroupLayout gl_textPanel = new GroupLayout(textPanel);
		gl_textPanel.setHorizontalGroup(
			gl_textPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_textPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_textPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblAssociation, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblType, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_textPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtAssociation, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
						.addComponent(txtType, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_textPanel.setVerticalGroup(
			gl_textPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_textPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_textPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(txtType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_textPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAssociation, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAssociation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		textPanel.setLayout(gl_textPanel);
				
		setLayout(groupLayout);
	}
	
	public boolean isSelectedIrreflexive()
	{
		return cbxAntireflexive.isSelected();
	}
	
	public boolean isSelectedAntiSymmetric()
	{
		return cbxAntisymmetric.isSelected();
	}

	public boolean isSelectedIntransitive()
	{
		return cbxNontransitive.isSelected();
	}
	
	public boolean isSelectedReflexive()
	{
		return cbxReflexive.isSelected();
	}
	
	public boolean isSelectedSymmetric()
	{
		return cbxSymmetryc.isSelected();
	}
	
	public boolean isSelectedTransitive()
	{
		return cbxTransitive.isSelected();
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
		btnGenerateOclSolution.addActionListener(actionListener);
	}
	
	/*
	public void GenerateAlloyActionPerformed(ActionEvent event) throws Exception
	{
		Boolean symmetryc = cbxSymmetryc.isSelected();
		Boolean reflexive = cbxReflexive.isSelected();
		Boolean transitive = cbxTransitive.isSelected();
		Boolean antisymmetric = cbxAntisymmetric.isSelected();
		Boolean antireflexive = cbxAntireflexive.isSelected();
		Boolean nontransitive = cbxNontransitive.isSelected();
		
		String symmetrycPred = new String();
		String reflexivePred = new String();
		String transitivePred = new String();
		String antisymmetricPred = new String();
		String antireflexivePred = new String();
		String nontransitivePred = new String();
		
		Integer cardinality = (Integer)spinScope.getModel().getValue();
		
		if(symmetryc) symmetrycPred =str.generateSymmetricPredicate(cardinality, frame.getModelsComponent().getOntoUMLParser());
		if(reflexive) reflexivePred =str.generateReflexivePredicate(cardinality, frame.getModelsComponent().getOntoUMLParser());
		if(transitive) transitivePred = str.generateTransitivePredicate(cardinality, frame.getModelsComponent().getOntoUMLParser());
		if(antisymmetric) antisymmetricPred = str.generateAntisymmetricPredicate(cardinality, frame.getModelsComponent().getOntoUMLParser());
		if(antireflexive) antireflexivePred = str.generateIrreflexivePredicate(cardinality, frame.getModelsComponent().getOntoUMLParser());
		if(nontransitive) nontransitivePred = str.generateIntransitivePredicate(cardinality, frame.getModelsComponent().getOntoUMLParser());
		
		Options opt = frame.getTheMenuBar().getOptions();		
		String alsPath = ModelEntryPanel.alsOutDirectory+frame.getModelsComponent().alsmodelName+"$str"+uniqueId+".als";		
		RefOntoUML.Package refmodel = frame.getModelsComponent().getOntoUMLModel();		
		OntoUML2Alloy.Transformation(refmodel, alsPath, opt);
		
		String predicates = new String();
		if (symmetryc) predicates += "\n\n"+symmetrycPred;
		if (reflexive) predicates += "\n\n"+reflexivePred;
		if (transitive) predicates += "\n\n"+transitivePred;
		if (antisymmetric) predicates += "\n\n"+antisymmetricPred;
		if (antireflexive) predicates += "\n\n"+antireflexivePred;
		if (nontransitive) predicates += "\n\n"+nontransitivePred;
				
		FileUtil.writeToFile(predicates, alsPath);
		
		if (opt.openAnalyzer)
		{
			AlloyJARExtractor.extractAlloyJaRTo("alloy4.2.jar", ModelEntryPanel.alsOutDirectory);
			
			String[] argsAnalyzer = { "","" };
			argsAnalyzer[0] = alsPath;
			argsAnalyzer[1] = ModelEntryPanel.alsOutDirectory + "standart_theme.thm"	;	
			SimpleGUICustom.main(argsAnalyzer);
		}
		
		frame.getTheConsolePanel().write(symmetrycPred +"\n\n"+reflexivePred+"\n\n"+transitivePred+"\n\n"+antisymmetricPred+"\n\n"+antireflexivePred+"\n\n"+nontransitivePred);		
	}*/
	

	/*
	public void GenerateOCLSolutionActionPerformed(ActionEvent event)
	{
		Boolean symmetryc = cbxSymmetryc.isSelected();
		Boolean reflexive = cbxReflexive.isSelected();
		Boolean transitive = cbxTransitive.isSelected();
		Boolean antisymmetric = cbxAntisymmetric.isSelected();
		Boolean antireflexive = cbxAntireflexive.isSelected();
		Boolean nontransitive = cbxNontransitive.isSelected();
		
		String symmetrycConstraint = new String();
		String reflexiveConstraint = new String();
		String transitiveConstraint = new String();
		String antisymmetricConstraint = new String();
		String antireflexiveConstraint = new String();
		String intransitiveConstraint = new String();
				
		if(symmetryc) symmetrycConstraint =str.generateSymmetricOcl();
		if(reflexive) reflexiveConstraint =str.generateReflexiveOcl();
		if(transitive) transitiveConstraint = str.generateTransitiveOcl();
		if(antisymmetric) antisymmetricConstraint = str.generateAntiSymmetricOcl();
		if(antireflexive) antireflexiveConstraint = str.generateIrreflexiveOcl();
		if(nontransitive) intransitiveConstraint = str.generateIntransitiveOcl();
		
		frame.getConsole().write(symmetrycConstraint +"\n\n"+reflexiveConstraint+"\n\n"+
		antisymmetricConstraint +"\n\n"+antireflexiveConstraint+"\n\n"+transitiveConstraint+"\n\n"+intransitiveConstraint+"\n\n");
		frame.ShowConsole();
	}*/
	
}
