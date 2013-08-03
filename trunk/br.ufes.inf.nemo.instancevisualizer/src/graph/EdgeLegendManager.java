package graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.TypeName;

public class EdgeLegendManager {
	
	private ArrayList<String> typeList;
	private ArrayList<String> stereoTypeList;
    private ArrayList<String> typeImages;
    private ArrayList<String> typeStyles;
    
    public EdgeLegendManager() {//EdgeManager edgeManager, OntoUMLParser ontoUmlParser, XMLFile xmlFile) {
    	typeList = new ArrayList();
    	stereoTypeList = new ArrayList();
        typeImages = new ArrayList();
        typeStyles = new ArrayList();
    }
    
    //.\resources\edges\box.png
    public void addType(String typeName, String stereoType, String mode) {
    	typeList.add(typeName);
    	String imagePath;
    	String style;
    	if(stereoTypeList.contains(stereoType)) {
    		imagePath = getStereoTypeImagePath(stereoType);
    		style = "size: 0.25; stroke-width: 1px; stroke-mode: plain;";
    	}else{
    		switch(stereoType) {
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
    	stereoTypeList.add(stereoType);
    	typeImages.add(imagePath);
    	typeStyles.add(style);
    }

    public void updateTypeStyle(String typeName, String style) {
    	int indexOfType = typeList.indexOf(typeName);
    	typeStyles.set(indexOfType, style);
    }
    
    public String getTypeImagePath(String typeName) {
    	return typeImages.get(typeList.indexOf(typeName));
    }
    
    public String getStereoTypeImagePath(String stereoTypeName) {
    	return typeImages.get(stereoTypeList.indexOf(stereoTypeName));
    }
    
    public String getTypeStyle(String typeName) {
    	return typeStyles.get(typeList.indexOf(typeName));
    }
    
    public String getTypeImage(int index) {
		return typeImages.get(index);
	}
    
    public String getType(int index) {
		return typeList.get(index);
	}
    
    public int getTypeIndex(String typeName) {
		return typeList.indexOf(typeName);
	}
    
    public int getListSize() {
    	return typeList.size();
    }
    
    public String getStereoType(String typeName) {
    	int index = typeList.indexOf(typeName);
    	return stereoTypeList.get(index);
    }
    
    
    
	public ArrayList<String> getTypeList() {
		return typeList;
	}

	public ArrayList<String> getStereoTypeList() {
		return stereoTypeList;
	}

	public ArrayList<String> getTypeImages() {
		return typeImages;
	}

	public ArrayList<String> getTypeStyles() {
		return typeStyles;
	}
    
    
}