package br.ufes.inf.nemo.oled.antipattern;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

import br.ufes.inf.nemo.antipattern.RBOSAntiPattern;

import java.awt.GridLayout;

/**
 * @author John Guerson
 */

public class RBOSAntiPatternPane extends JPanel {

	private static final long serialVersionUID = 531726756362017458L;

	@SuppressWarnings("unused")
	private RBOSAntiPattern rbos;
	
	private Component parent;	
	private JTextField textSuperType;	
	private JTextField textAssociation;	
	private JTextField textSource;		
	private JTextField textTarget;	
	private JButton btnGenerateAlloy;	
	private JButton btnGenerateOclSolution;	
	private JLabel lblOverlapping;
	private JCheckBox cbxSymmetric;			
	private JCheckBox cbxAntiReflexive;			
	private JCheckBox cbxAntiSymmetric;	
	private JCheckBox cbxTransitive;			
	private JCheckBox cbxReflexive;			
	private JCheckBox cbxNonTransitive;	
	private JCheckBox cbxDisjoint;
	
	/**
	 * COnstructor.
	 * 
	 * @param rbos
	 * @param parent
	 */
	public RBOSAntiPatternPane(RBOSAntiPattern rbos, Component parent)
	{
		this();
		
		this.rbos = rbos;
		this.parent = parent;
		
		textSource.setText("  "+rbos.getSource().getName());
		textTarget.setText("  "+rbos.getTarget().getName());
		textSuperType.setText("  "+rbos.getSupertype().getName());
		textAssociation.setText("  "+rbos.getAssociation().getName());	
		
		lblOverlapping.setText("Overlapping Subtypes");
	}	
	
	/**
	 * Constructor.
	 */
	public RBOSAntiPatternPane() 
	{
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "", TitledBorder.RIGHT, TitledBorder.BELOW_TOP, null, new Color(255, 0, 0)));
		setPreferredSize(new Dimension(330, 312));
		
		JPanel btnPanel = new JPanel();
		
		btnGenerateAlloy = new JButton("Execute With Analyzer");	
		btnPanel.add(btnGenerateAlloy);
		
		btnGenerateOclSolution = new JButton("OCL Solution");				
		btnPanel.add(btnGenerateOclSolution);
		
		lblOverlapping = new JLabel("Overlapping Subtypes");
		lblOverlapping.setHorizontalAlignment(SwingConstants.CENTER);
		lblOverlapping.setText("Overlapping Subtypes ");
		
		JPanel textPanel = new JPanel();
		
		JPanel cbxPanel = new JPanel();
		cbxPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		cbxSymmetric = new JCheckBox("Symmetric ");
		cbxPanel.add(cbxSymmetric);		
		cbxReflexive = new JCheckBox("Reflexive");
		cbxPanel.add(cbxReflexive);		
		cbxTransitive = new JCheckBox("Transitive");
		cbxPanel.add(cbxTransitive);		
		cbxAntiSymmetric = new JCheckBox("AntiSymmetric");
		cbxPanel.add(cbxAntiSymmetric);		
		cbxAntiReflexive = new JCheckBox("AntiReflexive");
		cbxPanel.add(cbxAntiReflexive);		
		cbxNonTransitive = new JCheckBox("Non-Transitive");
		cbxPanel.add(cbxNonTransitive);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(cbxPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
						.addComponent(textPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
						.addComponent(lblOverlapping, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
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
					.addComponent(cbxPanel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		
		cbxDisjoint = new JCheckBox("Disjoint");
		cbxPanel.add(cbxDisjoint);
		
		textSuperType = new JTextField();
		textSuperType.setPreferredSize(new Dimension(30, 20));
		textSuperType.setBorder(new LineBorder(new Color(128, 128, 128)));
		textSuperType.setBackground(Color.WHITE);
		textSuperType.setEditable(false);		
		textSuperType.setColumns(10);	
		JLabel lblSupertype = new JLabel("SuperType:");
		
		textAssociation = new JTextField();
		textAssociation.setPreferredSize(new Dimension(30, 20));
		textAssociation.setBorder(new LineBorder(new Color(128, 128, 128)));
		textAssociation.setBackground(Color.WHITE);
		textAssociation.setEditable(false);		
		textAssociation.setColumns(10);	
		JLabel lblAssociation = new JLabel("Association:");
		
		textSource = new JTextField();
		textSource.setPreferredSize(new Dimension(30, 20));
		textSource.setBorder(new LineBorder(new Color(128, 128, 128)));
		textSource.setBackground(Color.WHITE);
		textSource.setEditable(false);		
		textSource.setColumns(10);	
		JLabel lblSubtype = new JLabel("Source:");
		
		textTarget = new JTextField();
		textTarget.setPreferredSize(new Dimension(30, 20));
		textTarget.setBorder(new LineBorder(new Color(128, 128, 128)));
		textTarget.setBackground(Color.WHITE);
		textTarget.setEditable(false);		
		textTarget.setColumns(10);		
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
	
	public boolean isSelectedDisjoint()
	{
		return cbxDisjoint.isSelected();
	}
	
	public boolean isSelectedIrreflexive()
	{
		return cbxAntiReflexive.isSelected();
	}
	
	public boolean isSelectedAntiSymmetric()
	{
		return cbxAntiSymmetric.isSelected();
	}

	public boolean isSelectedIntransitive()
	{
		return cbxNonTransitive.isSelected();
	}
	
	public boolean isSelectedReflexive()
	{
		return cbxReflexive.isSelected();
	}
	
	public boolean isSelectedSymmetric()
	{
		return cbxSymmetric.isSelected();
	}
	
	public boolean isSelectedTransitive()
	{
		return cbxTransitive.isSelected();
	}
	
	public Component getTheFrame()
	{
		return parent;
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
