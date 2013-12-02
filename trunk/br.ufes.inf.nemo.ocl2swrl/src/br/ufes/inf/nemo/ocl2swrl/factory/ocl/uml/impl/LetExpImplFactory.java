package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

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

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2swrl.exceptions.NonImplemented;
import br.ufes.inf.nemo.ocl2swrl.exceptions.NonSupported;
import br.ufes.inf.nemo.ocl2swrl.factory.Factory;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:12
 */
public class LetExpImplFactory extends OCLExpressionImplFactory {
	OCLExpressionImplFactory inFactory;
	VariableImplFactory variableFactory;
	
	public LetExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}
	
	public void finalize() throws Throwable {
		super.finalize();
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) throws NonImplemented, NonSupported {
		LetExpImpl let = (LetExpImpl) this.m_NamedElementImpl;
		Variable variable = let.getVariable();
		OCLExpression in = let.getIn();
		
		this.variableFactory = (VariableImplFactory) Factory.constructor(variable, this.m_NamedElementImpl);
		ArrayList<SWRLDArgument> retArgsX = this.variableFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, operatorNot, repeatNumber, leftSideOfImplies); 
		
		this.inFactory = (OCLExpressionImplFactory) Factory.constructor(in, this.m_NamedElementImpl);
		ArrayList<SWRLDArgument> retArgsY = this.inFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, operatorNot, repeatNumber, leftSideOfImplies);
		
		return null;
	}
}