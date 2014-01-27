package br.ufes.inf.nemo.instancevisualizer.graph;

import java.util.ArrayList;

public class TypeManager {
	private ArrayList<String> typeList = new ArrayList();
	private ArrayList<String> stereotypeList = new ArrayList();
	
	public TypeManager() {
		typeList = new ArrayList();
		stereotypeList = new ArrayList();
	}
	
	public void addType(String type, String stereotype) {
		typeList.add(type);
		stereotypeList.add(stereotype);
	}
	
	public String getTypeS(String stereotype) {
		int index = stereotypeList.indexOf(stereotype); 
		if(index < 0) {
			return "Unknown Type";
		}
		return typeList.get(index);
	}
	
	public String getStereotypeT(String type) {
		int index = typeList.indexOf(type); 
		if(index < 0) {
			return "Unknown Type";
		}
		return stereotypeList.get(index);
	}
	
}