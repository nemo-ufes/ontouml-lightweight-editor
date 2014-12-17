package old;

import java.io.ByteArrayOutputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.ParserException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLNaryClassAxiom;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.MixinClass;
import RefOntoUML.Model;
import RefOntoUML.MomentClass;
import RefOntoUML.PackageableElement;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.OCL2OWL_SWRL;

public class OLD_Transformer {
	public String errors = "";
	private String nameSpace;
	private OntoUMLParser ontoParser;

	private OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	private OWLOntology ontology;
	private OWLDataFactory factory;

	//This attributes are used for make the compost datatypes
	private Set<DataType> _lstDataType;
	private List<String> _lstDataTypeAttributes = new ArrayList<String>();

	//Used for manipulation the disjoints associations
	private Set<OWLObjectProperty> _lstMaterialAssociations = new HashSet<OWLObjectProperty>();
	private Set<OWLObjectProperty> _lstFormalAssociations = new HashSet<OWLObjectProperty>();
	private Set<OWLObjectProperty> _lstMediationAssociations = new HashSet<OWLObjectProperty>();
	private Set<OWLObjectProperty> _lstPartofAssociations = new HashSet<OWLObjectProperty>();
	private Set<OWLObjectProperty> _lstCharacterizationAssociations = new HashSet<OWLObjectProperty>();

	//Used for make the dataproperties disjoint
	private HashMap<OWLClass, String> _aux_hashClassToDataProperty = new HashMap<OWLClass,String>();

	//Used to put the OWLSubClassOfAxioms in the same axiom
	private Set<OWLSubClassOfAxiom> _lstOWLSubClassOfAxiom = new HashSet<OWLSubClassOfAxiom>();

	/**
	 * Create a Transformer and use the nameSpace as the ontology URI
	 * @param nameSpace
	 */
	public OLD_Transformer(String nameSpace) {
		this.nameSpace = nameSpace+"#";

		try {
			this.ontology = manager.createOntology(IRI.create(nameSpace));
			this.factory = manager.getOWLDataFactory();
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}	
	}

	/**
	 * Transform a RefOntoUML.Model to OWL
	 * 
	 * @param ecoreModel
	 * @return a String with the OWL code
	 * @throws Exception 
	 * @throws Exception 
	 * @throws ParserException 
	 */
	public String transform(Model ecoreModel, String oclRules) throws Exception{

		ontoParser = new OntoUMLParser(ecoreModel);
		_lstDataType = ontoParser.getAllInstances(RefOntoUML.DataType.class);

		//Class
		//process each class of refontouml and their datatypes
		for(RefOntoUML.Class src: ontoParser.getAllInstances(RefOntoUML.Class.class))
			processClass(src);

		//Process the datatypes structured
		processDataTypeStructured();

		//Make all datatypes from the same mother class, disjoints
		processDataTypeDisjoint();

		//SubstanceSortal
		//todos os substanceSortal sao diferentes entre si
		processDifferentsClass();

		//GeneralizationSet
		for(GeneralizationSet src: ontoParser.getAllInstances(GeneralizationSet.class))
			processGeneralizationSet(src);

		//Generalization
		for(Generalization src: ontoParser.getAllInstances(Generalization.class))
			processGeneralization(src);	

		//Material
		for(MaterialAssociation src: ontoParser.getAllInstances(MaterialAssociation.class))
			createMaterialAssociation(src);

		//Material
		for(Characterization src: ontoParser.getAllInstances(Characterization.class))
			createCharacterizationAssociation(src);

		//Relator
		for(Relator src: ontoParser.getAllInstances(Relator.class))
			processRelator(src);

		//FormalAssociation
		for(FormalAssociation src: ontoParser.getAllInstances(FormalAssociation.class))
			processFormalAssociation(src);

		//Process Part-whole: componentOf
		if(ontoParser.getAllInstances(componentOf.class).size() > 1){
			createRelation_componentOf();
		}else if (ontoParser.getAllInstances(componentOf.class).size() == 1){
			//used to make disjoint associations
			_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"componentOf")));
		}
		for(componentOf a : ontoParser.getAllInstances(componentOf.class)){
			processMeronymic(a, "componentOf");
		}	

		//Process Part-whole: subCollectionOf 
		if(ontoParser.getAllInstances(subCollectionOf.class).size() > 1){
			createRelation_subCollectionOf();
		}else if (ontoParser.getAllInstances(subCollectionOf.class).size() == 1){
			//used to make disjoint associations
			_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"subCollectionOf")));
		}
		for(subCollectionOf a : ontoParser.getAllInstances(subCollectionOf.class)){
			processMeronymic(a, "subCollectionOf");
		}

		//Process Part-whole: subQuantityOf 
		if(ontoParser.getAllInstances(subQuantityOf.class).size() > 1){
			createRelation_subQuantityOf();
		}else if (ontoParser.getAllInstances(subQuantityOf.class).size() == 1){
			//used to make disjoint associations
			_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"subQuantityOf")));
		}
		for(subQuantityOf a : ontoParser.getAllInstances(subQuantityOf.class)){
			processMeronymic(a, "subQuantityOf");
		}

		//Process Part-whole: memberOf 
		if(ontoParser.getAllInstances(memberOf.class).size() >= 1){
			if(ontoParser.getAllInstances(subCollectionOf.class).size() >= 1){
				//if has a memberof association and a subcollectionof association
				//than make a swrl for these
				createRelation_memberOf();
			}else{
				//create a relation without the swrl for the subcollectionof
				createRelation_memberOf_withoutSubCollectionOf();
			}
		}else if (ontoParser.getAllInstances(memberOf.class).size() == 1){
			//used to make disjoint associations
			_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"memberOf")));
		}
		for(memberOf a : ontoParser.getAllInstances(memberOf.class)){
			processMeronymic(a, "memberOf");
		}

		//Process disjuntion of associations
		processDisjointionOfAssociations();

		//Process Comments
		processAnnotation();

		//Process Axioms
		processAxioms();
		
		if(oclRules != null){
			if(!oclRules.equals("")){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int result = JOptionPane.showConfirmDialog (null, "Do You Wish To Transform the OCL rules?","Warning",dialogButton);
                
                if(result == JOptionPane.YES_OPTION){
                	OCL2OWL_SWRL ocl2owl_swrl = new OCL2OWL_SWRL(oclRules, ontoParser, manager, nameSpace);
        			//OCL2SWRL ocl2swrl = new OCL2SWRL(oclParser, ontoParser, manager, nameSpace);
                	ocl2owl_swrl.Transformation();
        			
        			this.errors += "\n" + ocl2owl_swrl.errors;
                }
			}
			
		}

		try {	
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			manager.saveOntology(ontology, os);
			//String s = new String(os.toByteArray(),"ISO-8859-1");
			String owl = new String(os.toByteArray(),"UTF-8");
			//Process special characters
			owl = processSpecialCharacter(owl);
			return owl;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	private String processSpecialCharacter(String owl) {
		owl = Normalizer.normalize(owl, Normalizer.Form.NFD);
		owl = owl.replaceAll("[^\\p{ASCII}]", "");
		return owl;
	}

	/**
	 * Create the OWLObjectProperty memberOf and his swrl
	 * @param 
	 */
	private void createRelation_memberOf_withoutSubCollectionOf() {
		_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"memberOf")));

		OWLObjectProperty memberOf = factory.getOWLObjectProperty(IRI.create(nameSpace+"memberOf"));
		OWLObjectProperty inv_memberOf = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.memberOf"));

		//Make memberOf irreflexive and asymetric
		OWLIrreflexiveObjectPropertyAxiom iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(memberOf);//memberOf is Irreflexive
		manager.applyChange(new AddAxiom(ontology, iopa));
		OWLAsymmetricObjectPropertyAxiom aopa = factory.getOWLAsymmetricObjectPropertyAxiom(memberOf);//memberOf is Asymetric
		manager.applyChange(new AddAxiom(ontology, aopa));

		//Make INV.memberOf irreflexive and asymetric
		aopa = factory.getOWLAsymmetricObjectPropertyAxiom(inv_memberOf);//inv_memberOf is Asymetric
		manager.applyChange(new AddAxiom(ontology, aopa));
		iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(inv_memberOf);//inv_memberOf is Irreflexive
		manager.applyChange(new AddAxiom(ontology, iopa));

		//Make the inverse property disjoint of the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(memberOf,inv_memberOf)));

		//Make memberOf disjoint of inv.memberOf
		OWLInverseObjectPropertiesAxiom inverse = factory.getOWLInverseObjectPropertiesAxiom(memberOf, inv_memberOf);
		manager.applyChange(new AddAxiom(ontology, inverse));
	}
	/**
	 * Create the OWLObjectProperty characterization and his cardinality
	 * @param 
	 */
	private void createCharacterizationAssociation(Characterization ass) {
		/*
		 * If the property has a name
		 * 	use this name
		 * else
		 * 	create a property with the name TypeProperty.ClassSrc.ClassDst
		 * If this name was used 
		 * 	make this property subPropertyOf the mother property
		 * */

		OWLObjectProperty prop,invProp;
		String propName;
		if(ass.getName()==null || ass.getName() == "" || ass.getName() == " " || ass.getName().length() == 0){
			propName = "characterization."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
		}else{
			propName = ass.getName().replaceAll(" ", "_");
		}

		//Search for other instances of this formal
		for(Characterization fa:ontoParser.getAllInstances(Characterization.class)){
			String formalName = "";
			if(fa.getName() == null || fa.getName() == "" || fa.getName() == " "|| fa.getName().length() == 0){
				formalName = "characterization."+fa.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+fa.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
			}else{
				formalName = fa.getName().replaceAll(" ", "_");
			}
			if(!ass.equals(fa) && propName.equals(formalName)){
				propName += "."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
				break;				
			}
		}

		prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
		invProp = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+propName));

		//source class of the relation
		OWLClass ori = factory.getOWLClass(IRI.create(nameSpace+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")));		

		//destination class of the relation
		OWLClass dst = factory.getOWLClass(IRI.create(nameSpace+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_")));

		//Set domain and range from the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyDomainAxiom(prop, ori)));
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(prop, dst)));

		//Set domain and range from the inverse property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyDomainAxiom(invProp, dst)));
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(invProp, ori)));

		//set that the inverse property is the inverse of the property
		manager.applyChange(new AddAxiom(ontology,factory.getOWLInverseObjectPropertiesAxiom(prop, invProp)));

		//Process the cardinalities
		processRelations(ass,propName,1,false);
		processRelations(ass,"INV."+propName,0,true);

		//Add from the global list of the mediations
		_lstCharacterizationAssociations.add(prop);

		//Make the inverse property disjoint of the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop,invProp)));
	}
	/**
	 * Make all associations with different stereotype different
	 * @param 
	 */
	private void processDisjointionOfAssociations() {
		//		Set<OWLObjectProperty> lst = new HashSet<OWLObjectProperty>();

		//manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(_lstPartofAssociations)));

		//Make all Formal disjoints of the other associations


		//Falta mudar a ordem
		for(OWLObjectProperty prop1 : _lstFormalAssociations){
			for(OWLObjectProperty prop2 : _lstMediationAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop2)));	
			}
			for(OWLObjectProperty prop3 : _lstCharacterizationAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop3)));
			}
			for(OWLObjectProperty prop4 : _lstMaterialAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop4)));
			}
			for(OWLObjectProperty prop5 : _lstPartofAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop5)));
			}
		}

		for(OWLObjectProperty prop1 : _lstMediationAssociations){
			for(OWLObjectProperty prop2 : _lstFormalAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop2)));	
			}
			for(OWLObjectProperty prop3 : _lstCharacterizationAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop3)));
			}
			for(OWLObjectProperty prop4 : _lstMaterialAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop4)));
			}
			for(OWLObjectProperty prop5 : _lstPartofAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop5)));
			}
		}

		for(OWLObjectProperty prop1 : _lstCharacterizationAssociations){
			for(OWLObjectProperty prop2 : _lstFormalAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop2)));	
			}
			for(OWLObjectProperty prop3 : _lstMediationAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop3)));
			}
			for(OWLObjectProperty prop4 : _lstMaterialAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop4)));
			}
			for(OWLObjectProperty prop5 : _lstPartofAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop5)));
			}
		}

		for(OWLObjectProperty prop1 : _lstMaterialAssociations){
			for(OWLObjectProperty prop2 : _lstFormalAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop2)));	
			}
			for(OWLObjectProperty prop3 : _lstMediationAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop3)));
			}
			for(OWLObjectProperty prop4 : _lstCharacterizationAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop4)));
			}
			for(OWLObjectProperty prop5 : _lstPartofAssociations){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop5)));
			}
		}

		for(OWLObjectProperty prop1 : _lstPartofAssociations){
			for(OWLObjectProperty prop2 : _lstPartofAssociations){
				if(!prop1.equals(prop2))
					manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop2)));
			}
		}


		//				
		//				//Make all Mediations disjoints of the other associations
		//				for(OWLObjectProperty prop1 : _lstMediationAssociations){
		//					for(OWLObjectProperty prop2 : _lstMediationAssociations){
		//						if(!prop1.equals(prop2))
		//						manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop2)));	
		//					}
		//				}	
		//
		//				//Make all Material disjoints of the other associations
		//				for(OWLObjectProperty prop1 : _lstMaterialAssociations){
		//					for(OWLObjectProperty prop2 : _lstMaterialAssociations){
		//						if(!prop1.equals(prop2))
		//						manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop1,prop2)));	
		//					}
		//				}	
		//
		//						//Make all PartyOf disjoints of the other associations
		//				for(OWLObjectProperty prop : _lstPartofAssociations){
		//					lst.add(prop);
		//					manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(lst)));
		//					lst.remove(lst.size()-1);
		//				}	
		//
		//		lst = new HashSet<OWLObjectProperty>();
		//
		//		lst.addAll(_lstMaterialAssociations);
		//		lst.addAll(_lstMediationAssociations);
		//		lst.addAll(_lstFormalAssociations);
		//		lst.addAll(_lstPartofAssociations);
		//
		//		//Make all Characterization disjoints of the other associations
		//		for(OWLObjectProperty prop : _lstCharacterizationAssociations){
		//			lst.add(prop);
		//			manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(lst)));
		//			lst.remove(lst.size()-1);
		//		}	
	}
	/**
	 * This belongs the recursion chain of the datatypes process
	 * @param 
	 */
	private void processDataTypeDisjoint() {
		Set<OWLDataProperty> lst = new HashSet<OWLDataProperty>();
		for (Map.Entry<OWLClass,String> entry : _aux_hashClassToDataProperty.entrySet()) {
			for(String s:entry.getValue().split("@")){
				lst.add(factory.getOWLDataProperty(IRI.create(s)));
			}
			if(lst.size()>1)
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointDataPropertiesAxiom(lst)));
			lst = new HashSet<OWLDataProperty>();
		}		
	}

	/**
	 * Process the RefOntoUML.FormalAssociation in model.
	 * If the association doesn't have a name, a relation with the 
	 * name formal.ClassSource.ClassSink is created.
	 * Else, the name of the relation is used.
	 * 
	 * For the inverse relation, the prefix inv. is used.  
	 * 
	 * @param ass
	 */
	private void processFormalAssociation(FormalAssociation ass) {
		/*
		 * If the property has a name
		 * 	use this name
		 * else
		 * 	create a property with the name TypeProperty.ClassSrc.ClassDst
		 * If this name was used 
		 * 	make this property subPropertyOf the mother property
		 * */

		OWLObjectProperty prop,invProp;
		String propName;
		if(ass.getName()==null || ass.getName() == "" || ass.getName() == " " || ass.getName().length() == 0){
			propName = "formal."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
		}else{
			propName = ass.getName().replaceAll(" ", "_");
		}

		//Search for other instances of this formal
		for(FormalAssociation fa:ontoParser.getAllInstances(FormalAssociation.class)){
			String formalName = "";
			if(fa.getName() == null || fa.getName() == "" || fa.getName() == " "|| fa.getName().length() == 0){
				formalName = "formal."+fa.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+fa.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
			}else{
				formalName = fa.getName().replaceAll(" ", "_");
			}
			if(!ass.equals(fa) && propName.equals(formalName)){
				propName += "."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
				break;				
			}
		}

		prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
		invProp = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+propName));

		//source class of the relation
		OWLClass ori = factory.getOWLClass(IRI.create(nameSpace+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")));		

		//destination class of the relation
		OWLClass dst = factory.getOWLClass(IRI.create(nameSpace+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_")));

		//Set domain and range from the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyDomainAxiom(prop, ori)));
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(prop, dst)));

		//Set domain and range from the inverse property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyDomainAxiom(invProp, dst)));
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(invProp, ori)));

		//set that the inverse property is the inverse of the property
		manager.applyChange(new AddAxiom(ontology,factory.getOWLInverseObjectPropertiesAxiom(prop, invProp)));

		//Process the cardinalities
		processRelations(ass,propName,1,false);
		processRelations(ass,"INV."+propName,0,true);

		//Add from the global list of the mediations
		_lstFormalAssociations.add(prop);

		//Make the inverse property disjoint of the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop,invProp)));
	}

	/**
	 * Process the meronymics relation (componentOf, subCollectiveOf,
	 * subQuantityOf and memberOf).
	 * 
	 * This method just create a relationship between meronymic association members.  
	 * 
	 * @param ass
	 */
	private void processMeronymic(Meronymic ass, String propName) {
		String prop = "", invProp = "";
		if(ass.getName()==null || ass.getName() == "" || ass.getName() == " "){
			prop = propName+"."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_"); 
			invProp = "INV."+propName+"."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
		}else{
			prop = ass.getName().replaceAll(" ", "_");
			invProp = "INV."+ass.getName().replaceAll(" ", "_");
		}
		processRelationMeronymic(ass, "INV."+propName, invProp , 0, true);
		processRelationMeronymic(ass, propName, prop, 1, false);

		OWLObjectProperty relProp = factory.getOWLObjectProperty(IRI.create(nameSpace+prop));
		OWLObjectProperty relInvProp = factory.getOWLObjectProperty(IRI.create(nameSpace+invProp));

		OWLInverseObjectPropertiesAxiom inv = factory.getOWLInverseObjectPropertiesAxiom(relInvProp, relProp);
		manager.applyChange(new AddAxiom(ontology, inv));
	}

	private void createRelation_subQuantityOf() {
		_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"subQuantityOf")));

		//Make the inverse property disjoint of the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(factory.getOWLObjectProperty(IRI.create(nameSpace+"subQuantityOf")),factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.subQuantityOf")))));

		createRelationPartOf_SWRL("subQuantityOf");

		OWLObjectProperty rel = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.subQuantityOf"));

		OWLIrreflexiveObjectPropertyAxiom iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(rel);//Irreflexive
		manager.applyChange(new AddAxiom(ontology, iopa));
		OWLAsymmetricObjectPropertyAxiom aopa = factory.getOWLAsymmetricObjectPropertyAxiom(rel);//Asymetric
		manager.applyChange(new AddAxiom(ontology, aopa));

		OWLInverseObjectPropertiesAxiom inv = factory.getOWLInverseObjectPropertiesAxiom(factory.getOWLObjectProperty(IRI.create(nameSpace+"subQuantityOf")), rel);
		manager.applyChange(new AddAxiom(ontology, inv));
	}
	/**
	 * Create a party of relation with his respectively swrl
	 * @param the name of the partyof association
	 */
	private void createRelationPartOf_SWRL(String name){
		OWLObjectProperty rel = factory.getOWLObjectProperty(IRI.create(nameSpace+name));

		OWLIrreflexiveObjectPropertyAxiom iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(rel);//Irreflexive
		manager.applyChange(new AddAxiom(ontology, iopa));
		OWLAsymmetricObjectPropertyAxiom aopa = factory.getOWLAsymmetricObjectPropertyAxiom(rel);//Asymetric
		manager.applyChange(new AddAxiom(ontology, aopa));

		//variaveis
		SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+"x"));
		SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+"y"));
		SWRLVariable varZ = factory.getSWRLVariable(IRI.create(nameSpace+"z"));

		//Fazendo as variaveis diferentes
		SWRLAtom diffYX = factory.getSWRLDifferentIndividualsAtom(varY, varX);
		SWRLAtom diffXZ = factory.getSWRLDifferentIndividualsAtom(varX, varZ);
		SWRLAtom diffYZ = factory.getSWRLDifferentIndividualsAtom(varY, varZ);

		//sentencas
		Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
		antecedent.add(diffXZ); //DifferentFrom(?x,?z)
		antecedent.add(diffYZ); //DifferentFrom(?y,?z)
		antecedent.add(diffYX); //DifferentFrom(?y,?z)
		antecedent.add(factory.getSWRLObjectPropertyAtom(rel, varX, varY)); //prop(?x,?Y)
		antecedent.add(factory.getSWRLObjectPropertyAtom(rel, varY, varZ)); //prop(?y,?z)

		Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
		consequent.add(factory.getSWRLObjectPropertyAtom(rel, varX, varZ)); //prop(?x,?z)

		SWRLRule rule = factory.getSWRLRule(antecedent,consequent);		
		manager.applyChange(new AddAxiom(ontology, rule));
	}

	private void createRelation_subCollectionOf() {
		_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"subCollectionOf")));

		//Make the inverse property disjoint of the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(factory.getOWLObjectProperty(IRI.create(nameSpace+"subCollectionOf")),factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.subCollectionOf")))));

		createRelationPartOf_SWRL("subCollectionOf");	

		OWLObjectProperty rel = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.subCollectionOf"));

		OWLIrreflexiveObjectPropertyAxiom iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(rel);//Irreflexive
		manager.applyChange(new AddAxiom(ontology, iopa));
		OWLAsymmetricObjectPropertyAxiom aopa = factory.getOWLAsymmetricObjectPropertyAxiom(rel);//Asymetric
		manager.applyChange(new AddAxiom(ontology, aopa));

		OWLInverseObjectPropertiesAxiom inv = factory.getOWLInverseObjectPropertiesAxiom(rel, factory.getOWLObjectProperty(IRI.create(nameSpace+"subCollectionOf")));
		manager.applyChange(new AddAxiom(ontology, inv));
	}

	private void createRelation_memberOf() {
		_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"memberOf")));

		//Make the inverse property disjoint of the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(factory.getOWLObjectProperty(IRI.create(nameSpace+"memberOf")),factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.memberOf")))));

		OWLObjectProperty memberOf = factory.getOWLObjectProperty(IRI.create(nameSpace+"memberOf"));
		OWLObjectProperty subCollectionOf = factory.getOWLObjectProperty(IRI.create(nameSpace+"subCollectionOf"));

		OWLIrreflexiveObjectPropertyAxiom iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(memberOf);//memberOf is Irreflexive
		manager.applyChange(new AddAxiom(ontology, iopa));
		iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(subCollectionOf);//subCollectiveOf is Irreflexive
		manager.applyChange(new AddAxiom(ontology, iopa));

		OWLAsymmetricObjectPropertyAxiom aopa = factory.getOWLAsymmetricObjectPropertyAxiom(memberOf);//memberOf is Asymetric
		manager.applyChange(new AddAxiom(ontology, aopa));
		aopa = factory.getOWLAsymmetricObjectPropertyAxiom(subCollectionOf);//subCollectiveOf is Asymetric
		manager.applyChange(new AddAxiom(ontology, aopa));

		OWLObjectProperty inv_memberOf = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.memberOf"));

		aopa = factory.getOWLAsymmetricObjectPropertyAxiom(inv_memberOf);//inv_memberOf is Asymetric
		manager.applyChange(new AddAxiom(ontology, aopa));
		iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(inv_memberOf);//inv_memberOf is Irreflexive
		manager.applyChange(new AddAxiom(ontology, iopa));

		OWLInverseObjectPropertiesAxiom inverse = factory.getOWLInverseObjectPropertiesAxiom(memberOf, inv_memberOf);
		manager.applyChange(new AddAxiom(ontology, inverse));

		//variaveis
		SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+"x"));
		SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+"y"));
		SWRLVariable varZ = factory.getSWRLVariable(IRI.create(nameSpace+"z"));

		//Fazendo as variaveis diferentes
		SWRLAtom diffYX = factory.getSWRLDifferentIndividualsAtom(varY, varX);
		SWRLAtom diffXZ = factory.getSWRLDifferentIndividualsAtom(varX, varZ);
		SWRLAtom diffYZ = factory.getSWRLDifferentIndividualsAtom(varY, varZ);

		//sentencas
		Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
		antecedent.add(diffXZ); //DifferentFrom(?x,?z)
		antecedent.add(diffYZ); //DifferentFrom(?y,?z)
		antecedent.add(diffYX); //DifferentFrom(?y,?z)
		antecedent.add(factory.getSWRLObjectPropertyAtom(memberOf, varY, varX)); //memberOf(?x,?y)
		antecedent.add(factory.getSWRLObjectPropertyAtom(subCollectionOf, varZ, varY)); //subCollectiveOf(?y,?z)

		Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
		consequent.add(factory.getSWRLObjectPropertyAtom(memberOf, varZ, varX)); //memberOf(?x,?z)

		SWRLRule rule = factory.getSWRLRule(antecedent,consequent);		
		manager.applyChange(new AddAxiom(ontology, rule));		

	}
	/**
	 * Create the relationship between the relator and his associations.
	 * In this method creates the mediation associations that use the relator src
	 * 
	 * @param 
	 */
	private void processRelator(Relator src) {
		try {
			List<MaterialAssociation> lstMaterialAssociation = this.getRelatorMaterials(src);
			List<Mediation> lstMediation = ontoParser.getMediations(src);
			List<Mediation> auxLstMediation = new ArrayList<Mediation>();
			String mediation0 = null, mediation1 = null;

			// Process MaterialAssociation
			for(MaterialAssociation ma:lstMaterialAssociation){
				// clean the variables
				mediation0 = null;
				mediation1 = null;
				for(Mediation m : lstMediation){
					//Verifica se a MaterialAssociation e a Mediation possuem a mesma classe de um lado
					if(ma.getMemberEnd().get(0).getType().equals(m.getMemberEnd().get(0).getType()) 
							|| ma.getMemberEnd().get(0).getType().equals(m.getMemberEnd().get(1).getType())){
						mediation0 = createMediationAssociation(m);
						auxLstMediation.add(m);
					}
					if(ma.getMemberEnd().get(1).getType().equals(m.getMemberEnd().get(1).getType()) 
							|| ma.getMemberEnd().get(1).getType().equals(m.getMemberEnd().get(1).getType())){
						mediation1 = createMediationAssociation(m);
						auxLstMediation.add(m);
					}
				}	
				if(mediation0 != null && mediation1 != null){
					//create swrl for material with mediations
					createSWRLforRelator(mediation0,mediation1, ma, src);
				}
			}

			//create mediations without material
			for(Mediation m : lstMediation){
				if(!auxLstMediation.contains(m))
					createMediationAssociation(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * After processed the datatype structureds in string, we create the owl objects
	 * 
	 * @param 
	 */
	private void processDataTypeStructured() {
		for(String s:_lstDataTypeAttributes){
			//Normative_Act#publication_date.day:Integer
			//attribName = publication_date.day
			//dtName = Normative_Act
			//range = Integer

			String attrbName = s.split(":")[0].split("#")[1];
			String dtName = s.split(":")[0].split("#")[0];
			String range = s.split(":")[1];

			OWLDataProperty atributo = manager.getOWLDataFactory().getOWLDataProperty(IRI.create(nameSpace+dtName.replaceAll(" ", "_")+"."+attrbName));
			OWLDatatype tipoAtributo = getDataTypeRange(range);		
			manager.applyChange(new AddAxiom(ontology, factory.getOWLDataPropertyRangeAxiom(atributo, tipoAtributo)));
			OWLClassExpression expression = manager.getOWLDataFactory().getOWLDataExactCardinality(1, atributo,tipoAtributo);

			OWLClass owner = manager.getOWLDataFactory().getOWLClass(IRI.create(nameSpace+dtName.replaceAll(" ", "_")));

			//set the father of this datatype
			manager.applyChange(new AddAxiom(ontology, factory.getOWLDataPropertyDomainAxiom(atributo, owner)));

			//usado para criar o disjoint dos dataproperties para a classe
			if(_aux_hashClassToDataProperty.get(owner) == null){
				_aux_hashClassToDataProperty.put(owner, "");	
			}
			_aux_hashClassToDataProperty.put(owner, _aux_hashClassToDataProperty.get(owner)+atributo.getIRI().toString()+"@");


			OWLEquivalentClassesAxiom ax = manager.getOWLDataFactory().getOWLEquivalentClassesAxiom(owner,expression);

			manager.applyChange(new AddAxiom(ontology, ax));
		}
	}

	/**
	 * Process the chain of the attributes
	 * @param The actual datatype of the chain
	 */
	//semi-globals variables, used in the context of the recursion chain
	private String dt_atual = "";
	private OWLDatatype _aux_tipoAtributo = null;
	private void processDataTypeAttributo(DataType src) {
		String range;
		OWLDatatype dt = null;
		for(Property p:src.getAttribute()){
			//for each dataype
			if(p!=null && p.getType() != null){
				range = p.getType().getName().replaceAll(" ", "_");
				dt = getDataTypeRange(range);
				if(dt == null){
					//if the range is other class in the model
					String aux = dt_atual;
					//increase global datatype name, adding the name of the current datatype name and type
					dt_atual += src.getName().replaceAll(" ", "_")+"@"+p.getName().replaceAll(" ", "_")+"@"+p.getType().getName().replaceAll(" ", "_")+"@";
					//start the recursion
					processDataTypeProperty(p);
					//save the last name;
					dt_atual = aux;					
				}else{
					//put the datatype in the global list of datatype
					_lstDataTypeAttributes.add(dt_atual.replaceAll("@", "_").substring(0,dt_atual.length()-1)+"."+p.getName().replaceAll(" ", "_")+":"+range);
					_aux_tipoAtributo = dt;
				}
			}
		}
	}

	/**
	 * Process the chain of the properties
	 * @param The actual property of the chain
	 */
	private void processDataTypeProperty(Property src){
		//This is a critical function
		for(DataType dt:_lstDataType){	
			//search in all datatypes from the model
			if(dt.getName().equals(src.getType().getName())){
				//if is the actual element in the search
				if(dt_atual == "" || dt_atual.contains("#")){
					//add the name of this property added the a wildcard for next processing
					dt_atual += src.getName().replaceAll(" ", "_")+"@";
				}
				//process the attributes for the actual property
				processDataTypeAttributo(dt);
			}
		}
	}
	/**
	 * Create the swrl for an relator that have a material association between two of his mediations. 
	 * @param 
	 */
	private void createSWRLforRelator(String mediation0, String mediation1, MaterialAssociation material, Relator relator) {
		try {
			OWLClass cA = factory.getOWLClass(IRI.create(nameSpace+material.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")));
			OWLDeclarationAxiom declarationAxiom = factory.getOWLDeclarationAxiom(cA);
			manager.addAxiom(ontology, declarationAxiom);		

			OWLClass cB = factory.getOWLClass(IRI.create(nameSpace+material.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_")));
			declarationAxiom = factory.getOWLDeclarationAxiom(cB);
			manager.addAxiom(ontology, declarationAxiom);

			OWLClass rel = factory.getOWLClass(IRI.create(nameSpace+relator.getName().replaceAll(" ", "_")));
			declarationAxiom = factory.getOWLDeclarationAxiom(rel);
			manager.addAxiom(ontology, declarationAxiom);

			OWLObjectProperty propMediation0 = factory.getOWLObjectProperty(IRI.create(nameSpace+mediation0));
			OWLObjectProperty propMediation1 = factory.getOWLObjectProperty(IRI.create(nameSpace+mediation1));

			String propName;
			if(material.getName()==null || material.getName() == "" || material.getName() == " " || material.getName().length() == 0){
				propName = "material."+material.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+material.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
			}else{
				propName = material.getName().replaceAll(" ", "_");
			}
			OWLObjectProperty propMaterial = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));

			//SWRL

			//variaveis
			SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+"x"));
			SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+"y"));
			SWRLVariable varZ = factory.getSWRLVariable(IRI.create(nameSpace+"z"));

			//tipos das variavais
			SWRLAtom cAvX = factory.getSWRLClassAtom(cA, varX); //A(?x)
			SWRLAtom cBvY = factory.getSWRLClassAtom(cB, varY); //B(?y)
			SWRLAtom relvZ = factory.getSWRLClassAtom(rel, varZ); //relator(?z)

			SWRLAtom diffYX = factory.getSWRLDifferentIndividualsAtom(varY, varX);
			SWRLAtom diffXZ = factory.getSWRLDifferentIndividualsAtom(varX, varZ);
			SWRLAtom diffYZ = factory.getSWRLDifferentIndividualsAtom(varY, varZ);

			//sentencas
			Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
			antecedent.add(diffXZ); //DifferentFrom(?x,?z)
			antecedent.add(diffYZ); //DifferentFrom(?y,?z)
			antecedent.add(diffYX); //DifferentFrom(?y,?z)
			antecedent.add(cAvX); //A(?x)
			antecedent.add(cBvY); //B(?y)
			antecedent.add(relvZ); //relator(?z)
			antecedent.add(factory.getSWRLObjectPropertyAtom(propMediation0, varZ, varX)); //propMediation0(?x,?z)
			antecedent.add(factory.getSWRLObjectPropertyAtom(propMediation1, varZ, varY)); //propMediation1(?z,?y)

			Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
			consequent.add(factory.getSWRLObjectPropertyAtom(propMaterial, varX, varY)); //propMaterial(?x,?y)

			SWRLRule rule = factory.getSWRLRule(antecedent,consequent);		

			manager.applyChange(new AddAxiom(ontology, rule));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Grounded in the UFO's rules, we make:
	 * - All the substancesortal are differents from each other
	 * - Every substancesortal are differents from every top level moment
	 * - Every Top level moment are differents from each other
	 * - Every top level mixinclass is disjoint from every top level moment
	 * @param 
	 */
	private void processDifferentsClass() {
		Set<SubstanceSortal> setSortal = ontoParser.getAllInstances(SubstanceSortal.class);
		Set<MomentClass> setMoment = ontoParser.getAllInstances(MomentClass.class);
		Set<MixinClass> mixinClass =  ontoParser.getAllInstances(MixinClass.class);		

		//All the substancesortal are differents from each other
		for(SubstanceSortal ss1 : setSortal){
			OWLClass cl1 = factory.getOWLClass(IRI.create(nameSpace+ss1.getName().replaceAll(" ", "_")));
			for(SubstanceSortal ss2 : setSortal){
				if(!ss1.equals(ss2)){
					OWLClass cl2 = factory.getOWLClass(IRI.create(nameSpace+ss2.getName().replaceAll(" ", "_")));
					manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointClassesAxiom(cl1,cl2)));
				}
			}
		}


		//Every substancesortal are differents from every top level moment
		for(MomentClass sm : setMoment){
			OWLClass cl1 = factory.getOWLClass(IRI.create(nameSpace+sm.getName().replaceAll(" ", "_")));
			if(sm.getGeneral().isEmpty()){
				for(SubstanceSortal ss : setSortal){
					OWLClass cl2 = factory.getOWLClass(IRI.create(nameSpace+ss.getName().replaceAll(" ", "_")));
					manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointClassesAxiom(cl1,cl2)));
				}
			}			
		}

		//Every Top level moment are differents from each other		
		for(MomentClass sm1 : setMoment){
			OWLClass cl1 = factory.getOWLClass(IRI.create(nameSpace+sm1.getName().replaceAll(" ", "_")));
			if(sm1.getGeneral().isEmpty()){				
				for(MomentClass sm2 : setMoment){
					if(!sm1.equals(sm2)){
						if(sm2.getGeneral().isEmpty()){
							OWLClass cl2 = factory.getOWLClass(IRI.create(nameSpace+sm2.getName().replaceAll(" ", "_")));
							manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointClassesAxiom(cl1,cl2)));
						}
					}
				}
			}			
		}

		//every top level mixinclass is disjoint from every top level moment
		for(MixinClass smixin : mixinClass){
			if(smixin.getGeneral().isEmpty()){	
				OWLClass cl1 = factory.getOWLClass(IRI.create(nameSpace+smixin.getName().replaceAll(" ", "_")));
				for(MomentClass sm2 : setMoment){
					if(sm2.getGeneral().isEmpty()){
						OWLClass cl2 = factory.getOWLClass(IRI.create(nameSpace+sm2.getName().replaceAll(" ", "_")));
						manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointClassesAxiom(cl1,cl2)));
					}
				}
			}			
		}
	}
	/**
	 * Make the sons class subClassOf her father class 
	 * @param 
	 */
	private void processGeneralization(Generalization src) {
		//Verifica se é um datatype
		OWLClass father = factory.getOWLClass(IRI.create(nameSpace+src.getGeneral().getName().replaceAll(" ", "_")));        
		OWLClass ontSon = factory.getOWLClass(IRI.create(nameSpace+src.getSpecific().getName().replaceAll(" ", "_")));					

		OWLAxiom axiom = factory.getOWLSubClassOfAxiom(ontSon,father);
		_lstOWLSubClassOfAxiom.add(factory.getOWLSubClassOfAxiom(ontSon,father));
		manager.applyChange(new AddAxiom(ontology, axiom));	
	}
	/**
	 * Create the material association like a OWLObjectProperty.
	 * If the ass dosen't has a name. Her name will be material.ClassSrc.ClassDest
	 * @param 
	 */
	private void createMaterialAssociation(MaterialAssociation ass) {
		/*
		 * If the property has a name
		 * 	use this name
		 * else
		 * 	create a property with the name TypeProperty.ClassSrc.ClassDst
		 * If this name was used 
		 * 	make this property subPropertyOf the mother property
		 * */

		OWLObjectProperty prop,invProp;
		String propName;
		if(ass.getName()==null || ass.getName() == "" || ass.getName() == " " || ass.getName().length() == 0){
			propName = "material."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
		}else{
			propName = ass.getName().replaceAll(" ", "_");
		}

		//Search for other instances of this formal
		for(MaterialAssociation fa:ontoParser.getAllInstances(MaterialAssociation.class)){
			String formalName = "";
			if(fa.getName() == null || fa.getName() == "" || fa.getName() == " " || fa.getName().length() == 0){
				formalName = "material."+fa.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+fa.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
			}else{
				formalName = fa.getName().replaceAll(" ", "_");
			}
			if(!ass.equals(fa) && propName.equals(formalName)){
				propName += "."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
				break;				
			}
		}

		prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
		invProp = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+propName));

		//source class of the relation
		OWLClass ori = factory.getOWLClass(IRI.create(nameSpace+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")));		

		//destination class of the relation
		OWLClass dst = factory.getOWLClass(IRI.create(nameSpace+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_")));

		//Set domain and range from the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyDomainAxiom(prop, ori)));
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(prop, dst)));

		//Set domain and range from the inverse property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyDomainAxiom(invProp, dst)));
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(invProp, ori)));

		//set that the inverse property is the inverse of the property
		manager.applyChange(new AddAxiom(ontology,factory.getOWLInverseObjectPropertiesAxiom(prop, invProp)));

		//Process the cardinalities
		processRelations(ass,propName,1,false);
		processRelations(ass,"INV."+propName,0,true);

		//Add from the global list of the mediations
		_lstMaterialAssociations.add(prop);

		//Make the inverse property disjoint of the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop,invProp)));
	}
	/**
	 * Create the cardinality for the association. 
	 * The parameter side represents the side of the association src in turn.
	 * The inverse parameter is a flag for inform if is the normal association or his inverse. 
	 * @param 
	 */
	private void processRelations(Association src, String propName, int side, boolean inverse) {
		int upperCard = src.getMemberEnd().get(side).getUpper();
		int lowerCard = src.getMemberEnd().get(side).getLower();

		if(upperCard == -1 && lowerCard == 0){
			return;
		}

		OWLObjectProperty prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
		OWLClass dst, ori;
		if(!inverse){
			dst = factory.getOWLClass(IRI.create(nameSpace+src.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_")));
			ori = factory.getOWLClass(IRI.create(nameSpace+src.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")));
		}else{
			ori = factory.getOWLClass(IRI.create(nameSpace+src.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_")));
			dst = factory.getOWLClass(IRI.create(nameSpace+src.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")));
		}

		OWLEquivalentClassesAxiom ax = null;
		OWLSubClassOfAxiom sax = null; 

		if(upperCard == lowerCard){
			OWLObjectExactCardinality oecr = factory.getOWLObjectExactCardinality(lowerCard, prop, dst);
			ax = factory.getOWLEquivalentClassesAxiom(ori, oecr);
		}else if(upperCard == -1 && lowerCard == 1){
			OWLObjectSomeValuesFrom oecr = factory.getOWLObjectSomeValuesFrom(prop, dst);
			ax = factory.getOWLEquivalentClassesAxiom(ori, oecr);
		}else if (upperCard != -1 && lowerCard == 0){
			OWLObjectMaxCardinality maxcard = factory.getOWLObjectMaxCardinality(upperCard, prop,dst);
			sax = factory.getOWLSubClassOfAxiom(ori, maxcard);
			_lstOWLSubClassOfAxiom.add(factory.getOWLSubClassOfAxiom(ori, maxcard));
		}else{		
			if(upperCard == -1){
				OWLObjectMinCardinality mincard = factory.getOWLObjectMinCardinality(lowerCard, prop,dst);
				ax = factory.getOWLEquivalentClassesAxiom(ori, mincard);	
			}else{
				OWLObjectMaxCardinality maxcard = factory.getOWLObjectMaxCardinality(upperCard, prop,dst);
				OWLObjectMinCardinality mincard = factory.getOWLObjectMinCardinality(lowerCard, prop,dst);
				OWLObjectIntersectionOf oio =  factory.getOWLObjectIntersectionOf(maxcard,mincard);
				ax = factory.getOWLEquivalentClassesAxiom(ori, oio);
			}

		}

		if(ax != null){
			manager.applyChange(new AddAxiom(ontology, ax));
		}
		if(sax != null){
			manager.applyChange(new AddAxiom(ontology, sax));
		}
	}
	/**
	 * Create the cardinality for the association. 
	 * The parameter side represents the side of the association src in turn.
	 * The inverse parameter is a flag for inform if is the normal association or his inverse. 
	 * This method put a meronymic association like a son of his father meronymic association
	 * @param 
	 */
	private void processRelationMeronymic(Association src, String relName, String propName, int side, boolean inverse) {
		int upperCard = src.getMemberEnd().get(side).getUpper();
		int lowerCard = src.getMemberEnd().get(side).getLower();

		if(upperCard == -1 && lowerCard == 0){
			return;
		}

		OWLObjectProperty propMom = factory.getOWLObjectProperty(IRI.create(nameSpace+relName));
		OWLObjectProperty prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));

		OWLSubObjectPropertyOfAxiom sopa = factory.getOWLSubObjectPropertyOfAxiom(prop,propMom);
		manager.applyChange(new AddAxiom(ontology, sopa));

		OWLClass dst, ori;
		if(!inverse){
			dst = factory.getOWLClass(IRI.create(nameSpace+src.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_")));
			ori = factory.getOWLClass(IRI.create(nameSpace+src.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")));
		}else{
			ori = factory.getOWLClass(IRI.create(nameSpace+src.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_")));
			dst = factory.getOWLClass(IRI.create(nameSpace+src.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")));
		}


		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyDomainAxiom(prop, ori)));
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(prop, dst)));


		OWLEquivalentClassesAxiom ax = null;
		OWLSubClassOfAxiom sax = null; 

		if(upperCard == lowerCard){
			OWLObjectExactCardinality oecr = factory.getOWLObjectExactCardinality(lowerCard, prop, dst);
			ax = factory.getOWLEquivalentClassesAxiom(ori, oecr);
		}else if(upperCard == -1 && lowerCard == 1){
			OWLObjectSomeValuesFrom oecr = factory.getOWLObjectSomeValuesFrom(prop, dst);
			ax = factory.getOWLEquivalentClassesAxiom(ori, oecr);
		}else if (upperCard != -1 && lowerCard == 0){
			OWLObjectMaxCardinality maxcard = factory.getOWLObjectMaxCardinality(upperCard, prop,dst);
			sax = factory.getOWLSubClassOfAxiom(ori, maxcard);
			_lstOWLSubClassOfAxiom.add(factory.getOWLSubClassOfAxiom(ori, maxcard));
		}else{		
			if(upperCard == -1){
				OWLObjectMinCardinality mincard = factory.getOWLObjectMinCardinality(lowerCard, prop,dst);
				ax = factory.getOWLEquivalentClassesAxiom(ori, mincard);	
			}else{
				OWLObjectMaxCardinality maxcard = factory.getOWLObjectMaxCardinality(upperCard, prop,dst);
				OWLObjectMinCardinality mincard = factory.getOWLObjectMinCardinality(lowerCard, prop,dst);
				OWLObjectIntersectionOf oio =  factory.getOWLObjectIntersectionOf(maxcard,mincard);
				ax = factory.getOWLEquivalentClassesAxiom(ori, oio);
			}
		}

		if(ax != null){
			manager.applyChange(new AddAxiom(ontology, ax));
		}
		if(sax != null){
			manager.applyChange(new AddAxiom(ontology, sax));
		}
	}
	/**
	 * Process the generalizationset putting his stereotypes (disjoint and/or complete)
	 * @param 
	 */
	private void processGeneralizationSet(GeneralizationSet src) {
		if(!src.getGeneralization().isEmpty()){
			if((src.isIsDisjoint() && src.isIsCovering()) || src.getGeneralization().get(0).getSpecific() instanceof Phase){
				//{disjoint, complete} or is a Phase Partition
				processGeneralization(src.getGeneralization(), true, true);
			}else if(src.isIsDisjoint()){
				//{disjoint}
				processGeneralization(src.getGeneralization(), true, false);
			}else if(src.isIsCovering()){
				//{complete}
				processGeneralization(src.getGeneralization(), false, true);
			}else{
				//{}
				processGeneralization(src.getGeneralization(), false, false);
			}		
		}
	}
	/**
	 * This method is called by the processGeneralizationSet setted with his boolean parameter disjoint and complete
	 * @param 
	 */
	private void processGeneralization(EList<Generalization> genSet, boolean disjoint, boolean complete) {
		Set<OWLClass> lst = new HashSet<OWLClass>();

		OWLClass father = factory.getOWLClass(IRI.create(nameSpace+genSet.get(0).getGeneral().getName().replaceAll(" ", "_")));        
		if(father != null){			
			for(Generalization son:genSet){
				if(!genSet.get(0).getGeneral().getName().equals(son.getSpecific().getName())){					
					OWLClass ontSon = factory.getOWLClass(IRI.create(nameSpace+son.getSpecific().getName().replaceAll(" ", "_")));					
					if(son != null){
						//{}
						OWLAxiom axiom = factory.getOWLSubClassOfAxiom(ontSon,father);	
						_lstOWLSubClassOfAxiom.add(factory.getOWLSubClassOfAxiom(ontSon, father));
						manager.applyChange(new AddAxiom(ontology, axiom));						
						if(complete){
							//{complete}
							lst.add(ontSon);							
						}
					}
				}
			}

			if(!lst.isEmpty()){				
				OWLObjectUnionOf ouf = factory.getOWLObjectUnionOf(lst);
				OWLEquivalentClassesAxiom eqclax = factory.getOWLEquivalentClassesAxiom(father, ouf);
				manager.addAxiom(ontology, eqclax);
			}

			if(disjoint){
				//{disjoint}
				for(Generalization ssA:genSet){
					OWLClass _ssA = factory.getOWLClass(IRI.create(nameSpace+ssA.getSpecific().getName().replaceAll(" ", "_")));
					for(Generalization ssB:genSet){
						if(!ssA.getSpecific().getName().equals(ssB.getSpecific().getName())){
							OWLClass _ssB = factory.getOWLClass(IRI.create(nameSpace+ssB.getSpecific().getName().replaceAll(" ", "_")));
							OWLAxiom axiom = factory.getOWLDisjointClassesAxiom(_ssB,_ssA);		
							manager.applyChange(new AddAxiom(ontology, axiom));	
						}
					}
				}
			}			
		}
	}

	/**
	 * Convert a RefOntoUML.Class in a OWLClass in the ontology and
	 * create the direct attributes from this class 
	 * @param RefOntoUML.Class
	 */
	private void processClass(Class src) {
		OWLClass cls = factory.getOWLClass(IRI.create(nameSpace+src.getName().replaceAll(" ", "_")));
		OWLDeclarationAxiom declarationAxiom = factory.getOWLDeclarationAxiom(cls);
		manager.addAxiom(ontology, declarationAxiom);
		if(!src.getAttribute().isEmpty()){
			createAttributes(src);
		}
	}
	/**
	 * Create the mediation association like a OWLObjectProperty.
	 * If the ass dosen't has a name. Her name will be mediation.ClassSrc.ClassDest
	 * @param 
	 */
	private String createMediationAssociation(Association ass) {
		/*
		 * If the property has a name
		 * 	use this name
		 * else
		 * 	create a property with the name TypeProperty.ClassSrc.ClassDst
		 * If this name was used 
		 * 	make this property subPropertyOf the mother property
		 * */

		OWLObjectProperty prop,invProp;
		String propName;
		if(ass.getName()==null || ass.getName() == "" || ass.getName() == " " || ass.getName().length() == 0){
			propName = "mediation."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
		}else{
			propName = ass.getName().replaceAll(" ", "_");
		}

		//Search for other instances of this formal
		for(Mediation fa:ontoParser.getAllInstances(Mediation.class)){
			String formalName = "";
			if(fa.getName() == null || fa.getName() == "" || fa.getName() == " "|| fa.getName().length() == 0){
				formalName = "mediation."+fa.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+fa.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
			}else{
				formalName = fa.getName().replaceAll(" ", "_");
			}
			if(!ass.equals(fa) && propName.equals(formalName)){
				propName += "."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
				break;				
			}
		}

		prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
		invProp = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+propName));

		//source class of the relation
		OWLClass ori = factory.getOWLClass(IRI.create(nameSpace+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")));		

		//destination class of the relation
		OWLClass dst = factory.getOWLClass(IRI.create(nameSpace+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_")));

		//Set domain and range from the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyDomainAxiom(prop, ori)));
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(prop, dst)));

		//Set domain and range from the inverse property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyDomainAxiom(invProp, dst)));
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(invProp, ori)));

		//set that the inverse property is the inverse of the property
		manager.applyChange(new AddAxiom(ontology,factory.getOWLInverseObjectPropertiesAxiom(prop, invProp)));

		//Process the cardinalities
		processRelations(ass,propName,1,false);
		processRelations(ass,"INV."+propName,0,true);

		//Add from the global list of the mediations
		_lstMediationAssociations.add(prop);

		//Make the inverse property disjoint of the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop,invProp)));

		return propName;
	}

	/**
	 * Return the OWLDatatype for the range.
	 * If this is a unknown range, return null
	 * @param range
	 * @return the OWLDatatype for a known range, else null
	 */
	private OWLDatatype getDataTypeRange(String range){
		if (range.equalsIgnoreCase("unsigned_int")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_UNSIGNED_INT.getIRI());
		}else if(range.equalsIgnoreCase("int") || range.equalsIgnoreCase("integer")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
		}else if(range.equalsIgnoreCase("unsigned_byte")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_UNSIGNED_BYTE.getIRI());
		}else if(range.equalsIgnoreCase("double")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_DOUBLE.getIRI());
		}else if(range.equalsIgnoreCase("string")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());
		}else if(range.equalsIgnoreCase("normalized_string")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_NORMALIZED_STRING.getIRI());
		}else if(range.equalsIgnoreCase("boolean")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_BOOLEAN.getIRI());
		}else if(range.equalsIgnoreCase("hex_binary")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_HEX_BINARY.getIRI());
		}else if(range.equalsIgnoreCase("short")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_SHORT.getIRI());
		}else if(range.equalsIgnoreCase("byte")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_BYTE.getIRI());
		}else if(range.equalsIgnoreCase("unsigned_long")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_UNSIGNED_LONG.getIRI());
		}
		return null;
	}

	/**
	 * Create all direct attributes from the Classifier
	 * @param RefOntoUML.Classifier
	 */
	private void createAttributes(Classifier src){	
		for(Property p:src.getAttribute()){		
			int upperCard = p.getUpper();
			int lowerCard = p.getLower();

			OWLDatatype tipoAtributo = null;
			OWLDataPropertyRangeAxiom set = null;


			if(p.getType()== null){
				//If the type of this property isn't in the model
				tipoAtributo = factory.getOWLDatatype(OWL2Datatype.RDFS_LITERAL.getIRI());
			}else{
				String range = p.getType().getName().replaceAll(" ", "_");
				//Search for the range of this property
				tipoAtributo = getDataTypeRange(range);
				if(tipoAtributo == null){
					//If the type exist in the model but can be a other class.
					//set the name of this attribute stating by the name of this classifier
					dt_atual = src.getName().replaceAll(" ", "_")+"#";
					//Process the chain of the properties
					processDataTypeProperty(p);
					dt_atual = "";

					if(_aux_tipoAtributo == null){
						//if isn't a type in owl
						tipoAtributo = factory.getOWLDatatype(OWL2Datatype.RDFS_LITERAL.getIRI());
					}else{
						//if is a valid type 
						continue;
					}
				}
			}
			OWLDataProperty atributo = factory.getOWLDataProperty(IRI.create(nameSpace+src.getName().replaceAll(" ", "_")+"."+p.getName().replaceAll(" ", "_")));

			set = factory.getOWLDataPropertyRangeAxiom(atributo, tipoAtributo);
			manager.applyChange(new AddAxiom(ontology, set));

			OWLClass owner = factory.getOWLClass(IRI.create(nameSpace+src.getName().replaceAll(" ", "_")));
			//set the father of this datatype
			manager.applyChange(new AddAxiom(ontology, factory.getOWLDataPropertyDomainAxiom(atributo, owner)));

			//used to make the dataproperties disjoint
			if(_aux_hashClassToDataProperty.get(owner) == null){
				_aux_hashClassToDataProperty.put(owner, "");	
			}
			_aux_hashClassToDataProperty.put(owner, _aux_hashClassToDataProperty.get(owner)+atributo.getIRI().toString()+"@");

			OWLEquivalentClassesAxiom ax = null;

			if(upperCard == 1 && lowerCard == 1){
				OWLClassExpression oecr = factory.getOWLDataExactCardinality(1, atributo, tipoAtributo);
				ax = factory.getOWLEquivalentClassesAxiom(owner, oecr);
			}else if(upperCard == -1 && lowerCard == 1){
				OWLDataMinCardinality mincard = factory.getOWLDataMinCardinality(1, atributo,tipoAtributo);
				ax = factory.getOWLEquivalentClassesAxiom(owner, mincard);
			}else if (upperCard == 1 && lowerCard == 0){
				OWLDataMinCardinality mincard = factory.getOWLDataMinCardinality(1, atributo,tipoAtributo);
				OWLDataMaxCardinality maxcard = factory.getOWLDataMaxCardinality(1, atributo,tipoAtributo);
				OWLObjectIntersectionOf oio =  factory.getOWLObjectIntersectionOf(maxcard,mincard);
				ax = factory.getOWLEquivalentClassesAxiom(owner, oio);
			}else if(upperCard > 0 && lowerCard > 0){		
				OWLDataMinCardinality mincard = factory.getOWLDataMinCardinality(lowerCard, atributo,tipoAtributo);
				OWLDataMaxCardinality maxcard = factory.getOWLDataMaxCardinality(upperCard, atributo,tipoAtributo);
				OWLObjectIntersectionOf oio =  factory.getOWLObjectIntersectionOf(maxcard,mincard);
				ax = factory.getOWLEquivalentClassesAxiom(owner, oio);
			}
			manager.applyChange(new AddAxiom(ontology, ax));
		}	
	}

	/**
	 * Return the Mediations and Material Relation from a specific Relator 'r'
	 * 
	 * @param relator
	 * @return A list with the all MaterialAssociation from the Relator
	 */
	private List<MaterialAssociation> getRelatorMaterials(Relator r){
		List<MaterialAssociation> lst = new ArrayList<>();
		try {
			MaterialAssociation m;
			for(Derivation d:ontoParser.getAllInstances(Derivation.class)){
				//Verifica se a Derivation eh do relator. Sendo, pega o outro MemberEnd que serah a
				//MaterialAssociation
				if(d.getMemberEnd().get(1).getType().equals(r)){
					m = (MaterialAssociation)d.getMemberEnd().get(0).getType();
					lst.add(m);
				}else if(d.getMemberEnd().get(0).getType().equals(r)){
					m = (MaterialAssociation)d.getMemberEnd().get(1).getType();
					lst.add(m);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	/**
	 * Create the SWRL for the componentOf relation
	 * @param 
	 */
	private void createRelation_componentOf(){
		_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"componentOf")));

		//Make the inverse property disjoint of the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(factory.getOWLObjectProperty(IRI.create(nameSpace+"componentOf")),factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.componentOf")))));

		OWLObjectProperty rel = factory.getOWLObjectProperty(IRI.create(nameSpace+"componentOf"));

		OWLIrreflexiveObjectPropertyAxiom iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(rel);//Irreflexive
		manager.applyChange(new AddAxiom(ontology, iopa));
		OWLAsymmetricObjectPropertyAxiom aopa = factory.getOWLAsymmetricObjectPropertyAxiom(rel);//Asymetric
		manager.applyChange(new AddAxiom(ontology, aopa));

		OWLObjectProperty inv = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.componentOf"));

		iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(inv);//Irreflexive
		manager.applyChange(new AddAxiom(ontology, iopa));
		aopa = factory.getOWLAsymmetricObjectPropertyAxiom(inv);//Asymetric
		manager.applyChange(new AddAxiom(ontology, aopa));

		OWLInverseObjectPropertiesAxiom inverse = factory.getOWLInverseObjectPropertiesAxiom(inv, rel);
		manager.applyChange(new AddAxiom(ontology, inverse));

		//variaveis
		SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+"x"));
		SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+"y"));
		SWRLVariable varZ = factory.getSWRLVariable(IRI.create(nameSpace+"z"));

		//Fazendo as variaveis diferentes
		SWRLAtom diffYX = factory.getSWRLDifferentIndividualsAtom(varY, varX);
		SWRLAtom diffXZ = factory.getSWRLDifferentIndividualsAtom(varX, varZ);
		SWRLAtom diffYZ = factory.getSWRLDifferentIndividualsAtom(varY, varZ);

		//sentencas
		Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
		antecedent.add(diffXZ); //DifferentFrom(?x,?z)
		antecedent.add(diffYZ); //DifferentFrom(?y,?z)
		antecedent.add(diffYX); //DifferentFrom(?y,?z)
		antecedent.add(factory.getSWRLObjectPropertyAtom(rel, varX, varY)); //prop(?x,?Y)
		antecedent.add(factory.getSWRLObjectPropertyAtom(rel, varY, varZ)); //prop(?y,?z)

		Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
		consequent.add(factory.getSWRLObjectPropertyAtom(rel, varX, varZ)); //prop(?x,?z)

		SWRLRule rule = factory.getSWRLRule(antecedent,consequent);		
		manager.applyChange(new AddAxiom(ontology, rule));
	}
	/**
	 * Create the annotation present in the RefOntoUML.
	 * Create annotations for the ontology, class and dataproperty
	 * @param 
	 */
	private void processAnnotation(){
		for(PackageableElement p : ontoParser.getAllInstances(PackageableElement.class)){
			if(p.getOwnedComment() != null && !p.getOwnedComment().isEmpty()){
				if(p instanceof Class){
					for(RefOntoUML.Comment c : p.getOwnedComment()){
						String comment = c.getBody().replaceAll("\\<[^>]*>","").replaceAll("\"", "");
						comment = Normalizer.normalize(comment, Normalizer.Form.NFD);  
						comment = comment.replaceAll("[^\\p{ASCII}]", "");  
						OWLClass cls = factory.getOWLClass(IRI.create(nameSpace+p.getName().replaceAll(" ", "_")));
						OWLAnnotation commentAnno = factory.getOWLAnnotation( factory.getRDFSComment(),  factory.getOWLLiteral(comment, "pt"));
						OWLAxiom ax = factory.getOWLAnnotationAssertionAxiom( cls.getIRI(), commentAnno);
						manager.applyChange(new AddAxiom(ontology, ax));
					}
				}else{
					for(RefOntoUML.Comment c : p.getOwnedComment()){
						String comment = c.getBody().replaceAll("\\<[^>]*>","").replaceAll("\"", "");
						comment = Normalizer.normalize(comment, Normalizer.Form.NFD);  
						comment = comment.replaceAll("[^\\p{ASCII}]", "");  
						OWLAnnotation commentAnno = factory.getOWLAnnotation( factory.getRDFSComment(),  factory.getOWLLiteral(comment, "pt"));
						OWLAxiom ax = factory.getOWLAnnotationAssertionAxiom( IRI.create(nameSpace.substring(0,nameSpace.length()-1)), commentAnno);
						manager.applyChange(new AddAxiom(ontology, ax));
					}
				}
			}
		}
	}
	/**
	 * This is a pos-method.
	 * It put all equivalentClass, disjointClass and subClassOf axioms from a class in just one axiom for all classes
	 * @param 
	 */
	private void processAxioms(){
		for(OWLClass c : ontology.getClassesInSignature()){

			processAxiom(c);
		}
	}
	
	/**
	 * This is a pos-method.
	 * It put all equivalentClass, disjointClass and subClassOf axioms from a class in just one axiom for only one class
	 * @param 
	 */
	private void processAxiom(OWLClass c){
		Set<OWLClassExpression> eqClsExpr = new HashSet<OWLClassExpression>();
		Set<OWLClassExpression> dsjClsExpr = new HashSet<OWLClassExpression>();
		Set<OWLClassExpression> sbClsExpr = new HashSet<OWLClassExpression>();

		Set<OWLClassAxiom> setClsAxs = ontology.getAxioms(c);
		Iterator<OWLClassAxiom> itr = setClsAxs.iterator();

		//Process EquivalentClassAxiom
		while(itr.hasNext()) {
			OWLClassAxiom ax = itr.next();

			if(ax instanceof OWLEquivalentClassesAxiom){
				OWLNaryClassAxiom nax = (OWLNaryClassAxiom)ax;
				eqClsExpr.addAll(nax.getClassExpressions());
				manager.removeAxiom(ontology, ax);
			} 
			if(ax instanceof OWLSubClassOfAxiom){
				OWLSubClassOfAxiom sax = (OWLSubClassOfAxiom)ax;
				sbClsExpr.add(sax.getSuperClass());
				manager.removeAxiom(ontology, ax);	
			}
		}

		if(eqClsExpr.size() > 1){
			eqClsExpr.remove(c);
			OWLObjectIntersectionOf oi = factory.getOWLObjectIntersectionOf(eqClsExpr);
			OWLEquivalentClassesAxiom eqAx = factory.getOWLEquivalentClassesAxiom(c, oi);
			manager.applyChange(new AddAxiom(ontology, eqAx));
		}
		if(dsjClsExpr.size() > 1){
			manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointClassesAxiom(dsjClsExpr)));
		}
		if(sbClsExpr.size() > 1){
			OWLObjectIntersectionOf oi = factory.getOWLObjectIntersectionOf(sbClsExpr);
			OWLSubClassOfAxiom sbAx = factory.getOWLSubClassOfAxiom(c, oi);
			manager.applyChange(new AddAxiom(ontology, sbAx));
		}else{
			Iterator<OWLClassExpression> i = sbClsExpr.iterator();
			while(i.hasNext()) {
				OWLClassExpression ax = i.next();
				OWLSubClassOfAxiom sbAx = factory.getOWLSubClassOfAxiom(c, ax);
				manager.applyChange(new AddAxiom(ontology, sbAx));
			}
		}
	}
}