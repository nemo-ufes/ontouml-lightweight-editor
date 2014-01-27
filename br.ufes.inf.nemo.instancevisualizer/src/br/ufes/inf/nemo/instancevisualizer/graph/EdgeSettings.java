package br.ufes.inf.nemo.instancevisualizer.graph;

import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.graph.Edge;

import br.ufes.inf.nemo.instancevisualizer.xml.XMLFile;

public class EdgeSettings {
	private String worldId;
	private String type;
	private String stereoType;
	private ArrayList<Attribute> attrList;
	private Edge edgeRef;
	
	public EdgeSettings(String worldId, String edgeId, XMLFile xmlFile) {	//true - node; false - edge
		this.worldId = worldId;
		type = edgeId.substring(0, edgeId.indexOf('$'));
		stereoType = xmlFile.getFieldStereotype(edgeId.substring(0, edgeId.indexOf('$')));
		attrList = new ArrayList();
		edgeRef = null;
		
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
	
	public String getWorldId() {
		return this.worldId;
	}

	public String getType() {
		return type;
	}

	public String getStereoType() {
		return stereoType;
	}
	
	public Edge getEdgeRef() {
		return edgeRef;
	}
	
	public void setEdgeRef(Edge edgeRef) {
		this.edgeRef = edgeRef; 
	}
	
	//public String getType(int index) {
	//	return typeList.get(index);
	//}
	
	//public remove("")
}
