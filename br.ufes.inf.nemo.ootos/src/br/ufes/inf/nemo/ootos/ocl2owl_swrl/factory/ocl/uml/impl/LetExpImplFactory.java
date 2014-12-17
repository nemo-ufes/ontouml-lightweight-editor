package br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.uml.impl.LetExpImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.Ocl2Owl_SwrlException;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.Factory;

/**
 * @author Freddy Brasileiro Silva {freddybrasileiro@gmail.com}
 */
public class LetExpImplFactory extends OCLExpressionImplFactory {	
	OCLExpressionImplFactory inFactory;
	VariableImplFactory variableFactory;
	
	public LetExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) throws Ocl2Owl_SwrlException{
		//since the factory is created according to the rule fragment, the fragment is got as a let fragment
		LetExpImpl let = (LetExpImpl) this.m_NamedElementImpl;
		//then, the variable of the let is got
		Variable variable = let.getVariable();
		//and the IN of the let is got
		OCLExpression in = let.getIn();
		
		//and a factory is created according to the variable class 
		this.variableFactory = (VariableImplFactory) Factory.constructor(variable, this.m_NamedElementImpl);
		
		//the let is always on the left side of the operator implies
		Boolean variableLeftSideOfImplies = true;
		//the variable is solved and the and the returned arguments from the variableSolveMethod above are returned 
		ArrayList<SWRLDArgument> retArgsX = this.variableFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, operatorNot, repeatNumber, variableLeftSideOfImplies); 
		
		//esse trecho foi incluido para se adaptar ao exemplo da tabela 12 do meu tcc
		if(retArgsX.size()>0){
			referredArgument = retArgsX.get(retArgsX.size()-1);
		}		
		
		//and a factory is created according to the IN class 
		this.inFactory = (OCLExpressionImplFactory) Factory.constructor(in, this.m_NamedElementImpl);
		//the IN is solved and the and the returned arguments from the inSolveMethod above are returned 
		ArrayList<SWRLDArgument> retArgsY = this.inFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, operatorNot, repeatNumber, leftSideOfImplies);
		
		return null;
	}
}