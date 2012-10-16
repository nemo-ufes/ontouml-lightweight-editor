package br.ufes.inf.nemo.ontouml.xmi2refontouml.mapperImpl;

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

import br.ufes.inf.nemo.ontouml.xmi2refontouml.core.Mapper;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.util.ElementType;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.util.XMLDOMUtil;


public class MapperEA implements Mapper {
	
	// FIXED NAMESPACES
	private final String XMLNS = "http://www.w3.org/2000/xmlns/";
	private final String UMLNS = "http://schema.omg.org/spec/UML/2.1";
	private final String XMINS = "http://schema.omg.org/spec/XMI/2.1";
	
	// VARIABLE NAMESPACES
	private String OntoUML;
//	private String EAUML;
	
	Document doc;
	
	Map<String, Element> stereotypes = new HashMap<String, Element>();
	
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
        		if (nsName.equalsIgnoreCase("xmlns:EAUML")) {
//        			EAUML = docAttributes.item(i).getNodeValue();
        		}
        	}
        }

        setID(doc.getDocumentElement());
        
        createStereotypesMap();
	}
	
    protected void setID(Element element) {
    	List<Element> childList = getElementChilds(element);
	    for (Element child : childList) {
    		if (child.hasAttributeNS(XMINS, "id") &&
    			doc.getElementById(child.getAttributeNS(XMINS, "id")) == null) {
    			child.setIdAttributeNS(XMINS, "id", true);
    		}
    		setID(child);
	    }
    }
    
    protected void createStereotypesMap() {
    	NodeList stereotypeList = doc.getElementsByTagNameNS(OntoUML, "*");
    	if (stereotypeList.getLength() != 0) {
    		for (int i=0; i < stereotypeList.getLength(); i++) {
    			Element stereotypeElem = (Element) stereotypeList.item(i);
    			
    			if (stereotypeElem.hasAttribute("base_Class")) {
    				stereotypes.put(stereotypeElem.getAttribute("base_Class"),
    						stereotypeElem);
    			}
    			if (stereotypeElem.hasAttribute("base_Association")) {
    				stereotypes.put(stereotypeElem.getAttribute("base_Association"),
    						stereotypeElem);
    			}
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
		String stereotype;
		
		Element stereotypeElem = stereotypes.get(elem.getAttributeNS(XMINS, "id"));
    	if (stereotypeElem != null) {
    		stereotype = stereotypeElem.getNodeName().replace("OntoUML:", "");
    		
    	} else {
			String type = elem.getAttributeNS(XMINS, "type").replace("uml:", "");
			if (ElementType.get(type) == ElementType.CLASS ||
					ElementType.get(type) == ElementType.ASSOCIATION) {
				stereotype = "";
				
			} else if (ElementType.get(type) == ElementType.ASSOCIATIONCLASS) {
				stereotype = "relator";
				
			} else {
				stereotype = type;
			}
		}
		
		return stereotype;
	}

	@Override
	public List<Object> getElements(Object element, ElementType type) {
		List<Object> elemList = new ArrayList<Object>();
		Element elem = (Element) element;
		
		switch (type) {
			case ASSOCIATION:
				elemList = getChildsByType(elem, ElementType.ASSOCIATION);
				List<Object> assoClassList = getChildsByType(elem, ElementType.ASSOCIATIONCLASS);
				for (Object assocClass : assoClassList) {
					Element assocClassElem = (Element) assocClass;
					Element assocClassElemClone = (Element) assocClassElem.cloneNode(true);
					NodeList extendedProp = doc.getElementsByTagName("extendedProperties");
					for (int i = 0; i < extendedProp.getLength(); i++) {
						if (((Element)extendedProp.item(i)).hasAttribute("associationclass") &&
								((Element)extendedProp.item(i)).getAttribute("associationclass").equals(assocClassElem.getAttributeNS(XMINS, "id"))) {
							assocClassElemClone.setAttribute("relator", 
									assocClassElemClone.getAttributeNS(XMINS, "id"));
							assocClassElemClone.setAttributeNS(XMINS, "id", 
									((Element)extendedProp.item(i).getParentNode()).getAttributeNS(XMINS, "idref"));
							assocClassElemClone.setAttribute("name", 
									((Element)extendedProp.item(i).getParentNode()).getAttribute("name"));
							assocClassElem.getParentNode().appendChild(assocClassElemClone);
							assocClassElem.setIdAttributeNS(XMINS, "id", true);
							assocClassElemClone.setIdAttributeNS(XMINS, "id", true);
						}
					}
					elemList.add(assocClassElemClone);
				}
				break;
			case CLASS:
				elemList = getChildsByType(elem, ElementType.CLASS);
				elemList.addAll(getChildsByType(elem, ElementType.ASSOCIATIONCLASS));
				break;
			case COMMENT:
				elemList = getChildsByType(elem, ElementType.COMMENT);
				break;
			case DATATYPE:
				elemList = getChildsByType(elem, ElementType.DATATYPE);
				break;
			case DEPENDENCY:
				elemList = getChildsByType(elem, ElementType.DEPENDENCY);
				break;
			case ENUMERATION:
				elemList = getChildsByType(elem, ElementType.ENUMERATION);
				break;
			case ENUMLITERAL:
				elemList = getChildsByType(elem, ElementType.ENUMLITERAL);
				break;
			case GENERALIZATION:
				elemList = getChildsByType(elem, ElementType.GENERALIZATION);
				break;
			case GENERALIZATIONSET:
				elemList = getChildsByType(elem, ElementType.GENERALIZATIONSET);
				break;
			case PACKAGE:
				elemList = getChildsByType(elem, ElementType.PACKAGE);
				break;
			case PRIMITIVE:
				Element model = (Element) getModelElement();
				if (elem == model) {
					elemList = getPrimitives();
				}
				elemList.addAll(getChildsByType(elem, ElementType.PRIMITIVE));
				break;
			case PROPERTY:
				if (getType(elem) == ElementType.ASSOCIATIONCLASS) {
					List<Object> elemListAux = getChildsByType(elem, ElementType.PROPERTY);
					for (Object o : elemListAux) {
						Element e = (Element) o;
						if (elem.hasAttribute("relator")) {
							if (e.hasAttribute("association") &&
									e.getAttribute("association").equals(elem.getAttribute("relator"))) {
								elemList.add(e);
							}
						}
						else if (!e.hasAttribute("association") ||
								!e.getAttribute("association").equals(elem.getAttributeNS(XMINS, "id"))) {
								elemList.add(e);
						}
					}
				} else {
					elemList = getChildsByType(elem, ElementType.PROPERTY);
				}
				break;
			default:
				break;
		}
		
		return elemList;
	}
	
	private List<Object> getPrimitives() {
		List<Object> elemList = new ArrayList<Object>();
		NodeList primPackList = doc.getElementsByTagName("primitivetypes");
		if (primPackList.getLength() != 0) {
			NodeList primList = ((Element)primPackList.item(0)).getElementsByTagName("packagedElement");
			if (primList.getLength() != 0) {
				for (int i = 0; i < primList.getLength(); i++) {
					Node child = primList.item(i);
					if (child instanceof Element && getType(child) == ElementType.PRIMITIVE) {
						elemList.add(child);
					}
				}
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
    	Element stereotypeElem = stereotypes.get(elem.getAttributeNS(XMINS, "id"));
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
    		List<Element> childList = getElementChilds(elem);
        	for (Element childElem : childList) {
        		if (childElem.getNodeName().equals("type")) {
            		hashProp.put("type", childElem.getAttributeNS(XMINS, "idref"));
            	} else if (childElem.getNodeName().equals("lowerValue")) {
            		hashProp.put("lower", childElem.getAttribute("value"));
            	} else if (childElem.getNodeName().equals("upperValue")) {
            		hashProp.put("upper", childElem.getAttribute("value"));
            	} else if (childElem.getNodeName().equals("defaultValue")) {
            		hashProp.put("default", childElem.getAttribute("value"));
            	}
        	}
        	
    	} else if (getType(elem) == ElementType.ASSOCIATION || getType(elem) == ElementType.ASSOCIATIONCLASS) {
    		List<Element> memberEndsAux = getElementChildsByTagName(elem, "memberEnd");
    		List<String> memberEnds = new ArrayList<String>();
    		for (Element memberEnd : memberEndsAux) {
    			memberEnds.add(memberEnd.getAttributeNS(XMINS, "idref"));
    		}
    		hashProp.put("memberend", memberEnds);
    		
    	} else if (getType(elem) == ElementType.GENERALIZATIONSET) {
    		List<Element> generalizationsAux = getElementChildsByTagName(elem, "generalization");
    		List<String> generalizations = new ArrayList<String>();
    		for (Element generalization : generalizationsAux) {
    			generalizations.add(generalization.getAttributeNS(XMINS, "idref"));
    		}
    		hashProp.put("generalization", generalizations);
    		
    	} else if (getType(elem) == ElementType.COMMENT) {
    		List<Element> commentsAux = getElementChildsByTagName(elem, "annotatedElement");
    		List<String> comments = new ArrayList<String>();
    		for (Element comment : commentsAux) {
    			comments.add(comment.getAttributeNS(XMINS, "idref"));
    		}
    		hashProp.put("annotatedelement", comments);
    	}
    	
		return hashProp;
	}
	
	private List<Object> getChildsByType (Element elem, ElementType type) {
		List<Object> elemList = new ArrayList<Object>();
		for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
    		if (child instanceof Element && getType(child) == type) {
				elemList.add((Element)child);
			}
		}
		return elemList;
	}
	
	private List<Element> getElementChildsByTagName (Element elem, String TagName) {
		List<Element> elemList = new ArrayList<Element>();
		for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
    		if (child instanceof Element && child.getNodeName().equals(TagName)) {
				elemList.add((Element)child);
			}
		}
		return elemList;
	}
	
	private List<Element> getElementChilds (Element elem) {
		List<Element> elemList = new ArrayList<Element>();
		for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
    		if (child instanceof Element) {
    			elemList.add((Element)child);
    		}
		}
		return elemList;
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
			elem = XMLDOMUtil.getChild(elem, "properties");
		}
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
		Element elem = (Element) element;
		if (getType(elem) == ElementType.ASSOCIATIONCLASS) {
			return elem.getAttribute("relator");
		}
		return null;
	}

	@Override
	public List<Object> getDiagramList() {
		List<Object> diagramList = new ArrayList<Object>();
		NodeList diagrams = doc.getElementsByTagName("diagrams");
		if (diagrams != null) {
			diagramList = XMLDOMUtil.getElementChilds((Element)diagrams.item(0));
		}
		Object[] diagramArray = diagramList.toArray();
		Arrays.sort(diagramArray, new EAComparator());
		return Arrays.asList(diagramArray);
	}

	@Override
	public List<String> getDiagramElements(Object diagram) throws Exception {
		List<String> diagElemIDList = new ArrayList<String>();
		Element elements = XMLDOMUtil.getChild((Element)diagram, "elements");
		if (elements != null) {
			List<Object> diagElemList = XMLDOMUtil.getElementChilds(elements);
	
			for (Object diagElem : diagElemList) {
				if (getElementById(((Element)diagElem).getAttribute("subject")) == null) {
					throw new Exception("XMI file is invalid. Reference to a inexistent element " +
							"in diagram " + ((Element)((Element)diagram).getElementsByTagName
							("properties").item(0)).getAttribute("name") + " found.");
				}
				ElementType type = getType(getElementById(((Element)diagElem).getAttribute("subject")));
				if (type == ElementType.CLASS ||
						type == ElementType.ASSOCIATION ||
						type == ElementType.ASSOCIATIONCLASS) {
					
					diagElemIDList.add(((Element)diagElem).getAttribute("subject"));
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
