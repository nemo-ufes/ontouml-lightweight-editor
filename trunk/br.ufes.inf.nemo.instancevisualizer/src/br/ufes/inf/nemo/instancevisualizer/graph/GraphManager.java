package br.ufes.inf.nemo.instancevisualizer.graph;

import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JToolTip;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.graphicGraph.GraphicElement;
import org.graphstream.ui.graphicGraph.GraphicSprite;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.util.Camera;
import org.graphstream.ui.swingViewer.util.DefaultMouseManager;
import org.graphstream.ui.swingViewer.util.DefaultShortcutManager;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.instancevisualizer.apl.AplMainWindow;
import br.ufes.inf.nemo.instancevisualizer.gui.MainWindow;
import br.ufes.inf.nemo.instancevisualizer.gui.PropertiesPanel;
import br.ufes.inf.nemo.instancevisualizer.xml.Atom;
import br.ufes.inf.nemo.instancevisualizer.xml.Field;
import br.ufes.inf.nemo.instancevisualizer.xml.Tuple;
import br.ufes.inf.nemo.instancevisualizer.xml.XMLFile;

public class GraphManager {
	
    private XMLFile xmlFile;
    
    private Graph worldGraph;
    private Viewer worldViewer;
    private View worldView;
    private String selectedWorld;
    
    private Graph selectedGraph;
    private ArrayList<Graph> graphList;
    private Viewer selectedViewer;
    private View selectedView;
    private SpringBox layout;
    
    private NodeLegendManager nodeLegendManager;
    private NodeManager nodeManager;
    private EdgeManager edgeManager;
    
    private MainWindow mainWindow;
    
    private boolean worldChanging;
  
    public GraphManager(XMLFile xmlFile, OntoUMLParser ontoUmlParser, MainWindow mainWindow) {
        this.xmlFile = xmlFile;
        
        worldGraph = null;
        worldViewer = null;
        worldView = null;
        selectedWorld = null;
        
        selectedGraph = null;//new MultiGraph("Selected World");
        graphList = new ArrayList();
        selectedViewer = null;
        selectedView = null;
        layout = null;
        
        nodeLegendManager = new NodeLegendManager(ontoUmlParser, xmlFile);
        nodeManager = new NodeManager(xmlFile, nodeLegendManager);
        edgeManager = new EdgeManager(xmlFile, nodeManager);
        
        this.mainWindow = mainWindow;
        
        worldChanging = false;
        
        //createSelectedWorld("world_structure/PastWorld$0");
        //createSelectedWorldToList();
        createWorldMap();
        createAllSelectedWorlds();/*
        for(Graph g : graphList) {
        	if(g.getId() == "world_structure/CurrentWorld$0") {
        		setSelectedGraph(g);
        		break;
        	}
        }
        setSelectedWorld("world_structure/CurrentWorld$0");
        //mainWindow.setScrollPanes1();
        */
    }
    
    /**
	   * Creates the world map graph based on the XMLFile file.
	   */
	public org.graphstream.graph.Graph createWorldMap() {
        ArrayList<Atom> atomList = xmlFile.getAtomList();
        ArrayList<Field> fieldList = xmlFile.getFieldList();
        
        int i, j;
        worldGraph = new MultiGraph("World Map");
        ArrayList<Tuple> tuplesList;
        Tuple tuple;
        int current=1, past=-2, future=2;
        
        // Getting only world atoms.
        for(i=0; i<atomList.size(); i++) {
        	if(atomList.get(i).isWorld()) {
        		//System.out.println(atomList.get(i).getLabel().substring(0, 15));
        		org.graphstream.graph.Node nodeAux = worldGraph.addNode(atomList.get(i).getLabel());
        		
                    switch(atomList.get(i).getWorldType()) {
                        case "Past":
                        	nodeAux.addAttribute("ui.style", "size: 10px; fill-color: white;");
                			nodeAux.addAttribute("xy", past, 0);
                			past--;
                			past--;
                            break;
                        case "Current":
                        	//nodeAux.addAttribute("ui.style", "size: 15px; fill-color: green; shadow-mode: gradient-radial; shadow-width: 10px; shadow-color: black, white; shadow-offset: 0px;");
                			//nodeAux.addAttribute("ui.label", "C");
                        	nodeAux.addAttribute("ui.style", "size: 10px; fill-color: green;");
                			nodeAux.addAttribute("xy", 0, 0);
                            break;
                        case "Future":
                        	nodeAux.addAttribute("ui.style", "size: 10px; fill-color: white;");
                			nodeAux.addAttribute("xy", future, 0);
                			future++;
                			future++;
                            break;
                        case "Counterfactual":
                        	nodeAux.addAttribute("ui.style", "size: 10px; fill-color: white;");
                			nodeAux.addAttribute("xy", 0, current);
                			current=-1;
                            break;
                        case "Temporal":
                            break;
                        default:
                            System.exit(1);
                    }
                    
        	}
        }
        
        // Getting only "next" associations - the only thing we need in a worlds graph.
        for(i=0; i<fieldList.size(); i++) {
            if(fieldList.get(i).getLabel().equals("next")) {
                tuplesList = fieldList.get(i).getTuples();
                for(j=0; j<tuplesList.size(); j++) {
                    tuple = tuplesList.get(j);
                    //for(k=0; k<tuple.size() - 1; k++) {	// 0=k; 1=k+1
                        try {
                            //String edgeName = tuple.get(0) + "_" + fieldList.get(i).getLabel() + "_" + tuple.get(1);
                            //edgeName = edgeName.concat(tuple.get(k+1).getLabel());
                            //System.out.println(tuple.get(0));
                            //System.out.println(tuple.get(1));
                            worldGraph.addEdge(fieldList.get(i).getLabel() + "$" + j, tuple.get(0), tuple.get(1), true);//.addAttribute("ui.label", fieldList.get(i).getLabel());
                        } catch (Exception e) {
                        	
                        }
                    //}
                }
            }
            break;
        }
        
        worldGraph.addAttribute("ui.antialias");
        
        worldGraph.addAttribute("ui.stylesheet", "graph {\n" +
"padding: 40px;\n" +
"}\n" +
"node { size-mode: normal; text-size: 12; size: 10px; fill-color: white; stroke-mode: plain;} edge { shape: blob; size: 10px; }");

        return worldGraph;
    }
    
	public void createAllSelectedWorlds() {
    	selectedGraph = new MultiGraph("test");
		for(int i=0; i<nodeManager.getNodeAmount(); i++) {
			NodeM n = nodeManager.getNode(i);
			Node node = selectedGraph.addNode(n.getId());
			//System.out.println(n.getId());
			if(n.existsInWorld("world_structure/CurrentWorld$0")) {
				String mainType = n.getMainType("world_structure/CurrentWorld$0");//xmlFile.getAtomMainType(idList.get(i), selectedWorld).getName();
				String stylePath = AplMainWindow.STYLE_NODE_PATH + mainType + AplMainWindow.STYLE_EXT;
				File f = new File(stylePath);
				if(f.exists()) {
				    try {
				    	FileInputStream inputFileStream = new FileInputStream(f);
					    ObjectInputStream objectInputStream = new ObjectInputStream(inputFileStream);
						NodeLegend nl = (NodeLegend) objectInputStream.readObject();
						objectInputStream.close();
					    inputFileStream.close();
					    nodeLegendManager.setLegendWithType(mainType, nl);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}
    			String attrib = nodeLegendManager.getLegendWithType(mainType).getStyle();
    			node.addAttribute("ui.style", attrib);
    			Iterator<Attribute> attrs = n.getAttributeIterator("world_structure/CurrentWorld$0");
    			while(attrs.hasNext()) {
    				Attribute attr = attrs.next();
    				node.addAttribute(attr.getName(), attr.getValue());
    			}
				
			}else{
				node.addAttribute("ui.style", "visibility-mode: hidden;");
			}
									/*
			Node node = selectedGraph.addNode(n.getId());
			String mainType = "";
			String attrib = "";
			String arg0 = "";
			
			if(!n.existsInWorld("world_structure/CurrentWorld$0")) {
				arg0 = "old.";
			}else{
				mainType = n.getMainType("world_structure/CurrentWorld$0");//xmlFile.getAtomMainType(idList.get(i), selectedWorld).getName();
				attrib = nodeLegendManager.getLegendWithType(mainType).getStyle();
			}
			node.addAttribute(arg0 + "ui.style", attrib);
			Iterator<Attribute> attrs = n.getAttributeIterator("world_structure/CurrentWorld$0");
			while(attrs.hasNext()) {
    			Attribute attr = attrs.next();
    			node.addAttribute(attr.getName(), attr.getValue());
    		}*/
		}
		for(int i=0; i<edgeManager.getEdgeAmount(); i++) {
    		EdgeM e = edgeManager.getEdge(i);
    		Edge edge = selectedGraph.addEdge(e.getId(), e.getNode0Id(), e.getNode1Id(), true);
    		e.setEdgeRef("world_structure/CurrentWorld$0", edge);
    		if(e.existsInWorld("world_structure/CurrentWorld$0")) {
    			Iterator<Attribute> attrs = e.getAttributeIterator("world_structure/CurrentWorld$0");
    			while(attrs.hasNext()) {
    				Attribute attr = attrs.next();
    				edge.addAttribute(attr.getName(), attr.getValue());
    			}
    			String style = edgeManager.getEdgeLegendManager().getEdgeLegendWithStereotype(e.getStereoTypeOnWorld("world_structure/CurrentWorld$0")).getStyle();
    			edge.addAttribute("ui.style", style);
    		}else{
    			edge.addAttribute("ui.style", "visibility-mode: hidden;");
				edge.addAttribute("invisible", "");
    		}
		}
		
		selectedGraph.addAttribute("ui.antialias");
	    selectedGraph.addAttribute("ui.quality");
	    selectedGraph.addAttribute("layout.quality", 4);
	        
	    selectedGraph.addAttribute("ui.stylesheet", "graph {\n" +
	//"    padding: 100px, 100px, 0px;\n" +
	"}\n" +
	"node {\n" +
	"    text-size: 12;\n" +
	"	 text-alignment: under;\n" +
	"    stroke-mode: plain;\n" +
	"    stroke-color: black;\n" +
	"    text-visibility-mode: under-zoom;\n" +
	"    text-visibility: 0.5;\n" +
	"}\n" +
	"edge {\n" +
	"    fill-color: black;\n" +
	"	 arrow-shape: none;\n" +
	"}\n");
	}
	
	public void changeSelectedWorld(String world) {
		if(selectedWorld.equals(world)) {
			setWorldChanging(false);
    		return;
		}
		
		Iterator<EdgeM> toBlacken = edgeManager.getEdgeIterator();
		while(toBlacken.hasNext()) {
			selectedGraph.getEdge(toBlacken.next().getId()).addAttribute("ui.style", "fill-color: black;");
		}
		
    	final String worldChange = world;
    	ArrayList<NodeM>[] nodeChange = nodeManager.nodesToKill(selectedWorld, world);	//get nodes that doesn't exist on the next world
    	ArrayList<EdgeM>[] edgeChange = edgeManager.edgesToKill(selectedWorld, world);	//get nodes that doesn't exist on the next world
    	final ArrayList<NodeM> nodesToKill = nodeChange[0];
    	final ArrayList<NodeM> nodesToAdd = nodeChange[1];
    	final ArrayList<EdgeM> edgesToKill = edgeChange[0];
    	final ArrayList<EdgeM> edgesToAdd = edgeChange[1];
    	
    	new Thread()
        {
            public void run() {
            	//selectedViewer.disableAutoLayout();
            	int sz = 32;
            	double szstroke = 1;
            	double szsize = 0.25;
            	
            	while(sz > 0 && !nodesToKill.isEmpty() && !edgesToKill.isEmpty()) {
        	    	for(NodeM n : nodesToKill) {
        	    		Node node = selectedGraph.getNode(n.getId());
        	    		if(node != null) {
        	    			String attr = selectedGraph.getNode(n.getId()).getAttribute("ui.style");
            	    		selectedGraph.getNode(n.getId()).addAttribute("ui.style", attr + "\nsize: " + sz + "px;");
        	    		}else{
        	    			System.out.println("nao era pra imprimir - nó");
        	    		}
        	    	}
        	    	
        	    	for(EdgeM e : edgesToKill) {
        	    		Edge edge = selectedGraph.getEdge(e.getId());
        	    		if(edge != null) {
        	    			String attr = edge.getAttribute("ui.style");
            	    		edge.addAttribute("ui.style", attr + "stroke-width:" + szstroke + ";" + "size:" + szsize +"px;\n");
        	    		}else{
        	    			System.out.println("nao era pra imprimir - aresta");
        	    		}
        	    	}
        	    	
        	    	szstroke -= 0.0625;
        	    	szsize -= 0.015625;
        	    	sz -= 2;
        	    	try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
            	}
            	
            	for(EdgeM e : edgesToKill) {
    	    		Edge edge = selectedGraph.getEdge(e.getId());
    	    		if(edge != null) {
    	    			edge.addAttribute("ui.style", "visibility-mode: hidden;");
    	    		}else{
    	    			System.out.println("nao era pra imprimir - aresta");
    	    		}
    	    	}
            	
            	for(NodeM n : nodesToKill) {
    	    		Node node = selectedGraph.getNode(n.getId());
    	    		if(node != null) {
    	    			node.addAttribute("ui.style", "visibility-mode: hidden;");
    	    		}else{
    	    			System.out.println("nao era pra imprimir - nó");
    	    		}
    	    	}
            	
            	for(NodeM n : nodesToAdd) {
    	    		Node node = selectedGraph.getNode(n.getId());
    	    		node.removeAttribute("ui.style");
    	    		node.setAttribute("ui.style", "visibility-mode: normal;");
    	    	}
            	for(EdgeM e : edgesToAdd) {
    	    		Edge edge = selectedGraph.getEdge(e.getId());
    	    		edge.removeAttribute("ui.style");
    	    		edge.setAttribute("ui.style", "visibility-mode: normal;");
    	    	}
            	setSelectedWorld(worldChange);
            	update();
            	szstroke = 0.0625;
    	    	szsize = 0.015625;
    	    	sz = 2;
	            while(sz < 32 && !nodesToAdd.isEmpty() && !edgesToAdd.isEmpty()) {
	            	for(NodeM n : nodesToAdd) {
	    	    		Node node = selectedGraph.getNode(n.getId());
        	    		if(node != null) {
        	    			//String attr = node.getAttribute("ui.style");
        	    			//if(attr != null)
            	    		node.addAttribute("ui.style", "\nsize: " + sz + "px;");
        	    			//selectedGraph.getNode(n.getId()).addAttribute("ui.style", attr + "\nfill-color: rgba(0,0,0,128);");
        	    		}else{
        	    			System.out.println("nao era pra imprimir - nó");
        	    		}
	    	    	}
	            	for(EdgeM e : edgesToAdd) {
	    	    		Edge edge = selectedGraph.getEdge(e.getId());
        	    		if(edge != null) {
        	    			//String attr = edge.getAttribute("ui.style");
            	    		edge.addAttribute("ui.style", "fill-color: red; \nstroke-width:" + szstroke + "; size:" + szsize +"px;\n");
            	    		//System.out.println("Foi");
        	    			//selectedGraph.getEdge(e.getId()).addAttribute("ui.style", attr + "\nstroke-width: 0.1;" + "size: 0.1;\n");
        	    		}else{
        	    			System.out.println("nao era pra imprimir - aresta");
        	    		}
	    	    	}
	            	szstroke += 0.0625;
        	    	szsize += 0.015625;
        	    	sz += 2;
        	    	try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	            }
            	
            	update();
    			setWorldChanging(false);
            }
        }.start();
	}
	
    public void createSelectedWorldToList() {
    	ArrayList<Atom> worldList = xmlFile.getWorldList();
    	ArrayList<String> worlds = new ArrayList();
    	for(Atom w : worldList) {
    		worlds.add(w.getLabel());
    	}
    	
    	for(int j=0; j<worlds.size(); j++) {
    		String world = worlds.get(j);
    		Graph graph = new MultiGraph(world);
	    	for(int i=0; i<nodeManager.getNodeAmount(); i++) {
	    		NodeM n = nodeManager.getNode(i);
	    		if(n.existsInWorld(world)) {
	    			Node node = graph.addNode(n.getId());
	    			
	    			String mainType = n.getMainType(world);//xmlFile.getAtomMainType(idList.get(i), selectedWorld).getName();
	    			String attrib = nodeLegendManager.getLegendWithType(mainType).getStyle();
	    			node.addAttribute("ui.style", attrib);
	    			
	    			Iterator<Attribute> attrs = n.getAttributeIterator(world);
	    			//System.out.println("\nWORLD: " + world);
	    			while(attrs.hasNext()) {
	    				Attribute attr = attrs.next();
	    				//System.out.println(node.getId() + "Attr" + attr.getName() + attr.getValue());
	    				node.addAttribute(attr.getName(), attr.getValue());
	    			}
	    			//System.out.println("\n");
	    		}
	    	}
	    	
	    	for(int i=0; i<edgeManager.getEdgeAmount(); i++) {
	    		EdgeM e = edgeManager.getEdge(i);
	    		if(e.existsInWorld(world)) {
	    			Edge edge = graph.addEdge(e.getId(), e.getNode0Id(), e.getNode1Id(), true);
	    			e.setEdgeRef(world, edge);
	    			//System.out.println(e.getEdgeRef(world));
	    			Iterator<Attribute> attrs = e.getAttributeIterator(world);
	    			while(attrs.hasNext()) {
	    				Attribute attr = attrs.next();
	    				edge.addAttribute(attr.getName(), attr.getValue());
	    			}
	    			//System.out.println(edgeManager);
	    			//System.out.println(getEdgeLegendManager());
	    			String style = edgeManager.getEdgeLegendManager().getEdgeLegendWithStereotype(e.getStereoTypeOnWorld(world)).getStyle();
	    			edge.addAttribute("ui.style", style);
	    		}
	    	}
	    	
	    	graph.addAttribute("ui.antialias");
	        graph.addAttribute("ui.quality");
	        graph.addAttribute("layout.quality", 4);
	        
	        graph.addAttribute("ui.stylesheet", "graph {\n" +
	//"    padding: 100px, 100px, 0px;\n" +
	"}\n" +
	"node {\n" +
	"    text-size: 12;\n" +
	"	 text-alignment: under;\n" +
	"    stroke-mode: plain;\n" +
	"    stroke-color: black;\n" +
	"    text-visibility-mode: under-zoom;\n" +
	"    text-visibility: 0.5;\n" +
	"}\n" +
	"edge {\n" +
	"    fill-color: black;\n" +
	"	 arrow-shape: none;\n" +
	"}\n");
	        graphList.add(graph);
	    }
	}
    
    public void changeWorldNew(String world) {
    	if(selectedWorld.equals(world))
    		return;
    	final String worldChange = world;
    	ArrayList<NodeM>[] nodeChange = nodeManager.nodesToKill(selectedWorld, world);	//get nodes that doesn't exist on the next world
    	ArrayList<EdgeM>[] edgeChange = edgeManager.edgesToKill(selectedWorld, world);	//get nodes that doesn't exist on the next world
    	final ArrayList<NodeM> nodesToKill = nodeChange[0];
    	final ArrayList<NodeM> nodesToAdd = nodeChange[1];
    	final ArrayList<EdgeM> edgesToKill = edgeChange[0];
    	final ArrayList<EdgeM> edgesToAdd = edgeChange[1];
    	
    	new Thread()
        {
            public void run() {
            	//selectedViewer.disableAutoLayout();
            	int sz = 32;
            	double szstroke = 1;
            	double szsize = 0.25;
            	while(sz > 0 && !nodesToKill.isEmpty() && !edgesToKill.isEmpty()) {
        	    	for(NodeM n : nodesToKill) {
        	    		Node node = selectedGraph.getNode(n.getId());
        	    		if(node != null) {
        	    			String attr = selectedGraph.getNode(n.getId()).getAttribute("ui.style");
            	    		selectedGraph.getNode(n.getId()).addAttribute("ui.style", attr + "\nsize: " + sz + "px;");
        	    			//selectedGraph.getNode(n.getId()).addAttribute("ui.style", attr + "\nfill-color: rgba(0,0,0,128);");
        	    		}else{
        	    			System.out.println("nao era pra imprimir - nó");
        	    		}
        	    	}
        	    	
        	    	for(EdgeM e : edgesToKill) {
        	    		Edge edge = selectedGraph.getEdge(e.getId());
        	    		if(edge != null) {
        	    			String attr = edge.getAttribute("ui.style");
            	    		edge.addAttribute("ui.style", attr + "fill-color: red; \nstroke-width:" + szstroke + ";" + "size:" + szsize +"px;\n");
        	    			//selectedGraph.getEdge(e.getId()).addAttribute("ui.style", attr + "\nstroke-width: 0.1;" + "size: 0.1;\n");
        	    		}else{
        	    			System.out.println("nao era pra imprimir - aresta");
        	    		}
        	    	}
        	    	
        	    	szstroke -= 0.0625;
        	    	szsize -= 0.015625;
        	    	sz -= 2;
        	    	try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
            	}
            	
            	for(EdgeM e : edgesToKill) {
    	    		Edge edge = selectedGraph.getEdge(e.getId());
    	    		if(edge != null) {
    	    			selectedGraph.removeEdge(edge);
    	    		}else{
    	    			System.out.println("nao era pra imprimir - aresta");
    	    		}
    	    	}
            	
            	for(NodeM n : nodesToKill) {
    	    		Node node = selectedGraph.getNode(n.getId());
    	    		if(node != null) {
    	    			selectedGraph.removeNode(node);
    	    		}else{
    	    			System.out.println("nao era pra imprimir - nó");
    	    		}
    	    	}
            	
            	for(NodeM n : nodesToAdd) {
    	    		Node node = selectedGraph.addNode(n.getId());
    	    		
    	    	}
            	for(EdgeM e : edgesToAdd) {
    	    		Edge edge = selectedGraph.addEdge(e.getId(), e.getNode0Id(), e.getNode1Id(), true);
    	    	}
            	setSelectedWorld(worldChange);
            	update();
            	szstroke = 0.0625;
    	    	szsize = 0.015625;
    	    	sz = 2;
	            while(sz < 32 && !nodesToAdd.isEmpty() && !edgesToAdd.isEmpty()) {
	            	for(NodeM n : nodesToAdd) {
	    	    		Node node = selectedGraph.getNode(n.getId());
        	    		if(node != null) {
        	    			//String attr = node.getAttribute("ui.style");
        	    			//if(attr != null)
            	    		node.addAttribute("ui.style", "\nsize: " + sz + "px;");
        	    			//selectedGraph.getNode(n.getId()).addAttribute("ui.style", attr + "\nfill-color: rgba(0,0,0,128);");
        	    		}else{
        	    			System.out.println("nao era pra imprimir - nó");
        	    		}
	    	    	}
	            	for(EdgeM e : edgesToAdd) {
	    	    		Edge edge = selectedGraph.getEdge(e.getId());
        	    		if(edge != null) {
        	    			//String attr = edge.getAttribute("ui.style");
            	    		edge.addAttribute("ui.style", "fill-color: red; \nstroke-width:" + szstroke + "; size:" + szsize +"px;\n");
            	    		//System.out.println("Foi");
        	    			//selectedGraph.getEdge(e.getId()).addAttribute("ui.style", attr + "\nstroke-width: 0.1;" + "size: 0.1;\n");
        	    		}else{
        	    			System.out.println("nao era pra imprimir - aresta");
        	    		}
	    	    	}
	            	szstroke += 0.0625;
        	    	szsize += 0.015625;
        	    	sz += 2;
        	    	try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	            }
            	
            	update();
            }
        }.start();
    	
    }
    
    public View showWorldGraph() {
        worldViewer = new Viewer(worldGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        worldViewer.disableAutoLayout();
        worldView = worldViewer.addDefaultView(false);   // false indicates "no JFrame".
        worldView.getCamera().setViewCenter(0, 0, 0);
        worldView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				worldView.repaint();
			}
		});

        DefaultMouseManager worldManager = new DefaultMouseManager() {
        	protected void elementMoving(GraphicElement element, MouseEvent event) {
        	}
        	
        	private JToolTip tip = null;
        	
        	public void mouseMoved(MouseEvent event) {
        		curElement = view.findNodeOrSpriteAt(event.getX(), event.getY());
        		/*
        		if(tip != null) {
        			tip.setLocation(event.getX() - tip.getTipText().length()*3 + 1, event.getY());
        			tip.repaint();
        		}
        		*/
        		if(curElement == null && tip != null) {
        			tip.setVisible(false);
					setTip(null);
					view.repaint();
        		}
        		
        		if(curElement != null && tip == null) {
        			String worldId = curElement.getId();
        			int idSize = worldId.length();
        			String worldNo = worldId.substring(idSize-1);
        			String tipText = xmlFile.getAtom(worldId).getWorldType() + " " + worldNo;
        			tip = new JToolTip();
        			tip.setTipText(tipText);
        			tip.setBounds(event.getX() - tipText.length()*3 + 1, event.getY(), tipText.length()*6 + 3, 20);
        			setTip(tip);
        			tip.setVisible(true);
        			
        			if(tipText.length() > 10) {
        				tip.setLocation(event.getX()-15, event.getY());
        			}
        			
        			view.add(tip);
        			tip.repaint();
        		}
        	}
        	
        	protected void mouseButtonPressOnElement(GraphicElement element, MouseEvent event) {
        		if(!worldChanging) {
        			setWorldChanging(true);
	        		view.freezeElement(element, true);
	        		if (event.getButton() != 3) {
	        			element.addAttribute("ui.selected");
	                    String id = element.getId();
	                    //System.out.println("MUNDOW:" + id);
	                    Iterator iter = getWorldGraph().getNodeIterator();
	                    while(iter.hasNext()) {
	                    	org.graphstream.graph.Node nodeAux = (org.graphstream.graph.Node) iter.next();
	                    	nodeAux.addAttribute("ui.style", "fill-color: white;");
	                    }
	                    getWorldGraph().getNode(id).addAttribute("ui.style", "fill-color: green;");
	                    changeSelectedWorld(id);
	                    
                    /*
                    for(Graph g : graphList) {
                    	if(g.getId() == id) {
                    		setSelectedGraph(g);
                    		break;
                    	}
                    }
                    setSelectedWorld(id);
                    */
                    //getMainWindow().setScrollPanes1();
	        		} else {
	        			element.addAttribute("ui.clicked");
	        		}
        		}
        	}
        	protected void setTip(JToolTip toolTip) {
        		this.tip = toolTip;
        	}
        };
        worldView.setMouseManager(worldManager);
        worldView.setShortcutManager(new DefaultShortcutManager() {
        	public void keyPressed(KeyEvent event) {
        	}

        	public void keyReleased(KeyEvent event) {
        	}

        	public void keyTyped(KeyEvent event) {
        	}
        });
        return worldView;
    }
    
    public View showSelectedGraph() {
    	
        selectedViewer = new Viewer(selectedGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        //viewer.enableAutoLayout();
        //Viewer v = new Viewer(g, Viewer.TheadingModel.GRAPH_IN_ANOTHER_THREAD);
        //SpringBox layout = new SpringBox();
        layout = new SpringBox();
        //layout.setGravityFactor(0.5);
        //layout.freezeNode("Object$1", true);
        //layout.moveNode("Object$1", 0, 0, 0);
        layout.setForce(1);
        layout.setStabilizationLimit(0.09);
        //layout.configure(2, 2, false, 1);
        selectedViewer.enableAutoLayout(layout);
        selectedView = selectedViewer.addDefaultView(false);   // false indicates "no JFrame".
        
        DefaultShortcutManager shortcutManager = new DefaultShortcutManager() {
        	public void keyPressed(KeyEvent event) {
        		Camera camera = view.getCamera();
        		
        		if (event.getKeyCode() == KeyEvent.VK_PAGE_UP && camera.getViewPercent() > 0.05) {
        			//camera.setViewPercent(camera.getViewPercent() - 0.05f);
        		} else if (event.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        			//camera.setViewPercent(camera.getViewPercent() + 0.05f);
        		} else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
        			if ((event.getModifiers() & KeyEvent.ALT_MASK) != 0) {
        				double r = camera.getViewRotation();
        				camera.setViewRotation(r - 5);
        			} else {
        				double delta = 0;

        				if ((event.getModifiers() & KeyEvent.SHIFT_MASK) != 0)
        					delta = camera.getGraphDimension() * 0.1f;
        				else
        					delta = camera.getGraphDimension() * 0.01f;

        				Point3 p = camera.getViewCenter();
        				camera.setViewCenter(p.x - delta, p.y, 0);
        			}
        		} else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
        			if ((event.getModifiers() & KeyEvent.ALT_MASK) != 0) {
        				double r = camera.getViewRotation();
        				camera.setViewRotation(r + 5);
        			} else {
        				double delta = 0;

        				if ((event.getModifiers() & KeyEvent.SHIFT_MASK) != 0)
        					delta = camera.getGraphDimension() * 0.1f;
        				else
        					delta = camera.getGraphDimension() * 0.01f;

        				Point3 p = camera.getViewCenter();
        				camera.setViewCenter(p.x + delta, p.y, 0);
        			}
        		} else if (event.getKeyCode() == KeyEvent.VK_UP) {
        			double delta = 0;

        			if ((event.getModifiers() & KeyEvent.SHIFT_MASK) != 0)
        				delta = camera.getGraphDimension() * 0.1f;
        			else
        				delta = camera.getGraphDimension() * 0.01f;

        			Point3 p = camera.getViewCenter();
        			camera.setViewCenter(p.x, p.y + delta, 0);
        		} else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
        			double delta = 0;

        			if ((event.getModifiers() & KeyEvent.SHIFT_MASK) != 0)
        				delta = camera.getGraphDimension() * 0.1f;
        			else
        				delta = camera.getGraphDimension() * 0.01f;

        			Point3 p = camera.getViewCenter();
        			camera.setViewCenter(p.x, p.y - delta, 0);
        		}
        	}
        };

        selectedView.setShortcutManager(shortcutManager);
        //shortcutManager.
        //selectedView.getCamera().setViewPercent(1);
        
        DefaultMouseManager selectedManager = new DefaultMouseManager() {
        	Point3 origPx;
        	protected void mouseButtonPress(MouseEvent event) {
        		if(event.getButton() != MouseEvent.BUTTON2) {
	        		origPx = new Point3(event.getX(), event.getY(), 0);
	        		view.requestFocus();
	        		// Unselect all.
	        		if (!event.isShiftDown()) {
	        			for (Node node : graph) {
	        				if (node.hasAttribute("ui.selected"))
	        					node.removeAttribute("ui.selected");
	        			}
	
	        			for (GraphicSprite sprite : graph.spriteSet()) {
	        				if (sprite.hasAttribute("ui.selected"))
	        					sprite.removeAttribute("ui.selected");
	        			}
	        		}
        		}else{
        			Camera camera = view.getCamera();
	        		Point3 p3 = camera.transformPxToGu(event.getX(), event.getY());//.getMetrics()..setViewCenter(event.getX(), event.getY(), 0);
	        		camera.setViewCenter(p3.x, p3.y, p3.z);
        		}
        	}

        	protected void mouseButtonRelease(MouseEvent event,
        			ArrayList<GraphicElement> elementsInArea) {
        		for (GraphicElement element : elementsInArea) {
        			if (!element.hasAttribute("ui.selected"))
        				element.addAttribute("ui.selected");
        		}
        		getSelectedView().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        	}
        	
        	public void mouseDragged(MouseEvent event) {
        		if (curElement != null) {
        			elementMoving(curElement, event);
        		} else {
        			// px:
        			// -1 : down and left
        			// +1 : up and right
        			getSelectedView().setCursor(new Cursor(Cursor.MOVE_CURSOR));
        			Point3 diffPx = new Point3(event.getX()-origPx.x, origPx.y-event.getY(), 0);
        			Point3 origGu = view.getCamera().getViewCenter();
        			//Point3 diffGu = new Point3(origGu.x + diffPx.x*Math.pow(10, -16), origGu.y + diffPx.y*Math.pow(10, -16), 0);
        			double deltaX = view.getCamera().getGraphDimension() * (0.001f * diffPx.x);
        			double deltaY = view.getCamera().getGraphDimension() * (0.001f * diffPx.y);
        			//System.out.println(diffPx);
        			view.getCamera().setViewCenter(origGu.x+deltaX, origGu.y+deltaY, 0);//-p.x+view.getCamera().getViewCenter().x, -p.y+view.getCamera().getViewCenter().y, 0);
        			origPx = new Point3(event.getX(), event.getY(), 0);
        		}
        	}
        	
        	protected void mouseButtonPressOnElement(GraphicElement element, MouseEvent event) {
        		view.freezeElement(element, true);
        		if (event.getButton() != 3) {
        			element.addAttribute("ui.selected");
        		} else {
        			element.addAttribute("ui.clicked");
        		}
        		if(event.getButton() == MouseEvent.BUTTON2) {
        			Camera camera = view.getCamera();
        	        Point3 p3 = camera.transformPxToGu(event.getX(), event.getY());//.getMetrics()..setViewCenter(event.getX(), event.getY(), 0);
        	        camera.setViewCenter(p3.x, p3.y, p3.z);
        		}
        	}
        	
        	protected void elementMoving(GraphicElement element, MouseEvent event) {
        		view.moveElementAtPx(element, event.getX(), event.getY());
        	}
        	
        	protected void mouseButtonReleaseOffElement(GraphicElement element, MouseEvent event) {
        		view.freezeElement(element, false);
        		if (event.getButton() == 3) {
        			element.removeAttribute("ui.clicked");
        			
        			String id = element.getId();
        			Node nodePressed = getSelectedGraph().getNode(id);
	        		ArrayList<String> typeList = getXmlFile().getAtomTypeOnWorld(id, selectedWorld);
	        		OntoUMLParser ontoUmlParser = xmlFile.getOntoUmlParser();
	        		XMLFile xmlFile = getXmlFile();
	        		//System.out.println("CLICKED ON " + id);
	        		String mainTypeName = xmlFile.getAtomMainType(id, selectedWorld).getName();
	        		String imagePath = nodeLegendManager.getLegendWithType(mainTypeName).getFillImage();
	        		if(AplMainWindow.popOutEnabled) {
	        			JDialog popOutWindow = new JDialog();
	        			//popOutWindow.setTitle((String)mainWindow.getxGraph().getSelectedGraph().getNode(id).getAttribute("ui.label"));
	        			popOutWindow.setContentPane(new PropertiesPanel(imagePath, nodePressed, xmlFile, ontoUmlParser, selectedWorld, nodeManager, edgeManager));
	        			popOutWindow.setBounds(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, 195, 238);
	        			popOutWindow.setVisible(true);
	        			popOutWindow.toFront();
	        		}else{
	        			if(!(mainWindow.getTabbedPane().getTabCount() == 1)) {
	        				mainWindow.getTabbedPane().removeTabAt(1);
	        			}
	        			//System.out.println(mainWindow.getxGraph().getSelectedGraph().getNode(id).getAttribute("ui.label"));
	        			mainWindow.getTabbedPane().addTab((String) getSelectedGraph().getNode(id).getAttribute("ui.label"), null, new PropertiesPanel(imagePath, nodePressed, xmlFile, ontoUmlParser, selectedWorld, nodeManager, edgeManager), null);
		        		mainWindow.getTabbedPane().setSelectedIndex(mainWindow.getTabbedPane().getTabCount()-1);
	        		}
	        		
        		} else {
        			
        		}
        	}
        	
        };
        
        selectedView.setMouseManager(selectedManager);
    
        //You can find two layouts in gs-core, LinLog and SpringBox. There is also an adaptation of the OpenOrd layout here : https://github.com/gsavin/gs-openord
        return selectedView;
    }
    
    public void update() {
		for(int i=0; i<nodeManager.getNodeAmount(); i++) {
			NodeM n = nodeManager.getNode(i);
			if(n.existsInWorld(selectedWorld)) {
    			Node node = selectedGraph.getNode(n.getId());
    			
    			String mainType = n.getMainType(selectedWorld);	//xmlFile.getAtomMainType(idList.get(i), selectedWorld).getName();
    			NodeLegend nl = nodeLegendManager.getLegendWithType(mainType);
    			String attrib = nl.getStyle();
    			
				try {
					String stylePath = AplMainWindow.STYLE_NODE_PATH + mainType + AplMainWindow.STYLE_EXT;
					File f = new File(stylePath);
					FileOutputStream outputFileStream = new FileOutputStream(f);
					ObjectOutputStream outputStream = new ObjectOutputStream(outputFileStream);
					//writeObject method of ObjectOutputStream will write/serialize the employeeobject to 
					//the path provided by FileOutputStream
					outputStream.writeObject(nl);
					outputStream.close();
					outputFileStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    			
    			//node.removeAttribute("ui.style");
    			node.setAttribute("ui.style", attrib);
    			//node.addAttribute("layout.weight", 100);
    			//if(edgeManager.getEdgesConnecting0(n.getId()).isEmpty() && edgeManager.getEdgesConnecting1(n.getId()).isEmpty())
    				//node.setAttribute("layout.weight", 1);
    			
    			Iterator<Attribute> attrs = n.getAttributeIterator(selectedWorld);
    			while(attrs.hasNext()) {
    				Attribute attr = attrs.next();
    				node.removeAttribute(attr.getName());
    				node.addAttribute(attr.getName(), attr.getValue());
    			}
    		}
		}
		for(int i=0; i<edgeManager.getEdgeAmount(); i++) {
			EdgeM e = edgeManager.getEdge(i);
			if(e.existsInWorld(selectedWorld)) {
    			Edge edge = selectedGraph.getEdge(e.getId());
    			
    			String type = e.getTypeOnWorld(selectedWorld);	//xmlFile.getAtomMainType(idList.get(i), selectedWorld).getName();
    			String attrib = edgeManager.getEdgeLegendManager().getEdgeTypeLegend(type).getStyle();
    			//edge.removeAttribute("ui.style");
    			edge.setAttribute("ui.style", attrib);
    			
    			Iterator<Attribute> attrs = e.getAttributeIterator(selectedWorld);
    			while(attrs.hasNext()) {
    				Attribute attr = attrs.next();
    				edge.removeAttribute(attr.getName());
    				edge.addAttribute(attr.getName(), attr.getValue());
    			}
    		}
		}
    		
    }
    
    public XMLFile getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(XMLFile xmlFile) {
        this.xmlFile = xmlFile;
    }

    public Graph getSelectedGraph() {
        return selectedGraph;
    }

    public void setSelectedGraph(Graph selectedGraph) {
        this.selectedGraph = selectedGraph;
    }
    
    public Graph getWorldGraph() {
        return worldGraph;
    }

    public void setWorldGraph(Graph worldGraph) {
        this.worldGraph = worldGraph;
    }

	public Viewer getSelectedViewer() {
		return selectedViewer;
	}

	public void setSelectedViewer(Viewer selectedViewer) {
		this.selectedViewer = selectedViewer;
	}

	public View getSelectedView() {
		return selectedView;
	}

	public void setSelectedView(View selectedView) {
		this.selectedView = selectedView;
	}

	public Viewer getWorldViewer() {
		return worldViewer;
	}

	public void setWorldViewer(Viewer worldViewer) {
		this.worldViewer = worldViewer;
	}

	public View getWorldView() {
		return worldView;
	}

	public void setWorldView(View worldView) {
		this.worldView = worldView;
	}

	public NodeLegendManager getLegendManager() {
		return nodeLegendManager;
	}

	public void setLegendManager(NodeLegendManager nodeLegendManager) {
		this.nodeLegendManager = nodeLegendManager;
	}
	
	public String getSelectedWorld() {
		return selectedWorld;
	}

	public void setSelectedWorld(String selectedWorld) {
		this.selectedWorld = selectedWorld;
	}

	public NodeManager getNodeManager() {
		return nodeManager;
	}

	public void setNodeManager(NodeManager nodeManager) {
		this.nodeManager = nodeManager;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public EdgeManager getEdgeManager() {
		return edgeManager;
	}

	public void setEdgeManager(EdgeManager edgeManager) {
		this.edgeManager = edgeManager;
	}

	public ArrayList<Graph> getGraphList() {
		return graphList;
	}

	public void setGraphList(ArrayList<Graph> graphList) {
		this.graphList = graphList;
	}

	public boolean isWorldChanging() {
		return worldChanging;
	}

	public void setWorldChanging(boolean worldChanging) {
		this.worldChanging = worldChanging;
	}
	
}
