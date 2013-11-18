package br.ufes.inf.nemo.xmi2refontouml.framework;

import java.util.Map;

import RefOntoUML.Comment;
import RefOntoUML.Element;
import RefOntoUML.RefOntoUMLFactory;
import br.ufes.inf.nemo.xmi2refontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2refontouml.util.ElementType;

public abstract class XMI2RefElement
{
	protected static RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
	
	protected static ElementMap elemMap = new ElementMap();

	protected Object XMIElement;
	
	protected Element RefOntoUMLElement;
	
	protected Mapper Mapper;
	
	protected Map<String, Object> hashProp;
	
	protected void commonTasks() throws Exception
	{
		if (Mapper.getID(XMIElement) == "")
		{
        	String error = "XMI file is invalid. Element with no ID found.\n" +
        			"Element Type: " + RefOntoUMLElement.getClass() + ".\n" +
        			"Element Name: " + hashProp.get("Name") + ".\n" +
        			"Element Path: " + Mapper.getPath(XMIElement);
        	throw new Exception(error);
        }
		else
			elemMap.put(Mapper.getID(XMIElement), this);
		
		deal();
		createSubElements();
	}
	
	protected abstract void deal();
	
	public abstract void dealReferences();
	
	protected void createSubElements() throws Exception
	{
        for (Object comment : Mapper.getElements(XMIElement, ElementType.COMMENT))
        {
        	XMI2RefComment xmi2refcomm = new XMI2RefComment(comment, Mapper);
	        RefOntoUMLElement.getOwnedComment().add((Comment) xmi2refcomm.getRefOntoUMLElement());
        }
	}
	
	public static ElementMap getElemMap() {
		return elemMap;
	}
	
	public Object getXMIElement() {
		return XMIElement;
	}

	public void setXMIElement(Object XMIElement) {
		this.XMIElement = XMIElement;
	}

	public Element getRefOntoUMLElement() {
		return RefOntoUMLElement;
	}

	public void setRefOntoUMLElement(Element refOntoUMLElement) {
		this.RefOntoUMLElement = refOntoUMLElement;
	}
}
