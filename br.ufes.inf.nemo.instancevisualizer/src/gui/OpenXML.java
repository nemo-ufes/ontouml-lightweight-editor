package gui;

import br.ufes.inf.nemo.common.ontoumlparser.*;

import graph.GraphManager;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;

import org.graphstream.graph.Graph;
import org.xml.sax.SAXException;

import xml.XMLFile;

public class OpenXML {
	private JFileChooser fc;
	int returnVal;
	
	public OpenXML(MainWindow mainWindow, boolean exitIfCancel) {
		 fc = new JFileChooser();
		 fc.setCurrentDirectory(new java.io.File("./"));
		 fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML instances", "xml"));
		 returnVal = fc.showOpenDialog(null);
		    
		 if (returnVal == JFileChooser.APPROVE_OPTION) {
			 try {
				 String fileNameWithoutExt = fc.getSelectedFile().getName().replaceFirst("[.][^.]+$", "");
				 String refontoPath = fc.getSelectedFile().getParent() + "\\" + fileNameWithoutExt + ".refontouml";
				 File refontoFile = new File(refontoPath);
				 
				 if(refontoFile.exists()) {
					 OntoUMLParser ontoUmlParser = new OntoUMLParser(refontoPath);
					 XMLFile xmlFile = new XMLFile(fc.getSelectedFile(), ontoUmlParser);	// Creation of XMLFile object
					 GraphManager graphManager = new GraphManager(xmlFile, ontoUmlParser, mainWindow);	// Creation of GraphManager
					 mainWindow.setxGraph(graphManager);
					 for(Graph g : graphManager.getGraphList()) {
	                    if(g.getId().equals("world_structure/PastWorld$0")) {
	                    	graphManager.setSelectedGraph(g);
	                    	break;
	                    }
					 }
	                 graphManager.setSelectedWorld("world_structure/PastWorld$0");
	                 mainWindow.setScrollPanes1();
				 }else{
					 System.out.println(".refontouml NOT FOUND... You need it on the same directory of the .xml.");
					 System.exit(1);
				 }
			 } catch (ParserConfigurationException | SAXException | IOException ex) {
				 Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
			 }
		 }else{
			 if(exitIfCancel) {
				 System.exit(0);
			 }
			 
		 }
	}
}
