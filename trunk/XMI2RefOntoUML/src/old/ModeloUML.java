package old;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

public class ModeloUML {
	
	public static UMLFactory factory = UMLFactory.eINSTANCE;
	
	public static void main(String[] args) {
		
		ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		rs.getPackageRegistry().put(UMLPackage.eNS_URI,UMLPackage.eINSTANCE);
		//RefOntoUMLPackageImpl.init();
		Resource resource = rs.createResource(URI.createURI("testeuml.uml"));
		
		URI baseUri = 
		URI.createURI("jar:file:/C:<PATH>\\org.eclipse.uml2.uml.resources-3.1.0.v201005031530.jar!/");
		URIConverter.URI_MAP.put(URI.createURI( UMLResource.LIBRARIES_PATHMAP ), 
			baseUri.appendSegment( "libraries" ).appendSegment( "" ));
		URIConverter.URI_MAP.put(URI.createURI( UMLResource.METAMODELS_PATHMAP 
			), baseUri.appendSegment( "metamodels" ).appendSegment( "" ));
		URIConverter.URI_MAP.put(URI.createURI( UMLResource.PROFILES_PATHMAP ), 
			baseUri.appendSegment( "profiles" ).appendSegment( "" ));
		
		Model model = factory.createModel();
		model.setName("model");
		resource.getContents().add(model);
		
		Classifier classf = factory.createClass();
		classf.setName("kind1");
		model.getPackagedElements().add(classf);
		
		Stereotype ster1 = factory.createStereotype();
		ster1.setName("kind");
		model.getPackagedElements().add(ster1);
		classf.applyStereotype(ster1);
		
		Classifier classf1 = factory.createClass();
		classf.setName("role1");
		model.getPackagedElements().add(classf1);
		
		Stereotype ster2 = factory.createStereotype();
		ster2.setName("role");
		model.getPackagedElements().add(ster2);
		classf1.applyStereotype(ster2);
		
		Generalization gen1 = factory.createGeneralization();
		gen1.setGeneral(classf);
		gen1.setSpecific(classf1);
		classf1.getGeneralizations().add(gen1);
		
		Classifier classf2 = factory.createClass();
		classf.setName("relator1");
		model.getPackagedElements().add(classf2);
		
		Stereotype ster3 = factory.createStereotype();
		ster3.setName("relator");
		model.getPackagedElements().add(ster3);
		classf2.applyStereotype(ster3);
		
		Classifier classf3 = factory.createClass();
		classf.setName("kind2");
		model.getPackagedElements().add(classf3);
		
		Stereotype ster4 = factory.createStereotype();
		ster4.setName("kind");
		model.getPackagedElements().add(ster4);
		classf3.applyStereotype(ster4);
		
		Classifier classf4 = factory.createClass();
		classf.setName("role2");
		model.getPackagedElements().add(classf4);
		
		Stereotype ster5 = factory.createStereotype();
		ster5.setName("role");
		model.getPackagedElements().add(ster5);
		classf4.applyStereotype(ster5);
		
		Generalization gen2 = factory.createGeneralization();
		gen1.setGeneral(classf3);
		gen1.setSpecific(classf4);
		classf4.getGeneralizations().add(gen2);
		
//		hashProp.put("name", "kind3");
//		Classifier classf5 = refcreator.createClass("kind");
//		refcreator.dealClass((RefOntoUML.Class)classf5, hashProp);
//		refcreator.addPackagedElement(model, classf5);
//		
//		hashProp.put("name", "kind4");
//		Classifier classf6 = refcreator.createClass("kind");
//		refcreator.dealClass((RefOntoUML.Class)classf6, hashProp);
//		refcreator.addPackagedElement(model, classf6);
//		
//		hashProp.put("name", "relator2");
//		Classifier classf7 = refcreator.createClass("relator");
//		refcreator.dealClass((RefOntoUML.Class)classf7, hashProp);
//		refcreator.addPackagedElement(model, classf7);
//		
//		hashProp.put("name", "kind5");
//		Classifier classf8 = refcreator.createClass("kind");
//		refcreator.dealClass((RefOntoUML.Class)classf8, hashProp);
//		refcreator.addPackagedElement(model, classf8);
//		
//		hashProp.put("name", "");
//		Property prop;
//		
//		Association assoc1 = refcreator.createAssociation("mediation");
//		refcreator.addPackagedElement(model, assoc1);
//		hashProp.put("type", classf1);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc1, prop);
//		hashProp.put("type", classf2);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc1, prop);
//		
//		Association assoc2 = refcreator.createAssociation("mediation");
//		refcreator.addPackagedElement(model, assoc2);
//		hashProp.put("type", classf4);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc2, prop);
//		hashProp.put("type", classf2);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc2, prop);
//		
//		Association assoc3 = refcreator.createAssociation("mediation");
//		refcreator.addPackagedElement(model, assoc3);
//		hashProp.put("type", classf5);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc3, prop);
//		hashProp.put("type", classf2);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc3, prop);
//		
//		Association assoc4 = refcreator.createAssociation("mediation");
//		refcreator.addPackagedElement(model, assoc4);
//		hashProp.put("type", classf6);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc4, prop);
//		hashProp.put("type", classf7);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc4, prop);
//		
//		Association assoc5 = refcreator.createAssociation("mediation");
//		refcreator.addPackagedElement(model, assoc5);
//		hashProp.put("type", classf8);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc5, prop);
//		hashProp.put("type", classf7);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc5, prop);
//		
//		Association assoc6 = refcreator.createAssociation("mediation");
//		refcreator.addPackagedElement(model, assoc6);
//		hashProp.put("type", classf2);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc6, prop);
//		hashProp.put("type", classf7);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc6, prop);
//		
//		Association assoc7 = refcreator.createAssociation("material");
//		refcreator.addPackagedElement(model, assoc7);
//		hashProp.put("type", classf1);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc7, prop);
//		hashProp.put("type", classf5);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc7, prop);
//		
//		Association assoc8 = refcreator.createAssociation("material");
//		refcreator.addPackagedElement(model, assoc8);
//		hashProp.put("type", classf4);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc8, prop);
//		hashProp.put("type", classf5);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc8, prop);
//		
//		Association assoc9 = refcreator.createAssociation("material");
//		refcreator.addPackagedElement(model, assoc9);
//		hashProp.put("type", classf6);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc9, prop);
//		hashProp.put("type", classf8);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc9, prop);
//		
//		Association assoc10 = refcreator.createAssociation("derivation");
//		refcreator.addPackagedElement(model, assoc10);
//		hashProp.put("type", classf2);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc10, prop);
//		hashProp.put("type", assoc7);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc10, prop);
//		
//		Association assoc11 = refcreator.createAssociation("derivation");
//		refcreator.addPackagedElement(model, assoc11);
//		hashProp.put("type", classf2);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc11, prop);
//		hashProp.put("type", assoc8);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc11, prop);
//		
//		Association assoc12 = refcreator.createAssociation("derivation");
//		refcreator.addPackagedElement(model, assoc12);
//		hashProp.put("type", classf7);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc12, prop);
//		hashProp.put("type", assoc9);
//		prop = refcreator.createProperty();
//		refcreator.dealProperty(prop, hashProp);
//		refcreator.addProperty(assoc12, prop);
		
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			System.out.println("erro aqui");
			e.printStackTrace();
		}
	}
}
