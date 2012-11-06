package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.ontouml.antipattern.IAAntiPattern;

/**
 * @author John Guerson
 */

public class SingleIAPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField textAssociation;	
	private JTextField textTarget;
	private JTextField textSource;
	
	@SuppressWarnings("unused")
	private IAAntiPattern ia;
	@SuppressWarnings("unused")
	private TheFrame frame;
	
	/**
	 * Create a Single RWOR AntiPattern Panel.
	 * 
	 * @param rbos
	 */
	public SingleIAPanel(IAAntiPattern ia,TheFrame frame)
	{
		this();
		
		this.ia = ia;
		this.frame = frame;
		
		textAssociation.setText(ia.getAssociation().getName());
		textSource.setText(ia.getSource().getName());
		textTarget.setText(ia.getTarget().getName());
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
	public SingleIAPanel() 
	{
		setPreferredSize(new Dimension(330, 301));	
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "IA #1", TitledBorder.LEFT, TitledBorder.BELOW_TOP, null, Color.RED));
		
		JLabel lblSource = new JLabel("Source:");
		
		textSource = new JTextField();
		textSource.setBackground(Color.WHITE);
		textSource.setEditable(false);
		textSource.setColumns(10);
		
		JLabel lblTarget = new JLabel("Target:");
		
		JLabel lblAssociation = new JLabel("Association:");
		
		textAssociation = new JTextField();
		textAssociation.setBackground(Color.WHITE);
		textAssociation.setEditable(false);
		textAssociation.setColumns(10);
		
		textTarget = new JTextField();
		textTarget.setBackground(Color.WHITE);
		textTarget.setEditable(false);
		textTarget.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblTarget, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblAssociation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblSource, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textTarget, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
						.addComponent(textSource, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
						.addComponent(textAssociation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
					.addGap(28))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAssociation)
						.addComponent(textAssociation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSource)
						.addComponent(textSource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTarget)
						.addComponent(textTarget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(203, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
