package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.RichTooltip;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;

import br.ufes.inf.nemo.move.ribbons.ConstraintsEditionRibbonBand;
import br.ufes.inf.nemo.move.ribbons.ModelDiagnosisRibbonBand;
import br.ufes.inf.nemo.move.ribbons.ModelEditionRibbonBand;
import br.ufes.inf.nemo.move.ribbons.ModelOtherRibbonBand;
import br.ufes.inf.nemo.move.ribbons.SimulationRibbonBand;
import br.ufes.inf.nemo.move.ui.util.Extractor;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

/**
 * @author John Guerson
 */

public class TheFrame extends JRibbonFrame {

	private static final long serialVersionUID = 1L;
		
	private TheToolBar toolBar;	
	private TheMenuBar menuBar;	
	private LogPanel console;	
	private JSplitPane mainSplitPane;
	//private JSplitPane innerSplitPane;	
	private JSplitPane centerSplitPane;	
	private JTabbedPane ontoumlTabbedPane;
	private JTabbedPane oclTabbedPane;
	//private JTabbedPane antipatternPane;
	private JTabbedPane infoTabbedPane;
	private TheManager appmanager;	
	private SimpleGUICustom analyzer;
	private StatusPanel statusBar;
	private PropertyTablePanel properties	;
	private WarningTablePanel warnings;
	private ErrorTablePanel errors;
	
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

	private static ResizableIcon getResizableIconFromResource(String resource) 
	{
		return ImageWrapperResizableIcon.getIcon(TheFrame.class.getClassLoader().getResource(resource), new Dimension(36,36));
	}
	
	/** 
	 * Constructor.
	 */
	public TheFrame() 
	{
		super();		
		getContentPane().setBackground(new Color(230, 230, 250));
		
		RibbonTask ontoumlTask = new RibbonTask(
			"OntoUML",
			new ModelEditionRibbonBand(this,"Basic Edition",null),
			new ModelDiagnosisRibbonBand(this,"Diagnosis",null),
			new ModelOtherRibbonBand(this,"Other",null)			
		);
		getRibbon().addTask(ontoumlTask);
		
		RibbonTask oclTask = new RibbonTask(
			"OCL", 
			new ConstraintsEditionRibbonBand(this,"Basic Edition",null)
		);
		getRibbon().addTask(oclTask);
		
		RibbonTask alloyTask = new RibbonTask(
			"Simulation", 
			new SimulationRibbonBand(this,"Analyzer/Manager",null)
		);
		getRibbon().addTask(alloyTask);
		
		getRibbon().setApplicationIcon(getResizableIconFromResource("resources/icon/window-black.png"));

		/*RibbonContextualTaskGroup  contextualGroup = new RibbonContextualTaskGroup(
				"Grupo1", 
				ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHTEST),
				ontoumlTask, oclTask,alloyTask,antiPatternTask);
		this.getRibbon().addContextualTaskGroup(contextualGroup);*/
		
		JCommandButton AboutButton = new JCommandButton("About",
				getResizableIconFromResource("resources/icon/info-36x36.png"));
		String title = "About";
		String description = "See more about this application, the authors, version and etc.";
		AboutButton.setPopupRichTooltip(new RichTooltip(title, description));

		JCommandButton LicensesButton = new JCommandButton("Licenses",
				getResizableIconFromResource("resources/icon/copyright-36x36.png"));
		title = "Licenses";
		description = "See the copyright licenses embedded with this application.";
		LicensesButton.setPopupRichTooltip(new RichTooltip(title, description));
						
		this.getRibbon().addTaskbarComponent(AboutButton);
		this.getRibbon().addTaskbarComponent(LicensesButton);
		
		//RibbonApplicationMenu menu = new RibbonApplicationMenu();		
		//menu.addMenuEntry(new RibbonApplicationMenuEntryPrimary(null,"Help",null,CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION));
		//menu.addFooterEntry(new RibbonApplicationMenuEntryFooter(null,"Close",null));
		//menu.addMenuSeparator();		
		//getRibbon().setApplicationMenu(menu);
		
		//getContentPane().setLayout(new BorderLayout(0, 0));
		//
		//menuBar = new TheMenuBar(this);		
		//setJMenuBar(menuBar);
		//
		//toolBar = new TheToolBar(this);
		//
		//JPanel headpanel = new JPanel();
		//headpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		//headpanel.setLayout(new BorderLayout(0, 0));
		//headpanel.add(BorderLayout.NORTH,toolBar);		
		//
		//getContentPane().add(BorderLayout.NORTH,headpanel);
		
		appmanager = new TheManager(this);
		
		oclTabbedPane = new JTabbedPane();
		oclTabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		oclTabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));		
		oclTabbedPane.setBackground(UIManager.getColor("Panel.background"));
		oclTabbedPane.setBorder(null);
		oclTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
				
		oclTabbedPane.add(appmanager.getOCLView());	
		oclTabbedPane.setTitleAt(0,"OCL Editor");
		oclTabbedPane.setIconAt(0,new ImageIcon(TheFrame.class.getResource("/resources/icon/edit-ocl-16x16.png")));
		oclTabbedPane.setBackgroundAt(0,UIManager.getColor("Panel.background"));		
		oclTabbedPane.setTabComponentAt(0,new ButtonTabComponent(oclTabbedPane));
		
		oclTabbedPane.add(appmanager.getAntiPatternListView());	
		oclTabbedPane.setTitleAt(1,"AntiPattern Manager");
		oclTabbedPane.setIconAt(1,new ImageIcon(TheFrame.class.getResource("/resources/icon/search-16x16.png")));
		oclTabbedPane.setBackgroundAt(1,UIManager.getColor("Panel.background"));
		oclTabbedPane.setTabComponentAt(1,new ButtonTabComponent(oclTabbedPane));
		
		/*antipatternPane = new JTabbedPane();
		antipatternPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		antipatternPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		antipatternPane.add(appmanager.getAntiPatternListView());	
		antipatternPane.setTitleAt(0,"AntiPattern Manager");
		antipatternPane.setBackground(UIManager.getColor("Panel.background"));
		antipatternPane.setIconAt(0,new ImageIcon(TheFrame.class.getResource("/resources/icon/search-red-16x16.png")));
		antipatternPane.setBorder(null);*/
		
		/*innerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,oclTabbedPane,antipatternPane);
		innerSplitPane.setOneTouchExpandable(true);		
		innerSplitPane.setBorder(null);
		innerSplitPane.setDividerSize(7);*/

		ontoumlTabbedPane = new JTabbedPane();
		ontoumlTabbedPane.setBorder(null);
		ontoumlTabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ontoumlTabbedPane.setPreferredSize(new Dimension(400,600));
		ontoumlTabbedPane.add(appmanager.getOntoUMLView());	
		ontoumlTabbedPane.setTitleAt(0,"Model Tree");
		ontoumlTabbedPane.setBackground(UIManager.getColor("Panel.background"));		
		ontoumlTabbedPane.setBackgroundAt(0,UIManager.getColor("Panel.background"));		
		//ontoumlTabbedPane.setIconAt(0,new ImageIcon(TheFrame.class.getResource("/resources/icon/model-16x16.jpg")));
		
		console = new LogPanel(this);	
		properties = new PropertyTablePanel(this);
		warnings = new WarningTablePanel(this);
		errors = new ErrorTablePanel(this);
		
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
		infoTabbedPane.setTitleAt(3," Log ");
		infoTabbedPane.setIconAt(3,new ImageIcon(TheFrame.class.getResource("/resources/icon/display-16x16.png")));
		
		centerSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,oclTabbedPane,infoTabbedPane);
		centerSplitPane.setOneTouchExpandable(true);		
		centerSplitPane.setBorder(null);
		centerSplitPane.setDividerSize(7);
		
		mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,ontoumlTabbedPane,centerSplitPane);
		mainSplitPane.setOneTouchExpandable(true);
		mainSplitPane.setBorder(null);
		mainSplitPane.setDividerSize(7);
		
		getContentPane().add(BorderLayout.CENTER,mainSplitPane);				
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setPreferredSize(new Dimension(r.width,r.height));
		setIconImage(Toolkit.getDefaultToolkit().getImage(TheFrame.class.getResource("/resources/icon/window.png")));
		setTitle("Model Validation Environment - MOVE");
					
		statusBar = new StatusPanel();
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
            	//innerSplitPane.setDividerLocation(1.00);
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
	public LogPanel getConsole() { 
		return console; 
	}
	public PropertyTablePanel getProperties(){
		return properties;
	}
	public WarningTablePanel getWarnings(){
		return warnings;
	}
	public ErrorTablePanel getErrors(){
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
	public StatusPanel getTheStatusBar() {
		return statusBar;
	}
	
	/**
	 * Show Or Hide OCL View...
	 */
	public void ShowOrHideOCLView()
	{
		oclTabbedPane.setSelectedIndex(0);
		/*int location = innerSplitPane.getDividerLocation();
		int minLocation = innerSplitPane.getMinimumDividerLocation();
		if(location > minLocation)
	    {
			innerSplitPane.setDividerLocation(0.0);	
	    }
	    else
	    {
	      	innerSplitPane.setDividerLocation(1.00);
	    }*/
	}
	
	/** Show OCL View */
	public void ShowOCLView() {
		oclTabbedPane.setSelectedIndex(0); 
		//innerSplitPane.setDividerLocation(0.50); 
	}
	
	/** Hide OCL View */
	public void HideOCLView() { 
		oclTabbedPane.setSelectedIndex(0); 
		//innerSplitPane.setDividerLocation(0.0); 
	}
	
	/**
	 * Show Or Hide AntiPAttern View...
	 */
	public void ShowOrHideAntiPatternView()
	{
		oclTabbedPane.setSelectedIndex(1);
		/*int location = innerSplitPane.getDividerLocation();
		int maxLocation = innerSplitPane.getMaximumDividerLocation();
		if(location < maxLocation)
	    {
			innerSplitPane.setDividerLocation(1.0);	
	    }
	    else
	    {
	      	innerSplitPane.setDividerLocation(0.50);
	    }*/
	}
	
	/** Show AntiPattern View */
	public void ShowAntiPatternView() { 
		//innerSplitPane.setDividerLocation(0.50);
		oclTabbedPane.setSelectedIndex(1);
	}
	/** Hide AntiPattern View */
	public void HideAntiPatternView() { 
		//innerSplitPane.setDividerLocation(1.00);
		oclTabbedPane.setSelectedIndex(1);
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
