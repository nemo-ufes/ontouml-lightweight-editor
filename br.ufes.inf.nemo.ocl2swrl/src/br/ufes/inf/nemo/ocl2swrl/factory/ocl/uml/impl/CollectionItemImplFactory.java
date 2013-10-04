package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.Set;

import org.eclipse.ocl.uml.impl.CollectionItemImpl;
import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2swrl.factory.Factory;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:12
 */
public class CollectionItemImplFactory extends CollectionLiteralPartImplFactory {

	OCLExpressionImplFactory itemFactory;
	
	public CollectionItemImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);	
	}
	
	
	public void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public SWRLDArgument solve(OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated, int repeatNumber) {
		CollectionItemImpl collectionItemImpl = (CollectionItemImpl) this.m_NamedElementImpl;
		
		OCLExpressionImpl item = (OCLExpressionImpl)collectionItemImpl.getItem();
		
		this.itemFactory = (OCLExpressionImplFactory) Factory.constructor(item);
		
		SWRLDArgument varX = this.itemFactory.solve(refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, oclConsequentShouldBeNegated, expressionIsNegated, repeatNumber);
		
		return varX;
	}
}