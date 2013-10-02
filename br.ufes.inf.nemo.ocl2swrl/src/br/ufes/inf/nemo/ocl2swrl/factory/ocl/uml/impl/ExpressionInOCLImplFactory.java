package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.Set;

import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.uml.impl.ExpressionInOCLImpl;
import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLClassAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;

import br.ufes.inf.nemo.ocl2swrl.factory.uml2.uml.internal.impl.NamedElementImplFactory;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:12
 */
public class ExpressionInOCLImplFactory extends OpaqueExpressionImplFactory {

	public OCLExpressionImplFactory bodyExpressionFactory;
	
	public ExpressionInOCLImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	
	@Override
	public SWRLDArgument solve(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated, int repeatNumber) {
		ExpressionInOCLImpl expressionInOCLImpl = (ExpressionInOCLImpl) this.m_NamedElementImpl;
		OCLExpressionImpl bodyExpression = (OCLExpressionImpl) expressionInOCLImpl.getBodyExpression();
		
		bodyExpressionFactory = (OCLExpressionImplFactory) NamedElementImplFactory.constructor(bodyExpression);
		bodyExpressionFactory.setIsBodyExpression(true);
		if(bodyExpressionFactory.isImpliesOperation()){
			oclConsequentShouldBeNegated = true;
		}
		
		Variable<Classifier, Parameter> contextVariable = expressionInOCLImpl.getContextVariable();
		Classifier classContVar = contextVariable.getType();
		
		//create a swrl variable with the self name
		String contVarName = classContVar.getName();
		String iriName = nameSpace+contVarName;
		IRI iri = IRI.create(iriName);
		SWRLVariable contextVar = factory.getSWRLVariable(iri);
		
		bodyExpressionFactory.solve(nameSpace, manager, factory, ontology, antecedent, consequent, null, oclConsequentShouldBeNegated, expressionIsNegated, repeatNumber);
		
		OWLClass owlClass = factory.getOWLClass(iri);
		//get the complement of the self
		OWLObjectComplementOf complementOf = factory.getOWLObjectComplementOf(owlClass);
		//create a swrl atom that means swrlVariable isn't self
		SWRLClassAtom atom = factory.getSWRLClassAtom(complementOf, contextVar);
		consequent.add(atom);
		
		//create a rule with the incremented antecedents and consequents
		SWRLRule rule = factory.getSWRLRule(antecedent,consequent);
		
		//apply changes in the owl manager
		manager.applyChange(new AddAxiom(ontology, rule));
		
		return null;
		
	}
}