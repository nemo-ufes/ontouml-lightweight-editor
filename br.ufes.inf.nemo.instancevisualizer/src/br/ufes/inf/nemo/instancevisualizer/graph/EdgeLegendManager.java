package br.ufes.inf.nemo.instancevisualizer.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import br.ufes.inf.nemo.instancevisualizer.xml.XMLFile;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.TypeName;

public class EdgeLegendManager {
	
	private ArrayList<EdgeLegend> legendList;
    private XMLFile xmlFile;
	
    public EdgeLegendManager(XMLFile xmlFile) {
    	legendList = new ArrayList();
    	this.xmlFile = xmlFile;
    }
    
    public Iterator<EdgeLegend> getLegendIterator() {
    	return legendList.iterator();
    }
    
    public void addDefaultLegend(String type, String stereotype) {
    	String imagePath = "";
    	String style = "";
    	    	
		switch(stereotype) {
			case "Material":
				imagePath = ".\\resources\\edges\\dashes.png";
				style = "size: 0.25; stroke-width: 1px; stroke-mode: dashes;";
				break;
			case "Characterization":
				imagePath = ".\\resources\\edges\\cubic-curve.png";
				style = "size: 0.25; stroke-width: 1px; shape: cubic-curve; stroke-mode: plain;";
				break;
			default:
				imagePath = ".\\resources\\edges\\plain.png";
				style = "size: 0.25; stroke-width: 1px; stroke-mode: plain;";
				break;
		}
    	
    	legendList.add(new EdgeLegend(type, stereotype, imagePath, style));
    }
    
    public EdgeLegend getEdgeTypeLegend(String type) {
    	Iterator<EdgeLegend> iter = legendList.iterator();
    	while(iter.hasNext()) {
    		EdgeLegend el = iter.next();
    		if(el.getType().equals(type))
    			return el;
    	}
    	return null;
    }
    
    public EdgeLegend getEdgeLegendWithStereotype(String stereotype) {
    	Iterator<EdgeLegend> iter = legendList.iterator();
    	while(iter.hasNext()) {
    		EdgeLegend el = iter.next();
    		if(el.getStereotype().equals(stereotype))
    			return el;
    	}
    	return null;
    }
    
    /*public String getResultStyle(String type) {
    	String stereotype = xmlFile.getFieldStereotype(type);
    	EdgeTypeLegend etl = getEdgeTypeLegend(type); 
		if(etl.getImagePath() != null) {
			imagePath = edgeTypeLegend.getImagePath();
		}
    }*/
    
}