package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.Map;

import RefOntoUML.Comment;
import RefOntoUML.Element;
import RefOntoUML.RefOntoUMLFactory;
import br.ufes.inf.nemo.xmi2ontouml.Creator;
import br.ufes.inf.nemo.xmi2ontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;

public abstract class XMI2RefElement
{
	protected static RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
	
	protected static ElementMap elemMap = new ElementMap();

	protected Object XMIElement;
	
	protected Element RefOntoUMLElement;
	
	protected Mapper Mapper;
	
	protected Map<String, Object> hashProp;
	
	protected static boolean ignoreErrorElements;
	
	protected void commonTasks() throws Exception
	{
		if (Mapper.getID(XMIElement) == "" && !(this instanceof XMI2RefConstraint))
		{
        	String error = "Element with no ID found.\n" +
        			"Element Type: " + RefOntoUMLElement.getClass() + ".\n" +
        			"Element Name: " + hashProp.get("Name") + ".\n" +
        			"Element Path: " + Mapper.getPath(XMIElement)+".\n\n";
        	
        	if (ignoreErrorElements)
        	{
        		System.out.println(error);
        		Creator.warningLog += error;
        		this.RefOntoUMLElement = null;
        		return;
        	}
        	else
        		throw new Exception(error);
        }
		else
			elemMap.put(Mapper.getID(XMIElement), this);
		
		deal();
		createSubElements();
	}
	
	protected abstract void deal();
	
	public abstract void dealReferences() throws Exception;
	
	protected void createSubElements() throws Exception
	{
        for (Object comment : Mapper.getElements(XMIElement, ElementType.COMMENT))
        {
        	XMI2RefComment xmi2refcomm = new XMI2RefComment(comment, Mapper);
        	if (xmi2refcomm.RefOntoUMLElement != null)
        	{
        		RefOntoUMLElement.getOwnedComment().add((Comment) xmi2refcomm.getRefOntoUMLElement());
        	}
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

	public static boolean isIgnoreErrorElements() {
		return ignoreErrorElements;
	}

	public static void setIgnoreErrorElements(boolean ignoreErrorElements) {
		XMI2RefElement.ignoreErrorElements = ignoreErrorElements;
	}
}
