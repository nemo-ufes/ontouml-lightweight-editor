package br.ufes.inf.nemo.ontouml2owl;

import java.io.IOException;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Set;

import javax.xml.transform.ErrorListener;

import org.coode.owlapi.rdfxml.parser.DataPropertyListItemTranslator;
import org.eclipse.emf.common.util.EList;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Element;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Kind;
import RefOntoUML.Meronymic;
import RefOntoUML.PackageableElement;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class Main {
	
	static String baseOntologyIRI = "http://br.ufes.inf.nemo.ontouml2owl/exemplos/";
	static String baseDocumentIRI = "file:///D:/modeling/OLED/br.ufes.inf.nemo.ontouml2owl/models/owl/";
	static String baseOntouml = "models/ontouml/";
	
	static TETable referencias = new TETable();
	static HashMap<String, OWLDatatype> tiposPrimitivos = new HashMap<String, OWLDatatype>();
	
	static OntoUMLParser p;
	
	static IRI ontologyIRI;
	static IRI documentIRI;
	static OWLOntologyManager manager;
	static OWLDataFactory factory;
	static OWLOntology ontology;
	
	
	public static void main ( String args[] ) throws OWLOntologyCreationException, OWLOntologyStorageException{
		try {
			//Resource r = ResourceUtil.loadReferenceOntoUML("models/exemplo1.refontouml");

			

			//CARREGANDO MODELO ONTOUML
			
			p = new OntoUMLParser(baseOntouml + "exemplo1.refontouml");
			
			
			//INICIALIZANDO MODELO OWL
			manager = OWLManager.createOWLOntologyManager();
			factory = manager.getOWLDataFactory();
			
			ontologyIRI = IRI.create( baseOntologyIRI + "exemplo1");
			documentIRI = IRI.create(baseDocumentIRI + "exemplo1.owl");
			
			//investigar o que o código abaixo faz. Tem a ver com o arquivo de saída.
			SimpleIRIMapper mapper = new SimpleIRIMapper(ontologyIRI,documentIRI);
			manager.addIRIMapper(mapper);
			ontology = manager.createOntology(ontologyIRI);
			
			IRI iri;
			iri = IRI.create("xsd:int");
			tiposPrimitivos.put("int", factory.getOWLDatatype(iri));
		
			
			iri = IRI.create("xsd:String");
			tiposPrimitivos.put("char", factory.getOWLDatatype(iri));
			

			for ( DataType ont_dtt : p.getAllInstances(DataType.class))
			{
				transformDataType(ont_dtt);
			}
			
			
			for ( Class ont_cls : p.getAllInstances(Class.class))
			{
				transformClass(ont_cls);
			}
			
			setDisjointClasses();
			
			
			for ( Generalization ont_gen : p.getAllInstances(Generalization.class)){
				transformGeneralization(ont_gen);
			}
			
			for ( GeneralizationSet ont_genset : p.getAllInstances(GeneralizationSet.class)){
				transformGeneralizationSet(ont_genset);
			}
			
			for ( Association ont_assoc : p.getAllInstances(Association.class)){
				
				
				if ( ont_assoc instanceof Derivation ) 
				{
					// Criar regra neste caso, se existe conexão pelo relator então existe a material e vice-versa.
					//System.out.println("Derivation");
					continue;
				}
					
				
				if ( ont_assoc instanceof Meronymic )
				{
					//System.out.println(ont_assoc.getName());
					transformMeronymicAssociation(ont_assoc);
					continue;
				}
				
				transformAssociation(ont_assoc);
			}
			
			for ( Property ont_attribute : p.getAllInstances(Property.class)) {
				Association ass = ont_attribute.getAssociation();
				Boolean isAttribute = ( ass == null);
				//Type t = (Type) ont_attribute.eContainer();
				if ( isAttribute )
				{
					transformAttribute(ont_attribute);
				}
				
			}
			
			manager.saveOntology(ontology);
			
			//referencias.show();
			
			System.out.println("Created ontology: " + ontology);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Determina quais classes são disjuntas.
	 * 
	 * São consideradas disjuntas os Substance Sortais, os Relators e os Datatypes que não possuem pai.
	 * */
	static private void setDisjointClasses()
	{
		
		Set<Classifier> ont_classesDisjuntas = new HashSet<Classifier>();
		
	
		for ( Class ont_cls : p.getAllInstances(SubstanceSortal.class))
		{
			ont_classesDisjuntas.add(ont_cls);
		}
		
		//pega os datatypes que não possuem pai
		for ( DataType ont_cls : p.getAllInstances(DataType.class))
		{
			Set<Classifier> pais = p.getParents(ont_cls);
			
			if (pais.size() == 0) {
				//System.out.println("Top-class: " + ont_cls.getName()  + "    " + classes.size());
				ont_classesDisjuntas.add(ont_cls);
			}
		}
		
		//pega os relators que não possuem pai
		for ( Relator ont_cls : p.getAllInstances(Relator.class))
		{
			Set<Classifier> pais = p.getParents(ont_cls);
			
			if (pais.size() == 0) {
				//System.out.println("Top-class: " + ont_cls.getName()  + "    " + classes.size());
				ont_classesDisjuntas.add(ont_cls);
			}
		}
		
		Set<OWLClass> owl_classesDisjuntas = new HashSet<OWLClass>();
		for ( Classifier ont_cls : ont_classesDisjuntas )
		{
			owl_classesDisjuntas.add( (OWLClass) referencias.getOWLCorrespondence(ont_cls));
		}
		
		OWLAxiom disjointClassesAxiom = factory.getOWLDisjointClassesAxiom(owl_classesDisjuntas);
		manager.applyChange(new AddAxiom(ontology, disjointClassesAxiom));
	}
	
	/*
	 * Realiza a transformação de associações entre classes.
	 * 
	 * Tais relações são transformadas para Object Properties. Cada relação OntoUML é transformada para duas ObjectProperties.
	 * Cada uma representando uma direção da relação. As cardinalidades são representadas afirmando em OWL que toda instância
	 * da classe de origem faz parte do conjunto de classes que possuem tal relação respeitando a restrição cardinalidade com 
	 * a classe de destino.
	 */
	static private void transformAssociation(Association ont_assoc){
		OWLAxiom objPropDecAxiom;  
		OWLAxiom domainAxiom;  
		OWLAxiom rangeAxiom; 
		OWLAxiom subClassMinAxiom = null;
		OWLAxiom subClassMaxAxiom = null;
		OWLObjectMinCardinality minCard;
		OWLObjectMaxCardinality maxCard;
	
		OWLObjectProperty objproperty, objproperty2; // The number 2 means inverse object property.
		OWLClass owl_domain, owl_range;
		
		//System.out.println(ont_assoc.getName());
		
		EList<Property> list = ont_assoc.getMemberEnd();
		if ( list.size() != 2) System.out.println("ERRO: Associotion with more than 2 ends");
		
		Property uml_source = list.get(0);
		Property uml_target = list.get(1);
		
		owl_domain = (OWLClass) referencias.getOWLCorrespondence(uml_source.getType());
		owl_range = (OWLClass) referencias.getOWLCorrespondence(uml_target.getType());
		
		//System.out.println(p.getAlias(ont_formal));
		
		
		//Representando a "ida"
		objproperty = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#" + p.getAlias(ont_assoc)));
		domainAxiom = factory.getOWLObjectPropertyDomainAxiom(objproperty, owl_domain);
		rangeAxiom = factory.getOWLObjectPropertyRangeAxiom(objproperty, owl_range);
		
		// as estruturas condicionais abaixo representam as cadinalidades mínimas e máximas
		if ( uml_target.getLower() > 0 ) {
			minCard = factory.getOWLObjectMinCardinality(uml_target.getLower(), objproperty, owl_range);
			subClassMinAxiom = factory.getOWLSubClassOfAxiom(owl_domain, minCard);
		}
		if ( uml_target.getUpper() > 0 ) {
			maxCard = factory.getOWLObjectMaxCardinality(uml_target.getUpper(), objproperty, owl_range);
			subClassMaxAxiom = factory.getOWLSubClassOfAxiom(owl_domain, maxCard);
		}
		
		objPropDecAxiom = factory.getOWLDeclarationAxiom(objproperty);
		
		referencias.set(ont_assoc, objproperty);
		
		manager.applyChange(new AddAxiom(ontology, domainAxiom));
		manager.applyChange(new AddAxiom(ontology, rangeAxiom));
		manager.applyChange(new AddAxiom(ontology, objPropDecAxiom));
		if ( uml_target.getUpper() > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMaxAxiom));
		if ( uml_target.getLower() > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMinAxiom));
		
		//Representando a "volta"
		objproperty2 = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#" + p.getAlias(ont_assoc) + "Inv"));
		domainAxiom = factory.getOWLObjectPropertyDomainAxiom(objproperty2, owl_range);
		rangeAxiom = factory.getOWLObjectPropertyRangeAxiom(objproperty2, owl_domain);
		
		if ( uml_source.getLower() > 0 ) {
			minCard = factory.getOWLObjectMinCardinality(uml_source.getLower(), objproperty2, owl_domain);
			subClassMinAxiom = factory.getOWLSubClassOfAxiom(owl_range, minCard);
		}
		if ( uml_source.getUpper() > 0 ) {
			maxCard = factory.getOWLObjectMaxCardinality(uml_source.getUpper(), objproperty2, owl_domain);
			subClassMaxAxiom = factory.getOWLSubClassOfAxiom(owl_range, maxCard);
		}
		
		objPropDecAxiom = factory.getOWLDeclarationAxiom(objproperty2);
		
		manager.applyChange(new AddAxiom(ontology, domainAxiom));
		manager.applyChange(new AddAxiom(ontology, rangeAxiom));
		manager.applyChange(new AddAxiom(ontology, objPropDecAxiom));
		if ( uml_source.getUpper() > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMaxAxiom));
		if ( uml_source.getLower() > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMinAxiom));
		
		OWLAxiom inverseAxiom = factory.getOWLInverseObjectPropertiesAxiom(objproperty, objproperty2 );
		manager.applyChange(new AddAxiom(ontology, inverseAxiom));	
		
	}

	/*
	 * Realiza a transformação de atributos de classes.
	 * 
	 * Tais relações são transformadas em Object ou Data Properties.
	 * Se o tipo do atributo for um tipo primitivo, é transformado para um Data Propertie. Caso contrário é transformado
	 * para um object propertie. As restrições de cardinalidade são representadas de forma análoga à transformação de associações, 
	 * no método transformAssociation. 
	 */
	static private void transformAttribute(Property ont_attr)
	{
		Type container = (Type) ont_attr.eContainer();
		Type type = ont_attr.getType();
		int minCard = ont_attr.getLower();
		int maxCard = ont_attr.getUpper();
		
		OWLClass owl_dataDomain = (OWLClass) referencias.getOWLCorrespondence(container);
		if ( type instanceof PrimitiveType ) {
			
			
			OWLDataRange owl_range = (OWLDataRange) tiposPrimitivos.get(type.getName());
			OWLDataMinCardinality OWLDataMinCard = null;
			OWLDataMaxCardinality OWLDataMaxCard = null;
			OWLSubClassOfAxiom subClassMinAxiom = null;
			OWLSubClassOfAxiom subClassMaxAxiom = null;
			
			
			OWLDataProperty dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#" + p.getAlias(ont_attr)));
			OWLDataPropertyDomainAxiom domainAxiom = factory.getOWLDataPropertyDomainAxiom(dataproperty, owl_dataDomain);
			OWLDataPropertyRangeAxiom rangeAxiom = factory.getOWLDataPropertyRangeAxiom(dataproperty, owl_range);
			
			// as estruturas condicionais abaixo representam as cadinalidades mínimas e máximas
			if ( minCard > 0 ) {
				OWLDataMinCard = factory.getOWLDataMinCardinality(minCard, dataproperty, owl_range);
				subClassMinAxiom = factory.getOWLSubClassOfAxiom(owl_dataDomain, OWLDataMinCard);
			}
			if ( maxCard > 0 ) {
				OWLDataMaxCard = factory.getOWLDataMaxCardinality(maxCard, dataproperty, owl_range);
				subClassMaxAxiom = factory.getOWLSubClassOfAxiom(owl_dataDomain, OWLDataMaxCard);
			}
			
			OWLDeclarationAxiom objPropDecAxiom = factory.getOWLDeclarationAxiom(dataproperty);
			
			referencias.set(ont_attr, dataproperty);
			
			manager.applyChange(new AddAxiom(ontology, domainAxiom));
			manager.applyChange(new AddAxiom(ontology, rangeAxiom));
			manager.applyChange(new AddAxiom(ontology, objPropDecAxiom));
			if ( maxCard > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMaxAxiom));
			if ( minCard > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMinAxiom));
			
		} else {
			
			OWLClass owl_domain = (OWLClass) referencias.getOWLCorrespondence(container);
			OWLClass owl_range = (OWLClass) referencias.getOWLCorrespondence(type);
			OWLObjectMinCardinality OWLObjectMinCard = null;
			OWLObjectMaxCardinality OWLObjectMaxCard = null;
			OWLSubClassOfAxiom subClassMinAxiom = null;
			OWLSubClassOfAxiom subClassMaxAxiom = null;
			
			
			OWLObjectProperty objproperty = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#" + p.getAlias(ont_attr)));
			OWLObjectPropertyDomainAxiom domainAxiom = factory.getOWLObjectPropertyDomainAxiom(objproperty, owl_domain);
			OWLObjectPropertyRangeAxiom rangeAxiom = factory.getOWLObjectPropertyRangeAxiom(objproperty, owl_range);
			
			// as estruturas condicionais abaixo representam as cadinalidades mínimas e máximas
			if ( minCard > 0 ) {
				OWLObjectMinCard = factory.getOWLObjectMinCardinality(minCard, objproperty, owl_range);
				subClassMinAxiom = factory.getOWLSubClassOfAxiom(owl_domain, OWLObjectMinCard);
			}
			if ( maxCard > 0 ) {
				OWLObjectMaxCard = factory.getOWLObjectMaxCardinality(maxCard, objproperty, owl_range);
				subClassMaxAxiom = factory.getOWLSubClassOfAxiom(owl_domain, OWLObjectMaxCard);
			}
			
			OWLDeclarationAxiom objPropDecAxiom = factory.getOWLDeclarationAxiom(objproperty);
			
			referencias.set(ont_attr, objproperty);
			
			manager.applyChange(new AddAxiom(ontology, domainAxiom));
			manager.applyChange(new AddAxiom(ontology, rangeAxiom));
			manager.applyChange(new AddAxiom(ontology, objPropDecAxiom));
			if ( maxCard > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMaxAxiom));
			if ( minCard > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMinAxiom));
		}
		
		
	}
	
	/*
	 * Realiza a transformação de classes.
	 * 
	 * A transformação é direta. Cada classe em OntoUML é transformada para uma classe em OWL.
	 */
	static private void transformClass(Class ont_cls){
		OWLClass owl_cls = factory.getOWLClass(IRI.create(ontologyIRI + "#" + p.getAlias(ont_cls)));
		referencias.set(ont_cls, owl_cls);
		
		OWLAxiom axiom;
		AddAxiom addAxiom;
		axiom = factory.getOWLDeclarationAxiom(owl_cls);
		addAxiom = new AddAxiom(ontology, axiom);
		manager.applyChange(addAxiom);
	}
	
	/*
	 * Realiza a transformação de DataTypes.
	 * 
	 * Cada Datatype é transformado em uma classe.
	 */
	static private void transformDataType(DataType ont_cls){
		OWLClass owl_cls = factory.getOWLClass(IRI.create(ontologyIRI + "#" + p.getAlias(ont_cls)));
		referencias.set(ont_cls, owl_cls);
		
		OWLAxiom axiom;
		AddAxiom addAxiom;
		axiom = factory.getOWLDeclarationAxiom(owl_cls);
		addAxiom = new AddAxiom(ontology, axiom);
		manager.applyChange(addAxiom);
	}
	
	/*
	 * Realiza a transformação de Generalizations.
	 * 
	 * A tranformação é direta entre uma relação de generalização em OntoUML e uma em OWL.
	 * 
	 */
	static private void transformGeneralization(Generalization ont_gen){
		OWLAxiom axiom;
		OWLClass superclass, subclass;
		
		superclass = (OWLClass) referencias.getOWLCorrespondence(ont_gen.getGeneral());
		subclass = (OWLClass) referencias.getOWLCorrespondence(ont_gen.getSpecific());
		
		axiom = factory.getOWLSubClassOfAxiom(subclass, superclass);
		manager.addAxiom(ontology, axiom);
		
		referencias.set(ont_gen, axiom);
		
	}
	
	/*
	 * Realiza a transformação de Generalization Sets
	 * 
	 * O objetivo desta tranformação é representar as propriedades dos generalization sets de serem disjuntos e/ou completos.
	 * 
	 */
	static private void transformGeneralizationSet(GeneralizationSet ont_genset){
		OWLAxiom axiom;
		HashSet<OWLClass> owl_subtypes = new HashSet<OWLClass>();
		OWLClass owl_supertype = null;
		
		boolean isNothing = !ont_genset.isIsDisjoint() && !ont_genset.isIsCovering();
		if ( isNothing ) return;
		
		boolean isDisjoint = ont_genset.isIsDisjoint() && !ont_genset.isIsCovering();
		boolean isComplete = !ont_genset.isIsDisjoint() && ont_genset.isIsCovering();
		boolean isDisjointComplete = ont_genset.isIsDisjoint() && ont_genset.isIsCovering();
		
				
		for ( Generalization ont_gen : ont_genset.getGeneralization())
		{
			// a atribuição de owl_supertype é feita dentro do for pois ela é feita em relação 
			// a ont_gen, que é a generelização corrente. Ainda não conheço um meio de fazer isso
			// fora do for, ou seja, pegar o supertype direto do generalizationset.
			owl_supertype = ( owl_supertype == null) ? (OWLClass) referencias.getOWLCorrespondence(ont_gen.getGeneral()) : owl_supertype;
			owl_subtypes.add( (OWLClass) referencias.getOWLCorrespondence(ont_gen.getSpecific()) );
		}
		
		if ( owl_supertype == null ) System.out.println("ERRO: owl_supertype is null");

		if ( isComplete ){
			
			OWLClassExpression objectUnionOf = factory.getOWLObjectUnionOf(owl_subtypes);
			axiom = factory.getOWLEquivalentClassesAxiom(owl_supertype, objectUnionOf);
			
			
		} else if ( isDisjoint ){

			axiom = factory.getOWLDisjointClassesAxiom(owl_subtypes);
			
		} else if ( isDisjointComplete ) {
			
			axiom = factory.getOWLDisjointUnionAxiom(owl_supertype, owl_subtypes);
			
		} else {
			//impossível entrar aqui mas se não deixar esse else a IDE
			//mostra um erro informando que axiom pode não ter sido inicializada.
			axiom = null;
			System.out.println("ERRO: Generalizationset is not covering and not disjoint");
		}
		
		
		
		referencias.set(ont_genset, axiom);
		AddAxiom addAxiom = new AddAxiom(ontology, axiom);
		manager.applyChange(addAxiom);
	}

	/*
	 * Realiza a transformação de relações todo-parte. Também chamadas de meronímicas.
	 * 
	 * Cada relação todo-parte é transformada para duas Object Properties, representando a ida e a volta. E a esta relação
	 * adicionam-se duas meta-propriedades características das relações todo-parte ( em UML ):
	 * - Um objeto não pode ser uma parte de si mesmo. ( irreflexiva )
	 * - Cada parte só pertence a um todo. ( funcional ) 
	 */
	static private void transformMeronymicAssociation(Association ont_assoc){
		OWLAxiom objPropDecAxiom;  
		OWLAxiom domainAxiom;  
		OWLAxiom rangeAxiom; 
		OWLAxiom subClassMinAxiom = null;
		OWLAxiom subClassMaxAxiom = null;
		OWLObjectMinCardinality minCard;
		OWLObjectMaxCardinality maxCard;
	
		OWLObjectProperty objproperty, objproperty2; // The number 2 means inverse object property.
		OWLClass owl_domain, owl_range;
		
		//System.out.println(ont_assoc.getName());
		
		EList<Property> list = ont_assoc.getMemberEnd();
		if ( list.size() != 2) System.out.println("ERRO: Associotion with more than 2 ends");
		
		Property uml_source = list.get(0);
		Property uml_target = list.get(1);
		
		owl_domain = (OWLClass) referencias.getOWLCorrespondence(uml_source.getType());
		owl_range = (OWLClass) referencias.getOWLCorrespondence(uml_target.getType());
		
		//System.out.println(p.getAlias(ont_formal));
		
		//Cada relação OntoUML é transformada para duas ObjectProperties. Cada uma representando
		//uma direção da relação.
		
		//Representando a "ida"
		objproperty = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#" + p.getAlias(ont_assoc)));
		domainAxiom = factory.getOWLObjectPropertyDomainAxiom(objproperty, owl_domain);
		rangeAxiom = factory.getOWLObjectPropertyRangeAxiom(objproperty, owl_range);
		
		// as estruturas condicionais abaixo representam as cadinalidades mínimas e máximas
		if ( uml_target.getLower() > 0 ) {
			minCard = factory.getOWLObjectMinCardinality(uml_target.getLower(), objproperty, owl_range);
			subClassMinAxiom = factory.getOWLSubClassOfAxiom(owl_domain, minCard);
		}
		if ( uml_target.getUpper() > 0 ) {
			maxCard = factory.getOWLObjectMaxCardinality(uml_target.getUpper(), objproperty, owl_range);
			subClassMaxAxiom = factory.getOWLSubClassOfAxiom(owl_domain, maxCard);
		}
		
		objPropDecAxiom = factory.getOWLDeclarationAxiom(objproperty);
		
		referencias.set(ont_assoc, objproperty);
		
		manager.applyChange(new AddAxiom(ontology, domainAxiom));
		manager.applyChange(new AddAxiom(ontology, rangeAxiom));
		manager.applyChange(new AddAxiom(ontology, objPropDecAxiom));
		if ( uml_target.getUpper() > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMaxAxiom));
		if ( uml_target.getLower() > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMinAxiom));
		
		//Representando a "volta"
		objproperty2 = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#" + p.getAlias(ont_assoc) + "Inv"));
		domainAxiom = factory.getOWLObjectPropertyDomainAxiom(objproperty2, owl_range);
		rangeAxiom = factory.getOWLObjectPropertyRangeAxiom(objproperty2, owl_domain);
		
		if ( uml_source.getLower() > 0 ) {
			minCard = factory.getOWLObjectMinCardinality(uml_source.getLower(), objproperty2, owl_domain);
			subClassMinAxiom = factory.getOWLSubClassOfAxiom(owl_range, minCard);
		}
		if ( uml_source.getUpper() > 0 ) {
			maxCard = factory.getOWLObjectMaxCardinality(uml_source.getUpper(), objproperty2, owl_domain);
			subClassMaxAxiom = factory.getOWLSubClassOfAxiom(owl_range, maxCard);
		}
		
		objPropDecAxiom = factory.getOWLDeclarationAxiom(objproperty2);
		
		manager.applyChange(new AddAxiom(ontology, domainAxiom));
		manager.applyChange(new AddAxiom(ontology, rangeAxiom));
		manager.applyChange(new AddAxiom(ontology, objPropDecAxiom));
		if ( uml_source.getUpper() > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMaxAxiom));
		if ( uml_source.getLower() > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMinAxiom));
		
		OWLAxiom inverseAxiom = factory.getOWLInverseObjectPropertiesAxiom(objproperty, objproperty2 );
		manager.applyChange(new AddAxiom(ontology, inverseAxiom));
		
		// ATÉ AQUI, O CÓDIGO ACIMA É UMA CÓPIA DA FUNÇÃO transformAssociation. Abaixo que vem a diferença.
		
		manager.applyChange(new AddAxiom(ontology, factory.getOWLIrreflexiveObjectPropertyAxiom(objproperty)));
		manager.applyChange(new AddAxiom(ontology, factory.getOWLInverseFunctionalObjectPropertyAxiom(objproperty)));
	}

}
