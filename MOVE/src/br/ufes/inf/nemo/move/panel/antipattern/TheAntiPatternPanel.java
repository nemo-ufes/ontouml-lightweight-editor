package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.ontouml.antipattern.ACAntiPattern;
import br.ufes.inf.nemo.ontouml.antipattern.IAAntiPattern;
import br.ufes.inf.nemo.ontouml.antipattern.RBOSAntiPattern;
import br.ufes.inf.nemo.ontouml.antipattern.RSAntiPattern;
import br.ufes.inf.nemo.ontouml.antipattern.RWORAntiPattern;
import br.ufes.inf.nemo.ontouml.antipattern.STRAntiPattern;

import java.awt.GridLayout;
import javax.swing.UIManager;

/**
 * @author John Guerson
 */

public class TheAntiPatternPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtAntipatterns;

	private JTabbedPane tabbedPane;	
	private TheFrame frame;
	
	private JPanel acTabPanel;
	private JPanel strTabPanel;
	private JPanel rbosTabPanel;
	private JPanel iaTabPanel;
	private JPanel rsTabPanel;
	private JPanel rworTabPanel;
	
	private ArrayList<SingleRBOSPanel> singleRBOS = new ArrayList<SingleRBOSPanel>();
	private ArrayList<SingleRSPanel> singleRS = new ArrayList<SingleRSPanel>();
	private ArrayList<SingleSTRPanel> singleSTR = new ArrayList<SingleSTRPanel>();
	private ArrayList<SingleACPanel> singleAC = new ArrayList<SingleACPanel>();
	private ArrayList<SingleIAPanel> singleIA = new ArrayList<SingleIAPanel>();
	private ArrayList<SingleRWORPanel> singleRWOR = new ArrayList<SingleRWORPanel>();
	
	/**
	 * Create the AntiPatern Panel
	 * @param frame
	 */
	public TheAntiPatternPanel(TheFrame frame)
	{
		this();		
		
		this.frame = frame;
	}
	
	/**
	 * Create the panel.
	 */
	public TheAntiPatternPanel() 
	{
		setBackground(Color.WHITE);
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
		tabbedPane.setBackground(UIManager.getColor("Panel.background"));
		
		add(txtAntipatterns, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);		
		
		//createACTabPanel();
		//createSTRTabPanel();
		//createRBOSTabPanel();
		//createIATabPanel();
		//createRSTabPanel();
		//createRWORTabPanel();
	}
	
	/**
	 * Clear the tabbed Pane.
	 */
	public void Clear()
	{
		tabbedPane.removeAll();
	}
	
	/** 
	 * Create Empty AC Tab Panel. 
	 */
	public void createACTabPanel()
	{
		acTabPanel = new JPanel();			
		JScrollPane acScrollPane = new JScrollPane();
		acScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		acScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		acScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		acScrollPane.setViewportView(acTabPanel);		
		tabbedPane.add(acScrollPane);	
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(acScrollPane),"AC: Association Cycle");
	}

	/** 
	 * Create Empty STR Tab Panel. 
	 */
	public void createSTRTabPanel()
	{
		strTabPanel = new JPanel();
		JScrollPane strScrollPane = new JScrollPane();
		strScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		strScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		strScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		strScrollPane.setViewportView(strTabPanel);
		tabbedPane.add(strScrollPane);		
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(strScrollPane),"STR: Self-Type Relationship");
	}
	
	/** 
	 * Create Empty RBOS Tab Panel. 
	 */
	public void createRBOSTabPanel()
	{
		rbosTabPanel = new JPanel();		
		JScrollPane rbosScrollPane = new JScrollPane();
		rbosScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		rbosScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		rbosScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		rbosScrollPane.setViewportView(rbosTabPanel);		
		tabbedPane.add(rbosScrollPane);		
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(rbosScrollPane),"RBOS: Relation Between Overlapping SubTypes");
	}
	
	/** 
	 * Create Empty IA Tab Panel. 
	 */
	public void createIATabPanel()
	{
		iaTabPanel = new JPanel();		
		JScrollPane iaScrollPane = new JScrollPane();
		iaScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		iaScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		iaScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		iaScrollPane.setViewportView(iaTabPanel);
		tabbedPane.add(iaScrollPane);				
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(iaScrollPane),"IA: Imprecise Abstraction");		
	}
	
	/** 
	 * Create Empty RS Tab Panel. 
	 */
	public void createRSTabPanel()
	{
		rsTabPanel = new JPanel();
		JScrollPane rsScrollPane = new JScrollPane();
		rsScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		rsScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		rsScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		rsScrollPane.setViewportView(rsTabPanel);
		tabbedPane.add(rsScrollPane);						
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(rsScrollPane),"RS: Relation Specialization");		
	}
	
	/** 
	 * Create Empty RWOR Tab Panel. 
	 */
	public void createRWORTabPanel()
	{
		rworTabPanel = new JPanel();		
		JScrollPane rworScrollPane = new JScrollPane();
		rworScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		rworScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		rworScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		rworScrollPane.setViewportView(rworTabPanel);
		tabbedPane.add(rworScrollPane);		
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(rworScrollPane),"RWOR: Relator With Overlapping Roles");		
	}
		
	/** 
	 * Initialize RBOS Tab Panel from a RBOS AntiPattern Array List. 
	 */
	public void initializeRBOSTabPanel(ArrayList<RBOSAntiPattern> rboslist)
	{
		rbosTabPanel.setLayout(new GridLayout(rboslist.size(), 1, 0, 0));
		
		int i=1;
		for(RBOSAntiPattern rbos: rboslist)
		{
			SingleRBOSPanel singleRBOS =  new SingleRBOSPanel(rbos,this.frame);			
			rbosTabPanel.add(singleRBOS);
			
			singleRBOS.setTitleBorder("Relation Between Overlapping SubTypes : #"+i);
			i++;
			
			this.singleRBOS.add(singleRBOS);
		}
		rbosTabPanel.repaint();
	}	
	
	/** 
	 * Initialize RS Tab Panel from a RS AntiPattern Array List. 
	 */
	public void initializeRSTabPanel(ArrayList<RSAntiPattern> rslist)
	{
		rsTabPanel.setLayout(new GridLayout(rslist.size(), 1, 0, 0));
		
		int i=1;
		for(RSAntiPattern rs: rslist)
		{
			SingleRSPanel singleRS =  new SingleRSPanel(rs,this.frame);			
			rsTabPanel.add(singleRS);
			
			singleRS.setTitleBorder("Relation Specialization : #"+i);
			i++;
			
			this.singleRS.add(singleRS);
		}
		rsTabPanel.repaint();
	}
	
	/** 
	 * Initialize STR Tab Panel from a STR AntiPattern Array List. 
	 */
	public void initializeSTRTabPanel(ArrayList<STRAntiPattern> strlist)
	{
		strTabPanel.setLayout(new GridLayout(strlist.size(), 1, 0, 0));
		
		int i=1;
		for(STRAntiPattern str: strlist)
		{
			SingleSTRPanel singleSTR =  new SingleSTRPanel(str,this.frame);			
			strTabPanel.add(singleSTR);
			
			singleSTR.setTitleBorder("Self-Type Relationship : #"+i);
			i++;
			
			this.singleSTR.add(singleSTR);
		}
		strTabPanel.repaint();
	}
	
	/** 
	 * Initialize AC Tab Panel from a AC AntiPattern Array List. 
	 */
	public void initializeACTabPanel(ArrayList<ACAntiPattern> aclist)
	{
		acTabPanel.setLayout(new GridLayout(aclist.size(), 1, 0, 0));
		
		int i=1;
		for(ACAntiPattern ac: aclist)
		{
			SingleACPanel singleAC =  new SingleACPanel(ac,this.frame);			
			acTabPanel.add(singleAC);
			
			singleAC.setTitleBorder("Association Cycle : #"+i);
			i++;
			
			this.singleAC.add(singleAC);
		}
		acTabPanel.repaint();
	}
	
	/** 
	 * Initialize IA Tab Panel from a IA AntiPattern Array List. 
	 */
	public void initializeIATabPanel(ArrayList<IAAntiPattern> ialist)
	{
		iaTabPanel.setLayout(new GridLayout(ialist.size(), 1, 0, 0));
		
		int i=1;
		for(IAAntiPattern ia: ialist)
		{
			SingleIAPanel singleIA =  new SingleIAPanel(ia,this.frame);			
			iaTabPanel.add(singleIA);
			
			singleIA.setTitleBorder("Imprecise Abstraction : #"+i);
			i++;
			
			this.singleIA.add(singleIA);
		}
		iaTabPanel.repaint();
	}
	
	/** 
	 * Initialize RWOR Tab Panel from a RWOR AntiPattern Array List. 
	 */
	public void initializeRWORTabPanel(ArrayList<RWORAntiPattern> rworlist)
	{
		rworTabPanel.setLayout(new GridLayout(rworlist.size(), 1, 0, 0));
		
		int i=1;
		for(RWORAntiPattern rwor: rworlist)
		{
			SingleRWORPanel singleRWOR =  new SingleRWORPanel(rwor,this.frame);			
			rworTabPanel.add(singleRWOR);
			
			singleRWOR.setTitleBorder("Relator With Overlapping Roles : #"+i);
			i++;
			
			this.singleRWOR.add(singleRWOR);
		}
		rworTabPanel.repaint();
	}
	
	
}
