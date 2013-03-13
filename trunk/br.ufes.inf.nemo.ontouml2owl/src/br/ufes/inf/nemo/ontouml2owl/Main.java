package br.ufes.inf.nemo.ontouml2owl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Set;

import javax.xml.transform.ErrorListener;

import org.eclipse.emf.common.util.EList;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Element;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Kind;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class Main {
	
	static String baseOntologyIRI = "http://br.ufes.inf.nemo.ontouml2owl/exemplos/";
	static String baseDocumentIRI = "file:///D:/modeling/OLED/br.ufes.inf.nemo.ontouml2owl/models/owl/";
	static String baseOntouml = "models/ontouml/";
	
	static TETable referencias = new TETable();
	
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
			
					
			// Essa abordagem de usar um for para cada tipo de elemento deve ser custosa
			// pois em cada chamada de getAllInstance deve percorrer todos os elementos do modelo original
			// Melhor percorrer todos os elementos uma única vez utilizando um switch case.
			// Mas é preciso analisar os casos de prioridade de transformação. Por exemplo,
			// aparentemente é fundamental que classes sejam transformadas antes de relações.
			for ( Class ont_cls : p.getAllInstances(Class.class))
			{
				transformClass(ont_cls);
			}
			
			for ( Generalization ont_gen : p.getAllInstances(Generalization.class)){
				transformGeneralization(ont_gen);
			}
			
			for ( GeneralizationSet ont_genset : p.getAllInstances(GeneralizationSet.class)){
				transformGeneralizationSet(ont_genset);
			}
			
			for ( FormalAssociation ont_formal : p.getAllInstances(FormalAssociation.class)){
				transformFormal(ont_formal);
			}
			
			for ( Property ont_attribute : p.getAllInstances(Property.class)) {
				Association ass = ont_attribute.getAssociation();
				Type t = (Type) ont_attribute.eContainer();
				if ( ass == null )
				{
					//Property other = ont_attribute.getOtherEnd();
					System.out.println(t.toString());
					System.out.println(ont_attribute.getType().toString());
					//System.out.println(other.getType().toString());
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
	
	static private void transformClass(Class ont_cls){
		OWLClass owl_cls = factory.getOWLClass(IRI.create(ontologyIRI + "#" + p.getAlias(ont_cls)));
		referencias.set(ont_cls, owl_cls);
		
		OWLAxiom axiom;
		AddAxiom addAxiom;
		axiom = factory.getOWLDeclarationAxiom(owl_cls);
		addAxiom = new AddAxiom(ontology, axiom);
		manager.applyChange(addAxiom);
	}
	
	static private void transformGeneralization(Generalization ont_gen){
		OWLAxiom axiom;
		OWLClass superclass, subclass;
		
		superclass = (OWLClass) referencias.getOWLCorrespondence(ont_gen.getGeneral());
		subclass = (OWLClass) referencias.getOWLCorrespondence(ont_gen.getSpecific());
		
		axiom = factory.getOWLSubClassOfAxiom(subclass, superclass);
		manager.addAxiom(ontology, axiom);
		
		referencias.set(ont_gen, axiom);
		
	}
	
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
	
	static private void transformFormal(FormalAssociation ont_formal){
		OWLAxiom objPropDecAxiom;  
		OWLAxiom domainAxiom;  
		OWLAxiom rangeAxiom; 
		OWLAxiom subClassMinAxiom = null;
		OWLAxiom subClassMaxAxiom = null;
		OWLObjectMinCardinality minCard;
		OWLObjectMaxCardinality maxCard;

		OWLObjectProperty objproperty, objproperty2; // The number 2 points the inverse object property.
		OWLClass owl_domain, owl_range;
		
		EList<Property> list = ont_formal.getMemberEnd();
		if ( list.size() != 2) System.out.println("ERRO: Associotion with more than 2 ends");
		
		Property uml_source = list.get(0);
		Property uml_target = list.get(1);
		
		owl_domain = (OWLClass) referencias.getOWLCorrespondence(uml_source.getType());
		owl_range = (OWLClass) referencias.getOWLCorrespondence(uml_target.getType());
		
		//System.out.println(p.getAlias(ont_formal));
		
		//Cada relação OntoUML é transformada para duas ObjectProperties. Cada uma representando
		//uma direção da relação.
		
		//Representando a "ida"
		objproperty = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#" + p.getAlias(ont_formal)));
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
		
		referencias.set(ont_formal, objproperty);
		
		manager.applyChange(new AddAxiom(ontology, domainAxiom));
		manager.applyChange(new AddAxiom(ontology, rangeAxiom));
		manager.applyChange(new AddAxiom(ontology, objPropDecAxiom));
		if ( uml_target.getUpper() > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMaxAxiom));
		if ( uml_target.getLower() > 0 ) manager.applyChange(new AddAxiom(ontology, subClassMinAxiom));
		
		//Representando a "volta"
		objproperty2 = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#" + p.getAlias(ont_formal) + "Inv"));
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
}
