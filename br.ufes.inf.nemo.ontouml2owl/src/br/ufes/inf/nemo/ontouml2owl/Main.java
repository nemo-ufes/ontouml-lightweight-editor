package br.ufes.inf.nemo.ontouml2owl;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import RefOntoUML.Class;
import RefOntoUML.Kind;
import RefOntoUML.PackageableElement;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class Main {
	
	TETable referencias = new TETable();
	
	public static void main ( String args[] ) throws OWLOntologyCreationException, OWLOntologyStorageException{
		try {
			//Resource r = ResourceUtil.loadReferenceOntoUML("models/exemplo1.refontouml");

			String baseOntologyIRI = "http://br.ufes.inf.nemo.ontouml2owl/exemplos/";
			String baseDocumentIRI = "file:///D:/modeling/OLED/br.ufes.inf.nemo.ontouml2owl/models/owl/";
			String baseOntouml = "models/ontouml/";

			//CARREGANDO MODELO ONTOUML
			OntoUMLParser p = new OntoUMLParser(baseOntouml + "exemplo1.refontouml");
			
			
			
			//INICIALIZANDO MODELO OWL
			OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
			IRI ontologyIRI = IRI.create( baseOntologyIRI + "exemplo1");
			IRI documentIRI = IRI.create(baseDocumentIRI + "exemplo1.owl");
			SimpleIRIMapper mapper = new SimpleIRIMapper(ontologyIRI,documentIRI);
			manager.addIRIMapper(mapper);
			OWLOntology ontology = manager.createOntology(ontologyIRI);
			
			OWLDataFactory factory = manager.getOWLDataFactory();
			
			
			OWLAxiom axiom;
			AddAxiom addAxiom;
			for ( Class pe : p.getAllInstances(Class.class))
			{
				//System.out.println("Onto class: " + pe.getName());
				
				//System.out.println("n: " + pe);
				//System.out.println("l: " + pe.getLabel());
				
				
			}
			
			/*OWLClass clsA = factory.getOWLClass(IRI.create(ontologyIRI + "#A"));
			OWLClass clsB = factory.getOWLClass(IRI.create(ontologyIRI + "#B"));
			OWLAxiom axiom = factory.getOWLSubClassOfAxiom(clsA, clsB);
			AddAxiom addAxiom = new AddAxiom(ontology, axiom);
			manager.applyChange(addAxiom);*/
			
			manager.saveOntology(ontology);
			
			for ( OWLClass cls : ontology.getClassesInSignature())
			{
				System.out.println("Referenced class: " + cls);
			}
			
			/*Set<OWLClassExpression> superClasses = clsA.getSuperClasses(ontology);
			System.out.println("Asserted superclasses of " + clsA + ":");
			for ( OWLClassExpression desc : superClasses ){
				System.out.println(desc);
			}*/
			
			
			
			System.out.println("Created ontology: " + ontology);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void transformClass(Class cls){
		OWLClass cls = factory.getOWLClass(IRI.create(ontologyIRI + "#" + pe.getName()));
		referencias.set(pe, cls);
		
		axiom = factory.getOWLDeclarationAxiom(cls);
		addAxiom = new AddAxiom(ontology, axiom);
		manager.applyChange(addAxiom);
	}
}
