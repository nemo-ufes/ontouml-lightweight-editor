package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import br.ufes.inf.nemo.ontouml.xmi2refontouml.transformation.Mediator;



public class MapperAstah implements Mapper {
	
	private final String[] ASSOCIATION_TAG_PATH = {"UML:Namespace.ownedElement", "UML:Association"};
	private final String[] CLASS_TAG_PATH = {"UML:Namespace.ownedElement", "UML:Class"};
	private final String[] COMMENT_TAG_PATH = {"UML:ModelElement.comment", "UML:Comment"};
	//private final String[] DATATYPE_TAG_PATH = {"UML:Namespace.ownedElement", "UML:Class"};
	private final String[] DEPENDENCY_TAG_PATH = {"UML:Namespace.ownedElement", "UML:Dependency"};
	//private final String[] ENUMERATION_TAG_PATH = {"UML:Namespace.ownedElement", "UML:Class"};
	//private final String[] ENUMLITERAL_TAG_PATH = {"UML:Classifier.feature", "UML:Attribute"};
	private final String[] GENERALIZATION_TAG_PATH = {"UML:GeneralizableElement.generalization", "UML:Generalization"};
	//private final String[] GENERALIZATIONSET_TAG_PATH = {"JUDE:GeneralizationGroupPresentation"};
	private final String[] PACKAGE_TAG_PATH = {"UML:Namespace.ownedElement", "UML:Package"};
	private final String[] PRIMITIVE_TAG_PATH = {"UML:Primitive"};
	private final String[] ATTRIBUTE_TAG_PATH = {"UML:Classifier.feature", "UML:Attribute"};
	private final String[] CONNECTION_TAG_PATH = {"UML:Association.connection", "UML:AssociationEnd"};

	Document doc;
	
	public MapperAstah(String read_file_address) {
		try {
			// Creates an instance of the parser that will read the XMI
	        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        //docBuilderFactory.setNamespaceAware(true);
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        
	        doc = docBuilder.parse(new File(read_file_address));
	        
	        // Set the xmi.id to be the ID element
            setID(doc.getDocumentElement());
	        
		} catch (SAXParseException err) {
            Mediator.errorLog += "** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId();
            Mediator.errorLog += " " + err.getMessage();
 
		} catch (SAXException e) {
			Exception x = e.getException();
			Mediator.errorLog += ((x == null) ? e : x).getMessage();
			
		} catch (IOException e) {
			Mediator.errorLog += "File " + read_file_address + 
			" does not exist or could not be oppened.";
			
		} catch (ParserConfigurationException e) {
			Mediator.errorLog += e.getMessage();
		}
	}
	
	//For the Element 'element', if it is an ELEMENT_NODE sets the xmi.id to be the ID
    protected void setID(Element element) {
    	
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
    public Object getModelElement() {
		return doc.getElementsByTagName("UML:Model").item(0);
    }
	
	/*
	 * Retorna todos os elementos do tipo 'type', dentro do elemento 'parent'
	 * A lista contém somente elementos que são filhos diretos do elemento 'parent'
	 */
	public List<Object> getElements(Object scope, ElementType type) {
		List<Object> elemList = new ArrayList<Object>();
		List<Object> elemListAux;
		
		Element parent = (Element) scope;
		
		switch (type) {
			case ASSOCIATION:
				elemList = getChildsByTagName(parent, ASSOCIATION_TAG_PATH);
				break;
			case CLASS:
				elemListAux = getChildsByTagName(parent, CLASS_TAG_PATH);
				for (Object o : elemListAux) {
					String stereotype = getStereotype((Element)o);
					if (ElementType.get(stereotype) != ElementType.ENUMERATION && 
							ElementType.get(stereotype) != ElementType.DATATYPE &&
							ElementType.get(stereotype) != ElementType.PRIMITIVE) {
						elemList.add(o);
					}
				}
				break;
			case COMMENT:
				elemList = getChildsByTagName(parent, COMMENT_TAG_PATH);
				break;
			case DATATYPE:
				elemList = getClassifierByType(parent, ElementType.DATATYPE);
				break;
			case DEPENDENCY:
				elemList = getChildsByTagName(parent, DEPENDENCY_TAG_PATH);
				break;
			case ENUMERATION:
				elemList = getClassifierByType(parent, ElementType.ENUMERATION);
				break;
			case ENUMLITERAL:
				elemList = getChildsByTagName(parent, ATTRIBUTE_TAG_PATH);
				break;
			case GENERALIZATION:
				elemList = getChildsByTagName(parent, GENERALIZATION_TAG_PATH);
				break;
			case GENERALIZATIONSET:
				elemList = getGeneralizationsSets(parent);
				//elemList = getChildsByTagName(parent, GENERALIZATIONSET_TAG_PATH);
				break;
			case PACKAGE:
				elemList = getChildsByTagName(parent, PACKAGE_TAG_PATH);
				break;
			case PRIMITIVE:
				Element model = (Element) getModelElement();
				if (parent == model) {
					elemList = getChildsByTagName((Element)model.getParentNode(), PRIMITIVE_TAG_PATH);
				}
				elemList.addAll(getClassifierByType(parent, ElementType.PRIMITIVE));
				break;
			case PROPERTY:
				if (parent.getNodeName().equals("UML:Class")) {
					elemList = getChildsByTagName(parent, ATTRIBUTE_TAG_PATH);
				} else if (parent.getNodeName().equals("UML:Association")) {
					elemList = getChildsByTagName(parent, CONNECTION_TAG_PATH);
//				} else if (parent.getNodeName().equals("Derivation")) {
//					elemList = getChildsByTagName(parent, CONNECTION_TAG_PATH);
				}
				break;
		}

		return elemList;
	}
	
	private List<Object> getClassifierByType(Element elem, ElementType type) {
		List<Object> elemList = new ArrayList<Object>();
		List<Object> elemListAux = getChildsByTagName(elem, CLASS_TAG_PATH);
		
		for (Object o : elemListAux) {
			String stereotype = getStereotype((Element)o);
			if (ElementType.get(stereotype) == type) {
				elemList.add(o);
			}
		}
		
		return elemList;
	}
	
	private List<Object> getChildsByTagName(Element elem, String[] tag) {
		
		int n = tag.length;
		if (n > 1) {
			return getChildsByTagName(getChild(elem, tag[0]), Arrays.copyOfRange(tag, 1, n));
		}
		
		List<Object> elemList = new ArrayList<Object>();
		
		if (elem != null) {
			
			for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
	    		if (child instanceof Element &&
	    				((Element)child).getNodeName().equalsIgnoreCase(tag[0])) {
	    			if (((Element)child).hasAttribute("xmi.idref")) {
	    				String elemID = ((Element)child).getAttribute("xmi.idref");
	    				elemList.add(doc.getElementById(elemID));
	    			}
	    			else {
						elemList.add(child);
	    			}
				}
			}
		}
		
		return elemList;
	}
	
	public List<Object> getGeneralizationsSets(Element elem) {
		List<Object> listGenSet = new ArrayList<Object>();
		
		// Retorna os agrupamentos de generalizações. Por definição do projeto, quando se tem esse agrupamento
		// se tem um GeneralizationSet.
		NodeList gengroups = doc.getElementsByTagName("JUDE:GeneralizationGroupPresentation");
		
		for (int i = 0; i < gengroups.getLength(); i++) {
			
			// If it only a reference (does not have any child tags) ignore
			if (!((Element)gengroups.item(i)).hasChildNodes()) {
				continue;
			}
			
			// If it does not have the "disjoint" or "complete" properties do not threat as a GenSet
			HashMap<String, Object> hashPropAux = new HashMap<String, Object>();
			getGenSetProperties((Element)gengroups.item(i), hashPropAux);
			if (!hashPropAux.containsKey("isdisjoint") && !hashPropAux.containsKey("iscovering")) {
				continue;
			}
			
			// Retorna o diagrama do qual o agrupamento faz parte através do idref
			NodeList diagram = ((Element)gengroups.item(i)).getElementsByTagName("JUDE:Diagram");
			if (diagram.getLength() == 0) {
				continue;
			}
			Element diagramelement = doc.getElementById(((Element)diagram.item(0)).getAttribute("xmi.idref"));
			// Busca o Namespace do diagrama, ou seja, o Pacote do qual ele faz parte
			NodeList namespace = diagramelement.getElementsByTagName("UML:Namespace");
			String namespId = ((Element)namespace.item(0)).getAttribute("xmi.idref");
			
			// Caso o namespace seja o 'elem' em questão, adiciona na lista
			if (namespId.equals(elem.getAttribute("xmi.id"))) {
				listGenSet.add((Element)gengroups.item(i));
			}
			
		}
		
		return listGenSet;
	}
	
	// Auxiliary function that returns the first direct child of @parent with name @name
	public Element getChild(Element parent, String name) {
		for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
			if (child instanceof Element && name.equals(child.getNodeName())) {
				return (Element) child;
			}
		}
		return null;
	}
	
	// Get the stereotype of the element node
	public String getStereotype(Object element) throws NullPointerException { 
		
		Element elem = (Element) element;
//		
//		if (elem.getNodeName().equals("Derivation")) {
//			return "derivation";
//		}
		
    	NodeList stereotypeRef = elem.getElementsByTagName("UML:Stereotype");
    	if (stereotypeRef.getLength() == 0) {
    		return "";
    		
    	} else {
    		Element stereotypeElem = (Element) stereotypeRef.item(0);
    		stereotypeElem = doc.getElementById(stereotypeElem.getAttribute("xmi.idref"));
    		return stereotypeElem.getAttribute("name");
    	}
	}

	/* A função retorna um HashMap com as propriedades do elemento em questão. Para que a transformação
	* funcione corretamente, todas as propriedades do elemento definidas no metamodelo de UML devem ser retornadas.
	* Propriedades definidas em extensões como Tagged Values também são retornadas. O HashMap deve ter como key 
	* o nome da propriedade como definido no metamodelo, e como value o valor da propriedade, ou conjunto de valores.
	* No caso de um conjunto de valores, devem ser retornado no campo value do HashMap uma List do tipo dos valores.
	*/
	// Não importa como é feita a implementação, desde que as propriedades daquele objeto específico sejam retornadas.
	// Nesse caso de implementação, para qualquer elemento a função procura por propriedades de cada elemento
	// definido no metamodelo. É o caso, por exemplo, de para uma classe ela procurar a propriedade "general" de
	// generalizations. Como essa propriedade não existe na classe, não será definido essa propriedade no HashMap.
	public HashMap<String, Object> getProperties(Object element) {
		
		Element elem = (Element) element;
		
		HashMap<String, Object> hashProp = new HashMap<String, Object>();
		
		// Como GeneralizationSet não é suportado pelo Astah, as propriedades 
		// (Mesmo as comuns à todos os elementos) são obtidas de forma diferente.
		if (elem.getNodeName().equals("JUDE:GeneralizationGroupPresentation")) {
			getGenSetProperties(elem, hashProp);
		}
		
		else {
			// Obtem as propriedades comuns à todos os elementos
			getCommonProperties(elem, hashProp);
			
			// Obtem as propriedades não definidas no metamodelo de UML, ou não suportadas pelo Astah.
			getExtendedProperties(elem, hashProp);
			
			// Obtem as propriedades específicas de determinados elementos
			getSpecificProperties(elem, hashProp);
		}
    	
    	return hashProp;
	}
	
	public void getCommonProperties (Element elem, HashMap<String, Object> hashProp) {
		
		// Gets the properties that are inside the element tag, such as 'name'.
		NamedNodeMap listProp = elem.getAttributes();
    	for (int j = 0; j < listProp.getLength(); j++) {
    		hashProp.put(listProp.item(j).getNodeName().toLowerCase(), listProp.item(j).getNodeValue());
    	}
    	
    	if (hashProp.containsKey("name")) {
    		try {
				hashProp.put("name", URLDecoder.decode((String)hashProp.get("name"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				Mediator.warningLog += "Name could not be decoded in " + hashProp.get("name") + "\n";
			}
    	}
    	
    	// The 'visibility' property is defined the same way in every element.
    	Element visibility = getChild(elem, "UML:ModelElement.visibility");
    	if (visibility != null) {
    		hashProp.put("visibility", visibility.getAttribute("xmi.value"));
    	}
    	
	}
	
	public void getExtendedProperties (Element elem, HashMap<String, Object> hashProp) {
		// Procura propriedades não suportadas pelo Astah, ou seja, propriedades são definidas
    	// sobre uma extensão de UML. As propriedades de OntoUML são exemplos disso, assim como algumas
    	// propriedades que passaram a existir a partir da UML 1.5. Foi definido neste trabalho que 
    	// essas propriedades viriam na forma de tagged values, que é a forma de extensão que a UML fornece.
    	Element taggedValue = getChild(elem, "UML:ModelElement.taggedValue");
    	if (taggedValue != null) {
	    	for (Node child = taggedValue.getFirstChild(); child != null; child = child.getNextSibling()) {
				if (child instanceof Element) {
					hashProp.put(((Element)child).getAttribute("tag").toLowerCase(), 
							((Element)child).getAttribute("value"));
				}
			}
	    	if (hashProp.containsKey("derive")) {
	    		hashProp.put("isderived", hashProp.get("derive"));
	    	}
    	}
	}
	
	public void getSpecificProperties (Element elem, HashMap<String, Object> hashProp) {
		
		// AssociationEnd properties and Attribute properties (becomes RefOntoUML.Property)
		if (elem.getNodeName().equals("UML:AssociationEnd") || elem.getNodeName().equals("UML:Attribute")) {
			// Gets the 'upper' and 'lower' values
			Element mult = getChild(elem, "UML:StructuralFeature.multiplicity");
			if (mult != null) {
				Element range = (Element)mult.getElementsByTagName("UML:MultiplicityRange").item(0);
				getCommonProperties(range, hashProp);
	    	}
			if (hashProp.containsKey("changeability") && hashProp.get("changeability").equals("frozen")) {
				hashProp.put("isreadonly", "true");
			}
			Element initial = getChild(elem, "UML:Attribute.initialValue");
			if (initial == null) {
				initial = getChild(elem, "UML:AssociationEnd.initialValue");
			}
			if (initial != null) {
				NodeList body = initial.getElementsByTagName("UML:Expression.body");
				hashProp.put("default", body.item(0).getTextContent());
			}
			
			if (elem.getNodeName().equals("UML:AssociationEnd")) {
				// Get the target, which becomes the 'type'
				Element participant = getChild(elem, "UML:AssociationEnd.participant");
				if (participant != null) {
					Element type = getChild(participant, "UML:Classifier");
					if (type != null) {
						hashProp.put("type", type.getAttribute("xmi.idref"));
					}
		    	}
				if (hashProp.containsKey("targetscope") && hashProp.get("targetscope").equals("classifier")) {
					hashProp.put("isstatic", "true");
				}
			} else {
				// Get the 'type'
				Element type = getChild(elem, "UML:StructuralFeature.type");
				if (type != null) {
					Element classf = getChild(type, "UML:Classifier");
					if (classf != null) {
						hashProp.put("type", classf.getAttribute("xmi.idref"));
					}
		    	}
				if (hashProp.containsKey("ownerscope") && hashProp.get("ownerscope").equals("classifier")) {
					hashProp.put("isstatic", "true");
				}
			}
			
		}
		
    	// Generalization properties
		else if (elem.getNodeName().equals("UML:Generalization")) {
	    	Element parent = getChild(elem, "UML:Generalization.parent");
	    	if (parent != null) {
		    	Element general = getChild(parent, "UML:GeneralizableElement");
		    	if (general != null) {
		    		hashProp.put("general", general.getAttribute("xmi.idref"));
		    	}
	    	}
	    	Element child = getChild(elem, "UML:Generalization.child");
	    	if (child != null) {
		    	Element specific = getChild(child, "UML:GeneralizableElement");
		    	if (specific != null) {
		    		hashProp.put("specific", specific.getAttribute("xmi.idref"));
		    	}
	    	}
		}
    	
    	// Dependency properties
		else if (elem.getNodeName().equals("UML:Dependency")) {
			List<String> clients = new ArrayList<String>();
	    	List<String> suppliers = new ArrayList<String>();
	    	Element client = getChild(elem, "UML:Dependency.client");
	    	if (client != null) {
	    		for (Node child = client.getFirstChild(); child != null; child = child.getNextSibling()) {
					if (child instanceof Element) {
						clients.add(((Element)child).getAttribute("xmi.idref"));
					}
		    	}
				hashProp.put("client", clients);
	    	}
	    	Element supplier = getChild(elem, "UML:Dependency.supplier");
	        if (supplier != null) {
	        	for (Node child = supplier.getFirstChild(); child != null; child = child.getNextSibling()) {
	    			if (child instanceof Element) {
	    				suppliers.add(((Element)child).getAttribute("xmi.idref"));
	    			}
	    	    }
	    		hashProp.put("supplier", suppliers);
	        }
		}
		
		// Comment properties
		else if (elem.getNodeName().equals("UML:Comment")) {
			List<String> annotElements = new ArrayList<String>();
			Element annotElem = getChild(elem, "UML:Comment.annotatedElement");
	    	if (annotElem != null) {
	    		for (Node child = annotElem.getFirstChild(); child != null; child = child.getNextSibling()) {
	    			if (child instanceof Element) {
	    				annotElements.add(((Element)child).getAttribute("xmi.idref"));
					}
	    		}
	    		hashProp.put("annotatedelement", annotElements);
	    	}
	    	if (hashProp.get("body") != null) {
		    	try {
					hashProp.put("body", URLDecoder.decode((String)hashProp.get("body"), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					Mediator.warningLog += "Name could not be decoded in " + hashProp.get("name") + "\n";
				}
	    	}
		}
	}
	
	public void getGenSetProperties (Element elem, HashMap<String, Object> hashProp) {
		
		// Lista onde serão gravados os ids das generalizações que fazem parte do GeneralizationSet
		List<String> genIdList = new ArrayList<String>();
		
		// Busca as generalizações que fazem parte do GeneralizationSet
		Element clients = getChild(elem, "JUDE:UPresentation.clients");
		if (clients == null) {
			return;
		}
		NodeList genpresent = clients.getElementsByTagName("JUDE:GeneralizationPresentation");
		
		for (int i=0; i < genpresent.getLength(); i++) {
			Element e = (Element)genpresent.item(i);
			Element genpres = (Element)doc.getElementById(e.getAttribute("xmi.idref"));
			Element gen = (Element)genpres.getElementsByTagName("UML:Generalization").item(0);
			genIdList.add(gen.getAttribute("xmi.idref"));
		}
		hashProp.put("generalization", genIdList);
		
		for (String id : genIdList) {
			Element genelement = (Element) doc.getElementById(id);
			// Se estiver em Tagged Values
			getExtendedProperties(genelement, hashProp);
			// Se estiver no nome
			String genname = genelement.getAttribute("name");
			// Se estiver no contraint
			String constname = "";
			Element constraint = getChild(genelement, "UML:ModelElement.constraint");
			if (constraint != null) {
				constraint = getChild(constraint, "UML:Constraint");
				constname = constraint.getAttribute("name");
			}
			
			if (genname.contains("disjoint") || constname.contains("disjoint")) {
				hashProp.put("isdisjoint", "true");
			}
			if (genname.contains("complete") || constname.contains("complete")) {
				hashProp.put("iscovering", "true");
			}
			
			
		}
	}
	
	@Override
	public String getID(Object elem) {
    	return ((Element)elem).getAttribute("xmi.id");
    }
    
	@Override
    public String getName(Object elem) {
    	try {
			return URLDecoder.decode(((Element)elem).getAttribute("name"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Mediator.warningLog += "Name could not be decoded in " + ((Element)elem).getAttribute("name") + "\n";
		}
		return "";
    }
    
	@Override
    public ElementType getType(Object elem) {
    	String type = ((Element)elem).getNodeName();
    	return ElementType.get(type.replace("UML:", ""));
    }
    
	@Override
    public Object getElementById(String id) {
    	return doc.getElementById(id);
    }
	
	@Override
	public String getRelatorfromMaterial(Object element) {
		Element elem = (Element) element;
		
		if (elem.getNodeName().equalsIgnoreCase("UML:Association") &&
				getStereotype(elem).equalsIgnoreCase("material")) {
			HashMap<String, Object> hashProp = new HashMap<String, Object>();
			getExtendedProperties(elem, hashProp);
			if (hashProp.containsKey("relator")) {
				return getRelatorID(elem, (String)hashProp.get("relator"));
			}
		}
		return null;
	}
	
	public String getRelatorID(Element elem, String name) {
		Node parent = elem.getParentNode();
		while (parent.getNodeName() != "UML:Package" && parent.getNodeName() != "UML:Model") {
			parent = parent.getParentNode();
		}
		List<Object> list = getChildsByTagName((Element)parent, CLASS_TAG_PATH);
		for (Object c : list) {
			if (((Element)c).getAttribute("name").equalsIgnoreCase(name)) {
				return ((Element)c).getAttribute("xmi.id");
			}
		}
		return null;
	}
}
