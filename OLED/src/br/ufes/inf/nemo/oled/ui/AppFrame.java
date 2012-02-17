package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.util.AppCommandListener;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;
import br.ufes.inf.nemo.oled.util.MethodCall;

public class AppFrame extends JFrame implements AppCommandListener {

	private static final long serialVersionUID = 3464348864344034246L;
	private transient MainMenu mainMenu;
	private transient MainToolbar mainToolBar;
	private transient ToolManager toolManager;
	private transient DiagramManager diagramManager;
	private transient StatusBar statusBar;
	private transient Map<String, MethodCall> selectorMap = new HashMap<String, MethodCall>();

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

	/**
	 * Adds the main menu.
	 */
	private void installMainMenu() {
		mainMenu = new MainMenu();
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

	/**
	 * Adds the main diagram manager (the tabbed pane which holds the diagrams)
	 */
	private void installManagers() {
		JSplitPane mainArea = new JSplitPane();
		mainArea.setOneTouchExpandable(true);
		mainArea.setDividerLocation(230);
		mainArea.setDividerSize(7);
		this.getContentPane().add(mainArea, BorderLayout.CENTER);

		diagramManager = new DiagramManager(this);
		mainArea.add(diagramManager, JSplitPane.RIGHT);

		toolManager = new ToolManager(this, diagramManager.getEditorDispatcher());
		toolManager.setMinimumSize(new Dimension(230, 100));
		mainArea.add(toolManager, JSplitPane.LEFT);
		
		diagramManager.addStartPanel();
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
			selectorMap.put("OPEN_PROJECT",
					new MethodCall(DiagramManager.class.getMethod("openProject")));
			selectorMap.put("COMMUNITY",
					new MethodCall(DiagramManager.class.getMethod("openCommunity")));
			selectorMap.put("LEARN_ONTOUML",
					new MethodCall(DiagramManager.class.getMethod("openLearnOntoUML")));
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
		
		DiagramEditor editor = diagramManager.getCurrentEditor();
		if(editor != null)
			if (editor.canUndo()) {
				return JOptionPane.showConfirmDialog(
						this,
						ApplicationResources.getInstance().getString(
								"confirm.quit.message"),
						ApplicationResources.getInstance().getString(
								"confirm.quit.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
			}
		return true;
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
	 * Shows the inputed text in the status bar
	 * @param the text
	 */
	public void showStatus(String status)
	{
		statusBar.reportStatus(status);
	}
}
