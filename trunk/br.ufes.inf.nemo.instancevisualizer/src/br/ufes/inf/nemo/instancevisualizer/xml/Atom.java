package br.ufes.inf.nemo.instancevisualizer.xml;

public class Atom {
    private Sig sig;
    private String label;
    
    public Atom(org.w3c.dom.Element node, Sig sigVar) {
        sig = sigVar;
        label = node.getAttribute("label");
        
    }
    /*
    public Atom findAtom(Array label) {
        int i;
        for(i=0; i<atomList.size(); i++) {
            if(atomList.get(i).getLabel().equals(label)) {
                return atomList.get(i);
            }
        }
        return null;
    }
    */
    public boolean hasLabel(String labelVar) {
        if(label.equals(labelVar)) {
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
    
    public String getWorldType() {
        if(isWorld()) {
            switch (sig.getLabel()) {
                case "world_structure/CurrentWorld":
                    return "Current";
                case "world_structure/PastWorld":
                    return "Past";
                case "world_structure/FutureWorld":
                    return "Future";
                case "world_structure/CounterfactualWorld":
                    return "Counterfactual";
                case "world_structure/TemporalWorld":
                    return "Temporal";
                default:
                    System.out.println("THIS AIN'T SUPPOSED TO HAPPEN");
                    System.exit(0);
                    break;
            }
        }
        return "NAW";
    }
    
    public boolean isObject() {
        return sig.isObject();
    }
    
    public boolean isProperty() {
        return sig.isProperty();
    }
    
    public boolean isDataType() {
        return sig.isDataType();
    }

    public Sig getSig() {
        return sig;
    }

    public void setSig(Sig sig) {
        this.sig = sig;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
}
