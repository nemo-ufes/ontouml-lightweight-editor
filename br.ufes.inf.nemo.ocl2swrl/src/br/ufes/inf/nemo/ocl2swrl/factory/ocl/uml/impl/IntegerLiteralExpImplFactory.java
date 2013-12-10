package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.uml.impl.IntegerLiteralExpImpl;
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
 * @created 24-set-2013 09:16:12
 */
public class IntegerLiteralExpImplFactory extends NumericLiteralExpImplFactory {

	public IntegerLiteralExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}
	
	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) {
		IntegerLiteralExpImpl integerLiteralExpImpl = (IntegerLiteralExpImpl)m_NamedElementImpl;
		Integer integerSymbol = integerLiteralExpImpl.getIntegerSymbol();
		OWLLiteral owlLiteral = factory.getOWLLiteral(integerSymbol);
		SWRLLiteralArgument var = factory.getSWRLLiteralArgument(owlLiteral);
		
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(var);
		
		return retArgs;
	}
	
	@Override
	public ArrayList<SWRLDArgument> solveNegativeNumber(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot) {
		IntegerLiteralExpImpl integerLiteralExpImpl = (IntegerLiteralExpImpl)m_NamedElementImpl;
		Integer integerSymbol = integerLiteralExpImpl.getIntegerSymbol();
		integerSymbol *= (-1);
		OWLLiteral owlLiteral = factory.getOWLLiteral(integerSymbol);
		SWRLLiteralArgument var = factory.getSWRLLiteralArgument(owlLiteral);
		
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(var);
		
		return retArgs;
	}
}