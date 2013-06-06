package obj;

import java.util.ArrayList;
import org.w3c.dom.NodeList;
/**
 *
 * @author Mauricio
 */
public class Field extends Sig {
	
    private ArrayList<ArrayList<String>> tuples;
    private ArrayList<ArrayList<Integer>> types;
    
    public Field(org.w3c.dom.Element node, ArrayList<Atom> atomList) {
        super(node);
        tuples = new ArrayList();
        types = new ArrayList();
        
        int i, j, k;
        ArrayList<String> tuple;
        ArrayList<Integer> type;
        org.w3c.dom.Element xmlTuple;
        org.w3c.dom.Element xmlTypes;
        org.w3c.dom.Element xmlTypeTuple;
        NodeList xmlAtomList;
        NodeList xmlTypeList;
        String attrLabel;
        int attrID;
        Atom atom;
        NodeList tupleList = node.getElementsByTagName("tuple");
        NodeList typesList = node.getElementsByTagName("types");
        
        System.out.println(getLabel());
        for(i=0; i<tupleList.getLength(); i++) {
            //System.out.println("NUM0");
            tuple = new ArrayList();
            xmlTuple = (org.w3c.dom.Element) tupleList.item(i);
            xmlAtomList = xmlTuple.getElementsByTagName("atom");
            System.out.println("    tuple:");
            for(j=0; j<xmlAtomList.getLength(); j++) {
                //System.out.println("CONTA");
                xmlTuple = (org.w3c.dom.Element) xmlAtomList.item(j);
                attrLabel = xmlTuple.getAttribute("label");
                //atom = findAtom(atomList, attrLabel);
                tuple.add(attrLabel);
                //System.out.println("        " + atom.getLabel());
                /*
                //System.out.println("    ".concat(aux2.getAttribute("label")));
                for(k=0; k<atomList.size(); k++) {
                    if(aux2.getAttribute("label").compareTo(atomList.get(k).getLabel()) == 0) {
                        //System.out.println(atomList.get(k).getLabel());
                        aux.add(atomList.get(k));
                    }
                    */
            }
            tuples.add(tuple);
        }
        
        for(i=0; i<typesList.getLength(); i++) {
        	type = new ArrayList();
            xmlTypes = (org.w3c.dom.Element) typesList.item(i);
            xmlTypeList = xmlTypes.getElementsByTagName("type");
            System.out.println("    types:");
            for(j=0; j<xmlTypeList.getLength(); j++) {
                xmlTuple = (org.w3c.dom.Element) xmlTypeList.item(j);
                attrID = Integer.parseInt(xmlTuple.getAttribute("ID"));
                type.add(attrID);
                //System.out.println("        " + attrID);
            }
            types.add(type);
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
        ArrayList<String> atomList = new ArrayList();
        int i, j, k;
        for(i=0; i<getTuples().size(); i++) {
            if(getTuple(i).get(0).equals(world)) {
                atomList.add(getTuple(i).get(1));
            }
        }
        return atomList;
    }

    public ArrayList<String> getTuple(int i) {
        try {
            return tuples.get(i);
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public ArrayList<ArrayList<String>> getTuples() {
        return tuples;
    }

    public void setTuples(ArrayList<ArrayList<String>> tuples) {
        this.tuples = tuples;
    }

	public ArrayList<ArrayList<Integer>> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<ArrayList<Integer>> types) {
		this.types = types;
	}
    
}
