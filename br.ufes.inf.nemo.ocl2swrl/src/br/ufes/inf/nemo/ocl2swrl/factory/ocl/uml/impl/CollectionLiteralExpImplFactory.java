package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.Set;

import org.eclipse.ocl.uml.impl.CollectionItemImpl;
import org.eclipse.ocl.uml.impl.CollectionLiteralExpImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2swrl.factory.Factory;
import br.ufes.inf.nemo.ocl2swrl.factory.uml2.uml.internal.impl.TypedElementImplFactory;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:12
 */
public class CollectionLiteralExpImplFactory extends LiteralExpImplFactory {
	public TypedElementImplFactory partFactory;
	
	public CollectionLiteralExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);		
	}
	
	public void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public SWRLDArgument solve(OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated, int repeatNumber) {
		CollectionLiteralExpImpl collectionLiteralExpImpl = (CollectionLiteralExpImpl) this.m_NamedElementImpl;
		
		CollectionItemImpl part = (CollectionItemImpl)collectionLiteralExpImpl.getPart().get(0);
		
		this.partFactory = (TypedElementImplFactory) Factory.constructor(part);
		SWRLDArgument varX = this.partFactory.solve(refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, oclConsequentShouldBeNegated, expressionIsNegated, repeatNumber);
		
		return varX;
	}
}