package br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl;

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
import org.semanticweb.owlapi.model.SWRLSameIndividualAtom;
import org.semanticweb.owlapi.model.SWRLVariable;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.ConsequentVariableNonDeclaredOnAntecedent;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.Ocl2Owl_SwrlException;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.UnexpectedResultingRule;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.Factory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.uml2.uml.internal.impl.NamedElementImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.tags.Tag;



/**
 * @author Freddy Brasileiro Silva {freddybrasileiro@gmail.com}
 */
public class ExpressionInOCLImplFactory extends OpaqueExpressionImplFactory {

	public OCLExpressionImplFactory bodyExpressionFactory;
	public PropertyCallExpImplFactory elementFactory;
	Element element;
	
	public void setElement(Element element) {
		this.element = element;
	}
	
	public ExpressionInOCLImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}
	
	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) throws Ocl2Owl_SwrlException{
		//since the factory is created according to the rule fragment, the fragment is got as a expression fragment
		ExpressionInOCLImpl expressionInOCLImpl = (ExpressionInOCLImpl) this.m_NamedElementImpl;
		//then, the body of the expression is got
		OCLExpressionImpl bodyExpression = (OCLExpressionImpl) expressionInOCLImpl.getBodyExpression();
		
		//and a factory is created according to the bodyExpression class 
		bodyExpressionFactory = (OCLExpressionImplFactory) NamedElementImplFactory.constructor(bodyExpression, this.m_NamedElementImpl);
		//bodyExpressionFactory.setIsBodyExpression(true);

		//then, the context variable of the expression is got
		Variable<Classifier, Parameter> contextVariable = expressionInOCLImpl.getContextVariable();
		Classifier classContVar = contextVariable.getType();
		
		//create a swrl variable with the self name
		String contVarName = classContVar.getName();
		String iriName = nameSpace+contVarName;
		iriName = iriName.replace(" ", "_");
		IRI iri = IRI.create(iriName);
		SWRLVariable contextVar = factory.getSWRLVariable(iri);
		
		//the bodyExpression is solved and the and the returned arguments from the bodyExpressionSolveMethod above are returned 
		ArrayList<SWRLDArgument> retArgsX = bodyExpressionFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, contextVar, operatorNot, repeatNumber, leftSideOfImplies);
		
		//verify if is the stereotype is a tag
		Boolean isTag = Tag.isTag(ctStereotype);
		/*Boolean isTag = true;
		try {
			@SuppressWarnings("unused")
			Tag tag = Tag.valueOf(ctStereotype);
		} catch (Exception e) {
			isTag = false;
		}
		*/
		if(isTag){
			String strRule = bodyExpression.toString();
			int isImpliesOperation = strRule.indexOf("implies");
			
			//the tag DERIVE is used combined with the IMPLIES operator
			if(ctStereotype.equals(Tag.derive.toString()) && isImpliesOperation < 0){
				//then, the context class is inserted as the unique atom on the antecedent
				OWLClass owlClass = factory.getOWLClass(iri);
				SWRLClassAtom atom = factory.getSWRLClassAtom(owlClass, contextVar);
				antecedent.add(atom);
			}
		}else{
			
			if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
				//in case of invariants, the complement of the context class is always considered the unique atom on the consequent
				OWLClass owlClass = factory.getOWLClass(iri);
				//get the complement of the self
				OWLObjectComplementOf complementOf = factory.getOWLObjectComplementOf(owlClass);
				//create a swrl atom that means swrlVariable isn't self
				SWRLClassAtom atom = factory.getSWRLClassAtom(complementOf, contextVar);
				consequent.add(atom);
				
				
			}else if(org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype)){
				//in the derivations case, the context is always considered the unique atom on the consequent
				this.elementFactory = new PropertyCallExpImplFactory(m_NamedElementImpl, (Property) element);
				ArrayList<SWRLDArgument> retArgsY = this.elementFactory.solvePropertyAssociation(refParser, nameSpace, manager, factory, ontology, antecedent, consequent, contextVar, operatorNot, 1);
				
				if(retArgsX.size() > 0 && retArgsY.size() > 0){
					SWRLDArgument varX = retArgsX.get(retArgsX.size()-1);
					SWRLDArgument varY = retArgsY.get(retArgsY.size()-1);
					
					SWRLSameIndividualAtom same = factory.getSWRLSameIndividualAtom((SWRLVariable)varX, (SWRLVariable)varY);
					antecedent.add(same);
				}				
			}
		}
		
		if(antecedent.size()>0 && consequent.size()>0){
			//this.solveTempVariables(ctStereotype, factory, antecedent, consequent);
			
			//create a rule with the incremented antecedents and consequents
			SWRLRule rule = factory.getSWRLRule(antecedent,consequent);
			
			//apply changes in the owl manager
			manager.applyChange(new AddAxiom(ontology, rule));
		}else if(!Tag.isObjectPropertyTag(ctStereotype)){
			//get the string of the rule
			String strRule = Factory.getStrRule(this.m_NamedElementImpl);
			
			//get the null atoms
			String atoms = "";
			if(antecedent.size()==0){
				atoms += "antecedent";
			}
			if(antecedent.size()==0 && consequent.size()==0){
				atoms += "/";
			}
			if(consequent.size()==0){
				atoms += "consequent";
			}
			
			//throw a new exception
			throw new UnexpectedResultingRule(atoms, strRule);
		}

		String nonDeclVar = Factory.allConsequentVariablesExistsInAntecedent(antecedent, consequent);
		if(!nonDeclVar.equals("")){
			String strRule = Factory.getStrRule(this.m_NamedElementImpl);
			throw new ConsequentVariableNonDeclaredOnAntecedent(nonDeclVar, strRule);
		}
		
		return null;		
	}
}