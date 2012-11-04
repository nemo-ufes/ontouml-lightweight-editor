package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AntiPatternPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtAntipatterns;

	private JTabbedPane tabbedPane;
	
	private JPanel acTabPanel;
	private JPanel strTabPanel;
	private JPanel rbosTabPanel;
	private JPanel iaTabPanel;
	private JPanel rsTabPanel;
	private JPanel rworTabPanel;
	
	public void createACTabPanel()
	{
		acTabPanel = new JPanel();		
		tabbedPane.add(acTabPanel);	
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(acTabPanel),"AC : Association Cycle");
	}

	public void createSTRTabPanel()
	{
		strTabPanel = new JPanel();		
		tabbedPane.add(strTabPanel);		
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(strTabPanel),"STR : Self-Type Relationship");
	}
	
	public void createRBOSTabPanel()
	{
		rbosTabPanel = new JPanel();		
		tabbedPane.add(rbosTabPanel);		
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(rbosTabPanel),"RBOS : Relation Between Overlapping SubTypes");
	}
	
	public void createIATabPanel()
	{
		iaTabPanel = new JPanel();		
		tabbedPane.add(iaTabPanel);		
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(iaTabPanel),"IA : Imprecise Abstraction");		
	}
	
	public void createRSTabPanel()
	{
		rsTabPanel = new JPanel();		
		tabbedPane.add(rsTabPanel);		
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(rsTabPanel),"RS : Relation Specialization");		
	}
	
	public void createRWORTabPanel()
	{
		rworTabPanel = new JPanel();		
		tabbedPane.add(rworTabPanel);		
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(rworTabPanel),"RWOR : Relator With Overlapping Roles");		
	}
		
	/**
	 * Create the panel.
	 */
	public AntiPatternPanel() 
	{
		setLayout(new BorderLayout(0, 0));
		
		txtAntipatterns = new JTextField();
		txtAntipatterns.setText("AntiPatterns");
		txtAntipatterns.setHorizontalAlignment(SwingConstants.CENTER);
		txtAntipatterns.setForeground(Color.WHITE);
		txtAntipatterns.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAntipatterns.setEditable(false);
		txtAntipatterns.setColumns(10);
		txtAntipatterns.setBackground(Color.BLACK);
		
		tabbedPane = new JTabbedPane();		
		
		add(txtAntipatterns, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);		
		
		createACTabPanel();
		createSTRTabPanel();
		createRBOSTabPanel();
		createIATabPanel();
		createRSTabPanel();
		createRWORTabPanel();
	}

}
