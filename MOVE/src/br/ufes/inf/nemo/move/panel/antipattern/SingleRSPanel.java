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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.ontouml.antipattern.RSAntiPattern;

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
		
	/**
	 * Create a Single RS AntiPattern Panel.
	 * 
	 * @param rbos
	 */
	public SingleRSPanel(RSAntiPattern rs,TheFrame frame)
	{
		this();
		
		this.rs = rs;
		this.frame = frame;
		
		textGeneralSource.setText(rs.getGeneralSource().getName());
		textGeneralTarget.setText(rs.getGeneralTarget().getName());
		textGeneralAssociation.setText(rs.getGeneral().getName());
		textSpecificAssociation.setText(rs.getSpecific().getName());
		textSpecificSource.setText(rs.getSpecificSource().getName());
		textSpecificTarget.setText(rs.getSpecificTarget().getName());
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
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "RS #1", TitledBorder.LEFT, TitledBorder.BELOW_TOP, null, Color.RED));
		setPreferredSize(new Dimension(330, 389));
		
		JLabel lblSourceType = new JLabel("[General]");
		lblSourceType.setHorizontalAlignment(SwingConstants.CENTER);
		
		textGeneralSource = new JTextField();
		textGeneralSource.setBackground(Color.WHITE);
		textGeneralSource.setEditable(false);
		textGeneralSource.setColumns(10);
		
		JLabel lblSepecificSourceType = new JLabel("Association:");
		lblSepecificSourceType.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textGeneralTarget = new JTextField();
		textGeneralTarget.setBackground(Color.WHITE);
		textGeneralTarget.setEditable(false);
		textGeneralTarget.setColumns(10);
		
		JLabel lblSpecificAssociation = new JLabel("Target:");
		lblSpecificAssociation.setHorizontalAlignment(SwingConstants.LEFT);
		
		textGeneralAssociation = new JTextField();
		textGeneralAssociation.setBackground(Color.WHITE);
		textGeneralAssociation.setEditable(false);
		textGeneralAssociation.setColumns(10);
		
		JLabel lblSourceType_1 = new JLabel("Source:");
		lblSourceType_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblSpecific = new JLabel("[Specific]");
		lblSpecific.setBackground(Color.WHITE);
		lblSpecific.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblSource = new JLabel("Source:");
		
		textSpecificSource = new JTextField();
		textSpecificSource.setBackground(Color.WHITE);
		textSpecificSource.setEditable(false);
		textSpecificSource.setColumns(10);
		
		JLabel lblAssociation = new JLabel("Association:");
		
		textSpecificAssociation = new JTextField();
		textSpecificAssociation.setBackground(Color.WHITE);
		textSpecificAssociation.setEditable(false);
		textSpecificAssociation.setColumns(10);
		
		JLabel lblTarget = new JLabel("Target:");
		
		textSpecificTarget = new JTextField();
		textSpecificTarget.setBackground(Color.WHITE);
		textSpecificTarget.setEditable(false);
		textSpecificTarget.setColumns(10);
		
		JPanel btnPanel = new JPanel();		
		JPanel cbxPanel1 = new JPanel();	
		JPanel cbxPanel2 = new JPanel();
		
		cbxPanel2.setLayout(new GridLayout(2, 2, 0, 0));
		cbxRedefinition = new JCheckBox("Redefinition");		
		cbxPanel2.add(cbxRedefinition);		
		cbxRedefinition.setHorizontalAlignment(SwingConstants.LEFT);
		
		cbxNonsubsetting = new JCheckBox("Non-Subsetting");
		cbxPanel2.add(cbxNonsubsetting);		
		cbxNonsubsetting.setHorizontalAlignment(SwingConstants.LEFT);
		
		cbxPanel1.setLayout(new GridLayout(0, 1, 0, 0));
		
		cbxSubtype = new JCheckBox("Subsetting\r\n");
		cbxPanel1.add(cbxSubtype);
		cbxSubtype.setHorizontalAlignment(SwingConstants.LEFT);
		
		cbxDisjoint = new JCheckBox("Disjointness");
		cbxPanel1.add(cbxDisjoint);
		cbxDisjoint.setHorizontalAlignment(SwingConstants.LEFT);
				
		btnGenerateAlloy = new JButton("Generate Alloy");	
		btnGenerateAlloy.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			GenerateAlloyActionPerformed(event);
       		}
       	});		
		btnPanel.add(btnGenerateAlloy);
		
		btnGenerateOclSolution = new JButton("Generate OCL Solution");
		btnGenerateOclSolution.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			GenerateOCLSolutionActionPerformed(event);
       		}
       	});		
		btnPanel.add(btnGenerateOclSolution);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
						.addComponent(lblSourceType, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblSpecificAssociation, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSourceType_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSepecificSourceType, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textGeneralTarget, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
								.addComponent(textGeneralAssociation, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
								.addComponent(textGeneralSource, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSpecific, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
							.addGap(2))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblTarget, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
								.addComponent(lblAssociation, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
								.addComponent(lblSource, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textSpecificSource, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
								.addComponent(textSpecificAssociation, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
								.addComponent(textSpecificTarget, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(cbxPanel2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cbxPanel1, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSourceType)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSourceType_1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(textGeneralSource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSepecificSourceType, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(textGeneralAssociation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSpecificAssociation, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(textGeneralTarget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblSpecific, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSource, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(textSpecificSource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAssociation)
						.addComponent(textSpecificAssociation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTarget)
						.addComponent(textSpecificTarget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxPanel2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxPanel1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(21))
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
		Boolean redefinition = cbxRedefinition.isSelected();
		Boolean nonSubsetting = cbxNonsubsetting.isSelected();
		Boolean subtype = cbxSubtype.isSelected();
		Boolean disj = cbxDisjoint.isSelected();
		
		String redefinitionPred = new String();
		String disjPred = new String();
		String nonSubsettingPred = new String();
		String subtypePred = new String();
		
		if (disj) disjPred = rs.generateDisjointPredicate(frame.getTheModelsPanel().getOntoUMLParser());
		if (redefinition) redefinitionPred = rs.generateRedefinePredicate(frame.getTheModelsPanel().getOntoUMLParser());
		if (nonSubsetting) nonSubsettingPred = rs.generateNotSubsetPredicate(frame.getTheModelsPanel().getOntoUMLParser());
		if (subtype) subtypePred = rs.generateSubsetPredicate(frame.getTheModelsPanel().getOntoUMLParser());
		
		frame.getTheConsolePanel().write(disjPred +"\n\n"+redefinitionPred+"\n\n"+nonSubsettingPred+"\n\n"+subtypePred);
		frame.ShowConsole();
	}
	
	/**
	 * Generate OCL Solution.
	 * 
	 * @param event
	 */
	public void GenerateOCLSolutionActionPerformed(ActionEvent event)
	{
		Boolean redefinition = cbxRedefinition.isSelected();
		Boolean nonSubsetting = cbxNonsubsetting.isSelected();
		Boolean subtype = cbxSubtype.isSelected();
		Boolean disj = cbxDisjoint.isSelected();
		
		String redefinitionConstraint = new String();
		String disjConstraint = new String();
		String nonSubsettingConstraint = new String();
		String subtypeConstraint = new String();
		
		if (disj) disjConstraint = rs.generateDisjointOcl();
		if (redefinition) redefinitionConstraint = rs.generateRedefineOcl();
		if (nonSubsetting) nonSubsettingConstraint = rs.generateNotSubsetOcl();
		if (subtype) subtypeConstraint = rs.generateSubsetOcl();
		
		frame.getTheConsolePanel().write(disjConstraint +"\n\n"+redefinitionConstraint+"\n\n"+nonSubsettingConstraint+"\n\n"+subtypeConstraint);
		frame.ShowConsole();		
	}
}
