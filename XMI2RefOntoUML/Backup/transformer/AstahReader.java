package transformer;

import org.w3c.dom.*;

public class AstahReader implements Reader {

	final static String MODEL = "UML:Model";
    final static String ATTRIBUTES = "UML:Attribute";
    final static String CLASSES = "UML:Class";
    final static String ASSOCIATIONS = "UML:Association";
    final static String CLASS_INHERITANCES = "UML:Generalization";
    //final static String COUPLINGS = "UML:AssociationEnd";
    final static String STEREOTYPE = "UML:Stereotype";
    final static String VISIBILITY = "UML:ModelElement.visibility";
    
    //For the Element 'element', if it is an element node sets the xmi.id to be the ID
    public void setID(Element element) {
    	
	    for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
	    	if (child.getNodeType() == Node.ELEMENT_NODE) {
	    		if (((Element)child).hasAttribute("xmi.id")) {
	    			((Element)child).setIdAttribute("xmi.id", true);
	    		}
	    		setID((Element)child);
	    	}
	    }
    }
    
    // Retorna o elemento principal de um modelo UML, o UML:Model. Supõe que o XMI é bem formado e só possui
    // uma tag to tipo UML:Model
    public Element getModelElement(Document doc) {
    	NodeList model = doc.getElementsByTagName(MODEL);
		return (Element)model.item(0);
    }
    
    //Get all the Classes
	public NodeList getClasses(Document doc) {
		NodeList listClass = doc.getElementsByTagName(CLASSES);
		return listClass;
	}

	//Get the stereotype of the element node
	public String getStereotype(Document doc, Element elem) {
		
    	NodeList st = elem.getElementsByTagName(STEREOTYPE);
		Element stereotype = (Element) st.item(0);
			
		try {
			
			return doc.getElementById(stereotype.getAttribute("xmi.idref")).getAttribute("name");
			
		} catch (NullPointerException npe) {
    		System.out.println("Error: Stereotype is not defined or could not be found.");
    		return null;
    	}
	}

	//Get all the properties for the class (the ones defined in the metamodel)
	public NamedNodeMap getProperties(Element elem) {
		String visibility = ((Element)elem.getElementsByTagName(VISIBILITY).item(0)).getAttribute("xmi.value");
		elem.setAttribute("visibility", visibility);
		NamedNodeMap listProp = elem.getAttributes();
		// SE FOR COLLECTIVE PEGAR TAMBEM ISEXTENSIONAL (VER COMO FICA REGISTRADO NO XMI DO ASTAH)
		return listProp;
	}

	//Get all the Associations
	public NodeList getAssociations(Document doc) {
		NodeList listAssoc = doc.getElementsByTagName(ASSOCIATIONS);
//		for (int i = 0; i < listAssoc.getLength(); i++) {
//			if (!listAssoc.item(i).hasChildNodes()) {
//				listAssoc.item(i).getParentNode().removeChild(listAssoc.item(i));
//			}
//		}
		return listAssoc;
	}

	//Get all the attributes for the class (the ones defined by the user)
	public NodeList getClassAttributes(Element elem) {
		NodeList listAtt = elem.getElementsByTagName(ATTRIBUTES);
		return listAtt;
	}
    
	//Get all the Generalizations
	public NodeList getClassGeneralizations(Document doc, Element elem) {
		
		NodeList genlist = elem.getElementsByTagName(CLASS_INHERITANCES);
		for (int j=0; j < genlist.getLength(); j++) {
			Element genref = (Element) genlist.item(j);
			Element genele = (Element) doc.getElementById(genref.getAttribute("xmi.idref"));
			genref.getParentNode().replaceChild(genele, genref);
		}
		return genlist;
	}
}
