package br.ufes.inf.nemo.oled.ui.antipattern;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.antipattern.relover.RelOverAntipattern;
import br.ufes.inf.nemo.oled.ui.AppFrame;

/**
 * @author John Guerson
 */

public class RWORAntiPatternPane extends JPanel {

	private static final long serialVersionUID = -462975341854464169L;

	@SuppressWarnings("unused")
	private RelOverAntipattern rwor;
		
	private RoleTable table;	
	
	private AppFrame parent;	
	private JTextField textRelator;
	private JPanel tablePane;
	private JScrollPane scrollPane;		
	private JButton btnGenerateAlloy;
	private JButton btnGenerateOclSolution;	
	private JCheckBox cbxExclusive;
	private JCheckBox cbxOverlapping;
	private JCheckBox cbxExclusiveFromTable;	
	private JSpinner spinScope;
	private JLabel lblatLeast;
	private JLabel lblRelationWithOverlapping;
	private JLabel lblScope;
	private JButton btnAdd;				
	private JCheckBox cbxDisjoint;
	private JCheckBox cbxDisjFromTable;
	
	/**
	 * Constructor.
	 * 
	 * @param rwor
	 * @param parent
	 */
	public RWORAntiPatternPane(RelOverAntipattern rwor, AppFrame parent)
	{
		this();
		
		this.rwor = rwor;
		this.parent= parent;				
		
		textRelator.setText(rwor.getRelator().getName());		
				
		table.setRWORAntiPatternModel(rwor);		
		table.setPreferredSize(new Dimension(table.getRowCount()*20,table.getColumnCount()*50));	
		
		lblRelationWithOverlapping.setText("Relator With Overlapping Roles");
		lblScope.setText("\""+rwor.getRelator().getName()+"\""+" Scope:");
	}	

	/**
	 * Constructor.
	 */
	public RWORAntiPatternPane() 
	{
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "", TitledBorder.RIGHT, TitledBorder.BELOW_TOP, null, new Color(255, 0, 0)));
		
		JLabel lblRelator = new JLabel("Relator:");		
		JLabel lblDisjointsRoles = new JLabel("Customizing Disjoints Roles:");
		
		textRelator = new JTextField();
		textRelator.setBackground(Color.WHITE);
		textRelator.setEditable(false);
		textRelator.setColumns(10);
		
		JPanel checkPane = new JPanel();		
		JPanel btnPane = new JPanel();
		
		btnGenerateAlloy = new JButton("Execute With Analyzer");		
		btnPane.add(btnGenerateAlloy);
		
		btnGenerateOclSolution = new JButton("OCL Solution");		
		btnPane.add(btnGenerateOclSolution);
		checkPane.setLayout(new GridLayout(2, 3, 0, 0));
		
		cbxExclusive = new JCheckBox("Exclusive");
		checkPane.add(cbxExclusive);
		
		cbxExclusiveFromTable = new JCheckBox("Exclusive from Table");
		checkPane.add(cbxExclusiveFromTable);
		
		cbxOverlapping = new JCheckBox("Overlapping");
		checkPane.add(cbxOverlapping);
		
		cbxDisjoint = new JCheckBox("Disjoint");
		checkPane.add(cbxDisjoint);
		
		cbxDisjFromTable = new JCheckBox("Disjoint from Table");
		checkPane.add(cbxDisjFromTable);
		
		tablePane = new JPanel();
		tablePane.setBackground(Color.WHITE);
		tablePane.setLayout(new BorderLayout(0, 0));
		
		JPanel scopePanel = new JPanel();
		
		lblRelationWithOverlapping = new JLabel("Relator With Overlapping Roles");
		lblRelationWithOverlapping.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnAdd = new JButton("Add");
		btnAdd.setFocusable(false);
				
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnPane, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
								.addComponent(scopePanel, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
							.addGap(21))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblRelator)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textRelator, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
								.addComponent(lblRelationWithOverlapping, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDisjointsRoles, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
									.addGap(7)
									.addComponent(btnAdd))
								.addComponent(tablePane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
								.addComponent(checkPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 405, Short.MAX_VALUE))
							.addGap(22))))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDisjointsRoles, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tablePane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkPane, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scopePanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		
		lblScope = new JLabel("Scope");
		scopePanel.add(lblScope);
		
		spinScope = new JSpinner();
		spinScope.setModel(new SpinnerNumberModel(new Integer(2), new Integer(0), null, new Integer(1)));
		spinScope.setPreferredSize(new Dimension(60, 20));
		scopePanel.add(spinScope);
		
		lblatLeast = new JLabel("(at least)");
		scopePanel.add(lblatLeast);
		
		scrollPane = new JScrollPane();
		tablePane.add(scrollPane, BorderLayout.CENTER);
		
		table = new RoleTable();		
		scrollPane.setViewportView(table);
		
		setLayout(groupLayout);			
	}
	
	public RoleTable getTableView()
	{
		return table;
	}
	
	public boolean isSelectedExclusive()
	{
		return cbxExclusive.isSelected();
	}
	
	public boolean isSelectedOverlapping()
	{
		return cbxOverlapping.isSelected();
	}

	public boolean isSelectedExclusiveFromTable()
	{
		return cbxExclusiveFromTable.isSelected();
	}
	
	public boolean isSelectedDisjoint()
	{
		return cbxDisjoint.isSelected();
	}
	
	public boolean isSelectedDisjointFromTable()
	{
		return cbxDisjFromTable.isSelected();
	}
	
	public Integer getScope()
	{
		return (Integer)spinScope.getModel().getValue();
	}

	public AppFrame getFrame()
	{
		return parent;
	}
	
	public void addEmptyEntryRoleTableListener(ActionListener actionListener) 
	{
		btnAdd.addActionListener(actionListener);
	}
	
	public void addExecuteWithAnalzyerListener(ActionListener actionListener) 
	{
		btnGenerateAlloy.addActionListener(actionListener);
	}

	public void addOCLSolutionListener(ActionListener actionListener) 
	{
		btnGenerateOclSolution.addActionListener(actionListener);
	}
}
