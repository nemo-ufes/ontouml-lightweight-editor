package gui;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;

import obj.*;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.ViewerListener;
import org.graphstream.ui.swingViewer.ViewerPipe;
import org.xml.sax.SAXException;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.TypeName;

public class Main {
	private MainWindow mainWindow;
    protected boolean loop = true;
    protected boolean mode;	//true - worlds; false - selected world;
    private ViewerPipe fromViewer;
    private ViewerPipe fromViewer2;
    String selectedWorld = "world_structure/CurrentWorld$0";
 
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

    	//fromViewer = new BlockingViewerPipe(mainWindow.getxGraph().getViewer1());
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
                selectedWorld = id;
                mainWindow.getxGraph().getViewer1().disableAutoLayout();
                
                Iterator iter = mainWindow.getxGraph().getWorldGraph().getNodeIterator();
                while(iter.hasNext()) {
                	org.graphstream.graph.Node nodeAux = (org.graphstream.graph.Node) iter.next();
                	nodeAux.addAttribute("ui.style", "fill-color: white;");
                }
                mainWindow.getxGraph().getWorldGraph().getNode(id).addAttribute("ui.style", "fill-color: green;");
                mainWindow.getxGraph().changeSelectedWorld(mainWindow.getXmlFile().findAtom(id));
                
            }
        });
        //fromViewer.addSink(graph);
        
        
        fromViewer2 = mainWindow.getxGraph().getViewer().newViewerPipe();
        //fromViewer2 = new BlockingViewerPipe(mainWindow.getxGraph().getViewer());
        fromViewer2.addViewerListener(new ViewerListener() {
        	public void buttonPushed(String id) {
        		int i;
        		System.out.println(id);
        		mainWindow.changeTableId(id);
        		mainWindow.changeTableLabel(mainWindow.getxGraph().getSelectedGraph().getNode(id).getAttribute("ui.label").toString());
        		
        		DefaultTableModel model = (DefaultTableModel) mainWindow.getTable().getModel();
        		
        		while(model.getRowCount() != 4) {
        			model.removeRow(4);
        		}
        		
        		ArrayList<String> typeList = mainWindow.getxGraph().getXmlFile().getAtomTypeOnWorld(id, selectedWorld);
        		
        		for(i=0; i<typeList.size(); i++) {
        			String stereoType = TypeName.getTypeName(mainWindow.getxGraph().getOntoUmlParser().getElement(typeList.get(i)));
            		model.addRow(new Object[]{stereoType, typeList.get(i)});
        		}
        		mainWindow.getTable().setModel(model);
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