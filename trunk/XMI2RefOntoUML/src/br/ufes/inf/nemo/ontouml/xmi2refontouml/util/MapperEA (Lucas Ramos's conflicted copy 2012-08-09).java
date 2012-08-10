package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import br.ufes.inf.nemo.ontouml.xmi2refontouml.transformation.Mediator;


public class MapperEA implements Mapper {
	
	// FIXED NAMESPACES
	private final String XMLNS = "http://www.w3.org/2000/xmlns/";
	private final String UMLNS = "http://schema.omg.org/spec/UML/2.1";
	private final String XMINS = "http://schema.omg.org/spec/XMI/2.1";
	
	// VARIABLE NAMESPACES
	private String OntoUML;
//	private String EAUML;
	
	Document doc;
	
	public MapperEA(String inputPath) {
		try {
	        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        docBuilderFactory.setNamespaceAware(true);
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        
	        doc = docBuilder.parse(new File(inputPath));
	        
	        Element root = doc.getDocumentElement();
	        NamedNodeMap docAttributes = root.getAttributes();
	        for (int i = 0; i < docAttributes.getLength(); i++) {
	        	if (docAttributes.item(i).getNamespaceURI().equals(XMLNS) &&
	        			!docAttributes.item(i).getNodeName().equals("xmlns:uml") &&
	        			!docAttributes.item(i).getNodeName().equals("xmlns:xmi")) {
	        		if (docAttributes.item(i).getNodeName().equalsIgnoreCase("OntoUML")) {
	        			OntoUML = docAttributes.item(i).getNamespaceURI();
	        		} else {
	        			System.out.println("tem outro");
	        			System.out.println(docAttributes.item(i).getNodeName());
	        		}
	        	}
	        }

            setID(doc.getDocumentElement());
	        
		} catch (SAXParseException err) {
            Mediator.errorLog += "** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId();
            Mediator.errorLog += " " + err.getMessage();
 
		} catch (SAXException e) {
			Exception x = e.getException();
			Mediator.errorLog += ((x == null) ? e : x).getMessage();
			
		} catch (IOException e) {
			Mediator.errorLog += "File " + inputPath + 
			" does not exist or could not be oppened.";
			
		} catch (ParserConfigurationException e) {
			Mediator.errorLog += e.getMessage();
		}
	}
	
    protected void setID(Element element) {
    	
	    for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
	    	if (child.getNodeType() == Node.ELEMENT_NODE) {
	    		if (((Element)child).hasAttributeNS(XMINS, "id")) {
	    			((Element)child).setIdAttributeNS(XMINS, "id", true);
	    		}
	    		setID((Element)child);
	    	}
	    }
    }

	@Override
	public Object getModelElement() {
		return doc.getElementsByTagNameNS(UMLNS, "Model").item(0);
	}

	@Override
	public String getStereotype(Object element) {
		Element elem = (Element) element;
		
		String type = elem.getAttributeNS(XMINS, "type");
		
		Element stereotypeElem = getElementByBaseRef(elem);
		if (stereotypeElem != null) {
	    	return stereotypeElem.getNodeName();
		} else {
			return type.replace("uml:", "");
		}
	}
	
	private Element getElementByBaseRef(Element elem) {
		String type = elem.getAttributeNS(XMINS, "type");
		
		NodeList stereotypeList = doc.getElementsByTagNameNS(OntoUML, "*");
		
		if (stereotypeList.getLength() != 0) {
    		for (int i=0; i < stereotypeList.getLength(); i++) {
    			Element stereotypeElem = (Element) stereotypeList.item(i);
    			String elemID = elem.getAttributeNS(XMINS, "id");
    			if (type.equalsIgnoreCase("uml:Class") && 
    					stereotypeElem.hasAttribute("base_Class") &&
    					stereotypeElem.getAttribute("base_Class").equals(elemID)) {
    				return stereotypeElem;
    			}
    			if (type.equalsIgnoreCase("uml:Association") &&
    					stereotypeElem.hasAttribute("base_Association") &&
    					stereotypeElem.getAttribute("base_Association").equals(elemID)) {
    				return stereotypeElem;
    			}
    		}
    	}
		
		return null;
	}

	@Override
	public List<Object> getElements(Object element, ElementType type) {
		List<Object> elemList = new ArrayList<Object>();
		Element elem = (Element) element;
		
		switch (type) {
			case PACKAGE:
				elemList = getChildsByType(elem, "uml:Package");
				break;
			case PRIMITIVE:
				elemList = getChildsByType(elem, "uml:PrimitiveType");
				break;
			case CLASS:
				elemList = getChildsByType(elem, "uml:Class");
				elemList.addAll(getChildsByType(elem, "uml:AssociationClass"));
				break;
			case DATATYPE:
				elemList = getChildsByType(elem, "uml:DataType");
				break;
			case PROPERTY:
				elemList = getChildsByType(elem, "uml:Property");
				break;
			case ENUMLITERAL:
				elemList = getChildsByType(elem, "uml:EnumerationLiteral");
				break;
			case ASSOCIATION:
				elemList = getChildsByType(elem, "uml:Association");
				List<Object> assoClassList = getChildsByType(elem, "uml:AssociationClass");
				for (Object assocClass : assoClassList) {
					Element assocClassElem = (Element) assocClass;
					Element assocClassElemClone = (Element) assocClassElem.cloneNode(true);
					NodeList extendedProp = doc.getElementsByTagName("extendedProperties");
					for (int i = 0; i < extendedProp.getLength(); i++) {
						if (((Element)extendedProp.item(i)).hasAttribute("associationclass")) {
							assocClassElemClone.setAttributeNS(XMINS, "id", 
									((Element)extendedProp.item(i).getParentNode()).getAttributeNS(XMINS, "idref"));
							assocClassElemClone.setAttribute("name", 
									((Element)extendedProp.item(i).getParentNode()).getAttribute("name"));
						}
					}
					elemList.add(assocClassElemClone);
				}
				break;
			case GENERALIZATION:
				elemList = getChildsByType(elem, "uml:Generalization");
				break;
			case GENERALIZATIONSET:
				elemList = getChildsByType(elem, "uml:GeneralizationSet");
				break;
			case DEPENDENCY:
				elemList = getChildsByType(elem, "uml:Dependency");
				break;
			case COMMENT:
				elemList = getChildsByType(elem, "uml:Comment");
				break;
		}
		
		return elemList;
	}
	
	private List<Object> getChildsByType (Element elem, String tag) {
		List<Object> elemList = new ArrayList<Object>();
		
		for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
    		if (child instanceof Element &&
    				((Element)child).getAttributeNS(XMINS, "type").equalsIgnoreCase(tag)) {
					elemList.add(child);
			}
		}
		
		return elemList;
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
    	Element stereotypeElem = getElementByBaseRef(elem);
    	if (stereotypeElem != null) {
    		NamedNodeMap tagList = stereotypeElem.getAttributes();
    		if (tagList.getLength() > 1) {
    			for (int i = 0; i < tagList.getLength(); i++) {
    				hashProp.put(tagList.item(i).getNodeName(),
    						tagList.item(i).getNodeValue());
    			}
    		}
    	}
    	
    	// Specific Properties
    	if (getType(elem) == ElementType.PROPERTY) {
        	for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
        		if (child instanceof Element) {
	        		Element childElem = (Element) child;
	        		if (childElem.getNodeName().equals("type")) {
	            		hashProp.put("type", childElem.getAttributeNS(XMINS, "idref"));
	            	} else if (childElem.getNodeName().equals("lowerValue")) {
	            		hashProp.put("lowerValue", childElem.getAttribute("value"));
	            	} else if (childElem.getNodeName().equals("upperValue")) {
	            		hashProp.put("upperValue", childElem.getAttribute("value"));
	            	} else if (childElem.getNodeName().equals("defaultValue")) {
	            		hashProp.put("defaultValue", childElem.getAttribute("value"));
	            	}
        		}
        	}
    	}
    	
    	if (elem.getAttributeNS(XMINS, "type").equalsIgnoreCase("uml:AssociationClass")) {
    		
    	}
    	
		return hashProp;
	}

	@Override
	public String getID(Object element) {
		Element elem = (Element) element;
		if (elem.getAttributeNS(XMINS, "type").equalsIgnoreCase("uml:Model")) {
			return "Model_ID_"+elem.getAttribute("name");
		}
		return elem.getAttributeNS(XMINS, "id");
	}

	@Override
	public String getName(Object element) {
		Element elem = (Element) element;
		return elem.getAttribute("name");
	}

	@Override
	public ElementType getType(Object element) {
		Element elem = (Element) element;
		return ElementType.get(elem.getAttributeNS(XMINS, "type").replace("uml:", ""));
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
		if (((Element)element).getAttributeNS(XMINS, "type").equalsIgnoreCase("uml:AssociationClass")) {
			
		}
		return null;
	}

}
