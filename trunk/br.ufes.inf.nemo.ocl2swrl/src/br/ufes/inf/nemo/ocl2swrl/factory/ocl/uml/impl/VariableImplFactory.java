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
import br.ufes.inf.nemo.ocl2swrl.exceptions.NonImplemented;
import br.ufes.inf.nemo.ocl2swrl.exceptions.NonSupported;
import br.ufes.inf.nemo.ocl2swrl.factory.Factory;
import br.ufes.inf.nemo.ocl2swrl.factory.uml2.uml.internal.impl.TypedElementImplFactory;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:13
 */
public class VariableImplFactory extends TypedElementImplFactory {
	OCLExpressionImplFactory initExpressionFactory;
	
	public VariableImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	
	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies)  throws NonImplemented, NonSupported{
		VariableImpl variable = (VariableImpl) this.m_NamedElementImpl;
		
		OCLExpression initExpression = (OCLExpression) variable.getInitExpression();
		this.initExpressionFactory = (OCLExpressionImplFactory) Factory.constructor(initExpression, this.m_NamedElementImpl);
		ArrayList<SWRLDArgument> retArgsX = this.initExpressionFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, operatorNot, repeatNumber, leftSideOfImplies);
		SWRLDArgument varX = retArgsX.get(retArgsX.size()-1);//pega o ultimo
		
		String varName = variable.getName();
		SWRLVariable var = factory.getSWRLVariable(IRI.create(nameSpace+varName));
		
		SWRLSameIndividualAtom same0 = factory.getSWRLSameIndividualAtom((SWRLVariable)varX, (SWRLVariable)var);
		
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, same0);
		
		return null;
	}
}