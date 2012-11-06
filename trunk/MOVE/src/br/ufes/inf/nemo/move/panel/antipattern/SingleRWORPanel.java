package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.ontouml.antipattern.RWORAntiPattern;

/**
 * @author John Guerson
 */

public class SingleRWORPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField textRelator;
	
	private JTable table;

	private JButton btnGenerateAlloy;
	private JButton btnGenerateOclSolutio;
	
	private JCheckBox chckbxFree;	
	private JCheckBox chckbxOverlapping;
	private JCheckBox chckbxCustom;;
	
	@SuppressWarnings("unused")
	private RWORAntiPattern rwor;
	
	@SuppressWarnings("unused")
	private TheFrame frame;
	
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
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "RWOR #1", TitledBorder.LEFT, TitledBorder.BELOW_TOP, null, Color.RED));
		
		setPreferredSize(new Dimension(330, 329));
		
		textRelator = new JTextField();
		textRelator.setBackground(Color.WHITE);
		textRelator.setEditable(false);
		textRelator.setColumns(10);
		
		JLabel lblRelator = new JLabel("Relator:");
		
		btnGenerateAlloy = new JButton("Generate Alloy");		
		btnGenerateOclSolutio = new JButton("Generate OCL Solution");
		
		chckbxFree = new JCheckBox("Exclusive");		
		chckbxOverlapping = new JCheckBox("Overlapping");		
		chckbxCustom = new JCheckBox("Customized Exclusive");
						
		table = new JTable();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chckbxFree)
							.addGap(18)
							.addComponent(chckbxOverlapping)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxCustom))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnGenerateAlloy)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGenerateOclSolutio))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblRelator)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textRelator, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
							.addGap(18)))
					.addGap(3))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textRelator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRelator))
					.addGap(18)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxFree)
						.addComponent(chckbxOverlapping)
						.addComponent(chckbxCustom))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGenerateAlloy)
						.addComponent(btnGenerateOclSolutio))
					.addGap(37))
		);
		setLayout(groupLayout);

	}
}
