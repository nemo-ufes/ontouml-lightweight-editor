package transformer;

import org.w3c.dom.*;

public interface Reader {
	
	//Sets the ID attribute for every element
	public void setID(Element element);
	
	public Element getModelElement(Document element);
	
	public NodeList getClasses(Document doc);
	public NamedNodeMap getProperties(Element elem);
	public NodeList getClassAttributes(Element elem);
	public NodeList getClassGeneralizations(Document doc, Element elem);
	
	public NodeList getAssociations(Document doc);
	
	public String getStereotype(Document doc, Element elem);
	
}
