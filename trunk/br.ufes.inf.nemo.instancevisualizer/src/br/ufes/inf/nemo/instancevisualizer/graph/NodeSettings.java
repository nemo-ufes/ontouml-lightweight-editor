package br.ufes.inf.nemo.instancevisualizer.graph;

import java.util.ArrayList;
import java.util.Iterator;

import br.ufes.inf.nemo.instancevisualizer.xml.XMLFile;

import br.ufes.inf.nemo.common.resource.TypeName;

public class NodeSettings {
	private String worldId;
	private ArrayList<String> typeList;	// index 0 is the main type;
	private ArrayList<String> stereoTypeList;	// index 0 is the main type;
	private ArrayList<DataType> dataTypeList;
	private ArrayList<Attribute> attrList;
	
	public NodeSettings(String worldId, String nodeId, XMLFile xmlFile) {	//true - node; false - edge
		this.worldId = worldId;
		typeList = xmlFile.getAtomClassifiersTypeOnWorld(nodeId, worldId);
		stereoTypeList = xmlFile.getAtomStereoTypesOnWorld(nodeId, worldId);
		dataTypeList = new ArrayList();
		attrList = new ArrayList();
		
		String aux = typeList.get(0);
		String mainType = xmlFile.getAtomMainType(nodeId, worldId).getName();
		String mainStereoType = TypeName.getTypeName(xmlFile.getAtomMainType(nodeId, worldId));
		//System.out.println(nodeId + mainType + typeList);
		int oldMainTypeIndex = typeList.indexOf(mainType);
		if(oldMainTypeIndex != -1) {
			typeList.set(oldMainTypeIndex, typeList.get(0));
			stereoTypeList.set(oldMainTypeIndex, stereoTypeList.get(0));
			typeList.set(0, mainType);
			stereoTypeList.set(0, mainStereoType);
		}
	}
	
	public void addAttribute(String attr, String value) {
		for(Attribute a : attrList) {
			if(a.hasName(attr)) {
				a.setValue(value);
				return;
			}
		}
		attrList.add(new Attribute(attr, value));
	}
	
	public Attribute getAttribute(String attr) {
		for(Attribute a : attrList) {
			if(a.hasName(attr)) {
				return a;
			}
		}
		System.out.println("ERROR - couldn't find attribute: " + attr);
		return null;
	}
	
	public Iterator<Attribute> getAttributeIterator() {
		return attrList.iterator();
	}
	
	public boolean isWorldId(String world) {
		if(worldId.equals(world)) {
			return true;
		}
			return false;
	}
	
	public void addDataType(String dataTypeName, String dataTypeValue) {
		dataTypeList.add(new DataType(dataTypeName, dataTypeValue));
	}
	
	public Iterator getDataTypeListIterator() {
		return dataTypeList.iterator();
	}
	
	public String getWorldId() {
		return this.worldId;
	}
	
	public String getType(int index) {
		return typeList.get(index);
	}
	
	public String getStereoType(int index) {
		return stereoTypeList.get(index);
	}
	
	//public remove("")
}
