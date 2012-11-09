package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.ui.TheTextField;
import br.ufes.inf.nemo.ontouml.antipattern.RWORAntiPattern;
import javax.swing.SwingConstants;

/**
 * @author John Guerson
 */

public class SingleRWORPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField textRelator;
	private JPanel tablePane;
	private JScrollPane scrollPane;
	private RoleJTable table;		
	private JButton btnGenerateAlloy;
	private JButton btnGenerateOclSolution;	
	private JCheckBox cbxExclusive;
	private JCheckBox cbxOverlapping;
	private JCheckBox cbxCustomizedExclusive;	
	
	private RWORAntiPattern rwor;	
	private TheFrame frame;
	
	private Collection<Classifier> disjRoles;
	
	private JSpinner spinScope;
	private JLabel lblatLeast;
	private JLabel lblRelationWithOverlapping;
			
	/**
	 * Create a Single RWOR AntiPattern Panel.
	 * 
	 * @param rbos
	 */
	public SingleRWORPanel(RWORAntiPattern rwor, TheFrame frame)
	{
		this();
		
		this.rwor = rwor;
		this.frame= frame;				
		
		textRelator.setText(rwor.getRelator().getName());		
		
		disjRoles = rwor.getMediations().values();		
		
		table.setRWORAntiPattern(rwor);		
		table.setRolesTableModel(disjRoles);
		table.setPreferredSize(new Dimension(table.getRowCount()*20,table.getColumnCount()*50));		
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
	public SingleRWORPanel() 
	{
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "", TitledBorder.RIGHT, TitledBorder.BELOW_TOP, null, new Color(255, 0, 0)));
		
		JLabel lblRelator = new JLabel("Relator:");		
		JLabel lblDisjointsRoles = new JLabel("Customizing Disjoints Roles:");
		
		textRelator = new TheTextField();
		textRelator.setBackground(Color.WHITE);
		textRelator.setEditable(false);
		textRelator.setColumns(10);
		
		JPanel checkPane = new JPanel();		
		JPanel btnPane = new JPanel();
		
		btnGenerateAlloy = new JButton("Execute With Analyzer");
		btnGenerateAlloy.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			GenerateAlloyActionPerformed(event);
       		}
       	});		
		btnPane.add(btnGenerateAlloy);
		
		btnGenerateOclSolution = new JButton("OCL Solution");
		btnGenerateOclSolution.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			GenerateOCLSolutionActionPerformed(event);
       		}
       	});	
		btnPane.add(btnGenerateOclSolution);
		
		cbxExclusive = new JCheckBox("Exclusive");
		checkPane.add(cbxExclusive);
		
		cbxOverlapping = new JCheckBox("Overlapping");
		checkPane.add(cbxOverlapping);
		
		cbxCustomizedExclusive = new JCheckBox("Customized Exclusive");
		checkPane.add(cbxCustomizedExclusive);
		
		tablePane = new JPanel();
		tablePane.setBackground(Color.WHITE);
		tablePane.setLayout(new BorderLayout(0, 0));
		
		JPanel scopePanel = new JPanel();
		
		lblRelationWithOverlapping = new JLabel("Relation With Overlapping Roles");
		lblRelationWithOverlapping.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(tablePane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
								.addComponent(checkPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
							.addGap(22))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDisjointsRoles, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblRelator)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textRelator))
								.addComponent(lblRelationWithOverlapping, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
							.addGap(25))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnPane, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
								.addComponent(scopePanel, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
							.addGap(21))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblRelationWithOverlapping)
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRelator)
						.addComponent(textRelator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDisjointsRoles, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tablePane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkPane, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scopePanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		
		JLabel lblScope = new JLabel("Scope");
		scopePanel.add(lblScope);
		
		spinScope = new JSpinner();
		spinScope.setModel(new SpinnerNumberModel(new Integer(2), new Integer(0), null, new Integer(1)));
		spinScope.setPreferredSize(new Dimension(60, 20));
		scopePanel.add(spinScope);
		
		lblatLeast = new JLabel("(at least)");
		scopePanel.add(lblatLeast);
		
		scrollPane = new JScrollPane();
		tablePane.add(scrollPane, BorderLayout.CENTER);
		
		table = new RoleJTable();		
		scrollPane.setViewportView(table);
		
		setLayout(groupLayout);			
	}
	
	/**
	 * Generate Alloy.
	 * 
	 * @param event
	 */
	public void GenerateAlloyActionPerformed(ActionEvent event)
	{		
		
		Boolean exclusive = cbxExclusive.isSelected();
		Boolean overlapping = cbxOverlapping.isSelected();
		Boolean custom = cbxCustomizedExclusive.isSelected();
				
		String exclusivePred = new String();
		String overlappingPred = new String();
		String customPred = new String();

		int cardinality = (Integer)spinScope.getValue();
		
		if(exclusive) exclusivePred = rwor.generateAllMultipleExclusivePredicate(frame.getTheModelBar().getOntoUMLParser(), cardinality);
		if(overlapping) overlappingPred = rwor.generateOverlappingPredicate(frame.getTheModelBar().getOntoUMLParser(), cardinality);
		if(custom) customPred = rwor.generateMultipleExclusivePredicate(table.getMediationsMatrixFromRolesTable(), frame.getTheModelBar().getOntoUMLParser(), cardinality);
		
		frame.getTheConsolePanel().write(exclusivePred+"\n\n"+overlappingPred+"\n\n"+customPred);
		frame.ShowConsole();
	}
	
	/**
	 * Generate OCL Solution.
	 * 
	 * @param event
	 */
	public void GenerateOCLSolutionActionPerformed(ActionEvent event)
	{		
		/*Boolean exclusive = cbxExclusive.isSelected();
		Boolean overlapping = cbxOverlapping.isSelected();
		Boolean custom = cbxCustomizedExclusive.isSelected();
				
		String exclusiveConstraint = new String();
		String overlappingConstraint = new String();
		String customConstraint = new String();	
		
		int cardinality = (Integer)spinScope.getValue();
		
		if(exclusive) exclusivePred = rwor.generateAllMultipleExclusivePredicate(frame.getTheModelsBar().getOntoUMLParser(), cardinality);
		if(overlapping) overlappingPred = rwor.generateOverlappingPredicate(frame.getTheModelsBar().getOntoUMLParser(), cardinality);
		if(custom) customPred = rwor.generateMultipleExclusivePredicate(table.getMediationsMatrixFromRolesTable(), frame.getTheModelsBar().getOntoUMLParser(), cardinality);
		
		frame.getTheConsolePanel().write(exclusivePred+"\n\n"+overlappingPred+"\n\n"+customPred);
		frame.ShowConsole();*/
	}
}
