package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.ontouml.antipattern.ACAntiPattern;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * @author John Guerson
 */

public class SingleACPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextPane txtClassCycle;
	
	private JCheckBox cbxOpenCycle;	
	private JCheckBox cbxClosedCycle;
	
	private JButton btnGenerateAlloy;	
	private JButton btnGenerateOclSolution;
	
	@SuppressWarnings("unused")
	private ACAntiPattern ac;
	
	@SuppressWarnings("unused")
	private TheFrame frame;
	
	/**
	 * Create a Single AC AntiPattern Panel.
	 * 
	 * @param rbos
	 */
	public SingleACPanel(ACAntiPattern ac, TheFrame frame)
	{
		this();
		
		this.ac = ac;
		this.frame = frame;
		
		int i=1;
		String resultBuffer= new String();
		for (RefOntoUML.Class c : ac.getCycle())
		{			
			if (i != ac.getCycle().size()) resultBuffer += c.getName()+"->";
			else resultBuffer += c.getName();
			i++;
		}		
		txtClassCycle.setText(resultBuffer);		
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
	public SingleACPanel() 
	{
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "AC #1", TitledBorder.LEFT, TitledBorder.BELOW_TOP, null, Color.RED));
		setPreferredSize(new Dimension(330, 218));

		txtClassCycle = new JTextPane();
		txtClassCycle.setEditable(false);
		txtClassCycle.setBorder(new LineBorder(Color.GRAY, 1, true));
		
		JLabel lblClassCycle = new JLabel("Class Cycle");
		lblClassCycle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel cbxPanel = new JPanel();		
		JPanel btnPanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
						.addComponent(cbxPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
						.addComponent(lblClassCycle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
						.addComponent(txtClassCycle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
					.addGap(13))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(lblClassCycle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtClassCycle, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		
		btnGenerateAlloy = new JButton("Generate Alloy");
		btnGenerateAlloy.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			//not implemented yet...
       		}
       	});
		btnPanel.add(btnGenerateAlloy);
		
		btnGenerateOclSolution = new JButton("Generate OCL Solution");
		btnGenerateOclSolution.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			//not implemented yet...
       		}
       	});
		btnPanel.add(btnGenerateOclSolution);
		
		cbxOpenCycle = new JCheckBox("Open Cycle");		
		cbxPanel.add(cbxOpenCycle);
		
		cbxClosedCycle = new JCheckBox("Closed Cycle");
		cbxPanel.add(cbxClosedCycle);
		
		setLayout(groupLayout);

	}
}
