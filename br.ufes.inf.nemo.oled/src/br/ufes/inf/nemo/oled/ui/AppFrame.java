package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.util.AlloyExtractorUtil;
import br.ufes.inf.nemo.oled.util.AppCommandListener;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;
import br.ufes.inf.nemo.oled.util.MethodCall;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

public class AppFrame extends JFrame implements AppCommandListener {

	private static final long serialVersionUID = 3464348864344034246L;
	
	private transient MainMenu mainMenu;
	private transient MainToolbar mainToolBar;
	
	private transient ToolManager toolManager;
	private transient DiagramManager diagramManager;
	private transient ProjectBrowser projectBrowser;
	private transient InfoManager infoManager;
	private transient StatusBar statusBar;
	
	private transient JSplitPane toolArea = new JSplitPane();
	private transient JSplitPane browserArea = new JSplitPane();
	private transient JSplitPane editorArea = new JSplitPane();
	
	private transient Map<String, MethodCall> selectorMap = new HashMap<String, MethodCall>();
	
	private transient SimpleGUICustom analyzer;
	
	//For modelling assistant
	private static transient AppFrame instance;

	/**
	 * Default constructor.
	 * */
	public AppFrame() {
		super();
		super.setIconImage(IconLoader.getInstance().getImage("WINDOW"));
		setTitle(getResourceString("application.title"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		Dimension size = new Dimension(1000, 648);
		Dimension minimumSize = new Dimension(700, 650);
		this.setSize(size);
		this.setPreferredSize(size);
		this.setMinimumSize(minimumSize);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		installManagers();
		installMainMenu();
		installMainToolBar();
		installStatusBar();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quitApplication();
			}
		});

		pack();
		initSelectorMap();
		
		restoreDefaults();
		
		//for modelling assistant
		instance = this;
	}
	
	/** Restore default sizes of the split panes. */
	public void restoreDefaults() 
	{
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {            	        		
        		editorArea.setDividerLocation(GetScreenWorkingHeight());
        		browserArea.setDividerLocation(GetScreenWorkingWidth()-258);
            }
        });
    }
	
	public void hideInfoManager()
	{
		editorArea.setDividerLocation(GetScreenWorkingHeight());
	}
	
	public void initializeAlloyAnalyzer()
	{
		// extract alloy jar file and open alloy
		try {			
			AlloyExtractorUtil.alloyAnalyzerJAR();
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
	}

	/**
	 * Adds the main menu.
	 */
	private void installMainMenu() {
		mainMenu = new MainMenu(this);
		mainMenu.addCommandListener(this);
		mainMenu.addCommandListener(diagramManager.getEditorDispatcher());
		setJMenuBar(mainMenu.getMenuBar());
	}

	/**
	 * Adds the main toolbar
	 */
	private void installMainToolBar() {
		mainToolBar = new MainToolbar();
		mainToolBar.addCommandListener(this);
		mainToolBar.addCommandListener(diagramManager.getEditorDispatcher());
		this.getContentPane().add(mainToolBar.getToolbar(), BorderLayout.NORTH);
	}

	public DiagramManager getDiagramManager() {
		return diagramManager;
	}
	
	public ProjectBrowser getProjectBrowser()
	{
		return projectBrowser;
	}
	
	public StatusBar getStatusBar()
	{
		return statusBar;		
	}
	
	public static int GetScreenWorkingWidth() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
	
	/**
	 * Adds the main diagram manager (the tabbed pane which holds the diagrams)
	 */
	private void installManagers() {		
		
		toolArea.setContinuousLayout(true);
		toolArea.setOneTouchExpandable(false);		
		toolArea.setDividerSize(5);
			
		browserArea.setContinuousLayout(true);
		browserArea.setOneTouchExpandable(true);		
		browserArea.setDividerSize(7);
		browserArea.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		
		editorArea.setContinuousLayout(true);
		editorArea.setOneTouchExpandable(false);		
		editorArea.setDividerSize(5);
		editorArea.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		diagramManager = new DiagramManager(this);
		diagramManager.setTabPlacement(JTabbedPane.BOTTOM);
		infoManager= new InfoManager(this, null);		
		projectBrowser = new ProjectBrowser(diagramManager.getFrame(),null);
		toolManager = new ToolManager(this, diagramManager.getEditorDispatcher());
		
		editorArea.add(diagramManager, JSplitPane.TOP);		
		editorArea.add(infoManager,JSplitPane.BOTTOM);	
		editorArea.setDividerLocation(GetScreenWorkingHeight());
		
		browserArea.add(editorArea, JSplitPane.LEFT);
		browserArea.add(projectBrowser, JSplitPane.RIGHT);
		projectBrowser.setMinimumSize(new Dimension(230,250));	
		browserArea.setDividerLocation(GetScreenWorkingWidth()-250);
		browserArea.setResizeWeight(1);
		
		toolManager.setMinimumSize(new Dimension(0,0));		
		toolArea.add(toolManager, JSplitPane.LEFT);
		toolArea.add(browserArea, JSplitPane.RIGHT);
		toolArea.setDividerLocation(0.0d);
		
		getContentPane().add(toolArea, BorderLayout.CENTER);
		
		//to end...
		diagramManager.addStartPanel();		
		editorArea.setDividerLocation(0);
	}

	/**
	 * Adds the status bar.
	 * */
	private void installStatusBar() {
		statusBar = new StatusBar();
		this.getContentPane().add(statusBar, BorderLayout.SOUTH);
	}

	/**
	 * Initializes the selector map.
	 */
	private void initSelectorMap() {
		try {
			selectorMap.put("NEW_PROJECT",
					new MethodCall(DiagramManager.class.getMethod("newProject")));
			selectorMap.put("NEW_DIAGRAM",
					new MethodCall(DiagramManager.class.getMethod("newDiagram")));
			selectorMap.put("OPEN_PROJECT",
					new MethodCall(DiagramManager.class.getMethod("openProject")));
			selectorMap.put("CLOSE_PROJECT",
					new MethodCall(DiagramManager.class.getMethod("closeCurrentProject")));
			selectorMap.put("OPEN_RECENT_PROJECT",
					new MethodCall(DiagramManager.class.getMethod("openRecentProject")));			
			selectorMap.put("ISSUE_REPORT",
					new MethodCall(DiagramManager.class.getMethod("openIssueReport")));
			selectorMap.put("SAVE_PROJECT_AS",
					new MethodCall(DiagramManager.class.getMethod("saveProjectAs")));
			selectorMap.put("SAVE_PROJECT",
					new MethodCall(DiagramManager.class.getMethod("saveProject")));
			selectorMap.put("EXPORT_GFX",
					new MethodCall(DiagramManager.class.getMethod("exportGfx")));
			selectorMap.put("EXPORT_OWL",
					new MethodCall(DiagramManager.class.getMethod("exportOwl")));
			selectorMap.put("EXPORT_ECORE",
					new MethodCall(DiagramManager.class.getMethod("exportEcore")));
			selectorMap.put("IMPORT_ECORE",
					new MethodCall(DiagramManager.class.getMethod("importEcore")));
			selectorMap.put("IMPORT_XMI",
					new MethodCall(DiagramManager.class.getMethod("importXMI")));
			selectorMap.put("EXPORT_OCL",
					new MethodCall(DiagramManager.class.getMethod("exportOCL")));
			selectorMap.put("IMPORT_OCL",
					new MethodCall(DiagramManager.class.getMethod("importOCL")));
			selectorMap.put("DELETE",
					new MethodCall(DiagramManager.class.getMethod("delete")));
			selectorMap.put("EDIT_SETTINGS", new MethodCall(getClass()
					.getMethod("editSettings")));
			selectorMap.put("QUIT",
					new MethodCall(getClass().getMethod("quitApplication")));
			selectorMap.put("ABOUT",
					new MethodCall(getClass().getMethod("about")));
			selectorMap.put("HELP_CONTENTS", new MethodCall(getClass()
					.getMethod("displayHelpContents")));
		} catch (NoSuchMethodException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Handles the fired commands.
	 * */
	@Override
	public void handleCommand(String command) {
		MethodCall methodcall = selectorMap.get(command);
		if (methodcall != null) {
			if(methodcall.getMethod().getDeclaringClass() != getClass())
				methodcall.call(diagramManager);
			else
				methodcall.call(this);
		} else { 
			//System.out.println("not handled: " + command);
		}
	}

	/**
	 * Displays the settings manager.
	 * */
	public void editSettings() {

	}

	/**
	 * Displays the about dialog.
	 * */
	public void about() {

	}

	/**
	 * Displays the help contents.
	 */
	public void displayHelpContents() {

	}

	/**
	 * Quits the application without confirmation.
	 * */
	public void quitApplication() {
		if (canQuit()) {		
			
			diagramManager.dispose();
			
			statusBar.getTimer().cancel();
			statusBar.getTimer().purge();
			dispose();
			Thread.currentThread().interrupt();
			
			System.gc();
			Runtime.getRuntime().exit(0);
		}
	}

	/**
	 * Checks if application can be quit safely.
	 * 
	 * @return true if can quit safely, false otherwise
	 */
	private boolean canQuit() {
		
		DiagramEditor editor = diagramManager.getCurrentDiagramEditor();
		if(editor != null)
			if (editor.canUndo()) {
				return true;
				
				// Commented this line because we don't want this dialog anymore...
				
				/*
				JOptionPane.showConfirmDialog(
					this,
					ApplicationResources.getInstance().getString(
						"confirm.quit.message"),
					ApplicationResources.getInstance().getString(
						"confirm.quit.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
				 */
			}
		return true;
	}

	public InfoManager getInfoManager()
	{
		return infoManager;
	}
	
	/**
	 * Gets the application main menu.
	 * 
	 * @return {@link MainMenu} the main menu
	 * */
	public MainMenu getMainMenu() {
		return mainMenu;
	}

	/**
	 * Gets the tool manager (the tabbed pane responsible for managing the
	 * tools).
	 * 
	 * @return {@link ToolManager} the tool manager
	 * */
	public ToolManager getToolManager() {
		return toolManager;
	}

	/**
	 * Resets the active palette (in the tool manager) to the default element,
	 * the pointer.
	 * */
	public void selectPaletteDefaultElement() {
		toolManager.getOpenPalette().selectDefault();
	}

	// Helper method
	private String getResourceString(String property) {
		return ApplicationResources.getInstance().getString(property);
	}

	/**
	 * Reset the transient values for serialization.
	 * 
	 * @param stream
	 *            an ObjectInputStream
	 * @throws IOException
	 *             if I/O error occured
	 * @throws ClassNotFoundException
	 *             if class was not found
	 */
	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {

		mainMenu = null;
		mainToolBar = null;
		toolManager = null;
		diagramManager = null;
		statusBar = null;
		infoManager = null;
				
		initSelectorMap();
	}

	/**
	 * Query the specified editor state and set the menu and the toolbars
	 * accordingly.
	 * 
	 * @param editor
	 *            the editor
	 */
	public void updateMenuAndToolbars(DiagramEditor editor) {
		
		if(mainMenu != null && mainToolBar != null )
		{
			if(editor != null)
			{
				mainMenu.enableMenuItem("UNDO", editor.canUndo());
				mainMenu.enableMenuItem("REDO", editor.canRedo());
				mainToolBar.enableButton("UNDO", editor.canUndo());
				mainToolBar.enableButton("REDO", editor.canRedo());
			}
			else
			{
				mainMenu.enableMenuItem("UNDO", false);
				mainMenu.enableMenuItem("REDO", false);
				mainToolBar.enableButton("UNDO", false);
				mainToolBar.enableButton("REDO", false);
			}
		}
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
			new ImageIcon(AppFrame.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/delete-36x36.png"))
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
			new ImageIcon(AppFrame.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/warning-36x36.png"))
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
			new ImageIcon(AppFrame.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/check-36x36.png"))
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

	/**
	 * Shows the inputed text in the status bar
	 * @param the text
	 */
	public void showStatus(String status)
	{
		statusBar.reportStatus(status);
	}
	
	/** Get Alloy Analyzer.  */
	public SimpleGUICustom getAlloyAnalyzer()
	{
		return analyzer;
	}
	
	/**
	 * Return a current instance of AppFrame. 
	 * It s used for Modelling Instance project.
	 * @return current AppFrame instance 
	 */
	public static AppFrame getInstance(){
		return instance;
	}

	public void focusOnProperties()
	{	
		infoManager.setSelectedIndex(0);
	}
	
	public void focusOnErrors()
	{	
		infoManager.setSelectedIndex(2);
	}
	
	public void focusOnWarnings()
	{	
		infoManager.setSelectedIndex(1);
	}
	
	public void focusOnOutput()
	{	
		infoManager.setSelectedIndex(3);
	}
	
	public void focusOnOclEditor()
	{		
		infoManager.setSelectedIndex(4);		
	}
	
	public void showInfoManager()
	{		
		editorArea.setDividerLocation(GetScreenWorkingHeight()-243);
	}
	
	public boolean isFocusedOnOclEditor()
	{
		return infoManager.getSelectedIndex()==4;
	}
}
