package br.ufes.inf.nemo.instancevisualizer.apl;

import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;

import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.util.Camera;
import org.xml.sax.SAXException;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.instancevisualizer.graph.GraphManager;
import br.ufes.inf.nemo.instancevisualizer.gui.LegendPanel;
import br.ufes.inf.nemo.instancevisualizer.gui.MainWindow;
import br.ufes.inf.nemo.instancevisualizer.util.Choice;
import br.ufes.inf.nemo.instancevisualizer.util.DialogUtil;
import br.ufes.inf.nemo.instancevisualizer.xml.XMLFile;
import edu.mit.csail.sdg.alloy4.Err;
import edu.mit.csail.sdg.alloy4compiler.ast.Command;
import edu.mit.csail.sdg.alloy4compiler.parser.CompModule;
import edu.mit.csail.sdg.alloy4compiler.parser.CompUtil;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Options;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Solution;
import edu.mit.csail.sdg.alloy4compiler.translator.TranslateAlloyToKodkod;

public class AplMainWindow {
	
	public final static String STYLE_PATH = "." + File.separator + "style" + File.separator;
	public final static String STYLE_NODE_PATH = STYLE_PATH + "node" + File.separator;
	public final static String STYLE_EDGE_PATH = STYLE_PATH + "edge" + File.separator;
	public final static String STYLE_EXT = ".ser";
	
	public static MainWindow mainWindow;
	public static boolean popOutEnabled = false;
	public static A4Solution solution;
	public static GraphManager graphManager;
	public static String fWithoutExt;
	public static boolean alsOpen = false;
		
	/**
	 * Opens a file chooser dialog. 
	 */
	private static File openFileChooserDialog(String startingDir, boolean mustExist, String... filters) {
		// Opening file dialog:
		File f = DialogUtil.fileDialog("Open", startingDir, filters, false);
		// Checking if file exists (it must exist!):
		// If f==null, the file chooser dialog has been canceled.
		while(f != null && !f.exists() && mustExist) {
			DialogUtil.errorDialog(mainWindow, DialogUtil.ERROR, "No such file", "Specified file doesn't exist. Please, select another file.");
			f = DialogUtil.fileDialog("Open", f.getParent(), filters, false);
		}
		
		return f;
	}
	
	/**
	 * Calls the "Open File" menu item on the main window. A file chooser dialog is opened to select the file. 
	 */
	public static void openFile(String startingDir) {
		// Opening file dialog:
		final File fop = openFileChooserDialog("."+File.separator, true, "XML Instance$xml", "Alloy code$als");
		if(fop != null){
			new Thread() {
		   		@SuppressWarnings("deprecation")
				public void run() {
					try {
						File f = fop;
						/* The .refontouml file is used to get types' stereotypes.
						 * It muts be located on the same directory of the selected .als/.xml file.
						 * This file is not required. 
						 */
						// Loading .refontouml file:
						fWithoutExt = f.getParent() + File.separator + f.getName().replaceFirst("[.][^.]+$", "");
						File refontoFile = new File(fWithoutExt + ".refontouml");
						if(!refontoFile.exists()) {
							// TODO make refontouml optional?
							refontoFile = null;
							DialogUtil.errorDialog(mainWindow, DialogUtil.ERROR, ".refontouml not found", "The corresponding .refontouml must be in the same directory as the opened file.");
							String parent = f.getParent();
							if(parent == null) {
								openFile("."+File.separator);
							}else{
								openFile(parent);
							}
							stop();
						}
						
						// Disabling the main window:
						mainWindow.setEnabled(false);
						
						mainWindow.setStatus("Detecting file type...");
						FileReader fr = new FileReader(f);
						char cbuf[] = new char[6];
						fr.read(cbuf);
						String beginning = new String(cbuf);
						fr.close();
											
						// We detect if the file is an alloy xml by checking if it starts with "<alloy": 
						if(beginning.equals("<alloy")) {
							alsOpen = false;
						}else{
							alsOpen = true;
							CompModule model = null;
							Command cmd = null;
							try {
								mainWindow.setStatus("Parsing file...");
								model = CompUtil.parseEverything_fromFile(null, null, f.getAbsolutePath());
								cmd = model.getAllCommands().get(0);
								
								mainWindow.setStatus("Generating solution...");
							   	solution = TranslateAlloyToKodkod.execute_command(null, model.getAllReachableSigs(), cmd, new A4Options());
							   	
							   	mainWindow.setStatus("Writing solution .xml...");
							   	String xmlFilePath = fWithoutExt + "_temp.xml";
							   	solution.writeXML(xmlFilePath);
							   	
							   	// Setting f variable, so the xml can be loaded:
							   	f = new File(xmlFilePath);
							   	
							   	// Enabling the "Next Instance" menu item on the main window:
							   	mainWindow.getMntmNextInstance().setEnabled(true);
							   	
							} catch(Err e) {
								DialogUtil.errorDialog(mainWindow, DialogUtil.ERROR, "Invalid alloy file", "Please, select a valid .als or .xml file.");
								mainWindow.setEnabled(true);
								mainWindow.setStatus("");
								stop();
								//e.printStackTrace();
							}
						}
						loadFile(f, refontoFile);
					} catch (IOException e) {
						DialogUtil.bugDialog(mainWindow, e);
					}
		   		}
		   	}.start();
		}
	}
	
	/**
	 * Loads an specific instance file and refontoFile.
	 * This method creates the graph manager.
	 * @param f
	 * @param refontoFile
	 */
	public static void loadFile(final File f, final File refontoFile) {
		new Thread() {
			public void run() {
				if(alsOpen) {
					mainWindow.getMntmNextInstance().setEnabled(true);
				}else{
					mainWindow.getMntmNextInstance().setEnabled(false);
				}
				mainWindow.setEnabled(false);
				mainWindow.setStatus("Loading instance .xml...");
				OntoUMLParser ontoUmlParser = null;
				// If refontoFile is null, then it will not be loaded.
				if(refontoFile != null) {
					try {
						ontoUmlParser = new OntoUMLParser(refontoFile.getAbsolutePath());
					} catch (IOException e) {
						DialogUtil.bugDialog(mainWindow, e);
					}
				}
				mainWindow.setStatus("Parsing instance .xml...");
				XMLFile xmlFile = null;
				try {
					xmlFile = new XMLFile(f, ontoUmlParser);
				} catch (ParserConfigurationException | SAXException | IOException e) {
					DialogUtil.bugDialog(mainWindow, e);
				}
				mainWindow.setStatus("Creating graph...");
				graphManager = new GraphManager(xmlFile, ontoUmlParser, mainWindow);	// Creation of GraphManager
				
			   	displayAllGraphs();
			   	mainWindow.setEnabled(true);
			   	mainWindow.setStatus("Done!");
			}
		}.start();
	}
	
	// TODO
	public static void openTheme() {

		// Setting file dialog filters:
		String filters[] = new String[1];
		filters[0] = "Instance Visualizer Theme$ivt";
		
		// Opening file dialog:
		File f = DialogUtil.fileDialog("Open", "." + File.separator, filters, false);
		
		// f==null means cancelled dialog.
		if(f != null) {
			// Checking if file exists (it must exist!) :
			if(!f.exists()) {
				DialogUtil.errorDialog(mainWindow, DialogUtil.ERROR, "No such file", "Specified file doesn't exist.");
				openTheme();
				return;
			}
			
			mainWindow.setStatus("Validating theme file...");
			FileReader fr;
			String beginning = null;
			try {
				fr = new FileReader(f);
				char cbuf[] = new char[6];
				fr.read(cbuf);
				beginning = new String(cbuf);
				fr.close();
			} catch (IOException e) {
				DialogUtil.bugDialog(mainWindow, e);
			}
			
			// Valid theme files contain the following header:
			if(beginning.equals("ivthmc")) {
				try {
					mainWindow.setStatus("Applying theme file...");
					String theme = readFile(f.getAbsolutePath(), Charset.defaultCharset());
					//System.out.println(theme);
					graphManager.getLegendManager().loadString(theme.substring(6));
					graphManager.update();
					/*graphManager.setGraphList(new ArrayList());
					graphManager.createSelectedWorldToList();
					for(Graph g : graphManager.getGraphList()) {
		            	if(g.getId().equals(graphManager.getSelectedWorld())) {
		            		graphManager.setSelectedGraph(g);
		            		break;
		            	}
		            }*/
					refreshGraphs();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else{
				DialogUtil.errorDialog(mainWindow, DialogUtil.ERROR, "Invalid theme", "Specified theme isn't valid.");
				mainWindow.setEnabled(true);
				mainWindow.setStatus("");
				openTheme();
				return;
			}
			mainWindow.setStatus("Done!");
		}
    		
	}
			
	public static void saveTheme() {
		String theme = graphManager.getLegendManager().saveToString();
		
		// Setting file dialog filters:
		String filters[] = new String[1];
		filters[0] = "Instance Visualizer Theme$thm";
		
		// Opening file dialog:
		File f = DialogUtil.fileDialog("Save", "." + File.separator, filters, false);
		
		// f==null means cancelled dialog.
		if(f != null) {
			// Checking if file exists. If it does, it'll be asked if it can be overwritten:
			while(f.exists()) {
				Choice choice = new Choice();
				DialogUtil.chooseDialog(choice, mainWindow, "File already exists", "Overwrite" + f.getAbsoluteFile() + "?", "Yes", "No");
				if(choice.isChoice()) {
					break;
				}
				saveTheme();
				return;
			}
	    		//File file = fileChooser.getSelectedFile();
			try {
				FileWriter fw = new FileWriter(f);
				fw.write("ivthmc" + theme);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void nextInstance() {
		try {
			solution = solution.next();
			String xmlPath = fWithoutExt + "_temp.xml";
			String refontoPath = fWithoutExt + ".refontouml";
			solution.writeXML(xmlPath);
			loadFile(new File(xmlPath), new File(refontoPath));
		} catch (Err e) {
			DialogUtil.bugDialog(mainWindow, e);
		}
	}
	
	/**
	 * Function to read a file to a string.
	 * @param path
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	static public String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}
	
	/**
	 * Load/Reload the graphs onto the scroll panes. 
	 */
	public static void refreshGraphs() {
		mainWindow.getSelectedWorld().getViewport().removeAll();
		mainWindow.getSelectedWorld().setViewportView(graphManager.showSelectedGraph());
		
		mainWindow.getWorldMap().getViewport().removeAll();
		mainWindow.getWorldMap().setViewportView(graphManager.showWorldGraph());
		
		mainWindow.getTabbedPane().removeAll();
		mainWindow.getTabbedPane().addTab("Legend", null, new LegendPanel(graphManager));
		
		//mainWindow.miniMapProto();
	}
	
	/**
	 * Displays everything - legend, selected world and world map - on the main window.]
	 */
	public static void displayAllGraphs() {
		graphManager.setSelectedWorld("world_structure/CurrentWorld$0");
        refreshGraphs();
	}
	
	/**
	 * <empty method> Disable all interactive components of the pointed mainWindow. Useful when using threads to open files and such.  
	 */
	private static void disableAll() {
		
	}

	public static void zoomSelectedView(MouseWheelEvent arg0) {
		// TODO better zoom system
		
		View view = graphManager.getSelectedView();
		//Point posPx = view.getMousePosition();
		Camera camera = view.getCamera();
		//Point3 posGu = camera.transformPxToGu(posPx.x, posPx.y);
		/*
		//Point3 origCam = camera.getViewCenter();
		view.getSize();
		double deltaX = view.getCamera().getGraphDimension() * (0.01f * posGu.x);
		double deltaY = view.getCamera().getGraphDimension() * (0.01f * posGu.y);
		camera.setViewCenter(deltaX, deltaY, 0);
		*/
		if(arg0.getWheelRotation() < 0) {
			if(camera.getViewPercent() > 0.1)
				camera.setViewPercent(camera.getViewPercent() - 0.05f);
		}else{
			camera.setViewPercent(camera.getViewPercent() + 0.05f);
		}
	}
}
