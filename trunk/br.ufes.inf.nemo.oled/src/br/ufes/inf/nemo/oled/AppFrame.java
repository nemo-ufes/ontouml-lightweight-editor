/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.jdesktop.swingx.MultiSplitLayout;
import org.jdesktop.swingx.MultiSplitPane;

import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;
import br.ufes.inf.nemo.oled.util.MethodCall;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

/**
 * @author John Guerson
 */
public class AppFrame extends JFrame implements AppCommandListener {

	private static final long serialVersionUID = 3464348864344034246L;
	
	private transient AppMenu mainMenu;
	private transient AppToolbar mainToolBar;
	
	private transient ToolboxPane toolManager;
	private transient TopViewPane topPane;
	private transient BottomViewPane bottomPane;
//	private transient DiagramManager diagramManager;
//	private transient InfoManager infoManager;
	private transient ProjectBrowserPane browserManager;
	
	private transient Map<String, MethodCall> selectorMap = new HashMap<String, MethodCall>();
	
	private transient SimpleGUICustom analyzer;

	private transient MultiSplitPane multiSplitPane;

	private int browserWidth=230;
	private int toolboxWidth=230;
	private int infoHeight=200;
	
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
			  
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quitApplication();
				Main.printOutLine("OLED application closed");
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
			
	public void setAlloyAnalyzer(SimpleGUICustom analyzer){
		this.analyzer = analyzer;
	}
	

	/**
	 * Adds the main menu.
	 */
	private void installMainMenu() {
		mainMenu = new AppMenu(this);
		mainMenu.addCommandListener(this);
		mainMenu.addCommandListener(getDiagramManager().getEditorDispatcher());
		setJMenuBar(mainMenu.getMenuBar());
	}
	
	/**
	 * Adds the main toolbar
	 */
	private void installMainToolBar() 
	{		
		mainToolBar = new AppToolbar(this);
		mainToolBar.addCommandListener(this);
		mainToolBar.addCommandListener(getDiagramManager().getEditorDispatcher());
		JPanel panel = new JPanel();		
		panel.setLayout(new BorderLayout(5,5));
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(mainToolBar.getToolbar(), BorderLayout.WEST);		
		this.getContentPane().add(panel, BorderLayout.NORTH);
		//this.getContentPane().add(mainToolBar.getToolbar(), BorderLayout.NORTH);
	}

	public ProjectBrowser getProjectBrowser(){
		return browserManager.getProjectBrowser();
	}
	
	public ProjectBrowserPane getBrowserManager() {
		return browserManager;
	}
	
	public DiagramManager getDiagramManager() {
		return topPane.getDiagramManager();
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
		topPane = new TopViewPane(this);		
		topPane.setPreferredSize(new Dimension(GetScreenWorkingWidth()-240-240,GetScreenWorkingHeight()));
		
		bottomPane= new BottomViewPane(this, null);
		bottomPane.setPreferredSize(new Dimension(GetScreenWorkingWidth()-240-240,0));
				
		browserManager = new ProjectBrowserPane(this);
		browserManager.setPreferredSize(new Dimension(230,250));
		browserManager.getProjectBrowser().setPreferredSize(new Dimension(230,250));
		
		toolManager = new ToolboxPane(this, getDiagramManager().getEditorDispatcher());
		toolManager.setPreferredSize(new Dimension(230,250));
		toolManager.getPalleteAccordion().setPreferredSize(new Dimension(230,250));
		
		//==========	
			
		String layoutDef = "(ROW weight=1.0 left (COLUMN middle.top middle.bottom) right)";
		MultiSplitLayout.Node modelRoot = MultiSplitLayout.parseModel(layoutDef);
		
		multiSplitPane = new MultiSplitPane();
		multiSplitPane.getMultiSplitLayout().setModel(modelRoot);
		multiSplitPane.add(toolManager, "left");
		multiSplitPane.add(browserManager, "right");
		multiSplitPane.add(topPane, "middle.top");		
		multiSplitPane.add(bottomPane, "middle.bottom");
		multiSplitPane.setBorder(null);
		multiSplitPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(multiSplitPane, BorderLayout.CENTER);
	}	
	
	public boolean isShowToolBox()
	{
		return !(toolManager.getPreferredSize().width == 0);
	}
	
	public boolean isShowBrowser()
	{
		return !(browserManager.getPreferredSize().width==0);
	}
	
	public boolean isShowBottomView()
	{
		return !(bottomPane.getPreferredSize().height == 0);
	}
	
	public void showBottomView()
	{
		int dividerSize = multiSplitPane.getMultiSplitLayout().getDividerSize();
		recordSplitSizes();
		multiSplitPane.getMultiSplitLayout().setFloatingDividers(true);	
		if(getMainMenu().isSelected("BOTTOMVIEW")){
			bottomPane.setPreferredSize(new Dimension(bottomPane.getSize().width,infoHeight));
			browserManager.getProjectBrowser().setPreferredSize(new Dimension(browserManager.getSize().width,250));
			browserManager.setPreferredSize(new Dimension(browserManager.getSize().width,250));
			toolManager.setPreferredSize(new Dimension(toolManager.getSize().width,250));
			toolManager.getPalleteAccordion().setPreferredSize(new Dimension(toolManager.getSize().width,250));			
			topPane.setPreferredSize(new Dimension(topPane.getSize().width,GetScreenWorkingHeight()-infoHeight-dividerSize-70));
			getMainToolBar().getBottomViewButton().setSelected(true);			
		}else{
			bottomPane.setPreferredSize(new Dimension(bottomPane.getSize().width,0));
			browserManager.getProjectBrowser().setPreferredSize(new Dimension(browserManager.getSize().width,250));
			browserManager.setPreferredSize(new Dimension(browserManager.getSize().width,250));
			toolManager.setPreferredSize(new Dimension(toolManager.getSize().width,250));
			toolManager.getPalleteAccordion().setPreferredSize(new Dimension(toolManager.getSize().width,250));			
			topPane.setPreferredSize(new Dimension(topPane.getSize().width,GetScreenWorkingHeight()-dividerSize-70));	
			getMainToolBar().getBottomViewButton().setSelected(false);			
		}		
		multiSplitPane.revalidate();	
	}
	
	public void recordSplitSizes()
	{
		if(toolManager.getSize().width!=0) toolboxWidth = toolManager.getSize().width;
		if(browserManager.getSize().width!=0) browserWidth = browserManager.getSize().width;
		if(bottomPane.getSize().height!=0) infoHeight = bottomPane.getSize().height;
	}
	
	public void showProjectBrowser()
	{
		int dividerSize = multiSplitPane.getMultiSplitLayout().getDividerSize();
		recordSplitSizes();
		multiSplitPane.getMultiSplitLayout().setFloatingDividers(true);		
		if(getMainMenu().isSelected("BROWSER")){
			browserManager.getProjectBrowser().setPreferredSize(new Dimension(browserWidth,250));
			browserManager.setPreferredSize(new Dimension(browserWidth,250));
			toolManager.setPreferredSize(new Dimension(toolManager.getSize().width,250));
			toolManager.getPalleteAccordion().setPreferredSize(new Dimension(toolManager.getSize().width,250));
			bottomPane.setPreferredSize(new Dimension(GetScreenWorkingWidth()-browserWidth-toolManager.getSize().width-(2*dividerSize),bottomPane.getSize().height));
			topPane.setPreferredSize(new Dimension(GetScreenWorkingWidth()-browserWidth-toolManager.getSize().width-(2*dividerSize),topPane.getSize().height));	
			getMainToolBar().getProjectBrowserButton().setSelected(true);
		}else{
			browserManager.getProjectBrowser().setPreferredSize(new Dimension(0,250));
			browserManager.setPreferredSize(new Dimension(0,250));			
			toolManager.setPreferredSize(new Dimension(toolManager.getSize().width,250));
			toolManager.getPalleteAccordion().setPreferredSize(new Dimension(toolManager.getSize().width,250));
			bottomPane.setPreferredSize(new Dimension(GetScreenWorkingWidth()-toolManager.getSize().width-(2*dividerSize),bottomPane.getSize().height));						
			topPane.setPreferredSize(new Dimension(GetScreenWorkingWidth()-toolManager.getSize().width-(2*dividerSize),topPane.getSize().height));
			getMainToolBar().getProjectBrowserButton().setSelected(false);
		}		
		multiSplitPane.revalidate();
	}
	
	public void showToolBox()
	{		
		int dividerSize = multiSplitPane.getMultiSplitLayout().getDividerSize();
		recordSplitSizes();
		multiSplitPane.getMultiSplitLayout().setFloatingDividers(true);
		if(getMainMenu().isSelected("TOOLBOX")){
			toolManager.setPreferredSize(new Dimension(toolboxWidth,250));
			toolManager.getPalleteAccordion().setPreferredSize(new Dimension(toolboxWidth,250));
			browserManager.getProjectBrowser().setPreferredSize(new Dimension(browserManager.getSize().width,250));
			browserManager.setPreferredSize(new Dimension(browserManager.getSize().width,250));
			bottomPane.setPreferredSize(new Dimension(GetScreenWorkingWidth()-toolboxWidth-browserManager.getSize().width-(2*dividerSize),bottomPane.getSize().height));
			topPane.setPreferredSize(new Dimension(GetScreenWorkingWidth()-toolboxWidth-browserManager.getSize().width-(2*dividerSize),topPane.getSize().height));	
			getMainToolBar().getToolBoxButton().setSelected(true);			
		}else{
			toolManager.setPreferredSize(new Dimension(0,250));
			toolManager.getPalleteAccordion().setPreferredSize(new Dimension(0,250));
			browserManager.getProjectBrowser().setPreferredSize(new Dimension(browserManager.getSize().width,250));
			browserManager.setPreferredSize(new Dimension(browserManager.getSize().width,250));
			bottomPane.setPreferredSize(new Dimension(GetScreenWorkingWidth()-browserManager.getSize().width-(2*dividerSize),bottomPane.getSize().height));						
			topPane.setPreferredSize(new Dimension(GetScreenWorkingWidth()-browserManager.getSize().width-(2*dividerSize),topPane.getSize().height));
			getMainToolBar().getToolBoxButton().setSelected(false);
		}		
		multiSplitPane.revalidate();
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
			selectorMap.put("CLOSE_DIAGRAM",
					new MethodCall(DiagramManager.class.getMethod("closeDiagram")));
			selectorMap.put("OPEN_PROJECT",
					new MethodCall(DiagramManager.class.getMethod("openProject")));
			selectorMap.put("CLOSE_PROJECT",
					new MethodCall(DiagramManager.class.getMethod("closeCurrentProject")));
			selectorMap.put("OPEN_RECENT_PROJECT",
					new MethodCall(DiagramManager.class.getMethod("openRecentProject")));			
			selectorMap.put("SAVE_PROJECT_AS",
					new MethodCall(DiagramManager.class.getMethod("saveProjectAs")));
			selectorMap.put("SAVE_PROJECT",
					new MethodCall(DiagramManager.class.getMethod("saveProject")));
			selectorMap.put("EXPORT_GFX",
					new MethodCall(DiagramManager.class.getMethod("exportGfx")));
			selectorMap.put("EXPORT_ECORE",
					new MethodCall(DiagramManager.class.getMethod("exportEcore")));
			selectorMap.put("EXPORT_UML",
					new MethodCall(DiagramManager.class.getMethod("exportUML")));
			selectorMap.put("IMPORT_ECORE",
					new MethodCall(DiagramManager.class.getMethod("importEcore")));
			selectorMap.put("IMPORT_PATTERN",
					new MethodCall(DiagramManager.class.getMethod("importPattern")));
			selectorMap.put("IMPORT_XMI",
					new MethodCall(DiagramManager.class.getMethod("importXMI")));
			selectorMap.put("EXPORT_PATTERN",
					new MethodCall(DiagramManager.class.getMethod("exportPattern")));
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
				methodcall.call(getDiagramManager());
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
	 * Quits the application with confirmation.
	 * */
	public void quitApplication() {
		Main.printOutLine("OLED application closed");
		
		if (canQuit()) {		
			
			getDiagramManager().dispose();

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
		
		if(getDiagramManager().getCurrentProject()==null)
			return true;
		
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
			getDiagramManager().saveProject();
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
		return bottomPane.getInfoManager();
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
	 * @return {@link ToolboxPane} the tool manager
	 * */
	public ToolboxPane getToolManager() {
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
		topPane = null;		
		bottomPane = null;
				
		initSelectorMap();
	}

	/**
	 * Query the specified editor state and set the menu and the toolbars
	 * accordingly.
	 * 
	 * @param editor
	 *            the editor
	 */
//	public void updateMenuAndToolbars(DiagramEditor editor) {
//		
//		if(mainMenu != null && mainToolBar != null )
//		{
//			if(editor != null)
//			{
//				mainMenu.enableMenuItem("UNDO", editor.canUndo());
//				mainMenu.enableMenuItem("REDO", editor.canRedo());
//				mainToolBar.enableButton("UNDO", editor.canUndo());
//				mainToolBar.enableButton("REDO", editor.canRedo());
//			}
//			else
//			{
//				mainMenu.enableMenuItem("UNDO", false);
//				mainMenu.enableMenuItem("REDO", false);
//				mainToolBar.enableButton("UNDO", false);
//				mainToolBar.enableButton("REDO", false);
//			}
//		}
//	}
	
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
	
	/** Get Alloy Analyzer.  */
	public SimpleGUICustom getAlloyAnalyzer()
	{
		return analyzer;
	}
	
	public void selectConsole()
	{	
		bottomPane.getInfoManager().selectConsole();
	}	
	
	public void selectWarnings()
	{
		bottomPane.getInfoManager().selectWarnings();
	}
	
	public void selectProblems()
	{
		bottomPane.getInfoManager().selectProblems();
	}
	
	public void selectStatistic()
	{
		bottomPane.getInfoManager().selectStatistic();
	}	
}
