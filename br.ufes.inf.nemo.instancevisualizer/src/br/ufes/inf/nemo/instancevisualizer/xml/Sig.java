package br.ufes.inf.nemo.instancevisualizer.xml;

import java.util.ArrayList;

public class Sig extends AlloyElement {
    
	private ArrayList<Atom> atomList;
	
    public Sig(org.w3c.dom.Element node) {
        super(node);
        atomList = new ArrayList<Atom>();
    }

    public boolean isObject() {
        if(getLabel().equals("this/Object"))
            return true;
        else
            return false;
    }
    
    public boolean isProperty() {
        if(getLabel().equals("this/Property"))
            return true;
        else
            return false;
    }
    
    public boolean isDataType() {
        if(getLabel().equals("this/DataType"))
            return true;
        else
            return false;
    }
    
    public boolean isWorld() {
        if(getLabel().length() > 14) {
            if(getLabel().substring(0, 15).equals("world_structure"))
                return true;
        }
        return false;
    }
    
    public void addAtom(Atom atom) {
    	atomList.add(atom);
    }
    
    public void getAtom(int i) {
    	atomList.get(i);
    }
    
    public int getAtomAmount() {
    	return atomList.size();
    }
}
