package br.ufes.inf.nemo.instancevisualizer.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mauricio
 */
public class ReadXML {
    
    private Document doc;
    private Node pos;
    
    public ReadXML(File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(file.getAbsolutePath());
        doc.getDocumentElement().normalize();
        pos = doc.getFirstChild();
    }
    
    public Document getDoc() {
        return doc;
    }
    
    public Node getPos() {
        return pos;
    }
    
    public void resetPos() {
        pos = doc.getFirstChild();
    }
    
    public Node getChild() {
        pos = pos.getFirstChild().getNextSibling();
        return pos;
    }
    
    public Node getSibling() {
        pos = pos.getNextSibling().getNextSibling();
        return pos;
    }
    
    public Node getAlloyNode() {
        pos = doc.getFirstChild();
        return pos;
    }
    
    public Node getInstanceNode() {
        pos = getAlloyNode().getFirstChild().getNextSibling();
        return pos;
    }
    
    public Node getSigNode() {
        pos = getInstanceNode().getFirstChild().getNextSibling();
        return pos;
    }
    


}