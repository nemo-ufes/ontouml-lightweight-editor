package br.ufes.inf.nemo.oled.antipattern;

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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.oled.ui.AppFrame;

/**
 * @author John Guerson
 */

public class RelSpecAntiPatternPane extends JPanel {

	private static final long serialVersionUID = -7777729413155242695L;

	@SuppressWarnings("unused")
	private RelSpecOccurrence rs;
	
	private AppFrame frame;
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
	private JLabel lblRelationSpecialization;
	private JLabel lblGeneratePredicate;
	
	/**
	 * Constructor.
	 * 
	 * @param rs
	 * @param parent
	 */
	public RelSpecAntiPatternPane(RelSpecOccurrence rs, AppFrame parent) 
	{
		this();
		
		this.rs = rs;
		this.frame = parent;
		
		textGeneralSource.setText("  "+rs.getGeneralSource().getName());
		textGeneralTarget.setText("  "+rs.getGeneralTarget().getName());
		textGeneralAssociation.setText("  "+rs.getGeneral().getName());
		textSpecificAssociation.setText("  "+rs.getSpecific().getName());
		textSpecificSource.setText("  "+rs.getSpecificSource().getName());
		textSpecificTarget.setText("  "+rs.getSpecificTarget().getName());
		
		lblRelationSpecialization.setText("Relation Specialization");
	}	
	
	/**
	 * Constructor.
	 */
	public RelSpecAntiPatternPane() 
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
		btnPanel.add(btnGenerateAlloy);
		
		btnGenerateOclSolution = new JButton("OCL Solution");		
		btnPanel.add(btnGenerateOclSolution);
		
		JPanel generalPanel = new JPanel();
		generalPanel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "General", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel specificPanel = new JPanel();
		specificPanel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "Specific", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		
		lblRelationSpecialization = new JLabel("Relation Specialization ");
		lblRelationSpecialization.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblGeneratePredicate = new JLabel("Generate Predicate:");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxPanel2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(cbxPanel1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addGap(0, 0, Short.MAX_VALUE))
						.addComponent(generalPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(lblRelationSpecialization, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(specificPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(btnPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(lblGeneratePredicate, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
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
					.addComponent(lblGeneratePredicate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(cbxPanel2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxPanel1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblSpecificSource = new JLabel("Source:");		
		JLabel lblSpecificAssociation = new JLabel("Association:");
		JLabel lblSpecificTarget = new JLabel("Target:");
		
		textSpecificSource = new JTextField();	
		textSpecificSource.setPreferredSize(new Dimension(30, 20));
		textSpecificSource.setBorder(new LineBorder(new Color(128, 128, 128)));
		textSpecificSource.setBackground(Color.WHITE);
		textSpecificSource.setEditable(false);		
		textSpecificSource.setColumns(10);	
		
		textSpecificAssociation = new JTextField();		
		textSpecificAssociation.setPreferredSize(new Dimension(30, 20));
		textSpecificAssociation.setBorder(new LineBorder(new Color(128, 128, 128)));
		textSpecificAssociation.setBackground(Color.WHITE);
		textSpecificAssociation.setEditable(false);		
		textSpecificAssociation.setColumns(10);	
		
		textSpecificTarget = new JTextField();
		textSpecificTarget.setPreferredSize(new Dimension(30, 20));
		textSpecificTarget.setBorder(new LineBorder(new Color(128, 128, 128)));
		textSpecificTarget.setBackground(Color.WHITE);
		textSpecificTarget.setEditable(false);		
		textSpecificTarget.setColumns(10);	
		
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
		
		textGeneralSource = new JTextField();
		textGeneralSource.setPreferredSize(new Dimension(30, 20));
		textGeneralSource.setBorder(new LineBorder(new Color(128, 128, 128)));
		textGeneralSource.setBackground(Color.WHITE);
		textGeneralSource.setEditable(false);		
		textGeneralSource.setColumns(10);	
		
		textGeneralTarget = new JTextField();
		textGeneralTarget.setPreferredSize(new Dimension(30, 20));
		textGeneralTarget.setBorder(new LineBorder(new Color(128, 128, 128)));
		textGeneralTarget.setBackground(Color.WHITE);
		textGeneralTarget.setEditable(false);		
		textGeneralTarget.setColumns(10);	
		
		textGeneralAssociation = new JTextField();	
		textGeneralAssociation.setPreferredSize(new Dimension(30, 20));
		textGeneralAssociation.setBorder(new LineBorder(new Color(128, 128, 128)));
		textGeneralAssociation.setBackground(Color.WHITE);
		textGeneralAssociation.setEditable(false);		
		textGeneralAssociation.setColumns(10);	
		
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
	
	public boolean isSelectedDisjoint()
	{
		return cbxDisjoint.isSelected();
	}
	
	public boolean isSelectedNonSubsetting()
	{
		return cbxNonsubsetting.isSelected();
	}

	public boolean isSelectedRedefinition()
	{
		return cbxRedefinition.isSelected();
	}
	
	public boolean isSelectedSubType()
	{
		return cbxSubtype.isSelected();
	}
	
	public AppFrame getFrame()
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
}
