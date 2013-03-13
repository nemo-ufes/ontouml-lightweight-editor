package br.ufes.inf.nemo.move.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.move.mvc.controller.ACAntiPatternController;
import br.ufes.inf.nemo.move.mvc.controller.IAAntiPatternController;
import br.ufes.inf.nemo.move.mvc.controller.RBOSAntiPatternController;
import br.ufes.inf.nemo.move.mvc.controller.RSAntiPatternController;
import br.ufes.inf.nemo.move.mvc.controller.RWORAntiPatternController;
import br.ufes.inf.nemo.move.mvc.controller.STRAntiPatternController;
import br.ufes.inf.nemo.move.mvc.model.ACAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.AntiPatternListModel;
import br.ufes.inf.nemo.move.mvc.model.IAAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.RBOSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.RSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.RWORAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.STRAntiPatternModel;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.ui.util.TitleTextField;

/**
 * @author John Guerson
 */

public class AntiPatternListView extends JPanel {

	private static final long serialVersionUID = -4983514772261169354L;

	private AntiPatternListModel antipatternListModel;
	private TheFrame frame;
	
	private TitleTextField txtAntipatterns;
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
	public AntiPatternListView(AntiPatternListModel antipatternListModel, TheFrame frame) 
	{		
		this();
		
		this.frame = frame;
		
		setAntiPatternListModel(this.antipatternListModel);	
	}	
		
	/**
	 * Create the panel View.
	 */
	public AntiPatternListView() 
	{
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		txtAntipatterns = new TitleTextField();
		txtAntipatterns.setText("AntiPattern");
		
		tabbedPane = new JTabbedPane();		
		tabbedPane.setBackground(UIManager.getColor("Panel.background"));
		
		add(txtAntipatterns, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);		
	}
	
	public TheFrame getTheFrame()
	{
		return frame;
	}
	
	/**
	 * Set AntiPattern List Model.
	 */
	public void setAntiPatternListModel(AntiPatternListModel antipatternListModel)
	{		
		Clear();
		
		this.antipatternListModel = antipatternListModel;
		
		if (antipatternListModel != null)
		{
			if (antipatternListModel.getACListModel().size()>0)	{ createACTabPanel();	initializeACTabPanel(); }			
			if (antipatternListModel.getRBOSListModel().size()>0) {	createRBOSTabPanel(); initializeRBOSTabPanel(); }
			if (antipatternListModel.getRSListModel().size()>0) { createRSTabPanel(); initializeRSTabPanel(); }
			if (antipatternListModel.getSTRListModel().size()>0) { createSTRTabPanel(); initializeSTRTabPanel(); }
			if (antipatternListModel.getRWORListModel().size()>0) { createRWORTabPanel(); initializeRWORTabPanel(); }
			if (antipatternListModel.getIAListModel().size()>0) { createIATabPanel(); initializeIATabPanel(); }
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
		acTabPanel.setLayout(new GridLayout(antipatternListModel.getACListModel().size(), 1, 0, 0));
		
		for(ACAntiPatternModel acModel: antipatternListModel.getACListModel())
		{
			ACAntiPatternView acView =  new ACAntiPatternView(acModel, frame);
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
		rbosTabPanel.setLayout(new GridLayout(antipatternListModel.getRBOSListModel().size(), 1, 0, 0));
		
		for(RBOSAntiPatternModel rbosModel: antipatternListModel.getRBOSListModel())
		{
			RBOSAntiPatternView rbosView =  new RBOSAntiPatternView(rbosModel,frame);
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
		rsTabPanel.setLayout(new GridLayout(antipatternListModel.getRSListModel().size(), 1, 0, 0));
		
		for(RSAntiPatternModel rsModel: antipatternListModel.getRSListModel())
		{
			RSAntiPatternView rsView =  new RSAntiPatternView(rsModel,frame);		
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
		strTabPanel.setLayout(new GridLayout(antipatternListModel.getSTRListModel().size(), 1, 0, 0));
		
		for(STRAntiPatternModel strModel: antipatternListModel.getSTRListModel())
		{
			STRAntiPatternView strView =  new STRAntiPatternView(strModel,frame);
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
		rworTabPanel.setLayout(new GridLayout(antipatternListModel.getRWORListModel().size(), 1, 0, 0));
		
		for(RWORAntiPatternModel rworModel: antipatternListModel.getRWORListModel())
		{
			RWORAntiPatternView rworView =  new RWORAntiPatternView(rworModel,frame);	
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
		iaTabPanel.setLayout(new GridLayout(antipatternListModel.getIAListModel().size(), 1, 0, 0));
		
		for(IAAntiPatternModel iaModel: antipatternListModel.getIAListModel())
		{
			IAAntiPatternView iaView =  new IAAntiPatternView(iaModel,frame);
			@SuppressWarnings("unused")
			IAAntiPatternController iaController = new IAAntiPatternController(iaView,iaModel);
			iaTabPanel.add(iaView);			
		}
		iaTabPanel.validate();
		iaTabPanel.repaint();
	}	
	
}

	
	

