package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.Color;
import java.awt.Dimension;
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
import br.ufes.inf.nemo.ontouml.antipattern.RBOSAntiPattern;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

/**
 * @author John Guerson
 */

public class SingleRBOSPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField textSuperType;	
	private JTextField textAssociation;	
	private JTextField textSource;		
	private JTextField textTarget;	
	private JCheckBox chckbxRflexive;	
	private JCheckBox chckbxIrreflexive;	
	private JButton btnGenerateAlloy;	
	private JButton btnGenerateOclSolution;	
	private JLabel lblOverlapping;
	
	private RBOSAntiPattern rbos;	
	private TheFrame frame;	
	private Integer uniqueId = 0;
		
	/**
	 * Create a Single RBOS AntiPattern Panel.
	 * 
	 * @param rbos
	 */
	public SingleRBOSPanel(RBOSAntiPattern rbos,TheFrame frame, Integer uniqueId)
	{
		this();
		
		this.rbos = rbos;
		this.frame = frame;
		this.uniqueId = uniqueId;
				
		textSource.setText("  "+rbos.getSource().getName());
		textTarget.setText("  "+rbos.getTarget().getName());
		textSuperType.setText("  "+rbos.getSupertype().getName());
		textAssociation.setText("  "+rbos.getAssociation().getName());	
		
		lblOverlapping.setText("Overlapping Subtypes "+uniqueId);
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
	public SingleRBOSPanel() 
	{
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "", TitledBorder.RIGHT, TitledBorder.BELOW_TOP, null, new Color(255, 0, 0)));
		setPreferredSize(new Dimension(330, 298));
		
		JPanel btnPanel = new JPanel();		
		JPanel cbxPanel = new JPanel();
		
		JLabel lblGeneratePredicate = new JLabel("Generate Predicate:");
		cbxPanel.add(lblGeneratePredicate);
		
		chckbxRflexive = new JCheckBox("Reflexive");		
		cbxPanel.add(chckbxRflexive);
		
		chckbxIrreflexive = new JCheckBox("Irreflexive");
		cbxPanel.add(chckbxIrreflexive);
		
		btnGenerateAlloy = new JButton("Execute With Analyzer");		
		btnGenerateAlloy.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			try{
       				
       				GenerateAlloyActionPerformed(event);
       			
       			} catch (Exception e) {
       				JOptionPane.showMessageDialog(SingleRBOSPanel.this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
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
       			GenerateOCLSolutionActionPerformed(event);
       		}
       	});		
		btnPanel.add(btnGenerateOclSolution);
		
		lblOverlapping = new JLabel("Overlapping Subtypes");
		lblOverlapping.setHorizontalAlignment(SwingConstants.CENTER);
		lblOverlapping.setText("Overlapping Subtypes "+this.uniqueId);
		
		JPanel textPanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
						.addComponent(cbxPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
						.addComponent(textPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
						.addComponent(lblOverlapping, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
					.addGap(13))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOverlapping)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		
		textSuperType = new TheTextField();
		JLabel lblSupertype = new JLabel("SuperType:");
		
		textAssociation = new TheTextField();		
		JLabel lblAssociation = new JLabel("Association:");
		
		textSource = new TheTextField();		
		JLabel lblSubtype = new JLabel("Source:");
		
		textTarget = new TheTextField();
		JLabel lblTarget = new JLabel("Target:");
		
		GroupLayout gl_textPanel = new GroupLayout(textPanel);
		gl_textPanel.setHorizontalGroup(
			gl_textPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_textPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_textPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblSubtype, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAssociation)
						.addComponent(lblSupertype, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTarget, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_textPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textSuperType, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
						.addComponent(textAssociation, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
						.addComponent(textSource, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
						.addComponent(textTarget, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_textPanel.setVerticalGroup(
			gl_textPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_textPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_textPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textSuperType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSupertype))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_textPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textAssociation, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAssociation))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_textPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textSource, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSubtype, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_textPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textTarget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTarget))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		textPanel.setLayout(gl_textPanel);
		
		setLayout(groupLayout);
	}
	
	/**
	 * Generate Alloy Solution.
	 * 
	 * @param event
	 */
	public void GenerateAlloyActionPerformed(ActionEvent event) throws Exception
	{		       			
		Boolean reflexive = chckbxRflexive.isSelected();
		Boolean irreflexive = chckbxIrreflexive.isSelected();
		String reflexivePred = new String();
		String irreflexivePred = new String();
			
		if(reflexive) reflexivePred = rbos.generateReflexivePredicate(frame.getTheModelBar().getOntoUMLParser());
		if(irreflexive) irreflexivePred = rbos.generateIrreflexivePredicate(frame.getTheModelBar().getOntoUMLParser()); 
					
		/* ontouml2alloy */
		Options opt = frame.getTheMenuBar().getOptions();		
		String alsPath = TheModelBar.alsOutDirectory+frame.getTheModelBar().alsmodelName+"$rbos"+uniqueId+".als";		
		RefOntoUML.Package refmodel = frame.getTheModelBar().getOntoUMLModel();		
		OntoUML2Alloy.Transformation(refmodel, alsPath, opt);
		
		/* add predicates */
		String predicates = new String();
		if (reflexive) predicates += "\n\n"+reflexivePred;
		if (irreflexive) predicates += "\n\n"+irreflexivePred;
		
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
		
		frame.getTheConsolePanel().write(reflexivePred+"\n\n"+irreflexivePred);		
	}
	
	/**
	 * Generate OCL SOlution.
	 * 
	 * @param event
	 */	
	public void GenerateOCLSolutionActionPerformed(ActionEvent event)
	{
		Boolean reflexive = chckbxRflexive.isSelected();
		Boolean irreflexive = chckbxIrreflexive.isSelected();
		String reflexiveConstraint = new String();
		String irreflexiveConstraint = new String();
		
		if(reflexive) reflexiveConstraint = rbos.generateReflexiveOcl();
		if(irreflexive) irreflexiveConstraint = rbos.generateIrreflexiveOcl();		
		
		frame.getTheConsolePanel().write(reflexiveConstraint+"\n\n"+irreflexiveConstraint);
		frame.ShowConsole();
	}
}
