/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.ViewerPipe;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
    private Viewer viewer;
    private View view;
    private Viewer viewer1;
    private View view1;
    OntoUMLParser ontoUmlParser;
    
    
    public XGraph(XMLFile xmlGraph, OntoUMLParser onto, int mode) throws ParserConfigurationException, SAXException, IOException {
        xmlFile = xmlGraph;
        worldGraph = null;
        selectedGraph = null;
        viewer = null;
        view = null;
        ontoUmlParser = onto;
    }

	public org.graphstream.graph.Graph setGraphToAllWorlds() {
        ArrayList<Skolem> skolemList = xmlFile.getSkolemList();
        ArrayList<Atom> atomList = xmlFile.getAtomList();
        ArrayList<Sig> sigList = xmlFile.getSigList();
        ArrayList<Field> fieldList = xmlFile.getFieldList();
        
        int i, j, k;
        org.graphstream.graph.Node node;
        Graph graph = new MultiGraph("I can see dead pixels");
        ArrayList<ArrayList<String>> tuplesList;
        ArrayList<String> tuple;
        
        int onlyone = 0;
        // Getting only world atoms. Their label have a length higher than 14. Substring prior to index 15 is "world_structure"
        for(i=0; i<atomList.size(); i++) {
            if(atomList.get(i).getLabel().length() > 14) {
                System.out.println(atomList.get(i).getLabel().substring(0, 15));
                //if(atomList.get(i).getLabel().substring(0, 15).equals("world_structure")) {
                if(atomList.get(i).isWorld()) {
                    graph.addNode(atomList.get(i).getLabel());
                    switch(atomList.get(i).getWorldType()) {
                        case "Past":
                            graph.getNode(atomList.get(i).getLabel()).addAttribute("ui.color", 0);
                            break;
                        case "Current":
                            graph.getNode(atomList.get(i).getLabel()).addAttribute("ui.color", 0.2);
                            break;
                        case "Future":
                            graph.getNode(atomList.get(i).getLabel()).addAttribute("ui.color", 0.4);
                            break;
                        case "Counterfactual":
                            graph.getNode(atomList.get(i).getLabel()).addAttribute("ui.color", 0.6);
                            break;
                        case "Temporal":
                            graph.getNode(atomList.get(i).getLabel()).addAttribute("ui.color", 0.8);
                            break;
                        default:
                            System.out.println("THIS IS NOT POSSIBLE! QUITING");
                            System.exit(1);
                    }
                    graph.getNode(atomList.get(i).getLabel()).addAttribute("ui.style", "text-alignment: at-left;");
                    
                    graph.getNode(atomList.get(i).getLabel()).addAttribute("x", (50 + i*100));
                    graph.getNode(atomList.get(i).getLabel()).addAttribute("y", (50 + i*100));
                    if(onlyone==0) {
                    	graph.getNode(atomList.get(i).getLabel()).addAttribute("ui.style", "text-alignment: at-right;");
                    	
                    	onlyone++;
                    }
                    graph.getNode(atomList.get(i).getLabel()).addAttribute("ui.label", atomList.get(i).getLabel().substring(16).replace("$", ": ").concat("  "));
                    //graph.getNode(atomList.get(i).getLabel()).addAttribute("ui.size", atomList.get(i).getLabel());
                }
            }
        }
        
        // Getting only "next" associations - the only thing we need in a worlds graph.
        for(i=0; i<fieldList.size(); i++) {
            if(0 == fieldList.get(i).getLabel().compareTo("next")) {
                tuplesList = fieldList.get(i).getTuples();
                for(j=0; j<tuplesList.size(); j++) {
                    tuple = tuplesList.get(j);
                    for(k=0; k<tuple.size() - 1; k++) {
                        try {
                            String edgeName = tuple.get(k) + "_" + tuple.get(k+1);
                            //edgeName = edgeName.concat(tuple.get(k+1).getLabel());
                            System.out.println(tuple.get(k));
                            System.out.println(tuple.get(k+1));
                            graph.addEdge(edgeName, tuple.get(k), tuple.get(k+1), true).addAttribute("ui.label", fieldList.get(i).getLabel());
                            //graph.getEdge(edgeName).addAttribute("ui.label", fieldList.get(i).getLabel());
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }
        
        graph.addAttribute("ui.antialias");
        
        graph.addAttribute("ui.stylesheet", "graph {\n" +
"padding: 25px;\n" +
"}\n" +
"node { size-mode: normal; text-size: 12; text-background-mode: plain; text-background-color: gray; text-alignment: at-left; size: 10px, 10px; fill-color: white; stroke-mode: plain; size-mode: fit; stroke-width: 3px; stroke-color: #333; } edge { shape: blob; size: 10px; }" +
    "edge { text-alignment: along; text-color: white; }");
/*
"node {\n" +
"    size: 250px, 100px;\n" +
"    shape: freeplane;\n" +
//"    fill-color: rgba(255,255,255,255);\n" +
"    text-size: 12;\n" +
"    stroke-mode: none;\n" +
//"    stroke-color: black;\n" +
"    size-mode: normal;\n" +
//"    fill-mode: image-scaled;\n" +
//"    fill-image: url('./resources/cloud.gif');\n" +
"fill-color: red, green, blue, yellow, white;\n" +
"}\n" +
"edge {\n" +
"    fill-color: #222;\n" +
"    arrow-size: 8px, 8px;\n" +
"}\n" +
"node:clicked {\n" +
//"    fill-mode: image-scaled;\n" +
//"    fill-image: url('./resources/cloud_red.gif');\n" +
"}");   
*/
        this.worldGraph = graph;
        return graph;
    }

    
	/**
	   * Testing javadoc!
	   * @param world the atom that represents the world
	   */
    public org.graphstream.graph.Graph setGraphToSelectedWorld(Atom world) {
        ArrayList<Skolem> skolemList = xmlFile.getSkolemList();
        ArrayList<Atom> atomList = xmlFile.getAtomList();
        ArrayList<Sig> sigList = xmlFile.getSigList();
        ArrayList<Field> fieldList = xmlFile.getFieldList();
        
        ArrayList<Atom> objectList = xmlFile.getObjectList();
        ArrayList<Atom> propertyList = xmlFile.getPropertyList();
        ArrayList<Atom> dataTypeList = xmlFile.getDataTypeList();
        
        int i, j, k;
        org.graphstream.graph.Node node;
        obj.Field exists = null;
        Graph graph = new MultiGraph(world.getLabel());
        ArrayList<ArrayList<String>> tuplesList;
        ArrayList<String> tuple;
        ArrayList<ArrayList<Integer>> typesList;
        ArrayList<Integer> types;
        
        for(i=0; i<fieldList.size(); i++) {
            if(fieldList.get(i).getLabel().equals("exists")) {
                exists = fieldList.remove(i);
                break;
            }
        }
        
        for(i=0; i<exists.getTuples().size(); i++) {
            if(world.hasLabel(exists.getTuples().get(i).get(0))) {
                ArrayList<String> stringList = xmlFile.getAtomTypeOnWorld(exists.getTuples().get(i).get(1), world.getLabel());
                String typeName = "<";
                for(j=0; j<stringList.size(); j++) {
                	System.out.println("TYPE: " + stringList.get(j));
                    typeName = typeName + stringList.get(j);
                    typeName = typeName + " (" + TypeName.getTypeName(ontoUmlParser.getElement(stringList.get(j))) + ")";
                    if(j + 1 != stringList.size()) {
                        typeName = typeName + ", ";
                    }
                }
                typeName = typeName + ">";
                
                //System.out.println("COMECOU " + exists.getTuples().get(i).get(1));
                graph.addNode(exists.getTuples().get(i).get(1)).addAttribute("ui.label", exists.getTuples().get(i).get(1) + typeName);
                graph.getNode(exists.getTuples().get(i).get(1)).addAttribute("layout.weight", 0);
                
                if(xmlFile.findAtom(exists.getTuples().get(i).get(1)).isObject()) {
                    graph.getNode(exists.getTuples().get(i).get(1)).addAttribute("ui.style", "shape: circle; size: 300px, 50px;");
                }
                if(xmlFile.findAtom(exists.getTuples().get(i).get(1)).isProperty()) {
                    graph.getNode(exists.getTuples().get(i).get(1)).addAttribute("ui.style", "shape: box; size: 90px;");
                }
                if(xmlFile.findAtom(exists.getTuples().get(i).get(1)).isDataType()) {
                    graph.getNode(exists.getTuples().get(i).get(1)).addAttribute("ui.style", "shape: diamond; size: 90px;");
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
                        if(world.hasLabel(tuple.get(0))) {
                            for(k=1; k<tuple.size() - 1; k++) {
                                try {
                                	String edgeName = tuple.get(k) + "_" + tuple.get(k+1) + "_" + tuple.get(k+1);
                                    if(xmlFile.findSigById(typesList.get(0).get(k+1)).isBuiltin()) {
                                    	graph.addNode(tuple.get(k+1)).addAttribute("ui.label", tuple.get(k+1));
                                    	graph.getNode(tuple.get(k+1)).addAttribute("ui.style", "shape: circle; size: 10px; fill-color: black; size-mode: normal; text-alignment: at-left;");
                                    	graph.getNode(tuple.get(k+1)).addAttribute("layout.weight", 0);
                                    	graph.getNode(tuple.get(k+1)).addAttribute("xyz", 1000, 1000, 10000);
                                    	graph.addEdge(edgeName, tuple.get(k), tuple.get(k+1), true).addAttribute("ui.label", fieldList.get(i).getLabel());
                                    }else{
                                    	graph.addEdge(edgeName, tuple.get(k), tuple.get(k+1), true).addAttribute("ui.label", fieldList.get(i).getLabel());
                                    }
                                    graph.getEdge(edgeName).addAttribute("layout.weight", 200);
                                } catch (Exception e) {

                                }
                            }
                        }
                    }
                }
            }else{
            	if(fieldList.get(i).getTypes().get(0).size() > 2) {
            		
            	}
            }
        }
        
        fieldList.add(exists);
        
        graph.addAttribute("ui.antialias");
        graph.addAttribute("ui.quality");
        graph.addAttribute("layout.quality", 4);
        graph.addAttribute("ui.stylesheet", "graph {\n" +
"    padding: 150px, 50px, 0px;\n" +
"}\n" +
"node {\n" +
//"    size: 300px, 50px;\n" +
//"    shape: box;\n" +
"    fill-color: rgba(255,255,255,255);\n" +
"    text-size: 12;\n" +
//"    text-mode: truncated;\n" +
"    stroke-mode: plain;\n" +
"    stroke-color: black;\n" +
"    size-mode: dyn-size;\n" +
"}\n" +
"edge {\n" +
"    fill-color: #222;\n" +
"    arrow-size: 8px, 8px;\n" +
"}\n" +
                /*
"node#Object$0 {\n" +
"    fill-color: blue;\n" +
"}\n" +
*/
"node:clicked {\n" +
"    fill-color: red;\n" +
"}");
        
        this.selectedGraph = graph;
        return graph;
    }
    
    public org.graphstream.graph.Graph changeSelectedWorld(Atom world) {
        ArrayList<Skolem> skolemList = xmlFile.getSkolemList();
        ArrayList<Atom> atomList = xmlFile.getAtomList();
        ArrayList<Sig> sigList = xmlFile.getSigList();
        ArrayList<Field> fieldList = xmlFile.getFieldList();
        
        ArrayList<Atom> objectList = xmlFile.getObjectList();
        ArrayList<Atom> propertyList = xmlFile.getPropertyList();
        ArrayList<Atom> dataTypeList = xmlFile.getDataTypeList();
        
        int i, j, k;
        org.graphstream.graph.Node node;
        obj.Field exists = null;
        //Graph graph = new MultiGraph(world.getLabel());
        ArrayList<ArrayList<String>> tuplesList;
        ArrayList<String> tuple;
        ArrayList<ArrayList<Integer>> typesList;
        ArrayList<Integer> types;
        boolean control = false;
        
        Iterator graphIter = selectedGraph.getNodeIterator();
        while(graphIter.hasNext()) {
        	org.graphstream.graph.Node graphNode = (org.graphstream.graph.Node) graphIter.next();
        	graphNode.addAttribute("ui.style", "fill-color: rgba(0,0,0,64); text-color: rgba(0,0,0,64); stroke-color: rgba(0,0,0,64);");
        }
        graphIter = selectedGraph.getEdgeIterator();
        while(graphIter.hasNext()) {
        	org.graphstream.graph.Edge graphNode = (org.graphstream.graph.Edge) graphIter.next();
        	graphNode.addAttribute("ui.style", "fill-color: rgba(0,0,0,64); text-color: rgba(0,0,0,64);");
        }
        
        for(i=0; i<fieldList.size(); i++) {
            if(fieldList.get(i).getLabel().equals("exists")) {
                exists = fieldList.remove(i);
                break;
            }
        }
        
        for(i=0; i<exists.getTuples().size(); i++) {
            if(world.hasLabel(exists.getTuples().get(i).get(0))) {
                ArrayList<String> stringList = xmlFile.getAtomTypeOnWorld(exists.getTuples().get(i).get(1), world.getLabel());
                String typeName = "<";
                for(j=0; j<stringList.size(); j++) {
                	System.out.println("TYPE: " + stringList.get(j));
                    typeName = typeName + stringList.get(j);
                    typeName = typeName + " (" + TypeName.getTypeName(ontoUmlParser.getElement(stringList.get(j))) + ")";
                    if(j + 1 != stringList.size()) {
                        typeName = typeName + ", ";
                    }
                }
                typeName = typeName + ">";
                
                //System.out.println("COMECOU " + exists.getTuples().get(i).get(1));
                Iterator iter = selectedGraph.getNodeIterator();
                while(iter.hasNext()) {
                	org.graphstream.graph.Node n = (org.graphstream.graph.Node) iter.next();
                	if(exists.getTuples().get(i).get(1).equals(n.toString())) {
                		System.out.println("This node already exists, no need to kill it");
                		n.addAttribute("ui.style", "fill-color: rgba(255, 255, 255, 255); text-color: rgba(0,0,0,255); stroke-color: black;");
                		control = true;
                		break;
                	}
                }
                
                if(control) {
                	
                }else{
                	selectedGraph.addNode(exists.getTuples().get(i).get(1)).addAttribute("ui.label", exists.getTuples().get(i).get(1) + typeName);
                	selectedGraph.getNode(exists.getTuples().get(i).get(1)).addAttribute("layout.weight", 0);
                    if(xmlFile.findAtom(exists.getTuples().get(i).get(1)).isObject()) {
                    	selectedGraph.getNode(exists.getTuples().get(i).get(1)).addAttribute("ui.style", "shape: circle; size: 300px, 50px;");
                    }
                    if(xmlFile.findAtom(exists.getTuples().get(i).get(1)).isProperty()) {
                    	selectedGraph.getNode(exists.getTuples().get(i).get(1)).addAttribute("ui.style", "shape: box; size: 90px;");
                    }
                    if(xmlFile.findAtom(exists.getTuples().get(i).get(1)).isDataType()) {
                    	selectedGraph.getNode(exists.getTuples().get(i).get(1)).addAttribute("ui.style", "shape: diamond; size: 90px;");
                    }
                }
                control = false;
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
                        if(world.hasLabel(tuple.get(0))) {
                            for(k=1; k<tuple.size() - 1; k++) {
                            	String edgeName = tuple.get(k) + "_" + tuple.get(k+1) + "_" + tuple.get(k+1) + "["+tuple.get(k)+"->"+tuple.get(k+1)+"]";
                            	Iterator edgeIter = selectedGraph.getEdgeIterator();
                            	while(edgeIter.hasNext()) {
                            		org.graphstream.graph.Edge e = (org.graphstream.graph.Edge) edgeIter.next();
                            		if(e.toString().equals(edgeName)) {
                            			e.addAttribute("ui.style", "fill-color: rgba(0, 0, 0, 255); text-color: rgba(0,0,0,255);");
                            			control = true;
                            			break;
                            		}
                            	}
                            	if(control) {
	                                
                            	}else{
                            		try {
	                                    if(xmlFile.findSigById(typesList.get(0).get(k+1)).isBuiltin()) {
	                                    	selectedGraph.addNode(tuple.get(k+1)).addAttribute("ui.label", tuple.get(k+1));
	                                    	selectedGraph.getNode(tuple.get(k+1)).addAttribute("ui.style", "shape: circle; size: 10px; fill-color: black; size-mode: normal; text-alignment: at-left;");
	                                    	selectedGraph.getNode(tuple.get(k+1)).addAttribute("layout.weight", 0);
	                                    	selectedGraph.getNode(tuple.get(k+1)).addAttribute("xyz", 1000, 1000, 10000);
	                                    	selectedGraph.addEdge(edgeName, tuple.get(k), tuple.get(k+1), true).addAttribute("ui.label", fieldList.get(i).getLabel());
	                                    }else{
	                                    	selectedGraph.addEdge(edgeName, tuple.get(k), tuple.get(k+1), true).addAttribute("ui.label", fieldList.get(i).getLabel());
	                                    }
	                                    selectedGraph.getEdge(edgeName).addAttribute("layout.weight", 200);
	                                } catch (Exception e) {
	
	                                }
                            	}
                            	control = false;
                            }
                        }
                    }
                }
            }else{
            	if(fieldList.get(i).getTypes().get(0).size() > 2) {
            		
            	}
            }
        }
        
        fieldList.add(exists);
        
        return selectedGraph;
    }
    
    public View showWorldGraph() {
        //viewer = graph.display(true);
        //viewer.enableAutoLayout();
        //viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
        viewer1 = new Viewer(worldGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer1.disableAutoLayout();
        viewer1.enableXYZfeedback(true);
// ...
        view1 = viewer1.addDefaultView(false);   // false indicates "no JFrame".
        //view1.setAutoscrolls(true);
// ...
        
        //view = viewer.getDefaultView();
        //view.disable();
        //view = viewer.getDefaultView();
        return view1;
    }
    
    public View showSelectedGraph() {
        //viewer = graph.display(true);
        //viewer.enableAutoLayout();
        //viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
        viewer = new Viewer(selectedGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
// ...
        view = viewer.addDefaultView(false);   // false indicates "no JFrame".
        //view.setAutoscrolls(true);
        //view.setMinimumSize(new Dimension(600, 600));
        //view.setPreferredSize(new Dimension(600, 600));
// ...
        
        //view = viewer.getDefaultView();
        //view.disable();
        //view = viewer.getDefaultView();
        return view;
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

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }
    
    public Viewer getViewer1() {
        return viewer1;
    }

    public void setViewer1(Viewer viewer1) {
        this.viewer1 = viewer1;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
    
    public View getView1() {
        return view1;
    }

    public void setView1(View view1) {
        this.view1 = view1;
    }
    
    
    
}
