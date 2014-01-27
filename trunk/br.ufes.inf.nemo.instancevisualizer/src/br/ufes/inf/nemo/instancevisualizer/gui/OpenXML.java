package br.ufes.inf.nemo.instancevisualizer.gui;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

import br.ufes.inf.nemo.instancevisualizer.apl.AplMainWindow;
import br.ufes.inf.nemo.instancevisualizer.graph.GraphManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;

import org.graphstream.graph.Graph;
import org.xml.sax.SAXException;

import br.ufes.inf.nemo.instancevisualizer.xml.XMLFile;


public class OpenXML extends Thread {
	private JFileChooser fc;
	private File openFile;
	private String refontoPath;
	private MainWindow mainWindow;
	private boolean exitIfCancel;
	int returnVal;
	
	public void run() {
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				File refontoFile = new File(refontoPath);
				if(refontoFile.exists()) {
					OntoUMLParser ontoUmlParser = new OntoUMLParser(refontoPath);
					XMLFile xmlFile = new XMLFile(openFile, ontoUmlParser);	// Creation of XMLFile object
					GraphManager graphManager = new GraphManager(xmlFile, ontoUmlParser, mainWindow);	// Creation of GraphManager
					AplMainWindow.graphManager = graphManager;
					for(Graph g : graphManager.getGraphList()) {
						if(g.getId().equals("world_structure/CurrentWorld$0")) {
							graphManager.setSelectedGraph(g);
	                    	break;
	                    }
					}
	                graphManager.setSelectedWorld("world_structure/CurrentWorld$0");
	                AplMainWindow.displayAllGraphs();
				}else{
					System.out.println("Couldn't find " + refontoPath + " You need to put it on the same directory of the loaded file ...");
				}
			} catch (ParserConfigurationException | SAXException | IOException ex) {
				Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
			}
		}else{
			if(exitIfCancel) {
				System.exit(0);
			}
			 
		}
		mainWindow.setStatus("Done!");
	}
	
	public OpenXML(MainWindow mainWindow, boolean exitIfCancel) {
		this.mainWindow = mainWindow;
		this.exitIfCancel = exitIfCancel;
		fc = new JFileChooser();
		fc.setVisible(true);
		fc.setCurrentDirectory(new java.io.File("./"));
		fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML instances", "xml"));
		returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			mainWindow.setStatus("Loading " + "\"" + fc.getSelectedFile().getName() + "\"");
			openFile = fc.getSelectedFile();
			String fileNameWithoutExt = openFile.getName().replaceFirst("[.][^.]+$", "");
			refontoPath = openFile.getParent() + "\\" + fileNameWithoutExt + ".refontouml";
			start();
		}
	}
	
	public OpenXML(MainWindow mainWindow, String alsPath, String xmlPath) {
		fc = null;
		this.mainWindow = mainWindow;
		this.exitIfCancel = false;
		returnVal = JFileChooser.APPROVE_OPTION;
		openFile = new File(alsPath);
		refontoPath = openFile.getParent() + "\\" + openFile.getName().replaceFirst("[.][^.]+$", "") + ".refontouml";
		System.out.println(refontoPath);
		System.out.println("XML: " + xmlPath);
		openFile = new File(xmlPath);
		mainWindow.setStatus("Loading " + "instance...");
		start();
	}
}
