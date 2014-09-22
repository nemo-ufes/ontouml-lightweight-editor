package br.ufes.inf.nemo.instancevisualizer.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import br.ufes.inf.nemo.instancevisualizer.xml.XMLFile;

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
				imagePath = "." + File.separator + "resources" + File.separator + "edges" + File.separator + "dashes.png";
				style = "size: 0.25; stroke-width: 1px; stroke-mode: dashes; z-index: 1;";
				break;
			case "Characterization":
				imagePath = "." + File.separator + "resources" + File.separator + "edges" + File.separator + "cubic-curve.png";
				style = "size: 0.25; stroke-width: 1px; shape: cubic-curve; stroke-mode: plain; z-index: 1;";
				break;
			default:
				imagePath = "." + File.separator + "resources" + File.separator + "edges" + File.separator + "plain.png";
				style = "size: 0.25; stroke-width: 1px; stroke-mode: plain; z-index: 1;";
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
    
    public void setVisible(String type, boolean visible) {
    	EdgeLegend legend = getEdgeTypeLegend(type);
    	String style = legend.getStyle();
    	if(visible) {
    		int index = style.indexOf("\nvisibility-mode: hidden;");
    		if(index > 0) {
    			style = style.substring(0, index) + "\nvisibility-mode: normal;" + style.substring(index+25);
    		}else{
    			style += "\nvisibility-mode: normal;";
    		}
    		legend.setStyle(style);
    	}else{
    		int index = style.indexOf("\nvisibility-mode: normal;");
    		if(index > 0) {
    			style = style.substring(0, index) + "\nvisibility-mode: hidden;" + style.substring(index+25);
    		}else{
    			style += "\nvisibility-mode: hidden;";
    		}
    		legend.setStyle(style);
    	}
    }
    
    /*public String getResultStyle(String type) {
    	String stereotype = xmlFile.getFieldStereotype(type);
    	EdgeTypeLegend etl = getEdgeTypeLegend(type); 
		if(etl.getImagePath() != null) {
			imagePath = edgeTypeLegend.getImagePath();
		}
    }*/
    
}