package br.ufes.inf.nemo.instancevisualizer.xml;

public abstract class AlloyElement {
	private boolean builtin;
    private int id;
    private int parentId;
    private AlloyElement parent;
    private String label;
    
    public AlloyElement(org.w3c.dom.Element node) {
        if("yes".compareTo(node.getAttribute("builtin")) == 0) {
            builtin = true;
        }else{
            builtin = false;
        }
        id = Integer.parseInt(node.getAttribute("ID"));
        String hasParent = node.getAttribute("parentID");
        if(hasParent.isEmpty()) {
            parentId = -1;
        }else{
            parentId = Integer.parseInt(node.getAttribute("parentID"));
        }
        label = node.getAttribute("label");
        
        parent = null;
    }
    
    public boolean isBuiltin() {
        return builtin;
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public AlloyElement getParent() {
        return parent;
    }

    public void setParent(AlloyElement parent) {
        this.parent = parent;
    }

    public String getLabel() {
        return label;
    }
    
    @Override
    public String toString() {
        return label;
    }
}
