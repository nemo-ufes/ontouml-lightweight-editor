package xmi2refontouml.transformation;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;

import org.w3c.dom.*;
 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import xmi2refontouml.mapper.AstahMapper;
import xmi2refontouml.ElementType;
import xmi2refontouml.Mapper;

import RefOntoUML.Generalization;


public class Parser {
	
	final static String FILE_ADDRESS = "C:\\Users\\Vinicius\\Documents\\ANTT\\XMItoOntoRef\\Ontologia da ANTT.xml";
	// Mapeia o id dos elementos para elementos RefOntoUML. É utilizado como auxiliar para se criar referências,
	// como por exemplo em generalizations e associations.
	final static HashMap<String, RefOntoUML.Element> elemMap = new HashMap<String, RefOntoUML.Element>();
	// Leitor de arquivo XMI que conhece as tags específicas do programa que exportou o arquivo
	private static Mapper mapper;
	// Instância da classe que cria os objetos RefOntoUML
    private static RefOntoCreator refcreator = new RefOntoCreator();

    
    /* Função principal de criação
     * cria o modelo propriamente dito e coordena a criação de todos os elementos
     */
    public static void createModel(RefOntoUML.Model model, Document doc) {
    	Element modelelement = mapper.getModelElement(doc);
    	Map<String, Object> hashProp = mapper.getProperties(modelelement);
        refcreator.dealModel(model, hashProp);
        elemMap.put(modelelement.getAttribute(mapper.getIDName()), model);
        /* Só existe um elemento Model
         * Todo model é um package
         * Namespace tem PackageMerge* e PackageImport*, ElementImport e Contraintx
         * Um Package pode ter: Package, Class, DataType, Association, GeneralizationSet, PackageMerge*
         * Uma Classe pode ter: Atributos, Generalizações, ??nestedClassifier??
         * DataTypes podem ter: Atributos, Generalizações
         * Associations podem ter: Atributos(OwnedEnd`s), Generalizações
         * Classes, DataTypes e Associations podem ter: Constraintx, ElementImport, PackageImport
         * Todo element pode ter Comments
         */
        createPackageableElements(model, modelelement);
    }
    
    public static void createPackageableElements(RefOntoUML.Package pack, Element element) {
    	// Cria Packages
    	List<Element> listPacks = mapper.getElements(element, ElementType.PACKAGE);
        for (Element e : listPacks) {
	        RefOntoUML.Package pack1 = createPackage(e);
	        refcreator.addPackagedElement(pack, pack1);
        }
        // Cria Classes e Datatypes. São tratados iguais, pois só muda 1 parâmetro
        List<Element> listClass = mapper.getElements(element, ElementType.CLASS);
        for (Element e : listClass) {
        	RefOntoUML.Classifier class1 = createClass(e);
        	refcreator.addPackagedElement(pack, class1);
        }
        // Cria Associations
        List<Element> listAssoc = mapper.getElements(element, ElementType.ASSOCIATION);
        for (Element e : listAssoc) {
        	RefOntoUML.Association assoc1 = createAssociation(e);
        	refcreator.addPackagedElement(pack, assoc1);
        }
        // Cria Generalization Sets
        List<Element> listGenSet = mapper.getElements(element, ElementType.GENERALIZATIONSET);
        for (Element e : listGenSet) {
        	RefOntoUML.GeneralizationSet genset1 = createGeneralizationSet(e);
        	refcreator.addPackagedElement(pack, genset1);
        }
        // Cria Dependencies
        List<Element> listDepend = mapper.getElements(element, ElementType.DEPENDENCY);
        for (Element e : listDepend) {
        	RefOntoUML.Dependency depend1 = createDependency(e);
        	refcreator.addPackagedElement(pack, depend1);
        }
    }
    
    public static RefOntoUML.Package createPackage(Element packageelement) {
    	RefOntoUML.Package pack1 = refcreator.createPackage();
    	Map<String, Object> hashProp = mapper.getProperties(packageelement);
    	refcreator.dealPackage(pack1, hashProp);
    	
    	elemMap.put(packageelement.getAttribute(mapper.getIDName()), pack1);
    	
    	createPackageableElements(pack1, packageelement);
        
        return pack1;
    }
    
    public static RefOntoUML.Classifier createClass(Element classelement) {
    	String stereotype = mapper.getStereotype(classelement);
    	RefOntoUML.Classifier class1 = null;
    	if (stereotype != null) {
    		// Get the class properties (the ones defined in the metamodel)
    		Map<String, Object> hashProp = mapper.getProperties(classelement);
    		// Get the class attributes (the ones created by users)
        	List<Element> listAtt = mapper.getElements(classelement, ElementType.ATTRIBUTE);
    		// Verifica se é Classe ou DataType
    		if (stereotype.equalsIgnoreCase("datatype") || stereotype.equalsIgnoreCase("enumeration") || 
    				stereotype.equalsIgnoreCase("enum") || stereotype.equalsIgnoreCase("primitivetype")) {
    			class1 = refcreator.createDataType(stereotype);
    			refcreator.dealDataType((RefOntoUML.DataType)class1, hashProp);
    			for (Element e : listAtt) {
            		RefOntoUML.Property prop1 = createProperty(e);
            		refcreator.addAttribute((RefOntoUML.DataType)class1, prop1);
            	}
    		} else {
    			if ((class1 = refcreator.createClass(stereotype)) != null) {
	    			refcreator.dealClass((RefOntoUML.Class)class1, hashProp);
	    			for (Element e : listAtt) {
	            		RefOntoUML.Property prop1 = createProperty(e);
	            		refcreator.addAttribute((RefOntoUML.Class)class1, prop1);
	            	}
    			} else {
    				System.out.println("Class '" + classelement.getAttribute("name") + "' could not be created.");
    				System.exit(-1);
    			}
    		}
    		// Get the class generalizations
    		List<Element> listGen = mapper.getElements(classelement, ElementType.GENERALIZAION);
    		for (Element e : listGen) {
        		RefOntoUML.Generalization gen1 = createGeneralization(e);
        		refcreator.addGeneralization(class1, gen1);
        	}
    		elemMap.put(classelement.getAttribute(mapper.getIDName()), class1);
    	} else {
    		System.out.println("Error: Class '" + classelement.getAttribute("name") + "' does not have a stereotype.");
    		System.exit(-1);
    	}
    	return class1;
    }
    
    public static RefOntoUML.Association createAssociation(Element assocelement) {
    	String stereotype = mapper.getStereotype(assocelement);
    	RefOntoUML.Association assoc1 = null;
    	if (stereotype != null) {
    		
    		if ((assoc1 = refcreator.createAssociation(stereotype)) != null) {
	    		Map<String, Object> hashProp = mapper.getProperties(assocelement);
	    		
	    		// Get association connections
	    		List<Element> listCon = mapper.getElements(assocelement, ElementType.COUPLING);
	    		for (Element e : listCon) {
	        		RefOntoUML.Property prop1 = createProperty(e);
	        		refcreator.addConnection(assoc1, prop1);
	        	}
	    		
	    		// Get association generalizations
	    		List<Element> listGen = mapper.getElements(assocelement, ElementType.GENERALIZAION);
	    		for (Element e : listGen) {
	        		RefOntoUML.Generalization gen1 = createGeneralization(e);
	        		refcreator.addGeneralization(assoc1, gen1);
	        	}
	    		
	    		refcreator.dealAssociation(assoc1, hashProp);
	    		elemMap.put(assocelement.getAttribute(mapper.getIDName()), assoc1);
    		} else {
    			System.out.println("Error: Association '" + assocelement.getAttribute("name") + "' could not be created.");
        		System.exit(-1);
    		}
    	} else {
    		System.out.println("Error: Association '" + assocelement.getAttribute("name") + "' does not have a stereotype.");
    		System.exit(-1);
    	}
    	
    	return assoc1;
    }
    
    public static RefOntoUML.Property createProperty(Element propelement) {
    	RefOntoUML.Property prop1 = refcreator.createProperty();
    	Map<String, Object> hashProp = mapper.getProperties(propelement);
    	
    	// Gets the RefOntoUML.Element that is the 'type' of the property
    	hashProp.put("type", elemMap.get(hashProp.get("type")));
    	
    	refcreator.dealProperty(prop1, hashProp);
    	elemMap.put(propelement.getAttribute(mapper.getIDName()), prop1);
    	
    	return prop1;
    }
    
    public static RefOntoUML.Generalization createGeneralization(Element genelement) {
    	RefOntoUML.Generalization gen1 = refcreator.createGeneralization();
    	Map<String, Object> hashProp = mapper.getProperties(genelement);
    	
    	// Gets the RefOntoUML.Element that is the 'general' of the generalization
    	hashProp.put("general", elemMap.get(hashProp.get("general")));
    	
    	refcreator.dealGeneralization(gen1, hashProp);
    	elemMap.put(genelement.getAttribute(mapper.getIDName()), gen1);
    	
    	return gen1;
    }
    
    public static RefOntoUML.GeneralizationSet createGeneralizationSet(Element gensetelement) {
    	RefOntoUML.GeneralizationSet genset1 = refcreator.createGeneralizationSet();
    	Map<String, Object> hashProp = mapper.getProperties(gensetelement);
    	
    	List<Generalization> generalizations = new ArrayList<Generalization>();
    	for (Object gen : (List<?>)hashProp.get("generalization")) {
    		generalizations.add((Generalization)elemMap.get((String)gen));
		}
    	hashProp.put("generalization", generalizations);
    	
    	refcreator.dealGeneralizationSet(genset1, hashProp);
    	elemMap.put(gensetelement.getAttribute(mapper.getIDName()), genset1);
    	
    	return genset1;
    }
    
    public static RefOntoUML.Dependency createDependency(Element dependelement) {
    	RefOntoUML.Dependency depend1 = refcreator.createDependency();
    	Map<String, Object> hashProp = mapper.getProperties(dependelement);
    	
    	List<RefOntoUML.NamedElement> clients = new ArrayList<RefOntoUML.NamedElement>();
    	for (Object client : (List<?>)hashProp.get("client")) {
    		clients.add((RefOntoUML.NamedElement)elemMap.get((String)client));
		}
    	hashProp.put("client", clients);
    	
    	List<RefOntoUML.NamedElement> suppliers = new ArrayList<RefOntoUML.NamedElement>();
    	for (Object supplier : (List<?>)hashProp.get("supplier")) {
    		suppliers.add((RefOntoUML.NamedElement)elemMap.get((String)supplier));
		}
    	hashProp.put("supplier", suppliers);
    	
    	refcreator.dealDependency(depend1, hashProp);
    	elemMap.put(dependelement.getAttribute(mapper.getIDName()), depend1);
    	
    	return depend1;
    }
    
    public static void main(String args[]) {
        try {
        	// Creates an instance of the parser that will read the XMI
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            //docBuilderFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(FILE_ADDRESS));
            
            // Identifies which program was the exporter of the XMI file and creates an instance of the specific reader
            mapper = identifyExporter(doc);
            
            // Set the xmi.id to be the ID element
            setID(doc.getDocumentElement());
            
            // Creates the mediator that will coordinate the transformation and starts it.
            Mediator mediator = new Mediator(refcreator, mapper);
            mediator.startTransformation(doc);
            
            // Saves XMI into file
            refcreator.saveXMI();
            
            System.out.println("Transformação realizada com sucesso!");
 
        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
 
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
 
        } catch (Throwable t) {
            t.printStackTrace();
        }// May have also ParserConfigurationException and IOException
    }
    
    //For the Element 'element', if it is an element node sets the xmi.id to be the ID
    public static void setID(Element element) {
    	
	    for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
	    	if (child.getNodeType() == Node.ELEMENT_NODE) {
	    		if (((Element)child).hasAttribute(mapper.getIDName())) {
	    			((Element)child).setIdAttribute(mapper.getIDName(), true);
	    		}
	    		setID((Element)child);
	    	}
	    }
    }

	private static Mapper identifyExporter(Document doc) {
		
		Element root = doc.getDocumentElement();
		NamedNodeMap nnm = root.getAttributes();
		String version = "";
		String exporter = "";
		
		//Get version of XMI document
		for (int i=0; i < nnm.getLength(); i++) {
			if (nnm.item(i).getNodeName().contains("version")) {
				version = nnm.item(i).getNodeValue();
				break;
			}
		}
		
		//For each version, identify the exporter
		if (version.equals("1.1")) {
			exporter = doc.getElementsByTagName("XMI.exporter").item(0).getFirstChild().getNodeValue();
			if (exporter.contains("Jomt")) {
				return new AstahMapper();
			}
		}
		return null;
		
	}
}