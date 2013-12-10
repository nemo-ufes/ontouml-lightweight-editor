package br.ufes.inf.nemo.oled.ui.antipattern;

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

import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverVariation1Occurrence;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;
import br.ufes.inf.nemo.antipattern.relover.RelOverOccurrence;
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
	private JPanel acTabPanel;
	private JPanel strTabPanel;
	private JPanel rsTabPanel;
	private JPanel rworTabPanel;
	private JPanel iaTabPanel;

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
				createACTabPanel();	
				initializeACTabPanel(); 
			}
			
			if (antipatternList.getRelSpec().getOccurrences().size()>0) { 
				createRSTabPanel(); 
				initializeRSTabPanel(); 
			}
			
			if (antipatternList.getBinOver().getOccurrences().size()>0) { 
				createSTRTabPanel(); 
				initializeSTRTabPanel(); 
			}
			if (antipatternList.getRelOver().getOccurrences().size()>0) { 
				createRWORTabPanel(); 
				initializeRWORTabPanel(); 
			}
			if (antipatternList.getImpAbs().getOccurrences().size()>0) { 
				createIATabPanel(); 
				initializeIATabPanel(); 
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
	 * Create Empty AC Tab Panel. 
	 */
	private void createACTabPanel()
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
	 * Create Empty RS Tab Panel. 
	 */
	private void createRSTabPanel()
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
	 * Create Empty STR Tab Panel. 
	 */
	private void createSTRTabPanel()
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
	 * Create Empty RWOR Tab Panel. 
	 */
	private void createRWORTabPanel()
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
	 * Create Empty IA Tab Panel. 
	 */
	private void createIATabPanel()
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
	 * Initialize AC Tab Panel from Anti Pattern List Model. 
	 */
	private void initializeACTabPanel()
	{
		acTabPanel.setLayout(new GridLayout(antipatternList.getAssCyc().getOccurrences().size(), 1, 0, 0));
		
		for(AssCycOccurrence acModel: antipatternList.getAssCyc().getOccurrences())
		{
			AssCycAntiPatternPane acView =  new AssCycAntiPatternPane(acModel, frame);
			@SuppressWarnings("unused")
			AssCycAntiPatternController acController = new AssCycAntiPatternController(acView,acModel);
			acTabPanel.add(acView);
		}
		acTabPanel.validate();
		acTabPanel.repaint();
	}
	
	/** 
	 * Initialize RS Tab Panel from Anti Pattern List Model.
	 */
	private void initializeRSTabPanel()
	{
		rsTabPanel.setLayout(new GridLayout(antipatternList.getRelSpec().getOccurrences().size(), 1, 0, 0));
		
		for(RelSpecOccurrence rsModel: antipatternList.getRelSpec().getOccurrences())
		{
			RelSpecAntiPatternPane rsView =  new RelSpecAntiPatternPane(rsModel,frame);		
			@SuppressWarnings("unused")
			RelSpecAntiPatternController rsController = new RelSpecAntiPatternController(rsView,rsModel);
			rsTabPanel.add(rsView);						
		}
		rsTabPanel.validate();
		rsTabPanel.repaint();
	}		

	/** 
	 * Initialize STR Tab Panel from Anti Pattern List Model. 
	 */
	private void initializeSTRTabPanel()
	{
		strTabPanel.setLayout(new GridLayout(antipatternList.getBinOver().getOccurrences().size(), 1, 0, 0));
		
		for(BinOverOccurrence strModel: antipatternList.getBinOver().getOccurrences())
		{
			if(strModel instanceof BinOverVariation1Occurrence){
				BinOverAntiPatternPane strView =  new BinOverAntiPatternPane((BinOverVariation1Occurrence) strModel,frame);
				@SuppressWarnings("unused")
				BinOverAntiPatternController strController = new BinOverAntiPatternController(strView,(BinOverVariation1Occurrence) strModel);
				strTabPanel.add(strView);
			}
		}
		strTabPanel.validate();
		strTabPanel.repaint();
	}	
	
	/** 
	 * Initialize RWOR Tab Panel from Anti Pattern List Model. 
	 */
	public void initializeRWORTabPanel()
	{
		rworTabPanel.setLayout(new GridLayout(antipatternList.getRelOver().getOccurrences().size(), 1, 0, 0));
		
		for(RelOverOccurrence rworModel: antipatternList.getRelOver().getOccurrences())
		{
			RelOverAntiPatternPane rworView =  new RelOverAntiPatternPane(rworModel,frame);	
			@SuppressWarnings("unused")
			RelOverAntiPatternController rworController = new RelOverAntiPatternController(rworView,rworModel);
			rworTabPanel.add(rworView);					
		}
		
		rworTabPanel.validate();
		rworTabPanel.repaint();
	}
	
	
	/** 
	 * Initialize IA Tab Panel from Anti Pattern List Model.
	 */
	public void initializeIATabPanel()
	{
		iaTabPanel.setLayout(new GridLayout(antipatternList.getImpAbs().getOccurrences().size(), 1, 0, 0));
		
		for(ImpAbsOccurrence iaModel: antipatternList.getImpAbs().getOccurrences())
		{
			ImpAbsAntiPatternPane iaView =  new ImpAbsAntiPatternPane(iaModel,frame);
			@SuppressWarnings("unused")
			ImpAbsAntiPatternController iaController = new ImpAbsAntiPatternController(iaView,iaModel);
			iaTabPanel.add(iaView);			
		}
		iaTabPanel.validate();
		iaTabPanel.repaint();
	}
}