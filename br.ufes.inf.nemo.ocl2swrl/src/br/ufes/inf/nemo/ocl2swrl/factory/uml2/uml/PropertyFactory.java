package br.ufes.inf.nemo.ocl2swrl.factory.uml2.uml;

import java.util.Set;

import org.eclipse.uml2.uml.Property;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2swrl.factory.Factory;

public class PropertyFactory extends Factory{
	Property property;
	
	public PropertyFactory(Property property) {
		this.property = property;
	}
	@Override
	public SWRLDArgument solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated, int repeatNumber) {
		//String iriRelationName = nameSpace+property.getAssociation().getName();
		//IRI iriRelation = IRI.create(iriRelationName);
		//OWLObjectProperty relation = factory.getOWLObjectProperty(iriRelation);
		
		return null;
	}
}
