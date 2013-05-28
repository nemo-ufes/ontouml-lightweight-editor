/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public class AtomList extends ArrayList<Atom>{

    public AtomList() {
        super();
    }
    
    public int containAtomWithLabel(String label) {
        int i;
        for(i=0; i<this.size(); i++) {
            if(this.get(i).getLabel().equals(label)) {
                return i;
            }
        }
        return -1;
    }
}
