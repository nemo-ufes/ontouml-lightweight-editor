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
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.ontouml.antipattern.RBOSAntiPattern;

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
	private RBOSAntiPattern rbos;	
	private TheFrame frame;	
	
	/**
	 * Create a Single RBOS AntiPattern Panel.
	 * 
	 * @param rbos
	 */
	public SingleRBOSPanel(RBOSAntiPattern rbos,TheFrame frame)
	{
		this();
		
		this.rbos = rbos;
		this.frame = frame;
		
		textSource.setText(rbos.getSource().getName());
		textTarget.setText(rbos.getTarget().getName());
		textSuperType.setText(rbos.getSupertype().getName());
		textAssociation.setText(rbos.getAssociation().getName());
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
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "RBOS #1", TitledBorder.LEFT, TitledBorder.BELOW_TOP, null, Color.RED));
		setPreferredSize(new Dimension(330, 250));
		
		JLabel lblSupertype = new JLabel("SuperType:");
		JLabel lblAssociation = new JLabel("Association:");
		JLabel lblSubtype = new JLabel("Source:");
		JLabel lblTarget = new JLabel("Target:");
		
		textSuperType = new JTextField();
		textSuperType.setBackground(Color.WHITE);
		textSuperType.setEditable(false);
		textSuperType.setColumns(10);
		
		textAssociation = new JTextField();
		textAssociation.setBackground(Color.WHITE);
		textAssociation.setEditable(false);
		textAssociation.setColumns(10);		
		
		textSource = new JTextField();
		textSource.setBackground(Color.WHITE);
		textSource.setEditable(false);
		textSource.setColumns(10);
						
		textTarget = new JTextField();
		textTarget.setBackground(Color.WHITE);
		textTarget.setEditable(false);
		textTarget.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JPanel btnPanel = new JPanel();		
		JPanel cbxPanel = new JPanel();
		
		chckbxRflexive = new JCheckBox("Reflexive");		
		cbxPanel.add(chckbxRflexive);
		
		chckbxIrreflexive = new JCheckBox("Irreflexive");
		cbxPanel.add(chckbxIrreflexive);
		
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
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblAssociation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblSupertype, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(lblSubtype, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblTarget, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textSuperType, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
								.addComponent(textAssociation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
								.addComponent(textSource, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
								.addComponent(textTarget, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
							.addGap(6)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(cbxPanel, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
							.addGap(17))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnPanel, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
							.addGap(17))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupertype)
						.addComponent(textSuperType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAssociation)
						.addComponent(textAssociation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubtype)
						.addComponent(textSource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTarget)
						.addComponent(textTarget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(123, Short.MAX_VALUE))
		);
		
		setLayout(groupLayout);
	}
	
	/**
	 * Generate Alloy Solution.
	 * 
	 * @param event
	 */
	public void GenerateAlloyActionPerformed(ActionEvent event)
	{		       			
		Boolean reflexive = chckbxRflexive.isSelected();
		Boolean irreflexive = chckbxIrreflexive.isSelected();
		String reflexivePred = new String();
		String irreflexivePred = new String();
			
		if(reflexive) 
		{
			reflexivePred = rbos.generateReflexivePredicate(frame.getTheModelsBar().getOntoUMLParser());
		}
		if(irreflexive) 
		{
			irreflexivePred = rbos.generateIrreflexivePredicate(frame.getTheModelsBar().getOntoUMLParser()); 
		}
			
		frame.getTheConsolePanel().write(reflexivePred+"\n\n"+irreflexivePred);
		frame.ShowConsole();
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
		
		if(reflexive) 
		{
			reflexiveConstraint = rbos.generateReflexiveOcl();
		}
		if(irreflexive) 
		{
			irreflexiveConstraint = rbos.generateIrreflexiveOcl(); 
		}
		
		frame.getTheConsolePanel().write(reflexiveConstraint+"\n\n"+irreflexiveConstraint);
		frame.ShowConsole();
	}
	 
	
}
