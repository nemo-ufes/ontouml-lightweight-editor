package br.ufes.inf.nemo.instancevisualizer.graph;

import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.graph.Edge;

import br.ufes.inf.nemo.instancevisualizer.xml.XMLFile;

public class EdgeM {
	private String edgeId;
	private String node0Id;
	private String node1Id;
	private ArrayList<EdgeSettings> settingList;
	
	public EdgeM(String edgeId, String node0Id, String node1Id, XMLFile xmlFile) {
		this.edgeId = edgeId;
		this.node0Id = node0Id;
		this.node1Id = node1Id;
		
		settingList = new ArrayList();
		//System.out.println(edgeId + ": " + node0Id + "->" + node1Id);
		for(String world : xmlFile.getWorldsContainingFieldRelation(edgeId.substring(0, edgeId.indexOf('$')), node0Id, node1Id)) {
			settingList.add(new EdgeSettings(world, edgeId, xmlFile));
		}
		
	}
	/*
	public interface IFunction {
		public void execute(Object o);
	}
	
	public static void doFunc(ArrayList c, IFunction f) {
		for (Object o : c) {
			f.execute(o);
		}
	}
	*/
	
	public void setEdgeRef(String world, Edge edge) {
		EdgeSettings es = getWorld(world);
		if(es != null)
			es.setEdgeRef(edge);
	}
	
	public Edge getEdgeRef(String world) {
		EdgeSettings es = getWorld(world);
		if(es != null)
			return es.getEdgeRef();
		return null;
	}
	
	public void addAttribute(String world, String attr, String value) {
		for(EdgeSettings nodeSet : settingList) {
			if(nodeSet.isWorldId(world)) {
				nodeSet.addAttribute(attr, value);
				return;
			}
		}
		System.out.println("ERROR - world not found");
	}
	
	public Attribute getAttribute(String world, String attr) {
		for(EdgeSettings nodeSet : settingList) {
			if(nodeSet.isWorldId(world)) {
				return nodeSet.getAttribute(attr);
			}
		}
		System.out.println("ERROR - world not found");
		return null;
	}
	
	public Iterator<Attribute> getAttributeIterator(String world) {
		for(EdgeSettings nodeSet : settingList) {
			if(nodeSet.isWorldId(world)) {
				return nodeSet.getAttributeIterator();
			}
		}
		System.out.println("ERROR - world not found");
		return null;
	}
	
	public EdgeSettings getWorld(String world) {
		for(EdgeSettings ns : settingList) {
			if(ns.isWorldId(world)) {
				return ns;
			}
		}
		return null;
	}
	
	public boolean existsInWorld(String world) {
		if(getWorld(world) != null) {
			return true;
		}else{
			return false;
		}
	}
	
	public String getId() {
		return edgeId;
	}
	
	public String getTypeOnWorld(String world) {
		for(EdgeSettings nodeSet : settingList) {
			if(nodeSet.isWorldId(world)) {
				return nodeSet.getType();
			}
		}
		System.out.println("ERROR - world not found");
		return null;
	}

	public String getStereoTypeOnWorld(String world) {
		for(EdgeSettings nodeSet : settingList) {
			if(nodeSet.isWorldId(world)) {
				return nodeSet.getStereoType();
			}
		}
		System.out.println("ERROR - world not found");
		return null;
	}
	
	public boolean connects0(String nodeId) {
		if(node0Id.equals(nodeId))
			return true;
		else
			return false;
	}
	
	public boolean connects1(String nodeId) {
		if(node1Id.equals(nodeId))
			return true;
		else
			return false;
	}
	
	public String getNode1Id() {
		return node1Id;
	}
	
	public String getNode0Id() {
		return node0Id;
	}
}