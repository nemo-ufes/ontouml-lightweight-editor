package graph;

import java.util.ArrayList;

import xml.XMLFile;

public class TypeManager {
	private int edgeId;
	private ArrayList<String> typeList;
    private ArrayList<Integer> typeAmount;
    
    private ArrayList<String> edgeTypeList;
    private ArrayList<Integer> edgeTypeAmount;
    
    public TypeManager(XMLFile xmlFile) {
    	edgeId = 0;
    	typeList = new ArrayList();
    	typeAmount = new ArrayList();
    	edgeTypeList = new ArrayList();
    	edgeTypeAmount = new ArrayList();
    }
    
    public void addType(String typeName) {
    	typeList.add(typeName);
    	typeAmount.add(0);
    }
    
    /**
     * Creates a reduced label from an existing one by removing the vowels and spaces. If a space is found, the following character will be converted to upper case.
     * @param typeName
     * @return A reduced label.
     */
    public String createLabelOLD(String typeName) {
    	int id = typeAmount.get(typeList.indexOf(typeName));
    	if(typeName.length() <= 1) {
    		typeAmount.set(typeList.indexOf(typeName), id+1);
    		return typeName + id;
    	}
    	String newLabel = "" + typeName.charAt(0);
    	
    	for(int i=1; i<typeName.length(); i++) {
    		String verify = "" + typeName.charAt(i);
    		if(!verify.matches("a|e|i|o|u|á|é|í|ó|ú|â|ê|ô|ã|A|E|I|O|U|Á|É|Í|Ó|Ú|Â|Ê|Ã| ")) {
    			if(verify.matches(" ")){
    				verify.toUpperCase();
    			}
    			newLabel = newLabel + verify;
    		}
    	}
    	newLabel = newLabel + id;
    	typeAmount.set(typeList.indexOf(typeName), id+1);
    	return newLabel;
    }
    
    public int indexOfType(String typeName) {
    	return typeList.indexOf(typeName);
    }

	public int getEdgeId() {
		return edgeId;
	}

	public void setEdgeId(int edgeId) {
		this.edgeId = edgeId;
	}

	public ArrayList<String> getTypeList() {
		return typeList;
	}

	public void setTypeList(ArrayList<String> typeList) {
		this.typeList = typeList;
	}

	public ArrayList<Integer> getTypeAmount() {
		return typeAmount;
	}

	public void setTypeAmount(ArrayList<Integer> typeAmount) {
		this.typeAmount = typeAmount;
	}
    
}