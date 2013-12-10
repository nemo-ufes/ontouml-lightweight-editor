package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.uml.impl.CollectionItemImpl;
import org.eclipse.ocl.uml.impl.CollectionLiteralExpImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2swrl.exceptions.Ocl2SwrlException;
import br.ufes.inf.nemo.ocl2swrl.factory.Factory;
import br.ufes.inf.nemo.ocl2swrl.factory.uml2.uml.internal.impl.TypedElementImplFactory;

/**
 * @author Freddy Brasileiro Silva {freddybrasileiro@gmail.com}
 */
public class CollectionLiteralExpImplFactory extends LiteralExpImplFactory {
	public TypedElementImplFactory partFactory;
	
	public CollectionLiteralExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);		
	}

	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies)  throws Ocl2SwrlException{
		CollectionLiteralExpImpl collectionLiteralExpImpl = (CollectionLiteralExpImpl) this.m_NamedElementImpl;
		
		CollectionItemImpl part = (CollectionItemImpl)collectionLiteralExpImpl.getPart().get(0);
		
		this.partFactory = (TypedElementImplFactory) Factory.constructor(part, this.m_NamedElementImpl);
		ArrayList<SWRLDArgument> retArgsX = this.partFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, operatorNot, repeatNumber, leftSideOfImplies);
		
		return retArgsX;
	}
	
	@Override
	public OWLObjectProperty getOWLObjectProperty(String nameSpace, OntoUMLParser refParser, OWLDataFactory factory) throws Ocl2SwrlException{
		CollectionLiteralExpImpl collectionLiteralExpImpl = (CollectionLiteralExpImpl) this.m_NamedElementImpl;
		CollectionItemImpl part = (CollectionItemImpl)collectionLiteralExpImpl.getPart().get(0);
		
		this.partFactory = (TypedElementImplFactory) Factory.constructor(part, this.m_NamedElementImpl);
		
		return this.partFactory.getOWLObjectProperty(nameSpace, refParser, factory);
		
		
	}
}