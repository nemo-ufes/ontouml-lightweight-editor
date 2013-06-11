package br.ufes.inf.nemo.xmi2refontouml.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import RefOntoUML.Derivation;
import br.ufes.inf.nemo.xmi2refontouml.core.RefOntoCreator.RefOntoUMLException;
import br.ufes.inf.nemo.xmi2refontouml.util.ElementType;


/**
 * This is the transformation main class, responsible for mediating
 * all the transformation. It communicates with the Mapper to get
 * the specific elements that will become RefOntoUML elements,
 * according to the metamodel rules.
 * 
 * @author Vinicius
 *
 */

public class Mediator {
	
	// File addresses
	public String READ_FILE_ADDRESS;
	public String SAVE_FILE_ADDRESS;
	// Mapeia o id dos elementos para elementos RefOntoUML. � utilizado como auxiliar para se criar refer�ncias,
	// como por exemplo em generalizations e associations.
	public static HashMap<String, RefOntoUML.Element> elemMap = new HashMap<String, RefOntoUML.Element>();
	// Leitor de arquivo XMI que conhece as tags espec�ficas do programa que exportou o arquivo
	public Mapper mapper;
	// Inst�ncia da classe que cria os objetos RefOntoUML
    private RefOntoCreator refcreator = new RefOntoCreator();

    public static String errorLog = "";
    public static String warningLog = "";
    
    public void initialize() throws Exception {
        //Call the factory to read the Document and decide which Mapper
        //to create, depending on the program/exporter of the XMI
    	errorLog = "";
    	warningLog = "";
    	
    	MapperFactory mapperFactory = new MapperFactory();
    	mapper = mapperFactory.createMapper(new File(READ_FILE_ADDRESS));
    	if (mapper == null) {
    		Exception e = new Exception(errorLog);
        	throw e;
    	}
    	
    	//Resets the element map
    	elemMap = new HashMap<String, RefOntoUML.Element>();
    }
    
    public void save() {
        try {
			refcreator.saveXMI();
		} catch (IOException e) {
			errorLog += "Error saving file.\n";
			e.printStackTrace();
		}
    }
    
    public RefOntoUML.Model parse() throws Exception {
    	
    	initialize();
    	
    	//Initializes the model
        refcreator.intialize(SAVE_FILE_ADDRESS);
        
        // Cria o modelo propriamente dito
        Object modelelement = mapper.getModelElement();
        RefOntoUML.Model model = (RefOntoUML.Model) createElement(modelelement, ElementType.MODEL);
        dealModel();
        return model;
    }
    
    protected RefOntoUML.Element createElement(Object element, ElementType elemType) throws Exception {
    	RefOntoUML.Element elem1 = null;
    	String stereotype = null;
    	
    	switch (elemType) {
    		case MODEL:
    			elem1 = refcreator.createModel();
    			if (doPackage((RefOntoUML.Model)elem1, element) == null)
    				return null;
    			break;
    			
    		case PACKAGE:
    			elem1 = refcreator.createPackage();
    			if (doPackage((RefOntoUML.Package)elem1, element) == null)
    				return null;
    			break;
    			
    		case PRIMITIVE:
    			//System.out.println("Debug: criando primitive name = " + mapper.getName(element));
    			if (mapper.getName(element).contains("int")) {
    				elem1 = RefOntoCreator.INTEGER_PRIMITIVE;
            	} else if (mapper.getName(element).contains("bool")) {
            		elem1 = RefOntoCreator.BOOLEAN_PRIMITIVE;
            	} else if (mapper.getName(element).contains("str") || mapper.getName(element).equalsIgnoreCase("char")) {
            		elem1 = RefOntoCreator.STRING_PRIMITIVE;
            	} else if (mapper.getName(element).contains("unlimited") || mapper.getName(element).equalsIgnoreCase("float")) {
            		elem1 = RefOntoCreator.UNLIMITED_NATURAL_PRIMITIVE;
            	} else {
            		elem1 = refcreator.createPrimitiveType();
            	}
    			break;
    			
    		case DATATYPE:
    			elem1 = refcreator.createDataType();
    			doClassifier(element, (RefOntoUML.Classifier)elem1);
    			break;
    			
    		case CLASS:
				stereotype = mapper.getStereotype(element);
	    		elem1 = refcreator.createClass(stereotype);
    			doClassifier(element, (RefOntoUML.Classifier)elem1);
    			break;
    			
    		case PROPERTY:
    			elem1 = refcreator.createProperty();
    			break;
    			
    		case ENUMERATION:
    			elem1 = refcreator.createEnumeraion();
    			doClassifier(element, (RefOntoUML.Classifier)elem1);
    			break;
    			
    		case ENUMLITERAL:
    			elem1 = refcreator.createEnumLiteral();
    			break;
    			
    		case ASSOCIATION:
				stereotype = mapper.getStereotype(element);
    			elem1 = refcreator.createAssociation(stereotype);
    			doClassifier(element, (RefOntoUML.Classifier)elem1);
    			break;
    			
    		case GENERALIZATION:
    			elem1 = refcreator.createGeneralization();
    			break;
    			
    		case GENERALIZATIONSET:
    			elem1 = refcreator.createGeneralizationSet();
    			break;
    			
    		case DEPENDENCY:
    			elem1 = refcreator.createDependency();
    			break;
    			
    		case COMMENT:
    			elem1 = refcreator.createComment();
    			break;
			default:
				break;
    	}
    	
    	if (elem1 != null) {
    		String name = mapper.getName(element);
        	if (elem1 instanceof RefOntoUML.NamedElement && !(elem1 instanceof RefOntoUML.PrimitiveType)) {
        		refcreator.setName((RefOntoUML.NamedElement)elem1, name);
        	}
        	
        	//Every Element can have a Comment
	    	List<Object> listComment = mapper.getElements(element, ElementType.COMMENT);
	        for (Object e : listComment) {
		        RefOntoUML.Comment comment1 = (RefOntoUML.Comment) createElement(e, ElementType.COMMENT);
		        refcreator.addComment(elem1, comment1);
	        }
	        //Ecore specific element for saving arbitrary information
	        List<Object> listAnnotations = mapper.getElements(element, ElementType.ANNOTATION);
	        for (Object e : listAnnotations) {
	        	String annot = (String) e;
		        refcreator.addAnnotation(elem1, annot);
	        }
	        if (mapper.getID(element) == "") {
	        	String error = "XMI file is invalid. Element with no ID found.\n" +
	        			"Element Type: " + elemType + ".\n" +
	        			"Element Name: " + mapper.getName(element) + ".\n" +
	        			"Element Path: " + mapper.getPath(element);
	        	throw new Exception(error);
	        }
	        elemMap.put(mapper.getID(element), elem1);
    	}
    	
    	return elem1;
    }
    
    protected void dealModel () {
    	for (Entry<String, RefOntoUML.Element> entry : elemMap.entrySet()) {
    		RefOntoUML.Element modelElem = entry.getValue();
    		String id = entry.getKey();
    		if (modelElem instanceof Derivation)
    		{
    			continue;
    		}
    		Object domElem = mapper.getElementById(id);
    		// Get the properties of the element (the ones defined in the metamodel)
        	Map<String, Object> hashProp = mapper.getProperties(domElem);
        	
    		if (modelElem instanceof RefOntoUML.Model) {
    			refcreator.dealModel((RefOntoUML.Model)modelElem, hashProp);
    			
    		} else if (modelElem instanceof RefOntoUML.Package) {
    			refcreator.dealPackage((RefOntoUML.Package)modelElem, hashProp);
    			
    		} else if (modelElem instanceof RefOntoUML.Class) {
	    		refcreator.dealClass((RefOntoUML.Class)modelElem, hashProp);
	    		
    		} else if (modelElem instanceof RefOntoUML.DataType) {
    			refcreator.dealDataType((RefOntoUML.DataType)modelElem, hashProp);
    			
    		} else if (modelElem instanceof RefOntoUML.Property) {
    			doProperty(hashProp);
    			refcreator.dealProperty((RefOntoUML.Property)modelElem, hashProp);
    			
    		} else if (modelElem instanceof RefOntoUML.EnumerationLiteral) {
    			refcreator.dealEnumLiteral((RefOntoUML.EnumerationLiteral)modelElem, hashProp);
    			
    		} else if (modelElem instanceof RefOntoUML.Association) {
    			doAssociation(hashProp);
    			refcreator.dealAssociation((RefOntoUML.Association)modelElem, hashProp);
    			
    		} else if (modelElem instanceof RefOntoUML.Generalization) {
    			doGeneralization(hashProp);
    			refcreator.dealGeneralization((RefOntoUML.Generalization)modelElem, hashProp);
    			
    		} else if (modelElem instanceof RefOntoUML.GeneralizationSet) {
    			doGeneralizationSet(hashProp);
    			refcreator.dealGeneralizationSet((RefOntoUML.GeneralizationSet)modelElem, hashProp);
    			
    		} else if (modelElem instanceof RefOntoUML.Dependency) {
    			doDependency(hashProp);
    			refcreator.dealDependency((RefOntoUML.Dependency)modelElem, hashProp);
    			
    		} else if (modelElem instanceof RefOntoUML.Comment) {
    			doComment(hashProp);
    			refcreator.dealComment((RefOntoUML.Comment)modelElem, hashProp);
    			
    		}
    	}
    }
    
    protected RefOntoUML.Package doPackage(RefOntoUML.Package pack, Object element) throws Exception {
    	// Create the Packageable Elements Inside the package
    	try {
    		// Create Primitive Types first
    		packageIterator(pack, element, ElementType.PRIMITIVE);
    		
            // Create nested Packages
            packageIterator(pack, element, ElementType.PACKAGE);
            
	        // Create Classes
            packageIterator(pack, element, ElementType.CLASS);

	        // Create DataTypes
            packageIterator(pack, element, ElementType.DATATYPE);
            
	        // Create Enumerations
            packageIterator(pack, element, ElementType.ENUMERATION);

	        // Create Associations
            packageIterator(pack, element, ElementType.ASSOCIATION);

	        // Create Generalization Sets
            packageIterator(pack, element, ElementType.GENERALIZATIONSET);

            // Create Dependencies
            //packageIterator(pack, element, ElementType.DEPENDENCY);

    	} catch (NullPointerException npe) {
    		npe.printStackTrace();
    		Mediator.warningLog += "Warning: Empty package '" + pack.getName() + "'.\n";
    		
    	}
        
        return pack;
    }
    
    protected void packageIterator(RefOntoUML.Package pack, Object domParent, ElementType type) throws Exception {
		List<Object> listPacks = mapper.getElements(domParent, type);
        for (Object e : listPacks) {
        	try {
        		
        		Object refOntoElement = createElement(e, type);
				refcreator.addPackagedElement(pack, (RefOntoUML.PackageableElement) refOntoElement);
				
				if (refOntoElement instanceof RefOntoUML.MaterialAssociation) {
	        		doMaterial((RefOntoUML.MaterialAssociation)refOntoElement, e, pack);
	        	}
				
        	} catch (RefOntoUMLException refe) {
        		errorLog = refe.getError();
        		errorLog += "Path of the element with error: " + mapper.getPath(e) + "\n";
        		if (mapper.getType(e) == ElementType.ASSOCIATION) {
        			List<Object> listProp = mapper.getElements(e, ElementType.PROPERTY);
    				String ends = "Ends: ";
    		    	for (Object p : listProp) {
    		    		Map<String, Object> hashProp = mapper.getProperties(p);
    		    		ends += mapper.getName(mapper.getElementById((String)hashProp.get("type"))) + ", ";
    		    	}
    		    	errorLog += ends;
        		}
            	Exception ex = new Exception(errorLog);
            	throw ex;
        	}
        }
    }
    
    protected void doClassifier(Object classelement, RefOntoUML.Classifier class1) throws Exception {
    	
		// Get the classifier properties (they can be attributes or association ends)
    	if (class1 instanceof RefOntoUML.Enumeration) {
    		List<Object> listLit = mapper.getElements(classelement, ElementType.ENUMLITERAL);
        	for (Object e : listLit) {
        		RefOntoUML.EnumerationLiteral lit1 = (RefOntoUML.EnumerationLiteral) createElement(e, ElementType.ENUMLITERAL);
        		refcreator.addEnumLiteral((RefOntoUML.Enumeration)class1, lit1);
        	}
    	} else {
	    	List<Object> listProp = mapper.getElements(classelement, ElementType.PROPERTY);
	    	for (Object e : listProp) {
	    		RefOntoUML.Property prop1 = (RefOntoUML.Property) createElement(e, ElementType.PROPERTY);
	    		refcreator.addProperty((RefOntoUML.Classifier)class1, prop1);
	    	}
    	}
    	// Get the classifier generalizations
    	List<Object> listGen = mapper.getElements(classelement, ElementType.GENERALIZATION);
    	for (Object e : listGen) {
        	RefOntoUML.Generalization gen1 = (RefOntoUML.Generalization) createElement(e, ElementType.GENERALIZATION);
        	refcreator.addGeneralization(class1, gen1);
        }
    }
    
    protected void doAssociation (Map<String, Object> hashProp) {
    	
    	List<RefOntoUML.Property> memberEnds = new ArrayList<RefOntoUML.Property>();
    	for (Object memberEnd : (List<?>)hashProp.get("memberend")) {
    		memberEnds.add((RefOntoUML.Property)elemMap.get((String)memberEnd));
		}
    	hashProp.put("memberend", memberEnds);
    }
    
    protected void doProperty (Map<String, Object> hashProp) {
    	
    	// Gets the RefOntoUML.Element that is the 'type' of the property
    	if (hashProp.get("type") == null)
    	{
    		return;
    	}
    	else if (hashProp.get("type").equals("Integer"))
    	{
    		hashProp.put("type", RefOntoCreator.INTEGER_PRIMITIVE);
    	}
    	else if (hashProp.get("type").equals("String"))
    	{
    		hashProp.put("type", RefOntoCreator.STRING_PRIMITIVE);
    	}
    	else if (hashProp.get("type").equals("Boolean"))
    	{
    		hashProp.put("type", RefOntoCreator.BOOLEAN_PRIMITIVE);
    	}
    	else if (hashProp.get("type").equals("UnlimitedNatural"))
    	{
    		hashProp.put("type", RefOntoCreator.UNLIMITED_NATURAL_PRIMITIVE);
    	}
    	else
    		hashProp.put("type", elemMap.get(hashProp.get("type")));
    }
    
    protected void doGeneralization (Map<String, Object> hashProp) {
    	
    	// Gets the RefOntoUML.Element that is the 'general' of the generalization
    	hashProp.put("general", elemMap.get(hashProp.get("general")));
    	hashProp.put("specific", elemMap.get(hashProp.get("specific")));
    }
    
    protected void doGeneralizationSet (Map<String, Object> hashProp) {
    	
    	// Gets the RefOntoUML.Element that is the 'general' of the generalization
    	List<RefOntoUML.Generalization> generalizations = new ArrayList<RefOntoUML.Generalization>();
    	for (Object gen : (List<?>)hashProp.get("generalization")) {
    		generalizations.add((RefOntoUML.Generalization)elemMap.get((String)gen));
		}
    	hashProp.put("generalization", generalizations);
    }
    
    protected void doDependency(Map<String, Object> hashProp) {
    	
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
    }
    
    protected void doComment(Map<String, Object> hashProp) {
    	List<RefOntoUML.Element> annotatedElements = new ArrayList<RefOntoUML.Element>();
    	for (Object annotElem : (List<?>)hashProp.get("annotatedelement")) {
    		annotatedElements.add((RefOntoUML.Element)elemMap.get((String)annotElem));
		}
    	hashProp.put("annotatedelement", annotatedElements);
    }
    
    protected void doMaterial(RefOntoUML.MaterialAssociation material, Object materialObject, 
    		RefOntoUML.Package pack) throws RefOntoUMLException {
    	String relatorID = mapper.getRelatorfromMaterial(materialObject);
    	if (relatorID != null && relatorID != "") {
    		RefOntoUML.Derivation der = (RefOntoUML.Derivation) refcreator.createAssociation("Derivation");
    		RefOntoUML.Relator relator = (RefOntoUML.Relator) elemMap.get(relatorID);
    		
    		elemMap.put("derivation"+relatorID, der);
    		
        	Map<String, Object> hashProp = new HashMap<String, Object>();
//        	hashProp.put("name", "Derivation_" + material.getName() + "_" + relator.getName());
//        	refcreator.dealAssociation(der, hashProp);
        	
        	RefOntoUML.Property prop1 = refcreator.createProperty();
        	hashProp.put("type", material);
        	hashProp.put("lower", "1");
        	hashProp.put("upper", "1");
        	refcreator.addProperty(der, prop1);
        	refcreator.dealProperty(prop1, hashProp);
        	
        	RefOntoUML.Property prop2 = refcreator.createProperty();
        	hashProp.put("type", relator);
        	refcreator.addProperty(der, prop2);
        	refcreator.dealProperty(prop2, hashProp);
        	
        	refcreator.addPackagedElement(pack, der);
    	}
    }
}
