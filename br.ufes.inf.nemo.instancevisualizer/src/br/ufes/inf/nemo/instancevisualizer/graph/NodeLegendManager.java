package br.ufes.inf.nemo.instancevisualizer.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.TypeName;
import br.ufes.inf.nemo.instancevisualizer.xml.Atom;
import br.ufes.inf.nemo.instancevisualizer.xml.XMLFile;

public class NodeLegendManager {
	
	private ArrayList<NodeLegend> legendList;
    
    public NodeLegendManager(OntoUMLParser ontoUmlParser, XMLFile xmlFile) {
    	legendList = new ArrayList();
        Iterator<Atom> atoms = xmlFile.getAtomList().iterator();
        ArrayList<Atom> worldList = xmlFile.getWorldList();
        
        int imageNo = 0;
    	File imageFolder = new File("." + File.separator + "resources" + File.separator + "gur project");
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
                    
                    if(!containsType(mainTypeName)) {
                    	// Getting stereotype:
                    	String stereotype = TypeName.getTypeName(mainTypeClassif);
                    	
                    	// Getting image file path:
                    	String imagePath = imageFolder.getPath() + File.separator + imageFolder.list()[imageNo];
                    	imageNo++;
                    	
                    	// Getting style:
                    	String style = "";
                    	
                    	legendList.add(new NodeLegend(mainTypeName, stereotype, imagePath, style, "oneWord"));
                    	
                    }
        		}
        	}
        }
    }
    
    public NodeLegend getLegendWithType(String type) {
    	for(NodeLegend nl : legendList) {
    		if(nl.getType().equals(type)){
    			return nl;
    		}
    	}
    	return null;
    }
    
    public void setLegendWithType(String type, NodeLegend nodeleg) {
    	for(int i=0; i<legendList.size(); i++) {
    		NodeLegend nl = legendList.get(i);
    		if(nl.getType().equals(type)){
    			legendList.set(i, nodeleg);
    			return;
    		}
    	}
    }
    
    public Iterator getLegendIterator() {
    	return legendList.iterator();
    }
    
    public boolean containsType(String type) {
    	for(NodeLegend legend : legendList) {
    		if(legend.getType().equals(type)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public String saveToString() {
    	String theme = "";
    	int i;
    	for(i=0; i<legendList.size(); i++) {
    		NodeLegend legend = legendList.get(i);
    		theme += legend.getType() + "$" + legend.getStereotype() + "$"
    				+ legend.getPrefix() + "$" + legend.getFillImage() + "$"
    				+ legend.getStyle() + "$" + legend.getAmount().toString() + "$";
    	}
    	theme = i + "$" + theme;
    	
    	return theme;
    }
    
    public void loadString(String theme) {
    	legendList = new ArrayList();
    	String[] types = theme.split("[$]");
    	for(int i=0; i<Integer.parseInt(types[0]); i++) {
    		String type = types[i*6+1];
    		String stereotype = types[i*6+2];
    		String prefix = types[i*6+3];
    		String image = types[i*6+4];
    		String style = types[i*6+5];
    		int amount = Integer.parseInt(types[i*6+6]);
    		legendList.add(new NodeLegend(type, stereotype, image, style, prefix));
    	}
    }
    
    public String createPrefix(String typeName) {
    	String prefix = "" + typeName.charAt(0);
    	for(int i=1; i<typeName.length(); i++) {
    		String verify = "" + typeName.charAt(i);
    		if(!verify.matches("a|e|i|o|u|�|�|�|�|�|�|�|�|�|A|E|I|O|U|�|�|�|�|�|�|�|�| ")) {
    			if(verify.matches(" ")){
    				verify.toUpperCase();
    			}
    			prefix = prefix + verify;
    		}
    	}
    	return prefix;
    }
    
    public void setVisible(String type, boolean visible) {
    	NodeLegend legend = getLegendWithType(type);
    	String style = legend.getStyle();
    	if(visible) {
    		int index = style.indexOf("\nvisibility-mode: hidden;");
    		if(index > 0) {
    			style = style.substring(0, index) + "\nvisibility-mode: normal;" + style.substring(index+25);
    		}else{
    			style += "\nvisibility-mode: normal;";
    		}
    		//legend.setStyle(style);
    	}else{
    		int index = style.indexOf("\nvisibility-mode: normal;");
    		if(index > 0) {
    			style = style.substring(0, index) + "\nvisibility-mode: hidden;" + style.substring(index+25);
    		}else{
    			style += "\nvisibility-mode: hidden;";
    		}
    		//legend.setStyle(style);
    	}
    }
}