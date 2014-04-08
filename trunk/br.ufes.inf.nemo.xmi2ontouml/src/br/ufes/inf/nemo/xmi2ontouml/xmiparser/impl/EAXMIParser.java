package br.ufes.inf.nemo.xmi2ontouml.xmiparser.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.ufes.inf.nemo.xmi2ontouml.Creator;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;
import br.ufes.inf.nemo.xmi2ontouml.util.OntoUMLError;
import br.ufes.inf.nemo.xmi2ontouml.util.XMLDOMUtil;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;


public class EAXMIParser implements XMIParser {
	
	// FIXED NAMESPACES
	private final String XMLNS = "http://www.w3.org/2000/xmlns/";
	private final String UMLNS = "http://schema.omg.org/spec/UML/2.1";
	private final String XMINS = "http://schema.omg.org/spec/XMI/2.1";
	private String EAUML = "http://www.sparxsystems.com/profiles/EAUML/1.0";
	
	// VARIABLE NAMESPACES
	private String OntoUML;
	
	Document doc;
	
	Map<String, Element> stereotypes = new HashMap<String, Element>();
	
	Map<String, Element> model2diagram = new HashMap<String, Element>();
	
	List<String> ignoredElements = new ArrayList<String>();
	
	public EAXMIParser(String inputPath) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        
        doc = docBuilder.parse(new File(inputPath));
        
        Element root = doc.getDocumentElement();
        NamedNodeMap docAttributes = root.getAttributes();
        for (int i = 0; i < docAttributes.getLength(); i++) {
        	if (docAttributes.item(i).getNamespaceURI().equals(XMLNS)) {
        		String nsName = docAttributes.item(i).getNodeName();
        		String nsValue = docAttributes.item(i).getNodeValue();
        		if (nsName.equalsIgnoreCase("xmlns:uml") &&
        				!nsValue.equals(UMLNS)) {
        			//Mediator.errorLog += "Unsuported version of UML Namespace";
        			throw new Exception("Unsuported version of UML Namespace");
        		}
        		if (nsName.equalsIgnoreCase("xmlns:xmi") &&
        				!nsValue.equals(XMINS)) {
        			//Mediator.errorLog += "Unsuported version of XMI Namespace";
        			throw new Exception("Unsuported version of XMI Namespace");
        		}
        		if (nsName.equalsIgnoreCase("xmlns:OntoUML")) {
        			OntoUML = docAttributes.item(i).getNodeValue();
        		}
        	}
        }
        
        preProcessXMI(doc.getDocumentElement());
	}
	
	//######## Start pre-processing methods ########
	private void preProcessXMI(Element element)
	{
		List<Element> childList = XMLDOMUtil.getElementChilds(element);
	    for (Element child : childList)
	    {
    		setID(child);
    		
    		populateStereotypesMap(child);
    		
    		removeNonUMLElements(child);
    		
    		reverseAggregation(child);
    		
    		separateAssociationClass(child);
    		
    		preProcessXMI(child);
	    }
	}
	
	private void setID(Element element)
	{
		if (element.hasAttributeNS(XMINS, "id") && !element.getNodeName().equals("Association"))
		{
			if (doc.getElementById(element.getAttributeNS(XMINS, "id")) == null)
				element.setIdAttributeNS(XMINS, "id", true);
			
			// Threats the case when EA exports twice the same Class, one with 'name' and one without
			else
			{
				Element e = doc.getElementById(element.getAttributeNS(XMINS, "id"));
				if (!e.hasAttribute("name") && element.hasAttribute("name"))
				{
					e.setAttribute("name", element.getAttribute("name"));
					for (Element child : XMLDOMUtil.getElementChilds(element))
					{
						e.appendChild(child);
						setID(child);
					}
					XMLDOMUtil.removeElement(element);
				}
			}
    	}
	}
	
	private void populateStereotypesMap(Element element)
	{
		if (element.getNamespaceURI() == null ||
				element.getNamespaceURI().equals(XMINS) ||
				element.getNamespaceURI().equals(UMLNS) ||
				element.getNamespaceURI().equals(UMLNS+"/") ||
				element.getNamespaceURI().equals(EAUML))
		{
			return;
		}
		if (element.getNamespaceURI().equals(OntoUML))
		{
			if (element.hasAttribute("base_Class"))
			{
				stereotypes.put(element.getAttribute("base_Class"), element);
			}
			if (element.hasAttribute("base_Association"))
			{
				stereotypes.put(element.getAttribute("base_Association"), element);
			}
		}
		else
		{
			Creator.warningLog += OntoUMLError.wrongStereotype(element);
		}
	}
	
	private void removeNonUMLElements(Element element)
	{
		// If it is a diagram element, adds to model2diagram map
		if ((element.getTagName().equals("element") || element.getTagName().equals("connector") || element.getTagName().equals("attribute")) &&
				element.hasAttributeNS(XMINS, "idref") && doc.getElementById(element.getAttributeNS(XMINS, "idref")) != null)
		{
			model2diagram.put(element.getAttributeNS(XMINS, "idref"), element);
			
			if (element.getNodeName().equals("element"))
			{
				String type = element.getAttributeNS(XMINS, "type");
				if (type.equals("uml:UMLDiagram") || type.equals("uml:Text") || type.equals("uml:Boundary"))
				{
					XMLDOMUtil.removeElement(doc.getElementById(element.getAttributeNS(XMINS, "idref")));
					if (!ignoredElements.contains(element.getAttributeNS(XMINS, "idref")))
					{
						ignoredElements.add(element.getAttributeNS(XMINS, "idref"));
					}
				}
			}
		}
	}
	
	private void reverseAggregation(Element element)
	{
		if (element.getNodeName().equals("type") && element.hasAttribute("aggregation") &&
				(element.getAttribute("aggregation").equals("composite") ||
				element.getAttribute("aggregation").equals("shared")))
		{
			String aggType = element.getAttribute("aggregation");
			Element connectorElem = (Element) element.getParentNode().getParentNode();
			Element assocElem = doc.getElementById(connectorElem.getAttributeNS(XMINS, "idref"));
			if (assocElem == null)
			{
				Element extProp = XMLDOMUtil.getFirstAppearanceOf(connectorElem, "extendedProperties");
				assocElem = doc.getElementById(extProp.getAttribute("associationclass"));
			}
			
			for (Element ownedEnd : XMLDOMUtil.getElementChildsByTagName(assocElem, "ownedEnd"))
			{
				if (ownedEnd.getAttribute("aggregation").equals("none"))
					ownedEnd.setAttribute("aggregation", aggType);
				else
					ownedEnd.setAttribute("aggregation", "none");
			}
		}
	}
	
	private void separateAssociationClass(Element element)
	{
		if (element.getNodeName().equals("extendedProperties") && element.hasAttribute("associationclass") &&
				XMLDOMUtil.getFirstAppearanceOf((Element)element.getParentNode(), "properties").hasAttribute("stereotype"))
		{
			Element assocClassElem = doc.getElementById(element.getAttribute("associationclass"));
			Element assocClassElemClone = (Element) assocClassElem.cloneNode(true);
			assocClassElem.getParentNode().appendChild(assocClassElemClone);
			
			//process Class part
			assocClassElem.setAttributeNS(XMINS, "type", "uml:Class");
			for (Element childElem : XMLDOMUtil.getElementChilds(assocClassElem))
			{
				if (childElem.getNodeName().equals("memberEnd") || childElem.getNodeName().equals("ownedEnd"))
				{
//					System.out.println("removing " + childElem.getNodeName());
					XMLDOMUtil.removeElement(childElem);
				}
			}
			
			//Process Association part
			assocClassElemClone.setAttributeNS(XMINS, "type", "uml:Association");
			assocClassElemClone.setAttributeNS(XMINS, "id", 
					((Element)element.getParentNode()).getAttributeNS(XMINS, "idref"));
			assocClassElemClone.setAttribute("name", ((Element)element.getParentNode()).getAttribute("name"));
			assocClassElemClone.setAttribute("relator", assocClassElem.getAttributeNS(XMINS, "id"));
			//EA sometimes export twice the same end
			eliminateDuplicateEnds(assocClassElemClone);
			
			for (Element childElem : XMLDOMUtil.getElementChilds(assocClassElemClone))
			{
				if (childElem.getNodeName().equals("ownedAttribute") || 
						childElem.getNodeName().equals("generalization"))
				{
					XMLDOMUtil.removeElement(childElem);
				}
				else if (childElem.hasAttributeNS(XMINS, "id"))
					childElem.setIdAttributeNS(XMINS, "id", true);
			}
			//For EA version 10, since it does not export the stereotype of the AssociationClass' Association
			Element new_st = doc.createElementNS(OntoUML, "Material");
			new_st.setAttribute("base_Association", assocClassElemClone.getAttributeNS(XMINS, "id"));
			stereotypes.put(assocClassElemClone.getAttributeNS(XMINS, "id"), new_st);
			
			assocClassElem.setIdAttributeNS(XMINS, "id", true);
			assocClassElemClone.setIdAttributeNS(XMINS, "id", true);
		}
	}
	
	private void eliminateDuplicateEnds(Element elem)
	{
		List<Element> ownedEnds = XMLDOMUtil.getElementChildsByTagName(elem, "ownedEnd");
		if (ownedEnds.size() > 2)
		{
			List<String> excludedTypes = new ArrayList<String>();
			Map<String, Element> nodes = new HashMap<String, Element>();
			for (Element ownedEnd : ownedEnds)
			{
				String typeIdRefChild = XMLDOMUtil.getFirstAppearanceOf(ownedEnd, "type").getAttributeNS(XMINS, "idref");
				if (!nodes.containsKey(typeIdRefChild))
					nodes.put(typeIdRefChild, ownedEnd);
				
				else if (!excludedTypes.contains(typeIdRefChild))
				{
					for (Element memberEnd : XMLDOMUtil.getElementChildsByTagName(elem, "memberEnd"))
					{
						if (memberEnd.getAttributeNS(XMINS, "idref").equals(nodes.get(typeIdRefChild).getAttributeNS(XMINS, "id")))
						{
							XMLDOMUtil.removeElement(memberEnd);
							break;
						}
					}
					XMLDOMUtil.removeElement(nodes.get(typeIdRefChild));
					excludedTypes.add(typeIdRefChild);
				}
			}
		}
	}
	//######## End pre-processing methods ########
	
	@Override
	public Object getRoot() {
		return doc.getElementsByTagNameNS(UMLNS, "Model").item(0);
	}

	@Override
	public String getStereotype(Object element) {
		Element elem = (Element) element;
		String stereotype;
		
		Element stereotypeElem = stereotypes.get(elem.getAttributeNS(XMINS, "id"));
    	if (stereotypeElem != null) {
    		stereotype = stereotypeElem.getNodeName().replace("OntoUML:", "");
    		
    	} else {
			String type = elem.getAttributeNS(XMINS, "type").replace("uml:", "");
			if (ElementType.get(type) == ElementType.CLASS ||
					ElementType.get(type) == ElementType.ASSOCIATION) {
				stereotype = "";	
			} else {
				stereotype = type;
			}
		}
		
		return stereotype;
	}

	@Override
	public List<Object> getElements(Object element, ElementType type) {
		List<Element> elemList = new ArrayList<Element>();
		Element elem = (Element) element;
		
		switch (type)
		{
			case ASSOCIATION:
				elemList = XMLDOMUtil.getElementChildsByType(elem, "uml:Association");
				break;
			case CLASS:
				elemList = XMLDOMUtil.getElementChildsByType(elem, "uml:Class");
				break;
			case COMMENT:
				if (getType(elem) != ElementType.COMMENT)
				{
					createDescriptionNode(elem);
				}
				elemList = XMLDOMUtil.getElementChildsByType(elem, "uml:Comment");
				break;
			case CONSTRAINT:
				elemList = XMLDOMUtil.getElementChildsByType(elem, "uml:Constraint");
				
				Element eaElem = model2diagram.get(elem.getAttributeNS(XMINS, "id"));
				if (eaElem != null)
				{
					Element constraints = XMLDOMUtil.getFirstAppearanceOf(eaElem, "constraints");
					constraints = (constraints == null ? XMLDOMUtil.getFirstAppearanceOf(eaElem, "Constraints") : constraints);
					
					if (constraints != null)
						for (Element e : XMLDOMUtil.getElementChilds(constraints))
						{
							e.setAttributeNS(XMINS, "type", "uml:Constraint");
							elemList.add(e);
						}
				}
				break;
			case DATATYPE:
				elemList = XMLDOMUtil.getElementChildsByType(elem, "uml:DataType");
				break;
			case DEPENDENCY:
				elemList = XMLDOMUtil.getElementChildsByType(elem, "uml:Dependency");
				break;
			case DIAGRAM:
				NodeList diagrams = doc.getElementsByTagName("diagrams");
				if (diagrams != null)
				{
					for (Element diag : XMLDOMUtil.getElementChildsByTagName((Element) diagrams.item(0), "diagram"))
					{
		    			Element properties = XMLDOMUtil.getChild(diag, "properties");
		    			if (!properties.getAttribute("type").equals("Package") &&
		    					!properties.getAttribute("type").equals("Custom"))
		    			{
		    				elemList.add(diag);
		    			}
					}
				}
				Element[] diagramArray = elemList.toArray(new Element[]{});
				Arrays.sort(diagramArray, new EAComparator());
				elemList = Arrays.asList(diagramArray);
				break;
			case DIAGRAMELEMENT:
				Element elements = XMLDOMUtil.getFirstAppearanceOf(elem, "elements");
				if (elements != null)
				{
					for (Element diagElem : XMLDOMUtil.getElementChilds(elements))
					{
						if (!ignoredElements.contains(diagElem.getAttribute("subject")))
						{
							if (doc.getElementById(diagElem.getAttribute("subject")) == null)
							{
								System.err.println("Reference to a inexistent element " +
										"in diagram " + ((Element)elem.getElementsByTagName
										("properties").item(0)).getAttribute("name") + " found.");
								continue;
							}
							else
							{
								elemList.add(diagElem);
//								if (doc.getElementById(diagElem.getAttribute("subject")).hasAttribute("relator"))
//									elemList.add("derivation" + elem.getAttribute("relator"));
							}
						}
					}
				}
				break;
			case ENUMERATION:
				elemList = XMLDOMUtil.getElementChildsByType(elem, "uml:Enumeration");
				break;
			case ENUMLITERAL:
				elemList = XMLDOMUtil.getElementChildsByType(elem, "uml:EnumerationLiteral");
				break;
			case GENERALIZATION:
				if (getType(elem) != ElementType.ASSOCIATION)
				{
					elemList = XMLDOMUtil.getElementChildsByType(elem, "uml:Generalization");
				}
				break;
			case GENERALIZATIONSET:
				elemList = XMLDOMUtil.getElementChildsByType(elem, "uml:GeneralizationSet");
				break;
			case PACKAGE:
				elemList = XMLDOMUtil.getElementChildsByType(elem, "uml:Package");
				break;
			case PRIMITIVE:
				Element model = (Element) getRoot();
				if (elem == model) {
					elemList = getPrimitives();
				}
				elemList.addAll(XMLDOMUtil.getElementChildsByType(elem, "uml:PrimitiveType"));
				break;
			case PROPERTY:
				elemList = XMLDOMUtil.getElementChildsByType(elem, "uml:Property");
				if (elemList.size() == 2)
				{
					if (elemList.get(0).getAttributeNS(XMINS, "id").startsWith("EAID_dst"))
						elemList.add(1, elemList.remove(0));
				}
				break;
			default:
				break;
		}
		
		return Arrays.asList(elemList.toArray());
	}
	
	private List<Element> getPrimitives() {
		List<Element> elemList = new ArrayList<Element>();
		NodeList primPackList = doc.getElementsByTagName("primitivetypes");
		if (primPackList.getLength() != 0) {
			NodeList primList = ((Element)primPackList.item(0)).getElementsByTagName("packagedElement");
			if (primList.getLength() != 0) {
				for (int i = 0; i < primList.getLength(); i++) {
					Node child = primList.item(i);
					if (child instanceof Element && getType(child) == ElementType.PRIMITIVE) {
						elemList.add((Element)child);
					}
				}
			}
		}
		return elemList;
	}
	
	private void createDescriptionNode(Element elem) {
		Element elements = XMLDOMUtil.getFirstAppearanceOf((Element)doc.getElementsByTagNameNS(XMINS, "Extension").item(0), "elements");
		if (elements != null) {
			List<Element> elemList = XMLDOMUtil.getElementChilds(elements);
	
			for (Element element : elemList) {
				Element properties = XMLDOMUtil.getFirstAppearanceOf(element, "properties");
				if (element.getAttributeNS(XMINS, "idref").equals(elem.getAttributeNS(XMINS, "id")) &&
						properties != null &&
						properties.getAttribute("documentation") != "") {
					
					Element description = doc.createElement("ownedComment");
					description.setAttributeNS(XMINS, "id", elem.getAttributeNS(XMINS, "id") + "_description");
					description.setIdAttributeNS(XMINS, "id", true);
					description.setAttributeNS(XMINS, "type", "uml:Comment");
					description.setAttribute("body", "Definition="+properties.getAttribute("documentation"));
					elem.appendChild(description);
					
				}
			}
		}
	}

	@Override
	public Map<String, Object> getProperties(Object element) {
		Element elem = (Element) element;
		
		HashMap<String, Object> hashProp = new HashMap<String, Object>();
		
		NamedNodeMap listProp = elem.getAttributes();
    	for (int i = 0; i < listProp.getLength(); i++) {
    		hashProp.put(listProp.item(i).getNodeName().toLowerCase(), listProp.item(i).getNodeValue());
    	}
    	
    	// Tagged Values
    	Element stereotypeElem = stereotypes.get(elem.getAttributeNS(XMINS, "id"));
    	if (stereotypeElem != null) {
    		NamedNodeMap tagList = stereotypeElem.getAttributes();
    		if (tagList.getLength() > 1) {
    			for (int i = 0; i < tagList.getLength(); i++) {
    				hashProp.put(tagList.item(i).getNodeName().toLowerCase(),
    						tagList.item(i).getNodeValue());
    			}
    		}
    	}
    	
    	// Specific Properties
    	if (getType(elem) == ElementType.PROPERTY) {
//    		reverseComposition(hashProp, elem);
    		List<Element> childList = XMLDOMUtil.getElementChilds(elem);
        	for (Element childElem : childList) {
        		if (childElem.getNodeName().equals("type")) {
        			if (childElem.hasAttribute("href"))
        				hashProp.put("type", childElem.getAttribute("href").split("#")[1]);
        			else
        				hashProp.put("type", doc.getElementById(childElem.getAttributeNS(XMINS, "idref")));
            	} else if (childElem.getNodeName().equals("lowerValue")) {
            		hashProp.put("lower", childElem.getAttribute("value"));
            	} else if (childElem.getNodeName().equals("upperValue")) {
            		hashProp.put("upper", childElem.getAttribute("value"));
            	} else if (childElem.getNodeName().equals("defaultValue")) {
            		hashProp.put("default", childElem.getAttribute("value"));
            	}
        	}
        	
    	} else if (getType(elem) == ElementType.ASSOCIATION)
    	{
    		List<Element> memberEndsAux = XMLDOMUtil.getElementChildsByTagName(elem, "memberEnd");
    		List<Element> memberEnds = new ArrayList<Element>();
    		for (Element memberEnd : memberEndsAux)
    		{
    			if (memberEnd.getAttributeNS(XMINS, "idref").startsWith("EAID_src"))
    				memberEnds.add(0, doc.getElementById(memberEnd.getAttributeNS(XMINS, "idref")));
    			else
    				memberEnds.add(doc.getElementById(memberEnd.getAttributeNS(XMINS, "idref")));
    		}
    		if (elem.hasAttribute("relator"))
    			hashProp.put("relator", doc.getElementById(elem.getAttribute("relator")));
    		hashProp.put("memberend", memberEnds);
    		
    	} else if (getType(elem) == ElementType.GENERALIZATION)
    	{
    		Element general = doc.getElementById(elem.getAttribute("general"));
    		hashProp.put("general", general);
    		
    	} else if (getType(elem) == ElementType.GENERALIZATIONSET) {
    		List<Element> generalizationsAux = XMLDOMUtil.getElementChildsByTagName(elem, "generalization");
    		List<Element> generalizations = new ArrayList<Element>();
    		for (Element generalization : generalizationsAux) {
    			generalizations.add(doc.getElementById(generalization.getAttributeNS(XMINS, "idref")));
    		}
    		hashProp.put("generalization", generalizations);
    		
    	} else if (getType(elem) == ElementType.COMMENT) {
    		List<Element> commentsAux = XMLDOMUtil.getElementChildsByTagName(elem, "annotatedElement");
    		List<Element> comments = new ArrayList<Element>();
    		for (Element comment : commentsAux) {
    			comments.add(doc.getElementById(comment.getAttributeNS(XMINS, "idref")));
    		}
    		hashProp.put("annotatedelement", comments);
    		
    	} else if (getType(elem) == ElementType.CONSTRAINT) {
    		List<Element> constraints = new ArrayList<Element>();
    		String body = new String();
    		if (elem.getTagName().equals("ownedRule"))
    		{
	    		List<Element> constraintsAux = XMLDOMUtil.getElementChildsByTagName(elem, "constrainedElement");
	    		for (Element constraint : constraintsAux)
	    		{
	    			constraints.add(doc.getElementById(constraint.getAttributeNS(XMINS, "idref")));
	    		}
	    		
	    		Element e = XMLDOMUtil.getFirstAppearanceOf(elem, "specification");
	    		if (e != null)
	    			body = e.getAttribute("body");
    		}
    		else
    		{
    			constraints.add(doc.getElementById(
    					((Element)elem.getParentNode().getParentNode()).getAttributeNS(XMINS, "idref")));
    			if (elem.hasAttribute("notes"))
    				body = elem.getAttribute("notes");
    			if (elem.hasAttribute("description"))
    				body = elem.getAttribute("description");
    		}
    		
    		hashProp.put("constrainedelement", constraints);
    		hashProp.put("body", body);
    	}
    	else if (getType(elem) == ElementType.DIAGRAM)
    	{
    		hashProp.put("name", XMLDOMUtil.getFirstAppearanceOf(elem, "properties").getAttribute("name"));
    	}
    	else if (getType(elem) == ElementType.DIAGRAMELEMENT)
    	{
    		hashProp.put("subject", doc.getElementById(elem.getAttribute("subject")));
    	}
    	
		return hashProp;
	}

	private ElementType getType(Object element)
	{
		Element elem = (Element) element;
		if (elem.getNodeName().equals("diagram"))
			return ElementType.DIAGRAM;
		else if (elem.getNodeName().equals("element") && 
				elem.getParentNode().getParentNode().getNodeName().equals("diagram"))
			return ElementType.DIAGRAMELEMENT;
		return ElementType.get(elem.getAttributeNS(XMINS, "type").replace("uml:", ""));
	}
	
	class EAComparator implements Comparator<Object> {

		@Override
		public int compare(Object arg0, Object arg1) {
			Element elem1 = (Element) arg0;
			Element elem2 = (Element) arg1;
			
			String name1 = XMLDOMUtil.getFirstAppearanceOf(elem1, "properties").getAttribute("name");
			String name2 = XMLDOMUtil.getFirstAppearanceOf(elem2, "properties").getAttribute("name");
			
			return name1.compareToIgnoreCase(name2);
		}
	}
	
//	private void reverseComposition(HashMap<String, Object> hashProp, Element elem) {
//	String aggregation = elem.getAttribute("aggregation");
//	if (aggregation.equals("composite") || aggregation.equals("shared")) {
//		hashProp.put("aggregation", "none");
//		
//	} else if (aggregation.equals("none")) {
//		Element packagedAssoc = (Element) doc.getElementById(elem.getAttribute("association"));
//		if (packagedAssoc == null)
//		{
//			packagedAssoc = (Element) elem.getParentNode();
//		}
//		List<Element> memberEnds = XMLDOMUtil.getElementChildsByTagName(packagedAssoc, "memberEnd");
//		for (Element memberEnd : memberEnds) {
//			Element ownedEnd = doc.getElementById(memberEnd.getAttributeNS(XMINS, "idref"));
//			String aggregation2 = ownedEnd.getAttribute("aggregation");
//			if (!ownedEnd.equals(elem) && 
//					aggregation2.equals("shared") ||
//					aggregation2.equals("composite")) {
//				hashProp.put("aggregation", aggregation2);
//			}
//		}
//	}
}
