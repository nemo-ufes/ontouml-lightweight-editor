package old;

import java.io.IOException;
import java.util.HashMap;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Model;
import RefOntoUML.Property;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.transformation.RefOntoCreator;

public class ModeloRefOntoUML {
	
	public static void main(String[] args) {
		RefOntoCreator refcreator = new RefOntoCreator();
		refcreator.intialize("teste.refontouml");
		
		HashMap<String, Object> hashProp = new HashMap<String, Object>();
		
		hashProp.put("name", "model");
		Model model = refcreator.createModel();
		refcreator.dealModel(model, hashProp);
		
		hashProp.put("name", "kind1");
		Classifier classf = refcreator.createClass("kind");
		refcreator.dealClass((RefOntoUML.Class)classf, hashProp);
		refcreator.addPackagedElement(model, classf);
		
		hashProp.put("general", classf);
		
		hashProp.put("name", "role1");
		Classifier classf1 = refcreator.createClass("role");
		refcreator.dealClass((RefOntoUML.Class)classf1, hashProp);
		refcreator.addPackagedElement(model, classf1);
		
		hashProp.put("specific", classf1);
		Generalization gen1 = refcreator.createGeneralization();
		refcreator.dealGeneralization(gen1, hashProp);
		refcreator.addGeneralization(classf1, gen1);
		
		hashProp.put("name", "relator1");
		Classifier classf2 = refcreator.createClass("relator");
		refcreator.dealClass((RefOntoUML.Class)classf2, hashProp);
		refcreator.addPackagedElement(model, classf2);
		
		hashProp.put("name", "kind2");
		Classifier classf3 = refcreator.createClass("kind");
		refcreator.dealClass((RefOntoUML.Class)classf3, hashProp);
		refcreator.addPackagedElement(model, classf3);
		
		hashProp.put("general", classf3);
		
		hashProp.put("name", "role2");
		Classifier classf4 = refcreator.createClass("role");
		refcreator.dealClass((RefOntoUML.Class)classf4, hashProp);
		refcreator.addPackagedElement(model, classf4);
		
		hashProp.put("specific", classf4);
		Generalization gen2 = refcreator.createGeneralization();
		refcreator.dealGeneralization(gen2, hashProp);
		refcreator.addGeneralization(classf4, gen2);
		
		hashProp.put("name", "kind3");
		Classifier classf5 = refcreator.createClass("kind");
		refcreator.dealClass((RefOntoUML.Class)classf5, hashProp);
		refcreator.addPackagedElement(model, classf5);
		
		hashProp.put("name", "kind4");
		Classifier classf6 = refcreator.createClass("kind");
		refcreator.dealClass((RefOntoUML.Class)classf6, hashProp);
		refcreator.addPackagedElement(model, classf6);
		
		hashProp.put("name", "relator2");
		Classifier classf7 = refcreator.createClass("relator");
		refcreator.dealClass((RefOntoUML.Class)classf7, hashProp);
		refcreator.addPackagedElement(model, classf7);
		
		hashProp.put("name", "kind5");
		Classifier classf8 = refcreator.createClass("kind");
		refcreator.dealClass((RefOntoUML.Class)classf8, hashProp);
		refcreator.addPackagedElement(model, classf8);
		
		hashProp.put("name", "");
		Property prop;
		
		Association assoc1 = refcreator.createAssociation("mediation");
		refcreator.addPackagedElement(model, assoc1);
		hashProp.put("type", classf1);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc1, prop);
		hashProp.put("type", classf2);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc1, prop);
		
		Association assoc2 = refcreator.createAssociation("mediation");
		refcreator.addPackagedElement(model, assoc2);
		hashProp.put("type", classf4);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc2, prop);
		hashProp.put("type", classf2);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc2, prop);
		
		Association assoc3 = refcreator.createAssociation("mediation");
		refcreator.addPackagedElement(model, assoc3);
		hashProp.put("type", classf5);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc3, prop);
		hashProp.put("type", classf2);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc3, prop);
		
		Association assoc4 = refcreator.createAssociation("mediation");
		refcreator.addPackagedElement(model, assoc4);
		hashProp.put("type", classf6);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc4, prop);
		hashProp.put("type", classf7);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc4, prop);
		
		Association assoc5 = refcreator.createAssociation("mediation");
		refcreator.addPackagedElement(model, assoc5);
		hashProp.put("type", classf8);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc5, prop);
		hashProp.put("type", classf7);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc5, prop);
		
		Association assoc6 = refcreator.createAssociation("mediation");
		refcreator.addPackagedElement(model, assoc6);
		hashProp.put("type", classf2);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc6, prop);
		hashProp.put("type", classf7);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc6, prop);
		
		Association assoc7 = refcreator.createAssociation("material");
		refcreator.addPackagedElement(model, assoc7);
		hashProp.put("type", classf1);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc7, prop);
		hashProp.put("type", classf5);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc7, prop);
		
		Association assoc8 = refcreator.createAssociation("material");
		refcreator.addPackagedElement(model, assoc8);
		hashProp.put("type", classf4);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc8, prop);
		hashProp.put("type", classf5);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc8, prop);
		
		Association assoc9 = refcreator.createAssociation("material");
		refcreator.addPackagedElement(model, assoc9);
		hashProp.put("type", classf6);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc9, prop);
		hashProp.put("type", classf8);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc9, prop);
		
		Association assoc10 = refcreator.createAssociation("derivation");
		refcreator.addPackagedElement(model, assoc10);
		hashProp.put("type", classf2);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc10, prop);
		hashProp.put("type", assoc7);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc10, prop);
		
		Association assoc11 = refcreator.createAssociation("derivation");
		refcreator.addPackagedElement(model, assoc11);
		hashProp.put("type", classf2);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc11, prop);
		hashProp.put("type", assoc8);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc11, prop);
		
		Association assoc12 = refcreator.createAssociation("derivation");
		refcreator.addPackagedElement(model, assoc12);
		hashProp.put("type", classf7);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc12, prop);
		hashProp.put("type", assoc9);
		prop = refcreator.createProperty();
		refcreator.dealProperty(prop, hashProp);
		refcreator.addProperty(assoc12, prop);
		
		try {
			refcreator.saveXMI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
