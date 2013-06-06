package gui;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
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

public class Main implements ViewerListener {
	private MainWindow mainWindow;
    protected boolean loop = true;
 
    public static void main(String args[]) throws HeadlessException, InterruptedException {
    	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        new Main();
    }
    
    public Main() throws HeadlessException, InterruptedException {
    	mainWindow = new MainWindow();
    	new OpenXML(mainWindow, true);
    	mainWindow.setVisible(true);
    	
    	mainWindow.setScrollPanes();

        //Viewer viewer = graphl.display();
 
        // The default action when closing the view is to quit
        // the program.
        //viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
 
        // We connect back the viewer to the graph,
        // the graph becomes a sink for the viewer.
        // We also install us as a viewer listener to
        // intercept the graphic events.
    	
    	Graph graph = mainWindow.getxGraph().getWorldGraph();
    	
        ViewerPipe fromViewer = new BlockingViewerPipe(mainWindow.getxGraph().getViewer1());
        fromViewer.addViewerListener(this);
        //fromViewer.addSink(graph);
        
        // Then we need a loop to wait for events.
        // In this loop we will need to call the
        // pump() method to copy back events that have
        // already occurred in the viewer thread inside
        // our thread.
        
        //mainWindow.getxGraph().getWorldGraph().removeNode("world_structure/CurrentWorld$0");
        //mainWindow.getxGraph().getWorldGraph().addNode("world_structure/CurrentWorld$0");
        
        fromViewer.pump();
        while(loop) {
        		fromViewer.pump();
        		System.out.println("Pumping ...");
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
        System.out.println("Button released on node "+id);
        mainWindow.getChckbxmntmNewCheckItem().setSelected(true);
        mainWindow.getxGraph().setSelectedGraph(mainWindow.getxGraph().setGraphToSelectedWorld(mainWindow.getXmlFile().findAtom(id)));
        mainWindow.getxGraph().showSelectedGraph();
        mainWindow.setScrollPanes1();
        
    }
}