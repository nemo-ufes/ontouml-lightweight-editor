package transformer;

import java.io.IOException;
import java.util.Collections;

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
	
	public Model intialize() {

		//Criar um ResourceSet para "gerenciar" o resource do modelo
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,RefOntoUML.RefOntoUMLPackage.eINSTANCE);
		RefOntoUMLPackageImpl.init();
	
		//Cria um novo modelo (Resource) na URL especificada
		resource = resourceSet.createResource(URI.createURI("teste.xmi"));

		//Cria Modelo
		Model model = factory.createModel();
		
		resource.getContents().add(model);
		
		return model;
		
	}
	
	public void saveXMI() {
		
		try {
			//Salvar o modelo em XMI
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Class createClass (Model model, String stereotype, String name, boolean isAbstract,
			boolean isActive, boolean isLeaf, VisibilityKind visibility) {
		
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
    		newclass = factory.createCollective();
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
    	
    	newclass.setName(name);
    	newclass.setIsAbstract(isAbstract);
    	newclass.setIsActive(isActive);
    	newclass.setIsLeaf(isLeaf);
    	newclass.setVisibility(visibility);
    	
    	//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(newclass);
		
		return newclass;
	}
	
	public void createKind(Model model, String name, boolean isAbstract, boolean isActive, boolean isLeaf, VisibilityKind visibility) {
		
		//Criar elementos
		Kind kind1 = factory.createKind();
		kind1.setName(name);
		kind1.setIsAbstract(isAbstract);
		kind1.setIsActive(isActive);
		kind1.setIsLeaf(isLeaf);
		kind1.setVisibility(visibility);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(kind1);

	}
	
	public void createSubkind(Model model, String name, boolean isAbstract, boolean isActive, boolean isLeaf, VisibilityKind visibility) {
		
		//Criar elementos
		SubKind subkind1 = factory.createSubKind();
		subkind1.setName(name);
		subkind1.setIsAbstract(isAbstract);
		subkind1.setIsActive(isActive);
		subkind1.setIsLeaf(isLeaf);
		subkind1.setVisibility(visibility);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(subkind1);		

	}
	
	public void createRole(Model model, String name, boolean isAbstract, boolean isActive, boolean isLeaf, VisibilityKind visibility) {
		
		//Criar elementos
		Role role1 = factory.createRole();
		role1.setName(name);
		role1.setIsAbstract(isAbstract);
		role1.setIsActive(isActive);
		role1.setIsLeaf(isLeaf);
		role1.setVisibility(visibility);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(role1);		

	}

	public void createPhase(Model model, String name, boolean isAbstract, boolean isActive, boolean isLeaf, VisibilityKind visibility) {
	
		//Criar elementos
		Phase phase1 = factory.createPhase();
		phase1.setName(name);
		phase1.setIsAbstract(isAbstract);
		phase1.setIsActive(isActive);
		phase1.setIsLeaf(isLeaf);
		phase1.setVisibility(visibility);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(phase1);
	}
	
	public void createCategory(Model model, String name, boolean isAbstract, boolean isActive, boolean isLeaf, VisibilityKind visibility) {
		
		//Criar elementos
		Category category1 = factory.createCategory();
		category1.setName(name);
		category1.setIsAbstract(isAbstract);
		category1.setIsActive(isActive);
		category1.setIsLeaf(isLeaf);
		category1.setVisibility(visibility);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(category1);
	}
	
	public void createCollective(Model model, String name, boolean isAbstract, boolean isActive, boolean isLeaf, VisibilityKind visibility, boolean isExtensional) {
		
		//Criar elementos
		Collective collective1 = factory.createCollective();
		collective1.setName(name);
		collective1.setIsAbstract(isAbstract);
		collective1.setIsActive(isActive);
		collective1.setIsLeaf(isLeaf);
		collective1.setVisibility(visibility);
		collective1.setIsExtensional(isExtensional);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(collective1);
	}
	
	public void createDatatype(Model model, String name, boolean isAbstract, boolean isLeaf, VisibilityKind visibility) {
		
		//Criar elementos
		DataType datatype1 = factory.createDataType();
		datatype1.setName(name);
		datatype1.setIsAbstract(isAbstract);
		datatype1.setIsLeaf(isLeaf);
		datatype1.setVisibility(visibility);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(datatype1);
	}
	
	public void createMixin(Model model, String name, boolean isAbstract, boolean isActive, boolean isLeaf, VisibilityKind visibility) {
		
		//Criar elementos
		Mixin mixin1 = factory.createMixin();
		mixin1.setName(name);
		mixin1.setIsAbstract(isAbstract);
		mixin1.setIsActive(isActive);
		mixin1.setIsLeaf(isLeaf);
		mixin1.setVisibility(visibility);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(mixin1);
	}
	
	public void createMode(Model model, String name, boolean isAbstract, boolean isActive, boolean isLeaf, VisibilityKind visibility) {
		
		//Criar elementos
		Mode mode1 = factory.createMode();
		mode1.setName(name);
		mode1.setIsAbstract(isAbstract);
		mode1.setIsActive(isActive);
		mode1.setIsLeaf(isLeaf);
		mode1.setVisibility(visibility);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(mode1);
	}
	
	public void createRelator(Model model, String name, boolean isAbstract, boolean isActive, boolean isLeaf, VisibilityKind visibility) {
		
		//Criar elementos
		Relator relator1 = factory.createRelator();
		relator1.setName(name);
		relator1.setIsAbstract(isAbstract);
		relator1.setIsActive(isActive);
		relator1.setIsLeaf(isLeaf);
		relator1.setVisibility(visibility);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(relator1);
	}
	
	public void createQuantity(Model model, String name, boolean isAbstract, boolean isActive, boolean isLeaf, VisibilityKind visibility) {
		
		//Criar elementos
		Quantity quantity1 = factory.createQuantity();
		quantity1.setName(name);
		quantity1.setIsAbstract(isAbstract);
		quantity1.setIsActive(isActive);
		quantity1.setIsLeaf(isLeaf);
		quantity1.setVisibility(visibility);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(quantity1);
	}
	
	public void createRoleMixin(Model model, String name, boolean isAbstract, boolean isActive, boolean isLeaf, VisibilityKind visibility) {
		
		//Criar elementos
		RoleMixin rolemixin1 = factory.createRoleMixin();
		rolemixin1.setName(name);
		rolemixin1.setIsAbstract(isAbstract);
		rolemixin1.setIsActive(isActive);
		rolemixin1.setIsLeaf(isLeaf);
		rolemixin1.setVisibility(visibility);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(rolemixin1);
	}
	
	public void createCharacterization(Model model, String name) {
		
		//Criar elementos
		Characterization characterization1 = factory.createCharacterization();
		characterization1.setName(name);
		//characterization1.getMemberEnd().add(arg0)
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(characterization1);
	}
	
	public void createDerivation(Model model, String name) {
		
		//Criar elementos
		Derivation derivation1 = factory.createDerivation();
		derivation1.setName(name);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(derivation1);
	}
	
	public void createMaterialAssociation(Model model, String name) {
		
		//Criar elementos
		MaterialAssociation materialassociation1 = factory.createMaterialAssociation();
		materialassociation1.setName(name);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(materialassociation1);
	}
	
	public void createFormalAssociation(Model model, String name) {
		
		//Criar elementos
		FormalAssociation formalassociation1 = factory.createFormalAssociation();
		formalassociation1.setName(name);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(formalassociation1);
	}
	
	public void createMediation(Model model, String name) {
		
		//Criar elementos
		Mediation mediation1 = factory.createMediation();
		mediation1.setName(name);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(mediation1);
	}
	
	public void createComponentOf(Model model, String name) {
		
		//Criar elementos
		componentOf componentof1 = factory.createcomponentOf();
		componentof1.setName(name);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(componentof1);
	}
	
	public void createMemberOf(Model model, String name) {
		
		//Criar elementos
		memberOf memberof1 = factory.creatememberOf();
		memberof1.setName(name);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(memberof1);
	}
	
	public void createSubCollectionOf(Model model, String name) {
		
		//Criar elementos
		subCollectionOf subcollectionof1 = factory.createsubCollectionOf();
		subcollectionof1.setName(name);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(subcollectionof1);
	}
	
	public void createSubQuantityOf(Model model, String name) {
		
		//Criar elementos
		subQuantityOf subquantityof1 = factory.createsubQuantityOf();
		subquantityof1.setName(name);
	
		//Adicionar no modelo de maneira simples:
		model.getPackagedElement().add(subquantityof1);
	}

}
