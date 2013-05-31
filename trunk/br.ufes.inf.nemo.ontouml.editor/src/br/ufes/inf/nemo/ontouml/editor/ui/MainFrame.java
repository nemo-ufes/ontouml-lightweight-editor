package br.ufes.inf.nemo.ontouml.editor.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.util.PluginManagerUtil;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.ontouml.editor.plugin.Action;
import br.ufes.inf.nemo.ontouml.editor.plugin.ApplicationHandler;
import br.ufes.inf.nemo.ontouml.editor.plugin.EditorFactory;
import br.ufes.inf.nemo.ontouml.editor.struct.Project;
import br.ufes.inf.nemo.ontouml.editor.struct.impl.ProjectManager;
import br.ufes.inf.nemo.ontouml.editor.util.Logger;
import br.ufes.inf.nemo.ontouml.editor.util.Resources;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements CommandListener, ApplicationHandler {

	private Map<String, Method> selectorMap = new HashMap<String, Method>();
	private MainMenu mainMenu;
	private MainToolbar mainToolBar;
	private MainStatusPanel mainStatusPanel;
	private ToolManager toolManager;
	private EditorManager editorManager;
	private PluginManager pluginManager;
	private ProjectManager projectManager;
	private JSplitPane mainSplitPane;
	private JPanel contentPane;
	private Map<String, String> supportedArtifacts;
	private Map<String, EditorFactory> editorFactories;
		
	/**
	 * Create the frame.
	 */
	public MainFrame(PluginManager pluginManager) {
		super();
		
		this.pluginManager = pluginManager;
		projectManager = new ProjectManager(this);
		
		editorFactories = new HashMap<String, EditorFactory>();
		supportedArtifacts = new HashMap<String, String>();
		
		initGUI();
		initSelectorMap();
						
	}

	private void initGUI()
	{
		{
			//Basic window configuration
			setTitle(Resources.getString("mainframe.title"));
			setIconImage(Resources.getImage("mainframe.icon"));
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			Dimension minimumSize = new Dimension(700, 650);
			setSize(minimumSize);
			setPreferredSize(minimumSize);
			setMinimumSize(minimumSize);
			setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		}
		{
			//Layout configuration
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout());
		}
		{
			//Install MainMenu
			mainMenu = new MainMenu();
			mainMenu.addCommandListener(this);
			setJMenuBar(mainMenu);
		}
		{
			//Install MainToolbar
			mainToolBar = new MainToolbar();
			mainToolBar.addCommandListener(this); //Listen to main commands
			//mainToolBar.addCommandListener(diagramManager.getEditorDispatcher()); //Listen to editor commands
			contentPane.add(mainToolBar, BorderLayout.NORTH);
		}
		{
			//Install MainStatusPanel
			mainStatusPanel = new MainStatusPanel();
			contentPane.add(mainStatusPanel, BorderLayout.SOUTH);
			
		}
		{
			//Install main area
			mainSplitPane = new JSplitPane();
			mainSplitPane.setContinuousLayout(true);
			mainSplitPane.setOneTouchExpandable(true);
			mainSplitPane.setDividerSize(7);

			mainSplitPane.setResizeWeight(0);
			contentPane.add(mainSplitPane, BorderLayout.CENTER);
			
			editorManager = new EditorManager();
			toolManager = new ToolManager(this);
			toolManager.setMinimumSize(new Dimension(230, 100));
			
			mainSplitPane.setDividerLocation(230);
			
			mainSplitPane.add(toolManager, JSplitPane.LEFT);
			mainSplitPane.add(editorManager, JSplitPane.RIGHT);
			
			
//			//Install editor area
//			JSplitPane editorSplitPane = new JSplitPane();
//			editorSplitPane.setOneTouchExpandable(true);
//			editorSplitPane.setDividerLocation(230);
//			editorSplitPane.setDividerSize(7);
//				
//			editorManager = new EditorManager();
//			toolManager = new ToolManager(this);
//			toolManager.setMinimumSize(new Dimension(230, 100));
//			
//			editorSplitPane.add(editorManager, JSplitPane.RIGHT);
//			editorSplitPane.add(toolManager, JSplitPane.LEFT);
//
//			//outputPanel = new OutputPanel();
//			
//			mainSplitPane.add(editorSplitPane, JSplitPane.TOP);
			//mainSplitPane.add(outputPanel, JSplitPane.BOTTOM);
			
			//diagramManager.addStartPanel();
		}
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				quit();
			}
		});
		
		pack();
		//mainSplitPane.setDividerLocation(mainSplitPane.getMaximumDividerLocation() - 80);
	}

	public void loadPlugableComponents() {
		
		//Install plugins here
		PluginManagerUtil pmu = new PluginManagerUtil(pluginManager);
				
		for (EditorFactory editorFactory : pmu.getPlugins(EditorFactory.class)) {
			editorFactories.put(editorFactory.getSupportedArtifactExtension(), editorFactory);
			supportedArtifacts.put(editorFactory.getSupportedArtifactDesciption(), editorFactory.getSupportedArtifactExtension());
		}
		
		for (Action action : pmu.getPlugins(Action.class)) {
			action.registerApplicationHandler(this);
			mainToolBar.add(action.getToolbarButton());
		}
	}
	
	public Map<String, String> getSupportedArtifacts()
	{
		return supportedArtifacts;
	}
	
	private void initSelectorMap() {
		try {
			
			selectorMap.put("NEW_PROJECT", ProjectManager.class.getMethod("newProject"));
			selectorMap.put("NEW_ARTIFACT", ProjectManager.class.getMethod("newArtifact"));
			selectorMap.put("OPEN_PROJECT",	ProjectManager.class.getMethod("openProject", String.class));
			selectorMap.put("SAVE_PROJECT_AS", ProjectManager.class.getMethod("saveProjectAs"));
			selectorMap.put("SAVE_PROJECT", ProjectManager.class.getMethod("saveProject"));
			selectorMap.put("CUT", EditorManager.class.getMethod("cut"));
			selectorMap.put("COPY", EditorManager.class.getMethod("copy"));
			selectorMap.put("PASTE", EditorManager.class.getMethod("paste"));
			selectorMap.put("UNDO", EditorManager.class.getMethod("undo"));
			selectorMap.put("REDO", EditorManager.class.getMethod("redo"));
			selectorMap.put("QUIT", getClass().getMethod("quit"));
			
		} catch (NoSuchMethodException ex) {
			Logger.logException(ex);
		}
	}
	
	public void handleCommand(String command, Object... parameters) {
		
		Method method = selectorMap.get(command);
		Object target = null;
		
		if(method != null)
		{
			// Find an instance of the declaring class
			if(method.getDeclaringClass().equals(getClass()))
			{
				target = this;
			}
			else if (method.getDeclaringClass().equals(ProjectManager.class))
			{
				target = projectManager;
			}
			
			if(target !=null)
			{
				// Now call the method associated with the command
				try 
				{
					// Match the parameters with the method arguments
					Object[] methodArguments = new Object[method.getParameterTypes().length];
					for (int i = 0; i < methodArguments.length; i++) {
						if(i < parameters.length)
						{
							methodArguments[i] = parameters[i];
						}
					}
					
					method.invoke(target, methodArguments);
			    } 
				catch (InvocationTargetException ex) 
			    {
			    	Logger.logException(ex);
			    } 
				catch (IllegalAccessException ex) 
			    {
					Logger.logException(ex);
			    }
			}
		}
	}
	
	public void quit() {
		/*if (canQuit()) {		
			
			diagramManager.dispose();
			
			statusBar.getTimer().cancel();
			statusBar.getTimer().purge();
			dispose();
			Thread.currentThread().interrupt();
			
			System.gc();
			Runtime.getRuntime().exit(0);
		}*/
		
		pluginManager.shutdown();
		
		//TODO See if this is really necessary
		dispose();
		Thread.currentThread().interrupt();
		
		System.gc();
		Runtime.getRuntime().exit(0);
	}

	public EObject getSelectedElement() {
		return null;
	}

	public Project getSelectedProject() {
		return null;
	}

	public Object getSelectedAftifact() {
		return null;
	}

	public void showOutput(Object object) {
		//outputPanel.write(object.toString());
	}

	public void showStatus(String status) {
		mainStatusPanel.showStatus(status);
	}

	public void registerProject(Project project) {
		project.addProjectListener(toolManager.getExplorer());
		toolManager.getExplorer().setProject(project);
	}

}
