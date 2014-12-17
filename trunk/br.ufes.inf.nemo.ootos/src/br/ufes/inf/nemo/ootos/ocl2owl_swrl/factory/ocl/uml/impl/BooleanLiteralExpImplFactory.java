package br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.uml.impl.BooleanLiteralExpImpl;
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
public class BooleanLiteralExpImplFactory extends PrimitiveLiteralExpImplFactory {

	public BooleanLiteralExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}

	/**
	 * This method solve the boolean rule fragments
	 * 
	 */
	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) {
		//since the factory is created according to the rule fragment, the fragment is got as a boolean fragment
		BooleanLiteralExpImpl booleanLiteralExpImpl = (BooleanLiteralExpImpl)m_NamedElementImpl;
		//then, the boolean symbol is got
		Boolean booleanSymbol = booleanLiteralExpImpl.getBooleanSymbol();
		//and a OWL Literal is generated from the boolean symbol
		OWLLiteral owlLiteral = factory.getOWLLiteral(booleanSymbol);
		//and a swrl variable is created from the owl literal
		SWRLLiteralArgument var = factory.getSWRLLiteralArgument(owlLiteral);
		
		//the variable is added to a variable array and returned
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(var);
		
		return retArgs;
	}
}