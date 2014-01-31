package br.ufes.inf.nemo.instancevisualizer.xml;

import java.util.ArrayList;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class Field extends AlloyElement {
	
    private ArrayList<Tuple> xmlTuples;
    private ArrayList<ArrayList<Integer>> typeIdsList;
    private ArrayList<Sig> sigs;
    
    public Field(org.w3c.dom.Element node) {
        super(node);
        xmlTuples = new ArrayList<Tuple>();
        typeIdsList = new ArrayList<ArrayList<Integer>>();
        sigs = new ArrayList<Sig>();
        
        // Getting tuples:
        NodeList tupleList = node.getElementsByTagName("tuple");
        for(int i=0; i<tupleList.getLength(); i++) {
            Element xmlTuple = (org.w3c.dom.Element) tupleList.item(i);
            NodeList xmlAtomList = xmlTuple.getElementsByTagName("atom");
            xmlTuples.add(new Tuple(xmlAtomList));
        }
        
        // Getting types:
        NodeList typesList = node.getElementsByTagName("types");
        for(int i=0; i<typesList.getLength(); i++) {
        	ArrayList<Integer> type = new ArrayList<Integer>();
        	Element xmlTypes = (org.w3c.dom.Element) typesList.item(i);
            NodeList xmlTypeList = xmlTypes.getElementsByTagName("type");
            for(int j=0; j<xmlTypeList.getLength(); j++) {
            	Element xmlTuple = (org.w3c.dom.Element) xmlTypeList.item(j);
                int attrID = Integer.parseInt(xmlTuple.getAttribute("ID"));
                type.add(attrID);
            }
            typeIdsList.add(type);
            sigs.add(null);
        }
        
    }
    
    public final Atom findAtom(ArrayList<Atom> atomList, String label) {
        int i;
        for(i=0; i<atomList.size(); i++) {
            if(atomList.get(i).getLabel().equals(label)) {
                return atomList.get(i);
            }
        }
        return null;
    }
    
    public ArrayList<String> getExistingAtomsByWorld(Atom world) {
        if(!getLabel().equals("exists") || world.isWorld()) {
            System.out.println("This field is not a 'exist' field OR Given atom is not a world atom");
            return null;
        }
        ArrayList<String> atomList = new ArrayList<String>();
        for(int i=0; i<getTuples().size(); i++) {
            if(getTuple(i).get(0).equals(world)) {
                atomList.add(getTuple(i).get(1));
            }
        }
        return atomList;
    }

    public Tuple getTuple(int i) {
        try {
            return xmlTuples.get(i);
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public ArrayList<Tuple> getTuples() {
        return xmlTuples;
    }
    
    public ArrayList<ArrayList<Integer>> getTypeIdsList() {
    	return typeIdsList;
    }
}
