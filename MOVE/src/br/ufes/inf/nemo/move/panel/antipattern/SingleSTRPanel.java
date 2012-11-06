package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.ontouml.antipattern.STRAntiPattern;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JCheckBox cbxNontransitive ;
	
	private JButton btnGenerateAlloy;
	private JButton btnGenerateOclSolution;
	
	@SuppressWarnings("unused")
	private STRAntiPattern str;
	
	@SuppressWarnings("unused")
	private TheFrame frame;
	
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	
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
		setPreferredSize(new Dimension(330, 224));
		
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
		
		panel = new JPanel();
		
		panel_1 = new JPanel();
		
		panel_2 = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblAssociation)
								.addComponent(lblType, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtAssociation, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
								.addComponent(txtType, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))))
					.addGap(22))
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
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(150))
		);
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
       			//not implemented yet...
       		}
       	});		
		panel.add(btnGenerateAlloy);
		
		btnGenerateOclSolution = new JButton("Generate OCL Solution");
		btnGenerateOclSolution.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			//not implemented yet...
       		}
       	});		
		panel.add(btnGenerateOclSolution);
		
		setLayout(groupLayout);
	}
}
