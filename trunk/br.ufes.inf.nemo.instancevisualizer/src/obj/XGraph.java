/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
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
    private Graph graph;
    private Viewer viewer;
    private View view;
    OntoUMLParser ontoUmlParser;
    
    public XGraph(XMLFile xmlGraph, OntoUMLParser onto, int mode) throws ParserConfigurationException, SAXException, IOException {
        xmlFile = xmlGraph;
        graph = null;
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
        ArrayList<ArrayList<Atom>> tuplesList;
        ArrayList<Atom> tuple;
        
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
                            System.out.println("THIS IS NOT POSSIBLE!");
                            System.exit(1);
                    }
                    graph.getNode(atomList.get(i).getLabel()).addAttribute("ui.label", atomList.get(i).getLabel());
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
                            String edgeName = tuple.get(k).getLabel() + "_" + tuple.get(k+1);
                            //edgeName = edgeName.concat(tuple.get(k+1).getLabel());
                            System.out.println(tuple.get(k));
                            System.out.println(tuple.get(k+1));
                            graph.addEdge(edgeName, tuple.get(k).getLabel(), tuple.get(k+1).getLabel(), true).addAttribute("ui.label", fieldList.get(i).getLabel());
                            //graph.getEdge(edgeName).addAttribute("ui.label", fieldList.get(i).getLabel());
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }
        
        graph.addAttribute("ui.antialias");
        
        graph.addAttribute("ui.stylesheet", "graph {\n" +
"padding: 90px;\n" +
"}\n" +
"node {\n" +
"    size: 250px, 50px;\n" +
"    shape: circle;\n" +
//"    fill-color: rgba(255,255,255,255);\n" +
"    text-size: 14;\n" +
"    stroke-mode: plain;\n" +
"    stroke-color: black;\n" +
"    size-mode: normal;\n" +
"    fill-mode: dyn-plain;\n" +
"fill-color: red, green, blue, yellow, white;\n" +
"}\n" +
"edge {\n" +
"    fill-color: #222;\n" +
"    arrow-size: 8px, 8px;\n" +
"}" +
"node#A {\n" +
"    fill-color: blue;\n" +
"}\n" +
"node:clicked {\n" +
"    fill-color: red;\n" +
"}");   

        this.graph = graph;
        return graph;
    }

    
    
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
        Graph graph = new MultiGraph("I can see dead pixels");
        ArrayList<ArrayList<Atom>> tuplesList;
        ArrayList<Atom> tuple;
        graph.addAttribute("ui.stylesheet", "graph {\n" +
"    padding: 200px;\n" +
"}\n");
        /*
        for(i=0; i<atomList.size(); i++) {
                if('w' == atomList.get(i).getLabel().charAt(0)) {
                    System.out.println("qwoficnwe9in");
                    graph.addNode(atomList.get(i).getLabel());
                    graph.getNode(atomList.get(i).getLabel()).addAttribute("ui.label", atomList.get(i).getLabel());
                    worldComboBox.addItem(atomList.get(i).getLabel());
                }
        }
        */
        //graph.setAutoCreate(true);
        for(i=0; i<fieldList.size(); i++) {
            if(fieldList.get(i).getLabel().equals("exists")) {
                exists = fieldList.remove(i);
                break;
            }
        }
        
        for(i=0; i<exists.getTuples().size(); i++) {
            if(exists.getTuples().get(i).get(0).equals(world)) {
                ArrayList<String> stringList = xmlFile.getAtomTypeOnWorld(exists.getTuples().get(i).get(1).getLabel(), world.getLabel());
                String typeName = "<";
                for(j=0; j<stringList.size(); j++) {
                    typeName = typeName + stringList.get(j);
                    typeName = typeName + " (" + TypeName.getTypeName(ontoUmlParser.getElement(stringList.get(j))) + ")";
                    if(j + 1 != stringList.size()) {
                        typeName = typeName + ", ";
                    }
                }
                typeName = typeName + ">";
                graph.addNode(exists.getTuples().get(i).get(1).getLabel()).addAttribute("ui.label", exists.getTuples().get(i).get(1).getLabel() + typeName);
                if(exists.getTuples().get(i).get(1).isObject()) {
                    //graph.getNode(exists.getTuples().get(i).get(1).getLabel()).addAttribute("ui.style", "shape: circle;\n");
                    //graph.getNode(exists.getTuples().get(i).get(1).getLabel()).addAttribute("ui.size", "60");
                    graph.getNode(exists.getTuples().get(i).get(1).getLabel()).addAttribute("ui.stylesheet", "node#"+exists.getTuples().get(i).get(1).getLabel()+"{\n   size: 200px, 50px;\n}\n");
                    graph.getNode(exists.getTuples().get(i).get(1).getLabel()).addAttribute("ui.style", "shape: circle;\n");
                }
                if(exists.getTuples().get(i).get(1).isProperty()) {
                    graph.getNode(exists.getTuples().get(i).get(1).getLabel()).addAttribute("ui.style", "shape: box;\n");
                    graph.getNode(exists.getTuples().get(i).get(1).getLabel()).addAttribute("ui.size", "90");
                }
                if(exists.getTuples().get(i).get(1).isDataType()) {
                    graph.getNode(exists.getTuples().get(i).get(1).getLabel()).addAttribute("ui.style", "shape: diamond;\n");
                    graph.getNode(exists.getTuples().get(i).get(1).getLabel()).addAttribute("ui.size", "90");
                }
                
            }
        }
        
        for(i=0; i<fieldList.size(); i++) {
            if(!fieldList.get(i).getTuples().isEmpty()) {
                System.out.println(fieldList.get(i).getTuples().get(0).get(0).getLabel());
                if(fieldList.get(i).getTuples().get(0).size() > 2) { 
                    tuplesList = fieldList.get(i).getTuples();
                    for(j=0; j<tuplesList.size(); j++) {
                        tuple = tuplesList.get(j);
                        if(tuple.get(0).equals(world)) {
                            for(k=1; k<tuple.size() - 1; k++) {
                                try {
                                    String edgeName = tuple.get(k).getLabel() + "_" + tuple.get(k+1);
                                    System.out.println(tuple.get(k));
                                    System.out.println(tuple.get(k+1));
                                    graph.addEdge(edgeName, tuple.get(k).getLabel(), tuple.get(k+1).getLabel(), true).addAttribute("ui.label", fieldList.get(i).getLabel());
                                } catch (Exception e) {

                                }
                            }
                        }
                    }
                }
            }
        }
        /*
        for(i=0; i<fieldList.size(); i++) {
                tuplesList = fieldList.get(i).getTuples();
                for(j=0; j<tuplesList.size(); j++) {
                    tuple = tuplesList.get(j);
                    if(tuple.get(0).equals((String) worldComboBox.getSelectedItem())) {
                        for(k=0; k<tuple.size() - 1; k++) {
                            try {
                                System.out.println("oweifheroigherbhgerth");
                                System.out.println(tuple.get(k));
                                System.out.println(tuple.get(k+1));
                                graph.addNode(tuple.get(k));
                                graph.addNode(tuple.get(k+1));
                                graph.getNode(tuple.get(k)).addAttribute("ui.label", tuple.get(k));
                                graph.getNode(tuple.get(k+1)).addAttribute("ui.label", tuple.get(k));
                                graph.addEdge(tuple.get(k).concat(fieldList.get(i).getLabel().concat(tuple.get(k+1))), tuple.get(k), tuple.get(k+1), true);
                                graph.getEdge(tuple.get(k).concat(fieldList.get(i).getLabel().concat(tuple.get(k+1)))).addAttribute("ui.label", fieldList.get(i).getLabel());
                            } catch (Exception e) {

                            }
                        }
                    }
                }
        }
        */
        fieldList.add(exists);
        
        graph.addAttribute("ui.antialias");
        
        graph.addAttribute("ui.stylesheet", "graph {\n" +
//"    padding: 50px\n" +
"}\n" +
"node {\n" +
"    size: 600px, 50px;\n" +
//"    shape: box;\n" +
"    fill-color: rgba(255,255,255,255);\n" +
"    text-size: 14;\n" +
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
        
        this.graph = graph;
        return graph;
    }
/*
    public org.graphstream.graph.Graph showAll() {
        int i, j, k;
        Graph graph = new MultiGraph("I can see dead pixels");
        ArrayList<ArrayList<String>> tuplesList;
        ArrayList<String> tuple;
        System.out.println(atomList.size());
        
        for(i=0; i<atomList.size(); i++) {
            System.out.println("FOI2");
            graph.addNode(atomList.get(i).getLabel());
            graph.getNode(i).addAttribute("ui.label", atomList.get(i).getLabel());

        }
        
        for(i=0; i<fieldList.size(); i++) {
            tuplesList = fieldList.get(i).getTuples();
            for(j=0; j<tuplesList.size(); j++) {
                tuple = tuplesList.get(j);
                for(k=0; k<tuple.size() - 1; k++) {
                    try {
                    System.out.println(tuple.get(k));
                    System.out.println(tuple.get(k+1));
                    graph.addEdge(tuple.get(k).concat(fieldList.get(i).getLabel().concat(tuple.get(k+1))), tuple.get(k), tuple.get(k+1));
                    graph.getEdge(tuple.get(k).concat(fieldList.get(i).getLabel().concat(tuple.get(k+1)))).addAttribute("ui.label", fieldList.get(i).getLabel());
                    } catch (Exception e) {
                        
                    }
                }
            }
        }
        
        graph.addAttribute("ui.antialias");
        
        graph.addAttribute("ui.stylesheet", "graph {\n" +
"}\n" +
"node {\n" +
"    size: 50px, 50px;\n" +
"    shape: box;\n" +
"    fill-color: green;\n" +
"    stroke-mode: plain;\n" +
"    stroke-color: yellow;\n" +
"    size-mode: fit;\n" +
"}\n" +
"node#A {\n" +
"    fill-color: blue;\n" +
"}\n" +
"node:clicked {\n" +
"    fill-color: red;\n" +
"}");   
        
        return graph;
    }
*/
    
    public View showGraph() {
        //viewer = graph.display(true);
        //viewer.enableAutoLayout();
        //viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
        viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
// ...
        view = viewer.addDefaultView(false);   // false indicates "no JFrame".
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

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
    
}
