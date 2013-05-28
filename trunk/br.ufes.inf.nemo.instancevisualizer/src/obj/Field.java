package obj;

import java.util.ArrayList;
import org.w3c.dom.NodeList;
/**
 *
 * @author Mauricio
 */
public class Field extends Sig {
    private ArrayList<ArrayList<Atom>> tuples;
    
    public Field(org.w3c.dom.Element node, ArrayList<Atom> atomList) {
        super(node);
        tuples = new ArrayList();
        /*
        if("field".compareTo(node.getNodeName()) != 0) {
            System.out.println("NOT A FIELD");
        }else{
            System.out.println("    IT'S A FIELD");
        }
        */
        int i, j, k;
        ArrayList<Atom> tuple;
        org.w3c.dom.Element xmlTuple;
        NodeList xmlAtomList;
        String aux4;
        Atom atom;
        NodeList tupleList = node.getElementsByTagName("tuple");
        
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
                aux4 = xmlTuple.getAttribute("label");
                atom = findAtom(atomList, aux4);
                tuple.add(atom);
                System.out.println("        " + atom.getLabel());
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
    
    public ArrayList<Atom> getExistingAtomsByWorld(Atom world) {
        if(!getLabel().equals("exists") || world.isWorld()) {
            System.out.println("This field is not a 'exist' field OR Given atom is not a world atom");
            return null;
        }
        ArrayList<Atom> atomList = new ArrayList();
        int i, j, k;
        for(i=0; i<getTuples().size(); i++) {
            if(getTuple(i).get(0).equals(world)) {
                atomList.add(getTuple(i).get(1));
            }
        }
        return atomList;
    }

    public ArrayList<Atom> getTuple(int i) {
        try {
            return tuples.get(i);
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public ArrayList<ArrayList<Atom>> getTuples() {
        return tuples;
    }

    public void setTuples(ArrayList<ArrayList<Atom>> tuples) {
        this.tuples = tuples;
    }
     
}
