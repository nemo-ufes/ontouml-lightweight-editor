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

public class Main {
	private MainWindow mainWindow;
    protected boolean loop = true;
    protected boolean mode;	//true - worlds; false - selected world;
    private ViewerPipe fromViewer;
    private ViewerPipe fromViewer2;
 
    public static void main(String args[]) throws HeadlessException, InterruptedException {
    	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        new Main(true);
    }
    
    public Main(boolean closeIfCancel) throws HeadlessException, InterruptedException {
    	mode = false;
    	mainWindow = new MainWindow(this);
    	new OpenXML(mainWindow, closeIfCancel);
    	mainWindow.setVisible(true);
    	
    	mainWindow.setScrollPanes();

        //Viewer viewer = graphl.display();
 
        //viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    	
        
    	
    	fromViewer = new BlockingViewerPipe(mainWindow.getxGraph().getViewer1());
    	fromViewer = mainWindow.getxGraph().getViewer1().newViewerPipe();
        fromViewer.addViewerListener(new ViewerListener(){
        	public void viewClosed(String id) {
                //loop = false;
            }
        	
            public void buttonPushed(String id) {
                System.out.println("Button pushed on node "+id);
            }
         
            public void buttonReleased(String id) {
                System.out.println("Button released on node "+id);
                mainWindow.getxGraph().getViewer1().disableAutoLayout();
                
                mainWindow.getxGraph().getWorldGraph().getNode(id).addAttribute("ui.style", "fill-color: green;");
                //mainWindow.getChckbxmntmNewCheckItem().setSelected(true);
                //mainWindow.getxGraph().setSelectedGraph(mainWindow.getxGraph().setGraphToSelectedWorld(mainWindow.getXmlFile().findAtom(id)));
                mainWindow.getxGraph().changeSelectedWorld(mainWindow.getXmlFile().findAtom(id));
                //mainWindow.getxGraph().showSelectedGraph();
                //mainWindow.setScrollPanes();
                //fromViewer2 = mainWindow.getxGraph().getViewer().newViewerPipe();
                //fromViewer2 = new BlockingViewerPipe(mainWindow.getxGraph().getViewer());
                
                System.out.println("FFEZ");
                
                //mainWindow.getxGraph().showSelectedGraph();
                //mainWindow.setScrollPanes1();
                
                
            }
        });
        //fromViewer.addSink(graph);
        
        
        fromViewer2 = mainWindow.getxGraph().getViewer().newViewerPipe();
        //fromViewer2 = new BlockingViewerPipe(mainWindow.getxGraph().getViewer());
        fromViewer2.addViewerListener(new ViewerListener() {
        	public void buttonPushed(String id) {
        		// TODO Auto-generated method stub
        		System.out.println(id);
        		mainWindow.changeTableId(id);
        		mainWindow.changeTableLabel(mainWindow.getxGraph().getSelectedGraph().getNode(id).getAttribute("ui.label").toString());
        	}
        	public void buttonReleased(String id) {
        		// TODO Auto-generated method stub
        		System.out.println(id);
        	}
        	public void viewClosed(String viewName) {
        		// TODO Auto-generated method stub
        		
        	}
        });
        
        while(loop) {
        	Thread.sleep(100);
        	//System.out.println("PUMPING");
        	if(mode) {
        		fromViewer.pump();
        	}else{        		
        		fromViewer2.pump();
        	}
        }
        
    }
    /*
    public void callWorldLoop() {
    	while(loop) {
    		fromViewer.pump();
    		//fromViewer2.pump();
    		//System.out.println("Pumping ...");
    	}
    }
    
    public void callSelectedWorldLoop() {
    	while(loop) {
    		fromViewer.pump();
    		//fromViewer2.pump();
    		//System.out.println("Pumping ...");
    	}
    }
    */
    public void stopActiveLoop() {
    	setLoop(false);
    	setLoop(true);
    }

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public boolean isMode() {
		return mode;
	}

	public void setMode(boolean mode) {
		this.mode = mode;
	}
	
	public boolean getMode() {
		return this.mode;
	}
	
}