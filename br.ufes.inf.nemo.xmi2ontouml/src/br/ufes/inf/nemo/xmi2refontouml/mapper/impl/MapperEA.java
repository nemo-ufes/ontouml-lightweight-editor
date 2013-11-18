package br.ufes.inf.nemo.xmi2refontouml.mapper.impl;

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
import br.ufes.inf.nemo.xmi2refontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2refontouml.util.ElementType;
import br.ufes.inf.nemo.xmi2refontouml.util.OntoUMLError;
import br.ufes.inf.nemo.xmi2refontouml.util.XMLDOMUtil;


public class MapperEA implements Mapper {
	
	// FIXED NAMESPACES
	private final String XMLNS = "http://www.w3.org/2000/xmlns/";
	private final String UMLNS = "http://schema.omg.org/spec/UML/2.1";
	private final String XMINS = "http://schema.omg.org/spec/XMI/2.1";
	private String EAUML = "http://www.sparxsystems.com/profiles/EAUML/1.0";
	
	// VARIABLE NAMESPACES
	private String OntoUML;
	
	Document doc;
	
	Map<String, Element> stereotypes = new HashMap<String, Element>();
	
	List<String> ignoredElements = new ArrayList<String>();
	
	public MapperEA(String inputPath) throws Exception {
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
	
	private void preProcessXMI(Element element)
	{
		List<Element> childList = XMLDOMUtil.getElementChilds(element);
	    for (Element child : childList)
	    {
    		setID(child);
    		
    		populateStereotypesMap(child);
    		
    		removeNonUMLElements(child);
    		
    		separateAssociationClass(child);
    		
    		preProcessXMI(child);
	    }
	}
	
	private void setID(Element element)
	{
		if (element.hasAttributeNS(XMINS, "id"))
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
		if (element.getNodeName().equals("element") && element.hasAttributeNS(XMINS, "idref"))
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
	
	private void separateAssociationClass(Element element)
	{
		if (element.getNodeName().equals("extendedProperties") && element.hasAttribute("associationclass"))
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
					XMLDOMUtil.removeElement(childElem);
				}
			}
			
			//Process Association part
			assocClassElemClone.setAttributeNS(XMINS, "type", "uml:Association");
			assocClassElemClone.setAttributeNS(XMINS, "id", 
					((Element)element.getParentNode()).getAttributeNS(XMINS, "idref"));
			assocClassElemClone.setAttribute("name", ((Element)element.getParentNode()).getAttribute("name"));
			assocClassElemClone.setAttribute("relator", assocClassElem.getAttributeNS(XMINS, "id"));
			for (Element childElem : XMLDOMUtil.getElementChilds(assocClassElemClone))
			{
				if (childElem.getNodeName().equals("ownedAttribute") || 
						childElem.getNodeName().equals("generalization"))
				{
					XMLDOMUtil.removeElement(childElem);
				}
				else if (childElem.hasAttributeNS(XMINS, "id"))
				{
					childElem.setIdAttributeNS(XMINS, "id", true);
				}
			}
			//For EA version 10, since it does not export the stereotype of the AssociationClass' Association
			Element new_st = doc.createElementNS(OntoUML, "Material");
			new_st.setAttribute("base_Association", assocClassElemClone.getAttributeNS(XMINS, "id"));
			stereotypes.put(assocClassElemClone.getAttributeNS(XMINS, "id"), new_st);
			
			assocClassElem.setIdAttributeNS(XMINS, "id", true);
			assocClassElemClone.setIdAttributeNS(XMINS, "id", true);
		}
	}

	@Override
	public Object getModelElement() {
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
		
		switch (type) {
			case ANNOTATION:
				if (getType(elem) == ElementType.ASSOCIATION)
				{
					return Arrays.asList(getAnnotations(elem).toArray());
				}
				break;
			case ASSOCIATION:
				elemList = XMLDOMUtil.getElementChildsByType(elem, ElementType.ASSOCIATION, this);
				break;
			case CLASS:
				elemList = XMLDOMUtil.getElementChildsByType(elem, ElementType.CLASS, this);
				break;
			case COMMENT:
				if (getType(elem) != ElementType.COMMENT)
				{
					createDescriptionNode(elem);
				}
				elemList = XMLDOMUtil.getElementChildsByType(elem, ElementType.COMMENT, this);
				break;
			case DATATYPE:
				elemList = XMLDOMUtil.getElementChildsByType(elem, ElementType.DATATYPE, this);
				break;
			case DEPENDENCY:
				elemList = XMLDOMUtil.getElementChildsByType(elem, ElementType.DEPENDENCY, this);
				break;
			case ENUMERATION:
				elemList = XMLDOMUtil.getElementChildsByType(elem, ElementType.ENUMERATION, this);
				break;
			case ENUMLITERAL:
				elemList = XMLDOMUtil.getElementChildsByType(elem, ElementType.ENUMLITERAL, this);
				break;
			case GENERALIZATION:
				if (getType(elem) != ElementType.ASSOCIATION)
				{
					elemList = XMLDOMUtil.getElementChildsByType(elem, ElementType.GENERALIZATION, this);
				}
				break;
			case GENERALIZATIONSET:
				elemList = XMLDOMUtil.getElementChildsByType(elem, ElementType.GENERALIZATIONSET, this);
				break;
			case PACKAGE:
				elemList = XMLDOMUtil.getElementChildsByType(elem, ElementType.PACKAGE, this);
				break;
			case PRIMITIVE:
				Element model = (Element) getModelElement();
				if (elem == model) {
					elemList = getPrimitives();
				}
				elemList.addAll(XMLDOMUtil.getElementChildsByType(elem, ElementType.PRIMITIVE, this));
				break;
			case PROPERTY:
				elemList = XMLDOMUtil.getElementChildsByType(elem, ElementType.PROPERTY, this);
				break;
			default:
				break;
		}
		
		return Arrays.asList(elemList.toArray());
	}
	
	private List<String> getAnnotations(Element elem)
	{
		return new ArrayList<String>();
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
    		reverseComposition(hashProp, elem);
    		List<Element> childList = XMLDOMUtil.getElementChilds(elem);
        	for (Element childElem : childList) {
        		if (childElem.getNodeName().equals("type")) {
        			if (childElem.hasAttribute("href"))
        				hashProp.put("type", childElem.getAttribute("href").split("#")[1]);
        			else
        				hashProp.put("type", childElem.getAttributeNS(XMINS, "idref"));
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
    		List<String> memberEnds = new ArrayList<String>();
    		for (Element memberEnd : memberEndsAux) {
    			memberEnds.add(memberEnd.getAttributeNS(XMINS, "idref"));
    		}
    		hashProp.put("memberend", memberEnds);
    		
    	} else if (getType(elem) == ElementType.GENERALIZATIONSET) {
    		List<Element> generalizationsAux = XMLDOMUtil.getElementChildsByTagName(elem, "generalization");
    		List<String> generalizations = new ArrayList<String>();
    		for (Element generalization : generalizationsAux) {
    			generalizations.add(generalization.getAttributeNS(XMINS, "idref"));
    		}
    		hashProp.put("generalization", generalizations);
    		
    	} else if (getType(elem) == ElementType.COMMENT) {
    		List<Element> commentsAux = XMLDOMUtil.getElementChildsByTagName(elem, "annotatedElement");
    		List<String> comments = new ArrayList<String>();
    		for (Element comment : commentsAux) {
    			comments.add(comment.getAttributeNS(XMINS, "idref"));
    		}
    		hashProp.put("annotatedelement", comments);
    	}
    	
		return hashProp;
	}
	
	private void reverseComposition(HashMap<String, Object> hashProp, Element elem) {
		String aggregation = elem.getAttribute("aggregation");
		if (aggregation.equals("composite") || aggregation.equals("shared")) {
			hashProp.put("aggregation", "none");
			
		} else if (aggregation.equals("none")) {
			Element packagedAssoc = (Element) doc.getElementById(elem.getAttribute("association"));
			if (packagedAssoc == null)
			{
				packagedAssoc = (Element) elem.getParentNode();
			}
			List<Element> memberEnds = XMLDOMUtil.getElementChildsByTagName(packagedAssoc, "memberEnd");
			for (Element memberEnd : memberEnds) {
				Element ownedEnd = doc.getElementById(memberEnd.getAttributeNS(XMINS, "idref"));
				String aggregation2 = ownedEnd.getAttribute("aggregation");
				if (!ownedEnd.equals(elem) && 
						aggregation2.equals("shared") ||
						aggregation2.equals("composite")) {
					hashProp.put("aggregation", aggregation2);
				}
			}
		}
	}

	@Override
	public String getID(Object element) {
		Element elem = (Element) element;
		if (getType(elem) == ElementType.MODEL) {
			return "Model_ID_"+elem.getAttribute("name");
		}
		return elem.getAttributeNS(XMINS, "id");
	}

	@Override
	public String getName(Object element) {
		Element elem = (Element) element;
		if (elem.getNodeName().equals("diagram")) {
			elem = XMLDOMUtil.getFirstAppearanceOf(elem, "properties");
		}
		return elem.getAttribute("name");
	}

	@Override
	public ElementType getType(Object element) {
		Element elem = (Element) element;
		return ElementType.get(elem.getAttributeNS(XMINS, "type").replace("uml:", ""));
	}
	
	@Override
    public String getPath(Object element) {
		String elementPath = getName(element);
		for (Node elem = (Node) element; getType(elem) != ElementType.MODEL; elem = elem.getParentNode()) {
			if (elem instanceof Element && 
					(getType(elem) == ElementType.CLASS ||
					getType(elem) == ElementType.ASSOCIATION ||
					getType(elem) == ElementType.PACKAGE)) {
				elementPath = getName(elem) + " -> " + elementPath;
			}
		}
    	return elementPath;
    }

	@Override
	public Object getElementById(String id) {
		if (id.contains("Model_ID_")) {
			return getModelElement();
		}
		return doc.getElementById(id);
	}

	@Override
	public String getRelatorfromMaterial(Object element) {
		return ((Element)element).getAttribute("relator");
	}

	@Override
	public List<Object> getDiagramList() {
		List<Element> diagramList = new ArrayList<Element>();
		NodeList diagrams = doc.getElementsByTagName("diagrams");
		if (diagrams != null) {
			for (Node child = diagrams.item(0).getFirstChild(); child != null; child = child.getNextSibling()) {
	    		if (child instanceof Element) {
	    			Element properties = XMLDOMUtil.getChild((Element)child, "properties");
	    			if (!properties.getAttribute("type").equals("Package") &&
	    					!properties.getAttribute("type").equals("Custom"))
	    			{
	    				diagramList.add((Element)child);
	    			}
	    		}
			}
		}
		Object[] diagramArray = diagramList.toArray();
		Arrays.sort(diagramArray, new EAComparator());
		return Arrays.asList(diagramArray);
	}

	@Override
	public List<String> getDiagramElements(Object diagram) throws Exception {
		List<String> diagElemIDList = new ArrayList<String>();
		Element elements = XMLDOMUtil.getFirstAppearanceOf((Element)diagram, "elements");
		if (elements != null) {
			List<Element> diagElemList = XMLDOMUtil.getElementChilds(elements);
	
			for (Object diagElem : diagElemList) {
				if (this.ignoredElements.contains(((Element)diagElem).getAttribute("subject")))
					continue;
				if (getElementById(((Element)diagElem).getAttribute("subject")) == null) {
					throw new Exception("XMI file is invalid. Reference to a inexistent element " +
							"in diagram " + ((Element)((Element)diagram).getElementsByTagName
							("properties").item(0)).getAttribute("name") + " found.");
				}
				Element elem = doc.getElementById(((Element)diagElem).getAttribute("subject"));
				ElementType type = getType(elem);
				if (type == ElementType.CLASS ||
						type == ElementType.ASSOCIATION ||
						type == ElementType.DATATYPE ||
						type == ElementType.PRIMITIVE ||
						type == ElementType.ENUMERATION)
				{
					diagElemIDList.add(((Element)diagElem).getAttribute("subject"));
					if (elem.hasAttribute("relator"))
					{
						diagElemIDList.add("derivation" + elem.getAttribute("relator"));
					}
				}
			}
		}
		
		return diagElemIDList;
	}
	
	class EAComparator implements Comparator<Object> {

		@Override
		public int compare(Object arg0, Object arg1) {
			Element elem1 = (Element) arg0;
			Element elem2 = (Element) arg1;
			
			String name1 = getName(elem1);
			String name2 = getName(elem2);
			
			return name1.compareToIgnoreCase(name2);
		}
		
	}

}
