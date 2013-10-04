package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.Set;

import org.eclipse.ocl.uml.impl.RealLiteralExpImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLLiteralArgument;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:13
 */
public class RealLiteralExpImplFactory extends NumericLiteralExpImplFactory {

	public RealLiteralExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}
	
	public void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public SWRLDArgument solve(OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated, int repeatNumber) {
		RealLiteralExpImpl realLiteralExpImpl = (RealLiteralExpImpl)m_NamedElementImpl;
		Double realSymbol = realLiteralExpImpl.getRealSymbol();
		OWLLiteral owlLiteral = factory.getOWLLiteral(realSymbol);
		SWRLLiteralArgument var = factory.getSWRLLiteralArgument(owlLiteral);
		
		return var;
	}
	
	@Override
	public SWRLDArgument solveNegativeNumber(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
		RealLiteralExpImpl realLiteralExpImpl = (RealLiteralExpImpl)m_NamedElementImpl;
		Double realSymbol = realLiteralExpImpl.getRealSymbol();
		realSymbol *= (-1);
		OWLLiteral owlLiteral = factory.getOWLLiteral(realSymbol);
		SWRLLiteralArgument var = factory.getSWRLLiteralArgument(owlLiteral);
		
		return var;
	}

}