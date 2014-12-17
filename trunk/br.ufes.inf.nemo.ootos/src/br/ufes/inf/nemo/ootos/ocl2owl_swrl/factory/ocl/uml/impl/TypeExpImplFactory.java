package br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.uml.impl.TypeExpImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLVariable;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.util.Util;

/**
 * @author Freddy Brasileiro Silva {freddybrasileiro@gmail.com}
 */
public class TypeExpImplFactory extends OCLExpressionImplFactory {

	public TypeExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}

	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) {
		//since the factory is created according to the rule fragment, the fragment is got as a type item fragment
		TypeExpImpl typeExpImpl = (TypeExpImpl) this.m_NamedElementImpl;
		//a variable name is generated from the type name and the referred argument
		String varXName = Util.generateVarName(typeExpImpl, referredArgument);
		
		//and a swrl variable is created from the varXName
		SWRLVariable varZ = factory.getSWRLVariable(IRI.create(nameSpace+varXName));
		
		//the variable is added to a variable array and returned
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(varZ);
		
		return retArgs;
	}
}