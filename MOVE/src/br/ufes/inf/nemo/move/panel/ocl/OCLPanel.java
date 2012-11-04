package br.ufes.inf.nemo.move.panel.ocl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class OCLPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtOclConstraints;

	private JTabbedPane tabbedPane;
	
	private JPanel ctTabPanel;
	
	public void createConstraintTabPanel()
	{
		ctTabPanel = new JPanel();		
		tabbedPane.add(ctTabPanel);	
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(ctTabPanel),"Constraints");
	}
	
	/**
	 * Create the panel.
	 */
	public OCLPanel() 
	{		
		setLayout(new BorderLayout(0, 0));
		
		txtOclConstraints = new JTextField();
		txtOclConstraints.setText("OCL Domain Constraints");
		txtOclConstraints.setHorizontalAlignment(SwingConstants.CENTER);
		txtOclConstraints.setForeground(Color.WHITE);
		txtOclConstraints.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtOclConstraints.setEditable(false);
		txtOclConstraints.setColumns(10);
		txtOclConstraints.setBackground(Color.BLACK);
		
		tabbedPane = new JTabbedPane();
		
		add(txtOclConstraints,BorderLayout.NORTH);
		add(tabbedPane,BorderLayout.CENTER);
		
		createConstraintTabPanel();
	}
}
