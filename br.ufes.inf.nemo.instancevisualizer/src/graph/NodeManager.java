package graph;

import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.graph.Node;

import xml.Atom;
import xml.Field;
import xml.Sig;
import xml.XMLFile;

import RefOntoUML.Classifier;

public class NodeManager {
	private ArrayList<NodeM> nodeList;
	//private ArrayList<String> idList;
	//private ArrayList<ArrayList<Integer>> existanceList;
	//private ArrayList<ArrayList<String>> attrList;
	//private ArrayList<ArrayList<String>> valueList;
	
	//private LegendManager legendManager;
	
	public NodeManager(XMLFile xmlFile, NodeLegendManager nodeLegendManager) {
		nodeList = new ArrayList();
		//worldList = new ArrayList();
		//idList = new ArrayList();
		//existanceList = new ArrayList();
		//attrList = new ArrayList();
		//valueList = new ArrayList();
		
		//this.legendManager = legendManager
		ArrayList<String> alreadyAdded = new ArrayList();
		ArrayList<Atom> atomList = xmlFile.getAtomList();
		//for(Atom x:atomList)
		//System.out.println("ATOM: " + x.getLabel() +"; SIG: " + x.getSig().getLabel());
        ArrayList<Sig> sigList = xmlFile.getSigList();
        ArrayList<Field> fieldList = xmlFile.getFieldList();
        //String_$0
        int i;
        
        ArrayList<ArrayList<String>> tuplesList;
        ArrayList<String> tuple;
        ArrayList<ArrayList<Integer>> typesList;
        
        for(i=0; i<fieldList.size(); i++) {
        	if(fieldList.get(i).getLabel().equals("exists")) {
        		Field exists = fieldList.get(i);
		        for(i=0; i<exists.getTuples().size(); i++) {
		        	String worldLabel = exists.getTuple(i).get(0);
		        	String atomLabel = exists.getTuple(i).get(1);
		        	
		        	if(!alreadyAdded.contains(atomLabel)) {
		        		alreadyAdded.add(atomLabel);
		        		NodeM nodeM = new NodeM(atomLabel, xmlFile);
			        	String mainType = nodeM.getMainType(worldLabel);
			        	nodeM.addAttribute(worldLabel, "ui.label", nodeLegendManager.createLabel(mainType));
			        	nodeList.add(nodeM);
		        	}
		        }
		        break;
	        }
    	}
	}
	
	/*
	public void clearSpecificAttributes(String id) {
		int indexOfId = idList.indexOf(id);
		ArrayList<String> attrs = attrList.get(indexOfId);
		ArrayList<String> values = valueList.get(indexOfId);
		for(int i=0; i<attrs.size(); i++) {
			if(attrs.get(i).contains("\n")) {
				
			}
		}
	}
	*/
	public ArrayList<NodeM> nodesInWorld(String world) {
		ArrayList<NodeM> resultList = new ArrayList();
		for(NodeM n : nodeList) {
			if(n.existsInWorld(world))
				resultList.add(n);
		}
		return nodeList;
	}
	
	public ArrayList<NodeM> nodesToKill(String world0, String world1) {
		ArrayList<NodeM> toRemove = nodesInWorld(world0);
		ArrayList<NodeM> toMantain = nodesInWorld(world1);
        
		int limit = toRemove.size();
        for(int i=0; i<limit; i++) {
        	if(!toMantain.contains(toRemove.get(i))) {
        		System.out.println("Removed " + toRemove.get(i).getId());
        		toRemove.remove(toRemove.get(i));
        		limit--;
        		i--;
        	}
        }
        
        return toRemove;
	}
	
	public NodeM getNode(String nodeId) {
		for(NodeM n : nodeList) {
			if(n.getId().equals(nodeId)) {
				return n;
			}
		}
		System.out.println("ERROR - node not found: " + nodeId);
		return null;
		
	}
	
	public NodeM getNode(int i) {
		return nodeList.get(i);
	}
	
	public Iterator getNodeIterator() {
		return nodeList.iterator();
	}
	
	public int getNodeAmount() {
		return nodeList.size();
	}
}
