package gui;

import br.ufes.inf.nemo.common.ontoumlparser.*;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import obj.*;

public class OpenXML {
	private JFileChooser fc;
	
	public OpenXML(MainWindow mainWindow, boolean exitIfCancel) {
		 fc = new JFileChooser();
		 fc.setCurrentDirectory(new java.io.File("./"));
		 fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML instances", "xml"));
		 int returnVal = fc.showOpenDialog(null);
		    
		 if (returnVal == JFileChooser.APPROVE_OPTION) {
			 try {
				 XMLFile xmlFile;
				 XGraph xGraph;
				 xmlFile = new XMLFile(fc.getSelectedFile());
				 String fileNameWithoutExt = fc.getSelectedFile().getName().replaceFirst("[.][^.]+$", "");
				 String refontoPath = fc.getSelectedFile().getParent() + "\\" + fileNameWithoutExt + ".refontouml";
				 File refontoFile = new File(refontoPath);
				 System.out.println(refontoFile.getAbsolutePath());
				 OntoUMLParser onto = null;
		            
				 if(refontoFile.exists()) {
					 onto = new OntoUMLParser(refontoPath);
					 xGraph = new XGraph(xmlFile, onto, 0);
					 mainWindow.setxGraph(xGraph);
					 mainWindow.setXmlFile(xmlFile);
					 mainWindow.getxGraph().setGraphToSelectedWorld(mainWindow.getxGraph().getXmlFile().findAtom("world_structure/CurrentWorld$0"));
					 mainWindow.getxGraph().setGraphToAllWorlds();
					 
				 }else{
					 System.out.println(".refontouml NOT FOUND... You need it on the same directory of the .xml.");
					 System.exit(0);
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
