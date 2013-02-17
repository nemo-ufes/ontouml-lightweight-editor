package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.move.utilities.Extractor;

import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

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
	private SimpleGUICustom analyzer;
		
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(TheFrame.class.getResource("/resources/icon/window.png")));
		setTitle("Model Validation Environment - MOVE");
					
		try {			
			Extractor.alloyAnalyzerJAR();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() 
	        {
	        	String[] args = {""};
	        	analyzer = new SimpleGUICustom(args,false,"");
	        }
		});
		
		pack();
		restoreDefaults();
				
		showFrame();		
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
	
	/** Get Alloy Analyzer.  */
	public SimpleGUICustom getAlloyAnalyzer()
	{
		return analyzer;
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
	 * Show Or Hide OCL View...
	 */
	public void ShowOrHideOCLView()
	{
		int location = innerSplitPane.getDividerLocation();
		int minLocation = innerSplitPane.getMinimumDividerLocation();
		if(location > minLocation)
	    {
			innerSplitPane.setDividerLocation(0.0);	
	    }
	    else
	    {
	      	innerSplitPane.setDividerLocation(1.00);
	    }
	}
	
	/** Show OCL View */
	public void ShowOCLView() { innerSplitPane.setDividerLocation(0.50); }
	
	/** Hide OCL View */
	public void HideOCLView() { innerSplitPane.setDividerLocation(0.0); }
	
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
	
	/**
	 * Shoe Error Message Dialog.
	 * 
	 * @param title
	 * @param message
	 */
	public void showErrorMessageDialog(String title, String message)
	{
		JOptionPane.showMessageDialog(
			this,message,title,JOptionPane.ERROR_MESSAGE,
			new ImageIcon(TheFrame.class.getResource("/resources/icon/delete-36x36.png"))
		);	
	}
	
	/**
	 * Show Warning Message Dialog.
	 * 
	 * @param title
	 * @param message
	 */
	public void showWarningMessageDialog(String title, String message)
	{
		JOptionPane.showMessageDialog(
			this,message,title,JOptionPane.WARNING_MESSAGE,
			new ImageIcon(TheFrame.class.getResource("/resources/icon/warning-36x36.png"))
		);	
	}
	
	/**
	 * Show Successful Message Dialog.
	 * 
	 * @param title
	 * @param message
	 */
	public void showSuccessfulMessageDialog(String title, String message)
	{
		JOptionPane.showMessageDialog(
			this,message,title,JOptionPane.INFORMATION_MESSAGE,
			new ImageIcon(TheFrame.class.getResource("/resources/icon/check-36x36.png"))
		);
	}
	
	/**
	 * Show Information Message Dialog.
	 * 
	 * @param title
	 * @param message
	 */
	public void showInformationMessageDialog(String title, String message)
	{
		JOptionPane.showMessageDialog(
			this,message,title,JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	/** Make the frame visible, non-iconized, and focused. */
    public void showFrame() 
    {
      setVisible(true);
      setExtendedState(getExtendedState() & ~JFrame.ICONIFIED);
      requestFocus();
      toFront();
    }
}
