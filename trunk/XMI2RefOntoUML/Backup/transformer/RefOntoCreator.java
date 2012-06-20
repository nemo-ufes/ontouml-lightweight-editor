package transformer;

import java.io.IOException;
import java.util.*;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import RefOntoUML.*;
import RefOntoUML.Class;
import RefOntoUML.impl.*;

public class RefOntoCreator {
	
	public Resource resource;
	public RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
	public Model model;
	
	public void intialize() {

		//Criar um ResourceSet para "gerenciar" o resource do modelo
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,RefOntoUML.RefOntoUMLPackage.eINSTANCE);
		RefOntoUMLPackageImpl.init();
	
		//Cria um novo modelo (Resource) na URL especificada
		resource = resourceSet.createResource(URI.createURI("teste.xmi"));

		//Cria Modelo
		model = factory.createModel();
		
		resource.getContents().add(model);
		
	}
	
	public void dealModel(Element model_element) {
		
	}
	
	public void saveXMI() {
		
		try {
			//Salvar o modelo em XMI
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Class createClass(String stereotype, HashMap<String, String> hashProp) {
		
		Class newclass = null;
		
		//Criar elementos
		if (stereotype.equalsIgnoreCase("kind")) {
    		newclass = factory.createKind();
    	}
    	if (stereotype.equalsIgnoreCase("subkind")) {
    		newclass = factory.createSubKind();
    	}
    	if (stereotype.equalsIgnoreCase("role")) {
    		newclass = factory.createRole();
    	}
    	if (stereotype.equalsIgnoreCase("phase")) {
    		newclass = factory.createPhase();
    	}
    	if (stereotype.equalsIgnoreCase("category")) {
    		newclass = factory.createCategory();
    	}
    	if (stereotype.equalsIgnoreCase("collective")) {
    		Collective collective1 = factory.createCollective();
    		collective1.setIsExtensional(Boolean.parseBoolean(hashProp.get("isExtensional")));
    		newclass = collective1;
    	}
//    	if (stereotype.equalsIgnoreCase("datatype")) {
//    		newclass = factory.createKind();
//    	}
    	if (stereotype.equalsIgnoreCase("mixin")) {
    		newclass = factory.createMixin();
    	}
    	if (stereotype.equalsIgnoreCase("mode")) {
    		newclass = factory.createMode();
    	}
    	if (stereotype.equalsIgnoreCase("quantity")) {
    		newclass = factory.createQuantity();
    	}
    	if (stereotype.equalsIgnoreCase("relator")) {
    		newclass = factory.createRelator();
    	}
    	if (stereotype.equalsIgnoreCase("rolemixin")) {
    		newclass = factory.createRoleMixin();
    	}
    	
    	dealClassifier(newclass, hashProp);
    	
    	newclass.setIsActive(Boolean.parseBoolean(hashProp.get("isActive")));
    	
    	
    	
    	//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(newclass);
		
		return newclass;
	}
	
	public Association createAssociation(String stereotype, HashMap<String, String> hashProp) {
		
		Association newassoc = null;
		
		if (stereotype.equalsIgnoreCase("characterization")) {
			newassoc = factory.createCharacterization();
    	}
    	if (stereotype.equalsIgnoreCase("componentof")) {
    		newassoc = factory.createcomponentOf();
    	}
    	if (stereotype.equalsIgnoreCase("formal")) {
    		newassoc = factory.createFormalAssociation();
    	}
    	if (stereotype.equalsIgnoreCase("derivation")) {
    		newassoc = factory.createDerivation();
    	}
    	if (stereotype.equalsIgnoreCase("material")) {
    		newassoc = factory.createMaterialAssociation();
    	}
    	if (stereotype.equalsIgnoreCase("mediation")) {
    		newassoc = factory.createMediation();
    	}
    	if (stereotype.equalsIgnoreCase("memberof")) {
    		newassoc = factory.creatememberOf();
    	}
    	if (stereotype.equalsIgnoreCase("subcollectionof")) {
    		newassoc = factory.createsubCollectionOf();
    	}
    	if (stereotype.equalsIgnoreCase("subquantityof")) {
    		newassoc = factory.createsubQuantityOf();
    	}
    	
    	dealClassifier(newassoc, hashProp);
    	
    	newassoc.setIsDerived(Boolean.parseBoolean(hashProp.get("isDerived")));
    	
    	//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(newassoc);
		
		return newassoc;
	}
	
	public Property createProperty(HashMap<String, String> hashProp) {
		Property property = factory.createProperty();
		dealStructFeature(property, hashProp);
		//property.setIsDerived(Boolean.parseBoolean(hashProp.get("isDerived")));
		//property.setIsDerivedUnion(Boolean.parseBoolean(hashProp.get("isDerivedUnion")));
		//property.setDefault(value);
		//property.setIsComposite(Boolean.parseBoolean(hashProp.get("isComposite")));
		property.setAggregation(AggregationKind.get(hashProp.get("aggregation")));
		return property;
	}
	
	public Generalization createGeneralization() {
		Generalization generalization = factory.createGeneralization();
		//generalization.setIsSubstitutable(Boolean.parseBoolean(hashProp.get("isSubstitutable")));
		return generalization;
	}
	
	public void dealClassifier (Classifier c, HashMap<String, String> hashProp) {
		dealRedefElement(c, hashProp);
		c.setIsAbstract(Boolean.parseBoolean(hashProp.get("isAbstract")));
	}
	
	public void dealStructFeature (StructuralFeature stf, HashMap<String, String> hashProp) {
		dealFeature(stf, hashProp);
		//stf.setIsReadOnly(Boolean.parseBoolean(hashProp.get("isReadOnly")));
	}
	
	public void dealFeature (StructuralFeature feat, HashMap<String, String> hashProp) {
		dealRedefElement(feat, hashProp);
		//feat.setIsReadOnly(Boolean.parseBoolean(hashProp.get("isStatic")));
	}
	
	public void dealRedefElement (RedefinableElement rel, HashMap<String, String> hashProp) {
		dealNamedElement(rel, hashProp);
		rel.setIsLeaf(Boolean.parseBoolean(hashProp.get("isLeaf")));
	}
	
	public void dealNamedElement (NamedElement nel, HashMap<String, String> hashProp) {
		nel.setName(hashProp.get("name"));
		nel.setVisibility(VisibilityKind.get(hashProp.get("visibility")));
	}

}
