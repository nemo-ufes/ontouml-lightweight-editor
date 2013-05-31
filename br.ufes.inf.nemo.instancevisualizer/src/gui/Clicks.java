package gui;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import obj.*;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.ViewerListener;
import org.graphstream.ui.swingViewer.ViewerPipe;
import org.xml.sax.SAXException;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class Clicks extends JFileChooser implements ViewerListener {
    protected boolean loop = true;
 
    public static void main(String args[]) throws HeadlessException, InterruptedException {
    	
    	
        new Clicks();
    }
    
    public void callFc() throws HeadlessException, InterruptedException {
    	JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("./"));
        fc.setFileFilter(new FileNameExtensionFilter("XML instances", "xml"));
        int returnVal = fc.showOpenDialog(this);
        
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
                    /*
                    EObject eo = onto.getElement("Passenger");
                    String estereotipo = TypeName.getTypeName(eo);
                    System.out.println(estereotipo);
                    */
                    xGraph = new XGraph(xmlFile, onto, 0);
                    xGraph.setGraphToSelectedWorld(xGraph.getXmlFile().findAtom("world_structure/CurrentWorld$0"));
                    xGraph.setGraphToAllWorlds();
                    new MainWindow(xGraph).setVisible(true);
            		
            		//new Clicks(xGraph);
            		
                    //xGraph.showGraph();
                    //scrollPane.getcont.add(xGraph.showGraph());

                    /*
                    if(tabbedPane.getTabCount() > 1) {
                        java.awt.Component c = tabbedPane.getTabComponentAt(0);
                        System.out.println(tabbedPane.getComponentCount());
                        tabbedPane.removeAll();
                        tabbedPane.addTab("Welcome", c);
                        System.out.println(tabbedPane.getComponentCount());
                    }
                    //getContentPane().add(xGraph.getView());
                    panel_1.add(xGraph.getView());
                    //tabbedPane.add(xGraph.getView());
                     * 
                    //tabbedPane.setSelectedIndex(1);
                    */
                    //tabbedPane.setTitleAt(1, "All Worlds");
                }else{
                	System.out.println(".refontouml NOT FOUND... You need it on the same directory of the .xml.");
                }
            } catch (    ParserConfigurationException | SAXException | IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Clicks() throws HeadlessException, InterruptedException {
    	callFc();
        // We do as usual to display a graph. This
        // connect the graph outputs to the viewer.
        // The viewer is a sink of the graph.
        Graph graphl = new SingleGraph("Clicks");
        
        Viewer viewer = graphl.display();
 
        // The default action when closing the view is to quit
        // the program.
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
 
        // We connect back the viewer to the graph,
        // the graph becomes a sink for the viewer.
        // We also install us as a viewer listener to
        // intercept the graphic events.
        ViewerPipe fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(this);
        fromViewer.addSink(graphl);
        graphl.addNode("s");
        graphl.addNode("a");
 
        // Then we need a loop to wait for events.
        // In this loop we will need to call the
        // pump() method to copy back events that have
        // already occurred in the viewer thread inside
        // our thread.
        fromViewer.pump();
        while(loop) {
            fromViewer.pump();
 
            // here your simulation code.
            // You do not necessarily need to use a loop, this is only an example.
            // as long as you call pump() before using the graph. pump() is non
            // blocking.
        }
    }
 
    public void viewClosed(String id) {
        loop = false;
    }
 
    public void buttonPushed(String id) {
        System.out.println("Button pushed on node "+id);
    }
 
    public void buttonReleased(String id) {
    	try {
    		
			new MainWindow().setVisible(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Button released on node "+id);
    }
}