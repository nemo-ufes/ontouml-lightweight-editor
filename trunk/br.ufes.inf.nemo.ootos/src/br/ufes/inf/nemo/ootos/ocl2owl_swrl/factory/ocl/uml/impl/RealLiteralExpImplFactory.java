package br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
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

import RefOntoUML.parser.OntoUMLParser;

/**
 * @author Freddy Brasileiro Silva {freddybrasileiro@gmail.com}
 */
public class RealLiteralExpImplFactory extends NumericLiteralExpImplFactory {

	public RealLiteralExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}

	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) {
		//since the factory is created according to the rule fragment, the fragment is got as a real fragment
		RealLiteralExpImpl realLiteralExpImpl = (RealLiteralExpImpl)m_NamedElementImpl;
		//then, the real symbol is got
		Double realSymbol = realLiteralExpImpl.getRealSymbol();
		//and a OWL Literal is generated from the real symbol
		OWLLiteral owlLiteral = factory.getOWLLiteral(realSymbol);
		//and a swrl variable is created from the owl literal
		SWRLLiteralArgument var = factory.getSWRLLiteralArgument(owlLiteral);
		
		//the variable is added to a variable array and returned
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(var);
		
		return retArgs;
	}
	
	@Override
	public ArrayList<SWRLDArgument> solveNegativeNumber(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot) {
		//since the factory is created according to the rule fragment, the fragment is got as a real fragment
		RealLiteralExpImpl realLiteralExpImpl = (RealLiteralExpImpl)m_NamedElementImpl;
		//then, the real symbol is got
		Double realSymbol = realLiteralExpImpl.getRealSymbol();
		//the real symbol is multiplied for -1
		realSymbol *= (-1);
		//and a OWL Literal is generated from the negative real symbol
		OWLLiteral owlLiteral = factory.getOWLLiteral(realSymbol);
		//and a swrl variable is created from the owl literal
		SWRLLiteralArgument var = factory.getSWRLLiteralArgument(owlLiteral);
		
		//the variable is added to a variable array and returned
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(var);
		
		return retArgs;
	}

}