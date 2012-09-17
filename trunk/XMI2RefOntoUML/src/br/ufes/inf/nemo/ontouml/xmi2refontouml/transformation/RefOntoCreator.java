package br.ufes.inf.nemo.ontouml.xmi2refontouml.transformation;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import br.ufes.inf.nemo.ontouml.xmi2refontouml.util.RefOntoUMLResourceFactory;

import RefOntoUML.*;
import RefOntoUML.impl.*;

public class RefOntoCreator {
	
	public Resource resource;
	public RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
	
	public static PrimitiveType INTEGER_PRIMITIVE;
	public static PrimitiveType BOOLEAN_PRIMITIVE;
	public static PrimitiveType STRING_PRIMITIVE;
	public static PrimitiveType UNLIMITED_NATURAL_PRIMITIVE;
	
	public RefOntoCreator() {
		INTEGER_PRIMITIVE = factory.createPrimitiveType();
		INTEGER_PRIMITIVE.setName("Integer");
		BOOLEAN_PRIMITIVE = factory.createPrimitiveType();
		BOOLEAN_PRIMITIVE.setName("Boolean");
		STRING_PRIMITIVE = factory.createPrimitiveType();
		STRING_PRIMITIVE.setName("String");
		UNLIMITED_NATURAL_PRIMITIVE = factory.createPrimitiveType();
		UNLIMITED_NATURAL_PRIMITIVE.setName("Unlimited Natural");
	}
	
	public void intialize(String saveAddress) {

		//Criar um ResourceSet para "gerenciar" o resource do modelo
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new RefOntoUMLResourceFactory());
		resourceSet.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,RefOntoUML.RefOntoUMLPackage.eINSTANCE);
		RefOntoUMLPackageImpl.init();
		
		//Cria um novo modelo (Resource) na URL especificada
		resource = resourceSet.createResource(URI.createURI(saveAddress));

	}
	
	public void saveXMI() throws IOException {
		// Saves XMI into file
		try {
			resource.save(Collections.EMPTY_MAP);
			
		} catch (Exception e) {
			Mediator.errorLog += e.getMessage() + "\n";
		}
		
	}
	
	/**
	 * Custom exception class that is thrown when creating
	 * or dealing with a RefOntoUML object, if something
	 * goes unexpected.
	 * 
	 * @author Vinicius
	 *
	 */
	
	class RefOntoUMLException extends Exception {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String error_msg;
		
		public RefOntoUMLException() {
			super();
			error_msg = "";
		}
	  
		public RefOntoUMLException(String err) {
			super(err);
			error_msg = err;
		}

		public String getError() {
			return error_msg;
	  	}
	}
	
	/*
	 * CREATION METHODS
	 */
	
	public RefOntoUML.Model createModel() {
		Model model = factory.createModel();
		resource.getContents().add(model);
		return model;
	}
	
	public RefOntoUML.Package createPackage() {
		return factory.createPackage();
	}
	
	public RefOntoUML.PrimitiveType createPrimitiveType() {
		return factory.createPrimitiveType();
	}
	
	public RefOntoUML.Enumeration createEnumeraion() {
		return factory.createEnumeration();
	}
	
	public RefOntoUML.DataType createDataType() {
		return factory.createDataType();
	}
	
	public RefOntoUML.Class createClass(String stereotype) throws RefOntoUMLException {
		
		RefOntoUML.Class newclass = null;
		
		// Criar elementos do tipo Class
		if (stereotype.equalsIgnoreCase("kind")) {
    		newclass = factory.createKind();
    	} 
		else if (stereotype.equalsIgnoreCase("subkind")) {
    		newclass = factory.createSubKind();
    	} 
    	else if (stereotype.equalsIgnoreCase("role")) {
    		newclass = factory.createRole();
    	} 
    	else if (stereotype.equalsIgnoreCase("phase")) {
    		newclass = factory.createPhase();
    	}
    	else if (stereotype.equalsIgnoreCase("category")) {
    		newclass = factory.createCategory();
    	}
    	else if (stereotype.equalsIgnoreCase("collective")) {
    		newclass = factory.createCollective();
    	}
    	else if (stereotype.equalsIgnoreCase("mixin")) {
    		newclass = factory.createMixin();
    	}
    	else if (stereotype.equalsIgnoreCase("mode")) {
    		newclass = factory.createMode();
    	}
    	else if (stereotype.equalsIgnoreCase("quantity")) {
    		newclass = factory.createQuantity();
    	}
    	else if (stereotype.equalsIgnoreCase("relator")) {
    		newclass = factory.createRelator();
    	}
    	else if (stereotype.equalsIgnoreCase("rolemixin")) {
    		newclass = factory.createRoleMixin();
    	}
    	else if (stereotype.equalsIgnoreCase("")) {
    		newclass = factory.createClass();
    	}
    	else {
    		throw new RefOntoUMLException("Class stereotype '" + stereotype + "' not supported." + "\n");
    	}
		
		return newclass;
	}
	
	public RefOntoUML.Association createAssociation(String stereotype) throws RefOntoUMLException {
		
		Association newassoc = null;
		
		if (stereotype.equalsIgnoreCase("characterization")) {
			newassoc = factory.createCharacterization();
    	}
		else if (stereotype.equalsIgnoreCase("componentof")) {
    		newassoc = factory.createcomponentOf();
    	}
		else if (stereotype.equalsIgnoreCase("formal") || stereotype.equalsIgnoreCase("formalassociation")) {
    		newassoc = factory.createFormalAssociation();
    	}
		else if (stereotype.equalsIgnoreCase("derivation")) {
    		newassoc = factory.createDerivation();
    	}
		else if (stereotype.equalsIgnoreCase("material") || stereotype.equalsIgnoreCase("materialassociation")) {
    		newassoc = factory.createMaterialAssociation();
    	}
		else if (stereotype.equalsIgnoreCase("mediation")) {
    		newassoc = factory.createMediation();
    	}
		else if (stereotype.equalsIgnoreCase("memberof")) {
    		newassoc = factory.creatememberOf();
    	}
		else if (stereotype.equalsIgnoreCase("subcollectionof")) {
    		newassoc = factory.createsubCollectionOf();
    	}
		else if (stereotype.equalsIgnoreCase("subquantityof")) {
    		newassoc = factory.createsubQuantityOf();
    	}
		else if (stereotype.equalsIgnoreCase("") || stereotype.equalsIgnoreCase("datatyperelationship")) {
			newassoc = factory.createAssociation();
		}
		else {
			throw new RefOntoUMLException("Association stereotype '" + stereotype + "' not supported.\n");
		}
		
		return newassoc;
	}
	
	public RefOntoUML.Property createProperty() {
		return factory.createProperty();
	}
	
	public RefOntoUML.EnumerationLiteral createEnumLiteral() {
		return factory.createEnumerationLiteral();
	}
	
	public RefOntoUML.Generalization createGeneralization() {
		return factory.createGeneralization();
	}
	
	public RefOntoUML.GeneralizationSet createGeneralizationSet() {
		return factory.createGeneralizationSet();
	}
	
	public RefOntoUML.Dependency createDependency() {
		return factory.createDependency();
	}
	
	public RefOntoUML.Comment createComment() {
		return factory.createComment();
	}
	
	public RefOntoUML.ValueSpecification createValueSpecification(Object value) {
		
		RefOntoUML.ValueSpecification ValueSpec;
		
		if (value instanceof Boolean) {
			ValueSpec = factory.createLiteralBoolean();
		
		} else if (value instanceof Integer) {
			if ((Integer)value == -1) {
				ValueSpec = factory.createLiteralUnlimitedNatural();
				((RefOntoUML.LiteralUnlimitedNatural)ValueSpec).setValue((Integer) value);
			} else {
				ValueSpec = factory.createLiteralInteger();
				((RefOntoUML.LiteralInteger)ValueSpec).setValue((Integer) value);
			}
		
		} else if (value instanceof String) {
			ValueSpec = factory.createLiteralString();
		
		} else {
			ValueSpec = factory.createLiteralNull();
		}
		
		return ValueSpec;
	}
	
	/* 
	 * DEALER METHODS 
	 */
	
	public void dealModel(RefOntoUML.Model model, Map<String, Object> hashProp) {
		model.setViewpoint((String)hashProp.get("viewpoint"));
		dealPackage(model, hashProp);
	}
	
	public void dealPackage(RefOntoUML.Package pack, Map<String, Object> hashProp) {
		dealPackageableElement(pack, hashProp);
		dealNamespace(pack, hashProp);
	}
	
	public void dealClass(RefOntoUML.Class cl, Map<String, Object> hashProp) {
		try {
			// Specific property of Collective
    		((Collective)cl).setIsExtensional(Boolean.parseBoolean((String)hashProp.get("isextensional")));
    	} catch (ClassCastException e) {
    		// DO NOTHING
    	}
    	cl.setIsActive(Boolean.parseBoolean((String)hashProp.get("isactive")));
    	dealClassifier(cl, hashProp);
	}
	
	public void dealDataType(RefOntoUML.DataType dt, Map<String, Object> hashProp) {
		dealClassifier(dt, hashProp);
	}
	
	public void dealAssociation(RefOntoUML.Association assoc1, Map<String, Object> hashProp) {
		try {
			// Specific properties of Meronymic associations.
			if(assoc1 instanceof subQuantityOf) {
				((subQuantityOf) assoc1).setIsEssential(true);
			
			} else {
				((Meronymic)assoc1).setIsEssential(Boolean.parseBoolean((String)hashProp.get("isessential")));
			}
    		((Meronymic)assoc1).setIsImmutablePart(Boolean.parseBoolean((String)hashProp.get("isimmutablepart")));
    		((Meronymic)assoc1).setIsImmutableWhole(Boolean.parseBoolean((String)hashProp.get("isimmutablewhole")));
    		((Meronymic)assoc1).setIsInseparable(Boolean.parseBoolean((String)hashProp.get("isinseparable")));
    		if (hashProp.containsKey("isshareable")) {
    			((Meronymic)assoc1).setIsShareable(Boolean.parseBoolean((String)hashProp.get("isshareable")));
    		}
    	} catch (ClassCastException e) {
    		// DO NOTHING
    	}
    	
    	// Makes sure the Properties (memberEnds) are added in the correct order
    	// to be in accordance to the RefOntoUML metamodel.
		if (assoc1.getMemberEnd().size() == 2) {
	    	List<?> endList = (List<?>)hashProp.get("memberend");
	    	if ((assoc1 instanceof Mediation && ((Property)endList.get(1)).getType() instanceof Relator) || 
	    			(assoc1 instanceof Characterization && ((Property)endList.get(1)).getType() instanceof Mode) || 
	    			(assoc1 instanceof Derivation && ((Property)endList.get(1)).getType() instanceof MaterialAssociation) ||
	    			((Property)endList.get(1)).getAggregation() == AggregationKind.COMPOSITE ||
	    			((Property)endList.get(1)).getAggregation() == AggregationKind.SHARED) {
	    		assoc1.getMemberEnd().removeAll(endList);
	    		assoc1.getMemberEnd().add((Property)endList.get(1));
	    		assoc1.getMemberEnd().add((Property)endList.get(0));
	    	} else {
	    		assoc1.getMemberEnd().add((Property)endList.get(0));
				assoc1.getMemberEnd().add((Property)endList.get(1));
	    	}
		}
    	
		assoc1.setIsDerived(Boolean.parseBoolean((String)hashProp.get("isderived")));
		dealClassifier(assoc1, hashProp);
		dealRelashionship(assoc1, hashProp);
	}
	
	public void dealClassifier (Classifier c1, Map<String, Object> hashProp) {
		//Categories, roleMixins and Mixins are abstract by default
		if (c1 instanceof Category || c1 instanceof RoleMixin || c1 instanceof Mixin) {
			c1.setIsAbstract(true);
		} else {
			c1.setIsAbstract(Boolean.parseBoolean((String)hashProp.get("isabstract")));
		}
		dealNamespace(c1, hashProp);
		dealRedefElement(c1, hashProp);
		dealType(c1, hashProp);
	}
	
	public void dealGeneralization (Generalization gen1, Map<String, Object> hashProp) {
		if (hashProp.containsKey("issubstitutable")) {
			gen1.setIsSubstitutable(Boolean.parseBoolean((String)hashProp.get("issubstitutable")));
		}
		// General and Specific are EOposites.
		// Given that, it`s only necessary to define one of those properties.
		gen1.setGeneral((Classifier)hashProp.get("general"));
		//gen1.setSpecific((Classifier)hashProp.get("specific"));
		dealDirectedRelashionship(gen1, hashProp);
	}
	
	public void dealGeneralizationSet (GeneralizationSet genset1, Map<String, Object> hashProp) {
		genset1.setIsCovering(Boolean.parseBoolean((String)hashProp.get("iscovering")));
		genset1.setIsDisjoint(Boolean.parseBoolean((String)hashProp.get("isdisjoint")));

		for (Object gen : (List<?>)hashProp.get("generalization")) {
			// Only one of those properties must be set, since they are EOposite.
			genset1.getGeneralization().add((Generalization)gen);
			((Generalization)gen).getGeneralizationSet().add(genset1);
		}
		
		dealPackageableElement(genset1, hashProp);
	}
	
	public void dealDependency (Dependency dep1, Map<String, Object> hashProp) {
		for (Object client : (List<?>)hashProp.get("client")) {
			dep1.getClient().add((NamedElement)client);
		}
		for (Object supplier : (List<?>)hashProp.get("supplier")) {
			dep1.getSupplier().add((NamedElement)supplier);
		}
		
		dealPackageableElement(dep1, hashProp);
		dealDirectedRelashionship(dep1, hashProp);
	}
	
	public void dealDirectedRelashionship (DirectedRelationship drel, Map<String, Object> hashProp) {
		dealRelashionship(drel, hashProp);
	}
	
	public void dealRelashionship (Relationship rel1, Map<String, Object> hashProp) {
		dealElement(rel1, hashProp);
	}
	
	public void dealProperty(RefOntoUML.Property prop1, Map<String, Object> hashProp) {
		prop1.setIsDerived(Boolean.parseBoolean((String)hashProp.get("isderived")));
		prop1.setIsDerivedUnion(Boolean.parseBoolean((String)hashProp.get("isderivedunion")));
		prop1.setDefault((String)hashProp.get("default"));
		prop1.setAggregation(AggregationKind.get((String)hashProp.get("aggregation")));
		dealStructFeature(prop1, hashProp);
	}
	
	public void dealStructFeature (StructuralFeature stf, Map<String, Object> hashProp) {
		if ((stf.eContainer() instanceof Mediation && !(hashProp.get("type") instanceof Relator)) ||
				(stf.eContainer() instanceof Characterization && !(hashProp.get("type") instanceof Mode))) {
			stf.setIsReadOnly(true);
		} else {
			stf.setIsReadOnly(Boolean.parseBoolean((String)hashProp.get("isreadonly")));
		}
		dealFeature(stf, hashProp);
		dealTypedElement(stf, hashProp);
		dealMultiplicityElement(stf, hashProp);
	}
	
	public void dealFeature (StructuralFeature feat, Map<String, Object> hashProp) {
		feat.setIsReadOnly(Boolean.parseBoolean((String)hashProp.get("isstatic")));
		dealRedefElement(feat, hashProp);
	}
	
	public void dealType (RefOntoUML.Type typ, Map<String, Object> hashProp) {
		dealPackageableElement(typ, hashProp);
	}
	
	public void dealEnumLiteral(RefOntoUML.EnumerationLiteral elit1, Map<String, Object> hashProp) {
		dealInstanceSpecification(elit1, hashProp);
	}
	
	public void dealInstanceSpecification(RefOntoUML.InstanceSpecification ispec, Map<String, Object> hashProp) {
//		for (Object classf : (List<?>)hashProp.get("classifier")) {
//			ispec.getClassifier().add((Classifier)classf);
//		}
		dealNamedElement(ispec, hashProp);
	}
	
	public void dealTypedElement (RefOntoUML.TypedElement tel, Map<String, Object> hashProp) {
		tel.setType((Type)hashProp.get("type"));
		dealNamedElement(tel, hashProp);
	}
	
	// Método criado puramente no intuito de tornar a representação ideal do metamodelo
	public void dealPackageableElement (RefOntoUML.PackageableElement pack, Map<String, Object> hashProp) {
		dealNamedElement(pack, hashProp);
	}
	
	// Método criado puramente no intuito de tornar a representação ideal do metamodelo
	public void dealNamespace (RefOntoUML.Namespace namesp, Map<String, Object> hashProp) {
		dealNamedElement(namesp, hashProp);
	}
	
	public void dealRedefElement(RefOntoUML.RedefinableElement rel, Map<String, Object> hashProp) {
		rel.setIsLeaf(Boolean.parseBoolean((String)hashProp.get("isleaf")));
		dealNamedElement(rel, hashProp);
	}
	
	public void dealMultiplicityElement (RefOntoUML.MultiplicityElement mel, Map<String, Object> hashProp) {
		if (hashProp.get("lower") != null && hashProp.get("upper") != null) {
			//mel.setLowerValue(createValueSpecification(Integer.parseInt((String)hashProp.get("lower"))));
			//mel.setUpperValue(createValueSpecification(Integer.parseInt((String)hashProp.get("upper"))));
			RefOntoUML.LiteralUnlimitedNatural upper = factory.createLiteralUnlimitedNatural();
			upper.setValue((Integer.parseInt((String)hashProp.get("upper"))));
			mel.setUpperValue(upper);
			RefOntoUML.LiteralInteger lower = factory.createLiteralInteger();
			if (hashProp.get("lower").equals("-1")) {
				lower.setValue(0);
			} else {
				lower.setValue((Integer.parseInt((String)hashProp.get("lower"))));
			}
			mel.setLowerValue(lower);
			
		} else {
			Mediator.warningLog += "Warning: Property '" + hashProp.get("name") + "' multiplicity undefined.\n";
			String warningPath = hashProp.get("name") + "\n";
			for (EObject aux = mel.eContainer(); aux != null; aux = aux.eContainer()) {
				if (aux instanceof NamedElement && ((NamedElement) aux).getName() != null) {
					warningPath = ((NamedElement) aux).getName() + " -> " + warningPath;
					
				} else {
					warningPath = "<" + aux.eClass().getName() + "> -> " + warningPath;
				}
			}
			Mediator.warningLog += "Property of: " + warningPath + "\n";
		}
		
		mel.setIsOrdered(Boolean.parseBoolean((String)hashProp.get("isordered")));
		if (hashProp.containsKey("isunique")) {
			mel.setIsUnique(Boolean.parseBoolean((String)hashProp.get("isunique")));
		}
		
		dealElement(mel, hashProp);
	}
	
	public void dealNamedElement (RefOntoUML.NamedElement nel, Map<String, Object> hashProp) {
		nel.setName((String)hashProp.get("name"));
		nel.setVisibility(VisibilityKind.get((String)hashProp.get("visibility")));
		dealElement(nel, hashProp);
	}
	
	public void dealComment (RefOntoUML.Comment com, Map<String, Object> hashProp) {
		com.setBody((String)hashProp.get("body"));
		
		for (Object annotatedElement : (List<?>)hashProp.get("annotatedelement")) {
			com.getAnnotatedElement().add((Element)annotatedElement);
		}
		
		dealElement(com, hashProp);
	}
	
	public void dealElement (RefOntoUML.Element ele, Map<String, Object> hashProp) {
		//ele.getOwnedComment().add();
	}
	
	/*
	 * ADD METHODS
	 */
	
	public void addPackagedElement(RefOntoUML.Package pack, RefOntoUML.PackageableElement pel) {
		pack.getPackagedElement().add(pel);
	}
	
	public void addProperty(RefOntoUML.Classifier classf, RefOntoUML.Property prop) {
		if (classf instanceof RefOntoUML.Class) {
        	((RefOntoUML.Class)classf).getOwnedAttribute().add(prop);
        }
		else if (classf instanceof RefOntoUML.DataType) {
        	((RefOntoUML.DataType)classf).getOwnedAttribute().add(prop);
		}
		else if (classf instanceof RefOntoUML.Association) {
			((RefOntoUML.Association)classf).getOwnedEnd().add(prop);
			prop.setAssociation(((RefOntoUML.Association)classf));
		}
	}
	
	public void addEnumLiteral(RefOntoUML.Enumeration enumeration, RefOntoUML.EnumerationLiteral lit) {
		enumeration.getOwnedLiteral().add(lit);
	}
	
	public void addGeneralization(RefOntoUML.Classifier classf, RefOntoUML.Generalization gen) {
		classf.getGeneralization().add(gen);
	}
	
	public void addComment(RefOntoUML.Element elem, RefOntoUML.Comment comment) {
		elem.getOwnedComment().add(comment);
	}
	
	public void setName(RefOntoUML.NamedElement nelem, String name) {
		nelem.setName(name);
	}

}
