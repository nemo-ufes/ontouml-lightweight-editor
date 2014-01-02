package br.ufes.inf.nemo.oled.antipattern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverVariation1Occurrence;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsAntipattern;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;
import br.ufes.inf.nemo.antipattern.relover.RelOverAntipattern;
import br.ufes.inf.nemo.antipattern.relover.RelOverOccurrence;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.oled.model.AntiPatternList;
import br.ufes.inf.nemo.oled.ui.AppFrame;
import br.ufes.inf.nemo.oled.util.IconLoader;

/**
 * @author John Guerson
 */

public class AntiPatternListPane extends JFrame {

	private static final long serialVersionUID = -4983514772261169354L;

	private AntiPatternList antipatternList;
	
	private AppFrame frame;	 
	private JTabbedPane tabbedPane;
	private JPanel assCycTabPanel;
	private JPanel binOverTabPanel;
	private JPanel relSpecTabPanel;
	private JPanel relOverTabPanel;
	private JPanel impAbsTabPanel;

	/**
	 * Create the panel View from a AntiPattern List Model.
	 */
	public AntiPatternListPane(AntiPatternList antipatternList, AppFrame frame) 
	{		
		this();
		
		this.frame = frame;
		
		setAntiPatternListModel(this.antipatternList);	
	}	
		
	/**
	 * Create the panel View.
	 */
	public AntiPatternListPane() 
	{		
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		setTitle("AntiPattern Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(IconLoader.getInstance().getImage("ANTIPATTERN"));
		setPreferredSize(new Dimension(500,500));
		
		tabbedPane = new JTabbedPane();		
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setBackground(UIManager.getColor("Panel.background"));
		add(tabbedPane, BorderLayout.CENTER);
		
		pack();
	}
	
	public AppFrame getFrame()
	{
		return frame;
	}
	
	/**
	 * Set AntiPattern List Model.
	 */
	public void setAntiPatternListModel(AntiPatternList antipatternList)
	{		
		Clear();
		
		this.antipatternList = antipatternList;
		
		if (antipatternList != null)
		{
			if (antipatternList.getAssCyc().getOccurrences().size()>0)	{ 
				createAssCycTabPanel();	
				initializeAssCycTabPanel(); 
			}
			
			if (antipatternList.getRelSpec().getOccurrences().size()>0) { 
				createRelSpecTabPanel(); 
				initializeRelSpecTabPanel(); 
			}
			
			if (antipatternList.getBinOver().getOccurrences().size()>0) { 
				createBinOverTabPanel(); 
				initializeBinOverTabPanel(); 
			}
			if (antipatternList.getRelOver().getOccurrences().size()>0) { 
				createRelOverTabPanel(); 
				initializeRelOverTabPanel(); 
			}
			if (antipatternList.getImpAbs().getOccurrences().size()>0) { 
				createImpAbsTabPanel(); 
				initializeImpAbsTabPanel(); 
			}
		}
	}
	
	/**
	 * Clear the tabbed Pane.
	 */
	public void Clear()
	{
		tabbedPane.removeAll();
		
		tabbedPane.validate();
		tabbedPane.repaint();
	}
	
	/** 
	 * Create Empty AssCyc Tab Panel. 
	 */
	private void createAssCycTabPanel()
	{
		assCycTabPanel = new JPanel();			
		JScrollPane acScrollPane = new JScrollPane();
		acScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		acScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		acScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		acScrollPane.setViewportView(assCycTabPanel);		
		tabbedPane.add(acScrollPane);	
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(acScrollPane),AssCycAntipattern.getAntipatternInfo().getAcronym()+": "+AssCycAntipattern.getAntipatternInfo().getName());
	}	
	
	/** 
	 * Create Empty RelSpec Tab Panel. 
	 */
	private void createRelSpecTabPanel()
	{
		relSpecTabPanel = new JPanel();
		JScrollPane relSpecScrollPane = new JScrollPane();
		relSpecScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		relSpecScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		relSpecScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		relSpecScrollPane.setViewportView(relSpecTabPanel);
		tabbedPane.add(relSpecScrollPane);						
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(relSpecScrollPane),RelSpecAntipattern.getAntipatternInfo().getAcronym()+": "+RelSpecAntipattern.getAntipatternInfo().getName());		
	}	

	/** 
	 * Create Empty BinOver Tab Panel. 
	 */
	private void createBinOverTabPanel()
	{
		binOverTabPanel = new JPanel();
		JScrollPane binOverScrollPane = new JScrollPane();
		binOverScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		binOverScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		binOverScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		binOverScrollPane.setViewportView(binOverTabPanel);
		tabbedPane.add(binOverScrollPane);		
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(binOverScrollPane),BinOverAntipattern.getAntipatternInfo().getAcronym()+": "+BinOverAntipattern.getAntipatternInfo().getName());
	}
	
	/** 
	 * Create Empty RelOver Tab Panel. 
	 */
	private void createRelOverTabPanel()
	{
		relOverTabPanel = new JPanel();		
		JScrollPane relOverScrollPane = new JScrollPane();
		relOverScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		relOverScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		relOverScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		relOverScrollPane.setViewportView(relOverTabPanel);
		tabbedPane.add(relOverScrollPane);		
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(relOverScrollPane),RelOverAntipattern.getAntipatternInfo().getAcronym()+": "+RelOverAntipattern.getAntipatternInfo().getName());		
	}
	
	/** 
	 * Create Empty ImpAbs Tab Panel. 
	 */
	private void createImpAbsTabPanel()
	{
		impAbsTabPanel = new JPanel();		
		JScrollPane impAbsScrollPane = new JScrollPane();
		impAbsScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		impAbsScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		impAbsScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		impAbsScrollPane.setViewportView(impAbsTabPanel);
		tabbedPane.add(impAbsScrollPane);				
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(impAbsScrollPane),ImpAbsAntipattern.getAntipatternInfo().getAcronym()+": "+ImpAbsAntipattern.getAntipatternInfo().getName());		
	}	
	
	/** 
	 * Initialize AssCyc Tab Panel from Anti Pattern List Model. 
	 */
	private void initializeAssCycTabPanel()
	{
		assCycTabPanel.setLayout(new GridLayout(antipatternList.getAssCyc().getOccurrences().size(), 1, 0, 0));
		
		for(AssCycOccurrence acModel: antipatternList.getAssCyc().getOccurrences())
		{
			AssCycAntiPatternPane acView =  new AssCycAntiPatternPane(acModel, frame);
			@SuppressWarnings("unused")
			AssCycAntiPatternController acController = new AssCycAntiPatternController(acView,acModel);
			assCycTabPanel.add(acView);
		}
		assCycTabPanel.validate();
		assCycTabPanel.repaint();
	}
	
	/** 
	 * Initialize RelSpec Tab Panel from Anti Pattern List Model.
	 */
	private void initializeRelSpecTabPanel()
	{
		relSpecTabPanel.setLayout(new GridLayout(antipatternList.getRelSpec().getOccurrences().size(), 1, 0, 0));
		
		for(RelSpecOccurrence rsModel: antipatternList.getRelSpec().getOccurrences())
		{
			RelSpecAntiPatternPane rsView =  new RelSpecAntiPatternPane(rsModel,frame);		
			@SuppressWarnings("unused")
			RelSpecAntiPatternController rsController = new RelSpecAntiPatternController(rsView,rsModel);
			relSpecTabPanel.add(rsView);						
		}
		relSpecTabPanel.validate();
		relSpecTabPanel.repaint();
	}		

	/** 
	 * Initialize BinOver Tab Panel from Anti Pattern List Model. 
	 */
	private void initializeBinOverTabPanel()
	{
		binOverTabPanel.setLayout(new GridLayout(antipatternList.getBinOver().getOccurrences().size(), 1, 0, 0));
		
		for(BinOverOccurrence strModel: antipatternList.getBinOver().getOccurrences())
		{
			if(strModel instanceof BinOverVariation1Occurrence){
				BinOverAntiPatternPane strView =  new BinOverAntiPatternPane((BinOverVariation1Occurrence) strModel,frame);
				@SuppressWarnings("unused")
				BinOverAntiPatternController strController = new BinOverAntiPatternController(strView,(BinOverVariation1Occurrence) strModel);
				binOverTabPanel.add(strView);
			}
		}
		binOverTabPanel.validate();
		binOverTabPanel.repaint();
	}	
	
	/** 
	 * Initialize RelOver Tab Panel from Anti Pattern List Model. 
	 */
	public void initializeRelOverTabPanel()
	{
		relOverTabPanel.setLayout(new GridLayout(antipatternList.getRelOver().getOccurrences().size(), 1, 0, 0));
		
		for(RelOverOccurrence RelOverModel: antipatternList.getRelOver().getOccurrences())
		{
			RelOverAntiPatternPane RelOverView =  new RelOverAntiPatternPane(RelOverModel,frame);	
			@SuppressWarnings("unused")
			RelOverAntiPatternController RelOverController = new RelOverAntiPatternController(RelOverView,RelOverModel);
			relOverTabPanel.add(RelOverView);					
		}
		
		relOverTabPanel.validate();
		relOverTabPanel.repaint();
	}
	
	
	/** 
	 * Initialize ImpAbs Tab Panel from Anti Pattern List Model.
	 */
	public void initializeImpAbsTabPanel()
	{
		impAbsTabPanel.setLayout(new GridLayout(antipatternList.getImpAbs().getOccurrences().size(), 1, 0, 0));
		
		for(ImpAbsOccurrence iaModel: antipatternList.getImpAbs().getOccurrences())
		{
			ImpAbsAntiPatternPane iaView =  new ImpAbsAntiPatternPane(iaModel,frame);
			@SuppressWarnings("unused")
			ImpAbsAntiPatternController iaController = new ImpAbsAntiPatternController(iaView,iaModel);
			impAbsTabPanel.add(iaView);			
		}
		impAbsTabPanel.validate();
		impAbsTabPanel.repaint();
	}
}