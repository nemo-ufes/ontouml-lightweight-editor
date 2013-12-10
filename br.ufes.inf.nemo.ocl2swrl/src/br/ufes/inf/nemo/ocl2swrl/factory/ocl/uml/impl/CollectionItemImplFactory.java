package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.uml.impl.CollectionItemImpl;
import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
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

/**
 * @author Freddy Brasileiro Silva {freddybrasileiro@gmail.com}
 */
public class CollectionItemImplFactory extends CollectionLiteralPartImplFactory {

	OCLExpressionImplFactory itemFactory;
	
	public CollectionItemImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);	
	}
	
	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies)  throws Ocl2SwrlException {
		CollectionItemImpl collectionItemImpl = (CollectionItemImpl) this.m_NamedElementImpl;
		
		OCLExpressionImpl item = (OCLExpressionImpl)collectionItemImpl.getItem();
		
		this.itemFactory = (OCLExpressionImplFactory) Factory.constructor(item, this.m_NamedElementImpl);
		
		ArrayList<SWRLDArgument> retArgsX = this.itemFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, operatorNot, repeatNumber, leftSideOfImplies);
		
		return retArgsX;
	}
	
	@Override
	public OWLObjectProperty getOWLObjectProperty(String nameSpace, OntoUMLParser refParser, OWLDataFactory factory) throws Ocl2SwrlException{
		
		CollectionItemImpl collectionItemImpl = (CollectionItemImpl) this.m_NamedElementImpl;
		OCLExpressionImpl item = (OCLExpressionImpl)collectionItemImpl.getItem();	
		
		this.itemFactory = (OCLExpressionImplFactory) Factory.constructor(item, this.m_NamedElementImpl);
		
		return this.itemFactory.getOWLObjectProperty(nameSpace, refParser, factory);
		
	}
}