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
    private OntoUMLParser ontoUmlParser;
    private int edgeId;
    
    public XGraph(XMLFile xmlGraph, OntoUMLParser onto, int mode) throws ParserConfigurationException, SAXException, IOException {
        xmlFile = xmlGraph;
        worldGraph = null;
        selectedGraph = null;
        viewer = null;
        view = null;
        ontoUmlParser = onto;
        edgeId = 0;
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
        int current=1, past=0, future=0, temporal=0;
        
        // Getting only world atoms.
        for(i=0; i<atomList.size(); i++) {
        	if(atomList.get(i).isWorld()) {
        		System.out.println(atomList.get(i).getLabel().substring(0, 15));
        		org.graphstream.graph.Node nodeAux = worldGraph.addNode(atomList.get(i).getLabel());
        		/*
        		if(atomList.get(i).getWorldType().equals("Current")) {
        			nodeAux.addAttribute("ui.style", "size: 15px; shadow-mode: gradient-radial; shadow-width: 10px; shadow-color: black, white; shadow-offset: 0px;");
        			nodeAux.addAttribute("ui.label", "C");
        			nodeAux.addAttribute("xy", 0, 0);
        		}else{
        			nodeAux.addAttribute("ui.style", "size: 10px; fill-color: white;");
        			nodeAux.addAttribute("xy", i, 0);
        		}
        		*/
                	
                    switch(atomList.get(i).getWorldType()) {
                        case "Past":
                        	nodeAux.addAttribute("ui.style", "size: 10px; fill-color: white;");
                			nodeAux.addAttribute("xy", -1, past);
                			past=-1;
                            break;
                        case "Current":
                        	nodeAux.addAttribute("ui.style", "size: 15px; fill-color: green; shadow-mode: gradient-radial; shadow-width: 10px; shadow-color: black, white; shadow-offset: 0px;");
                			nodeAux.addAttribute("ui.label", "C");
                			nodeAux.addAttribute("xy", 0, 0);
                            break;
                        case "Future":
                        	nodeAux.addAttribute("ui.style", "size: 10px; fill-color: white;");
                			nodeAux.addAttribute("xy", 1, future);
                			future=-1;
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
"padding: 80px;\n" +
"}\n" +
"node { size-mode: normal; text-size: 12; size: 10px; fill-color: white; stroke-mode: plain;} edge { shape: blob; size: 10px; }");

        return worldGraph;
    }

    
	/**
	   * Creates the 
	   * @param world the atom that represents the world
	   */
    public org.graphstream.graph.Graph createSelectedWorld() {
        ArrayList<Skolem> skolemList = xmlFile.getSkolemList();
        ArrayList<Atom> atomList = xmlFile.getAtomList();
        ArrayList<Sig> sigList = xmlFile.getSigList();
        ArrayList<Field> fieldList = xmlFile.getFieldList();
        
        ArrayList<Atom> objectList = xmlFile.getObjectList();
        ArrayList<Atom> propertyList = xmlFile.getPropertyList();
        ArrayList<Atom> dataTypeList = xmlFile.getDataTypeList();
        
        int i, j, k;
        
        obj.Field exists = null;
        Graph graph = new MultiGraph("world_structure/CurrentWorld$0");
        ArrayList<ArrayList<String>> tuplesList;
        ArrayList<String> tuple;
        ArrayList<ArrayList<Integer>> typesList;
        String edgeType = "";
        /*
        for(i=0; i<fieldList.size(); i++) {
            if(fieldList.get(i).getLabel().equals("exists")) {
                exists = fieldList.remove(i);
                break;
            }
        }
        */
        for(i=0; i<fieldList.size(); i++) {
        	if(fieldList.get(i).getLabel().equals("exists")) {
        		exists = fieldList.get(i);
		        for(i=0; i<exists.getTuples().size(); i++) {
		            if("world_structure/CurrentWorld$0".equals(exists.getTuples().get(i).get(0))) {
		                /*
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
		                */
		                org.graphstream.graph.Node nodeAux = graph.addNode(exists.getTuples().get(i).get(1));
		                nodeAux.addAttribute("ui.label", exists.getTuples().get(i).get(1));
		                nodeAux.addAttribute("layout.weight", 0);
		                
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
                        if("world_structure/CurrentWorld$0".equals(tuple.get(0))) {
                            //for(k=1; k<tuple.size() - 1; k++) {	// k=1; k+1=2
                                try {
                                	edgeType = TypeName.getTypeName(ontoUmlParser.getElement(fieldList.get(i).getLabel()));
                                	String edgeName = fieldList.get(i).getLabel() + "$" + edgeId;//"[" + tuple.get(1) + "->" + tuple.get(2) + "]";
                                	System.out.println("NAME: " + edgeName);
                                	edgeId++;
                                    if(xmlFile.findSigById(typesList.get(0).get(2)).isBuiltin()) {
                                    	graph.addNode(tuple.get(2)).addAttribute("ui.label", tuple.get(2));
                                    	graph.getNode(tuple.get(2)).addAttribute("ui.style", "shape: circle; size: 10px; fill-color: black; size-mode: normal; text-alignment: at-left;");
                                    	graph.getNode(tuple.get(2)).addAttribute("layout.weight", 0);
                                    	graph.getNode(tuple.get(2)).addAttribute("xyz", 1000, 1000, 10000);
                                    	graph.addEdge(edgeName, tuple.get(1), tuple.get(2), true).addAttribute("ui.label", fieldList.get(i).getLabel());
                                    	graph.getEdge(edgeName).addAttribute("layout.weight", 1);
                                    }else{
                                    	graph.addEdge(edgeName, tuple.get(1), tuple.get(2), true).addAttribute("ui.label", fieldList.get(i).getLabel() + " (" + edgeType + ")");
                                    }
                                    graph.getEdge(edgeName).addAttribute("layout.weight", 2);
                                } catch (Exception e) {

                                }
                            //}
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
"    size-mode: fit;\n" +
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
        ArrayList<ArrayList<String>> tuplesList;
        ArrayList<String> tuple;
        ArrayList<ArrayList<Integer>> typesList;
        ArrayList<Integer> types;
        boolean control = false;
        String edgeType = "";
        
        Iterator graphIter = selectedGraph.getNodeIterator();
        while(graphIter.hasNext()) {
        	org.graphstream.graph.Node graphNode = (org.graphstream.graph.Node) graphIter.next();
        	//graphNode.addAttribute("isOld", true);
        	graphNode.addAttribute("ui.style", "fill-color: rgba(0,0,0,64); text-color: rgba(0,0,0,64); stroke-color: rgba(0,0,0,64);");
        }
        graphIter = selectedGraph.getEdgeIterator();
        while(graphIter.hasNext()) {
        	org.graphstream.graph.Edge graphNode = (org.graphstream.graph.Edge) graphIter.next();
        	graphNode.addAttribute("ui.style", "fill-color: rgba(0,0,0,64); text-color: rgba(0,0,0,64);");
        }
        
        for(i=0; i<fieldList.size(); i++) {
            if(fieldList.get(i).getLabel().equals("exists")) {
                exists = fieldList.get(i);
                break;
            }
        }
        
        for(i=0; i<exists.getTuples().size(); i++) {
            if(world.hasLabel(exists.getTuples().get(i).get(0))) {
            	/*
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
                */
                
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
                	selectedGraph.addNode(exists.getTuples().get(i).get(1)).addAttribute("ui.label", exists.getTuples().get(i).get(1));
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
                        	edgeType = TypeName.getTypeName(ontoUmlParser.getElement(fieldList.get(i).getLabel()));
                        	String edgeName = fieldList.get(i).getLabel() + "$" + edgeId;// + "["+tuple.get(1)+"->"+tuple.get(2)+"]";
                        	String edgeToString = fieldList.get(i).getLabel() + "["+tuple.get(1)+"->"+tuple.get(2)+"]";
                        	
                        	Iterator edgeIter = selectedGraph.getEdgeIterator();
                        	while(edgeIter.hasNext()) {
                        		org.graphstream.graph.Edge edg = (org.graphstream.graph.Edge) edgeIter.next();
                        		int indexAux = edg.toString().indexOf('$');
                        		String oldEdgeToString = edg.toString().substring(0, indexAux);
                        		indexAux = edg.toString().indexOf('[');
                        		oldEdgeToString = oldEdgeToString.concat(edg.toString().substring(indexAux));
                        		System.out.println("IGUAL: " + edgeToString + "__" + oldEdgeToString);
                        		if(oldEdgeToString.equals(edgeToString)) {
                        			
                        			edg.addAttribute("ui.style", "fill-color: rgba(0, 0, 0, 255); text-color: rgba(0,0,0,255);");
                        			control = true;
                        			break;
                        		}
                        	}
                        	edgeId++;
                        	org.graphstream.graph.Edge ed = selectedGraph.getEdge(edgeName);
                        	System.out.println("NEWNAME: " + edgeName);
                        	
                        	if(control) {
                        		
                        	}else{
                        		
                        		try {
                        			if(xmlFile.findSigById(typesList.get(0).get(2)).isBuiltin()) {
                        				selectedGraph.addNode(tuple.get(2)).addAttribute("ui.label", tuple.get(2));
                        				selectedGraph.getNode(tuple.get(2)).addAttribute("ui.style", "shape: circle; size: 10px; fill-color: black; size-mode: normal; text-alignment: at-left;");
                        				selectedGraph.getNode(tuple.get(2)).addAttribute("layout.weight", 0);
                        				selectedGraph.getNode(tuple.get(2)).addAttribute("xyz", 1000, 1000, 10000);
                        				selectedGraph.addEdge(edgeName, tuple.get(1), tuple.get(2), true).addAttribute("ui.label", fieldList.get(i).getLabel());
                        				
                        			}else{
                        				selectedGraph.addEdge(edgeName, tuple.get(1), tuple.get(2), true).addAttribute("ui.label", fieldList.get(i).getLabel() + " (" + edgeType + ")");
                        				
                        			}
                        			selectedGraph.getEdge(edgeName).addAttribute("layout.weight", 200);
                        			
                        		} catch (Exception e) {
                        			
                        		}
                        		
                        	}
                        	
                        	/*
                        	if(ed != null) {
                        		ed.addAttribute("ui.style", "fill-color: rgba(0, 0, 0, 255); text-color: rgba(0,0,0,255);");
                    			control = true;
                    			break;
                        	}else{
                        		System.out.println("SFEWSDFERGRTFNBRTDB");
                        		try {
                        			if(xmlFile.findSigById(typesList.get(0).get(2)).isBuiltin()) {
                        				selectedGraph.addNode(tuple.get(2)).addAttribute("ui.label", tuple.get(2));
                        				selectedGraph.getNode(tuple.get(2)).addAttribute("ui.style", "shape: circle; size: 10px; fill-color: black; size-mode: normal; text-alignment: at-left;");
                        				selectedGraph.getNode(tuple.get(2)).addAttribute("layout.weight", 0);
                        				selectedGraph.getNode(tuple.get(2)).addAttribute("xyz", 1000, 1000, 10000);
                        				selectedGraph.addEdge(edgeName, tuple.get(1), tuple.get(2), true).addAttribute("ui.label", fieldList.get(i).getLabel());
                        				
                        			}else{
                        				selectedGraph.addEdge(edgeName + "$" + edgeId, tuple.get(1), tuple.get(2), true).addAttribute("ui.label", fieldList.get(i).getLabel());
                        				
                        			}
                        			selectedGraph.getEdge(edgeName).addAttribute("layout.weight", 200);
                        			
                        		} catch (Exception e) {
                        			
                        		}
                        		
                        	}
                        	*/
                        	control = false;
                            }
                        }
                    }
            }
        }
        return selectedGraph;
    }
    
    public View showWorldGraph() {
        //viewer = graph.display(true);
        //viewer.enableAutoLayout();
        //viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
        viewer1 = new Viewer(worldGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer1.disableAutoLayout();
// ...
        view1 = viewer1.addDefaultView(false);   // false indicates "no JFrame".
        view1.getCamera().setViewCenter(0, 0, 0);
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

	public OntoUMLParser getOntoUmlParser() {
		return ontoUmlParser;
	}

	public void setOntoUmlParser(OntoUMLParser ontoUmlParser) {
		this.ontoUmlParser = ontoUmlParser;
	}
    
    
    
}
