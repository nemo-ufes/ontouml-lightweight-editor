package br.ufes.inf.nemo.instancevisualizer.graph;

import java.util.ArrayList;
import java.util.Iterator;

import br.ufes.inf.nemo.common.resource.TypeName;
import br.ufes.inf.nemo.instancevisualizer.xml.Atom;
import br.ufes.inf.nemo.instancevisualizer.xml.Field;
import br.ufes.inf.nemo.instancevisualizer.xml.Sig;
import br.ufes.inf.nemo.instancevisualizer.xml.Tuple;
import br.ufes.inf.nemo.instancevisualizer.xml.XMLFile;

public class EdgeManager {
	private ArrayList<EdgeM> edgeList;
	
	private EdgeLegendManager edgeLegendManager;
	
	public EdgeManager(XMLFile xmlFile, NodeManager nodeManager) {
		
		edgeList = new ArrayList();
		edgeLegendManager = new EdgeLegendManager(xmlFile);
		
		ArrayList<Atom> atomList = xmlFile.getAtomList();
        ArrayList<Sig> sigList = xmlFile.getSigList();
        ArrayList<Field> fieldList = xmlFile.getFieldList();
        
        int i, j, k;
        
        ArrayList<Tuple> tuplesList;
        Tuple tuple;
        ArrayList<ArrayList<Integer>> typesList;
        
        ArrayList<String> alreadyAdded = new ArrayList();

        for(i=0; i<fieldList.size(); i++) {
        	typesList = fieldList.get(i).getTypeIdsList();
            if(!fieldList.get(i).getTuples().isEmpty()) {
                if(fieldList.get(i).getTuples().get(0).size() > 2) {	// relation field has 3 atoms;
                	//if(!edgeLegendManager.getTypeList().contains(fieldList.get(i))) {
                		String typeName = fieldList.get(i).getLabel();
                		String stereotypeName = TypeName.getTypeName(xmlFile.getOntoUmlParser().getElement(typeName));
                		if(!stereotypeName.equals("Unknown Type")) {
                			edgeLegendManager.addDefaultLegend(typeName, stereotypeName);
                		}
                	//}
                	
                	
                    tuplesList = fieldList.get(i).getTuples();
                    
                    for(j=0; j<tuplesList.size(); j++) {
                    	tuple = tuplesList.get(j);
                    	String control = fieldList.get(i).getLabel() + "$" + tuple.get(1) + "$" + tuple.get(2);
                    	if(!alreadyAdded.contains(control)) {
                    		alreadyAdded.add(control);
                    		if(!xmlFile.findSigById(typesList.get(0).get(2)).isBuiltin() && !xmlFile.findSigById(typesList.get(0).get(2)).getLabel().equals("this/String_")) {
                           		String edgeName = fieldList.get(i) + "$" + j;
                           		edgeList.add(new EdgeM(edgeName, tuple.get(1), tuple.get(2), xmlFile));
                           		
                           	}else{
                           		nodeManager.getNode(tuple.get(1)).addDataType(tuple.get(0), fieldList.get(i).getLabel(), tuple.get(2));
                           		
                           		/*
                           		String attrAmount = nodeManager.getAttribute(tuple.get(1), world+"\nattrAmount") + "0";
                                int attrId = Integer.parseInt(attrAmount)/10;
                           		String attrName = "attr$";
                           		nodeManager.addAttribute(tuple.get(1), world+"\n"+attrName + attrId, fieldList.get(i).getLabel() + "\n" + tuple.get(2));
                           		nodeManager.addAttribute(tuple.get(1), world+"\nattrAmount", String.valueOf(attrId + 1));
                           		attrId++;
                           		*/
                           	}
                    	}
                    }
                }
            }else{
            	
            }
        }
        
	}
	
	public ArrayList<EdgeM> edgesInWorld(String world) {
		ArrayList<EdgeM> resultList = new ArrayList();
		for(EdgeM e : edgeList) {
			if(e.existsInWorld(world))
				resultList.add(e);
		}
		return resultList;
	}
	
	public ArrayList<EdgeM>[] edgesToKill(String world0, String world1) {
		ArrayList<EdgeM> toRemove = edgesInWorld(world0);
		ArrayList<EdgeM> toMantain = edgesInWorld(world1);
		
		int limit = toMantain.size();
        for(int i=0; i<limit; i++) {
        	if(toRemove.contains(toMantain.get(i))) {
        		toRemove.remove(toMantain.get(i));
        		toMantain.remove(i);
        		i--;
        		limit--;
        	}
        }
        
        ArrayList<EdgeM>[] result = new ArrayList[2];
        result[0] = toRemove;
        result[1] = toMantain;
        return result;
	}
	
	public ArrayList<EdgeM> getEdgesConnecting0(String nodeId) {
		ArrayList<EdgeM> connectin0 = new ArrayList();
		for(EdgeM e : edgeList) {
			if(e.connects0(nodeId))
				connectin0.add(e);
		}
		return connectin0;
	}
	
	public ArrayList<EdgeM> getEdgesConnecting1(String nodeId) {
		ArrayList<EdgeM> connectin1 = new ArrayList();
		for(EdgeM e : edgeList) {
			if(e.connects1(nodeId))
				connectin1.add(e);
		}
		return connectin1;
	}
	
	public Iterator<EdgeM> getEdgeIterator() {
		return edgeList.iterator();
	}
	
	public EdgeM getEdge(int i) {
		return edgeList.get(i);
	}
	
	public int getEdgeAmount() {
		return edgeList.size();
	}
	
	public EdgeLegendManager getEdgeLegendManager() {
		return edgeLegendManager;
	}

	public void setEdgeLegendManager(EdgeLegendManager edgeLegendManager) {
		this.edgeLegendManager = edgeLegendManager;
	}
	
	
	
}
