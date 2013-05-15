/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import org.w3c.dom.*;

/**
 *
 * @author Mauricio
 */
public class Sig {
    private boolean builtin;
    private int id;
    private int parentID;
    private Sig parentSig;
    private String label;
    
    public Sig(org.w3c.dom.Element node) {
        if("sig".compareTo(node.getNodeName()) != 0) {
            System.out.println("NOT A SIG");
        }
        if("yes".compareTo(node.getAttribute("builtin")) == 0) {
            builtin = true;
        }else{
            builtin = false;
        }
        id = Integer.parseInt(node.getAttribute("ID"));
        String aux = node.getAttribute("parentID");
        if(aux.isEmpty()) {
            parentID = -1;
        }else{
            parentID = Integer.parseInt(node.getAttribute("parentID"));
        }
        label = node.getAttribute("label");
    }

    public boolean isBuiltin() {
        return builtin;
    }

    public void setBuiltin(boolean builtin) {
        this.builtin = builtin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public Sig getParentSig() {
        return parentSig;
    }

    public void setParentSig(Sig parentSig) {
        this.parentSig = parentSig;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
    
    //, ArrayList<Sig> sigList
    
    @Override
    public String toString() {
        return label;
    }
}
