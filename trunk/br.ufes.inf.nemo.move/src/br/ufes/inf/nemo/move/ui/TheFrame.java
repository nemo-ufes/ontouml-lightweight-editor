package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.move.ui.util.Extractor;
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
	private JTabbedPane ontoumlTabbedPane;
	private JTabbedPane oclTabbedPane;
	private JTabbedPane antipatternPane;
	private JTabbedPane infoTabbedPane;
	private TheManager appmanager;	
	private SimpleGUICustom analyzer;
	private TheStatus statusBar;
	private TheProperties properties;
	private TheWarnings warnings;
	private TheErrors errors;
	
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
		
		oclTabbedPane = new JTabbedPane();
		oclTabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		oclTabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		oclTabbedPane.add(appmanager.getOCLView());	
		oclTabbedPane.setTitleAt(0,"OCL Editor");
		oclTabbedPane.setBackground(UIManager.getColor("Panel.background"));
		oclTabbedPane.setIconAt(0,new ImageIcon(TheFrame.class.getResource("/resources/icon/edit-ocl-16x16.png")));
		oclTabbedPane.setBorder(null);
		
		antipatternPane = new JTabbedPane();
		antipatternPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		antipatternPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		antipatternPane.add(appmanager.getAntiPatternListView());	
		antipatternPane.setTitleAt(0,"AntiPattern Manager");
		antipatternPane.setBackground(UIManager.getColor("Panel.background"));
		antipatternPane.setIconAt(0,new ImageIcon(TheFrame.class.getResource("/resources/icon/search-red-16x16.png")));
		antipatternPane.setBorder(null);
		
		innerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,oclTabbedPane,antipatternPane);
		innerSplitPane.setOneTouchExpandable(true);		
		innerSplitPane.setBorder(null);
		
		ontoumlTabbedPane = new JTabbedPane();
		ontoumlTabbedPane.setBorder(null);
		ontoumlTabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ontoumlTabbedPane.setPreferredSize(new Dimension(400,600));
		ontoumlTabbedPane.add(appmanager.getOntoUMLView());	
		ontoumlTabbedPane.setTitleAt(0,"OntoUML Explorer");
		ontoumlTabbedPane.setBackground(UIManager.getColor("Panel.background"));		
		//ontoumlTabbedPane.setIconAt(0,new ImageIcon(TheFrame.class.getResource("/resources/icon/model-16x16.jpg")));
		
		console = new TheConsole(this);	
		properties = new TheProperties(this);
		warnings = new TheWarnings(this);
		errors = new TheErrors(this);
		
		infoTabbedPane = new JTabbedPane();
		infoTabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		infoTabbedPane.setBorder(null);
		infoTabbedPane.setPreferredSize(new Dimension(400,600));
		infoTabbedPane.setBackground(UIManager.getColor("Panel.background"));
					
		infoTabbedPane.add(properties);	
		infoTabbedPane.setTitleAt(0," Properties ");
		infoTabbedPane.setIconAt(0,new ImageIcon(TheFrame.class.getResource("/resources/icon/table-16x16.png")));		
				
		infoTabbedPane.add(warnings);	
		infoTabbedPane.setTitleAt(1," Warnings ");
		infoTabbedPane.setIconAt(1,new ImageIcon(TheFrame.class.getResource("/resources/icon/warning-16x16.png")));

		infoTabbedPane.add(errors);	
		infoTabbedPane.setTitleAt(2," Errors ");
		infoTabbedPane.setIconAt(2,new ImageIcon(TheFrame.class.getResource("/resources/icon/error-16x16.png")));
		
		infoTabbedPane.add(console);	
		infoTabbedPane.setTitleAt(3," Console ");
		infoTabbedPane.setIconAt(3,new ImageIcon(TheFrame.class.getResource("/resources/icon/display-16x16.png")));
		
		centerSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,innerSplitPane,infoTabbedPane);
		centerSplitPane.setOneTouchExpandable(true);		
		centerSplitPane.setBorder(null);
				
		mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,ontoumlTabbedPane,centerSplitPane);
		mainSplitPane.setOneTouchExpandable(true);			
		mainSplitPane.setBorder(null);
		
		JPanel eastPane = new JPanel();
		getContentPane().add(BorderLayout.EAST,eastPane);
		
		JPanel westPane = new JPanel();
		getContentPane().add(BorderLayout.WEST,westPane);
		
		getContentPane().add(BorderLayout.CENTER,mainSplitPane);				
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TheFrame.class.getResource("/resources/icon/window.png")));
		setTitle("Model Validation Environment - MOVE");
					
		statusBar = new TheStatus();
		getContentPane().add(BorderLayout.SOUTH,statusBar);
		
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
	public void restoreDefaults() 
	{
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {            	
            	//mainSplitPane.setDividerLocation(0.35);
            	innerSplitPane.setDividerLocation(1.00);
            	centerSplitPane.setDividerLocation(0.65);            	
            }
        });
    }
	
	public String getCurrentDateAndTime()
	{
		String result = new String();
	   
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    //get current date time with Date()
	    Date date = new Date();
	    result += dateFormat.format(date);
	 			   
	    return result;
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
	
	public void focusOnConsole()
	{
		infoTabbedPane.setSelectedIndex(3);
	}
	
	public void focusOnWarnings()
	{
		infoTabbedPane.setSelectedIndex(1);
	}
	
	public void focusOnErrors()
	{
		infoTabbedPane.setSelectedIndex(2);
	}
	
	public void focusOnProperties()
	{
		infoTabbedPane.setSelectedIndex(0);
	}
	
	/** Get Console Panel. */
	public TheConsole getConsole() { 
		return console; 
	}
	public TheProperties getProperties(){
		return properties;
	}
	public TheWarnings getWarnings(){
		return warnings;
	}
	public TheErrors getErrors(){
		return errors;
	}
	/** Get Tool Bar */
	public TheToolBar getToolBar() { 
		return toolBar; 
	}
	/** Get Menu Bar */
	public TheMenuBar getTheMenuBar() { 
		return menuBar; 
	}
	public TheStatus getTheStatusBar() {
		return statusBar;
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
		int location = centerSplitPane.getDividerLocation();
		int maxLocation = centerSplitPane.getMaximumDividerLocation();
		if(location < maxLocation)
	    {
			centerSplitPane.setDividerLocation(1.00);	
	    }
	    else
	    {
	    	centerSplitPane.setDividerLocation(0.65);
	    }
	}	
	
	/** Show Console */
	public void ShowConsole() { 
		centerSplitPane.setDividerLocation(0.65); 
	}
	/** Hide Console */
	public void HideConsole() { 
		centerSplitPane.setDividerLocation(1.00); 
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
