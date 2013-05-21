package obj;

/**
 *
 * @author Mauricio
 */
public class Atom {
    private Sig sig;
    private String label;
    
    public Atom(org.w3c.dom.Element node, Sig sigVar) {
        sig = sigVar;
        label = node.getAttribute("label");
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
