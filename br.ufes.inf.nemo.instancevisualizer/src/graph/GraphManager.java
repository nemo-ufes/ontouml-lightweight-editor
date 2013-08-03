package graph;

import gui.MainWindow;
import gui.PropertiesPanel;

import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.graphicGraph.GraphicElement;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;

import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.util.DefaultMouseManager; 

import xml.Atom;
import xml.Field;
import xml.XMLFile;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class GraphManager {
	
    private XMLFile xmlFile;
    
    private Graph worldGraph;
    private Viewer worldViewer;
    private View worldView;
    private String selectedWorld;
    
    private Graph selectedGraph;
    private Viewer selectedViewer;
    private View selectedView;
    private SpringBox layout;
    
    private Graph rmGraph;
    private Viewer rmViewer;
    private View rmView;
    private SpringBox rmLayout;
    private ArrayList<String> quaList;
    private String selectedRm;
    
    private NodeLegendManager nodeLegendManager;
    private NodeManager nodeManager;
    private EdgeManager edgeManager;
    
    private MainWindow mainWindow;
  
    public GraphManager(XMLFile xmlFile, OntoUMLParser ontoUmlParser, MainWindow mainWindow) {
        this.xmlFile = xmlFile;
        
        worldGraph = null;
        worldViewer = null;
        worldView = null;
        selectedWorld = null;
        
        selectedGraph = new MultiGraph("Selected World");
        selectedViewer = null;
        selectedView = null;
        layout = null;
        
        rmGraph = new MultiGraph("Material");
        rmViewer = null;
        rmView = null;
        rmLayout = null;
        quaList = new ArrayList();
        selectedRm = null;
        
        nodeLegendManager = new NodeLegendManager(ontoUmlParser, xmlFile);
        nodeManager = new NodeManager(xmlFile, nodeLegendManager);
        edgeManager = new EdgeManager(xmlFile, nodeManager);
        
        this.mainWindow = mainWindow;
        
        createSelectedWorld("world_structure/CurrentWorld$0");
        createWorldMap();
        createSelectedRm("world_structure/CurrentWorld$0");
    }
    
    /**
	   * Creates the world map graph based on the XMLFile file.
	   */
	public org.graphstream.graph.Graph createWorldMap() {
        ArrayList<Atom> atomList = xmlFile.getAtomList();
        ArrayList<Field> fieldList = xmlFile.getFieldList();
        
        int i, j;
        worldGraph = new MultiGraph("World Map");
        ArrayList<ArrayList<String>> tuplesList;
        ArrayList<String> tuple;
        int current=1, past=-2, future=2;
        
        // Getting only world atoms.
        for(i=0; i<atomList.size(); i++) {
        	if(atomList.get(i).isWorld()) {
        		System.out.println(atomList.get(i).getLabel().substring(0, 15));
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
                            System.out.println(tuple.get(0));
                            System.out.println(tuple.get(1));
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
	
	/**
	   * Creates the selected world graph.
	   * @param world the world id
	   */
    private void createSelectedWorld(String world) {
    	selectedWorld = world;
    	
    	for(int i=0; i<nodeManager.getNodeAmount(); i++) {
    		NodeM n = nodeManager.getNode(i);
    		if(n.existsInWorld(world)) {
    			Node node = selectedGraph.addNode(n.getId());
    			
    			String mainType = n.getMainType(world);//xmlFile.getAtomMainType(idList.get(i), selectedWorld).getName();
    			String attrib = nodeLegendManager.getTypeStyle(mainType);
    			node.addAttribute("ui.style", attrib);
    			
    			Iterator<Attribute> attrs = n.getAttributeIterator(world);
    			while(attrs.hasNext()) {
    				Attribute attr = attrs.next();
    				node.addAttribute(attr.getName(), attr.getValue());
    			}
    		}
    	}
    	
    	for(int i=0; i<edgeManager.getEdgeAmount(); i++) {
    		EdgeM e = edgeManager.getEdge(i);
    		if(e.existsInWorld(world)) {
    			Edge edge = selectedGraph.addEdge(e.getId(), e.getNode0Id(), e.getNode1Id(), true);
    			Iterator<Attribute> attrs = e.getAttributeIterator(world);
    			while(attrs.hasNext()) {
    				Attribute attr = attrs.next();
    				edge.addAttribute(attr.getName(), attr.getValue());
    			}
    		}
    	}
    	
    	selectedGraph.addAttribute("ui.antialias");
        selectedGraph.addAttribute("ui.quality");
        selectedGraph.addAttribute("layout.quality", 4);
        
        selectedGraph.addAttribute("ui.stylesheet", "graph {\n" +
"    padding: 100px, 100px, 0px;\n" +
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
    
    public void changeSelectedWorldM(String world) {
    	//selectedViewer.disableAutoLayout();
    	
    	ArrayList<EdgeM> toRemove = edgeManager.edgesToKill(selectedWorld, world);
    	for(EdgeM e : toRemove) {
        	selectedGraph.removeEdge(e.getId());
        }
    	//toRemo
    	
    	ArrayList<NodeM> toRemove2 = nodeManager.nodesToKill(selectedWorld, world);
        for(NodeM n : toRemove2) {
        	selectedGraph.removeNode(n.getId());
        }

    	for(int i=0; i<nodeManager.getNodeAmount(); i++) {
    		NodeM n = nodeManager.getNode(i);
    		if(n.existsInWorld(world)) {
    			Node node = selectedGraph.getNode(n.getId());
    			if(node == null)
    				node = selectedGraph.addNode(n.getId());
    			
    			String mainType = n.getMainType(world);//xmlFile.getAtomMainType(idList.get(i), selectedWorld).getName();
    			String attrib = nodeLegendManager.getTypeStyle(mainType);
    			node.addAttribute("ui.style", attrib);
    			
    			Iterator<Attribute> attrs = n.getAttributeIterator(world);
    			while(attrs.hasNext()) {
    				Attribute attr = attrs.next();
    				node.addAttribute(attr.getName(), attr.getValue());
    			}
    		}
    	}
    	
    	for(int i=0; i<edgeManager.getEdgeAmount(); i++) {
    		EdgeM e = edgeManager.getEdge(i);
    		if(e.existsInWorld(world)) {
    			
    			Edge edge = selectedGraph.getEdge(e.getId());
    			if(edge == null)
    				edge = selectedGraph.addEdge(e.getId(), e.getNode0Id(), e.getNode1Id(), true);
    			Iterator<Attribute> attrs = e.getAttributeIterator(world);
    			while(attrs.hasNext()) {
    				Attribute attr = attrs.next();
    				edge.addAttribute(attr.getName(), attr.getValue());
    			}
    		}
    	}
        
    	//selectedViewer.enableAutoLayout(layout);
    	selectedWorld = world;
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
        	
        	protected void mouseButtonPressOnElement(GraphicElement element, MouseEvent event) {
        		view.freezeElement(element, true);
        		if (event.getButton() != 3) {
        			element.addAttribute("ui.selected");
                    String id = element.getId();
                    System.out.println("MUNDOW:" + id);
                    Iterator iter = mainWindow.getxGraph().getWorldGraph().getNodeIterator();
                    while(iter.hasNext()) {
                    	org.graphstream.graph.Node nodeAux = (org.graphstream.graph.Node) iter.next();
                    	nodeAux.addAttribute("ui.style", "fill-color: white;");
                    }
                    getWorldGraph().getNode(id).addAttribute("ui.style", "fill-color: green;");
                    changeSelectedWorldM(id);
        		} else {
        			element.addAttribute("ui.clicked");
        		}
        		
        	}
            
        };
        worldView.setMouseManager(worldManager);
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
        //selectedView.getCamera().setViewPercent(1);
        
        DefaultMouseManager selectedManager = new DefaultMouseManager() {
        	/*
        	public void mouseDragged(MouseEvent event) {
        		if (curElement != null) {
        			elementMoving(curElement, event);
        		} else {
        			Point3 p = view.getCamera().transformPxToGu(event.getX(), event.getY());
        			view.getCamera().setViewCenter(-p.x+view.getCamera().getViewCenter().x, -p.y+view.getCamera().getViewCenter().y, 0);
        		}
        	}
        	*/
        	protected void mouseButtonPressOnElement(GraphicElement element, MouseEvent event) {
        		view.freezeElement(element, true);
        		if (event.getButton() != 3) {
        			element.addAttribute("ui.selected");
        		} else {
        			element.addAttribute("ui.clicked");
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
        			Node nodePressed = mainWindow.getxGraph().getSelectedGraph().getNode(id);
	        		ArrayList<String> typeList = mainWindow.getxGraph().getXmlFile().getAtomTypeOnWorld(id, selectedWorld);
	        		OntoUMLParser ontoUmlParser = xmlFile.getOntoUmlParser();
	        		XMLFile xmlFile = mainWindow.getxGraph().getXmlFile();
	        		//System.out.println("CLICKED ON " + id);
	        		String mainTypeName = xmlFile.getAtomMainType(id, selectedWorld).getName();
	        		int indexOfImage = nodeLegendManager.getTypeIndex(mainTypeName);//.getTypeList().indexOf(xmlFile.getAtomMainType(id, selectedWorld).getName());
	        		String imagePath = nodeLegendManager.getTypeImage(indexOfImage);
	        		if(mainWindow.isPopOut()) {
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
	        			mainWindow.getTabbedPane().addTab((String) mainWindow.getxGraph().getSelectedGraph().getNode(id).getAttribute("ui.label"), null, new PropertiesPanel(imagePath, nodePressed, xmlFile, ontoUmlParser, selectedWorld, nodeManager, edgeManager), null);
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
    
    public void createSelectedRm(String world) {
    	selectedRm = world;
    	for(int i=0; i<edgeManager.getEdgeAmount(); i++) {
    		EdgeM e = edgeManager.getEdge(i);
    		if(e.existsInWorld(world) && e.getStereoTypeOnWorld(selectedRm).matches("Material|Mediation")) {
    			
    			NodeM n = nodeManager.getNode(e.getNode0Id());
    			Node node = rmGraph.getNode(n.getId());
        		if(node == null)
        			node = rmGraph.addNode(n.getId());
        		if(n.getMainStereoType(world) != "Relator") {
        			createQua(n);
        		}
        		
        		String mainType = n.getMainType(world);//xmlFile.getAtomMainType(idList.get(i), selectedWorld).getName();
        		String attrib = nodeLegendManager.getTypeStyle(mainType);
        		node.addAttribute("ui.style", attrib);
        		
        		Iterator<Attribute> attrs = n.getAttributeIterator(world);
        		
        		while(attrs.hasNext()) {
        			Attribute attr = attrs.next();
        			node.addAttribute(attr.getName(), attr.getValue());
        		}
        		
        		n = nodeManager.getNode(e.getNode1Id());
        		node = rmGraph.getNode(n.getId());
        		if(node == null)
        			node = rmGraph.addNode(n.getId());
        		if(n.getMainStereoType(world) != "Relator") {
        			createQua(n);
        		}
        		
        		mainType = n.getMainType(world);//xmlFile.getAtomMainType(idList.get(i), selectedWorld).getName();
        		attrib = nodeLegendManager.getTypeStyle(mainType);
        		node.addAttribute("ui.style", attrib);
        		
        		attrs = n.getAttributeIterator(world);
        		while(attrs.hasNext()) {
        			Attribute attr = attrs.next();
        			node.addAttribute(attr.getName(), attr.getValue());
        		}
    			
    			Edge edge = rmGraph.addEdge(e.getId(), e.getNode0Id(), e.getNode1Id(), true);
    			attrs = e.getAttributeIterator(world);
    			while(attrs.hasNext()) {
    				Attribute attr = attrs.next();
    				edge.addAttribute(attr.getName(), attr.getValue());
    			}
    		}
    	}
    	
    	rmGraph.addAttribute("ui.antialias");
        rmGraph.addAttribute("ui.quality");
        rmGraph.addAttribute("layout.quality", 4);
        
        rmGraph.addAttribute("ui.stylesheet", "graph {\n" +
"    padding: 100px, 100px, 0px;\n" +
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
    
    public void createQua(NodeM n) {//, String relatorId) {
    	Iterator<DataType> dts = n.getDataTypeIterator(selectedWorld);
    	int i=0;
		//while(dts.hasNext()) {
			DataType dt = dts.next();
			Node qua = rmGraph.getNode(n.getId()+"$"+"qua$"+i);
			if(qua==null) {
				qua = rmGraph.addNode(n.getId()+"$"+"qua$"+i);
				qua.addAttribute("ui.label", dt.getValue());
				Edge me = rmGraph.addEdge(n.getId()+"$"+"mainEdgeQua", n.getId(), qua.getId());
				me.addAttribute("ui.style", "stroke-width: 0.2px; stroke-mode: dashes; text-alignment: along; text-background-mode: plain; text-background-color: rgba(255,255,255,128); text-size: 11;");
				me.addAttribute("ui.label", dt.getName());
				Iterator<NodeM> relators = n.getNeighborsOfStereoType("Relator", selectedRm, nodeManager, edgeManager).iterator();
				while(relators.hasNext()) {
					NodeM r = relators.next();
					i++;
					Edge er = rmGraph.addEdge(n.getId()+"$"+"edgeQua$"+i, qua.getId(), r.getId(), true);
					er.addAttribute("ui.style", "arrow-shape: diamond;");// fill-color: white; stroke-color: black; stroke-mode: plain; stroke-width: 2;");
				}
				//rmGraph.addEdge(n.getId()+"$"+"edgeQua$"+i++, qua.getId(), relatorId);//.addAttribute("ui.style", "stroke-width: 0.2px; stroke-mode: dashes;");
			}
			i++;
		//}
    }
    
    public void changeSelectedRmLevel(int level) {
    	switch(level) {
	    	case 2:
	    		updateEdgesStyleS(rmGraph, "Mediation", "visibility-mode: hidden;");
	    		updateEdgesStyleS(rmGraph, "Material", "visibility-mode: normal;");
	    		break;
	    	case 1:
	    		updateEdgesStyleS(rmGraph, "Material", "visibility-mode: hidden;");
	    		updateEdgesStyleS(rmGraph, "Mediation", "visibility-mode: normal;");
	    		break;
	    	case 0:
	    		//updateEdgesStyleS(rmGraph, "Material", "visibility-mode: hidden;");
	    		updateEdgesStyleSS(rmGraph, "Mediation", "visibility-mode: hidden;");

	    		break;
	    	default:
	    		System.out.println("NO!");
	    		return;
    	}
    }
    
    
    
    public View showRmGraph() {
    	
        rmViewer = new Viewer(rmGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        rmLayout = new SpringBox();
        rmLayout.setForce(1);
        rmLayout.setStabilizationLimit(0.09);
        rmViewer.enableAutoLayout(rmLayout);
        rmView = rmViewer.addDefaultView(false);   // false indicates "no JFrame".
        rmView.getCamera().setViewPercent(1);
        
        DefaultMouseManager selectedManager = new DefaultMouseManager() {
        	protected void mouseButtonPressOnElement(GraphicElement element, MouseEvent event) {
        		view.freezeElement(element, true);
        		if (event.getButton() != 3) {
        			element.addAttribute("ui.selected");
        		} else {
        			element.addAttribute("ui.clicked");
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
        			Node nodePressed = mainWindow.getxGraph().getSelectedGraph().getNode(id);
	        		ArrayList<String> typeList = mainWindow.getxGraph().getXmlFile().getAtomTypeOnWorld(id, selectedRm);
	        		OntoUMLParser ontoUmlParser = xmlFile.getOntoUmlParser();
	        		XMLFile xmlFile = mainWindow.getxGraph().getXmlFile();
	        		//System.out.println("CLICKED ON " + id);
	        		String mainTypeName = xmlFile.getAtomMainType(id, selectedRm).getName();
	        		int indexOfImage = nodeLegendManager.getTypeIndex(mainTypeName);//.getTypeList().indexOf(xmlFile.getAtomMainType(id, selectedWorld).getName());
	        		String imagePath = nodeLegendManager.getTypeImage(indexOfImage);
	        		if(mainWindow.isPopOut()) {
	        			JDialog popOutWindow = new JDialog();
	        			//popOutWindow.setTitle((String)mainWindow.getxGraph().getSelectedGraph().getNode(id).getAttribute("ui.label"));
	        			popOutWindow.setContentPane(new PropertiesPanel(imagePath, nodePressed, xmlFile, ontoUmlParser, selectedRm, nodeManager, edgeManager));
	        			popOutWindow.setBounds(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, 195, 238);
	        			popOutWindow.setVisible(true);
	        			popOutWindow.toFront();
	        		}else{
	        			if(!(mainWindow.getTabbedPane().getTabCount() == 1)) {
	        				mainWindow.getTabbedPane().removeTabAt(1);
	        			}
	        			mainWindow.getTabbedPane().addTab((String) mainWindow.getxGraph().getSelectedGraph().getNode(id).getAttribute("ui.label"), null, new PropertiesPanel(imagePath, nodePressed, xmlFile, ontoUmlParser, selectedWorld, nodeManager, edgeManager), null);
		        		mainWindow.getTabbedPane().setSelectedIndex(mainWindow.getTabbedPane().getTabCount()-1);
	        		}
	        		
        		} else {
        			
        		}
        	}
        	
        };
        rmView.setMouseManager(selectedManager);
    
        return rmView;
    }

    public void updateNodesStyle(Graph selectedGraph, String typeName, String attrValue) {
		Iterator<NodeM> it = nodeManager.getNodeIterator();
		while(it.hasNext()) {
			NodeM node = it.next();
			if(node.existsInWorld(selectedWorld)) {
				System.out.println("GONNACHANGE: " + node.getId() + node.getAttribute(selectedWorld, "ui.label").getValue());
				String mainType = node.getMainType(selectedWorld);
				if(typeName.equals(mainType)) {
					Node graphNode = selectedGraph.getNode(node.getId());
					if(graphNode != null) {
						graphNode.addAttribute("ui.style", attrValue);
					}
				}
			}
		}
	}
    
    public void updateNodesStyleS(Graph selectedGraph, String stereoTypeName, String attrValue) {
		Iterator<NodeM> it = nodeManager.getNodeIterator();
		while(it.hasNext()) {
			NodeM node = it.next();
			if(node.existsInWorld(selectedWorld)) {
				System.out.println("GONNACHANGE: " + node.getId() + node.getAttribute(selectedWorld, "ui.label").getValue());
				String mainType = node.getMainStereoType(selectedWorld);
				if(stereoTypeName.equals(mainType)) {
					Node graphNode = selectedGraph.getNode(node.getId());
					if(graphNode != null) {
						graphNode.addAttribute("ui.style", attrValue);
					}
				}
			}
		}
	}

    public void updateNodeEdgesStyle(Graph selectedGraph, String typeName, String attrValue) {
    	Iterator<EdgeM> it = edgeManager.getEdgeIterator();
    	
		while(it.hasNext()) {
			EdgeM edge = it.next();
			String node0Type = nodeManager.getNode(edge.getNode0Id()).getMainType(selectedWorld);
			String node1Type = nodeManager.getNode(edge.getNode1Id()).getMainType(selectedWorld);
			
			if(!(node0Type == null || node1Type == null)) {
				if(edge.existsInWorld(selectedWorld) && (typeName.equals(node0Type) || typeName.equals(node1Type))) {
					Edge edgeNode = selectedGraph.getEdge(edge.getId());
					if(edgeNode != null) {
						edgeNode.addAttribute("ui.style", attrValue);
					}
				}
			}
		}
	}
    
    public void updateNodeEdgesStyleS(Graph selectedGraph, String stereoTypeName, String attrValue) {
    	Iterator<EdgeM> it = edgeManager.getEdgeIterator();
    	
		while(it.hasNext()) {
			EdgeM edge = it.next();
			String node0Type = nodeManager.getNode(edge.getNode0Id()).getMainStereoType(selectedWorld);
			String node1Type = nodeManager.getNode(edge.getNode1Id()).getMainStereoType(selectedWorld);
			
			if(!(node0Type == null || node1Type == null)) {
				if(edge.existsInWorld(selectedWorld) && (stereoTypeName.equals(node0Type) || stereoTypeName.equals(node1Type))) {
					Edge edgeNode = selectedGraph.getEdge(edge.getId());
					if(edgeNode != null) {
						edgeNode.addAttribute("ui.style", attrValue);
					}
				}
			}
		}
	}
    
    public void updateEdgesStyle(Graph selectedGraph, String typeName, String attrValue) {
    	Iterator<EdgeM> it = edgeManager.getEdgeIterator();
    	
		while(it.hasNext()) {
			EdgeM edge = it.next();
			
			if(edge.existsInWorld(selectedWorld) && typeName.equals(edge.getTypeOnWorld(selectedWorld))) {
				selectedGraph.getEdge(edge.getId()).addAttribute("ui.style", attrValue);
				
				Edge edgeGraph = selectedGraph.getEdge(edge.getId());
				if(edgeGraph != null) {
					edgeGraph.addAttribute("ui.style", attrValue);
				}
				
			}
		}
	}
    
    public void updateEdgesStyleS(Graph selectedGraph, String stereoTypeName, String attrValue) {
    	Iterator<EdgeM> it = edgeManager.getEdgeIterator();
    	
		while(it.hasNext()) {
			EdgeM edge = it.next();
			
			System.out.println("LALALALALA: " + edge.getStereoTypeOnWorld(selectedWorld));
			if(edge.existsInWorld(selectedWorld) && stereoTypeName.equals(edge.getStereoTypeOnWorld(selectedWorld))) {
				selectedGraph.getNode(edge.getNode0Id()).addAttribute("ui.style", attrValue);
				selectedGraph.getNode(edge.getNode1Id()).addAttribute("ui.style", attrValue);
				selectedGraph.getEdge(edge.getId()).addAttribute("ui.style", attrValue);
				
				Edge edgeGraph = selectedGraph.getEdge(edge.getId());
				if(edgeGraph != null) {
					edgeGraph.addAttribute("ui.style", attrValue);
				}
				
			}
		}
	}
    
    public void updateEdgesStyleSS(Graph selectedGraph, String stereoTypeName, String attrValue) {
    	Iterator<EdgeM> it = edgeManager.getEdgeIterator();
    	
		while(it.hasNext()) {
			EdgeM edge = it.next();
			
			System.out.println("LALALALALA: " + edge.getStereoTypeOnWorld(selectedWorld));
			if(edge.existsInWorld(selectedWorld) && stereoTypeName.equals(edge.getStereoTypeOnWorld(selectedWorld))) {
				selectedGraph.getEdge(edge.getId()).addAttribute("ui.style", attrValue);
				
				Edge edgeGraph = selectedGraph.getEdge(edge.getId());
				if(edgeGraph != null) {
					edgeGraph.addAttribute("ui.style", attrValue);
				}
				
			}
		}
	}
    
    /*
    public void updateNodesPrefixes(String typeName) {
    	Iterator<NodeM> it = nodeManager.getNodeIterator();
		while(it.hasNext()) {
			NodeM node = it.next();
			String nodeId = node.getMainType(selectedWorld);
			String mainType = node.getMainType(selectedWorld);
			if(typeName.equals(mainType)) {
				selectedGraph.getNode(nodeId).addAttribute("ui.style", attrValue);
			}
		}
		
    	ArrayList<String> idList = getNodeManager().getIdList();
		for(int i=0; i<idList.size(); i++) {
			String nodeId = idList.get(i);
			Classifier mainType = xmlFile.getAtomMainType(nodeId, selectedWorld);
			if(mainType != null) {
		        String mainTypeName = mainType.getName();
				if(typeName.equals(mainTypeName)) {
					String oldLabel = (String) selectedGraph.getNode(nodeId).getAttribute("ui.label");
				}
			}
			
		}
    }
    */
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

	public Graph getRmGraph() {
		return rmGraph;
	}
	
	public View getRmView() {
		return rmView;
	}
	
	
	
}
