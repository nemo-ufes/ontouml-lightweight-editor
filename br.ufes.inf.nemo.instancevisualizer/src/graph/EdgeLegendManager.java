package graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import xml.XMLFile;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.TypeName;

public class EdgeLegendManager {
	
	private ArrayList<EdgeTypeLegend> edgeTypeLegendList;
	private ArrayList<EdgeTypeLegend> edgeStereotypeLegendList;
    private XMLFile xmlFile;
	
    public EdgeLegendManager(XMLFile xmlFile) {
    	edgeTypeLegendList = new ArrayList();
    	edgeStereotypeLegendList = new ArrayList();
    	this.xmlFile = xmlFile;
    }
    
    //.\resources\edges\box.png
    /*public void addDefaultLegend(String type, String stereotype) {
    	String imagePath;
    	String style;
    	EdgeTypeLegend els = getEdgeStereotypeLegend(stereotype);
    	
    	if(els == null) {
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
    		edgeStereotypeLegendList.add(new EdgeTypeLegend(stereotype, imagePath, style));
    	}
    	
    	style = getEdgeStereotypeLegend(stereotype).getStyle();
    	imagePath = getEdgeStereotypeLegend(stereotype).getStyle();
    	EdgeTypeLegend elt = getEdgeTypeLegend(type);
    	if(elt == null)
    		edgeTypeLegendList.add(new EdgeTypeLegend(type, imagePath, ""));
    	
    }*/
    
    public void addDefaultLegendT(String type) {
    	edgeTypeLegendList.add(new EdgeTypeLegend(type, null, null));
    }
    
    public void addDefaultLegendS(String stereotype) {
    	String imagePath;
    	String style;
    	EdgeTypeLegend el = getEdgeStereotypeLegend(stereotype);
    	
    	if(el != null) {
    		imagePath = el.getImagePath();
    		style = "size: 0.25; stroke-width: 1px; stroke-mode: plain;";
    		return;
    	}else{
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
    	}
    	edgeStereotypeLegendList.add(new EdgeTypeLegend(stereotype, imagePath, style));
    }
    
    public EdgeTypeLegend getEdgeTypeLegend(String type) {
    	Iterator<EdgeTypeLegend> i = edgeTypeLegendList.iterator();
    	while(i.hasNext()) {
    		EdgeTypeLegend el = i.next();
    		if(el.getType() == type)
    			return el;
    	}
    	return null;
    }
    
    public EdgeTypeLegend getEdgeStereotypeLegend(String stereotype) {
    	Iterator<EdgeTypeLegend> i = edgeStereotypeLegendList.iterator();
    	while(i.hasNext()) {
    		EdgeTypeLegend el = i.next();
    		if(el.getType() == stereotype)
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
    
    public int getEdgeTypeListSize() {
    	return edgeTypeLegendList.size();
    }
    
    public EdgeTypeLegend getEdgeTypeLegend(int i) {
    	return edgeTypeLegendList.get(i);
    }

	public ArrayList<EdgeTypeLegend> getEdgeTypeLegendList() {
		return edgeTypeLegendList;
	}

	public ArrayList<EdgeTypeLegend> getEdgeStereotypeLegendList() {
		return edgeStereotypeLegendList;
	}
    
}