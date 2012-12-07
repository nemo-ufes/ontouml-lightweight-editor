package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 * @author John Guerson
 */

public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;
		
	private TheToolBar toolBar;	
	private TheMenuBar menuBar;	
	private TheConsole console;	
	private JSplitPane mainSplitPane;
	private JSplitPane innerSplitPane;	
	private JSplitPane centerSplitPane;	
	private TheManager appmanager;	
		
	/**
	 * Constructor.
	 * 
	 * @param model
	 * @param oclConstraints
	 * @param alsPath
	 * @throws IOException
	 */
	public TheFrame (RefOntoUML.Package model, String oclConstraints, String alsPath) throws IOException
	{
		this();		
		
		appmanager.setManager(this,model,oclConstraints,alsPath);
		
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
	}	
	
	/**
	 * Constructor.
	 */
	public TheFrame() 
	{
		super();						
			
		getContentPane().setBackground(new Color(230, 230, 250));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		menuBar = new TheMenuBar(this);
		setJMenuBar(menuBar);		
		
		toolBar = new TheToolBar(this);
		
		JPanel headpanel = new JPanel();
		headpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		headpanel.setLayout(new BorderLayout(0, 0));
		headpanel.add(BorderLayout.NORTH,toolBar);		
		
		getContentPane().add(BorderLayout.NORTH,headpanel);
				
		appmanager = new TheManager(this);
		
		innerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,appmanager.getOCLView(),appmanager.getAntiPatternListView());
		innerSplitPane.setOneTouchExpandable(true);		
		innerSplitPane.setDividerLocation(1.0);
		
		centerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,appmanager.getOntoUMLView(),innerSplitPane);
		centerSplitPane.setOneTouchExpandable(true);		
		centerSplitPane.setDividerLocation(0.50);
		
		console = new TheConsole();		
		
		mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,centerSplitPane,console);
		mainSplitPane.setOneTouchExpandable(true);		
		mainSplitPane.setDividerLocation(1.0);	
		
		getContentPane().add(BorderLayout.CENTER,mainSplitPane);				
					
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TheFrame.class.getResource("/resources/br/ufes/inf/nemo/move/window.png")));
		setTitle("OntoUML Model Validation Environment - MOVE");
	
		pack();
		
		restoreDefaults();
	}
		
	/** Restore default sizes of the split panes. */
	private void restoreDefaults() 
	{
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
            	mainSplitPane.setDividerLocation(1.0);            	
            	innerSplitPane.setDividerLocation(1.0);
            }
        });
    }
	
	/** Get Manager. */
	public TheManager getManager(){
		return appmanager;
	}
	
	/** Get Console Panel. */
	public TheConsole getConsole() { 
		return console; 
	}
	/** Get Tool Bar */
	public TheToolBar getToolBar() { 
		return toolBar; 
	}
	/** Get Menu Bar */
	public TheMenuBar getTheMenuBar() { 
		return menuBar; 
	}
	
	/**
	 * Show Or Hide AntiPAttern View...
	 */
	public void ShowOrHideAntiPatternView()
	{
		int location = innerSplitPane.getDividerLocation();
		int maxLocation = innerSplitPane.getMaximumDividerLocation();
		if(location < maxLocation)
	    {
			innerSplitPane.setDividerLocation(1.0);	
	    }
	    else
	    {
	      	innerSplitPane.setDividerLocation(0.50);
	    }
	}
	
	/** Show AntiPattern View */
	public void ShowAntiPatternView() { 
		innerSplitPane.setDividerLocation(0.50); 
	}
	/** Hide AntiPattern View */
	public void HideAntiPatternView() { 
		innerSplitPane.setDividerLocation(1.00); 
	}
	
	/**
	 * Show Or Hide Console...
	 */
	public void ShowOrHideConsole()
	{
		int location = mainSplitPane.getDividerLocation();
		int maxLocation = mainSplitPane.getMaximumDividerLocation();
		if(location < maxLocation)
	    {
			mainSplitPane.setDividerLocation(1.0);	
	    }
	    else
	    {
	      	mainSplitPane.setDividerLocation(0.60);
	    }
	}	
	
	/** Show Console */
	public void ShowConsole() { 
		mainSplitPane.setDividerLocation(0.70); 
	}
	/** Hide Console */
	public void HideConsole() { 
		mainSplitPane.setDividerLocation(1.00); 
	}
	
}
