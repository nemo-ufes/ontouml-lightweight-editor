package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.uml.impl.VariableImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLSameIndividualAtom;
import org.semanticweb.owlapi.model.SWRLVariable;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2swrl.exceptions.Ocl2SwrlException;
import br.ufes.inf.nemo.ocl2swrl.factory.Factory;
import br.ufes.inf.nemo.ocl2swrl.factory.uml2.uml.internal.impl.TypedElementImplFactory;

/**
 * @author Freddy Brasileiro Silva {freddybrasileiro@gmail.com}
 */
public class VariableImplFactory extends TypedElementImplFactory {
	OCLExpressionImplFactory initExpressionFactory;
	
	public VariableImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}
	
	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) throws Ocl2SwrlException{
		//since the factory is created according to the rule fragment, the fragment is got as a variable fragment
		VariableImpl variable = (VariableImpl) this.m_NamedElementImpl;
		
		//then, the initExpression of the variable is got
		OCLExpression initExpression = (OCLExpression) variable.getInitExpression();
		//and a factory is created according to the initExpression class 
		this.initExpressionFactory = (OCLExpressionImplFactory) Factory.constructor(initExpression, this.m_NamedElementImpl);
		//the initExpression is solved and the and the returned arguments from the initExpressionSolveMethod above are returned 
		ArrayList<SWRLDArgument> retArgsX = this.initExpressionFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, operatorNot, repeatNumber, leftSideOfImplies);
		SWRLDArgument varX = retArgsX.get(retArgsX.size()-1);//get the last
		
		//the variable name is got
		String varName = variable.getName();
		//and a swrl variable is created from the varName
		SWRLVariable var = factory.getSWRLVariable(IRI.create(nameSpace+varName));
		
		//then, var and varX are considered the same one
		SWRLSameIndividualAtom same0 = factory.getSWRLSameIndividualAtom((SWRLVariable)varX, (SWRLVariable)var);
		
		//the atom is inserted in the antecedent or in the consequent
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, same0);
		
		return null;
	}
}