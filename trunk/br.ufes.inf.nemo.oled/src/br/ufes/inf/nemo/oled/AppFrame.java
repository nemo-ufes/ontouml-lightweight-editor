package br.ufes.inf.nemo.oled;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jdesktop.swingx.MultiSplitLayout;
import org.jdesktop.swingx.MultiSplitPane;

import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;
import br.ufes.inf.nemo.oled.util.MethodCall;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

public class AppFrame extends JFrame implements AppCommandListener {

	private static final long serialVersionUID = 3464348864344034246L;
	
	private transient AppMenu mainMenu;
	private transient AppToolbar mainToolBar;
	
	private transient ToolManager toolManager;
	private transient DiagramManager diagramManager;
	private transient ProjectBrowser projectBrowser;
	private transient InfoManager infoManager;
	private transient AppStatusBar statusBar;
	
	private transient Map<String, MethodCall> selectorMap = new HashMap<String, MethodCall>();
	
	private transient SimpleGUICustom analyzer;

	private transient MultiSplitPane multiSplitPane;
	
	/**
	 * Default constructor.
	 * */
	public AppFrame() {
		super();
		super.setIconImage(IconLoader.getInstance().getImage("WINDOW"));
		setTitle(getResourceString("application.title")+" v"+Main.OLED_VERSION);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setPreferredSize();
		
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
	}
	
	public void setPreferredSize()
	{
		Dimension size = new Dimension(1000, 648);
		Dimension minimumSize = new Dimension(700, 650);
		this.setSize(size);
		this.setPreferredSize(size);
		this.setMinimumSize(minimumSize);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void createSysOutInterceptor()
	{
		PrintStream interceptor = new Interceptor(System.out);
		System.setOut(interceptor);
	}
	
	public void setAlloyAnalyzer(SimpleGUICustom analyzer){
		this.analyzer = analyzer;
	}
	
	/**
	 * Adds the main menu.
	 */
	private void installMainMenu() {
		mainMenu = new AppMenu(this);
		mainMenu.addCommandListener(this);
		mainMenu.addCommandListener(diagramManager.getEditorDispatcher());
		setJMenuBar(mainMenu.getMenuBar());
	}
	
	/**
	 * Adds the main toolbar
	 */
	private void installMainToolBar() 
	{		
		mainToolBar = new AppToolbar(this);
		mainToolBar.addCommandListener(this);
		mainToolBar.addCommandListener(diagramManager.getEditorDispatcher());
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(5,5));
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(mainToolBar.getToolbar(), BorderLayout.WEST);		
		this.getContentPane().add(panel, BorderLayout.NORTH);
		//this.getContentPane().add(mainToolBar.getToolbar(), BorderLayout.NORTH);
	}

	public DiagramManager getDiagramManager() {
		return diagramManager;
	}
	
	public ProjectBrowser getProjectBrowser()
	{
		return projectBrowser;
	}
	
	public AppStatusBar getStatusBar()
	{
		return statusBar;		
	}
	
	public AppToolbar getMainToolBar()
	{
		return mainToolBar;
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
	private void installManagers() 
	{		
		diagramManager = new DiagramManager(this);
		diagramManager.setTabPlacement(JTabbedPane.BOTTOM);		
		diagramManager.addStartPanel();
		diagramManager.setPreferredSize(new Dimension(GetScreenWorkingWidth()-240-240,GetScreenWorkingHeight()-200));
		
		infoManager= new InfoManager(this, null);
		infoManager.setPreferredSize(new Dimension(GetScreenWorkingWidth()-240-240,230));
		
		projectBrowser = new ProjectBrowser(diagramManager.getFrame(),null);
		projectBrowser.setPreferredSize(new Dimension(230,250));
		
		toolManager = new ToolManager(this, diagramManager.getEditorDispatcher());
		toolManager.setPreferredSize(new Dimension(230,250));
		toolManager.getPalleteAccordion().setPreferredSize(new Dimension(230,250));
		
		//==========	
			
		String layoutDef = "(ROW weight=1.0 left (COLUMN middle.top middle.bottom) right)";
		MultiSplitLayout.Node modelRoot = MultiSplitLayout.parseModel(layoutDef);
		
		multiSplitPane = new MultiSplitPane();
		multiSplitPane.getMultiSplitLayout().setModel(modelRoot);
		multiSplitPane.add(toolManager.getPalleteAccordion(), "left");
		multiSplitPane.add(projectBrowser, "right");
		multiSplitPane.add(diagramManager, "middle.top");		
		multiSplitPane.add(infoManager, "middle.bottom");
		getContentPane().add(multiSplitPane, BorderLayout.CENTER); 
	}	
			
	public void showToolBox(boolean value)
	{		
		if(value){
			diagramManager.setPreferredSize(new Dimension(GetScreenWorkingWidth()-240-240,GetScreenWorkingHeight()-200));
			toolManager.setPreferredSize(new Dimension(230,250));
			toolManager.getPalleteAccordion().setPreferredSize(new Dimension(230,250));			
		}else{
			diagramManager.setPreferredSize(new Dimension(GetScreenWorkingWidth()-240,GetScreenWorkingHeight()-200));
			toolManager.setPreferredSize(new Dimension(0,250));
			toolManager.getPalleteAccordion().setPreferredSize(new Dimension(0,250));
		}		
		multiSplitPane.repaint();
		multiSplitPane.validate();		
	}
	
	/**
	 * Adds the status bar.
	 * */
	private void installStatusBar() {
		statusBar = new AppStatusBar();
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
		
		int response = JOptionPane.showOptionDialog(
			this,
			"Do you really want to quit?",
			"Quit application?", 
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			new String[]{"Save and Exit", "Exit without saving", "Cancel"},
			"default");		
		if(response==JOptionPane.YES_OPTION){
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			diagramManager.saveProject();
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		if(response==JOptionPane.NO_OPTION){
			return true;
		}
		if(response==JOptionPane.CANCEL_OPTION){
			return false;
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
	 * @return {@link AppMenu} the main menu
	 * */
	public AppMenu getMainMenu() {
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
		toolManager.getElementsPalette().selectDefault();
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
			this,message,title,JOptionPane.ERROR_MESSAGE			
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
			this,message,title,JOptionPane.WARNING_MESSAGE			
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
			this,message,title,JOptionPane.INFORMATION_MESSAGE			
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

	public void focusOnErrors()
	{	
		infoManager.setSelectedIndex(1);
	}
	
	public void focusOnWarnings()
	{	
		infoManager.setSelectedIndex(0);
	}
	
	public void focusOnOutput()
	{	
		infoManager.setSelectedIndex(2);
	}
	
	public void focusOnOclEditor()
	{		
		infoManager.setSelectedIndex(4);		
	}
	
	public void focusOnFinder()
	{		
		infoManager.setSelectedIndex(3);
		infoManager.getFinder().requestFindFocus();
	}
	
	public boolean isFocusedOnOclEditor()
	{
		return infoManager.getSelectedIndex()==4;
	}
	
	public class Interceptor extends PrintStream
	{
	    public Interceptor(OutputStream out)
	    {
	        super(out,true);
	    }
	    @Override
	    public void print(String s)
	    {
	        super.print(s);
	        infoManager.getOutput().append(s);
	    }

	    @Override 
	    public void println(String s)
	    {
	        super.println(s);
	        infoManager.getOutput().append("\n");
	    }       
	}
}
