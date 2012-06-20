package transformer;

import java.lang.String;
import java.util.HashMap;
import java.io.File;

import org.w3c.dom.*;
 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class Parser {

    final static String FILE_ADDRESS = "sampleAstah.xmi";
    
    public static void createClasses(Reader reader, Document doc, RefOntoCreator refcreator) {
    	
    	//Get all the classes in the document
    	NodeList listClass = reader.getClasses(doc);
        
        for (int i = 0; i < listClass.getLength(); i++) {
        	
        	//For each element in the list, gets the Stereotype
        	Element e = (Element) listClass.item(i);
        	String stereotype = reader.getStereotype(doc, e);
        	
        	HashMap<String, String> hashProp = getProperties(e, reader);
        	
        	//Calls the creator to create the class with the right stereotype
        	RefOntoUML.Class newclass = refcreator.createClass(stereotype, hashProp);
        	
        	//Get the class attributes (the ones created by users)
        	NodeList listAtt = reader.getClassAttributes(e);
        	for (int j = 0; j < listAtt.getLength(); j++) {
        		HashMap<String, String> hashPropAtt = getProperties((Element)listAtt.item(j), reader);
	        	RefOntoUML.Property newatt = refcreator.createProperty(hashPropAtt);
	        	newclass.getOwnedAttribute().add(newatt);
        	}
        	
        	NodeList listGen = reader.getClassGeneralizations(doc, e);
        	for (int j = 0; j < listGen.getLength(); j++) {
	        	RefOntoUML.Generalization newgen = refcreator.createGeneralization();
	        	newclass.getGeneralization().add(newgen);
        	}
        }
    }
    
    // Get the class properties (the ones defined in the metamodel) and adds to a HashMap
    // HashMap<Name, Value> of the attribute
    public static HashMap<String, String> getProperties(Element elem, Reader reader) {
    	NamedNodeMap listProp = reader.getProperties(elem);
    	HashMap<String, String> hashProp = new HashMap<String, String>();
    	for (int j = 0; j < listProp.getLength(); j++) {
    		hashProp.put(listProp.item(j).getNodeName(), listProp.item(j).getNodeValue());
    	}
    	return hashProp;
    }
    
    public static void createAssociations(Reader reader, Document doc, RefOntoCreator refcreator) {
    	
    	//Get all the associations in the document
        NodeList listAssoc = reader.getAssociations(doc);
        
        for (int i = 0; i < listAssoc.getLength(); i++) {
        	
        	//For each element in the list, gets the Stereotype
        	Element e = (Element) listAssoc.item(i);
        	//MUDAR ESSE IF, ESTÁ ESPECÍFICO DO ASTAH. TEM QUE ARRUMAR UM JEITO DE EXCLUIR NODE DO NODELIST
        	if (!e.hasChildNodes()) {
        		continue;
        	}
        	String stereotype = reader.getStereotype(doc, e);
        	
        	HashMap<String, String> hashProp = getProperties(e, reader);
        	
        	//Calls the creator to create the class with the right stereotype
        	RefOntoUML.Association newassoc = refcreator.createAssociation(stereotype, hashProp);
        }
    }
 
    public static void main(String args[]) {
        try {
        	//Creates an instance of the parser that will read the XMI
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            //docBuilderFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(FILE_ADDRESS));
            
            //Identifies which program was the exporter of the XMI file and creates an instance of the specific reader
            Reader reader = identifyExporter(doc);
            
            //Set the xmi.id to be the ID element
            reader.setID(doc.getDocumentElement());
            
            //Creates an instance of the Class that will map the UML elements to the OntoUML elements
            RefOntoCreator refcreator = new RefOntoCreator();
            
            //Initializes the model
            refcreator.intialize();
            
            // Cria o modelo propriamente dito
            Element model = reader.getModelElement(doc);
            refcreator.dealModel(model);
            
            createClasses(reader, doc, refcreator);
            System.exit(1);
            
            createAssociations(reader, doc, refcreator);
            
            //Saves XMI into file
            refcreator.saveXMI();
 
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

	private static Reader identifyExporter(Document doc) {
		
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
				return new AstahReader();
			}
		}
		return null;
		
	}
}