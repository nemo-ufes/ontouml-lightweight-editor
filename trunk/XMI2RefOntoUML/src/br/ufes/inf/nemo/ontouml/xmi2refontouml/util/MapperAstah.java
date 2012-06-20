package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import java.util.ArrayList;
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



public class MapperAstah implements Mapper {
	
	Document doc;
	
	public MapperAstah(String read_file_address) {
		try {
			// Creates an instance of the parser that will read the XMI
	        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        //docBuilderFactory.setNamespaceAware(true);
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        
	        //System.out.println("Parsing file...");
	        doc = docBuilder.parse(new File(read_file_address));
	        
	        // Set the xmi.id to be the ID element
            setID(doc.getDocumentElement());
	        
		} catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
 
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
 
        } catch (IOException ioe) {
        	System.out.println("File " + read_file_address + 
        			" does not exist or could not be oppened.");
        	
        } catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//For the Element 'element', if it is an element node sets the xmi.id to be the ID
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
    
    public String getIDName() {
    	return "xmi.id";
    }
    
    public String getID(Object elem) {
    	return ((Element)elem).getAttribute("xmi.id");
    }
    
    public String getName(Object elem) {
    	return ((Element)elem).getAttribute("name");
    }
    
    public ElementType getType(Object elem) {
    	String type = ((Element)elem).getNodeName();
    	if (type.equals("UML:Model")) {
    		return ElementType.MODEL;
    	}
    	else if (type.equals("UML:Package")) {
    		return ElementType.PACKAGE;
    	}
    	else if (type.equals("UML:Class")) {
    		return ElementType.CLASS;
    	}
    	else if (type.equals("UML:Datatype")) {
    		return ElementType.DATATYPE;
    	}
    	else if (type.equals("UML:Association")) {
    		return ElementType.ASSOCIATION;
    	}
    	else if (type.equals("UML:Property")) {
    		return ElementType.PROPERTY;
    	}
    	else if (type.equals("UML:Generalization")) {
    		return ElementType.GENERALIZATION;
    	}
    	else if (type.equals("UML:GeneralizationSet")) {
    		return ElementType.GENERALIZATIONSET;
    	}
    	else if (type.equals("UML:Dependency")) {
    		return ElementType.DEPENDENCY;
    	}
    	else
    		return null;
    }
    
    public Object getElementById(String id) {
    	return doc.getElementById(id);
    }
    
    // Retorna o elemento principal de um modelo UML, o UML:Model. Supõe que o XMI é bem formado e só possui
    // uma tag to tipo UML:Model
    public Object getModelElement() {
    	NodeList model = doc.getElementsByTagName("UML:Model");
		return (Element)model.item(0);
    }
	
	/*
	 * Retorna todos os elementos do tipo 'type', dentro do elemento 'parent'
	 * A lista contém somente elementos que são filhos diretos do elemento 'parent'
	 */
	public List<Object> getElements(Object scope, ElementType type) {
		List<Object> elemList = new ArrayList<Object>();
		
		Element parent = (Element) scope;
		
		switch (type) {
			case PACKAGE:
				elemList = getChildElements(parent, "UML:Package");
				break;
			case CLASS:
				elemList = getChildElements(parent, "UML:Class");
				break;
			case PROPERTY:
				if (parent.getNodeName().equals("UML:Class")) {
					elemList = getAttributes(parent);
				} else if (parent.getNodeName().equals("UML:Association")) {
					elemList = getConnections(parent);
				}
				break;
			case ASSOCIATION:
				elemList = getChildElements(parent, "UML:Association");
				break;
			case GENERALIZATION:
				elemList = getGeneralizations(parent);
				break;
			case GENERALIZATIONSET:
				elemList = getGeneralizationsSets(parent);
				break;
			case DEPENDENCY:
				elemList = getChildElements(parent, "UML:Dependency");
				break;
			case COMMENT:
				elemList = getComments(parent);
				break;
		}
		return elemList;
	}
	
	// Get all the attributes for the class (the ones defined by the user)
	public List<Object> getAttributes(Element elem) {
		
		NodeList listAtt = elem.getElementsByTagName("UML:Attribute");
		List<Object> list = new ArrayList<Object>();
		
		for (int i = 0; i < listAtt.getLength(); i++) {
    		list.add((Element)listAtt.item(i));
    	}
		return list;
	}
	
	// Get all the connections for the association
	public List<Object> getConnections(Element elem) {
		
		NodeList listCon = elem.getElementsByTagName("UML:AssociationEnd");
		List<Object> list = new ArrayList<Object>();
		
		for (int i = 0; i < listCon.getLength(); i++) {
			//Since the AssociationEnd inside the Association can be a
			//reference or the definition itself, we have to deal with
			//this by making sure the Element has child nodes.
			if (listCon.item(i).hasChildNodes()) {
				list.add((Element)listCon.item(i));
			} else {
				Document doc = elem.getOwnerDocument();
				String elemID = ((Element)listCon.item(i)).getAttribute("xmi.idref");
				list.add(doc.getElementById(elemID));
			}
    	}
		
		return list;
	}
    
	// Get a list of the Generalization of element 'elem'
	public List<Object> getGeneralizations(Element elem) {
		
		Document doc = elem.getOwnerDocument();
		
		Element allgen = getChild(elem, "UML:GeneralizableElement.generalization");
		List<Object> listGen = new ArrayList<Object>();
		
		if (allgen != null) { // If garante que classe tem generalizações antes de executar o for.
			for (Node child = allgen.getFirstChild(); child != null; child = child.getNextSibling()) {
				if (child instanceof Element) {
					Element genele = (Element) doc.getElementById(((Element)child).getAttribute("xmi.idref"));
					listGen.add(genele);
				}
			}
		}
		
		return listGen;
	}
	
	public List<Object> getGeneralizationsSets(Element elem) {
		List<Object> listGenSet = new ArrayList<Object>();
		
		Document doc = elem.getOwnerDocument();
		
		// Retorna os agrupamentos de generalizações. Por definição do projeto, quando se tem esse agrupamento
		// se tem um GeneralizationSet.
		NodeList gengroups = doc.getElementsByTagName("JUDE:GeneralizationGroupPresentation");
		
		for (int i = 0; i < gengroups.getLength(); i++) {
			
			if (!((Element)gengroups.item(i)).hasChildNodes()) {
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
	
	public List<Object> getComments(Element elem) {
		
		Element commentsElem = getChild(elem, "UML:ModelElement.comment");
		List<Object> listComment = new ArrayList<Object>();
		
		if (commentsElem != null) {
			for (Node child = commentsElem.getFirstChild(); child != null; child = child.getNextSibling()) {
				if (child instanceof Element) {
					//See if the comment is defined inside the element or if its just a reference
					if (child.hasChildNodes()) {
						listComment.add((Element)child);
					} else {
						Document doc = elem.getOwnerDocument();
						String elemID = ((Element)child).getAttribute("xmi.idref");
						listComment.add(doc.getElementById(elemID));
					}
				}
			}
		}
		
		return listComment;
	}
	
	// Auxiliary function that returns all owned elements of @parent with name @name, and put in a List.
	public List<Object> getChildElements(Element parent, String name) {
		Element ownedElement = getChild(parent, "UML:Namespace.ownedElement");
    	List<Object> list = new ArrayList<Object>();
    	try {
	    	for (Node child = ownedElement.getFirstChild(); child != null; child = child.getNextSibling()) {
	    		if (child instanceof Element && child.getNodeName().equals(name)) {
	    			list.add((Element)child);
				}
	    	}
    	} catch (NullPointerException npe) {
    		System.out.println("Warning: Empty package '" +	parent.getAttribute("name") + "'.");
    	}
		return list;
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
		
		Document doc = elem.getOwnerDocument();
		
    	NodeList stereotypeRef = elem.getElementsByTagName("UML:Stereotype");
		Element stereotypeElem = (Element) stereotypeRef.item(0);
		stereotypeElem = doc.getElementById(stereotypeElem.getAttribute("xmi.idref"));

		return stereotypeElem.getAttribute("name");
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
			return getGenSetProperties(elem);
		}
		
		// Obtem as propriedades comuns à todos os elementos
		getCommonProperties(elem, hashProp);
		
		// Obtem as propriedades não definidas no metamodelo de UML, ou não suportadas pelo Astah.
		getExtendedProperties(elem, hashProp);
		
		// Obtem as propriedades específicas de determinados elementos
		getSpecificProperties(elem, hashProp);
    	
    	return hashProp;
	}
	
	public void getCommonProperties (Element elem, HashMap<String, Object> hashProp) {
		
		// Gets the properties that are inside the element tag, such as 'name'.
		NamedNodeMap listProp = elem.getAttributes();
    	for (int j = 0; j < listProp.getLength(); j++) {
    		hashProp.put(listProp.item(j).getNodeName().toLowerCase(), listProp.item(j).getNodeValue());
    	}
    	
    	if (hashProp.get("name") != null) {
    		try {
				hashProp.put("name", URLDecoder.decode((String)hashProp.get("name"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
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
			hashProp.put("isderived", hashProp.get("derive"));
			
			if (elem.getNodeName().equals("UML:AssociationEnd")) {
				// Gets the target, which becomes the 'type'
				Element participant = getChild(elem, "UML:AssociationEnd.participant");
				if (participant != null) {
					Element type = getChild(participant, "UML:Classifier");
					if (type != null) {
						hashProp.put("type", type.getAttribute("xmi.idref"));
					}
		    	}
				// Gets 'isLeaf', 'isStatic' and 'isDerived'
				if (hashProp.get("changeability").equals("frozen")) {
					hashProp.put("isleaf", "true");
				}
				if (hashProp.get("targetscope").equals("classifier")) {
					hashProp.put("isstatic", "true");
				}
			} else {
				// Gets 'isLeaf', 'isStatic' and 'isDerived'
				if (hashProp.get("changeability").equals("frozen")) {
					hashProp.put("isreadonly", "true");
				}
				if (hashProp.get("ownerscope").equals("classifier")) {
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
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
	    	}
		}
	}
	
	public HashMap<String, Object> getGenSetProperties (Element elem) {
		
		HashMap<String, Object> hashProp = new HashMap<String, Object>();
		// Lista onde serão gravados os ids das generalizações que fazem parte do GeneralizationSet
		List<String> genIdList = new ArrayList<String>();
		
		Document doc = elem.getOwnerDocument();
		
		// Busca as generalizações que fazem parte do GeneralizationSet
		Element clients = getChild(elem, "JUDE:UPresentation.clients");
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
			if (genname.contains("disjoint")) {
				hashProp.put("isdisjoint", "true");
			}
			if (genname.contains("complete")) {
				hashProp.put("iscovering", "true");
			}
		}
		
		return hashProp;
	}
}
