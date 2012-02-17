package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;
import br.ufes.inf.nemo.oled.util.ConfigurationHelper;
import br.ufes.inf.nemo.oled.util.IconLoader;
import br.ufes.inf.nemo.oled.util.IconLoader.IconType;
import br.ufes.inf.nemo.oled.util.OLEDSettings;
import edu.mit.csail.sdg.alloy4.ConstMap;
import edu.mit.csail.sdg.alloy4.Err;
import edu.mit.csail.sdg.alloy4.OurUtil;
import edu.mit.csail.sdg.alloy4.Util;
import edu.mit.csail.sdg.alloy4compiler.ast.Module;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Solution;
import edu.mit.csail.sdg.alloy4viz.AlloyInstance;
import edu.mit.csail.sdg.alloy4viz.AlloyType;
import edu.mit.csail.sdg.alloy4viz.StaticInstanceReader;
import edu.mit.csail.sdg.alloy4viz.VizGUI;
import edu.mit.csail.sdg.alloy4viz.VizGraphPanel;
import edu.mit.csail.sdg.alloy4viz.VizState;

/**
 * This class provides an visualizer for the alloy generated instances. It is based on {@link VizGUI}.
 */
public class InstanceVisualizer extends JPanel implements Editor{

	private static final long serialVersionUID = 704479201259601034L;
	
	private VisualizerMode currentMode = VisualizerMode.get();
	private VizState myState;
	private VizGraphPanel myGraphPanel;
	private JComponent content;
	private JToolBar toolbar;
	private JPopupMenu projectionPopup;
	private JButton projectionButton, nextButton;
	private int fontSize = 12;
	private StructureDiagram diagram; 
    private	A4Solution solution;
    private Module module;
    private ConstMap<String, String> alloySources;
	
	public InstanceVisualizer(StructureDiagram diagram, A4Solution solution, Module module, ConstMap<String, String> alloyMap) {

		super();

		this.diagram = diagram;		
		this.solution = solution;
		this.module = module;
		this.alloySources = alloyMap;
		
		initGUI();
		
		loadSolution();
	}

	public void initGUI() {

		this.setLayout(new BorderLayout());
		{
			// Create the toolbar
			projectionPopup = new JPopupMenu();
			projectionButton = new JButton("Projection: none");
			projectionButton.setToolTipText("Projections");
			projectionButton.setMargin(new Insets(4, 4, 4, 4));
			projectionButton.setFocusable(false);
			projectionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (projectionPopup.getComponentCount() > 0)
						projectionPopup.show(projectionButton, 10, 10);
				}
			});
		}
		{
			nextButton = new JButton();
			nextButton.setIcon(IconLoader.getInstance().getIcon(IconType.NEXT));
			nextButton.setToolTipText("Next Solution");
			nextButton.setMargin(new Insets(3, 3, 3, 3));
			nextButton.setFocusable(false);
			nextButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					nextSolution();
				}
			});
		}
		{
			toolbar = new JToolBar();
			toolbar.setFloatable(true);
			toolbar.add(nextButton);
			toolbar.add(projectionButton);
			this.add(toolbar, BorderLayout.NORTH);
		}
		
		projectionButton.setEnabled(true);
	}

	private void nextSolution() {
		
		try {
			
			A4Solution newSolution = solution.next();
			
			if(newSolution.satisfiable())
			{
				solution = newSolution;
				
				loadSolution();
			}
			
		} catch (Err e) {
			
			e.printStackTrace();
		}
		
	}
    
	public void loadSolution() {
			
		AlloyInstance myInstance;
		
		String solutionFileName = ConfigurationHelper.getCanonPath(diagram.getTempDir() , OLEDSettings.SIMULATION_SOLUTION_FILE.getValue());
		String themeFileName = ConfigurationHelper.getCanonPath(diagram.getTempDir(), OLEDSettings.SIMULATION_THEME_FILE.getValue());
		
		File solutionFile = new File(solutionFileName);
		solutionFile.deleteOnExit();
		
		File themeFile = new File(themeFileName);
		themeFile.deleteOnExit();
		
		try {
		
			solution.writeXML(null, solutionFileName, module.getAllFunc(), alloySources);
			
			myInstance = StaticInstanceReader.parseInstance(solutionFile);
			
			if (myState == null)
				myState = new VizState(myInstance);
			else
				myState.loadInstance(myInstance);
				
			myState.loadPaletteXML(themeFileName);
			
			loadProjectionPopup();
		
		} catch (Err e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		updateDisplay();
	}
	
	private void loadProjectionPopup()
	{
		if (myState == null) {
			return;
		}
		
		projectionPopup.removeAll();
		
		/*for (AlloyType type : myState.getProjectedTypes()) {
			myState.deproject(type);
		}*/
		
		for (final AlloyType type : myState.getOriginalModel().getTypes())
			
			if (myState.canProject(type)) {
				JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(type.getName());
				menuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JCheckBoxMenuItem source = (JCheckBoxMenuItem) e.getSource();
						AlloyType type = getType(source.getText());
						if(myState.getProjectedTypes().contains(type))
						{
							myState.deproject(type);						}
						else
						{
							myState.project(type);
						}
						updateDisplay();
					}
				});
				
				projectionPopup.add(menuItem);
								
				if(myState.getProjectedTypes().contains(type))
					menuItem.setSelected(true);
					//projectionPopup.setSelected(menuItem);
			}
	}

	private void updateDisplay() {
		
		String label;
		
		if(myState.getProjectedTypes().size() > 0)
		{
			int i = 1;
			label = "Projected over ";
			for (AlloyType item : myState.getProjectedTypes()) {
				label += item.getName();
				if(i < myState.getProjectedTypes().size())
					label += ", ";
				i++;
			}	 
		}
		else
		{
			label = "Projection: none";
		}
		
		projectionButton.setText(label);
		
		if (myGraphPanel == null)
		{
			myGraphPanel = new VizGraphPanel(myState, currentMode == VisualizerMode.DOT);
		}
		else {
			myGraphPanel.seeDot(currentMode == VisualizerMode.DOT);
			myGraphPanel.remakeAll();
		}
		
		content = myGraphPanel;
		
		this.add(content, BorderLayout.CENTER);
		
		if (currentMode != VisualizerMode.Tree) {
			content.setFont(OurUtil.getVizFont().deriveFont((float) fontSize));
			content.invalidate();
			content.repaint();
			content.validate();
			content.setVisible(true);
		}
	}
	
	private AlloyType getType(String typeName)
	{
		for (AlloyType item : myState.getOriginalModel().getTypes()) {
			if(item.getName().equalsIgnoreCase(typeName))
				return item;
		}
		
		return null;
	}
	
	private enum VisualizerMode {
		/** Visualize using graphviz's dot. */
		Viz("graphviz"),
		/** See the DOT content. */
		DOT("dot"),
		/** See the XML content. */
		XML("xml"),
		/** See the instance as a tree. */
		Tree("tree");
		/**
		 * This is a unique String for this value; it should be kept consistent
		 * in future versions.
		 */
		private final String id;

		/** Constructs a new VisualizerMode value with the given id. */
		private VisualizerMode(String id) {
			this.id = id;
		}

		/**
		 * Given an id, return the enum value corresponding to it (if there's no
		 * match, then return Viz).
		 */
		private static VisualizerMode parse(String id) {
			for (VisualizerMode vm : values())
				if (vm.id.equals(id))
					return vm;
			return Viz;
		}

		/** Saves this value into the Java preference object. */
		@SuppressWarnings("unused")
		public void set() {
			Preferences.userNodeForPackage(Util.class)
					.put("VisualizerMode", id);
		}

		/**
		 * Reads the current value of the Java preference object (if it's not
		 * set, then return Viz).
		 */
		public static VisualizerMode get() {
			return parse(Preferences.userNodeForPackage(Util.class).get(
					"VisualizerMode", ""));
		}
	}

	@Override
	public boolean isSaveNeeded() {
		return false;
	}

	@Override
	public EditorNature getEditorNature() {
		return EditorNature.INSTANCE_VISUALIZER;
	}
	
	@Override
	public Diagram getDiagram() {
		return diagram;
	}
	
	public A4Solution getSolution() {
		return solution;
	}

	public void setSolution(A4Solution solution) {
		this.solution = solution;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public ConstMap<String, String> getAlloySources() {
		return alloySources;
	}

	public void setAlloySources(ConstMap<String, String> alloySources) {
		this.alloySources = alloySources;
	}

	@Override
	public void dispose() {
		
	};

}
