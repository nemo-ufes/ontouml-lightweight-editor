package br.ufes.inf.nemo.instancevisualizer.graph;

import java.util.ArrayList;
import java.util.Iterator;

import br.ufes.inf.nemo.instancevisualizer.xml.XMLFile;

public class NodeM {
	private String nodeId;
	private ArrayList<NodeSettings> worldList;
	
	public NodeM(String nodeId, XMLFile xmlFile) {
		this.nodeId = nodeId;
		worldList = new ArrayList();
		for(String world : xmlFile.getWorldsContainingAtom(nodeId)) {
			worldList.add(new NodeSettings(world, nodeId, xmlFile));
		}
	}
	
	public String getMainType(String world) {
		//System.out.println("WORLD IS " + world);
		for(NodeSettings nodeSet : worldList) {
			//System.out.println("CHECKING " + nodeSet.getWorldId());
			if(nodeSet.isWorldId(world))
				return nodeSet.getType(0);
		}
		return null;
	}
	
	public String getMainStereoType(String world) {
		//System.out.println("WORLD IS " + world);
		for(NodeSettings nodeSet : worldList) {
			//System.out.println("CHECKING " + nodeSet.getWorldId());
			if(nodeSet.isWorldId(world))
				return nodeSet.getStereoType(0);
		}
		return null;
	}
	
	public void addAttribute(String world, String attr, String value) {
		for(NodeSettings nodeSet : worldList) {
			if(nodeSet.isWorldId(world)) {
				nodeSet.addAttribute(attr, value);
				return;
			}
		}
		System.out.println("ERROR - world not found");
	}
	
	public Attribute getAttribute(String world, String attr) {
		for(NodeSettings nodeSet : worldList) {
			if(nodeSet.isWorldId(world)) {
				return nodeSet.getAttribute(attr);
			}
		}
		System.out.println("ERROR - world not found");
		return null;
	}
	
	public Iterator<Attribute> getAttributeIterator(String world) {
		for(NodeSettings nodeSet : worldList) {
			if(nodeSet.isWorldId(world)) {
				return nodeSet.getAttributeIterator();
			}
		}
		System.out.println("ERROR - world not found");
		return null;
	}
	
	public void addDataType(String world, String dataTypeName, String dataTypeValue) {
		for(NodeSettings ns : worldList) {
			if(ns.isWorldId(world)) {
				ns.addDataType(dataTypeName, dataTypeValue);
			}
		}
	}
	
	public Iterator getDataTypeIterator(String world) {
		for(NodeSettings ns : worldList) {
			if(ns.isWorldId(world)) {
				return ns.getDataTypeListIterator();
			}
		}
		System.out.println("ERROR - dataTypeList iterator not found on world: " + world);
		return null;
	}
	
	public NodeSettings getWorld(String world) {
		for(NodeSettings ns : worldList) {
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
	
	public ArrayList<NodeM> getNeighbors(EdgeManager em) {
		
		return null;
	}
	
	public ArrayList<NodeM> getNeighborsOfStereoType(String sType, String world, NodeManager nm, EdgeManager em) {
		ArrayList<NodeM> nodeList = new ArrayList();
		
		Iterator<EdgeM> edges = em.getEdgesConnecting0(nodeId).iterator();
		while(edges.hasNext()) {
			EdgeM edge = edges.next();
			NodeM node1 = nm.getNode(edge.getNode1Id());
			if(sType.equals(node1.getMainStereoType(world)))
				nodeList.add(node1);
		}
		
		edges = em.getEdgesConnecting1(nodeId).iterator();
		while(edges.hasNext()) {
			EdgeM edge = edges.next();
			NodeM node0 = nm.getNode(edge.getNode0Id());
			if(sType.equals(node0.getMainStereoType(world)))
				nodeList.add(node0);
		}
		
		return nodeList;
	}
	
	public String getId() {
		return nodeId;
	}
}