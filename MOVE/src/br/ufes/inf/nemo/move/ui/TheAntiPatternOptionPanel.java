package br.ufes.inf.nemo.move.ui;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 * This Panel was created using the Windows Builder in Eclipse. 
 * 
 * @author John Guerson
 */

public class TheAntiPatternOptionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/* check boxes */
	private JCheckBox chckbxSelftypeRelationship;	
	private JCheckBox chckbxRelationBetweenOverlapping;
	private JCheckBox chckbxImpreciseAbstraction; 
	private JCheckBox chckbxAssociationCicle;
	private JCheckBox chckbxRelationSpecialization;
	private JCheckBox chckbxRelatorWithOverlapping;
	
	/** 
	 * Check if AntiPattern is selected.
	 */
	public Boolean ACisSelected()  { return chckbxAssociationCicle.isSelected();           }	
	public Boolean STRisSelected() { return chckbxSelftypeRelationship.isSelected();       }
	public Boolean RBOisSelected() { return chckbxRelationBetweenOverlapping.isSelected(); }
	public Boolean IAisSelected()  { return chckbxImpreciseAbstraction.isSelected();       }
	public Boolean RSisSelected()  { return chckbxRelationSpecialization.isSelected();     }
	public Boolean RWOisSelected() { return chckbxRelatorWithOverlapping.isSelected();     }
	
	/**
	 * Create the panel.
	 */
	public TheAntiPatternOptionPanel() 
	{
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "AntiPattern Options", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setBackground(UIManager.getColor("inactiveCaption"));
		
		chckbxSelftypeRelationship = new JCheckBox("STR : Self-Type Relationship");
		chckbxSelftypeRelationship.setBackground(UIManager.getColor("inactiveCaption"));
		
		chckbxRelationBetweenOverlapping = new JCheckBox("RBOS : Relation Between Overlapping SubTypes");
		chckbxRelationBetweenOverlapping.setBackground(UIManager.getColor("inactiveCaption"));
		
		chckbxImpreciseAbstraction = new JCheckBox("IA : Imprecise Abstraction");
		chckbxImpreciseAbstraction.setBackground(UIManager.getColor("inactiveCaption"));
		
		chckbxAssociationCicle = new JCheckBox("AC : Association Cycle");
		chckbxAssociationCicle.setBackground(UIManager.getColor("inactiveCaption"));
		
		chckbxRelationSpecialization = new JCheckBox("RS : Relation Specialization");
		chckbxRelationSpecialization.setBackground(UIManager.getColor("inactiveCaption"));
		
		chckbxRelatorWithOverlapping = new JCheckBox("RWOR : Relator With Overlapping Roles");
		chckbxRelatorWithOverlapping.setBackground(UIManager.getColor("inactiveCaption"));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxRelationBetweenOverlapping)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chckbxRelatorWithOverlapping)
							.addContainerGap(142, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(chckbxImpreciseAbstraction)
									.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(chckbxSelftypeRelationship)
									.addGap(18)))
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxRelationSpecialization)
								.addComponent(chckbxAssociationCicle))
							.addGap(17))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxSelftypeRelationship)
						.addComponent(chckbxAssociationCicle))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxImpreciseAbstraction)
						.addComponent(chckbxRelationSpecialization))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxRelatorWithOverlapping)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxRelationBetweenOverlapping)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
