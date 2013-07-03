package gui;

import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;

import obj.*;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
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
    	fromViewer = mainWindow.getxGraph().getWorldViewer().newViewerPipe();
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
                mainWindow.getxGraph().getWorldViewer().disableAutoLayout();
                
                Iterator iter = mainWindow.getxGraph().getWorldGraph().getNodeIterator();
                while(iter.hasNext()) {
                	org.graphstream.graph.Node nodeAux = (org.graphstream.graph.Node) iter.next();
                	nodeAux.addAttribute("ui.style", "fill-color: white;");
                }
                mainWindow.getxGraph().getWorldGraph().getNode(id).addAttribute("ui.style", "fill-color: green;");
                mainWindow.getxGraph().changeSelectedWorld(id);
                
            }
        });
        //fromViewer.addSink(graph);
        
        
        fromViewer2 = mainWindow.getxGraph().getSelectedViewer().newViewerPipe();
        //fromViewer2 = new BlockingViewerPipe(mainWindow.getxGraph().getViewer());
        fromViewer2.addViewerListener(new ViewerListener() {
        	public void buttonPushed(String id) {
        		int i;
        		System.out.println(id);
        		ArrayList<String> typeList = mainWindow.getxGraph().getXmlFile().getAtomTypeOnWorld(id, selectedWorld);
        		for(i=0; i<typeList.size(); i++) {
        			String stereoType = TypeName.getTypeName(mainWindow.getxGraph().getOntoUmlParser().getElement(typeList.get(i)));
        			System.out.println(stereoType + " - - " + typeList.get(i));
        		}
        	}
        	public void buttonReleased(String id) {
        		if(!id.contains("sat")) {
	        		Node nodePressed = mainWindow.getxGraph().getSelectedGraph().getNode(id);
	        		ArrayList<String> typeList = mainWindow.getxGraph().getXmlFile().getAtomTypeOnWorld(id, selectedWorld);
	        		OntoUMLParser ontoUmlParser = mainWindow.getxGraph().getOntoUmlParser();
	        		XMLFile xmlFile = mainWindow.getxGraph().getXmlFile();
	        		int indexOfImage = mainWindow.getxGraph().getAllTypes().indexOf(xmlFile.getAtomMainType(id, selectedWorld).getName());
	        		String imagePath = mainWindow.getxGraph().getTypeImages().get(indexOfImage);
	        		if(mainWindow.isPopOut()) {
	        			JDialog popOutWindow = new JDialog();
	        			//popOutWindow.setTitle((String)mainWindow.getxGraph().getSelectedGraph().getNode(id).getAttribute("ui.label"));
	        			popOutWindow.setContentPane(new PropertiesPanel(imagePath, nodePressed, mainWindow.getXmlFile(), ontoUmlParser, selectedWorld));
	        			popOutWindow.setBounds(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, 195, 238);
	        			popOutWindow.setVisible(true);
	        			popOutWindow.toFront();
	        		}else{
	        			if(!(mainWindow.getTabbedPane().getTabCount() == 1)) {
	        				mainWindow.getTabbedPane().removeTabAt(1);
	        			}
	        			mainWindow.getTabbedPane().addTab((String) mainWindow.getxGraph().getSelectedGraph().getNode(id).getAttribute("ui.label"), null, new PropertiesPanel(imagePath, nodePressed, mainWindow.getXmlFile(), ontoUmlParser, selectedWorld), null);
		        		mainWindow.getTabbedPane().setSelectedIndex(mainWindow.getTabbedPane().getTabCount()-1);
	        		}
	        		
        		}else{
        			System.out.println("not a valid node to display properties");
        		}
        	}
        	public void viewClosed(String viewName) {
        		
        	}
        });
		
        
        
        while(loop) {
        	Thread.sleep(100);
        	/*
        	Collection<Node> nodeList = mainWindow.getxGraph().getSelectedGraph().getNodeSet();
    		Collection<Edge> edgeList = mainWindow.getxGraph().getSelectedGraph().getEdgeSet();
    		Iterator node = nodeList.iterator();
    		Iterator edge = edgeList.iterator();
    		while(node.hasNext()) {
    			//Node n = node.next();
    			Node nodem = (Node) node.next();
    			nodem.addAttribute("layout.weight", -1);
    		}
    		while(edge.hasNext()) {
    			//Node n = node.next();
    			Edge edgem = (Edge) edge.next();
    			edgem.addAttribute("layout.weight", 20);
    		}
    		*/
        	//mainWindow.getxGraph().getSelectedGraph().
        	//mainWindow.getxGraph().getView().getCamera().setAutoFitView(true);
        	//System.out.println("PUMPING");
        	//mainWindow.getScrollPane_1().requestFocusInWindow();
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