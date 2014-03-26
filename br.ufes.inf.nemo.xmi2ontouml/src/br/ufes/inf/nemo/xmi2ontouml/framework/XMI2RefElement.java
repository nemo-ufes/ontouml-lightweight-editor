package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.Map;

import RefOntoUML.Comment;
import RefOntoUML.Element;
import RefOntoUML.RefOntoUMLFactory;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public abstract class XMI2RefElement
{
	protected static RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
	
	protected static ElementMap elemMap = new ElementMap();

	protected Object XMIElement;
	
	protected Element RefOntoUMLElement;
	
	protected XMIParser Mapper;
	
	protected Map<String, Object> hashProp;
	
	protected static boolean ignoreErrorElements = true;
	
	private static boolean importComments = false;
	
	public XMI2RefElement() {}
	
	public XMI2RefElement(Object XMIElement, XMIParser mapper)
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		elemMap.put(XMIElement, this);
		
		if ((hashProp.get("xmi:id") == null && hashProp.get("id") == null) && 
				!(this instanceof XMI2RefConstraint) &&
				!(this instanceof XMI2RefModel) &&
				!(this instanceof XMI2RefDiagramElement))
		{
			System.err.println("Element with no ID found.\n"+
					"Element Name: " +hashProp.get("name") + "\n"+
					"Type: "+this.getClass() + "\n"+
					"Node: "+XMIElement);
		}
	}
	
	public XMI2RefElement(Object XMIElement, XMIParser mapper, Element refOntoUMLElement)
	{
		this(XMIElement, mapper);
		
		this.RefOntoUMLElement = refOntoUMLElement;
		
		deal();
	}
	
	protected abstract void deal();
	
	public abstract void dealReferences() throws Exception;
	
	protected void createSubElements() throws Exception
	{
		if (isImportComments())
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

	public static boolean isImportComments() {
		return importComments;
	}

	public static void setImportComments(boolean importComments) {
		XMI2RefElement.importComments = importComments;
	}
}
