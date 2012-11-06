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
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.ontouml.antipattern.STRAntiPattern;

/**
 * @author John Guerson
 */

public class SingleSTRPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
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
	private STRAntiPattern str;	
	private TheFrame frame;	
	private JSpinner spinScope;
		
	/**
	 * Create a Single STR AntiPattern Panel.
	 * 
	 * @param rbos
	 */
	public SingleSTRPanel(STRAntiPattern str, TheFrame frame)
	{
		this();
		
		this.str = str;
		this.frame = frame;
		
		txtAssociation.setText(str.getAssociation().getName());
		txtType.setText(str.getType().getName());		
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
	public SingleSTRPanel() 
	{
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "STR #1", TitledBorder.LEFT, TitledBorder.BELOW_TOP, null, Color.RED));
		setPreferredSize(new Dimension(330, 275));
		
		JLabel lblType = new JLabel("Type:");
		
		txtType = new JTextField();
		txtType.setBackground(Color.WHITE);
		txtType.setEditable(false);
		txtType.setColumns(10);
		
		JLabel lblAssociation = new JLabel("Association:");
		
		txtAssociation = new JTextField();
		txtAssociation.setBackground(Color.WHITE);
		txtAssociation.setEditable(false);
		txtAssociation.setColumns(10);
		
		JPanel panel = new JPanel();		
		JPanel panel_1 = new JPanel();		
		JPanel panel_2 = new JPanel();		
		JPanel panel_3 = new JPanel();
		
		JLabel lblEscope = new JLabel("Escope: ");
		panel_3.add(lblEscope);
		
		spinScope = new JSpinner();
		spinScope.setPreferredSize(new Dimension(40, 20));		
		spinScope.setModel(new SpinnerNumberModel(new Integer(2), new Integer(0), null, new Integer(1)));
		panel_3.add(spinScope);
		
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		cbxAntireflexive = new JCheckBox("AntiReflexive");		
		panel_2.add(cbxAntireflexive);
		
		cbxTransitive = new JCheckBox("Transitive");
		panel_2.add(cbxTransitive);
		
		cbxNontransitive = new JCheckBox("Non-Transitive");
		panel_2.add(cbxNontransitive);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		
		cbxSymmetryc = new JCheckBox("Symmetric ");		
		panel_1.add(cbxSymmetryc);
		
		cbxAntisymmetric = new JCheckBox("AntiSymmetric");		
		panel_1.add(cbxAntisymmetric);
		
		cbxReflexive = new JCheckBox("Reflexive");		
		panel_1.add(cbxReflexive);
		
		btnGenerateAlloy = new JButton("Generate Alloy");	
		btnGenerateAlloy.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			GenerateAlloyActionPerformed(event);
       		}
       	});		
		panel.add(btnGenerateAlloy);
		
		btnGenerateOclSolution = new JButton("Generate OCL Solution");
		btnGenerateOclSolution.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			GenerateOCLSolutionActionPerformed(event);
       		}
       	});		
		panel.add(btnGenerateOclSolution);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblAssociation)
								.addComponent(lblType, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtAssociation, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(txtType, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))))
					.addGap(21))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(txtType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblAssociation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtAssociation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);		
				
		setLayout(groupLayout);
	}
	
	/**
	 * Generate Alloy.
	 * @param event
	 */
	public void GenerateAlloyActionPerformed(ActionEvent event)
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
		if(symmetryc) symmetrycPred =str.generateSymmetricPredicate(cardinality, frame.getTheModelsPanel().getOntoUMLParser());
		if(reflexive) reflexivePred =str.generateReflexivePredicate(cardinality, frame.getTheModelsPanel().getOntoUMLParser());
		if(transitive) transitivePred = str.generateTransitivePredicate(cardinality, frame.getTheModelsPanel().getOntoUMLParser());
		if(antisymmetric) antisymmetricPred = str.generateAntisymmetricPredicate(cardinality, frame.getTheModelsPanel().getOntoUMLParser());
		if(antireflexive) antireflexivePred = str.generateIrreflexivePredicate(cardinality, frame.getTheModelsPanel().getOntoUMLParser());
		if(nontransitive) nontransitivePred = str.generateIntransitivePredicate(cardinality, frame.getTheModelsPanel().getOntoUMLParser());
		
		frame.getTheConsolePanel().write(symmetrycPred +"\n\n"+reflexivePred+"\n\n"+transitivePred+"\n\n"+antisymmetricPred+"\n\n"+antireflexivePred+"\n\n"+nontransitivePred);
		frame.ShowConsole();
	}
	
	/**
	 * Generate OCL Solution.
	 * @param event
	 */
	public void GenerateOCLSolutionActionPerformed(ActionEvent event)
	{
		Boolean symmetryc = cbxSymmetryc.isSelected();
		Boolean reflexive = cbxReflexive.isSelected();
		//Boolean transitive = cbxTransitive.isSelected();
		Boolean antisymmetric = cbxAntisymmetric.isSelected();
		Boolean antireflexive = cbxAntireflexive.isSelected();
		//Boolean nontransitive = cbxNontransitive.isSelected();
		
		String symmetrycConstraint = new String();
		String reflexiveConstraint = new String();
		//String transitiveConstraint = new String();
		String antisymmetricConstraint = new String();
		String antireflexiveConstraint = new String();
		//String nontransitiveConstraint = new String();
				
		if(symmetryc) symmetrycConstraint =str.generateSymmetricOcl();
		if(reflexive) reflexiveConstraint =str.generateReflexiveOcl();
		//if(transitive) transitiveConstraint = str.generateTransitiveOcl();
		if(antisymmetric) antisymmetricConstraint = str.generateAntiSymmetricOcl();
		if(antireflexive) antireflexiveConstraint = str.generateIrreflexiveOcl();
		//if(nontransitive) nontransitiveConstraint = str.generateIntransitiveOcl();
		
		frame.getTheConsolePanel().write(symmetrycConstraint +"\n\n"+reflexiveConstraint+"\n\n"+antisymmetricConstraint +"\n\n"+antireflexiveConstraint+"\n\n");
		frame.ShowConsole();
	}
	
}
