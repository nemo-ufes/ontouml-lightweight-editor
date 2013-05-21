package obj;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import org.graphstream.graph.EdgeRejectedException;
import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.Graph;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.graphicGraph.stylesheet.*;
import java.util.Scanner;
import org.graphstream.ui.swingViewer.View;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mauricio
 */
public class Tester {
    
    public static void main(String argv[]) throws ParserConfigurationException, SAXException, IOException {
        //System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        
        ArrayList<Integer> integers = new ArrayList();
        ArrayList skolemList = new ArrayList();
        
        ArrayList<Atom> atomList = new ArrayList();
        ArrayList<Sig> sigList = new ArrayList();
        ArrayList<Field> fieldList = new ArrayList();
        
        int i, j, k;
        
        ReadXML xml = new ReadXML("open.xml");
        //System.out.println(xml.getPos().getNodeName());
        
        Sig sig;
        Field field;
        
        NodeList sigl = xml.getDoc().getElementsByTagName("sig");
        NodeList atoml;
        NodeList fieldl = xml.getDoc().getElementsByTagName("field");
        //System.out.println(fieldl.item(0));
        org.w3c.dom.Element aux;
        
        for(i=0; i<sigl.getLength(); i++) {
            System.out.println("NUM");
            aux = (org.w3c.dom.Element) sigl.item(i);
            sig = new Sig(aux);
            atoml = aux.getElementsByTagName("atom");
            
            sigList.add(sig);
            
            
            for(j=0; j<atoml.getLength(); j++) {
                aux = (org.w3c.dom.Element) atoml.item(j);
                //System.out.println(atoml.getLength());
                atomList.add(new Atom(aux, sig));
                System.out.println(aux.getAttribute("label"));
            }
        }
        
        for(i=0; i<fieldl.getLength(); i++) {
            System.out.println("NUM2");
            fieldList.add(new Field((org.w3c.dom.Element) fieldl.item(i), atomList));
        }
        
        System.out.println("");
        System.out.println("");
        for(i=0; i<fieldList.get(0).getTuples().size(); i++) {
            for(j=0; j<fieldList.get(0).getTuples().get(i).size(); j++) {
                System.out.println(fieldList.get(0).getTuples().get(i).get(j));
            }
            System.out.println("");
            
        }
        System.out.println("");
        System.out.println("");
        
        Graph graph = new MultiGraph("I can see dead pixels");
        ArrayList<ArrayList<String>> aux2;
        ArrayList<String> aux3;
        System.out.println(atomList.size());
        
        for(i=0; i<atomList.size(); i++) {
            System.out.println("FOI2");
            graph.addNode(atomList.get(i).getLabel());
            graph.getNode(i).addAttribute("ui.label", atomList.get(i).getLabel());
            //graph.getNode(i).addAttribute("ui.stylesheet", atomList.get(i).getLabel());
        }
        
        for(i=0; i<fieldList.size(); i++) {
            aux2 = fieldList.get(i).getTuples();
            for(j=0; j<aux2.size(); j++) {
                aux3 = aux2.get(j);
                for(k=0; k<aux3.size() - 1; k++) {
                    try {
                    System.out.println(aux3.get(k));
                    System.out.println(aux3.get(k+1));
                    graph.addEdge(aux3.get(k).concat(fieldList.get(i).getLabel().concat(aux3.get(k+1))), aux3.get(k), aux3.get(k+1));
                    graph.getEdge(aux3.get(k).concat(fieldList.get(i).getLabel().concat(aux3.get(k+1)))).addAttribute("ui.label", fieldList.get(i).getLabel());
                    } catch (Exception e) {
                        
                    }
                }
            }
        }
        graph.addAttribute("ui.antialias");
        
        graph.addAttribute("ui.style", "node {\n" +
"   fill-color: red;\n" +
"   size: 1px, 1px;\n" +
"   shape: box;\n" +
"}");
        
        graph.addAttribute("ui.stylesheet", "node:clicked {\n" +
"    fill-color: red;\n" +
"}");
        gui.MainWindow mw = new gui.MainWindow(graph);
        mw.setVisible(true);
        
    }
}
