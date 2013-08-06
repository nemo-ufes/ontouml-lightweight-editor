package br.ufes.inf.nemo.oled.ontouml2owl_swrl;

import java.io.ByteArrayOutputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
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
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
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
import org.semanticweb.owlapi.util.AxiomSubjectProvider;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import RefOntoUML.Association;
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
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class Transformer {	
	private String nameSpace;
	private OntoUMLParser ontoParser;

	private OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	private OWLOntology ontology;
	private OWLDataFactory factory;

	private Set<DataType> _lstDataType;
	private List<String> _lstDataTypeAttributes = new ArrayList<String>();

	private Set<OWLObjectProperty> _lstMaterialAssociations = new HashSet<OWLObjectProperty>();
	private Set<OWLObjectProperty> _lstFormalAssociations = new HashSet<OWLObjectProperty>();
	private Set<OWLObjectProperty> _lstMediationAssociations = new HashSet<OWLObjectProperty>();
	private Set<OWLObjectProperty> _lstPartofAssociations = new HashSet<OWLObjectProperty>();

	//Usado para criar o disjoint dos dataproperties
	private HashMap<OWLClass, String> _aux_hashClassToDataProperty = new HashMap<OWLClass,String>();

	/**
	 * Create a Transformer and use the nameSpace as the ontology URI
	 * 
	 * @param nameSpace
	 */
	public Transformer(String nameSpace) {
		this.nameSpace = nameSpace+"#";

		try {
			this.ontology = manager.createOntology(IRI.create(nameSpace));
			this.factory = manager.getOWLDataFactory();
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/**
	 * Transform a RefOntoUML.Model to OWL
	 * 
	 * @param ecoreModel
	 * @return a String with the OWL code
	 */
	public String transform(Model ecoreModel) {

		ontoParser = new OntoUMLParser(ecoreModel);
		_lstDataType = ontoParser.getAllInstances(RefOntoUML.DataType.class);

		//Class
		//cria um Class para cada classe ontoUML
		for(RefOntoUML.Class src: ontoParser.getAllInstances(RefOntoUML.Class.class))
			processClass(src);
		processDataTypeStructured();
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

		//Relator
		for(Relator src: ontoParser.getAllInstances(Relator.class))
			processRelator(src);

		//FormalAssociation
		for(FormalAssociation src: ontoParser.getAllInstances(FormalAssociation.class))
			processFormalAssociation(src);

		//Process Part-whole: componentOf
		if(ontoParser.getAllInstances(componentOf.class).size() > 1)
			createRelation_componentOf();
		for(componentOf a : ontoParser.getAllInstances(componentOf.class)){
			processMeronymic(a, "componentOf");
		}	

		//Process Part-whole: subCollectionOf 
		if(ontoParser.getAllInstances(subCollectionOf.class).size() > 1)
			createRelation_subCollectionOf();
		for(subCollectionOf a : ontoParser.getAllInstances(subCollectionOf.class)){
			processMeronymic(a, "subCollectionOf");
		}

		//Process Part-whole: subQuantityOf 
		if(ontoParser.getAllInstances(subQuantityOf.class).size() > 1)
			createRelation_subQuantityOf();
		for(subQuantityOf a : ontoParser.getAllInstances(subQuantityOf.class)){
			processMeronymic(a, "subQuantityOf");
		}

		//Process Part-whole: memberOf 
		if(ontoParser.getAllInstances(memberOf.class).size() >= 1 && ontoParser.getAllInstances(subCollectionOf.class).size() >= 1)
			createRelation_memberOf();
		for(memberOf a : ontoParser.getAllInstances(memberOf.class)){
			processMeronymic(a, "memberOf");
		}

		//Process disjuntion of associations
		//processDisjointionOfAssociations();

		//Process Comments
		processAnnotation();

		//Process Axioms
		//		processAxioms();


		try {	
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			manager.saveOntology(ontology, os);
			//String s = new String(os.toByteArray(),"ISO-8859-1");
			String s = new String(os.toByteArray(),"UTF-8");
			return s;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private void processDisjointionOfAssociations() {
		Set<OWLObjectProperty> lst = new HashSet<OWLObjectProperty>();

		lst.addAll(_lstPartofAssociations);
		lst.addAll(_lstMaterialAssociations);
		lst.addAll(_lstMediationAssociations);

		for(OWLObjectProperty prop : _lstFormalAssociations){
			lst.add(prop);
			manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(lst)));
			lst.remove(lst.size()-1);
		}

		lst = new HashSet<OWLObjectProperty>();

		lst.addAll(_lstPartofAssociations);
		lst.addAll(_lstMaterialAssociations);
		lst.addAll(_lstFormalAssociations);

		for(OWLObjectProperty prop : _lstMediationAssociations){
			lst.add(prop);
			manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(lst)));
			lst.remove(lst.size()-1);
		}	

		lst = new HashSet<OWLObjectProperty>();

		lst.addAll(_lstPartofAssociations);
		lst.addAll(_lstFormalAssociations);
		lst.addAll(_lstMediationAssociations);

		for(OWLObjectProperty prop : _lstMaterialAssociations){
			lst.add(prop);
			manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(lst)));
			lst.remove(lst.size()-1);
		}		
	}

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
		if(ass.getName()==null){
			propName = "formal."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
		}else{
			propName = ass.getName().replaceAll(" ", "_");
		}

		prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
		invProp = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+propName));

		if(_lstFormalAssociations.contains(prop)){
			OWLObjectProperty topProp = prop;
			OWLObjectProperty invTopProp = invProp;

			//Get the new name from the prop
			propName += "."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");

			//Set new props
			prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
			invProp = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+propName));

			//Make the new props subPropertyOf the mother prop of them
			manager.addAxiom(ontology, factory.getOWLSubObjectPropertyOfAxiom(prop, topProp));
			manager.addAxiom(ontology, factory.getOWLSubObjectPropertyOfAxiom(invProp, invTopProp));
		}else{
			int cont = 0;
			//Search for other instances of this formal
			for(FormalAssociation fa:ontoParser.getAllInstances(FormalAssociation.class)){
				if(ass.getName().equals(fa.getName())){
					cont++;
					if(cont >= 2)
						break;
				}
			}
			if(cont >= 2){
				//If has some object with the equal name
				_lstFormalAssociations.add(prop);
				_lstFormalAssociations.add(invProp);
				//set that the inverse property is the inverse of the property
				manager.applyChange(new AddAxiom(ontology,factory.getOWLInverseObjectPropertiesAxiom(prop, invProp)));
				processFormalAssociation(ass);
				return;
			}
		}

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
		_lstFormalAssociations.add(invProp);
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
		if(ass.getName()==null){
			processRelationMeronymic(ass, propName, propName+"."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_"), 1, false);
			processRelationMeronymic(ass, "INV."+propName, "INV."+propName+"."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_"), 0, true);
		}else{
			processRelationMeronymic(ass,propName,ass.getName().replaceAll(" ", "_"),1,false);
			processRelationMeronymic(ass,"INV."+propName,"INV."+ass.getName().replaceAll(" ", "_"),0,true);
		}
	}

	private void createRelation_subQuantityOf() {
		_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"subQuantityOf")));
		_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.subQuantityOf")));

		createRelationPartOf_SWRL("subQuantityOf");

		OWLObjectProperty rel = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.subQuantityOf"));

		OWLIrreflexiveObjectPropertyAxiom iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(rel);//Irreflexive
		manager.applyChange(new AddAxiom(ontology, iopa));
		OWLAsymmetricObjectPropertyAxiom aopa = factory.getOWLAsymmetricObjectPropertyAxiom(rel);//Asymetric
		manager.applyChange(new AddAxiom(ontology, aopa));

		OWLInverseObjectPropertiesAxiom inv = factory.getOWLInverseObjectPropertiesAxiom(factory.getOWLObjectProperty(IRI.create(nameSpace+"subQuantityOf")), rel);
		manager.applyChange(new AddAxiom(ontology, inv));
	}

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
		_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.subCollectionOf")));

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
		_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.memberOf")));

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

	private void processRelator(Relator src) {

		try {
			//TODO MUDAR PARA O ONTOPARSER
			List<MaterialAssociation> lstMaterialAssociation = this.getRelatorMaterials(src);
			List<Mediation> lstMediation = ontoParser.getRelatorsMediations(src);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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

	//Variáveis auxiliares semi-globais
	private String dt_atual = "";
	private OWLDatatype _aux_tipoAtributo = null;

	private void processDataTypeAttributo(DataType src) {
		String range;
		OWLDatatype dt = null;
		for(Property p:src.getAttribute()){
			range = p.getType().getName().replaceAll(" ", "_");
			dt = getDataTypeRange(range);
			if(dt == null){
				String aux = dt_atual;
				dt_atual += src.getName().replaceAll(" ", "_")+"@"+p.getName().replaceAll(" ", "_")+"@"+p.getType().getName().replaceAll(" ", "_")+"@";
				processDataTypeProperty(p);
				dt_atual = aux;					
			}else{
				_lstDataTypeAttributes.add(dt_atual.replaceAll("@", "_").substring(0,dt_atual.length()-1)+"."+p.getName().replaceAll(" ", "_")+":"+range);
				_aux_tipoAtributo = dt;
			}
		}
	}

	private void processDataTypeProperty(Property src){
		for(DataType dt:_lstDataType){			
			if(dt.getName().equals(src.getType().getName())){
				if(dt_atual == "" || dt_atual.contains("#"))
					dt_atual += src.getName().replaceAll(" ", "_")+"@";
				processDataTypeAttributo(dt);
			}
		}
	}

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

			OWLObjectProperty propMaterial = factory.getOWLObjectProperty(IRI.create(nameSpace+material.getName().replaceAll(" ", "_")));

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processDifferentsClass() {
		Set<SubstanceSortal> setSortal = ontoParser.getAllInstances(SubstanceSortal.class);
		Set<MomentClass> setMoment = ontoParser.getAllInstances(MomentClass.class);
		Set<MixinClass> mixinClass =  ontoParser.getAllInstances(MixinClass.class);		
		Set<OWLClass> lst = new HashSet<OWLClass>();

		//All the substancesortal are differents from each other
		for(SubstanceSortal ss : setSortal){
			lst.add(factory.getOWLClass(IRI.create(nameSpace+ss.getName().replaceAll(" ", "_"))));
		}
		manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointClassesAxiom(lst)));

		//Every substancesortal are differents from every top level moment
		for(MomentClass sm : setMoment){
			if(sm.getGeneral().isEmpty()){			
				lst.add(factory.getOWLClass(IRI.create(nameSpace+sm.getName().replaceAll(" ", "_"))));
				lst.remove(lst.size()-1);
			}			
		}

		lst = new HashSet<OWLClass>();
		//Every Top level moment are differents from each other		
		for(MomentClass sm : setMoment){
			if(sm.getGeneral().isEmpty()){				
				lst.add(factory.getOWLClass(IRI.create(nameSpace+sm.getName().replaceAll(" ", "_"))));
			}			
		}

		if(lst.size() > 1){
			manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointClassesAxiom(lst)));
		}
		//every top level mixinclass is disjoint from every top level moment
		for(MixinClass smixin : mixinClass){
			if(smixin.getGeneral().isEmpty()){		
				lst.add(factory.getOWLClass(IRI.create(nameSpace+smixin.getName().replaceAll(" ", "_"))));
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointClassesAxiom(lst)));
				lst.remove(factory.getOWLClass(IRI.create(nameSpace+smixin.getName().replaceAll(" ", "_"))));
			}			
		}
	}

	private void processGeneralization(Generalization src) {
		OWLClass father = factory.getOWLClass(IRI.create(nameSpace+src.getGeneral().getName().replaceAll(" ", "_")));        
		OWLClass ontSon = factory.getOWLClass(IRI.create(nameSpace+src.getSpecific().getName().replaceAll(" ", "_")));					

		OWLAxiom axiom = factory.getOWLSubClassOfAxiom(ontSon,father);				        
		manager.applyChange(new AddAxiom(ontology, axiom));	
	}

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
		if(ass.getName()==null){
			propName = "material."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
		}else{
			propName = ass.getName().replaceAll(" ", "_");
		}

		prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
		invProp = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+propName));

		if(_lstMaterialAssociations.contains(prop)){
			OWLObjectProperty topProp = prop;
			OWLObjectProperty invTopProp = invProp;

			//Get the new name from the prop
			propName += "."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");

			//Set new props
			prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
			invProp = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+propName));

			//Make the new props subPropertyOf the mother prop of them
			manager.addAxiom(ontology, factory.getOWLSubObjectPropertyOfAxiom(prop, topProp));
			manager.addAxiom(ontology, factory.getOWLSubObjectPropertyOfAxiom(invProp, invTopProp));
		}else{
			int cont = 0;
			//Search for other instances of this material
			for(MaterialAssociation ma:ontoParser.getAllInstances(MaterialAssociation.class)){
				if(ass.getName().equals(ma.getName())){
					cont++;
					if(cont >= 2)
						break;
				}
			}
			if(cont >= 2){
				//If has some object with the equal name
				_lstMaterialAssociations.add(prop);
				_lstMaterialAssociations.add(invProp);
				//set that the inverse property is the inverse of the property
				manager.applyChange(new AddAxiom(ontology,factory.getOWLInverseObjectPropertiesAxiom(prop, invProp)));
				createMaterialAssociation(ass);
				return;
			}
		}

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
		_lstMaterialAssociations.add(invProp);
	}

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


	private void processClass(Class src) {
		OWLClass cls = factory.getOWLClass(IRI.create(nameSpace+src.getName().replaceAll(" ", "_")));
		OWLDeclarationAxiom declarationAxiom = factory.getOWLDeclarationAxiom(cls);
		manager.addAxiom(ontology, declarationAxiom);
		if(!src.getAttribute().isEmpty()){
			createAttributes(src);
		}
	}


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
		if(ass.getName()==null){
			propName = "mediation."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
		}else{
			propName = ass.getName().replaceAll(" ", "_");
		}


		prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
		invProp = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+propName));

		if(_lstMediationAssociations.contains(prop)){
			OWLObjectProperty topProp = prop;
			OWLObjectProperty invTopProp = invProp;

			//Get the new name from the prop
			propName += "."+ass.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ass.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");

			//Set new props
			prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
			invProp = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+propName));

			//Make the new props subPropertyOf the mother prop of them
			manager.addAxiom(ontology, factory.getOWLSubObjectPropertyOfAxiom(prop, topProp));
			manager.addAxiom(ontology, factory.getOWLSubObjectPropertyOfAxiom(invProp, invTopProp));
		}else{
			int cont = 0;
			//Search for other instances of this mediation
			for(Mediation ma:ontoParser.getAllInstances(Mediation.class)){
				String mediationName = "";
				if(ma.getName() == null){
					mediationName = "mediation."+ma.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ma.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_");
				}else{
					mediationName = ma.getName();
				}
				if(propName.equals(mediationName)){
					cont++;
					if(cont >= 2)
						break;
				}
			}
			if(cont >= 2){
				//If has some object with the equal name
				_lstMediationAssociations.add(prop);
				_lstMediationAssociations.add(invProp);
				//set that the inverse property is the inverse of the property
				manager.applyChange(new AddAxiom(ontology,factory.getOWLInverseObjectPropertiesAxiom(prop, invProp)));
				return createMediationAssociation(ass);
			}
		}

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
		_lstMediationAssociations.add(invProp);

		return propName;
	}

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


	private void createAttributes(Classifier src){	
		for(Property p:src.getAttribute()){		
			int upperCard = p.getUpper();
			int lowerCard = p.getLower();

			OWLDatatype tipoAtributo = null;
			OWLDataPropertyRangeAxiom set = null;

			//Se o tipo nao for reconhecido cria o atributo com range literal
			if(p.getType()== null){
				tipoAtributo = factory.getOWLDatatype(OWL2Datatype.RDFS_LITERAL.getIRI());
			}else{
				String range = p.getType().getName().replaceAll(" ", "_");
				tipoAtributo = getDataTypeRange(range);
				if(tipoAtributo == null){
					dt_atual = src.getName().replaceAll(" ", "_")+"#";
					processDataTypeProperty(p);
					dt_atual = "";

					if(_aux_tipoAtributo == null){
						//Se nao existe um tipo compativel em owl
						tipoAtributo = factory.getOWLDatatype(OWL2Datatype.RDFS_LITERAL.getIRI());
					}else{
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

			//usado para criar o disjoint dos dataproperties para a classe
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
	}

	private void createRelation_componentOf(){
		_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"componentOf")));
		_lstPartofAssociations.add(factory.getOWLObjectProperty(IRI.create(nameSpace+"INV.componentOf")));

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

	private void processAxioms(){
		Iterator<OWLAxiom> itr = ontology.getAxioms().iterator();



		for(OWLClass c : ontology.getClassesInSignature()){
			Set<OWLEquivalentClassesAxiom> eqClsLst = new HashSet<OWLEquivalentClassesAxiom>();

			for(OWLEquivalentClassesAxiom ax : ontology.getEquivalentClassesAxioms(c)){
					eqClsLst.add((OWLEquivalentClassesAxiom)ax);
					manager.removeAxiom(ontology, ax);
			}

//			manager.applyChange(new AddAxiom(ontology, eqClsLst));

		}





		//		while(itr.hasNext()) {
		//			OWLAxiom ax = itr.next();
		//			if(ax instanceof OWLAnnotationAssertionAxiom){
		//				continue;
		//			}
		//			
		//			for(OWLClass cls : ax.getClassesInSignature()){
		//				
		//				
		//				if(cls.toString().contains("Letter_")){
		//					System.out.println("{Class: "+cls.toString());
		//					System.out.println(ax.toString()+"}");
		//				}
		//			}
		//			
		//		}

	}
}