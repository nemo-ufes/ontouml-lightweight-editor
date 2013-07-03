package obj;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.graphicGraph.GraphicElement;
import org.graphstream.ui.layout.HierarchicalLayout;
import org.graphstream.ui.layout.springbox.implementations.LinLog;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.algorithm.layout.openord.*;

import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.ViewerPipe;
import org.graphstream.ui.swingViewer.util.DefaultMouseManager; 
import org.xml.sax.SAXException;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.TypeName;

/**
 *
 * @author Mauricio
 */
public class XGraph {
    private XMLFile xmlFile;
    private Graph worldGraph;
    private Graph selectedGraph;
    private Viewer selectedViewer;
    private View selectedView;
    private Viewer worldViewer;
    private View worldView;
    private String selectedWorld;
    private OntoUMLParser ontoUmlParser;
    private int edgeId;
    protected ArrayList<String> allTypes;
    protected ArrayList<String> typeImages;
    protected ArrayList<Integer> typeAmount;
  
    public XGraph(XMLFile xmlGraph, OntoUMLParser onto, int mode) throws ParserConfigurationException, SAXException, IOException {
        xmlFile = xmlGraph;
        worldGraph = null;
        selectedGraph = null;
        selectedViewer = null;
        selectedView = null;
        worldViewer = null;
        worldView = null;
        selectedWorld = "";
        ontoUmlParser = onto;
        edgeId = 0;
        allTypes = new ArrayList();
        typeImages = new ArrayList();
        typeAmount = new ArrayList();
    }
    
    /**
	   * Creates the world map graph based on the input file.
	   */
	public org.graphstream.graph.Graph createWorldMap() {
        ArrayList<Skolem> skolemList = xmlFile.getSkolemList();
        ArrayList<Atom> atomList = xmlFile.getAtomList();
        ArrayList<Sig> sigList = xmlFile.getSigList();
        ArrayList<Field> fieldList = xmlFile.getFieldList();
        
        int i, j;
        worldGraph = new MultiGraph("World Map");
        ArrayList<ArrayList<String>> tuplesList;
        ArrayList<String> tuple;
        int current=1, past=-2, pasty=0, future=2, temporal=0;
        
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
	   * Creates the 
	   * @param world the atom that represents the world
	   */
    public org.graphstream.graph.Graph createSelectedWorld(String worldLabel) {
        ArrayList<Atom> atomList = xmlFile.getAtomList();
        ArrayList<Sig> sigList = xmlFile.getSigList();
        ArrayList<Field> fieldList = xmlFile.getFieldList();
        
        int i, j, k;
        
        selectedWorld = worldLabel;
        Graph graph = new MultiGraph(worldLabel);
        ArrayList<ArrayList<String>> tuplesList;
        ArrayList<String> tuple;
        ArrayList<ArrayList<Integer>> typesList;
        
        // Creating nodes - we need the "exists" tuples:
        for(i=0; i<fieldList.size(); i++) {
        	if(fieldList.get(i).getLabel().equals("exists")) {
        		Field exists = fieldList.get(i);
        		// After we find the "exists" field, we don't need to search anymore
		        for(i=0; i<exists.getTuples().size(); i++) {
		            if(exists.getTuple(i).get(0).equals(worldLabel)) {	// First element of tuple has to be the selected world
		            	String atomLabel = exists.getTuple(i).get(1);
		                Node nodeAux = graph.addNode(atomLabel);	// Node created
		                String mainType = xmlFile.getAtomMainType(atomLabel, worldLabel).getName();
		                if(!allTypes.contains(mainType)) {
		                	allTypes.add(mainType);
		                	typeAmount.add(0);
		                	File image = new File("./resources/gur project");
		                	String imagePath = image.getPath() + "\\" + image.list()[allTypes.indexOf(mainType)];
		                	typeImages.add(imagePath);
		                	System.out.println(imagePath);
		                	nodeAux.addAttribute("ui.style", "text-offset: 0px, 10px; shape: circle; text-background-mode: plain; text-background-color: rgba(255,255,255,192); stroke-mode: none; size: 32px; fill-mode: image-scaled; fill-image: url('" + imagePath + "');");
		                }else{
		                	nodeAux.addAttribute("ui.style", "text-offset: 0px, 10px; shape: circle; text-background-mode: plain; text-background-color: rgba(255,255,255,192); stroke-mode: none; size: 32px; fill-mode: image-scaled; fill-image: url('" + typeImages.get(allTypes.indexOf(mainType)) + "');");
		                }
		                //ontoUmlParser.getElement("");
		                nodeAux.addAttribute("ui.label", createLabel(mainType));
		                
		                ArrayList<Classifier> secList = xmlFile.getAtomSecondayTypes(atomLabel, worldLabel); 
		                for(j=0; j<secList.size(); j++) {
		                	String satLabel = atomLabel + "_sat$" + j;
		                	String orbitLabel = atomLabel + "_orbit$" + j;
	                    	org.graphstream.graph.Node sat = graph.addNode(atomLabel + "_sat$" + j);
	                    	org.graphstream.graph.Edge orbit = graph.addEdge(atomLabel + "_orbit$" + j, atomLabel, satLabel, false);
	                    	//sat.addAttribute("ui.label", secList.get(j).getName());
	                    	
	                    	if(!allTypes.contains(secList.get(j).getName())) {
			                	allTypes.add(secList.get(j).getName());
			                	typeAmount.add(0);
			                	File image = new File("./resources/gur project");
			                	String imagePath = image.getPath() + "\\" + image.list()[allTypes.indexOf(secList.get(j).getName())];
			                	typeImages.add(imagePath);
			                	System.out.println(imagePath);
			                	sat.addAttribute("ui.style", "text-offset: 0px, 10px; shape: circle; text-background-mode: plain; text-background-color: rgba(255,255,255,192); stroke-mode: none; size: 16px; fill-mode: image-scaled; fill-image: url('" + imagePath + "');");
			                }else{
			                	sat.addAttribute("ui.style", "text-offset: 0px, 10px; shape: circle; text-background-mode: plain; text-background-color: rgba(255,255,255,192); stroke-mode: none; size: 16px; fill-mode: image-scaled; fill-image: url('" + typeImages.get(allTypes.indexOf(secList.get(j).getName())) + "');");
			                }
	                    	
	                    	//sat.addAttribute("ui.style", "text-background-mode: plain; text-background-color: rgba(255,255,255,192); size: 12px; stroke-mode: none; fill-mode: image-scaled; fill-image: url('./resources/Heart-icon.png');");
	                    	//sat.addAttribute("z", 1);
	                    	orbit.addAttribute("layout.weight", 2);
	                    	orbit.addAttribute("ui.style", "fill-color: rgba(0,0,0,0);");
	                    }
		                
		                
		            }
		        }
		        
		        break;
	        }
    	}
        
        for(i=0; i<fieldList.size(); i++) {
        	typesList = fieldList.get(i).getTypes();
            if(!fieldList.get(i).getTuples().isEmpty()) {
                System.out.println(fieldList.get(i).getTuples().get(0).get(0));
                if(fieldList.get(i).getTuples().get(0).size() > 2) {	// relation field has 3 atoms; 
                    tuplesList = fieldList.get(i).getTuples();
                    for(j=0; j<tuplesList.size(); j++) {
                        tuple = tuplesList.get(j);
                        if(worldLabel.equals(tuple.get(0))) {
                        	if(!xmlFile.findSigById(typesList.get(0).get(2)).isBuiltin() && !xmlFile.findSigById(typesList.get(0).get(2)).getLabel().equals("this/String_")) {
                                //try {
                                	//String edgeType = TypeName.getTypeName(ontoUmlParser.getElement(fieldList.get(i).getLabel()));
                                String edgeName = fieldList.get(i).getLabel() + "$" + edgeId;
                                System.out.println("edgeName: "+ edgeName);
                                edgeId++;
                                
                                Node sat1 = graph.getNode(tuple.get(1)), sat2 = graph.getNode(tuple.get(2));
                                Iterator neighbors1 = sat1.getNeighborNodeIterator();
                                while(neighbors1.hasNext()) {
                                	sat1 = (Node) neighbors1.next();
                                	if(sat1.getId().contains(tuple.get(1) + "_sat$")) {
                                		break;
                                	}else{
                                		sat1 = graph.getNode(tuple.get(1));
                                	}
                                }
                                
                                System.out.println("sat1: "+sat1.getId());
                                System.out.println(xmlFile.findAtom(tuple.get(2)).getSig().getId());
                                Iterator neighbors2 = sat2.getNeighborNodeIterator();
                                while(neighbors2.hasNext()) {
                                	sat2 = (Node) neighbors2.next();
                                	if(sat2.getId().contains(tuple.get(2) + "_sat$")) {
                                		break;
                                	}else{
                                		sat2 = graph.getNode(tuple.get(2));
                                	}
                                }
                                
                                graph.addEdge(edgeName, sat1, sat2, true).addAttribute("layout.weight", 24);
                                
                                graph.getNode(tuple.get(1)).addAttribute(edgeName, tuple.get(2));
                                graph.getNode(tuple.get(2)).addAttribute(tuple.get(1), edgeName);
                                
                        	}else{
                        		String attrName = "attr$";
                        		int attrId = 0;
                        		while(graph.getNode(tuple.get(1)).hasAttribute(attrName + attrId)) {
                        			attrId++;
                        		}
                        		System.out.println("ADDEDATTRTO" + graph.getNode(tuple.get(1)).getAttribute("ui.label"));
                        		graph.getNode(tuple.get(1)).addAttribute(attrName + attrId, fieldList.get(i).getLabel() + "\n" + tuple.get(2));
                        	}
                        }
                    }
                }
            }else{
            	if(fieldList.get(i).getTypes().get(0).size() > 2) {
            		
            	}
            }
        }
        
        for(i=0; i<typeImages.size(); i++)
        	System.out.println(typeImages.get(i));
        
        graph.addAttribute("ui.antialias");
        graph.addAttribute("ui.quality");
        graph.addAttribute("layout.quality", 4);
        //graph.addAttribute("layout.force", 2);
        graph.addAttribute("ui.stylesheet", "graph {\n" +
"    padding: 20px, 40px, 0px;\n" +
"}\n" +
"node {\n" +
//"    size: 300px, 50px;\n" +
//"    shape: box;\n" +
//"    fill-color: rgba(255,255,255,255);\n" +
"    text-size: 12;\n" +
"	 text-alignment: under;\n" +
//"    text-mode: truncated;\n" +
"    stroke-mode: plain;\n" +
"    stroke-color: black;\n" +
"}\n" +
"edge {\n" +
"    fill-color: black;\n" +
"    arrow-size: 4px, 4px;\n" +
"}\n" +
                /*
"node#Object$0 {\n" +
"    fill-color: blue;\n" +
"}\n" +
*/
"node:clicked {\n" +
"    size-mode: fit;\n" +
"    fill-color: red;\n" +
"}");
        
        this.selectedGraph = graph;
        return graph;
    }
    
    public org.graphstream.graph.Graph changeSelectedWorld(String worldLabel) {
    	ArrayList<Atom> atomList = xmlFile.getAtomList();
        ArrayList<Sig> sigList = xmlFile.getSigList();
        ArrayList<Field> fieldList = xmlFile.getFieldList();
        
        int i, j, k;
        ArrayList<ArrayList<String>> tuplesList;
        ArrayList<String> tuple;
        ArrayList<ArrayList<Integer>> typesList;
        
        // Creating/Changing nodes - we need the "exists" tuples:
        for(i=0; i<fieldList.size(); i++) {
        	if(fieldList.get(i).getLabel().equals("exists")) {
        		Field exists = fieldList.get(i);
        		// After we find the "exists" field, we don't need to search anymore
		        for(i=0; i<exists.getTuples().size(); i++) {
		            if(exists.getTuple(i).get(0).equals(worldLabel)) {	// First element of tuple has to be the selected world
		            	String atomLabel = exists.getTuple(i).get(1);
		                Node nodeAux = selectedGraph.getNode(atomLabel);	// Node created
		                if(nodeAux == null) {
		                	nodeAux = selectedGraph.addNode(atomLabel);
		                }else{
		                	nodeAux.addAttribute("dontkill", "1");
		                	removeSats(nodeAux);
		                }
		                String mainType = xmlFile.getAtomMainType(atomLabel, worldLabel).getName();
		                if(!allTypes.contains(mainType)) {
		                	allTypes.add(mainType);
		                	typeAmount.add(0);
		                	File image = new File("./resources/gur project");
		                	String imagePath = image.getPath() + "\\" + image.list()[allTypes.indexOf(mainType)];
		                	typeImages.add(imagePath);
		                	System.out.println(imagePath);
		                	nodeAux.addAttribute("ui.style", "text-offset: 0px, 10px; shape: circle; text-background-mode: plain; text-background-color: rgba(255,255,255,192); stroke-mode: none; size: 32px; fill-mode: image-scaled; fill-image: url('" + imagePath + "');");
		                }else{
		                	nodeAux.addAttribute("ui.style", "text-offset: 0px, 10px; shape: circle; text-background-mode: plain; text-background-color: rgba(255,255,255,192); stroke-mode: none; size: 32px; fill-mode: image-scaled; fill-image: url('" + typeImages.get(allTypes.indexOf(mainType)) + "');");
		                }
		                //ontoUmlParser.getElement("");
		                nodeAux.addAttribute("ui.label", createLabel(mainType));
		                
		                ArrayList<Classifier> secList = xmlFile.getAtomSecondayTypes(atomLabel, worldLabel); 
		                for(j=0; j<secList.size(); j++) {
		                	String satLabel = atomLabel + "_sat$" + j;
		                	String orbitLabel = atomLabel + "_orbit$" + j;
	                    	org.graphstream.graph.Node sat = selectedGraph.addNode(atomLabel + "_sat$" + j);
	                    	org.graphstream.graph.Edge orbit = selectedGraph.addEdge(atomLabel + "_orbit$" + j, atomLabel, satLabel, false);
	                    	//sat.addAttribute("ui.label", secList.get(j).getName());
	                    	
	                    	if(!allTypes.contains(secList.get(j).getName())) {
			                	allTypes.add(secList.get(j).getName());
			                	typeAmount.add(0);
			                	File image = new File("./resources/gur project");
			                	String imagePath = image.getPath() + "\\" + image.list()[allTypes.indexOf(secList.get(j).getName())];
			                	typeImages.add(imagePath);
			                	System.out.println(imagePath);
			                	sat.addAttribute("ui.style", "text-offset: 0px, 10px; shape: circle; text-background-mode: plain; text-background-color: rgba(255,255,255,192); stroke-mode: none; size: 16px; fill-mode: image-scaled; fill-image: url('" + imagePath + "');");
			                }else{
			                	sat.addAttribute("ui.style", "text-offset: 0px, 10px; shape: circle; text-background-mode: plain; text-background-color: rgba(255,255,255,192); stroke-mode: none; size: 16px; fill-mode: image-scaled; fill-image: url('" + typeImages.get(allTypes.indexOf(secList.get(j).getName())) + "');");
			                }
	                    	
	                    	//sat.addAttribute("ui.style", "text-background-mode: plain; text-background-color: rgba(255,255,255,192); size: 12px; stroke-mode: none; fill-mode: image-scaled; fill-image: url('./resources/Heart-icon.png');");
	                    	//sat.addAttribute("z", 1);
	                    	orbit.addAttribute("layout.weight", 2);
	                    	orbit.addAttribute("ui.style", "fill-color: rgba(0,0,0,64);");
	                    }
		                
		                
		            }
		        }
		        
		        break;
	        }
    	}
        
        for(i=0; i<fieldList.size(); i++) {
        	if(fieldList.get(i).getLabel().equals("exists")) {
        		Field exists = fieldList.get(i);
		        for(i=0; i<exists.getTuples().size(); i++) {
		            if(exists.getTuple(i).get(0).equals(selectedWorld)) {	// First element of tuple has to be the selected world
		            	String atomLabel = exists.getTuple(i).get(1);
		                Node nodeAux = selectedGraph.getNode(atomLabel);
		                if(nodeAux.getAttribute("dontkill") == null) {
		                	removeSats(nodeAux);
		                	selectedGraph.removeNode(nodeAux);
		                }
		            }
		        }
        	}
        }
        
        for(i=0; i<fieldList.size(); i++) {
        	typesList = fieldList.get(i).getTypes();
            if(!fieldList.get(i).getTuples().isEmpty()) {
                System.out.println(fieldList.get(i).getTuples().get(0).get(0));
                if(fieldList.get(i).getTuples().get(0).size() > 2) {	// relation field has 3 atoms; 
                    tuplesList = fieldList.get(i).getTuples();
                    
                    for(j=0; j<tuplesList.size(); j++) {
                        tuple = tuplesList.get(j);
                        if(worldLabel.equals(tuple.get(0))) {
                        	if(!xmlFile.findSigById(typesList.get(0).get(2)).isBuiltin() && !xmlFile.findSigById(typesList.get(0).get(2)).getLabel().equals("this/String_")) {
                                Node node1 = selectedGraph.getNode(tuple.get(1));
                                for(k=0; k<edgeId; k++) {
                                	String node2Id = (String) node1.getAttribute(fieldList.get(i).getLabel() + "$" + edgeId);
                                	if(!(node2Id == null)) {
                                		//if(node2Id
                                	}
                                }
                                
                        		
                                String edgeName = fieldList.get(i).getLabel() + "$" + edgeId;
                                System.out.println("edgeName: "+ edgeName);
                                edgeId++;
                                
                                Node sat1 = selectedGraph.getNode(tuple.get(1)), sat2 = selectedGraph.getNode(tuple.get(2));
                                Iterator neighbors1 = sat1.getNeighborNodeIterator();
                                while(neighbors1.hasNext()) {
                                	sat1 = (Node) neighbors1.next();
                                	if(sat1.getId().contains(tuple.get(1) + "_sat$")) {
                                		break;
                                	}else{
                                		sat1 = selectedGraph.getNode(tuple.get(1));
                                	}
                                }
                                
                                System.out.println("sat1: "+sat1.getId());
                                System.out.println(xmlFile.findAtom(tuple.get(2)).getSig().getId());
                                Iterator neighbors2 = sat2.getNeighborNodeIterator();
                                while(neighbors2.hasNext()) {
                                	sat2 = (Node) neighbors2.next();
                                	if(sat2.getId().contains(tuple.get(2) + "_sat$")) {
                                		break;
                                	}else{
                                		sat2 = selectedGraph.getNode(tuple.get(2));
                                	}
                                }
                                
                                selectedGraph.addEdge(edgeName, sat1, sat2, true).addAttribute("layout.weight", 12);
                                
                        	}else{
                        		String attrName = "attr$";
                        		int attrId = 0;
                        		while(selectedGraph.getNode(tuple.get(1)).hasAttribute(attrName + attrId)) {
                        			attrId++;
                        		}
                        		System.out.println("ADDEDATTRTO" + selectedGraph.getNode(tuple.get(1)).getAttribute("ui.label"));
                        		selectedGraph.getNode(tuple.get(1)).addAttribute(attrName + attrId, fieldList.get(i).getLabel() + "\n" + tuple.get(2));
                        	}
                        }
                    }
                }
            }else{
            	if(fieldList.get(i).getTypes().get(0).size() > 2) {
            		
            	}
            }
        }
        
        for(i=0; i<typeImages.size(); i++)
        	System.out.println(typeImages.get(i));
        
        selectedWorld = worldLabel;
        return selectedGraph;
    }
    
    public View showWorldGraph() {
        worldViewer = new Viewer(worldGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        worldViewer.disableAutoLayout();
        worldView = worldViewer.addDefaultView(false);   // false indicates "no JFrame".
        worldView.getCamera().setViewCenter(0, 0, 0);

        DefaultMouseManager worldManager = new DefaultMouseManager() {
        	protected void elementMoving(GraphicElement element, MouseEvent event) {
        	}
        };
        worldView.setMouseManager(worldManager);
        return worldView;
    }
    
    public View showSelectedGraph() {
    	
        selectedViewer = new Viewer(selectedGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        //viewer.enableAutoLayout();
        //Viewer v = new Viewer(g, Viewer.TheadingModel.GRAPH_IN_ANOTHER_THREAD);
        SpringBox layout = new SpringBox();
        //layout.setGravityFactor(0.5);
        layout.freezeNode("Object$1", true);
        //layout.moveNode("Object$1", 0, 0, 0);
        layout.setForce(1);
        //layout.configure(2, 2, false, 1);
        selectedViewer.enableAutoLayout(layout);
        selectedView = selectedViewer.addDefaultView(false);   // false indicates "no JFrame".
        
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
        		} else {
        			
        		}
        	}
        	
        };
        selectedView.setMouseManager(selectedManager);
        
        
		
        
        //You can find two layouts in gs-core, LinLog and SpringBox. There is also an adaptation of the OpenOrd layout here : https://github.com/gsavin/gs-openord
        return selectedView;
    }

    /**
     * Creates a reduced label from an existing one by removing the vowels and spaces. If a space is found, the following character will be converted to upper case.
     * @param label
     * @return A reduced label.
     */
    
    private String createLabel(String label) {
    	int id = typeAmount.get(allTypes.indexOf(label));
    	if(label.length() <= 1) {
    		typeAmount.set(allTypes.indexOf(label), id+1);
    		return label + id;
    	}
    	String newLabel = "" + label.charAt(0);
    	
    	for(int i=1; i<label.length(); i++) {
    		String verify = "" + label.charAt(i);
    		if(!verify.matches("a|e|i|o|u|á|é|í|ó|ú|â|ê|ô|A|E|I|O|U|Á|É|Í|Ó|Ú|Â|Ê| ")) {
    			if(verify.matches(" ")){
    				verify.toUpperCase();
    			}
    			newLabel = newLabel + verify;
    		}
    	}
    	newLabel = newLabel + id;
    	typeAmount.set(allTypes.indexOf(label), id+1);
    	return newLabel;
    }
    
    private void removeSats(Node node) {
    	System.out.println("\n TIME TO " + node.getId() + node.getAttribute("ui.label"));
    	Iterator neighbors = node.getNeighborNodeIterator();
    	System.out.println("gonna remove....");
    	ArrayList<Node> satList = new ArrayList();
    	while(neighbors.hasNext()) {
    		Node sat = (Node) neighbors.next();
    		satList.add(sat);
    	}
    	
    	for(int i=0; i<satList.size(); i++) {
    		if(satList.get(i).getId().contains(node.getId() + "_sat$")) {
    			System.out.println("removed " + satList.get(i).getId());
    			selectedGraph.removeNode(satList.get(i));
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

	public OntoUMLParser getOntoUmlParser() {
		return ontoUmlParser;
	}

	public void setOntoUmlParser(OntoUMLParser ontoUmlParser) {
		this.ontoUmlParser = ontoUmlParser;
	}

	public ArrayList<String> getAllTypes() {
		return allTypes;
	}

	public void setAllTypes(ArrayList<String> allTypes) {
		this.allTypes = allTypes;
	}

	public ArrayList<String> getTypeImages() {
		return typeImages;
	}

	public void setTypeImages(ArrayList<String> typeImages) {
		this.typeImages = typeImages;
	}

	public ArrayList<Integer> getTypeAmount() {
		return typeAmount;
	}

	public void setTypeAmount(ArrayList<Integer> typeAmount) {
		this.typeAmount = typeAmount;
	}
    
}
