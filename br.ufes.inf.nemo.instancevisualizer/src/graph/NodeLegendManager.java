package graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import xml.Atom;
import xml.XMLFile;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.TypeName;

public class NodeLegendManager {
	
	private ArrayList<String> typeList;
	private ArrayList<String> stereoTypeList;
	private ArrayList<String> prefixList;
    private ArrayList<String> typeImages;
    private ArrayList<String> typeStyles;
    
    private ArrayList<Integer> typeAmounts;
    
    public NodeLegendManager(OntoUMLParser ontoUmlParser, XMLFile xmlFile) {
    	typeList = new ArrayList();
    	stereoTypeList = new ArrayList();
    	prefixList = new ArrayList();
        typeImages = new ArrayList();
        typeStyles = new ArrayList();
        
        typeAmounts = new ArrayList();
        
        Iterator<Atom> atoms = xmlFile.getAtomList().iterator();
        ArrayList<Atom> worldList = xmlFile.getWorldList();
        
        while(atoms.hasNext()) {
        	Atom atom = atoms.next();
        	String atomLabel = atom.getLabel();
        	
        	for(int i=0; i<worldList.size(); i++) {
        		String worldLabel = worldList.get(i).getLabel();
        		if(!atom.isWorld() && xmlFile.isAtomOnWorld(atomLabel, worldLabel)) {
        			
        			Classifier mainTypeClassif = xmlFile.getAtomMainType(atomLabel, worldLabel);
                    String mainTypeName = "";
                    if(mainTypeClassif == null) {
                    	mainTypeName = "Unknown Type";
                    }else{
                    	mainTypeName = mainTypeClassif.getName();
                    }
                    
                    if(!typeList.contains(mainTypeName)) {
                    	typeList.add(mainTypeName);
                    	typeAmounts.add(0);
                    	stereoTypeList.add(TypeName.getTypeName(mainTypeClassif));
                    	prefixList.add(createPrefix(mainTypeName));
                    	
                    	File image = new File("./resources/gur project");
                    	String imagePath = image.getPath() + "\\" + image.list()[typeList.indexOf(mainTypeName)];
                    	typeImages.add(imagePath);
                    	System.out.println(imagePath);
                    	switch(TypeName.getTypeName(mainTypeClassif)) {
			                case "Mode":
			                	typeStyles.add("text-offset: 0px, 0px;\n shape: circle;\n text-background-mode: plain;\n text-background-color: rgba(255,255,255,192);\n stroke-mode: none;\n size: 16px;\n fill-mode: image-scaled;\n fill-image: url('" + imagePath + "');\n");
			                	break;
			                case "Relator":
			                	typeStyles.add("text-offset: 0px, 0px;\n shape: circle;\n text-background-mode: plain;\n text-background-color: rgba(255,255,255,192);\n stroke-mode: none;\n size: 48px;\n fill-mode: image-scaled;\n fill-image: url('" + imagePath + "');\n");
			                	//typeStyles.add("text-offset: 0px, 0px; shape: circle; text-background-mode: plain; text-background-color: rgba(255,255,255,192); stroke-mode: none; size: 48px; fill-mode: image-scaled; fill-image: url('" + imagePath + "');");
			                	break;
			                default:
			                	typeStyles.add("text-offset: 0px, 0px;\n shape: circle;\n text-background-mode: plain;\n text-background-color: rgba(255,255,255,192);\n stroke-mode: none;\n size: 32px;\n fill-mode: image-scaled;\n fill-image: url('" + imagePath + "');\n");
			                	//typeStyles.add("text-offset: 0px, 0px; shape: circle; text-background-mode: plain; text-background-color: rgba(255,255,255,192); stroke-mode: none; size: 32px; fill-mode: image-scaled; fill-image: url('" + imagePath + "');");
			                	break;
                    	}
                    }
        		}
        	}
        }
    }
    
    public String createPrefix(String typeName) {
    	String prefix = "" + typeName.charAt(0);
    	for(int i=1; i<typeName.length(); i++) {
    		String verify = "" + typeName.charAt(i);
    		if(!verify.matches("a|e|i|o|u|á|é|í|ó|ú|â|ê|ô|ã|A|E|I|O|U|Á|É|Í|Ó|Ú|Â|Ê|Ã| ")) {
    			if(verify.matches(" ")){
    				verify.toUpperCase();
    			}
    			prefix = prefix + verify;
    		}
    	}
    	return prefix;
    }
    
    public String createLabel(String typeName) {
    	return getPrefix(typeName) + incrementTypeAmount(typeName);
    }
    
    public void updateTypeStyle(String typeName, String style) {
    	int indexOfType = typeList.indexOf(typeName);
    	typeStyles.set(indexOfType, style);
    }
    
    public void updatePrefix(String typeName, String newPrefix) {
    	int indexOfType = typeList.indexOf(typeName);
    	prefixList.set(indexOfType, newPrefix);
    	
    }
    
    public String getTypeImagePath(String typeName) {
    	return typeImages.get(typeList.indexOf(typeName));
    }
    
    public String getTypeStyle(String typeName) {
    	System.out.println(typeName);
    	return typeStyles.get(typeList.indexOf(typeName));
    }
    
    public int getTypeAmount(String typeName) {
    	return typeAmounts.get(typeList.indexOf(typeName));
    }
    
    public int incrementTypeAmount(String typeName) {
    	int indexOfType = typeList.indexOf(typeName);
    	int oldValue = typeAmounts.get(indexOfType);
    	typeAmounts.set(indexOfType, oldValue+1);
    	return oldValue;
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
    
    public String getPrefix(String typeName) {
    	int indexOfType = typeList.indexOf(typeName);
    	return prefixList.get(indexOfType);
    }
    
    public String getStereoType(String typeName) {
    	int index = typeList.indexOf(typeName);
    	return stereoTypeList.get(index);
    }
	
}