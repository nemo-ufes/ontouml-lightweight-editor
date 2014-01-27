package br.ufes.inf.nemo.instancevisualizer.xml;

import java.util.ArrayList;
import org.w3c.dom.*;

/**
 *
 * @author Mauricio
 */
public class Sig {
    private boolean builtin;
    private int id;
    private int parentId;
    private Sig parentSig;
    private String label;
    
    public Sig(org.w3c.dom.Element node) {
        
        if("yes".compareTo(node.getAttribute("builtin")) == 0) {
            builtin = true;
        }else{
            builtin = false;
        }
        id = Integer.parseInt(node.getAttribute("ID"));
        String aux = node.getAttribute("parentID");
        if(aux.isEmpty()) {
            parentId = -1;
        }else{
            parentId = Integer.parseInt(node.getAttribute("parentID"));
        }
        label = node.getAttribute("label");
        
        parentSig = null;
    }

    public boolean isObejct() {
        if(getLabel().equals("this/Object")) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isProperty() {
        if(getLabel().equals("this/Property")) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isDataType() {
        if(getLabel().equals("this/DataType")) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isWorld() {
        if(getLabel().length() > 14) {
            //System.out.println(atomList.get(i).getLabel().substring(0, 15));
            if(getLabel().substring(0, 15).equals("world_structure")) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isObject() {
        if(getLabel().equals("this/Object")) {
            //System.out.println(atomList.get(i).getLabel().substring(0, 15));
            return true;
        }
        return false;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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
