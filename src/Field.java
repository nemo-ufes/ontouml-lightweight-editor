/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import org.w3c.dom.NodeList;
/**
 *
 * @author Mauricio
 */
public class Field extends Sig {
    private ArrayList<ArrayList<String>> tuples;
    
    public Field(org.w3c.dom.Element node, ArrayList<Atom> atomList) {
        super(node);
        tuples = new ArrayList();
        if("field".compareTo(node.getNodeName()) != 0) {
            System.out.println("NOT A FIELD");
        }else{
            System.out.println("    IT'S A FIELD");
        }
        
        int i, j, k;
        ArrayList<String> aux;
        org.w3c.dom.Element aux2;
        NodeList aux3;
        String aux4;
        Atom aux5;
        NodeList tupleList = node.getElementsByTagName("tuple");
        
        for(i=0; i<tupleList.getLength(); i++) {
            System.out.println("NUM0");
            aux = new ArrayList();
            aux2 = (org.w3c.dom.Element) tupleList.item(i);
            aux3 = aux2.getElementsByTagName("atom");
            for(j=0; j<aux3.getLength(); j++) {
                System.out.println("CONTA");
                aux2 = (org.w3c.dom.Element) aux3.item(j);
                aux4 = aux2.getAttribute("label");
                aux.add(aux4);
                /*
                //System.out.println("    ".concat(aux2.getAttribute("label")));
                for(k=0; k<atomList.size(); k++) {
                    if(aux2.getAttribute("label").compareTo(atomList.get(k).getLabel()) == 0) {
                        //System.out.println(atomList.get(k).getLabel());
                        aux.add(atomList.get(k));
                    }
                    */
            }
            tuples.add(aux);
        }
    }

    public ArrayList<ArrayList<String>> getTuples() {
        return tuples;
    }

    public void setTuples(ArrayList<ArrayList<String>> tuples) {
        this.tuples = tuples;
    }
    
}
