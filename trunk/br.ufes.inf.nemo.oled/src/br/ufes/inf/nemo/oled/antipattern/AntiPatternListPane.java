package br.ufes.inf.nemo.oled.antipattern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.antipattern.ACAntiPattern;
import br.ufes.inf.nemo.antipattern.IAAntiPattern;
import br.ufes.inf.nemo.antipattern.RBOSAntiPattern;
import br.ufes.inf.nemo.antipattern.RSAntiPattern;
import br.ufes.inf.nemo.antipattern.RWORAntiPattern;
import br.ufes.inf.nemo.antipattern.STRAntiPattern;
import br.ufes.inf.nemo.move.mvc.controller.ACAntiPatternController;
import br.ufes.inf.nemo.move.mvc.controller.IAAntiPatternController;
import br.ufes.inf.nemo.move.mvc.controller.RBOSAntiPatternController;
import br.ufes.inf.nemo.move.mvc.controller.RSAntiPatternController;
import br.ufes.inf.nemo.move.mvc.controller.RWORAntiPatternController;
import br.ufes.inf.nemo.move.mvc.controller.STRAntiPatternController;
import br.ufes.inf.nemo.oled.ui.AppFrame;

/**
 * @author John Guerson
 */

public class AntiPatternListPane extends JPanel {

	private static final long serialVersionUID = -4983514772261169354L;

	private AntiPatternList antipatternList;
	
	private AppFrame frame;	 
	private JTabbedPane tabbedPane;
	private JPanel acTabPanel;
	private JPanel strTabPanel;
	private JPanel rbosTabPanel;
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
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane();		
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setBackground(UIManager.getColor("Panel.background"));
		add(tabbedPane, BorderLayout.CENTER);		
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
			if (antipatternList.getACListModel().size()>0)	{ createACTabPanel();	initializeACTabPanel(); }			
			if (antipatternList.getRBOSListModel().size()>0) {	createRBOSTabPanel(); initializeRBOSTabPanel(); }
			if (antipatternList.getRSListModel().size()>0) { createRSTabPanel(); initializeRSTabPanel(); }
			if (antipatternList.getSTRListModel().size()>0) { createSTRTabPanel(); initializeSTRTabPanel(); }
			if (antipatternList.getRWORListModel().size()>0) { createRWORTabPanel(); initializeRWORTabPanel(); }
			if (antipatternList.getIAListModel().size()>0) { createIATabPanel(); initializeIATabPanel(); }
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
	 * Create Empty RBOS Tab Panel. 
	 */
	private void createRBOSTabPanel()
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
		acTabPanel.setLayout(new GridLayout(antipatternList.getACListModel().size(), 1, 0, 0));
		
		for(ACAntiPattern acModel: antipatternList.getACListModel())
		{
			ACAntiPatternPane acView =  new ACAntiPatternPane(acModel, frame);
			@SuppressWarnings("unused")
			ACAntiPatternController acController = new ACAntiPatternController(acView,acModel);
			acTabPanel.add(acView);
		}
		acTabPanel.validate();
		acTabPanel.repaint();
	}
	
	/** 
	 * Initialize RBOS Tab Panel from Anti Pattern List Model. 
	 */
	private void initializeRBOSTabPanel()
	{
		rbosTabPanel.setLayout(new GridLayout(antipatternList.getRBOSListModel().size(), 1, 0, 0));
		
		for(RBOSAntiPattern rbosModel: antipatternList.getRBOSListModel())
		{
			RBOSAntiPatternPane rbosView =  new RBOSAntiPatternPane(rbosModel,frame);
			@SuppressWarnings("unused")
			RBOSAntiPatternController rbosController = new RBOSAntiPatternController(rbosView,rbosModel);
			rbosTabPanel.add(rbosView);
		}
		rbosTabPanel.validate();
		rbosTabPanel.repaint();
	}
	
	/** 
	 * Initialize RS Tab Panel from Anti Pattern List Model.
	 */
	private void initializeRSTabPanel()
	{
		rsTabPanel.setLayout(new GridLayout(antipatternList.getRSListModel().size(), 1, 0, 0));
		
		for(RSAntiPattern rsModel: antipatternList.getRSListModel())
		{
			RSAntiPatternPane rsView =  new RSAntiPatternPane(rsModel,frame);		
			@SuppressWarnings("unused")
			RSAntiPatternController rsController = new RSAntiPatternController(rsView,rsModel);
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
		strTabPanel.setLayout(new GridLayout(antipatternList.getSTRListModel().size(), 1, 0, 0));
		
		for(STRAntiPattern strModel: antipatternList.getSTRListModel())
		{
			STRAntiPatternPane strView =  new STRAntiPatternPane(strModel,frame);
			@SuppressWarnings("unused")
			STRAntiPatternController strController = new STRAntiPatternController(strView,strModel);
			strTabPanel.add(strView);			
		}
		strTabPanel.validate();
		strTabPanel.repaint();
	}	
	
	/** 
	 * Initialize RWOR Tab Panel from Anti Pattern List Model. 
	 */
	public void initializeRWORTabPanel()
	{
		rworTabPanel.setLayout(new GridLayout(antipatternList.getRWORListModel().size(), 1, 0, 0));
		
		for(RWORAntiPattern rworModel: antipatternList.getRWORListModel())
		{
			RWORAntiPatternPane rworView =  new RWORAntiPatternPane(rworModel,frame);	
			@SuppressWarnings("unused")
			RWORAntiPatternController rworController = new RWORAntiPatternController(rworView,rworModel);
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
		iaTabPanel.setLayout(new GridLayout(antipatternList.getIAListModel().size(), 1, 0, 0));
		
		for(IAAntiPattern iaModel: antipatternList.getIAListModel())
		{
			IAAntiPatternPane iaView =  new IAAntiPatternPane(iaModel,frame);
			@SuppressWarnings("unused")
			IAAntiPatternController iaController = new IAAntiPatternController(iaView,iaModel);
			iaTabPanel.add(iaView);			
		}
		iaTabPanel.validate();
		iaTabPanel.repaint();
	}
}