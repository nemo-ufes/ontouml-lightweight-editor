package br.ufes.inf.nemo.instancevisualizer.xml;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Tuple {
	private ArrayList<String> elements;
    
    public Tuple(NodeList atomList) {
    	elements = new ArrayList<String>();
    	for(int i=0; i<atomList.getLength(); i++) {
    		Element tupleElement = (Element) atomList.item(i);
            String attrLabel = tupleElement.getAttribute("label");
            elements.add(attrLabel);
        }
    }
    
    public String get(int i) {
    	return elements.get(i);
    }
    
    public int size() {
    	return elements.size();
    }
}