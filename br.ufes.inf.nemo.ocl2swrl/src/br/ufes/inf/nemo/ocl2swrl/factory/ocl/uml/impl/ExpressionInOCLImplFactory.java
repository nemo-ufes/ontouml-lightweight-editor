package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.uml.impl.ExpressionInOCLImpl;
import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
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

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2swrl.factory.uml2.uml.internal.impl.NamedElementImplFactory;
import br.ufes.inf.nemo.ocl2swrl.tags.Tag;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:12
 */
public class ExpressionInOCLImplFactory extends OpaqueExpressionImplFactory {

	public OCLExpressionImplFactory bodyExpressionFactory;
	public PropertyCallExpImplFactory elementFactory;
	Element element;
	
	public ExpressionInOCLImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	
	public void setElement(Element element) {
		this.element = element;
	}
	
	public Element getElement() {
		return element;
	}
	
	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) {
		ExpressionInOCLImpl expressionInOCLImpl = (ExpressionInOCLImpl) this.m_NamedElementImpl;
		OCLExpressionImpl bodyExpression = (OCLExpressionImpl) expressionInOCLImpl.getBodyExpression();
		
		bodyExpressionFactory = (OCLExpressionImplFactory) NamedElementImplFactory.constructor(bodyExpression);
		bodyExpressionFactory.setIsBodyExpression(true);
		/*
		if(bodyExpressionFactory.isImpliesOperation()){
			oclConsequentShouldBeNegated = true;
		}
		*/
		Variable<Classifier, Parameter> contextVariable = expressionInOCLImpl.getContextVariable();
		Classifier classContVar = contextVariable.getType();
		
		//create a swrl variable with the self name
		String contVarName = classContVar.getName();
		String iriName = nameSpace+contVarName;
		iriName = iriName.replace(" ", "_");
		IRI iri = IRI.create(iriName);
		SWRLVariable contextVar = factory.getSWRLVariable(iri);
		
		bodyExpressionFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, contextVar, operatorNot, repeatNumber, leftSideOfImplies);
		
		Boolean isTag = true;
		try {
			@SuppressWarnings("unused")
			Tag tag = Tag.valueOf(ctStereotype);
		} catch (Exception e) {
			isTag = false;
		}
		
		if(!isTag){
			
			if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
				OWLClass owlClass = factory.getOWLClass(iri);
				//get the complement of the self
				OWLObjectComplementOf complementOf = factory.getOWLObjectComplementOf(owlClass);
				//create a swrl atom that means swrlVariable isn't self
				SWRLClassAtom atom = factory.getSWRLClassAtom(complementOf, contextVar);
				consequent.add(atom);
				
				
			}else if(org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype)){
				this.elementFactory = new PropertyCallExpImplFactory(m_NamedElementImpl, (Property) element);
				this.elementFactory.solveProperty(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, contextVar, operatorNot, 1);
				
			}
			
			
			//create a rule with the incremented antecedents and consequents
			SWRLRule rule = factory.getSWRLRule(antecedent,consequent);
			
			//apply changes in the owl manager
			manager.applyChange(new AddAxiom(ontology, rule));
		}
		
		return null;		
	}
}