package br.ufes.inf.nemo.ootos.ontouml2owl_swrl;


import java.io.ByteArrayOutputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;

import org.eclipse.emf.common.util.EList;
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
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
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
import org.semanticweb.owlapi.model.SWRLObjectPropertyAtom;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.MixinClass;
import RefOntoUML.MomentClass;
import RefOntoUML.PackageableElement;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.Type;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.OCL2OWL_SWRL;
import br.ufes.inf.nemo.ootos.util.MappingProperties;


public class Transformer {
	/**
	 * Considerations:
	 * - Chain of DataTypes can be infinity if a datatype x has a datatype y and y has a datatype x
	 * - Multiply the bounds of the associations in chain of datatypes
	 * */
	//Global Variables
	private OntoUMLParser ontoParser;

	private Set<Class> lstOntClass;
	private Set<GeneralizationSet> lstGenSets;
	private Set<Generalization> lstGen;
	private Set<MaterialAssociation> lstMaterials;
	private Set<Mediation> lstMediations;
	private Set<Characterization> lstCharacterization;
	private Set<FormalAssociation> lstFormal;
	private Set<Derivation> lstDerivation;
	private Set<Relator> lstRelator;
	private Set<componentOf> lstComponentOf;
	private Set<subCollectionOf> lstSubCollectionOf;
	private Set<subQuantityOf> lstSubQuantityOf;
	private Set<memberOf> lstMemberOf;
	private Set<DataType> lstDataType;
	private HashMap<RefOntoUML.Classifier,Set<OWLDataProperty>> hashDataProperty;
	private HashMap<String,Set<OWLObjectProperty>> hashAssociations;
	private ArrayList<Property> dataTypesProcesseds = new ArrayList<>();

	//OWL
	private String nameSpace;

	//OWL API
	private OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	private OWLOntology ontology;
	private OWLDataFactory factory;

	//ocl 2 swrl
	String oclRules;
	
	//
	MappingProperties mappingProperties;
	
	//OLED
	private String errors = "\n";

	public String getErrors(){
		errors = mappingProperties.getOutputMessages() + errors;
		return errors;
	}

	/**
	 * Initialize the Transformer
	 * */
	public Transformer(RefOntoUML.Package model, String nameSpace, String _oclRules) {
		this.nameSpace = nameSpace+"#";

		try {
			this.factory = manager.getOWLDataFactory();
			this.ontology = manager.createOntology(IRI.create(nameSpace));
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}	
		
		ontoParser = new OntoUMLParser(model);
		lstOntClass = ontoParser.getAllInstances(RefOntoUML.Class.class);
		lstGenSets = ontoParser.getAllInstances(GeneralizationSet.class);
		lstGen = ontoParser.getAllInstances(Generalization.class);
		lstMaterials = ontoParser.getAllInstances(MaterialAssociation.class);
		lstMediations = ontoParser.getAllInstances(Mediation.class);
		lstCharacterization = ontoParser.getAllInstances(Characterization.class);
		lstFormal = ontoParser.getAllInstances(FormalAssociation.class);
		lstDerivation = ontoParser.getAllInstances(Derivation.class);
		lstRelator = ontoParser.getAllInstances(Relator.class);
		lstComponentOf = ontoParser.getAllInstances(componentOf.class);
		lstSubCollectionOf = ontoParser.getAllInstances(subCollectionOf.class);
		lstSubQuantityOf = ontoParser.getAllInstances(subQuantityOf.class);
		lstMemberOf = ontoParser.getAllInstances(memberOf.class);
		hashAssociations = new HashMap<>();
		lstDataType = ontoParser.getAllInstances(RefOntoUML.DataType.class);
		hashDataProperty = new HashMap<>(); 
		
		oclRules = _oclRules;
		
		mappingProperties = new MappingProperties(ontoParser);
		mappingProperties.generateAllPropertyNames();
	}

	/**
	 * Transform a RefOntoUML.Model to OWL
	 * 
	 * @param ecoreModel
	 * @return a String with the OWL code
	 * @throws Exception 
	 */
	public String transform() throws Exception {
		try{
			processClass();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when creating the OWL classes;\n");			
		}

		try{
			processDataType();
		}catch (Exception e){
			errors = "";
			e.printStackTrace();
			throw new Exception("Error: An unexpected exception happened when processing Datatypes;\n");
		}

		try{
			processGeneralizations();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when processing Generalizations;\n");
		}

		try{
			processCharacterization();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when processing Characterization Associations;\n");
		}

		try{
			processFormal();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when processing Formal Associations;\n");
		}

		try{
			processMediation();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when processing Mediation Associations;\n");
		}

		try{
			processMaterial();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when processing Material Associations;\n");
		}

		try{
			processRelator();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when processing Relators;\n");
		}

		try{
			processComponentOf();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when processing ComponentOf Association;\n");
		}

		try{
			processSubCollectionOf();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when processing SubCollectionOf Association;\n");
		}

		try{
			processSubQuantityOf();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when processing SubQuantityOf Association;\n");
		}

		try{
			processSubQuantityOf();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when processing SubQuantityOf Association;\n");
		}

		try{
			processMemberOf();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when processing MemberOf Association;\n");
		}

		try{
			processDisjointClass();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when creating the disjointness of the Classes;\n");
		}

		try{
			processDisjointAssociation();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when creating the disjointness of the Associations;\n");
		}

		//		try{
		//			processDisjointDataType();
		//		}catch (Exception e){
		//			errors = "";
		//			throw new Exception("Error: An unexpected exception happened when creating the disjointness of the Datatypes;\n");
		//		}

		try{
			processAnnotation();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when creating the Annotations;\n");
		}

		try{
			processAxiom();
		}catch (Exception e){
			errors = "";
			throw new Exception("Error: An unexpected exception happened when creating the Axioms;\n");
		}

		if(oclRules != null){
			if(!oclRules.equals("")){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int result = JOptionPane.showConfirmDialog (null, "Do you want to perform the OCL transformation to OWL and SWRL?","Warning",dialogButton);

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

	/**
	 * Set all stereotype of association disjoint between they
	 * */
	private void processDisjointAssociation() {

		for (String stereotype : hashAssociations.keySet()) {
			for (String _stereotype : hashAssociations.keySet()) {
				if(!stereotype.equals(_stereotype) && !stereotype.equals("formal") && !stereotype.equals("material") && !_stereotype.equals("formal") && !_stereotype.equals("material")){
					for (OWLObjectProperty prop : hashAssociations.get(stereotype)) {
						//For each ObjectProperty of a key (stereotype) make different for the 
						//properties of the other stereotypes (_stereotype)
						for (OWLObjectProperty _prop : hashAssociations.get(_stereotype)) {
							manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop,_prop)));
						}
					}
				}
			}
		}	
	}

	private void putInHash(String stereotype, OWLObjectProperty prop){
		if(!hashAssociations.containsKey(stereotype)){
			hashAssociations.put(stereotype, new HashSet<OWLObjectProperty>());
		}
		hashAssociations.get(stereotype).add(prop);
	}

	private void processSubCollectionOf() {
		if(!lstSubCollectionOf.isEmpty()){
			Set<Association> lst = new HashSet<Association>();

			lst.addAll(lstSubCollectionOf);
			processMeronymic(lst, "subCollectionOf");
		}
		if(lstSubCollectionOf.size() > 1){
			//For transitivity
			createSWRLforTrasitivity("subCollectionOf");
		}
	}

	private void processSubQuantityOf() {
		if(!lstSubQuantityOf.isEmpty()){
			Set<Association> lst = new HashSet<Association>();

			lst.addAll(lstSubQuantityOf);
			processMeronymic(lst, "subQuantityOf");
		}
		if(lstSubQuantityOf.size() > 1){
			//For transitivity
			createSWRLforTrasitivity("subQuantityOf");
		}
	}

	private void processComponentOf() {
		if(!lstComponentOf.isEmpty()){
			Set<Association> lst = new HashSet<Association>();

			lst.addAll(lstComponentOf);
			processMeronymic(lst, "componentOf");
		}
		if(lstComponentOf.size() > 1){
			//For transitivity
			createSWRLforTrasitivity("componentOf");
		}
	}

	private void processMemberOf() {
		if(!lstMemberOf.isEmpty()){
			Set<Association> lst = new HashSet<Association>();

			lst.addAll(lstMemberOf);
			processMeronymic(lst, "memberOf");
		}
		if((lstMemberOf.size() >= 1) && (lstSubCollectionOf.size() >= 1)){
			//if has a memberof association and a subcollectionof association
			createSWRLforMemberOfWithSubCollectionOf();	
		}
	}

	private void createSWRLforMemberOfWithSubCollectionOf() {
		//FALTA PADRONIZAR AQUI
		OWLObjectProperty memberOf = factory.getOWLObjectProperty(IRI.create(nameSpace+"memberOf"));
		OWLObjectProperty subCollectionOf = factory.getOWLObjectProperty(IRI.create(nameSpace+"subCollectionOf"));

		//Variables
		SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+"x"));
		SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+"y"));
		SWRLVariable varZ = factory.getSWRLVariable(IRI.create(nameSpace+"z"));

		//Make all variables disjoint between they
		SWRLAtom diffYX = factory.getSWRLDifferentIndividualsAtom(varY, varX);
		SWRLAtom diffXZ = factory.getSWRLDifferentIndividualsAtom(varX, varZ);
		SWRLAtom diffYZ = factory.getSWRLDifferentIndividualsAtom(varY, varZ);

		//statements
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
	 * Create a transitivity in SWRL for property with its name.
	 * Ex.: propName(?x,?y),propName(?y,?z)->propName(?x,?z)
	 * */

	private void createSWRLforTrasitivity(String propName) {
		//FALTA PADRONIZAR AQUI
		OWLObjectProperty prop = factory.getOWLObjectProperty(IRI.create(nameSpace+propName));

		//Create the variables
		SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+"x"));
		SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+"y"));
		SWRLVariable varZ = factory.getSWRLVariable(IRI.create(nameSpace+"z"));

		//Set all variables disjoint between they
		SWRLAtom diffYX = factory.getSWRLDifferentIndividualsAtom(varY, varX);
		SWRLAtom diffXZ = factory.getSWRLDifferentIndividualsAtom(varX, varZ);
		SWRLAtom diffYZ = factory.getSWRLDifferentIndividualsAtom(varY, varZ);

		//Create the statements
		Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
		antecedent.add(diffXZ); //DifferentFrom(?x,?z)
		antecedent.add(diffYZ); //DifferentFrom(?y,?z)
		antecedent.add(diffYX); //DifferentFrom(?y,?z)
		antecedent.add(factory.getSWRLObjectPropertyAtom(prop, varX, varY)); //prop(?x,?Y)
		antecedent.add(factory.getSWRLObjectPropertyAtom(prop, varY, varZ)); //prop(?y,?z)

		Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
		consequent.add(factory.getSWRLObjectPropertyAtom(prop, varX, varZ)); //prop(?x,?z)

		SWRLRule rule = factory.getSWRLRule(antecedent,consequent);		
		manager.applyChange(new AddAxiom(ontology, rule));
	}

	/**
	 * Create a top ObjectProperty called stereotype and add, as a subPropertyOf, all 
	 * Associations in lstAssociation in it.
	 * Set this top ObjectProperty as Irreflexive and Asymmetric.
	 * */

	private void processMeronymic(Set<Association> lstAssociation, String stereotype){
		//Create the top property
		//FALTA PADRONIZAR AQUI
		OWLObjectProperty topProp = factory.getOWLObjectProperty(IRI.create(nameSpace+stereotype));
		OWLObjectProperty topInvProp = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+stereotype));

		//Used after to make associations disjoints
		putInHash(stereotype, topProp);
		putInHash(stereotype, topInvProp);

		OWLInverseObjectPropertiesAxiom inv = factory.getOWLInverseObjectPropertiesAxiom(topInvProp, topProp);
		manager.applyChange(new AddAxiom(ontology, inv));

		//Make the inverse top property disjoint of the top property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(topProp, topInvProp)));

		//Set prop irreflexive
		OWLIrreflexiveObjectPropertyAxiom iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(topProp);
		manager.applyChange(new AddAxiom(ontology, iopa));

		//Set prop asymmetric
		OWLAsymmetricObjectPropertyAxiom aopa = factory.getOWLAsymmetricObjectPropertyAxiom(topProp);
		manager.applyChange(new AddAxiom(ontology, aopa));

		//Set prop irreflexive		
		iopa = factory.getOWLIrreflexiveObjectPropertyAxiom(topInvProp);
		manager.applyChange(new AddAxiom(ontology, iopa));

		//Set prop asymmetric
		aopa = factory.getOWLAsymmetricObjectPropertyAxiom(topInvProp);
		manager.applyChange(new AddAxiom(ontology, aopa));

		OWLObjectProperty prop = null;
		OWLObjectProperty invProp = null;
		OWLSubObjectPropertyOfAxiom sopa = null;

		for (Association ass : lstAssociation) {
			//Create the meronymics with its name, whether exist, or stereotype.source.destiny
			prop = createAssociation(ass,stereotype);
			invProp = createInverseAssociation(ass,stereotype);

			//Set invProp inverse of prop
			manager.applyChange(new AddAxiom(ontology, factory.getOWLInverseObjectPropertiesAxiom(invProp, prop)));
			
			//Set both property disjoints
			manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop, invProp)));

			//Set both subPropertyOf its top property
			sopa = factory.getOWLSubObjectPropertyOfAxiom(prop,topProp);
			manager.applyChange(new AddAxiom(ontology, sopa));

			sopa = factory.getOWLSubObjectPropertyOfAxiom(invProp,topInvProp);
			manager.applyChange(new AddAxiom(ontology, sopa));
		}
	}

	/**
	 * Create the SWRL between Relator.Source and Relator.Destiny by its Material associations
	 * */
	private void processRelator() {
		Set<MaterialAssociation> materials = null;
		ArrayList<Mediation> mediations = null;

		Mediation mediation0 = null;
		Mediation mediation1 = null;

		for (Relator relator : lstRelator) {
			try{
				materials = getRelatorMaterials(relator);
				mediations = ontoParser.getMediations(relator);

				//Get the triple <Mediation,Material,Mediation>
				for (MaterialAssociation material : materials) {
					//Clean up the variables
					mediation0 = null;
					mediation1 = null;
					for (Mediation mediation : mediations) {
						//Verify source of the material
						if(material.getMemberEnd().get(0).getType().equals(mediation.getMemberEnd().get(0).getType()) 
								|| material.getMemberEnd().get(0).getType().equals(mediation.getMemberEnd().get(1).getType())){
							mediation0 = mediation;
						}

						//Verify target of the material
						if(material.getMemberEnd().get(1).getType().equals(mediation.getMemberEnd().get(0).getType()) 
								|| material.getMemberEnd().get(1).getType().equals(mediation.getMemberEnd().get(1).getType())){
							mediation1 = mediation;
						}
						if(mediation0 != null && mediation1 != null){
							//Now we have the Material and Mediations of the member-ends of the Material
							createSWRLforRelator(mediation0, mediation1, material, relator);
							break;
						}
					}
				}
			}catch(Exception e){
				errors += "The Relator does not exist;\n";
			}
		}
	}

	/**
	 * Create a SWRL for an Relator that have a material association between two of his mediations. 
	 * @param 
	 */
	private void createSWRLforRelator(Mediation mediation0, Mediation mediation1, MaterialAssociation material, Relator relator) {
		OWLObjectProperty propMediation0 = getAbsolutObjectProperty(mediation0, "mediation");
		OWLObjectProperty propMediation1 = getAbsolutObjectProperty(mediation1, "mediation");
		OWLObjectProperty propMaterial = getAbsolutObjectProperty(material, "material");

		OWLClass materialSource = getOwlClass(material.getMemberEnd().get(0).getType());
		OWLClass materialDestiny = getOwlClass(material.getMemberEnd().get(1).getType());
		OWLClass relatorClass = getOwlClass(relator);

		//SWRL

		//variables
		SWRLVariable varMaterialSource = factory.getSWRLVariable(IRI.create(nameSpace+"x"));
		SWRLVariable varMaterialDestiny = factory.getSWRLVariable(IRI.create(nameSpace+"y"));
		SWRLVariable varRelator = factory.getSWRLVariable(IRI.create(nameSpace+"z"));

		//Set the type of the variables
		SWRLAtom typeOfMaterialSource = factory.getSWRLClassAtom(materialSource, varMaterialSource); //A(?x)
		SWRLAtom typeOfMaterialDestiny = factory.getSWRLClassAtom(materialDestiny, varMaterialDestiny); //B(?y)
		SWRLAtom typeOfRelator = factory.getSWRLClassAtom(relatorClass, varRelator); //relator(?z)

		//Set all variables different between they
		SWRLAtom diffMatSrcMatDst = factory.getSWRLDifferentIndividualsAtom(varMaterialSource, varMaterialDestiny);
		SWRLAtom diffMatSrcRel = factory.getSWRLDifferentIndividualsAtom(varMaterialSource, varRelator);
		SWRLAtom diffMatDstRel = factory.getSWRLDifferentIndividualsAtom(varMaterialDestiny, varRelator);

		//Create the contraints
		Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
		antecedent.add(diffMatSrcRel); //DifferentFrom(?x,?z)
		antecedent.add(diffMatDstRel); //DifferentFrom(?y,?z)
		antecedent.add(diffMatSrcMatDst); //DifferentFrom(?x,?x)
		antecedent.add(typeOfMaterialSource); //A(?x)
		antecedent.add(typeOfMaterialDestiny); //B(?y)
		antecedent.add(typeOfRelator); //relator(?z)
		SWRLObjectPropertyAtom sopa = factory.getSWRLObjectPropertyAtom(propMediation0, varRelator, varMaterialSource); 
		antecedent.add(sopa); //propMediation0(?x,?z)
		sopa = factory.getSWRLObjectPropertyAtom(propMediation1, varRelator, varMaterialDestiny);
		antecedent.add(sopa); //propMediation1(?z,?y)

		Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
		consequent.add(factory.getSWRLObjectPropertyAtom(propMaterial, varMaterialSource, varMaterialDestiny)); //propMaterial(?x,?y)

		SWRLRule rule = factory.getSWRLRule(antecedent,consequent);		

		manager.applyChange(new AddAxiom(ontology, rule));
	}

	/**
	 * Return the ObjectProperty with the name:
	 * if ass' name be null, than stereotype.source.destiny
	 * else if more than one associations has the same name, than assName.source.destiny
	 * else the name o the association  
	 * */
	private OWLObjectProperty getAbsolutObjectProperty(Association ass, String stereotype){
		int match = 0;
		OWLObjectProperty prop = null;

		//Verify the name of the property
		prop = getObjectProperty(ass);
		if(prop == null){
			//Create Association with the name stereotype.Source.Destiny
			prop = getObjectProperty(ass, stereotype);
			return prop;
		}else{
			//Verify if exist other prop with the same name
			for (Association sameAss : ontoParser.getAllInstances(Association.class)) {
				//If exist some relations with the same names
				if(getObjectPropertyName(ass,stereotype).equalsIgnoreCase(getObjectPropertyName(sameAss, stereotype))){
					match++;
				}
				if(match > 1){
					//If has some associations with the same name
					//Create the associations with the name assName.Source.Destiny
					prop = getObjectProperty(ass, getName(ass.getMemberEnd().get(0).getType()), getName(ass.getMemberEnd().get(1).getType()));
					return prop;
				}
			}
		}
		//If has just one association with this name
		prop = getObjectProperty(ass, stereotype);
		return prop;
	}

	/**
	 * Return the Mediations and Material Relation from a specific Relator 'r'
	 * 
	 * @param relator
	 * @return A list with the all MaterialAssociation from the Relator
	 */
	private Set<MaterialAssociation> getRelatorMaterials(Relator r){
		Set<MaterialAssociation> lst = new HashSet<MaterialAssociation>();
		MaterialAssociation material;

		for(Derivation derivation:lstDerivation){
			//Verify the member-ends of the derivations, relators and materials
			//One side is the Relator and other is the Material
			if(derivation.getMemberEnd().get(1).getType().equals(r)){
				material = (MaterialAssociation)derivation.getMemberEnd().get(0).getType();
				lst.add(material);
			}else if(derivation.getMemberEnd().get(0).getType().equals(r)){
				material = (MaterialAssociation)derivation.getMemberEnd().get(1).getType();
				lst.add(material);
			}
		}
		return lst;
	}

	/**
	 * Create all characterization relation 
	 * */	
	private void processCharacterization() {
		Set<Association> lst = new HashSet<Association>();

		lst.addAll(lstCharacterization);
		processAssociation(lst, "characterization");
	}

	/**
	 * Create all formal relation 
	 * */	
	private void processFormal() {
		Set<Association> lst = new HashSet<Association>();

		lst.addAll(lstFormal);
		processAssociation(lst, "formal");
	}

	/**
	 * Create all mediation relation 
	 * */	
	private void processMediation() {
		Set<Association> lst = new HashSet<Association>();

		lst.addAll(lstMediations);
		processAssociation(lst, "mediation");
	}

	/**
	 * Create all material relation 
	 * */	
	private void processMaterial() {
		Set<Association> lst = new HashSet<Association>();

		lst.addAll(lstMaterials);
		processAssociation(lst, "material");
	}

	/**
	 * Used to clean up the string with the owl.
	 * Sometimes wildcards can do a mistake in the code.
	 * */
	private String processSpecialCharacter(String owl) {
		owl = Normalizer.normalize(owl, Normalizer.Form.NFD);
		owl = owl.replaceAll("[^\\p{ASCII}]", "");
		return owl;
	}

	/**
	 * This method make a unique for to create a string name for an Type
	 * */
	private String getName(RefOntoUML.Type ontType){
		if(ontType == null){
			return "unnamed_class";
		}
		return ontType.getName().replaceAll(" ", "_");
	}

	private String getAbsoluteName(RefOntoUML.Type type){
		return type.getName().replaceAll(" ", "_");
	}

	/**
	 * Create a unique name for DataProperty
	 * */
	private String getDataPropertyName(RefOntoUML.Classifier ontCls, RefOntoUML.Property prop){
		if(ontCls == null){
			return nameSpace + prop.getName().replaceAll(" ", "_");	
		}
		return  nameSpace + getAbsoluteName(ontCls)+"."+prop.getName().replaceAll(" ", "_");
	}

	private String getPropertyName(RefOntoUML.Property prop){
		return prop.getName().replaceAll(" ", "_");
	}

	/**
	 * Return a OWL Classs for the ontCls
	 * */
	private OWLClass getOwlClass(RefOntoUML.Classifier ontCls){
		return factory.getOWLClass(IRI.create(nameSpace+ontCls.getName().replaceAll(" ", "_")));
	}

	private OWLClass getOwlClass(RefOntoUML.Type ontType){
		return factory.getOWLClass(IRI.create(nameSpace+ontType.getName().replaceAll(" ", "_")));
	}

	/**
	 * Return an OWLObjectProperty if the Association ass has some name
	 * or null otherwise;
	 * */
	private OWLObjectProperty getObjectProperty(RefOntoUML.Association ass){
		if(ass.getName()==null || ass.getName() == "" || ass.getName() == " " || ass.getName().length() == 0){
			return null;
		}else{
			String assName = mappingProperties.getPropertyName(ass);
			return factory.getOWLObjectProperty(IRI.create(nameSpace+assName));
			//return factory.getOWLObjectProperty(IRI.create(nameSpace+ass.getName().replaceAll(" ", "_")));
		}
	}

	/**
	 * Return an OWLObjectProperty if the Association ass has some name
	 * or stereotype.source.destiny;
	 * */
	private OWLObjectProperty getObjectProperty(RefOntoUML.Association ass, String stereotype){
		String propName = mappingProperties.getPropertyName(ass);
		return factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
		
//		if(ass.getName()==null || ass.getName() == "" || ass.getName() == " " || ass.getName().length() == 0){
//			//String propName = stereotype+"."+this.getName(ass.getMemberEnd().get(0).getType())+"."+this.getName(ass.getMemberEnd().get(1).getType());
//			return factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
//		}else{
//			return factory.getOWLObjectProperty(IRI.create(nameSpace+ass.getName().replaceAll(" ", "_")));
//		}
	}

	/**
	 * Return a String with the name of the Association ass
	 * */
	private String getObjectPropertyName(Association ass, String stereotype) {
		return mappingProperties.getPropertyName(ass);
		
//		if(ass.getName()==null || ass.getName() == "" || ass.getName() == " " || ass.getName().length() == 0){
//			return stereotype+"."+this.getName(ass.getMemberEnd().get(0).getType())+"."+this.getName(ass.getMemberEnd().get(1).getType());
//		}else{
//			return ass.getName().replaceAll(" ", "_");
//		}
	}

	/**
	 * Return an OWLObjectProperty if the Association ass has some name
	 * or stereotype.destiny.source;
	 * */
	private OWLObjectProperty getInverseObjectProperty(RefOntoUML.Association ass, String stereotype){
		String propName = mappingProperties.getPropertyName(ass);
		return factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+propName));
//		if(ass.getName()==null || ass.getName() == "" || ass.getName() == " " || ass.getName().length() == 0){
//			//String propName = "INV."+stereotype+"."+this.getName(ass.getMemberEnd().get(0).getType())+"."+this.getName(ass.getMemberEnd().get(1).getType());
//			return factory.getOWLObjectProperty(IRI.create(nameSpace+propName));
//		}else{
//			return factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+ass.getName().replaceAll(" ", "_")));
//		}
	}

	/**
	 * Create an Inverse Association and set the cardinality for the destiny
	 * */
	private OWLObjectProperty processCreateInverseAssociation(Association ass, OWLObjectProperty prop){
		int sideSrc = 1, sideDst = 0;

		//source class of the relation
		OWLClass src = getOwlClass(ass.getMemberEnd().get(sideSrc).getType());		

		//destination class of the relation
		OWLClass dst = getOwlClass(ass.getMemberEnd().get(sideDst).getType());

		//Set domain and range from the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyDomainAxiom(prop, src)));
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(prop, dst)));

		//Processing cardinality to the destiny
		int upperCard = ass.getMemberEnd().get(sideDst).getUpper();
		int lowerCard = ass.getMemberEnd().get(sideDst).getLower();

		processCardinality(prop, src, dst, lowerCard, upperCard);

		return prop;
	}

	/**
	 *  Create an Inverse Association and set the cardinality for the destiny
	 * */
	private OWLObjectProperty createInverseAssociation(Association ass, String stereotype){
		OWLObjectProperty prop = getInverseObjectProperty(ass, stereotype);
		return processCreateInverseAssociation(ass, prop);
	}

	/**
	 *  Create an Inverse Association and set the cardinality for the destiny
	 * */
	private OWLObjectProperty createInverseAssociation(Association ass){
		OWLObjectProperty prop = getInverseObjectProperty(ass, getName(ass.getMemberEnd().get(0).getType()), getName(ass.getMemberEnd().get(1).getType()));
		return processCreateInverseAssociation(ass, prop);
	}

	/**
	 *  Create an Association and set the cardinality for the destiny
	 * */
	private OWLObjectProperty createAssociation(Association ass, String stereotype){
		OWLObjectProperty prop = getObjectProperty(ass, stereotype);
		return processCreateAssociation(ass, prop);
	}

	/**
	 *  Create an Association with the name assName.Source.Destiny and set the cardinality for the destiny
	 * */
	private OWLObjectProperty createAssociation(Association ass){
		//Set the name of the associations with the name of its range and domain
		OWLObjectProperty prop = getObjectProperty(ass, getName(ass.getMemberEnd().get(0).getType()), getName(ass.getMemberEnd().get(1).getType()));
		return processCreateAssociation(ass, prop);
	}

	private OWLObjectProperty getObjectProperty(Association ass, String src, String dst) {
		String assName = mappingProperties.getPropertyName(ass);
		OWLObjectProperty prop = factory.getOWLObjectProperty(IRI.create(nameSpace+assName));
		//OWLObjectProperty prop = factory.getOWLObjectProperty(IRI.create(nameSpace+ass.getName().replaceAll(" ","_")+"."+src+"."+dst));
		return prop;
	}

	private OWLObjectProperty getInverseObjectProperty(Association ass, String src, String dst) {
		String assName = mappingProperties.getPropertyName(ass);
		OWLObjectProperty prop = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+assName));
		//OWLObjectProperty prop = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+ass.getName().replaceAll(" ","_")+"."+src+"."+dst));
		return prop;
	}

	/**
	 * Create an Association and set the cardinality for the destiny
	 * */
	private OWLObjectProperty processCreateAssociation(Association ass, OWLObjectProperty prop){
		int sideSrc = 0, sideDst = 1;

		//source class of the relation
		OWLClass src = getOwlClass(ass.getMemberEnd().get(sideSrc).getType());		

		//destination class of the relation
		OWLClass dst = getOwlClass(ass.getMemberEnd().get(sideDst).getType());

		//Set domain and range from the property
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyDomainAxiom(prop, src)));
		manager.applyChange(new AddAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(prop, dst)));

		//Processing cardinality to the destiny
		int upperCard = ass.getMemberEnd().get(sideDst).getUpper();
		int lowerCard = ass.getMemberEnd().get(sideDst).getLower();

		processCardinality(prop, src, dst, lowerCard, upperCard);

		return prop;
	}

	private void processCardinality(OWLObjectProperty prop, OWLClass src, OWLClass dst, int lowerCard, int upperCard){
		OWLEquivalentClassesAxiom ax = null;
		OWLSubClassOfAxiom sax = null; 

		if(upperCard == lowerCard){
			//x..x
			OWLObjectExactCardinality oecr = factory.getOWLObjectExactCardinality(lowerCard, prop, dst);
			ax = factory.getOWLEquivalentClassesAxiom(src, oecr);
		}else if(upperCard == -1 && lowerCard == 1){
			//1..*
			OWLObjectSomeValuesFrom oecr = factory.getOWLObjectSomeValuesFrom(prop, dst);
			ax = factory.getOWLEquivalentClassesAxiom(src, oecr);
		}else if (upperCard != -1 && lowerCard == 0){
			//0..*
			OWLObjectMaxCardinality maxcard = factory.getOWLObjectMaxCardinality(upperCard, prop,dst);
			sax = factory.getOWLSubClassOfAxiom(src, maxcard);
		}else if(upperCard == -1 && lowerCard != 0){
			//x..*
			OWLObjectMinCardinality mincard = factory.getOWLObjectMinCardinality(lowerCard, prop,dst);
			ax = factory.getOWLEquivalentClassesAxiom(src, mincard);	
		}else if(upperCard != -1 && lowerCard > 0){
			//x..n
			OWLObjectMaxCardinality maxcard = factory.getOWLObjectMaxCardinality(upperCard, prop,dst);
			OWLObjectMinCardinality mincard = factory.getOWLObjectMinCardinality(lowerCard, prop,dst);
			OWLObjectIntersectionOf oio =  factory.getOWLObjectIntersectionOf(maxcard,mincard);
			ax = factory.getOWLEquivalentClassesAxiom(src, oio);
		}else{
			errors += "Warning: The cardinality 0..* is not mapped to OWL (occurrence at association "+prop.getIRI().toString().substring(prop.getIRI().toString().indexOf("#")+1)+" , from classe "+src.getIRI().toString().substring(src.getIRI().toString().indexOf("#")+1)+" to class "+dst.getIRI().toString().substring(dst.getIRI().toString().indexOf("#")+1)+");\n";
		}

		if(ax != null){
			manager.applyChange(new AddAxiom(ontology, ax));
		}

		if(sax != null){
			manager.applyChange(new AddAxiom(ontology, sax));
		}
	}

	private void processAssociation(Set<Association> lstAssociation, String stereotype) {
		int match = 0;

		OWLObjectProperty prop = null;
		OWLObjectProperty invProp = null;

		OWLObjectProperty topProperty = null;
		OWLObjectProperty invTopProperty = null;

		OWLSubObjectPropertyOfAxiom sopa = null;

		for (Association ass : lstAssociation) {
			//Verify the name of the property
			String assName = mappingProperties.getPropertyName(ass);
			prop = getObjectProperty(ass);
			if(prop == null){
				//errors += "Warning: An unnamed Association from <"+getName(ass.getMemberEnd().get(0).getType())+"> (source class) to <"+getName(ass.getMemberEnd().get(1).getType())+"> (target class) was mapped to OWL <"+getObjectPropertyName(ass,stereotype)+">;\n";
				//errors += "Warning: An unnamed Association from <"+getName(ass.getMemberEnd().get(0).getType())+"> (source class) to <"+getName(ass.getMemberEnd().get(1).getType())+"> (target class) was mapped to OWL <"+assName+">;\n";
				
//				topProperty = factory.getOWLObjectProperty(IRI.create(nameSpace+stereotype));
//				invTopProperty = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+stereotype));
				topProperty = factory.getOWLObjectProperty(IRI.create(nameSpace+assName));
				invTopProperty = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+assName));

				//Create Association with the name stereotype.Source.Destiny
				prop = createAssociation(ass,stereotype);
				invProp = createInverseAssociation(ass,stereotype);
			}else{

				//Verify if exist other prop with the same name
//				for (Association sameAss : lstAssociation) {
//					//If exist some relations with the same names
//					if(getObjectPropertyName(ass,stereotype).equalsIgnoreCase(getObjectPropertyName(sameAss, stereotype))){
//						match++;
//					}
//					if(match > 1){
//						//errors += "Warning: The association <"+getObjectPropertyName(ass,stereotype)+"> with repeted name was mapped as subPropertyOf <"+getObjectPropertyName(ass, stereotype)+"> with the name <"+getObjectPropertyName(ass, stereotype)+"."+getName(ass.getMemberEnd().get(0).getType())+"."+getName(ass.getMemberEnd().get(1).getType())+">;\n";
//
//						//If has some associations with the same name
//						//Create a top property with the name of the associations
////						topProperty = factory.getOWLObjectProperty(IRI.create(nameSpace+getObjectPropertyName(ass, stereotype)));
////						invTopProperty = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+getObjectPropertyName(ass, stereotype)));
//						topProperty = factory.getOWLObjectProperty(IRI.create(nameSpace+assName));
//						invTopProperty = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+assName));
//
//						//Create the associations with the name assName.Source.Destiny
//						prop = createAssociation(ass);
//						invProp = createInverseAssociation(ass);
//
//						//Improve performance
//						break;
//					}
//				}
				
				if(mappingProperties.isMappedAsSubRelationOf(ass)){
					String superPropertyName = mappingProperties.getSuperPropertyName(ass);
					topProperty = factory.getOWLObjectProperty(IRI.create(nameSpace+superPropertyName));
					invTopProperty = factory.getOWLObjectProperty(IRI.create(nameSpace+"INV."+superPropertyName));

					//Create the associations with the name assName.Source.Destiny
					prop = createAssociation(ass);
					invProp = createInverseAssociation(ass);
				}				
			}

			//			if(match > 1 || nameNull){
			if(mappingProperties.isMappedAsSubRelationOf(ass)){
			//if(match > 1){
				//set same properties subPropertyOf the topProperty
				sopa = factory.getOWLSubObjectPropertyOfAxiom(prop,topProperty);
				manager.applyChange(new AddAxiom(ontology, sopa));

				sopa = factory.getOWLSubObjectPropertyOfAxiom(invProp,invTopProperty);
				manager.applyChange(new AddAxiom(ontology, sopa));

				//set that the inverse top property is the inverse of the top property
				manager.applyChange(new AddAxiom(ontology,factory.getOWLInverseObjectPropertiesAxiom(topProperty, invTopProperty)));

				//Make the inverse top property disjoint of the top property
				//				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(topProperty, invTopProperty)));

				//Used after to make associations disjoints
				putInHash(stereotype, topProperty);
				putInHash(stereotype, invTopProperty);
			}else{
				//If has just one association with this name
				prop = createAssociation(ass, stereotype);
				invProp = createInverseAssociation(ass, stereotype);

				//Used after to make associations disjoints
				putInHash(stereotype, prop);
				putInHash(stereotype, invProp);
			}

			//set that the inverse property is the inverse of the property
			manager.applyChange(new AddAxiom(ontology,factory.getOWLInverseObjectPropertiesAxiom(prop, invProp)));

			//Make the inverse property disjoint of the property
			//			manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(prop,invProp)));

			match = 0;
		}
	}

	private void processGeneralizations() {
		//First process all GeneralizationSet
		processGeneralizationSet();

		//Process Generalizations
		for(Generalization gen : lstGen){
			if(gen.getGeneral() instanceof DataType){
				continue;
			}
			OWLClass father = getOwlClass(gen.getGeneral());

			OWLClass son = 	getOwlClass(gen.getSpecific());

			//Set subClassOf 
			OWLAxiom axiom = factory.getOWLSubClassOfAxiom(son,father);	
			manager.applyChange(new AddAxiom(ontology, axiom));	
		}
	}

	/**
	 * Create all GeneralizationSets
	 * */
	private void processGeneralizationSet() {
		for(GeneralizationSet gen : lstGenSets){
			if(gen.getGeneralization().get(0).getGeneral() instanceof DataType){
				continue;
			}
			if(!gen.getGeneralization().isEmpty()){
				if((gen.isIsDisjoint() && gen.isIsCovering()) || gen.getGeneralization().get(0).getSpecific() instanceof Phase){
					//{disjoint, complete} or is a Phase Partition
					processGeneralizationDisjointCovering(gen.getGeneralization());
				}else if(gen.isIsDisjoint() && !gen.isIsCovering()){
					//{disjoint}
					processGeneralizationDisjoint(gen.getGeneralization());
				}else if(!gen.isIsDisjoint() && gen.isIsCovering()){
					//{complete}
					processGeneralizationCovering(gen.getGeneralization());
				}else{
					//{}
					processGeneralization(gen.getGeneralization());
				}		
			}
		}
	}

	private void processGeneralizationDisjointCovering(EList<Generalization> genSet){
		OWLClass father = getOwlClass(genSet.get(0).getGeneral());

		Set<OWLClass> lstCls = new HashSet<OWLClass>();

		for(int i = 0; i < genSet.size(); i++){
			OWLClass son = 	getOwlClass(genSet.get(i).getSpecific());				

			//Set subClassOf 
			OWLAxiom axiom = factory.getOWLSubClassOfAxiom(son,father);	
			manager.applyChange(new AddAxiom(ontology, axiom));	

			//Used after to make the unionOf
			lstCls.add(son);
		}

		OWLAxiom axiom = factory.getOWLDisjointClassesAxiom(lstCls);		
		manager.applyChange(new AddAxiom(ontology, axiom));

		//Set all classes equivalents
		OWLObjectUnionOf ouf = factory.getOWLObjectUnionOf(lstCls);
		OWLEquivalentClassesAxiom eqclax = factory.getOWLEquivalentClassesAxiom(father, ouf);
		manager.addAxiom(ontology, eqclax);
	}

	private void processGeneralizationDisjoint(EList<Generalization> genSet){
		OWLClass father = getOwlClass(genSet.get(0).getGeneral());

		Set<OWLClass> lstCls = new HashSet<OWLClass>();

		for(int i = 0; i < genSet.size(); i++){
			OWLClass son = 	getOwlClass(genSet.get(i).getSpecific());				

			//Set subClassOf 
			OWLAxiom axiom = factory.getOWLSubClassOfAxiom(son,father);	
			manager.applyChange(new AddAxiom(ontology, axiom));	

			//Set both classes disjoint
			lstCls.add(son);
		}

		OWLAxiom axiom = factory.getOWLDisjointClassesAxiom(lstCls);		
		manager.applyChange(new AddAxiom(ontology, axiom));
	}

	private void processGeneralizationCovering(EList<Generalization> genSet){
		OWLClass father = getOwlClass(genSet.get(0).getGeneral());

		Set<OWLClass> lstCls = new HashSet<OWLClass>();

		for(int i = 0; i < genSet.size(); i++){
			OWLClass son = 	getOwlClass(genSet.get(i).getSpecific());				

			//Set subClassOf 
			OWLAxiom axiom = factory.getOWLSubClassOfAxiom(son,father);	
			manager.applyChange(new AddAxiom(ontology, axiom));	

			//Used after to make the unionOf
			lstCls.add(son);
		}

		//Set all classes equivalents
		OWLObjectUnionOf ouf = factory.getOWLObjectUnionOf(lstCls);
		OWLEquivalentClassesAxiom eqclax = factory.getOWLEquivalentClassesAxiom(father, ouf);
		manager.addAxiom(ontology, eqclax);
	}

	private void processGeneralization(EList<Generalization> genSet){
		OWLClass father = getOwlClass(genSet.get(0).getGeneral());

		for(int i = 0; i < genSet.size(); i++){
			OWLClass son = 	getOwlClass(genSet.get(i).getSpecific());				

			//Set subClassOf 
			OWLAxiom axiom = factory.getOWLSubClassOfAxiom(son,father);	
			manager.applyChange(new AddAxiom(ontology, axiom));	
		}
	}

	/**
	 * Process the simple DataTypes (as class attributes), DataTypes in class (class with
	 * stereotype DataType) and DataType structured (DataType that has other DataTypes).
	 * */
	private void processDataType() {
		for(RefOntoUML.Class ontCls: lstOntClass){
			//has some attribute
			if(!ontCls.getAttribute().isEmpty()){
				for(Property prop:ontCls.getAttribute()){
					//Set the owner class of the datatype
					_OWLownerClass = getOwlClass(ontCls);
					_RefOntoOwnerClass = ontCls;
					_upperCard.add(1);
					_lowerCard.add(1);
					createAttribute(prop);
					//Clean up variables
					_attributeName = "";
					_upperCard = new ArrayList<Integer>();
					_lowerCard = new ArrayList<Integer>();
				}
			}
		}

		_RefOntoOwnerClass = null;
		for(RefOntoUML.DataType dtcls: lstDataType){
			if(!dtcls.getAttribute().isEmpty()){
				for(Property prop:dtcls.getAttribute()){
					if(dataTypesProcesseds.contains(prop)){
						continue;
					}
					//Set the owner class of the datatype
					_OWLownerClass = null;
					_RefOntoOwnerClass = dtcls;
					_upperCard.add(1);
					_lowerCard.add(1);
					createAttribute(prop);
					//Clean up variables
					_attributeName = "";
					_upperCard = new ArrayList<Integer>();
					_lowerCard = new ArrayList<Integer>();
				}
			}
		}
	}

	/**
	 * These are variables used in the context of the process of attributes structured
	 * */
	private String _attributeName = "";
	private OWLClass _OWLownerClass = null;
	private RefOntoUML.Classifier _RefOntoOwnerClass = null;
	private Property _prop = null;
	private ArrayList<Integer> _upperCard = new ArrayList<Integer>();
	private ArrayList<Integer> _lowerCard = new ArrayList<Integer>();

	/**
	 * Used to create the Class attributes
	 * */
	private void createAttribute(Property prop) {

		OWLDatatype tipoAtributo = null;
		OWLDataProperty atributo = null;

		//If the type of this property isn't in the model.
		if(prop == null){
			//Than create a generic type for this property (RDFS_LITERAL)
			tipoAtributo = factory.getOWLDatatype(OWL2Datatype.RDFS_LITERAL.getIRI());
			atributo = factory.getOWLDataProperty(IRI.create(_attributeName));
			prop = _prop;
			//Removing the current cardinality
			if(_lowerCard.size() > 1){
				_lowerCard.remove(_lowerCard.size()-1);
				_upperCard.remove(_upperCard.size()-1);
			}
			dataTypesProcesseds.add(prop);
		}else{
			dataTypesProcesseds.add(prop);
			_prop = prop;
			String _aux = "";

			tipoAtributo = getDataTypeRange(prop.getType());
			if(tipoAtributo == null){
				//Isn't a simple DataType
				if(_attributeName.isEmpty()){
					//add name to attribute variable
					//OwnerClass.AttributeName
					_attributeName = getDataPropertyName(_RefOntoOwnerClass, prop);
				}else{
					//this is a transitivy property, because that we need to return a level
					_aux = _attributeName;
					//concat the name of the current property
					_attributeName+="."+getPropertyName(prop);
				}

				//Used for structured datatypes 
				_lowerCard.add(prop.getLower());
				_upperCard.add(prop.getUpper());

				processDataTypeProperty(prop);

				//Removing the current cardinality
				if(_lowerCard.size() > 1){
					_lowerCard.remove(_lowerCard.size()-1);
					_upperCard.remove(_upperCard.size()-1);
				}
				//returning a level of the chain of datatypes
				_attributeName = _aux;
				return;
			}else{
				if(_attributeName.isEmpty()){
					//Isn't a simple DataType
					atributo = factory.getOWLDataProperty(IRI.create(getDataPropertyName(_RefOntoOwnerClass, prop)));
				}else{
					//Is a simple DataType
					_aux = _attributeName;
					_attributeName+="."+getPropertyName(prop);
					atributo = factory.getOWLDataProperty(IRI.create(_attributeName));
					_attributeName = _aux;
				}
			}
		}

		if(_RefOntoOwnerClass != null){
			if(!hashDataProperty.containsKey(_RefOntoOwnerClass)){
				hashDataProperty.put(_RefOntoOwnerClass, new HashSet<OWLDataProperty>());
			}
			hashDataProperty.get(_RefOntoOwnerClass).add(atributo);
		}

		//Set the Range of the DataProperty
		OWLDataPropertyRangeAxiom axRange = factory.getOWLDataPropertyRangeAxiom(atributo, tipoAtributo);
		manager.applyChange(new AddAxiom(ontology, axRange));

		if(_OWLownerClass == null){
			//get here if are processing alone datatypes
			return;
		}

		//set the owner of this datatype (Domain)
		OWLDataPropertyDomainAxiom axDomain = factory.getOWLDataPropertyDomainAxiom(atributo, _OWLownerClass);
		manager.applyChange(new AddAxiom(ontology, axDomain));

		//Solving the cardinality of the attribute
		int upperCard = 1;
		int lowerCard = 1;

		//Multiply all last cardinality
		for(int i = 0; i < _lowerCard.size(); i++){
			lowerCard *= _lowerCard.get(i);
			if(upperCard == -1 || _upperCard.get(i) == -1){
				upperCard = -1;
			}else{
				upperCard *= _upperCard.get(i);
			}
		}

		if(upperCard ==-1 || prop.getUpper() == -1){
			upperCard = -1;
		}else{
			upperCard *= prop.getUpper();
		}
		lowerCard *= prop.getLower();

		OWLEquivalentClassesAxiom ax = null;
		OWLSubClassOfAxiom sax = null; 

		if(upperCard == lowerCard){
			//x..x
			OWLClassExpression oecr = factory.getOWLDataExactCardinality(lowerCard, atributo, tipoAtributo);
			ax = factory.getOWLEquivalentClassesAxiom(_OWLownerClass, oecr);
		}else if(upperCard == -1 && lowerCard == 1){
			//1..*
			OWLDataSomeValuesFrom oecr = factory.getOWLDataSomeValuesFrom(atributo, tipoAtributo);
			ax = factory.getOWLEquivalentClassesAxiom(_OWLownerClass, oecr);
		}else if (upperCard != -1 && lowerCard == 0){
			//0..*
			OWLDataMaxCardinality maxcard = factory.getOWLDataMaxCardinality(upperCard, atributo,tipoAtributo);
			sax = factory.getOWLSubClassOfAxiom(_OWLownerClass, maxcard);
		}else if(upperCard == -1 && lowerCard != 0){
			//x..*
			OWLDataMinCardinality mincard = factory.getOWLDataMinCardinality(lowerCard, atributo,tipoAtributo);
			ax = factory.getOWLEquivalentClassesAxiom(_OWLownerClass, mincard);	
		}else if(upperCard != -1 && lowerCard > 0){
			//x..n
			OWLDataMaxCardinality maxcard = factory.getOWLDataMaxCardinality(upperCard, atributo,tipoAtributo);
			OWLDataMinCardinality mincard = factory.getOWLDataMinCardinality(lowerCard, atributo,tipoAtributo);
			OWLObjectIntersectionOf oio =  factory.getOWLObjectIntersectionOf(maxcard,mincard);
			ax = factory.getOWLEquivalentClassesAxiom(_OWLownerClass, oio);
		}else{
			errors += "Warning: The cardinality 0..* is not mapped to OWL (occurrence at datatype "+atributo.getIRI().toString().substring(atributo.getIRI().toString().indexOf("#")+1)+" , from classe "+_OWLownerClass.getIRI().toString().substring(_OWLownerClass.getIRI().toString().indexOf("#")+1)+");\n";
		}

		if(ax != null){
			manager.applyChange(new AddAxiom(ontology, ax));
		}

		if(sax != null){
			manager.applyChange(new AddAxiom(ontology, sax));
		}
	}

	/**
	 * Process the chain of the properties
	 * @param The actual property of the chain
	 */
	@SuppressWarnings("unused")
	private void processDataTypeProperty(Property prop){
		int c = 0;
		boolean f = false;
		for(DataType dt:lstDataType){	
			//search in all datatypes from the model
			if(dt.getName().equals(prop.getType().getName())){
				for (Property dtProp : dt.getAttribute()) {
					createAttribute(dtProp);
					f = true;
				}
				if(f){
					return;
				}
				c++;
			}
		}
		//		if(c==1){
		errors += "Unknown datatype "+_attributeName.substring(_attributeName.indexOf("#")+1)+" of (class "+getName(_RefOntoOwnerClass)+") mapped to OWL Literal;\n";
		createAttribute(null);
		//		}
	}

	/**
	 * Get the range of this type.
	 * The rages supported are: unsigned_int, int, unsigned_byte, 
	 * double, string, normalized_string, boolean, hex_binary, 
	 * short, byte, unsigned_long or null if doesn't have some match
	 * */
	private OWLDatatype getDataTypeRange(Type type) {
		String range = getName(type);
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
	 * Process all OntoUML class.
	 * This method is the funnel between OntoUML and OWL.
	 * It create a OWLClass for each RefOntoUML.Class
	 * */
	private void processClass() {
		for(RefOntoUML.Class ontCls: lstOntClass){
			OWLClass owlCls = getOwlClass(ontCls);
			OWLDeclarationAxiom declarationAxiom = factory.getOWLDeclarationAxiom(owlCls);
			manager.addAxiom(ontology, declarationAxiom);
		}
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
	 * Put all equivalentClass, disjointClass and subClassOf axioms from a class in just one axiom for only one class
	 * @param 
	 */
	private void processAxiom(){
		for(OWLClass currentClass : ontology.getClassesInSignature()){

			Set<OWLClassExpression> lstEquivalentExpression = new HashSet<OWLClassExpression>();
			Set<OWLClassExpression> lstDisjointExpression = new HashSet<OWLClassExpression>();
			Set<OWLClassExpression> lstSubClassOfExpression = new HashSet<OWLClassExpression>();

			Set<OWLClassAxiom> lstClassAxioms= ontology.getAxioms(currentClass);
			Iterator<OWLClassAxiom> itr = lstClassAxioms.iterator();

			//Process EquivalentClassAxiom
			while(itr.hasNext()) {
				OWLClassAxiom ax = itr.next();

				if(ax instanceof OWLEquivalentClassesAxiom){
					OWLNaryClassAxiom nax = (OWLNaryClassAxiom)ax;
					lstEquivalentExpression.addAll(nax.getClassExpressions());
					manager.removeAxiom(ontology, ax);
				} 
				if(ax instanceof OWLSubClassOfAxiom){
					OWLSubClassOfAxiom sax = (OWLSubClassOfAxiom)ax;
					lstSubClassOfExpression.add(sax.getSuperClass());
					manager.removeAxiom(ontology, ax);	
				}
			}

			if(lstEquivalentExpression.size() > 1){
				lstEquivalentExpression.remove(currentClass);
				OWLObjectIntersectionOf oi = factory.getOWLObjectIntersectionOf(lstEquivalentExpression);
				OWLEquivalentClassesAxiom eqAx = factory.getOWLEquivalentClassesAxiom(currentClass, oi);
				manager.applyChange(new AddAxiom(ontology, eqAx));
			}
			if(lstDisjointExpression.size() > 1){
				manager.applyChange(new AddAxiom(ontology, factory.getOWLDisjointClassesAxiom(lstDisjointExpression)));
			}
			if(lstSubClassOfExpression.size() > 1){
				OWLObjectIntersectionOf oi = factory.getOWLObjectIntersectionOf(lstSubClassOfExpression);
				OWLSubClassOfAxiom sbAx = factory.getOWLSubClassOfAxiom(currentClass, oi);
				manager.applyChange(new AddAxiom(ontology, sbAx));
			}else{
				Iterator<OWLClassExpression> i = lstSubClassOfExpression.iterator();
				while(i.hasNext()) {
					OWLClassExpression ax = i.next();
					OWLSubClassOfAxiom sbAx = factory.getOWLSubClassOfAxiom(currentClass, ax);
					manager.applyChange(new AddAxiom(ontology, sbAx));
				}
			}
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
	private void processDisjointClass() {
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
}
